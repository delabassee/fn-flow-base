package com.fn;

import com.fnproject.fn.api.flow.*;
import com.fnproject.fn.runtime.flow.FlowFeature;
import com.fnproject.fn.api.FnFeature;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;

@FnFeature(FlowFeature.class)
public class FlowFunction implements Serializable{

    public String handleSimpleInvocation(String input) {

		final String funcA = "01D2MWS1E1NG8G00GZJ0000001";

        Flow flow = Flows.currentFlow();

		FlowFuture<byte[]> stage = 
			flow.invokeFunction(funcA, POST, emptyHeaders(), input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }


    public String handleParallelInvocation(String input) {

        final String funcA = "01D2MWS1E1NG8G00GZJ0000001";

        Flow flow = Flows.currentFlow();

        FlowFuture<byte[]> stageA =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 3000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        FlowFuture<byte[]> stageB =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 2000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        FlowFuture<byte[]> stageC =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 1000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        FlowFuture<byte[]> stageD =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 500 )
                        .thenApply(HttpResponse::getBodyAsBytes);


        List<FlowFuture<byte[]>> listFlows = allResults(stageA, stageB, stageC, stageD).get();

        /*listFlows.forEach(flowFuture -> {
            flowFuture.whenComplete((bytes, throwable) -> {
                if (throwable != null) {
                    System.out.println("---> Exception: " + throwable);
                } else {
                    System.out.println("---> individualResponse : " + new String(bytes));
                }
            });
        });*/

        List<String> results = new ArrayList<>();

        for (FlowFuture<byte[]> flowFuture : listFlows) {
            FlowFuture<String> result = flowFuture.thenApply((bytes) -> {
                return new String(bytes);
            });
            results.add(result.get());
        }


        return "handleParallelInvocation : " + results.toString();
    }

    public <X> FlowFuture<List<FlowFuture<X>>> allResults(FlowFuture<X>... flows) {
        return Flows.currentFlow().allOf(flows).thenApply(ignored -> Arrays.asList(flows));
    }

}
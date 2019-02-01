package com.fn;

import com.fnproject.fn.api.flow.*;
import com.fnproject.fn.runtime.flow.FlowFeature;
import com.fnproject.fn.api.FnFeature;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;

@FnFeature(FlowFeature.class)
public class FlowFunction {

    public String handleRequestSimple(String input) {

		final String funcA = "01D2MWS1E1NG8G00GZJ0000001";

        Flow flow = Flows.currentFlow();

		FlowFuture<byte[]> stage = 
			flow.invokeFunction(funcA, POST, emptyHeaders(), input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }


    public String handleRequest(String input) {

        final String funcA = "01D2MWS1E1NG8G00GZJ0000001";


        Flow flow = Flows.currentFlow();

        FlowFuture<byte[]> stageA =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 6000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        FlowFuture<byte[]> stageB =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 2000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        FlowFuture<byte[]> stageC =
                flow.invokeFunction(funcA, POST, emptyHeaders(), 1000 )
                        .thenApply(HttpResponse::getBodyAsBytes);

        List<FlowFuture<byte[]>> listFlows = allResults(stageA, stageB, stageC).get();

        listFlows.forEach(flowFuture -> {
            System.out.println(" flowFuture -> " + flowFuture.get());
        });

        return input;
    }

    public <X> FlowFuture<List<FlowFuture<X>>> allResults(FlowFuture<X>... flows) {
        return Flows.currentFlow().allOf(flows).thenApply(ignored -> Arrays.asList(flows));
    }
}
package com.fn;

import com.fnproject.fn.api.flow.*;
import com.fnproject.fn.runtime.flow.FlowFeature;
import com.fnproject.fn.api.FnFeature;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;

@FnFeature(FlowFeature.class)
public class FlowFunction {

    public String handleRequest(String input) {

		final String funcA = "";

        Flow flow = Flows.currentFlow();

		FlowFuture<byte[]> stage = 
			flow.invokeFunction(funcA, POST, emptyHeaders(), input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }
}
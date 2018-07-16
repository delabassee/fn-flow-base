package com.example.fn;

import com.fnproject.fn.api.flow.*;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;


public class HelloFunction {

    public String handleRequest(String input) {

        Flow flow = Flows.currentFlow();

        FlowFuture<byte[]> stage = flow.invokeFunction( "./duke", POST,
                emptyHeaders(), input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }
}
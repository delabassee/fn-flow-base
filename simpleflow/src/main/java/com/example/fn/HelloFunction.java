package com.example.fn;

import com.fnproject.fn.api.flow.*;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;


public class HelloFunction {

    public String handleRequest(String input) {

        Flow flow = Flows.currentFlow();

        // invokeFunction  now takes an Fn Function ID (opaque string) rather than `app/path`
        // fn inspect fn app invokedFunction

        //FlowFuture<byte[]> stage = flow.invokeFunction( "./duke", POST,
		FlowFuture<byte[]> stage = flow.invokeFunction( "...functionId...", POST,
                emptyHeaders(), input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }
}
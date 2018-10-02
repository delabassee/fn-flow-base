package com.example.fn;

import com.fnproject.fn.api.flow.*;

import static com.fnproject.fn.api.Headers.emptyHeaders;
import static com.fnproject.fn.api.flow.HttpMethod.POST;


public class HelloFunction {

    public String handleRequest(String input) {

        Flow flow = Flows.currentFlow();

        // invokeFunction  now takes an Fn Function ID of the func to invoke  rather than `app/path`
        // `fn inspect function appName fnName`

		FlowFuture<byte[]> stage = flow.invokeFunction( "01CRQVKFZDNG8G00GZJ0000001", POST,
                emptyHeaders(),
				input.getBytes() )
                .thenApply(HttpResponse::getBodyAsBytes);

        return new String(stage.get());
    }
}
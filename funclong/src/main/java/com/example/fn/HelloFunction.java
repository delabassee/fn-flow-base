package com.example.fn;

import static java.lang.Thread.sleep;

public class HelloFunction {

    public String handleRequest(int duration) {

        if (duration <= 0)  duration = 5000;

        try {
            sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Slept for " + duration + " ms.";
    }

}
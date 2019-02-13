package com.example.fn;

public class CalcFunction {

    public Payload doubleUp(Payload input) {

        if (input.getValue()>10) {
            throw new RuntimeException("Value too high to compute!");
        }
        else {
            return new Payload(input.getValue()*2);
        }

    }
}



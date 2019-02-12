package com.example.fn;

public class CalcFunction {

    public Payload doubleUp(Payload input) {
        return new Payload(input.getValue()*2);
    }

}



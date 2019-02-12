package com.example.fn;

public class HelloFunction {

    public Result doubleUp(Result input) {
        return new Result(input.getValue()*2);
    }

}



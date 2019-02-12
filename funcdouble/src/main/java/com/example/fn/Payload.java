package com.example.fn;

import java.io.Serializable;


class Payload implements Serializable {

    private int value;

    public Payload(int value) {
        this.value = value;
    }

    public Payload() {
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}

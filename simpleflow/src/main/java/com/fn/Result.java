package com.fn;

import java.io.Serializable;


    class Result implements Serializable {

        private int value;

        public Result(int value) {
            this.value = value;
        }

        public Result() {
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

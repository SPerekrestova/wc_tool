package org.example;

import java.util.Arrays;

public enum Option {
    BYTES("-c"),
    LINES("-l"),
    WORDS("-w"),
    CHARS("-m");

    private final String param;
    Option(String param) {
        this.param = param;
    }

    public static Option find(String param) {
        return Arrays.stream(values()).filter(value -> value.param.equals(param)).findFirst().orElse(null);
    }
}

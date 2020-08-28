package com.github.onozaty.junit.xml2csv;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TestCase {

    private String className;

    private String name;

    private double time;

    private Result result;

    public static enum Result {
        PASSED, SKIPED, FAILURE, ERROR
    }
}

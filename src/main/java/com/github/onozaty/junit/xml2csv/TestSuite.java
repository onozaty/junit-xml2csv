package com.github.onozaty.junit.xml2csv;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Builder;
import lombok.Singular;
import lombok.Value;

@Value
@Builder
public class TestSuite {

    private String name;

    private LocalDateTime timestamp;

    private double time;

    @Singular
    private List<TestCase> testCases;
}

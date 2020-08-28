package com.github.onozaty.junit.xml2csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import com.github.onozaty.junit.xml2csv.TestCase.Result;

public class XmlParserTest {

    @Test
    public void parse() throws URISyntaxException, ParserConfigurationException, SAXException, IOException {

        Path xmlPath = Paths.get(getClass().getResource("TestCase1.xml").toURI());

        TestSuite testSuite = new XmlParser().parse(xmlPath);

        assertThat(testSuite).isEqualTo(
                TestSuite.builder()
                        .name("com.github.onozaty.junit.xml2csv.TestCase1")
                        .timestamp(LocalDateTime.of(2020, 8, 28, 4, 39, 49))
                        .time(0.003)
                        .testCase(
                                TestCase.builder()
                                        .className("com.github.onozaty.junit.xml2csv.TestCase1")
                                        .name("test1")
                                        .time(0)
                                        .result(Result.PASSED)
                                        .build())
                        .testCase(
                                TestCase.builder()
                                        .className("com.github.onozaty.junit.xml2csv.TestCase1")
                                        .name("test2")
                                        .time(0)
                                        .result(Result.FAILURE)
                                        .build())
                        .testCase(
                                TestCase.builder()
                                        .className("com.github.onozaty.junit.xml2csv.TestCase1")
                                        .name("test3")
                                        .time(0.001)
                                        .result(Result.ERROR)
                                        .build())
                        .testCase(
                                TestCase.builder()
                                        .className("com.github.onozaty.junit.xml2csv.TestCase1")
                                        .name("test4")
                                        .time(0)
                                        .result(Result.SKIPED)
                                        .build())
                        .testCase(
                                TestCase.builder()
                                        .className("com.github.onozaty.junit.xml2csv.TestCase1")
                                        .name("test5")
                                        .time(0.002)
                                        .result(Result.PASSED)
                                        .build())
                        .build());

    }
}

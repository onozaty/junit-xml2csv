package com.github.onozaty.junit.xml2csv;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.github.onozaty.junit.xml2csv.TestCase.Result;

public class XmlParser {

    public TestSuite parse(Path xmlPath) {

        Document document = parseXml(xmlPath);

        Element testSuiteElement = document.getDocumentElement();
        NodeList testCaseNodeList = testSuiteElement.getElementsByTagName("testcase");

        List<TestCase> testCases = toStream(testCaseNodeList)
                .map(Element.class::cast)
                .map(this::toTestCase)
                .collect(Collectors.toList());

        return TestSuite.builder()
                .name(testSuiteElement.getAttribute("name"))
                .timestamp(getAttributeDateTime(testSuiteElement, "timestamp"))
                .time(getAttributeDouble(testSuiteElement, "time"))
                .testCases(testCases)
                .build();
    }

    private Document parseXml(Path xmlPath) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();

            return builder.parse(xmlPath.toFile());

        } catch (Exception e) {
            throw new XmlParseException(e);
        }
    }

    private TestCase toTestCase(Element testCaseElement) {

        return TestCase.builder()
                .className(testCaseElement.getAttribute("classname"))
                .name(testCaseElement.getAttribute("name"))
                .time(getAttributeDouble(testCaseElement, "time"))
                .result(judgeResult(testCaseElement))
                .build();
    }

    private Result judgeResult(Element testCaseElement) {

        for (Node node : toList(testCaseElement.getChildNodes())) {

            switch (node.getNodeName()) {
                case "skipped":
                    return Result.SKIPED;
                case "failure":
                    return Result.FAILURE;
                case "error":
                    return Result.ERROR;
                default:
                    break;
            }
        }

        return Result.PASSED;
    }

    private double getAttributeDouble(Element element, String attributeName) {

        String value = element.getAttribute(attributeName);
        return Double.parseDouble(value);
    }

    private LocalDateTime getAttributeDateTime(Element element, String attributeName) {

        String value = element.getAttribute(attributeName);
        return LocalDateTime.parse(value);
    }

    private Stream<Node> toStream(NodeList nodeList) {

        return IntStream.range(0, nodeList.getLength())
                .mapToObj(nodeList::item);
    }

    private List<Node> toList(NodeList nodeList) {

        return toStream(nodeList)
                .collect(Collectors.toList());
    }

    private static class XmlParseException extends RuntimeException {

        private static final long serialVersionUID = 9015685750788432889L;

        private XmlParseException(Throwable cause) {
            super(cause);
        }
    }
}

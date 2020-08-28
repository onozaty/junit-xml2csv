package com.github.onozaty.junit.xml2csv;

import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class Converter {

    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("usage: java -jar junit-xml2csv-all.jar <junit xml directory> <output csv file>");
            System.exit(1);
        }

        Path inputDirectoryPath = Paths.get(args[0]);
        Path outputFilePath = Paths.get(args[1]);

        new Converter().convert(inputDirectoryPath, outputFilePath);
    }

    public void convert(Path inputDirectoryPath, Path outputFilePath) throws IOException {

        XmlParser parser = new XmlParser();

        List<TestSuite> testSuites = Files.list(inputDirectoryPath)
                .filter(Files::isRegularFile)
                .filter(x -> x.getFileName().toString().toLowerCase().endsWith(".xml"))
                .map(parser::parse)
                .sorted(Comparator.comparing(TestSuite::getTimestamp))
                .collect(Collectors.toList());

        try (
                Writer writer = Files.newBufferedWriter(outputFilePath, StandardCharsets.UTF_8);
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL)) {

            writer.write("\uFEFF"); // BOM

            // Header
            csvPrinter.printRecord(
                    "TestSuite: Name",
                    "TestSuite: Timestamp",
                    "TestCase: ClassName",
                    "TestCase: Name",
                    "TestCase: Time",
                    "TestCase: Result");

            for (TestSuite testSuite : testSuites) {
                write(csvPrinter, testSuite);
            }
        }
    }

    private void write(CSVPrinter printer, TestSuite testSuite) throws IOException {

        for (TestCase testCase : testSuite.getTestCases()) {
            printer.printRecord(
                    testSuite.getName(),
                    testSuite.getTimestamp(),
                    testCase.getClassName(),
                    testCase.getName(),
                    testCase.getTime(),
                    testCase.getResult());
        }
    }
}

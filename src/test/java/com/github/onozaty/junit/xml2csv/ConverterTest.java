package com.github.onozaty.junit.xml2csv;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Test;

public class ConverterTest {

    @Test
    public void convert() throws URISyntaxException, IOException {

        Path xmlDirectoryPath = Paths.get(getClass().getResource("xmls").toURI());
        Path expectedPath = Paths.get(getClass().getResource("convert-expected.csv").toURI());
        Path outputTempFilePath = Files.createTempFile(null, ".csv");

        try {
            new Converter().convert(xmlDirectoryPath, outputTempFilePath);

            assertThat(outputTempFilePath)
                    .hasSameBinaryContentAs(expectedPath);
        } finally {
            Files.delete(outputTempFilePath);
        }

    }

}

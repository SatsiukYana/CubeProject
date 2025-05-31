package com.epam.cube;

import com.epam.cube.exception.InvalidPathException;
import com.epam.cube.parser.DataParser;
import com.epam.cube.reader.DataReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);
    private static final String DEFAULT_PATH = "data/cube_data.txt";

    public static void main(String[] args) {
        try {
            List<String> lines = DataReader.readData(DEFAULT_PATH);
            DataParser dataParser = new DataParser();
            log.info(dataParser.parse(lines).toString());
        } catch (InvalidPathException e) {
            log.error("Cannot proceed due to file reading error: {}", e.getMessage());
        }
    }
}



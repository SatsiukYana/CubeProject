package com.epam.cube.reader;

import com.epam.cube.exception.InvalidPathException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class DataReader {
    private static final Logger log = LoggerFactory.getLogger(DataReader.class);

    public static List<String> readData(String path) throws InvalidPathException {
        Path inputPath = Paths.get(path);

        try {
            log.info("Reading data from file: {}", path);
            return Files.readAllLines(inputPath);
        } catch (IOException e) {
            log.error("Unable to read file at '{}'", path, e);
            throw new InvalidPathException("Failed to read from path: " + path);
        }
    }
}
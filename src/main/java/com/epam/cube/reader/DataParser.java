package com.epam.cube.reader;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.exception.InvalidPathException;
import com.epam.cube.factory.CubeFactory;
import com.epam.cube.factory.PointFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    private final Logger log = LoggerFactory.getLogger(DataParser.class);
    private final String DEFAULT_PATH = "data/cube_data.txt";
    private final int EXPECTED_FIELD_COUNT = 6;

    public List<Cube> parse() {
        return parse(DEFAULT_PATH);
    }

    public List<Cube> parse(String path) {
        log.info("Parsing data from path: {}", path);
        List<String> lines;

        try {
            lines = DataReader.readData(path);
        } catch (InvalidPathException e) {
            log.error("Failed to read data from path '{}': {}", path, e.getMessage());
            return new ArrayList<>();
        }

        List<Cube> cubes = new ArrayList<>();

        for (String line : lines) {
            String[] arr = line.split(";");
            if (arr.length < EXPECTED_FIELD_COUNT) {
                log.warn("Invalid line skipped (expected {} fields): {}", EXPECTED_FIELD_COUNT, line);
                continue;
            }

            try {
                Point point = PointFactory.create(
                        Double.parseDouble(arr[0]),
                        Double.parseDouble(arr[1]),
                        Double.parseDouble(arr[2])
                );

                Cube cube = CubeFactory.create(
                        Long.parseLong(arr[3]),
                        arr[4],
                        point,
                        Double.parseDouble(arr[5])
                );

                cubes.add(cube);
            } catch (NumberFormatException e) {
                log.warn("Failed to parse line due to number format error: {}", line);
            }
        }

        return cubes;
    }
}

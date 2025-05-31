package com.epam.cube.parser;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.factory.CubeFactory;
import com.epam.cube.factory.Factory;
import com.epam.cube.factory.PointFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class DataParser {
    private final Logger log = LoggerFactory.getLogger(DataParser.class);
    private final int EXPECTED_FIELD_COUNT = 6;

    public List<Cube> parse(List<String> lines) {
        log.info("Parsing {} lines of cube data", lines.size());

        List<Cube> cubes = new ArrayList<>();

        for (String line : lines) {
            String[] arr = line.split(";");
            if (arr.length < EXPECTED_FIELD_COUNT) {
                log.warn("Invalid line skipped (expected {} fields): {}", EXPECTED_FIELD_COUNT, line);
                continue;
            }

            try {
                Factory<Point> pointFactory = PointFactory.create(
                        Double.parseDouble(arr[0]),
                        Double.parseDouble(arr[1]),
                        Double.parseDouble(arr[2])
                );
                Point point = pointFactory.create();

                Factory<Cube> cubeFactory = CubeFactory.create(
                        Long.parseLong(arr[3]),
                        arr[4],
                        point,
                        Double.parseDouble(arr[5])
                );
                Cube cube = cubeFactory.create();

                cubes.add(cube);
            } catch (NumberFormatException e) {
                log.warn("Failed to parse line due to number format error: {}", line);
            }
        }

        return cubes;
    }
}

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
                double x = Double.parseDouble(arr[0]);
                double y = Double.parseDouble(arr[1]);
                double z = Double.parseDouble(arr[2]);
                long id = Long.parseLong(arr[3]);
                String name = arr[4];
                double length = Double.parseDouble(arr[5]);

                if (length <= 0) {
                    log.warn("Cube length must be > 0. Skipping line: {}", line);
                    continue;
                }

                Point point = PointFactory.create(x, y, z).create();
                Cube cube = CubeFactory.create(id, name, point, length).create();
                cubes.add(cube);

            } catch (NumberFormatException e) {
                log.warn("Failed to parse line due to number format error: {}", line);
            } catch (Exception e) {
                log.error("Unexpected error while parsing line: {}", line, e);
            }
        }

        return cubes;
    }
}
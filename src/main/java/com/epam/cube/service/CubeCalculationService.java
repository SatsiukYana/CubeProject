package com.epam.cube.service;

import com.epam.cube.entity.Cube;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CubeCalculationService {
    private static final Logger log = LoggerFactory.getLogger(CubeCalculationService.class);

    public double calculateArea(Cube cube) {
        log.trace("calculateArea");
        return 6 * Math.pow(cube.getLength(), 2);
    }

    public double calculatePerimeter(Cube cube) {
        log.trace("calculatePerimeter");
        return 12 * cube.getLength();
    }

    public double calculateVolume(Cube cube) {
        log.trace("calculateVolume");
        return Math.pow(cube.getLength(), 3);
    }

}

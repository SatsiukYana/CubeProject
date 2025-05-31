package com.epam.cube.specification.impl;

import com.epam.cube.entity.Cube;
import com.epam.cube.specification.CubeSpecification;

public class LengthRangeCubeSpecification implements CubeSpecification {
    private final double min;
    private final double max;

    public LengthRangeCubeSpecification(double min, double max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isSatisfiedBy(Cube cube) {
        double length = cube.getLength();
        return length >= min && length <= max;
    }
}


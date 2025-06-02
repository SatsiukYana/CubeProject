package com.epam.cube.specification.impl;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.CubeSpecification;

public class CoordinateCubeSpecification implements CubeSpecification {

    public enum Axis {
        X, Y, Z
    }

    private final Axis axis;
    private final double min;
    private final double max;

    public CoordinateCubeSpecification(Axis axis, double min, double max) {
        this.axis = axis;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isSatisfiedBy(Cube cube) {
        Point centre = cube.getCentre();
        double value;

        switch (axis) {
            case X -> value = centre.getX();
            case Y -> value = centre.getY();
            case Z -> value = centre.getZ();
            default -> throw new IllegalArgumentException("Unknown axis: " + axis);
        }

        return value >= min && value <= max;
    }
}

package com.epam.cube.specification.impl;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.CubeSpecification;

public class CentrePointEqualsSpecification implements CubeSpecification {
    private final Point expectedPoint;

    public CentrePointEqualsSpecification(Point expectedPoint) {
        this.expectedPoint = expectedPoint;
    }

    @Override
    public boolean isSatisfiedBy(Cube cube) {
        return cube.getCentre() != null && cube.getCentre().equals(expectedPoint);
    }
}

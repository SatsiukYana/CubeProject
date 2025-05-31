package com.epam.cube.specification.impl;

import com.epam.cube.entity.Cube;
import com.epam.cube.specification.CubeSpecification;

public class IdCubeSpecification implements CubeSpecification {
    private final long id;

    public IdCubeSpecification(long id) {
        this.id = id;
    }

    @Override
    public boolean isSatisfiedBy(Cube cube) {
        return cube.getId() == id;
    }
}


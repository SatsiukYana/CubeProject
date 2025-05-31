package com.epam.cube.specification.impl;

import com.epam.cube.entity.Cube;
import com.epam.cube.specification.CubeSpecification;

public class NameCubeSpecification implements CubeSpecification {
    private final String name;

    public NameCubeSpecification(String name) {
        this.name = name;
    }

    @Override
    public boolean isSatisfiedBy(Cube cube) {
        return cube.getName().equalsIgnoreCase(name);
    }
}


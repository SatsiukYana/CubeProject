package com.epam.cube.specification;

import com.epam.cube.entity.Cube;

public interface CubeSpecification {
    boolean isSatisfiedBy(Cube cube);
}
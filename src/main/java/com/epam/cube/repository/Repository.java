package com.epam.cube.repository;

import com.epam.cube.specification.CubeSpecification;
import com.epam.cube.entity.Cube;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private final List<Cube> cubes;

    public Repository(List<Cube> cubes) {
        this.cubes = new ArrayList<>(cubes);
    }

    public void addCube(Cube cube) {
        this.cubes.add(cube);
    }

    public List<Cube> getCubes() {
        return new ArrayList<>(cubes);
    }
    public List<Cube> query(CubeSpecification specification) {
        return cubes.stream()
                .filter(specification::isSatisfiedBy)
                .collect(Collectors.toList());
    }
    public List<Cube> sortBy(Comparator<Cube> comparator) {
        return cubes.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}

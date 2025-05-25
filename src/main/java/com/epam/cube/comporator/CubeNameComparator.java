package com.epam.cube.comporator;

import com.epam.cube.entity.Cube;

import java.util.Comparator;

public class CubeNameComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube c1, Cube c2) {
        return c1.getName().compareTo(c2.getName());
    }
}


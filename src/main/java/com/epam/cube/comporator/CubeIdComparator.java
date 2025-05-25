package com.epam.cube.comporator;

import com.epam.cube.entity.Cube;

import java.util.Comparator;

public class CubeIdComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube c1, Cube c2) {
        return Long.compare(c1.getId(), c2.getId());
    }
}
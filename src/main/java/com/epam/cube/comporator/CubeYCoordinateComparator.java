package com.epam.cube.comporator;

import com.epam.cube.entity.Cube;

import java.util.Comparator;

public class CubeYCoordinateComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube c1, Cube c2) {
        return Double.compare(c1.getCentre().getY(), c2.getCentre().getY());
    }
}


package com.epam.cube.comporator;

import com.epam.cube.entity.Cube;

import java.util.Comparator;

public class CubeXCoordinateComparator implements Comparator<Cube> {
    @Override
    public int compare(Cube c1, Cube c2) {
        return Double.compare(c1.getCentre().getX(), c2.getCentre().getX());
    }
}
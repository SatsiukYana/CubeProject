package com.epam.cube.factory;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;

public class CubeFactory {
    public static Cube create(long id, String name, Point center, double length) {
        return new Cube(id, name, center, length);
    }
}

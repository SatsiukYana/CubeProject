package com.epam.cube.factory;

import com.epam.cube.entity.Point;

public class PointFactory {
    public static Point create(double x, double y, double z) {
        return new Point(x, y, z);
    }
}

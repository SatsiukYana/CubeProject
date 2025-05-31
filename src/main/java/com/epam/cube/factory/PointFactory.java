package com.epam.cube.factory;

import com.epam.cube.entity.Point;
import com.epam.cube.factory.Factory;

public class PointFactory {
    public static Factory<Point> create(double x, double y, double z) {
        return () -> new Point(x, y, z);
    }
}
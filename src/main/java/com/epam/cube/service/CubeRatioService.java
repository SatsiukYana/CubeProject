package com.epam.cube.service;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class CubeRatioService {

    private static final Logger log = LoggerFactory.getLogger(CubeRatioService.class);

    public static Optional<Double> getCubeRatio(Cube cube) {
        log.trace("getCubeRatio");

        Point centre = cube.getCentre();
        double halfLength = cube.getLength() / 2.0;

        double x = centre.getX();
        double y = centre.getY();
        double z = centre.getZ();

        // Проверим, какие оси пересекаются
        boolean xIntersect = Math.abs(x) < halfLength;
        boolean yIntersect = Math.abs(y) < halfLength;
        boolean zIntersect = Math.abs(z) < halfLength;

        if (!xIntersect && !yIntersect && !zIntersect) {
            return Optional.empty();
        }

        // Выбираем ось с наибольшим отклонением от центра
        double maxDistance = -1;
        double selectedCoord = 0;

        if (xIntersect && Math.abs(x) > maxDistance) {
            maxDistance = Math.abs(x);
            selectedCoord = x;
        }
        if (yIntersect && Math.abs(y) > maxDistance) {
            maxDistance = Math.abs(y);
            selectedCoord = y;
        }
        if (zIntersect && Math.abs(z) > maxDistance) {
            maxDistance = Math.abs(z);
            selectedCoord = z;
        }

        return Optional.of(calculateCubeRatio(cube, selectedCoord));
    }

    private static double calculateCubeRatio(Cube cube, double coordToPlane) {
        log.trace("calculateCubeRatio");

        double halfLength = cube.getLength() / 2.0;
        double distance = Math.abs(coordToPlane);

        if (distance >= halfLength) {
            log.warn("Plane does not intersect cube properly. Returning ratio 1.");
            return 1.0;
        }

        double smallerLength = halfLength - distance;
        double smallerVolume = Math.pow(smallerLength, 3);
        double totalVolume = Math.pow(cube.getLength(), 3);
        double largerVolume = totalVolume - smallerVolume;

        if (largerVolume == 0) {
            log.warn("Division by zero in cube ratio calculation");
            return 0;
        }

        return smallerVolume / largerVolume;
    }
}

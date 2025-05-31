package com.epam.cube.validator;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlaneValidator {
    private final Logger log = LoggerFactory.getLogger(PlaneValidator.class);

    public boolean isCubeOnX(Cube cube) {
        log.trace("isCubeOnX");

        Point centre = cube.getCentre();
        return isOnAxis(centre.getX(), cube);
    }

    public boolean isCubeOnY(Cube cube) {
        log.trace("isCubeOnY");

        Point centre = cube.getCentre();
        return isOnAxis(centre.getY(), cube);
    }

    public boolean isCubeOnZ(Cube cube) {
        log.trace("isCubeOnZ");

        Point centre = cube.getCentre();
        return isOnAxis(centre.getZ(), cube);
    }

    public boolean isCubeOnPlane(Cube cube) {
        return isCubeOnX(cube) || isCubeOnY(cube) || isCubeOnZ(cube);
    }

    private boolean isOnAxis(double centre, Cube cube) {
        log.trace("isOnAxis");

        return centre + cube.getLength() / 2 == 0 || centre - cube.getLength() / 2 == 0;
    }
}

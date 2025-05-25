package cube.service;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.validator.ValidatePlaneService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneServiceTest {

    @Test
    void testIsCubeOnX() {
        Cube cubeOnX = new Cube(1,"CubeX", new Point(-2, 0, 0), 4);
        Cube cubeNotOnX = new Cube(1,"CubeNotX", new Point(3, 3, 3), 2);

        Assertions.assertTrue(ValidatePlaneService.isCubeOnX(cubeOnX), "Куб должен пересекать ось X");
        assertFalse(ValidatePlaneService.isCubeOnX(cubeNotOnX), "Куб не должен пересекать ось X");
    }

    @Test
    void testIsCubeOnY() {
        Cube cubeOnY = new Cube(1,"CubeY", new Point(0, -3, 0), 6);
        Cube cubeNotOnY = new Cube(1,"CubeNotY", new Point(10, 10, 10), 1);

        assertTrue(ValidatePlaneService.isCubeOnY(cubeOnY), "Куб должен пересекать ось Y");
        assertFalse(ValidatePlaneService.isCubeOnY(cubeNotOnY), "Куб не должен пересекать ось Y");
    }

    @Test
    void testIsCubeOnZ() {
        Cube cubeOnZ = new Cube(1,"CubeZ", new Point(0, 0, 2), 4);
        Cube cubeNotOnZ = new Cube(1,"CubeNotZ", new Point(2, 2, 2), 2);

        assertTrue(ValidatePlaneService.isCubeOnZ(cubeOnZ), "Куб должен пересекать ось Z");
        assertFalse(ValidatePlaneService.isCubeOnZ(cubeNotOnZ), "Куб не должен пересекать ось Z");
    }

    @Test
    void testIsCubeOnPlane() {
        Cube cubeOnPlane = new Cube(1,"CubeOnPlane", new Point(0, 0, 2), 4);
        Cube cubeNotOnPlane = new Cube(1,"CubeNotOnPlane", new Point(3, 3, 3), 2);

        assertTrue(ValidatePlaneService.isCubeOnPlane(cubeOnPlane), "Куб должен пересекать хотя бы одну плоскость");
        assertFalse(ValidatePlaneService.isCubeOnPlane(cubeNotOnPlane), "Куб не должен пересекать ни одну плоскость");
    }
}
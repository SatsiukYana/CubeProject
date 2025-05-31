package cube.service;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.validator.PlaneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlaneServiceTest {

    private PlaneValidator planeValidator;

    @BeforeEach
    void setUp() {
        planeValidator = new PlaneValidator();
    }

    @Test
    void testIsCubeOnX() {
        Cube cubeOnX = new Cube(1, "CubeX", new Point(-2, 0, 0), 4);
        Cube cubeNotOnX = new Cube(2, "CubeNotX", new Point(3, 3, 3), 2);

        assertTrue(planeValidator.isCubeOnX(cubeOnX), "Куб должен пересекать ось X");
        assertFalse(planeValidator.isCubeOnX(cubeNotOnX), "Куб не должен пересекать ось X");
    }

    @Test
    void testIsCubeOnY() {
        Cube cubeOnY = new Cube(3, "CubeY", new Point(0, -3, 0), 6);
        Cube cubeNotOnY = new Cube(4, "CubeNotY", new Point(10, 10, 10), 1);

        assertTrue(planeValidator.isCubeOnY(cubeOnY), "Куб должен пересекать ось Y");
        assertFalse(planeValidator.isCubeOnY(cubeNotOnY), "Куб не должен пересекать ось Y");
    }

    @Test
    void testIsCubeOnZ() {
        Cube cubeOnZ = new Cube(5, "CubeZ", new Point(0, 0, 2), 4);
        Cube cubeNotOnZ = new Cube(6, "CubeNotZ", new Point(2, 2, 2), 2);

        assertTrue(planeValidator.isCubeOnZ(cubeOnZ), "Куб должен пересекать ось Z");
        assertFalse(planeValidator.isCubeOnZ(cubeNotOnZ), "Куб не должен пересекать ось Z");
    }

    @Test
    void testIsCubeOnPlane() {
        Cube cubeOnPlane = new Cube(7, "CubeOnPlane", new Point(0, 0, 2), 4);
        Cube cubeNotOnPlane = new Cube(8, "CubeNotOnPlane", new Point(3, 3, 3), 2);

        assertTrue(planeValidator.isCubeOnPlane(cubeOnPlane), "Куб должен пересекать хотя бы одну плоскость");
        assertFalse(planeValidator.isCubeOnPlane(cubeNotOnPlane), "Куб не должен пересекать ни одну плоскость");
    }
}

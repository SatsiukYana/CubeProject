package cube.specifications;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.impl.CoordinateCubeSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CoordinateCubeSpecificationTest {

    @Test
    void testXCoordinateWithinRange() {
        Cube cube = new Cube(1L, "TestCube", new Point(5.0, 2.0, 3.0), 4.0);
        CoordinateCubeSpecification spec = new CoordinateCubeSpecification(CoordinateCubeSpecification.Axis.X, 0.0, 10.0);

        assertTrue(spec.isSatisfiedBy(cube), "Координата X должна быть в диапазоне 0.0 - 10.0");
    }

    @Test
    void testXCoordinateOutOfRange() {
        Cube cube = new Cube(2L, "TestCube2", new Point(15.0, 2.0, 3.0), 4.0);
        CoordinateCubeSpecification spec = new CoordinateCubeSpecification(CoordinateCubeSpecification.Axis.X, 0.0, 10.0);

        assertFalse(spec.isSatisfiedBy(cube), "Координата X вне диапазона");
    }

    @Test
    void testYCoordinateWithinRange() {
        Cube cube = new Cube(3L, "TestCube3", new Point(5.0, 7.5, 3.0), 4.0);
        CoordinateCubeSpecification spec = new CoordinateCubeSpecification(CoordinateCubeSpecification.Axis.Y, 5.0, 10.0);

        assertTrue(spec.isSatisfiedBy(cube), "Координата Y должна быть в пределах диапазона");
    }

    @Test
    void testZCoordinateAtBoundary() {
        Cube cube = new Cube(4L, "TestCube4", new Point(5.0, 2.0, 10.0), 4.0);
        CoordinateCubeSpecification spec = new CoordinateCubeSpecification(CoordinateCubeSpecification.Axis.Z, 5.0, 10.0);

        assertTrue(spec.isSatisfiedBy(cube), "Z = 10.0 должен быть включен в диапазон");
    }

    @Test
    void testZCoordinateBelowMinimum() {
        Cube cube = new Cube(5L, "TestCube5", new Point(5.0, 2.0, -1.0), 4.0);
        CoordinateCubeSpecification spec = new CoordinateCubeSpecification(CoordinateCubeSpecification.Axis.Z, 0.0, 10.0);

        assertFalse(spec.isSatisfiedBy(cube), "Z = -1.0 меньше минимального значения диапазона");
    }
}

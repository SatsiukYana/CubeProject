package cube.specifications;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.impl.LengthRangeCubeSpecification;
import com.epam.cube.specification.CubeSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LengthRangeCubeSpecificationTest {

    @Test
    void testLengthWithinRange() {
        Cube cube = new Cube(1L, "TestCube", new Point(0, 0, 0), 6.0);
        CubeSpecification spec = new LengthRangeCubeSpecification(5.0, 10.0);

        assertTrue(spec.isSatisfiedBy(cube));
    }

    @Test
    void testLengthBelowRange() {
        Cube cube = new Cube(2L, "SmallCube", new Point(0, 0, 0), 3.0);
        CubeSpecification spec = new LengthRangeCubeSpecification(4.0, 8.0);

        assertFalse(spec.isSatisfiedBy(cube));
    }

    @Test
    void testLengthAboveRange() {
        Cube cube = new Cube(3L, "LargeCube", new Point(0, 0, 0), 12.0);
        CubeSpecification spec = new LengthRangeCubeSpecification(4.0, 10.0);

        assertFalse(spec.isSatisfiedBy(cube));
    }
}


package cube.specifications;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.impl.NameCubeSpecification;
import com.epam.cube.specification.CubeSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameCubeSpecificationTest {

    @Test
    void testMatchingName() {
        Cube cube = new Cube(1L, "AlphaCube", new Point(1, 2, 3), 4.0);
        CubeSpecification spec = new NameCubeSpecification("AlphaCube");

        assertTrue(spec.isSatisfiedBy(cube));
    }

    @Test
    void testNonMatchingName() {
        Cube cube = new Cube(2L, "BetaCube", new Point(0, 0, 0), 4.0);
        CubeSpecification spec = new NameCubeSpecification("GammaCube");

        assertFalse(spec.isSatisfiedBy(cube));
    }
}

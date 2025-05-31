package cube.specifications;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.specification.impl.IdCubeSpecification;
import com.epam.cube.specification.CubeSpecification;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IdCubeSpecificationTest {

    @Test
    void testMatchingId() {
        Cube cube = new Cube(100L, "TestCube", new Point(0, 0, 0), 5.0);
        CubeSpecification spec = new IdCubeSpecification(100L);

        assertTrue(spec.isSatisfiedBy(cube));
    }

    @Test
    void testNonMatchingId() {
        Cube cube = new Cube(101L, "OtherCube", new Point(0, 0, 0), 5.0);
        CubeSpecification spec = new IdCubeSpecification(999L);

        assertFalse(spec.isSatisfiedBy(cube));
    }
}

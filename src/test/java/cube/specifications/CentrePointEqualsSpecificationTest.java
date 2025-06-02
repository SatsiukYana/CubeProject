package cube.specifications;

import com.epam.cube.specification.impl.CentrePointEqualsSpecification;
import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentrePointEqualsSpecificationTest {

    @Test
    void testSatisfiedWhenCentreMatches() {
        Point centre = new Point(1.0, 2.0, 3.0);
        Cube cube = new Cube(1L, "Cube", centre, 5.0);
        CentrePointEqualsSpecification spec = new CentrePointEqualsSpecification(centre);

        assertTrue(spec.isSatisfiedBy(cube), "Specification should be satisfied if centres are equal");
    }

    @Test
    void testNotSatisfiedWhenCentreDoesNotMatch() {
        Cube cube = new Cube(1L, "Cube", new Point(1.0, 2.0, 3.0), 5.0);
        CentrePointEqualsSpecification spec = new CentrePointEqualsSpecification(new Point(0.0, 0.0, 0.0));

        assertFalse(spec.isSatisfiedBy(cube), "Specification should not be satisfied if centres differ");
    }

    @Test
    void testNotSatisfiedWhenCubeCentreIsNull() {
        Cube cube = new Cube(1L, "Cube", null, 5.0);
        CentrePointEqualsSpecification spec = new CentrePointEqualsSpecification(new Point(0.0, 0.0, 0.0));

        assertFalse(spec.isSatisfiedBy(cube), "Specification should not be satisfied if cube centre is null");
    }
}

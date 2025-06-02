package cube.service;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.service.CubeCalculationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CubeCalculationServiceTest {

    private CubeCalculationService service;

    @BeforeEach
    void setUp() {
        service = new CubeCalculationService();
    }

    @Test
    void testCalculateArea() {
        Cube cube = new Cube(1, "TestCube", new Point(0, 0, 0), 4);
        double expectedArea = 6 * Math.pow(4, 2);
        assertEquals(expectedArea, service.calculateArea(cube), 0.001, "Площадь должна быть 96");
    }

    @Test
    void testCalculatePerimeter() {
        Cube cube = new Cube(1, "TestCube", new Point(0, 0, 0), 4);
        double expectedPerimeter = 12 * 4;
        assertEquals(expectedPerimeter, service.calculatePerimeter(cube), 0.001, "Периметр должен быть 48");
    }

    @Test
    void testCalculateVolume() {
        Cube cube = new Cube(1, "TestCube", new Point(0, 0, 0), 4);
        double expectedVolume = Math.pow(4, 3);
        assertEquals(expectedVolume, service.calculateVolume(cube), 0.001, "Объем должен быть 64");
    }
}

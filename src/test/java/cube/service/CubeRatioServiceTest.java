package cube.service;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.service.CubeRatioService;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CubeRatioServiceTest {

    @Test
    void testGetCubeRatio_CubeCrossingXY() {
        Cube cube = new Cube(1, "TestCube", new Point(0, 0, 1), 4);
        Optional<Double> ratio = CubeRatioService.getCubeRatio(cube);
        assertTrue(ratio.isPresent(), "Должен возвращаться Optional с результатом");

        double expectedRatio = 1.0 / 63.0;
        assertEquals(expectedRatio, ratio.get(), 0.001, "Неверное отношение частей для пересечения XY");
    }

    @Test
    void testGetCubeRatio_CubeCrossingYZ() {
        Cube cube = new Cube(1, "TestCube", new Point(1, 0, 0), 4);
        Optional<Double> ratio = CubeRatioService.getCubeRatio(cube);
        assertTrue(ratio.isPresent(), "Должен возвращаться Optional с результатом");

        double expectedRatio = 1.0 / 63.0;
        assertEquals(expectedRatio, ratio.get(), 0.001, "Неверное отношение частей для пересечения YZ");
    }

    @Test
    void testGetCubeRatio_CubeCrossingZX() {
        Cube cube = new Cube(1, "TestCube", new Point(0, 1, 0), 4);
        Optional<Double> ratio = CubeRatioService.getCubeRatio(cube);
        assertTrue(ratio.isPresent(), "Должен возвращаться Optional с результатом");

        double expectedRatio = 1.0 / 63.0;
        assertEquals(expectedRatio, ratio.get(), 0.001, "Неверное отношение частей для пересечения ZX");
    }

    @Test
    void testGetCubeRatio_NoIntersection() {
        Cube cube = new Cube(1, "TestCube", new Point(10, 10, 10), 4);
        Optional<Double> ratio = CubeRatioService.getCubeRatio(cube);
        assertTrue(ratio.isEmpty(), "Если нет пересечения, должен возвращаться Optional.empty()");
    }

    @Test
    void testGetCubeRatio_OffsetIntersection() {
        Cube cube = new Cube(1, "OffsetCube", new Point(0, 0, 0.5), 2);
        Optional<Double> ratio = CubeRatioService.getCubeRatio(cube);
        assertTrue(ratio.isPresent(), "Cube пересекается по оси Z, должно быть значение");

        double expectedRatio = 0.125 / 7.875;
        assertEquals(expectedRatio, ratio.get(), 0.001, "Неверное отношение объемов");
    }
}

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

        // При смещении на 1 и длине 4, половина длины = 2
        // smallerLength = 2 - 1 = 1, smallerVolume = 1^3 = 1
        // totalVolume = 4^3 = 64, largerVolume = 64 - 1 = 63
        // ratio = 1 / 63
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

        // При смещении на 0.5 и длине 2, половина длины = 1
        // smallerLength = 1 - 0.5 = 0.5, smallerVolume = 0.5^3 = 0.125
        // totalVolume = 2^3 = 8, largerVolume = 8 - 0.125 = 7.875
        // ratio = 0.125 / 7.875
        double expectedRatio = 0.125 / 7.875;
        assertEquals(expectedRatio, ratio.get(), 0.001, "Неверное отношение объемов");
    }
}

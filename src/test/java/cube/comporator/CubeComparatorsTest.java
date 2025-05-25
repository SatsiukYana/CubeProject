package cube.comporator;

import com.epam.cube.comporator.CubeIdComparator;
import com.epam.cube.comporator.CubeLengthComparator;
import com.epam.cube.comporator.CubeNameComparator;
import com.epam.cube.comporator.CubeXCoordinateComparator;
import com.epam.cube.comporator.CubeYCoordinateComparator;
import com.epam.cube.comporator.CubeZCoordinateComparator;
import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CubeComparatorsTest {

    private final Cube cube1 = new Cube(1L, "Alpha", new Point(1.0, 5.0, 3.0), 4.0);
    private final Cube cube2 = new Cube(2L, "Beta", new Point(2.0, 2.0, 6.0), 3.0);
    private final Cube cube3 = new Cube(3L, "Gamma", new Point(0.0, 8.0, 1.0), 5.0);

    @Test
    void testSortById() {
        List<Cube> cubes = List.of(cube3, cube1, cube2);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeIdComparator());

        assertEquals(List.of(cube1, cube2, cube3), sorted);
    }

    @Test
    void testSortByLength() {
        List<Cube> cubes = List.of(cube3, cube1, cube2);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeLengthComparator());

        assertEquals(List.of(cube2, cube1, cube3), sorted);
    }

    @Test
    void testSortByName() {
        List<Cube> cubes = List.of(cube3, cube1, cube2);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeNameComparator());

        assertEquals(List.of(cube1, cube2, cube3), sorted); // Alpha, Beta, Gamma
    }

    @Test
    void testSortByXCoordinate() {
        List<Cube> cubes = List.of(cube2, cube1, cube3);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeXCoordinateComparator());

        assertEquals(List.of(cube3, cube1, cube2), sorted); // 0.0, 1.0, 2.0
    }

    @Test
    void testSortByYCoordinate() {
        List<Cube> cubes = List.of(cube2, cube3, cube1);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeYCoordinateComparator());

        assertEquals(List.of(cube2, cube1, cube3), sorted); // 2.0, 5.0, 8.0
    }

    @Test
    void testSortByZCoordinate() {
        List<Cube> cubes = List.of(cube1, cube3, cube2);
        List<Cube> sorted = new ArrayList<>(cubes);
        sorted.sort(new CubeZCoordinateComparator());

        assertEquals(List.of(cube3, cube1, cube2), sorted); // 1.0, 3.0, 6.0
    }
}


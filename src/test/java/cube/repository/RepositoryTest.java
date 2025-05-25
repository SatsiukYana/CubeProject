package cube.repository;

import com.epam.cube.entity.Cube;
import com.epam.cube.repository.Repository;
import com.epam.cube.entity.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryTest {

    private Repository repository;
    private Cube cube1;
    private Cube cube2;
    private Cube cube3;

    @BeforeEach
    void setUp() {
        cube1 = new Cube(1L, "Alpha", new Point(0.0, 0.0, 0.0), 5.0);
        cube2 = new Cube(2L, "Bravo", new Point(1.0, 1.0, 1.0), 3.0);
        cube3 = new Cube(3L, "Charlie", new Point(2.0, 2.0, 2.0), 7.0);
        repository = new Repository(List.of(cube1, cube2));
    }

    @Test
    void testAddCube() {
        repository.addCube(cube3);
        List<Cube> cubes = repository.getCubes();

        assertEquals(3, cubes.size(), "Repository should contain 3 cubes");
        assertTrue(cubes.contains(cube3), "Repository should contain the added cube");
    }

    @Test
    void testGetCubesReturnsCopy() {
        List<Cube> cubes = repository.getCubes();
        cubes.clear(); // attempt to modify outside

        assertEquals(2, repository.getCubes().size(), "Repository's internal list should not be affected");
    }

    @Test
    void testSortByLength() {
        List<Cube> sorted = repository.sortBy(Comparator.comparingDouble(Cube::getLength));
        assertEquals(List.of(cube2, cube1), sorted, "Cubes should be sorted by length ascending");
    }

    @Test
    void testSortByName() {
        repository.addCube(cube3);
        List<Cube> sorted = repository.sortBy(Comparator.comparing(Cube::getName));
        assertEquals(List.of(cube1, cube2, cube3), sorted, "Cubes should be sorted by name");
    }
}


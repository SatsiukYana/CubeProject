package cube.reader;

import com.epam.cube.entity.Cube;
import com.epam.cube.reader.DataParser;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataParserTest {

    private static final String TEST_FILE = "data/test_cube_data.txt";

    @BeforeAll
    static void setUp() throws IOException {
        Files.createDirectories(Paths.get("data"));
        Files.write(Paths.get(TEST_FILE), List.of(
                "10.0;20.0;30.0;1;TestCube;5",
                "15.5;25.5;35.5;2;SecondCube;8"
        ));
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void parse_validFile_returnsCubes() {
        DataParser parser = new DataParser();
        List<Cube> cubes = parser.parse(TEST_FILE);

        assertNotNull(cubes, "Parsed list should not be null");
        assertEquals(2, cubes.size(), "Should parse 2 cubes");

        Cube first = cubes.get(0);
        assertEquals("TestCube", first.getName());
        assertEquals(10.0, first.getCentre().getX());
        assertEquals(5.0, first.getLength());
    }

    @Test
    void parse_invalidFile_returnsEmptyList() {
        DataParser parser = new DataParser();
        List<Cube> cubes = parser.parse("nonexistent/path.txt");

        assertNotNull(cubes, "List should not be null even if file is invalid");
        assertEquals(0, cubes.size(), "Should return empty list when file can't be read");
    }

    @Test
    void parse_invalidLine_skipsLine() throws IOException {
        Path invalidFile = Paths.get("data/invalid_test_data.txt");
        Files.write(invalidFile, List.of(
                "invalid;data;that;won't;parse",
                "10.0;20.0;30.0;3;ValidCube;6"
        ));

        DataParser parser = new DataParser();
        List<Cube> cubes = parser.parse("data/invalid_test_data.txt");

        assertEquals(1, cubes.size(), "Only 1 valid line should be parsed");
        assertEquals("ValidCube", cubes.get(0).getName());

        Files.deleteIfExists(invalidFile);
    }
}

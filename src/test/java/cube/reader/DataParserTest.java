package cube.reader;

import com.epam.cube.entity.Cube;
import com.epam.cube.exception.InvalidPathException;
import com.epam.cube.parser.DataParser;
import com.epam.cube.reader.DataReader;
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
        Files.deleteIfExists(Paths.get("data/invalid_test_data.txt"));
    }

    @Test
    void parse_validFile_returnsCubes() throws InvalidPathException {
        List<String> lines = DataReader.readData(TEST_FILE);
        DataParser parser = new DataParser();
        List<Cube> cubes = parser.parse(lines);

        assertNotNull(cubes, "Parsed list should not be null");
        assertEquals(2, cubes.size(), "Should parse 2 cubes");

        Cube first = cubes.get(0);
        assertEquals("TestCube", first.getName());
        assertEquals(10.0, first.getCentre().getX());
        assertEquals(5.0, first.getLength());
    }

    @Test
    void parse_invalidFile_throwsException() {
        assertThrows(InvalidPathException.class, () -> {
            DataReader.readData("nonexistent/path.txt");
        });
    }

    @Test
    void parse_invalidLine_skipsLine() throws IOException, InvalidPathException {
        Path invalidFile = Paths.get("data/invalid_test_data.txt");
        Files.write(invalidFile, List.of(
                "invalid;data;that;won't;parse",
                "10.0;20.0;30.0;3;ValidCube;6"
        ));

        List<String> lines = DataReader.readData(invalidFile.toString());
        DataParser parser = new DataParser();
        List<Cube> cubes = parser.parse(lines);

        assertEquals(1, cubes.size(), "Only 1 valid line should be parsed");
        assertEquals("ValidCube", cubes.get(0).getName());
    }
}

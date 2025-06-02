package cube.reader;

import com.epam.cube.exception.InvalidPathException;
import com.epam.cube.reader.DataReader;
import org.junit.jupiter.api.*;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    private static final String TEST_FILE = "data/test_input.txt";

    @BeforeAll
    static void setUp() throws IOException {
        Files.createDirectories(Paths.get("data"));
        Files.write(Paths.get(TEST_FILE), List.of(
                "10;20;30;1;Hello;5",
                "invalid;data",
                "35;45;55;6;Response;7"
        ));
    }

    @AfterAll
    static void tearDown() throws IOException {
        Files.deleteIfExists(Paths.get(TEST_FILE));
    }

    @Test
    void testReadData_ShouldReadLinesFromFile() throws InvalidPathException {
        List<String> lines = DataReader.readData(TEST_FILE);

        assertNotNull(lines, "Результат не должен быть null");
        assertEquals(3, lines.size(), "Ожидалось 3 строки в тестовом файле");

        assertEquals("10;20;30;1;Hello;5", lines.get(0));
        assertEquals("invalid;data", lines.get(1));
        assertEquals("35;45;55;6;Response;7", lines.get(2));
    }

    @Test
    void testReadData_ShouldThrowExceptionIfFileNotExists() {
        String invalidPath = "nonexistent/file.txt";

        InvalidPathException thrown = assertThrows(
                InvalidPathException.class,
                () -> DataReader.readData(invalidPath),
                "Ожидалось исключение InvalidPathException"
        );

        assertTrue(thrown.getMessage().contains("Failed to read from path"));
    }
}

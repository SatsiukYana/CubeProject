package cube.reader;

import com.epam.cube.exception.InvalidPathException;
import com.epam.cube.reader.DataReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataReaderTest {

    String[] testData = {
            "10;20;30;1;Hello;5",
            "15;25;35;2;World;8",
            "20;30;40;3;Java;12",
            "25;35;45;4;Spring;6",
            "30;40;50;5;Filter;10",
            "35;45;55;6;Response;7"
    };

    @Test
    void testReadData_Success() {
        try {
            List<String> result = DataReader.readData("data/cube_data.txt");

            assertEquals(6, result.size(), "Ожидалось 6 строк данных");

            for (int i = 0; i < testData.length; i++) {
                assertEquals(testData[i], result.get(i), "Неверная строка на позиции " + i);
            }

        } catch (InvalidPathException e) {
            fail("Исключение не должно было быть выброшено при корректном пути: " + e.getMessage());
        }
    }
}
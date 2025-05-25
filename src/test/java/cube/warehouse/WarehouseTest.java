package cube.warehouse;

import com.epam.cube.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseTest {

    private Warehouse warehouse;
    private final long cubeId = 1L;
    private final double area = 150.0;
    private final double perimeter = 60.0;
    private final double volume = 1000.0;

    @BeforeEach
    void setUp() {
        warehouse = Warehouse.getInstance();
        // Очистим данные перед каждым тестом, чтобы избежать влияния singleton-состояния
        clearWarehouse(warehouse);
    }

    @Test
    void testSingletonInstance() {
        Warehouse anotherInstance = Warehouse.getInstance();
        assertSame(warehouse, anotherInstance, "Warehouse should be a singleton");
    }

    @Test
    void testPutAndGetArea() {
        warehouse.putArea(cubeId, area);
        assertEquals(area, warehouse.getArea(cubeId), "Area value should match what was put");
    }

    @Test
    void testPutAndGetPerimeter() {
        warehouse.putPerimeter(cubeId, perimeter);
        assertEquals(perimeter, warehouse.getPerimeter(cubeId), "Perimeter value should match what was put");
    }

    @Test
    void testPutAndGetVolume() {
        warehouse.putVolume(cubeId, volume);
        assertEquals(volume, warehouse.getVolume(cubeId), "Volume value should match what was put");
    }

    @Test
    void testGetNonExistentValues() {
        assertNull(warehouse.getArea(999L), "Area for unknown ID should be null");
        assertNull(warehouse.getPerimeter(999L), "Perimeter for unknown ID should be null");
        assertNull(warehouse.getVolume(999L), "Volume for unknown ID should be null");
    }

    // Метод для очистки полей singleton-а (можно вынести в TestUtils, если понадобится чаще)
    private void clearWarehouse(Warehouse warehouse) {
        try {
            var areaField = Warehouse.class.getDeclaredField("shapeAreaValues");
            var perimeterField = Warehouse.class.getDeclaredField("shapePerimetrValues");
            var volumeField = Warehouse.class.getDeclaredField("shapeVolumeValues");

            areaField.setAccessible(true);
            perimeterField.setAccessible(true);
            volumeField.setAccessible(true);

            ((java.util.Map<?, ?>) areaField.get(warehouse)).clear();
            ((java.util.Map<?, ?>) perimeterField.get(warehouse)).clear();
            ((java.util.Map<?, ?>) volumeField.get(warehouse)).clear();

        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Failed to clear Warehouse state for testing", e);
        }
    }
}


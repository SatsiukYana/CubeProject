package cube.observer;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.observer.WarehouseObserver;
import com.epam.cube.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseObserverTest {

    private WarehouseObserver observer;
    private Warehouse warehouse;

    private final long cubeId = 42L;
    private final double length = 4.0;

    @BeforeEach
    void setUp() {
        observer = new WarehouseObserver();
        warehouse = Warehouse.getInstance();
        clearWarehouse();
    }

    @Test
    void testUpdate_addsCorrectAreaPerimeterVolume() {
        Cube cube = new Cube(cubeId, "TestCube", new Point(0.0, 0.0, 0.0), length);

        observer.update(cube);

        double expectedArea = 6 * Math.pow(length, 2);
        double expectedPerimeter = 12 * length;
        double expectedVolume = Math.pow(length, 3);

        assertEquals(expectedArea, warehouse.getArea(cubeId), 0.001, "Area should match expected");
        assertEquals(expectedPerimeter, warehouse.getPerimeter(cubeId), 0.001, "Perimeter should match expected");
        assertEquals(expectedVolume, warehouse.getVolume(cubeId), 0.001, "Volume should match expected");
    }

    @Test
    void testUpdate_overwritesOldValues() {
        // First update
        Cube initial = new Cube(cubeId, "InitialCube", new Point(0, 0, 0), 3.0);
        observer.update(initial);

        // Second update with different length
        Cube updated = new Cube(cubeId, "UpdatedCube", new Point(1, 1, 1), 5.0);
        observer.update(updated);

        assertEquals(6 * 25.0, warehouse.getArea(cubeId), 0.001);
        assertEquals(12 * 5.0, warehouse.getPerimeter(cubeId), 0.001);
        assertEquals(125.0, warehouse.getVolume(cubeId), 0.001);
    }

    // Удаляет все значения из Warehouse перед каждым тестом
    private void clearWarehouse() {
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
            throw new RuntimeException("Failed to clear warehouse for test", e);
        }
    }
}

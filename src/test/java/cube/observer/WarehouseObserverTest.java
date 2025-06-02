package cube.observer;

import com.epam.cube.entity.Cube;
import com.epam.cube.entity.Point;
import com.epam.cube.observer.WarehouseObserver;
import com.epam.cube.service.CubeCalculationService;
import com.epam.cube.warehouse.Warehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WarehouseObserverTest {

    private WarehouseObserver observer;
    private Warehouse warehouse;
    private CubeCalculationService service;

    private final long cubeId = 42L;
    private final double length = 4.0;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        service = new CubeCalculationService();
        observer = new WarehouseObserver(service);
        warehouse = Warehouse.getInstance();

        // Очистка warehouse
        var areaField = Warehouse.class.getDeclaredField("shapeAreaValues");
        var perimeterField = Warehouse.class.getDeclaredField("shapePerimetrValues");
        var volumeField = Warehouse.class.getDeclaredField("shapeVolumeValues");

        areaField.setAccessible(true);
        perimeterField.setAccessible(true);
        volumeField.setAccessible(true);

        ((java.util.Map<?, ?>) areaField.get(warehouse)).clear();
        ((java.util.Map<?, ?>) perimeterField.get(warehouse)).clear();
        ((java.util.Map<?, ?>) volumeField.get(warehouse)).clear();
    }

    @Test
    void testUpdate_addsCorrectAreaPerimeterVolume() {
        Cube cube = new Cube(cubeId, "TestCube", new Point(0.0, 0.0, 0.0), length);

        observer.update(cube);

        double expectedArea = 6 * Math.pow(length, 2);
        double expectedPerimeter = 12 * length;
        double expectedVolume = Math.pow(length, 3);

        assertEquals(expectedArea, warehouse.getArea(cubeId), 0.001);
        assertEquals(expectedPerimeter, warehouse.getPerimeter(cubeId), 0.001);
        assertEquals(expectedVolume, warehouse.getVolume(cubeId), 0.001);
    }

    @Test
    void testUpdate_overwritesOldValues() {
        Cube initial = new Cube(cubeId, "InitialCube", new Point(0, 0, 0), 3.0);
        observer.update(initial);

        Cube updated = new Cube(cubeId, "UpdatedCube", new Point(1, 1, 1), 5.0);
        observer.update(updated);

        double expectedArea = 6 * 25.0;
        double expectedPerimeter = 12 * 5.0;
        double expectedVolume = 125.0;

        assertEquals(expectedArea, warehouse.getArea(cubeId), 0.001);
        assertEquals(expectedPerimeter, warehouse.getPerimeter(cubeId), 0.001);
        assertEquals(expectedVolume, warehouse.getVolume(cubeId), 0.001);
    }
}

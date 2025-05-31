package com.epam.cube.observer;

import com.epam.cube.entity.Cube;
import com.epam.cube.service.CubeCalculationService;
import com.epam.cube.warehouse.Observe;
import com.epam.cube.warehouse.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarehouseObserver implements Observe {
    private final Logger log = LoggerFactory.getLogger(WarehouseObserver.class);

    @Override
    public void update(Cube cube) {
        log.info("Updating " + cube.getName());

        Warehouse warehouse = Warehouse.getInstance(); // ← перемещено сюда

        long id = cube.getId();
        double area = CubeCalculationService.calculateArea(cube);
        double perimeter = CubeCalculationService.calculatePerimeter(cube);
        double volume = CubeCalculationService.calculateVolume(cube);

        warehouse.putArea(id, area);
        warehouse.putPerimeter(id, perimeter);
        warehouse.putVolume(id, volume);
    }
}

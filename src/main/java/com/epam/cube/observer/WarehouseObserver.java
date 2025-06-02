package com.epam.cube.observer;

import com.epam.cube.entity.Cube;
import com.epam.cube.service.CubeCalculationService;
import com.epam.cube.warehouse.Observe;
import com.epam.cube.warehouse.Warehouse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WarehouseObserver implements Observe {
    private static final Logger log = LoggerFactory.getLogger(WarehouseObserver.class);

    private final CubeCalculationService calculationService;

    public WarehouseObserver(CubeCalculationService calculationService) {
        this.calculationService = calculationService;
    }

    @Override
    public void update(Cube cube) {
        log.info("Updating {}", cube.getName());
        Warehouse warehouse = Warehouse.getInstance();

        long id = cube.getId();
        double area = calculationService.calculateArea(cube);
        double perimeter = calculationService.calculatePerimeter(cube);
        double volume = calculationService.calculateVolume(cube);

        warehouse.putArea(id, area);
        warehouse.putPerimeter(id, perimeter);
        warehouse.putVolume(id, volume);
    }
}

package com.epam.cube.warehouse;

import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    private static Warehouse instance;
    private final Map<Long, Double> shapeAreaValues = new HashMap<>();
    private final Map<Long, Double> shapePerimetrValues = new HashMap<>();
    private final Map<Long, Double> shapeVolumeValues = new HashMap<>();

    private Warehouse() {}

    public static Warehouse getInstance() {
        if (instance == null) {
            instance = new Warehouse();
        }
        return instance;
    }

    // Методы для обновления значений
    public void putArea(long id, double area) {
        shapeAreaValues.put(id, area);
    }

    public void putPerimeter(long id, double perimeter) {
        shapePerimetrValues.put(id, perimeter);
    }

    public void putVolume(long id, double volume) {
        shapeVolumeValues.put(id, volume);
    }

    public Double getArea(long shapeId) {
        return shapeAreaValues.get(shapeId);
    }

    public Double getPerimeter(long shapeId) {
        return shapePerimetrValues.get(shapeId);
    }

    public Double getVolume(long shapeId) {
        return shapeVolumeValues.get(shapeId);
    }
}

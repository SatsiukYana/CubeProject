package com.epam.cube.entity;

import com.epam.cube.observer.Observable;
import com.epam.cube.observer.WarehouseObserver;
import com.epam.cube.warehouse.Observe;
import com.epam.cube.warehouse.Warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cube implements Observable {

    private long id;
    private String name;
    private Point centrePoint;
    private double length;
    private final List<Observe> observers = new ArrayList<>();

    public Cube(long id, String name, Point centrePoint, double length) {
        this.id = id;
        this.name = name;
        this.centrePoint = centrePoint;
        this.length = length;
        addObserver(new WarehouseObserver());
        notifyObservers();
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Point getCentre() {
        return centrePoint;
    }

    public double getLength() {
        return length;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCentrePoint(Point centrePoint) {
        this.centrePoint = centrePoint; // Сначала меняем состояние
        notifyObservers(); // Затем уведомляем наблюдателей
    }

    public void setLength(double length) { // Изменил параметр на double
        this.length = length; // Сначала меняем состояние
        notifyObservers(); // Затем уведомляем наблюдателей
    }

    @Override
    public void addObserver(Observe observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observe observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observe observer : observers) {
            observer.update(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cube cube = (Cube) o;

        if (length != cube.length) return false;
        if (!Objects.equals(name, cube.name)) return false;
        return Objects.equals(centrePoint, cube.centrePoint);
    }

    @Override
    public int hashCode() {
        int result = (name != null) ? name.hashCode() : 0;
        result = 31 * result + (centrePoint != null ? centrePoint.hashCode() : 0);
        result = 31 * result + (int) length;
        return result;
    }

    @Override
    public String toString() {
        return "Cube{" +
                "name=" + name +
                "centrePoint" + centrePoint +
                "length" + length +
                "}";
    }
}

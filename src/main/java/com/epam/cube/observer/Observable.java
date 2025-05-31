package com.epam.cube.observer;
import com.epam.cube.warehouse.Observe;

public interface Observable {
    void addObserver(Observe observer);
    void removeObserver(Observe observer);
    void notifyObservers();
}
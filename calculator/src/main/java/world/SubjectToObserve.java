package main.java.world;

import main.java.views.Observer;

import java.util.ArrayList;

public abstract class SubjectToObserve {
    private ArrayList<Observer> observers;

    public SubjectToObserve() {
        observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        ArrayList<Observer> obeserverClone = new ArrayList<>(this.observers);
        for(Observer observer : obeserverClone){
            observer.react();
        }
    }
}

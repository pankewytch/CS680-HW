package edu.umb.cs680.hw13;

import java.util.ArrayList;

public class DJIAQuoteObservable implements Observable {
    private boolean isChanged;
    private float quote;
    private ArrayList<Observer> observersArrayList;

    public DJIAQuoteObservable(float quote) {
        isChanged = false;
        this.quote = quote;
        observersArrayList = new ArrayList<>();
    }

    public void changeQuote(float quote) {
        this.quote = quote;
        setChanged();
        notifyObservers(new DJIAEvent(this.quote));
    }

    public void notifyObservers(DJIAEvent event) {
        if(hasChanged()) {
            for (Observer observer : observersArrayList) {
                observer.update(this, event);
            }
        }
        clearChanged();
    }

    public void addObserver(Observer observer) {
        observersArrayList.add(observer);
    }

    @Override
    public void setChanged() {
        isChanged = true;
    }

    @Override
    public boolean hasChanged() {
        return isChanged;
    }

    @Override
    public void clearChanged() {
        isChanged = false;
    }
}

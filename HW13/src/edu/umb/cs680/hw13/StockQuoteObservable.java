package edu.umb.cs680.hw13;

import java.util.ArrayList;
import java.util.HashMap;

public class StockQuoteObservable implements Observable {
    private HashMap<String, Float> tickers;
    private ArrayList<Observer> observerArrayList;
    private boolean isChanged;

    public StockQuoteObservable(HashMap<String, Float> tickers) {
        this.tickers = tickers;
        this.observerArrayList = new ArrayList<>();
        isChanged = false;
    }

    public void addObserver(Observer observer) {
        observerArrayList.add(observer);
    }

    public void changeQuote(String ticker, float quote) {
        StockEvent event = new StockEvent(ticker, quote);
        tickers.replace(ticker, quote);
        setChanged();
        notifyObservers(event);
    }

    private void notifyObservers(StockEvent event) {
        if(hasChanged()) {
            for (Observer observer : observerArrayList) {
                observer.update(this, event);
            }
        }
        clearChanged();
    }

    public void setChanged() {
        isChanged = true;
    }

    public void clearChanged() {
        isChanged = false;
    }

    public boolean hasChanged() {
        return isChanged;
    }
}

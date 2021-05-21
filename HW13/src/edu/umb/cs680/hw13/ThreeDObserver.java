package edu.umb.cs680.hw13;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ThreeDObserver implements Observer {
    private HashMap<String, ArrayList<LocalDateTime>> stockChanges;
    private ArrayList<LocalDateTime> djiaChanges;

    public ThreeDObserver() {
        stockChanges = new HashMap<>();
        djiaChanges = new ArrayList<>();
    }

    public HashMap<String, ArrayList<LocalDateTime>> getStockChanges() {
        return stockChanges;
    }

    public ArrayList<LocalDateTime> getDjiaChanges() {
        return djiaChanges;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(arg instanceof StockEvent) {
            if(stockChanges.containsKey(((StockEvent) arg).getTicker())) {
                stockChanges.get(((StockEvent) arg).getTicker()).add(LocalDateTime.now());
            } else {
                ArrayList<LocalDateTime> tickerChangeTimes = new ArrayList<>();
                tickerChangeTimes.add(LocalDateTime.now());
                stockChanges.put(((StockEvent) arg).getTicker(), tickerChangeTimes);
            }
            renderArg((StockEvent) arg);
        }
        else if (arg instanceof DJIAEvent){
            djiaChanges.add(LocalDateTime.now());
            renderArg((DJIAEvent) arg);
        }
    }

    public void renderArg(StockEvent event) {
        System.out.println("Rendering new stock event: "+event.getTicker()+" "+event.getQuote());
    }

    public void renderArg(DJIAEvent event) {
        System.out.println("Rendering new djia event: "+event.getDjia());
    }
}

package edu.umb.cs680.hw13;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class TableObserver implements Observer {
    private HashMap<String, ArrayList<LocalDateTime>> stockChanges;
    private ArrayList<LocalDateTime> djiaChanges;

    public TableObserver() {
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
            tableArg((StockEvent) arg);
        }
        else if (arg instanceof DJIAEvent){
            djiaChanges.add(LocalDateTime.now());
            tableArg((DJIAEvent) arg);
        }
    }

    public void tableArg(StockEvent event) {
        System.out.println("Entering new stock event into table: "+event.getTicker()+" "+event.getQuote());
    }

    public void tableArg(DJIAEvent event) {
        System.out.println("Entering new djia event into table: "+event.getDjia());
    }
}

package edu.umb.cs680.hw13;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class PieChartObserver implements Observer {
    private HashMap<String, ArrayList<LocalDateTime>> stockChanges;
    private ArrayList<LocalDateTime> djiaChanges;

    public PieChartObserver() {
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
            drawArg((StockEvent) arg);
        }
        else if (arg instanceof DJIAEvent){
            djiaChanges.add(LocalDateTime.now());
            drawArg((DJIAEvent) arg);
        }
    }

    public void drawArg(StockEvent event) {
        System.out.println("Drawing new stock event: "+event.getTicker()+" "+event.getQuote());
    }

    public void drawArg(DJIAEvent event) {
        System.out.println("Drawing new djia event: "+event.getDjia());
    }
}

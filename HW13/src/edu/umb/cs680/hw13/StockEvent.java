package edu.umb.cs680.hw13;

public class StockEvent {
    private String ticker;
    private float quote;

    public StockEvent(String ticker, float quote){
        this.ticker = ticker;
        this.quote = quote;
    }

    public String getTicker() {
        return ticker;
    }

    public float getQuote() {
        return quote;
    }
}

package edu.umb.cs680.hw13;

import static org.junit.jupiter.api.Assertions.*;
import edu.umb.cs680.hw13.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.*;

public class ThreeDObserverTest {
    private StockQuoteObservable stockQuoteObservable;
    private DJIAQuoteObservable djiaQuoteObservable;

    @BeforeEach
    private void createObservables() {
        HashMap<String, Float> tickers = new HashMap<>();
        tickers.put("test1", 0.347f);
        tickers.put("test2", 243.281f);
        tickers.put("test3", 26.2f);
        tickers.put("test4", 0.0034f);
        tickers.put("test5", 1000f);
        stockQuoteObservable = new StockQuoteObservable(tickers);
        djiaQuoteObservable = new DJIAQuoteObservable(5000f);
    }

    @Test
    public void CreatingInstanceOfThreeDObserverAndVerifyingWithInstanceOf() {
        ThreeDObserver cut = new ThreeDObserver();
        assertTrue(cut instanceof ThreeDObserver);
    }

    @Test
    public void VerifyingChangesMadeToStockTickerMapInThreeDObserver() {
        ThreeDObserver cut = new ThreeDObserver();
        stockQuoteObservable.addObserver(cut);
        stockQuoteObservable.changeQuote("test1", 20f);
        stockQuoteObservable.changeQuote("test2", 30f);
        stockQuoteObservable.changeQuote("test3", 40f);
        assertEquals(3, cut.getStockChanges().size());
        assertTrue(cut.getStockChanges().containsKey("test1"));
        assertTrue(cut.getStockChanges().containsKey("test2"));
        assertTrue(cut.getStockChanges().containsKey("test3"));
        assertTrue(cut.getDjiaChanges().isEmpty());
    }

    @Test
    public void VerifyingChangesMadeToDJIAListInThreeDObserver() {
        ThreeDObserver cut = new ThreeDObserver();
        djiaQuoteObservable.addObserver(cut);
        djiaQuoteObservable.changeQuote(2f);
        djiaQuoteObservable.changeQuote(3f);
        djiaQuoteObservable.changeQuote(4f);
        assertEquals(3, cut.getDjiaChanges().size());
        assertTrue(cut.getDjiaChanges().get(0).isBefore(cut.getDjiaChanges().get(1)) &&  cut.getDjiaChanges().get(1).isBefore(cut.getDjiaChanges().get(2)));
        assertTrue(cut.getStockChanges().isEmpty());
    }

    @Test
    public void VerifyThreeDObserverWorksIfInMultipleObservables() {
        ThreeDObserver cut = new ThreeDObserver();
        stockQuoteObservable.addObserver(cut);
        djiaQuoteObservable.addObserver(cut);
        stockQuoteObservable.changeQuote("test1", 20f);
        djiaQuoteObservable.changeQuote(2f);
        stockQuoteObservable.changeQuote("test2", 30f);
        stockQuoteObservable.changeQuote("test3", 40f);
        djiaQuoteObservable.changeQuote(3f);
        djiaQuoteObservable.changeQuote(4f);
        assertEquals(3, cut.getStockChanges().size());
        assertTrue(cut.getStockChanges().containsKey("test1"));
        assertTrue(cut.getStockChanges().containsKey("test2"));
        assertTrue(cut.getStockChanges().containsKey("test3"));
        assertEquals(3, cut.getDjiaChanges().size());
        assertTrue(cut.getDjiaChanges().get(0).isBefore(cut.getDjiaChanges().get(1)) &&  cut.getDjiaChanges().get(1).isBefore(cut.getDjiaChanges().get(2)));
    }

}

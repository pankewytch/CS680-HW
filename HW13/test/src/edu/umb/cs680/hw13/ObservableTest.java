package edu.umb.cs680.hw13;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.umb.cs680.hw13.*;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

public class ObservableTest {
    private TableObserver tableObserver1;
    private TableObserver tableObserver2;
    private ThreeDObserver threeDObserver1;
    private ThreeDObserver threeDObserver2;
    private PieChartObserver pieChartObserver1;
    private PieChartObserver pieChartObserver2;

    @BeforeEach
    private void createTestEnvironment() {
        tableObserver1 = new TableObserver();
        tableObserver2 = new TableObserver();
        threeDObserver1 = new ThreeDObserver();
        threeDObserver2 = new ThreeDObserver();
        pieChartObserver1 = new PieChartObserver();
        pieChartObserver2 = new PieChartObserver();
    }

    @Test
    public void VerifyHasChangedMethodWorksAsExpectedInAllObservables() {
        HashMap<String, Float> tickers = new HashMap<>();
        tickers.put("test1", 0.347f);
        tickers.put("test2", 243.281f);
        tickers.put("test3", 26.2f);
        tickers.put("test4", 0.0034f);
        tickers.put("test5", 1000f);
        StockQuoteObservable stockQuoteObservable = new StockQuoteObservable(tickers);
        DJIAQuoteObservable djiaQuoteObservable = new DJIAQuoteObservable(5000f);
        stockQuoteObservable.addObserver(tableObserver1);
        djiaQuoteObservable.addObserver(tableObserver1);
        stockQuoteObservable.changeQuote("test5", 6f);
        djiaQuoteObservable.changeQuote(55f);
        assertFalse(stockQuoteObservable.hasChanged());
        assertFalse(djiaQuoteObservable.hasChanged());
    }

    @Test
    public void VerifyStockQuoteObservableMakesChangesToAllObservers() {
        HashMap<String, Float> tickers = new HashMap<>();
        tickers.put("test1", 0.347f);
        tickers.put("test2", 243.281f);
        tickers.put("test3", 26.2f);
        tickers.put("test4", 0.0034f);
        tickers.put("test5", 1000f);
        StockQuoteObservable cut = new StockQuoteObservable(tickers);
        cut.addObserver(tableObserver1);
        cut.addObserver(tableObserver2);
        cut.addObserver(threeDObserver1);
        cut.addObserver(threeDObserver2);
        cut.addObserver(pieChartObserver1);
        cut.addObserver(pieChartObserver2);
        cut.changeQuote("test1", 1f);
        cut.changeQuote("test2", 1f);
        cut.changeQuote("test3", 1f);
        cut.changeQuote("test4", 1f);
        cut.changeQuote("test5", 1f);
        assertEquals(5, tableObserver1.getStockChanges().size());
        assertEquals(5, tableObserver2.getStockChanges().size());
        assertEquals(5, threeDObserver1.getStockChanges().size());
        assertEquals(5, threeDObserver2.getStockChanges().size());
        assertEquals(5, pieChartObserver1.getStockChanges().size());
        assertEquals(5, pieChartObserver2.getStockChanges().size());
    }

    @Test
    public void VerifyDJIAQuoteObservableMakesChangesToAllObservers() {
        DJIAQuoteObservable cut = new DJIAQuoteObservable(1f);
        cut.addObserver(tableObserver1);
        cut.addObserver(tableObserver2);
        cut.addObserver(threeDObserver1);
        cut.addObserver(threeDObserver2);
        cut.addObserver(pieChartObserver1);
        cut.addObserver(pieChartObserver2);
        cut.changeQuote(1f);
        cut.changeQuote(1f);
        cut.changeQuote(1f);
        cut.changeQuote(1f);
        cut.changeQuote(1f);
        assertEquals(5, tableObserver1.getDjiaChanges().size());
        assertEquals(5, tableObserver2.getDjiaChanges().size());
        assertEquals(5, threeDObserver1.getDjiaChanges().size());
        assertEquals(5, threeDObserver2.getDjiaChanges().size());
        assertEquals(5, pieChartObserver1.getDjiaChanges().size());
        assertEquals(5, pieChartObserver2.getDjiaChanges().size());
    }
}

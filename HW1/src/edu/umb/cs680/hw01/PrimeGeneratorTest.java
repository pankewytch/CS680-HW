package edu.umb.cs680.hw01;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class PrimeGeneratorTest {
    @Test
    public void createPrimeGeneratorInstance_Successfully(){
        PrimeGenerator cut = new PrimeGenerator(1l, 20l);
        assertTrue(cut instanceof PrimeGenerator);
    }

    @Test
    public void createPrimeGeneratorInstance_Fail(){
        assertThrows(RuntimeException.class, ()->{
            PrimeGenerator cut = new PrimeGenerator(30l, 1l);
        });
    }

    @Test
    public void isEven_ReturnsTrue(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        assertTrue(cut.isEven(24l));

    }

    @Test
    public void isEven_ReturnsFalse(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        assertFalse(cut.isEven(25l));
    }

    @Test
    public void isPrime_ReturnsTrue(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        assertTrue(cut.isPrime(59l));
    }

    @Test
    public void isPrime_ReturnsFalse(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        assertFalse(cut.isPrime(51l));
    }

    @Test
    public void getPrimesReturnsLinkedListClass(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        cut.generatePrimes();
        LinkedList<Long> prime_list = cut.getPrimes();
        cut.isEven(2l);
        assertTrue(prime_list.getClass()==java.util.LinkedList.class);
    }

    @Test
    public void generatePrimeSequence_1to30(){
        PrimeGenerator cut = new PrimeGenerator(1l, 30l);
        cut.generatePrimes();
        LinkedList<Long> primes_generated = cut.getPrimes();
        LinkedList<Long> primes_correctSet = new LinkedList<>();
        primes_correctSet.add(2l);
        primes_correctSet.add(3l);
        primes_correctSet.add(5l);
        primes_correctSet.add(7l);
        primes_correctSet.add(11l);
        primes_correctSet.add(13l);
        primes_correctSet.add(17l);
        primes_correctSet.add(19l);
        primes_correctSet.add(23l);
        primes_correctSet.add(29l);
        assertEquals(primes_correctSet, primes_generated);
    }
}

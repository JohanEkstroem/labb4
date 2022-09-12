package edu.lernia.labb4;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.Month;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TollFeeCalculatorTest {
    
    @Test
    @DisplayName("Testing a single pass and expect 18 return")
    void testGetTollFeePerPassing() {
    LocalDateTime date = LocalDateTime.of(2020, Month.JUNE, 30, 15, 30);
        assertEquals(18, TollFeeCalculator.getTollFeePerPassing(date));

    }

    @Test
    @DisplayName("Testing a single pass during free hours, expect return 0")
    void testFreeHours(){
        LocalDateTime date = LocalDateTime.of(2020, Month.JUNE, 30, 00, 00);
        assertEquals(0, TollFeeCalculator.getTollFeePerPassing(date));
    }


    @Test
    @DisplayName("Testing three passages with different hours")
    void testGetTotalFeeCost() {
        LocalDateTime[] date = new LocalDateTime[3];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 10, 00);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 12, 00);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 14, 00);
        
        assertEquals(24, TollFeeCalculator.getTotalFeeCost(date));
    }

    @Test
    @DisplayName("Testing that several passes within 60 minutes returns the highest price for one pass, expect return 8")
    void testSeveralPassesWithinSameHour() {
        LocalDateTime[] date = new LocalDateTime[3];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 10, 05);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 10, 30);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 11, 00);
        
        assertEquals(8, TollFeeCalculator.getTotalFeeCost(date));
    }


    @Test
    @DisplayName("Testing six passes with two different prices within same hour, expect 18")
    void testSeveralPassesOverlappingDifferentPrices() {
        LocalDateTime[] date = new LocalDateTime[6];
        date[0] = LocalDateTime.of(2020, Month.JUNE, 30, 15, 25);
        date[1] = LocalDateTime.of(2020, Month.JUNE, 30, 15, 30);
        date[2] = LocalDateTime.of(2020, Month.JUNE, 30, 15, 50);
        date[3] = LocalDateTime.of(2020, Month.JUNE, 30, 16, 15);
        date[4] = LocalDateTime.of(2020, Month.JUNE, 30, 16, 16);
        date[5] = LocalDateTime.of(2020, Month.JUNE, 30, 16, 18);
        
        assertEquals(18, TollFeeCalculator.getTotalFeeCost(date));
    }

    
    @Test
    @DisplayName("Returns boolean if date is free of charge")
    void testIsTollFreeDate() {
        LocalDateTime date = LocalDateTime.of(2020, Month.JULY, 01, 10, 00);

        assertEquals(true, TollFeeCalculator.isTollFreeDate(date));
    }
}

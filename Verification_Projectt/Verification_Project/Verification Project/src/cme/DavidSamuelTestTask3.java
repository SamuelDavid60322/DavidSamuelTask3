package cme;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class DavidSamuelTestTask3 {

    @Test
    public void testCarParkKind() {
        // create the input data
        CarParkKind kind = CarParkKind.STAFF;
        // check if Car Park Kind value is valid
        assertTrue(kind == CarParkKind.STAFF || kind == CarParkKind.STUDENT || kind == CarParkKind.MANAGEMENT || kind == CarParkKind.VISITOR);
    }

    @Test
    public void testConstructorWithValidInputs() {
        BigDecimal hourlyNormalRate = BigDecimal.valueOf(10);
        BigDecimal hourlyReducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 2));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(2, 24));

        Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);

    }

    @Test
    public void testConstructorWithValidInput() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(7, 9));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(1, 6));

        Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
    }

    @Test
    public void testConstructorWithOverlappingPeriods() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(12, 16));
        reduced.add(new Period(14, 18));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(12, 16));
        normal.add(new Period(14, 18));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithNullParameters() {
        BigDecimal hourlyNormalRate = null;
        BigDecimal hourlyReducedRate = BigDecimal.valueOf(10);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> reduced = null;
        ArrayList<Period> normal = new ArrayList<>();

        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithEmptyNormalPeriods() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(5, 7));
        ArrayList<Period> normal = null;

        assertThrows(IllegalArgumentException.class, () -> {
            new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }


    @Test
    public void testConstructorWithNegativeNumberInNormalRate() {
        BigDecimal hourlyNormalRate = new BigDecimal("-7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> periods = new ArrayList<>();
        periods.add(new Period(1, 4));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, periods, periods);
        });
    }

    @Test
    public void testRateConstructorWithEmptyReducedPeriods() {
        BigDecimal hourlyNormalRate = BigDecimal.valueOf(7);
        BigDecimal hourlyReducedRate = BigDecimal.valueOf(1);
        CarParkKind kind = CarParkKind.STAFF;
        ArrayList<Period> reduced = null;
        ArrayList<Period> normal = new ArrayList<Period>();
        normal.add(new Period(12, 16));
        normal.add(new Period(14, 18));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithSameNormalAndReducedPeriods() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> periods = new ArrayList<>();
        periods.add(new Period(1, 4));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, periods, periods);
        });
    }

    @Test
    public void testCalculate() {
        BigDecimal hourlyNormalRate = new BigDecimal("10.0");
        BigDecimal hourlyReducedRate = new BigDecimal("5.0");
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 3));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(3, 6));
        normal.add(new Period(7, 10));

        Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);

        BigDecimal expectedRate = new BigDecimal("26.00");
        Period periodStay = new Period(1, 9);
        BigDecimal actualRate = rate.calculate(periodStay);
        assertEquals(expectedRate, actualRate);
    }

    @Test
    public void testConstructorWithNormalRatesLessThenReduced() {
        BigDecimal hourlyNormalRate = new BigDecimal("5.0");
        BigDecimal hourlyReducedRate = new BigDecimal("10.0");
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 3));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(3, 6));
        normal.add(new Period(7, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithNullRates() {
        BigDecimal hourlyNormalRate = null;
        BigDecimal hourlyReducedRate = null;
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 3));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(3, 6));
        normal.add(new Period(7, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithNegativeNumberInReducedRate() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("-1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> periods = new ArrayList<>();
        periods.add(new Period(1, 4));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, periods, periods);
        });
    }

    @Test
    public void testConstructorWithNegativeRates() {
        BigDecimal hourlyNormalRate = new BigDecimal("-5.0");
        BigDecimal hourlyReducedRate = new BigDecimal("-10.0");
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 3));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(3, 6));
        normal.add(new Period(7, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }


    @Test
    public void testConstructorWithInvalidPeriods() {
        BigDecimal hourlyNormalRate = BigDecimal.valueOf(10);
        BigDecimal hourlyReducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(3, 6));
        reduced.add(new Period(7, 10));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(5, 8));
        normal.add(new Period(7, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    @Test
    public void testConstructorWithNullReducedRate() {
        BigDecimal hourlyNormalRate = BigDecimal.valueOf(10);
        BigDecimal hourlyReducedRate = null;
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(0, 4));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(4, 24));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        }, "The rates cannot be null");
    }


    @Test
    public void testConstructorWithEmptyReducedPeriods() {
        BigDecimal hourlyNormalRate = new BigDecimal("7.0");
        BigDecimal hourlyReducedRate = new BigDecimal("1.0");
        CarParkKind kind = CarParkKind.STUDENT;
        ArrayList<Period> normal = new ArrayList<>();
        ArrayList<Period> reduced = null;
        normal.add(new Period(4, 24));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        }, "The periods are not valid individually");
    }

    @Test
    public void testConstructorWithInvalidPeriodsMidway() {
        BigDecimal hourlyNormalRate = BigDecimal.valueOf(10);
        BigDecimal hourlyReducedRate = BigDecimal.valueOf(5);
        CarParkKind kind = CarParkKind.VISITOR;
        ArrayList<Period> reduced = new ArrayList<>();
        reduced.add(new Period(1, 4));
        ArrayList<Period> normal = new ArrayList<>();
        normal.add(new Period(4, 6));
        normal.add(new Period(5, 8));
        normal.add(new Period(8, 10));

        assertThrows(IllegalArgumentException.class, () -> {
            Rate rate = new Rate(hourlyNormalRate, hourlyReducedRate, kind, reduced, normal);
        });
    }

    //TDD PROCESS TESTS

    @Test
    public void visitorFirstTenFree() {
        CarParkKind carParkKind = CarParkKind.VISITOR;

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(1, 18);
        Period reducedPeriod1 = new Period(20, 22);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(1, 5); // Parking for 5 hours

        assertEquals(BigDecimal.valueOf(0).setScale(2), rate.calculate(parkingPeriod)); // Expected cost = 0€ (because total cost is at 10€ which is free)
    }

    @Test
    public void visitorFirstTenFreeAndReductionAbove() {
        CarParkKind carParkKind = CarParkKind.VISITOR;

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(1, 18);
        Period reducedPeriod1 = new Period(20, 22);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(1, 9); // Parking for 9 hours

        // Total cost without reduction: (8 hours normal rate * 2€) = 18€
        // Free amount = 10€
        // Remaining amount after free: 18€ - 10€ = 8€
        // 50% reduction on remaining amount: 8€ * 0.5 = 4€
        assertEquals(BigDecimal.valueOf(4).setScale(2), rate.calculate(parkingPeriod)); // Expected cost = 4€
    }

    @Test
    public void managementMinimumPayable() {
        CarParkKind carParkKind = CarParkKind.MANAGEMENT;

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(1, 18);
        Period reducedPeriod1 = new Period(20, 22);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(1, 3); // Parking for 2 hours

        assertEquals(BigDecimal.valueOf(5.0).setScale(2), rate.calculate(parkingPeriod));
    }

    @Test
    public void managementMinimumPayable2() {
        CarParkKind carParkKind = CarParkKind.MANAGEMENT;

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(1, 18);
        Period reducedPeriod1 = new Period(20, 22);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(1, 4); // Parking for 3 hours

        assertEquals(BigDecimal.valueOf(5.00).setScale(2), rate.calculate(parkingPeriod));
    }

    @Test
    public void studentReductionAboveFiveFifty() {
        CarParkKind carParkKind = CarParkKind.STUDENT;

        BigDecimal normalRate = new BigDecimal(2);
        BigDecimal reducedRate = new BigDecimal(1);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(6, 18);
        Period reducedPeriod1 = new Period(18, 24);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(8, 14); // Parking for 6 hours

        assertEquals(BigDecimal.valueOf(2.00).setScale(2), rate.calculate(parkingPeriod).setScale(2));
    }

    @Test
    public void studentReductionAboveFiveFiftyDifferentHours() {
        CarParkKind carParkKind = CarParkKind.STUDENT;

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(1.5);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(7, 19);
        Period reducedPeriod1 = new Period(19, 24);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(10, 18); // Parking for 8 hours

        assertEquals(BigDecimal.valueOf(8.00).setScale(2), rate.calculate(parkingPeriod).setScale(2));
    }

    @Test
    public void staffMaximumPayable1() {
        CarParkKind carParkKind = CarParkKind.STAFF;

        BigDecimal normalRate = new BigDecimal(2.5);
        BigDecimal reducedRate = new BigDecimal(1.5);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(7, 19);
        Period reducedPeriod1 = new Period(19, 24);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(7, 24); // Parking for 17 hours

        assertEquals(BigDecimal.valueOf(14.75).setScale(2), rate.calculate(parkingPeriod).setScale(2));
    }

    @Test
    public void staffMaximumPayablePerDayWithDifferentRates() {
        CarParkKind carParkKind = CarParkKind.STAFF;

        BigDecimal normalRate = new BigDecimal(3);
        BigDecimal reducedRate = new BigDecimal(2);

        ArrayList<Period> normalPeriods = new ArrayList<>();
        ArrayList<Period> reducedPeriods = new ArrayList<>();

        Period normalPeriod1 = new Period(6, 18);
        Period reducedPeriod1 = new Period(18, 24);

        normalPeriods.add(normalPeriod1);
        reducedPeriods.add(reducedPeriod1);

        Rate rate = new Rate(normalRate, reducedRate, carParkKind, reducedPeriods, normalPeriods);

        Period parkingPeriod = new Period(6, 22); // Parking for 16 hours

        assertEquals(BigDecimal.valueOf(18.00).setScale(2), rate.calculate(parkingPeriod).setScale(2));
    }

}
package com.zen.task.domain.compensation;

import com.zen.task.application.controller.EmployeeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BonusCalculatorTest {

    @Autowired
    private EmployeeController employeeController;

    @Test
    public void shouldBonusIncreaseOverFullYearProgressions() {
        // setup
        LocalDate startDate = LocalDate.of(2010,1, 1);
        LocalDate calcDate = LocalDate.of(2010,12, 31);
        LocalDate calcDate2 = LocalDate.of(2011,1, 1);
        LocalDate calcDate3 = LocalDate.of(2011,12, 31);
        LocalDate calcDate4 = LocalDate.of(2012,1, 1);

        assertEquals(0, BonusCalculator.calculate(startDate, calcDate, 1000.00));
        assertEquals(150, BonusCalculator.calculate(startDate, calcDate2, 1000.00));
        assertEquals(150, BonusCalculator.calculate(startDate, calcDate3, 1000.00));
        assertEquals(300, BonusCalculator.calculate(startDate, calcDate4, 1000.00));

    }

    @Test
    public void shouldBonusCapAt10Years() {
        // setup
        LocalDate startDate = LocalDate.of(2000,1, 1);
        LocalDate calcDate = LocalDate.of(2015,1, 1);

        assertEquals(1500, BonusCalculator.calculate(startDate, calcDate, 1000.00));

    }

}

package com.zen.task.domain.compensation;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BonusCalculator {

    private final static Double BONUS_FACTOR = 1.5;

    public static Double calculate(LocalDate employmentStart, LocalDate calculationDate, Double baseSalary) {
        long fullYears = ChronoUnit.YEARS.between(employmentStart, calculationDate);
        double experienceMarker = Math.min(fullYears/10.0, 1);
        return baseSalary * experienceMarker * BONUS_FACTOR;
    }
}

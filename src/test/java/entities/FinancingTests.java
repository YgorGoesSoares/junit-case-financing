package entities;

import org.example.entities.Financing;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FinancingTests {

    @Test
    public void shouldCreateNewFinancingWhenParametersAreValid() {
        Double totalAmount = 100000.0;
        Double income = 2000.0;
        Integer months = 80;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);

        Assertions.assertInstanceOf(Financing.class, fin);

    }

    @Test
    public void shouldThrowExceptionsWhenExceedHalfIncome() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Double totalAmount = 100000.0;
            Double income = 2000.0;
            Integer months = 20;

            Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);
        });
    }

    @Test
    public void shouldUpdateTotalAmountWhenValidValue () {
        Double initialTotalAmount = 100000.0;
        Double updatedTotalAmount = 10000.0;
        Double income = 2000.0;
        Integer months = 80;

        Financing fin = FinancingFactory.createFinancing(initialTotalAmount, income, months);
        fin.setTotalAmount(updatedTotalAmount);

        Assertions.assertEquals(updatedTotalAmount, fin.getTotalAmount());
    }

    @Test
    public void shouldThrowExceptionWhenTryToUpdateTotalAmountWithInvalidValue() {
        Double initialTotalAmount = 100000.0;
        Double updatedTotalAmount = 1000000.0;
        Double income = 2000.0;
        Integer months = 80;

        Financing fin = FinancingFactory.createFinancing(initialTotalAmount, income, months);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
           fin.setTotalAmount(updatedTotalAmount);
        });
    }

    @Test
    public void shouldUpdateIncomeWhenValidValue() {
        Double totalAmount = 100000.0;
        Double updatedIncome = 3000.0;
        Double income = 2000.0;
        Integer months = 80;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);
        fin.setIncome(updatedIncome);

        Assertions.assertEquals(updatedIncome, fin.getIncome());
    }

    @Test
    public void shouldThrowExceptionWhenTryToUpdateIncomeWithInvalidValue() {
        Double totalAmount = 100000.0;
        Double updatedIncome = 1000.0;
        Double income = 2000.0;
        Integer months = 80;
        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fin.setIncome(updatedIncome);
        });
    }

    @Test
    public void shouldUpdateMonthsWhenValidValue() {
        Double totalAmount = 100000.0;
        Double income = 2000.0;
        Integer months = 80;
        Integer updatedMonths = 90;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);
        fin.setMonths(updatedMonths);

        Assertions.assertEquals(updatedMonths, fin.getMonths());
    }

    @Test
    public void shouldThrowExceptionWhenTryToSetInvalidMonths() {
        Double totalAmount = 100000.0;
        Double income = 2000.0;
        Integer months = 80;
        Integer updatedMonths = 20;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fin.setMonths(updatedMonths);
        });
    }

    @Test
    public void shouldCalculateCorrectlyEntryValue() {
        Double totalAmount = 100000.0;
        Double income = 2000.0;
        Integer months = 80;
        Double expectedEntry = totalAmount * 0.2;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);

        Assertions.assertEquals(expectedEntry, fin.entry());
    }

    @Test
    public void shouldCalculateCorrectlyQuotaValue() {
        Double totalAmount = 100000.0;
        Double income = 2000.0;
        Integer months = 80;

        Financing fin = FinancingFactory.createFinancing(totalAmount, income, months);
        Double expectedQuota = (totalAmount - fin.entry()) / fin.getMonths();

        Assertions.assertEquals(expectedQuota, fin.quota());
    }
}

package e02_functional_interface;

import static org.junit.Assert.*;

public class CalculatorServiceTest {

    @org.junit.Test
    public void testCalculateAddition() throws Exception {
        //Calculation calculation = new Addition();
        Calculation calculation = (i1, i2) -> i1 + i2;

        int actual = calculation.calculate(1, 1);

        assertEquals(actual, 2);
    }

    @org.junit.Test
    public void testCalculateSubtraction() throws Exception {
        Calculation calculation = new Subtraction();

        int actual = calculation.calculate(1, 1);

        assertEquals(actual, 0);
    }

    @org.junit.Test
    public void testCalculateMultiplication() throws Exception {
        Calculation calculation = new Multiplication();

        int actual = calculation.calculate(1, 1);

        assertEquals(actual, 1);
    }

    @org.junit.Test
    public void testCalculateDivision() throws Exception {
        Calculation calculation = new Division();

        int actual = calculation.calculate(8, 4);

        assertEquals(actual, 2);
    }
}
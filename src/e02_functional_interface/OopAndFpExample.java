package e02_functional_interface;

public class OopAndFpExample {
    public static void main(String[] args) {
        CalculatorService calculatorService = new CalculatorService(new Addition(), new Subtraction());
        int additionResult = calculatorService.calculate(11, 4);
        System.out.println(additionResult);
        int subtractionResult = calculatorService.calculate(11, 1);
        System.out.println(subtractionResult);
        int multiplicationResult = calculatorService.calculate(11, 2);
        System.out.println(multiplicationResult);
        int divisionResult = calculatorService.calculate(20, 4);
        System.out.println(divisionResult);

        /** 출력
         * 15
         * 12
         * 13
         * 24
         */

        // Fp
        FpCalculatorService fpCalculatorService = new FpCalculatorService();
        System.out.println("      addition : " + fpCalculatorService.calculate(new Addition(), 11, 4));
        System.out.println("   subtraction : " + fpCalculatorService.calculate(new Subtraction(), 11, 1));
        System.out.println("multiplication : " + fpCalculatorService.calculate(new Multiplication(), 11, 2));
        System.out.println("      division : " + fpCalculatorService.calculate(new Division(), 20, 4));

        // Fp - using lambda expression
        System.out.println("      addition : " + fpCalculatorService.calculate((i1, i2) -> i1 + i2, 11, 4));
        System.out.println("   subtraction : " + fpCalculatorService.calculate((i1, i2) -> i1 - i2, 11, 1));
        System.out.println("multiplication : " + fpCalculatorService.calculate((i1, i2) -> i1 * i2, 11, 2));
        System.out.println("      division : " + fpCalculatorService.calculate((i1, i2) -> i1 / i2, 20, 4));

        /** 출력
         *       addition : 15
         *    subtraction : 10
         * multiplication : 22
         *       division : 5
         *       addition : 15
         *    subtraction : 10
         * multiplication : 22
         *       division : 5
         */

    }
}

interface Calculation {
    int calculate(int num1, int num2);
}

class Addition implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 + num2;
    }
}

class Subtraction implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 - num2;
    }
}

class Multiplication implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 * num2;
    }
}

class Division implements Calculation {
    @Override
    public int calculate(int num1, int num2) {
        return num1 / num2;
    }
}

class CalculatorService {
    private Calculation calculation;
    private Calculation calculation2;

    public CalculatorService(Calculation calculation, Calculation calculation2) {
        this.calculation = calculation;
        this.calculation2 = calculation2;
    }

    public int calculate( int num1, int num2) {
        // num1 이 항상 10 보다 커야 하고, num2 는 항상 num1 보다 작아야 한다고. 가정
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid Argument : num1 : " + num1 + ", num2 : " + num2);
        }
        /*
        if (calculation == '+') {
            return num1 + num2;
        } else if (calculation == '-') {
            return num1 - num2;
        } else if (calculation == '*') {
            return num1 * num2;
        } else if (calculation == '/') {
            return num1 / num2;
        } else {
            throw new IllegalArgumentException("Unknown calculation : " + calculation);
        }
        */
    }

    public int compute( int num1, int num2) {
        // num1 이 항상 10 보다 커야 하고, num2 는 항상 num1 보다 작아야 한다고. 가정
        if (num1 > 10 && num2 < num1) {
            return calculation2.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid Argument : num1 : " + num1 + ", num2 : " + num2);
        }
        /*
        if (calculation == '+') {
            return num1 + num2;
        } else if (calculation == '-') {
            return num1 - num2;
        } else if (calculation == '*') {
            return num1 * num2;
        } else if (calculation == '/') {
            return num1 / num2;
        } else {
            throw new IllegalArgumentException("Unknown calculation : " + calculation);
        }
        */
    }
}

class FpCalculatorService {
    int calculate(Calculation calculation, int num1, int num2) {
        // num1 이 항상 10 보다 커야 하고, num2 는 항상 num1 보다 작아야 한다고. 가정
        if (num1 > 10 && num2 < num1) {
            return calculation.calculate(num1, num2);
        } else {
            throw new IllegalArgumentException("Invalid Argument : num1 : " + num1 + ", num2 : " + num2);
        }
    }
}
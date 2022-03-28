package e07_method_reference;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MethodReferenceExample3 {
    public static void main(String[] args) {
        /*
         * First Class Function
         */

        /*
         * A function can be passed to another function
         */
        // Using Lambda Expression
        System.out.println("Using Lambda Expression #1 - " +
                testFirstClassFunction1(3, i -> String.valueOf(i * 2))
        );

        // Using Method Reference
        System.out.println("Using Method Reference #1  - " +
            testFirstClassFunction1(3, MethodReferenceExample3::doubleThenToString)
        );

        /*
         * A function can be returned as the result of the function
         */
        // Using Lambda Expression
        Function<Integer, String> f1 = getDoubleThenToStringUsingLambdaExpression();
        System.out.println("Using Lambda Expression #2 - " +
                f1.apply(3)
        );

        // Using Method Reference
        Function<Integer, String> f2 = getDoubleThenToStringUsingMethodReference();
        System.out.println("Using Method Reference #2  - " +
                f2.apply(3)
        );

        /*
         * A function can be stored in the data structure
         */
        // Using Lambda Expression
        List<Function<Integer, String>> fsList1 = Arrays.asList(i -> String.valueOf(i * 2));
        System.out.println("Using Lambda Expression #3 - " +
                fsList1.get(0).apply(3)
        );

        // Using Method Reference
        List<Function<Integer, String>> fsList2 = Arrays.asList(MethodReferenceExample3::doubleThenToString);
        System.out.println("Using Method Reference #3  - " +
                fsList2.get(0).apply(3)
        );

        /** 출력
         * Using Lambda Expression #1 - The result is 6
         * Using Method Reference #1  - The result is 6
         * Using Lambda Expression #2 - 6
         * Using Method Reference #2  - 6
         * Using Lambda Expression #3 - 6
         * Using Method Reference #3  - 6
         */
    }

    private static String testFirstClassFunction1(int n, Function<Integer, String> f) {
        return "The result is " + f.apply(n);
    }

    private static String doubleThenToString(int i) {
        return String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingLambdaExpression() {
        return i -> String.valueOf(i * 2);
    }

    private static Function<Integer, String> getDoubleThenToStringUsingMethodReference() {
        return MethodReferenceExample3::doubleThenToString;
    }
}


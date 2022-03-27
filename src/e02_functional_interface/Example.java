package e02_functional_interface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author ziippy on 2022-03-27.
 * @project modern_java8_untold/e02_functional_interface
 */

public class Example {
    public static void main(String[] args) {
        /////////////// Function 에 대해 알아보자
        // apply 메소드만 하나 있는 것
        Function<String, Integer> toInt = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        System.out.println(toInt.apply("100"));     // 100 출력

        // 람다를 해도 된다.
        Function<String, Integer> toInt2 = (final String v) -> {
            return Integer.parseInt(v);
        };
        System.out.println(toInt2.apply("100"));    // 100 출력

        // 한 줄 람다로 심플하게~
        Function<String, Integer> toInt3 = v -> Integer.parseInt(v);
        System.out.println(toInt3.apply("100"));    // 100 출력

        //////////////////////////// identity 라는 게 있다. 입력 값을 그대로 출력하는 method 를 identity function 이라고 한다.
        Function<Integer, Integer> identity = Function.identity();
        System.out.println(identity.apply(200));    // 200 출력

        Function<Integer, Integer> identity2 = v -> v;
        System.out.println(identity2.apply(200));   // 200 출력


        /////////////// Consumer 에 대해 알아보자
        // 소비만 하는 메소드
        Consumer<String> print = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        print.accept("Hello");          // Hello 출력

        Consumer<String> print2 = s -> System.out.println(s);
        print2.accept("Hello");         // Hello 출력


        /////////////// Predicate 에 대해 알아보자
        // 비교 구문을 이용해서 boolean 값을 리턴하는 메소드를 가진 Functional Inteface
        Predicate<Integer> isPositive = s -> s > 0 ? true : false;
        System.out.println(isPositive.test(1));     // true 출력

        Predicate<Integer> lessThan3 = v -> v < 3;
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> numbersLessThan3 = new ArrayList<>();
        for (Integer num : numbers) {
            if (lessThan3.test(num)) {
                numbersLessThan3.add(num);
            }
        }
        System.out.println(numbersLessThan3);       // [1, 2] 출력

        System.out.println(filter(numbers, lessThan3));     // [1, 2] 출력


        /////////////// Supplier 에 대해 알아보자
        Supplier<String> helloSupplier = () -> "Hello ";

        System.out.println(helloSupplier.get() + "world");  // Hello world 출력

        long start = System.currentTimeMillis();
        printIfValidIndex(0, getVeryExpensiveValue());
        printIfValidIndex(-1, getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms");

        start = System.currentTimeMillis();
        printIfValidIndex2(0, () -> getVeryExpensiveValue());
        printIfValidIndex2(-1, () -> getVeryExpensiveValue());
        System.out.println("It took " + (System.currentTimeMillis() - start) + " ms");
    }

    private static String getVeryExpensiveValue() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Martin";
    }

    private static void printIfValidIndex(int index, String value) {
        if (index >= 0) {
            System.out.println("The value is " + value + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    private static void printIfValidIndex2(int index, Supplier<String> valueSupplier) {
        if (index >= 0) {
            System.out.println("The value is " + valueSupplier.get() + ".");
        } else {
            System.out.println("Invalid");
        }
    }

    // 직접 만드는 필터
    private static <T> List<T> filter(List<T> list, Predicate<T> filter) {
        final List<T> result = new ArrayList<>();
        for (final T input : list) {
            if (filter.test(input)) {
                result.add(input);
            }
        }
        return result;
    }

    // 직접 만드는 맵
    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T input : list) {
            result.add(mapper.apply(input));
        }
        return result;
    }
}

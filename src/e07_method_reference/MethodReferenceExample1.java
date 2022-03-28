package e07_method_reference;

import java.math.BigDecimal;
import java.util.Arrays;

import static java.util.stream.Collectors.toList;

public class MethodReferenceExample1 {
    public static void main(String[] args) {
        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(i -> System.out.println(i));

        Arrays.asList(1, 2, 3, 4, 5)
                .forEach(System.out::println);

        /////////// ex
        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                    .stream()
                    .sorted((b1, b2) -> b1.compareTo(b2))
                    .collect(toList())
        );

        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimal::compareTo)
                        .collect(toList())
        );

        System.out.println(
                Arrays.asList(new BigDecimal("10.0"), new BigDecimal("23"), new BigDecimal("5"))
                        .stream()
                        .sorted(BigDecimalUtil::compareToDown)
                        .collect(toList())
        );

        /////////////
        final String ccc = "c";
        System.out.println(
                Arrays.asList("a", "b", "c", "d")
                        .stream()
                        .anyMatch(ccc::equals)
        );

        System.out.println(
                Arrays.asList("a", "b", "c", "d")
                        .stream()
                        .anyMatch("c"::equals)
        );

        /** 출력
         * [5, 10.0, 23]
         * [5, 10.0, 23]
         * [23, 10.0, 5]
         * true
         * true
         */
    }
}

class BigDecimalUtil {
    public static int compareToUp(BigDecimal bd1, BigDecimal bd2) {
        return bd1.compareTo(bd2);
    }

    public static int compareToDown(BigDecimal bd1, BigDecimal bd2) {
        return bd2.compareTo(bd1);
    }
}

package e04_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class StreamExample1 {
    public static void main(String[] args) {
//        IntStream.range(0, 10).forEach(i -> System.out.print(i + " "));
//        System.out.println("");
//        IntStream.rangeClosed(1, 10).forEach(i -> System.out.print(i + " "));
//        System.out.println("");

        // 무한대를 만들려면
        //IntStream.iterate(1, i -> i + 1).forEach(i -> System.out.print(i + " "));

        //Stream.iterate(BigInteger.ONE, i -> i.add(BigInteger.ONE)).forEach(i -> System.out.print(i + " "));

        //Stream.iterate(1, i -> i + 1).forEach(i -> System.out.println(i));
        //Stream.of(1,2,3,4,5).forEach(i -> System.out.println(i));

        // 기존의 Imperative Programming
        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer result = null;
        for (final Integer number : numbers) {
            if (number > 3 && number < 9) {
                final Integer newNumber = number * 2;
                if (newNumber > 10) {
                    result = newNumber;
                    break;
                }
            }
        }
        System.out.println("Imperative Result : " + result);                // 이건 15번 만에 끝나는데

        System.out.println("Functional Result : " + numbers.stream()
                .filter(i -> i > 3)
                .filter(i -> i < 9)
                .map(i -> i * 2)
                .filter(i -> i > 10)
                .findFirst()
        );                                                                  // 이건 27번이나 걸린다. 너무 비효율 아니냐?


        // 27번이나 걸리는 지 확인 ----------> 아니네 15번 걸리네!!
        System.out.println("Functional Result : " + numbers.stream()
                .filter(i -> {
                    System.out.println("i > 3    "  + i);
                    return i > 3;
                })
                .filter(i -> {
                    System.out.println("i < 9    "  + i);
                    return i< 9;
                })
                .map(i -> {
                    System.out.println("i * 2    "  + i);
                    return i * 2;
                })
                .filter(i -> {
                    System.out.println("i > 10    "  + i);
                    return i > 10;
                })
                .findFirst()
        );
        /* 아니네 15번 걸리네!!?
        i > 3    1
        i > 3    2
        i > 3    3
        i > 3    4
        i < 9    4
        i * 2    4
        i > 10    8
        i > 3    5
        i < 9    5
        i * 2    5
        i > 10    10
        i > 3    6
        i < 9    6
        i * 2    6
        i > 10    12
        */

        // 이유는 stream collection builder 는 게으른 컬렉션 빌더이기 때문이다.
        // 뭔가를 내 놓아라 하기 전까지는 하지 않는다.
        // findFirst 가 호출되니 그제서야 .filter .filter .map .filter 가 동작되는 것이다.

        // 직접 만든 필터와 맵을 이용할 수 도 있다.
        // But, 이건 27번이나 다 수행해야 한다... 성능상 좋지도 않다. -----> 그러므로 stream 을 쓰는 게 낫다.
        List<Integer> over3 = filter(numbers, i -> i > 3);
        List<Integer> under9 = filter(over3, i -> i > 3);
        List<Integer> doubled = map(under9, i -> i * 2);
        List<Integer> over10 = filter(doubled, i -> i > 10);
        System.out.println("my own method result : " + over10.get(0));

        /** 출력
         * Imperative Result : 12
         * Functional Result : Optional[12]
         * i > 3    1
         * i > 3    2
         * i > 3    3
         * i > 3    4
         * i < 9    4
         * i * 2    4
         * i > 10    8
         * i > 3    5
         * i < 9    5
         * i * 2    5
         * i > 10    10
         * i > 3    6
         * i < 9    6
         * i * 2    6
         * i > 10    12
         * Functional Result : Optional[12]
         * my own method result : 12
         *
         * Process finished with exit code 0
         */

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




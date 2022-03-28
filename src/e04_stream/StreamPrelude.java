package e04_stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class StreamPrelude {
    public static void main(String[] args) {
        final int abs1 = Math.abs(-1);
        final int abs2 = Math.abs(1);

        System.out.println(abs1);
        System.out.println(abs2);
        System.out.println("abs1 == abs2 is " + (abs1 == abs2));

        final int minInt = Math.abs(Integer.MIN_VALUE);
        System.out.println(minInt);

        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);

        final List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(
                map(numbers, i -> i * 2)
        );
        System.out.println(
                map(numbers, null)
        );
        //System.out.println(numbers);

        final List<Integer> numbers2 = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(
                mapNew(numbers2, i -> i * 2)
        );
        System.out.println(
                mapNew(numbers2, i -> i)
        );
        System.out.println(
                mapNew(numbers2, Function.identity())
        );

        /** 출력
         * 1
         * 1
         * abs1 == abs2 is true
         * -2147483648
         * -2147483648
         * 2147483647
         * [2, 4, 6, 8, 10]
         * [1, 2, 3, 4, 5]
         * [2, 4, 6, 8, 10]
         * [1, 2, 3, 4, 5]
         * [1, 2, 3, 4, 5]
         */
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        // mapper 가 null 인 경우를 위해서 아래처럼 짤 수 있지만.. 뭔가 복잡하다.
        final List<R> result;
        if (mapper != null) {
            result = new ArrayList<>();
        } else {
            result = new ArrayList<>((List<R>) list);
        }
        if (result.isEmpty()) {
            for (final T t : list) {
                result.add(mapper.apply(t));
            }
        }
        return result;
    }

    private static <T, R> List<R> mapNew(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}

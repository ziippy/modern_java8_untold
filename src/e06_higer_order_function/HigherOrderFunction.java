package e06_higer_order_function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import static java.util.stream.Collectors.toList;

/**
 * @author ziippy on 2022-03-28.
 * @project modern_java8_untold/e06_higer_order_function
 */
public class HigherOrderFunction {
    public static void main(String[] args) {
        // 조건 1 - Function 이 파라미터로 또다른 Function 을 받을 경우
        final Function<Function<Integer, String>, String> f = g -> g.apply(10);

        System.out.println(f.apply(i -> "#" + i));

        // 조건 2 - Function 이 Function 을 리턴
        final Function<Integer, Function<Integer, Integer>> f2 = g -> g2 -> g + g2;

        System.out.println(f2.apply(1).apply(9));


        // 우리가 이미 이런 Higher Order Function 을 많이 쓰고 있다.
        // 예를 들어.. 아래와 같은 것들이 이미 파라미터로 function 을 받고 있다.
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(map(list, i -> i + 10));

        System.out.println(
                list.stream()
                        .map(i -> i + 10)
                        .collect(toList())
        );

        // function 을 넘기는 건 identity 가 그 예시다.
        //Function.identity();

        /** 출력
         * #10
         * 10
         * [11, 12, 13, 14, 15]
         * [11, 12, 13, 14, 15]
         */
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> mapper) {
        final List<R> result = new ArrayList<>();
        for (final T t : list) {
            result.add(mapper.apply(t));
        }
        return result;
    }
}

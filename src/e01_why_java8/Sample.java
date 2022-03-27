package e01_why_java8;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * @author ziippy on 2022-03-27.
 * @project modern_java8_untold/why_java8
 */

public class Sample {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // "1 : 2 : 3 : 4 : 5 : 6 : 7 : 8 : 9: 10" 을 출력해라

        // case #1 - old
        // 매번 " : " 를 append 하고, 맨 마지막에만 하지 않는 코드..
        StringBuilder stringBuilder = new StringBuilder();

        int size = numbers.size();
        for (int i = 0; i < size; i++) {
            stringBuilder.append(numbers.get(i));
            if (i != size - 1) {
                stringBuilder.append(" : ");
            }
        }
        System.out.println(stringBuilder);

        // case #2 - old
        // 일단 다 붙이고.. 그 후 " : " 를 제거하는 코드..
        stringBuilder = new StringBuilder();

        String separator = " : ";
        for (Integer number : numbers) {
            stringBuilder.append(number).append(separator);
        }
        int stringLength = stringBuilder.length();
        if (stringLength > 0) {
            stringBuilder.delete(stringLength - separator.length(), stringLength);
        }

        System.out.println(stringBuilder);

        // 뭔가 되게 복잡하고. noise 가 많은 코드다. (kevin 님 말투 참고)

        // case #3 (modern java8 functional programming)
        // simple
        String result = numbers.stream()
                .map(String::valueOf)
                .collect(joining(" : "));

        System.out.println(result);
    }
}

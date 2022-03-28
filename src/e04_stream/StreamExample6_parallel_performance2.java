package e04_stream;

import java.util.ArrayList;
import java.util.List;

public class StreamExample6_parallel_performance2 {

    public static long iterativeSum(List<Long> numbers) {
        long result = 0;
        for (long number : numbers) {
            result += number;
        }
        return result;
    }

    public static long sequentialSum(List<Long> numbers) {
        return numbers.stream().reduce(Long::sum).get();
    }

    public static long parallelSum(List<Long> numbers) {
        return numbers.parallelStream().reduce(Long::sum).get();
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        long sum = 0;
        final long number = 10_000_000;

        List<Long> numbers = new ArrayList<>();
        for (int i = 1; i <= number; i++) {
            numbers.add((long) i);
        }

        sum = iterativeSum(numbers);
        System.out.printf("iterativeSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = sequentialSum(numbers);
        System.out.printf("sequentialSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = parallelSum(numbers);
        System.out.printf("parallelSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        /** 출력
         * iterativeSum 50000005000000 - takes 1577 ms
         * sequentialSum 50000005000000 - takes 721 ms
         * parallelSum 50000005000000 - takes 170 ms
         */
    }
}

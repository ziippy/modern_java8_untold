package e04_stream;

import java.util.concurrent.TimeUnit;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class StreamExample6_parallel_performance {

    public static void slowDown() {
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    public static long sequentialSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    public static long sequentialSum2(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).peek(i -> slowDown()).reduce(Long::sum).get();
    }

    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();
        //
        // iterate 는 function 을 필요로 하는데, 이전 값이 있어야지만 다음 값을 만드는 것이다.
        // 그런데 위의 경우에는 이전 값을 기다려야 하므로.. 오히려 parallel 로 하면, 여러 쓰레드가 체크하느라 더 오래 걸린다.
    }

    public static long parallelSum2(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().peek(i -> slowDown()).reduce(Long::sum).get();
        // iterate 를 씀에도 불구하고.. 이건 빠른 이유는
        // peek 에서 사용하는 slowDown 이라는 함수에서 오래 걸리므로, 여러 쓰레드에서 하면 오히려 끝난 쓰레드들의 값을 합치고 넘길 수 있으므로.. 더 빠르다.
    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long rangedSum2(long n) {
        return LongStream.rangeClosed(1, n).peek(i -> slowDown()).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum2(long n) {
        return LongStream.rangeClosed(1, n).parallel().peek(i -> slowDown()).reduce(Long::sum).getAsLong();
    }

    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        long sum = 0;
        final long number = 10_000;

        sum = iterativeSum(number);
        System.out.printf("iterativeSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = sequentialSum(number);
        System.out.printf("sequentialSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = parallelSum(number);
        System.out.printf("parallelSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = rangedSum(number);
        System.out.printf("rangedSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = parallelRangedSum(number);
        System.out.printf("parallelRangedSum %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        System.out.println("==================================================");

        start = System.currentTimeMillis();
        sum = sequentialSum2(number);
        System.out.printf("sequentialSum2 %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = parallelSum2(number);
        System.out.printf("parallelSum2 %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = rangedSum2(number);
        System.out.printf("rangedSum2 %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        sum = parallelRangedSum2(number);
        System.out.printf("parallelRangedSum2 %d - takes %d ms\n", sum, (System.currentTimeMillis() - start));

        /** 출력
         * iterativeSum 50005000 - takes 1 ms
         * sequentialSum 50005000 - takes 10 ms
         * parallelSum 50005000 - takes 52 ms
         * rangedSum 50005000 - takes 28 ms
         * parallelRangedSum 50005000 - takes 15 ms
         */
    }
}

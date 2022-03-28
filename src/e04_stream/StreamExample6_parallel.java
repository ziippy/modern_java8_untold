package e04_stream;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class StreamExample6_parallel {
    public static void main(String[] args) {
        int[] sum = {0};
        IntStream.range(0, 100000)
                .forEach(i -> sum[0] += i);
        System.out.println("sum : " + sum[0]);


        // 멀티코어 프로세스를 활용해보자
        int[] sum2 = {0};
        IntStream.range(0, 100000)
                .parallel()
                .forEach(i -> {
                    //System.out.println("Starting " + Thread.currentThread().getName() + ",    i=" + i + ", " + sum2);
                    sum2[0] += i;
                });
        System.out.println("parallel sum2 (with side effect) : " + sum2[0]);

        // sum : 4950
        //sum2 : 4938
        // 이렇게 나오는 경우가 있다. 이게 side effect 이다.

        System.out.println("         sum (no side effect) : " + IntStream.range(0, 100000).sum());
        System.out.println("parallel sum (no side effect) : " + IntStream.range(0, 100000).parallel().sum());

        ////////////////////////

        System.out.println("\n===============");
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .forEach(i -> System.out.print(i));

        System.out.println("\n===============");
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .stream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.print(i));

        System.out.println("\n===============");
        //System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "1");  // 2개를 쓰겠다는 의미.. -> 이건 맨 처음에 해 줘야 한다.
        // The dynamic setting works when it is the first statement accessing the pool.
        // Try removing the first line which gets and prints the default parallelism. Use just the below.
        System.out.println("getParallelism=" + ForkJoinPool.commonPool().getParallelism());
        Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)
                .parallelStream()
                .map(i -> {
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return i;
                })
                .forEach(i -> System.out.print(i));

        /** 출력
         * sum : 704982704
         * parallel sum2 (with side effect) : 651059230
         *          sum (no side effect) : 704982704
         * parallel sum (no side effect) : 704982704
         *
         * ===============
         * 65382741
         * ===============
         * 12345678
         * ===============
         * getParallelism=3
         * 63425871
         */
    }
}

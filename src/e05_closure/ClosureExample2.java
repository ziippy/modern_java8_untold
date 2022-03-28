package e05_closure;

public class ClosureExample2 {
    private int number = 999;

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        int number = 100;

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();

        Runnable runnable1 = () -> System.out.println(number);    // 이걸 compiler 가 위 처럼 바꿔서 하는 걸까? -> No!
        runnable1.run();

        /** 출력
         * 100
         * 100
         */
    }
}

package e05_closure;

public class ClosureExample {
    public static void main(String[] args) {
        int number = 100;   // 이건 lambda 에서 호출하기 때문에 effectively final 이다.
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                System.out.println(number);
            }
        };
        runnable.run();

        Runnable runnable2 = () -> System.out.println(number);
        runnable2.run();

        /** 출력
         * 100
         * 100
         */
    }
}

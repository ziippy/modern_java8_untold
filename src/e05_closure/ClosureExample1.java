package e05_closure;

public class ClosureExample1 {
    private int number = 999;

    @Override
    public String toString() {
        return "this is ClosureExample2 toString";
    }

    public static <T> String toString(T value) {
        return "static toString : " + String.valueOf(value);
    }

    public void testClosure(final Runnable runnable) {
        System.out.println("==================================");
        runnable.run();
        System.out.println("==================================");
    }
    public void test1() {
        int number = 111;

        // number = 99;    // cannot

        testClosure(new Runnable() {
            @Override
            public void run() {
                //System.out.println(this.number);        // error
                System.out.println(ClosureExample1.this.number);    // 이렇게 해야 클래스 멤버변수의 number 를 접근할 수 있다.
            }
        });

        testClosure(() -> System.out.println(this.number));

        //////////

        testClosure(new Runnable() {
            @Override
            public void run() {
                System.out.println(this.toString());        // this is runnable toString
                System.out.println(ClosureExample1.this.toString());        // this is ClosureExample2 toString
            }

            @Override
            public String toString() {
                return "this is runnable toString";
            }
        });

        testClosure(() -> System.out.println(this.toString()));     // this is ClosureExample2 toString


        //////////

        testClosure(new Runnable() {
            @Override
            public void run() {
                System.out.println(toString());                                 // this is runnable toString
                System.out.println(ClosureExample1.this.toString());            // this is ClosureExample2 toString
                System.out.println(ClosureExample1.toString("test3"));    // static toString : test3
                //System.out.println(toString("test3"));                          // 불가.. shadowing 때문에 안됨
            }

            @Override
            public String toString() {
                return "this is runnable toString";
            }
        });

        testClosure(() -> {
            System.out.println(toString());                                 // this is ClosureExample2 toString
            System.out.println(ClosureExample1.this.toString());            // this is ClosureExample2 toString
            System.out.println(ClosureExample1.toString("test3"));    // static toString : test3
            System.out.println(toString("test3"));                     // static toString : test3
        });

        /** 출력
         * ==================================
         * 999
         * ==================================
         * ==================================
         * 999
         * ==================================
         * ==================================
         * this is runnable toString
         * this is ClosureExample2 toString
         * ==================================
         * ==================================
         * this is ClosureExample2 toString
         * ==================================
         * ==================================
         * this is runnable toString
         * this is ClosureExample2 toString
         * static toString : test3
         * ==================================
         * ==================================
         * this is ClosureExample2 toString
         * this is ClosureExample2 toString
         * static toString : test3
         * static toString : test3
         * ==================================
         */
    }

    public static void main(String[] args) {
        new ClosureExample1().test1();

//        int number = 100;   // 이건 lambda 에서 호출하기 때문에 effectively final 이다.
//        Runnable runnable = new Runnable() {
//            @Override
//            public void run() {
//                System.out.println(number);
//            }
//        };
//        runnable.run();
//
//        Runnable runnable2 = () -> System.out.println(number);
//        runnable2.run();
    }
}

package e03_my_functional_interface;

import java.util.function.Function;

/**
 * @author ziippy on 2022-03-28.
 * @project modern_java8_untold/e03_my_functional_interface
 */
public class MyFunctionalInterface {
    void doSomething (Function<Integer, String> f) {
        doSomething(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) {
                return String.valueOf(integer);
            }
        });
    }

    void doSomething2 (Function<Integer, String> f) {
        doSomething(i -> String.valueOf(i));
    }

    public static void main(String[] args) {
        callPrintln(1, 2, 3, (i1, i2, i3) -> String.valueOf(i1 + i2 + i3));     // 6 출력
        callPrintln("Area is ", 12, 20, (message, width, height) -> String.valueOf(message + (width * height)));    // 240 출력
    }

    private static <T1, T2, T3> void callPrintln(T1 t1, T2 t2, T3 t3, Function3<T1, T2, T3, String> function) {
        System.out.println(function.aaa(t1, t2, t3));
    }
}

@FunctionalInterface
interface Function3<T1, T2, T3, R> {
    R aaa(T1 t1, T2 t2, T3 t3);
}
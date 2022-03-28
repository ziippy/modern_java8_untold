package e04_stream;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample3 {
    public static void main(String[] args) {
        System.out.println("collect(toList()) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toList())
        );

        System.out.println("collect(toSet()) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(toSet())
        );

        System.out.println("collect(joining()) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining())
        );

        System.out.println("collect(joining(\", \")) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining(", "))
        );

        System.out.println("collect(joining(<\", \">)) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .collect(joining(", ", "<", ">"))
        );

        // 중복된 걸 제거해서 joining 하고 싶다. --> distinct 를 사용하면 된다.
        System.out.println("collect(distinct before joining(<\", \">)) : " + Stream.of(1, 3, 3, 5, 5)
                .filter(i -> i > 2)
                .map(i -> i * 2)
                .map(i -> "#" + i)
                .distinct()
                .collect(joining(", ", "<", ">"))
        );

        System.out.println("findFirst : " + Stream.of(1, 2, 3, 4, 5)
                .findFirst()
        );

        System.out.println("findFirst : " + Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i == 3)    // 여기서는 i 가 Integer 이지만 value 값을 얻어서 3과 primitive 비교를 한다. 그러므로 3이 있다고 나온다.
                .findFirst()
        );

        final Integer integer3 = 3;
        System.out.println("findFirst : " + Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i == integer3)    // 여기서는 Integer 이기 때문에 identity 비교를 한다.
                                               // 즉, 메모리 러퍼런스 비교를 하기 때문에 없을 것이라고 예상하는데.. 결과는 있다고 나온다.
                                               // 이유는 자바8 이 하는 Integer 의 auto boxing 에서는 Integer.valueOf 를 이용하는데
                                               // 이게 -128 ~ 127 (기본값) 사이는 caching 을 하기 때문이다.
                .findFirst()
        );

        final Integer integer128 = 128;
        System.out.println("findFirst : " + Stream.of(1, 2, 3, 4, 5, 128)
                .filter(i -> i == integer128)    // 이렇게 Integer.valueOf 가 cache 하지 않는 범위의 숫자를 이용하면. empty 가 나온다.
                .findFirst()
        );

        final Integer integer129 = 129;
        System.out.println("findFirst : " + Stream.of(1, 2, 3, 4, 5, 129)
                .filter(i -> i.equals(integer129))    // 이렇게 equals 를 사용해야 찾는다.
                .findFirst()
        );
        
        // Equality 는 equals 를 이용해서 확인
        // Identity 는 == 이용해 메모리 레퍼런스 확인

        System.out.println(Stream.of(1, 2, 3, 4, 5)
                .filter(i -> i > 3)    // 이렇게 equals 를 사용해야 찾는다.
                .count()
        );

        /** 출력
         * collect(toList()) : [#6, #6, #10, #10]
         * collect(toSet()) : [#10, #6]
         * collect(joining()) : #6#6#10#10
         * collect(joining(", ")) : #6, #6, #10, #10
         * collect(joining(<", ">)) : <#6, #6, #10, #10>
         * collect(distinct before joining(<", ">)) : <#6, #10>
         * findFirst : Optional[1]
         * findFirst : Optional[3]
         * findFirst : Optional[3]
         * findFirst : Optional.empty
         * findFirst : Optional[129]
         * 2
         *
         * Process finished with exit code 0
         */

    }
}

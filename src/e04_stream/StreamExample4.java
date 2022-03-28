package e04_stream;

import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

public class StreamExample4 {
    public static void main(String[] args) {
        Stream.of(1, 2, 3, 4, 5)
                .collect(toList());

        Stream.of(1, 2, 3, 4, 5)
                .collect(toSet());

        Stream.of(1, 2, 3, 4, 5)
                .map(i -> "" + i)
                .collect(joining());
    }
}

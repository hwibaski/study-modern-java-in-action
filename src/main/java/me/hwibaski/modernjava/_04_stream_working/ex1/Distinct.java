package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Distinct {
    public static void main(String[] args) {
        println("----------------------distinct----------------------------");
        println("고유한 요소만 반환");
        // 1, 2, 3
        Stream.of(1, 1, 1, 2, 2, 2, 3, 3, 3)
                .distinct()
                .forEach(System.out::println);
    }

    static void println(String s) {
        System.out.println(s);
    }
}

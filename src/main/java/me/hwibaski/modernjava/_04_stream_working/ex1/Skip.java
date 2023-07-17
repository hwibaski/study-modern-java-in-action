package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Skip {
    public static void main(String[] args) {
        println("----------------------skip----------------------------");
        println("조건에 부합하지만 skip의 인수만큼 건너뛰고 결과 반환");

        // 6, 8
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 == 0)
                .skip(2)
                .forEach(System.out::println);
    }

    static void println(String s) {
        System.out.println(s);
    }
}

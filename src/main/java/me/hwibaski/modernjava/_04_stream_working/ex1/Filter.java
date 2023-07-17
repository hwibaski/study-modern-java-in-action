package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Filter {
    public static void main(String[] args) {
        println("----------------------filter----------------------------");
        println("filter 는 모든 요소를 다 조건에 맞는지 확인한다.");
        // 2, 4, 6, 8
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    static void println(String s) {
        System.out.println(s);
    }
}

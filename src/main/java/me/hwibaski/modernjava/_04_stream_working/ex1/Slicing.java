package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Slicing {
    public static void main(String[] args) {
        println("-----------------------takeWhile---------------------------");
        println("takeWhile은 조건에 대해 참이 아닐 경우 해당 요소에서 바로 멈춘다.");

        // 2, 4
        Stream.of(2, 4, 3, 4, 5, 6, 7, 8, 9)
                .takeWhile(n -> n % 2 == 0)
                .forEach(System.out::println);

        println("-----------------------dropWhile---------------------------");
        println("조건이 거짓이 되면 그 지점에서 작업을 중단하고 남은 요소를 모두 반환한다.");
        // 3, 4, 5, 6, 7, 8, 9
        Stream.of(2, 4, 3, 4, 5, 6, 7, 8, 9)
                .dropWhile(n -> n % 2 == 0)
                .forEach(System.out::println);
    }

    static void println(String s) {
        System.out.println(s);
    }
}

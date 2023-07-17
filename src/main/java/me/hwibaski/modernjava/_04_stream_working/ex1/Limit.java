package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Limit {
    public static void main(String[] args) {
        println("----------------------filter & limit----------------------------");
        println("조건에 만족하는 요소가 limit의 인수만큼 충족되면 즉시 결과를 반환한다.");
        println("** 소스가 정렬되어 있지 않다면 limit의  결과도 정렬되지 않은 상태로 반환된다.");
        // 2, 4, 6
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 == 0)
                .limit(3)
                .forEach(System.out::println);

    }

    static void println(String s) {
        System.out.println(s);
    }
}

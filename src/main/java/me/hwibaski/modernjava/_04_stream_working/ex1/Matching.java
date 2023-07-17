package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Matching {
    public static void main(String[] args) {
        System.out.println("----------------------anyMatch----------------------------");
        System.out.println("요소 중 2보다 큰 요소가 하나라도 있으면 true");

        boolean isThereAnyValueGreaterThanTwo = Stream.of(1, 1, 1, 2, 2, 2, 3, 3, 3)
                .anyMatch(n -> n > 2);

        System.out.println(isThereAnyValueGreaterThanTwo);

        System.out.println("----------------------allMatch----------------------------");
        System.out.println("모든 요소가 2보다 크다면 true");

        boolean isAllValueGreaterThanTwo = Stream.of(1, 1, 1, 2, 2, 2, 3, 3, 3)
                .allMatch(n -> n > 2);

        System.out.println(isAllValueGreaterThanTwo);

        System.out.println("----------------------noneMatch----------------------------");
        System.out.println("모든 요소가 2보다 크지 않다면 true");

        boolean isThereNoValueGreaterThanTwo = Stream.of(1, 1, 1, 2, 2, 2, 3, 3, 3)
                .noneMatch(n -> n > 2);

        System.out.println(isThereNoValueGreaterThanTwo);
    }

}

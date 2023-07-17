package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.stream.Stream;

public class Find {
    public static void main(String[] args) {
        System.out.println("----------------------findAny----------------------------");
        System.out.println("조건에 부합하는 임으의 요소를 찾는 즉시 종료, Optional을 리턴");
        System.out.println("병렬처리하지 않을 때는 findFirst와 같이 동작하지만 병렬 처리시 꼭 첫 번째 요소를 리턴한다고 볼 수 없다.");

        // 2
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 == 0)
                .findAny()
                .ifPresent(System.out::println);


        System.out.println("----------------------findFirst----------------------------");
        System.out.println("조건에 부합하는 첫번째 요소 리턴, Optional을 리턴");

        // 2
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .filter(n -> n % 2 == 0)
                .findFirst()
                .ifPresent(System.out::println);
    }
}

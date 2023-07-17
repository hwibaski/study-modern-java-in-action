package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.Arrays;
import java.util.stream.Stream;

public class Mapping {
    public static void main(String[] args) {
        println("----------------------map----------------------------");

        // 11, 21, 31, 41, 51, 61, 71, 81, 91
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .map(n -> n * 10)
                .map(n -> n + 1)
                .forEach(System.out::println);

        println("----------------------Arrays.stream()----------------------------");
        println("배열을 받아서 스트림으로 변환");

        String[] arrayOfWords = {"Hello", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);

        println("----------------------flatMap----------------------------");
        // H, e, l, o, W, r, d
        Arrays.stream(arrayOfWords)
                .map(word -> word.split(""))
//                .flatMap(list -> Arrays.stream(list))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);

    }

    static void println(String s) {
        System.out.println(s);
    }
}

package me.hwibaski.modernjava._02_lambdaexpression.ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<String> listOfString = List.of("a", "b", "", "c", "d", "");
        // Predicate, 비어 있지 않은 문자열만 필터링
        filter(listOfString, (String s) -> !s.isEmpty());

        // Consumer, 리스트 순회
        forEach(listOfString, (String s) -> System.out.println(s));
        forEach(listOfString, s -> System.out.println(s));

        // Map, T 타입을 이용해서 R 타입의 결과물 리턴
        map(listOfString, (String s) -> s.length());
        Predicate<String> p = s -> listOfString.add("abc");
        Consumer<String> b = s -> listOfString.add("abc");
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();

        for (T t : list) {
            if (p.test(t)) {
                result.add(t);
            }
        }

        return result;
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }

        return result;
    }
}

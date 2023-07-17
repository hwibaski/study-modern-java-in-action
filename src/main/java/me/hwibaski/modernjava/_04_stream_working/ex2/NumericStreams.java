package me.hwibaski.modernjava._04_stream_working.ex2;

import me.hwibaski.modernjava._03_stream_intro.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NumericStreams {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT),
                new Dish("beef", false, 700, Dish.Type.MEAT),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", false, 530, Dish.Type.OTHER),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER),
                new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("salmon", false, 450, Dish.Type.FISH)
        );


        // 스트림의 총합, 숫자관련 메서드를 제공해준다.
        // sum()은 스트림이 비어있으면 기본값 0을 반환한다.
        // max, min, average도 지원
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();

        // 숫자 스트림으로 매핑
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);

        // 다시 객체 스트림으로 복원
        Stream<Integer> boxed = intStream.boxed();

        // OptionalInt
        // 최댓값에서는 기본값 0이 스트림이 비어서 0인지, 아니면 정말 최댓값이 0인지 구분하기 힘들다.
        // 따라서 OptionalInt를 제공해준다.
        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();

        // 값이 없을 때 기본 최댓값을 명시적을 지정
        int max = maxCalories.orElse(1);

        // 1 ~ 99, 두 번째 인자 값을 결과 값에 포함시키지 않는다.
        IntStream evenNumbers = IntStream.range(1, 100).filter(n -> n % 2 == 0);

        // 1 ~ 100, 두 번째 인자 값을 결과 값에 포함시킨다.
        IntStream evenNumbers2 = IntStream.rangeClosed(1, 100).filter(n -> n % 2 == 0);

        // 값으로 스트림 만들기
//        Stream.of();
//        Stream.empty();
        String homeValue = System.getProperty("home");
        Stream<String> homeValueStream = homeValue == null ? Stream.empty() : Stream.of(homeValue);
        Stream.ofNullable(homeValue); // 위의 코드를 아래와 같이 간단하게 작성 가능

        // flatMap과 아래의 패턴으로 사용가능
        Stream<String> values = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 배열로 스트림 만들기
        int[] number = {2, 3, 5};
        int sum1 = Arrays.stream(number).sum();

        // 무한 스트림

        // 0을 시작으로 2씩 더해서 10개 출력
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // 0을 시작으로 4씩 더해서 20보다 작은 수 출력
        Stream.iterate(0, n -> n < 20, n -> n + 4)
                .forEach(System.out::println);
    }
}

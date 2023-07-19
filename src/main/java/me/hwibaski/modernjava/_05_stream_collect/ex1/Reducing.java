package me.hwibaski.modernjava._05_stream_collect.ex1;

import me.hwibaski.modernjava._03_stream_intro.Dish;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

public class Reducing {
    public static void main(String[] args) {
        /*
        컬렉터로 스트림의 항목을 컬렉션으로 재구성할 수 있다.
        컬렉터로 스트림의 모든 항목을 하나의 결과로 합칠 수 있다.
         */

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

        // list의 size구하면 되지만 단순 사용 예시이므로 참고
        long howManyDishes = menu.stream().collect(Collectors.counting());
        long howManyDishesV2 = menu.stream().count();

        // ====================
        // 스트림에서 최댓값과 최솟값 검색
        // ====================

        Comparator<Dish> dishCalroiesComparator = Comparator.comparingInt(Dish::getCalories);

        // 가장 칼로리가 큰 Dish 객체 반환
        // 스트림이 비어있다면 결과물이 없을 수도 있으므로 Optional 리턴
        Optional<Dish> mostCaloriesDish = menu.stream().collect(maxBy(dishCalroiesComparator));

        // ====================
        // 요약 연산
        // ====================

        // 스트림의 모든 dish의 칼로리의 합
        int totalCalories = menu.stream().collect(summingInt(Dish::getCalories));

        // averagingInt, averagingLong, averagingDouble 등으로 다양한 형식으로 이루어진 숫자 집합의 평균을 계산할 수 있다.
        double avgCalories = menu.stream().collect(averagingDouble(Dish::getCalories));

        // 개수, 합계, 최소, 최대, 평균 값을 한 번에 구해주는 연산 제공
        // summarizingLong, summarizingDouble도 제공됨
        IntSummaryStatistics menuStatistics = menu.stream().collect(summarizingInt(Dish::getCalories));
        System.out.println(menuStatistics); // IntSummaryStatistics{count=9, sum=4200, min=120, average=466.666667, max=800}

        System.out.println("---------------------------------------------------------------------");

        // ====================
        // 문자열 연결
        // ====================

        String shortMenu = menu.stream().map(Dish::getName).collect(joining());
        System.out.println(shortMenu); // porkbeefchickenfrench friesriceseason fruitpizzaprawnssalmon

        String shortMenuV2 = menu.stream().map(Dish::getName).collect(joining(", "));
        System.out.println(shortMenuV2); // pork, beef, chicken, french fries, rice, season fruit, pizza, prawns, salmon

        // ====================
        // 범용 리듀싱
        // ====================

        /*
        범용 리듀싱은 앞에서 봤던 예제들을 Collectors.reducing으로 구현할 수 있다.
        하지만 프로그래머의 편의성과 가독성을 위해 앞서 사용된 특화된 컬렉터들을 사용했다.
         */

        /*
        reducing(초기값, Function (mapper역할), BinaryOperator(수행할 연산))
        mapper는 컬렉션을 변환하기 위핸 매퍼가 필요하다.
         */
        int totalCaloriesReduce = menu.stream().collect(reducing(0, Dish::getCalories, (a, b) -> a + b));

        /*
        reducing으로 칼로리가 가장 높은 dish를 찾을 수도 있다.
        첫 번째 요소를 시작 요소로 다음 요소가 자신의 칼로리보다 낮을 경우 자기 자신을 다시 반환하는 방식
        아래의 reducing은 초기값이 설정되어 있지 않다. 따라서 스트림이 비어있을 때 결과값이 없을 수 있기 때문에 Optional 리턴
         */
        Optional<Dish> mostCalorieDish = menu.stream().collect(reducing(
                (d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2));

        // ====================
        // 같은 연산도 다양한 방식
        // ====================

        int totalCaloriesV2 = menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        int totalCaloriesV3 = menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        int totalCaloriesV4 = menu.stream().mapToInt(Dish::getCalories).sum();

        /*
            스트림 인터페이스에서 직접 제공하는 메서드를 이용하는 것에 비해 컬렉터를 이용하는 코드가 더 복잡하다.
            대신 재사용성과 커스터마이징 가능성을 제공하는 높은 수준의 추상화화 일반화를 얻을 수 있다.
         */


    }
}

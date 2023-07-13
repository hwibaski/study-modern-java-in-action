package me.hwibaski.modernjava._01_behaviorparameterization;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = List.of(new Apple(Color.GREEN, 100), new Apple(Color.RED, 150));

        filterGreenApplesV1(apples);

        filterApplesByColor(apples, Color.RED);

        filterApples(apples, Color.GREEN, 0, true);
        filterApples(apples, null, 150, false);

        /**
         * 아래의 방법도 좋지만, 클래스가 너무 많이 생겨난다.
         * 또한 중요한 것은 test() 메서드 한 줄인데 그 외의 부가적인 코드가 많이 생성된다.
         */
        filterApplesV2(apples, new AppleGreenColorPredicate());
        filterApplesV2(apples, new AppleHeavyWeightPredicate());
        filterApplesV2(apples, new AppleRedAndHeavyPredicate());

        /**
         * 익명 클래스 사용
         * 여전히 핵심적인 로직은 잘 보이지 않고, 익명 클래스의 선언부가 코드의 너무 많은 부분을 차지한다.
         * 코드의 장황함은 나쁜 특성이다
         * 유지보수하는 데 시간이 오래 걸리게 하고, 코드를 읽기 어렵게 한다.
         */
        filterApplesV2(apples, new ApplePredicate() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == Color.RED;
            }
        });

        /**
         * 람다 표현식 사용
         */
        filterApplesV2(apples, (Apple apple) -> Color.RED == apple.getColor());


        /**
         * 바나나의 색은 신경쓰지 말자..
         */
        List<Banana> bananas = List.of(new Banana(Color.GREEN, 100), new Banana(Color.RED, 150));
        filter(bananas, (Banana banana) -> banana.getWeight() > 125);
        filter(apples, (Apple apple) -> apple.getColor() == Color.GREEN);
    }

    /**
     * 만약 Color.RED인 사과를 필터링하려면 어떻게 해야할까?
     * -> 색을 파라미터화 하면 되짆아!
     */
    public static List<Apple> filterGreenApplesV1(List<Apple> inventory) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (Color.GREEN.equals(apple.getColor())) {  // <- 코드가 너무 의존적이다.
                result.add(apple);
            }
        }

        return result;
    }

    /**
     * 꽤나 잘 해결 되었지만 아래와 같은 요구사항에는 어떻게 대처할 수 있을까?
     * 무게도 필터링의 기준에 넣고 싶어...
     * 아래의 메서드와 비슷하게 생긴 메서드를 복붙하고 Color 자리에 int weight 파라미터를 한 개 받을 것이다.
     * 괜찮은 방법이지만, DRY (Don't repeat yourself) 원칙을 어기는 것이다.
     */
    public static List<Apple> filterApplesByColor(List<Apple> inventory, Color color) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (apple.getColor() == color) {
                result.add(apple);
            }
        }

        return result;
    }

    /**
     * 또한 색과 무게를 하나의 메서드로 합치고 어떠한 기준으로 필터링을 할 것인지 flag 변수를 하나 받을 수도 있다.
     * e.g. String flag , flag가 "color"이면 Color color 매개변수를 사용하는 식이다.
     * 이런 식의 개발은 절대 절대 하면 안된다.
     * why?
     * 1. 유지보수가 용이하지 않다.
     * 2. 함수가 한 가지 이상의 일을 할 수 있게 된다.
     */

    public static List<Apple> filterApples(List<Apple> inventory, Color color, int weight, boolean flag) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if ((flag && apple.getColor() == color) || (!flag && apple.getWeight() > weight)) {
                result.add(apple);
            }
        }

        return result;
    }

    /**
     * 인퍼페이스를 이용해서 사과를 판별할 공통메서드를 정의한다.
     * 그리고 판별할 로직들을 각각 ApplePredicate를 구현한 클래스들의 test() 메서드에 구현한다.
     * 사용하는 클라이언트쪽에서 필터링할 기준을 전달해서 사용한다.
     */
    public static List<Apple> filterApplesV2(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();

        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }

    /**
     * 이제 Apple 뿐만이 아니라 다른 List도 filter할 수 있도록 유연해졌다.
     */
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if (p.test(e)) {
                result.add(e);
            }
        }

        return result;
    }

}

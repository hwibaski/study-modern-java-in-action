package me.hwibaski.modernjava._02_lambdaexpression.ex3;


import java.util.List;

import static java.util.Comparator.comparing;

public class Main {
    public static void main(String[] args) {
        List<Apple> apples = List.of(new Apple(100, Color.RED, "korea"), new Apple(150, Color.GREEN, "arab"));
        apples.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));
        apples.sort((a1, a2) -> a1.getWeight() - a2.getWeight());
        apples.sort(comparing(Apple::getWeight));

        // comparing이 아래의 코드를 리턴함
        //        return (Comparator<T> & Serializable)
        //                (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));

        // 무게를 내림 차순으로 정렬
        apples.sort(comparing(Apple::getWeight).reversed());

        // 무게를 내림차 순 정렬 후, 사과의 무게가 같으면 국가 별로 정렬
        apples.sort(comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry));
    }

}

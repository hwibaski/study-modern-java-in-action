package me.hwibaski.modernjava._01_behaviorparameterization.ex2;

import lombok.extern.slf4j.Slf4j;

import java.util.Comparator;
import java.util.List;

@Slf4j
public class Main {
    public static void main(String[] args) {
        // List.of()는 ImmutableCollections.ListN<>()을 리턴하므로 sort를 바로 사용할 수 없다.
        List<Apple> apples = new java.util.ArrayList<>(List.of(new Apple(100), new Apple(150), new Apple(20)));
        apples.sort(new Comparator<Apple>() {
            /**
             *  int overflow만 조심한다면 a1.getWeight() - a2.getWeight()를 리턴해도 된다.
             */
            @Override
            public int compare(Apple a1, Apple a2) {

                if (a1.getWeight() > a2.getWeight()) {   // 자기자신의 weight가 a2의 weight보다 크다면 양수
                    return 1;
                } else if (a1.getWeight() == a2.getWeight()) { // 자기 자신의 weight와 a2의 weight가 같다면 0
                    return 0;
                } else {  // 자기 자신의 weight가 o의 weight보다 작다면 음수
                    return -1;
                }
            }
        });

        // 위의 new Comparator 대신 아래와 같이 사용 가능
        apples.sort((Apple a1, Apple a2) -> a1.getWeight() - a2.getWeight());

        apples.forEach(apple -> System.out.println(apple.getWeight()));
    }
}

package me.hwibaski.modernjava._04_stream_working.ex1;

import java.util.Optional;
import java.util.stream.Stream;

public class Reducing {
    public static void main(String[] args) {
        System.out.println("----------------------reduce / 요소의 합----------------------------");

        // 45
        Integer sum = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
//                .reduce(0, (a, b) -> a + b)
                .reduce(0, Integer::sum);

        System.out.println("----------------------reduce / 요소의 합 / 초기값 미설정----------------------------");
        System.out.println("초기값 없을 경우 제대로된 값을 리턴하지 못할 수도 있기 때문에 Optional이 반환됨");

        Optional<Integer> sum2 = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(Integer::sum);

        System.out.println("----------------------reduce / 최댓값, 최솟값---------------------------");

        // (x, y) -> x < y ? x : y 을 인수로 넣어도 되지만, 메서드 참조가 더 가독성이 높다.
        Optional<Integer> min = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(Integer::min);

        Optional<Integer> max = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9)
                .reduce(Integer::max);
    }
}

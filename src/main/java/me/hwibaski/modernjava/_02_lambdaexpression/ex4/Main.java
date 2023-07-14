package me.hwibaski.modernjava._02_lambdaexpression.ex4;

import me.hwibaski.modernjava._02_lambdaexpression.ex3.Apple;
import me.hwibaski.modernjava._02_lambdaexpression.ex3.Color;

import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        /**
         * predicate 조합
         */
        Predicate<Apple> isRedApple = (apple) -> apple.getColor() == Color.RED;

        Predicate<Apple> notRedApple = isRedApple.negate();

        Predicate<Apple> redAndHeavyApple = isRedApple.and(apple -> apple.getWeight() > 150);

        // (isRed && isHeavy) || isGreen
        Predicate<Apple> redAndHeavyAppleOrGreen = isRedApple
                .and(apple -> apple.getWeight() > 150)
                .or(apple -> apple.getColor() == Color.GREEN);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;
        Function<Integer, Integer> h = f.andThen(g); // g(f(x));
        int result = h.apply(1); // 4,  (x + 1) * 2


        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 2;
        Function<Integer, Integer> h1 = f1.compose(g1); // f1(g1(x));
        int result2 = h1.apply(1); // 3 , (x * 2) + 1
    }
}

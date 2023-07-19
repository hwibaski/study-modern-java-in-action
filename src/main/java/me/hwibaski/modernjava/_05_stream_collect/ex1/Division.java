package me.hwibaski.modernjava._05_stream_collect.ex1;

import me.hwibaski.modernjava._03_stream_intro.Dish;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Division {
    public static void main(String[] args) {
        List<Dish> menu = asList(
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

        Map<Boolean, List<Dish>> partitionedMenu = menu.stream().collect(partitioningBy(Dish::isVegetarian));

        // {false=[pork, beef, chicken, french fries, prawns, salmon], true=[rice, season fruit, pizza]}
        System.out.println(partitionedMenu);

        List<Dish> vegetarianDishes = partitionedMenu.get(true);

        Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menu.stream().collect(partitioningBy(Dish::isVegetarian,
                groupingBy(Dish::getType)
        ));

        // {false={FISH=[prawns, salmon], OTHER=[french fries], MEAT=[pork, beef, chicken]}, true={OTHER=[rice, season fruit, pizza]}}
        System.out.println(vegetarianDishesByType);

        Map<Boolean, Dish> mostCaloricPartitionedByVegetarian = menu.stream().collect(
                partitioningBy(Dish::isVegetarian,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)),
                                Optional::get))
        );

        // {false=pork, true=pizza}, 채식이 아닌 요리, 채식인 요리 각각의 그룹에서 가장 칼로리가 높은 요리의 이름
        System.out.println(mostCaloricPartitionedByVegetarian);
    }
}

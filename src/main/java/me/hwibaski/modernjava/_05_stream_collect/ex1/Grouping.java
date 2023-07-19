package me.hwibaski.modernjava._05_stream_collect.ex1;

import me.hwibaski.modernjava._03_stream_intro.Dish;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.*;

public class Grouping {
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

        Map<Dish.Type, List<Dish>> dishesByType = menu.stream().collect(groupingBy(Dish::getType));
        // dishesByType = {OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}
        System.out.println("dishesByType = " + dishesByType);

        Map<CaloricLevel, List<Dish>> dishesByCaloricLevel = menu.stream().collect(
                groupingBy(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                })
        );

        // {NORMAL=[beef, french fries, pizza, salmon], FAT=[pork], DIET=[chicken, rice, season fruit, prawns]}
        System.out.println(dishesByCaloricLevel);

        Map<Dish.Type, List<Dish>> caloricDishesByType = menu.stream().collect(groupingBy(Dish::getType,
                filtering(dish -> dish.getCalories() > 500, toList())
        ));

        // {OTHER=[french fries, pizza], MEAT=[pork, beef], FISH=[]}
        System.out.println(caloricDishesByType);

        Map<Dish.Type, List<String>> dishNamesByType = menu.stream().collect(groupingBy(Dish::getType,
                mapping(Dish::getName, toList())));

        // {OTHER=[french fries, rice, season fruit, pizza], MEAT=[pork, beef, chicken], FISH=[prawns, salmon]}
        System.out.println(dishNamesByType);


        Map<String, List<String>> dishTags = new HashMap<>();
        dishTags.put("pork", asList("greasy", "salty"));
        dishTags.put("beef", asList("salty", "roasted"));
        dishTags.put("chicken", asList("fried", "crisp"));
        dishTags.put("french fries", asList("greasy", "fried"));
        dishTags.put("rice", asList("light", "natural"));
        dishTags.put("season fruit", asList("fresh", "natural"));
        dishTags.put("pizza", asList("tasty", "salty"));
        dishTags.put("prawns", asList("tasty", "roasted"));
        dishTags.put("salmon", asList("delicious", "fresh"));

        Map<Dish.Type, Set<String>> dishNamesByType2 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        flatMapping(dish -> dishTags.get(dish.getName()).stream(),
                                toSet())));

        // {OTHER=[salty, greasy, natural, light, tasty, fresh, fried], MEAT=[salty, greasy, roasted, fried, crisp], FISH=[roasted, tasty, fresh, delicious]}
        System.out.println(dishNamesByType2);

        Map<Dish.Type, Map<CaloricLevel, List<Dish>>> dishesByTypeCaloricLevel = menu.stream().collect(
                groupingBy(Dish::getType,
                        groupingBy(dish -> {
                            if (dish.getCalories() <= 400) {
                                return CaloricLevel.DIET;
                            } else if (dish.getCalories() <= 700) {
                                return CaloricLevel.NORMAL;
                            } else {
                                return CaloricLevel.FAT;
                            }
                        })
                )
        );

        // {OTHER={NORMAL=[french fries, pizza], DIET=[rice, season fruit]}, MEAT={NORMAL=[beef], FAT=[pork], DIET=[chicken]}, FISH={NORMAL=[salmon], DIET=[prawns]}}
        System.out.println(dishesByTypeCaloricLevel);

        Map<Dish.Type, Long> typesCount = menu.stream().collect(groupingBy(Dish::getType, counting()));

        // {OTHER=4, MEAT=3, FISH=2}
        System.out.println(typesCount);

        Map<Dish.Type, Optional<Dish>> mostCaloricByType = menu.stream().collect(groupingBy(Dish::getType,
                maxBy(Comparator.comparingInt(Dish::getCalories))));

        // {OTHER=Optional[pizza], MEAT=Optional[pork], FISH=Optional[salmon]}
        System.out.println(mostCaloricByType);

        Map<Dish.Type, Dish> mostCaloricByTypeV2 = menu.stream()
                .collect(groupingBy(Dish::getType,
                        collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get
                        )));

        // {OTHER=pizza, MEAT=pork, FISH=salmon}
        System.out.println(mostCaloricByTypeV2);

        Map<Dish.Type, Integer> totalCaloriesByType = menu.stream().collect(groupingBy(Dish::getType,
                summingInt(Dish::getCalories)));

        // {OTHER=1550, MEAT=1900, FISH=750}
        System.out.println(totalCaloriesByType);

        Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream().collect(groupingBy(Dish::getType, mapping(dish -> {
            if (dish.getCalories() <= 400) {
                return CaloricLevel.DIET;
            } else if (dish.getCalories() <= 700) {
                return CaloricLevel.NORMAL;
            } else {
                return CaloricLevel.FAT;
            }
        }, toSet())));

        // {OTHER=[NORMAL, DIET], MEAT=[NORMAL, FAT, DIET], FISH=[NORMAL, DIET]}
        System.out.println(caloricLevelsByType);

        Map<Dish.Type, HashSet<CaloricLevel>> caloricLevelsByTypeV2 = menu.stream().collect(
                groupingBy(Dish::getType, mapping(dish -> {
                    if (dish.getCalories() <= 400) {
                        return CaloricLevel.DIET;
                    } else if (dish.getCalories() <= 700) {
                        return CaloricLevel.NORMAL;
                    } else {
                        return CaloricLevel.FAT;
                    }
                }, toCollection(HashSet::new)))
        );

        // {OTHER=[NORMAL, DIET], MEAT=[NORMAL, FAT, DIET], FISH=[NORMAL, DIET]}
        System.out.println(caloricLevelsByTypeV2);

    }

    public enum CaloricLevel {DIET, NORMAL, FAT}
}

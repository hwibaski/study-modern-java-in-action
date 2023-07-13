package me.hwibaski.modernjava._01_behaviorparameterization;

public class AppleRedAndHeavyPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150 && apple.getColor() == Color.RED;
    }
}

package me.hwibaski.modernjava._01_behaviorparameterization;

public class AppleGreenColorPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getColor() == Color.GREEN;
    }
}

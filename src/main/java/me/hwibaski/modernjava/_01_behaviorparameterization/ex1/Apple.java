package me.hwibaski.modernjava._01_behaviorparameterization.ex1;

public class Apple {
    private final Color color;
    private final int weight;

    public Apple(Color color, int weight) {
        this.color = color;
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public int getWeight() {
        return weight;
    }
}

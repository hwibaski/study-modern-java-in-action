package me.hwibaski.modernjava._01_behaviorparameterization;

public class Banana {
    private final Color color;
    private final int weight;

    public Banana(Color color, int weight) {
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

package me.hwibaski.modernjava._02_lambdaexpression.ex3;

public class Apple {
    private final Color color;
    private final Integer weight;
    private final String country;

    public Apple(int weight, Color color, String country) {
        this.weight = weight;
        this.color = color;
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public Integer getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }
}

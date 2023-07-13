package me.hwibaski.modernjava._01_behaviorparameterization.ex3;

public class Main {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello, world");
            }
        });

        Thread thread2 = new Thread(() -> System.out.println("Hello, World"));
    }
}

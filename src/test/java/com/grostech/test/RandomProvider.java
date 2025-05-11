package com.grostech.test;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class RandomProvider {

    public static int randomStrictlyPositiveInt() {
        return Math.abs(RandomProvider.randomInt()) + 1;
    }

    public static int randomInt() {
        return new Random().nextInt();
    }
}

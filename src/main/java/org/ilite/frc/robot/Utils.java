package org.ilite.frc.robot;

public class Utils {

    public static double clamp(double value, double minValue, double maxValue) {
        value = (value > maxValue) ? maxValue : value;
        value = (value < minValue) ? minValue : value;
        return value;
    }

    public static double clamp(double value, double magnitude) {
        return clamp(value, -magnitude, magnitude);
    }

}

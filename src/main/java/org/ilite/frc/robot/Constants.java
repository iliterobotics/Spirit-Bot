package org.ilite.frc.robot;

public class Constants {

    /**
     * Relays
     */
    public static final int RELAY_PORT_SHOOTER = 1;
    public static final int RELAY_PORT_DUMP = 2;
    public static final int RELAY_PORT_LED = 3;
    public static final int RELAY_PORT_HORN = 0;

    /**
     * PWM Outputs
     */
    public static final int TALON_PWM_PORT_ELEVATE = 0;
    public static final int TALON_PWM_PORT_LEFT_DRIVETRAIN = 2;
    public static final int TALON_PWM_PORT_RIGHT_DRIVETRAIN = 1;

    /**
     * Sensors
     */
    public static final int ANALOG_PORT_ELEVATION = 1;
    public static final int ANALOG_PORT_PRESSURE_SENSOR = 0;
    public static final int DIO_PORT_ELEVATION_LIMIT_SWITCH = 0;

    /**
     * Shooter
     */
    public static final double PSI_THRESHOLD = 60;
    public static final double ELEVATION_SPEED_DOWN = 0.15;
    public static final double ELEVATION_SPEED_UP = -0.2;
    public static long SHOOTER_RELAY_DURATION = 450;

    /**
     * Horn
     */
    public static long HORN_RELAY_DURATION = 400;//duration for relay to be on.
}

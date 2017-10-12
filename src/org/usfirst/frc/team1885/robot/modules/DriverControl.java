package org.usfirst.frc.team1885.robot.modules;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;

public class DriverControl implements Module{

	public static final double JOYSTICK_DEADZONE = 0.05;
	public static final double TRIGGER_DEADZONE = 0.5;
	public static final int GAMEPAD_LEFT_X = 0;
	public static final int GAMEPAD_LEFT_Y = 1;
	public static final int GAMEPAD_LEFT_TRIGGER = 2;
	public static final int GAMEPAD_RIGHT_TRIGGER = 3;
	public static final int GAMEPAD_RIGHT_X = 4;
	public static final int GAMEPAD_RIGHT_Y = 5;
	public static final int GAMEPAD_A_BUTTON = 1;
	public static final int GAMEPAD_Y_BUTTON = 4;
	
	
	public final int CONTROLLER_ID = 0;
	
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Joystick gamepad;
	
	public DriverControl(DriveTrain drivetrain, Shooter shooter) {
		this.drivetrain = drivetrain;
		this.shooter = shooter;
	}
	
	public void init() {
		gamepad = new Joystick(CONTROLLER_ID);
	}
	
	public boolean update() {
		
		if(isOutsideDeadzone(GAMEPAD_RIGHT_TRIGGER, TRIGGER_DEADZONE) && isOutsideDeadzone(GAMEPAD_RIGHT_TRIGGER, TRIGGER_DEADZONE) && gamepad.getRawButton(GAMEPAD_A_BUTTON)) {
			if(shooter.shoot()) {
				DriverStation.reportError("Firing.", false);
			} else {
				DriverStation.reportError("Not Firing.", false);
			}
		}
		
		double throttle = gamepad.getRawAxis(GAMEPAD_LEFT_Y);
		double turn = gamepad.getRawAxis(GAMEPAD_RIGHT_X);
		double left = throttle - turn;
		double right = throttle + turn;
		drivetrain.setSpeeds(left, right);
		
		return false;
	}
	
	private boolean isOutsideDeadzone(int axis, double deadzone) {
		return (Math.abs(gamepad.getRawAxis(axis)) > deadzone) ? true : false;
	}
	
}

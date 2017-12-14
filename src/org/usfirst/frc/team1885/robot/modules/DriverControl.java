package org.usfirst.frc.team1885.robot.modules;

import org.usfirst.frc.team1885.robot.Constants;
import org.usfirst.frc.team1885.robot.sensors.PressureSensor;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

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
	public static final int GAMEPAD_B_BUTTON = 2;
	public static final int GAMEPAD_Y_BUTTON = 4;
	public static final int GAMEPAD_DPAD_UP = 0;
	public static final int GAMEPAD_DPAD_DOWN = 0;
	
	public final int CONTROLLER_ID = 0;
	
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Joystick gamepad;
	private Relay hornRelay; 
	private PressureSensor pressureSensor;
	private double startTime = System.currentTimeMillis();
	private double duration = 1000;//duration for relay to be on.

	
	public DriverControl(DriveTrain drivetrain, Shooter shooter, PressureSensor pressureSensor) {
		this.drivetrain = drivetrain;
		this.shooter = shooter;
		this.pressureSensor = pressureSensor;
		gamepad = new Joystick(CONTROLLER_ID);
		hornRelay = new Relay(Constants.HORN_RELAY);
	
	}
	
	public DriverControl(DriveTrain drivetrain) {
		this.drivetrain = drivetrain;
	}
	
	public void init() {
		
		
	}
	public void blowHorn() {

			hornRelay.set(Relay.Value.kOn);// On state.
			double startTime = System.currentTimeMillis();
			double duration = 1000;//duration for relay to be on.
			while (System.currentTimeMillis() - startTime < duration);
			hornRelay.set(Relay.Value.kOff);// Off state.
			
	}
	
	public void hornRelayOff()
	{
		hornRelay.set(Relay.Value.kOff);
	}
	public boolean update() {

		double throttle = gamepad.getRawAxis(GAMEPAD_LEFT_Y);
		double turn = gamepad.getRawAxis(GAMEPAD_RIGHT_X);
		double left = throttle - turn;
		double right = throttle + turn;
		drivetrain.setSpeeds(left, right);
		
		if(gamepad.getRawAxis(GAMEPAD_RIGHT_TRIGGER)>0.5)
		{
			
			if(!(System.currentTimeMillis() - startTime > duration))
			{
				hornRelay.set(Relay.Value.kOn);
			}
			else
			{
				hornRelay.set(Relay.Value.kOff);
				startTime = System.currentTimeMillis();
			}
			shooter.shoot();
			
		}
		else {
			hornRelayOff();
			shooter.shootRelayOff();
		}
		
		if(gamepad.getRawAxis(GAMEPAD_LEFT_TRIGGER) > 0.5)
		{
			shooter.dump();
		}
		else {
			shooter.dumpRelayOff();
		}
		if(gamepad.getRawButton(GAMEPAD_Y_BUTTON))
		{
			shooter.setOutput(1); //Move shooter up.
		}
		else if(gamepad.getRawButton(GAMEPAD_A_BUTTON))
		{
			shooter.setOutput(-1);//Move shooter down.
		}
		else
		{
			shooter.setOutput(0);
		}
		
		
		
		return false;
	}
	
	private boolean isOutsideDeadzone(int axis, double deadzone) {
		return (Math.abs(gamepad.getRawAxis(axis)) > deadzone) ? true : false;
	}
	
}

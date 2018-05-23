package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Joystick;
import org.ilite.frc.robot.Constants;
import org.ilite.frc.robot.sensors.PressureSensor;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;

public class DriverControl implements IModule {

	private DriveTrain drivetrain;
	private Shooter shooter;
	private Joystick gamepad;
	private Horn horn;
	private PressureSensor pressureSensor;
	private long startTime = System.currentTimeMillis();
	private boolean hornSequenceInit = false;
	private long endOfWarningTime = -1;
	private long endOfShotTime = -1;
	
	public DriverControl(DriveTrain drivetrain, Shooter shooter, PressureSensor pressureSensor, Horn horn) {
		this.drivetrain = drivetrain;
		this.shooter = shooter;
		this.horn = horn;
		this.pressureSensor = pressureSensor;
		this.gamepad = new Joystick(DriverInputMap.CONTROLLER_ID);
	
	}
	
	public DriverControl(DriveTrain drivetrain) {
		this.drivetrain = drivetrain;
	}
	
	public void init() {
		
		
	}
	/*
	public void blowHorn() {

			hornRelay.set(Relay.Value.kOn);// On state.
			double startTime = System.currentTimeMillis();
			double duration = 1000;//duration for relay to be on.
			System.currentTimeMillis() - startTime < duration);
			hornRelay.set(Relay.Value.kOff);// Off state.

	}
	*/
	public boolean update() {

		double throttle = gamepad.getRawAxis(DriverInputMap.GAMEPAD_LEFT_Y);
		double turn = gamepad.getRawAxis(DriverInputMap.GAMEPAD_RIGHT_X) / 2;
		double left = throttle - turn;
		double right = throttle + turn;
		drivetrain.setSpeeds(left, -right);
		
		// Fire Trigger
		if(gamepad.getRawAxis(DriverInputMap.GAMEPAD_RIGHT_TRIGGER) > 0.5)
		{
			if(!hornSequenceInit) {
				
				hornSequenceInit = true;
				startTime = System.currentTimeMillis();
				endOfWarningTime = startTime + Constants.HORN_RELAY_DURATION;
				endOfShotTime = endOfWarningTime + Constants.SHOOTER_RELAY_DURATION;
				horn.sound(Constants.HORN_RELAY_DURATION);
			}

			long now = System.currentTimeMillis();

			if(!horn.isSounding() && now < endOfShotTime) {
				shooter.shoot();
			} else if (now > endOfShotTime)
			{
				shooter.shootRelayOff();
			}
		} else {
			horn.turnOff();
			shooter.shootRelayOff();
			hornSequenceInit = false;
		}

		// Horn Button
		if(gamepad.getRawButton(DriverInputMap.GAMEPAD_B_BUTTON))
		{
			horn.turnOn();
		} else {
			horn.turnOff();
		}

		// Dump Trigger
		if(gamepad.getRawAxis(DriverInputMap.GAMEPAD_LEFT_TRIGGER) > 0.5)
		{
			shooter.dump();
		} else {
			shooter.dumpRelayOff();
		}

		// Shooter Elevation
		if(gamepad.getRawButton(DriverInputMap.GAMEPAD_Y_BUTTON))
		{
			shooter.setOutput(Constants.ELEVATION_SPEED_UP); //Move shooter up.
		} else if(gamepad.getRawButton(DriverInputMap.GAMEPAD_A_BUTTON))
		{
			shooter.setOutput(Constants.ELEVATION_SPEED_DOWN);//Move shooter down.
		} else
		{
			shooter.setOutput(0);
		}else if(gamepad.getRawButton(GAMEPAD_X_BUTTON))	
		{
			if(!(gamepad.getRawButton(GAMEPAD_X_BUTTON))) {
				
				solenoid.set(!solenoid.get());
			}

		return false;
	}
	
	private boolean isOutsideDeadzone(int axis, double deadzone) {
		return (Math.abs(gamepad.getRawAxis(axis)) > deadzone) ? true : false;
	}
	
}


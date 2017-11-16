package org.usfirst.frc.team1885.robot.modules;

import org.usfirst.frc.team1885.robot.sensors.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

public class Shooter implements Module {
    
	private Talon angleMotor;
	private PressureSensor pressureSensor;
	private Potentiometer pot;
	private Relay shootRelay;
	private Relay dumpRelay;
	private double angle;
	private DigitalInput limitSwitch;
	public static final double PSI_THRESHOLD = 60;
	//public static final double LIMIT_1;// first angle limit
	//public static final double LIMIT_2;// second angle limit

	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.pressureSensor = pressureSensor;
		this.pot = pot;
		angleMotor = new Talon(2);
		this.shootRelay = new Relay(1);
		this.dumpRelay = new Relay(0);
		this.limitSwitch = new DigitalInput(1);


	}

	@Override
	public void init() {
		this.angle = pot.getAngle();
		
	}

	@Override
	public boolean update() {
		this.angle = pot.getAngle();
		return false;
	}

	public boolean dump() {
			dumpRelay.set(Relay.Value.kOn);// On state.
			return true;
		
	}

	public boolean shoot() {
		if (pressureSensor.getPSI() >= PSI_THRESHOLD) {
			shootRelay.set(Relay.Value.kOn);// On state.
			return true;
		} 
		else {
			return false;
		}
	}
	public void shootRelayOff()
	{
		shootRelay.set(Relay.Value.kOff);
	}
	public void dumpRelayOff()
	{
		dumpRelay.set(Relay.Value.kOff);
	}

	public void setOutput(double output) {
		double threshold = 5;
		/*
		if(Math.abs(angle - LIMIT_1) < threshold || Math.abs(angle - LIMIT_2) < threshold)
		{
			angleMotor.set(0);
		}
		*/
		if(!limitSwitch.get()) {
			angleMotor.set(0);
		}
		angleMotor.set(output);
	}
    
}

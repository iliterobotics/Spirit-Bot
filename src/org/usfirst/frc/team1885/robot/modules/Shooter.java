package org.usfirst.frc.team1885.robot.modules;

import org.usfirst.frc.team1885.robot.Constants;
import org.usfirst.frc.team1885.robot.sensors.*;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

public class Shooter implements Module {
    
	private Talon elevateMotor;
	private PressureSensor pressureSensor;
	private Potentiometer pot;
	private Relay shootRelay;
	private Relay dumpRelay;
	private double angle;
	private DigitalInput limitSwitch;
	private Relay ledRelay;
	private long startTime;
	private long currentTime;
	

	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.pressureSensor = pressureSensor;
		this.pot = pot;
		elevateMotor = new Talon(Constants.TALON_PWM_PORT_ELEVATE);
		this.shootRelay = new Relay(Constants.RELAY_PORT_SHOOTER);
		this.dumpRelay = new Relay(Constants.RELAY_PORT_DUMP);
		this.ledRelay =  new Relay(Constants.LED_RELAY);
		this.limitSwitch = new DigitalInput(Constants.DIO_PORT_ELEVATION_LIMIT_SWITCH);


	}

	@Override
	public void init() {
		this.angle = pot.getAngle();
		startTime = System.currentTimeMillis();
		currentTime = System.currentTimeMillis();
	}

	@Override
	public boolean update() {
		this.angle = pot.getAngle();
		blinkLED();
		return false;
		
	}

	public boolean dump() {
			dumpRelay.set(Relay.Value.kOn);// On state.
			return true;
		
	}
	
	public void blinkLED(){
		if(pressureSensor.getPSI() < Constants.PSI_THRESHOLD && currentTime - startTime > 1000)
		{
			if (ledRelay.get().equals(Relay.Value.kOn))
				ledRelay.set(Relay.Value.kOff);
			else
				ledRelay.set(Relay.Value.kOn);
			startTime = currentTime;
			
			
		}
	}

	public boolean shoot() {
		if (pressureSensor.getPSI() >= Constants.PSI_THRESHOLD) {
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
		if( Math.abs(angle - Constants.POT_LIMIT_1) < threshold ||
		 	Math.abs(angle - Constants.POT_LIMIT_2) < threshold)
		{
			elevateMotor.set(0);
		}
		*/
		if(!limitSwitch.get()) {
			elevateMotor.set(0);
		}
		elevateMotor.set(output);
	}
    
}

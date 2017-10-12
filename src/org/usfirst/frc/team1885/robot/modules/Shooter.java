package org.usfirst.frc.team1885.robot.modules;

import org.usfirst.frc.team1885.robot.sensors.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;

public class Shooter implements Module {

	private Talon angleMotor;
	private PressureSensor pressureSensor;
	private Potentiometer pot;
	private Relay shootRelay;
	private Relay dumpRelay;
	private double angle;
	public static final double PSI_THRESHOLD = 60;
	public static final double LIMIT_1;// first angle limit
	public static final double LIMIT_2;// second angle limit

	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.pressureSensor = pressureSensor;
		this.pot = pot;

	}

	@Override
	public void init() {
		this.angle = pot.getAngle();
		angleMotor = new Talon(2);
		this.shootRelay = new Relay(0);
		this.dumpRelay = new Relay(1);

	}

	@Override
	public boolean update() {
		this.angle = pot.getAngle();

		return false;
	}

	public boolean dump() {

		if (pressureSensor.getPSI() <= PSI_THRESHOLD) {

			dumpRelay.set(Relay.Value.kOn);// On state.
			double startTime = System.currentTimeMillis();
			double duration = 1000;//duration for relay to be on.
			while (System.currentTimeMillis() - startTime < duration);
			dumpRelay.set(Relay.Value.kOff);// Off state.
			return true;
		}
		else {
			return false;
		}
	}

	public boolean shoot() {
		if (pressureSensor.getPSI() >= PSI_THRESHOLD) {
			shootRelay.set(Relay.Value.kOn);// On state.
			double startTime = System.currentTimeMillis();
			double duration = 1000;//duration for relay to be on.
			while (System.currentTimeMillis() - startTime < duration);
			shootRelay.set(Relay.Value.kOff);// Off state.
			return true;
		} 
		else {
			return false;
		}
	}

	public void setOutput(double output) {
		double threshold = 5;
		if (Math.abs(angle - LIMIT_1) < threshold || Math.abs(angle - LIMIT_2) < threshold) {
			angleMotor.set(0);// Won't turn motor when the angle is close to limit angles.
		}
		angleMotor.set(output);
	}

}

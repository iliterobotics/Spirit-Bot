package org.usfirst.frc.team1885.robot.modules;
import org.usfirst.frc.team1885.robot.sensors.*;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Talon;
public class Shooter implements Module {
	
	private Talon angleMotor;
	private PressureSensor pressureSensor;
	private Potentiometer pot;
	private Relay relay;
	private double angle;
	
	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.pressureSensor = pressureSensor;
		this.pot = pot;

	}
	@Override
	public void init() {
		this.angle = pot.getAngle();
		angleMotor = new Talon(2);
		
	}

	@Override
	public boolean update() {
		this.angle = pot.getAngle();
		return false;
	}
	
	public boolean dump() {
		
	}
	
	public void setOutput(double output) {
		double limit1, limit2;
		double threshold = 5;
		if(Math.abs(angle - limit1)  < threshold || Math.abs(angle - limit2) < threshold)
		{
			angleMotor.set(0);//Won't turn motor when the angle is close to limit angles.
		}
		angleMotor.set(output);
	}
	
	public Talon getTalon()
	{
		return angleMotor;
	}

}

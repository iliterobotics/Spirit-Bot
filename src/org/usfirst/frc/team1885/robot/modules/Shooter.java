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
	public static final double LIMIT_1;//first angle limit
	public static final double LIMIT_2;//second angle limit
	//Red thigns are mosfet transistors for controlling white dpdts.
	
	public Shooter(PressureSensor pressureSensor, Potentiometer pot) {
		this.pressureSensor = pressureSensor;
		this.pot = pot;
		this.shootRelay = new Relay(0);
		this.dumpRelay = new Relay(1);
		
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
	
	public boolean shoot()
	{
		
	}
	public void setOutput(double output) {
		double threshold = 5;
		if(Math.abs(angle - LIMIT_1)  < threshold || Math.abs(angle - LIMIT_2) < threshold)
		{
			angleMotor.set(0);//Won't turn motor when the angle is close to limit angles.
		}
		angleMotor.set(output);
	}

}

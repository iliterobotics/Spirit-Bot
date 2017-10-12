package org.usfirst.frc.team1885.robot.sensors;
import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic
{
	private static final double SCALE_FACTOR = 0.2; //needs to be tested
	private AnalogInput analogInput;
	private static final int PORT;
	private double voltage;
	
	public Ultrasonic(int port)
	{
		
		analogInput = new AnalogInput(PORT);
	}
	
	
	private boolean update()
	{
		voltage = analogInput.getVoltage();
		return true;
	}
	

	public double getDistance()
	{
		//return voltage * SCALE_FACTOR;
		return 0.0;
	}
	
}

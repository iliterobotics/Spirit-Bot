package org.ilite.frc.robot.sensors;
import edu.wpi.first.wpilibj.AnalogInput;

public class Ultrasonic
{
	private static final double SCALE_FACTOR = 0.2; //needs to be tested
	public double distance;
	private AnalogInput analogInput;
	private static final int PORT= 2;
	private double voltage;
	
	public Ultrasonic(int port)
	{
		analogInput = new AnalogInput(PORT);
	}
	
	public void init()
	{
	}
	
	
	private boolean update()
	{
		voltage = analogInput.getVoltage();
		distance = voltage / SCALE_FACTOR;
		return true;
	}
	

	public double getDistance()
	{
		update();
		return 0.0;
	}
	
}

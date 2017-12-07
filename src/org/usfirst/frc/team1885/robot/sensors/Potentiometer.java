package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;

public class Potentiometer 
{
	private AnalogInput input;
	private int angle;
	private double voltage;
	

	public Potentiometer()
	{
		input = new AnalogInput(Constants.ANALOG_PORT_ELEVATION);
	}
	public void cvtToAngle(double voltage)
	{
		angle = 0;
	}

	public double getAngle() 
	{
		return angle;
	}
	
	public double getVoltage()
	{
		voltage = input.getVoltage();
		return voltage;
	}
	
	public void update()
	{
		System.out.println(getVoltage());
	}
	
}

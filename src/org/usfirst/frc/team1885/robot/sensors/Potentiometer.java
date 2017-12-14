package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.Constants;

import edu.wpi.first.wpilibj.AnalogInput;

public class Potentiometer implements Module
{
	private AnalogInput input;
	private int angle;
	private double voltage;
	public static final int OFFSET = 20, MAX_VOLTAGE = 5; //needs to be checked
	

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
		update();
		return angle;
	}

	
	public boolean update()
	{
		voltage = input.getVoltage();
		cvtToAngle(voltage);
		return true;
	}
	
}

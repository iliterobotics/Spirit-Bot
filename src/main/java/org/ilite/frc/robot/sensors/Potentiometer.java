package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.Constants;
import org.usfirst.frc.team1885.robot.modules.Module;

import edu.wpi.first.wpilibj.AnalogInput;

public class Potentiometer
{
	private AnalogInput input;
	public double offset = 14.7;
	public static final int MAX_VOLTAGE = 5; //needs to be checked
	
	

	public Potentiometer()
	{
		input = new AnalogInput(Constants.ANALOG_PORT_ELEVATION);
	}
	public double cvtToAngle(double voltage)
	{
		return (voltage / MAX_VOLTAGE) * 340 - offset;
	}
	
	public void zeroAngle(double currentAngle)
	{
		offset = currentAngle;
	}

	public double getAngle() 
	{
		System.out.println(cvtToAngle(input.getVoltage()) + "Pot reading");
		return cvtToAngle(input.getVoltage());
		
	}
	
}

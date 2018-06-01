package org.ilite.frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import org.ilite.frc.robot.Constants;

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
	
	public void zeroAngle()
	{
		offset =(input.getVoltage() / MAX_VOLTAGE) * 340;
	}

	public double getAngle() 
	{
		return -cvtToAngle(input.getVoltage());
		
	}

	public double getVoltage() {
		return input.getVoltage();
	}
	
}

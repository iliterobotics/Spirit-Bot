package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.modules.Module;
import org.usfirst.frc.team1885.robot.modules.Shooter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;

public class Potentiometer implements Module
{
	private AnalogInput input;
	private double angle;
	private final int PORT = 2;
	private Shooter myShooter;
	private double voltage;
	public static final int OFFSET = 20, MAX_VOLTAGE = 5; //needs to be checked
	
	public Potentiometer(Shooter s)
	{
		myShooter = s;
	}
	
	public void init()
	{
		input = new AnalogInput(PORT);
	}
	
	public void cvtToAngle(double voltage)
	{
		angle = (voltage / MAX_VOLTAGE) * 340 + OFFSET;
		//We will test later.
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

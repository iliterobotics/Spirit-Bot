package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.modules.Shooter;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Talon;

public class Potentiometer 
{
	private AnalogInput input;
	private int angle;
	private final int PORT = 2;
	private Shooter myShooter;
	private double voltage;
	
	public Potentiometer(Shooter s)
	{
		myShooter = s;
	}
	
	public double cvtToAngle(double voltage)
	{
		return 0;
		//We will test later.
	}
	
	public void setAngle(int degree)
	{
		//calculate how much power for how many degrees.
	}
	
	public void control()
	{
		
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

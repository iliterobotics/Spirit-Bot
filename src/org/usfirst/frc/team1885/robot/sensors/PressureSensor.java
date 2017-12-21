package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.Constants;
import org.usfirst.frc.team1885.robot.modules.Module;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay;
import java.util.logging.Handler;

public class PressureSensor implements Module{
    
	//public static final double PSI_PER_VOLTAGE = 1;
    
	private AnalogInput aio;
    private double voltageReadout;
    private final double supplyVoltage = 5;
    private long startTime;
    private boolean ledState;
    
	public PressureSensor() {
        aio = new AnalogInput(Constants.ANALOG_PORT_PRESSURE_SENSOR);
        
    }
	
	public void init() {
		startTime = System.currentTimeMillis();
	}
	
	public boolean update() {
		
		
		voltageReadout = aio.getVoltage();
		return true;
	}
	
	
	public double getPSI()
	{
		double pressure = 250 * ( voltageReadout/supplyVoltage ) - 25;
		return pressure;
	}
	

} 
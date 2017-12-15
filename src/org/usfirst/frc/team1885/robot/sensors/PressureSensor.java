package org.usfirst.frc.team1885.robot.sensors;

import org.usfirst.frc.team1885.robot.Constants;
import org.usfirst.frc.team1885.robot.modules.Module;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.Relay;
import java.util.logging.Handler;

public class PressureSensor implements Module{
    
	//public static final double PSI_PER_VOLTAGE = 1;

    public static final int AIO_PORT = 1;
    
	private AnalogInput aio;
    private double voltageReadout;
    private final double supplyVoltage = 5;
    private long startTime;
    private boolean ledState;
    
	public PressureSensor() {
        aio = new AnalogInput(AIO_PORT);
        
    }
	
	public void init() {
		startTime = System.currentTimeMillis();
	}
	
	public boolean update() {
		
		
		voltageReadout = aio.getVoltage();
//		System.out.println("Voltage: " + aio.getVoltage() + "v");
		return true;
	}
	
	
	public double getPSI()
	{
		double pressure = 250 * ( voltageReadout/supplyVoltage ) - 25;
		return pressure;
	}
	

} 
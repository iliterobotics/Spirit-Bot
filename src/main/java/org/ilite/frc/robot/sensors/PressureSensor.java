package org.ilite.frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import org.ilite.frc.robot.Constants;
import org.ilite.frc.robot.modules.IModule;

public class PressureSensor implements IModule {
    
	//public static final double PSI_PER_VOLTAGE = 1;
    
	private AnalogInput aio;
    private double voltageReadout;
    private final double supplyVoltage = 5;
    private long startTime;
    private boolean ledState;
    private double offset;
    
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

	public double getOffset(double PSI) {

		offset = (aio.getVoltage())/(0.0004 * PSI + 0.01);
		return offset;
	}
	

} 
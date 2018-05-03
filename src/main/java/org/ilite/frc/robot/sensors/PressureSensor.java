package org.ilite.frc.robot.sensors;

import edu.wpi.first.wpilibj.AnalogInput;
import org.ilite.frc.robot.Constants;

public class PressureSensor {

	private AnalogInput aio;
    
	public PressureSensor() {
        aio = new AnalogInput(Constants.ANALOG_PORT_PRESSURE_SENSOR);
    }
	
	public double getPSI()
	{
		return 250 * ( aio.getVoltage() / Constants.ANALOG_SUPPLY_VOLTAGE_PRESSURE_SENSOR ) - 25;
	}

	public double getNormalizedSupplyVoltage(double knownPressure) {
	    return aio.getVoltage() / ((0.004 * knownPressure) + 0.1);
    }
	

} 
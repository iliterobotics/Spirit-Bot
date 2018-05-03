
package org.ilite.frc.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import org.ilite.frc.robot.modules.DriveTrain;
import org.ilite.frc.robot.modules.DriverControl;
import org.ilite.frc.robot.modules.Module;
import org.ilite.frc.robot.modules.Shooter;
import org.ilite.frc.robot.sensors.Potentiometer;
import org.ilite.frc.robot.sensors.PressureSensor;

import java.util.LinkedList;


public class Robot extends SampleRobot {

	private LinkedList<Module> runningModules;
	private DriverControl driverControl;
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Potentiometer angleSensor;
	private PressureSensor pressureSensor;
	
	public Robot() {
		runningModules = new LinkedList<>();
		drivetrain = new DriveTrain();
		angleSensor = new Potentiometer();
	    pressureSensor = new PressureSensor();
		shooter = new Shooter(pressureSensor, angleSensor);
		driverControl = new DriverControl(drivetrain, shooter, pressureSensor);
	}
	

	public void robotInit() {
		
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		setRunningModules(drivetrain, driverControl, shooter);
		initModules();
		while (isOperatorControl() && isEnabled()) {
			for(Module m : runningModules) {
				m.update();
			}
		}
	}
	
	private void setRunningModules(Module...modules) {
		runningModules.clear();
		for(Module m : modules) {
			runningModules.add(m);
		}
		initModules();
	}
	
	private void initModules() {
		for(Module m : runningModules) {
			m.init();
		}
	}

}

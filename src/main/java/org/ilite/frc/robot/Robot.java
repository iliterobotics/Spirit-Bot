
package org.ilite.frc.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import org.ilite.frc.robot.modules.*;
import org.ilite.frc.robot.sensors.Potentiometer;
import org.ilite.frc.robot.sensors.PressureSensor;

import java.util.LinkedList;


public class Robot extends SampleRobot {

	private LinkedList<IModule> runningModules;
	private DriverControl driverControl;
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Potentiometer angleSensor;
	private PressureSensor pressureSensor;
	private Horn horn;
	
	public Robot() {
		runningModules = new LinkedList<>();
		horn = new Horn();
		drivetrain = new DriveTrain();
		angleSensor = new Potentiometer();
	    pressureSensor = new PressureSensor();
		shooter = new Shooter(pressureSensor, angleSensor);
		driverControl = new DriverControl(drivetrain, shooter, pressureSensor, horn);
	}
	

	public void robotInit() {
		
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		setRunningModules(drivetrain, driverControl, shooter, horn);
		initModules();
		while (isOperatorControl() && isEnabled()) {
			for(IModule m : runningModules) {
				m.update();
			}
		}
	}
	
	private void setRunningModules(IModule...modules) {
		runningModules.clear();
		for(IModule m : modules) {
			runningModules.add(m);
		}
		initModules();
	}
	
	private void initModules() {
		for(IModule m : runningModules) {
			m.init();
		}
	}

}

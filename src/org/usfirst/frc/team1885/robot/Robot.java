
package org.usfirst.frc.team1885.robot;

import java.util.LinkedList;

import org.usfirst.frc.team1885.robot.modules.DriveTrain;
import org.usfirst.frc.team1885.robot.modules.DriverControl;
import org.usfirst.frc.team1885.robot.modules.Module;
import org.usfirst.frc.team1885.robot.modules.Shooter;
import org.usfirst.frc.team1885.robot.sensors.PressureSensor;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Robot extends SampleRobot {

	private LinkedList<Module> runningModules;
	private DriverControl driverControl;
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Potentiometer angleSensor;
	private PressureSensor pressureSensor;

	public void robotInit() {
		runningModules = new LinkedList<>();
		drivetrain = new DriveTrain();
		//angleSensor = new Potentiometer();
		//pressureSensor = new PressureSensor();
		//shooter = new Shooter(pressureSensor, angleSensor);
		driverControl = new DriverControl(drivetrain);
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		setRunningModules(drivetrain, driverControl);
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

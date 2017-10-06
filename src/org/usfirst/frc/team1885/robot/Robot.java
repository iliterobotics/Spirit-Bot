
package org.usfirst.frc.team1885.robot;

import java.util.LinkedList;

import org.usfirst.frc.team1885.robot.modules.*;
import org.usfirst.frc.team1885.robot.sensors.PressureSensor;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.interfaces.Potentiometer;

public class Robot extends SampleRobot {

	private LinkedList<Module> runningModules;
	private DriveTrain drivetrain;
	private Shooter shooter;
	private Potentiometer angleSensor;
	private PressureSensor pressureSensor;

	public void robotInit() {
		runningModules = new LinkedList<>();
		drivetrain = new DriveTrain();
		angleSensor = new Potentiometer();
		pressureSensor = new PressureSensor();
		shooter = new Shooter(pressureSensor, angleSensor);
	}

	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		runningModules.add(drivetrain);
		runningModules.add(shooter);
		runningModules.add(angleSensor);
		runningModules.add(pressureSensor);
		while (isOperatorControl() && isEnabled()) {
			for(Module m : runningModules) {
				m.update();
			}
		}
	}

}

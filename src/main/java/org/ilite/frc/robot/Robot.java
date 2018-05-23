
package org.ilite.frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.SampleRobot;
import org.ilite.frc.robot.modules.*;
import org.ilite.frc.robot.sensors.Potentiometer;
import org.ilite.frc.robot.sensors.PressureSensor;

import java.util.LinkedList;


public class Robot extends SampleRobot {

//	private LinkedList<IModule> runningModules;
//	private DriverControl driverControl;
//	private DriveTrain drivetrain;
//	private Shooter shooter;
//	private Potentiometer angleSensor;
//	private PressureSensor pressureSensor;
//	private Horn horn;
	
	public Robot() {
//		runningModules = new LinkedList<>();
//		horn = new Horn();
//		drivetrain = new DriveTrain();
//		angleSensor = new Potentiometer();
//	    pressureSensor = new PressureSensor();
//		shooter = new Shooter(pressureSensor, angleSensor);
//		driverControl = new DriverControl(drivetrain, shooter, pressureSensor, horn);
	}
	

	public void robotInit() {
		
	}
	Relay horn = new Relay(Constants.RELAY_PORT_HORN);
	Relay dump = new Relay(Constants.RELAY_PORT_DUMP);
	Relay shoot = new Relay(Constants.RELAY_PORT_SHOOTER);
	Joystick pad = new Joystick(DriverInputMap.CONTROLLER_ID);
	// This function is called once each time the robot enters teleop mode.
	public void operatorControl() {
		while(true) {
			if (pad.getRawButton(DriverInputMap.GAMEPAD_A_BUTTON)) {
				horn.set(Relay.Value.kOn);
				System.out.println("hey");
			} else {
				horn.set(Relay.Value.kOff);
			}
			if (pad.getRawButton(DriverInputMap.GAMEPAD_B_BUTTON)) {
				shoot.set(Relay.Value.kOn);
			} else {
				shoot.set(Relay.Value.kOff);
			}
			if (pad.getRawButton(DriverInputMap.GAMEPAD_Y_BUTTON)) {
				dump.set(Relay.Value.kOn);
			} else {
				dump.set(Relay.Value.kOff);
			}
		}
//		setRunningModules(drivetrain, driverControl, shooter, horn);
//		initModules();
//		while (isOperatorControl() && isEnabled()) {
//			for(IModule m : runningModules) {
//				m.update();
//			}
//		}
	}
	
//	private void setRunningModules(IModule...modules) {
//		runningModules.clear();
//		for(IModule m : modules) {
//			runningModules.add(m);
//		}
//		initModules();
//	}
//
//	private void initModules() {
//		for(IModule m : runningModules) {
//			m.init();
//		}
//	}

}

package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Talon;

public class DriveTrain implements Module {

	public final int LEFT_PWM_ID = 0;
	public final int RIGHT_PWM_ID = 1;
	
	Talon leftMotor, rightMotor;
	double desiredLeft, desiredRight;
	public DriveTrain() {
		leftMotor = new Talon(LEFT_PWM_ID);
		rightMotor = new Talon(RIGHT_PWM_ID);
		leftMotor.setBounds(2.0387, 1.539, 1.513, 1.487, .989);
		rightMotor.setBounds(2.0387, 1.539, 1.513, 1.487, .989);
	}
	
	public void init() {
		
	}
	
	public boolean update() {
		updateSpeeds(desiredLeft, desiredRight);
		return false;
	}
	
	private void updateSpeeds(double left, double right) {
		leftMotor.setSpeed(left);
		rightMotor.setSpeed(right);
	}
	
	public void setSpeeds(double left, double right) {
		desiredLeft = left;
		desiredRight = right;
	}
	
}

package org.ilite.frc.robot.modules;

import edu.wpi.first.wpilibj.Talon;
import org.ilite.frc.robot.modules.IModule;

public class DriveTrain implements IModule {

	public final int LEFT_PWM_ID = 0;
	public final int RIGHT_PWM_ID = 1;
	
	Talon mLeftMotor, mRightMotor;
	double mDesiredLeft, mDesiredRight;
	
	
	public DriveTrain() {
		mLeftMotor = new Talon(LEFT_PWM_ID);
		mRightMotor = new Talon(RIGHT_PWM_ID);
		mLeftMotor.setBounds(2.0387, 1.539, 1.513, 1.487, .989);
		mRightMotor.setBounds(2.0387, 1.539, 1.513, 1.487, .989);
	}
	
	public void init() {
		
	}
	
	public boolean update() {
		updateSpeeds(mDesiredLeft, mDesiredRight);
		return false;
	}
	
	private void updateSpeeds(double left, double right) {
		mLeftMotor.setSpeed(left);
		mRightMotor.setSpeed(right);
	}
	
	public void setSpeeds(double left, double right) {
		mDesiredLeft = left;
		mDesiredRight = right;
	}
	
}

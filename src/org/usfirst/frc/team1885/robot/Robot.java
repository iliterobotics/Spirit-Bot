
package org.usfirst.frc.team1885.robot;

import edu.wpi.first.wpilibj.SampleRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends SampleRobot{
	     //This function is called once each time the robot enters autonomous mode.
	     public void autonomous() {
	          // Put code here
	          Timer.delay(0.05);
	     }

	     // This function is called once each time the robot enters teleop mode.
	     public void operatorControl() {
	         while(isOperatorControl() && isEnabled()) {
	               //Put code here
	               //Timer.delay(0.05);
	         }
	     }

	
}

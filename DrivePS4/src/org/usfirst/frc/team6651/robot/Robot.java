/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6651.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;


public class Robot extends IterativeRobot {
	
	public static DifferentialDrive DT;
	Spark slider;
	Joystick PS4 = new Joystick(0);
	
	
	@Override
	public void robotInit() {
		Spark rightFront = new Spark(1);
		Spark rightBack = new Spark(2);
		SpeedControllerGroup m_right = new SpeedControllerGroup(rightFront, rightBack);
		Spark leftFront = new Spark(4);
		Spark leftBack = new Spark(5);
		SpeedControllerGroup m_left = new SpeedControllerGroup(leftFront, leftBack);
		m_left.setInverted(false);
		DT = new DifferentialDrive(m_right, m_left);	
		slider = new Spark(6);
	}

	@Override
	public void autonomousInit() {
		
	}


	@Override
	public void autonomousPeriodic() {

	}


	@Override
	public void teleopPeriodic() {
		int X_axis = 1, Y_axis = 0, Rotation = 2;
		double left = PS4.getRawAxis(X_axis); 
		double right = PS4.getRawAxis(Rotation); 
		DT.tankDrive(left,right);
		
		double slideSpeed = PS4.getRawAxis(Y_axis);
		slider.set(slideSpeed);
	}

	
	@Override
	public void testPeriodic() {
	}
}

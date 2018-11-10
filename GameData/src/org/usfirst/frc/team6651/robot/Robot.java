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
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;


public class Robot extends IterativeRobot {
	
	public static DifferentialDrive DT;
	Spark slider;
	Joystick PS4 = new Joystick(0);
	Encoder RightEncoder;
	Encoder LeftEncoder;
	int countRight;
	int countLeft;
	String gameData;
	
	
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
		
		RightEncoder = new Encoder(0, 1, false, Encoder.EncodingType.k4X);
		LeftEncoder = new Encoder(2, 3, false, Encoder.EncodingType.k4X);
	}


	@Override
	public void autonomousInit() {
		DT.tankDrive(0,0);
		RightEncoder.reset();
		LeftEncoder.reset();
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		System.out.println("gameData: " + gameData);
	}


	@Override
	public void autonomousPeriodic() {
		countRight = RightEncoder.get();
		countLeft = LeftEncoder.get();
		if(gameData.equals("LLL"))
		{
			System.out.println("Go Left");
		}
		else
		{
			System.out.println("Go Right");
		}
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

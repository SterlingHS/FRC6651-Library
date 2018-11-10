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
import edu.wpi.first.wpilibj.Encoder;

public class Robot extends IterativeRobot {
	
	public static DifferentialDrive DT;
	Spark slider;
	Joystick PS4 = new Joystick(0);
	Encoder RightEncoder;
	Encoder LeftEncoder;
	int countRight, previousRight;
	int countLeft, previousLeft;
	double ticks_per_inch=250;
	
	
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
		RightEncoder.reset();
		LeftEncoder.reset();
	}


	@Override
	public void autonomousInit() {
		DT.tankDrive(-.6,-.6);
		RightEncoder.reset();
		LeftEncoder.reset();
		countRight = RightEncoder.get();
		countLeft = LeftEncoder.get();
		System.out.println("Right Encoder: " + countRight);
	}
	
	public int distance_reached(int encoder_value, double distance)
	{
		int reach = 0;
			if( encoder_value/ticks_per_inch>distance)
				reach = 1;
		return reach;
	}

	
	@Override
	public void autonomousPeriodic() {
		countRight = RightEncoder.get();
		countLeft = LeftEncoder.get();
		if(distance_reached(countRight,5) == 1)
		{
			DT.tankDrive(0,0);
			System.out.println("Stop at: " + countLeft + " " + countRight);
		}
		else DT.tankDrive(-.65,-.65);
	}


	@Override
	public void teleopPeriodic() {
		int X_axis = 1, Y_axis = 0;
		double left = PS4.getRawAxis(X_axis); 
		double right = PS4.getRawAxis(Y_axis); 
		DT.arcadeDrive(left,right);

		countRight = RightEncoder.get();
		countLeft = LeftEncoder.get();
		if(previousRight != countRight || previousLeft != countLeft)
		{
			System.out.println("Encoders: " + countRight + " " + countLeft);
			previousRight = countRight;
			previousLeft = countLeft;
		}
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}

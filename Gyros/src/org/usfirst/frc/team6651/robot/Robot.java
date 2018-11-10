/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6651.robot;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
// import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;



public class Robot extends IterativeRobot {
	
	ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	double angle;
	double tick_per_degree=0.000144114;
	
	
	@Override
	public void robotInit() {

        // gyro.calibrate();
        gyro.reset();
		
	}

	
	@Override
	public void autonomousInit() {

		gyro.reset();
		// gyro.calibrate();
	}
	

	@Override
	public void autonomousPeriodic() {
		angle = gyro.getAngle();
		System.out.println("Angle at: " + angle + "    The angle: " + (int)(angle/tick_per_degree));
	}


	@Override
	public void teleopPeriodic() {
		angle = gyro.getAngle();
		System.out.println("Angle at: " + angle + "    The angle: " + (int)(angle/tick_per_degree));
	}


	@Override
	public void testPeriodic() {
	}
}

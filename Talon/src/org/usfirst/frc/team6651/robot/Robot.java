/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6651.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;



public class Robot extends IterativeRobot {
	
	public static DifferentialDrive DT;

	Joystick PS4 = new Joystick(0);
	Button butterflyButtonUp = new JoystickButton(PS4, 1);
	Button butterflyButtonDown = new JoystickButton(PS4, 2);
	
	WPI_TalonSRX talon10;
	WPI_TalonSRX talon11;
	WPI_TalonSRX talon12;
	WPI_TalonSRX talon13;
	
	Compressor c;
	DoubleSolenoid butterflySolenoid;
	boolean changeOfState = true;
	boolean butterflyState = false;

	
	@Override
	public void robotInit() {
		talon10 = new WPI_TalonSRX(10);
		talon12 = new WPI_TalonSRX(12);
		talon12.follow(talon10);
		talon11 = new WPI_TalonSRX(11);
		talon13 = new WPI_TalonSRX(13);
		talon13.follow(talon11);
		
		DT = new DifferentialDrive(talon10, talon11);
		
		c = new Compressor(0);
		c.setClosedLoopControl(true);  // Start compressor control
		
		butterflySolenoid = new DoubleSolenoid(0, 1);
		butterflySolenoid.set(DoubleSolenoid.Value.kForward);	
	}


	@Override
	public void autonomousInit() {
		
	}


	@Override
	public void autonomousPeriodic() {

	}


	@Override
	public void teleopPeriodic() {
		int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		double forward = PS4.getRawAxis(X_axis); 
		double turn = PS4.getRawAxis(Y_axis); 
		DT.arcadeDrive(forward, -turn);
		
		if (PS4.getRawButton(1) == true) {
			butterflySolenoid.set(DoubleSolenoid.Value.kForward);
		}
		if (PS4.getRawButton(1) == false && PS4.getRawButton(2) == true) {
			butterflySolenoid.set(DoubleSolenoid.Value.kReverse);
		}
	}


	@Override
	public void testPeriodic() {
	}
}

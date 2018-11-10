package org.usfirst.frc.team6651.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

public class Robot extends IterativeRobot {
	private static final String kDefaultAuto = "Default";
	private static final String kCustomAuto = "My Auto";
	private String m_autoSelected;
	private SendableChooser<String> m_chooser = new SendableChooser<>();
	private Spark slider;
	
	public static DifferentialDrive DT;
	
	Joystick joy1 = new Joystick(1);
	Joystick joy2 = new Joystick(0);

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
		m_autoSelected = m_chooser.getSelected();
		System.out.println("Auto selected: " + m_autoSelected);
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch (m_autoSelected) {
			case kCustomAuto:
				break;
			case kDefaultAuto:
			default:
				break;
		}
	}

	
	@Override
	public void teleopPeriodic() {
		
		// Normal arcade with slide on throttle
		// int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		// double forward = joy1.getRawAxis(X_axis); 
    		// double turn = joy1.getRawAxis(Y_axis); 
    		// forward=0.4*forward+0.6*forward*forward*forward;
    		// turn=0.4*turn+0.6*turn*turn*turn;
		// DT.arcadeDrive(forward, turn);
		// double slideSpeed = joy1.getRawAxis(Throttle);
		// slideSpeed=0.4*slideSpeed+0.6*slideSpeed*slideSpeed*slideSpeed;
		// slider.set(slideSpeed);
		
		// Forward in Y_axis
		// Rotate in Z_axis rotation
		// Slide on X_axis
		// int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		// double forward = joy.getRawAxis(X_axis); 
    		// double turn = joy.getRawAxis(Rotation); 
		// DT.arcadeDrive(forward, turn);
		// double slideSpeed = joy.getRawAxis(Y_axis);
		// slider.set(slideSpeed);
		
		// 2 joystick drive
		// Joystick 1: Arcade drive
		// Joystick 2: Slide on X_axis
		//int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		//double forward = joy1.getRawAxis(X_axis); 
		//double turn = joy1.getRawAxis(Y_axis); 
		// forward=forward*forward*forward;
		// turn=turn*turn*turn;
		//DT.arcadeDrive(forward, turn);
		//double slideSpeed = joy2.getRawAxis(Y_axis);
		//slider.set(slideSpeed);
		
		// 2 joystick drive
		// Joystick 1 and 2: Tank drive
		// Joystick 2: Slide on X_axis
		int X_axis = 1, Y_axis = 0, Rotation = 2, Throttle = 3;
		double left = joy1.getRawAxis(X_axis); 
		double right = joy2.getRawAxis(X_axis); 
		DT.tankDrive(left,right);
		
		double slideSpeed = (joy1.getRawAxis(Y_axis)+joy2.getRawAxis(Y_axis))/2;
		slider.set(slideSpeed);
	}


	@Override
	public void testPeriodic() {
		
	}
}
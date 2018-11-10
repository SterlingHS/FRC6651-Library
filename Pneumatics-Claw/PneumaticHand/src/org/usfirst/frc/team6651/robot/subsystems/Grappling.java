// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc.team6651.robot.subsystems;

import org.usfirst.frc.team6651.robot.RobotMap;
// import org.usfirst.frc.team6651.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;
// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class Grappling extends Subsystem {

	// Declare Selonoid
	private DoubleSolenoid solenoidClaw;
    // private final Compressor compressor = RobotMap.grapplingCompressor;
    // private final DoubleSolenoid solenoidClaw = RobotMap.grapplingSolenoidClaw;

	private static final DoubleSolenoid.Value OPEN = DoubleSolenoid.Value.kReverse;
	private static final DoubleSolenoid.Value CLOSED = DoubleSolenoid.Value.kForward;
    
	public Grappling() {
		
		// Makes the solenoids
		 // solenoidClaw = RobotMap.grapplingSolenoidClaw;
		solenoidClaw = new DoubleSolenoid(RobotMap.clawDoubleSolenoidA, RobotMap.clawDoubleSolenoidB);

		
		// Sets solenoids to initial positions
		solenoidClaw.set(OPEN);
	}
	
	
	
    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
    public void openClaw() {
    	
    	solenoidClaw.set(OPEN);
    
    }
    
    public void closeClaw() {
    	
    	solenoidClaw.set(CLOSED);
    	
    }
    
    public boolean isClawOpen() {
    	
    	return solenoidClaw.get().equals(OPEN);
    
    }
    
    public boolean isClawClosed() {
    	return solenoidClaw.get().equals(CLOSED);
    }
    
    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

}


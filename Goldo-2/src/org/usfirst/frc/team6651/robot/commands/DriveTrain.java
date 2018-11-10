package org.usfirst.frc.team6651.robot.commands;

import org.usfirst.frc.team6651.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrain extends Command {
	
	int X_AXIS = 0;
	int Y_AXIS = 1;
	int ROTATE = 2;

    public DriveTrain() {
    		requires(Robot.DT);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    		Robot.DT.DrivePS4(0,0,0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		double forward = Robot.oi.getJoy1Axis(X_AXIS);
    		double turn = Robot.oi.getJoy1Axis(Y_AXIS);
    		double slide = Robot.oi.getJoy1Axis(ROTATE);
    		Robot.DT.DrivePS4(forward, turn, slide);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    		Robot.DT.DrivePS4(0,0,0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

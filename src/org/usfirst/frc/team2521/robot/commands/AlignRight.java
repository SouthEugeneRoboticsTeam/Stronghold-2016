package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AlignRight extends Command {
	
    public AlignRight() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivetrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setSetpoint(Robot.sensors.getInitYaw());
    	SmartDashboard.putNumber("Setpoint", Robot.drivetrain.getSetpoint());
    	SmartDashboard.putString("Current command", "Align right");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        //return hasTraversed && !Robot.sensors.isTraversing;
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.disable();
    }
}

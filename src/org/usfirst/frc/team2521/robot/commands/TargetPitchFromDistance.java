package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TargetPitchFromDistance extends Command {
	

    public TargetPitchFromDistance() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pitch.autoInit();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double setpoint = Robot.pitch.getTargetEncoderPosition();
    	
    	Robot.pitch.set(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.pitch.getOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

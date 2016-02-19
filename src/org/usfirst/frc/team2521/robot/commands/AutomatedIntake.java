package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutomatedIntake extends Command {
	boolean ballInBot = false;

    public AutomatedIntake() {
    	requires(Robot.intake);
    	requires(Robot.flyWheels);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	ballInBot = Robot.intake.ballInBot();
    	if(ballInBot){
    		Robot.intake.in();
    		Robot.flyWheels.in();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.intake.ballInShooter();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.stop();
    	Robot.flyWheels.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetPusher extends Command {
	boolean out;
    public SetPusher(boolean out) {
    	this.out = out;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (out) {
    		//return Robot.sensors.ballInShooter();
    		//return Robot.flyWheels.upToSpeed();
    		return true;
    	} else {
    		//return !Robot.sensors.ballInBot();
    		return true;
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (out) {
    		Robot.flyWheels.setPusher(true);
    	} else {
    		Robot.flyWheels.setPusher(false);
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

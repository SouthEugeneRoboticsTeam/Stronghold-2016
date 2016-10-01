
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SensorDefault extends Command {
	
	public SensorDefault() {
		requires(Robot.sensors);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.sensors.getHeight();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.sensors.display();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

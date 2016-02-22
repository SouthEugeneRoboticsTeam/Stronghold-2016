
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleoperatedDrive extends Command {
	int traverseCount = 0;
	boolean lastTraverseState = false;
	boolean hasTraversed = false;
	
	public TeleoperatedDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.teleoperatedDrive();
		if(Robot.sensors.isTraversing() && !lastTraverseState) traverseCount++;
		hasTraversed = traverseCount >= 2;
		SmartDashboard.putNumber("Traverse count", traverseCount);
		SmartDashboard.putBoolean("Last traverse state", lastTraverseState);
		SmartDashboard.putBoolean("Has traversed", hasTraversed);
		lastTraverseState = Robot.sensors.isTraversing();
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
		end();
	}
}

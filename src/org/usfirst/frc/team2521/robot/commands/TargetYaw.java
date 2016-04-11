
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetYaw extends Command {
	
	public TargetYaw() {
		//requires(Robot.yaw);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		//    	Robot.yaw.autoInit(); 
		//Robot.yaw.enable();
		SmartDashboard.putString("Current command:", "Target yaw");
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.yaw.setSetpoint(0);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;//Robot.yaw.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		//	Robot.yaw.autoEnd();
		//Robot.yaw.disable();
		SmartDashboard.putBoolean("Target yaw running", false);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//Robot.yaw.autoEnd();
	}
}

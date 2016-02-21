
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IntakeIn extends Command {
	
	public IntakeIn() {
		requires(Robot.intake);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("Intake set", true);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.intake.in();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		Robot.intake.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//Robot.intake.stop();
	}
}

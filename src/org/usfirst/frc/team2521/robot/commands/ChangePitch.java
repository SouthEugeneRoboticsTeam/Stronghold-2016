
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ChangePitch extends Command {
	
	// Up = true - Down = false
	double position;
	public static boolean changePitchIsFinished = false;
	
	public ChangePitch(double position) {
		requires(Robot.intake);
		
		this.position = position;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.changePitch(this.position);
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return changePitchIsFinished;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

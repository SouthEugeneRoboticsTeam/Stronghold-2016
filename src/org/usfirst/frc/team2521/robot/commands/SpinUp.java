package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SpinUp extends Command {
	
	public static boolean stopSpin = false;

	public SpinUp() {
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		FileManager.currentCommand = getClass().toString();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.shooter.startSpinUp();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return stopSpin;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.shooter.stopSpinUp();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

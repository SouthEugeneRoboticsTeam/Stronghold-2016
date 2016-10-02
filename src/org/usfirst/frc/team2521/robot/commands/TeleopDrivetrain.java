package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set drivetrain based on joystick input
 */
public class TeleopDrivetrain extends Command {
	
	public TeleopDrivetrain() {
		requires(Robot.drivetrain);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.drivetrain.teleoperatedDrive();
	}
	
	protected boolean isFinished() {
		return false;
	}

	protected void end() {}
	
	protected void interrupted() {}
}

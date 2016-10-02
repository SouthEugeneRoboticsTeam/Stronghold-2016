package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Command wrapper for calling sensors.display by default
 */
public class SensorDefault extends Command {
	
	public SensorDefault() {
		requires(Robot.sensors);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.sensors.display();
	}
	
	protected boolean isFinished() {
		return false;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}

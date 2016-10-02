package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Start fly wheels in one direction or another.
 * StopFlywheels must be called later.
 */
public class SetFlyWheels extends Command {
	
	boolean out;
	
	// True means shoot, false means intake
	public SetFlyWheels(boolean out) {
		this.out = out;
	}
	
	protected void initialize() {
    	if (out) {
			Robot.flyWheels.out();
		} else {
			Robot.flyWheels.in();
		}
	}
	
	protected void execute() {}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {}
}

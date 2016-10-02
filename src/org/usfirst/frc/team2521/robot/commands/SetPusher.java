package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets position of pneumatic pusher in shooter
 */
public class SetPusher extends Command {
	boolean out;
	
    public SetPusher(boolean out) {
    	this.out = out;
    }
    
    protected void initialize() {}
    
    protected void execute() {}

    protected boolean isFinished() {
    	return true;
    }

    protected void end() {
    	if (out) {
    		Robot.flyWheels.setPusher(true);
    	} else {
    		Robot.flyWheels.setPusher(false);
    	}
    }
    
    protected void interrupted() {}
}

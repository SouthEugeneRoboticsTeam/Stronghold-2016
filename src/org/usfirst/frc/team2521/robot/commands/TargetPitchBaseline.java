


package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetPitchBaseline extends Command {
	boolean high;
    public TargetPitchBaseline(boolean high) {
    	this.high = high;
    }

    protected void initialize() {
    	Robot.pitch.autoInit();
    }

    protected void execute() {
    	SmartDashboard.putString("Auto place","Target pitch baseline " + high);
    	if (high) {
    		Robot.pitch.set(0.75*RobotMap.ENCODER_RANGE);
    	}
    	else {
    		Robot.pitch.set(0.55*RobotMap.ENCODER_RANGE);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

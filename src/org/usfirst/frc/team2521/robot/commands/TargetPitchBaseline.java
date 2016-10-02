package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetPitchBaseline extends Command {
	static int counter = 0;
	boolean high;
    public TargetPitchBaseline(boolean high) {
    	this.high = high;
    	counter++;
    	SmartDashboard.putNumber("Counter", counter);
    }

    protected void initialize() {
    	SmartDashboard.putNumber("Auto num", 1);
    	Robot.pitch.autoInit();
    }

    protected void execute() {
    	SmartDashboard.putNumber("Baseline counter", counter);
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

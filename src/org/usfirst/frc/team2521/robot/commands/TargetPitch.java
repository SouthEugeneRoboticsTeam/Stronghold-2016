package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetPitch extends Command {
	double setpoint;
	int baselineCounter = 0;
	int counter = 0;
	
    public TargetPitch(int baselineCounter) {
    	this.baselineCounter = baselineCounter;
    }
    
    public TargetPitch() {}

    protected void initialize() {
    	SmartDashboard.putBoolean("Target running?", true);
    	SmartDashboard.putNumber("Auto num", 4);
    	setpoint = Robot.pitch.getTargetEncoderPosition();
    	Robot.pitch.autoInit();
    }

    protected void execute() {
    	SmartDashboard.putNumber("Baseline counter", counter);
    	if (counter < baselineCounter) {
    		Robot.pitch.set(0.5*RobotMap.ENCODER_RANGE);
    		SmartDashboard.putString("Target pitch mode", "Baseline");
    	} else {
    		Robot.pitch.set(setpoint);
    		SmartDashboard.putString("Target pitch mode", "Aim");
    	}
    	SmartDashboard.putString("Auto place", "Target Pitch");
    	counter++;
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	SmartDashboard.putBoolean("Target running?", false);
    }

    protected void interrupted() {
    }
}

package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Sets pitch to encoder position calculated based on lidar
 */
public class TargetPitch extends Command {
	double setpoint;
	int baselineCounter = 0;
	int counter = 0;
	
    public TargetPitch(int baselineCounter) {
    	this.baselineCounter = baselineCounter;
    }
    
    // Allows us to call target pitch with no arguments if we want,
    // which sets the baseline counter to zero
    public TargetPitch() {}

    protected void initialize() {
    	setpoint = Robot.pitch.getTargetEncoderPosition();
    	Robot.pitch.autoInit();
    }

    protected void execute() {
    	SmartDashboard.putNumber("Baseline counter", counter);
    	if (counter < baselineCounter) {
    		Robot.pitch.set(0.7*RobotMap.ENCODER_RANGE);
    		SmartDashboard.putString("Target pitch mode", "Baseline");
    	} else {
    		Robot.pitch.set(setpoint);
    		SmartDashboard.putString("Target pitch mode", "Aim");
    		Robot.yaw.disable();
    	}
    	counter++;
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}

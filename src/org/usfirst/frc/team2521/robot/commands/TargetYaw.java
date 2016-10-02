package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Aims yaw based on camera input (tries to center the target within
 * the camera image)
 */
public class TargetYaw extends Command {

    public TargetYaw() {}

    protected void initialize() {
    	Robot.yaw.autoInit(); 
    }
    
    protected void execute() { 
    	Robot.yaw.setSetpoint(0);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	Robot.yaw.teleopEnd();
    }

    protected void interrupted() {
    	Robot.yaw.teleopEnd();
    }
}

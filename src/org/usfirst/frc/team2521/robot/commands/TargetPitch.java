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

    public TargetPitch() {
    }

    protected void initialize() {
    	setpoint = Robot.pitch.getTargetEncoderPosition();
    	Robot.pitch.autoInit();
    }

    protected void execute() {
    	SmartDashboard.putString("Auto place", "Target Pitch");
    	Robot.pitch.set(setpoint);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

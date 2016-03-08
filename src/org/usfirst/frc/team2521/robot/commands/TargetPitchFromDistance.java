package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TargetPitchFromDistance extends Command {
	double setpoint;

    public TargetPitchFromDistance() {
    	requires(Robot.pitch);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pitch.autoInit();
    	Robot.pitch.set(600 + Robot.pitch.getEncoderMin());
    	SmartDashboard.putNumber("600 set", 600 + Robot.pitch.getEncoderMin());
    	setpoint = Robot.pitch.getTargetEncoderPosition() + Robot.pitch.getEncoderMin();
    	Timer.delay(5);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Targ enc pos", setpoint);
    	Robot.pitch.set(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//Robot.pitch.getOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

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
    	//requires(Robot.pitch);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setpoint = Robot.pitch.getTargetEncoderPosition();
    	Robot.pitch.autoInit();
    	/*Robot.pitch.autoInit();
    	Robot.pitch.set(RobotMap.PITCH_VISION_BASELINE + Robot.pitch.getEncoderMin());
    	SmartDashboard.putNumber("Baseline", RobotMap.PITCH_VISION_BASELINE + Robot.pitch.getEncoderMin());
    	setpoint = Robot.pitch.getTargetEncoderPosition() + Robot.pitch.getEncoderMin();
    	SmartDashboard.putNumber("Relative encoder position", setpoint);
    	Timer.delay(RobotMap.FIND_TARGET_DELAY);
    	SmartDashboard.putString("Current command:", "Target Pitch");*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//SmartDashboard.putNumber("Targ enc pos", setpoint);
    	SmartDashboard.putString("Current command:", "Baseline");
    	Robot.pitch.set(setpoint);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//return Robot.pitch.getOnTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pitch.autoEnd();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pitch.autoEnd();
    }
}

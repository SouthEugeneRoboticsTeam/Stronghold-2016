
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopPitch extends Command {
	
	public TeleopPitch() {
		//requires(Robot.pitch);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		//Robot.pitch.autoInit();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//double value = -OI.getInstance().getSecondaryStick().getY();
		//SmartDashboard.putNumber("Pitch set", value);
		double value = 1;//= -OI.getInstance().getSecondaryStick().getZ();
		//value = 0.5*(value + 1); //transforms to be 0 to 1
		SmartDashboard.putNumber("Relative encoder position", value * (RobotMap.ENCODER_RANGE));
		//value = Robot.pitch.getEncoderMin()+600;//4*RobotMap.ENCODER_RANGE/13;//409.449;
		//value = value*(RobotMap.ENCODER_RANGE)+Robot.pitch.getEncoderMin();
		//SmartDashboard.putNumber("Encoder value", value);*/
		//Robot.pitch.set(value);
		//SmartDashboard.putNumber("Motor value", Robot.pitch.getMotorValue());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}

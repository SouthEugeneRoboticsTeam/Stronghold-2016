
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TeleopYaw extends Command {
	
	public TeleopYaw() {
		//requires(Robot.yaw);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		SmartDashboard.putBoolean("Teleop Yaw called?", true);
		//Robot.yaw.autoInit();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//Robot.yaw.set((RobotMap.YAW_ENCODER_RANGE/2)*OI.getInstance().getSecondaryStick().getZ() + Robot.yaw.getZero());
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		//Robot.yaw.autoEnd();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		//Robot.yaw.autoEnd();
	}
}

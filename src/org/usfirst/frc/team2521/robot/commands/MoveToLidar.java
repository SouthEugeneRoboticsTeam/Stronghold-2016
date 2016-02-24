package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Move the robot to a certain encoder location
 */
public class MoveToLidar extends Command {
	
	//private double time;
	
	
	public MoveToLidar() {
		requires(Robot.drivetrain);
		//this.time = time;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.set(0.5, 0.5);
		//SmartDashboard.putString("Current command", "Move for time");
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return Robot.sensors.getLongLidar() > RobotMap.LIDAR_OUTER_WORKS_THRESHOLD;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		SmartDashboard.putBoolean("Auto running", false);
		Robot.drivetrain.set(0,0);
		
		//Timer.delay(time);
		//Robot.drivetrain.set(0);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.set(0,0);
	}
}
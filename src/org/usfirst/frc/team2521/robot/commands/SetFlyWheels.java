
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SetFlyWheels extends Command {
	boolean out;
	public SetFlyWheels(boolean out) {//true means shoot, fasle means intake
		requires(Robot.flyWheels);
		this.out = out;
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		FileManager.currentCommand = getClass().toString();
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if(out){
			Robot.flyWheels.out();
		}else Robot.flyWheels.in();
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}
	
	// Called once after isFinished returns true
	protected void end() {
		Robot.flyWheels.stop();
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.flyWheels.stop();
	}
}

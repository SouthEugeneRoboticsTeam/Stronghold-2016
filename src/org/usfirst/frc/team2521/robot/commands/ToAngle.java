
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ToAngle extends Command {
	
	double angle;
	//boolean auto;
	//boolean relative;
	double initTime;
	
	public ToAngle(double angle) {
		//this.auto = auto;
		this.angle = angle;
		//this.relative = relative;
		SmartDashboard.putBoolean("ToAngle called", true);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}
	
	// Called just before this Command runs the first time
	protected void initialize() {
		initTime = Timer.getFPGATimestamp();
		/*if(relative){
			angle = (Robot.sensors.getYaw() + angle);
			if(angle > 360) angle -= 360;
		}*/
		SmartDashboard.putNumber("Target angle", angle);
		//Timer.delay(5);
		Robot.drivetrain.enable();
		Robot.drivetrain.setSetpoint(0);
		
		Robot.drivetrain.setTargetAngle(angle);
	}
	
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Setpoint", Robot.drivetrain.getSetpoint());
		//SmartDashboard.putString("Current command", "Align right");
	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		//return hasTraversed && !Robot.sensors.isTraversing;
		return /*(auto || !((OI.getInstance().getRightStick().getRawButton(RobotMap.SPIN_BUTTON_BACK)) 
				|| (OI.getInstance().getRightStick().getRawButton(RobotMap.SPIN_BUTTON_FRONT)))) || */
		(Robot.drivetrain.getLargestMotorVal() < 0.5 && (Timer.getFPGATimestamp() - initTime) > 0.5);//false;//Robot.drivetrain.onTarget();
	}
	
	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.disable();
		SmartDashboard.putString("Current command", "None");
		SmartDashboard.putBoolean("ToAngle called", false);
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.disable();
		SmartDashboard.putBoolean("ToAngle called", false);
	}
}

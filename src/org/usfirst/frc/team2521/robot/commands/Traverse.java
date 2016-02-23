package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Traverse extends Command {
	boolean hasTraversed = false;
	
    public Traverse() {
    	requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.drivetrain.set(0.5);
    	Robot.drivetrain.set(0.8);
    	if(Robot.sensors.isTraversing()) hasTraversed = true;
		SmartDashboard.putBoolean("Has traversed", hasTraversed);
		Robot.sensors.updateTraversing();
		SmartDashboard.putString("Current command", "Traverse moat");
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (hasTraversed && !Robot.sensors.isTraversing())/* && (Robot.sensors.getPitch() ==0)*/;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

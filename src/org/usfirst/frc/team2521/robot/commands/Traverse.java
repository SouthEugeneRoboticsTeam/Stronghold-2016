package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Traverse extends Command {
	boolean hasTraversed = false;
	static int counter = 0;
	
    public Traverse() {
    	requires(Robot.drivetrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	counter++;
    	SmartDashboard.putBoolean("End Called", false);
    	SmartDashboard.putNumber("Counter", counter);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    //	Robot.drivetrain.set(0.8,0.8);
    	if(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise){
    		Robot.drivetrain.set(-0.8,-0.8);
		} else{
			Robot.drivetrain.set(0.8,0.8);
		}
    	if(Robot.sensors.isTraversing()){
    		hasTraversed = true;
    		if(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise){
    			Robot.manipulator.down();
    			
    		}
    	}
		SmartDashboard.putBoolean("Has traversed", hasTraversed);
		Robot.sensors.updateTraversing();
		SmartDashboard.putString("Current command", "Traversing");
    	SmartDashboard.putBoolean("End condition", hasTraversed && !Robot.sensors.isTraversing());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (hasTraversed && !Robot.sensors.isTraversing())/* && (Robot.sensors.getPitch() ==0)*/;
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*if(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise){
    		Robot.drivetrain.set(-0.8,-0.8);
		} else{
			Robot.drivetrain.set(0.8,0.8);
		}*/
    	SmartDashboard.putBoolean("End Called", true);
    	Robot.manipulator.up();
    	Robot.drivetrain.set(0,0);
    	Robot.manipulator.stop();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

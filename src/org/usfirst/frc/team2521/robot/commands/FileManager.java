package org.usfirst.frc.team2521.robot.commands;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FileManager extends Command {
	
	PrintWriter history;
	DriverStation driverStation;
	public static String currentCommand;

    public FileManager() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
		try {
			history = new PrintWriter("History.txt");
			history.println("Voltage   |   Current Command   |   Pitch | Yaw");
			history.println("------------------------------------------------");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	try {
    		history.println(driverStation.getBatteryVoltage() + "   |   " + currentCommand + "   |   " );
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (driverStation.getMatchTime() < 1)
        	return false;
        else
        	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	history.close();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

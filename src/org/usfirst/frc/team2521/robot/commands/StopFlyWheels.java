package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Stops flywheels
 */
public class StopFlyWheels extends Command {

    public StopFlyWheels() {}
    
    protected void initialize() {}

    protected void execute() {}

    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    	Robot.flyWheels.stop();
    }
    
    protected void interrupted() {}
}

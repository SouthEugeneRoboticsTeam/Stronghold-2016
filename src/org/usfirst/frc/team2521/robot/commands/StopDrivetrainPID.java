package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Makes sure drivetrain is disabled
 */
public class StopDrivetrainPID extends Command {

    public StopDrivetrainPID() {}
    
    protected void initialize() {}
    
    protected void execute() {}
    
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	Robot.drivetrain.disable();
    }

    protected void interrupted() {}
}

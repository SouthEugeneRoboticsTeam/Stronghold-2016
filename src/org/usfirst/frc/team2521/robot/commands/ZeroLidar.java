package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Reset averaged lidar value, then update it while the command runs
 * Used for getting lidar value in AutoShoot
 */
public class ZeroLidar extends Command {

    public ZeroLidar() {}

    protected void initialize() {
    	Robot.sensors.zeroLidar();
    }

    protected void execute() {
    	Robot.sensors.updateAvgLidar();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}

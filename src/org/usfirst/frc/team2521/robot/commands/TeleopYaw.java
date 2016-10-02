package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set yaw based on joystick input
 */
public class TeleopYaw extends Command {

    public TeleopYaw() {
    	requires(Robot.yaw);
    }

    protected void initialize() {}

    protected void execute() {
    	Robot.yaw.set(OI.getInstance().getSecondaryStick().getX());
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}

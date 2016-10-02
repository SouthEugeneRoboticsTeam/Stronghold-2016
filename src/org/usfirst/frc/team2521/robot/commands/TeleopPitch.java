package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Set pitch based on joystick input
 */
public class TeleopPitch extends Command {
    public TeleopPitch() {
    	requires(Robot.pitch);
    }

    protected void initialize() {}

    protected void execute() {
    	double value = -OI.getInstance().getSecondaryStick().getY();
    	Robot.pitch.set(value);
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}

    protected void interrupted() {}
}

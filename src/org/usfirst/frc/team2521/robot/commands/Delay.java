package org.usfirst.frc.team2521.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This command does nothing! It just lets us put in a delay after a command without
 * editing the command itself, which sacrificies generality
 */
public class Delay extends Command {

    public Delay() {}

    protected void initialize() {}

    protected void execute() {}

    protected boolean isFinished() {
        return false;
    }

    protected void end() {}
    
    protected void interrupted() {}
}

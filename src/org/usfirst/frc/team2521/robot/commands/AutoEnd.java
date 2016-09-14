package org.usfirst.frc.team2521.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoEnd extends CommandGroup {
    
    public  AutoEnd() {
    	addSequential(new SetPusher(false));
    	addSequential(new StopFlyWheels());
    }
}

package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.commands.*;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Reset extends CommandGroup {
    
    public  Reset() {
    	addParallel(new StopFlyWheels());
    	addParallel(new SetPusher(false));
    }
}

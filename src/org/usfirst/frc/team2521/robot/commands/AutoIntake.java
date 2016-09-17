package org.usfirst.frc.team2521.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoIntake extends CommandGroup {
    
    public  AutoIntake() {
    	addSequential(new IntakeWithLidar());
    }
}

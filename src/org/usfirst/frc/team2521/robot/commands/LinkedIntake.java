package org.usfirst.frc.team2521.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LinkedIntake extends CommandGroup {
    
    public  LinkedIntake() {
    	addParallel(new IntakeIn());
    	addParallel(new SetFlyWheels(false));
    }
}

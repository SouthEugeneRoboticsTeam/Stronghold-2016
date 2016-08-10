package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoAim extends CommandGroup {
    
    public  AutoAim() {
    	addParallel(new ZeroLidar(), 10);
    	addSequential(new TargetPitchBaseline(), 10);
    	addParallel(new TargetYaw(), 5);
    	addSequential(new TargetPitchBaseline(), 10);
    	addSequential(new TargetPitch(), 1);
    }
}

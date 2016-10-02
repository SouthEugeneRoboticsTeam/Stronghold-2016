package org.usfirst.frc.team2521.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Shoots into the goal autonomously...about half the time
 */
public class AutoShoot extends CommandGroup {
    
    public  AutoShoot() {
    	// 500 is how many iterations pitch will stay at its baseline position
    	addParallel(new TargetPitch(500));
    	
    	addSequential(new ZeroLidar(), 1);
    	addSequential(new TargetYaw(), 10);
        addSequential(new SetFlyWheels(true));
        addSequential(new Delay(), 5);
    	addSequential(new SetPusher(true));
    }
}

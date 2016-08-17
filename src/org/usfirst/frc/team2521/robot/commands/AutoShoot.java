package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShoot extends CommandGroup {
    
    public  AutoShoot() {
    	addSequential(new AutoAim());
    	addParallel(new TargetPitch());
    	Timer.delay(10);
    	addSequential(new SetFlyWheels(false), 2);
        addSequential(new SetFlyWheels(true), 7);
    	addSequential(new SetPusher(true));
    	Timer.delay(1);
    }
}

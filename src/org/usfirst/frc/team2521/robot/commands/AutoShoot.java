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
        addSequential(new SetFlyWheels(true));
        Timer.delay(7);
    	addSequential(new SetPusher(true));
    	Timer.delay(1);
    }
}

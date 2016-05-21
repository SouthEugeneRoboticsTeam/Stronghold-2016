package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoAim extends CommandGroup {
    
    public  AutoAim() {
    	addSequential(new TargetPitchBaseline(), 1);
    	Timer.delay(1);
    	//addParallel(new TargetYaw());
    	//Robot.sensors.autoAimOn = false;
    }
}

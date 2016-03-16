package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoAim extends CommandGroup {
    
    public  AutoAim() {
    	Robot.sensors.autoAimOn = true;
    	addParallel(new TargetYaw());
    	addParallel(new TargetPitch());
    	Robot.sensors.autoAimOn = false;
    }
}

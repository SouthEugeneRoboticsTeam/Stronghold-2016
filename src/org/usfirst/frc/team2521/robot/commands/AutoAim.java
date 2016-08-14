package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class AutoAim extends CommandGroup {
    
    public  AutoAim() {
    	SmartDashboard.putString("Auto place","Start");
    	addSequential(new TargetPitchBaseline(false), 3);
    	boolean veryClose = Robot.sensors.getDeltaX() == 0;
    	SmartDashboard.putBoolean("Very close", veryClose);
    	if (veryClose) {
    		addSequential(new TargetPitchBaseline(true), 3);
    	}
    	addParallel(new TargetPitchBaseline(veryClose), 10.5);
    	addSequential(new TargetYaw(), 7);
    	addSequential(new ZeroLidar(), 3);
    	addSequential(new TargetPitch());
    }
}

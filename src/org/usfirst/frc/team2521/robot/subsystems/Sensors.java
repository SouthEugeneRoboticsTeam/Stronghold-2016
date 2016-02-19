package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Sensors extends Subsystem {
	private AnalogInput lidar;
	
	public Sensors(){
		lidar = new AnalogInput(RobotMap.LIDAR_PORT);
	}
    
	public boolean ballInBot(){ //get if we have the ball in the bot
		return getDistance() < RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter(){ //get if we have the ball in the shooter
		return getDistance() < RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public double getDistance(){
		double raw = lidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


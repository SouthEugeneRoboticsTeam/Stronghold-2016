package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Sensors extends Subsystem {
	private AnalogInput lidar;
	private NetworkTable table;
	private double[] blobs_default = {-1};
	
	public Sensors(){
		lidar = new AnalogInput(RobotMap.LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
	}
    
	public boolean ballInBot(){ //get if we have the ball in the bot
		return getLidarDistance() < RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter(){ //get if we have the ball in the shooter
		return getLidarDistance() < RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public double getCameraDistance(){
    	return RobotMap.HEIGHT_TO_DISTANCE_FACTOR/(getHeight());
    }
	
	private double getHeight(){
    	double height = table.getNumber("HEIGHT", 0);
    	if(height != 0){
    		height = SmartDashboard.getNumber("Height");
    	}
    	return height;
    }
	
	public double getLidarDistance(){
		double raw = lidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
	}
	
	private double[] getBlobs(){
		double[] blobs = table.getNumberArray("BLOBS", blobs_default);
		return blobs;
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


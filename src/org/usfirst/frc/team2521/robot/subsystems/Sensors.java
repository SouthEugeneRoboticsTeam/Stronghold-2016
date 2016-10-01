


package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.OI.Defense;
import org.usfirst.frc.team2521.robot.commands.SensorDefault;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Sensors extends Subsystem {
	
	private AHRS ahrs;
	private AnalogInput intakeLidar;
	private AnalogInput longLidar;
	private AnalogInput aimLidar; // Sharp GP2Y0A710K0F
	private NetworkTable table;
	
	private double deltaX = 0;
	private double lastDeltaX = 0;
	private double maxPitch = 0;
	
	private double[] blobs_default = { -1 };
	
	private boolean isTraversing = false;
	private boolean targetVisible = false;
	
	private double initYaw = 0;
	
	private double lastYaw = 0;
	private double yawOffset = 0; 
	
	private double lidarCount = 0;
	private double lidarSum = 0;
	
	public boolean autoFireOn = false;
	public boolean autoAimOn = false;
	
	public Sensors() {
		ahrs = new AHRS(SPI.Port.kMXP);
		ahrs.reset();
		aimLidar = new AnalogInput(RobotMap.AIM_LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
		SmartDashboard.putNumber("BOX_ASPECT_RATIO", 0);
	}
 
	public double updateAvgLidar(){
		lidarSum += aimLidar.getValue();
		lidarCount++;
		return lidarSum / lidarCount;
	}
	
	public double getAvgLidar(){
		return lidarSum / lidarCount;
	}
	
	public double getLidarVoltage(){
		return aimLidar.getVoltage();
	}
	
	public void zeroLidar() {
		lidarSum = 0;
		lidarCount = 0;
	}
	
	public void display() {	
		SmartDashboard.putNumber("Aim lidar", getAimLidar());
		SmartDashboard.putNumber("Avg aim lidar", getAvgLidar());
		SmartDashboard.putNumber("Pitch Relative Encoder Position", Robot.pitch.getRelativeEncoderPosition());
		SmartDashboard.putNumber("Pitch Absolute Encoder Position", Robot.pitch.getEncoderPosition());
		SmartDashboard.putNumber("Delta x", getDeltaX());
		SmartDashboard.putNumber("Motor pitch val", Robot.pitch.getMotorValue());
		SmartDashboard.putNumber("Aspect Ratio", getAspectRatio());
		SmartDashboard.putNumber("Output voltage", getLidarVoltage());
	}
	
	public double getAimLidar(){
		return aimLidar.getValue();
	}
	
	
	public double getYaw(){
		double angle = ahrs.getYaw() - initYaw; //- RobotMap.RIGHT_ANGLE;
		angle = angle % 360;
		if(angle < 0){
			angle = 360 + angle;
		}
		return 360 - angle;
	}
	
	public double getInitYaw(){
		return initYaw;
	}
	
	public double getDeltaX() {
		double[] blobs = getBlobs();
		if (blobs.length > 0) { //makes sure that there is a blob, then calculates distance off center
			deltaX = blobs[0] - RobotMap.IMAGE_WIDTH/2;
		} else {
			deltaX = 0;
			SmartDashboard.putBoolean("Target seeen", false);
		}
		
		return deltaX;
	}
	
	public double getHeight() { 
    	double height = table.getNumber("HEIGHT", 0);
    	if (height == 0) {
    		try{
    			height = SmartDashboard.getNumber("Height");
    		}catch(@SuppressWarnings("deprecation") NetworkTableKeyNotDefined e){
    			System.out.print(e.getStackTrace());
    			SmartDashboard.putNumber("Height", table.getNumber("HEIGHT", 0));
    		}
    	}
    	if (height != 0) targetVisible = true;
    	else targetVisible = false;
    	
    	return height;
    }
	public double getAspectRatio(){
		double aspect_ratio = table.getNumber("BOX_ASPECT_RATIO", 0);
    	if (aspect_ratio == 0) {
    		try{
    			aspect_ratio = SmartDashboard.getNumber("BOX_ASPECT_RATIO");
    		}catch(@SuppressWarnings("deprecation") NetworkTableKeyNotDefined e){
    			System.out.print(e.getStackTrace());
    		}
    	}
    	return aspect_ratio;
	}
	
	public double getLongLidar(){
		return longLidar.getValue();
	}
	
	private double[] getBlobs() {
		double[] blobs = table.getNumberArray("BLOBS", blobs_default);
		return blobs;
	}
	
	public double getPitch(){
		return ahrs.getPitch();
	}
	
	public void setInitYaw(){
		initYaw = ahrs.getYaw();
	}
	
	public boolean isTraversing() {
		return isTraversing;
	}

    public void initDefaultCommand() {
        setDefaultCommand(new SensorDefault());
    }
}
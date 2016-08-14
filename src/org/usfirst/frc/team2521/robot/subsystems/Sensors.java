


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
	private AnalogInput aimLidar;
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
		intakeLidar = new AnalogInput(RobotMap.INTAKE_LIDAR_PORT);
		longLidar = new AnalogInput(RobotMap.LONG_LIDAR_PORT);
		aimLidar = new AnalogInput(RobotMap.AIM_LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
	}
 
	public double updateAvgLidar(){
		lidarSum += aimLidar.getValue();
		lidarCount++;
		return lidarSum / lidarCount;
	}
	
	public double getAvgLidar(){
		return lidarSum / lidarCount;
	}
	
	public void zeroLidar() {
		lidarSum = 0;
		lidarCount = 0;
	}
	
	public boolean ballInBot() { //get if we have the ball in the bot
		return intakeLidar.getValue() > RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter() { //get if we have the ball in the shooter
		return intakeLidar.getValue() > RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public void display() {	
		SmartDashboard.putNumber("Aim lidar", getAimLidar());
		SmartDashboard.putNumber("Avg aim lidar", getAvgLidar());
		SmartDashboard.putNumber("Pitch Relative Encoder Position", Robot.pitch.getRelativeEncoderPosition());
		SmartDashboard.putNumber("Pitch Absolute Encoder Position", Robot.pitch.getEncoderPosition());
		SmartDashboard.putNumber("Delta x", getDeltaX());
		SmartDashboard.putNumber("Motor pitch val", Robot.pitch.getMotorValue());
	}
	
	public void setLights(){
		OI.getInstance().setLight(RobotMap.INTAKE_LIGHT, ballInBot());
		OI.getInstance().setLight(RobotMap.VISION_LIGHT, false);
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
	
	public double getCameraDistance() {
    	return RobotMap.HEIGHT_TO_DISTANCE_FACTOR/(getHeight());
    }
	
	public double getDeltaX() {
		//SmartDashboard.putBoolean("Delta x called", true);
		double[] blobs = getBlobs();
		if (blobs.length > 0) { //makes sure that there is a blob, then calculates distance off center
			//SmartDashboard.putNumber("X", blobs[0]);
			deltaX = blobs[0] - RobotMap.IMAGE_WIDTH/2;
			//lastDeltaX = deltaX;
			//SmartDashboard.putBoolean("Target seeen", true);
		} else {
			deltaX = 0;//RobotMap.VISION_SETPOINT;
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
	
	public double getLongLidar(){
		return longLidar.getValue();
	}
	
	public double getLidarDistance() {
		double raw = intakeLidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
	}
	
	private double[] getBlobs() {
		double[] blobs = table.getNumberArray("BLOBS", blobs_default);
		return blobs;
	}
	
	/*public double getWidth() {
		double width = table.getNumber("WIDTH", 0);
    	if (width == 0) {
    		try{
    			width = SmartDashboard.getNumber("Width");
    		}catch(@SuppressWarnings("deprecation") NetworkTableKeyNotDefined e){
    			System.out.print(e.getStackTrace());
    			SmartDashboard.putNumber("Width", table.getNumber("WIDTH", 0));
    		}
    	}
    	return width;	
	}*/
	
	public double getPitch(){
		return ahrs.getPitch();
	}
	
	public void updateTraversing() {
		//switch(OI.getInstance().getDefense()){
		//case moat:
			if (ahrs.getRoll() >= RobotMap.TRAVERSE_DEGREES) {
				isTraversing = true;
			} else if (ahrs.getRoll() <= /*-RobotMap.MOAT_TRAVERSE_DEGREES*/ 0) {
				isTraversing = false;
			}
			//break;
		//default: isTraversing = false;
		//	break;
		//}
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
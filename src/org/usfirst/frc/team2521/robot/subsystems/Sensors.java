package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.SensorDefault;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.networktables.NetworkTableKeyNotDefined;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Subsystem for handling sensor input
 */
public class Sensors extends Subsystem {
	
	private AHRS ahrs; // Navx
	private AnalogInput aimLidar; // Sharp GP2Y0A710K0F
	private NetworkTable table;
	
	private double deltaX = 0;
	
	private double[] blobs_default = { -1 };
	
	private double initYaw = 0;
	
	// Values from the lidar are often jumpy and noisy, so we take
	// the average of them over a period of time that we know will
	// have good values. These variables are used in those calculations
	private double lidarCount = 0;
	private double lidarSum = 0;
	
	public Sensors() {
		ahrs = new AHRS(SPI.Port.kMXP);
		ahrs.reset();
		aimLidar = new AnalogInput(RobotMap.AIM_LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
		SmartDashboard.putNumber("BOX_ASPECT_RATIO", 0);
	}
 
	// Public methods
	// Lidar
	/**
	 * Getter for the stored average lidar value
	 * @return the stored average lidar value
	 */
	public double getAvgLidar(){
		return lidarSum / lidarCount;
	}
	
	/**
	 * Getter for the current lidar value, unfiltered. May be jumpy
	 * @return the current reading from the lidar
	 */
	public double getRawLidar(){
		return aimLidar.getValue();
	}
	
	/**
	 * Update our stored lidar value
	 * Should be used for a few seconds at a time without moving
	 * Generally zeroLidar should be called beforehand
	 * @see zeroLidar()
	 */
	public void updateAvgLidar(){
		lidarSum += aimLidar.getValue();
		lidarCount++;
	}
	
	/**
	 * Reset stored average lidar value
	 */
	public void zeroLidar() {
		lidarSum = 0;
		lidarCount = 0;
	}
	
	// Misc
	/**
	 * Method to be called periodically to update SmartDashboard
	 * with sensor values
	 */
	public void display() {	
		SmartDashboard.putNumber("Raw aim lidar", getRawLidar());
		SmartDashboard.putNumber("Avg aim lidar", getAvgLidar());
		SmartDashboard.putNumber("Pitch Relative Encoder Position", Robot.pitch.getRelativeEncoderPosition());
		SmartDashboard.putNumber("Pitch Absolute Encoder Position", Robot.pitch.getEncoderPosition());
		SmartDashboard.putNumber("Delta x", getDeltaX());
	}
	
	/**
	 * Retrieve the yaw angle from the navx
	 * @return the yaw of the robot, as reported by the naxv
	 */
	public double getYaw(){
		double angle = ahrs.getYaw() - initYaw;
		angle = angle % 360;
		if(angle < 0){
			angle = 360 + angle;
		}
		return 360 - angle;
	}
	
	// Vision
	/**
	 * Get the aspect of the target in the Roborealm image
	 * @return aspect ratio of the target
	 */
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
	
	/**
	 * Get the coordinates of the target in the Roborealm image
	 * @return array containing target's coordinates
	 */
	private double[] getBlobs() {
		double[] blobs = table.getNumberArray("BLOBS", blobs_default);
		return blobs;
	}
	
	/**
	 * Calculate the distance off center the target is
	 * (Is affected by how far the robot is from the target)
	 * @return the difference between the center of the target and the
	 * center of the image, in pixels
	 */
	public double getDeltaX() {
		double[] blobs = getBlobs();
		// Makes sure that there is a blob, then calculates distance off center
		if (blobs.length > 0) { 		deltaX = blobs[0] - RobotMap.IMAGE_WIDTH/2;
		} else {
			deltaX = 0;
		}
		return deltaX;
	}
	
	/**
	 * Get the height of the target in the Roborealm image
	 * @return height (in pixels) of the target
	 */
	public double getHeight() { 
    	double height = table.getNumber("HEIGHT", 0);
    	if (height == 0) {
    		try{
    			height = SmartDashboard.getNumber("HEIGHT");
    		}catch(@SuppressWarnings("deprecation") NetworkTableKeyNotDefined e){
    			System.out.print(e.getStackTrace());
    			SmartDashboard.putNumber("Height", table.getNumber("HEIGHT", 0));
    		}
    	}
    	return height;
    }

	// Overloaded methods
    public void initDefaultCommand() {
        setDefaultCommand(new SensorDefault());
    }
}
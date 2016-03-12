


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
	
	public boolean autoFireOn = false;
	public boolean autoAimOn = false;
	
	public Sensors() {
		ahrs = new AHRS(SPI.Port.kMXP);
		intakeLidar = new AnalogInput(RobotMap.INTAKE_LIDAR_PORT);
		longLidar = new AnalogInput(RobotMap.LONG_LIDAR_PORT);
		table = NetworkTable.getTable("SmartDashboard");
	}
 
	public boolean ballInBot() { //get if we have the ball in the bot
		return intakeLidar.getValue() > RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter() { //get if we have the ball in the shooter
		return intakeLidar.getValue() > RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public void display() {	
		SmartDashboard.putBoolean("Target visible", targetVisible);
		//SmartDashboard.putBoolean("On target", Robot.pitch.getOnTarget() && Robot.yaw.getVisionOnTarget());
		
		SmartDashboard.putBoolean("Ball in shooter", ballInShooter());
		SmartDashboard.putBoolean("Auto aim on", autoAimOn);
		SmartDashboard.putBoolean("Auto fire on", autoFireOn);
		
		/*SmartDashboard.putNumber("Lidar distance", getLidarDistance());
		SmartDashboard.putNumber("Lidar value", intakeLidar.getValue());
		SmartDashboard.putBoolean("Ball in bot", ballInBot());
		SmartDashboard.putBoolean("Ball in shooter", ballInShooter());
		SmartDashboard.putNumber("Yaw", ahrs.getYaw());
		
		SmartDashboard.putNumber("Fixed yaw", getYaw());
		SmartDashboard.putString("Defense", OI.getInstance().getDefense().toString());
		SmartDashboard.putNumber("Setpoint", Robot.drivetrain.getSetpoint());
		SmartDashboard.putBoolean("Is traversing", isTraversing());*/
		System.out.println("Pitch" + ahrs.getPitch());
		System.out.println("Traversing" + isTraversing());
		/*//if (Math.abs(maxPitch) < Math.abs(ahrs.getPitch())) maxPitch = ahrs.getPitch();
		//SmartDashboard.putNumber("Max pitch", maxPitch);
		//SmartDashboard.putNumber("Long lidar", longLidar.getValue());
		//SmartDashboard.putNumber("Last yaw", lastYaw);
		//SmartDashboard.putBoolean("Outerworks distance", longLidar.getValue() < RobotMap.LIDAR_OUTER_WORKS_THRESHOLD);
		//setLights();
		SmartDashboard.putNumber("Encoder position", Robot.pitch.getEncoderPosition());
		//SmartDashboard.putNumber("Height", getHeight()); 
		SmartDashboard.putBoolean("Target visible", targetVisible);
		SmartDashboard.putNumber("Wheel enc speed", Robot.flyWheels.getEncVelocity());
		SmartDashboard.putNumber("Target enc position", Robot.pitch.getTargetEncoderPosition());*/
	}
	
	public void setLights(){
		OI.getInstance().setLight(RobotMap.INTAKE_LIGHT, ballInBot());
		//OI.getInstance().setLight(RobotMap.WHEELS_LIGHT, Robot.flyWheels.getUpToSpeed());
		OI.getInstance().setLight(RobotMap.VISION_LIGHT, false);
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
		double[] blobs = getBlobs();
		if (blobs.length > 1) { //makes sure that there is a blob, then calculates distance off center
			deltaX = blobs[1] - RobotMap.IMAGE_WIDTH/2;
			lastDeltaX = deltaX;
		} else {
			deltaX = 0;//RobotMap.VISION_SETPOINT;
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
			if (ahrs.getPitch() >= RobotMap.TRAVERSE_DEGREES) {
				isTraversing = true;
			} else if (ahrs.getPitch() <= /*-RobotMap.MOAT_TRAVERSE_DEGREES*/ 0) {
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
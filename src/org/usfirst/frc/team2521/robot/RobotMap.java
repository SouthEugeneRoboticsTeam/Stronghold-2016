
package org.usfirst.frc.team2521.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Ports
	public static final int LEFT_STICK_PORT = 0;
	public static final int RIGHT_STICK_PORT = 1;
	public static final int SECONDARY_STICK_PORT = 2;
	
	public static final int FRONT_LEFT_MOTOR = 46; //Real
	public static final int FRONT_RIGHT_MOTOR = 48; //Real
	public static final int REAR_LEFT_MOTOR = 47; //Real
	public static final int REAR_RIGHT_MOTOR = 49; //Real
	
	public static final int LEFT_SHOOTER_MOTOR = 50; //Either this or 41 is the real value
	public static final int RIGHT_SHOOTER_MOTOR = 40; //Either this or 50 is the real value
	
	public static final int INTAKE_WHEEL_MOTOR = 30; //Real
	
	public static final int TARGETING_YAW_MOTOR = 31; //Real
	public static final int TARGETING_PITCH_MOTOR = 41; //Real
	
	public static final int PUSHER_IN_PORT = 1;
	public static final int PUSHER_OUT_PORT = 0;
	// Find actual numbers later.
	
	public static final int LIDAR_PORT = 0;
	
	// Buttons
	public static final int INTAKE_BUTTON_IN = 8;
	public static final int INTAKE_BUTTON_OUT = 3;
	public static final int SHOOTER_BUTTON_IN = 4;
	public static final int SHOOTER_BUTTON_OUT = 5;
	public static final int PUSHER_BUTTON = 1;
	public static final int FIRE_BUTTON = 6; // Real
	public static final int AUTO_INTAKE_BUTTON = 7; //Tester
	public static final int LINKED_INTAKE_BUTTON = 2;
	
	//Other constants
	public static final double LIDAR_FACTOR = 10940; //Calculated
	public static final double LIDAR_OFFSET = 145.2; //Calculated
	public static final double HEIGHT_TO_DISTANCE_FACTOR = 4138.375054; //calculated from measured data
	public static final double ENC_COUNTS_PER_RADIAN = 121/(0.5*Math.PI);//measured
	public static final double VISION_SETPOINT = 0;
	public static final double YAW_SENSITIVITY = 0.5;
	public static final double AUTO_SPEED = 0.25;
	public static final double AUTO_TIME = 5;
	
	public static final int AUTO_DISTANCE = 700;
	public static final int IMAGE_WIDTH = 320;
	
	// Thresholds
	public static final int FINISHED_SPIN_UP_THRESHOLD = 100;
	public static final int YAW_ERROR_THRESHOLD = 10;
	public static final int PITCH_ERROR_THRESHOLD = 10;
	
	public static final double LIDAR_IN_BOT_THRESHOLD = 20;
	public static final double LIDAR_IN_SHOOTER_THRESHOLD = 4;
	
	public static final double YAW_P = 1;
	public static final double YAW_I = 0;
	public static final double YAW_D = 0;
	
	public static final double PITCH_P = 1;
	public static final double PITCH_I = 0;
	public static final double PITCH_D = 0;
	
	public static final double DRIVE_P = 1;
	public static final double DRIVE_I = 0;
	public static final double DRIVE_D = 0;
	
	public static final int CAMERA_HEIGHT = 38; //in inches; needs to be fixed
	
	public static final float TRAVERSE_DEGREES = 12;
	
	
}

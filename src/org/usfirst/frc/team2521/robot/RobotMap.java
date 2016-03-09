
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
	public static final int CUSTOM_PORT = 3;
	
	public static int FRONT_LEFT_MOTOR;
	public static int FRONT_RIGHT_MOTOR;
	public static int REAR_LEFT_MOTOR;
	public static int REAR_RIGHT_MOTOR;
	
	public static int LEFT_SHOOTER_MOTOR = 50;
	public static int RIGHT_SHOOTER_MOTOR = 40;
	
	public static final int INTAKE_WHEEL_MOTOR = 30; //Real
	
	public static final int TARGETING_YAW_MOTOR = 31; //Real
	public static int TARGETING_PITCH_MOTOR;
	
	public static final int PUSHER_IN_PORT = 1;
	public static final int PUSHER_OUT_PORT = 0;
	public static final int LOCK_PORT = 2;
	// Find actual numbers later.
	
	public static final int INTAKE_LIDAR_PORT = 0;
	public static final int LONG_LIDAR_PORT = 1;
	
	public static final int LEFT_ENCODER_A = 0;
	public static final int LEFT_ENCODER_B = 1;
	public static final int RIGHT_ENCODER_A = 2;
	public static final int RIGHT_ENCODER_B = 3;
	
	public static final int FLYWHEEL_SWITCH = 0;
	
	// Buttons
	/*public static final int INTAKE_BUTTON_IN = 8;
	public static final int INTAKE_BUTTON_OUT = 3;
	public static final int SHOOTER_BUTTON_IN = 4;
	public static final int SHOOTER_BUTTON_OUT = 5;
	public static final int PUSHER_BUTTON = 1;
	public static final int FIRE_BUTTON = 6; // Real
	public static final int AUTO_INTAKE_BUTTON = 1; //Real
	public static final int LINKED_INTAKE_BUTTON = 2;
	public static final int LOCK_BUTTON = 7;*/
	
	
	//Right joystick buttons
	public static final int CHEVAL_BUTTON = 1;
	
	//Secondary joystick buttons
	public static final int PUSHER_BUTTON = 1;
	public static final int SHOOTER_BUTTON_IN = 3;
	public static final int SHOOTER_BUTTON_OUT = 4;
	public static final int INTAKE_BUTTON_IN = 5;
	public static final int INTAKE_BUTTON_OUT = 6;
	
	public static final int LOCK_BUTTON = 7;
	
	public static final int AUTO_AIM_BUTTON = 9;
	public static final int AUTO_INTAKE_BUTTON = 11;
	public static final int AUTO_SHOOT_BUTTON = 12;
	
	//Lights
	public static final int INTAKE_LIGHT = 0;
	public static final int WHEELS_LIGHT = 1;
	public static final int VISION_LIGHT = 2;
	public static final int OTHER_LIGHT = 3;
	
	//Other constants
	public static final double PITCH_VISION_BASELINE = 600;
	public static final double FIND_TARGET_DELAY = 5;
	public static final double LIDAR_FACTOR = 10940; //Calculated
	public static final double LIDAR_OFFSET = 145.2; //Calculated
	public static final double HEIGHT_TO_DISTANCE_FACTOR = 4138.375054; //calculated from measured data
	//public static final double ENC_COUNTS_PER_RADIAN = 121/(0.5*Math.PI);//measured
	public static final double VISION_SETPOINT = 0;
	public static final double IMAGE_WIDTH = 320; //Change if you crop!
	public static final double YAW_SENSITIVITY = 0.25;
	public static final double AUTO_SPEED = 0.25;
	public static final double AUTO_TIME = 5;
	public static final int AUTO_DISTANCE = 700;
	
	
	// Thresholds
	public static final int FINISHED_SPIN_UP_THRESHOLD = 100;
	public static final int YAW_ERROR_THRESHOLD = 10;
	public static final int PITCH_ERROR_THRESHOLD = 10;
	
	public static final double LIDAR_IN_BOT_THRESHOLD = 720; //uses .getValue()
	public static final double LIDAR_IN_SHOOTER_THRESHOLD = 3440; //uses .getValue()
	public static final double LIDAR_OUTER_WORKS_THRESHOLD = 1200; //uses .getValue()
	
	public static final double RIGHT_ANGLE = -140;
	
	public static final double YAW_P = 1;
	public static final double YAW_I = 0;
	public static final double YAW_D = 0;
	
	public static final double YAW_VISION_P = 1;
	public static final double YAW_VISION_I = 0;
	public static final double YAW_VISION_D = 0;
	
	public static final double PITCH_P = 1;
	public static final double PITCH_I = 0;
	public static final double PITCH_D = 0;
	
	public static final double DRIVE_TURN_P = 0.03;
	public static final double DRIVE_TURN_I = 0;
	public static final double DRIVE_TURN_D = 0;
	
	public static final double DRIVE_P = 1;
	public static final double DRIVE_I = 0;
	public static final double DRIVE_D = 0;
	
	public static final int CAMERA_HEIGHT = 38; //in inches; needs to be fixed
	
	public static final float TRAVERSE_DEGREES = 12;
	
	public static final double ENCODER_RANGE = 1300;
	
	public static void setMotors(){
		if(Robot.test_platform){
			FRONT_LEFT_MOTOR = 42;
			FRONT_RIGHT_MOTOR = 43;
			REAR_LEFT_MOTOR = 45;
			REAR_RIGHT_MOTOR = 44;
			
			TARGETING_PITCH_MOTOR = 40; //Real
			LEFT_SHOOTER_MOTOR = 51;
			RIGHT_SHOOTER_MOTOR = 56;
			
			
		}else {
			FRONT_LEFT_MOTOR = 46; //Real
			FRONT_RIGHT_MOTOR = 48; //Real
			REAR_LEFT_MOTOR = 47; //Real
			REAR_RIGHT_MOTOR = 49; //Real
			
			TARGETING_PITCH_MOTOR = 41; //Real
			LEFT_SHOOTER_MOTOR = 50;
			RIGHT_SHOOTER_MOTOR = 40;
		}
	}
}

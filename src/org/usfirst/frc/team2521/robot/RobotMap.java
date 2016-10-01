
package org.usfirst.frc.team2521.robot;

public class RobotMap {
	
	// Ports
	//Joysticks
	public static final int LEFT_STICK_PORT = 0;
	public static final int RIGHT_STICK_PORT = 1;
	public static final int SECONDARY_STICK_PORT = 2;
	
	//Motors
	public static final int FRONT_LEFT_MOTOR = 42;
	public static final int FRONT_RIGHT_MOTOR = 43;
	public static final int REAR_LEFT_MOTOR = 45;
	public static final int REAR_RIGHT_MOTOR = 44;
	
	public static final int TARGETING_PITCH_MOTOR = 52;
	public static final int LEFT_SHOOTER_MOTOR = 56;
	public static final int RIGHT_SHOOTER_MOTOR = 51;
	public static final int TARGETING_YAW_MOTOR = 40;
	
	//Pneumatics
	public static final int PUSHER_IN_PORT = 1;
	public static final int PUSHER_OUT_PORT = 0;
	
	//Lidar
	public static final int AIM_LIDAR_PORT = 2;
	//Measurements used to calculate the constant:
	//Distances: 67.5 in, 83 in, 1.65 in
	//Voltages: 1.9 V, 1.8 V, 1.65 V
	
	//Encoder
	public static final int LEFT_ENCODER_A = 0;
	public static final int LEFT_ENCODER_B = 1;
	
	public static final int RIGHT_ENCODER_A = 2;
	public static final int RIGHT_ENCODER_B = 3;
	
	public static final int FLYWHEEL_ENCODER_SWITCH = 0;
	
	//Buttons
	//Secondary joystick buttons
	public static final int PUSHER_BUTTON = 1;
	public static final int SHOOTER_BUTTON_IN = 3;
	public static final int SHOOTER_BUTTON_OUT = 4;
	
	
	//Other constants
	//Vision
	public static final double PITCH_VISION_BASELINE = 600;
	public static final double FIND_TARGET_DELAY = 5;
	public static final double IMAGE_WIDTH = 320;
	
	public static final double ENCODER_RANGE = 1400;
	
	public static final double YAW_P = 0.005;
	public static final double YAW_I = 0.0001;
	public static final double YAW_D = 0;
	
	public static final double PITCH_P = 0.5;
	public static final double PITCH_I = 0;
	public static final double PITCH_D = 0;
	
	//Constants used for Drivetrain
	//Tuned for old system
	public static final double DRIVE_TURN_P = 0.03;
	public static final double DRIVE_TURN_I = 0;
	public static final double DRIVE_TURN_D = 0;
	
	public static final double DRIVE_P = 1;
	public static final double DRIVE_I = 0;
	public static final double DRIVE_D = 0;
}

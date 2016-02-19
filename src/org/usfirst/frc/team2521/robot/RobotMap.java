
package org.usfirst.frc.team2521.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	// Buttons
	public static final int START_SPIN_BUTTON = 3;
	public static final int STOP_SPIN_BUTTON = 2;
	public static final int INTAKE_BUTTON = 6;
	public static final int FIRE_BUTTON = 1;
	
	// Thresholds
	public static final int FINISHED_SPIN_UP_THRESHOLD = 100;
	
	// Ports
	public static final int LEFT_STICK_PORT = 0;
	public static final int RIGHT_STICK_PORT = 1;
	public static final int SECONDARY_STICK_PORT = 2;
	
	public static final int FRONT_LEFT_MOTOR = 50;
	public static final int FRONT_RIGHT_MOTOR = 51;
	public static final int REAR_LEFT_MOTOR = 52;
	public static final int REAR_RIGHT_MOTOR = 53;
	
	public static final int LEFT_SHOOTER_MOTOR = 54;
	public static final int RIGHT_SHOOTER_MOTOR = 55;
	
	public static final int PUSHER_IN_PORT = 0;
	public static final int PUSHER_OUT_PORT = 0;
	
	public static final int INTAKE_WHEEL_MOTOR = 55;
	
	public static final int TARGETING_YAW_MOTOR = 42;
	public static final int TARGETING_PITCH_MOTOR = 69;
}

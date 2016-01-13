
package org.usfirst.frc.team2521.robot;

import edu.wpi.first.wpilibj.Joystick;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick translate;
	private Joystick rotate;
	private static OI instance;
	
	public OI() {
		translate = new Joystick(RobotMap.TRANSLATE_PORT);
		rotate = new Joystick(RobotMap.ROTATE_PORT);
	}
	
	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		
		return instance;
	}
	
	public Joystick getTranslateStick() {
		return translate;
	}
	
	public Joystick getRotateStick() {
		return rotate;
	}
}


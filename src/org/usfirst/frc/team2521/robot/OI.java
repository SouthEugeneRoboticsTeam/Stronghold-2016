
package org.usfirst.frc.team2521.robot;

import org.usfirst.frc.team2521.robot.commands.AutomatedIntake;
import org.usfirst.frc.team2521.robot.commands.IntakeIn;
import org.usfirst.frc.team2521.robot.commands.ShootBall;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private Joystick left;
	private Joystick right;
	private Joystick secondary;
	
	private JoystickButton intakeButton;
	private JoystickButton fireButton;
	private JoystickButton autoIntakeButton;
	
	private static OI instance;
	
	public OI() {
		left = new Joystick(RobotMap.LEFT_STICK_PORT);
		right = new Joystick(RobotMap.RIGHT_STICK_PORT);
		secondary = new Joystick(RobotMap.SECONDARY_STICK_PORT);
	}
	
	public static OI getInstance() {
		if (instance == null) {
			instance = new OI();
		}
		
		return instance;
	}
	
	public Joystick getLeftStick() {
		return left;
	}
	
	public Joystick getRightStick() {
		return right;
	}
	
	public Joystick getSecondaryStick() {
		return secondary;
	}
	
	public void initButtons() {
		intakeButton = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON);
		fireButton = new JoystickButton(secondary, RobotMap.FIRE_BUTTON);
		autoIntakeButton = new JoystickButton(secondary, RobotMap.AUTO_INTAKE_BUTTON);
	}
	
	public void tieButtons() {
		fireButton.whenPressed(new ShootBall());
		intakeButton.whileHeld(new IntakeIn());
		autoIntakeButton.whenPressed(new AutomatedIntake());
	}
}

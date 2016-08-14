
package org.usfirst.frc.team2521.robot;

import org.usfirst.frc.team2521.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private Preferences prefs;
	
	private Joystick left;
	private Joystick right;
	private Joystick secondary;
	private Joystick custom;
	
	private JoystickButton intakeButtonIn;
	private JoystickButton intakeButtonOut;
	private JoystickButton driveIntakeOut;
	
	private JoystickButton shooterButtonIn;
	private JoystickButton shooterButtonOut;
	
	private JoystickButton autoIntakeButton;
	private JoystickButton autoAimButton;
	private JoystickButton autoShootButton;
	private JoystickButton autoResetShooterButton;
	
	private JoystickButton manipulatorUp;
	private JoystickButton manipulatorDown;
	
	private JoystickButton pusherButton;
	
	private JoystickButton lockButton;
	
	private JoystickButton spinFrontButton;
	private JoystickButton spinBackButton;
	
	private JoystickButton gotoAimPosButton;
	
	private JoystickButton aimYawButton;
	
	private static OI instance;
	
	private int fieldPosition;
	private Defense defense;
	private AutoMode auto;
	private boolean slowMode;
	private double slowModeFactor;
	private boolean arcadeMode;
	
	public OI() {
		secondary = new Joystick(RobotMap.SECONDARY_STICK_PORT);
		right = new Joystick(RobotMap.RIGHT_STICK_PORT);
		left = new Joystick(RobotMap.LEFT_STICK_PORT);
		custom = new Joystick(RobotMap.CUSTOM_PORT);
		setPrefs();
		initButtons();
	}
	
	public void setPrefs(){
		prefs = Preferences.getInstance();
		fieldPosition = prefs.getInt("Field Position", 3);
		slowMode = prefs.getBoolean("Slow mode?", false);
		arcadeMode = prefs.getBoolean("Arcade mode?", false);
		slowModeFactor = prefs.getDouble("Slow mode factor", 0.5);
		switch(prefs.getInt("Defense", 0)){
		case 1: defense = Defense.portcullis;
			break;
		case 2: defense = Defense.chevalDeFrise;
		case 3: defense = Defense.moat;
			break;
		case 4: defense = Defense.ramparts;
			break;
		case 5: defense = Defense.drawbridge;
			break;
		case 6: defense = Defense.sallyPort;
			break;
		case 7: defense = Defense.roughTerrain;
			break;
		case 8: defense = Defense.rockWall;
			break;
		default: defense = Defense.lowBar;
		}
		switch(prefs.getInt("Auto Mode", 1)){
		case 1: auto = AutoMode.traverseOnly;
			break;
		case 2: auto = AutoMode.traverseAndReturn;
			break;
		case 3: auto = AutoMode.traverseAndLowGoal;
			break;
		default: auto = AutoMode.traverseOnly;
		}
	}
	
	public enum Defense {
		portcullis,
		chevalDeFrise,
		moat,
		ramparts,
		drawbridge,
		sallyPort,
		rockWall,
		roughTerrain,
		lowBar
	}
	
	public enum AutoMode {
		traverseOnly,
		traverseAndReturn,
		traverseAndLowGoal,
		traverseAndHighGoal,
		none
	}
	
	public Defense getDefense(){
		return defense;
	}
	
	public AutoMode getAutoMode(){
		return auto;
	}
	
	public int getFieldPosition(){
		return fieldPosition;
	}
	
	public boolean getSlowMode(){
		return slowMode;
	}
	
	public boolean getArcadeMode(){
		return arcadeMode;
	}
	
	public double getSlowModeFactor(){
		return slowModeFactor;
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
	
	public void setLight(int lightNumber, boolean value){
		custom.setOutput(lightNumber, value);
	}
	
	public void initButtons() {
		//intakeButtonIn = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON_IN);
		//intakeButtonOut = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON_OUT);
		if(Robot.test_platform){
			shooterButtonIn = new JoystickButton(secondary, RobotMap.SHOOTER_BUTTON_IN);
			shooterButtonOut = new JoystickButton(secondary, RobotMap.SHOOTER_BUTTON_OUT);
			pusherButton = new JoystickButton(secondary, RobotMap.PUSHER_BUTTON);
			gotoAimPosButton = new JoystickButton(secondary, RobotMap.GOTO_PITCH_AIM_BUTTON);
		} else{
			manipulatorUp = new JoystickButton(right, RobotMap.MANIPULATOR_UP_BUTTON);
			manipulatorDown = new JoystickButton(right, RobotMap.MANIPULATOR_DOWN_BUTTON);
			
			spinFrontButton = new JoystickButton(right, RobotMap.SPIN_BUTTON_FRONT);
			spinBackButton = new JoystickButton(right, RobotMap.SPIN_BUTTON_BACK);
		}
		aimYawButton = new JoystickButton(secondary, RobotMap.AIM_YAW_BUTTON);
		//autoIntakeButton = new JoystickButton(secondary, RobotMap.AUTO_INTAKE_BUTTON);
		//pusherButton = new JoystickButton(secondary, RobotMap.PUSHER_BUTTON);
		//driveIntakeOut = new JoystickButton(secondary, RobotMap.INTAKE_BUTTON_OUT);
		//lockButton = new JoystickButton(secondary, RobotMap.LOCK_BUTTON);
		//autoAimButton = new JoystickButton(secondary, RobotMap.AUTO_AIM_BUTTON);
		//autoShootButton = new JoystickButton(secondary, RobotMap.AUTO_SHOOT_BUTTON);
		//autoIntakeButton = new JoystickButton(secondary, RobotMap.AUTO_INTAKE_BUTTON);
		//autoResetShooterButton = new JoystickButton(secondary, RobotMap.RESET_SHOOTER_BUTTON);
		
		
		
		tieButtons();
	}
	
	public void tieButtons() {
		//fireButton.whenPressed(new ShootBall());
		//autoIntakeButton.whenPressed(new AutoIntake());
		//autoAimButton.whenPressetd(new Target());
		//autoShootButton.whenPressed(new AutoShoot());
		
		//intakeButtonIn.whenPressed(new IntakeIn());
		//intakeButtonIn.whenReleased(new IntakeStop());
		//intakeButtonOut.whenPressed(new IntakeOut());
		//intakeButtonOut.whenReleased(new IntakeStop());
		if(Robot.test_platform){
			shooterButtonIn.whenPressed(new SetFlyWheels(false));
			shooterButtonOut.whenPressed(new SetFlyWheels(true));
			shooterButtonIn.whenReleased(new StopFlyWheels());
			shooterButtonOut.whenReleased(new StopFlyWheels());
			pusherButton.whenPressed(new SetPusher(true));
			pusherButton.whenReleased(new SetPusher(false));
			gotoAimPosButton.whileHeld(new TargetPitchBaseline(false));
			aimYawButton.whileHeld(new TargetYaw());
		} else{
			manipulatorUp.whenPressed(new ManipulatorUp());
			manipulatorDown.whenPressed(new ManipulatorDown());
			
			manipulatorUp.whenReleased(new ManipulatorReset());
			manipulatorDown.whenReleased(new ManipulatorReset());
		}
		//pusherButton.whenPressed(new SetPusher(true));
		///pusherButton.whenReleased(new SetPusher(false));
		//lockButton.whenPressed(new SetLock(true));
		//lockButton.whenReleased(new SetLock(false));
		//autoResetShooterButton.whenPressed(new ResetShooter());
		
		
		
		//spinFrontButton.whenPressed(new ToAngle(179));
		//spinBackButton.whenPressed(new ToAngle(0));
		//spinButton.whenReleased(new DisableDrivetrainPID());
	}
}

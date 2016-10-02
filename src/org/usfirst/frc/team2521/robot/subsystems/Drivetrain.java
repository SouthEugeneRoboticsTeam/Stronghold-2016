package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleopDrivetrain;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for controlling drivetrain. Generally DrivetrainPUD
 * should be used since it has the automated turning commands,
 * but this subsystem can serve as a simpler fallback for testing
 */
public class Drivetrain extends Subsystem {
	
	private RobotDrive frontDrive;
	private RobotDrive rearDrive;
	
	private CANTalon frontLeft, frontRight, rearLeft, rearRight;
	
	public Drivetrain() {
		frontLeft = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		frontRight = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		rearLeft = new CANTalon(RobotMap.REAR_LEFT_MOTOR);
		rearRight = new CANTalon(RobotMap.REAR_RIGHT_MOTOR);
		
		frontLeft.enableControl();
		frontRight.enableControl();
		rearLeft.enableControl();
		rearRight.enableControl();
		
		frontDrive = new RobotDrive(rearLeft, rearRight);
		rearDrive = new RobotDrive(rearLeft, rearRight);
	}

	// Public methods
	/**
	 * Drive robot using one joystick
	 */
	public void arcadeDrive() {
		Joystick left = OI.getInstance().getLeftStick();
		
		if(OI.getInstance().getSlowMode()){
			frontDrive.arcadeDrive(left.getY()*OI.getInstance().getSlowModeFactor(), left.getX()*OI.getInstance().getSlowModeFactor());
			rearDrive.arcadeDrive(left.getY()*OI.getInstance().getSlowModeFactor(), left.getX()*OI.getInstance().getSlowModeFactor());
		}
		frontDrive.arcadeDrive(left);
		rearDrive.arcadeDrive(left);
	}
	
	/**
	 * Drive robot using two joysticks (one on each side)
	 */
	public void tankDrive() {
		double left = OI.getInstance().getLeftStick().getY();
		double right = OI.getInstance().getRightStick().getY();
		
		if(OI.getInstance().getSlowMode()){
			left *= OI.getInstance().getSlowModeFactor();
			right *= OI.getInstance().getSlowModeFactor();
		} else {
			frontDrive.tankDrive(right, left);
			rearDrive.tankDrive(right, left);
		}
	}
	
	/**
	 * Method that will be called during TeleopDrivetrain command
	 */
	public void teleoperatedDrive() {
		frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		frontRight.changeControlMode(TalonControlMode.PercentVbus);
		rearLeft.changeControlMode(TalonControlMode.PercentVbus);
		rearRight.changeControlMode(TalonControlMode.PercentVbus);
		
		tankDrive();
	}
	
	/**
	 * Set master motors to the given value regardless of mode, then
	 * switch the slaves to follower mode and have them follow the 
	 * masters.
	 * 
	 * @param value	the value that will be passed to the master motors
	 */
	public void set(double value) {
		frontRight.set(value);
		frontLeft.set(value);
		
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(RobotMap.FRONT_RIGHT_MOTOR);
		
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(RobotMap.FRONT_LEFT_MOTOR);
	}
	
	/**
	 * Switch talon control modes to use encoder position, then pass
	 * the encoder position to every motor
	 *  
	 * @param encoderPosition	the encoder position to be set
	 */
	public void setPosition(int encoderPosition) {
		frontLeft.changeControlMode(TalonControlMode.Position);
		frontRight.changeControlMode(TalonControlMode.Position);
		rearLeft.changeControlMode(TalonControlMode.Position);
		rearRight.changeControlMode(TalonControlMode.Position);
		
		frontLeft.set(encoderPosition);
		frontRight.set(encoderPosition);
		rearLeft.set(encoderPosition);
		rearRight.set(encoderPosition);
	}
	
	// Overloaded methods
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopDrivetrain());
	}
}

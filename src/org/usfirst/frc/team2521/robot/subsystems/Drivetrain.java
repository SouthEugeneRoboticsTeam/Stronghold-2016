
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleoperatedDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	//Configured for rhino
	
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
	
	public void tankDrive() {
		double left = OI.getInstance().getLeftStick().getY();
		double right = OI.getInstance().getRightStick().getY();
		
		frontDrive.tankDrive(left, right);
		rearDrive.tankDrive(left, right);
	}
	
	public void arcadeDrive() {
		Joystick left = OI.getInstance().getLeftStick();
		
		frontDrive.arcadeDrive(left);
		rearDrive.arcadeDrive(left);
	}
	
	public void teleoperatedDrive() {
		tankDrive();
	}
	
	public void setEncPosition(int encoderPosition) {
		frontLeft.setEncPosition(encoderPosition);
		frontRight.setEncPosition(encoderPosition);
		rearLeft.setEncPosition(encoderPosition);
		rearRight.setEncPosition(encoderPosition);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleoperatedDrive());
	}
}

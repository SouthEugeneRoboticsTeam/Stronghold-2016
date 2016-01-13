
package org.usfirst.frc.team2521.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleoperatedDrive;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;

/**
 *
 */
public class Drivetrain extends Subsystem {
	
	private RobotDrive drive;

	CANTalon frontLeft, frontRight, rearLeft, rearRight;
	
	public Drivetrain() {
		frontLeft = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		frontLeft.enableBrakeMode(true);
		frontLeft.enableControl();
		
		rearLeft = new CANTalon(RobotMap.REAR_LEFT_MOTOR);
		rearLeft.enableBrakeMode(true);
		rearLeft.enableControl();
		
		frontRight = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		frontRight.enableBrakeMode(true);
		frontRight.enableControl();
		
		rearRight = new CANTalon(RobotMap.REAR_RIGHT_MOTOR);
		rearRight.enableBrakeMode(true);
		rearRight.enableControl();

		drive = new RobotDrive(frontLeft, rearLeft, frontRight, rearRight);
		drive.setInvertedMotor(MotorType.kFrontLeft, true);
		drive.setInvertedMotor(MotorType.kRearLeft, true);
	}
	
	public void tankDrive() {
		double left = OI.getInstance().getTranslateStick().getY();
		double right = OI.getInstance().getRotateStick().getY();
		drive.tankDrive(left, right);
	}
	
	public void teleoperatedDrive() {
		tankDrive();
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleoperatedDrive());
    }
}


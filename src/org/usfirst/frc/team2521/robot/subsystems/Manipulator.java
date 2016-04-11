
package org.usfirst.frc.team2521.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Manipulator extends Subsystem {
	
	CANTalon manipulator;
	
	public Manipulator() {
		manipulator = new CANTalon(RobotMap.MANIPULATOR_MOTOR);
		manipulator.enableControl();
	}
	
	public void up() {
		manipulator.set(RobotMap.MANIPULATOR_SPEED);
		SmartDashboard.putBoolean("Man up?", true);
		SmartDashboard.putNumber("Man speed", manipulator.get());
	}
	
	public void down() {
		manipulator.set(-RobotMap.MANIPULATOR_SPEED);
		SmartDashboard.putBoolean("Man up?", false);
		SmartDashboard.putNumber("Man speed", manipulator.get());
	}
	
	public void stop() {
		manipulator.set(0);
		SmartDashboard.putNumber("Man speed", manipulator.get());
	}
	
	public double getEncoderPosition() {
		return manipulator.getEncPosition();
	}
	
	public void initDefaultCommand() {
	}
}

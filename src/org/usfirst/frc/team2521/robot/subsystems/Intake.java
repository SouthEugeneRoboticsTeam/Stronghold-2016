package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * 
 */
public class Intake extends Subsystem {

	private CANTalon intake;

	public void initDefaultCommand() {
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
	}

	public void startIntake() {
		intake.set(1);
	}
	public void ReleaseBall(){
		intake.set(-1);
	}
}

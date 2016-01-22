
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * 
 */
public class Intake extends Subsystem {
	
	private CANTalon intake;
	
	private boolean isBallHeld;
	
	public void initDefaultCommand() {
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
		
		isBallHeld = false;
	}
	
	public void startIntake() {
		intake.set(1);
	}
	
	public void releaseBall() {
		intake.set(-1);
		
		isBallHeld = false;
	}
	
	public void ballHeld() {
		isBallHeld = true;
	}
	
	public boolean getBallHeld() {
		return isBallHeld;
	}
}

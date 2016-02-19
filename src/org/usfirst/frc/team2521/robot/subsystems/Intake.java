
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/** 
 * 
 */
public class Intake extends Subsystem {
	
	private CANTalon intake;
	
	public Intake(){
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
	}
	
	public void initDefaultCommand() {
	}
	
	public void in() {
		intake.set(1);
	}
	
	public void out() {
		intake.set(-1);
	}
	
	public void stop(){
		intake.set(0);
	}
	
}

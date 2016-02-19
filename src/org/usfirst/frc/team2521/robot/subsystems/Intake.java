
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
	private AnalogInput lidar;
	
	private boolean isBallHeld;
	
	public Intake(){
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
		lidar = new AnalogInput(RobotMap.LIDAR_PORT);
		
		isBallHeld = false;
	}
	
	public double getDistance(){
		double raw = lidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
	}
	
	public void initDefaultCommand() {
	}
	
	public void startIntake() {
		intake.set(1);
	}
	
	public void releaseBall() {
		intake.set(-1);
		
		isBallHeld = false;
	}
	
	public boolean getBallHeld() {
		return isBallHeld;
	}
}

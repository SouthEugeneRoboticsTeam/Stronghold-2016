
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
	
	public Intake(){
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
		lidar = new AnalogInput(RobotMap.LIDAR_PORT);
	}
	
	public boolean ballInBot(){ //get if we have the ball in the bot
		return getDistance() < RobotMap.LIDAR_IN_BOT_THRESHOLD;
	}
	
	public boolean ballInShooter(){ //get if we have the ball in the shooter
		return getDistance() < RobotMap.LIDAR_IN_SHOOTER_THRESHOLD;
	}
	
	public double getDistance(){
		double raw = lidar.getValue();
		return RobotMap.LIDAR_FACTOR/(raw - RobotMap.LIDAR_OFFSET);
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

package org.usfirst.frc.team2521.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;;

/**
 * 
 */
public class Shooter extends Subsystem {

	private CANTalon shooter;

	public void initDefaultCommand() {
		shooter = new CANTalon(RobotMap.FLY_WHEEL_MOTOR);
	}

	public void startSpinUp() {
		shooter.set(1);
	}

	public void stopSpinUp() {
		shooter.set(0);
	}
	public void PitchUp(){
		
	}
	public void PitchDown(){
		
	}
    public void YawRight(){
    	
    }
    public void YawLeft(){
    	
    }

}

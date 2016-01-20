package org.usfirst.frc.team2521.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;;

/**
 * 
 */
public class Shooter extends Subsystem {

	private CANTalon shooter;
	private Intake intake; 
	//The targeting motors. 
	private CANTalon YawMotor;
	private CANTalon PitchMotor;
	
	public void initDefaultCommand() {
		shooter = new CANTalon(RobotMap.FLY_WHEEL_MOTOR);
		YawMotor = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		PitchMotor = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		
		intake = new Intake();
	}

	public void startSpinUp() {
		//Preparing the motor for firing.
		shooter.set(1);
	}
	public void FireBall(){
		intake.ReleaseBall();
	}

	public void stopSpinUp() {
		shooter.set(0);
		//Reset down the flywheel. 
	}
	public void PitchUp(){
		PitchMotor.set(1);
	}
	public void PitchDown(){
		PitchMotor.set(-1);
	}
    public void YawRight(){
    	YawMotor.set(1);
    }
    public void YawLeft(){
    	YawMotor.set(-1);
    }

}

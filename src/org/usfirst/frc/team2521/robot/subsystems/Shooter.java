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
	
	private boolean OnTarget;
	//Checks to see if vision is on the target or not. 
	public boolean YawLeft;
	public boolean YawRight;
	public boolean PitchPositive;
	public boolean PitchNegative;
	//Four bools that interact with vision. Help specify which method to call in. 
	
	public void initDefaultCommand() {
		shooter = new CANTalon(RobotMap.FLY_WHEEL_MOTOR);
		YawMotor = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		PitchMotor = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		
		intake = new Intake();
		OnTarget = false; 
		
		YawLeft = false;
		YawRight = false; 
		PitchPositive = false; 
		PitchNegative = false; 
	}

	public void startSpinUp() {
		//Preparing the motor for firing.
		shooter.set(1);
	}
	public void FireBall(){
		//If the ball is on the target, it will fire. If not, it will not fire. A redundancy added to avoid misfires.
		if(OnTarget){
			intake.ReleaseBall();
		}
		
		else if (!OnTarget){
			//Sends a console message. Note to self: Figure out how to send console messages in wpilibj library. 
		}
	}

	public void stopSpinUp() {
		shooter.set(0);
		//Reset down the flywheel. 
	}
	/* It should work out that the next four methods activate the motors that 
	 * adjust aiming depending on input from vision.
	 * 
	 * Positive is up and right.
	 * Negative is down and left. 
	 */
	
	public void PitchUp(){
		
		if(OnTarget){
			PitchMotor.set(0);
		}
		else if(!OnTarget && PitchPositive){
			PitchMotor.set(1);
		}
	}
	public void PitchDown(){
		if(OnTarget){
			PitchMotor.set(0);
		}
		else if(!OnTarget && PitchNegative){
			PitchMotor.set(-1);
		}
	}
    public void YawRight(){
    	if(OnTarget){
			YawMotor.set(0);
		}
		else if(!OnTarget && YawRight){
			YawMotor.set(1);
		}
    }
    public void YawLeft(){
    	if(OnTarget){
			YawMotor.set(0);
		}
		else if(!OnTarget && YawLeft){
			YawMotor.set(-1);
		}
    }
    //The method that will be invoked by vision when target is on sight. 
    public void AllTrue(){
    	OnTarget = true; 
    	
    	YawLeft = false;
		YawRight = false; 
		PitchPositive = false; 
		PitchNegative = false;
    }
}

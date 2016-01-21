
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;;

/**
 * 
 */
public class Shooter extends Subsystem {
	
	private Intake intake;
	
	private CANTalon shooter;
	
	private CANTalon yawMotor;
	private CANTalon pitchMotor;
	
	// If we're on target
	private boolean onTarget;
	
	public void initDefaultCommand() {
		shooter = new CANTalon(RobotMap.FLY_WHEEL_MOTOR);
		yawMotor = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		pitchMotor = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		
		intake = new Intake();
		onTarget = false;
	}
	
	public void startSpinUp() {
		// Preparing the motor for firing.
		shooter.set(1);
	}
	
	public void fireBall() {
		// If the ball is on the target, it will fire. If not, it will not fire.
		// A redundancy added to avoid misfires.
		if (onTarget) {
			intake.releaseBall();
		}
		
		else if (!onTarget) {
			// Sends a console message. Note to self: Figure out how to send
			// console messages in wpilibj library.
		}
	}
	
	public void stopSpinUp() {
		shooter.set(0);
		// Reset down the flywheel.
	}
	
	/**
	 * It should work out that the next four methods activate the motors that
	 * adjust aiming depending on input from vision. Positive is up and right.
	 * Negative is down and left.
	 */
	
	public void changePitch(double position) {
		if (onTarget) {
			pitchMotor.set(0);
		} else if (!onTarget) {
			// Add PID code to change position
			pitchMotor.set(1);
		}
	}
	
	public void changeYaw(double position) {
		if (onTarget) {
			yawMotor.set(0);
		} else if (!onTarget) {
			// Add PID code to change position
			yawMotor.set(-1);
		}
	}
	
	// The method that will be invoked by vision when target is on sight.
	public void onTarget(boolean isOn) {
		onTarget = isOn;
	}
}

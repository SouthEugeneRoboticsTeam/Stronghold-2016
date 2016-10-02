package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Subsystem for controlling the shooter's fly wheels
 * Also controls pneumatic pusher
 */
public class FlyWheels extends Subsystem {
	
	private CANTalon left;
	private CANTalon right;
	
	private DoubleSolenoid pusher;
	
	public FlyWheels() {
		left = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
		right = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
		left.enableBrakeMode(true);
		right.enableBrakeMode(true);
		
		right.changeControlMode(CANTalon.TalonControlMode.Follower);
		right.reverseOutput(true);
		
		pusher = new DoubleSolenoid(RobotMap.PUSHER_OUT_PORT, RobotMap.PUSHER_IN_PORT);
	}
	
	// Public methods
	/**
	 * Set flywheels to intake
	 */
	public void in() {
		left.set(-1);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	/**
	 * Set flywheels to shoot
	 */
	public void out() {
		left.set(1);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	/**
	 * Set flywheels to stop
	 */
	public void stop() {
		left.set(0);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}

	
	/**
	 * Set the pusher value
	 */
	public void setPusher(boolean on) {
		if (on) {
			pusher.set(Value.kForward);
		} else {
			pusher.set(Value.kReverse);
		}
	}
	
	// Overloaded methods
	public void initDefaultCommand() {}
}

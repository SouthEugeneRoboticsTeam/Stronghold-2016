
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlyWheels extends Subsystem {
	
	private CANTalon left;
	private CANTalon right;
	
	private DoubleSolenoid pusher;
	
	private DigitalInput wheelSwitch;
	private Counter counter;
	
	public FlyWheels() {
		left = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
		right = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
		
		left.enableBrakeMode(true);
		right.enableBrakeMode(true);
		
		right.changeControlMode(CANTalon.TalonControlMode.Follower);
		right.reverseOutput(true);
		
		pusher = new DoubleSolenoid(RobotMap.PUSHER_OUT_PORT, RobotMap.PUSHER_IN_PORT);
	
		wheelSwitch = new DigitalInput(RobotMap.FLYWHEEL_SWITCH);
		counter = new Counter(wheelSwitch);
	}
	
	public boolean getUpToSpeed(){
		return Math.abs(counter.getRate()) > RobotMap.FINISHED_SPIN_UP_THRESHOLD;
	}
	
	public void in() {
		left.set(-0.5);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	public void out() {
		left.set(0.25);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	public void stop() {
		left.set(0);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}

	
	public void setPusher(boolean on) {
		if (on) {
			pusher.set(Value.kForward);
		} else {
			pusher.set(Value.kReverse);
		}
	}
	
	public boolean upToSpeed(){
		double leftSpeed = left.getEncVelocity();
		double rightSpeed = right.getEncVelocity();
		return (Math.abs(leftSpeed) > RobotMap.FINISHED_SPIN_UP_THRESHOLD) && (Math.abs(rightSpeed) > RobotMap.FINISHED_SPIN_UP_THRESHOLD);
	}
	
	public void initDefaultCommand() {
	}
}

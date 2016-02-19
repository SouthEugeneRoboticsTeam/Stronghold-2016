
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.ChangePitch;
import org.usfirst.frc.team2521.robot.commands.ChangeYaw;
import org.usfirst.frc.team2521.robot.commands.FireBall;
import org.usfirst.frc.team2521.robot.commands.ShooterControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlyWheels extends Subsystem {
	
	private CANTalon left;
	private CANTalon right;
	
	private CANTalon pitch;
	private CANTalon yaw;
	
	private DoubleSolenoid pusher;
	
	public FlyWheels() {
		left = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
		right = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
		
		left.enableBrakeMode(true);
		right.enableBrakeMode(true);
		
		right.changeControlMode(CANTalon.TalonControlMode.Follower);
		right.reverseOutput(true);
		
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		
		pusher = new DoubleSolenoid(RobotMap.PUSHER_OUT_PORT, RobotMap.PUSHER_IN_PORT);
	}
	
	public void out() {
		left.set(1);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
		FireBall.fireBallEnded = true;
	}
	
	public void stop() {
		left.set(0);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	public void in() {
		left.set(-1);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	public void pitchControl() {
		Joystick secondary = OI.getInstance().getSecondaryStick();
		
		changePitch(secondary.getY());
	}
	
	public void yawControl() {
		Joystick secondary = OI.getInstance().getSecondaryStick();
		
		changeYaw(secondary.getX());
	}
	
	public void setPusher(boolean on) {
		if (on) {
			pusher.set(Value.kForward);
		} else {
			pusher.set(Value.kReverse);
		}
	}
	
	public void changePitch(double speed) {
		pitch.set(speed);
		ChangePitch.changePitchIsFinished = true;
	}
	
	public void changeYaw(double speed) {
		yaw.set(speed);
		ChangeYaw.changeYawIsFinished = true;
	}
	
	public double getLeftSpeed() {
		return left.getEncVelocity();
	}
	
	public double getRightSpeed() {
		return right.getEncVelocity();
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterControl());
	}
}

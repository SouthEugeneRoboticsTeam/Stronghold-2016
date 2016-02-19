
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.ShooterControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {
	
	private CANTalon left;
	private CANTalon right;
	
	private CANTalon pitch;
	private CANTalon yaw;
	
	private DoubleSolenoid pusher;
	
	public Shooter() {
		left = new CANTalon(RobotMap.LEFT_SHOOTER_MOTOR);
		right = new CANTalon(RobotMap.RIGHT_SHOOTER_MOTOR);
		
		left.enableBrakeMode(true);
		right.enableBrakeMode(true);
		
		right.changeControlMode(CANTalon.TalonControlMode.Follower);
		right.reverseOutput(true);
		
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		pitch = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		
		pusher = new DoubleSolenoid(RobotMap.PUSHER_OUT_PORT, RobotMap.PUSHER_IN_PORT);
	}
	
	public void startSpinUp() {
		left.set(1);
		right.set(RobotMap.LEFT_SHOOTER_MOTOR);
	}
	
	public void stopSpinUp() {
		left.set(1);
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
	}
	
	public void changeYaw(double speed) {
		yaw.set(speed);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterControl());
	}
}

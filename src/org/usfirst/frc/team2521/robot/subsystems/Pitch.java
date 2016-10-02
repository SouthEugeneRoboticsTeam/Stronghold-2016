package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleopPitch;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

/**
 * Subsystem for controlling shooter's pitch
 */
public class Pitch extends Subsystem {
	private CANTalon pitch;
	private double encoderMax;
	private double encoderMin;
	int counter = 0;
	
	public Pitch(){
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		pitch.reset();
		pitch.enableControl();
		
		// When set using encoders, a relative system is used to ensure
		// consistency, so the baselines must first be set
		encoderMin = pitch.getEncPosition();
		encoderMax = encoderMin + RobotMap.ENCODER_RANGE;
	}
	
	// Public methods
	/**
	 * Change CANTalon modes to be ready for auto
	 */
	public void autoInit(){
		pitch.changeControlMode(TalonControlMode.Position);
		pitch.setPID(RobotMap.PITCH_P, RobotMap.PITCH_I, RobotMap.PITCH_D);
		pitch.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		pitch.reverseOutput(true);
	}
	
	/**
	 * Getter for the current absolute encoder position
	 * @return the current pitch absolute encoder position
	 */
	public double getEncoderPosition(){
		return pitch.getEncPosition();
	}
	
	/**
	 * Getter for encoder max determines at init
	 * @return the max encoder position in the relative system
	 */
	public double getEncoderMax(){
		return encoderMax;
	}
	
	/**
	 * Getter for encoder min determines at init
	 * @return the min encoder position in the relative system
	 */
	public double getEncoderMin(){
		return encoderMin;
	}
	
	/**
	 * Getter for the current relative encoder position
	 * @return the current pitch relative encoder position
	 */
	public double getRelativeEncoderPosition(){
		return pitch.getEncPosition() - encoderMin;
	}
	
	/**
	 * Getter for setpoint of the current PID loop
	 * @return the current PID loop setpoint
	 */
	public double getSetpoint(){
		return pitch.getSetpoint();
	}
	
	
	/**
	 * Calculate the optimal encoder position based on the lidar reading
	 * @return the max encoder position in the relative system
	 */
	public double getTargetEncoderPosition(){
		double lidarVal = Robot.sensors.getAvgLidar();
		lidarVal = RobotMap.LIDAR_A*(Math.pow(lidarVal, 2)) + RobotMap.LIDAR_B*lidarVal + RobotMap.LIDAR_C;
		SmartDashboard.putNumber("Target pos", lidarVal);
		return lidarVal;
	}
	
	/**
	 * Set motor to the given value, taking into account its mode
	 * 
	 * @param value	the value that will be passed to the motor
	 */
	public void set(double value){
		if(pitch.getControlMode() == CANTalon.TalonControlMode.Position){
			value = value + encoderMin;
		}
		SmartDashboard.putNumber("Set value", value);
		pitch.set(value);
	}
	
	/**
	 * Change CANTalon modes to be ready for teleop
	 */
	public void teleopInit(){
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	// Overloaded methods
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopPitch());
    }
}


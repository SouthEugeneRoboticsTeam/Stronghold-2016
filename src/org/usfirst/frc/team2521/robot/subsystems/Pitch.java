package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleopPitch;
import org.usfirst.frc.team2521.robot.commands.TargetPitch;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

/**
 *
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
		encoderMin = pitch.getEncPosition();
		encoderMax = encoderMin + RobotMap.ENCODER_RANGE;
	}
	
	public void autoInit(){
		pitch.changeControlMode(TalonControlMode.Position);
		pitch.setPID(RobotMap.PITCH_P, RobotMap.PITCH_I, RobotMap.PITCH_D);
		pitch.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		reverseOutput(false);
	}
	
	public void autoEnd(){
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}
	
	public double getEncoderPosition(){
		return pitch.getEncPosition();
	}
	
	public double getRelativeEncoderPosition(){
		return pitch.getEncPosition() - encoderMin;
	}
	
	public double getSetpoint(){
		return pitch.getSetpoint();
	}
	
	public double getEncoderMax(){
		return encoderMax;
	}
	
	public double getEncoderMin(){
		return encoderMin;
	}
	
	public double getMotorValue(){
		return pitch.getOutputVoltage();
	}
    
    public boolean getOnTarget() {
    	return (Math.abs(pitch.getSetpoint() - pitch.getEncPosition()) < RobotMap.PITCH_ERROR_THRESHOLD);
    }
    
   public double getTargetEncoderPosition(){
	   double lidarVal = Robot.sensors.getAvgLidar();
	   lidarVal = 0.005184*(Math.pow(lidarVal, 2)) + -10.97*lidarVal + 7140;
	   SmartDashboard.putNumber("Target pos", lidarVal);
	   return lidarVal;
   }
    
    private double getTargetAngleRadians(){
    	double adj = Robot.sensors.getCameraDistance();
    	double opp = 85 - RobotMap.CAMERA_HEIGHT;
    	return Math.atan(opp/adj);
    }
	
	public void set(double value){
		if(pitch.getControlMode() == CANTalon.TalonControlMode.Position){
			value = value + encoderMin;
		}
		pitch.set(value);
	}
	
	public void teleopInit(){
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}

	public void reverseOutput(boolean state) {
		pitch.reverseOutput(false);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new TeleopPitch());
    }
}


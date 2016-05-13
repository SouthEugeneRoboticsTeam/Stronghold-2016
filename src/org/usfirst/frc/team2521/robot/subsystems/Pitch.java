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
		SmartDashboard.putNumber("Encoder minimum", encoderMin);
		SmartDashboard.putNumber("Encoder maximum", encoderMax);
	}
	
	public void autoInit(){
		pitch.changeControlMode(TalonControlMode.Position);
		pitch.setPID(RobotMap.PITCH_P, RobotMap.PITCH_I, RobotMap.PITCH_D);
		pitch.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		pitch.reverseOutput(true);
	}
	
	public double getEncoderPosition(){
		return pitch.getEncPosition();
	}
	
	public double getEncoderMax(){
		return encoderMax;
	}
	
	public double getEncoderMin(){
		return encoderMin;
	}
	
	public double getMotorValue(){
		return pitch.get();
	}
	
	/*private double getTargetPitchAngle(){
    	double adj = Robot.sensors.getLidarDistance();
    	double opp = 85 - RobotMap.CAMERA_HEIGHT;
    	double angle = Math.atan(opp/adj);
    	return angle*RobotMap.ENC_COUNTS_PER_RADIAN;
    }*/
    
    public boolean getOnTarget() {
    	return (Math.abs(pitch.getError()) < RobotMap.PITCH_ERROR_THRESHOLD);
    }
    
   public double getTargetEncoderPosition(){
	   //double angle = getTargetAngleRadians();
	   //return (angle*RobotMap.ENC_COUNTS_PER_RADIAN);
	   double h = Robot.sensors.getHeight();
	   SmartDashboard.putNumber("h", h);
	   //return 13*Math.pow(height, 2.348)+900+encoderMin; 
	   //return 540.9/(Math.pow((h-17.84),2))+19.92*(h+26.41)-4.661;
	   return Math.pow(h, 2)*9.355+-543.3*h+8952;
   }
    
    private double getTargetAngleRadians(){
    	double adj = Robot.sensors.getCameraDistance();
    	double opp = 85 - RobotMap.CAMERA_HEIGHT;
    	return Math.atan(opp/adj);
    }
	
	public void set(double value){
		SmartDashboard.putNumber("Value from in set", value);
		pitch.set(value);
		SmartDashboard.putNumber("Get from in set", pitch.get());
	}
	
	public void teleopInit(){
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
       // setDefaultCommand(new TargetPitch());
    	setDefaultCommand(new TeleopPitch());
    }
}


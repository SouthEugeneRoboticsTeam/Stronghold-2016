package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.PitchTeleop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.Math;

/**
 *
 */
public class Pitch extends Subsystem {
	private CANTalon pitch;
	
	int encYawZero = 0;
	
	public Pitch(){
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		pitch.enableControl();
	}
	
	public void autoInit(){
		pitch.changeControlMode(TalonControlMode.Position);
		pitch.setPID(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
	}
	
	private double getTargetPitchAngle(){
    	double adj = Robot.sensors.getLidarDistance();
    	double opp = 85 - RobotMap.CAMERA_HEIGHT;
    	double angle = Math.atan(opp/adj);
    	return angle*RobotMap.ENC_COUNTS_PER_RADIAN;
    }
    
    public boolean getOnTarget() {
    	return (Math.abs(pitch.getError()) < RobotMap.PITCH_ERROR_THRESHOLD);
    }
	
	public void set(double value){
		pitch.set(value);
	}
	
	public void teleopInit(){
		//yaw.changeControlMode(TalonControlMode.PercentVbus);
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PitchTeleop());
    }
}


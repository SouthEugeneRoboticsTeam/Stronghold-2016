package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TurretTeleop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

import java.lang.Math;

/**
 *
 */
public class Turret extends Subsystem {
	private CANTalon yaw;
	private CANTalon pitch;
	
	public Turret(){
		yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
	}
	
	public void autoInit(){
		yaw.changeControlMode(TalonControlMode.Position);
		pitch.changeControlMode(TalonControlMode.Position);
		
		yaw.setPID(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
		pitch.setPID(RobotMap.PITCH_P, RobotMap.PITCH_I, RobotMap.PITCH_D);
	}
	
	private double getTargetAngle(){
    	double adj = Robot.sensors.getLidarDistance();
    	double opp = 85 - RobotMap.CAMERA_HEIGHT;
    	double angle = Math.atan(opp/adj);
    	return angle*RobotMap.ENC_COUNTS_PER_RADIAN;
    }
    
    public boolean getOnTarget() {
    	return (Math.abs(yaw.getError()) < RobotMap.YAW_ERROR_THRESHOLD) && (Math.abs(pitch.getError()) < RobotMap.PITCH_ERROR_THRESHOLD);
    }
	
	public void setPitch(double value){
		pitch.set(value);
	}
	
	public void setYaw(double value){
		yaw.set(value);
	}
	
	public void teleopInit(){
		yaw.changeControlMode(TalonControlMode.PercentVbus);
		pitch.changeControlMode(TalonControlMode.PercentVbus);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TurretTeleop());
    }
}


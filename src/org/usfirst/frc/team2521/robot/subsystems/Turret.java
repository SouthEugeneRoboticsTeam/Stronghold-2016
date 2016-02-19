package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Turret extends Subsystem {
	private CANTalon yaw;
	private CANTalon pitch;
	
	public Turret(){
		yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		pitch = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
		
		yaw.changeControlMode(TalonControlMode.Position);
		pitch.changeControlMode(TalonControlMode.Position);
	}
	
	public void setPitch(double position){
		pitch.set(position);
	}
	
	public void setYaw(double position){
		yaw.set(position);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


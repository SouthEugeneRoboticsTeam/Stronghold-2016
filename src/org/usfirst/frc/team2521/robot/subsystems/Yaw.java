package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleopYaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Yaw extends Subsystem {
	CANTalon yaw;
	double yawZero;
	public Yaw(){
		yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		yaw.enableControl();
		yawZero = yaw.getEncPosition();
		
		yaw.changeControlMode(TalonControlMode.Position);
		yaw.setPID(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
	}
	
	public double getZero(){
		return yawZero;
	}
    
	public void set(double value){
		yaw.set(value);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopYaw());
    }
}


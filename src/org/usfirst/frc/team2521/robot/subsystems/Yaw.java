package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.YawTeleop;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Yaw extends Subsystem {
	CANTalon yaw;
	public Yaw(){
		yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		yaw.enableControl();
	}
    
	public void set(double value){
		yaw.set(value);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	
        // Set the default command for a subsystem here.
        setDefaultCommand(new YawTeleop());
    }
}


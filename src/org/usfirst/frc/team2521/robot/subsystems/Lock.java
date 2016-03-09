package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lock extends Subsystem {
	Solenoid lock;
	
	public Lock(){
		lock = new Solenoid(RobotMap.LOCK_PORT);
	}
    
	public void set(boolean locked){
		lock.set(locked);
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


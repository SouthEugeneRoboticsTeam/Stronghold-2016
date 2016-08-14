package org.usfirst.frc.team2521.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;

/**
 *
 */
public class Manipulator extends Subsystem {
    
    CANTalon manipulator;
    
    public Manipulator() {
    	manipulator = new CANTalon(RobotMap.MANIPULATOR_MOTOR);
    	manipulator.enableControl();
    }
    
    public void up() {
    	manipulator.set(RobotMap.MANIPULATOR_SPEED);
    }
    
    public void down() {
    	manipulator.set(-RobotMap.MANIPULATOR_SPEED);
    }

    public void stop() {
    	manipulator.set(0);
    }
    
    public double getEncoderPosition(){
    	return manipulator.getEncPosition();
    }
    
    public void initDefaultCommand() {
    }
}


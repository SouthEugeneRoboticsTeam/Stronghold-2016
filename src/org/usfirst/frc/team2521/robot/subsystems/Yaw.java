package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class Yaw extends PIDSubsystem {
	CANTalon yaw;
	
    // Initialize your subsystem here
    public Yaw() {
    	super(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
    	yaw = new CANTalon(RobotMap.TARGETING_PITCH_MOTOR);
    	yaw.changeControlMode(TalonControlMode.Position);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public boolean getOnTarget(){
    	return (Math.abs(getSetpoint() - Robot.sensors.getDeltaX()) < RobotMap.YAW_ERROR_THRESHOLD);
    }
    
    public void set(double position){
    	yaw.set(position);
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return Robot.sensors.getDeltaX();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	yaw.set(output);
    }
}

package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TargetYaw;
import org.usfirst.frc.team2521.robot.commands.TeleopYaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class YawPID extends PIDSubsystem {
	CANTalon yaw;
	double yawZero;
	
    // Initialize your subsystem here
    public YawPID() {
    	super(RobotMap.YAW_VISION_P, RobotMap.YAW_VISION_I, RobotMap.YAW_VISION_D);
    	yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		yaw.enableControl();
		yawZero = yaw.getEncPosition();
	}
	
    public void autoInit(){
    	System.out.println("Yo we initted");
    	yaw.changeControlMode(TalonControlMode.Position);
		yaw.setPID(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
		enable();
    }
    
    public void autoEnd(){
    	System.out.println("Yo we ended");
    	disable();
    	yaw.changeControlMode(TalonControlMode.PercentVbus);
    }
    
	public double getZero(){
		System.out.println("getZero() - " + yawZero);
		return yawZero;
	}
    
	public void set(double value){
		System.out.println("set to " + value);
		yaw.set(value);
	}
	
    public boolean getVisionOnTarget(){
    	System.out.println("getVisionOnTarget: " + (Math.abs(getSetpoint() - Robot.sensors.getDeltaX()) < RobotMap.YAW_VISION_ERROR_THRESHOLD));
    	return (Math.abs(getSetpoint() - Robot.sensors.getDeltaX()) < RobotMap.YAW_VISION_ERROR_THRESHOLD);
    }
    
    public boolean getOnTarget(){
    	System.out.println("getOnTarget: " + (Math.abs(yaw.get() - yaw.getEncPosition()) < RobotMap.YAW_ERROR_THRESHOLD));
    	return (Math.abs(yaw.get() - yaw.getEncPosition()) < RobotMap.YAW_ERROR_THRESHOLD);
    }
    
    public void printEncPos(){
    	System.out.println("Enc pos: " + yaw.getEncPosition());
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new TeleopYaw());
    	setDefaultCommand(new TargetYaw());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	System.out.println("PIDInput " + Robot.sensors.getDeltaX());
    	return Robot.sensors.getDeltaX();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	System.out.println("output " + output);
    	yaw.set(-output);
    }
}

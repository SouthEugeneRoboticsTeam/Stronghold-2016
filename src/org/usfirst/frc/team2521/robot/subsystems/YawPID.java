package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TargetYaw;
import org.usfirst.frc.team2521.robot.commands.TeleopYaw;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class YawPID extends PIDSubsystem {
	CANTalon yaw;
	double yawZero;
	
    public YawPID() {
    	super(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
    	yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
		yawZero = yaw.getEncPosition();
	}
	
    public void autoInit(){
		enable();
		setSetpoint(0);
    }
    
    public void autoEnd(){
    	disable();
    	yaw.changeControlMode(TalonControlMode.PercentVbus);
    }
    
	public double getZero(){
		return yawZero;
	}
    
	public void set(double value){
		yaw.set(value);
	}
	
    public boolean getOnTarget(){
       	return false;
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new TeleopYaw());
    	//setDefaultCommand(new TargetYaw());
    }
    
    protected double returnPIDInput() {
    	return Robot.sensors.getDeltaX();
       	
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	SmartDashboard.putNumber("Output", output);
       	yaw.set(-output);
    }
}

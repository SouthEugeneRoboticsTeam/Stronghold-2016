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
 * Subsystem for controlling yaw motor
 */
public class YawPID extends PIDSubsystem {
	CANTalon yaw;
	
    public YawPID() {
    	super(RobotMap.YAW_P, RobotMap.YAW_I, RobotMap.YAW_D);
    	yaw = new CANTalon(RobotMap.TARGETING_YAW_MOTOR);
	}
	
    // Public methods
    /**
     * Start PID loop
     */
    public void autoInit(){
		enable();
		setSetpoint(0);
    }
    
    /**
     * End PID loop
     */
    public void teleopEnd(){
    	disable();
    	yaw.changeControlMode(TalonControlMode.PercentVbus);
    }
    
    /**
     * Set yaw motor to a specified value
     * @param value the value to be passed to the yaw motor
     */
	public void set(double value){
		yaw.set(value);
	}
    
	// Overloaded methods
    public void initDefaultCommand() {
        setDefaultCommand(new TeleopYaw());
    }
    
    protected double returnPIDInput() {
    	return Robot.sensors.getDeltaX();
       	
    }
    
    protected void usePIDOutput(double output) {
       	yaw.set(-output);
    }
}

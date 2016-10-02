package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.Robot;
import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANTalon.TalonControlMode;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 * PID version of the Drivetrain subsystem. The PID is for turning
 * to specific angles using the gyro of the navx.
 */
public class DrivetrainPID extends PIDSubsystem {
	private RobotDrive frontDrive;
	private RobotDrive rearDrive;
	
	// Will be set later
	private double targetAngle = 0; 
	private double error = 0;
	
	private CANTalon frontLeft, frontRight, rearLeft, rearRight;

    public DrivetrainPID() {
    	super(RobotMap.DRIVE_TURN_P, RobotMap.DRIVE_TURN_I, RobotMap.DRIVE_TURN_D);
    	
    	frontLeft = new CANTalon(RobotMap.FRONT_LEFT_MOTOR);
		frontRight = new CANTalon(RobotMap.FRONT_RIGHT_MOTOR);
		rearLeft = new CANTalon(RobotMap.REAR_LEFT_MOTOR);
		rearRight = new CANTalon(RobotMap.REAR_RIGHT_MOTOR);
		
		frontLeft.enableControl();
		frontRight.enableControl();
		rearLeft.enableControl();
		rearRight.enableControl();
		
		frontDrive = new RobotDrive(rearLeft, rearRight);
		rearDrive = new RobotDrive(rearLeft, rearRight);
    }
    
    // Public methods
    /**
	 * Drive robot using two joysticks (one on each side)
	 */
    public void tankDrive() {
		double left = OI.getInstance().getLeftStick().getY();
		double right = OI.getInstance().getRightStick().getY();
		
		if(OI.getInstance().getSlowMode()){
			left *= OI.getInstance().getSlowModeFactor();
			right *= OI.getInstance().getSlowModeFactor();
		}
		
		// If the trigger is held, the directions on the joinstick are switched
		if(OI.getInstance().getRightStick().getRawButton(1)){
			left *= -1;
			right *= -1;
			double sub;
			sub = left;
			left = right;
			right = sub;
		} else {
			frontDrive.tankDrive(right, left); 
			rearDrive.tankDrive(right, left);
		}
	}
	
    
    /**
	 * Drive robot using one joystick
	 */
	public void arcadeDrive() {
		Joystick left = OI.getInstance().getLeftStick();
		if(OI.getInstance().getSlowMode()){
			frontDrive.arcadeDrive(left.getY()*OI.getInstance().getSlowModeFactor(), left.getX()*OI.getInstance().getSlowModeFactor());
			rearDrive.arcadeDrive(left.getY()*OI.getInstance().getSlowModeFactor(), left.getX()*OI.getInstance().getSlowModeFactor());
		} else {
			frontDrive.arcadeDrive(left);
			rearDrive.arcadeDrive(left);
		}
	}
	
	/**
	 * Method that will be called during TeleopDrivetrain command
	 */
	public void teleoperatedDrive() {
		frontLeft.changeControlMode(TalonControlMode.PercentVbus);
		frontRight.changeControlMode(TalonControlMode.PercentVbus);
		rearLeft.changeControlMode(TalonControlMode.PercentVbus);
		rearRight.changeControlMode(TalonControlMode.PercentVbus);
		
		if(OI.getInstance().getArcadeMode()) {
			arcadeDrive();
		} else tankDrive();
	}
	
	/**
	 * Set master motors to the given value regardless of mode, then
	 * switch the slaves to follower mode and have them follow the 
	 * masters.
	 * 
	 * @param value	the value that will be passed to the master motors
	 */
	public void set(double leftValue, double rightValue) {
		frontRight.set(leftValue); 
		frontLeft.set(-rightValue);
		
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(RobotMap.FRONT_RIGHT_MOTOR);
		
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(RobotMap.FRONT_LEFT_MOTOR);
	}
	
	/**
	 * Set the setpoint for angle PID loop
	 * @param angle	the absolute angle (determined by the navx)
	 *  in degrees to turn to
	 */
	public void setTargetAngle(double angle){
		targetAngle = angle;
	}
    
	// Private methods
	private void setLeft(double value){
		frontLeft.set(value);
		rearLeft.changeControlMode(TalonControlMode.Follower);
		rearLeft.set(RobotMap.FRONT_LEFT_MOTOR);
	}
	
	private void setRight(double value){
		frontRight.set(value);
		rearRight.changeControlMode(TalonControlMode.Follower);
		rearRight.set(RobotMap.FRONT_RIGHT_MOTOR);
	}
	
	// Overloaded methods
    public void initDefaultCommand() {}
    
    protected double returnPIDInput() {
    	double a = targetAngle - Robot.sensors.getYaw();
    	a = (a + 180) % 360 - 180;
    	error = a;
    	return a;
    }
    
    protected void usePIDOutput(double output) {
    	System.out.println(output);
    	setRight(output);
    	setLeft(output);
    }
}

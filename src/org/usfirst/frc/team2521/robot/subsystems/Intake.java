
package org.usfirst.frc.team2521.robot.subsystems;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.commands.TeleopDrivetrain;
import org.usfirst.frc.team2521.robot.commands.TeleopIntake;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/** 
 * 
 */
public class Intake extends Subsystem {
	
	private CANTalon intake;
	
	public Intake() {
		intake = new CANTalon(RobotMap.INTAKE_WHEEL_MOTOR);
	}
	
	public void in() {
		intake.set(1);
		SmartDashboard.putNumber("Intake val", intake.get());
	}
	
	public void out() {
		intake.set(-1);
		SmartDashboard.putNumber("Intake val", intake.get());
	}
	
	public void set(double speed) {
		intake.set(speed);
		SmartDashboard.putNumber("Intake val", intake.get());
	}
	
	public void stop() {
		intake.set(0);
	}
	
	public void initDefaultCommand() {
		setDefaultCommand(new TeleopIntake());
	}
	
}

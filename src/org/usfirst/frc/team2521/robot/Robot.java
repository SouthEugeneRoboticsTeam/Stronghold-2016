
package org.usfirst.frc.team2521.robot;

import org.usfirst.frc.team2521.robot.commands.*;
import org.usfirst.frc.team2521.robot.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	Preferences prefs;
	public static double YAW_P;
	public static double YAW_I;
	public static double YAW_D;
	
	public static double YAW_VISION_P;
	public static double YAW_VISION_I;
	public static double YAW_VISION_D;
	
	public static boolean drivetrain_attached = true;
	
	public static DrivetrainPID drivetrain;
	public static FlyWheels flyWheels;
	public static Sensors sensors;
	public static Pitch pitch;
	public static YawPID yaw;
	
	public static OI oi;
	
	Command auto;
	Command teleop;
	Command reset;
	Command stopDrivetrain;

	public void robotInit() {
		//Initialize subsystems
		flyWheels = new FlyWheels();
		pitch = new Pitch();
		yaw = new YawPID();
		sensors = new Sensors();
		if(drivetrain_attached) {
			drivetrain = new DrivetrainPID();
		}
		
		//Initialize OI
		oi = new OI();
		
		//Initialize commands
		auto = new AutoShoot();
		reset = new Reset();
		stopDrivetrain = new StopDrivetrainPID();
	}
	
	public void disabledInit() {}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void autonomousInit() {
		auto.start();
	}
	
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		//Stop all autonomous action
		auto.cancel();
		reset.start();
		if(drivetrain_attached) {
			stopDrivetrain.start();
		}
	}
	
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void testPeriodic() {
		LiveWindow.run();
	}
}

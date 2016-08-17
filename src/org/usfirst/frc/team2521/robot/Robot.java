
package org.usfirst.frc.team2521.robot;

import org.usfirst.frc.team2521.robot.commands.Autonomous;
import org.usfirst.frc.team2521.robot.commands.AutoAim;
import org.usfirst.frc.team2521.robot.commands.AutoShoot;
import org.usfirst.frc.team2521.robot.commands.DisableDrivetrainPID;
import org.usfirst.frc.team2521.robot.commands.SetDrivetrain;
import org.usfirst.frc.team2521.robot.commands.Spin;
import org.usfirst.frc.team2521.robot.commands.MoveToDistance;
import org.usfirst.frc.team2521.robot.commands.TeleopPitch;
import org.usfirst.frc.team2521.robot.commands.TargetPitch;
import org.usfirst.frc.team2521.robot.commands.TargetPitchBaseline;
import org.usfirst.frc.team2521.robot.subsystems.Drivetrain;
import org.usfirst.frc.team2521.robot.subsystems.DrivetrainPID;
import org.usfirst.frc.team2521.robot.subsystems.FlyWheels;
import org.usfirst.frc.team2521.robot.subsystems.Intake;
import org.usfirst.frc.team2521.robot.subsystems.Lock;
import org.usfirst.frc.team2521.robot.subsystems.Manipulator;
import org.usfirst.frc.team2521.robot.subsystems.Sensors;
import org.usfirst.frc.team2521.robot.subsystems.TalonLeft;
import org.usfirst.frc.team2521.robot.subsystems.TalonRight;
import org.usfirst.frc.team2521.robot.subsystems.YawPID;
import org.usfirst.frc.team2521.robot.subsystems.Yaw;
import org.usfirst.frc.team2521.robot.subsystems.Pitch;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	Preferences prefs;
	public static double YAW_P;
	public static double YAW_I;
	public static double YAW_D;
	
	public static double YAW_VISION_P;
	public static double YAW_VISION_I;
	public static double YAW_VISION_D;
	
	public static boolean test_platform = true; //are we on the real robot or the test platform?
	
	public static DrivetrainPID drivetrain;
	public static Manipulator manipulator;
	public static Intake intake;
	public static FlyWheels flyWheels;
	public static Sensors sensors;
	public static Pitch pitch;
	public static YawPID yaw;
	//public static TalonLeft talonLeft;
//	public static TalonRight talonRight;
	//public static Lock lock;
	
	public static OI oi;
	
	Command auto;
	Command teleop;
	Command pitchInit;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.setMotors();
		drivetrain = new DrivetrainPID();
		intake = new Intake();
		manipulator = new Manipulator();
		flyWheels = new FlyWheels();
		pitch = new Pitch();
		yaw = new YawPID();
		///lock = new Lock();
		
		sensors = new Sensors();
		
		oi = new OI();
		
		auto = new AutoShoot();
		//teleop = new AutoAim();
		//sensors.setInitYaw(); 
	}
	
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		//flyWheels.stop();
		//auto.cancel();
	}
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro You
	 * can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		//teleop.cancel();
		auto.start();
		//pitchInit.start();
	}
	
	/**
	 * This function is called periodically during autonomous
	 */

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}
	
	public void teleopInit() {
		Command stopDrivetrain = new DisableDrivetrainPID();
		stopDrivetrain.start();
	}
	
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
	
	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}

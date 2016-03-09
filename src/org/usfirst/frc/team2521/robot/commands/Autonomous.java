package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.OI;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	addSequential(new Traverse());
    	switch(OI.getInstance().getFieldPosition()){
    	case 1:
    		addSequential(new ToAngle(330));
    		SmartDashboard.putNumber("Field position", 1);
    		break;
    	case 2:
    		addSequential(new ToAngle(300));
    		SmartDashboard.putNumber("Field position", 2);
    		break;
    	case 3:
    		SmartDashboard.putNumber("Field position", 3);
    		break;
    	case 4:
    		addSequential(new ToAngle(60));
    		SmartDashboard.putNumber("Field position", 4);
    		break;
    	case 5:
    		addSequential(new ToAngle(30));
    		SmartDashboard.putNumber("Field position", 5);
    		break;
    	default:
    		SmartDashboard.putNumber("Field position", 6);
    		break;
    	}
    	//addSequential(new AutoShoot());
    	addSequential(new ToAngle(180));
    	addSequential(new Traverse());
    	addSequential(new ToAngle(0));
    }
}

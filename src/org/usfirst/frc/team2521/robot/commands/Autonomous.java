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
    	
    	//addSequential(new Traverse());
    	//addSequential(new MoveToLidar());
    	
    	//addSequential(new MoveForTime(0.5),5);
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
    	addSequential(new ToAngle(180));
    	addSequential(new Traverse());
    	addSequential(new ToAngle(0));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

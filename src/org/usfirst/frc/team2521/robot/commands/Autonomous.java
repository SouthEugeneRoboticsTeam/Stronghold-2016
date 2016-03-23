package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.RobotMap;
import org.usfirst.frc.team2521.robot.OI;
import org.usfirst.frc.team2521.robot.OI.Defense;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Autonomous extends CommandGroup {
    
    public  Autonomous() {
    	switch(OI.getInstance().getAutoMode()){
    	case traverseOnly: addSequential(new Traverse());
    		Timer.delay(5);
    		/*if(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise){
    			addSequential(new ToAngle(180));
    		}*/
    		break;
    	case traverseAndReturn: addSequential(new Traverse());
	    	if(!(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise)){
				addSequential(new ToAngle(180, true), 2);
			}
    		addSequential(new Traverse());
    		addSequential(new ToAngle(0, true), 2);
    		break;
    	case traverseAndLowGoal: addSequential(new MoveToLidar(RobotMap.LIDAR_WALL_THRESHOLD));
    		if(OI.getInstance().getFieldPosition() <= 3){
    			addSequential(new ToAngle(270, true), 2);
    		} else addSequential(new ToAngle(90, true), 2);
    		//addSequential(new MoveToLidar(RobotMap.LIDAR_WALL_THRESHOLD));
    		//addSequential(new IntakeOut(), 2);
    		break;
    	default: break;
    	}
    }
}

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
    	case traverseOnly: 
    		addSequential(new Traverse());
    		addSequential(new SetDrivetrain(0));
    		//addSequential(new ToAngle(0));
    		//Timer.delay(5);
    		/*if(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise || OI.getInstance().getDefense() == OI.Defense.portcullis){
    			addSequential(new ToAngle(180));
    		} else addSequential(new ToAngle(0));*/
    		break;
    	case traverseAndReturn:
    		addSequential(new Traverse());
    		addSequential(new SetDrivetrain(1), 1);
    		addSequential(new SetDrivetrain(0));
	    	if(!(OI.getInstance().getDefense() == OI.Defense.chevalDeFrise)){
				addSequential(new ToAngle(180));
			} else addSequential(new ToAngle(0));
    		addSequential(new Traverse());
    		addSequential(new ToAngle(0));
    		addSequential(new SetDrivetrain(0));
    		break;
    	case traverseAndLowGoal: 
    		addSequential(new Traverse());
    		addSequential(new SetDrivetrain(RobotMap.AUTO_SPEED), 0.5);
    		addSequential(new MoveToLidar(RobotMap.LIDAR_WALL_THRESHOLD));
    		if(OI.getInstance().getFieldPosition() < 4){
    			addSequential(new ToAngle(270));
    		} else addSequential(new ToAngle(90));
    		addParallel(new SetDrivetrain(RobotMap.AUTO_SPEED));
    		Timer.delay(5);
    		addSequential(new IntakeOut());
    		addSequential(new ToAngle(0));
    		addSequential(new SetDrivetrain(0));
    		break;
    	case traverseAndHighGoal:
    		addSequential(new Traverse());
    		addParallel(new TargetYaw());
    		Timer.delay(2);
    		addParallel(new SetPitch(1));
    	default: break;
    	}
    }
}

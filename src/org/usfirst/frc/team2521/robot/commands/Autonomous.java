
package org.usfirst.frc.team2521.robot.commands;

import org.usfirst.frc.team2521.robot.RobotMap;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class Autonomous extends CommandGroup {
	
	public Autonomous() {
		addSequential(new MoveToDistance(RobotMap.AUTONOMOUS_DISTANCE));
	}
}

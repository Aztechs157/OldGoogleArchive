/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc157.AztechRobot2014.subsystems.BallRetriever;

/**
 *
 * @author mattkahn
 */
public class PickupBall extends CommandGroup {

    public PickupBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        addSequential(new MoveBallRetriever(BallRetriever.Load));  // move to 3m from the wall
        addSequential(new SpinRoller(SpinRoller.ROLLER_IN));
    }
}

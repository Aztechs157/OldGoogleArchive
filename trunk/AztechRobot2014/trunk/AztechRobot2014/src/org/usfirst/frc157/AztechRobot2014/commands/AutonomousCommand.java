// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc157.AztechRobot2014.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import org.usfirst.frc157.AztechRobot2014.Robot;
import org.usfirst.frc157.AztechRobot2014.RobotMap;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {

    AnalogChannel autoSelectSwitch;

    public AutonomousCommand() {

        System.out.println("Creating  Up Autonomous Command Group");
        int switchNumPositions = 6;
        int switchMaxPosition = switchNumPositions - 1; // zero based index
        int switchStepSize = 1024 / switchNumPositions;

        autoSelectSwitch = new AnalogChannel(RobotMap.ANALOG_PORT_AutoModeSelect);
        int autoMode = switchMaxPosition - (autoSelectSwitch.getValue() + switchStepSize / 2) / switchStepSize;
        
        // Get the retriever out of the way just in case
        addSequential(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_MIDDLE));  // set the ingester to load position
 
        switch (autoMode) {
            case 0:
                // take a single shot
                addSequential(new DriveToRange(300));  // move to 3m from the wall
                addSequential(new SensorAlignToWall());
                addSequential(new SleepCommand(1.0));  // sleep for 2 seconds 
                addSequential(new Launch());           // launch ball
                break;
            case 1:
                // Shoot, get another ball and shoot againg
                addSequential(new DriveToRange(300));  // move to 3m from the wall
                addSequential(new SensorAlignToWall());
                addSequential(new SleepCommand(1.0));  // sleep for 1 seconds 
                addSequential(new Launch());           // launch ball
                addSequential(new SleepCommand(0.5));  // sleep for 1/2 second
                addSequential(new SpinRoller(SpinRoller.ROLLER_IN)); // start the ingesters
                addSequential(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_OUT));  // set the ingester to load position
                addSequential(new DriveSpeedForTime(-1, -1, 3));  // drive back to where the second ball was
                addSequential(new MoveBallRetriever(MoveBallRetriever.RETRIEVER_MIDDLE));  // set the ingester to idle position
                addSequential(new SpinRoller(SpinRoller.ROLLER_STOP)); // stop the ingesters
                addSequential(new DriveSpeedForTime(1, 1, 3));  // drive back to the wall       
                addSequential(new DriveToRange(300));  // move to 3m from the wall
                addSequential(new SensorAlignToWall());
                addSequential(new SleepCommand(1.0));  // sleep for 2 seconds 
                addSequential(new Launch());           // launch ball
                break;
            default:
                System.out.println("ERROR - Unexpected auto mode");
                addSequential(new SleepCommand(2.0));      // sleep for 2 seconds 
                addSequential(new Launch());            // launch ball (we don't want to keep it if there is an error)
                break;
        }
    }
}

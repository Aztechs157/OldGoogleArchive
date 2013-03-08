
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;


/**
 *
 * @author bradmiller
 */
public class AutonomousCommand extends CommandGroup {

    AnalogChannel autoSelectSwitch;
    
    public AutonomousCommand() {
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
        
        int switchNumPositions = 6;
        int switchMaxPosition = switchNumPositions - 1; // zero based index
        int switchStepSize = 190; //1024 / switchNumPositions;
        
        autoSelectSwitch = new AnalogChannel(RobotMap.AutoSelectChannel);
        int autoMode = switchMaxPosition - (autoSelectSwitch.getValue() + switchStepSize/2) / switchStepSize;
//        int autoMode = 5 - (autoSelectSwitch.getValue() + 170/2) / 170;
        
        String autoModeString = "AutoMode = " + autoMode + ": ";
        DriverStationLCD lcd = DriverStationLCD.getInstance();
        lcd.println(DriverStationLCD.Line.kUser1, 1, autoModeString);
        lcd.updateLCD();
        System.out.println("Autonomous Mode: "+autoMode+", orig = "+autoSelectSwitch.getValue());

        switch(autoMode)
        {
        case 0:
//            lcd.println(DriverStationLCD.Line.kUser1, autoModeString.length()+1, "Place anyhwhere");
            // start from anywhere.. no move
//            addSequential(new SetElevation(37));
            addSequential(new SleepCommand(2.0));      // sleep for 2 seconds 
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            break;
            
        case 1:
 //           lcd.println(DriverStationLCD.Line.kUser1, autoModeString.length()+1, "Place at back left");
            // start from back left
            addSequential(new SetElevation(37));
            addSequential(new Straight(4.0, 0.0));     // drive forward 4 feet
            addSequential(new Turn(-40));              // turn 40 degrees right
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            break;
            
        case 2:
 //           lcd.println(DriverStationLCD.Line.kUser1, autoModeString.length()+1, "Place at back right");
            // start from front right
            addSequential(new SetElevation(37));
            addSequential(new Straight(4.0, 0.0));     // drive forward 4 feet
            addSequential(new Turn(40));              // turn 40 degrees left
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new SleepCommand(0.250));
            addSequential(new LaunchDisk());            // launch disk
            break;

        default:
            System.out.println("Auto Mode "+autoMode+" not supported!");
        }    
    }
}

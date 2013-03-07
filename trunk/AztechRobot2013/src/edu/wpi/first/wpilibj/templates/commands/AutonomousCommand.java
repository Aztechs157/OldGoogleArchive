
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
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
        
        autoSelectSwitch = new AnalogChannel(RobotMap.AutoSelectChannel);
        int autoMode = autoSelectSwitch.getValue() / 6;
        System.out.println("Autonomous Mode: "+autoMode);

//        switch(autoMode)
//        {
//        case 0:
            // start from back left
            addSequential(new SetElevation(Shooter.basicShotAngle));
            addSequential(new Straight(8.0, 0.0));     // drive forward 8 feet

            addSequential(new LaunchDisk());            // launch disk
            addSequential(new LaunchDisk());            // launch disk
            addSequential(new LaunchDisk());            // launch disk
//            break;
//            
//        case 1:
//            // start from back left
//            addSequential(new SetElevation(Shooter.basicShotAngle));
//            addSequential(new Straight(8.0, .0));     // drive forward 8 feet
//            addSequential(new Turn(30.0));              // turn right 30 degrees
//            addSequential(new VisionAlign());           // vision align
//            
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            break;
//            
//        case 2:
//            // start from front left
//            addSequential(new SetElevation(Shooter.basicShotAngle));
//            addSequential(new Straight(2.0, 0.0));      // drive forward 2 feet
//            addSequential(new Turn(30.0));              // turn right 30 degrees
//            addSequential(new VisionAlign());           // vision align
//            
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            break;
//            
//        case 3:
//            // start from front left
//            addSequential(new SetElevation(Shooter.basicShotAngle));
//            addSequential(new Straight(2.0, 0.0));      // drive forward 2 feet
//            addSequential(new Turn(30.0));              // turn right 30 degrees
//            addSequential(new VisionAlign());           // vision align
//            
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            break;
//            
//        case 4:
//            // start from front left
//            addSequential(new SetElevation(Shooter.basicShotAngle));
//            addSequential(new Straight(2.0, 0.0));      // drive forward 2 feet
//            addSequential(new Turn(30.0));              // turn right 30 degrees
//            addSequential(new VisionAlign());           // vision align
//            
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            break;
//            
//        case 5:
//            // start from front left
//            addSequential(new SetElevation(Shooter.basicShotAngle));
//            addSequential(new Straight(2.0, 0.0));      // drive forward 2 feet
//            addSequential(new Turn(30.0));              // turn right 30 degrees
//            addSequential(new VisionAlign());           // vision align
//            
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            addSequential(new LaunchDisk());            // launch disk
//            break;
//
//        default:
//            System.out.println("Auto Mode "+autoMode+" not supported!");
//        }    
    }
}

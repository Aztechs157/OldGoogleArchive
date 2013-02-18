
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.AnalogChannel;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.templates.RobotMap;


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
        //System.out.println("Autonomous Mode: "+autoMode);
        
        addSequential(new VisionAlign());
    }
}

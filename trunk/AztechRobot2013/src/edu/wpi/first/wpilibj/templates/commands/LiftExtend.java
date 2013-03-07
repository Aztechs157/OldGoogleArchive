package edu.wpi.first.wpilibj.templates.commands;


import edu.wpi.first.wpilibj.templates.subsystems.LiftSubsystem;

/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
/**
 *
 * @author matt
 */
public class LiftExtend extends CommandBase {

    boolean raise; // whether command is to rais or lower lift hooks
    boolean finished;

    LiftSubsystem lift = CommandBase.lift;

    public LiftExtend(boolean inRaise) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        finished = true;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

        if (raise == true) // raising
        {
            lift.extend();
            if (lift.isFullUp()) // at upper limit
            {
                finished = true;
            }
        } else // lowering
        {
            lift.retract();
            if (lift.isFullDown()) // at lower limit
            {
                finished = true;
            }
        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        lift.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        end();
    }
}

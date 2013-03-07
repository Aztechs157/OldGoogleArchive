/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.templates.MotorControlAssembly;
import edu.wpi.first.wpilibj.templates.RobotMap;
import edu.wpi.first.wpilibj.templates.commands.LiftCommand;

/**
 *
 * @author aztechs
 */
public class LiftSubsystem extends Subsystem {
    
    private static Victor liftMotorL;
    private static Victor liftMotorR;
    
    private static DigitalInput liftLimitRetractL;
    private static DigitalInput liftLimitRetractR;
    private static DigitalInput liftLimitExtendL;
    private static DigitalInput liftLimitExtendR;
    
    public MotorControlAssembly liftAssemblyL;
    public MotorControlAssembly liftAssemblyR;
    
    public LiftSubsystem()
    {
        init();
    }
    public final void init() {
        if (liftMotorL == null) {
            liftMotorL = new Victor(RobotMap.LiftLeftPWMPort);
        }
        if (liftMotorR == null) {
            liftMotorR = new Victor(RobotMap.LiftRightPWMPort);
        }
        if (liftLimitRetractL == null) {
            liftLimitRetractL = new DigitalInput(RobotMap.LiftLeftLimitRetractGPIOPort);
        }
        if (liftLimitRetractR == null) {
            liftLimitRetractR = new DigitalInput(RobotMap.LiftRightLimitRetractGPIOPort);
        }
        if (liftLimitExtendL == null) {
            liftLimitExtendL = new DigitalInput(RobotMap.LiftLeftLimitExtendGPIOPort);
        }
        if (liftLimitExtendR == null) {
            liftLimitExtendR = new DigitalInput(RobotMap.LiftRightLimitExtendGPIOPort);
        }
        if (liftAssemblyL == null) {
            liftAssemblyL = new MotorControlAssembly(liftMotorL, null, liftLimitRetractL, liftLimitExtendL);
        }
        if (liftAssemblyR == null) {
            liftAssemblyR = new MotorControlAssembly(liftMotorR, null, liftLimitRetractR, liftLimitExtendR);
        }
        if (liftAssemblyL == null) {
            liftAssemblyL.setInvertedMotor(RobotMap.LiftLeftMotorInverted);
        }
        if (liftAssemblyR == null) {
            liftAssemblyR.setInvertedMotor(RobotMap.LiftRightMotorInverted);
        }
    }

        // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        setDefaultCommand(new LiftCommand());

        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void retract() {
//        System.out.println("Retracting... limitL="+liftAssemblyL.getLimit(-LIFT_EXTEND_SPEED_L)+", limitR="+liftAssemblyR.getLimit(-LIFT_EXTEND_SPEED_R));
//        System.out.println("Retracting... limitL="+liftLimitHighL.get()+", limitR="+liftLimitHighR.get());
        liftAssemblyL.runFromInput(-RobotMap.LiftLeftRetractSpeed);
        liftAssemblyR.runFromInput(-RobotMap.LiftRightRetractSpeed);
    }
    
    public void extend() {
//        System.out.println("Extending... limitL="+liftAssemblyL.getLimit(LIFT_RETRACT_SPEED_L)+", limitR="+liftAssemblyR.getLimit(LIFT_RETRACT_SPEED_R));
//        System.out.println("Extending... limitL="+liftLimitHighL.get()+", limitR="+liftLimitHighR.get());
        liftAssemblyL.runFromInput(RobotMap.LiftLeftExtendSpeed);
        liftAssemblyR.runFromInput(RobotMap.LiftRightExtendSpeed);
    }
    
    public void stop(){
        liftAssemblyL.runFromInput(0);
        liftAssemblyR.runFromInput(0);        
    }
    
    public boolean isFullUp()
    {
        return (liftAssemblyL.atHighLimitSw() && liftAssemblyR.atHighLimitSw());
    }
    public boolean isFullDown()
    {
        return (liftAssemblyL.atLowLimitSw() && liftAssemblyR.atLowLimitSw());        
    }
}




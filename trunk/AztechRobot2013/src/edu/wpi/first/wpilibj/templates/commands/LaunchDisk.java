/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.templates.AztechRobot;
import edu.wpi.first.wpilibj.templates.subsystems.Shooter;

/**
 *
 * @author MattKahn
 */

// Shot Sequence is:
// 1) Load Disk (extend -> retract Loader)
// 2) Spin Launch Wheels
// 3) Launch Disk (extend -> retract Shooter)

public class LaunchDisk extends CommandBase {
  
    
    // Launch Sequence State Enumeration
    public static class LaunchState {

        public final int value;
        // Drive Mode enumeration constants
        protected static final int m_kStart = 0;
        protected static final int m_kLoad = 1;
        protected static final int m_kLoading = 5;
        protected static final int m_kSpinUp = 2;
        protected static final int m_kSpinningUp = 6;
        protected static final int m_kShoot = 3;
        protected static final int m_kFinish = 4;
        
        public static final LaunchState Start  = new LaunchState(m_kStart);
        public static final LaunchState Load   = new LaunchState(m_kLoad);
        public static final LaunchState Loading= new LaunchState(m_kLoading);
        public static final LaunchState SpinUp = new LaunchState(m_kSpinUp);
        public static final LaunchState SpinningUp = new LaunchState(m_kSpinningUp);
        public static final LaunchState Shoot  = new LaunchState(m_kShoot);
        public static final LaunchState Finish = new LaunchState(m_kFinish);

        private LaunchState(int LaunchState) {
            this.value = LaunchState;
        }
    }

    private boolean isFinished = false;
    private double startTime = 0;
    private LaunchState state = LaunchState.Start;
    
    public LaunchDisk() {
        // Use requires() here to declare subsystem dependencies
//        requires(AztechRobot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
           isFinished = false;
           state = LaunchState.Start;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch (state.value) {
            case LaunchState.m_kStart:
                System.out.println("Shooter m_kStart");
                AztechRobot.shooter.extendLoader();
                state = LaunchState.Loading;
                startTime = Timer.getFPGATimestamp();
                break;
            case LaunchState.m_kLoading:
                System.out.println("Shooter m_kLoading " + Timer.getFPGATimestamp());
                // wait for extension (~0.5s)
                if (Timer.getFPGATimestamp() > (startTime + 0.5)) {
                    state = LaunchState.SpinUp;
                }
                break;
            case LaunchState.m_kSpinUp:
                System.out.println("Shooter m_kSpinUp");
                AztechRobot.shooter.retractLoader();
                AztechRobot.shooter.spinLaunchWheels(1);
                state = LaunchState.SpinningUp;
                startTime = Timer.getFPGATimestamp();
                break;
            case LaunchState.m_kSpinningUp:
                System.out.println("Shooter m_kSpinningUp");
                // wait for extnsion (~0.5s)
                if (Timer.getFPGATimestamp() > (startTime + 0.5)) {
                    state = LaunchState.Shoot;
                }
                break;
            case LaunchState.m_kShoot:
                System.out.println("Shooter m_kShoot");
                AztechRobot.shooter.extendShooter();
                state = LaunchState.Finish;
                startTime = Timer.getFPGATimestamp();
                break;
            case LaunchState.m_kFinish:
                System.out.println("Shooter m_kFinish");
                if (Timer.getFPGATimestamp() > (startTime + 0.5)) {
                    AztechRobot.shooter.retractShooter();
                    isFinished = true;
                }
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isFinished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

/*
 * In all files, search for DEBUG for things that were added or modified
 * due to debugging, and FIXME for things that need to be fixed.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main robot class, runs on startup.
 * @author 3414
 */
public class Main extends SimpleRobot implements IRobot {
    
    RobotControl myRobotControl;
    Compressor myCompressor;
    public static int loopCount;
    
    public Main() {
        myRobotControl = new RobotControl();
        myCompressor = new Compressor(DIO_FOURTEEN, RELAY_TWO);
    }
    
    /**
     * Runs once when the robot turns on.
     */
    public void robotInit() {
        System.out.println("Welcome to HACKBOTS code v1.0 final");
        myCompressor.start();
    }
    
    /**
     * Runs once each time the robot enters autonomous mode.
     */
    public void autonomous() {
        myRobotControl.autonomous();
    }

    /**
     * Runs once each time the robot enters operator control.
     */
    public void operatorControl() {
        
        System.out.println("DEBUG: Entering teleop mode");
        
        //Initialized a counter to keep track of current loop.
        loopCount = 0;
        myRobotControl.resetSystems();
        
        //Main control loop.
        while (isOperatorControl() && isEnabled()) {
            SmartDashboard.putNumber("Current Loop Iteration", loopCount);
            
            /* 
             * Each individual loop updates the bot at 20, 100 and 1000
             * millisecond intervals. 
             */
            if (loopCount%2 == 0) {
                myRobotControl.twentyMSLoop();
            }
            
            if (loopCount%10 == 0) {
                myRobotControl.hundredMSLoop();
            }
            
            if (loopCount%100 == 0) {
                myRobotControl.thousandMSLoop();
            }
            
            loopCount++;
            Timer.delay(TIME_DELAY);
        }
    }
    
    /**
     * Runs periodically while the robot is disabled.
     */
    public void disabled() {
    }
    
    /**
     * Runs once each time the robot enters test mode.
     */
    public void test() {
    }
}

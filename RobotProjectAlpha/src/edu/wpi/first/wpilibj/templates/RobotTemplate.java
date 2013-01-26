/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the SimpleRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends SimpleRobot implements IRobot
{
    public static final double TIME_DELAY = 0.010; // 10 millisecond loop
    
    DriveTrain myDrive = new DriveTrain();
    camera myCamera = new camera();
    
    public void dashboardUpdate()
    {
        myDrive.getDashboard();
    }
    
    public void autonomous()
    {
        
    }

    /**
     * This function is called once each time the robot enters operator control.
     */
    public void operatorControl()
    {
        /**
         * This loops every 10 milliseconds
         */
        while(isOperatorControl() && isEnabled())
        {
            myDrive.drive();
            
            myCamera.getCamera();
            
            dashboardUpdate();
            Timer.delay(TIME_DELAY);
        }
    }
    
    /**
     * This function is called once each time the robot enters test mode.
     */
    public void test()
    {

    }
}

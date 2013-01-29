package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.camera.AxisCamera;

/**
 *
 * @author RSchade
 */


public class camera implements IRobot
{
    
    public void getCamera()
    {     
        AxisCamera.getInstance().writeCompression(0);
        AxisCamera.getInstance().writeResolution(AxisCamera.ResolutionT.k320x240);
        AxisCamera.getInstance().writeBrightness(10);
        DriverStationLCD.getInstance().updateLCD();
    }
}

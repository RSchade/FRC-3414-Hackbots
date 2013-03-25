/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Solenoid;
import farmington.ultimateascent.IRobot;

/**
 *
 * @author Ray Schade and Shrihari B------------i
 */

public class LED implements IRobot
{
    private Solenoid red;
    private Solenoid blue;
    private Solenoid green;
    private int cycle;
    public boolean canCycle;
    
    public LED(int redChannel, int blueChannel, int greenChannel)
    {
       red = new Solenoid(redChannel); 
       blue = new Solenoid(blueChannel);
       green = new Solenoid(greenChannel);
       cycle = 0;
       canCycle = true;
       this.setPurple();
    }
    
    public void cycleColors() {
        if (canCycle) {
            cycle++;
            if (cycle==7) {
                cycle = 0;
            }

            if (cycle==0) {
                this.setPurple();
            } else if (cycle==1) {
                this.setRed();
            } else if (cycle==2) {
                this.setGreen();
            } else if (cycle==3) {
                this.setBlue();
            } else if (cycle==4) {
                this.setNastyBrown();
            } else if (cycle==5) {
                this.setSeaBlue();
            } else if (cycle==6) {
                this.justNo();
            }
        }
    }
    
    public void setPurple()
    {
        red.set(true);
        blue.set(true);
        green.set(false);
    }
    
    public void setRed()
    {
        red.set(true);
        blue.set(false);
        green.set(false);
    }
    
    public void setGreen()
    {
        red.set(false);
        blue.set(false);
        green.set(true);
    }
    
    public void setBlue()
    {
        red.set(false);
        blue.set(true);
        green.set(false);
    }
    
    public void setNastyBrown()
    {
        red.set(true);
        blue.set(false);
        green.set(true);
    }
    
    public void setSeaBlue()
    {
        red.set(false);
        blue.set(true);
        green.set(true);
    }
    
    public void justNo()
    {
        red.set(false);
        blue.set(false);
        green.set(false);
    }
}

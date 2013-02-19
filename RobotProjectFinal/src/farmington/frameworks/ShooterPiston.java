/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 * @author Robotics
 */
public class ShooterPiston {
    
    Solenoid shoot;
    Solenoid reload;
    boolean shooting;
    Waiter shootControl;
    Waiter reloadControl;
    
    public ShooterPiston(int shootChannel, int reloadChannel) {
        shoot = new Solenoid(shootChannel);
        reload = new Solenoid(reloadChannel);
        shooting = false;
        shootControl = new Waiter();
        reloadControl = new Waiter();
    }
    
    public void setWithMinTime(boolean wantToShoot) {
        if (wantToShoot && !shoot.get() && !shooting) {
            shoot.set(true);
            reload.set(false);
            shooting = true;
            shootControl.waitXLoops(12);
        }
        if (shootControl.timeUp()) {
            shoot.set(false);
            reload.set(true);
            reloadControl.waitXLoops(10);
        }
        if (reloadControl.timeUp()) {
            shooting = false;
        }
    }
    
    public void set(boolean shooting) {
        shoot.set(shooting);
        reload.set(!shooting);
    }
    
    public boolean get() {
        return shoot.get();
    }
    
    public void reset() {
        shootControl.reset();
    }
}

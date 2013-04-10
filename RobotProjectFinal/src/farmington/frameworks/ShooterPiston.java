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
    boolean readyToShoot;
    Waiter shootControl;
    Waiter reloadControl;
    
    public ShooterPiston(int shootChannel, int reloadChannel) {
        shoot = new Solenoid(shootChannel);
        reload = new Solenoid(reloadChannel);
        readyToShoot = true;
        shootControl = new Waiter();
        reloadControl = new Waiter();
    }
    
    public void shootWithTimeDelay(boolean wantToShoot) {
        if (wantToShoot && readyToShoot) {
            shoot.set(true);
            reload.set(false);
            //Extends the piston for 240 ms
            shootControl.waitXms(240);
            //Retracts the piston after 240 ms and waits for 900 more ms
            reloadControl.waitXms(240+900);
            readyToShoot = false;
        }
        if (shootControl.timeUp()) {
            shoot.set(false);
            reload.set(true);
        }
        if (reloadControl.timeUp()) {
            readyToShoot = true;
        }
    }
    
    public void set(boolean shooting, double shooterSpeed) {
        if (shooterSpeed > 0.0) {
            shoot.set(shooting);
            reload.set(!shooting);
        } else {
            shoot.set(false);
            reload.set(false);
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

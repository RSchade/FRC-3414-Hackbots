/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Controls the pneumatic lifter arm.
 * @author 3414
 */
public class PyramidLifter {
    
    DoubleSolenoid liftArm;
    
    /**
     * Main constructor for PyramidLifter.
     * @param liftArmForwardChannel forward solenoid channel for first piston
     * @param liftArmReverseChannel reverse solenoid channel for first piston
     */
    public PyramidLifter(int liftArmForwardChannel, int liftArmReverseChannel) {
        liftArm = new DoubleSolenoid(liftArmForwardChannel, liftArmReverseChannel);
    }
    
    public PyramidLifter(int module, int liftArmForwardChannel, int liftArmReverseChannel) {
        liftArm = new DoubleSolenoid(1, liftArmForwardChannel, liftArmReverseChannel);
    }
    
    public void alternate() {
        if (liftArm.get().equals(DoubleSolenoid.Value.kForward)) {
            liftArm.set(DoubleSolenoid.Value.kReverse);
        } else {
            liftArm.set(DoubleSolenoid.Value.kForward);
        }
    }
    
    /**
     * Moves the lifter arm up.
     */
    public void goUp() {
        liftArm.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Moves the lifter arm down.
     */
    public void goDown() {
        liftArm.set(DoubleSolenoid.Value.kReverse);
    }
}

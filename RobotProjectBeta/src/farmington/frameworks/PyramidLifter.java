/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Controls the pneumatic lifter arm.
 * @author 3414
 */
public class PyramidLifter {
    
    DoubleSolenoid liftArmA;
    DoubleSolenoid liftArmB;
    
    /**
     * Main constructor for PyramidLifter.
     * @param liftArmAForwardChannel forward solenoid channel for first piston
     * @param liftArmAReverseChannel reverse solenoid channel for first piston
     * @param liftArmBForwardChannel forward solenoid channel for second piston
     * @param liftArmBReverseChannel reverse solenoid channel for second piston
     */
    public PyramidLifter(int liftArmAForwardChannel, int liftArmAReverseChannel, int liftArmBForwardChannel, int liftArmBReverseChannel) {
        liftArmA = new DoubleSolenoid(liftArmAForwardChannel, liftArmAReverseChannel);
        liftArmB = new DoubleSolenoid(liftArmBForwardChannel, liftArmBReverseChannel);
    }
    
    /**
     * Moves the lifter arm up.
     */
    private void goUp() {
        liftArmA.set(DoubleSolenoid.Value.kForward);
        liftArmB.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Moves the lifter arm down.
     */
    private void goDown() {
        liftArmA.set(DoubleSolenoid.Value.kReverse);
        liftArmB.set(DoubleSolenoid.Value.kReverse);
    }
    
    /**
     * Sets the lifter to the value of a button.
     * @param up joystick button connected to on/off value
     */
    public void update(boolean up) {
        if (up) {
            goUp();
        } else {
            goDown();
        }
    }
}

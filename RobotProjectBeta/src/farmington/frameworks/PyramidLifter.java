/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 * @author Cooper Riehl
 */
public class PyramidLifter {
    
    DoubleSolenoid liftArmA;
    DoubleSolenoid liftArmB;
    
    public PyramidLifter(int liftArmAForwardChannel, int liftArmAReverseChannel, int liftArmBForwardChannel, int liftArmBReverseChannel) {
        liftArmA = new DoubleSolenoid(liftArmAForwardChannel, liftArmAReverseChannel);
        liftArmB = new DoubleSolenoid(liftArmBForwardChannel, liftArmBReverseChannel);
    }
    
    public void goUp() {
        liftArmA.set(DoubleSolenoid.Value.kForward);
        liftArmB.set(DoubleSolenoid.Value.kForward);
    }
    
    public void goDown() {
        liftArmA.set(DoubleSolenoid.Value.kReverse);
        liftArmB.set(DoubleSolenoid.Value.kReverse);
    }
}

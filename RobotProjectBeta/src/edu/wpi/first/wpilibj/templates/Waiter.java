/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Robotics
 */
public class Waiter extends LoopHandler {
    
    private int targetIteration;
    
    public void waitXLoops(int waitTime) {
        this.targetIteration = getCurrentIteration() + waitTime;
    }
    
    public boolean timeUp() {
        int thisLoop = getCurrentIteration();
        if (thisLoop == targetIteration) {
            return true;
        }
        return false;
    }
}

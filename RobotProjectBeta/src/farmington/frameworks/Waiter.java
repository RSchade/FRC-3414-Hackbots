/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

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
        if (getCurrentIteration() == targetIteration) {
            return true;
        }
        return false;
    }
}
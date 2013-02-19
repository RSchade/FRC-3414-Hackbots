/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

import farmington.ultimateascent.Main;

/**
 *
 * @author 3414
 */
public class Waiter {
    
    private int targetIteration;
    
    public Waiter() {
        targetIteration = -1;
    }
    
    public void waitXLoops(int waitTime) {
        this.targetIteration = getCurrentIteration() + waitTime;
    }
    
    public int getCurrentIteration() {
        return Main.loopCount;
    }
    
    public void reset() {
        this.targetIteration = -1;
    }
    
    public boolean isWaiting() {
        if (getCurrentIteration() < targetIteration) {
            return true;
        }
        return false;
    }
    
    public boolean timeUp() {
        if (getCurrentIteration() >= targetIteration) {
            return true;
        }
        return false;
    }
}

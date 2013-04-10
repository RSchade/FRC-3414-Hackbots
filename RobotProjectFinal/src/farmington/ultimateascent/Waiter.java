/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.ultimateascent;

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
    
    public void waitXms(int waitTime) {
        int loopsToAdd = (int)(waitTime/20);
        this.targetIteration = getCurrentIteration() + loopsToAdd;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

/**
 *
 * @author Robotics
 */
public class LoopHandler {
    
    private static int loopCount;
    
    public static void updateLoopCount(int counter) {
        loopCount = counter;
    }
    
    public static int getCurrentIteration() {
        return loopCount;
    }
}
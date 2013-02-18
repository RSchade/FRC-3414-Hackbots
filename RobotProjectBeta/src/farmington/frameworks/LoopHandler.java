/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package farmington.frameworks;

/**
 * Main loop iteration handler.
 * @author Cooper Riehl
 */
public class LoopHandler {
    
    private static int loopCount;
    
    /**
     * Updates the global loop count with input from Main.java.
     * @param counter the current loop count
     */
    public static void updateLoopCount(int counter) {
        loopCount = counter;
    }
    
    /**
     * Returns the current loop.
     * @return current iteration
     */
    public static int getCurrentIteration() {
        return loopCount;
    }
}

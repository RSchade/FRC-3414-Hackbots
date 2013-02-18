package oldcode;

//package deprecated;
//
//import edu.wpi.first.wpilibj.Solenoid;
//import edu.wpi.first.wpilibj.templates.IRobot;
//                                                        // Joystick and Solenoid buttons
//public class ForkLift implements IRobot {
//
//    /* Created by Josh Kavner
//     * IN THIS CLASS, ALL METHODS NEED:
//     * import edu.wpi.first.wpilibj.Solenoid;
//     * import edu.wpi.first.wpilibj.Joystick;
//     * import edu.wpi.first.wpilibj.SimpleRobot;
//     * 
//     * Joystick controller = new Joystick(1);
//     * Solenoid testMotor = new Solenoid(1);
//     */
//    
//     Solenoid AirPistonLifter = new Solenoid(1);
//     int JOYSTICK_BUTTON_ONE = 1;
//
//    public void lifter()
//    {
//            int pistonPulse = 1;
//            int pistonState = 1;
//                if (rightStick.getRawButton(JOYSTICK_BUTTON_ONE) == true && pistonState == 1)       // Every time joystick button is pressed once,      
//                {							
//                    pistonPulse++;                                                                  // Pulse increses
//                    pistonState = 0;                                                                // pistonState = 0, so this "if statement" is deactivated
//                }                                                                                   // and the joystick button won't do anything until it's releaced
//                if (rightStick.getRawButton(JOYSTICK_BUTTON_ONE) == false)                
//                {                                                       
//                    pistonState = 1;                                                                // Once the joystick button is released, pistonState resets
//                }
//                if (pistonPulse%2==0)                                                               // Pulse is even or odd to turn the Solenoid on/off
//                {
//                    AirPistonLifter.set(true);                                 
//                }							
//                else                                                    
//                {
//                    AirPistonLifter.set(false);                                                             
//                }
//                
//                
//                
//    }
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//}

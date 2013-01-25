/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author 06335
 */
public class Spinner implements IRobot{
    Talon shooterMotor = new Talon(5);
    Solenoid shooterControlPiston = new Solenoid(1);
    Talon shooterControlMotor = new Talon (6);    
        
        
        
        
    int i = 1;
    int a = 1;
    int m = 1;                                                              
    boolean n = false;     
    
        public void SetShooter()
        {
             if (rightStick.getRawButton(1) == true && a == 1)                   // if the button is pressed once,                           i=     controller button     a=
                        {                                                                   // and if arbitrary integer "a" is equal to 1                           
                            i++;                                                            // "i" increases by one and "a" is set to 0                 1           deactivated      activated
                            a = 0;                                                          //                                                          2           activated        activated
                        }                                                                   //                                                          2           activated        deactivated
                        if (rightStick.getRawButton(1) == false)                            //                                                          2           deactivated      activated
                        {                                                                   //                                                          3...        activated        activated
                            a = 1;                                                          // "a" is set back to 1 to allow for the motor to change.
                        }
                        if (i%2==0)                                                         // if "i" is even,
                        {
                            shooterMotor.set(1.0);                                             // motor runs at full power
                        }                                                                   // ^ We need to use a double for the motor set value
                        else                                                                // if "i" is odd,
                        {
                            shooterMotor.set(0.0);                                             // motor stops running                             
                        }                                                                   // *end shooter code* *start piston code*
        }

        public void ShooterPiston()
        {                                                                                   // *start shooter code*   
                         if (rightStick.getRawButton(2) == true || n == true)               // 1. if the button is pressed    -or-   n = true :                                   
                        {                                                                   // 2.                           
                            shooterControlPiston.set(true);                                        // 3. piston is pushed out
                            m++;                                                            // 4. m increases by 1
                            n = true;                                                       // 5. n = true
                            if (m==300) {                                                   // 6.               (**if m =/= 300: loop restarts (16) and 
                                                                                            // 7.                m increases for 3 seconds before running the next part)                 
                                                                                            // 8. if m = 300:                 
                                shooterControlPiston.set(false);                                   // 9.       reset: piston is pulled back in
                                n = false;                                                  // 10.      reset: n = false
                                m = 1;                                                      // 11.      reset: m restarts back to 1
                            }
                        }    
        }

        public void ShooterMotor()
        {
                         if (rightStick.getRawButton(2) == true || n == true)               // 1. if the button is pressed    -or-   n = true :                                   
                        {                                                                   // 2.                           
                            shooterControlMotor.set(1);                                        // 3. piston is pushed out
                            m++;                                                            // 4. m increases by 1
                            n = true;                                                       // 5. n = true
                            if (m==300) {                                                   // 6.               (**if m =/= 300: loop restarts (16) and 
                                                                                            // 7.                m increases for 3 seconds before running the next part)                 
                                                                                            // 8. if m = 300:                 
                                shooterControlMotor.set(0);                                   // 9.       reset: piston is pulled back in
                                n = false;                                                  // 10.      reset: n = false
                                m = 1;                                                      // 11.      reset: m restarts back to 1
                            }
                        }            
        }
                 
                 
                 
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wpi.first.wpilibj.templates;

/**
 *
 * @author Cooper Riehl
 */

public interface IRobot {
    
    final double TIME_DELAY = 0.010; // 10 millisecond loop
    
    final double SPEED_FORWARD_FULL = 1.0;  //Motor speed values
    final double SPEED_FORWARD_HALF = 0.5;
    final double SPEED_STOP = 0.0;
    final double SPEED_REVERSE_HALF = -0.5;
    final double SPEED_REVERSE_FULL = -1.0;
    
    final boolean ON = true;        //Solenoid values
    final boolean OFF = false;
    
    final int PWM_SLOT_ONE = 1;     //PWM slots
    final int PWM_SLOT_TWO = 2;
    final int PWM_SLOT_THREE = 3;
    final int PWM_SLOT_FOUR = 4;
    final int PWM_SLOT_FIVE = 5;
    final int PWM_SLOT_SIX = 6;
    final int PWM_SLOT_SEVEN = 7;
    final int PWM_SLOT_EIGHT = 8;
    final int PWM_SLOT_NINE = 9;
    final int PWM_SLOT_TEN = 10;
    
    final int USB_ONE = 1;          //USB ports
    final int USB_TWO = 2;
    
    final int DIO_ONE = 1;          //DIO slots
    
    final int HORIZ_AXIS = 1;       //Joystick axis
    final int VERTICAL_AXIS = 2;
    
    final int RIGHT_TRIGGER = 1;    //Joystick buttons
    final int RIGHT_BUTTON_TWO = 2;
    final int RIGHT_BUTTON_THREE = 3;
    final int LEFT_TRIGGER = 1;
    final int LEFT_BUTTON_TWO = 2;
    final int LEFT_BUTTON_THREE = 3;
    
    final double MAX_ANGLE = 50.0;  //Shooter angles
    final double MIN_ANGLE = 0.0;
    
    final int RED_MIN = 0;          //Threshold levels for Red, Green and Blue
    final int RED_MAX = 45;
    final int GRN_MIN = 25;
    final int GRN_MAX = 255;
    final int BLU_MIN = 0;
    final int BLU_MAX = 47;
}

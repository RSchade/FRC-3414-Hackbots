
package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Main extends SimpleRobot implements IRobot {

    Joystick leftStick = new Joystick(USB_ONE);
    Joystick rightStick = new Joystick(USB_TWO);
    
    DriveTrain myDrive = new DriveTrain(PWM_SLOT_ONE, PWM_SLOT_THREE, PWM_SLOT_TWO, PWM_SLOT_FOUR);
    Camera myCamera = new Camera();
    LightSensor photosensor = new LightSensor(DIO_ONE, PWM_SLOT_FIVE);
    Shooter myShooter = new Shooter(PWM_SLOT_SEVEN, PWM_SLOT_EIGHT, SOLENOID_ONE);
    Screw myScrew = new Screw(PWM_SLOT_SIX);
    LEDController myLEDControl = new LEDController(RELAY_ONE);

    
    public void dashboardUpdate() {
        photosensor.getDashboard();
        myDrive.getDashboard();
    }
    
    public void setInitCameraValues() {
        SmartDashboard.putNumber("RedMinVal", 245);
        SmartDashboard.putNumber("RedMaxVal", 255);
        SmartDashboard.putNumber("GrnMinVal", 245);
        SmartDashboard.putNumber("GrnMaxVal", 255);
        SmartDashboard.putNumber("BluMinVal", 245);
        SmartDashboard.putNumber("BluMaxVal", 255);
    }
    
    public void findVisionTarget(ParticleAnalysisReport target) {
        boolean isOnTargetX = false;
        boolean isOnTargetY = false;
        
        if (target.center_mass_x_normalized < -0.9) {
            myDrive.update(0.25, -0.25);
        } else if (target.center_mass_x_normalized > 0.9) {
            myDrive.update(-0.25, 0.25);
        } else {
            isOnTargetX = true;
        }
        
        if (target.center_mass_y_normalized < -0.9) {
            myScrew.updateAuto(0.25);
        } else if (target.center_mass_y_normalized > 0.9) {
            myScrew.updateAuto(-0.25);
        } else {
            isOnTargetY = true;
        }
        
        if (isOnTargetX && isOnTargetY) {
            SmartDashboard.putBoolean("Ready for shooting?", true);
        } else {
            SmartDashboard.putBoolean("Ready for shooting?", false);
        }
    }
    
//    public void disableAll() {
//        myDrive.disable();
//        myScrew.disable();
//        myLEDControl.disable();
//    }
//    
//    public void enableAll() {
//        myDrive.enable();
//        myScrew.enable();
//        myLEDControl.enable();
//    }
    
    public void autonomous() {
        
    }

    public void operatorControl() {
        
        setInitCameraValues();
        int targetLoopControl = 0;
        ParticleAnalysisReport target = null;
        
        /**
         * This loops every 10 milliseconds
         */
        while(isOperatorControl() && isEnabled()) {
            
            //Stop other processes and shoot at a target
            if (leftStick.getRawButton(LEFT_BUTTON_EIGHT)) {
//                disableAll();
                if (targetLoopControl==0) {
                    target = myCamera.centerCalculate();
                }
                if (target != null) {
                    findVisionTarget(target);
                }
                targetLoopControl++;
                if (targetLoopControl==20) {
                    targetLoopControl = 0;
                }
                
                boolean areWeReady = SmartDashboard.getBoolean("Ready for shooting?");
                if (areWeReady) {
                    //SHOOT A FRISBEE
                }
            } else {
                targetLoopControl = 0;
//                enableAll();
            
                //Take a picture with the camera for processing
                myCamera.setValues();
                myCamera.takePicture(leftStick.getRawButton(LEFT_TRIGGER));
            
                //Update systems
                myDrive.update(leftStick.getRawAxis(VERTICAL_AXIS), rightStick.getRawAxis(VERTICAL_AXIS));
                myScrew.updateManual(leftStick.getRawButton(LEFT_BUTTON_TWO), leftStick.getRawButton(LEFT_BUTTON_THREE));
                myLEDControl.update(rightStick.getRawButton(RIGHT_TRIGGER));
                dashboardUpdate();
            
                //Loop delay
                Timer.delay(TIME_DELAY);
            }
        }
    }
    
    public void test() {

    }
}

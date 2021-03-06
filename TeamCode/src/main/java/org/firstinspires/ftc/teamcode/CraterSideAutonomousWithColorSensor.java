/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.util.ElapsedTime;


import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Locale;


/**
 * This file illustrates the concept of driving a path based on encoder counts.
 * It uses the common Pushbot hardware class to define the drive on the robot.
 * The code is structured as a LinearOpMode
 *
 * The code REQUIRES that you DO have encoders on the wheels,
 *   otherwise you would use: PushbotAutoDriveByTime;
 *
 *  This code ALSO requires that the drive Motors have been configured such that a positive
 *  power command moves them forwards, and causes the encoders to count UP.
 *
 *   The desired path in this example is:
 *   - Drive forward for 48 inches
 *   - Spin right for 12 Inches
 *   - Drive Backwards for 24 inches
 *   - Stop and close the claw.
 *
 *  The code is written using a method called: encoderDrive(speed, leftInches, rightInches, timeoutS)
 *  that performs the actual movement.
 *  This methods assumes that each movement is relative to the last stopping place.
 *  There are other ways to perform encoder based moves, but this method is probably the simplest.
 *  This code uses the RUN_TO_POSITION mode to enable the Motor controllers to generate the run profile
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@Autonomous(name="CraterSideAutonomouswithColorSensor", group="Pushbot")
public class CraterSideAutonomousWithColorSensor extends LinearOpMode {
    HardwarePushbot robot = new HardwarePushbot();

    public ColorSensor Color_Sensor;
    public DistanceSensor Distance_Sensor;

    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 1440;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 60.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 4.0;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double DRIVE_SPEED = 1;
    static final double TURN_SPEED = 0.5;
    static final double ARM_SPEED = 0.5;

    //int Blue = Color_Sensor.blue();
    //int Red = Color_Sensor.red();
    //int Green = Color_Sensor.green();


    // hsvValues is an array that will hold the hue, saturation, and value information.
    float hsvValues[] = {0F, 0F, 0F};

    // values is a reference to the hsvValues array.
    final float values[] = hsvValues;

    // sometimes it helps to multiply the raw RGB values with a scale factor
    // to amplify/attentuate the measured values.
    final double SCALE_FACTOR = 255;


    @Override
    public void runOpMode() throws InterruptedException {


        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);


        //initialize the servos

        robot.Robot_Latch.setPosition(0);
        robot.Robot_Release.setPosition(0.5);
        robot.Robot_Open.setPosition(0);
        robot.Scoop_Servo.setPosition(0);


        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.Left_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Right_Wheel.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Arm_Extend.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.Arm_Raise.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.Left_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Right_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Arm_Extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.Arm_Raise.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.Left_Wheel.getCurrentPosition(),
                robot.Right_Wheel.getCurrentPosition());
        robot.Arm_Extend.getCurrentPosition();
        robot.Arm_Raise.getCurrentPosition();
        telemetry.update();

        // get a reference to the color sensor.
        Color_Sensor = hardwareMap.get(ColorSensor.class, "sensor_color_distance");

        // get a reference to the distance sensor that shares the same name.
        Distance_Sensor = hardwareMap.get(DistanceSensor.class, "sensor_color_distance");


        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // Color Sensor Stuff-----------------------------------------------------------------------
        while (opModeIsActive()) {
            // convert the RGB values to HSV values.
            // multiply by the SCALE_FACTOR.
            // then cast it back to int (SCALE_FACTOR is a double)

            Color.RGBToHSV((int) (Color_Sensor.red() * SCALE_FACTOR),
                    (int) (Color_Sensor.green() * SCALE_FACTOR),
                    (int) (Color_Sensor.blue() * SCALE_FACTOR),
                    hsvValues);

            // send the info back to driver station using telemetry function.
            telemetry.addData("Distance (cm)",
                    String.format(Locale.US, "%.02f", Distance_Sensor.getDistance(DistanceUnit.CM)));
            telemetry.addData("Alpha", Color_Sensor.alpha());
            telemetry.addData("Red  ", Color_Sensor.red());
            telemetry.addData("Green", Color_Sensor.green());
            telemetry.addData("Blue ", Color_Sensor.blue());
            telemetry.addData("Hue", hsvValues[0]);


            telemetry.update();
            int step = 0;

            switch (step) {
                case 0:
                    //
                    // Lower the Claw
                    //
                    encoderArmExtend(1,1,1);
                    step ++;
                    break;

                case 1:
                    //
                    //Unfold the robot
                    //
                    encoderArmRaise(1,1,1);
                    step ++;
                    break;
                case 2:
                    //
                    //Release the pin
                    //
                    robot.Robot_Release.setPosition(0);
                    step ++;
                    break;
                case 11:
                    //
                    //fold the arm down
                    //
                    encoderArmRaise(1,1,1);
                    step ++;
                    break;
                case 3:
                    //
                    // drive away form lander
                    //
                    encoderDrive(1,20,20,15);
                    step ++;
                    break;
                case 4:
                    //
                    //turn left for block reading positioning
                    //
                    encoderDrive(1,-5,5,15);
                    step ++;
                    break;
                case 5:
                    //
                    // drive backwards positioning for block reading
                    //
                    encoderDrive(1,-25,-25,15);

                    step ++;
                    break;

                case 6:
                    //
                    // read blocks
                    //
                    encoderDrive(1,70,70,15);

                    step ++;
                    break;
                case 7:
                    //
                    // turn left towards marker area
                    //
                    encoderDrive(1,-5,5,15);

                    step ++;
                    break;
                case 8:
                    //
                    // Drive into marker area
                    //
                    encoderDrive(1,61,61,15);

                    step ++;
                    break;

                case 9:
                    //
                    // release marker
                    //
                    robot.Scoop_Servo.setPosition(0);

                    step ++;
                    break;

                case 10:
                    //
                    // back out of marker spot
                    //
                    encoderDrive(1,-18,-18,15);

                    step ++;


                    break;

                    

                 default:
                     //
                     //Done
                     //
            }
            stop();
        }

    }


        // A bunch of code that needs to go in the steps
        //encoderDrive(1, 6, 6, 10);


        // Step through each leg of the path,
        // Note: Reverse movement is obtained by setting a negative distance (not speed)

        /*
        encoderArmExtend(ARM_SPEED,  20, 10);
        robot.Robot_Open.setPosition(1);
        encoderArmExtend(ARM_SPEED,  1, 10);
        sleep(200);
        robot.Robot_Release.setPosition(0);
        encoderArmRaise(ARM_SPEED, 1, 5);
        encoderArmRaise(ARM_SPEED, -2, 10);
        encoderDrive(DRIVE_SPEED,  15,  15, 10.0);
        */
        //ColorSensor


        //encoderDrive(DRIVE_SPEED, -2, 2, 3);
        /*
        encoderDrive(DRIVE_SPEED,  -12,  -12, 10.0);
        encoderDrive(DRIVE_SPEED,  -12,  12, 10.0);
        encoderDrive(DRIVE_SPEED,  50,  50, 15.0);
        encoderDrive(DRIVE_SPEED,  -4,  -4, 15.0);
        encoderDrive(DRIVE_SPEED,  42,  42, 15.0);
        robot.Scoop_Servo.setPosition(1);
        encoderDrive(DRIVE_SPEED,  -60,  -60, 15.0);
        robot.Scoop_Servo.setPosition(0);
        */


        /*
         *  Method to perfmorm a relative move, based on encoder counts.
         *  Encoders are not reset as the move is based on the current position.
         *  Move will stop if any of three conditions occur:
         *  1) Move gets to the desired position
         *  2) Move runs out of time
         *  3) Driver stops the opmode running.
         */

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftTarget;
        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.Left_Wheel.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.Right_Wheel.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.Left_Wheel.setTargetPosition(newLeftTarget);
            robot.Right_Wheel.setTargetPosition(newRightTarget);

            // Turn On RUN_TO_POSITION
            robot.Left_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.Right_Wheel.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            robot.Left_Wheel.setPower(Math.abs(speed));
            robot.Right_Wheel.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                   (runtime.seconds() < timeoutS) &&
                   (robot.Left_Wheel.isBusy() && robot.Right_Wheel.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newLeftTarget,  newRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                                            robot.Left_Wheel.getCurrentPosition(),
                                            robot.Right_Wheel.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            robot.Left_Wheel.setPower(0);
            robot.Right_Wheel.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.Left_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.Right_Wheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move


        }
    }  public void encoderArmExtend(double speed,
                                double leftInches,
                                double timeoutS) {
        int newLeftTarget;


        // Ensure that the opmode is still active
        if (opModeIsActive()) {


            // Determine new target position, and pass to motor controller
            newLeftTarget = robot.Arm_Extend.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);

            robot.Arm_Extend.setTargetPosition(newLeftTarget);

            telemetry.addData("speed", speed);
            telemetry.addData("newleftTarget", newLeftTarget);

            telemetry.update();
            sleep(500);


            // Turn On RUN_TO_POSITION
            robot.Arm_Extend.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            // reset the timeout time and start motion.
            runtime.reset();
            robot.Arm_Extend.setPower(Math.abs(speed));


            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.Arm_Extend.isBusy() )) {


            }

            telemetry.addData("Finished moving", "");
            telemetry.update();

            // Stop all motion;
            robot.Arm_Extend.setPower(0);


            // Turn off RUN_TO_POSITION
            robot.Arm_Extend.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }  public void encoderArmRaise(double speed, double rightInches,
                              double timeoutS) {

        int newRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {


            // Determine new target position, and pass to motor controller

            newRightTarget = robot.Arm_Raise.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);

            robot.Arm_Raise.setTargetPosition(newRightTarget);
            telemetry.addData("speed", speed);

            telemetry.addData("newRightTarget", newRightTarget);
            telemetry.update();
            sleep(3000);


            // Turn On RUN_TO_POSITION

            robot.Arm_Raise.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();

            robot.Arm_Raise.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.Arm_Raise.isBusy() )) {


            }

            telemetry.addData("Finished moving", "");
            telemetry.update();

            // Stop all motion;

            robot.Arm_Raise.setPower(0);

            // Turn off RUN_TO_POSITION

            robot.Arm_Raise.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        }
    }

}

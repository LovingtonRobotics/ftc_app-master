package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp
public class GamePad2 extends OpMode {
    HardwarePushbot robot = new HardwarePushbot();

   double leftwheelpower;
   double rightwheelpower;

   double leftwheelpowerTwo;
   double rightwheelpowerTwo;


   double armuppower;
   double armdownpower;

   double armuppower2;
   double armdownpower2;

   boolean ForwardScoopCurrState = false;
   boolean ForwardScoopPrevState = false;

   boolean ForwardScoopOn = true;







    @Override
    public void init() {

    robot.init(hardwareMap);



    robot.Robot_Latch.setPosition(.45);
    robot.Robot_Release.setPosition(0);
    robot.Robot_Open.setPosition(1);

    }

    @Override
    public void loop() {

        leftwheelpower = gamepad1.right_stick_y;
        rightwheelpower = gamepad1.left_stick_y;


        leftwheelpowerTwo = -gamepad2.left_stick_y;
        rightwheelpowerTwo = -gamepad2.right_stick_y;


        armuppower = gamepad1.right_trigger;
        armdownpower = gamepad1.left_trigger;

        armuppower2 = gamepad2.right_trigger;
        armdownpower2 = gamepad2.left_trigger;


        if (leftwheelpower != 0 || rightwheelpower != 0) {
            robot.Left_Wheel.setPower(leftwheelpower);
            robot.Right_Wheel.setPower(rightwheelpower);
        } else {
            robot.Left_Wheel.setPower(leftwheelpowerTwo);
            robot.Right_Wheel.setPower(rightwheelpowerTwo);
        }


        if (gamepad1.x) {
            robot.Robot_Latch.setPosition(0.45);
        } else if (gamepad1.y) {
            robot.Robot_Latch.setPosition(.6);
        }


        if (gamepad1.a) {
            robot.Robot_Release.setPosition(.5);
        } else if (gamepad1.b) {
            robot.Robot_Release.setPosition(0);
        }

        if (armdownpower != 0 || armuppower != 0) {

            if (gamepad1.right_trigger > 0 && gamepad1.left_trigger > 0) {
                robot.Arm_Raise.setPower(0);
            } else if (gamepad1.right_trigger > 0) {
                robot.Arm_Raise.setPower(armuppower);
            } else if (gamepad1.left_trigger > 0) {
                robot.Arm_Raise.setPower(-armdownpower);

            } else {
                robot.Arm_Raise.setPower(0);
            }
        } else {
            if (gamepad2.right_trigger > 0 && gamepad2.left_trigger > 0) {
                robot.Arm_Raise.setPower(0);
            } else if (gamepad1.right_trigger > 0) {
                robot.Arm_Raise.setPower(armuppower2 / 6);
            } else if (gamepad1.left_trigger > 0) {
                robot.Arm_Raise.setPower(-armdownpower2 / 6);

            } else {
                robot.Arm_Raise.setPower(0);
            }
        }


        if (gamepad1.right_bumper) {
            robot.Arm_Extend.setPower(.7);
        } else if (gamepad1.left_bumper) {
            robot.Arm_Extend.setPower(-.7);
        } else {
            robot.Arm_Extend.setPower(0);

        }


        ForwardScoopCurrState = gamepad1.x;

        // check for button state transitions.
        if (ForwardScoopCurrState && (ForwardScoopCurrState != ForwardScoopPrevState)) {

            // button is transitioning to a pressed state. So Toggle LED
            //ForwardScoopOn = !ForwardScoopOn;
            ForwardScoopOn = !ForwardScoopOn;
            if (ForwardScoopOn) {
                robot.Scoop_Spin_Left.setPower(1);
                robot.Scoop_Spin_Right.setPower(0);
                }
            else {
                robot.Scoop_Spin_Left.setPower(.5);
                robot.Scoop_Spin_Right.setPower(.5);
                }

            ForwardScoopPrevState = ForwardScoopCurrState;
        }
    }


}

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;


@TeleOp
public class GamePad extends OpMode {
    HardwarePushbot robot = new HardwarePushbot();

   double leftwheelpower;
   double rightwheelpower;


   double armuppower;
   double armdownpower;

   ColorSensor Color_sensor;

    @Override
    public void init() {

    robot.init(hardwareMap);

    Color_sensor = hardwareMap.colorSensor.get("color");

    robot.Robot_Latch.setPosition(.45);
    robot.Robot_Release.setPosition(0);
    robot.Robot_Open.setPosition(1);

    }

    @Override
    public void loop() {

        leftwheelpower = gamepad1.right_stick_y;
        rightwheelpower = gamepad1.left_stick_y;


        armuppower = gamepad1.right_trigger;
        armdownpower = gamepad1.left_trigger;

        robot.Left_Wheel.setPower(leftwheelpower);
        robot.Right_Wheel.setPower(rightwheelpower);


        if (gamepad1.x) {
            robot.Robot_Latch.setPosition(0.45);
        }
         else if (gamepad1.y){
            robot.Robot_Latch.setPosition(.6);
        }

        if (gamepad1.a) {
            robot.Robot_Release.setPosition(.5);
            Color_sensor.enableLed(true);  // turns on led when pin goes in
        }
        else if (gamepad1.b){
            robot.Robot_Release.setPosition(0);
            Color_sensor.enableLed(false);  // Turns off led when pin is pulled out
        }


        if (gamepad1.right_trigger > 0 && gamepad1.left_trigger > 0){
            robot.Arm_Raise.setPower(0);
        }
        else if (gamepad1.right_trigger > 0 ) {
            robot.Arm_Raise.setPower(armuppower/6);
        } else if (gamepad1.left_trigger > 0) {
            robot.Arm_Raise.setPower(-armdownpower/6);

        } else {
            robot.Arm_Raise.setPower(0);
        }


        if (gamepad1.right_bumper) {
            robot.Arm_Extend.setPower(.7);
        } else if (gamepad1.left_bumper) {
            robot.Arm_Extend.setPower(-.7);
        } else {
            robot.Arm_Extend.setPower(0);

        }
    }
    public void stop() {}
}

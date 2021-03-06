/*
Copyright (c) 2016 Robert Atkinson

All rights reserved.

Redistribution and use in source and binary forms, with or without modification,
are permitted (subject to the limitations in the disclaimer below) provided that
the following conditions are met:

Redistributions of source code must retain the above copyright notice, this list
of conditions and the following disclaimer.

Redistributions in binary form must reproduce the above copyright notice, this
list of conditions and the following disclaimer in the documentation and/or
other materials provided with the distribution.

Neither the name of Robert Atkinson nor the names of his contributors may be used to
endorse or promote products derived from this software without specific prior
written permission.

NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESSFOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR
TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
*/
package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a PushBot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Archetype", group="MyTeleOp")  // @Autonomous(...) is the other common choice
public class ArchetypeTeleOp extends OpMode
{
    /* Declare OpMode members. */
    private ElapsedTime runtime = new ElapsedTime();

    // test servos
    //private Servo leftservo = null;
    //private Servo rightservo = null;

    // wheel motors
    private DcMotor frontleft = null;
    private DcMotor frontright = null;
    private DcMotor backleft = null;
    private DcMotor backright = null;

    private DcMotor shooting1 = null;
    private DcMotor shooting2 = null;
    private DcMotor collect1 = null;
    private Servo beacon = null;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        // set up servo
        //leftservo = hardwareMap.servo.get("leftservo");
        //rightservo = hardwareMap.servo.get("rightservo");

        //leftservo.setDirection(Servo.Direction.REVERSE);
        // set up motors
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");
        shooting1 = hardwareMap.dcMotor.get("shooting1");
        shooting2 = hardwareMap.dcMotor.get("shooting2");
        collect1 = hardwareMap.dcMotor.get("collect1");
        beacon = hardwareMap.servo.get("beacon");

        // set motor directions
        frontleft.setDirection(DcMotorSimple.Direction.FORWARD);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.setDirection(DcMotorSimple.Direction.REVERSE);

        shooting1.setDirection(DcMotorSimple.Direction.FORWARD);
        shooting2.setDirection(DcMotorSimple.Direction.FORWARD);

        frontleft.setPower(0);
        frontright.setPower(0);
        backleft.setPower(0);
        backright.setPower(0);

        beacon.setPosition(1);

        telemetry.addData("Status", "Initialized");
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());
        //telemetry.addData("Power", gamepad1.y);

        // set position of servos
        //leftservo.setPosition(gamepad1.y ? 1 : 0);
        //rightservo.setPosition(gamepad1.y ? 1 : 0);

        // test the encoders
//        telemetry.addData("Frontright Position: ", frontright.getCurrentPosition());
//        telemetry.addData("frontleft Position: ", frontleft.getCurrentPosition());
//        telemetry.addData("backright Position: ", backright.getCurrentPosition());
//        telemetry.addData("backleft Position: ", backleft.getCurrentPosition());

        // TODO: Fix controls for setting power
        telemetry.addData("frontright power", frontright.getPower());
        telemetry.addData("frontleft power", frontleft.getPower());
        telemetry.addData("backright power", backright.getPower());
        telemetry.addData("backleft power", backleft.getPower());
        frontright.setPower(gamepad1.right_stick_y);
        frontleft.setPower(gamepad1.left_stick_x);
        backright.setPower(gamepad1.right_stick_x);
        backleft.setPower(gamepad1.left_stick_y);

        // gamepad2
        // shooting
        if(gamepad2.y) {
            shooting1.setPower(1);
            shooting2.setPower(1);
        } else {
            shooting1.setPower(0);
            shooting2.setPower(0);
        }

        // collecting/scooping
        if(gamepad2.x) {
            collect1.setPower(1);
        } else if(gamepad2.b) {
            collect1.setPower(-1);
        }

        // beacon pushing
        if(gamepad2.a) {
            beacon.setPosition(0);
        } else {
            beacon.setPosition(1);
        }

        // let's add turning for the trigger buttons
        turnright(gamepad1.right_trigger);
        turnleft(gamepad1.left_trigger);

        telemetry.update();

    }

    public void turnright(double power) {
        frontleft.setPower(power);
        frontright.setPower(-power);
        backleft.setPower(-power);
        backright.setPower(power);
    }

    public void turnleft(double power) {

        frontright.setPower(power);
        frontleft.setPower(power);
        backright.setPower(power);
        backleft.setPower(power);
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }

}

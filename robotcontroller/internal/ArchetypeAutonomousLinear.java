package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by jdt2 on 11/14/2016.
 */

@Autonomous(name = "Linear", group = "Autonomous")

public class ArchetypeAutonomousLinear extends LinearOpMode {

    // wheel motors
    private DcMotor frontleft = null;
    private DcMotor frontright = null;
    private DcMotor backleft = null;
    private DcMotor backright = null;

    private ColorSensor colorSensor;
    private IrSeekerSensor irSeeker;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Begin Initialization");

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        // set up motors
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");

        // set motor directions
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.setDirection(DcMotorSimple.Direction.FORWARD);

        // set up sensors
        colorSensor = hardwareMap.colorSensor.get("color sensor");
        irSeeker = hardwareMap.irSeekerSensor.get("irSeeker");

        telemetry.addData("Status", "Finished Initialization");

        waitForStart();

        telemetry.addData("Status", "Begin Program");

        // frontleft forward: negative, backward: positive
        // backright forward: positive, backward: negative
        // frontright right: positive, left: negative
        // backleft right: negative, left: positive

        // currently moves forward for 5 seconds, then turns right for 10 seconds (spins).
        frontleft.setPower(-1);
        backright.setPower(1);
        sleep(5000);

        frontleft.setPower(0);
        backright.setPower(0);
        sleep(1000);

        // spin right
        frontleft.setPower(-1);
        backright.setPower(1);
        frontright.setPower(1);
        backleft.setPower(-1);
        sleep(10000);

//        IrSeekerSensor irSeeker;    // Hardware Device Object
//
//        // get a reference to our GyroSensor object.
//        irSeeker = hardwareMap.irSeekerSensor.get("seeker");
//
//        // wait for the start button to be pressed.
//        waitForStart();
//
//        while (opModeIsActive())  {
//
//            // Ensure we have a IR signal
//            if (irSeeker.signalDetected())
//            {
//                // Display angle and strength
//                telemetry.addData("Angle",    irSeeker.getAngle());
//                telemetry.addData("Strength", irSeeker.getStrength());
//            }
//
//            else
//            {
//                // Display loss of signal
//                telemetry.addData("Seeker", "Signal Lost");
//            }
//
//            telemetry.update();
//            idle(); // Always call idle() at the bottom of your while(opModeIsActive()) loop
//        }

        telemetry.addData("Status", "Finished with running");

    }

    public void forward(int power) {

    }

}

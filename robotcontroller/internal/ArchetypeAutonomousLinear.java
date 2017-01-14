package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

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
    //private IrSeekerSensor irSeeker;

    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV    = 1440 ;    // eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION    = 2.0 ;     // This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED             = 0.6;
    static final double     TURN_SPEED              = 0.5;

    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Status", "Begin Initialization");
        telemetry.update();

        /* eg: Initialize the hardware variables. Note that the strings used here as parameters
         * to 'get' must correspond to the names assigned during the robot configuration
         * step (using the FTC Robot Controller app on the phone).
         */

        // set up motors
        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");
        backleft = hardwareMap.dcMotor.get("backleft");
        backright = hardwareMap.dcMotor.get("backright");

        frontleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backleft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backright.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        idle();

        frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // set motor directions
        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);
        backleft.setDirection(DcMotorSimple.Direction.REVERSE);
        backright.setDirection(DcMotorSimple.Direction.FORWARD);

        // set up sensors
        //colorSensor = hardwareMap.colorSensor.get("color sensor");
        //irSeeker = hardwareMap.irSeekerSensor.get("irSeeker");

        telemetry.addData("Status", "Finished Initialization");
        telemetry.update();

        waitForStart();

        telemetry.addData("Status", "Begin Program");
        telemetry.update();

<<<<<<< HEAD
        // frontright forward: negative, backward: positive
        // backleft forward: positive, backward: negative
        // frontleft right: positive, left: negative
        // backright right: negative, left: positive
=======
        // frontleft forward: positive, backward: negative
        // backright forward: positive, backward: negative
        // frontright right: positive, left: negative
        // backleft right: negative, left: positive
>>>>>>> 89629526564d47b1a858c33697cd413f086a8429

        // currently moves forward for 5 seconds, then turns right for 10 seconds (spins).
//        frontright.setPower(1);
//        backleft.setPower(1);
//        sleep(3000);
//
//        frontright.setPower(0);
//        backleft.setPower(0);
//        sleep(1000);

        // move toward beacon
        encoderDrive(TURN_SPEED, 12, -12, 2.0);
        encoderDrive(DRIVE_SPEED, 48, 48, 5.0);

        // push beacon


        telemetry.addData("Status", "Finished with running");
        telemetry.update();

    }

    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newFrontLeftTarget;
        int newFrontRightTarget;
        int newBackLeftTarget;
        int newBackRightTarget;

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newFrontRightTarget = frontright.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newFrontLeftTarget = frontleft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            newBackRightTarget = backright.getCurrentPosition() + (int)(leftInches * COUNTS_PER_INCH);
            newBackLeftTarget = backleft.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            frontright.setTargetPosition(newFrontRightTarget);
            frontleft.setTargetPosition(newFrontLeftTarget);
            backright.setTargetPosition(newBackRightTarget);
            backleft.setTargetPosition(newBackLeftTarget);

            // Turn On RUN_TO_POSITION
            frontright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            frontleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backright.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            backleft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            frontleft.setPower(Math.abs(speed));
            frontright.setPower(Math.abs(speed));
            backright.setPower(Math.abs(speed));
            backleft.setPower(Math.abs(speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (frontright.isBusy() || frontleft.isBusy() || backleft.isBusy() || backright.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Path1",  "Running to %7d :%7d", newFrontLeftTarget,  newFrontRightTarget);
                telemetry.addData("Path2",  "Running at %7d :%7d",
                        frontright.getCurrentPosition(),
                        backleft.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            frontright.setPower(0);
            frontleft.setPower(0);
            backright.setPower(0);
            backleft.setPower(0);

            // Turn off RUN_TO_POSITION
            frontleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backleft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backright.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            //  sleep(250);   // optional pause after each move
        }
    }

}

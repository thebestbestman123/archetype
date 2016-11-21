package org.firstinspires.ftc.robotcontroller.internal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.IrSeekerSensor;

/**
 * Created by jdt2 on 11/14/2016.
 */

@Autonomous(name = "Linear", group = "Autonomous")

public class ArchetypeAutonomousLinear extends LinearOpMode {

    // wheel motors
    private DcMotor frontleft = null;
    private DcMotor frontright = null;


    @Override
    public void runOpMode() throws InterruptedException {

        frontleft = hardwareMap.dcMotor.get("frontleft");
        frontright = hardwareMap.dcMotor.get("frontright");

        waitForStart();

        frontleft.setDirection(DcMotorSimple.Direction.REVERSE);
        frontright.setDirection(DcMotorSimple.Direction.FORWARD);

        frontleft.setPower(1);
        frontright.setPower(1);

        sleep(1000);

        frontleft.setPower(0);
        frontright.setPower(0);

        sleep(1000);

        frontleft.setPower(-1);
        frontright.setPower(-1);

        sleep(5000);

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

    }
}

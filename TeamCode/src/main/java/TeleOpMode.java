package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.*;


@TeleOp(name = "Basic: TeleOpMode", group = "Linear Opmode")
////THIS BELONGS TO 9853! :)
public class TeleOpMode extends LinearOpMode
{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftDrive, rightDrive, collection, drawerSlide, leftArm, rightArm, hangClaw;

    @Override
    public void runOpMode()
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration

        //Chassis Motors
        leftDrive = hardwareMap.get(DcMotor.class, "LeftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "RightDrive");
        drawerSlide = hardwareMap.get(DcMotor.class, "DrawerSlide");
        collection = hardwareMap.get(DcMotor.class, "Collection");
        leftArm = hardwareMap.get(DcMotor.class, "LeftArm");
        rightArm = hardwareMap.get(DcMotor.class, "RightArm");
        hangClaw = hardwareMap.get(DcMotor.class, "HangClaw");

        // Right motors are reversed because it is oriented differently then the Left side
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        collection.setDirection(DcMotor.Direction.REVERSE);
        drawerSlide.setDirection(DcMotor.Direction.REVERSE);
        leftArm.setDirection(DcMotor.Direction.REVERSE);
        rightArm.setDirection(DcMotor.Direction.FORWARD);
        //hangClaw.setDirection(DcMotor.Direction.);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        telemetry.addData("Status", "before While Loop");
        telemetry.update();
        while (opModeIsActive())
        {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double pinionPower;
            double collectionPower;
            double drive;
            double turn;
            double armPower;
            double clawPower;

            // - This uses basic math to combine motions and is easier to drive straight.
            drive = - gamepad1.left_stick_y;
            turn = gamepad1.right_stick_x;
            rightPower = Range.clip(-(drive + turn), -1.0, 1.0) ;

            leftPower = Range.clip(-(drive - turn), -1.0, 1.0 );



            // the dpad_up button on controller 2 will run the pinion to pull up the arm
            // the dpad_down button on controller 2 will run the pinion the other way and let the arm down
            if (gamepad2.dpad_up == true)
            {
                //UP
                pinionPower =   0.45;
            }
            else if (gamepad2.dpad_down == true)
            {
                //DOWN
                pinionPower = -0.45;
            }
            else
            {
                pinionPower=0;
            }

            if (gamepad2.right_trigger > 0)
            {
                // run the collector at 45% power
                collectionPower = 0.45;
            }
            else if (gamepad2.right_bumper == true)
            {
                // set collector power to 45% but invert so it is in reverse
                collectionPower = -0.45;
            }
            else
            {
                // if neither button was pressed, then turn it off.
                collectionPower = 0.0;
            }

            //ARM POWER
            if (gamepad1.right_trigger>0)
            {
                //if right trigger is pressed then set power to 45%
                //arm moves down
                armPower = 0.45;
            }
            else if  (gamepad1.right_bumper==true)
            {
                //reverse arm power
                //arm moves up
                armPower = -0.45;
            }
            else // if neither is being presed
            {
                armPower=0;
            }

            // Send calculated power to motors
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            collection.setPower(collectionPower);
            drawerSlide.setPower(pinionPower);
            leftArm.setPower(armPower);
            rightArm.setPower(armPower);

            /* Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.addData("Arm", "left (%.2f), right(%.2f)", armPower);
            telemetry.addData("Collector", "speed (%.2f)",collectionPower);
            telemetry.update();
            */
        }
    }
}

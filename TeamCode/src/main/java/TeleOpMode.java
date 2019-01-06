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
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        drawerSlide = hardwareMap.get(DcMotor.class, "drawerSlide");
        collection = hardwareMap.get(DcMotor.class, "collection");
        leftArm = hardwareMap.get(DcMotor.class, "leftArm");
        rightArm = hardwareMap.get(DcMotor.class, "rightArm");
        hangClaw = hardwareMap.get(DcMotor.class, "hangClaw");

        // Right motors are reversed because it is oriented differently then the Left side
        leftDrive.setDirection(DcMotor.Direction.FORWARD);
        rightDrive.setDirection(DcMotor.Direction.REVERSE);
        collection.setDirection(DcMotor.Direction.REVERSE);
        drawerSlide.setDirection(DcMotor.Direction.FORWARD);
        leftArm.setDirection(DcMotor.Direction.REVERSE);
        rightArm.setDirection(DcMotor.Direction.FORWARD);
        hangClaw.setDirection(DcMotor.Direction.FORWARD);

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
            turn = -gamepad1.right_stick_x;

            rightPower = Range.clip(-(drive + turn), -1.0, 1.0) ;
            leftPower = Range.clip(-(drive - turn), -1.0, 1.0 );


            if (gamepad2.dpad_up == true)
            {
                //UP
                pinionPower =   0.45;
            }
            else if (gamepad2.dpad_down == true)
            {
                pinionPower = -0.45;
            }
            else
            {
                pinionPower=0;
            }

            if (gamepad2.right_bumper == true)
            {
                // run the collector at 45% power
                collectionPower = 0.45;
            }
            else if (gamepad2.left_bumper == true)
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
            if (gamepad2.right_trigger > 0)
            {
                armPower = 0.45;
            }
            else if  (gamepad2.left_trigger > 0)
            {
                armPower = -0.45;
            }
            else
            {
                armPower=0;
            }

            //CLAMP
            if (gamepad1.right_trigger > 0)
            {
                clawPower = 0.45;
            }
            else if  (gamepad1.left_trigger > 0)
            {
                clawPower = -0.45;
            }
            else
            {
                clawPower=0;
            }

            // Send calculated power to motors
            leftDrive.setPower(leftPower);
            rightDrive.setPower(rightPower);
            collection.setPower(collectionPower);
            drawerSlide.setPower(pinionPower);
            leftArm.setPower(armPower);
            rightArm.setPower(armPower);
            hangClaw.setPower(clawPower);

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
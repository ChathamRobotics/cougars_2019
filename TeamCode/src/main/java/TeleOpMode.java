package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;
import java.lang.*;


@TeleOp(name = "Basic: TeleOpMode", group = "Linear Opmode")
//THIS BELONGS TO 9853
class TeleOpMode extends LinearOpMode{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor frontLeftDrive, frontRightDrive, backLeftDrive, backRightDrive;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration

        //Chassis Motors
        frontLeftDrive  = hardwareMap.get(DcMotor.class, "frontLeftDrive");
        backLeftDrive = hardwareMap. get(DcMotor.class, "backLeftDrive");
        backRightDrive = hardwareMap.get(DcMotor.class, "backRightDrive");
        frontRightDrive =  hardwareMap.get(DcMotor.class, "frontRightDrive");
        /*(Hub 1)
            frontLeftDrive= Port 0
            backLeftDrive= Port 1
            backRightDrive= Port 2
            frontRightDrive= Port 3
         */

        // Right motors are reversed because it is oriented differently then the Left side
        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        telemetry.addData("Status", "before While Loop");
        telemetry.update();
        while (opModeIsActive()) {

            // Setup a variable for each drive wheel to save power level for telemetry
            double leftPower;
            double rightPower;
            double pulleyPower;
            double armPower;
            double collectionPower;
            double drive;
            double turn;


            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            drive = -gamepad1.left_stick_y;
            turn  =  gamepad1.right_stick_x;
            leftPower   = Range.clip(drive + turn, -1.0, 1.0) ;
            rightPower   = Range.clip(drive - turn, -1.0, 1.0) ;


            // Tank Mode uses one stick to control each wheel.
            // - This requires no math, but it is hard to drive forward slowly and keep straight.
            //leftPower  = -gamepad1.left_stick_y ;
            //rightPower = -gamepad1.right_stick_y ;

            // Send calculated power to wheels
            frontLeftDrive.setPower(leftPower);
            backLeftDrive.setPower(leftPower);
            backRightDrive.setPower(rightPower);
            frontRightDrive.setPower(rightPower);

            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftPower, rightPower);
            telemetry.update();

            // read the gamepad dpad actuator to see if we need to move the arm
            // the dpad_up button will run the pulley to reel in and pull up the arm
            // the dpad_down button will run the pulley the other way and reel out to let the arm down
            if (gamepad2.dpad_up == true) {
                pulleyPower = 0.45;
            } else if (gamepad2.dpad_down == true) {
                pulleyPower = -0.45;
            } else {
                pulleyPower = 0.0;
            }

            // read the right_trigger button to see if is pressed more than half way.
            // if it is not pressed more than half way, then look at the right_bumper button.
            // if the bumper button is pressed, then reverse the collector
            // if neither one is pressed, then stop.
            // the trigger is a float, so we need to read it and compare to a threshold of 50%.

            if (gamepad2.right_trigger > 0.50) {   // if the trigger is pressed more than halfway down, call it "on"
                // and run the collector at 45% power
                collectionPower = 0.45;

            } else if (gamepad2.right_bumper == true) {  // if the trigger was not pressed or pressed only a little, ignore it.
                // instead, read the bumper button.

                collectionPower = -0.45;

            } else { // if neither button was pressed, then turn it off.

                collectionPower = 0.0;
            }

            //read the gamepad buttons for the arm to see if we need to move the arm
            // if the left bumper button is pressed we go up
            // if the left trigger button is pressed we go down.
            if (gamepad2.left_trigger > 0.50) {

                armPower = 0.45;

            } else if (gamepad2.left_bumper == true){

                armPower = -0.45;

            }else {

                armPower = 0.0;
            }




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
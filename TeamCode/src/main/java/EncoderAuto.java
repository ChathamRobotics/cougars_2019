
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
// :)
@Autonomous (name = "EncoderAuto", group = "Linear OpMode")
abstract public class EncoderAuto extends LinearOpMode {

    Hardware9853 robot = new Hardware9853();
    private ElapsedTime runtime = new ElapsedTime();

    static final double     COUNTS_PER_MOTOR_REV = 1120 ;// eg: TETRIX Motor Encoder
    static final double     DRIVE_GEAR_REDUCTION = 2.0 ;// This is < 1.0 if geared UP
    static final double     WHEEL_DIAMETER_INCHES = 4.0 ;// For figuring circumference
    static final double     COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    static final double     DRIVE_SPEED = 0.5;
    static final double     TURN_SPEED = 0.5;

    @Override
    public void runOpMode()throws InterruptedException {

        robot.init(hardwareMap);

        telemetry.addData("Status","Initialized");
        telemetry.update();

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData("Inital Path", "Starting at :%7d :%7d");
        robot.leftDrive.getCurrentPosition();
        robot.rightDrive.getCurrentPosition();
        telemetry.update();
        waitForStart();

        encoderDrive(DRIVE_SPEED,  48,  48, 5.0);  // S1: Forward 47 Inches with 5 Sec timeout
        encoderDrive(TURN_SPEED,   12, -12, 4.0);  // S2: Turn Right 12 Inches with 4 Sec timeout


        telemetry.addData("Status", "Complete");
        telemetry.update();


    }

    public void encoderDrive (  double leftInches, double rightInches, double speed, double timeoutS)
    {
        int newLeftTarget;
        int newRightTarget;

        if (opModeIsActive()){
            newLeftTarget = robot.leftDrive.getCurrentPosition()+ (int)(leftInches * COUNTS_PER_INCH);
            newRightTarget = robot.rightDrive.getCurrentPosition() + (int)(rightInches * COUNTS_PER_INCH);
            robot.leftDrive.setTargetPosition(newLeftTarget);
            robot.rightDrive.setTargetPosition(newRightTarget);

            robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            runtime.reset();
            robot.leftDrive.setPower(Math.abs(speed));
            robot.rightDrive.setPower(Math.abs(speed));

            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (robot.rightDrive.isBusy() && robot.leftDrive.isBusy())){
                //  telemetry.addData("Path1", "Running to 7%d : 7%d", newLeftTarget, newRightTarget);
                telemetry.addData("Path2","Running at 7%d : 7%d");
                robot.leftDrive.getCurrentPosition();

                robot.rightDrive.getCurrentPosition();
                telemetry.update();

            }
            // Stop all motion;
            robot.leftDrive.setPower(0);
            robot.rightDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            sleep(250);   // optional pause after each move


        }


    }
}

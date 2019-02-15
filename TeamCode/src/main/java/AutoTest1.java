import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoTest1", group = "Autonomous")
public class AutoTest1 extends LinearOpMode
{
    Hardware9853 robot = new Hardware9853();
    private ElapsedTime runtime = new ElapsedTime();
    static final double Speed = .25;

    @Override
    public void runOpMode()throws InterruptedException
    {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        robot.init(hardwareMap);

        waitForStart();
        runtime.reset();

        while (opModeIsActive() && (runtime.seconds() < 2.5 ))
        {
            robot.leftDrive.setPower(Speed); //Left is forward --> positive
            robot.rightDrive.setPower(-Speed); //Right is reverse --> negative
        }
        //for the next half a second the robot will turn to the right (same speed)
        while (opModeIsActive() && (runtime.seconds() > 2.5 )&& (runtime.seconds()<3))
        {
            robot.leftDrive.setPower(Speed);
            robot.rightDrive.setPower(Speed);
        }

        while (opModeIsActive() && (runtime.seconds()>3) && (runtime.seconds()<4.5))
        {
            robot.leftDrive.setPower(-Speed); //Left is forward --> positive
            robot.rightDrive.setPower(-Speed); //Right is reverse --> negative
        }

        while (opModeIsActive() && (runtime.seconds() > 4.5 )&& (runtime.seconds()<7))
        {
            robot.leftDrive.setPower(Speed);
            robot.rightDrive.setPower(Speed);
        }

        while (opModeIsActive() && (runtime.seconds()>7) && (runtime.seconds()<9))
        {
            robot.collection.setPower(Speed);
        }

        while (opModeIsActive() && (runtime.seconds()>10) && (runtime.seconds()<14))
        {
            robot.leftDrive.setPower(-Speed); //Left is forward --> positive
            robot.rightDrive.setPower(Speed); //Right is reverse --> negative
        }
    }
}

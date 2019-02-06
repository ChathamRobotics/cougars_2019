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

        while (opModeIsActive() && (runtime.seconds() < 2 ))
        {
            robot.leftDrive.setPower(Speed); //Left is forward --> positive
            robot.rightDrive.setPower(-Speed); //Right is reverse --> negative
        }


    }
}

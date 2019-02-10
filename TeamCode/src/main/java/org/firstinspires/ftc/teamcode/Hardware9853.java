package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
// ;)
public class Hardware9853  {

    public DcMotor  leftMotor  =  null;
    public DcMotor  rightMotor =  null;
    public DcMotor  leftArmMotor   =  null;
    public DcMotor  rightArmMotor  =  null;
    public DcMotor  collectionMotor =  null;
    public DcMotor  clampMotor      =  null;
    public DcMotor  liftMotor       =  null;

    public static final double DRIVE_FOWARD_LEFT_POWER = .5;
    public static final double DRIVE_FOWARD_RIGHT_POWER = .5;
    public static final double LEFT_ARM_MOTOR_POWER = .5;
    public static final double COLLECTION_MOTOR_POWER = .5;
    public static final double LIFT_MOTOR_POWER = .5;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public Hardware9853 (){

    }
    public void init (HardwareMap ahwMap){
        hwMap = ahwMap;

        leftMotor       = hwMap.get(DcMotor.class, "leftMotor");
        rightMotor      = hwMap.get(DcMotor.class,"rightMotor");
        leftArmMotor    = hwMap.get(DcMotor.class, "leftArmMotor");
        rightArmMotor   = hwMap.get(DcMotor.class,"rightArmMotor");
        collectionMotor = hwMap.get(DcMotor.class,"collectionMotor");
        clampMotor      = hwMap.get(DcMotor.class,"clampMotor");
        liftMotor       = hwMap.get(DcMotor.class,"liftMotor");

        leftMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightMotor.setDirection(DcMotorSimple.Direction.FORWARD);

        leftMotor.setPower(0);
        rightMotor.setPower(0);
        leftArmMotor.setPower(0);
        rightArmMotor.setPower(0);
        collectionMotor.setPower(0);
        clampMotor.setPower(0);
        liftMotor.setPower(0);

        leftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightArmMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        collectionMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        clampMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}

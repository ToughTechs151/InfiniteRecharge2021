package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.LimeLightSubsystem;

public class DriveSoloCommand extends CommandBase{
    private DriveSubsystem drive;
    private LimeLightSubsystem lime;
    private double left,right,z;
    private boolean fin=false;
    private double leftAdjust,rightAdjust;
    public DriveSoloCommand(DriveSubsystem drive,LimeLightSubsystem lime,double x,double y,double z){
        this.drive=drive;
        this.lime=lime;
        left=x;
        right=y;
        this.z=z;
        addRequirements(drive);
    }
    public void execute(){
        float Kp=-0.075f;
        leftAdjust=left;
        rightAdjust=right;
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //checks for input from drive to allign to target.
        if (NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0)==1){
          double heading_error = tx;
          double steering_adjust = Kp * tx;

          leftAdjust+=steering_adjust;
          rightAdjust-=steering_adjust;
        }
        drive.drive(leftAdjust,rightAdjust);
        if(lime.returnD()>=z-50&&lime.returnD()<=z+50){
            fin=true;
        }
    }


}
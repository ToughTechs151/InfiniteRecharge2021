/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.controller.PIDController;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * 
 */
public class LauncherSubsystem extends PIDSubsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  private static PIDController pid;
  private static CANSparkMax launcher1 = new CANSparkMax(Constants.Launcher1,MotorType.kBrushless);
  private static CANSparkMax launcher2 = new CANSparkMax(Constants.Launcher2,MotorType.kBrushless);
  private static CANEncoder lEncoder = new CANEncoder(launcher1);
  //private static Talon launcher1 = new Talon(Constants.Launcher1);
  //private static Talon launcher2 = new Talon(Constants.Launcher2);
  private static double setpoint = 0;
  private static SpeedControllerGroup launcher = new SpeedControllerGroup(launcher1, launcher2);
  public LauncherSubsystem(PIDController inPID) {
    super(inPID);
    launcher.setInverted(false);
  }
  public void setSetpoint(double set){
    setpoint=set;
    if(set!=0){
      try {
        getController().setSetpoint(setpoint);
      } 
      catch (NullPointerException Exception) {
        System.out.println("Exception: " + Exception);
        //TODO: handle exception
      }
    }
  }

  public void periodic() {
    //setSetpoint(RobotContainer.coDriverOI.getY()); NEVER EVER DO THIS
    useOutput(lEncoder.getVelocity(), setpoint);
    SmartDashboard.putNumber("LauncherSpeed in RPM", lEncoder.getVelocity());
    SmartDashboard.putNumber("Launcher Current", launcher1.getOutputCurrent());
    //setSetpoint(SmartDashboard.getNumber("LauncherSetpoint in RPM", setpoint));
    SmartDashboard.putNumber("LauncherSetpoint in RPM", setpoint);
    /*getController().setP(SmartDashboard.getNumber("Kp", getController().getP()));
    getController().setI(SmartDashboard.getNumber("Ki", getController().getI()));
    getController().setD(SmartDashboard.getNumber("Kd", getController().getD()));
    SmartDashboard.putNumber("Kp", getController().getP());
    SmartDashboard.putNumber("Ki", getController().getI());
    SmartDashboard.putNumber("Kd", getController().getD());*/
    SmartDashboard.putNumber("Launcher get",launcher1.get());
  }

  /*protected static void run(double output) {
    if (setpoint==0){
      output=setpoint;
    }
    launcher.set(output);

  }*/
@Override
protected void useOutput(double output, double setpoint) {
  if(setpoint == 0){
    setpoint=(getController().getSetpoint()*0.9);
  }
  output=getController().calculate(output,setpoint)/lEncoder.getVelocityConversionFactor();
  launcher1.set(output*0.95);
  launcher2.set(output);
	
}
@Override
protected double getMeasurement() {
	return launcher.get();
}
public static void setSpeed(double s){
  launcher.set(s/lEncoder.getVelocityConversionFactor());

}
}

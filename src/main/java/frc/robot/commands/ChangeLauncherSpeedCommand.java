
package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.LauncherSubsystem;

public class ChangeLauncherSpeedCommand extends CommandBase{
    private boolean fin=false;
    private double setspeed;
    private LauncherSubsystem mLauncher;
    //private int n=200;
    public ChangeLauncherSpeedCommand(double speed, LauncherSubsystem launcher) {
        setspeed=speed;
        mLauncher=launcher;
        addRequirements(mLauncher);
    }
    public void initialize(){

    }
    public void changeSpeed(double speed){
        setspeed = speed;
    }
    public void execute(){
       //*/
        mLauncher.setSetpoint(setspeed);
        /*mLauncher.setSpeed(setspeed/n);
        n++;
        if(n==0)
        fin=true;
        //*/
        if(!RobotContainer.coDriverOI.getRawButton(Constants.LEFT_BUMPER)){
            fin=true;
        }
    }
    @Override
    public boolean isFinished() {
        return fin;
    }
    @Override
    public void end(boolean interrupted) {
        if(RobotContainer.coDriverOI.getRawButtonReleased(Constants.LEFT_BUMPER)){
            mLauncher.setSetpoint(0);
        }
    }
}
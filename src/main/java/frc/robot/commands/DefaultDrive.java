package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class DefaultDrive extends CommandBase{
    private DriveSubsystem drive;
    public DefaultDrive(DriveSubsystem drive){
        this.drive=drive;
        addRequirements(drive);
    }
    @Override
    public void execute() {
        
        drive.drive(0, 0);
    }
    @Override
    public boolean isFinished() {
        return false;
    }
    public void execute(boolean interrupted){

    }
}
package frc.robot.subsystems;

//import drivetrain motors
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

//differential drive
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

//import settings
import frc.robot.Constants.BlinkyDrivetrainConstants;;

//create class with autoclosable (do this when closed)
public class BlinkyDrivetrain implements AutoCloseable{
    
    private final WPI_VictorSPX m_leftDrive = new WPI_VictorSPX(5);
    private final WPI_TalonSRX m_leftDrive2 = new WPI_TalonSRX(2);
    private final WPI_VictorSPX m_rightDrive = new WPI_VictorSPX(3);
    private final WPI_TalonSRX m_rightDrive2 = new WPI_TalonSRX(4);
    private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);

    public double DrivePercentage = 0.8;
    public double RotatePercentage = 0.8;

    public void Init(){
        //set settings
        DrivePercentage = BlinkyDrivetrainConstants.DrivePercentage;
        RotatePercentage = BlinkyDrivetrainConstants.RotatePercentage;

        // Set motor direction
        m_leftDrive.setInverted(true); 
        m_leftDrive2.setInverted(true);
        m_rightDrive.setInverted(true);
        m_rightDrive2.setInverted(true);

        // set breaking
        m_rightDrive.setNeutralMode(NeutralMode.Brake);
        m_rightDrive2.setNeutralMode(NeutralMode.Brake);
        m_leftDrive.setNeutralMode(NeutralMode.Brake);
        m_leftDrive2.setNeutralMode(NeutralMode.Brake);

        // create drivetrain
        m_leftDrive2.follow(m_leftDrive);
        m_rightDrive2.follow(m_rightDrive);
    }

    //Xspeed is in forward direction
    public void ArcadeDrive(double zRotation, double xSpeed){
        m_robotDrive.arcadeDrive(zRotation * RotatePercentage, xSpeed * DrivePercentage);
    }

    //when closed set speed to 0
    @Override
    public void close() throws Exception {
        //set speed to 0
        m_robotDrive.arcadeDrive(0,0);

        //free motors for pushing robot manually
        m_rightDrive.setNeutralMode(NeutralMode.Coast);
        m_rightDrive2.setNeutralMode(NeutralMode.Coast);
        m_leftDrive.setNeutralMode(NeutralMode.Coast);
        m_leftDrive2.setNeutralMode(NeutralMode.Coast);
    }
}

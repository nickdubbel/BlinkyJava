// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//motors
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();
 

  //drivetrain
  private final WPI_VictorSPX m_leftDrive = new WPI_VictorSPX(5);
  private final WPI_TalonSRX m_leftDrive2 = new WPI_TalonSRX(2);
  private final WPI_VictorSPX m_rightDrive = new WPI_VictorSPX(3);
  private final WPI_TalonSRX m_rightDrive2 = new WPI_TalonSRX(4);
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive, m_rightDrive);


  //arms
  private final PWMSparkMax m_leftIntake = new PWMSparkMax(1);
  private final PWMSparkMax m_rightIntake = new PWMSparkMax(2);
  private final PWMSparkMax m_Storage = new PWMSparkMax(0);

  //controller
  private final XboxController m_controller = new XboxController(0);


  //settings
  double DrivePercentage = 0.8;
  double RotatePercentage = 0.8;





  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    // Set motor direction
    m_leftDrive.setInverted(true); 
    m_leftDrive2.setInverted(true);
    m_rightDrive.setInverted(true);
    m_rightDrive2.setInverted(true);

    m_leftIntake.setInverted(true);
    m_rightIntake.setInverted(false);
    m_Storage.setInverted(false);

    // set breaking
    m_rightDrive.setNeutralMode(NeutralMode.Brake);
    m_rightDrive2.setNeutralMode(NeutralMode.Brake);
    m_leftDrive.setNeutralMode(NeutralMode.Brake);
    m_leftDrive2.setNeutralMode(NeutralMode.Brake);

    // create drivetrain
    m_leftDrive2.follow(m_leftDrive);
    m_rightDrive2.follow(m_rightDrive);

  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    }

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    m_robotDrive.arcadeDrive(m_controller.getLeftX() * RotatePercentage, m_controller.getLeftY() * DrivePercentage);
    m_leftIntake.set(m_controller.getRawAxis(5));
    m_rightIntake.set(m_controller.getRawAxis(5));
    m_Storage.set(m_controller.getRawAxis(5));  
  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {
    m_rightDrive.setNeutralMode(NeutralMode.Coast);
    m_rightDrive2.setNeutralMode(NeutralMode.Coast);
    m_leftDrive.setNeutralMode(NeutralMode.Coast);
    m_leftDrive2.setNeutralMode(NeutralMode.Coast);
  }

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}

package frc.robot.subsystems;

//motors
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;

//import sttings
import frc.robot.Constants.BlinkyIntakeConstants;

public class BlinkyIntake implements AutoCloseable {

    //arms
    private final PWMSparkMax m_leftIntake = new PWMSparkMax(BlinkyIntakeConstants.m_leftIntake);
    private final PWMSparkMax m_rightIntake = new PWMSparkMax(BlinkyIntakeConstants.m_rightIntake);
    private final PWMSparkMax m_Storage = new PWMSparkMax(BlinkyIntakeConstants.m_Storage);



    public void Init(){
        m_leftIntake.setInverted(true);
        m_rightIntake.setInverted(false);
        m_Storage.setInverted(false);
    }

    public void MoveBox(double speed){
        m_leftIntake.set(speed);
        m_rightIntake.set(speed);
        m_Storage.set(speed);  
    }

    @Override
    public void close() throws Exception {
        //set motors at 0 when stopping
        m_leftIntake.set(0);
        m_rightIntake.set(0);
        m_Storage.set(0); 
    }
}

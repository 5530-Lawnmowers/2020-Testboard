/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;
import frc.robot.Constants;
import frc.robot.commands.TurretMotorTest;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.helpers.ShuffleboardHelpers;

public class Turret extends SubsystemBase {
    private final WPI_TalonSRX turretSpin = new WPI_TalonSRX(Constants.TURRET);

    //Emergency stop the turret
    private final DigitalInput hardStop1 = new DigitalInput(Constants.TURRET_S1);
    private final DigitalInput hardStop2 = new DigitalInput(Constants.TURRET_S2);

    /**
     * Creates a new Turret.
     */
    public Turret() {
        turretSpin.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        turretSpin.setSelectedSensorPosition(0);
    }

    /**
     * For test purposes only
     *
     * @param speed set speed
     */
    public void testTurretSet(double speed) {
        if(speed > 0 && hardStop1.get()){
            turretSpin.set(0);
        }
        else if(speed > 0 && !hardStop1.get()){
            turretSpin.set(speed);
        }
        else if(speed < 0 && hardStop2.get()){
            turretSpin.set(0);
        }
        else if (speed < 0 && !hardStop2.get()){
            turretSpin.set(speed);
        }
        
    }

    /**
     * For test purposes only
     */
    public void testTurretStop() {
        turretSpin.stopMotor();
    }

    /**
     * For test purposes only
     *
     * @return {@type boolean}
     */
    public boolean testLimit() {
        return !hardStop1.get() || !hardStop2.get();
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        ShuffleboardHelpers.setWidgetValue("Sensors", "Turret Limit 1", !hardStop1.get());
        ShuffleboardHelpers.setWidgetValue("Sensors", "Turret Limit 2", !hardStop2.get());
        ShuffleboardHelpers.setWidgetValue("Encoders", "Turret", turretSpin.getSelectedSensorPosition());
    }
}

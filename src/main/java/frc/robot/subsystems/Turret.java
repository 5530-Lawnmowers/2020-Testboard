/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
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

    private final int ABSOLUTE_ZERO = 189;
    private int ZERO;
    /**
     * Creates a new Turret.
     */
    public Turret() {
        turretSpin.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 10);
        if (turretSpin.getSelectedSensorPosition() % 4096 > ABSOLUTE_ZERO + 2560) {
            ZERO = (((turretSpin.getSelectedSensorPosition() / 4096) + 1) * 4096) + ABSOLUTE_ZERO;
        } else {
            ZERO = ((turretSpin.getSelectedSensorPosition() / 4096) * 4096) + ABSOLUTE_ZERO;
        }
        //turretSpin.setSelectedSensorPosition(0);
        turretSpin.config_kF(0, .05, 10);
        turretSpin.config_kP(0, .01, 10);
        turretSpin.config_kI(0, .007, 10);
        turretSpin.config_kD(0, .007, 10);
    }

    /**
     * For test purposes only
     *
     * @param speed set speed
     */
    public void testTurretSet(double speed) {
         
        //TODO: Confirm that the hardstops correspond correctly to motor direction  
        
    }

    public void setPosition(int TargetPosition){
        turretSpin.set(ControlMode.Position, TargetPosition);
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

    /**
     * Get the current encoder angle of the turret
     *
     * @return The current angle
     */
    public int getEncoderVal() {
        return turretSpin.getSelectedSensorPosition();
    }

    /**
     * Get the front facing encoder value
     *
     * @return The front facing encoder value
     */
    public int getZero() {
        return ZERO;
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
        ShuffleboardHelpers.setWidgetValue("Sensors", "Turret Limit 1", !hardStop1.get());
        ShuffleboardHelpers.setWidgetValue("Sensors", "Turret Limit 2", !hardStop2.get());
        ShuffleboardHelpers.setWidgetValue("Encoders", "Turret", turretSpin.getSelectedSensorPosition());
    }
}

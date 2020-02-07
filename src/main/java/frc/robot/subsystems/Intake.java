/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.DigitalInput;

public class Intake extends SubsystemBase {
    //Intake has 2 motors: 2 NEO motors on the intake (Spark)
    private final CANSparkMax intake = new CANSparkMax(Constants.INTAKE, CANSparkMaxLowLevel.MotorType.kBrushless);

    /**
     * Creates a new Intake.
     */
    public Intake() {

    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    public void testIntakeSet(double input) {
        intake.set(input);
    }

    public void testIntakeStop() {
        intake.stopMotor();
    }
}

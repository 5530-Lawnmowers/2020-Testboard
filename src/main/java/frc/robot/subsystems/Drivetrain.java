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

public class Drivetrain extends SubsystemBase {
  private final WPI_TalonFX drivetrainLeft1 = new WPI_TalonFX(Constants.DT_L1);
  private final WPI_TalonFX drivetrainLeft2 = new WPI_TalonFX(Constants.DT_L2);
  private final WPI_TalonFX drivetrainLeft3 = new WPI_TalonFX(Constants.DT_L3);
  private final WPI_TalonFX drivetrainRight1 = new WPI_TalonFX(Constants.DT_R1);
  private final WPI_TalonFX drivetrainRight2 = new WPI_TalonFX(Constants.DT_R2);
  private final WPI_TalonFX drivetrainRight3 = new WPI_TalonFX(Constants.DT_R3);

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

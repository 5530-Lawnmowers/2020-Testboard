/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import frc.robot.Constants;
import frc.robot.helpers.ShuffleboardHelpers;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  private final WPI_TalonFX drivetrainLeft1 = new WPI_TalonFX(Constants.DT_L1);
  private final WPI_TalonFX drivetrainLeft2 = new WPI_TalonFX(Constants.DT_L2);
  private final WPI_TalonFX drivetrainRight1 = new WPI_TalonFX(Constants.DT_R1);
  private final WPI_TalonFX drivetrainRight2 = new WPI_TalonFX(Constants.DT_R2);

  private final AHRS gyro = new AHRS(SerialPort.Port.kMXP);

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    gyro.zeroYaw();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ShuffleboardHelpers.setWidgetValue("Gyro", "Yaw", gyro.getYaw());
  }

}

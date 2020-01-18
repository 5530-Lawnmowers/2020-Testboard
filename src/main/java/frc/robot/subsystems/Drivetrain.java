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
import frc.robot.commands.ThrottleMotorTest;

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
    setDefaultCommand(new ThrottleMotorTest(this));
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ShuffleboardHelpers.setWidgetValue("Sensors", "Gyro Yaw", gyro.getYaw());
    ShuffleboardHelpers.setWidgetValue("Drivetrain", "Right1", drivetrainRight1.get());
    ShuffleboardHelpers.setWidgetValue("Drivetrain", "Right2", drivetrainRight2.get());
    ShuffleboardHelpers.setWidgetValue("Drivetrain", "Left1", drivetrainLeft1.get());
    ShuffleboardHelpers.setWidgetValue("Drivetrain", "Left2", drivetrainLeft2.get());
  }

  /**
   * For test purposes only
   * @param speed set speed
   * @param controller "L1", "L2", "R1", or "R2" only
   */
  public void testDrivetrainSet(double speed, String controller) {
    if (controller.equalsIgnoreCase("L1")) {
      drivetrainLeft1.set(speed);
    } else if (controller.equalsIgnoreCase("L2")) {
      drivetrainLeft2.set(speed);
    } else if (controller.equalsIgnoreCase("R1")) {
      drivetrainRight1.set(speed);
    } else if (controller.equalsIgnoreCase("R2")) {
      drivetrainRight2.set(speed);
    }
  }

  /**
   * Stops all drivetrain motors
   */
  public void testDrivetrainStop() {
    drivetrainLeft1.stopMotor();
    drivetrainLeft2.stopMotor();
    drivetrainRight1.stopMotor();
    drivetrainRight2.stopMotor();
  }

}

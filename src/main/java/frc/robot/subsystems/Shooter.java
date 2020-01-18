/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.helpers.ShuffleboardHelpers;

import com.revrobotics.*;


public class Shooter extends SubsystemBase {
  private final CANSparkMax shooter1 = new CANSparkMax(Constants.SHOOTER_1, CANSparkMaxLowLevel.MotorType.kBrushless);
  private final CANSparkMax shooter2 = new CANSparkMax(Constants.SHOOTER_2, CANSparkMaxLowLevel.MotorType.kBrushless);

  /**
   * Creates a new Shooter.
   */
  public Shooter() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    ShuffleboardHelpers.setWidgetValue("Test", "Shooter Velocity", shooter1.getEncoder().getVelocity());
  }

  public void testShooterSet(double speed) {
    shooter1.set(speed);
    shooter2.set(speed);
  }

  public void testShooterStop() {
    shooter1.stopMotor();
    shooter2.stopMotor();
  }
}

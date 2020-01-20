/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.helpers.ShuffleboardHelpers;
import frc.robot.commands.DefaultDriveTest;
import frc.robot.commands.ThrottleMotorTest;

import com.revrobotics.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.kauailabs.navx.frc.AHRS;

public class Drivetrain extends SubsystemBase {
  //Drive test
  private final double driveMultiplier = 0.9;

  private final WPI_TalonFX drivetrainLeft1 = new WPI_TalonFX(Constants.DT_L1);
  private final WPI_TalonFX drivetrainLeft2 = new WPI_TalonFX(Constants.DT_L2);
  private final WPI_TalonFX drivetrainRight1 = new WPI_TalonFX(Constants.DT_R1);
  private final WPI_TalonFX drivetrainRight2 = new WPI_TalonFX(Constants.DT_R2);

  //Drive test
  private final SpeedControllerGroup drivetrainLeft;
  private final SpeedControllerGroup drivetrainRight;
  private final DifferentialDrive diffDrive;

  private final AHRS gyro = new AHRS(SerialPort.Port.kMXP);

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    drivetrainLeft1.setInverted(true);
    drivetrainLeft2.setInverted(true);
    drivetrainRight1.setInverted(false);
    drivetrainRight2.setInverted(false);

    drivetrainLeft = new SpeedControllerGroup(drivetrainLeft1, drivetrainLeft2);
    drivetrainRight = new SpeedControllerGroup(drivetrainRight1, drivetrainRight2);
    diffDrive = new DifferentialDrive(drivetrainLeft, drivetrainRight);

    gyro.zeroYaw();
    //setDefaultCommand(new ThrottleMotorTest(this)); //Use this for motor tests
    setDefaultCommand(new DefaultDriveTest(this, () -> testGetThrottle(), () -> testGetTurn()));
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

  //Drive test only
  public void testDrive(double throttle, double turn) {
    if(Math.abs(throttle) > 1)
			throttle = Math.abs(throttle) / throttle; // if the value given was too high, set it to the max
		throttle *= driveMultiplier; // scale down the speed
		
		
		if(Math.abs(turn) > 1)
			turn = Math.abs(turn) / turn; // if the value given was too high, set it to the max
		turn *= driveMultiplier; // scale down the speed
		
		diffDrive.arcadeDrive(throttle, turn); // function provided by the  controls y and turn speed at the same time.
  }

  //Drive test only
  public void testDriveStop() {
    drivetrainLeft.stopMotor();
    drivetrainRight.stopMotor();
  }

  private double testGetThrottle() {
    double n = RobotContainer.XBController.getTriggerAxis(GenericHID.Hand.kRight) - 
               RobotContainer.XBController.getTriggerAxis(GenericHID.Hand.kLeft);
		return Math.abs(n) < 0.1 ? 0 : n;
  }

  private double testGetTurn() {
    double n = RobotContainer.XBController.getX(GenericHID.Hand.kLeft);
    return Math.abs(n) < 0.1 ? 0 : n;
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

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.RobotContainer;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class ThrottleMotorTest extends CommandBase {
  private Drivetrain drivetrain;
  public static double oldTrigger;
  private static double driveWeight = 0.85;

  /**
   * Creates a new ThrottleMotorTest.
   */
  public ThrottleMotorTest(Drivetrain drivetrain) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.drivetrain = drivetrain;
    addRequirements(drivetrain);
  }

  /**
	 * Limits a value between -1.0 and 1.0
	 */
	public double limit(double n) {
		return Math.min(Math.max(n, -1.0), 1.0);
  }
  
  /**
	 * Slowly increases oldTrigger to newTriggerVal
	 * 
	 * @return next iteration closer to newTriggerVal
	 */
	public double accelerate(double newTriggerVal) {
		oldTrigger = 0.09516 * newTriggerVal + 0.9048 * oldTrigger;
		return oldTrigger;
  }

	/**
	 * Get the trigger value for a trigger side on XBox Controller
	 */
	public double getTrigger(Hand side) {
		return RobotContainer.XBController.getTriggerAxis(side);
  }
  
  /**
	 * Calculates speed based on XBox Controller output
	 * 
	 * @return speed = accelerator - deccelerator - lateral input
	 */
	public double speed(double accelerator, double deccelerator) {
		return driveWeight * limit(accelerator - deccelerator);
  }
  
  /**
	 * Sets the speed for both sides
	 */
	public void setSpeeds(double rTrigger, double lTrigger) {
		// Thrust
		double power = (double) speed(rTrigger, lTrigger);
		// If the difference from the old throttle is greater than 0.2, slowly
		// accelerate to new value.
		if (Math.abs(oldTrigger - power) > 0.2)
			power = accelerate(power);
    // Set the left side to the next iteration closer to leftPower
    drivetrain.testDrivetrainSet(power, "R1");
    drivetrain.testDrivetrainSet(power, "R2");
    drivetrain.testDrivetrainSet(power, "L1");
    drivetrain.testDrivetrainSet(power, "L2");
	}
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driveWeight = 0.9;
		setSpeeds(getTrigger(Hand.kRight), getTrigger(Hand.kLeft));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

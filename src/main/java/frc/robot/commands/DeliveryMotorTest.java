/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.helpers.ShuffleboardHelpers;
import frc.robot.subsystems.Delivery;

public class DeliveryMotorTest extends CommandBase {
    private double speedSet;
    private Delivery delivery;

    /**
     * Creates a new DeliveryMotorTest.
     */
    public DeliveryMotorTest(Delivery delivery) {
        this.delivery = delivery;
        addRequirements(delivery);
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
        speedSet = (double) ShuffleboardHelpers.getWidgetValue("Test", "Delivery Set Speed");
        delivery.testDeliverySet(speedSet);
        ShuffleboardHelpers.setWidgetValue("Test", "Delivery Test Status", "Running");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        delivery.testDeliveryStop();
        ShuffleboardHelpers.setWidgetValue("Test", "Delivery Test Status", "Ended");
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}

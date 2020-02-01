/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.helpers.SQLHelper;

import java.sql.SQLException;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private Timer timer = new Timer();
    private RobotContainer m_robotContainer;

    /**
     * This function is run when the robot is first started up and should be used for any
     * initialization code.
     */
    @Override
    public void robotInit() {
        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        // For color sensor test only, creates shuffleboard tab and fields
        Shuffleboard.getTab("Sensors");
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Red", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Green", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Blue", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Match", "Unknown"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Confidence", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Gyro Yaw", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Turret Limit 1", false));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Turret Limit 2", false));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Delivery Breakbeam 1", false));
        SQLHelper.stageWidget(Shuffleboard.getTab("Sensors").add("Delivery Breakbeam 2", false));
        Shuffleboard.getTab("Test");
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Intake Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Climber Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Drivetrain Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Shooter Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Spinner Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Delivery Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Turret Test Status", "Start"));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Delivery Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Shooter Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Spinner Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Intake Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Climber Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Turret Set Speed", 0.4));
        SQLHelper.stageWidget(Shuffleboard.getTab("Test").add("Shooter Velocity", 0));
        //Drivetrain Outputs
        Shuffleboard.getTab("Drivetrain");
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Right1", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Right2", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Left1", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Left2", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Left Position", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Drivetrain").add("Right Position", 0));
        //Encoders
        Shuffleboard.getTab("Encoders");
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Hood", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Spinner", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Delivery 1", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Delivery 2", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Drivetrain Left", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Drivetrain Right", 0));
        SQLHelper.stageWidget(Shuffleboard.getTab("Encoders").add("Turret", 0));
        Shuffleboard.getTab("Encoders").add("Turret Zero", 0);
        Shuffleboard.getTab("Encoders").add("Spin Act", 0);

        m_robotContainer = new RobotContainer();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use this for items like
     * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
     *
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     */
    @Override
    public void disabledInit() {
        try {
            timer.reset();
            timer.stop();
            SQLHelper.backupTable();
            SQLHelper.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void disabledPeriodic() {
    }

    /**
     * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
     */
    @Override
    public void autonomousInit() {
        m_autonomousCommand = m_robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }
        try {
            if (!SQLHelper.isOpen()) {
                SQLHelper.openConnection();
                SQLHelper.initTable();
                timer.start();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        try {
            SQLHelper.mySQLperiodic((int) timer.get());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        try {
            if (!SQLHelper.isOpen()) {
                SQLHelper.openConnection();
                SQLHelper.initTable();
                timer.start();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        try {
            SQLHelper.mySQLperiodic((int)(timer.get() * 1000));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testInit() {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6695.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	Talon rf;
	Talon rb;
	Talon lf;
	Talon lb;
	DifferentialDrive diffDrive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	Joystick joy;

	@Override
	public void robotInit() {
		rf = new Talon(1);
		rb = new Talon(2);
		lf = new Talon(3);
		lb = new Talon(4);
		left = new SpeedControllerGroup(lf, lb);
		diffDrive = new DifferentialDrive(rf, rb);
		joy = new Joystick(0);
	}

	@Override
	public void autonomousInit() {

	}

	@Override
	public void autonomousPeriodic() {

	}

	@Override
	public void teleopPeriodic() {
		diffDrive.arcadeDrive(joy.getX()*joy.getThrottle(), joy.getZ()*joy.getThrottle(), joy.getTrigger());
	}

	@Override
	public void testPeriodic() {

	}
}

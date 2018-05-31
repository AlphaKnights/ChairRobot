/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6695.robot;

import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	TalonSRX rf;
	TalonSRX rb;
	TalonSRX lf;
	TalonSRX lb;
	MvvDrive mvvdrive;
	SpeedControllerGroup left;
	SpeedControllerGroup right;
	Joystick joy;

	@Override
	public void robotInit() {
		rf = new TalonSRX(1); // Right Front
		rb = new TalonSRX(2); // Right Back
		lf = new TalonSRX(3); // Left Front
		lb = new TalonSRX(4); // Left Back

		mvvdrive = new MvvDrive(rf, rb, lf, lb);
		joy = new Joystick(0);
	}

	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit() {
	}

	@Override
	public void teleopPeriodic() {
		mvvdrive.arcadeDrive(joy.getY(), joy.getX(), joy.getTrigger(), joy.getThrottle());
	}

	@Override
	public void testPeriodic() {
	}
}

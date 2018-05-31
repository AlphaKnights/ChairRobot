package org.usfirst.frc.team6695.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.RobotDriveBase;
import edu.wpi.first.wpilibj.hal.HAL;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tInstances;
import edu.wpi.first.wpilibj.hal.FRCNetComm.tResourceType;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

/**
 * Ideas from {@link DifferentialDrive}
 */
public class MvvDrive extends RobotDriveBase {
	public static final double kDefaultQuickStopThreshold = 0.2;
	public static final double kDefaultQuickStopAlpha = 0.1;

	private TalonSRX m_leftMotor;
	private TalonSRX m_rightMotor;
	private TalonSRX m_leftMotor2;
	private TalonSRX m_rightMotor2;

	private boolean m_reported = false;

	public MvvDrive(TalonSRX rf, TalonSRX rb, TalonSRX lf, TalonSRX lb) {
		m_leftMotor = lf;
		m_leftMotor2 = lb;
		m_rightMotor = rf;
		m_rightMotor2 = rb;
	}

	public void arcadeDrive(double xSpeed, double zRotation, boolean squaredInputs, double throttle) {
		if (!m_reported) {
			HAL.report(tResourceType.kResourceType_RobotDrive, 2, tInstances.kRobotDrive_ArcadeStandard);
			m_reported = true;
		}

		throttle = 1 - ((throttle + 1) / 2);

		xSpeed = limit(xSpeed);
		xSpeed = applyDeadband(xSpeed, m_deadband);

		zRotation = limit(zRotation);
		zRotation = applyDeadband(zRotation, m_deadband);

		// Square the inputs (while preserving the sign) to increase fine control
		// while permitting full power.
		if (squaredInputs) {
			xSpeed = Math.copySign(xSpeed * xSpeed, xSpeed);
			zRotation = Math.copySign(zRotation * zRotation, zRotation);
		}

		double leftMotorOutput;
		double rightMotorOutput;

		double maxInput = Math.copySign(Math.max(Math.abs(xSpeed), Math.abs(zRotation)), xSpeed);

		if (xSpeed >= 0.0) {
			// First quadrant, else second quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			} else {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			}
		} else {
			// Third quadrant, else fourth quadrant
			if (zRotation >= 0.0) {
				leftMotorOutput = xSpeed + zRotation;
				rightMotorOutput = maxInput;
			} else {
				leftMotorOutput = maxInput;
				rightMotorOutput = xSpeed - zRotation;
			}
		}

		m_leftMotor.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput * throttle);
		m_leftMotor2.set(ControlMode.PercentOutput, limit(leftMotorOutput) * m_maxOutput * throttle);

		m_rightMotor.set(ControlMode.PercentOutput, -limit(rightMotorOutput) * m_maxOutput * throttle);
		m_rightMotor2.set(ControlMode.PercentOutput, -limit(rightMotorOutput) * m_maxOutput * throttle);

		m_safetyHelper.feed();
	}

	public void setDeadband(double deadband) {
		m_deadband = deadband;
	}

	public void setMaxOutput(double maxOutput) {
		m_maxOutput = maxOutput;
	}

	@Override
	public void initSendable(SendableBuilder builder) {

	}

	@Override
	public void stopMotor() {

	}

	@Override
	public String getDescription() {
		return null;
	}
}

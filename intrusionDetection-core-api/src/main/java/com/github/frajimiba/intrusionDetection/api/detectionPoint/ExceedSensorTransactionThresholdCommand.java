package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class ExceedSensorTransactionThresholdCommand extends BaseSensorCommand {
	
	private static final long serialVersionUID = -3855638626947263742L;

	private final SubjectId accountId;
	
	public ExceedSensorTransactionThresholdCommand(SensorId identifier, SubjectId accountId) {
		super(identifier);
		this.accountId = accountId;
	}

	public SubjectId getAccountId() {
		return this.accountId;
	}
}
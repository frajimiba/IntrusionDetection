package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionThresholdExceededEvent extends BaseSensorEvent {
		
	private static final long serialVersionUID = 3809315104562036280L;

	private final SensorTransactionId sensorTransactionId;
	private final SubjectId accountId;
	
	public SensorTransactionThresholdExceededEvent(SensorId identifier, SensorTransactionId sensorTransactionId, SubjectId accountId) {
		super(identifier);
		this.sensorTransactionId = sensorTransactionId;
		this.accountId = accountId;
	}

	public SensorTransactionId getSensorTransactionId() {
		return this.sensorTransactionId;
	}

	public SubjectId getAccountId() {
		return this.accountId;
	}
}
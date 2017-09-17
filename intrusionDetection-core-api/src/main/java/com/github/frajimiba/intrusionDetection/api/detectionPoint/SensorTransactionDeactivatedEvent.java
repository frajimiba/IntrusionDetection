package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionDeactivatedEvent extends BaseSensorEvent {

	private static final long serialVersionUID = -1086964108722143113L;
		
	private final SensorTransactionId sensorTransactionId;
	private final SubjectId accountId;
	
	public SensorTransactionDeactivatedEvent(SensorId identifier, SensorTransactionId sensorTransactionId, SubjectId accountId) {
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
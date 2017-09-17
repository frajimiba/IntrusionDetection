package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionStimulatedEvent extends BaseSensorEvent {

	private static final long serialVersionUID = -6329414847785147342L;
	
	private final SensorTransactionId sensorTransactionId;
	private final SubjectId accountId;
	
	public SensorTransactionStimulatedEvent(SensorId identifier, SensorTransactionId sensorTransactionId,  SubjectId accountId) {
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
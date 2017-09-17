package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionActivatedEvent extends BaseSensorEvent {
	
	private static final long serialVersionUID = -3538523957504763869L;
		
	private final SensorType sensorType;
	private final Threshold threshold;
	private final SensorTransactionId sensorTransactionId;
	private final SubjectId accountId;
	
	public SensorTransactionActivatedEvent(SensorId identifier, SensorTransactionId sensorTransactionId, SensorType sensorType, Threshold threshold, SubjectId accountId) {
		super(identifier);
		this.threshold = threshold;
		this.sensorTransactionId = sensorTransactionId;
		this.accountId = accountId;
		this.sensorType = sensorType;
	}
	
	public Threshold getThreshold() {
		return threshold;
	}

	public SensorTransactionId getSensorTransactionId() {
		return this.sensorTransactionId;
	}

	public SubjectId getAccountId() {
		return accountId;
	}

	public SensorType getSensorType() {
		return sensorType;
	}
	
}
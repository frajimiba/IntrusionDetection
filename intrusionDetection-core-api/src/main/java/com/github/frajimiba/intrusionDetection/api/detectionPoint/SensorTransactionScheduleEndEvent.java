package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionScheduleEndEvent extends BaseSensorEvent {

	private static final long serialVersionUID = -1773901940443739444L;
	
	private final SensorTransactionId sensorTransactionId;
	private final SubjectId accountId;
	
	public SensorTransactionScheduleEndEvent(SensorId identifier, SensorTransactionId sensorTransactionId, SubjectId accountId) {
		super(identifier);
		this.sensorTransactionId = sensorTransactionId;
		this.accountId = accountId;
	}

	public SensorTransactionId getSensorTransactionId() {
		return sensorTransactionId;
	}

	public SubjectId getAccountId() {
		return accountId;
	}
	
}

package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class ActiveSensorTransactionCommand extends BaseSensorCommand {

	private static final long serialVersionUID = 2425909497305803267L;
	
	private final SubjectId accountId;
	private final SensorTransactionId sensorTransactionId;
	
	public ActiveSensorTransactionCommand(SensorId identifier, SensorTransactionId sensorTransactionId, SubjectId accountId ) {
		super(identifier);
		this.sensorTransactionId = sensorTransactionId;
		this.accountId = accountId;
	}
	
	public SubjectId getAccountId() {
		return accountId;
	}

	public SensorTransactionId getSensorTransactionId() {
		return sensorTransactionId;
	}
}

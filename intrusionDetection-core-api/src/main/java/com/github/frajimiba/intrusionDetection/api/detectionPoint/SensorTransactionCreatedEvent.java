package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorTransactionCreatedEvent extends BaseSensorEvent {
		
	private static final long serialVersionUID = 7413916239387214284L;
	
	private final Threshold threshold;
	private final SubjectId accountId;
	
	public SensorTransactionCreatedEvent(SensorId identifier, Threshold threshold, SubjectId accountId) {
		super(identifier);
		this.threshold = threshold;
		this.accountId = accountId;
	}
	
	public Threshold getThreshold() {
		return threshold;
	}
	public SubjectId getAccountId() {
		return accountId;
	}
	
}
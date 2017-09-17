package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorActiveCommand extends BaseSensorCommand {

	private static final long serialVersionUID = 6134846557529662398L;
	
	private final SubjectId accountId;
	
	public SensorActiveCommand(SensorId identifier, SubjectId accountId) {
		super(identifier);
		this.accountId = accountId;
	}

	public SubjectId getAccountId() {
		return accountId;
	}

}
package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class DeactiveSensorTransactionCommand extends BaseSensorCommand {

	private static final long serialVersionUID = -2522686751839437013L;
	
	private final SubjectId accountId;
	
	public DeactiveSensorTransactionCommand(SensorId identifier, SubjectId accountId ) {
		super(identifier);
		this.accountId = accountId;
	}
	
	public SubjectId getAccountId() {
		return accountId;
	}

}

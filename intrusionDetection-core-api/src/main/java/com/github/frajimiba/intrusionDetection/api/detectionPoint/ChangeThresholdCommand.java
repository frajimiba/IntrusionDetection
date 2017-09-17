package com.github.frajimiba.intrusionDetection.api.detectionPoint;

public class ChangeThresholdCommand extends BaseSensorCommand {

	private static final long serialVersionUID = 5825008003726705677L;
	
	private final Long limitValue;
	private final Long activeTimestamp;
	
	public ChangeThresholdCommand(SensorId identifier, long limitValue, long activeTimestamp) {
		super(identifier);
		this.limitValue = limitValue;
		this.activeTimestamp = activeTimestamp;
	}

	public Long getLimitValue() {
		return limitValue;
	}

	public Long getActiveTimestamp() {
		return activeTimestamp;
	}

}

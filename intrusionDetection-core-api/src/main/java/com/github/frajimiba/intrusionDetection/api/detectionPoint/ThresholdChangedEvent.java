package com.github.frajimiba.intrusionDetection.api.detectionPoint;

public class ThresholdChangedEvent extends BaseSensorEvent {
	
	private static final long serialVersionUID = -168743534212995894L;
	
	private final Long limitValue;
	private final Long activeTimestamp;
	
	public ThresholdChangedEvent(SensorId identifier, long limitValue, long activeTimestamp) {
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
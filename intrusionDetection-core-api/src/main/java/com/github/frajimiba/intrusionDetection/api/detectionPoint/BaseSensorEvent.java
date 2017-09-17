package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public abstract class BaseSensorEvent implements SensorEvent {

	private static final long serialVersionUID = 3145319432719606756L;
	
	@TargetAggregateIdentifier
	private final SensorId identifier;
	
	public BaseSensorEvent(SensorId identifier) {
		this.identifier = identifier;
	}

	@Override
	public SensorId getIdentifier() {
		return identifier;
	}
}

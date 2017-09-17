package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public abstract class BaseSensorCommand implements SensorCommand {
	
	private static final long serialVersionUID = -4388039579222435856L;
	
	@TargetAggregateIdentifier
	private final SensorId identifier;
	
	public BaseSensorCommand(SensorId identifier){
		this.identifier = identifier;
	}

	@Override
	public SensorId getIdentifier() {
		return identifier;
	}
}

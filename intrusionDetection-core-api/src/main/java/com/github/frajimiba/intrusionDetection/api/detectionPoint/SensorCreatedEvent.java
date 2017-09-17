package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class SensorCreatedEvent extends BaseSensorEvent {
	
	private static final long serialVersionUID = -2255246013293757359L;

	private final Threshold threshold;
	private final SensorType sensorType;
		
	
	public SensorCreatedEvent(SensorId identifier, SensorType sensorType, Threshold threshold) {
		super(identifier);
		this.threshold = threshold;
		this.sensorType = sensorType;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public SensorType getSensorType() {
		return sensorType;
	}

}
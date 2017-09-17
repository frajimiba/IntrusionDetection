package com.github.frajimiba.intrusionDetection.api.detectionPoint;


public class CreateSensorCommand extends BaseSensorCommand {

	private static final long serialVersionUID = 1504029718938598015L;
	
	private final Threshold threshold;
	private final SensorType sensorType;
	
	public CreateSensorCommand(SensorId identifier, SensorType sensorType, Threshold threshold ) {
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
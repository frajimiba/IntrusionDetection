package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

public interface SensorCommand extends Serializable {
	SensorId getIdentifier();
}

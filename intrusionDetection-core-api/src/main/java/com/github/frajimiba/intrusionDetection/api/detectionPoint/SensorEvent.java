package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

public interface SensorEvent extends Serializable{
	SensorId getIdentifier();
}

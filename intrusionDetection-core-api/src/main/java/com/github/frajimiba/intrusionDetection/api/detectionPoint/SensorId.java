package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

public class SensorId implements Serializable {

	private static final long serialVersionUID = 4654598656754701927L;
	
	private String identifier;

	public SensorId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
	}

	public SensorId(String identifier) {
		Assert.notNull(identifier, "Identifier may not be null");
		this.identifier = identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SensorId sensorId = (SensorId) o;
		return identifier.equals(sensorId.identifier);
	}

	@Override
	public int hashCode() {
		return identifier.hashCode();
	}

	@Override
	public String toString() {
		return identifier;
	}
}
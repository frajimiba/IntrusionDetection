package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

public class SensorTransactionId implements Serializable {

	private static final long serialVersionUID = -3610456973259936450L;

	private String identifier;

	public SensorTransactionId() {
		this.identifier = IdentifierFactory.getInstance().generateIdentifier();
	}

	public SensorTransactionId(String identifier) {
		Assert.notNull(identifier, "Identifier may not be null");
		this.identifier = identifier;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		SensorTransactionId sensorTransactionId = (SensorTransactionId) o;
		return identifier.equals(sensorTransactionId.identifier);
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

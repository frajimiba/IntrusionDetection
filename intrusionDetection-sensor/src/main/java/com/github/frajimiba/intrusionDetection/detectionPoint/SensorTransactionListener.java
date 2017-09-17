package com.github.frajimiba.intrusionDetection.detectionPoint;

import javax.inject.Inject;
import javax.inject.Named;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.ActiveSensorTransactionCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionCreatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionId;

@Named
public class SensorTransactionListener {
	
	@Inject
	private CommandGateway commandGateway;
	
	@EventHandler
	public void handle(SensorTransactionCreatedEvent event) {
		SensorTransactionId sensorId = new SensorTransactionId();
		commandGateway.send(new ActiveSensorTransactionCommand(event.getIdentifier(), sensorId, event.getAccountId()));
	}

}
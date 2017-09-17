package com.github.frajimiba.intrusionDetection.query.detectionPoint;

import javax.inject.Inject;
import javax.inject.Named;

import org.axonframework.eventhandling.annotation.EventHandler;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorCreatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionActivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionDeactivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionThresholdExceededEvent;
import com.github.frajimiba.intrusionDetection.query.detectionPoint.repositories.SensorQueryRepository;

@Named
public class SensorListener {
	
	@Inject
	private SensorQueryRepository detectionPointRepository;

	@EventHandler
	public void handle(SensorCreatedEvent event) {
		SensorEntry sensor = new SensorEntry();
		sensor.setIdentifier(event.getIdentifier().toString());
		sensor.setActiveTransactions(0L);
		sensor.setThresholdExceeded(0L);
		detectionPointRepository.save(sensor);
	}
	
	@EventHandler
	public void handle(SensorTransactionActivatedEvent event) {
		SensorEntry sensor = detectionPointRepository.findOne(event.getIdentifier().toString());
		sensor.setActiveTransactions(sensor.getActiveTransactions() + 1);
		detectionPointRepository.save(sensor);
	}
	
	@EventHandler
	public void handle(SensorTransactionDeactivatedEvent event) {
		SensorEntry sensor = detectionPointRepository.findOne(event.getIdentifier().toString());
		sensor.setActiveTransactions(sensor.getActiveTransactions() - 1);
		detectionPointRepository.save(sensor);
	}
	
	@EventHandler
	public void handle(SensorTransactionThresholdExceededEvent event) {
		SensorEntry sensor = detectionPointRepository.findOne(event.getIdentifier().toString());
		sensor.setActiveTransactions(sensor.getActiveTransactions() + 1);
		detectionPointRepository.save(sensor);
	}
	
}

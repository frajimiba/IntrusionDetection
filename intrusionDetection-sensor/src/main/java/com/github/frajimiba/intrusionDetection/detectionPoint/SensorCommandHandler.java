package com.github.frajimiba.intrusionDetection.detectionPoint;

import javax.inject.Inject;
import javax.inject.Named;

import org.axonframework.commandhandling.annotation.CommandHandler;
import org.axonframework.repository.Repository;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.ActiveSensorTransactionCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.ChangeThresholdCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.CreateSensorCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.DeactiveSensorTransactionCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.ExceedSensorTransactionThresholdCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorActiveCommand;

@Named
public class SensorCommandHandler {
	
	@Inject
    @Named("sensorRepository")
	private Repository<Sensor> repository;
	
	@CommandHandler
	public void handle(CreateSensorCommand command) {
		Sensor sensor = new Sensor(command.getIdentifier(), command.getSensorType(), command.getThreshold());
		repository.add(sensor);
	}
	
	@CommandHandler
	public void handle(SensorActiveCommand command){
		Sensor sensor = repository.load(command.getIdentifier());
		sensor.active(command.getAccountId());	
	}

	@CommandHandler
	public void handle(ActiveSensorTransactionCommand command){
		Sensor sensor = repository.load(command.getIdentifier());
		sensor.activeSensor(command.getSensorTransactionId(), command.getAccountId());
	}
		
	@CommandHandler
	public void handle(DeactiveSensorTransactionCommand command) {
		Sensor sensor = repository.load(command.getIdentifier());
		sensor.deactiveSensor(command.getAccountId());
	}
	
	@CommandHandler
	public void handle(ExceedSensorTransactionThresholdCommand command) {
		Sensor sensor = repository.load(command.getIdentifier());
		sensor.exceedThreshold(command.getAccountId());
	}
	
	@CommandHandler
	public void handle(ChangeThresholdCommand command){
		Sensor sensor = repository.load(command.getIdentifier());
		sensor.changeThreshold(command.getLimitValue(), command.getActiveTimestamp());
	}
	
}
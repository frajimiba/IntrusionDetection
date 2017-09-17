package com.github.frajimiba.intrusionDetection.detectionPoint;

import java.util.logging.Logger;

import javax.annotation.Resource;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.Timestamp;
import org.axonframework.eventhandling.scheduling.EventScheduler;
import org.axonframework.eventhandling.scheduling.ScheduleToken;
import org.axonframework.saga.annotation.AbstractAnnotatedSaga;
import org.axonframework.saga.annotation.EndSaga;
import org.axonframework.saga.annotation.SagaEventHandler;
import org.axonframework.saga.annotation.StartSaga;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.DeactiveSensorTransactionCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.ExceedSensorTransactionThresholdCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionActivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionDeactivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionScheduleEndEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionStimulatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionThresholdExceededEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorType;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SubjectId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.Threshold;

public class SensorTransactionManager extends AbstractAnnotatedSaga {

	private static final long serialVersionUID = -3358364954655066373L;
	private static final Logger LOGGER = Logger.getLogger(SensorTransactionManager.class.getName());
	
	private transient CommandGateway commandGateway;
	private transient EventScheduler eventScheduler;

	private ScheduleToken scheduleToken;
	private long attemps;
	private Threshold threshold;
	private SensorType sensorType;
	
	@StartSaga
	@SagaEventHandler(associationProperty = "sensorTransactionId")
	public void handle(final SensorTransactionActivatedEvent event, @Timestamp DateTime eventTime) {
				
		if (this.scheduleToken != null) { 
			eventScheduler.cancelSchedule(this.scheduleToken); 
		}
		
		this.attemps = 0;
		this.threshold = event.getThreshold();
		this.sensorType = event.getSensorType();

		Duration duration = new Duration(this.threshold.getActiveTimestamp());
		
		this.scheduleToken = eventScheduler.schedule(duration,new SensorTransactionScheduleEndEvent(event.getIdentifier(), event.getSensorTransactionId(), event.getAccountId()));
		LOGGER.info("El sensor " + event.getIdentifier() + " se ha activado. umbral [Parada en : [" + duration.getMillis() + " ms])  , valor límite: (" + this.threshold.getLimitValue() + ")]");			

		evaluateThreshold(event.getIdentifier(), event.getSensorTransactionId(), event.getAccountId());
	}
		
	private void evaluateThreshold(SensorId sensorId, SensorTransactionId sensorTransactionId, SubjectId subjectId){
		this.attemps ++;
		LOGGER.info("El sensor " + sensorTransactionId + " se ha sido estimulado (" + this.attemps + " / " + this.threshold.getLimitValue() + ")");

		if (this.attemps >= this.threshold.getLimitValue()){
			if (this.sensorType==SensorType.INACTIVITY){
				commandGateway.send(new DeactiveSensorTransactionCommand(sensorId, subjectId));
			}else{
				commandGateway.send(new ExceedSensorTransactionThresholdCommand(sensorId, subjectId));
			}
		}
	}
	
	@SagaEventHandler(associationProperty = "sensorTransactionId")
	public void handle(final SensorTransactionStimulatedEvent event) {
		evaluateThreshold(event.getIdentifier(), event.getSensorTransactionId(), event.getAccountId());
	}

	@EndSaga
	@SagaEventHandler(associationProperty = "sensorTransactionId")
	public void handle(final SensorTransactionDeactivatedEvent event) {
		if (this.scheduleToken != null) { 
			LOGGER.info("Cancelada tarea programada: " + this.scheduleToken.toString());
			eventScheduler.cancelSchedule(this.scheduleToken);
		}
		LOGGER.info("El sensor " + event.getIdentifier() + " se ha desactivado.");
	}
	
	@SagaEventHandler(associationProperty = "sensorTransactionId")
	public void handle(final SensorTransactionThresholdExceededEvent event) {
		commandGateway.send(new DeactiveSensorTransactionCommand(event.getIdentifier(), event.getAccountId()));
		LOGGER.info("El sensor " + event.getIdentifier() + " ha sobrepasado el umbral");
	}

	@SagaEventHandler(associationProperty = "sensorTransactionId")
	public void handle(final SensorTransactionScheduleEndEvent event) {
		
		if (this.sensorType==SensorType.INACTIVITY){
			commandGateway.send(new ExceedSensorTransactionThresholdCommand(event.getIdentifier(), event.getAccountId()));
		}else {
			commandGateway.send(new DeactiveSensorTransactionCommand(event.getIdentifier(), event.getAccountId()));
		}
		
		LOGGER.info("El sensor " + event.getIdentifier() + " ha sobrepaso su tiempo de actividad (" + this.threshold.getActiveTimestamp() + ")");
	}

	@Resource
	public void setCommandGateway(CommandGateway commandGateway) {
		this.commandGateway = commandGateway;
	}

	@Resource
	public void setEventScheduler(EventScheduler eventScheduler) {
		this.eventScheduler = eventScheduler;
	}
}
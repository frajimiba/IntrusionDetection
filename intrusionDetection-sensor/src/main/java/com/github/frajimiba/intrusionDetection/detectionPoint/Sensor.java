package com.github.frajimiba.intrusionDetection.detectionPoint;

import java.util.HashMap;
import java.util.Map;

import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorCreatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionActivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionCreatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionDeactivatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionStimulatedEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorTransactionThresholdExceededEvent;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorType;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SubjectId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.Threshold;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.ThresholdChangedEvent;

public class Sensor extends AbstractAnnotatedAggregateRoot<SensorId> {

	private static final long serialVersionUID = 879972245872543957L;
	
	@AggregateIdentifier
	private SensorId id;
	private Threshold threshold;
	private SensorType sensorType;
	
	private Map<SubjectId,SensorTransactionId> activeSensors = new HashMap<SubjectId,SensorTransactionId>();

	public Sensor(){}
	
	public Sensor(SensorId id, SensorType sensorType, Threshold threshold){
		apply(new SensorCreatedEvent(id, sensorType, threshold));
	}
	
	public void changeThreshold(long limitValue, long activeTimestamp){
		apply(new ThresholdChangedEvent(this.id,limitValue, activeTimestamp));
	}
	
	public void exceedThreshold(SubjectId accountId){
		if (activeSensors.containsKey(accountId)){
			SensorTransactionId sensorId = activeSensors.get(accountId);
			apply(new SensorTransactionThresholdExceededEvent(this.id, sensorId, accountId));
		}
	}

	public void active(SubjectId accountId){
		if (activeSensors.containsKey(accountId)){
			SensorTransactionId sensorId = activeSensors.get(accountId);
			apply(new SensorTransactionStimulatedEvent(this.id, sensorId, accountId));
		}else{
			apply(new SensorTransactionCreatedEvent(this.id, this.threshold, accountId));
		}
	}
	
	public void activeSensor(SensorTransactionId sensorId, SubjectId accountId){
		if (!activeSensors.containsKey(accountId)){
			apply(new SensorTransactionActivatedEvent(this.id, sensorId, this.sensorType, this.threshold, accountId));
		}
	}
		
	public void deactiveSensor(SubjectId accountId){
		if (activeSensors.containsKey(accountId)){
			SensorTransactionId sensorId = activeSensors.get(accountId);
			apply(new SensorTransactionDeactivatedEvent(this.id, sensorId, accountId));
		}
	}
	
	@EventSourcingHandler
	public void on(SensorCreatedEvent event){
		this.id = event.getIdentifier();
		this.threshold = event.getThreshold();
		this.sensorType = event.getSensorType();
	}

	@EventSourcingHandler
	public void on(SensorTransactionActivatedEvent event){
		activeSensors.put(event.getAccountId(), event.getSensorTransactionId());
	}
	
	@EventSourcingHandler
	public void on(SensorTransactionDeactivatedEvent event){
		activeSensors.remove(event.getAccountId());
	}
	
	@EventSourcingHandler
	public void on(ThresholdChangedEvent event){
		this.threshold.setLimitValue(event.getLimitValue());
		this.threshold.setActiveTimestamp(event.getActiveTimestamp());
	}
	
}
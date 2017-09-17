package com.github.frajimiba.intrusionDetection.test;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import javax.inject.Inject;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.github.frajimiba.intrusionDetection.api.detectionPoint.CreateSensorCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorActiveCommand;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SensorType;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.SubjectId;
import com.github.frajimiba.intrusionDetection.api.detectionPoint.Threshold;

@Controller
@RequestMapping("/test")
@SessionAttributes({"detectionPoint1Id","detectionPoint2Id","detectionPoint3Id"})
public class TestController {

	private static final Logger LOGGER = Logger.getLogger(TestController.class.getName());
	
	@Inject
	private CommandGateway commandGateway;
	
	private static List<SubjectId> subjects = new ArrayList<SubjectId>();
	private static List<SensorId> sensors = new ArrayList<SensorId>(); 
	

	@RequestMapping(method = RequestMethod.GET)
	public String get(Model model) {
		
		if (subjects.size()==0) {
			subjects.add(new SubjectId());
			subjects.add(new SubjectId());
			subjects.add(new SubjectId());
		}
		
		if (sensors.size()==0) {
			
			SensorId Sensor1Id = new SensorId();
			SensorId Sensor2Id = new SensorId();
			SensorId Sensor3Id = new SensorId();
			
			commandGateway.sendAndWait(new CreateSensorCommand(Sensor1Id, SensorType.INACTIVITY, new Threshold(2, 60000)));
			LOGGER.info("[SENSOR: " + Sensor1Id + ", Threshold: (2,60000)] - CREADO");
			commandGateway.sendAndWait(new CreateSensorCommand(Sensor2Id, SensorType.INACTIVITY,new Threshold(3, 60000)));
			LOGGER.info("[SENSOR: " + Sensor2Id + ", Threshold: (3,60000)] - CREADO");
			commandGateway.sendAndWait(new CreateSensorCommand(Sensor3Id, SensorType.INACTIVITY,new Threshold(5, 60000)));
			LOGGER.info("[SENSOR: " + Sensor3Id + ", Threshold: (5,60000)] - CREADO");
			
			sensors.add(Sensor1Id);
			sensors.add(Sensor2Id);
			sensors.add(Sensor3Id);
			
		}
				

		return "test";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String post(ModelMap map) {
		
		SensorId sensorId = sensors.get(randInt(0,2));
		SubjectId subjectId = subjects.get(randInt(0,2));

		LOGGER.info("[SENSOR: " + sensorId + ", SUBJECT: " + subjectId + "] - ESTIMULAR");
		commandGateway.send(new SensorActiveCommand(sensorId , subjectId));
		
		return "test";
	}
	
	
	public int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}

}	

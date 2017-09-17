package com.github.frajimiba.intrusionDetection.query.detectionPoint.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.github.frajimiba.intrusionDetection.query.detectionPoint.SensorEntry;

public interface SensorQueryRepository 
	extends PagingAndSortingRepository<SensorEntry, String> {
		
}
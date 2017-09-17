package com.github.frajimiba.intrusionDetection.query.detectionPoint;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class SensorEntry {
	
	@Id
	private String identifier;
	private Long activeTransactions;
	private Long thresholdExceeded;
			
	public String getIdentifier(){
		return identifier;
	}
	
	public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
	
	public Long getActiveTransactions() {
		return activeTransactions;
	}

	public void setActiveTransactions(Long activeTransactions) {
		this.activeTransactions = activeTransactions;
	}

	public Long getThresholdExceeded() {
		return thresholdExceeded;
	}

	public void setThresholdExceeded(Long thresholdExceeded) {
		this.thresholdExceeded = thresholdExceeded;
	}


}

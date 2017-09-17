package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

public class Threshold implements Serializable {

	private static final long serialVersionUID = -5821962086625733744L;
	
	private long limitValue;
	private long activeTimestamp;
	
	public Threshold(){
		
	}
		
	public Threshold(int limitValue, long activeTimestamp){
		this.limitValue = limitValue;
		this.activeTimestamp = activeTimestamp;
	}

	public Long getLimitValue() {
		return this.limitValue;
	}

	public Long getActiveTimestamp() {
		return activeTimestamp;
	}
	
	public void setLimitValue(long limitValue) {
		this.limitValue = limitValue;
	}

	public void setActiveTimestamp(long activeTimestamp) {
		this.activeTimestamp = activeTimestamp;
	}
	
}
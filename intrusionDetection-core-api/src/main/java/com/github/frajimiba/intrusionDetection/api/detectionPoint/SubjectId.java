package com.github.frajimiba.intrusionDetection.api.detectionPoint;

import java.io.Serializable;

import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

public class SubjectId implements Serializable {

	private static final long serialVersionUID = -3610456973259936450L;

	private String identifier;

    public SubjectId() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public SubjectId(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }

    @Override
    public boolean equals(Object o) {
    	if (this == o) {
        	return true;
        }
        if (o == null || getClass() != o.getClass()){
        	return false;
        }
        SubjectId subjectId = (SubjectId) o;
        
        return identifier.equals(subjectId.identifier);
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

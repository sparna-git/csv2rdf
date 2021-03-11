package fr.sparna.rdf.model;

import java.io.Serializable;

public abstract class Value implements Serializable {

	public abstract boolean isLiteral();
	
	public abstract boolean isResource();
	
}

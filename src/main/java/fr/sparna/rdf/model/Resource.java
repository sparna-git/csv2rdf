package fr.sparna.rdf.model;

import java.io.Serializable;

public abstract class Resource extends Value implements Serializable {

	@Override
	public boolean isLiteral() {
		return false;
	}

	@Override
	public boolean isResource() {
		return true;
	}

	public abstract boolean isIRI();
	
	public abstract boolean isBNode();
	
}

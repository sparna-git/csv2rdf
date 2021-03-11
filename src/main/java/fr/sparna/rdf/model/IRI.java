package fr.sparna.rdf.model;

import java.io.Serializable;

public class IRI extends Resource implements Serializable {

	private String value;
	
	public IRI() {
		super();
	}

	public IRI(String value) {
		super();
		this.value = value;
	}

	@Override
	public boolean isIRI() {
		return true;
	}

	@Override
	public boolean isBNode() {
		return false;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "IRI [value=" + value + "]";
	}
	
}

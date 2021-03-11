package fr.sparna.rdf.model;

import java.io.Serializable;

public class BNode extends Resource implements Serializable {

	public String ID;
	
	@Override
	public boolean isIRI() {
		return false;
	}

	@Override
	public boolean isBNode() {
		return true;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	
	
}

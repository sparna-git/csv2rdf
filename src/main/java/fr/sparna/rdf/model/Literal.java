package fr.sparna.rdf.model;

import java.io.Serializable;

public class Literal extends Value implements Serializable {

	private String label;
	private String language;
	private IRI datatype;
	
	public Literal() {
		super();
	}
	
	public Literal(String value) {
		super();
		this.label = value;
	}
	
	public Literal(String value, String language) {
		super();
		this.label = value;
		this.language = language;
	}
	
	@Override
	public boolean isLiteral() {
		return true;
	}

	@Override
	public boolean isResource() {
		return false;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public IRI getDatatype() {
		return datatype;
	}

	public void setDatatype(IRI datatype) {
		this.datatype = datatype;
	}

	@Override
	public String toString() {
		return "Literal [label=" + label + ", language=" + language + ", datatype=" + datatype + "]";
	}

}

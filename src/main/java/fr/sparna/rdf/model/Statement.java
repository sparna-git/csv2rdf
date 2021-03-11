package fr.sparna.rdf.model;

import java.io.Serializable;

public class Statement implements Serializable {

	private Resource subject;
	private IRI predicate;
	private Value object;
	
	public Statement() {
		
	}
	
	public Statement(Resource subject, IRI predicate, Value object) {
		super();
		this.subject = subject;
		this.predicate = predicate;
		this.object = object;
	}

	public Resource getSubject() {
		return subject;
	}
	
	public void setSubject(Resource subject) {
		this.subject = subject;
	}
	
	public IRI getPredicate() {
		return predicate;
	}
	
	public void setPredicate(IRI predicate) {
		this.predicate = predicate;
	}
	
	public Value getObject() {
		return object;
	}
	
	public void setObject(Value object) {
		this.object = object;
	}

	@Override
	public String toString() {
		return "Statement [subject=" + subject + ", predicate=" + predicate + ", object=" + object + "]";
	}
	
}

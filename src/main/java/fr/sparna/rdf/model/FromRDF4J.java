package fr.sparna.rdf.model;

public class FromRDF4J {

	public static Model fromRDF4J(org.eclipse.rdf4j.model.Model model) {
		
		Model output = new Model();
		
		model.forEach(s -> { 
			output.getStatements().add(fromRDF4J(s));
		});
		
		return output;
		
	}
	
	public static Statement fromRDF4J(org.eclipse.rdf4j.model.Statement statement) {
		Statement output = new Statement();
		output.setSubject(fromRDF4J(statement.getSubject()));
		output.setPredicate(fromRDF4J(statement.getPredicate()));
		output.setObject(fromRDF4J(statement.getObject()));
		return output;
	}
	
	public static Value fromRDF4J(org.eclipse.rdf4j.model.Value value) {
		if(value instanceof org.eclipse.rdf4j.model.Resource) {
			return fromRDF4J((org.eclipse.rdf4j.model.Resource)value);
		} else if(value instanceof org.eclipse.rdf4j.model.Literal) {
			return fromRDF4J((org.eclipse.rdf4j.model.Literal)value);
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static Resource fromRDF4J(org.eclipse.rdf4j.model.Resource resource) {
		if(resource instanceof org.eclipse.rdf4j.model.IRI) {
			return fromRDF4J((org.eclipse.rdf4j.model.IRI)resource);
		} else if(resource instanceof org.eclipse.rdf4j.model.BNode) {
			BNode bnode = new BNode();
			bnode.setID(((org.eclipse.rdf4j.model.BNode)resource).getID());
			return bnode;
		} else {
			throw new IllegalArgumentException();
		}
	}
	
	public static IRI fromRDF4J(org.eclipse.rdf4j.model.IRI iri) {
		IRI output = new IRI();
		output.setValue(iri.stringValue());
		return output;
	}
	
	public static Literal fromRDF4J(org.eclipse.rdf4j.model.Literal literal) {
		Literal output = new Literal();
		output.setLabel(literal.getLabel());
		literal.getLanguage().ifPresent(l -> output.setLanguage(l));
		if(literal.getLanguage().isEmpty() && literal.getDatatype() != null) {
			output.setDatatype(fromRDF4J(literal.getDatatype()));
		}
		return output;
	}
	
}

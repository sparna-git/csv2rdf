package fr.sparna.rdf.model;
import static org.eclipse.rdf4j.model.util.Statements.statement;
import static org.eclipse.rdf4j.model.util.Values.iri;
import static org.eclipse.rdf4j.model.util.Values.bnode;
import static org.eclipse.rdf4j.model.util.Values.literal;

import org.eclipse.rdf4j.model.impl.TreeModel;

public class ToRDF4J {

	public static org.eclipse.rdf4j.model.Model toRDF4J(Model model) {
		org.eclipse.rdf4j.model.Model output = new TreeModel();
		
		model.getStatements().stream().forEach(s -> output.add(toRDF4J(s)));
		
		return output;
	}
	
	public static org.eclipse.rdf4j.model.Statement toRDF4J(Statement statement) {
		org.eclipse.rdf4j.model.Statement output = statement(
				toRDF4J(statement.getSubject()),
				toRDF4J(statement.getPredicate()),
				toRDF4J(statement.getObject()),
				null
		);
		
		return output;
	}
	
	public static org.eclipse.rdf4j.model.Value toRDF4J(Value value) {
		if(value.isResource()) {
			return toRDF4J((Resource)value);
		} else {
			return toRDF4J((Literal)value);
		}
	}
	
	public static org.eclipse.rdf4j.model.Resource toRDF4J(Resource resource) {
		if(resource.isIRI()) {
			return toRDF4J((IRI)resource);
		} else {
			return toRDF4J((BNode)resource);
		}
	}
	
	public static org.eclipse.rdf4j.model.IRI toRDF4J(IRI iri) {
		org.eclipse.rdf4j.model.IRI output = iri(iri.getValue());		
		return output;
	}
	
	public static org.eclipse.rdf4j.model.BNode toRDF4J(BNode bnode) {
		org.eclipse.rdf4j.model.BNode output = bnode(bnode.getID());		
		return output;
	}
	
	public static org.eclipse.rdf4j.model.Literal toRDF4J(Literal literal) {
		org.eclipse.rdf4j.model.Literal output;
		if(literal.getLanguage() != null) {
			output = literal(literal.getLabel(), literal.getLanguage());
		} else if(literal.getDatatype() != null) {
			output = literal(literal.getLabel(), toRDF4J(literal.getDatatype()));
		} else {
			output = literal(literal.getLabel());
		}
		
		return output;
	}
	
}

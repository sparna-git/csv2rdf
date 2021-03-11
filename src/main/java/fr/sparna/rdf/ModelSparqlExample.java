package fr.sparna.rdf;

import static org.eclipse.rdf4j.model.util.Statements.statement;
import static org.eclipse.rdf4j.model.util.Values.iri;
import static org.eclipse.rdf4j.model.util.Values.bnode;
import static org.eclipse.rdf4j.model.util.Values.literal;

import org.eclipse.rdf4j.model.BNode;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.impl.TreeModel;
import org.eclipse.rdf4j.model.vocabulary.RDF;
import org.eclipse.rdf4j.query.GraphQuery;
import org.eclipse.rdf4j.query.GraphQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFHandler;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

import fr.sparna.rdf.model.FromRDF4J;
import fr.sparna.rdf.model.ToRDF4J;

public class ModelSparqlExample {

	

	public void test(fr.sparna.rdf.model.Model inputModel) {
		// couvert our input Model structure to RDF4J model structure
		Model model = ToRDF4J.toRDF4J(inputModel);
		
		// prepare RDF4J Model as output of the query
		Model outputModel = new TreeModel();
		
		// create in-memory repository
		Repository r = new SailRepository(new MemoryStore());
		try(RepositoryConnection c = r.getConnection()) {
			// add the model to the repository
			c.add(model);
			
			// prepare SPARQL query
			GraphQuery gq = c.prepareGraphQuery("CONSTRUCT { ?x a <http://www.w3.org/ns/org#Organization> } WHERE { ?x a <http://schema.org/Organization> }");
			// prepare collector that will collect result of query into Model
			StatementCollector collector = new StatementCollector(outputModel);
			// execute query
			gq.evaluate(collector);
		}
		
		// convert to our Model
		fr.sparna.rdf.model.Model ourModel = FromRDF4J.fromRDF4J(outputModel);
		System.out.println(ourModel);
	}
	
	public static void main(String...strings) throws Exception {
		ModelSparqlExample me = new ModelSparqlExample();
		fr.sparna.rdf.model.Model testModel = new fr.sparna.rdf.model.Model();
		testModel.getStatements().add(new fr.sparna.rdf.model.Statement(
				new fr.sparna.rdf.model.IRI("http://sparna.fr"),
				FromRDF4J.fromRDF4J(RDF.TYPE),
				new fr.sparna.rdf.model.IRI("http://schema.org/Organization")
		));
		me.test(testModel);
	}
	
	
}

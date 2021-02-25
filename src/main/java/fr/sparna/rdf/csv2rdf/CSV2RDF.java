package fr.sparna.rdf.csv2rdf;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class CSV2RDF {

	protected String base;
	private int subjectCount, tripleCount;
	
	public CSV2RDF(String base) {
		super();
		this.base = base;
	}
	
	public Model convert(InputStream in) throws IOException {
		Model tableModel = ModelFactory.createDefaultModel();
		
		CSVParser parser = CSVParser.parse(new InputStreamReader(in), CSVFormat.DEFAULT.withFirstRecordAsHeader());
		List<String> headers = parser.getHeaderNames();
		List<Property> properties = headers.stream().map(s -> {
			try {
				return tableModel.createProperty( this.base + URLEncoder.encode(s.replace(" ", "_"), "UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
		for (CSVRecord record : parser.getRecords()) {
			Resource subject = tableModel.createResource();
			subjectCount++;
			for(int i=0; i < headers.size(); i++) {
				if(record.isSet(i)) {
					subject.addProperty(properties.get(i), record.get(i));
					tripleCount++;
				}
			}
		}
		
		return tableModel;
	}

	public int getSubjectCount() {
		return subjectCount;
	}

	public int getTripleCount() {
		return tripleCount;
	}

	public static void main(String...strings) throws Exception {
		String input = "id,first name,last name"+"\n"+"1,Thomas,Francart"+"\n"+"2,Jorge,Aquino";
		CSV2RDF csv2rdf = new CSV2RDF("http://sparna.fr/");
		InputStream in = new ByteArrayInputStream(input.getBytes());
		Model m = csv2rdf.convert(in);
		m.write(System.out, "Turtle");
	}
	
}

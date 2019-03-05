/*
 This class converts an object of type Records in xml format.
 */
package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.data.model.Record;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class XmlRecordsRepresentation extends WriterRepresentation {

    private final List<Record> records;

    public XmlRecordsRepresentation(List<Record> records) {
        super(MediaType.APPLICATION_XML);
        this.records = records;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Prices", List.class);
    	writer.write(xstream.toXML(records));
    }
}
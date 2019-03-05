/*
 * This class converts an object of type Record in xml format.
 */
package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Record;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.Writer;

public class XmlRecordRepresentation extends WriterRepresentation {

    private final Record record;

    public XmlRecordRepresentation(Record record) {
        super(MediaType.APPLICATION_XML);
        this.record = record;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Price", Record.class);
    	writer.write(xstream.toXML(record));
    }
}
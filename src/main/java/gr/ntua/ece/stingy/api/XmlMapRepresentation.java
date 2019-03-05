/*
 * This class converts a Map in a xml format.
 */
package gr.ntua.ece.stingy.api;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class XmlMapRepresentation extends WriterRepresentation {

    private final Map map;

    public XmlMapRepresentation(Map map) {
        super(MediaType.APPLICATION_XML);
        this.map = map;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Map", Map.class);
    	writer.write(xstream.toXML(map));

    }
}

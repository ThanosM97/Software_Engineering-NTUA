/*
 * This class converts a Message in a xml format.
 */
package gr.ntua.ece.stingy.api;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import gr.ntua.ece.stingy.data.model.Message;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

public class XmlMessageRepresentation extends WriterRepresentation {

    private final Message message;

    public XmlMessageRepresentation(Message message) {
        super(MediaType.APPLICATION_XML);
        this.message = message;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Message", Message.class);
    	writer.write(xstream.toXML(message));

    }
}

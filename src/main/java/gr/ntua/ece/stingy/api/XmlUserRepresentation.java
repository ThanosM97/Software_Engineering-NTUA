/*
 * This class converts an object of type User in xml format.
 */

package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.data.model.Record;
import gr.ntua.ece.stingy.data.model.User;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.Writer;

public class XmlUserRepresentation extends WriterRepresentation {

    private final User user;

    public XmlUserRepresentation(User user) {
        super(MediaType.APPLICATION_XML);
        this.user = user;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("User", User.class);
    	writer.write(xstream.toXML(user));
    }
}

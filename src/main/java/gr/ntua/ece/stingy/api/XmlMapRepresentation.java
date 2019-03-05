/*
 * This class converts a Map in a xml format.
 */
package gr.ntua.ece.stingy.api;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import gr.ntua.ece.stingy.data.model.Shop;
import gr.ntua.ece.stingy.data.model.Message;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Record;
import gr.ntua.ece.stingy.data.model.User;

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
    	xstream.alias("Product", Product.class);
    	xstream.alias("Shop", Shop.class);
    	xstream.alias("Record", Record.class);
    	xstream.alias("User", User.class);
    	xstream.alias("Message", Message.class);

    	writer.write(xstream.toXML(map));

    }
}

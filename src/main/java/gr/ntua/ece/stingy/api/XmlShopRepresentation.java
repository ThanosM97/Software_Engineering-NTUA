/*
 * This class converts an object of type Shop in xml format.
 */

package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.data.model.Record;
import gr.ntua.ece.stingy.data.model.Shop;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import java.io.IOException;
import java.io.Writer;

public class XmlShopRepresentation extends WriterRepresentation {

    private final Shop shop;

    public XmlShopRepresentation(Shop shop) {
        super(MediaType.APPLICATION_XML);
        this.shop = shop;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Shop", Shop.class);
    	writer.write(xstream.toXML(shop));
    }
}

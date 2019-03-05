/*
 * This class converts a Product in a xml format.
 */
package gr.ntua.ece.stingy.api;

import java.io.IOException;
import java.io.Writer;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import com.thoughtworks.xstream.XStream;

import gr.ntua.ece.stingy.data.model.Product;

public class XmlProductRepresentation extends WriterRepresentation {

    private final Product product;

    public XmlProductRepresentation(Product product) {
        super(MediaType.APPLICATION_XML);
        this.product = product;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	XStream xstream = new XStream();
    	xstream.alias("Product", Product.class);
    	writer.write(xstream.toXML(product));
    }
}

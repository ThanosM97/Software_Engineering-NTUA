/*
 * This class converts a Product in a xml format.
 */
package gr.ntua.ece.stingy.api;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import gr.ntua.ece.stingy.data.model.Product;

public class XmlProductRepresentation extends WriterRepresentation {

    private final Product product;

    public XmlProductRepresentation(Product product) {
        super(MediaType.APPLICATION_JSON);
        this.product = product;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	try {
    		
    		/*
    		 * Create JAXB Context
    		 */
            JAXBContext jaxbContext = JAXBContext.newInstance(Product.class);
             
            /*
             * Create Marshaller
             */
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
             jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
              
            /*
             * Write XML to Writer
             */
            jaxbMarshaller.marshal(product, writer);	    	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}

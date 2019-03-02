/*
 * This class converts a Map in a xml format.
 */
package gr.ntua.ece.stingy.api;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.ProductsMap;


public class XmlMapRepresentation extends WriterRepresentation {

    private final ProductsMap map;

    public XmlMapRepresentation(ProductsMap map) {
        super(MediaType.APPLICATION_XML);
        this.map = map;
    }

    @Override
    public void write(Writer writer) throws IOException {
    	try {
    		
    		/*
    		 * Create JAXB Context
    		 */
            JAXBContext jaxbContext = JAXBContext.newInstance(ProductsMap.class);
             
            /*
             * Create Marshaller
             */
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
             jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
              
            /*
             * Write XML to Writer
             */
            jaxbMarshaller.marshal(map, writer);	    	
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}


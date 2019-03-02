package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Message;

import org.restlet.data.Form;
import org.restlet.data.Header;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Optional;

public class LogoutResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    
    @Override
	protected Representation post(Representation entity) throws ResourceException {
    	Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	Message message = dataAccess.tokenIsValid(auth);
        /*
        * Return message.
        */
        return new JsonMessageRepresentation(message);
    }
}
    

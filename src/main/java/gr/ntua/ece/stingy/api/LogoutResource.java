package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.Message;

import org.restlet.data.Header;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

public class LogoutResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    
    @Override
	protected Representation post(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	/*
    	 * Check if it is valid and disable it.
    	 */
    	Message message = dataAccess.tokenIsValid(auth);
        /*
        * Return 'OK' message.
        */
        return new JsonMessageRepresentation(message);
    }
}
    

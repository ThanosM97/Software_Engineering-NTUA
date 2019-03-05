package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.User;

import org.restlet.data.Form;
import org.restlet.data.Header;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import java.util.Optional;

public class UserResource extends ServerResource {

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
         * Create a new restlet form
         */
        Form form = new Form(entity);

        /*
         * Read the parameters
         */
        String username = form.getFirstValue("username");
        
        if (username == null || username.isEmpty()){
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing username");
        }
        if (!dataAccess.isAdmin(auth) && !dataAccess.isUser(auth)) {
            throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "You are not authorized for this information");
        }
        Form queryParams = getQuery();
    	String format = queryParams.getFirstValue("format");
    	if (format == null || format.isEmpty()) {
    		format = "json";
    	}
        
        Optional<User> optional = dataAccess.getUserByUsername(username);
        User user = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "User not found - username: " + username));
        /*
         * Return message.
         */
        if (format.equals("xml")) {
        	return new XmlUserRepresentation(user);
        }
        else {
        	return new JsonUserRepresentation(user);
        }
    }
    
}

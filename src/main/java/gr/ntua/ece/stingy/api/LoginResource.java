package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.Map;

public class LoginResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
	protected Representation post(Representation entity) throws ResourceException {
    	/*
		 * Create a new restlet form
		 */	
		Form form = new Form(entity);
		/*
		 * Read the parameters from the body of the form.
		 */
		String username = form.getFirstValue("username");
		String password = form.getFirstValue("password");
		
		/*
		 *  Validate the values (in the general case)
		 */
		if (username == null || username.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Username is required");
		}
		if (password == null || password.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Password is required");
		}
		
		/*
		 * Add requested product in the database if user exists.
		 */
		
		String token = dataAccess.getToken(username, password);
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		/*
		 * Return token in json representation.
		 */
		return new JsonMapRepresentation(map);
    }
}
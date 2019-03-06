package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;

import org.restlet.data.Form;
import org.restlet.data.Header;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A class implementing get and post methods of /products URI.
 */

public class RegisterResource extends ServerResource {

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
		String firstName = form.getFirstValue("firstName");
		String lastName = form.getFirstValue("lastName");
		String password = form.getFirstValue("password");
		String email = form.getFirstValue("email");
		String phoneNumber = form.getFirstValue("phoneNumber");
		
		/*
		 *  Validate the values (in the general case)
		 */
		if (username == null || username.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Username is required");
		}
		if (email == null || email.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Email is required");
		}
		if (password == null || password.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Password is required");
		}
		
		/*
		 * Add requested user in the database.
		 */
		String token = dataAccess.addUser(username, firstName, lastName, email, password, phoneNumber );
		
		Map<String, String> map = new HashMap<>();
		map.put("token", token);
		return new JsonMapRepresentation(map);
	}
	
}
	





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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A class implementing get and post methods of /products URI.
 */

public class CommentsResource extends ServerResource {

	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	@Override
	protected Representation post(Representation entity) throws ResourceException {
		/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	
    	if (!dataAccess.isUser(auth) && !dataAccess.isAdmin(auth)) {
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can comment on products");
    	}
		/*
		 * Create a new restlet form
		 */	
		Form form = new Form(entity);
		/*
		 * Read the parameters from the body of the form.
		 */
		String text = form.getFirstValue("text");
		String userId = form.getFirstValue("userId");
		String productId = form.getFirstValue("productid");
		
		/*
		 *  Validate the values (in the general case)
		 */
		if (text == null || text.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Text is required");
		}
		if (userId == null || userId.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "User Id is required");
		}
		if (productId == null || productId.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Product Id is required");
		}
		
		/*
		 * Add requested comment in the database.
		 */
		//Comment comment = dataAccess.addComment(text, userId, productId);
		//return new JsonCommentRepresentation(commment);
		return null;
		
	}
}
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

public class ProductResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");
        Form queryParams = getQuery();
		String format = queryParams.getFirstValue("format");        /*
         * Check if given id is not null.
         */
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }
        if (format == null) {
        	format = "json";
        }
        /*
         * Convert given id tos long.
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }
        /*
         * Get product based on the given id.
         */
        Optional<Product> optional = dataAccess.getProduct(id);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        if (format == "json") {
        	return new JsonProductRepresentation(product);
        }
        else {
        	return new XmlProductRepresentation(product);
        }
    }

    @Override
    protected Representation delete() throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	
    	if (!dataAccess.isUser(auth) && !dataAccess.isAdmin(auth)) {
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can delete products");
    	}
    	
        String idAttr = getAttribute("id");
        /*
         * Check if given id is null.
         */
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }
        /*
         * Convert given id to long.
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }
        if (dataAccess.isAdmin(auth)) {
	        /*
	         * Delete product based on the id.
	         */
	        Optional<Message> optional = dataAccess.deleteProduct(id);
	        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
	        /*
	         * Return message.
	         */
	        return new JsonMessageRepresentation(message);
        }
	    else {
	    	/*
	         * Change withdrawn product based on the id.
	         */
	        Optional<Message> optional = dataAccess.withdrawnProduct(id);
	        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
	        /*
	         * Return message.
	         */
	        return new JsonMessageRepresentation(message);
	    }
    }
    
    @Override
    protected Representation put(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	
    	if (!dataAccess.isUser(auth) && !dataAccess.isAdmin(auth)) {
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can update products");
    	}
    	
        /*
         * Get the product id.
         */
    	String idAttr = getAttribute("id");
    	/*
    	 * Check if it is valid 
    	 */
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }
        /*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the parameters
         */
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        String category = form.getFirstValue("category");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        String tagsString = form.getFirstValue("tags");
        String extraDataString = form.getFirstValue("extraData");
        
        /*
		 *  Validate the values (in the general case)
		 */
		if (name == null || name.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Name is required");
		}
		if (description == null || description.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Description is required");
		}
		if (category == null || category.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Category is required");
		}
		if (tagsString == null || tagsString.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Tags are required");
		}
		
        /*
		 * Convert tagString that represents a list of tags to a list.
		 */
		ArrayList<String> tags = new Gson().fromJson(tagsString, ArrayList.class);
		
        /*
         * Update the certain product
         */
        Optional<Product> optional = dataAccess.updateProduct(id, name, description, category, withdrawn, tags, extraDataString);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        return new JsonProductRepresentation(product);
    }
    
    @Override
    protected Representation patch(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	
    	if (!dataAccess.isUser(auth) && !dataAccess.isAdmin(auth)) {
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can update products");
    	}
    	/*
         * Get the product id and check if it is valid 
         */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid product id: " + idAttr);
        }
        /*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the parameters
         */
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        String category = form.getFirstValue("category");
        String withdrawn = form.getFirstValue("withdrawn");
        String tagsString = form.getFirstValue("tags");
        String extraDataString = form.getFirstValue("extraData");
        /*
         * patch the certain product based on the non null value.
         * If more than two values are given only the first is updated.
         * That is because patch request should update only one value.
         * If more changes are required use put instead.
         */
        Optional<Product> optional = null;
        if (name != null) {
            optional = dataAccess.patchProduct(id, name, "name");
    	}
    	else if (description != null) {
    		System.out.println("asa");
            optional = dataAccess.patchProduct(id, description, "description");
    	}
    	else if (category != null) {
            optional = dataAccess.patchProduct(id, category, "category");
    	}
    	else if (withdrawn != null) {
            optional = dataAccess.patchProduct(id, withdrawn, "withdrawn");
    	}
    	else if (tagsString != null) {
            optional = dataAccess.patchProduct(id, tagsString, "tags");
    	}
    	else if (extraDataString != null) {
            optional = dataAccess.patchProduct(id, extraDataString, "extraData");
    	}
    	else {
    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
    	}
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        return new JsonProductRepresentation(product);
    }
}

package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.Shop;
import gr.ntua.ece.stingy.data.model.Message;
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
import java.util.Optional;

public class ShopResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {
    	/*
    	 * Get given id and check its validity.
    	 */
        String idAttr = getAttribute("id");
        Form queryParams = getQuery();
    	String format = queryParams.getFirstValue("format");
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }
        /*
         * Convert it to long.
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        if (format == null || format.isEmpty()) {
        	format = "json";
        }
        
        /*
         * Get shop based on the given id.
         */
        Optional<Shop> optional = dataAccess.getShop(id);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
        /*
         * Return the json representation of the shop.
         */
        if (format.equals("xml")) {
        	return new XmlShopRepresentation(shop);
        }
        else {
        	return new JsonShopRepresentation(shop);
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
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can delete shops");
    	}
    	/*
    	 * Get given id and check its validity.
    	 */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }
        /*
         * Convert it to long.
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        if (dataAccess.isAdmin(auth)) {
        	/*
             * Delete shop based on the given id and return 'OK' message.
             */
            Optional<Message> optional = dataAccess.deleteShop(id);
            Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
            return new JsonMessageRepresentation(message);
        }
	    else {
	    	/*
	         * Change withdrawn shop based on the id.
	         */
	        Optional<Message> optional = dataAccess.withdrawnShop(id);
	        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
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
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can update shops");
    	}
        /*
         * Get the shop id and check if it is valid 
         */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }
        /*
         * Convert given id to long.
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        /*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the parameters
         */
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        Double lng = Double.valueOf(form.getFirstValue("lng"));
        Double lat = Double.valueOf(form.getFirstValue("lat"));
		String[] tags = form.getValuesArray("tags");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        String image = form.getFirstValue("image");
        /*
         *  Validate the values (in the general case)
     	*/
     	if (name == null || name.isEmpty()) {
     		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Name is required");
     	}
     	if (address == null || address.isEmpty()) {
     		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Description is required");
     	}
     	if (lng == null || lat == null) {
     		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Longitude and latitude are required");
     	}
     	if (tags == null ) {
     		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Tags are required");
     	}

        /*
         * Update the certain shop
         */
        Optional<Shop> optional = dataAccess.updateShop(id, name, address, lng, lat, Arrays.asList(tags), withdrawn, image);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
        return new JsonShopRepresentation(shop);
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
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can update shops");
    	}
    	/*
         * Get the shop id and check if it is valid 
         */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }
        /*
         * Convert given id to long
         */
        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        /*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the parameters
         */
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        String lng = form.getFirstValue("lng");
        String lat = form.getFirstValue("lat");
		String[] tags = form.getValuesArray("tags");
        String withdrawn = form.getFirstValue("withdrawn");
        String image = form.getFirstValue("image");
        /*
         * patch the certain shop based on the non null value.
         * If more than two values are given only the first is updated.
         * That is because patch request should update only one value.
         * If more changes are required use put instead.
         */
        Optional<Shop> optional = null;
        if (name != null) {
            optional = dataAccess.patchShop(id, name, "name", null);
    	}
    	else if (address != null) {
            optional = dataAccess.patchShop(id, address, "address", null);
    	}
    	else if (lng != null) {
            optional = dataAccess.patchShop(id, lng, "lng", null);
    	}
    	else if (lat != null) {
            optional = dataAccess.patchShop(id, lat, "lat", null);
    	}
    	else if (tags != null) {
            optional = dataAccess.patchShop(id, null, "tags", Arrays.asList(tags));
    	}
    	else if (withdrawn != null) {
            optional = dataAccess.patchShop(id, withdrawn, "withdrawn", null);
    	}
    	else if (image != null) {
            optional = dataAccess.patchShop(id, image, "image", null);
    	}
    	else {
    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
    	}
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
        return new JsonShopRepresentation(shop);
    }
}


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

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * A class implementing get, put, patch and delete methods of /products/{id} URI.
 */

public class ProductResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");
        Form queryParams = getQuery();
		String format = queryParams.getFirstValue("format");  
		String shops = queryParams.getFirstValue("shops");
		if (format == null || format.isEmpty()) {
        	format = "json";
        }
		/*
         * Check if given id is not null.
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
        
        if (shops != null) {
        	return new JsonShopsRepresentation(dataAccess.getShopsByProductId(id));
        }
        /*
         * Get product based on the given id and return its representations based on the desired format.
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
    	
    	Form queryParams = getQuery();
 		String format = queryParams.getFirstValue("format");     
        String idAttr = getAttribute("id");
        if (format == null || format.isEmpty()) {
        	format = "json";
        }
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
	         * Return message in the desired format..
	         */
	        if (format.equals("xml")) {
		        return new XmlMessageRepresentation(message);
	        }
	        else {
		        return new JsonMessageRepresentation(message);	
	        }
        }
	    else {
	    	/*
	         * Change withdrawn product based on the id.
	         */
	        Optional<Message> optional = dataAccess.withdrawnProduct(id);
	        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
	        /*
	         * Return message in the desired format.
	         */
	        if (format.equals("xml")) {
		        return new XmlMessageRepresentation(message);
	        }
	        else {
		        return new JsonMessageRepresentation(message);	
	        }	    }
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
    	Form queryParams = getQuery();
 		String format = queryParams.getFirstValue("format"); 
 		
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
		String[] tags = form.getValuesArray("tags");

		
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
		if (tags == null ) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Tags are required");
		}
		if (format == null || format.isEmpty()) {
			format = "json";
		}

		Map<String, String> extraData = new HashMap<>();
		if (category.equals("laptop")) {
			/*
			 * Extra data supported for Laptops
			 */
			extraData.put("CPU", form.getFirstValue("CPU"));
			extraData.put("CPUcores", form.getFirstValue("CPUcores"));
			extraData.put("RAM", form.getFirstValue("RAM"));
			extraData.put("HardDrive", form.getFirstValue("HardDrive"));
			extraData.put("OS",form.getFirstValue("OS"));
			extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
			extraData.put("GraphicsCard", form.getFirstValue("GraphicsCard"));
		}
		else if (category.equals("tv")) {
			/*
			 * Extra data supported for TVs
			 */
			extraData.put("Smart", form.getFirstValue("Smart"));			
			extraData.put("Resolution", form.getFirstValue("Resolution"));
			extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
		}
		else if (category.equals("smartphone")) {
			/*
			 * Extra data supported for Smartphones
			 */
			extraData.put("CPUcores", form.getFirstValue("CPUcores"));
			extraData.put("RAM", form.getFirstValue("RAM"));
			extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
			extraData.put("Capacity", form.getFirstValue("Capacity"));
			extraData.put("FrontCamera", form.getFirstValue("FrontCamera"));
			extraData.put("SelfieCamera", form.getFirstValue("SelfieCamera"));
			extraData.put("OS", form.getFirstValue("OS"));
		}
		else if (category.equals("tablet")) {
			/*
			 * Extra data supported for Tablets
			 */
			extraData.put("ScreenSize", form.getFirstValue("ScrenSize"));
			extraData.put("RAM", form.getFirstValue("RAM"));
			extraData.put("OS", form.getFirstValue("OS"));
			extraData.put("HardDrive", form.getFirstValue("HardDrive"));
		}
		else if (category.equals("monitor")) {
			/*
			 * Extra data supported for TVs
			 */
			extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
			extraData.put("Resolution", form.getFirstValue("Resolution"));	
		}

        /*
         * Update the certain product and return its representation.
         */
        Optional<Product> optional = dataAccess.updateProduct(id, name, description, category, withdrawn, Arrays.asList(tags), extraData);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        if (format.equals("xml")){
        	return new XmlProductRepresentation(product);
        }
        else {
        	return new JsonProductRepresentation(product);
        }
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
    	Form queryParams = getQuery();
 		String format = queryParams.getFirstValue("format"); 
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
		String[] tags = form.getValuesArray("tags");
		Map<String, String> extraData = new HashMap<>();
		if (category!=null) {
			if (category.equals("laptop")) {
				/*
				 * Extra data supported for Laptops
				 */
				extraData.put("CPU", form.getFirstValue("CPU"));
				extraData.put("CPUcores", form.getFirstValue("CPUcores"));
				extraData.put("RAM", form.getFirstValue("RAM"));
				extraData.put("HardDrive", form.getFirstValue("HardDrive"));
				extraData.put("OS",form.getFirstValue("OS"));
				extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
				extraData.put("GraphicsCard", form.getFirstValue("GraphicsCard"));
			}
			else if (category.equals("tv")) {
				/*
				 * Extra data supported for TVs
				 */
				extraData.put("Smart", form.getFirstValue("Smart"));			
				extraData.put("Resolution", form.getFirstValue("Resolution"));
				extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
			}
			else if (category.equals("smartphone")) {
				/*
				 * Extra data supported for Smartphones
				 */
				extraData.put("CPUcores", form.getFirstValue("CPUcores"));
				extraData.put("RAM", form.getFirstValue("RAM"));
				extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
				extraData.put("Capacity", form.getFirstValue("Capacity"));
				extraData.put("FrontCamera", form.getFirstValue("FrontCamera"));
				extraData.put("SelfieCamera", form.getFirstValue("SelfieCamera"));
				extraData.put("OS", form.getFirstValue("OS"));
			}
			else if (category.equals("tablet")) {
				/*
				 * Extra data supported for Tablets
				 */
				extraData.put("ScreenSize", form.getFirstValue("ScrenSize"));
				extraData.put("RAM", form.getFirstValue("RAM"));
				extraData.put("OS", form.getFirstValue("OS"));
				extraData.put("HardDrive", form.getFirstValue("HardDrive"));
			}
			else if (category.equals("monitor")) {
				/*
				 * Extra data supported for TVs
				 */
				extraData.put("ScreenSize", form.getFirstValue("ScreenSize"));
				extraData.put("Resolution", form.getFirstValue("Resolution"));	
			}
		}
		/*
         * patch the certain product based on the non null value.
         * If more than two values are given only the first is updated.
         * That is because patch request should update only one value.
         * If more changes are required use put instead.
         */
		boolean empty = true;
		for(String key : extraData.keySet()) {
			if (extraData.get(key)!= null || extraData.get(key).length()!=0) {
				empty = false;
			}
		}
		
        Optional<Product> optional = null;
        if (name != null) {
            optional = dataAccess.patchProduct(id, name, "name", null);
    	}
    	else if (description != null) {
            optional = dataAccess.patchProduct(id, description, "description", null);
    	}
    	else if (category != null) {
            optional = dataAccess.patchProduct(id, category, "category", null);
    	}
    	else if (withdrawn != null) {
            optional = dataAccess.patchProduct(id, withdrawn, "withdrawn", null);
    	}
    	else if (tags != null) {
            optional = dataAccess.patchProduct(id, null, "tags", Arrays.asList(tags));
    	}
    	else if (!empty) {
            optional = dataAccess.updateExtraData(id, extraData);
    	}
    	else {
    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
    	}
        
        if (format == null || format.isEmpty()) {
			format = "json";
		}
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        if (format.equals("xml")){
        	return new XmlProductRepresentation(product);
        }
        else {
        	return new JsonProductRepresentation(product);
        }
    }
}

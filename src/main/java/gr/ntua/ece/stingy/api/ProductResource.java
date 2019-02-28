package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Message;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Optional;

public class ProductResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");
        /*
         * Check if given id is not null.
         */
        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing product id");
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

        return new JsonProductRepresentation(product);
    }

    @Override
    protected Representation delete() throws ResourceException {
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

        Optional<Message> optional = dataAccess.deleteProduct(id);
        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));

        return new JsonMessageRepresentation(message);
    }
    
    @Override
    protected Representation put(Representation entity) throws ResourceException {
        /*
         * get the product id and check if it is valid 
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
        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String description = form.getFirstValue("description");
        String category = form.getFirstValue("category");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        String tagsString = form.getFirstValue("tags");
        String extraDataString = form.getFirstValue("extraData");
        /*
         * update the certain product
         */
        Optional<Product> optional = dataAccess.updateProduct(id, name, description, category, withdrawn, tagsString, extraDataString);
        Product product = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + idAttr));
        return new JsonProductRepresentation(product);
    }
    
    @Override
    protected Representation patch(Representation entity) throws ResourceException {
    	/*
         * get the product id and check if it is valid 
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
        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
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

package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.model.Shop;
import gr.ntua.ece.stingy.data.model.Message;
import gr.ntua.ece.stingy.data.model.Product;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.Optional;

public class ShopResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    protected Representation get() throws ResourceException {

        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        Optional<Shop> optional = dataAccess.getShop(id);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));

        return new JsonShopRepresentation(shop);
    }
    
    @Override
    protected Representation delete() throws ResourceException {
        String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }

        Optional<Message> optional = dataAccess.deleteShop(id);
        Message message = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));

        return new JsonMessageRepresentation(message);
    }
    
    @Override
    protected Representation put(Representation entity) throws ResourceException {
        /*
         * get the shop id and check if it is valid 
         */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        double lng = Double.valueOf(form.getFirstValue("lng"));
        double lat = Double.valueOf(form.getFirstValue("lat"));
        String tags = form.getFirstValue("tags");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));
        /*
         * update the certain product
         */
        Optional<Shop> optional = dataAccess.updateShop(id, name, address, lng, lat, tags, withdrawn);
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
        return new JsonShopRepresentation(shop);
    }
    
    @Override
    protected Representation patch(Representation entity) throws ResourceException {
    	/*
         * get the shop id and check if it is valid 
         */
    	String idAttr = getAttribute("id");

        if (idAttr == null) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Missing shop id");
        }

        Long id = null;
        try {
            id = Long.parseLong(idAttr);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shop id: " + idAttr);
        }
        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        String lng = form.getFirstValue("lng");
        String lat = form.getFirstValue("lat");
        String tags = form.getFirstValue("tags");
        String withdrawn = form.getFirstValue("withdrawn");
        /*
         * patch the certain shop based on the non null value.
         * If more than two values are given only the first is updated.
         * That is because patch request should update only one value.
         * If more changes are required use put instead.
         */
        Optional<Shop> optional = null;
        if (name != null) {
            optional = dataAccess.patchShop(id, name, "name");
    	}
    	else if (address != null) {
            optional = dataAccess.patchShop(id, address, "address");
    	}
    	else if (lng != null) {
            optional = dataAccess.patchShop(id, lng, "lng");
    	}
    	else if (lat != null) {
            optional = dataAccess.patchShop(id, lat, "lat");
    	}
    	else if (tags != null) {
            optional = dataAccess.patchShop(id, tags, "tags");
    	}
    	else if (withdrawn != null) {
            optional = dataAccess.patchShop(id, withdrawn, "withdrawn");
    	}
    	else {
    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "None field changed");
    	}
        Shop shop = optional.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + idAttr));
        return new JsonShopRepresentation(shop);
    }
}


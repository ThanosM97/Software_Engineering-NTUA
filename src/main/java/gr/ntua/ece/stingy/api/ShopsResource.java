package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Shop;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopsResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    
    @Override
    protected Representation get() throws ResourceException {
	    /**
	     * Get parameters of the get method.
	     */
	    Form queryParams = getQuery();
	    String startString = queryParams.getFirstValue("start");
	    String countString = queryParams.getFirstValue("count");
	    String status = queryParams.getFirstValue("status");
	    String sort = queryParams.getFirstValue("sort");
	
	    Map<String, Object> map = new HashMap<>();
	    Limits limits = new Limits();
	    /**
	     * If a parameter exists and is valid put it in the map.
	     */
	    if (startString != null ) {
	    	Long start = null;
	    	try {
	    		start = Long.parseLong(startString);
	    	}
	    	catch(Exception e) {
	    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid start: " + startString);
	    	}
	    	limits.setStart(start);
	    	map.put("start", start);
	    } else {
	    	/*
	    	 * default value for start is 0.
	    	 */
	    	map.put("start", 0);
	    }
	    if (countString != null ) {
	    	Long count = null;
	    	try {
	    		count = Long.parseLong(countString);
	    	}
	    	catch(Exception e) {
	    		throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid count: " + countString);
	    	}
	    	limits.setCount(count);
	    	map.put("count", count);
	    } else{
	    	/*
	    	 * default value for count is 20.
	    	 */
	    	map.put("count", 20);
	    }
	    /*
	     * get shops based on the limits.
	     */
	    /*
	     * set default values for status and sort
	     */
	    if (status == null) {
	    	status = "ACTIVE";
	    }
	    if (sort == null) {
	    	sort = "id|DESC";
	    }
	    if (!status.equals("ALL") && !status.equals("WITHDRAWN") && !status.equals("ACTIVE")) {
	    	throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid status: " + status);
	    }
	    System.out.println(sort);
	    if (!sort.equals("id|ASC") && !sort.equals("id|DESC") && !sort.equals("name|ASC") && !sort.equals("name|DESC")) {
	    	throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort: " + sort);
	    }
	    List<Shop> shops = dataAccess.getShops(limits, status, sort);
	    /*
	     * set current total products.
	     */
	    map.put("total", limits.getTotal());
	    map.put("shops", shops);
	    return new JsonMapRepresentation(map);
	}

    @Override
    protected Representation post(Representation entity) throws ResourceException {

        //Create a new restlet form
        Form form = new Form(entity);
        //Read the parameters
        String name = form.getFirstValue("name");
        String address = form.getFirstValue("address");
        double lng = Double.valueOf(form.getFirstValue("lng"));
        double lat = Double.valueOf(form.getFirstValue("lat"));
        String tags = form.getFirstValue("tags");
        boolean withdrawn = Boolean.valueOf(form.getFirstValue("withdrawn"));

        //validate the values (in the general case)
        //...

        Shop shop = dataAccess.addShop(name, address, lng, lat, tags, withdrawn);

        return new JsonShopRepresentation(shop);
    }
}



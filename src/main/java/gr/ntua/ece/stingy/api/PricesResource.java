package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Record;
import gr.ntua.ece.stingy.data.model.Shop;

import org.restlet.data.Form;
import org.restlet.data.Header;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.restlet.util.Series;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PricesResource extends ServerResource {

    private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();


    @Override
    protected Representation get() throws ResourceException {
    	/**
    	 * Get parameters of the get method.
    	 */
    	Form queryParams = getQuery();
    	String startString = queryParams.getFirstValue("start");
    	String countString = queryParams.getFirstValue("count");
    	String geoDistString = queryParams.getFirstValue("geoDist");
    	String geoLngString = queryParams.getFirstValue("geoLng");
    	String geoLatString = queryParams.getFirstValue("geoLat");
    	String dateFrom = queryParams.getFirstValue("dateFrom");
    	String dateTo = queryParams.getFirstValue("dateTo");
    	
    	String shops = queryParams.getValues("shops");
    	String products = queryParams.getValues("products");
		String[] tags = queryParams.getValuesArray("tags");
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
        	 * Default value for start is 0.
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
        	 * Default value for count is 20.
        	 */
        	map.put("count", 20);
        }
        /*
         * Set default values for sort
         */

        if (sort == null) {
        	sort = "price|ASC";
        }
        if (!sort.equals("price|ASC") && !sort.equals("price|DESC") && !sort.equals("date|ASC") && !sort.equals("date|DESC")
        		&& !sort.equals("geo.dist|ASC") && !sort.equals("geo.dist|DESC")) {
        	throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort: " + sort);
        }
        
        if (!(geoDistString == null && geoLngString == null && geoLatString == null) && !(geoDistString != null && geoLngString != null && geoLatString != null)){
        	throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Something is missing in geographical data ");
        }
        if ((dateFrom == null && dateTo != null) || (dateFrom != null && dateTo == null)) {
        	throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid date ");
        }
        if (dateFrom == null) {
        	Date date = new Date();
        	dateFrom = new SimpleDateFormat("yyyy-MM-dd").format(date);
        	dateTo = new SimpleDateFormat("yyyy-MM-dd").format(date);
        }
        
        /*
         * Get prices based on the limits.
         */
    	List<Record> records = dataAccess.getRecords(limits, geoDistString, geoLngString, geoLatString, dateFrom, dateTo, 
    			shops, products, Arrays.asList(tags),sort);
    	/*
    	 * Set current total products.
    	 */
        map.put("total", limits.getTotal());
        map.put("prices", records);
        return new JsonMapRepresentation(map);
    }

    
    @Override
    protected Representation post(Representation entity) throws ResourceException {
    	/*
    	 * Get  token from headers
    	 */
    	@SuppressWarnings("unchecked")
		Series<Header> headers = (Series<Header>) getRequestAttributes().get("org.restlet.http.headers");
    	String auth = headers.getFirstValue("X-OBSERVATORY-AUTH");
    	
    	if (!dataAccess.isUser(auth) && !dataAccess.isAdmin(auth)) {
			throw new ResourceException(Status.CLIENT_ERROR_FORBIDDEN, "Only users and administrators can create new records");
    	}
    	/*
         * Create a new restlet form
         */
        Form form = new Form(entity);
        /*
         * Read the parameters
         */
        Double price = Double.valueOf(form.getFirstValue("price"));
        String dateFrom = form.getFirstValue("dateFrom");
        String dateTo = form.getFirstValue("dateTo");
        String productIdString = form.getFirstValue("productId");
        String shopIdString = form.getFirstValue("shopId");
        String userIdString = form.getFirstValue("userId");
        String validityString = form.getFirstValue("validity");
        
        
        /*
		 *  Validate the values (in the general case)
		 */
		if (price == null) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Price is required");
		}
		if (productIdString == null || productIdString.isEmpty()) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Product Id is required");
		}
		if (shopIdString == null || shopIdString == null) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Shop Id is required");
		}
		/*
		 * Set 1 as default value for user that corresponds to the administrator.
		 */
		Long userId = null;
		if (userIdString != null ) {
        	try {
                userId = Long.parseLong(userIdString);
            }
            catch(Exception e) {
                throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid userId: " + userId);
            }
        } else{
        	/*
        	 * Default value for userId is 1 (administrator).
        	 */
        	userId = (long) 1;
        }
		/*
		 * Set 0 as default value for validity.
		 */
		int validity;
		if (validityString == null || validityString.isEmpty()) {
			validity = 0;
		}
		else {
			validity = Integer.parseInt(validityString);
		}
		Long productId;
		Long shopId;
		try {
            productId = Long.parseLong(productIdString);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid productId: " + productIdString);
        }
		try {
            shopId = Long.parseLong(shopIdString);
        }
        catch(Exception e) {
            throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid shopId: " + shopIdString);
        }
		
		List<Record> records = dataAccess.addRecord(price, dateFrom, dateTo, productId, shopId, userId, validity);
        /*
         * Return the json representation of the record.
         */
        return new JsonRecordsRepresentation(records);
       
    
    }
 
}
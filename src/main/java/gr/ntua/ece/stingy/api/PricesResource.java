package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Record;
import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;

import java.text.SimpleDateFormat;
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
    	String tags = queryParams.getValues("tags");
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
         * get prices based on the limits.
         */
        /*
         * set default values for sort
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
        System.out.println(geoLngString +  geoLatString + dateFrom + dateTo + shops + products + tags + sort);
        
        
    	List<Record> records = dataAccess.getRecords(limits, geoDistString, geoLngString, geoLatString, dateFrom, dateTo, 
    			shops, products, tags ,sort);
    	/*
    	 * set current total products.
    	 */
        map.put("total", limits.getTotal());
        map.put("prices", records);
        return new JsonMapRepresentation(map);
    }



}
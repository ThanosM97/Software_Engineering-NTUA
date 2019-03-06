package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;

import org.restlet.data.Form;
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

public class TrendsResource extends ServerResource {

	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	@Override
	protected Representation get() throws ResourceException {
		/**
		 * Get parameters of the get method.
		 */
		Form queryParams = getQuery();
		String startString = queryParams.getFirstValue("start");
		String countString = queryParams.getFirstValue("count");
		
		Map<String, Object> map = new HashMap<>();
		Limits limits = new Limits();
		/**
		 * If a parameter exists and is valid put it in the map. Otherwise, use default value.
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
		} 
		else {
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
		} 
		else {
			map.put("count", 5);
		}
		
		/*
		 * Get trends based on the limits.
		 */
		//<Trend> trends;
		//trends = dataAccess.getTrends(limits);
		return null;
	}
}
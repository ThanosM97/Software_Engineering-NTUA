package gr.ntua.ece.stingy.api;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.DataAccess;
import gr.ntua.ece.stingy.data.Limits;
import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.ProductsMap;

import org.restlet.data.Form;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A class implementing get and post methods of /products URI.
 */

public class ProductsResource extends ServerResource {

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
		String format = queryParams.getFirstValue("format");
		
		Map<String, Object> map = new HashMap<>();
		ProductsMap map2 = new ProductsMap();
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
			map.put("count", 20);
		}
		
		/*
		 * Set default values for status and sort
		 */
		if (status == null) {
			status = "ACTIVE";
		}
		if (sort == null) {
			sort = "id|DESC";
		}
		if (format == null) {
			format = "json";
		}
		/*
		 * Check if status and sort are valid.
		 */
		if (!status.equals("ALL") && !status.equals("WITHDRAWN") && !status.equals("ACTIVE")) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid status: " + status);
		}
		if (!sort.equals("id|ASC") && !sort.equals("id|DESC") && !sort.equals("name|ASC") && !sort.equals("name|DESC")) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort: " + sort);
		}
		/*
		 * Get products based on the limits.
		 */
		List<Product> products = dataAccess.getProducts(limits, status, sort);
		/*
		 * Set current total products.
		 */
		map.put("total", limits.getTotal());
		map.put("products", products);
		map2.setProductdsMap(map);
		if (format == "json") {
			return new JsonMapRepresentation(map);
		}
		else {
			return new XmlMapRepresentation(map2);
		}
	}

	@Override
	protected Representation post(Representation entity) throws ResourceException {
		/*
		 * Create a new restlet form
		 */	
		Form form = new Form(entity);
		/*
		 * Read the parameters from the body of the form.
		 */
		String name = form.getFirstValue("name");
		String description = form.getFirstValue("description");
		String category = form.getFirstValue("category");
		String withdrawnString = form.getFirstValue("withdrawn");
		String tagsString = form.getFirstValue("tags");
		String extraDataString = form.getFirstValue("extraData");
		
		/*
		 * Convert tagString that represents a list of tags to a list.
		 */
		ArrayList<String> tags = new Gson().fromJson(tagsString, ArrayList.class);
		
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
		 * If withdrawn is not set, use default value.
		 */
		boolean withdrawn;
		if (withdrawnString == null) {
			withdrawn = false;
		}
		else {
			withdrawn = Boolean.valueOf(withdrawnString);
		}

		/*
		 * Add requested product in the database.
		 */
		Product product = dataAccess.addProduct(name, description, category, withdrawn, tags, extraDataString);
		return new JsonProductRepresentation(product);
	}
}

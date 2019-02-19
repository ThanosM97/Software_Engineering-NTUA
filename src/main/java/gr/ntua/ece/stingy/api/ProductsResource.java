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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * A class implementing get and post methods of /products URI
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
			 * else put default value for start (0).
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
			 * else put default value for count (20).
			 */
			map.put("count", 20);
		}
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
		if (!sort.equals("id|ASC") && !sort.equals("id|DESC") && !sort.equals("name|ASC") && !sort.equals("name|DESC")) {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid sort: " + sort);
		}
		/*
		 * get products based on the limits.
		 */
		List<Product> products = dataAccess.getProducts(limits, status, sort);
		/*
		 * set current total products.
		 */
		map.put("total", limits.getTotal());
		map.put("products", products);
		return new JsonMapRepresentation(map);
	}

	@Override
	protected Representation post(Representation entity) throws ResourceException {

		//Create a new restlet form
		
		Form form = new Form(entity);
	
		//Read the parameters
		String name = form.getFirstValue("name");
		String description = form.getFirstValue("description");
		String category = form.getFirstValue("category");
		String withdrawnString = form.getFirstValue("withdrawn");
		String tagsString = form.getFirstValue("tags");
		String extraDataString = form.getFirstValue("extraData");
		// Validate the values (in the general case)
		System.out.println(description);

		
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
		 * default value for withdrawn is false
		 */
		boolean withdrawn;
		if (withdrawnString == null) {
			withdrawn = false;
		}
		else {
			withdrawn = Boolean.valueOf(withdrawnString);
		}

		Product product = dataAccess.addProduct(name, description, category, withdrawn, tagsString, extraDataString);
		return new JsonProductRepresentation(product);
	}
}

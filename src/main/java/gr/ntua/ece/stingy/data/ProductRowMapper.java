package gr.ntua.ece.stingy.data;
/*
 * A class that converts the result of a query in the product table into a product object. 
 */

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.model.Product;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRowMapper implements RowMapper {
	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	@Override
	public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

		long id            = rs.getLong("id");
		String name        = rs.getString("name");
		String description = rs.getString("description");
		String category    = rs.getString("category");
		boolean withdrawn  = rs.getBoolean("withdrawn");

		/*
		 * Convert tags to a list of tags (tags are separated by comma).
		 */
		List<String> tags = dataAccess.getTagsById(id);
		Map<String, String> extraData = dataAccess.getExtraDataById(id); 
		return new Product(id, name, description, category, withdrawn, tags, extraData);
	}

}

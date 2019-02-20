package gr.ntua.ece.stingy.data;
/*
 * A class that converts the result of a query in the product table into a product object. 
 */

import gr.ntua.ece.stingy.data.model.Record;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordRowMapper implements RowMapper {

	@Override
	public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
	    
		double price            = rs.getDouble("price");
		long productId        = rs.getLong("productId");
		String productName = rs.getString("productName");
		String productTags    = rs.getString("productTags");
		long shopId        = rs.getLong("shopId");
		String shopName   = rs.getString("shopName");
		String shopTags   = rs.getString("shopTags");
		String shopAddress   = rs.getString("address");
		int shopDist	= rs.getInt("dist");
		Date date 		= rs.getDate("date");
		
	
		return new Record(price, productName, productId, productTags, shopId, shopName, shopTags, shopAddress, shopDist, date);
	}

}
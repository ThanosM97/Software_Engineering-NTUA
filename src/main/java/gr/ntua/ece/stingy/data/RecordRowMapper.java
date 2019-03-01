package gr.ntua.ece.stingy.data;
/*
 * A class that converts the result of a query in the product table into a product object. 
 */

import gr.ntua.ece.stingy.conf.Configuration;
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
	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	@Override
	public Record mapRow(ResultSet rs, int rowNum) throws SQLException {
	    
		long id				= rs.getLong("id");
		double price            = rs.getDouble("price");
		long productId        = rs.getLong("productId");
		String productName = rs.getString("productName");
		long shopId        = rs.getLong("shopId");
		String shopName   = rs.getString("shopName");
		String shopAddress   = rs.getString("address");
		int shopDist	= rs.getInt("dist");
		Date dateFrom 		= rs.getDate("dateFrom");
		Date dateTo 		= rs.getDate("dateTo");		
		
		List<String> shopTags = dataAccess.getShopTagsById(shopId);
		List<String> productTags = dataAccess.getProductTagsById(productId);		
		return new Record(id, price, productName, productId, productTags, shopId, shopName, shopTags, shopAddress, shopDist, dateFrom, dateTo);
	}

}
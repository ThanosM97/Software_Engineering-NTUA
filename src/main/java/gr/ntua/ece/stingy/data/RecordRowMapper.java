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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
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
	    
		double price            = rs.getDouble("price");
		String productId        = rs.getString("productId");
		String productName = rs.getString("productName");
		String shopId        = rs.getString("shopId");
		String shopName   = rs.getString("shopName");
		String shopAddress   = rs.getString("address");
		Double shopDist	= rs.getDouble("dist");
		Date dateDate 		= rs.getDate("date");
		String pattern = "yyyy-MM-dd";
		Double lng = rs.getDouble("lng");
		Double lat = rs.getDouble("lat");
		DateFormat df = new SimpleDateFormat(pattern);

		String date = df.format(dateDate);

		List<String> shopTags = dataAccess.getShopTagsById(shopId);
		List<String> productTags = dataAccess.getProductTagsById(productId);		
		return new Record(price, productName, productId, productTags, shopId, shopName, shopTags, shopAddress, shopDist, date, lng, lat);
	}

}
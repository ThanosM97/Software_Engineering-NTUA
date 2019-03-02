package gr.ntua.ece.stingy.data;
/*
 * A class implementing all the queries in the database.
 */

import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Message;
import gr.ntua.ece.stingy.data.model.Record;
import gr.ntua.ece.stingy.data.model.Shop;
import org.apache.commons.dbcp2.BasicDataSource;
import org.restlet.data.Status;
import org.restlet.resource.ResourceException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.google.gson.Gson;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public class DataAccess {

	private static final Object[] EMPTY_ARGS = new Object[0];
	private static final int MAX_TOTAL_CONNECTIONS = 16;
	private static final int MAX_IDLE_CONNECTIONS = 8;
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate namedJdbcTemplate;

	public void setup(String driverClass, String url, String user, String pass) throws SQLException {

		// Initialize the data source
		BasicDataSource bds = new BasicDataSource();
		bds.setDriverClassName(driverClass);
		bds.setUrl(url);
		bds.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		bds.setMaxIdle(MAX_IDLE_CONNECTIONS);
		bds.setUsername(user);
		bds.setPassword(pass);
		bds.setValidationQuery("SELECT 1");
		bds.setTestOnBorrow(true);
		bds.setDefaultAutoCommit(true);

		// Check that everything works OK
		bds.getConnection().close();

		// Initialize the jdbc template utility
		jdbcTemplate = new JdbcTemplate(bds);
		namedJdbcTemplate = new NamedParameterJdbcTemplate(bds);
	}

	public List<Product> getProducts(Limits limits, String status, String sort) {
		String sort_type = sort.replaceAll("\\|", " ");   
		/*
		 * Initialize withdrawn based on the status value
		 */
		String withdrawn = null;
		if (status.equals("ALL")) {
			withdrawn = "withdrawn";
		}
		else if (status.equals("WITHDRAWN")) {
			withdrawn = "1";
		}
		else {
			withdrawn = "0";
		}

		/*
		 * Get number of all products
		 */
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		jdbcTemplate.query("select * from Product order by id", countCallback);
		int rowCount = countCallback.getRowCount();
		limits.setTotal(rowCount);
		/*
		 * Return products based on the limits.
		 */
		//TODO: fix error in descending order 
		return jdbcTemplate.query("select * from Product where withdrawn=? order by ? limit ?,?", new Object[] { withdrawn, sort_type, limits.getStart(), limits.getCount() }, new ProductRowMapper());
	}

	public List<String> getProductTagsById(long id){
		String query = "select distinct Tag.name from Product_Tag, Tag where productId=? and tagId = Tag.id";
		return jdbcTemplate.queryForList(query, new Object[] { id }, String.class);
	}
	
	public List<String> getShopTagsById(long id){
		String query = "select distinct Tag.name from Shop_Tag, Tag where ShopId=? and TagId = Tag.id";
		return jdbcTemplate.queryForList(query, new Object[] { id }, String.class);
	}

	public Map<String, String> getExtraDataById(long id){
		String query = "SELECT extraData.characteristic, extraData.value FROM extraData where productId = ?";
		return jdbcTemplate.query(query ,new Object[] { id }, new ResultSetExtractor<Map>(){
			@Override
			public Map extractData(ResultSet rs) throws SQLException,DataAccessException {
				HashMap<String,String> mapRet= new HashMap<String,String>();
				while(rs.next()){
					mapRet.put(rs.getString("characteristic"),rs.getString("value"));
				}
				return mapRet;
			}
		});
	}

	public Product addProduct(String name, String description, String category, boolean withdrawn, ArrayList<String> tags, String extraDataString ) {
		/*
		 * Insert the new product in the Product table
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();	// for keeping the product id
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps =
								connection.prepareStatement("insert into Product(name, description, category, withdrawn) "
										+ "values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, name);
						ps.setString(2, description);
						ps.setString(3, category);
						ps.setBoolean(4, withdrawn);
						return ps;
					}
				},
				keyHolder);
		long productId = (long)keyHolder.getKey();
		/*
		 * For each tag insert it in the Tag table if not exists and then insert it in the Product_Tag table.
		 */
		Long tagId;
		int count;
		for (String tag: tags) { 
			count = jdbcTemplate.queryForObject("select count(*) from Tag where "
					+ "name=?", new Object[] { tag }, Integer.class);
			if (count > 0) {
				tagId = jdbcTemplate.queryForObject("select id from Tag where "
						+ "name=?", new Object[] { tag }, Long.class);
			}
			else {
				/*
				 * Insert tag in Tag table.
				 */
				keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(
						new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement ps =
										connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
								ps.setString(1, tag);
								return ps;
							}
						},
						keyHolder);
				tagId = (long)keyHolder.getKey();
			}
			jdbcTemplate.update("INSERT INTO Product_Tag(ProductId, TagId) VALUES(?, ?)", new Object[] { productId, tagId  });			
		}
		Map<String, String> extraData;
		if (extraDataString != null) {
			List<String> extraDataList = Arrays.asList(extraDataString.split(","));
			extraData = new HashMap<>();
			if (category.equals("Laptop")) {
				if (extraDataList.size() != 6) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 6");
				}
				/*
				 * Extra data supported for Laptops
				 */
				extraData.put("CPU", extraDataList.get(0));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('CPU', ?, ?)", new Object[] { extraDataList.get(0), productId});			
				extraData.put("RAM", extraDataList.get(1));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('RAM', ?, ?)", new Object[] { extraDataList.get(1), productId});			
				extraData.put("Hard Drive", extraDataList.get(2));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Hard Drive', ?, ?)", new Object[] { extraDataList.get(2), productId});			
				extraData.put("OS", extraDataList.get(3));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('OS', ?, ?)", new Object[] { extraDataList.get(3), productId});			
				extraData.put("Screen Size", extraDataList.get(4));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Screen Size', ?, ?)", new Object[] {  extraDataList.get(4), productId});			
				extraData.put("Graphics Card", extraDataList.get(5));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Graphics Card', ?, ?)", new Object[] { extraDataList.get(5), productId});			

			}
			else if (category.equals("TV")) {
				if (extraDataList.size() != 3) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 3");
				}
				/*
				 * Extra data supported for TVs
				 */
				if (extraDataList.get(0).equals("4k") || extraDataList.get(0).equals("4K")) {
					extraData.put("4K", "Yes");
				}
				else {
					extraData.put("4K", "No");
				}
				if (extraDataList.get(1).equals("Smart") || extraDataList.get(1).equals("SMART")) {
					extraData.put("Smart", "Yes");
				}
				else {
					extraData.put("Smart", "No");
				}			
				extraData.put("Frequency", extraDataList.get(2));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('4K', ?, ?)", new Object[] { extraDataList.get(0), productId});			
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Smart', ?, ?)", new Object[] { extraDataList.get(1), productId});			
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Frequency', ?, ?)", new Object[] { extraDataList.get(2), productId});			

			}
			else if (category.equals("Smartphone")) {
				if (extraDataList.size() != 7) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 7");
				}
				/*
				 * Extra data supported for Smartphones
				 */
				extraData.put("CPU cores", extraDataList.get(0));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('CPU cores', ?, ?)", new Object[] { extraDataList.get(0), productId});			
				extraData.put("CPU frequency", extraDataList.get(1));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('CPU frequency', ?, ?)", new Object[] { extraDataList.get(1), productId});			
				extraData.put("RAM", extraDataList.get(2));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('RAM', ?, ?)", new Object[] { extraDataList.get(2), productId});			
				extraData.put("Capacity", extraDataList.get(3));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Capacity', ?, ?)", new Object[] { extraDataList.get(3), productId});			
				extraData.put("Front camera", extraDataList.get(4));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Front camera', ?, ?)", new Object[] { extraDataList.get(4), productId});			
				extraData.put("Selfie camera", extraDataList.get(5));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('Selfie camera', ?, ?)", new Object[] { extraDataList.get(5), productId});			
				extraData.put("OS", extraDataList.get(6));
				jdbcTemplate.update("INSERT INTO extraData(characteristic, value, ProductId ) VALUES('OS', ?, ?)", new Object[] { extraDataList.get(6), productId});			
			}
			else {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Category " + category + " is not supported in stingy");
			}
		}
		else {
			extraData = null;
		}
		/*
		 * Create product and return it.
		 */
		Product product = new Product(
				productId, //the newly created project id
				name,
				description,
				category,
				withdrawn,
				tags,
				extraData
				);
		return product;
	}

	public Optional<Product> getProduct(long id) {
		Long[] params = new Long[]{id};
		List<Product> products = jdbcTemplate.query("select * from Product where id = ?", params, new ProductRowMapper());
		if (products.size() == 1)  {
			return Optional.of(products.get(0));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Message> deleteProduct(long id) {
		Long[] params = new Long[]{id};
		/*
		 * Delete product from Product table and return 'OK' message.
		 */
		int found = jdbcTemplate.update("delete from Product where id=?", params);
		if (found == 1)  {
			return Optional.of(new Message("OK"));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Product> updateProduct(long id, String name, String description, String category, boolean withdrawn, List<String> tags, String extraDataString ) {
		/*
		 * Update the new product record.
		 */
		int rows = jdbcTemplate.update("update Product set name=?, description=?, category=?, withdrawn=? where id =?", new Object[] {name, description, category, withdrawn, id});
		/*
		 *  Check if the product exists
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (rows == 1)  {
			/*
			 * Delete existing tags
			 */
			Long tagId;
			int count;
			jdbcTemplate.update("delete from Product_Tag where ProductId = ?", new Object[] { id});
			for (String tag: tags) { 
				count = jdbcTemplate.queryForObject("select count(*) from Tag where "
						+ "name=?", new Object[] { tag }, Integer.class);
				if (count > 0) {
					tagId = jdbcTemplate.queryForObject("select id from Tag where "
							+ "name=?", new Object[] { tag }, Long.class);
				}
				else {
					/*
					 * Insert tag in Tag table.
					 */
					keyHolder = new GeneratedKeyHolder();
					jdbcTemplate.update(
							new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps =
											connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
									ps.setString(1, tag);
									return ps;
								}
							},
							keyHolder);
					tagId = (long)keyHolder.getKey();
				}
				jdbcTemplate.update("INSERT INTO Product_Tag(ProductId, TagId) VALUES(?, ?)", new Object[] { id, tagId  });			
			}
			Map<String, String> extraData;
			if (extraDataString != null) {
				List<String> extraDataList = Arrays.asList(extraDataString.split(","));
				extraData = new HashMap<>();
				if (category.equals("Laptop")) {
					if (extraDataList.size() != 6) {
						throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 6");
					}
					/*
					 * Extra data supported for Laptops
					 */
					extraData.put("CPU", extraDataList.get(0));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU'", new Object[] { extraDataList.get(0), id});			
					extraData.put("RAM", extraDataList.get(1));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='RAM'", new Object[] { extraDataList.get(1), id});			
					extraData.put("Hard Drive", extraDataList.get(2));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Hard Drive'", new Object[] { extraDataList.get(2), id});			
					extraData.put("OS", extraDataList.get(3));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='OS'", new Object[] { extraDataList.get(3), id});			
					extraData.put("Screen Size", extraDataList.get(4));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Screen Size'", new Object[] {  extraDataList.get(4), id});			
					extraData.put("Graphics Card", extraDataList.get(5));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Graphics Card'", new Object[] { extraDataList.get(5), id});			

				}
				else if (category.equals("TV")) {
					if (extraDataList.size() != 3) {
						throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 3");
					}
					/*
					 * Extra data supported for TVs
					 */
					if (extraDataList.get(0).equals("4k") || extraDataList.get(0).equals("4K")) {
						extraData.put("4K", "Yes");
					}
					else {
						extraData.put("4K", "No");
					}
					if (extraDataList.get(1).equals("Smart") || extraDataList.get(1).equals("SMART")) {
						extraData.put("Smart", "Yes");
					}
					else {
						extraData.put("Smart", "No");
					}			
					extraData.put("Frequency", extraDataList.get(2));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='4K'", new Object[] { extraDataList.get(0), id});			
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Smart'", new Object[] { extraDataList.get(1), id});			
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Frequency'", new Object[] { extraDataList.get(2), id});			

				}
				else if (category.equals("Smartphone")) {
					if (extraDataList.size() != 7) {
						throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 7");
					}
					/*
					 * Extra data supported for Smartphones
					 */
					extraData.put("CPU cores", extraDataList.get(0));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU cores'", new Object[] { extraDataList.get(0), id});			
					extraData.put("CPU frequency", extraDataList.get(1));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU frequency'", new Object[] { extraDataList.get(1), id});			
					extraData.put("RAM", extraDataList.get(2));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='RAM'", new Object[] { extraDataList.get(2), id});			
					extraData.put("Capacity", extraDataList.get(3));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Capacity'", new Object[] { extraDataList.get(3), id});			
					extraData.put("Front camera", extraDataList.get(4));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Front camera'", new Object[] { extraDataList.get(4), id});			
					extraData.put("Selfie camera", extraDataList.get(5));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Selfie camera'", new Object[] { extraDataList.get(5), id});			
					extraData.put("OS", extraDataList.get(6));
					jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='OS'", new Object[] { extraDataList.get(6), id});			
				}
				else {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Category " + category + " is not supported in stingy");
				}
			}
			else {
				extraData = null;
			}

			/*
			 *  return the product that was updated.
			 */
			return getProduct(id);
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Product> patchProduct(long id, String value, String field ) {
		/*
		 *  Updates the new product record based on the non null value
		 */
		int rows;
		if (field.equals("withdrawn")) {
			boolean withdrawn = Boolean.valueOf(value);
			rows = jdbcTemplate.update("update Product set " + field + "=? where id =?", new Object[] {withdrawn, id});
		}
		else if (field.equals("tags")){
			/*
			 * Convert tagString that represents a list of tags to a list.
			 */
			ArrayList<String> tags = new Gson().fromJson(value, ArrayList.class);
			/*
			 * Delete existing tags
			 */
			KeyHolder keyHolder = new GeneratedKeyHolder();
			Long tagId;
			int count;
			jdbcTemplate.update("delete from Product_Tag where ProductId = ?", new Object[] { id});
			for (String tag: tags) { 
				count = jdbcTemplate.queryForObject("select count(*) from Tag where "
						+ "name=?", new Object[] { tag }, Integer.class);
				if (count > 0) {
					tagId = jdbcTemplate.queryForObject("select id from Tag where "
							+ "name=?", new Object[] { tag }, Long.class);
				}
				else {
					/*
					 * Insert tag in Tag table.
					 */
					keyHolder = new GeneratedKeyHolder();
					jdbcTemplate.update(
							new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps =
											connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
									ps.setString(1, tag);
									return ps;
								}
							},
							keyHolder);
					tagId = (long)keyHolder.getKey();
				}
				rows = jdbcTemplate.update("INSERT INTO Product_Tag(ProductId, TagId) VALUES(?, ?)", new Object[] { id, tagId  });			
			}
		}
		else if (field.equals("extraData")){
			Map<String, String> extraData;
			List<String> extraDataList = Arrays.asList(value.split(","));
			extraData = new HashMap<>();
			String category = jdbcTemplate.queryForObject("select category from Product where id = ?", new Object[] { id }, String.class);
			if (category.equals("Laptop")) {
				if (extraDataList.size() != 6) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 6");
				}
				/*
				 * Extra data supported for Laptops
				 */
				extraData.put("CPU", extraDataList.get(0));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU'", new Object[] { extraDataList.get(0), id});			
				extraData.put("RAM", extraDataList.get(1));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='RAM'", new Object[] { extraDataList.get(1), id});			
				extraData.put("Hard Drive", extraDataList.get(2));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Hard Drive'", new Object[] { extraDataList.get(2), id});			
				extraData.put("OS", extraDataList.get(3));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='OS'", new Object[] { extraDataList.get(3), id});			
				extraData.put("Screen Size", extraDataList.get(4));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Screen Size'", new Object[] {  extraDataList.get(4), id});			
				extraData.put("Graphics Card", extraDataList.get(5));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Graphics Card'", new Object[] { extraDataList.get(5), id});			

			}
			else if (category.equals("TV")) {
				if (extraDataList.size() != 3) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 3");
				}
				/*
				 * Extra data supported for TVs
				 */
				if (extraDataList.get(0).equals("4k") || extraDataList.get(0).equals("4K")) {
					extraData.put("4K", "Yes");
				}
				else {
					extraData.put("4K", "No");
				}
				if (extraDataList.get(1).equals("Smart") || extraDataList.get(1).equals("SMART")) {
					extraData.put("Smart", "Yes");
				}
				else {
					extraData.put("Smart", "No");
				}			
				extraData.put("Frequency", extraDataList.get(2));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='4K'", new Object[] { extraDataList.get(0), id});			
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Smart'", new Object[] { extraDataList.get(1), id});			
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Frequency'", new Object[] { extraDataList.get(2), id});			

			}
			else if (category.equals("Smartphone")) {
				if (extraDataList.size() != 7) {
					throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 7");
				}
				/*
				 * Extra data supported for Smartphones
				 */
				extraData.put("CPU cores", extraDataList.get(0));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU cores'", new Object[] { extraDataList.get(0), id});			
				extraData.put("CPU frequency", extraDataList.get(1));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='CPU frequency'", new Object[] { extraDataList.get(1), id});			
				extraData.put("RAM", extraDataList.get(2));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='RAM'", new Object[] { extraDataList.get(2), id});			
				extraData.put("Capacity", extraDataList.get(3));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Capacity'", new Object[] { extraDataList.get(3), id});			
				extraData.put("Front camera", extraDataList.get(4));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Front camera'", new Object[] { extraDataList.get(4), id});			
				extraData.put("Selfie camera", extraDataList.get(5));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='Selfie camera'", new Object[] { extraDataList.get(5), id});			
				extraData.put("OS", extraDataList.get(6));
				jdbcTemplate.update("update extraData set value=? where ProductId = ? and characteristic='OS'", new Object[] { extraDataList.get(6), id});			
			}
			else {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Category " + category + " is not supported in stingy");
			}
		}
		else {
			rows = jdbcTemplate.update("update Product set " + field + "=? where id =?", new Object[] {value, id});
		}

		/*
		 * Return the product that was updated.
		 */
		return getProduct(id);
	}

	public List<Shop> getShops(Limits limits, String status, String sort) {
		String sort_type = sort.replaceAll("\\|", " ");   
		/*
		 * Initialize withdrawn based on the status value
		 */
		String withdrawn = null;
		if (status.equals("ALL")) {
			withdrawn = "withdrawn";
		}
		else if (status.equals("WITHDRAWN")) {
			withdrawn = "1";
		}
		else {
			withdrawn = "0";
		}

		/*
		 * Get number of shops
		 */
		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  /* not reusable */
		jdbcTemplate.query("select * from Shop order by id", countCallback);
		int rowCount = countCallback.getRowCount();
		limits.setTotal(rowCount);
		/*
		 * Return shops based on the limits.
		 */
		//TODO: fix error in descending order 
		return jdbcTemplate.query("select * from Shop where withdrawn=? order by ? limit ?,?", new Object[] { withdrawn,sort_type, limits.getStart(), limits.getCount() }, new ShopRowMapper());
	}

	public Shop addShop(String name, String address,double lng, double lat, List<String> tags, boolean withdrawn, String image) {
		/*
		 * Insert the new shop in the Product table
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();	// for keeping the shop id
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps =
								connection.prepareStatement("insert into Shop(name, address, lng, lat, withdrawn, image) "
										+ "values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						ps.setString(1, name);
						ps.setString(2, address);
						ps.setDouble(3,  lng);
						ps.setDouble(4, lat);
						ps.setBoolean(5, withdrawn);
						ps.setString(6, image);
						return ps;
					}
				},
				keyHolder);
		long shopId = (long)keyHolder.getKey();
		/*
		 * For each tag insert it in the Tag table if not exists and then insert it in the Shop_Tag table.
		 */
		Long tagId;
		int count;
		for (String tag: tags) { 
			count = jdbcTemplate.queryForObject("select count(*) from Tag where "
					+ "name=?", new Object[] { tag }, Integer.class);
			if (count > 0) {
				tagId = jdbcTemplate.queryForObject("select id from Tag where "
						+ "name=?", new Object[] { tag }, Long.class);
			}
			else {
				/*
				 * Insert tag in Tag table.
				 */
				keyHolder = new GeneratedKeyHolder();
				jdbcTemplate.update(
						new PreparedStatementCreator() {
							public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
								PreparedStatement ps =
										connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
								ps.setString(1, tag);
								return ps;
							}
						},
						keyHolder);
				tagId = (long)keyHolder.getKey();
			}
			jdbcTemplate.update("INSERT INTO Shop_Tag(ShopId, TagId) VALUES(?, ?)", new Object[] { shopId, tagId  });			
		}
		Shop shop = new Shop(
					shopId, //the newly created project id
					name,
					address,
					lng,
					lat,
					tags,
					withdrawn,
					image
					);
			return shop;
	}

	public Optional<Shop> getShop(long id) {
		Long[] params = new Long[]{id};
		List<Shop> shops = jdbcTemplate.query("select * from Shop where id = ?", params, new ShopRowMapper());
		if (shops.size() == 1)  {
			return Optional.of(shops.get(0));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Message> deleteShop(long id) {
		Long[] params = new Long[]{id};
		int found = jdbcTemplate.update("delete from Shop where id=?", params);
		if (found == 1)  {
			return Optional.of(new Message("OK"));
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Shop> updateShop(long id, String name, String address, double lng, double lat, List<String> tags, boolean withdrawn, String image) {
		/*
		 *  Updates the new shop record
		 */
		int rows = jdbcTemplate.update("update Shop set name=?, address=?, lng=?, lat=?,  withdrawn=?, image=? where id =?", new Object[] {name, address, lng, lat, withdrawn, image, id});
		/*
		 *  Check if the product exists
		 */
		KeyHolder keyHolder = new GeneratedKeyHolder();
		if (rows == 1)  {
			/*
			 * Delete existing tags
			 */
			Long tagId;
			int count;
			jdbcTemplate.update("delete from Shop_Tag where ShopId = ?", new Object[] { id});
			for (String tag: tags) { 
				count = jdbcTemplate.queryForObject("select count(*) from Tag where "
						+ "name=?", new Object[] { tag }, Integer.class);
				if (count > 0) {
					tagId = jdbcTemplate.queryForObject("select id from Tag where "
							+ "name=?", new Object[] { tag }, Long.class);
				}
				else {
					/*
					 * Insert tag in Tag table.
					 */
					keyHolder = new GeneratedKeyHolder();
					jdbcTemplate.update(
							new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps =
											connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
									ps.setString(1, tag);
									return ps;
								}
							},
							keyHolder);
					tagId = (long)keyHolder.getKey();
				}
				jdbcTemplate.update("INSERT INTO Shop_Tag(ShopId, TagId) VALUES(?, ?)", new Object[] { id, tagId  });			
			}
			/*
			 *  return the shop that was updated.
			 */
			return getShop(id);
		}
		else {
			return Optional.empty();
		}
	}

	public Optional<Shop> patchShop(long id, String value, String field ) {
		/*
		 *  Updates the new shop record based on the non null value
		 */
		int rows;
		if (field.equals("withdrawn")) {
			boolean withdrawn = Boolean.valueOf(value);
			rows = jdbcTemplate.update("update Shop set " + field + "=? where id =?", new Object[] {withdrawn, id});
		}
		else if (field.equals("lng")) {
			double lng = Double.valueOf(value);
			rows = jdbcTemplate.update("update Shop set " + field + "=? where id =?", new Object[] {lng, id});
		}
		else if (field.equals("lat")) {
			double lat = Double.valueOf(value);
			rows = jdbcTemplate.update("update Shop set " + field + "=? where id =?", new Object[] {lat, id});
		}
		else if (field.equals("tags")){
			/*
			 * Convert tagString that represents a list of tags to a list.
			 */
			ArrayList<String> tags = new Gson().fromJson(value, ArrayList.class);
			/*
			 * Delete existing tags
			 */
			KeyHolder keyHolder = new GeneratedKeyHolder();
			Long tagId;
			int count;
			jdbcTemplate.update("delete from Shop_Tag where ShopId = ?", new Object[] { id});
			for (String tag: tags) { 
				count = jdbcTemplate.queryForObject("select count(*) from Tag where "
						+ "name=?", new Object[] { tag }, Integer.class);
				if (count > 0) {
					tagId = jdbcTemplate.queryForObject("select id from Tag where "
							+ "name=?", new Object[] { tag }, Long.class);
				}
				else {
					/*
					 * Insert tag in Tag table.
					 */
					keyHolder = new GeneratedKeyHolder();
					jdbcTemplate.update(
							new PreparedStatementCreator() {
								public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
									PreparedStatement ps =
											connection.prepareStatement("INSERT INTO Tag(name) VALUES(?)", Statement.RETURN_GENERATED_KEYS);
									ps.setString(1, tag);
									return ps;
								}
							},
							keyHolder);
					tagId = (long)keyHolder.getKey();
				}
				rows = jdbcTemplate.update("INSERT INTO Shop_Tag(ShopId, TagId) VALUES(?, ?)", new Object[] { id, tagId  });			
			}
		}
		else {
			rows = jdbcTemplate.update("update Shop set " + field + "=? where id =?", new Object[] {value, id});
		}

		/*
		 *  return the shop that was updated.
		 */
		return getShop(id);
		
}


	public List<Record> getRecords(Limits limits, String geoDistString, String geoLngString, String geoLatString, String dateFrom, String dateTo, 
			String shops, String products, List<String> tags , String sort) {
		String sort_type = sort.replaceAll("\\|", " ");

		/*
		 * Initialize named parameters
		 */
		MapSqlParameterSource parameters = new MapSqlParameterSource();
		parameters.addValue("geoLng", geoLngString);
		parameters.addValue("geoLat", geoLatString);
		parameters.addValue("geoDist", geoDistString);
		parameters.addValue("dateFrom", dateFrom);
		parameters.addValue("dateTo", dateTo);
		parameters.addValue("shops", shops);
		parameters.addValue("products", products);
		parameters.addValue("productTags", tags);
		parameters.addValue("shopTags", tags);
		parameters.addValue("sort", sort_type);
		parameters.addValue("start", limits.getStart());
		parameters.addValue("count", limits.getCount());
		/*
		System.out.println(geoLngString);
		System.out.println(geoLatString);
		System.out.println(geoDistString);
		System.out.println(dateFrom);
		System.out.println(dateTo);
		System.out.println(shops);
		System.out.println(products);
		System.out.println(tags);
		System.out.println(sort_type);
		*/
		String sqlStm;

		if (geoDistString != null) {
			sqlStm = "SELECT distinct price, Product.id as productId, Product.name as productName, Shop.id as shopId, Shop.name as shopName, Shop.address, \n" + 
					"SQRT(POW(Shop.lng - :geoLng, 2) + POW(Shop.lat - :geoLat, 2)) as dist, Record.dateFrom, Record.dateTo\n" + 
					"FROM Shop, Product, Record ";
			if (tags!= null) {
				sqlStm += ", Tag, Shop_Tag, Product_Tag\n";
			}
				sqlStm += "WHERE SQRT(POW(Shop.lng - :geoLng, 2) + POW(Shop.lat - :geoLat, 2)) < :geoDist\n" + 
					"AND Record.shopId = Shop.id \n" + 
					"AND Record.productId = Product.id\n" + 
					"AND Record.dateFrom <= :dateTo and Record.dateTo >= :dateFrom\n";
		}
		else {
			sqlStm = "SELECT distinct price, Product.id as productId, Product.name as productName, Shop.id as shopId, Shop.name as shopName, Shop.address, -1 as dist, \n" + 
					" Record.dateFrom, Record.dateTo\n" + 
					"FROM Shop, Product, Record ";
			if (tags!= null) {
				sqlStm += ", Tag, Shop_Tag, Product_Tag\n";
			}
				sqlStm += "WHERE \n" + 
					"Record.shopId = Shop.id \n" + 
					"AND Record.productId = Product.id\n" + 
					"AND Record.dateFrom <= :dateTo and Record.dateTo >= :dateFrom\n";
		}

		if (products != null) {
			sqlStm += "AND Record.productId in (:products)\n";
		}
		if (shops != null) {
			sqlStm += "AND Record.shopId in (:shops)\n";
		}
		if (tags != null) {
			sqlStm += "AND ((Tag.name in (:productTags)\n" + 
					"		and Tag.id = Product_Tag.TagId\n" + 
					"		and Product_Tag.ProductId = Product.id)\n" + 
					"		or (Tag.id = Shop_Tag.TagId\n" + 
					"		and Shop_Tag.ShopId = Shop.id\n" + 
					"		and Tag.name in (:shopTags)))";
		}

		RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
		namedJdbcTemplate.query(sqlStm, parameters, countCallback);
		int rowCount = countCallback.getRowCount();
		limits.setTotal(rowCount);

		sqlStm += "order by :sort limit :start, :count";
		return namedJdbcTemplate.query(sqlStm, parameters, new RecordRowMapper());
	}

	
	public Record addRecord(double price, String dateFromString, String dateToString, long productId, long shopId, long userId, int validity) {
		/*
		 * Insert the new record in the Record table
		 */
		jdbcTemplate.update(
				new PreparedStatementCreator() {
					public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
						PreparedStatement ps =
								connection.prepareStatement("insert into Record(price, dateFrom, dateTo, productId, shopId, userId) "
										+ "values(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
						ps.setDouble(1, price);
						ps.setString(2, dateFromString);
						ps.setString(3,   dateToString);
						ps.setLong(4, productId);
						ps.setLong(5, shopId);
						ps.setLong(6, userId);
						return ps;
					}
				});
		Optional<Product> productOpt = getProduct(productId);
		Optional<Shop> shopOpt = getShop(shopId);
        Product product = productOpt.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Product not found - id: " + productId));
        Shop shop = shopOpt.orElseThrow(() -> new ResourceException(Status.CLIENT_ERROR_NOT_FOUND, "Shop not found - id: " + shopId));
        /*
         * Convert dates from String to Date.
         */
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD"); 
        Date dateFrom = null;
        Date dateTo = null;
        try {
			dateFrom = (Date)formatter.parse(dateFromString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			dateTo = (Date)formatter.parse(dateToString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Record record = new Record(
					price,
					product.getName(),
					productId, 
					product.getTags(),
					shopId,
					shop.getName(),
					shop.getTags(),
					shop.getAddress(),
					-1,
					dateFrom,
					dateTo
					);
			return record;
	}
	
	public String getRandomString() {
		int leftLimit = 97; /* letter 'a' */
		int rightLimit = 122; /* letter 'z' */
		int targetStringLength = 10;
		Random random = new Random();
		StringBuilder buffer = new StringBuilder(targetStringLength);
		for (int i = 0; i < targetStringLength; i++) {
			int randomLimitedInt = leftLimit + (int) 
					(random.nextFloat() * (rightLimit - leftLimit + 1));
			buffer.append((char) randomLimitedInt);
		}
		return buffer.toString();
	}
	
	public String getToken( String username, String password) {
		/*
		 * Check if a user logged in.
		 */
		int count = jdbcTemplate.queryForObject("select count(*) from User where "
				+ "username=? and password=?", new Object[] { username, password}, Integer.class);
		String token;
		if (count > 0) {
			/*
			 * A user logged in.
			 */
			int userId = jdbcTemplate.queryForObject("select id from User where "
					+ "username=? and password=?", new Object[] { username, password }, Integer.class);
			token = getRandomString();
			/*
			 * Update newly created token.
			 */
			jdbcTemplate.update("update User set token=? where id=?", new Object[] {token, userId});
		}
		else {
			/*
			 * Check if a administrator logged in.
			 */
			count = jdbcTemplate.queryForObject("select count(*) from Administrator where "
					+ "username=? and password=?", new Object[] { username, password}, Integer.class);
			if (count > 0) {
				/*
				 * An administrator logged in.
				 */
				int adminId = jdbcTemplate.queryForObject("select id from Administrator where "
						+ "username=? and password=?", new Object[] { username, password }, Integer.class);
				token = getRandomString();
				/*
				 * Update newly created token.
				 */
				jdbcTemplate.update("update Administrator set token=? where id=?", new Object[] {token, adminId});
			}
			else {
				/*
				 * Wrong username or password.
				 */
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Wrong username or password");
			}
		}
		return token;
	}
	
	public Message tokenIsValid(String auth) {
		/*
		 * Check if token exists in User
		 */
		int id;
		int count = jdbcTemplate.queryForObject("select count(*) from User where "
				+ "token=?", new Object[] { auth }, Integer.class);
		if (count == 0) {
			/*
			 * Check if token exists in Administrator
			 */
			count = jdbcTemplate.queryForObject("select count(*) from Administrator where "
					+ "token=?", new Object[] { auth }, Integer.class);
			if (count == 0) {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Token not found");
			}
			else {
				id = jdbcTemplate.queryForObject("select id from Administrator where "
						+ "token=?", new Object[] { auth }, Integer.class);
				/*
				 * Disable token.
				 */
				jdbcTemplate.update("update Administrator set token=? where id=?", new Object[] {-1, id});
			}
		}
		else {
			id = jdbcTemplate.queryForObject("select id from User where "
					+ "token=?", new Object[] { auth }, Integer.class);
			/*
			 * Disable token.
			 */
			jdbcTemplate.update("update User set token=? where id=?", new Object[] {-1, id});
		}
		return new Message("OK");
	}
	
}

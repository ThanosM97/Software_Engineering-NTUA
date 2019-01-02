package gr.ntua.ece.stingy.data;


import gr.ntua.ece.stingy.data.model.Product;
import gr.ntua.ece.stingy.data.model.Message;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowCountCallbackHandler;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class DataAccess {

    private static final Object[] EMPTY_ARGS = new Object[0];

    private static final int MAX_TOTAL_CONNECTIONS = 16;
    private static final int MAX_IDLE_CONNECTIONS = 8;

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    public void setup(String driverClass, String url, String user, String pass) throws SQLException {

        //initialize the data source
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

        //check that everything works OK
        bds.getConnection().close();

        //initialize the jdbc template utilitiy
        jdbcTemplate = new JdbcTemplate(bds);
    }

    public List<Product> getProducts(Limits limits, String status, String sort) {
    	String sort_type = sort.replaceAll("\\|", " ");   
    	/*
    	 * initialize withdrawn based on the status value
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
    	 * get number of products
    	 */
    	RowCountCallbackHandler countCallback = new RowCountCallbackHandler();  // not reusable
    	jdbcTemplate.query("select * from product order by id", countCallback);
    	int rowCount = countCallback.getRowCount();
    	limits.setTotal(rowCount);
    	/*
    	 * return products based on the limits.
    	 */
    	//TODO: fix error in descending order 
    	return jdbcTemplate.query("select * from product where withdrawn=? order by ? limit ?,?", new Object[] { withdrawn,sort_type, limits.getStart(), limits.getCount() }, new ProductRowMapper());
    }

    public Product addProduct(String name, String description, String category, boolean withdrawn, String tags, String extraData ) {
        //Create the new product record using a prepared statement
        PreparedStatementCreator psc = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(
                        "insert into product(name, description, category, withdrawn, tags, extraData) values(?, ?, ?, ?, ?,?)",
                        Statement.RETURN_GENERATED_KEYS
                );
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setString(3, category);
                ps.setBoolean(4, withdrawn);
                ps.setString(5, tags);
                ps.setString(6, extraData);
                return ps;
            }
        };
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        int cnt = jdbcTemplate.update(psc, keyHolder);

        if (cnt == 1) {
            //New row has been added
            Product product = new Product(
                keyHolder.getKey().longValue(), //the newly created project id
                name,
                description,
                category,
                withdrawn,
                tags,
                extraData
            );
            return product;

        }
        else {
            throw new RuntimeException("Creation of Product failed");
        }
    }

    public Optional<Product> getProduct(long id) {
        Long[] params = new Long[]{id};
        List<Product> products = jdbcTemplate.query("select * from product where id = ?", params, new ProductRowMapper());
        System.out.println(products);
        if (products.size() == 1)  {
            return Optional.of(products.get(0));
        }
        else {
            return Optional.empty();
        }
    }
    
    public Optional<Message> deleteProduct(long id) {
        Long[] params = new Long[]{id};
        int found = jdbcTemplate.update("delete from product where id=?", params);
        if (found == 1)  {
            return Optional.of(new Message("OK"));
        }
        else {
            return Optional.empty();
        }
    }


}

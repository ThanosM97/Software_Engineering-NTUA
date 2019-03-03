package gr.ntua.ece.stingy.data;
/*
 * A class that converts the result of a query in the user table into a product object. 
 */

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.model.User;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class UserRowMapper implements RowMapper {
	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
	    
		long id            = rs.getLong("id");
		String username	   = rs.getString("username");
		String password    = rs.getString("password");
		String firstName   = rs.getString("firstName");
		String lastName    = rs.getString("lastName");
		String token       = rs.getString("token");
		String key         = rs.getString("key");
		String email       = rs.getString("email");
		String phoneNumber = rs.getString("phoneNumber");
		String profilePic  = rs.getString("profilePic");
		long points        = rs.getLong("points");
		
		return new User(id, username, password, firstName, lastName, token, key, email, phoneNumber, profilePic, points);
	}

}
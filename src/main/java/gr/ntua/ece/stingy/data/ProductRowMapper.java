package gr.ntua.ece.stingy.data;

import gr.ntua.ece.stingy.data.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class ProductRowMapper implements RowMapper {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id            = rs.getLong("id");
        String name        = rs.getString("name");
        String description = rs.getString("description");
        String category    = rs.getString("category");
        boolean withdrawn  = rs.getBoolean("withdrawn");
        String tagsString        = rs.getString("tags");
        String extraDataString   = rs.getString("extraData");
        
        ArrayList<String> tags = new ArrayList<String>(Arrays.asList(tagsString.split(" , ")));
        ArrayList<String> extraData = new ArrayList<String>(Arrays.asList(extraDataString.split(" , ")));
        return new Product(id, name, description, category, withdrawn, tags, extraData);
    }

}

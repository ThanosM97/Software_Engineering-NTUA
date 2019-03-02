package gr.ntua.ece.stingy.data;

import gr.ntua.ece.stingy.conf.Configuration;
import gr.ntua.ece.stingy.data.model.Shop;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ShopRowMapper implements RowMapper {
	private final DataAccess dataAccess = Configuration.getInstance().getDataAccess();

    @Override
    public Shop mapRow(ResultSet rs, int rowNum) throws SQLException {

        long id            = rs.getLong("id");
        String name        = rs.getString("name");
        String address     = rs.getString("address");
        double lng		   = rs.getDouble("lng");
        double lat		   = rs.getDouble("lat");
        boolean withdrawn  = rs.getBoolean("withdrawn");
        String image	   = rs.getString("image");
        /*
		 * Convert tags to a list of tags (tags are separated by comma).
		 */
		List<String> tags = dataAccess.getShopTagsById(id);
        return new Shop(id, name, address, lng, lat, tags, withdrawn, image);
    }

}
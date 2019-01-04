package gr.ntua.ece.stingy.data.model;
/*
 * A class representing a product,
 */
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

public class Product {

    private final long id;
    private final String name;
    private final String description;
    private final String category;
    private final boolean withdrawn;
    /*
     * tags and extraData are saved in the database as comma separated string.
     */
    private final ArrayList<String> tags;
    private final Map<String, String> extraData;

    public Product(long id, String name, String description, String category, boolean withdrawn, ArrayList<String> tags, String extraDataString) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.category    = category;
        this.withdrawn   = withdrawn;
        this.tags        = tags;
        /*
         * Convert comma separated string into a map.
         */
        this.extraData	 = setExtraData(extraDataString);
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public boolean isWithdrawn() {
        return withdrawn;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public Map<String, String> getExtraData() {
        return extraData;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id == product.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    
    /*
     * Converts a comma separated string into a map with specific
     * ids based in the category.
     */
    public Map<String, String> setExtraData(String extraDataString) {
    	// Get a list of the comma separated string.
    	List<String> extraDataList = Arrays.asList(extraDataString.split("\\s*,\\s*"));
		Map<String, String> extraData = new HashMap<>();
    	if (category.equals("Laptop")) {
			if (extraDataList.size() != 6) {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 6");
			}
			/*
			 * Extra data supported for Laptops
			 */
			extraData.put("CPU", extraDataList.get(0));
			extraData.put("RAM", extraDataList.get(1));
			extraData.put("Hard Drive", extraDataList.get(2));
			extraData.put("OS", extraDataList.get(3));
			extraData.put("Screen Size", extraDataList.get(4));
			extraData.put("Graphics Card", extraDataList.get(5));
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
		}
		else if (category.equals("Smartphone")) {
			if (extraDataList.size() != 6) {
				throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Invalid extra data size: " + extraDataList.size() + " instead of 6");
			}
			/*
			 * Extra data supported for Smartphones
			 */
			extraData.put("CPU cores", extraDataList.get(0));
			extraData.put("CPU frequency", extraDataList.get(1));
			extraData.put("RAM", extraDataList.get(2));
			extraData.put("Capacity", extraDataList.get(3));
			extraData.put("Screen Size", extraDataList.get(4));
			extraData.put("Screen Size", extraDataList.get(5));
		}
		else {
			throw new ResourceException(Status.CLIENT_ERROR_BAD_REQUEST, "Category " + category + " is not supported in stingy");
		}
    	return extraData;
    }
}

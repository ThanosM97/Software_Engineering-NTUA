package gr.ntua.ece.stingy.data.model;
/*
 * A class representing a product,
 */
import java.util.List;

import java.util.Map;
import java.util.Objects;

public class Product {
    private final long id;
    private final String name;
    private final String description;
    private final String category;
    private final boolean withdrawn;	
	private List<String> tags;
	private String tagsString;
    private final Map<String, String> extraData;
	private final String image;
	private final Double bestPrice;
    


    public Product(long id, String name, String description, String category, boolean withdrawn, List<String> tags, Map<String, String> extraData, Double bestPrice, String image) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.category    = category;
        this.withdrawn   = withdrawn;
        this.tags        = tags;
        this.tagsString  = String.join(",", tags);
        this.extraData	 = extraData;
        this.bestPrice	 = bestPrice; 
        this.image       = image;
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

    public List<String> getTags() {
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
    
}
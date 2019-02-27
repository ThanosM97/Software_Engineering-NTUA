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
    private final List<String> tags;
    private final Map<String, String> extraData;

    public Product(long id, String name, String description, String category, boolean withdrawn, List<String> tags, Map<String, String> extraData) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.category    = category;
        this.withdrawn   = withdrawn;
        this.tags        = tags;
        this.extraData	 = extraData;
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
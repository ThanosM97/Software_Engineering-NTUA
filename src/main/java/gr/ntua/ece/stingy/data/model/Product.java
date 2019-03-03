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

import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlList;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.restlet.data.Status;
import org.restlet.resource.ResourceException;

@XmlRootElement(name = "product")
public class Product {
	@XmlElement(name = "id")
    private final long id;
	@XmlElement(name = "name")
    private final String name;
	@XmlElement(name = "description")
    private final String description;
	@XmlElement(name = "category")
    private final String category;
	@XmlElement(name = "withdrawn")
    private final boolean withdrawn;
	
	private List<String> tags;
	@XmlElement(name = "tags")
	private String tagsString;
	@XmlElement(name = "extraData")
    private final Map<String, String> extraData;
	
	private final Double bestPrice;
    
    public Product() {
    	this.id          = -1;
        this.name        = null;
        this.description = null;
        this.category    = null;
        this.withdrawn   = false;
        this.tags        = null;
        this.extraData	 = null;
        this.bestPrice	 = null;
    }

    public Product(long id, String name, String description, String category, boolean withdrawn, List<String> tags, Map<String, String> extraData, Double bestPrice) {
        this.id          = id;
        this.name        = name;
        this.description = description;
        this.category    = category;
        this.withdrawn   = withdrawn;
        this.tags        = tags;
        this.tagsString  = String.join(",", tags);
        this.extraData	 = extraData;
        this.bestPrice	 = bestPrice; 
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
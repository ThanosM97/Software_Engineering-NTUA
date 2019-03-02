package gr.ntua.ece.stingy.data.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
 
@XmlRootElement (name="products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsMap {
     
    private Map<String, Object> productsMap = new HashMap<String, Object>();
 
    public Map<String, Object> getProductsMap() {
        return productsMap;
    }
 
    public void setProductdsMap(Map<String, Object> productsMap) {
        this.productsMap = productsMap;
    }
}
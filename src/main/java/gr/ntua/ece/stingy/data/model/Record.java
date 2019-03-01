/*
 * A class representing a certain record.
 */
package gr.ntua.ece.stingy.data.model;

import java.util.Objects;
import java.util.Date;
import java.util.List;

public class Record {

	private final long id;
    private final double price;
    private final String productName;
    private final long productId;
    private final List<String> productTags;
    private final long shopId;
    private final String shopName;
    private final List<String> shopTags;
    private final String shopAddress;
    private final int shopDist;
    private final Date dateFrom;
    private final Date dateTo;
    
	public Record(long id, double price, String productName, long productId, List<String> productTags, long shopId, String shopName,
			List<String> shopTags, String shopAddress, int shopDist, Date dateFrom, Date dateTo) {
		this.id = id;
		this.price = price;
		this.productName = productName;
		this.productId = productId;
		this.productTags = productTags;
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopTags = shopTags;
		this.shopAddress = shopAddress;
		this.shopDist = shopDist;
		this.dateFrom = dateFrom;
		this.dateTo = dateTo;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @return the productName
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * @return the productId
	 */
	public long getProductId() {
		return productId;
	}

	/**
	 * @return the productTags
	 */
	public List<String> getProductTags() {
		return productTags;
	}

	/**
	 * @return the shopId
	 */
	public long getShopId() {
		return shopId;
	}

	/**
	 * @return the shopName
	 */
	public String getShopName() {
		return shopName;
	}

	/**
	 * @return the shopTags
	 */
	public List<String> getShopTags() {
		return shopTags;
	}

	/**
	 * @return the shopAddress
	 */
	public String getShopAddress() {
		return shopAddress;
	}

	/**
	 * @return the shopDist
	 */
	public int getShopDist() {
		return shopDist;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return Objects.hash(price, productId, productName, productTags, shopAddress, shopDist, shopId, shopName,
				shopTags);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Record other = (Record) obj;
		return Double.doubleToLongBits(price) == Double.doubleToLongBits(other.price) && productId == other.productId
				&& Objects.equals(productName, other.productName) && Objects.equals(productTags, other.productTags)
				&& Objects.equals(shopAddress, other.shopAddress) && shopDist == other.shopDist
				&& Objects.equals(shopId, other.shopId) && Objects.equals(shopName, other.shopName)
				&& Objects.equals(shopTags, other.shopTags);
	}   
	
}

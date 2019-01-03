/*
 * A class representing a certain record.
 */
package gr.ntua.ece.stingy.data.model;

import java.util.Objects;

public class Record {

    private final double price;
    private final String productName;
    private final long productId;
    private final String productTags;
    private final String shopId;
    private final String shopName;
    private final String shopTags;
    private final String shopAddress;
    private final int shopDist;
    
	public Record(double price, String productName, long productId, String productTags, String shopId, String shopName,
			String shopTags, String shopAddress, int shopDist) {
		this.price = price;
		this.productName = productName;
		this.productId = productId;
		this.productTags = productTags;
		this.shopId = shopId;
		this.shopName = shopName;
		this.shopTags = shopTags;
		this.shopAddress = shopAddress;
		this.shopDist = shopDist;
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
	public String getProductTags() {
		return productTags;
	}

	/**
	 * @return the shopId
	 */
	public String getShopId() {
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
	public String getShopTags() {
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

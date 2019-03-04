package gr.ntua.ece.stingy

import gr.ntua.ece.softeng18b.client.RestAPI
import gr.ntua.ece.softeng18b.client.model.PriceInfo
import gr.ntua.ece.softeng18b.client.model.PriceInfoList
import gr.ntua.ece.softeng18b.client.model.Product
import gr.ntua.ece.softeng18b.client.model.Shop
import gr.ntua.ece.softeng18b.client.rest.RestCallFormat

import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Stepwise

@Stepwise class RestAPISpecification extends Specification {

    private static final String IGNORED = System.setProperty("IGNORE_SSL_ERRORS", "true")
    
    private static final String HOST = System.getProperty("gretty.host")
    private static final String PORT = System.getProperty("gretty.httpsPort")
	
    @Shared RestAPI api = new RestAPI(HOST, PORT as Integer, true)
    
    def "User logins"() {
        when:
        api.login("junkietech", "GngN4ftJ#1VX", RestCallFormat.JSON)
        
        then:
        api.isLoggedIn()
    }
    
    def "User adds product" () {
        when:
        Product sent = new Product(
            name       : "Product",
            description: "Description",
            category   : "Category",
            tags       : ["x", "y", "z"],
            withdrawn  : false
        )
        Product returned = api.postProduct(sent, RestCallFormat.JSON)
        
        then:
        returned.name == sent.name &&
        returned.description == sent.description &&
        returned.category == sent.category &&
        returned.tags == sent.tags &&
        returned.withdrawn == sent.withdrawn
    }

    def "User adds shop"() {
        when:
        Shop sent = new Shop(
            name: "Shop",
            address: "Somewhere",
            lat: 38.01324,
            lng: 23.77223,
            tags: ["one", "two", "three"]
        )

        Shop returned = api.postShop(sent, RestCallFormat.JSON)

        then:
        returned.name == sent.name &&
        returned.address == sent.address &&
        returned.lat == sent.lat &&
        returned.lng == sent.lng &&
        returned.tags == sent.tags &&
        !returned.withdrawn
    }

    def "User adds price"() {
        when:
        double price = 66.99
        String dateFrom = "2019-02-27"
        String dateTo = "2019-02-28"
        String shopId = "1"
        String productId = "2"
        PriceInfoList list = api.postPrice(price, dateFrom, dateTo, productId, shopId, RestCallFormat.JSON)

        then:
        list.total == 2 &&
        list.prices.every { PriceInfo p ->
            p.price == price &&
            p.productId == productId &&
            p.shopId == shopId
        } &&
        list.prices[0].date == dateFrom &&
        list.prices[1].date == dateTo
    }
}


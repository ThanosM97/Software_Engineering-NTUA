package gr.ntua.ece.stingy.api;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.routing.Router;

public class RestfulApp extends Application {

    @Override
    public synchronized Restlet createInboundRoot() {

        Router router = new Router(getContext());

        /**
         * Attach all resources to the corresponding URIs.
         */
        //GET, POST
        router.attach("/products", ProductsResource.class);

        //GET, DELETE, PUT, PATCΗ
        router.attach("/products/{id}", ProductResource.class);

        //GET, POST
        router.attach("/shops", ShopsResource.class);
        
        //GET, DELETE, PUT, PATCΗ
        router.attach("/shops/{id}", ShopResource.class);
        
        //GET, POST
        router.attach("/prices", PricesResource.class);
        
        return router;
    }

}

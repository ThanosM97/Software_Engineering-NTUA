package gr.ntua.ece.stingy.api;

import java.util.Arrays;
import java.util.HashSet;

import org.restlet.Application;
import org.restlet.Restlet;
import org.restlet.engine.application.CorsFilter;
import org.restlet.routing.Router;

public class RestfulApp extends Application {

	@Override
	public synchronized Restlet createInboundRoot() {

		Router router = new Router(getContext());

		 /*
		  *  Add a CORS filter to allow cross-domain requests
		  */
		 CorsFilter corsFilter = new CorsFilter(getContext(), router);
		 corsFilter.setAllowedOrigins(new HashSet<String>(Arrays.asList("*")));
		 corsFilter.setAllowedCredentials(true);
		 corsFilter.setAllowedHeaders(new HashSet<String>(Arrays.asList("X-OBSERVATORY-AUTH")));
		 corsFilter.setAllowingAllRequestedHeaders(true);
		 corsFilter.setExposedHeaders(new HashSet<String>(Arrays.asList("*")));
		 
		/**
		 * Attach all resources to the corresponding URIs.
		 */
		
		/*
		 * GET, POST methods
		 */
		router.attach("/products", ProductsResource.class);

		/*
		 * GET, DELETE, PUT, PATCΗ methods
		 */
		router.attach("/products/{id}", ProductResource.class);

		/*
		 * GET, POST methods
		 */
		router.attach("/shops", ShopsResource.class);

		/*
		 * GET, DELETE, PUT, PATCΗ methods
		 */
		router.attach("/shops/{id}", ShopResource.class);

		/*
		 * GET, POST methods
		 */
		router.attach("/prices", PricesResource.class);
		
		/*
		 * POST method
		 */
		router.attach("/login", LoginResource.class);

		/*
		 * POST method
		 */
		router.attach("/logout", LogoutResource.class);
		
		/*
		 * POST method
		 */
		router.attach("/user", UserResource.class);
		
		/*
		 * GET, POST methods
		 */
		router.attach("/comments", CommentsResource.class);
		
		/*
		 * GET method
		 */
	
		//router.attach("/offers", OffersResource.class);
		
		/*
		 * GET method
		 */
		router.attach("/trends", TrendsResource.class);
		
		/*
		 * POST method
		 */
		router.attach("/register", RegisterResource.class);
		
		return corsFilter;
	}

}

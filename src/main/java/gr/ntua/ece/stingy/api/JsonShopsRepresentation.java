/*
 This class converts an object of type Shops in json format.
 */
package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.Shop;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class JsonShopsRepresentation extends WriterRepresentation {

    private final List<Shop> shops;

    public JsonShopsRepresentation(List<Shop> shops) {
        super(MediaType.APPLICATION_JSON);
        this.shops = shops;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(shops));
    }
}
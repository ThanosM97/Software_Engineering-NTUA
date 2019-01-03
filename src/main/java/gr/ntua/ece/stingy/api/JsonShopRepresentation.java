package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.Shop;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonShopRepresentation extends WriterRepresentation {

    private final Shop shop;

    public JsonShopRepresentation(Shop shop) {
        super(MediaType.APPLICATION_JSON);
        this.shop = shop;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(shop));
    }
}

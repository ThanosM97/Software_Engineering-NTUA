package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.Product;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonProductRepresentation extends WriterRepresentation {

    private final Product product;

    public JsonProductRepresentation(Product product) {
        super(MediaType.APPLICATION_JSON);
        this.product = product;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(product));
    }
}
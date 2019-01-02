package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.Message;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonMessageRepresentation extends WriterRepresentation {

    private final Message message;

    public JsonMessageRepresentation(Message message) {
        super(MediaType.APPLICATION_JSON);
        this.message = message;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(message));
    }
}

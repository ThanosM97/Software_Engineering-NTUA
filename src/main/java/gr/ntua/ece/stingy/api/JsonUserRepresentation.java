/*
 * This class converts an object of type User in json format.
 */

package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.User;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonUserRepresentation extends WriterRepresentation {

    private final User user;

    public JsonUserRepresentation(User user) {
        super(MediaType.APPLICATION_JSON);
        this.user = user;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(user));
    }
}

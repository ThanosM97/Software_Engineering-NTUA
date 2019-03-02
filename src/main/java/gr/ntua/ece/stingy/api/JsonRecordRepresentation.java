/*
 * This class converts an object of type Record in json format.
 */
package gr.ntua.ece.stingy.api;

import com.google.gson.Gson;
import gr.ntua.ece.stingy.data.model.Record;
import org.restlet.data.MediaType;
import org.restlet.representation.WriterRepresentation;

import java.io.IOException;
import java.io.Writer;

public class JsonRecordRepresentation extends WriterRepresentation {

    private final Record record;

    public JsonRecordRepresentation(Record record) {
        super(MediaType.APPLICATION_JSON);
        this.record = record;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(record));
    }
}
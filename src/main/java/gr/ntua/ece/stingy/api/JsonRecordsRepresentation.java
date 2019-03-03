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
import java.util.List;

public class JsonRecordsRepresentation extends WriterRepresentation {

    private final List<Record> records;

    public JsonRecordsRepresentation(List<Record> records) {
        super(MediaType.APPLICATION_JSON);
        this.records = records;
    }

    @Override
    public void write(Writer writer) throws IOException {
        Gson gson = new Gson();
        writer.write(gson.toJson(records));
    }
}
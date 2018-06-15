package json.customtypeadapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.GregorianCalendar;

public class DateTypeAdapter implements JsonSerializer<GregorianCalendar>,
        JsonDeserializer<GregorianCalendar> {

    public JsonElement serialize(GregorianCalendar src, Type typeOfSrc, JsonSerializationContext
            context) {
        return new JsonPrimitive(src.getTimeInMillis());
    }

    public GregorianCalendar deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext
            context)
            throws JsonParseException {
        if (!(json instanceof JsonPrimitive)) {
            throw new JsonParseException("The date should be a long value");
        }

        GregorianCalendar g = new GregorianCalendar();
        g.setTimeInMillis(json.getAsLong());
        return g;
    }
}


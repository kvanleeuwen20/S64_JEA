package json.customtypeadapter;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

        if(((JsonPrimitive) json).isNumber()) {
            GregorianCalendar g = new GregorianCalendar();
            g.setTimeInMillis(json.getAsLong());
            return g;
        }
        else if (((JsonPrimitive) json).isString()) {
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'");
                Date date = df.parse(json.getAsString());

                GregorianCalendar g = new GregorianCalendar();
                g.setTime(date);
                return g;
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}


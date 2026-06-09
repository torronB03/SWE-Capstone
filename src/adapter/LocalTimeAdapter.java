package adapter;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Adapter for LocalTime
/** 
* @author June Fernandez
* LocalTimeAdapter class can translate the time obtained from a API calls within the TestAPIServlet
* into a uniform format (ie: "HH:mm")
* Allows GSON to properly serialize and deserialize any Local Time objects
* @attributes  formatter
* @methods  serialize, deserialize
*/
public class LocalTimeAdapter implements JsonSerializer<LocalTime>, JsonDeserializer<LocalTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

    /**
    * @return Primitive of the local session time that is properly formatted
    */
    @Override
    public JsonElement serialize(LocalTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }

    /**
    * @return Parses the formatted time into a readable string
    */
    @Override
    public LocalTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalTime.parse(json.getAsString(), formatter);
    }
}


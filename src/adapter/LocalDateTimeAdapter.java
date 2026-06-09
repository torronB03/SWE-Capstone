package adapter;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/** 
* @author June Fernandez
* LocalDateTimeAdapter class can convert a combined date-time object obtained from a API calls within the TestAPIServlet
* into a uniform format (ie: "yyyy-MM-dd'T'HH:mm:ss")
* Allows GSON to properly serialize and deserialize any Local DateTime objects
* @attributes  formatter
* @methods  serialize, deserialize
*/
public class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    @Override
    /**
    * @return Primitive of the local session date-time object that is properly formatted
    */
    public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }

    /**
    * @return Parses the formatted date-time object into a readable string
    */
    @Override
    public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDateTime.parse(json.getAsString(), formatter);
    }
}

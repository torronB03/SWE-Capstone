package adapter;
import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Adapter for LocalDate
/** 
* @author June Fernandez
* LocalDateAdapter class can translate the dates obtained from a API calls within the TestAPIServlet
* into a uniform format (ie: "yyyy-MM-dd").
* Allows GSON to properly serialize and deserialize any Local Date objects
* @attributes  formatter
* @methods  serialize, deserialize
*/
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
    * @return Primitive of the local session date that is properly formatted
    */
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.format(formatter));
    }

    /**
    * @return Parses the formatted date into a readable string
    */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString(), formatter);
    }
}

package networking.configurations;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

@SuppressWarnings("WeakerAccess")
public class Date2JsonAdapter implements JsonSerializer<Date> {
    private String format;

    public Date2JsonAdapter() {}

    public Date2JsonAdapter(String format) {
        this.format = format;
    }

    public JsonElement serialize(Date date, Type type, JsonSerializationContext jsonSerializationContext) {
        if (this.format == null) {
            this.format = "date_format";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(this.format);
        formatter.setTimeZone(TimeZone.getDefault());
        String dateFormatAsString = formatter.format(date);
        return new JsonPrimitive(dateFormatAsString);
    }
}


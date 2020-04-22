package co.edu.ff.orders.serialization;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.function.Function;

public class NumberValueAdapter<IN extends NumberSerializable> implements JsonSerializer<IN>, JsonDeserializer<IN> {
    private final Function<Number, IN> factory;

    public NumberValueAdapter(Function<Number, IN> factory) {
        this.factory = factory;
    }


    @Override
    public IN deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Number value = json.getAsNumber();
        return factory.apply(value);
    }

    @Override
    public JsonElement serialize(IN src, Type typeOfSrc, JsonSerializationContext context) {
        Number value = src.valueOf();
        return new JsonPrimitive(value);
    }
}

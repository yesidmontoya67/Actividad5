package co.edu.ff.orders.serialization;

import co.edu.ff.orders.user.domain.Username;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringValueAdapterTest {

    static Gson gson;

    @BeforeAll
    static void setUp() {
        gson = new GsonBuilder()
                .registerTypeAdapter(Username.class, new StringValueAdapter<Username>(Username::of))
                .create();
    }

    @Test
    void deserialize() {
        String username= "yesidm";
        JsonElement jsonElement= new JsonPrimitive(username);

        Username username1= Username.of(username);

        assertEquals(gson.fromJson(jsonElement,Username.class),username1);
    }

    @Test
    void serialize() {
        String usernameString= "yesidm";
        Username username= Username.of(usernameString);

        String esperado=gson.toJson(username);
        String actual=String.format("\"%s\"",username.valueOf());

        assertEquals(actual, esperado);
    }
}
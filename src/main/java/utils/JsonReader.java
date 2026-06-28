package utils;

import java.io.File;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {
    private static JsonNode jsonNode;
    static {
        try {
            ObjectMapper mapper =
                    new ObjectMapper();
            jsonNode =
                    mapper.readTree(
                            new File(
                                    "src/test/resources/testdata/CheckoutData.json"));
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
    public static String getValue(String key) {
        return jsonNode
                .get(key)
                .asText();
    }
}
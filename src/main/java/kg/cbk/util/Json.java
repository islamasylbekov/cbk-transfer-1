package kg.cbk.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class Json {

    private final static Logger LOG = LoggerFactory.getLogger(Json.class);

    public Json() {
    }

    public static JsonNode toJson(Boolean value) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(value, JsonNode.class);
    }

    public static <T> JsonNode toJson(List<T> models) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(models, JsonNode.class);
    }

    public static JsonNode toJson(Object obj) {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(obj, JsonNode.class);
    }

    public static JsonNode toJson(String str) {
        return (new ObjectMapper()).convertValue(str, JsonNode.class);
    }

    public static JsonNode fromJsonString(String str) {
        try {
            return (new ObjectMapper()).readTree(str);
        } catch (Exception var2) {
            LOG.info(var2.getMessage(), var2);
            return null;
        }
    }

    public static <T> T fromJson(String str, Class<T> clazz) {
        try {
            JsonNode e = fromJsonString(str);
            return (new ObjectMapper()).treeToValue(e, clazz);
        } catch (Exception var3) {
            LOG.info(var3.getMessage(), var3);
            return null;
        }
    }

    public static <T> T fromJson(JsonNode json, Class<T> clazz) {
        try {
            return (new ObjectMapper()).treeToValue(json, clazz);
        } catch (Exception var3) {
            LOG.info(var3.getMessage(), var3);
            return null;
        }
    }

}

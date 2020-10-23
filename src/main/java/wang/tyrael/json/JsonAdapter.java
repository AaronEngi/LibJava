package wang.tyrael.json;

import com.google.gson.Gson;

public class JsonAdapter {
    public static <T> T decode(String json, Class<T> clazz) {
        return new Gson().fromJson(json, clazz);
    }

    public static String encode(Object src){
        return new Gson().toJson(src);
    }
}

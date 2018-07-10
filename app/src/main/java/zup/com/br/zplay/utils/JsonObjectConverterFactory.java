package zup.com.br.zplay.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class JsonObjectConverterFactory extends Converter.Factory {

    public static JsonObjectConverterFactory create() {
        return new JsonObjectConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if (JsonObject.class.equals(type)) {
            return new Converter<ResponseBody, JsonObject>() {
                @Override
                public JsonObject convert(ResponseBody value) throws IOException {
                    JsonParser parser = new JsonParser();
                    return parser.parse(value.string()).getAsJsonObject();
                }
            };
        }
        return null;
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        if (JsonObject.class.equals(type)) {
            return new Converter<String, RequestBody>() {
                @Override
                public RequestBody convert(String value) throws IOException {
                    return RequestBody.create(MediaType.parse("application/json"), value);
                }
            };
        }
        return null;
    }
}

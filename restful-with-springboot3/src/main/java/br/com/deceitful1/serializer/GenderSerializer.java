package br.com.deceitful1.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class GenderSerializer extends JsonSerializer<String>
{

    @Override
    public void serialize(String gender, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider) throws IOException
    {
        String formattedGender = "Male".equals(gender) ? "M" : "F";
        jsonGenerator.writeString(formattedGender);
    }
}

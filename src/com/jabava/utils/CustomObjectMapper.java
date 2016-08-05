package com.jabava.utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class CustomObjectMapper extends ObjectMapper {
	
    public CustomObjectMapper(){
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addGenericMapping(Date.class, new JsonSerializer<Date>(){
            @Override
            public void serialize(Date value, JsonGenerator jsonGenerator, 
                    SerializerProvider provider) throws IOException, JsonProcessingException {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                jsonGenerator.writeString(sdf.format(value));
            }
        });
        
        factory.addGenericMapping(BigDecimal.class, new JsonSerializer<BigDecimal>(){
        	@Override
        	public void serialize(BigDecimal value, JsonGenerator jsonGenerator,
        			SerializerProvider provider) throws IOException, JsonProcessingException {
        		if(value == null){
        			jsonGenerator.writeString("");
        		}else{
        			jsonGenerator.writeString(value.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString());
        		}
        	}
        });
        
        this.setSerializerFactory(factory);
    }
}

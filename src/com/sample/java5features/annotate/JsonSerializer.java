package com.sample.java5features.annotate;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class JsonSerializer {
    public String serialize(Object object) throws JsonSerializeException{
        Class<?> objectClass=requireNonNull(object).getClass();
        Map<String,String> jsonElementMap=new HashMap<>();
            try {
                for(Field field:objectClass.getDeclaredFields()) {
                jsonElementMap.put(getSerializedKey(field), (String) field.get(object));
            }
            } catch (IllegalAccessException e) {
                throw new JsonSerializeException(e.getMessage());
            }

        String jsonString=toJsonString(jsonElementMap);
        System.out.println(jsonString);
        return jsonString;
    }

    private static String getSerializedKey(Field field){
        JsonField jsonField=field.getAnnotation(JsonField.class);
        if(null!=jsonField && !jsonField.value().isEmpty()){
            return field.getAnnotation(JsonField.class).value();
        }else{
            return field.getName();
        }
    }
    private String toJsonString(Map<String,String> jsonElementMap){
        String elementString=jsonElementMap.entrySet().stream()
                .map(entry -> "\""+entry.getKey()+"\":\""+entry.getValue()+"\"")
                .collect(Collectors.joining(","));
        return "{"+elementString+"}";
    }

}

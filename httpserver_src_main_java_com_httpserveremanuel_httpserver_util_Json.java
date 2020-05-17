package com.httpserveremanuel.httpserver.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;

public class Json {

private static ObjectMapper myObjectMapper = defaultObjectMapper();


    private static ObjectMapper defaultObjectMapper(){
    ObjectMapper om = new ObjectMapper();
    om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,  false);

    return om;
}
   //We create to parse an json string  into a json node
public static JsonNode parse(String jsonSrc) throws IOException {
    return myObjectMapper.readTree(jsonSrc);
}
    //Now we need a way this json node into the configuration file
    //This A us generic one
public static <A> A fromJson(JsonNode node ,Class<A> clazz) throws JsonProcessingException {
    return myObjectMapper.treeToValue(node, clazz);
}
// After this we create the configuration file into the json file
    public static JsonNode toJson(Object obj ){
    return myObjectMapper.valueToTree(obj);
    }
    //This is the method that we are called from the main method the one is the true the other is false
    public static String stringify(JsonNode node) throws JsonProcessingException {

        return generateJson(node, false);

    }
    //This is the method that we are called from the main method the one is true the other false
    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {

        return generateJson(node, true);

    }

    //be able to see a jsnode to see in a string
    private static  String generateJson(Object o , boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter  = myObjectMapper.writer();
        if(pretty)
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        return objectWriter.writeValueAsString(o);
    }


}

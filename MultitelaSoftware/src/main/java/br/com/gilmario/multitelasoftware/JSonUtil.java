package br.com.gilmario.multitelasoftware;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;

/**
 *
 * @author gilmario
 */
public class JSonUtil {

    public static JsonArrayBuilder criaArrayJson(String[] array) {
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for (String valor : array) {
            builder.add(Json.createObjectBuilder().add("usuario", valor));
        }
        return builder;
    }

}

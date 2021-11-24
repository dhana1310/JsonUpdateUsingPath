import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.apache.commons.lang3.StringUtils;

public class Main {

    public static void main(String[] args) {

        String json = "{\"store\":{\"book\":[{\"category\":\"reference\",\"author\":\"Nigel Rees\",\"title\":\"Sayings of the Century\",\"price\":8.95},{\"category\":\"fiction\",\"author\":\"Evelyn Waugh\",\"title\":\"Sword of Honour\",\"price\":12.99},{\"category\":\"fiction\",\"author\":\"Herman Melville\",\"title\":\"Moby Dick\",\"isbn\":\"0-553-21311-3\",\"price\":8.99},{\"category\":\"fiction\",\"author\":\"J. R. R. Tolkien\",\"title\":\"The Lord of the Rings\",\"isbn\":\"0-395-19395-8\",\"price\":22.99}],\"bicycle\":{\"color\":\"red\",\"price\":19.95}},\"expensive\":10}";
        String s = "store.bicycle.color%"; // added % at the end to make sure we remove the last field from the request correctly
        String[] splitFields = s.split("\\.");
        String lastField = splitFields[splitFields.length - 1];
        String fn = StringUtils.replace(s,"." + lastField, "");
        lastField = StringUtils.replace(lastField,"%", "");
        System.err.println(lastField);
        System.err.println(fn);
        DocumentContext doc = JsonPath.parse(json).
                put("$.store.bicycle","zipCode", "98765"). // adding
                put("$.store.bicycle","checkingNull", null). // removing
                put("$."+fn,lastField,"BLUE"); // updating
        System.err.println(new GsonBuilder().create().toJsonTree(doc.json()).toString());
    }
}
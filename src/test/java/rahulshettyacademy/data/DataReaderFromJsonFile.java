package rahulshettyacademy.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

public class DataReaderFromJsonFile {

    public static List<HashMap<String, String>> getJsonDataToMap() throws IOException {
        //reading Json To string
        String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json"), StandardCharsets.UTF_8);
        //covert string to map
        ObjectMapper mapper = new ObjectMapper();
        //   TypeReference<List<HashMap<String,String>>> ref = new TypeReference<List<HashMap<String,String>>>(){};
        List<HashMap<String, String>> data = mapper.readValue(jsonContent,
                new com.fasterxml.jackson.core.type.TypeReference<List<HashMap<String, String>>>() {
                });
        return data;

    }
}

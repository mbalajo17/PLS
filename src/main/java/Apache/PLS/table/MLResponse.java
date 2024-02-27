package Apache.PLS.table;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class MLResponse {
    Map<String,String> tableJson = new LinkedHashMap<>();

    public MLResponse() {
        // Initialize the map with key-value pairs
        tableJson.put("Date of this Report", "");
        tableJson.put("Dates of Trial", "");
        tableJson.put("Phase of the Study", "2");
        tableJson.put("Sponsor", "\n        Document Name: G CT301");
        tableJson.put("Title of this Trial", "\nAnswer = Evaluation Of The Effectiveness And Safety Profile Of Epcoritamib In Subjects With Advanced Non Hodgkins Lymphomas");
        tableJson.put("Trial Number", "Not Available");
    }
}

package Apache.PLS.table;

import lombok.Data;

import java.sql.Time;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class MLResponse {
    Map<String,String> tableJson = new LinkedHashMap<>();

    public MLResponse() {
        // Initialize the map with key-value pairs
        tableJson.put("1.Clinicaltrails.gov", "NCT Number 122233");
        tableJson.put("2.www.clinicaltrialsregister.eu ", "EudraCT Number 122334");
//        tableJson.put("Phase of the Study", "2");
//        tableJson.put("Sponsor", "\n        Document Name: G CT301");
//        tableJson.put("Title of this Trial", "\nAnswer = Evaluation Of The Effectiveness And Safety Profile Of Epcoritamib In Subjects With Advanced Non Hodgkins Lymphomas");
//        tableJson.put("Trial Number", "Not Available");
    }

    String s="`com.symbiance.pls.utill.MLResponse` (although at least one Creator exists): " +
            "no String-argument constructor/factory method to deserialize from String value" +
            " ('A Multicenter, Randomized, Double-Blind,  Placebo-Controlled  Clinical Study to" +
            " Assess the  Efficacy and Safety of Tildrakizumab in  the  Treatment of Moderate to" +
            " Severe Plaque  Psoriasis of the Scalp') at [Source: (byte[])\"{\"Trial Title\": \"" +
            "  A Multicenter, Randomized, Double-Blind,  Placebo-Controlled  Clinical" +
            " Study to Assess the  Efficacy and Safety of Tildrakizumab in  the " +
            " Treatment of Moderate to Severe Plaque  Psoriasis of the Scalp  \"," +
            " \"Protocol Identification Number\": \"01-08\", \"Phase of Development\":" +
            " \"  3b  \", \"Investigational Product\": \"  Tildrakizumab " +
            " \", \"Clinicaltrials.gov Number\": \"\", \"IND Number\": \"\", " +
            "\"EudraCT Number\": \"\", \"Indication\": \"\", \"Brief Trial Design\": \"\"," +
            " \"Sponsor\": \"Sun Pharma Global FZE\"\"[truncated 146 bytes]; line: 1, column: 17] " +
            "(through reference chain: java.util.LinkedHashMap[\"Trial Title\"])";
}

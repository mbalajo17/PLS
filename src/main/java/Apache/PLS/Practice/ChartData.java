package Apache.PLS.Practice;

import lombok.Data;

import java.util.List;
@Data
public class ChartData {
    // chart title
    private String title;
    private List<String> categories;
    private List<SerieData> series;
}
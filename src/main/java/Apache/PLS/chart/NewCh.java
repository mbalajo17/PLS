package Apache.PLS.chart;


import org.apache.poi.xddf.usermodel.PresetColor;

import java.util.List;

public class NewCh {

    private String chartTitle;
    private List<String> categories;
    private List<String> series;
    private List<Double> values1;
    private List<Double> values2;
    private PresetColor color = PresetColor.ALICE_BLUE;

    public PresetColor getColor() {
        return color;
    }

    public void setColor(PresetColor color) {
        this.color = color;
    }

    // Default constructor (required for Jackson deserialization)
    public NewCh() {
    }

    public NewCh(PresetColor color) {
        this.color = color;
    }

    // Constructor with parameters (optional)
    public NewCh(String chartTitle, List<String> categories, List<String> series, List<Double> values1, List<Double> values2) {
        this.chartTitle = chartTitle;
        this.categories = categories;
        this.series = series;
        this.values1 = values1;
        this.values2 = values2;
    }

    // Getters and setters (optional)
    public String getChartTitle() {
        return chartTitle;
    }

    public void setChartTitle(String chartTitle) {
        this.chartTitle = chartTitle;
    }

    public List<String> getCategories() {
        return categories;
    }

    public void setCategories(List<String> categories) {
        this.categories = categories;
    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    public List<Double> getValues1() {
        return values1;
    }

    public void setValues1(List<Double> values1) {
        this.values1 = values1;
    }

    public List<Double> getValues2() {
        return values2;
    }

    public void setValues2(List<Double> values2) {
        this.values2 = values2;
    }
}

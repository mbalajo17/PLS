package Apache.PLS.chart;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class NewCh {

    private String chartTitle;
    private List<String> categories;
    private List<String> series;
    private List<Number> values1;
    private List<Number> values2;
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
    public NewCh(String chartTitle, List<String> categories, List<String> series, List<Number> values1, List<Number> values2) {
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

    public List<Number> getValues1() {
        return values1;
    }

    public void setValues1(List<Number> values1) {
        this.values1 = values1;
    }

    public List<Number> getValues2() {
        return values2;
    }

    public void setValues2(List<Number> values2) {
        this.values2 = values2;
    }
}
//package Apache.PLS.chart;
//
//        import java.io.FileOutputStream;
//        import java.io.IOException;
//        import java.io.OutputStream;
//
//        import com.fasterxml.jackson.databind.ObjectMapper;
//        import org.apache.poi.ss.util.CellRangeAddress;
//        import org.apache.poi.xddf.usermodel.PresetColor;
//        import org.apache.poi.xddf.usermodel.XDDFColor;
//        import org.apache.poi.xddf.usermodel.XDDFLineProperties;
//        import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
//        import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
//        import org.apache.poi.xddf.usermodel.chart.AxisCrosses;
//        import org.apache.poi.xddf.usermodel.chart.AxisPosition;
//        import org.apache.poi.xddf.usermodel.chart.AxisTickMark;
//        import org.apache.poi.xddf.usermodel.chart.BarDirection;
//        import org.apache.poi.xddf.usermodel.chart.BarGrouping;
//        import org.apache.poi.xddf.usermodel.chart.ChartTypes;
//        import org.apache.poi.xddf.usermodel.chart.LegendPosition;
//        import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
//        import org.apache.poi.xddf.usermodel.chart.XDDFChart;
//        import org.apache.poi.xddf.usermodel.chart.XDDFChartAxis;
//        import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
//        import org.apache.poi.xddf.usermodel.chart.XDDFDataSource;
//        import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
//        import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
//        import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
//        import org.apache.poi.xddf.usermodel.text.XDDFRunProperties;
//        import org.apache.poi.xddf.usermodel.text.XDDFTextBody;
//        import org.apache.poi.xwpf.usermodel.XWPFChart;
//        import org.apache.poi.xwpf.usermodel.XWPFDocument;
//
//
//public final class BarChart {
//
//    private static NewCh readJsonFile() throws IOException {
//        String string = "{\n" +
//                "    \"chartTitle\": \"BarChart\",\n" +
//                "    \"categories\": [\"Male\", \"Female\", \"Others\"],\n" +
//                "    \"series\": [\"Number of Participants\", \"Category\"],\n" +
//                "    \"values1\": [10.0, 30.0, 5.0],\n" +
//                "    \"values2\":[29.0,33.0,33.0]\n" +
//                "  }\n" +
//                "  ";
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(string, NewCh.class);
//    }
//
//    public static void main(String[] args) throws Exception {
//
//        NewCh newCh = readJsonFile();
//
//        try (XWPFDocument doc = new XWPFDocument();
//             OutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Downloads\\chartsss.docx")) {
//            XWPFChart chart = doc.createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 5);
//            setBarData(chart, newCh);
//            doc.write(out);
//        }
//
//    }
//
//    private static void setBarData(XWPFChart chart, NewCh newCh) {
//        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
//        bottomAxis.setTitle(newCh.getSeries().get(0));
//        bottomAxis.setNumberFormat("Arial");
//
//        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
//        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
//        leftAxis.setNumberFormat("Arial");
//        leftAxis.setMajorTickMark(AxisTickMark.OUT);
//        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
//
//
//
//        final int numOfPoints = newCh.getCategories().size();
//        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, 0, 0));
//        final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_COUNTRIES, COLUMN_SPEAKERS));
//        final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, 1, 1));
//        String[] s = newCh.getCategories().toArray(new String[0]);
//        final XDDFDataSource<?> categoriesData =
//                XDDFDataSourcesFactory.fromArray(s, categoryDataRange, 0);
//
//
//        final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(newCh.getValues1().toArray(new Number[0]), valuesDataRange, COLUMN_COUNTRIES);
////        valuesData.setFormatCode("General");
//
////        newCh.getValues1().set(0, 16.0);
//
//
//        XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
//        bar.setBarGrouping(BarGrouping.CLUSTERED);
//
//        XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
//        series1.setTitle(newCh.getSeries().get(0), chart.setSheetTitle(newCh.getSeries().get(0), COLUMN_COUNTRIES));
//
//        XDDFLineProperties lineProperties = new XDDFLineProperties();
//        XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.RED));
//        series1.setFillProperties(fill);
////        lineProperties.setFillProperties(new XDDFSolidFillProperties(XDDFColor.from(PresetColor.RED))); // Assuming newCh.getColor() returns a PresetColor
////        series1.setLineProperties(lineProperties);
//
//
//
//
//
//
////        newCh.setColor(PresetColor.RED);
//
////        if (newCh.getValues2() != null) {
////            final XDDFNumericalDataSource<? extends Number> valuesData2 =
////                    XDDFDataSourcesFactory.fromArray(newCh.getValues2().toArray(new Number[0]), valuesDataRange2, COLUMN_COUNTRIES);
//////            valuesData2.setFormatCode("General");
////
////            XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
////            series2.setTitle(newCh.getSeries().get(1), chart.setSheetTitle(newCh.getSeries().get(1), COLUMN_SPEAKERS));
////
////            XDDFLineProperties lineProperties1 = new XDDFLineProperties();
////            lineProperties1.setFillProperties(new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK))); // Assuming newCh.getColor() returns a PresetColor
////            XDDFSolidFillProperties fill1 = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLACK));
////            series1.setFillProperties(fill1);
////            series2.setLineProperties(lineProperties1);
////
////
////
//////            newCh.setColor(PresetColor.BLACK);
////
////        }
//
//        bar.setVaryColors(false);
//        bar.setBarDirection(BarDirection.COL);
//        bar.setGapWidth(300);
//        chart.plot(bar);
//
////        XDDFChartLegend legend = chart.getOrAddLegend();
////        legend.setPosition(LegendPosition.LEFT);
////        legend.setOverlay(false);
//
//        chart.setTitleText(newCh.getChartTitle());
//        chart.setTitleOverlay(false);
//        chart.setAutoTitleDeleted(false);
//
//        if (bottomAxis.hasNumberFormat()) bottomAxis.setNumberFormat("@");
//        if (leftAxis.hasNumberFormat()) leftAxis.setNumberFormat("#,##0.00");
//    }
//
//    private static final int COLUMN_LANGUAGES = 0;
//    private static final int COLUMN_COUNTRIES = 1;
//    private static final int COLUMN_SPEAKERS = 2;
//}

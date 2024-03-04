package Apache.PLS.chart;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class HoriZandle {
    private static NewCh readJsonFile() throws IOException {
        String string = "{\n" +
                "    \"chartTitle\": \"BarChart\",\n" +
                "    \"categories\": [\"Male\", \"Female\", \"Others\"],\n" +
                "    \"series\": [\"Number of Participants\", \"Category\"],\n" +
                "    \"values1\": [10, 30, 5],\n" +
                "    \"values2\":[29,33,33]\n" +
                "  }\n" +
                "  ";
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(string, NewCh.class);
    }


    public static void main(String[] args) throws Exception {
        NewCh newCh = readJsonFile();
        XWPFDocument doc = new XWPFDocument();

        OutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Downloads\\HoriZandle.docx");
        XWPFChart chart = doc.createChart(XDDFChart.DEFAULT_WIDTH * 10, XDDFChart.DEFAULT_HEIGHT * 5);
        setBarData(chart, newCh);
        doc.write(out);

    }

    private static void setBarData(XWPFChart chart, NewCh newCh) {
        XDDFChartAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle(newCh.getSeries().get(0));

        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
        leftAxis.setMajorTickMark(AxisTickMark.OUT);
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
        leftAxis.getOrAddMinorGridProperties();
        leftAxis.getOrAddShapeProperties();
        leftAxis.setVisible(true);
        final int numOfPoints = newCh.getCategories().size();
        final String categoryDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_LANGUAGES, COLUMN_LANGUAGES));
        final String valuesDataRange = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_COUNTRIES, COLUMN_COUNTRIES));
        final String valuesDataRange2 = chart.formatRange(new CellRangeAddress(1, numOfPoints, COLUMN_SPEAKERS, COLUMN_SPEAKERS));
        String[] s = newCh.getCategories().toArray(new String[0]);
        final XDDFDataSource<?> categoriesData = XDDFDataSourcesFactory.fromArray(s, categoryDataRange, COLUMN_LANGUAGES);

        final XDDFNumericalDataSource<? extends Number> valuesData = XDDFDataSourcesFactory.fromArray(newCh.getValues1().toArray(new Number[0]), valuesDataRange, COLUMN_COUNTRIES);
        valuesData.setFormatCode("General");
        newCh.getValues1().set(0, 16.0);


        XDDFChartData bar = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
//        bar.setBarGrouping(BarGrouping.CLUSTERED);

        XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData);
        series1.setTitle(newCh.getSeries().get(0), chart.setSheetTitle(newCh.getSeries().get(0), COLUMN_COUNTRIES));
        byte[] colors = new byte[]{(byte) 3, (byte) 155, (byte) 229};

        int pointCount = series1.getCategoryData().getPointCount();
        for (int p = 0; p < pointCount; p++) {
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).addNewDPt().addNewIdx().setVal(p);
            chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(0).getDPtArray(p)
                    .addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(colors);
        }
        if (newCh.getValues2() != null) {
            final XDDFNumericalDataSource<? extends Number> valuesData2 =
                    XDDFDataSourcesFactory.fromArray(newCh.getValues2().toArray(new Number[0]), valuesDataRange2, COLUMN_SPEAKERS);
            valuesData2.setFormatCode("General");

            XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(categoriesData, valuesData2);
            series2.setTitle(newCh.getSeries().get(1), chart.setSheetTitle(newCh.getSeries().get(1), COLUMN_SPEAKERS));

            byte[] colors1 = new byte[]{(byte) 0, (byte) 150, (byte) 136};
            int pointCount1 = series2.getCategoryData().getPointCount();
            for (int p = 0; p < pointCount1; p++) {
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(1).addNewDPt().addNewIdx().setVal(p);
                chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(1).getDPtArray(p)
                        .addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(colors1);
            }
        }
        bar.setVaryColors(false);
//        bar.setBarDirection(BarDirection.COL);
//        bar.setGapWidth(100);

        chart.plot(bar);

        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);

        chart.setTitleText(newCh.getChartTitle());
        chart.setTitleOverlay(false);
        chart.setAutoTitleDeleted(false);

        if (bottomAxis.hasNumberFormat()) bottomAxis.setNumberFormat("@");
        if (leftAxis.hasNumberFormat()) leftAxis.setNumberFormat("#,##0.00");
    }

    private static final int COLUMN_LANGUAGES = 0;
    private static final int COLUMN_COUNTRIES = 1;
    private static final int COLUMN_SPEAKERS = 2;
}



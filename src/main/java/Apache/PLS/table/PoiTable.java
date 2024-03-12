package Apache.PLS.table;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.*;

public class PoiTable {
    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument();

        FileOutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Desktop\\APACHE\\TABLE.docx");

        MLResponse mlResponse = new MLResponse();
        Map<String, String> tableJson1 = mlResponse.getTableJson();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> tableJson = objectMapper.convertValue(tableJson1, new TypeReference<>() {});
//getParaText(document,mlResponse);
        XWPFTable table = document.createTable(tableJson.size(), 2);
        table.setTableAlignment(TableRowAlign.LEFT);
        table.setWidth("90%");

//        table.getRow(0).setHeight(999);
        table.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewTcW().setW("39%"); // Sets the first column to 4500
        table.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewTcW().setW("61%");  // Sets the second column to 9000

        int rowIndex = 0;

        for (Map.Entry<String, String> entry : tableJson.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            XWPFTableRow row = table.getRow(rowIndex);
            if (row == null) {
                row = table.createRow();
            }
//            if (rowIndex % 2 == 0) {
//                setRowColor(row, "91afee");
//            } else {
//                setRowColor(row, "bbd5fe");
//            }

            XWPFParagraph paragraph1 = row.getCell(0).getParagraphs().get(0);
            String trimmedText1 = StringUtils.normalizeSpace(key);

            paragraph1.setAlignment(ParagraphAlignment.LEFT);
            paragraph1.setIndentationLeft(100);
            paragraph1.setIndentationRight(100);
            paragraph1.setSpacingBefore(100);
            XWPFRun run = paragraph1.createRun();
            run.setBold(true);
            run.setText(trimmedText1);
            run.setFontSize(12);
            run.setFontFamily("Times New Roman");


            if (StringUtils.containsIgnoreCase(key,"Date of this Report")) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
                String currentDate = dateFormat.format(new Date());
                XWPFParagraph paragraph = row.getCell(1).getParagraphs().get(0);
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                paragraph.setIndentationLeft(100);
                paragraph.setIndentationRight(100);
                paragraph.setSpacingBefore(100);
                XWPFRun run1 = paragraph.createRun();
                run1.setText(currentDate);
                run1.setFontSize(12);
                run1.setFontFamily("Times New Roman");

//                row.getCell(1).setText(currentDate);

            } else {
                String trimmedText = StringUtils.normalizeSpace(value);
                XWPFParagraph paragraph = row.getCell(1).getParagraphs().get(0);
                paragraph.setAlignment(ParagraphAlignment.LEFT);
                paragraph.setIndentationLeft(100);
                paragraph.setSpacingBefore(100);
                paragraph.setIndentationRight(100);
                XWPFRun run1 = paragraph.createRun();
                run1.setText(trimmedText);
                run1.setFontSize(12);
                run1.setFontFamily("Times New Roman");
//                row.getCell(1).setText(trimmedText);
            }
            rowIndex++;
        }


        document.write(out);
        out.close();
    }
    private static void getParaText(XWPFDocument document, MLResponse mlResponse) {
        if (Objects.nonNull(mlResponse.getParaText())) {
            List<String> lines = Arrays.stream( mlResponse.getParaText().split("\n")).toList();
            lines.forEach(s -> {
                XWPFParagraph paragraph = document.createParagraph();
                XWPFRun xwpfRun = paragraph.createRun();
                xwpfRun.setText(s);
                xwpfRun.setFontSize(12);
                xwpfRun.setFontFamily("Times New Roman");
            });

            }
        }


    private static void setRowColor(XWPFTableRow row, String color) {
        for (XWPFTableCell cell : row.getTableCells()) {
            cell.setVerticalAlignment(XWPFTableCell.XWPFVertAlign.CENTER);
            CTTcPr tcpr = cell.getCTTc().getTcPr();
            if (tcpr == null) tcpr = cell.getCTTc().addNewTcPr();
            CTShd ctshd = tcpr.isSetShd() ? tcpr.getShd() : tcpr.addNewShd();
            ctshd.setColor("auto");
            ctshd.setVal(STShd.CLEAR);
            ctshd.setFill(color);
        }
    }
}

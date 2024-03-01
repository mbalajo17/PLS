package Apache.PLS.table;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class LinkTable {
    public static void main(String[] args) throws IOException {

        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Desktop\\APACHE\\TABLEDATA.docx");

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun xwpfRun = paragraph.createRun();
        xwpfRun.setText("To learn more about this study, you can find further information on these websites:");
        MLResponse mlResponse = new MLResponse();
        Map<String, String> tableJson1 = mlResponse.getTableJson();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> tableJson = objectMapper.convertValue(tableJson1, new TypeReference<>() {
        });

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


            XWPFParagraph paragraph1 = row.getCell(0).getParagraphs().get(0);
            String trimmedText1 = StringUtils.normalizeSpace(key);

            paragraph1.setAlignment(ParagraphAlignment.LEFT);
            paragraph1.setIndentationLeft(100);
            paragraph1.setIndentationRight(100);
            paragraph1.setSpacingAfter(0);
            row.getCell(0).setText(trimmedText1);


            String trimmedText = StringUtils.normalizeSpace(value);
            paragraph = row.getCell(1).getParagraphs().get(0);
            paragraph.setAlignment(ParagraphAlignment.LEFT);
            paragraph.setIndentationLeft(100);
            paragraph.setIndentationRight(100);
            paragraph.setSpacingAfter(0);

            row.getCell(1).setText(trimmedText);

            rowIndex++;
        }


        document.write(out);
        out.close();

    }
}

package Apache.PLS.Practice;

import org.apache.poi.common.usermodel.PictureType;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;

public class Prac {

    static String subTitleStyleId;

    // run mvn package or compile before you run main method.
    public static void main(String[] args) throws IOException, InvalidFormatException {
        try (XWPFDocument doc = new XWPFDocument()) {
            // If you are a Windows user, you can modify the document save path to a Windows file path.
            try (FileOutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Downloads\\fix.docx")) {
                genTitle(doc);
                subTitleStyleId = genStyleForSubTitle(doc);
                genSubTitle("The paragraph and text", doc);
                genParagraph1(doc);
                genParagraph2(doc);
                genSubTitle("The Table", doc);
                genParagraphForTable(doc);
                genTable(doc);
                genSubTitle("The Image", doc);
                genParagraphForImage(doc);
//                genImage(doc);
                genSubTitle("The Chart", doc);
                genChart(doc);
                doc.write(out);
            }
        }
    }

    private static void genChart(XWPFDocument doc) throws IOException, InvalidFormatException {
        new BarChartRenderer().render(doc);
    }

    // If you encounter a NullPointerException, please run "mvn compile" first and then execute the main method.
//    private static void genImage(XWPFDocument doc) throws IOException, InvalidFormatException {
//        XWPFParagraph p = doc.createParagraph();
//        XWPFRun run = p.createRun();
//        try (InputStream in = Prac.class.getResourceAsStream("/g.png")) {
//            run.addPicture(in, PictureType.JPEG, "img", (int) (Units.EMU_PER_CENTIMETER * 3), (int) (Units.EMU_PER_CENTIMETER * 3));
//        }
//    }

    private static void genParagraphForImage(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run = p.createRun();
        run.setText("This is a PNG image, with dimensions of 3cm x 3cm.");
    }

    private static void genTable(XWPFDocument doc) {
        XWPFTable table = doc.createTable(3, 2);

        // borders
        table.setInsideHBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");
        table.setInsideVBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");
        table.setLeftBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");
        table.setRightBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");
        table.setTopBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");
        table.setBottomBorder(XWPFTable.XWPFBorderType.DOTTED, 4, 0, "000000");

        XWPFTableRow row1 = table.getRow(0);
        row1.getCell(0).setText("This is a merged cell with the fill color of orange");
        row1.getCell(0).setColor("ffa500");
        table.getRow(0).getCell(0).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.RESTART);
        table.getRow(0).getCell(1).getCTTc().addNewTcPr().addNewHMerge().setVal(STMerge.CONTINUE);

        XWPFTableRow row2 = table.getRow(1);
        row2.getCell(0).setText("Row 2 cell 1");
        row2.getCell(1).setText("Row 2 cell 2");

        XWPFTableRow row3 = table.getRow(2);
        XWPFTableCell cell = row3.getCell(0);
        XWPFParagraph p = cell.getParagraphs().get(0);
        runWithColorText(p, "Row 3 cell 1", "FF0000");
        row3.getCell(1).setText("Row 3 cell 2");
    }

    private static void genParagraphForTable(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run = p.createRun();
        run.setText("This is a table with the dotted border. The table has three rows, each row containing two cells. In the first row, the two cells are merged into one cell, which has an orange fill color. ");
    }

    private static void genParagraph2(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run = p.createRun();
        run.setText("This is another paragraph where the text size is 14pt.");
        run.setFontSize(14);
    }

    private static void genSubTitle(String title, XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        p.setStyle(subTitleStyleId);
        XWPFRun run = p.createRun();
        run.setText(title);
    }

    private static String genStyleForSubTitle(XWPFDocument doc) {
        XWPFStyles styles = doc.createStyles();
        CTStyle ctStyleHeading1 = CTStyle.Factory.newInstance();
        // 样式 id
        String styleId = "heading2";
        ctStyleHeading1.setStyleId(styleId);
        CTString styleName = CTString.Factory.newInstance();
        // 样式名称
        styleName.setVal("heading 2");
        ctStyleHeading1.setName(styleName);

        CTRPr rPr = ctStyleHeading1.addNewRPr();
        // 加粗
        rPr.addNewB().setVal(true);
        // 字体大小
        rPr.addNewSz().setVal(new BigInteger("32"));
        rPr.addNewSpacing().setVal(new BigInteger("32"));
        CTFonts ctFonts = rPr.addNewRFonts();
        // 字体名称
        ctFonts.setCs("Calibri");
        ctFonts.setAscii("Calibri");
        ctFonts.setHAnsi("Calibri");
        CTPPrGeneral pPr = ctStyleHeading1.addNewPPr();
        pPr.addNewOutlineLvl().setVal(new BigInteger("1"));

        XWPFStyle heading1Style = new XWPFStyle(ctStyleHeading1);
        heading1Style.setType(STStyleType.PARAGRAPH);
        // 加入 XWPFStyles 中管理
        styles.addStyle(heading1Style);
        return styleId;
    }

    private static void genParagraph1(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        XWPFRun run1 = p.createRun();
        runWithText(p, "This is a paragraph that contains ");
        runWithColorText(p, "a segment of red text", "FF0000");
        runWithText(p, ", ");
        runWithColorText(p, "a segment of blue text", "0070c0");
        runWithText(p, ", ");
        runWithColorText(p, "a segment of green text", "00b050");
        runWithText(p, ", ");
        runWithText(p, "and ");
        runWithBoldText(p, "a segment of bold text");
        runWithText(p, ".");
    }

    private static void runWithText(XWPFParagraph p, String text) {
        XWPFRun run = p.createRun();
        run.setText(text);
    }

    private static void runWithColorText(XWPFParagraph p, String text, String color) {
        XWPFRun run = p.createRun();
        run.setText(text);
        run.setColor(color);
    }

    private static void runWithBoldText(XWPFParagraph p, String text) {
        XWPFRun run = p.createRun();
        run.setText(text);
        run.setBold(true);
    }

    private static void genTitle(XWPFDocument doc) {
        XWPFParagraph p = doc.createParagraph();
        CTPPr pPr = p.getCTP().addNewPPr();
        CTDecimalNumber lvl = pPr.addNewOutlineLvl();
        lvl.setVal(new BigInteger("0"));
        XWPFRun run = p.createRun();
        run.setBold(true);
        run.setText("This is the title");
        run.setFontSize(22);
        p.setAlignment(ParagraphAlignment.CENTER);
    }
}

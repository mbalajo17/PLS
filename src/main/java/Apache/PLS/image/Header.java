package Apache.PLS.image;

import org.apache.poi.util.Units;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.util.List;

public class Header {
    public static void main(String[] args) throws IOException {
        XWPFDocument document = new XWPFDocument();
        addHeaderFooterWithTable(document,"name");

        FileOutputStream out = new FileOutputStream("C:\\Users\\balajimohan.SYMBIANCE\\Desktop\\APACHE\\HEADER.docx");

        document.write(out);

    }
    public static void addHeaderFooterWithTable(XWPFDocument document, String studyName) {
        try {

            String imgFile = "C:\\Users\\balajimohan.SYMBIANCE\\Pictures\\new.png";
            FileInputStream is = new FileInputStream(imgFile);

            XWPFHeaderFooterPolicy headerFooterPolicy = document.createHeaderFooterPolicy();
            XWPFHeader hdr = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
            XWPFTable tbl = hdr.createTable(1, 2);
            tbl.setTableAlignment(TableRowAlign.LEFT);
            initializeTableTrackPropBeforeDoc(tbl, true);

            tbl.setCellMargins(28, 30, 28, 28);

            tbl.setWidth((int) (6.5 * 1440));

            XWPFTableRow row = tbl.getRow(0);
            XWPFTableCell cell = row.getCell(0);
            XWPFParagraph p = cell.getParagraphArray(0);
            XWPFRun r = p.createRun();
            r.setText(studyName);
            cell = row.getCell(1);
            p = cell.getParagraphArray(0);
            p.setAlignment(ParagraphAlignment.RIGHT);
            r = p.createRun();
//            int width=38;
//            int
            r.addPicture(is, Document.PICTURE_TYPE_PNG, studyName, Units.toEMU(107), Units.toEMU(33));


            BigInteger pos1 = BigInteger.valueOf(4500);
            BigInteger pos2 = BigInteger.valueOf(9000);
            /**
             * Footer *
             */
            String footerText = "Confidential";

            XWPFFooter footer = headerFooterPolicy.createFooter(XWPFHeaderFooterPolicy.DEFAULT);
            XWPFParagraph fp = footer.createParagraph();
            fp.getCTP().addNewPPr().addNewPStyle().setVal("Footer");
            fp.getCTP().getPPr().addNewRPr();
            XWPFRun fr = fp.createRun();
            fr.getCTR().addNewRPr();
            fr.setText(footerText);
            fr.addTab();
            fp.getCTP().addNewFldSimple().setInstr("PAGE \\* MERGEFORMAT");
            fp.createRun().setText(" of ");
            fp.getCTP().addNewFldSimple().setInstr("NUMPAGES \\* MERGEFORMAT");
            XWPFRun fr1 = fp.createRun();
            fr1.addTab();
            fr1.setText("");

            setTabStop(fp, STTabJc.Enum.forString("center"), pos1);
            setTabStop(fp, STTabJc.Enum.forString("right"), pos2);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public static void setTabStop(XWPFParagraph oParagraph, STTabJc.Enum oSTTabJc, BigInteger oPos) {
        CTP oCTP = oParagraph.getCTP();
        CTPPr oPPr = oCTP.getPPr();
        if (oPPr == null) {
            oPPr = oCTP.addNewPPr();
        }

        CTTabs oTabs = oPPr.getTabs();
        if (oTabs == null) {
            oTabs = oPPr.addNewTabs();
        }

        CTTabStop oTabStop = oTabs.addNewTab();
        oTabStop.setVal(oSTTabJc);
        oTabStop.setPos(oPos);
    }

    public static void initializeTableTrackPropBeforeDoc(XWPFTable xwpfTable, boolean b) {
        CTTbl tableCTTbl = xwpfTable.getCTTbl();
        CTTblPr ctTblPr = tableCTTbl.getTblPr();
        CTTblWidth tblW = ctTblPr.getTblW();

        tblW.setW(BigInteger.valueOf(5000));
        tblW.setType(STTblWidth.PCT);
        ctTblPr.setTblW(tblW);
        tableCTTbl.setTblPr(ctTblPr);
        CTTblBorders borders = ctTblPr.getTblBorders();
        CTBorder brd = CTBorder.Factory.newInstance();
        brd.setSz(BigInteger.valueOf(4));
        brd.setSpace(BigInteger.ZERO);
        brd.setVal(b ? STBorder.NONE : STBorder.SINGLE);
        brd.setColor("auto");
        borders.setTop(brd);
        borders.setBottom(brd);
        borders.setLeft(brd);
        borders.setRight(brd);
        borders.setInsideH(brd);
        borders.setInsideV(brd);
    }

}

package common.system;



import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


public class WordConverter {

    public static String converter(String filePath) {

        String content = "";
        try {
            if (filePath.toLowerCase().endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(filePath));
                WordExtractor ex = new WordExtractor(is);
                content = ex.getText();
                ex.close();
                is.close();
            } else if (filePath.endsWith(".docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                content = extractor.getText();
                extractor.close();

            } else {
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;

    }

    public String converterDoc(String filePath) {
        return null;
    }

    public String converterDocx(String filePath) {
        try{
            XWPFDocument document = new XWPFDocument(new FileInputStream(filePath));
            XWPFWordExtractor we = new XWPFWordExtractor(document);
            return we.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

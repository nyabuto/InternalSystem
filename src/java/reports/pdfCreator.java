/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

/**
 *
 * @author Geofrey Nyabuto
 */

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
 
public class pdfCreator {
 
    public static void main(String[] args) {
        try {
            OutputStream file = new FileOutputStream(new File("F:\\Test.pdf"));
 
            Document document = new Document();
            PdfWriter.getInstance(document, file);
 
            document.open();
            document.add(new Paragraph("Hello World, iText"));
            document.add(new Paragraph(new Date().toString()));
 
            document.close();
            file.close();
 
        } catch (Exception e) {
 
            e.printStackTrace();
        }
    }
}


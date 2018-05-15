/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickreports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 *
 * @author EKaunda
 */
public class masterlist extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();

        /* TODO output your page here. You may use following sample code. */
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
        HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        HSSFFont font2 = wb.createFont();
        font2.setFontName("Cambria");
        font2.setColor((short) 0000);
        CellStyle style2 = wb.createCellStyle();
        style2.setFont(font2);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFCellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("Masterlist");

        String year="";
        
        if(request.getParameter("year")!=null){
        
            year=request.getParameter("year");
        
        }
        dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue("APHIAPLUS Nuru Ya Bonde Supported Sites Summary "+year);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,3));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_masterlist_summary('2015-10-01','2016-09-30','"+year+"')";

        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                if(a > 0){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }

        
        
        
        
        HSSFRow rw01=shet.createRow(count1+1);
        HSSFCell cell1 = rw01.createCell(0);
                    cell1.setCellValue("APHIAPLUS Nuru Ya Bonde supported sites details "+year);
                    cell1.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(count1+1, count1+1, 0, 3));
        
        
        
        
        //========Query two====Facility Details==============
        
        String qry = "call rpt_masterlist('2015-10-01','2016-09-30','"+year+"')";

        conn.rs = conn.st.executeQuery(qry);

         metaData = conn.rs.getMetaData();
         columnCount = metaData.getColumnCount();
        int count = count1+3;
        ArrayList mycolumns = new ArrayList();

        while (conn.rs.next()) {

            if (count == (count1+3)) {
//header rows
                HSSFRow rw = shet.createRow(count);
                rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) {

                    mycolumns.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count);

            for (int a = 0; a < columnCount; a++) {
                //System.out.print(mycolumns.get(a) + ":" + conn.rs.getString("" + mycolumns.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                String value = conn.rs.getString("" + mycolumns.get(a));
                if(isNumeric(value) && value.contains(".")){
                
                    cell0.setCellValue(Double.parseDouble(value));
                   }
                else if(isNumeric(value) && !value.contains(".")){
                
                    cell0.setCellValue(Integer.parseInt(value));
                   }
                else {
                    cell0.setCellValue(value);
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count++;
        }

        
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        shet.setAutoFilter(new CellRangeAddress(count1+3, count - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(4, 14);

        
        if(conn.rs!=null){conn.rs.close();}
        if(conn.rs1!=null){conn.rs1.close();}
        if(conn.st!=null){conn.st.close();}
        if(conn.st1!=null){conn.st1.close();}
        
        
        IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();

        System.out.println("" + "MasterList_Gen_" + createdOn.trim() + ".xls");

        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=" + "MasterList_Gen_" + createdOn.trim() + ".xls");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(masterlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(masterlist.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
}

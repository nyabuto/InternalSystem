/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import java.sql.CallableStatement;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author GNyabuto
 */
public class ewi extends HttpServlet {
HttpSession session;
String value;
String yearmonth,month,start_date,end_date;
CallableStatement cs = null;
int row;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        dbConn conn = new dbConn();
        
        row = 0;
        
       yearmonth = request.getParameter("yearmonth");
//      yearmonth ="2018-11";
       String ym_array[] = yearmonth.split("-");
       
       month = ym_array[1];
       
       
         Calendar gc = new GregorianCalendar();
        gc.set(Calendar.MONTH, (Integer.parseInt(month)-1));
        gc.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = gc.getTime();
        gc.add(Calendar.MONTH, 1);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        Date monthEnd = gc.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd");

        System.out.println("Calculated month start date : " + format.format(monthStart)+"<br>");
        String month_end = format.format(monthEnd);
       
        
        start_date = yearmonth+"-01";
        end_date = yearmonth+"-"+month_end;
        
        
          XSSFWorkbook wb = new XSSFWorkbook();     
              
         XSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)18);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(XSSFCellStyle.ALIGN_CENTER);
    
    XSSFCellStyle styleHeader = wb.createCellStyle();
    styleHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
    styleHeader.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    styleHeader.setBorderTop(BorderStyle.THIN);
    styleHeader.setBorderBottom(BorderStyle.THIN);
    styleHeader.setBorderLeft(BorderStyle.THIN);
    styleHeader.setBorderRight(BorderStyle.THIN);
    styleHeader.setAlignment(HorizontalAlignment.CENTER);
    
    XSSFFont fontHeader = wb.createFont();
    fontHeader.setColor(HSSFColor.BLACK.index);
    fontHeader.setBold(true);
    fontHeader.setFamily(FontFamily.MODERN);
    fontHeader.setFontName("Cambria");
    fontHeader.setFontHeight(15);
    styleHeader.setFont(fontHeader);
    styleHeader.setWrapText(true);
    
    XSSFCellStyle stylex = wb.createCellStyle();
    stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    stylex.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    stylex.setBorderTop(BorderStyle.THIN);
    stylex.setBorderBottom(BorderStyle.THIN);
    stylex.setBorderLeft(BorderStyle.THIN);
    stylex.setBorderRight(BorderStyle.THIN);
    stylex.setAlignment(HorizontalAlignment.LEFT);
    
    XSSFFont fontx = wb.createFont();
    fontx.setColor(HSSFColor.BLACK.index);
    fontx.setBold(true);
    fontx.setFamily(FontFamily.MODERN);
    fontx.setFontName("Cambria");
    stylex.setFont(fontx);
    stylex.setWrapText(true);
    
    XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(BorderStyle.THIN);
    stborder.setBorderBottom(BorderStyle.THIN);
    stborder.setBorderLeft(BorderStyle.THIN);
    stborder.setBorderRight(BorderStyle.THIN);
    stborder.setAlignment(HorizontalAlignment.LEFT);
    
    XSSFFont font_cell=wb.createFont();
    font_cell.setColor(HSSFColor.BLACK.index);
    font_cell.setFamily(FontFamily.MODERN);
    font_cell.setFontName("Cambria");
    stborder.setFont(font_cell);
    stborder.setWrapText(true);     
      
        
     XSSFSheet sheet= wb.createSheet("EWI DATA FOR "+yearmonth); 
     
      cs = (CallableStatement) conn.conn.prepareCall("{call ewi_data(?,?)}");
    cs.setString(1, start_date);
    cs.setString(2, end_date);
    cs.execute();
   
        conn.rs = cs.getResultSet();
      ResultSetMetaData metaData = conn.rs.getMetaData();
      int col_count = metaData.getColumnCount(); //number of column
       
      XSSFRow RowTitles = sheet.createRow(row);
      
      for(int i=0;i<col_count; i++){
      value = metaData.getColumnLabel(i+1);
      XSSFCell cell = RowTitles.createCell(i);
      if(isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
      else{cell.setCellValue(value);}
      cell.setCellStyle(stylex);
      }
   
   while (conn.rs.next()) {
    row++;
    XSSFRow RowData = sheet.createRow(row);
    
    for(int i=0;i<col_count; i++){ // read and output data
          value = conn.rs.getString(i+1);
      XSSFCell cell = RowData.createCell(i);
      if(isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
      else{cell.setCellValue(value);}
      cell.setCellStyle(stborder);
      }
    
    } 
     
     
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte [] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=EWI_DATA_FOR___"+yearmonth.replace("-", "_")+".xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();      
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(ewi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(ewi.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
}

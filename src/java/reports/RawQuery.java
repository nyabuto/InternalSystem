/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
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
public class RawQuery extends HttpServlet {
HttpSession session;
String query,query_original,message;
int row,errors;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        dbConn conn = new dbConn();
        session = request.getSession();
        
        
       query_original = request.getParameter("query");
       
       query = query_original.toLowerCase();
       
       
       
       row=errors=0;
       message = "";
       
       XSSFWorkbook wb = new XSSFWorkbook();

        XSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 16);
        font.setFontName("Cambria");
        font.setColor((short) 0000);

        CellStyle style = wb.createCellStyle();
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
        fontHeader.setFontHeight(13);
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

        XSSFFont font_cell = wb.createFont();
        font_cell.setColor(HSSFColor.BLACK.index);
        font_cell.setFamily(FontFamily.MODERN);
        font_cell.setFontName("Cambria");
        stborder.setFont(font_cell);
        stborder.setWrapText(true);

        XSSFSheet Sheet = wb.createSheet("Query Output");
        
       // check query
       if(query.contains("insert") || query.contains("update") || query.contains("replace") || query.contains("into")  || query.contains("user") || query.contains("drop") || query.contains("truncate")){
           System.out.println("This query is not allowed");
           errors++;
           message="Your are running a wrong query. Any query attempting to change data is disabled";
       }
       else{
if(query.contains("select ") || query.contains("call ")){
  XSSFRow RowTitles = Sheet.createRow(row);  
    try {
        conn.rs = conn.st.executeQuery(query);
       String value;
        //get headers
        ResultSetMetaData metaData = conn.rs.getMetaData();
            int col_count = metaData.getColumnCount(); //number of column

            for (int i = 0; i < col_count; i++) {
                Sheet.setColumnWidth(col_count, 5000);
                value = metaData.getColumnLabel(i + 1);
                XSSFCell cell = RowTitles.createCell(i);
                if (isNumeric(value)) {
                    cell.setCellValue(Integer.parseInt(value));
                } else {
                    cell.setCellValue(value);
                }
                cell.setCellStyle(stylex);
            }
        
        
          while (conn.rs.next()) {
               // System.out.println("Name : " + conn.rs.getString(3));
                row++;
                //System.out.println("Row pos : .. " + row);
                XSSFRow RowData = Sheet.createRow(row);

                for (int i = 0; i < col_count; i++) { // read and output data
                    value = conn.rs.getString(i + 1);
                    XSSFCell cell = RowData.createCell(i);

                    if (isNumeric(value)) {
                        try{
                        cell.setCellValue(Integer.parseInt(value));
                        cell.setCellStyle(stborder); 
                        }
                        catch(NumberFormatException nfe){ // output it as a string
                        cell.setCellValue(value);
                        cell.setCellStyle(stborder);  
                        }
                    } 
                    else {
                        cell.setCellValue(value);
                        cell.setCellStyle(stborder);
                    }

                }

            }
          
          for (int e = 0; e < col_count; e++) {
                Sheet.autoSizeColumn(e);
            }

            Sheet.setDisplayGridlines(false);
        
        //get data
        
        System.out.println("query a success"); 
          
        
        
    } catch (SQLException ex) {
        Logger.getLogger(RawQuery.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Incomplete query. response is: "+ex);
        errors++;
        message="You are running an incomplete query. Check your syntax and try again.<br><br>Error message is : "+ex;
    }
    
    
} // seems legit query

else{//
//   query missing important info
System.out.println("Nothing to be done");
errors++;
message="There is nothing to be executed in this query. Review it and try executing again.";
}
       }
    
    if(errors==0){
       session.removeAttribute("errors");
     
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=Query_Output.xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();     
    }
    
    else{
     session.setAttribute("errors", message);
     session.setAttribute("query", query_original);
     response.sendRedirect("RawQuery.jsp");
    }
       
        
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
        processRequest(request, response);
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
        processRequest(request, response);
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

    private static String removeLast(String str, int num) {
        return str.substring(0, str.length() - num);
    }
}

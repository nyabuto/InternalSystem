/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RawQuery;

import General.IdGenerator2;
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
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.extensions.XSSFCellBorder;

/**
 *
 * @author GNyabuto
 */
public class RawQueryUpdate extends HttpServlet {
HttpSession session;
String query,query_original,message;
int row,errors;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        dbConn conn = new dbConn();
        session = request.getSession();
        
        
       query_original = request.getParameter("query");
       
       query = query_original.toLowerCase();
       
       
       
       row=errors=0;
       message = "";
       
       //XSSFWorkbook wb = new XSSFWorkbook();
       
       SXSSFWorkbook wb = new SXSSFWorkbook(1000);
       

        Font font = wb.createFont();
        //font.setFontHeightInPoints((short) 16);
        font.setFontName("Daytona");
        font.setColor((short) 0000);

        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(XSSFCellStyle.ALIGN_CENTER);

        CellStyle styleHeader = wb.createCellStyle();
        styleHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
        styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
        styleHeader.setAlignment(HSSFCellStyle.VERTICAL_CENTER);
        
        styleHeader.setBottomBorderColor(HSSFColor.BLUE.index);
        styleHeader.setTopBorderColor(HSSFColor.BLUE.index);
        styleHeader.setLeftBorderColor(HSSFColor.BLUE.index);
        styleHeader.setRightBorderColor(HSSFColor.BLUE.index);
        

        Font fontHeader = wb.createFont();
        fontHeader.setColor(HSSFColor.BLACK.index);
        fontHeader.setBoldweight((short)3);
        //fontHeader.setFamily(FontFamily.MODERN);
        fontHeader.setFontName("Daytona");
        //fontHeader.setFontHeight((short)12);
        styleHeader.setFont(fontHeader);
        styleHeader.setWrapText(true);

        CellStyle stylex = wb.createCellStyle();
        stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        
        stylex.setBottomBorderColor(HSSFColor.GREEN.index);
        stylex.setTopBorderColor(HSSFColor.GREEN.index);
        stylex.setLeftBorderColor(HSSFColor.GREEN.index);
        stylex.setRightBorderColor(HSSFColor.GREEN.index);
        

       Font fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
       // fontx.setBoldweight(true);
        //fontx.setFamily(FontFamily.MODERN);
        fontx.setFontName("Daytona");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

       CellStyle stborder = wb.createCellStyle();
        stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stborder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
        
        stborder.setBottomBorderColor(HSSFColor.GREEN.index);
        stborder.setTopBorderColor(HSSFColor.GREEN.index);
        stborder.setLeftBorderColor(HSSFColor.GREEN.index);
        stborder.setRightBorderColor(HSSFColor.GREEN.index);

        Font font_cell = wb.createFont();
        font_cell.setColor(HSSFColor.BLACK.index);
       // font_cell.setFamily(FontFamily.MODERN);
        font_cell.setFontName("Daytona");
        stborder.setFont(font_cell);
        stborder.setWrapText(false);

        Sheet Sheet = wb.createSheet("IMIS Adhoc Query Output");
        
       // check query
       if( query.contains("drop") || query.contains("truncate")){
           System.out.println("This query is not allowed");
           errors++;
           message="Your are running a wrong query. Any query attempting to change data is disabled";
       }
       else{
if(query.contains("select ") || query.contains("call ")){
  Row RowTitles = Sheet.createRow(row);  
    try {
        conn.rs = conn.st.executeQuery(query);
       String value;
        //get headers
        ResultSetMetaData metaData = conn.rs.getMetaData();
            int col_count = metaData.getColumnCount(); //number of column

            for (int i = 0; i < col_count; i++) {
                Sheet.setColumnWidth(col_count, 5000);
                value = metaData.getColumnLabel(i + 1);
                Cell cell = RowTitles.createCell(i);
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
                Row RowData = Sheet.createRow(row);

                for (int i = 0; i < col_count; i++) { // read and output data
                    value = conn.rs.getString(i + 1);
                    Cell cell = RowData.createCell(i);

                    if (isNumeric(value)) {
                        try{
                        cell.setCellValue(Double.parseDouble(value));
                        cell.setCellStyle(stborder); 
                        }
                        catch(NumberFormatException nfe){ // output it as a string
                        cell.setCellValue(Double.parseDouble(value));
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
                //Sheet.autoSizeColumn(e,false);
            }

            Sheet.setDisplayGridlines(false);
        
        //get data
        
        System.out.println("Imis Adhoc query:: "+query_original); 
         
        if(session.getAttribute("username")!=null)
        {           
          System.out.println("Query run by : "+session.getAttribute("username").toString());
        
        }
        
        
    } catch (SQLException ex) {
        Logger.getLogger(RawQueryUpdate.class.getName()).log(Level.SEVERE, null, ex);
        System.out.println("Incomplete query. response is: "+ex);
        errors++;
        message="You are running an incomplete query. Check your syntax and try again.<br><br>Error message is : "+ex;
    }
    
    
} // seems legit query

else{//
//   query missing important info
System.out.println("doing update");


conn.st.executeUpdate(query);

errors++;
message="Query update completed successfully.";
}
       }
    
    if(errors==0){
       session.removeAttribute("errors");
     
        IdGenerator2 ig= new IdGenerator2();
        
        String dt=ig.timestamp().replace(":","_").replace(" ","").replace("-","_").replace(".","_");
       
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte[] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=IMIS_Adhoc_Query_"+dt+".xlsx");
        OutputStream outStream = response.getOutputStream();
        outStream.write(outArray);
        outStream.flush();     
    }
    
    else
    {
     session.setAttribute("errors", message);
     session.setAttribute("query", query_original);
     response.sendRedirect("RawQueryUpdate.jsp");
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
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(RawQueryUpdate.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(RawQueryUpdate.class.getName()).log(Level.SEVERE, null, ex);
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

    
//    public boolean isNumeric(String s) {
//        return s != null && s.matches("[-+]?\\d*\\.?\\d+");
//    }
    
     public static boolean isNumeric(String strNum) {
      
    
    try {
        double d = Double.parseDouble(strNum);
    } catch (NumberFormatException | NullPointerException nfe) {
        return false;
    }
    return true;
      
}

    private static String removeLast(String str, int num) {
        return str.substring(0, str.length() - num);
    }
}

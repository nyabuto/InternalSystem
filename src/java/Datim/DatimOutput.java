/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datim;

import com.mysql.jdbc.CallableStatement;
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
import org.apache.poi.ss.usermodel.ComparisonOperator;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.FontFamily;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.PatternFormatting;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.Region;
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
public class DatimOutput extends HttpServlet {
HttpSession session;
String stored_procedures,where_indicators;
String categories,indicators;
int has_data,year,prevYear,month,row,t_i;
int end_month=0;
String reportDuration,duration,period,excelDuration,semi_annual,quarter;
String start_date,end_date;
String current_procedure,value;
CallableStatement cs = null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        session = request.getSession();
        dbConn conn = new dbConn();
        
              categories = request.getParameter("service_area");
              indicators = request.getParameter("indicators");
        
                year=Integer.parseInt(request.getParameter("year"));
                reportDuration=request.getParameter("reportDuration");
        
                
                
                end_month=0;
           //SELECTED PERIOD     
                
                prevYear=year-1;
                start_date=end_date="";
                
         if(reportDuration.equals("1")){
             start_date=prevYear+"-10-01";
             end_date=year+"-09-end";
             end_month=9;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
       if(semi_annual.equals("1")){
           start_date=prevYear+"-10-01";
           end_date=year+"-03-end";
           end_month=3;
       }
           else{
           start_date=year+"-04-01";
             end_date=year+"-09-end";
             end_month=9;
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
          start_date=prevYear+"-"+startMonth+"-01";
          end_date=prevYear+"-"+endMonth+"-end";
          end_month=Integer.parseInt(endMonth);
      }
      else{
        start_date=year+"-"+startMonth+"-01";
        end_date=year+"-"+endMonth+"-end";
          end_month=Integer.parseInt(endMonth);
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
     String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
        start_date=prevYear+"-"+month+"-01";
        end_date=prevYear+"-"+month+"-end";
          end_month=month;
     }
     else{
    start_date=year+"-0"+month+"-01";
    end_date=year+"-0"+month+"-end";
          end_month=month;
     }
      }
      }
      else{
     duration="";     
      }
          
      
         Calendar gc = new GregorianCalendar();
        gc.set(Calendar.MONTH, (end_month-1));
        gc.set(Calendar.DAY_OF_MONTH, 1);
        Date monthStart = gc.getTime();
        gc.add(Calendar.MONTH, 1);
        gc.add(Calendar.DAY_OF_MONTH, -1);
        Date monthEnd = gc.getTime();
        SimpleDateFormat format = new SimpleDateFormat("dd");

        System.out.println("Calculated month start date : " + format.format(monthStart)+"<br>");
        String month_end = format.format(monthEnd);
         
         end_date = end_date.replace("end", month_end);
         
            
       // INDICATOR CHOICES         
              has_data=0;
              
              if(categories==null || categories.equals("")){
                where_indicators=" 1=1 ";    
                }
              
              if(!(indicators==null || indicators.equals(""))){
            String[] indic_data = request.getParameterValues("indicators");
               where_indicators = " (";
           has_data=0;
           for(String indic:indic_data){
            if(indic!=null && !indic.equals("")){
             where_indicators+=" indicator_code='"+indic+"' OR ";
             has_data++;
            }  
           }
           
           if(has_data>0){
           where_indicators = removeLast(where_indicators, 3);
           where_indicators+=")";
           }
           else{  
           } 
                System.out.println(has_data+" where : "+where_indicators);    
              // where = " WHERE district.DistrictID='"+sub_county+"' ";    
            }
                
                      
            else if(!(categories==null || categories.equals(""))){
                   String[] cats = request.getParameterValues("service_area"); 
                   where_indicators = " (";
                    has_data=0;
                    System.out.println("categories : "+cats.length);
                    for(String cat:cats){
                     if(cat!=null && !cat.equals("")){
                      where_indicators+=" category='"+cat+"' OR ";
                      has_data++;
                     }  
                    }

                    if(has_data>0){
                    where_indicators = removeLast(where_indicators, 3);
                    where_indicators+=")";
                    }
                    else{  
                    }
                   
                }
       
         XSSFWorkbook wb = new XSSFWorkbook();     
              
         XSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)16);
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
    
    XSSFFont font_cell=wb.createFont();
    font_cell.setColor(HSSFColor.BLACK.index);
    font_cell.setFamily(FontFamily.MODERN);
    font_cell.setFontName("Cambria");
    stborder.setFont(font_cell);
    stborder.setWrapText(true);
    
    
    
      
        System.out.println("start "+start_date+" end date: "+end_date);
              
        // get stored procedures      
       String get_stored_procedures = "SELECT indicator_code,stored_procedure,headers,merging FROM datim_output WHERE "+where_indicators+" AND stored_procedure IS NOT NULL";
       conn.rs2 = conn.st2.executeQuery(get_stored_procedures);
       while(conn.rs2.next()){
           row=0; 
           XSSFSheet sheet= wb.createSheet(conn.rs2.getString(1));
          stored_procedures=conn.rs2.getString(2)+"('"+start_date+"','"+end_date+"')"; 
          
           System.out.println("call "+stored_procedures);
     
      //  GET HEADERS FROM DB  
      String headers = conn.rs2.getString(3);
      String merging = conn.rs2.getString(4);
           if(headers!=null && !headers.equals("")){
               String headers_array[] = headers.split("@");
              
               for(String header:headers_array){ 
                System.out.println("header main:"+header); 
                   t_i=0;
                XSSFRow RowHeader = sheet.createRow(row);
                RowHeader.setHeightInPoints(30);   
               
                String header_elems_array[] = header.split(",");
                for(String header_value:header_elems_array){
                    
                 XSSFCell cell_h = RowHeader.createCell(t_i); 
                 cell_h.setCellValue(header_value);
                 cell_h.setCellStyle(styleHeader);
                 
                 t_i++;
                }
                
               row++;    
               }
           }
           
           
   XSSFRow RowTitles = sheet.createRow(row);
//   RowTitles.setHeightInPoints(30);
   
           System.out.println("start date "+start_date+" end date : "+end_date);
    cs = (CallableStatement) conn.conn.prepareCall("{call "+stored_procedures+"}");
//    cs.setString(1, start_date);
//    cs.setString(2, end_date);
    cs.execute();
   
        conn.rs = cs.getResultSet();
      ResultSetMetaData metaData = conn.rs.getMetaData();
      int col_count = metaData.getColumnCount(); //number of column
       
      
      for(int i=0;i<col_count; i++){
      value = metaData.getColumnLabel(i+1);
      XSSFCell cell = RowTitles.createCell(i);
      if(isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
      else{cell.setCellValue(value);}
      cell.setCellStyle(stylex);
      }
           System.out.println("Row pos : "+row);
   while (conn.rs.next()) {
    System.out.println("Name : " + conn.rs.getString(3));
    row++;
    System.out.println("Row pos : .. "+row);
    XSSFRow RowData = sheet.createRow(row);
    
       for (int i = 0; i < col_count; i++) { // read and output data
           value = conn.rs.getString(i + 1);
           XSSFCell cell = RowData.createCell(i);
           if (isNumeric(value)) {
               cell.setCellValue(Integer.parseInt(value));

               if (value.contains("-")) {
                  
                   cell.setCellStyle(styleHeader);
               } else {
                   cell.setCellStyle(stborder);
               }

           } else {
               cell.setCellValue(value);
               cell.setCellStyle(stborder);
           }
      
      }
    
    } 
      
   
   
              
           if(merging!=null){
               String headers_array[] = merging.split("@");
              
               for(String header:headers_array){
                String header_elems_array[] = header.split(",");
                   System.out.println("header:"+header);
                int row_start = Integer.parseInt(header_elems_array[0]);
                int row_end = Integer.parseInt(header_elems_array[1]);
                int col_start  = Integer.parseInt(header_elems_array[2]);       
                int col_end  = Integer.parseInt(header_elems_array[3]);       
                 
                sheet.addMergedRegion(new CellRangeAddress(row_start,row_end,col_start,col_end)); 
                
               row++;    
               }
               int ln=0;
               if(headers.contains("@")){
               ln=headers.split("@").length;
               }
               
               sheet.createFreezePane(5, ln+1);
           }
           else {
           sheet.createFreezePane(5, 1);
           }
           
           for (int e = 0; e < col_count; e++) {
               sheet.autoSizeColumn(e);
           }
           
           sheet.setDisplayGridlines(false);
           

           
       }
       
       
    
        ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
        wb.write(outByteStream);
        byte [] outArray = outByteStream.toByteArray();
        response.setContentType("application/ms-excel");
        response.setContentLength(outArray.length);
        response.setHeader("Expires:", "0"); // eliminates browser caching
        response.setHeader("Content-Disposition", "attachment; filename=DATIM_OUTPUT_REPORT.xlsx");
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
        Logger.getLogger(DatimOutput.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(DatimOutput.class.getName()).log(Level.SEVERE, null, ex);
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
   private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
}

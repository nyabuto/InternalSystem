/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FPT;

import static database.OSValidator.isUnix;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
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
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTTable;
import scripts.copytemplates;

/**
 *
 * @author GNyabuto
 */
public class FPT_excel_tracker extends HttpServlet {
    HttpSession session;
    String county,sub_county,facility,where_location,query,value;
    int has_data=0,row=0,row2=0;
    String yearmonths,yearmonth,reportDuration;
     int year,prevYear;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, InvalidFormatException {
       session = request.getSession();
          dbConn conn = new dbConn();
         
          
        county = request.getParameter("county");
        sub_county = request.getParameter("district");
        facility = request.getParameter("facility");
          
        year = Integer.parseInt(request.getParameter("year"));
        reportDuration = request.getParameter("reportDuration");
        
          
          
           if(county==null || county.equals("")){
                where_location=" 1=1 ";    
                }
            
        else if(facility!=null && !facility.equals("")){
         String[] facility_data = request.getParameterValues("facility");   
            where_location = " (";
           has_data=0;
           for(String fac:facility_data){
            if(fac!=null && !fac.equals("")){
             where_location+=" subpartnera.SubpartnerID='"+fac+"' OR ";
             has_data++;
            }  
           }
           
           if(has_data>0){
           where_location = removeLast(where_location, 3);
           where_location+=")";
           }
           else{   
           } 
              
        }
        else if(sub_county!=null && !sub_county.equals("")){
            String[] sub_county_data = request.getParameterValues("district");
               where_location = " (";
           has_data=0;
           for(String sct:sub_county_data){
            if(sct!=null && !sct.equals("")){
             where_location+=" district.DistrictID='"+sct+"' OR ";
             has_data++;
            }  
           }
           
           if(has_data>0){
           where_location = removeLast(where_location, 3);
           where_location+=")";
           }
           else{  
           } 
                System.out.println(has_data+" where : "+where_location);    
              // where = " WHERE district.DistrictID='"+sub_county+"' ";    
            }
                
                      
            else if(county!=null || !county.equals("")){
                   String[] county_data = request.getParameterValues("county"); 
                   where_location = " (";
                    has_data=0;
                    System.out.println("counties : "+county_data.length);
                    for(String ct:county_data){
                     if(ct!=null && !ct.equals("")){
                         
                      where_location+=" county.CountyID='"+ct+"' OR ";
                      has_data++;
                     }  
                    }

                    if(has_data>0){
                    where_location = removeLast(where_location, 3);
                    where_location+=")";
                    }
                    else{  
                    }
                   
                }
          
        if(!where_location.contains("1=1")){
        String all_loc_ids = where_location;
        where_location=",";
         //get mfls
        String getfacilis = "SELECT CentreSanteID FROM subpartnera LEFT JOIN district on subpartnera.DistrictID = district.DistrictID LEFT JOIN county ON county.CountyID=district.CountyID"
            + " WHERE "+all_loc_ids+" AND subpartnera.active=1 and ( subpartnera.HTC=1) ";
            System.out.println("get facility ids : "+getfacilis);
        conn.rs = conn.st.executeQuery(getfacilis);
        while(conn.rs.next()){
        where_location+=""+conn.rs.getString(1)+",";
        }
        }
        else{
            where_location=",";
            
         String getfacilis = "SELECT CentreSanteID FROM subpartnera LEFT JOIN district on subpartnera.DistrictID = district.DistrictID LEFT JOIN county ON county.CountyID=district.CountyID"
            + "  WHERE subpartnera.active=1 and (  subpartnera.HTC=1 )";
            System.out.println("get facility ids all : "+getfacilis);
        conn.rs = conn.st.executeQuery(getfacilis);
        while(conn.rs.next()){
        where_location+=""+conn.rs.getString(1)+",";
        }   
        }
  
        //SELECTED PERIOD     

        prevYear = year - 1;

        if (reportDuration.equals("1")) {
            yearmonth = prevYear+"10,"+prevYear+"11,"+prevYear+"12,"+year+"01,"+year+"02,"+year+"03,"+year+"04,"+year+"05,"+year+"06,"+year+"07,"+year+"08,"+year+"09";
        } else if (reportDuration.equals("2")) {
          String  semi_annual = request.getParameter("semi_annual");
            if (semi_annual.equals("1")) {
               yearmonth = prevYear+"10,"+prevYear+"11,"+prevYear+"12,"+year+"01,"+year+"02,"+year+"03";
            } else {
             yearmonth = year+"04,"+year+"05,"+year+"06,"+year+"07,"+year+"08,"+year+"09";
            }
        } else if (reportDuration.equals("3")) {
          String  quarter = request.getParameter("quarter");
         if (quarter.equals("1")) {
             yearmonth = prevYear+"10,"+prevYear+"11,"+prevYear+"12";
                }
         else if(quarter.equals("2")){
           yearmonth = year+"01,"+year+"02,"+year+"03";  
                }
         else if(quarter.equals("3")){
           yearmonth = year+"04,"+year+"05,"+year+"06";   
                }
         else if(quarter.equals("4")){
            yearmonth = year+"07,"+year+"08,"+year+"09";  
                }
            
        } else if (reportDuration.equals("4")) {
            String months[] = request.getParameterValues("month");
            yearmonth="";
            
            for(String month_:months){
              int  month = Integer.parseInt(month_);  
              if (month>= 10) {
                    yearmonth += prevYear+""+month+",";
                } else {
                yearmonth += year+"0"+month+",";
                }
            }
          yearmonth = removeLast(yearmonth, 1);
        } else {
        }    
          
        System.out.println("period: "+yearmonth+" facils: "+where_location);
        String period[] = yearmonth.split(",");
        
        
       
String allpath = getServletContext().getRealPath("/FPT_Tracker.xlsx");

XSSFWorkbook wb1;
 
String pathtodelete=null;

Date da= new Date();
String dat2 = da.toString().replace(" ", "_");

dat2 = dat2.toString().replace(":", "_");

String mydrive = allpath.substring(0, 1);

String np=mydrive+":\\HSDSA\\HTSRRI\\MACROS\\";

String filepath="FPT_Tracker_Report"+dat2+".xlsx";


if(isUnix())
{
    np="/HSDSA/HTSRRI/MACROS/";
}


 new File(np).mkdirs();

 np+=filepath;
 
//check if file exists
String sourcepath = getServletContext().getRealPath("/FPT_Tracker.xlsx");

File f = new File(np);
if(!f.exists()&& !f.isFile() ) {
    /* do something */
    
    copytemplates ct= new copytemplates();
    System.out.println("Copying macros..");
    ct.transfermacros(sourcepath,np);
    
}
else
    //copy the file alone  
{
    
    copytemplates ct= new copytemplates();
//copy the agebased file only
ct.copymacros(sourcepath,np);

}
File allpathfile= new File(np);

OPCPackage pkg = OPCPackage.open(allpathfile);

pathtodelete=np;


//wb = new XSSFWorkbook( OPCPackage.open(allpath) );
wb1 = new XSSFWorkbook(pkg);


//SXSSFWorkbook wb = new SXSSFWorkbook(wb1, 100);

XSSFWorkbook wb = wb1;



             //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
//    XSSFWorkbook wb=new XSSFWorkbook();
//    XSSFSheet shet1=wb.createSheet("Raw Data Report");
    XSSFSheet shet1= wb.getSheet("RawTracker");
    XSSFSheet shet2= wb.getSheet("FPT Missing Reports Raw");
    XSSFFont font=wb.createFont();
    font.setFontHeightInPoints((short)18);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    
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
        
    row=0;  
//    XSSFRow RowHeader = shet1.createRow(row);
//    RowHeader.setHeightInPoints(50);
      
         row=1;
         row2=1;
        for (String ymonth : period) {
            query = "call rpt_fpt_tracker("+ymonth+")";
            conn.rs = conn.st.executeQuery(query);
            System.out.println("Running Query : "+query);
            
            ResultSetMetaData metaData = conn.rs.getMetaData();
            int col_count = metaData.getColumnCount(); //number of column
            
            while (conn.rs.next()) {
                if(where_location.contains(conn.rs.getString("MFLCode"))){ // among the selected facilities
                    row++;
                    XSSFRow RowData = shet1.createRow(row);
                    for(int i=0;i<col_count; i++){ // read and output data
                        value = conn.rs.getString(i+1);
                        XSSFCell cell = RowData.createCell(i);
                        if(isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
                        else{cell.setCellValue(value);}
                        cell.setCellStyle(stborder);
                    }
                }
            } 
//            end
           // for the summary 
            
           String query_summary = "call rpt_fpt_tracker_general("+ymonth+")"; // only shows missing reports
            conn.rs = conn.st.executeQuery(query_summary);
            System.out.println("Running Query summary : "+query_summary);
            
            metaData = conn.rs.getMetaData();
            col_count = metaData.getColumnCount(); //number of column
            
            while (conn.rs.next()) {
                if(where_location.contains(conn.rs.getString("MFLCode"))){ // among the selected facilities
                    row2++;
                    XSSFRow RowData = shet2.createRow(row2);
                    for(int i=0;i<col_count; i++){ // read and output data
                        value = conn.rs.getString(i+1);
                        XSSFCell cell = RowData.createCell(i);
                        if(isNumeric(value)){cell.setCellValue(Integer.parseInt(value));}
                        else{cell.setCellValue(value);}
                        cell.setCellStyle(stborder);
                    }
                }
            }
            
            
            
            
        }
    
        
        if(1==1){// for sections
    // XSSFSheet shet2= wb.getSheet("Raw Data Report");
        // tell your xssfsheet where its content begins and where it ends
((XSSFSheet)shet1).getCTWorksheet().getDimension().setRef("A1:L" + (shet1.getLastRowNum() + 1));

CTTable ctTable = ((XSSFSheet)shet1).getTables().get(0).getCTTable();

ctTable.setRef("A1:L" + (shet1.getLastRowNum() + 1)); // adjust reference as needed
    
        }
        
        
        if(1==1){ // for summary
    // XSSFSheet shet2= wb.getSheet("Raw Data Report");
        // tell your xssfsheet where its content begins and where it ends
((XSSFSheet)shet2).getCTWorksheet().getDimension().setRef("A1:J" + (shet2.getLastRowNum() + 1));

CTTable ctTable = ((XSSFSheet)shet2).getTables().get(0).getCTTable();

ctTable.setRef("A1:J" + (shet2.getLastRowNum() + 1)); // adjust reference as needed
    
        }
        
   
    ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
    wb.write(outByteStream);
    byte [] outArray = outByteStream.toByteArray();
    response.setContentType("application/ms-excel");
    response.setContentLength(outArray.length);
    response.setHeader("Expires:", "0"); // eliminates browser caching
    response.setHeader("Set-Cookie:", "fileDownload=true; path=/"); // set cookie header
    response.setHeader("Content-Disposition", "attachment; filename=FPT_Tracker_Report.xlsx");
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
            Logger.getLogger(FPT_excel_tracker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(FPT_excel_tracker.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(FPT_excel_tracker.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(FPT_excel_tracker.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GapAnalysis;

import General.IdGenerator;
import database.OSValidator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import scripts.copytemplates;

/**
 *
 * @author Emmanuel E
 */
public class gapAnalysis extends HttpServlet {

String pathtodelete=null;
 int no_months = 0;  
 String[] columns =  {"rule","gap","program_area","county","sub_county","facility","year","month","ward","latitude","longitude","explanation"};
 ArrayList periods = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //PrintWriter out = response.getWriter();
        try {
        
            ArrayList keyal= new ArrayList();
            ArrayList countyal= new ArrayList();
            ArrayList scountyal= new ArrayList();
            ArrayList facilal= new ArrayList();
            ArrayList yearmonthal= new ArrayList();
            ArrayList monthal= new ArrayList();
            ArrayList sectional= new ArrayList();
            
            periods.clear();
            
         XSSFWorkbook wb;    
            
    String periodname="";        
//String allpath = getServletContext().getRealPath("/Gapanalysis.xlsm");
String allpath = getServletContext().getRealPath("/GapAnalysis-R2.xlsm");

 System.out.println(allpath);
 
    XSSFWorkbook workbook;
      String mydrive = allpath.substring(0, 1);
   // wb = new XSSFWorkbook( OPCPackage.open(allpath) );
     
 Date da= new Date();
String dat2 = da.toString().replace(" ", "_");
 dat2 = dat2.toString().replace(":", "_");
        
        String np = "";
      if(OSValidator.isWindows()){
       np=mydrive+":\\HSDSA\\InternalSystem\\Gapanalysis"+dat2+".xlsm";
        }
        else if(OSValidator.isUnix()){
           np="/HSDSA/InternalSystem/Gapanalysis"+dat2+".xlsm";     
                }
            System.out.println("path:: "+np);
             // String desteepath1 = getServletContext().getRealPath("/Females 15to24.xlsm");
              String sr = getServletContext().getRealPath("/GapAnalysis-R2.xlsm");
    //check if file exists
              
   //first time , it should create those folders that host the macro file
    File f = new File(np);
if(!f.exists()&& !f.isDirectory() ) { /* do something */
copytemplates ct= new copytemplates();
    ct.transfermacros(sr,np);
 //rem np is the destination file name  
   
    System.out.println("Copying macro template first time ..");
    
}
else 
  //copy the file alone  
{
copytemplates ct= new copytemplates();
//copy the agebased file only
ct.copymacros(sr,np);

}
String filepth=np;      

  File allpathfile= new File(filepth);
     
      OPCPackage pkg = OPCPackage.open(allpathfile);

pathtodelete=filepth;
    wb = new XSSFWorkbook(pkg);

            
            
            
            
            
            
          dbConn conn= new dbConn();
          HashMap<String, String> rawdatahashmap= new HashMap<String,String>();
          
          int year=0;
          String yearval="";
          int prevyear=0;
          
          String quarter="";
          
          String yearmonth=""; 
         String startyearmonth="";
         String endyearmonth="";
         
          yearval=request.getParameter("year").toString();
          
            System.out.println("YEARVAL"+yearval);
          year=Integer.parseInt(yearval);
          prevyear=year-1;
          quarter=request.getParameter("quarter");
          periodname+=yearval+"_";
          if(quarter.equals("1")){ 
          startyearmonth=prevyear+"10";
          endyearmonth=prevyear+"12";
            periodname=prevyear+"_(Oct_Dec)";  
          }
          else if(quarter.equals("2")){
          startyearmonth=year+"01";
          endyearmonth=year+"03";
            periodname=yearval+"_(Jan-Mar)";
          }
          else if(quarter.equals("3")){
            startyearmonth=year+"04";
          endyearmonth=year+"06";
            periodname=yearval+"_(Apr_Jun)";
          }
          else if(quarter.equals("4")){
          startyearmonth=year+"07";
          endyearmonth=year+"09";
          periodname=yearval+"_(Jul_Sep)";
          }
          
         int s_ym=Integer.parseInt(startyearmonth);
          int colsmerging=6;
            String Sections[]={};
            String headers[]={"County","Sub-County","Facility","Year","Month"};
//            String headergsn[]={"County","Sub-County","Facility"};
            //if one wants gaps for one service area
           if(request.getParameterValues("gapsection")!=null){
               
           Sections=request.getParameterValues("gapsection");
           
           } 
         //This is the loop that well use to create worksheets for each 
 
             //______________________________________________________________________________________
              
            Font font = wb.createFont();
            font.setFontHeightInPoints((short) 18);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
           Font font2 = wb.createFont();
            font2.setFontName("Cambria");
            font2.setColor((short) 0000);
            CellStyle style2 = wb.createCellStyle();
            style2.setFont(font2);
            style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            CellStyle stborder = wb.createCellStyle();
            stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            CellStyle stylex = wb.createCellStyle();
            stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            
            
            CellStyle stylex1 = wb.createCellStyle();
            stylex1.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            stylex1.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex1.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex1.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            
            
            CellStyle stylex2 = wb.createCellStyle();
            stylex2.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
            stylex2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex2.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex2.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            CellStyle stylex3 = wb.createCellStyle();
            stylex3.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
            stylex3.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            stylex3.setBorderTop(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            stylex3.setBorderRight(HSSFCellStyle.BORDER_THIN);
            stylex3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
            Font fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);
            stylex1.setFont(fontx);
            stylex1.setWrapText(true);
            
            stylex2.setFont(fontx);
            stylex2.setWrapText(true);
   
            //==================================================
            
            
    Sheet shet= wb.getSheet("Sheet1");
    Sheet shetGap= wb.createSheet("Approved Gaps");
    Sheet shetGapNoJustfied= wb.createSheet("Not Approved Gaps");
          
        shet.setColumnWidth(0, 8000);  
        shet.setColumnWidth(1, 8000);  
        shet.setColumnWidth(2, 4000);  
        shet.setColumnWidth(3, 4000);  
        shet.setColumnWidth(4, 6000); 
        shet.setColumnWidth(5, 7000); 
        shet.setColumnWidth(6, 2000); 
        shet.setColumnWidth(7, 2000); 
        shet.setColumnWidth(8, 4000); 
        shet.setColumnWidth(9, 4000); 
        shet.setColumnWidth(10, 4000); 
        shet.setColumnWidth(11, 4000); 
        
        shetGap.setColumnWidth(0, 8000);  
        shetGap.setColumnWidth(1, 8000);  
        shetGap.setColumnWidth(2, 4000);  
        shetGap.setColumnWidth(3, 4000);  
        shetGap.setColumnWidth(4, 6000); 
        shetGap.setColumnWidth(5, 7000); 
        shetGap.setColumnWidth(6, 2000); 
        shetGap.setColumnWidth(7, 2000); 
        shetGap.setColumnWidth(8, 4000); 
        shetGap.setColumnWidth(9, 4000); 
        shetGap.setColumnWidth(10, 4000); 
        shetGap.setColumnWidth(11, 4000); 
        shetGap.setColumnWidth(12, 8000); 
        
        shetGapNoJustfied.setColumnWidth(0, 8000);  
        shetGapNoJustfied.setColumnWidth(1, 8000);  
        shetGapNoJustfied.setColumnWidth(2, 4000);  
        shetGapNoJustfied.setColumnWidth(3, 4000);  
        shetGapNoJustfied.setColumnWidth(4, 6000); 
        shetGapNoJustfied.setColumnWidth(5, 7000); 
        shetGapNoJustfied.setColumnWidth(6, 2000); 
        shetGapNoJustfied.setColumnWidth(7, 2000); 
        shetGapNoJustfied.setColumnWidth(8, 4000); 
        shetGapNoJustfied.setColumnWidth(9, 4000); 
        shetGapNoJustfied.setColumnWidth(10, 4000); 
        shetGapNoJustfied.setColumnWidth(11, 4000); 
        shetGapNoJustfied.setColumnWidth(12, 8000); 
    
        int row=0;

    //Row heading
//    row++;


String[] headers_just =  {"Rule","Gap","Program Area","County","Sub County","Facility","Year","Month","Ward","Latitude","Longitude","Explanation"};
//header for justified gaps
int ct=0,nojustgap=0, gaprow=0;
Row rw_gp=shetGap.createRow(gaprow); 
Row rw_gp2=shetGapNoJustfied.createRow(gaprow); 

 rw_gp.setHeightInPoints(24);
 rw_gp2.setHeightInPoints(24);
for(String header_elem:headers_just){
    
      Cell c1x= rw_gp.createCell(ct);
      c1x.setCellValue(header_elem);
      c1x.setCellStyle(stylex);
      
      Cell c1x1= rw_gp2.createCell(ct);
      c1x1.setCellValue(header_elem);
      c1x1.setCellStyle(stylex); 
      ct++;
}
gaprow++;
nojustgap++;
//end

     Row rwh=shet.createRow(row);            
      rwh.setHeightInPoints(24);
      
      Cell c1x= rwh.createCell(0);
      c1x.setCellValue("Rule");
      c1x.setCellStyle(stylex);
     
      Cell c2x= rwh.createCell(1);
      c2x.setCellValue("Gap");
      c2x.setCellStyle(stylex);//gsn sites do not have a yearmonth
      
      Cell c3x= rwh.createCell(2);
      c3x.setCellValue("Program Area");
      c3x.setCellStyle(stylex);
       
     Cell c4x= rwh.createCell(3);
      c4x.setCellValue("County");
      c4x.setCellStyle(stylex);
      
     Cell c5x= rwh.createCell(4);
      c5x.setCellValue("Sub County");
      c5x.setCellStyle(stylex);
      
     Cell c6x= rwh.createCell(5);
      c6x.setCellValue("Facility");
      c6x.setCellStyle(stylex);
      
     Cell c7x= rwh.createCell(6);
      c7x.setCellValue("Year");
      c7x.setCellStyle(stylex);
      
     Cell c8x= rwh.createCell(7);
      c8x.setCellValue("Month");
      c8x.setCellStyle(stylex);
      
     Cell c9x= rwh.createCell(8);
      c9x.setCellValue("Ward");
      c9x.setCellStyle(stylex);
      
     Cell c10x= rwh.createCell(9);
      c10x.setCellValue("Latitude");
      c10x.setCellStyle(stylex);
      
     Cell c11x= rwh.createCell(10);
      c11x.setCellValue("Longitude");
      c11x.setCellStyle(stylex);
      
     Cell c12x= rwh.createCell(11);
      c12x.setCellValue("Value");
      c12x.setCellStyle(stylex);
     
      for(int a=0;a< Sections.length; a++){
      
         
 //now go to the database and do a query for each section

 String getqueries=" Select * from gap_analysis where active=1 and section='"+Sections[a]+"' ";
 
 conn.rs=conn.st.executeQuery(getqueries); 
 while(conn.rs.next()){
 String active_section=conn.rs.getString("subpartnera_column");
 String section = conn.rs.getString("section");
 String rule = conn.rs.getString("explanation");
 String gap = conn.rs.getString("rule");
 String currentqry=conn.rs.getString("query");
     System.out.println("gaps are : "+gap);
int position = conn.rs.getInt("id");
 int i=0;
 while(i<3){
 int current_year=s_ym+i;  
 no_months=0;
 String running_query="";
 running_query=currentqry.replace("PAREA", active_section);
 running_query=running_query.replace("YMONTH", ""+current_year);

 System.out.println("Query in "+position+" is : ---"+running_query+"-----end");
 conn.rs1=conn.st1.executeQuery(running_query);
 while (conn.rs1.next()) {
    String value = "";
    String county=conn.rs1.getString("County");
    String sub_county=conn.rs1.getString("DistrictNom");
    String facility=conn.rs1.getString("SubPartnerNom");
    int yr = Integer.parseInt(String.valueOf(current_year).substring(0,4));
    int mn =Integer.parseInt(String.valueOf(current_year).substring(4));
    String ward=conn.rs1.getString("ward");
    Double latitude=conn.rs1.getDouble("latitude");
    Double longitude=conn.rs1.getDouble("longitude");
    ResultSetMetaData rsmd = conn.rs1.getMetaData();
    
    if(hasValue(rsmd)){
        value = conn.rs1.getString("value");
    }
    else if(no_months>0){
//        value=String.valueOf(((conn.rs1.getInt("HV0116")*100)/no_months))+"%";
          value = "0";
    }
 String mn_name="";
    switch (mn){
        case 1: mn_name="Jan";
        break;
        case 2: mn_name="Feb"; 
        break;
        case 3: mn_name="Mar";  
        break;
        case 4: mn_name="Apr"; 
        break;
        case 5: mn_name="May"; 
        break;
        case 6: mn_name="Jun"; 
        break;
        case 7: mn_name="Jul";
        break;
        case 8: mn_name="Aug"; 
        break;
        case 9: mn_name="Sep";  
        break;
        case 10: mn_name="Oct";  
        break;
        case 11: mn_name="Nov"; 
        break;
        case 12: mn_name="Dec"; 
        break;
        default:
            mn_name="No Month";
    }
    
    //check on accounted for gaps
    
    String checkifaccounted = "SELECT id FROM gaps WHERE year=? AND month=? AND gap=? AND program_area=? AND facility=? AND status=? ";
    conn.pst3 = conn.conn.prepareStatement(checkifaccounted);
    conn.pst3.setInt(1, yr);
    conn.pst3.setString(2, mn_name);
    conn.pst3.setString(3, gap);
    conn.pst3.setString(4, section);
    conn.pst3.setString(5, facility);
    conn.pst3.setInt(6, 1);
    conn.rs3 = conn.pst3.executeQuery();
    if(conn.rs3.next()){
        
    }
  
    else{
    row++; 
    Row rwx=shet.createRow(row);
    
      Cell c1= rwx.createCell(0);
      c1.setCellValue(rule);
      c1.setCellStyle(style2);
     
      Cell c2= rwx.createCell(1);
      c2.setCellValue(gap);
      c2.setCellStyle(style2);//gsn sites do not have a yearmonth
      
      Cell c3= rwx.createCell(2);
      c3.setCellValue(section);
      c3.setCellStyle(style2);
       
     Cell c4= rwx.createCell(3);
      c4.setCellValue(county);
      c4.setCellStyle(style2);
      
     Cell c5= rwx.createCell(4);
      c5.setCellValue(sub_county);
      c5.setCellStyle(style2);
      
     Cell c6= rwx.createCell(5);
      c6.setCellValue(facility);
      c6.setCellStyle(style2);
      
     Cell c7= rwx.createCell(6);
      c7.setCellValue(yr);
      c7.setCellStyle(style2);
      
     Cell c8= rwx.createCell(7);
      c8.setCellValue(mn_name);
      c8.setCellStyle(style2);
      
      
     Cell c9= rwx.createCell(8);
      c9.setCellValue(ward);
      c9.setCellStyle(style2);
      
      
     Cell c10= rwx.createCell(9);
      c10.setCellValue(latitude);
      c10.setCellStyle(style2);
      
      
     Cell c11= rwx.createCell(10);
      c11.setCellValue(longitude);
      c11.setCellStyle(style2);     
      
     Cell c12= rwx.createCell(11);
      if(isNumeric(value)){
      c12.setCellValue(Integer.parseInt(value));
      }
      else{
      c12.setCellValue(value);   
      }
      
      c12.setCellStyle(style2); 
      
    }
      //my key is a 
//      String mykey=active_section+conn.rs1.getString("SubPartnerNom")+"_"+current_year+"_";

 }

  
if(!periods.contains(current_year)){
  periods.add(current_year);
  
  String[] dets = getperiod(current_year).split("#");
  
  String yr = dets[0];
  String mn_name = dets[1];
  
  String getjustified = "SELECT * FROM gaps WHERE year=? AND month=? AND status=?";
  conn.pst3 = conn.conn.prepareStatement(getjustified);
  conn.pst3.setString(1, yr);
  conn.pst3.setString(2, mn_name);
  conn.pst3.setInt(3, 1);
  conn.rs3 = conn.pst3.executeQuery();
  
    System.out.println("---------get justified gaps : "+conn.pst3);
  while(conn.rs3.next()){
     Row rwx=shetGap.createRow(gaprow);
    int gp=0;
     for(String label:columns){
         
      Cell c1= rwx.createCell(gp);
      if(isNumeric(conn.rs3.getString(label))){
      c1.setCellValue(conn.rs3.getDouble(label));
      }
      else{
        c1.setCellValue(conn.rs3.getString(label));   
      }
      c1.setCellStyle(style2);
      
      gp++;
     }
      gaprow++;
  }
  
  //unjustfied gaps
  String getnotjustified = "SELECT * FROM gaps WHERE year=? AND month=? AND status=? && explanation IS NOT NULL ";
  conn.pst3 = conn.conn.prepareStatement(getnotjustified);
  conn.pst3.setString(1, yr);
  conn.pst3.setString(2, mn_name);
  conn.pst3.setInt(3, 0);
//  conn.pst3.setString(4, "IS NOT NULL");
  conn.rs3 = conn.pst3.executeQuery();
  
    System.out.println("---------get non justified gaps : "+conn.pst3);
  while(conn.rs3.next()){
     Row rwx=shetGapNoJustfied.createRow(nojustgap);
    int gp=0;
     for(String label:columns){
         
      Cell c1= rwx.createCell(gp);
      if(isNumeric(conn.rs3.getString(label))){
      c1.setCellValue(conn.rs3.getDouble(label));
      }
      else{
        c1.setCellValue(conn.rs3.getString(label));   
      }
      c1.setCellStyle(style2);
      
      gp++;
     }
      nojustgap++;
  }
  
  
  
 }
// end of month looping
i++;
 }
}//end of all queries per section

}// end of sheets loop   
 
     IdGenerator IG = new IdGenerator();
     String   createdOn=IG.CreatedOn();

ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=GapAnalysis_For"+periodname+"_Generatted_On_"+createdOn+".xlsm");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close(); 
   pkg.close();

   if(conn.rs!=null){conn.rs.close();}
   if(conn.rs1!=null){conn.rs1.close();}
   if(conn.st1!=null){conn.st1.close();}
   if(conn.st!=null){conn.st.close();}
       
         File file= new File(pathtodelete);
            System.out.println("path: 2"+pathtodelete);
           
        if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation  failed.");
    		}
        } 
        catch (SQLException ex) { 
            Logger.getLogger(gapAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(gapAnalysis.class.getName()).log(Level.SEVERE, null, ex);
        }        finally 
        {
           
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

    public String getmonthbetween(int currenttime){
    String monthbetween="";
    String[] getperiods = String.valueOf(currenttime).split("");
    int currentyear = Integer.parseInt(getperiods[0]+""+getperiods[1]+""+getperiods[2]+""+getperiods[3]);
    int currentmonth = Integer.parseInt(getperiods[4]+""+getperiods[5]);
    String start="",end="";
    int no_months=0;
    switch (currentmonth){
        case 1:
          start=(currentyear-1)+"10";
          end=currentyear+"01";
          no_months=4;
            break;
        case 2:
           start=(currentyear-1)+"10";
          end=currentyear+"02";
          no_months=5;
            break;
        case 3: // end of semi-annual 1
          start=(currentyear-1)+"10";
          end=currentyear+"03"; 
          no_months=6;
            break;
        case 4:
           start=currentyear+"04";
          end=currentyear+"04";  
          no_months=1;
            break;
        case 5:
          start=currentyear+"04";
          end=currentyear+"05";  
          no_months=2;
            break;
        case 6:
          start=currentyear+"04";
          end=currentyear+"06";
          no_months=3;
            break;
        case 7:
           start=currentyear+"04";
          end=currentyear+"07";
          no_months=4;
            break;
        case 8:
         start=currentyear+"04";
          end=currentyear+"08";    
            no_months=5;
            break;
        case 9: // end of semi-annual 2
            start=currentyear+"04";
          end=currentyear+"09";
          no_months = 6;
            break;
       
        case 10:// start of semi annual 1
        start=currentyear+"10";
          end=currentyear+"10";     
           no_months=1; 
            break;
        case 11:
          start=currentyear+"10";
          end=currentyear+"11"; 
          no_months=2;
            break;
        case 12:
          start=currentyear+"10";
          end=currentyear+"12";
          no_months=3;
           break;
        
        default:
            break;
    }
    
    monthbetween="yearmonth between "+start+" and "+end+"###"+no_months;
    return monthbetween;
    }
    public int prevmonth(int currentymonth){
      int pepfaryear=0;  
      String[] montharray = String.valueOf(currentymonth).split("");
      int year=Integer.parseInt(montharray[0]+""+montharray[1]+""+montharray[2]+""+montharray[3]);
      int month=Integer.parseInt(montharray[4]+""+montharray[5]);
     if(month<=3){
      pepfaryear=Integer.parseInt((year-1)+"09");   
     }
     else if(month>=10){
      pepfaryear=Integer.parseInt((year)+"09");   
     }
     else{
      pepfaryear=Integer.parseInt((year)+"03");   
     }
     return pepfaryear;
    } 
    
   public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
        
    public boolean hasValue(ResultSetMetaData rsmd) throws SQLException{
          boolean exist=false;  
          int columns = rsmd.getColumnCount();
            for (int x = 1; x <= columns; x++) {
                if ("value".equals(rsmd.getColumnName(x))) {
                    exist = true;
                    break;
                }
            }   
            
          return exist;   
        }


public String getperiod(int ym){
    String period="";  
    String[] montharray = String.valueOf(ym).split("");
    int year=Integer.parseInt(montharray[0]+""+montharray[1]+""+montharray[2]+""+montharray[3]);
    int month=Integer.parseInt(montharray[4]+""+montharray[5]);

    String mn_name="";
    switch (month){
        case 1: mn_name="Jan";
        break;
        case 2: mn_name="Feb"; 
        break;
        case 3: mn_name="Mar";  
        break;
        case 4: mn_name="Apr"; 
        break;
        case 5: mn_name="May"; 
        break;
        case 6: mn_name="Jun"; 
        break;
        case 7: mn_name="Jul";
        break;
        case 8: mn_name="Aug"; 
        break;
        case 9: mn_name="Sep";  
        break;
        case 10: mn_name="Oct";  
        break;
        case 11: mn_name="Nov"; 
        break;
        case 12: mn_name="Dec"; 
        break;
        default:
            mn_name="No Month";
    }
   
    period = year+"#"+mn_name;
return period;
}

}
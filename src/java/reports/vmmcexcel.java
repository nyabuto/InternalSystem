/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;

/**
 *
 * @author Geofrey Nyabuto
 */
public class vmmcexcel extends HttpServlet {

    HttpSession session=null;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
    response.setContentType("text/html;charset=UTF-8");
    session=request.getSession();
    
    
    dbConn conn=new dbConn();
    //get the existing data for the month, year and facility that is already on session
    
    String month="";      
    String year="";      
    String facil="361";
  
    String form="vmmc";
      
   
    
 
  
    

//=====================================================================================================
    
    year="2015";
     month="5";
    String county="";
   
    String header="";
    
  String  reportType="";
    if(request.getParameter("reportType")!=null){
      reportType=request.getParameter("reportType");
    }
    String  reportDuration="";
    if(request.getParameter("reportDuration")!=null){
    reportDuration=request.getParameter("reportDuration");
    }
    
    if(request.getParameter("year")!=null){
    year=request.getParameter("year");
    }
    
     if(request.getParameter("facility")!=null && reportType.equals("2")){
    facil=request.getParameter("facility");
    
    String getfacil="select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='"+facil+"'";
    conn.rs=conn.st.executeQuery(getfacil);
    
    while(conn.rs.next()){
    
    header+=" FACILITY : "+conn.rs.getString(1).toUpperCase()+"     MFL CODE  :  "+conn.rs.getString(2)+"  ";
    
    }
    
    
    
    }
    
    if(request.getParameter("county")!=null && reportType.equals("2")){
    county=request.getParameter("county");
    
    
    String getcounty="select County from county where CountyID='"+county+"'";
    conn.rs=conn.st.executeQuery(getcounty);
    
    while(conn.rs.next()){
    
    header+=" COUNTY : "+conn.rs.getString(1).toUpperCase()+" ";
    
    }
    
    }
    
    if(request.getParameter("month")!=null && reportDuration.equals("4")){
    month=request.getParameter("month");
    
    
      String getmonth="select name as monthname from month where id='"+month+"'";
    conn.rs=conn.st.executeQuery(getmonth);
    
    while(conn.rs.next()){
    
    header+=" MONTH : "+conn.rs.getString(1).toUpperCase()+"";
    
    }
    
    
    }
    
     
    header+=" YEAR : "+year+"";
     
     
     
     
     
   String facilitywhere="";
   String yearwhere="";
   String monthwhere="";
   String countywhere="";
   String duration="";
   String semi_annual="";
   String quarter="";
 
   //==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  
  
       int  yearcopy=Integer.parseInt(year);
      
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        String yearmonth=""+year;
       int prevYear=yearcopy-1; 
       int maxYearMonth=0;
       int monthcopy=0; 
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
            yearmonth+="_AnnualReport";
         duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
            yearmonth+="_Oct_Mar";
       duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
            yearmonth+="_Apr_Sep";
       duration=" "+form+".yearmonth BETWEEN "+year+"04 AND "+year+"09";      
           }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
           yearmonth+=year+"_"+conn.rs.getString(2);
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" "+form+".yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 

      }
      else{
     duration=" "+form+".yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
                              }
        }  
        
      else if(reportDuration.equals("4")){
     monthcopy=Integer.parseInt(request.getParameter("month"));
     yearmonth+=year+"_("+month+")";
     
//     month=5;
     if(monthcopy>=10){
     duration=" "+form+".yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" "+form+".yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
        




//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and countyid = '"+county+"'";    
   
   }
   
   if(!facil.equals("")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'";    
   
   } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration;  
    
    
    
//=====================================================================================================    
    
        
//=====================================================================================================    
    
//______________________________________________________________________________________
  //                       NOW CREATE THE WORKSHEETS          
  //______________________________________________________________________________________  
            
              HSSFWorkbook wb=new HSSFWorkbook();
              
              
              
              
    //______________________________________________________________________________________
    //______________________________________________________________________________________
              
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
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

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
            stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);

            HSSFFont fontx = wb.createFont();
            fontx.setColor(HSSFColor.BLACK.index);
            fontx.setFontName("Cambria");
            stylex.setFont(fontx);
            stylex.setWrapText(true);

   
   HSSFSheet shet=wb.createSheet(form);   
     
    //create headers for that worksheet
      
      HSSFRow rw=shet.createRow(0);
      rw.setHeightInPoints(25);
 HSSFCell cl0= rw.createCell(0);
 cl0.setCellValue("VOLUNTARY MALE CIRCUMCISION REPORTING FORM");
 cl0.setCellStyle(stylex);
    
 for(int a=1;a<=4;a++){ 
 HSSFCell clx= rw.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 
 HSSFRow rw1=shet.createRow(1);
 rw1.setHeightInPoints(23);
 HSSFCell cl= rw1.createCell(0);
 cl.setCellValue(header);
 cl.setCellStyle(stylex);
 
  for(int a=1;a<=4;a++){ 
 HSSFCell clx= rw1.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
  
   HSSFRow rw2=shet.createRow(2);
     rw2.setHeightInPoints(23);
      HSSFCell cl3= rw2.createCell(0);
 cl3.setCellValue("P5.1.D:");
 cl3.setCellStyle(style2);
     
  HSSFCell cl31= rw2.createCell(1);
 cl31.setCellValue("Number of Males Circumcised as part of the minimum package of MC for HIV prevention services:");
 cl31.setCellStyle(stylex);
 
  for(int a=2;a<=4;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
    shet.addMergedRegion(new CellRangeAddress(2,10,0,0));  
    shet.addMergedRegion(new CellRangeAddress(0,0,0,4));  
    shet.addMergedRegion(new CellRangeAddress(1,1,0,4));  
    shet.addMergedRegion(new CellRangeAddress(2,2,1,4));  
    shet.setColumnWidth(0, 2000);  
    shet.setColumnWidth(1, 9000);  
    shet.setColumnWidth(2, 5000);  
    shet.setColumnWidth(3, 5000);  
    shet.setColumnWidth(4, 5000);  

    
    
        
getexistingdata="select sum(P51D1) as P51D1,   sum(P51D9) as P51D9,   sum(P51D10) as P51D10,   sum(P51D19) as P51D19,sum(P51D24) as P51D24,  sum(P51D49) as  P51D49,   sum(P51D50) as P51D50,    sum(P51DT) as P51DT,   sum(P521DM) as  P521DM,    sum(P521DS) as P521DS,   sum(P521DT) as P521DT,   sum(P522DM) as P522DM,    sum(P522DS) as P522DS,    sum(P522DT) as P522DT,   sum(P52DM) as  P52DM,   sum(P52DS) as P52DS,    sum(P52DT) as P52DT,   sum(P511KP) as P511KP,   sum(P511KN) as P511KN,   sum(P511KU) as P511KU,   sum(P511Surg) as P511Surg,   sum(P511Dev) as P511Dev,   sum(P53DF) as P53DF,    sum(P53DO) as P53DO,   sum(P53DM) as P53DM,    sum(P53D) as P53D,   sum(P54D) as P54D  from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+"  ";

            System.out.println(getexistingdata);
String P51D1 = "";
            String P51D9 = "";
            String P51D10 = "";
            String P51D19 = "";
            String P51D24 = "";
            String P51D49 = "";
            String P51D50 = "";
            String P51DT = "";
            String P521DM = "";
            String P521DS = "";
            String P521DT = "";
            String P522DM = "";
            String P522DS = "";
            String P522DT = "";
            String P52DM = "";
            String P52DS = "";
            String P52DT = "";
            String P511KP = "";
            String P511KN = "";
            String P511KU = "";
            String P511Surg = "";
            String P511Dev = "";
            String P53DF = "";
            String P53DO = "";
            String P53DM = "";
            String P53D = "";
            String P54D = "";


String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}


int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
        
        
                P51D1 = conn.rs.getString("P51D1");
                if (P51D1 == null) {
                    P51D1 = "";
                }

                P51D9 = conn.rs.getString("P51D9");
                if (P51D9 == null) {
                    P51D9 = "";
                }

                P51D10 = conn.rs.getString("P51D10");
                if (P51D10 == null) {
                    P51D10 = "";
                }

                P51D19 = conn.rs.getString("P51D19");
                if (P51D19 == null) {
                    P51D19 = "";
                }

                P51D24 = conn.rs.getString("P51D24");
                if (P51D24 == null) {
                    P51D24 = "";
                }

                P51D49 = conn.rs.getString("P51D49");
                if (P51D49 == null) {
                    P51D49 = "";
                }

                P51D50 = conn.rs.getString("P51D50");
                if (P51D50 == null) {
                    P51D50 = "";
                }

                P51DT = conn.rs.getString("P51DT");
                if (P51DT == null) {
                    P51DT = "";
                }

                P521DM = conn.rs.getString("P521DM");
                if (P521DM == null) {
                    P521DM = "";
                }

                P521DS = conn.rs.getString("P521DS");
                if (P521DS == null) {
                    P521DS = "";
                }

                P521DT = conn.rs.getString("P521DT");
                if (P521DT == null) {
                    P521DT = "";
                }



                P522DM = conn.rs.getString("P522DM");
                if (P522DM == null) {
                    P522DM = "";
                }

                P522DS = conn.rs.getString("P522DS");
                if (P522DS == null) {
                    P522DS = "";
                }

                P522DT = conn.rs.getString("P522DT");
                if (P522DT == null) {
                    P522DT = "";
                }


                P52DM = conn.rs.getString("P52DM");
                if (P52DM == null) {
                    P52DM = "";
                }


                P52DS = conn.rs.getString("P52DS");
                if (P52DS == null) {
                    P52DS = "";
                }


                P52DT = conn.rs.getString("P52DT");
                if (P52DT == null) {
                    P52DT = "";
                }


                P511KP = conn.rs.getString("P511KP");
                if (P511KP == null) {
                    P511KP = "";
                }


                P511KN = conn.rs.getString("P511KN");
                if (P511KN == null) {
                    P511KN = "";
                }

                P511KU = conn.rs.getString("P511KU");
                if (P511KU == null) {
                    P511KU = "";
                }

                P511Surg = conn.rs.getString("P511Surg");
                if (P511Surg == null) {
                    P511Surg = "";
                }


                P511Dev = conn.rs.getString("P511Dev");
                if (P511Dev == null) {
                    P511Dev = "";
                }

                P53DF = conn.rs.getString("P53DF");
                if (P53DF == null) {
                    P53DF = "";
                }

                P53DO = conn.rs.getString("P53DO");
                if (P53DO == null) {
                    P53DO = "";
                }

                P53DM = conn.rs.getString("P53DM");
                if (P53DM == null) {
                    P53DM = "";
                }

                P53D = conn.rs.getString("P53D");
                if (P53D == null) {
                    P53D = "";
                }

                P54D = conn.rs.getString("P54D");
                if (P54D == null) {
                    P54D = "";
                }

        
       }
    
    String createdtable="";
    
    
if(1==1){ 
    
 
    
    
  if(1==1){
      int r=3;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
     
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("< 1");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D1);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
  
  if(1==1){
      int r=4;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("1-9");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D9);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  
  //=====================================================================================
  
  if(1==1){
      int r=5;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("10-14");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D10);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  
  
  //=====================================================================================
  
  if(1==1){
      int r=6;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("15-19");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D19);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
 //================================================================================== 
  
  if(1==1){
      int r=7;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("20-24");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D24);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
  
    
  
  if(1==1){
      int r=8;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("25-49");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D49);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
  
  
   if(1==1){
      int r=9;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("50 +");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51D50);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
 

    if(1==1){
      int r=10;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Total");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P51DT);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================

   

    if(1==1){
      int r=11;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
   HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("P5.2.D");
 clx0.setCellStyle(style2);
   
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Number of Clients circumcised who experienced one or more moderate or severe adverse events(s)");
 clx.setCellStyle(stylex);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue("");
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,4));
   shet.addMergedRegion(new CellRangeAddress(r,r+4,0,0));
 
  }
  //==================================================================================
    
    
    if(1==1){
      int r=12;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("");
 clx.setCellStyle(style2);
 

   HSSFCell clxm= rwx.createCell(2);
 clxm.setCellValue("Moderate");
 clxm.setCellStyle(stylex);
    
 
 HSSFCell clxs= rwx.createCell(3);
 clxs.setCellValue("Severe");
 clxs.setCellStyle(stylex);
 
 
  HSSFCell clxt= rwx.createCell(4);
 clxt.setCellValue("Total");
 clxt.setCellStyle(stylex);
 

 
   //shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
   
     
    if(1==1){
      int r=13;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("During Circumcission");
 clx.setCellStyle(stylex);
 

   HSSFCell clxm= rwx.createCell(2);
 clxm.setCellValue(P521DM);
 clxm.setCellStyle(style2);
    
 
 HSSFCell clxs= rwx.createCell(3);
 clxs.setCellValue(P521DS);
 clxs.setCellStyle(style2);
 
 
  HSSFCell clxt= rwx.createCell(4);
 clxt.setCellValue(P521DT);
 clxt.setCellStyle(style2);
 

 
   //shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
   
     
    if(1==1){
      int r=14;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Post CircumCission");
 clx.setCellStyle(stylex);
 

   HSSFCell clxm= rwx.createCell(2);
 clxm.setCellValue(P522DM);
 clxm.setCellStyle(style2);
    
 
 HSSFCell clxs= rwx.createCell(3);
 clxs.setCellValue(P522DS);
 clxs.setCellStyle(style2);
 
 
  HSSFCell clxt= rwx.createCell(4);
 clxt.setCellValue(P522DT);
 clxt.setCellStyle(style2);
 

 
   //shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
   
   if(1==1){
      int r=15;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
    HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Total Adverse Events");
 clx.setCellStyle(stylex);
 

   HSSFCell clxm= rwx.createCell(2);
 clxm.setCellValue(P52DM);
 clxm.setCellStyle(style2);
    
 
 HSSFCell clxs= rwx.createCell(3);
 clxs.setCellValue(P52DS);
 clxs.setCellStyle(style2);
 
 
  HSSFCell clxt= rwx.createCell(4);
 clxt.setCellValue(P52DT);
 clxt.setCellStyle(style2);
 

 
   //shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
 
  }
  //==================================================================================
    if(1==1){
      int r=16;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
   HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("P5.1.1.K");
 clx0.setCellStyle(style2);
   
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("HIV Status of MC clients)");
 clx.setCellStyle(stylex);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue("");
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,4));
   shet.addMergedRegion(new CellRangeAddress(r,r+3,0,0));
 
  }
  //==================================================================================
    if(1==1){
      int r=17;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Tested/self-reported positive");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P511KP);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
   if(1==1){
      int r=18;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Tested negative");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P511KN);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
  
   if(1==1){
      int r=19;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Unknown/self-reported negative");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P511KU);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
   //==================================================================================
    if(1==1){
      int r=20;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
   HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("P5.1.1.T");
 clx0.setCellStyle(style2);
   
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Circumcission Technique)");
 clx.setCellStyle(stylex);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue("");
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,4));
   shet.addMergedRegion(new CellRangeAddress(r,r+2,0,0));
 
  }
  //==================================================================================
    
     if(1==1){
      int r=21;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Surgical VMMC");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P511Surg);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
  
     if(1==1){
      int r=22;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Device-Based VMMC");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P511Dev);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
   
      //==================================================================================
    if(1==1){
      int r=23;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(23);
   
   HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("P5.3.D:");
 clx0.setCellStyle(style2);
   
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Number of locations providing MC surgery as part of the minimum package of MC for HIV prevention services within the reporting period ");
 clx.setCellStyle(stylex);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue("");
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,4));
   shet.addMergedRegion(new CellRangeAddress(r,r+4,0,0));
 
  }
  //==================================================================================
    
     if(1==1){
      int r=24;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Fixed/Static");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P53DF);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
   if(1==1){
      int r=25;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Outreach");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P53DO);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  //==============================================================================================
   //==================================================================================
   if(1==1){
      int r=26;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Mobile");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P53DM);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  //==============================================================================================
   //==================================================================================
   if(1==1){
      int r=27;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Total");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P53D);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  //==============================================================================================
     
    //==================================================================================
    if(1==1){
      int r=28;
   HSSFRow rwx=shet.createRow(r);
   rwx.setHeightInPoints(28);
   
   HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("P5.3.D:");
 clx0.setCellStyle(style2);
   
   
   HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("Number of males circumcised within the reporting period who return at least once for postoperative follow‐up care (routine or emergent) within 14 days of surgery");
 clx.setCellStyle(stylex);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue("");
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
                     }
   shet.addMergedRegion(new CellRangeAddress(r,r,1,4));
   shet.addMergedRegion(new CellRangeAddress(r,r+1,0,0));
 
  }
  //==================================================================================
    
     if(1==1){
      int r=29;
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);
      HSSFCell clx0= rwx.createCell(0);
 clx0.setCellValue("");
 clx0.setCellStyle(style2);
     
      HSSFCell clx= rwx.createCell(1);
 clx.setCellValue("");
 clx.setCellStyle(style2);
     
  HSSFCell clx1= rwx.createCell(4);
 clx1.setCellValue(P54D);
 clx1.setCellStyle(style2);
 
  for(int a=2;a<=3;a++){ 
 HSSFCell clx2= rwx.createCell(a);
 clx2.setCellValue("");
 clx2.setCellStyle(style2);
 shet.addMergedRegion(new CellRangeAddress(r,r,1,3));
                     }
 
  }
  
   //==================================================================================
   
   
    
    
    }
    
    
     
      //System.out.println(createdtable);
      
   
        
        if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
        
     
    
    
    
    
      



ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte[] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename="+form+yearmonth+".xls");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close();
    
    
}       catch (SQLException ex) {
            Logger.getLogger(Vmmcpdf.class.getName()).log(Level.SEVERE, null, ex);
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

}

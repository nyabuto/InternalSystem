/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Datim;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
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
public class datimReport extends HttpServlet {
HttpSession session;
int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityId;
int HV0308,HV0309,HV0310,HV0311,HV0312,HV0320,HV0321,HV0322,HV0323,HV0324;
int HV0314,HV0315,HV0316,HV0317,HV0318,HV0334,HV0335,HV0336,HV0337,HV0338;
double currentART1M,currentART1_4M,currentART5_14M,currentART15_19M,currentART20M;
double currentART1F,currentART1_4F,currentART5_14F,currentART15_19F,currentART20F;
double newART1M,newART1_4M,newART5_9M,newART10_14M,newART15_19M,newART20_24M,newART25_49M,newART50M;
double newART1F,newART1_4F,newART5_9F,newART10_14F,newART15_19F,newART20_24F,newART25_49F,newART50F;
double newCARE1M,newCARE1_4M,newCARE5_9M,newCARE10_14M,newCARE15_19M,newCARE20_24M,newCARE25_49M,newCARE50M;
double newCARE1F,newCARE1_4F,newCARE5_9F,newCARE10_14F,newCARE15_19F,newCARE20_24F,newCARE25_49F,newCARE50F;
double currentCARE1M,currentCARE1_4M,currentCARE5_9M,currentCARE10_14M,currentCARE15_19M,currentCARE20_24M,currentCARE25_49M,currentCARE50M;
double currentCARE1F,currentCARE1_4F,currentCARE5_9F,currentCARE10_14F,currentCARE15_19F,currentCARE20_24F,currentCARE25_49F,currentCARE50F;
String createdOn,period;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
       dbConn conn = new dbConn();
       
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
//        reportDuration="4";
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : APRIL "+year+" to SEPT "+year; 
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
     period="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration=" moh731.yearmonth="+prevYear+""+month;    
     period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration=" moh731.yearmonth="+year+"0"+month;  
    period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration="";     
      }
           
     System.out.println("period is : "+period);
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
    
//  facilityId=request.getParameter("facility");
  facilityId="403";
  
    HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet("DATIM DATA FROM MOH 731 ");

  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    stborder.setWrapText(true);
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_80_PERCENT.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

   HSSFCellStyle stylemainHeader = wb.createCellStyle();
stylemainHeader.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
stylemainHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylemainHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    stylemainHeader.setWrapText(true);
    
    HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);
    
    HSSFCellStyle styleminiHeader = wb.createCellStyle();
styleminiHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleminiHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleminiHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleminiHeader.setWrapText(true);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

  shet1.setColumnWidth(0, 4000);  
    for (int i=1;i<=17;i++){
   shet1.setColumnWidth(i, 2000);     
    }
 HSSFRow rw0=shet1.createRow(0);
 rw0.setHeightInPoints(25);
 HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
         
         
      c1.setCellValue(period);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=17;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      shet1.addMergedRegion(new CellRangeAddress(0,0,0,17));
      
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  conn.rs=conn.st.executeQuery(getName);
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
  }
     
     
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE moh731.SubPartnerID='"+facilityId+"' && "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
       
    String getData="SELECT SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
    + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324) FROM moh731 WHERE "
    + "moh731.SubPartnerID='"+facilityId+"' && "+duration ;
     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    if(conn.rs.next()==true){
HV0308=HV0309=HV0310=HV0311=HV0312=HV0320=HV0321=HV0322=HV0323=HV0324=0;
HV0314=HV0315=HV0316=HV0317=HV0318=HV0334=HV0335=HV0336=HV0337=HV0338=0;
currentART1M=currentART1_4M=currentART5_14M=currentART15_19M=currentART20M=0;
currentART1F=currentART1_4F=currentART5_14F=currentART15_19F=currentART20F=0;
newART1M=newART1_4M=newART5_9M=newART10_14M=newART15_19M=newART20_24M=newART25_49M=newART50M=0;
newART1F=newART1_4F=newART5_9F=newART10_14F=newART15_19F=newART20_24F=newART25_49F=newART50F=0;
newCARE1M=newCARE1_4M=newCARE5_9M=newCARE10_14M=newCARE15_19M=newCARE20_24M=newCARE25_49M=newCARE50M=0;
newCARE1F=newCARE1_4F=newCARE5_9F=newCARE10_14F=newCARE15_19F=newCARE20_24F=newCARE25_49F=newCARE50F=0;
currentCARE1M=currentCARE1_4M=currentCARE5_9M=currentCARE10_14M=currentCARE15_19M=currentCARE20_24M=currentCARE25_49M=currentCARE50M=0;
currentCARE1F=currentCARE1_4F=currentCARE5_9F=currentCARE10_14F=currentCARE15_19F=currentCARE20_24F=currentCARE25_49F=currentCARE50F=0;  
        
    HV0308=conn.rs.getInt(1);
    HV0309=conn.rs.getInt(2);
    HV0310=conn.rs.getInt(3);
    HV0311=conn.rs.getInt(4);
    HV0312=conn.rs.getInt(5);
    HV0320=conn.rs.getInt(6);
    HV0321=conn.rs.getInt(7);
    HV0322=conn.rs.getInt(8);
    HV0323=conn.rs.getInt(9);
    HV0324=conn.rs.getInt(10);
        
        
        
        
     String getCurrent="SELECT HV0314,HV0315,HV0316,HV0317,HV0318,"
    + "HV0334,HV0335,HV0336,HV0337,HV0338 FROM moh731 WHERE "
    + "moh731.SubPartnerID='"+facilityId+"' && yearmonth='"+maxYearMonth+"'";
     System.out.println("current : "+getCurrent);
     conn.rs1=conn.st1.executeQuery(getCurrent);
     if(conn.rs1.next()==true){
     HV0314=conn.rs1.getInt(1);
     HV0315=conn.rs1.getInt(2);
     HV0316=conn.rs1.getInt(3);
     HV0317=conn.rs1.getInt(4);
     HV0318=conn.rs1.getInt(5);
     HV0334=conn.rs1.getInt(6);
     HV0335=conn.rs1.getInt(7);
     HV0336=conn.rs1.getInt(8);
     HV0337=conn.rs1.getInt(9);
     HV0338=conn.rs1.getInt(10);    
         
         
         
         
     }
        
    }
    
rw0=shet1.createRow(2); 
rw0.setHeightInPoints(20);
 HSSFCell  c11,c12,c13,c14,c15,c16,c17,c18,c19,c110,c111,c112,c113,c114,c115,c116,c117,c118;
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
//         c113=rw0.createCell(12);
//         c114=rw0.createCell(13);
         
         
      c11.setCellValue("CURRENT ON ART");
      c12.setCellValue("MALE");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      
      c17.setCellValue("");
      
      c18.setCellValue("FEMALE");
      c19.setCellValue("");
      c110.setCellValue("");
      c111.setCellValue("");
      c112.setCellValue("");

      for(int i=0; i<=11;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }

//      c114.setCellValue("");
      shet1.addMergedRegion(new CellRangeAddress(2,2,1,5));
     shet1.addMergedRegion(new CellRangeAddress(2,2,7,11));
     
     
     
   rw0=shet1.createRow(3);
   rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
//         c113=rw0.createCell(12);
//         c114=rw0.createCell(13);
         
      c11.setCellValue("");
      c12.setCellValue("Paeds <15");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("Adults 15+ Yr");
      c16.setCellValue("");
      
      c17.setCellValue("");
      
      c18.setCellValue("Paeds <15yrs");
      c19.setCellValue("");
      c110.setCellValue("");
      c111.setCellValue("Adults 15+ Yr");
      c112.setCellValue("");

      for(int i=0; i<=11;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
      
      shet1.addMergedRegion(new CellRangeAddress(3,3,1,3));
     shet1.addMergedRegion(new CellRangeAddress(3,3,4,5));
     shet1.addMergedRegion(new CellRangeAddress(3,3,7,9));
     shet1.addMergedRegion(new CellRangeAddress(3,3,10,11));
     
      rw0=shet1.createRow(4);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         
         
      c11.setCellValue("");
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-14Y");
      c15.setCellValue("15-19Y");
      c16.setCellValue("20+Y");
      
      c17.setCellValue("");
      
      c18.setCellValue("<1");
      c19.setCellValue("1-4Y");
      c110.setCellValue("5-14Y");
      c111.setCellValue("15-19Y");
      c112.setCellValue("20+Y");
  
      for(int i=0; i<=11;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleminiHeader);
      }
      
//    VALUES FOR CURRENT ON ART
 currentART1M=(float)Math.round((0.03*HV0335));
 currentART1_4M=(float)Math.round((0.32*HV0335));
 currentART5_14M=(float)Math.round((0.65*HV0335));
 currentART15_19M=(float)Math.round((0.02*HV0337));
 currentART20M=(float)Math.round((0.98*HV0337));
 
currentART1F=(float)Math.round((0.03*HV0336));//NEED CLARIFICATION
currentART1_4F=(float)Math.round((0.32*HV0336));
currentART5_14F=(float)Math.round((0.65*HV0336));
currentART15_19F=(float)Math.round((0.02*HV0338));
currentART20F=(float)Math.round((0.98*HV0338));

     rw0=shet1.createRow(5);
     rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         
         
      c11.setCellValue("");
      c12.setCellValue(currentART1M);
      c13.setCellValue(currentART1_4M);
      c14.setCellValue(currentART5_14M);
      c15.setCellValue(currentART15_19M);
      c16.setCellValue(currentART20M);
 

      c17.setCellValue("");
      
      c18.setCellValue(currentART1F);
      c19.setCellValue(currentART1_4F);
      c110.setCellValue(currentART5_14F);
      c111.setCellValue(currentART15_19F);
      c112.setCellValue(currentART20F);
    
      for(int i=0; i<=11;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
      
      shet1.addMergedRegion(new CellRangeAddress(2,5,0,0));
     
     
      
//     NEW ON ART
      
        rw0=shet1.createRow(7);
        rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
   c11.setCellValue("NEW ON ART");
      c12.setCellValue("MALE");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("FEMALE");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
    
    for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
     shet1.addMergedRegion(new CellRangeAddress(7,7,1,8));
     shet1.addMergedRegion(new CellRangeAddress(7,7,10,17));
     
      
   rw0=shet1.createRow(8);
   rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
      c11.setCellValue("");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
      
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
      shet1.addMergedRegion(new CellRangeAddress(8,8,1,4));
     shet1.addMergedRegion(new CellRangeAddress(8,8,5,8));
     shet1.addMergedRegion(new CellRangeAddress(8,8,10,13));
     shet1.addMergedRegion(new CellRangeAddress(8,8,14,17)); 
     
      rw0=shet1.createRow(9);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("");
      
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
  
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleminiHeader);
      }
       
     //    VALUES
    
        newART1M=(float)Math.round((0.034*HV0321));
        newART1_4M=(float)Math.round((0.214*HV0321));
        newART5_9M=(float)Math.round((0.37*HV0321));
        newART10_14M=(float)Math.round((0.382*HV0321));
        newART15_19M=(float)Math.round((0.008*HV0323));
        newART20_24M=(float)Math.round((0.008*HV0323));
        newART25_49M=(float)Math.round((0.775*HV0323));
        newART50M=(float)Math.round((0.209*HV0323));
        
        newART1F=(float)Math.round((0.034*HV0322));
        newART1_4F=(float)Math.round((0.214*HV0322));
        newART5_9F=(float)Math.round((0.37*HV0322));
        newART10_14F=(float)Math.round((0.382*HV0322));
        newART15_19F=(float)Math.round((0.008*HV0324));
        newART20_24F=(float)Math.round((0.008*HV0324));
        newART25_49F=(float)Math.round((0.775*HV0324));
        newART50F=(float)Math.round((0.209*HV0324));
      
  
      
      rw0=shet1.createRow(10); 
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue(newART1M);
      c13.setCellValue(newART1_4M);
      c14.setCellValue(newART5_9M);
      c15.setCellValue(newART10_14M);
      c16.setCellValue(newART15_19M);
      c17.setCellValue(newART20_24M);
      c18.setCellValue(newART25_49M);
      c19.setCellValue(newART50M);
      
        
      c110.setCellValue("");
      
      c111.setCellValue(newART1F);
      c112.setCellValue(newART1_4F);
      c113.setCellValue(newART5_9F);
      c114.setCellValue(newART10_14F);
      c115.setCellValue(newART15_19F);
      c116.setCellValue(newART20_24F);
      c117.setCellValue(newART25_49F);
      c118.setCellValue(newART50F);
     
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
       
      shet1.addMergedRegion(new CellRangeAddress(7,10,0,0));
      //     NEW ON CARE
      
        rw0=shet1.createRow(12); 
        rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
   c11.setCellValue("NEW ON CARE");
      c12.setCellValue("MALE");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      c110.setCellValue("");
      c111.setCellValue("FEMALE");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
    
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
       
      shet1.addMergedRegion(new CellRangeAddress(12,12,1,8));
     shet1.addMergedRegion(new CellRangeAddress(12,12,10,17));
     
      
   rw0=shet1.createRow(13); 
   rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
      c11.setCellValue("");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
        
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
       
      shet1.addMergedRegion(new CellRangeAddress(13,13,1,4));
     shet1.addMergedRegion(new CellRangeAddress(13,13,5,8));
     shet1.addMergedRegion(new CellRangeAddress(13,13,10,13));
     shet1.addMergedRegion(new CellRangeAddress(13,13,14,17));
     
      rw0=shet1.createRow(14);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("");
      
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
  
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleminiHeader);
      }
       
     //    VALUES
    
        newCARE1M=(float)Math.round((0.18*HV0309));
        newCARE1_4M=(float)Math.round((0.34*HV0309));
        newCARE5_9M=(float)Math.round((0.28*HV0309));
        newCARE10_14M=(float)Math.round((0.20*HV0309));
        newCARE15_19M=(float)Math.round((0.02*HV0311));
        newCARE20_24M=(float)Math.round((0.09*HV0311));
        newCARE25_49M=(float)Math.round((0.80*HV0311));
        newCARE50M=(float)Math.round((0.09*HV0311));
        
        newCARE1F=(float)Math.round((0.18*HV0310));
        newCARE1_4F=(float)Math.round((0.34*HV0310));
        newCARE5_9F=(float)Math.round((0.28*HV0310));
        newCARE10_14F=(float)Math.round((0.20*HV0310));
        newCARE15_19F=(float)Math.round((0.02*HV0312));
        newCARE20_24F=(float)Math.round((0.09*HV0312));
        newCARE25_49F=(float)Math.round((0.80*HV0312));
        newCARE50F=(float)Math.round((0.09*HV0312));
      
  
      
      rw0=shet1.createRow(15);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue(newCARE1M);
      c13.setCellValue(newCARE1_4M);
      c14.setCellValue(newCARE5_9M);
      c15.setCellValue(newCARE10_14M);
      c16.setCellValue(newCARE15_19M);
      c17.setCellValue(newCARE20_24M);
      c18.setCellValue(newCARE25_49M);
      c19.setCellValue(newCARE50M);
      
        
      c110.setCellValue("");
      
      c111.setCellValue(newCARE1F);
      c112.setCellValue(newCARE1_4F);
      c113.setCellValue(newCARE5_9F);
      c114.setCellValue(newCARE10_14F);
      c115.setCellValue(newCARE15_19F);
      c116.setCellValue(newCARE20_24F);
      c117.setCellValue(newCARE25_49F);
      c118.setCellValue(newCARE50F);
      
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
       
      shet1.addMergedRegion(new CellRangeAddress(12,15,0,0));
      
      //     CURRENT ON CARE
      
        rw0=shet1.createRow(17); 
        rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
   c11.setCellValue("CURRENT ON CARE");
      c12.setCellValue("MALE");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      c110.setCellValue("");
      c111.setCellValue("FEMALE");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
    
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
       
      shet1.addMergedRegion(new CellRangeAddress(17,17,1,8));
     shet1.addMergedRegion(new CellRangeAddress(17,17,10,17));
     
   rw0=shet1.createRow(18);
   rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
      c11.setCellValue("");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
         for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
         
      shet1.addMergedRegion(new CellRangeAddress(18,18,1,4));
     shet1.addMergedRegion(new CellRangeAddress(18,18,5,8));
     shet1.addMergedRegion(new CellRangeAddress(18,18,10,13));
     shet1.addMergedRegion(new CellRangeAddress(18,18,14,17));
     
      rw0=shet1.createRow(19);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("");
      
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
  
       for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleminiHeader);
      }
       
     //    VALUES
    
        currentCARE1M=(float)Math.round((0.03*HV0315));
//        (float)Math.round(a);
        currentCARE1_4M=(float)Math.round((0.22*HV0315));
        currentCARE5_9M=(float)Math.round((0.37*HV0315));
        currentCARE10_14M=(float)Math.round((0.38*HV0315));
        currentCARE15_19M=(float)Math.round((0.02*HV0317));
        currentCARE20_24M=(float)Math.round((0.09*HV0317));
        currentCARE25_49M=(float)Math.round((0.80*HV0317));
        currentCARE50M=(float)Math.round((0.09*HV0317));
        
        currentCARE1F=(float)Math.round((0.03*HV0316));
        currentCARE1_4F=(float)Math.round((0.22*HV0316));
        currentCARE5_9F=(float)Math.round((0.37*HV0316));
        currentCARE10_14F=(float)Math.round((0.38*HV0316)); 
        currentCARE15_19F=(float)Math.round((0.02*HV0318));
        currentCARE20_24F=(float)Math.round((0.09*HV0318));
        currentCARE25_49F=(float)Math.round((0.80*HV0318));
        currentCARE50F=(float)Math.round((0.09*HV0318));
      
  
      
      rw0=shet1.createRow(20);
      rw0.setHeightInPoints(20);
         c11=rw0.createCell(0);
         c12=rw0.createCell(1);
         c13=rw0.createCell(2);
         c14=rw0.createCell(3);
         c15=rw0.createCell(4);
         c16=rw0.createCell(5);
         c17=rw0.createCell(6);
         c18=rw0.createCell(7);
         c19=rw0.createCell(8);
         c110=rw0.createCell(9);
         c111=rw0.createCell(10);
         c112=rw0.createCell(11);
         c113=rw0.createCell(12);
         c114=rw0.createCell(13);
         c115=rw0.createCell(14);
         c116=rw0.createCell(15);
         c117=rw0.createCell(16);
         c118=rw0.createCell(17);
         
         
      c11.setCellValue("");
      c12.setCellValue(currentCARE1M);
      c13.setCellValue(currentCARE1_4M);
      c14.setCellValue(currentCARE5_9M);
      c15.setCellValue(currentCARE10_14M);
      c16.setCellValue(currentCARE15_19M);
      c17.setCellValue(currentCARE20_24M);
      c18.setCellValue(currentCARE25_49M);
      c19.setCellValue(currentCARE50M);
      
        
      c110.setCellValue("");
      
      c111.setCellValue(currentCARE1F);
      c112.setCellValue(currentCARE1_4F);
      c113.setCellValue(currentCARE5_9F);
      c114.setCellValue(currentCARE10_14F);
      c115.setCellValue(currentCARE15_19F);
      c116.setCellValue(currentCARE20_24F);
      c117.setCellValue(currentCARE25_49F);
      c118.setCellValue(currentCARE50F);
      for(int i=0; i<=17;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
      
     shet1.addMergedRegion(new CellRangeAddress(17,20,0,0)); 
      
      
      if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
        IdGenerator IG = new IdGenerator();
        createdOn=IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH731_DATIM_REPORT_CREATED_ON_"+createdOn.trim()+".xls");
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
        Logger.getLogger(datimReport.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(datimReport.class.getName()).log(Level.SEVERE, null, ex);
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

}

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
import org.apache.poi.hssf.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Emmanuel E
 */
public class datimHTCResults extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            dbConn conn = new dbConn();
                 HSSFWorkbook wb=new HSSFWorkbook(); 
                 
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//HTC CODE FROM MAUREEN
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(1==1){
                     
              int less15m=0;
              int less15f=0;
              int gret15m=0;
              int gret15f=0;
              
              
                     ArrayList allFacilities = new ArrayList();
                         allFacilities.clear();
                 int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;

   year=month=prevYear=maxYearMonth=0;
 reportDuration=duration=semi_annual=quarter="";
 facilityName=mflcode=countyName=districtName=facilityIds=facilityId="";

   String facilityIds1="";
        facilityIds1="(";
           if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
 
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
     
      facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
    
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
                         }
   
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";     
     }
       
        else{
  
       facilityIds1="";    
        }   
        
     }      
 int TestedAdultMale=0,TestedAdultFemale=0;
 int TestedChildMale=0,TestedChildFemale=0;
 int HIV_AdultMale=0,HIV_AdultFemale=0;
 int HIV_ChildMale=0,HIV_ChildFemale=0;
 
double FemaleAdultTested;
double FemaleTestedChild;
double AdultFemaleHIV;
double ChildFemaleHIV;
 
 double  MaleAdultTested;
 double MaleTestedChild;
 double  AdultMaleHIV;
 double ChildMaleHIV;
 
 
  
  
  double FemaleAdultTested1=0;
double FemaleAdultTested4=0;
double FemaleAdultTested9=0;
double FemaleAdultTested14=0;
double FemaleAdultTested19=0;
double FemaleAdultTested24=0;
double FemaleAdultTested49=0;
double FemaleAdultTested50=0;

double FemaleTestedChild1=0;
double FemaleTestedChild4=0;
double FemaleTestedChild9=0;
double FemaleTestedChild14=0;
double FemaleTestedChild19=0;
double FemaleTestedChild24=0;
double FemaleTestedChild49=0;
double FemaleTestedChild50=0;

double AdultFemaleHIV19Neg=0;
double AdultFemaleHIV24Neg=0;
double AdultFemaleHIV49Neg=0;
double AdultFemaleHIV50Neg=0;

double AdultFemaleHIV19=0;
double AdultFemaleHIV24=0;
double AdultFemaleHIV49=0;
double AdultFemaleHIV50=0;

double ChildFemaleHIV1=0;
double ChildFemaleHIV4=0;
double ChildFemaleHIV9=0;
double ChildFemaleHIV14=0;

double ChildFemaleHIV1Neg=0;
double ChildFemaleHIV4Neg=0;
double ChildFemaleHIV9Neg=0;
double ChildFemaleHIV14Neg=0;
 
 
// MALES
  double MaleAdultTested19Neg=0;
  double MaleAdultTested21Neg=0;
  double MaleAdultTested49Neg=0;
  double MaleAdultTested50Neg=0;
  
  double MaleAdultTested19=0;
 double  MaleAdultTested24=0;
 double MaleAdultTested49=0;
 double MaleAdultTested50=0;
  
  
  double MaleTestedChild1=0;
  double MaleTestedChild4=0;
  double MaleTestedChild9=0;
  double MaleTestedChild14=0;
  
  double MaleTestedChild1Neg=0;
  double MaleTestedChild4Neg=0;
  double MaleTestedChild9Neg=0;
  double MaleTestedChild14Neg=0;
  
  double AdultMaleHIV19Neg=0;
  double AdultMaleHIV24Neg=0;
  double AdultMaleHIV49Neg=0;
  double AdultMaleHIV50Neg=0;
  
  double AdultMaleHIV19=0;
 double  AdultMaleHIV24=0;
  double AdultMaleHIV49=0;
  double AdultMaleHIV50=0;
  
  
 double ChildMaleHIV1=0;
 double ChildMaleHIV4=0;
double  ChildMaleHIV9=0;
 double ChildMaleHIV14=0;
 
 double ChildMaleHIV1Neg=0;
 double ChildMaleHIV4Neg=0;
 double ChildMaleHIV9Neg=0;
  double ChildMaleHIV14Neg=0;
  
  double splitData=0; int adderPos=0;
           double childSplitData=0;  
           
           int redalert=0;
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
//        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration1=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period1="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration1=" moh711.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : APRIL "+year+" to SEPT "+year; 
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
      duration1=" moh711.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period1="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration1=" moh711.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
     period1="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
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
     duration1=" moh711.yearmonth="+prevYear+""+month;    
     period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration1=" moh711.yearmonth="+year+"0"+month;  
    period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration1="";     
      }
        
      HSSFSheet shet3=wb.createSheet("HTC ");   
            HSSFCell  c11;
         FemaleAdultTested=0;
 FemaleTestedChild=0;
 AdultFemaleHIV=0;
 ChildFemaleHIV=0;
          double TotalTested=0;
            double    TotalPositiveFemale=0;
             double   TotalPositiveMale=0;
             double   TotalNegativeFemale=0;
             double   TotalNegativeMale=0;
 
// MALES
   MaleAdultTested=0;
  MaleTestedChild=0;
   AdultMaleHIV=0;
  ChildMaleHIV=0;
  double TotalPositive=0;
  double TotalNegative=0;
  
   String county="";
   String  district="";
    String facilityname="";
     
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
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
stylemainHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylemainHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
stylemainHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
stylemainHeader.setWrapText(true);

    HSSFCellStyle styleHeader = wb.createCellStyle();
    styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);  
    
    
       
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    stborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    stborder.setWrapText(true);

    
      HSSFFont font1 = wb.createFont();
            font1.setFontName("Cambria");
            font1.setColor((short) 0000);
           stborder.setFont(font1);
            
    
    
    
    // for the red color
   HSSFCellStyle redstyle = wb.createCellStyle();
    redstyle.setFillForegroundColor(HSSFColor.RED.index);
    redstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    redstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    redstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    redstyle.setWrapText(true);
    

  shet3.setColumnWidth(0, 4000);  
  shet3.setColumnWidth(1, 5000);  
  shet3.setColumnWidth(2,7000);  
  shet3.setColumnWidth(6,5000);  
//    for (int i=1;i<=42;i++){
//   shet3.setColumnWidth(i, 2000);     
//    }
 HSSFRow rw0=shet3.createRow(0);
 rw0.setHeightInPoints(30);
 HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
         
         
      c1.setCellValue(period1);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=51;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      shet3.addMergedRegion(new CellRangeAddress(0,0,0,17));
     HSSFCell  c12,c13,c14,c15,c16,c17,c18,c19,c20,c110,c111,c112,c113,c114,c115,c116,c117,c118,c219;
      HSSFCell    c119,c120,c121,c122,c123,c124,c125,c126,c127,c128,c129,c130,c131,c132,c133,c134,c135,c136,c137;  
   HSSFCell c211,c212,c213,c214,c215,c216,c217;
        rw0=shet3.createRow(2); 
        rw0.setHeightInPoints(30);
 
      
       
        
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
        
        
         c211.setCellValue("COUNTY");
         c212.setCellValue("SUB-COUNTY");
         c213.setCellValue("FACILITY");
         c214.setCellValue("MFL-CODE");
         c215.setCellValue("TYPE OF SUPPORT");
        
         c11=rw0.createCell(7);
         
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
      
         c119=rw0.createCell(25);
         c120=rw0.createCell(26);
         c121=rw0.createCell(27);
         c122=rw0.createCell(28);
         c123=rw0.createCell(29);
         c124=rw0.createCell(30);
         c125=rw0.createCell(31);
         c126=rw0.createCell(32);
         c127=rw0.createCell(33);
         c128=rw0.createCell(34);
         c129=rw0.createCell(35);
         c130=rw0.createCell(36);
         c131=rw0.createCell(37);
         c132=rw0.createCell(38);
         c133=rw0.createCell(39);
         c134=rw0.createCell(40);
         c135=rw0.createCell(41);
         c136=rw0.createCell(42);
         c137=rw0.createCell(43);
         
        c216.setCellValue("Total HIV+");
             
        c217.setCellValue("Total HIV+ (F)");
      c11.setCellValue("");
  
      c12.setCellValue("FEMALE(POSITIVE)");
              c216.setCellStyle(stylemainHeader);
              c217.setCellStyle(stylemainHeader);
              c11.setCellStyle(stylemainHeader);
              c12.setCellStyle(stylemainHeader);
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("MALE (POSITIVE)");
      c111.setCellStyle(stylemainHeader);
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
    
      
//      
//       rw0=shet3.createRow(2);
//        rw0.setHeightInPoints(20);
        
     
   c119.setCellValue("NEGATIVE");
      c120.setCellValue("FEMALE (NEGATIVE)");
      c119.setCellStyle(stylemainHeader);
      c120.setCellStyle(stylemainHeader);
      c121.setCellValue("");
      c122.setCellValue("");
      c123.setCellValue("");
      c124.setCellValue("");
      c125.setCellValue("");
      c126.setCellValue("");
      c127.setCellValue("");
      
      c128.setCellValue("");
      
      c129.setCellValue("MALE (NEGATIVE)");
      c129.setCellStyle(stylemainHeader);
      c130.setCellValue("");
      c131.setCellValue("");
      c132.setCellValue("");
      c133.setCellValue("");
      c134.setCellValue("");
      c135.setCellValue("");
      c136.setCellValue("");
      c137.setCellValue("");
     for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
     //add a blank header
      for(int i=43; i<=51;i++){
          c11=rw0.createCell(i);
          c11.setCellStyle(stylemainHeader);
      }
      
     
     
    for(int i=23; i<=43;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
      
   
     shet3.addMergedRegion(new CellRangeAddress(2,2,8,15));
     shet3.addMergedRegion(new CellRangeAddress(2,2,17,24));
     
     shet3.addMergedRegion(new CellRangeAddress(2,2,26,33));
     shet3.addMergedRegion(new CellRangeAddress(2,2,35,42));
     shet3.addMergedRegion(new CellRangeAddress(2,2,43,51));
     
      
   rw0=shet3.createRow(3);
   rw0.setHeightInPoints(30);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
         
         // other data
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
        
         
      c11.setCellValue("Num");
      c216.setCellValue("TOTAL HIV+");
      c217.setCellValue("TOTAL +VE (F)");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("TOTAL +VE MALE");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
      
      
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
        
         
      c11.setCellValue("TOTAL -VE(F)");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("TOTAL -VE(M)");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
      //201510
      
         c119=rw0.createCell(43);
         c119.setCellStyle(stylemainHeader);
         c120=rw0.createCell(44);
         c120.setCellStyle(stylemainHeader);
         c121=rw0.createCell(45);
         c121.setCellStyle(stylemainHeader);
         c122=rw0.createCell(46);
         c122.setCellStyle(stylemainHeader);
         c123=rw0.createCell(47);
         c123.setCellStyle(stylemainHeader);
         c124=rw0.createCell(48);
         c124.setCellStyle(stylemainHeader);
         c125=rw0.createCell(49);
         c125.setCellStyle(stylemainHeader);
         c126=rw0.createCell(50);
         c126.setCellStyle(stylemainHeader);
         c127=rw0.createCell(51);
         c127.setCellStyle(stylemainHeader);
         c128=rw0.createCell(52);
       
      c119.setCellValue("Female");
      c120.setCellValue("");
      c121.setCellValue("Male");
      c122.setCellValue("");
      c123.setCellValue("Sub-Total");
      c124.setCellValue("Positive");
      c125.setCellValue("Negative");
      c126.setCellValue("Sub-total");
      c127.setCellValue("Verification Status");
      
      
       for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
      
      shet3.addMergedRegion(new CellRangeAddress(3,3,8,11));
     shet3.addMergedRegion(new CellRangeAddress(3,3,12,15));
     shet3.addMergedRegion(new CellRangeAddress(3,3,17,20));
     shet3.addMergedRegion(new CellRangeAddress(3,3,21,24)); 
//     
      for(int k=23; k<=42;k++){
          c113=rw0.getCell(k);
          c113.setCellStyle(styleHeader);
      }
     
     shet3.addMergedRegion(new CellRangeAddress(3,3,26,29));
     shet3.addMergedRegion(new CellRangeAddress(3,3,30,33));
     shet3.addMergedRegion(new CellRangeAddress(3,3,35,38));
     shet3.addMergedRegion(new CellRangeAddress(3,3,39,42)); 
     shet3.addMergedRegion(new CellRangeAddress(3,3,43,44)); 
     shet3.addMergedRegion(new CellRangeAddress(3,3,45,46)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,47,47)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,48,48)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,49,49)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,50,50)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,51,51)); 
     
      rw0=shet3.createRow(4);
      rw0.setHeightInPoints(30);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
         
         
         // for ther est
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
         
      c11.setCellValue("NUM");
      c216.setCellValue("TOTAL HIV+");
      c217.setCellValue("TOTAL +VE(F)");
      c12.setCellValue("<1 ");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("TOTAL +VE MALE");
      shet3.addMergedRegion(new CellRangeAddress(3,4,16,16));
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
      
      
         for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
//     
      
      // for negative
      

  
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
         c119=rw0.createCell(43);
         
         
      c11.setCellValue("TOTAL -VE(F)");
       shet3.addMergedRegion(new CellRangeAddress(3,4,25,25));
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("TOTAL -VE(M)");
        shet3.addMergedRegion(new CellRangeAddress(3,4,34,34));
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
     
      
  //20151009
      
       
         c119=rw0.createCell(43);
         c119.setCellStyle(stylemainHeader);
         c120=rw0.createCell(44);
         c120.setCellStyle(stylemainHeader);
         c121=rw0.createCell(45);
         c121.setCellStyle(stylemainHeader);
         c122=rw0.createCell(46);
         c122.setCellStyle(stylemainHeader);
         c123=rw0.createCell(47);
         c123.setCellStyle(stylemainHeader);
         c124=rw0.createCell(48);
         c124.setCellStyle(stylemainHeader);
         c125=rw0.createCell(49);
         c125.setCellStyle(stylemainHeader);
         c126=rw0.createCell(50);
         c126.setCellStyle(stylemainHeader);
         c127=rw0.createCell(51);
         c127.setCellStyle(stylemainHeader);
         c128=rw0.createCell(52);
       
      
      
      c119.setCellValue("< 15");
      c120.setCellValue("15 +");
      c121.setCellValue("< 15");
      c122.setCellValue("15 + ");
      c123.setCellValue("Sub-total ");
      
       c124.setCellValue("Positive");
       c125.setCellValue("Negative");
       c126.setCellValue("Sub-total");
       c127.setCellValue("Verification Status");
       
  for(int l=20; l<=43;l++){
          c113=rw0.getCell(l);
          c113.setCellStyle(styleHeader);
      }
       
    
    
    
    
    
    
    
    
    
    double checkdiff=0;
    int count=4;
    TestedAdultMale=0;TestedAdultFemale=0;
 TestedChildMale=0;TestedChildFemale=0;
 HIV_AdultMale=0;HIV_AdultFemale=0;
 HIV_ChildMale=0;HIV_ChildFemale=0;
    
    //---------------------------------------------------------------------------
    
    
    String facilid="";
    String facilname="";
    String dsdta="";
   
                
          
    String get711data="SELECT(sum(VCTClient_Tested_CF) +sum( VCTClient_Tested_AF)+sum(DTCB_Test_Out_AF)+sum(DTCB_Test_In_AF))" //ADULTS TESTED FEMALE  
            + ",(sum(VCTClient_Tested_CM)+ sum(VCTClient_Tested_AM) +  sum(DTCB_Test_Out_AM) + sum(DTCB_Test_In_AM))"//ADULTS TESTED MALES
            + ", (sum(VCTClient_HIV_CF)+ sum(VCTClient_HIV_AF)+sum(DTCC_HIV_In_AF)+ sum(DTCC_HIV_Out_AF))" // ADULTS HIV+ FEMALE
            + ",(sum(VCTClient_HIV_CM)+sum(VCTClient_HIV_AM)+ sum(DTCC_HIV_In_AM) +sum(DTCC_HIV_Out_AM)) " // ADULTS HIV+ MALE
            + ", (sum(DTCB_Test_Out_CF) + sum(DTCB_Test_In_CF))" // CHILDREN TOTAL TESTED FEMALE
            + ", (sum(DTCB_Test_Out_CM) + sum(DTCB_Test_In_CM))" // CHILDREN TOTAL TESTED MALE
            + ", ( sum(DTCC_HIV_In_CF)+ sum(DTCC_HIV_Out_CF))" // CHILDREN POSITIVE FEMALE
            + ", (sum(DTCC_HIV_In_CM)+ sum(DTCC_HIV_Out_CM)),county.County,district.DistrictNom,"
            + "subpartnera.SubPartnerNom,subpartnera.CentreSanteId,subpartnera.HTC_Support1"// CHILDREN POSITIVE MALE
           +" FROM moh711 JOIN subpartnera "
            + "ON moh711.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && subpartnera.HTC=1  "
            + "GROUP BY moh711.SubPartnerID " ;
     System.out.println("711 : "+get711data);
    conn.rs=conn.st.executeQuery(get711data);
    while(conn.rs.next()){
     
       county=conn.rs.getString(9);
     district=conn.rs.getString(10);
     district=district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase();
     facilityname=conn.rs.getString(11);
     mflcode=conn.rs.getString(12);   
     dsdta=conn.rs.getString(13);   
     
     
     
    TestedAdultFemale=conn.rs.getInt(1);
    TestedAdultMale=conn.rs.getInt(2);
    HIV_AdultFemale=conn.rs.getInt(3);
    HIV_AdultMale=conn.rs.getInt(4);
    TestedChildFemale=conn.rs.getInt(5);
    TestedChildMale=conn.rs.getInt(6);
    HIV_ChildFemale=conn.rs.getInt(7);
    HIV_ChildMale=conn.rs.getInt(8);
    System.out.println(facilityname+" KKK "+HIV_AdultFemale+" "+HIV_AdultMale+" "+HIV_ChildFemale+"  "+HIV_ChildMale);
    System.out.println(facilityname+"TestedChildFemale "+TestedChildFemale+"  HIV_ChildFemale "+HIV_ChildFemale +"  TestedChildMale "+TestedChildMale+" HIV_ChildMale   "+HIV_ChildMale);
     
String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
String arrayDetails []=basicDetails.split("@");

  count++;
  rw0=shet3.createRow(count);
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
    
  HSSFCell  S3cell=rw0.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos : "+facilno+"     det : "+arrayDetails[j]);
     facilno++;  
   }
        
   
   
         
   System.out.println(facilityname  +"   TestedAdultFemale "+TestedAdultFemale+"TestedAdultMale  "+TestedAdultMale+" TestedChildFemale  "+TestedChildFemale+" TestedChildMale "+TestedChildMale +" HIV_AdultFemale  "+HIV_AdultFemale+" HIV_AdultMale "+HIV_AdultMale+" HIV_ChildFemale "+HIV_ChildFemale +" HIV_ChildMale "+HIV_ChildMale); 
 
      
//      FEMALES

FemaleAdultTested19=(float)Math.round((0.05*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((0.11*TestedAdultFemale));
FemaleAdultTested49=(float)Math.round((0.72*TestedAdultFemale));
FemaleAdultTested50=(float)Math.round((0.12*TestedAdultFemale));

FemaleTestedChild1=(float)Math.round((0.05*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((0.26*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((0.29*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((0.40*TestedChildFemale));


//postive
AdultFemaleHIV19=(float)Math.round((0.02*HIV_AdultFemale));
AdultFemaleHIV24=(float)Math.round((0.09*HIV_AdultFemale));
AdultFemaleHIV49=(float)Math.round((0.79*HIV_AdultFemale));
AdultFemaleHIV50=(float)Math.round((0.10*HIV_AdultFemale));



//positve
ChildFemaleHIV1=(float)Math.round((0.13*HIV_ChildFemale));
ChildFemaleHIV4=(float)Math.round((0.37*HIV_ChildFemale));
ChildFemaleHIV9=(float)Math.round((0.25*HIV_ChildFemale));
ChildFemaleHIV14=(float)Math.round((0.25*HIV_ChildFemale));





 
 
// MALES

  MaleAdultTested19=(float)Math.round((0.05*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((0.11*TestedAdultMale));
  MaleAdultTested49=(float)Math.round((0.72*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((0.12*TestedAdultMale));

  
  MaleTestedChild1=(float)Math.round((0.05*TestedChildMale));
  MaleTestedChild4=(float)Math.round((0.26*TestedChildMale));
  MaleTestedChild9=(float)Math.round((0.29*TestedChildMale));
  MaleTestedChild14=(float)Math.round((0.40*TestedChildMale));

//positive
  AdultMaleHIV19=(float)Math.round((0.02*HIV_AdultMale));
  AdultMaleHIV24=(float)Math.round((0.09*HIV_AdultMale));
  AdultMaleHIV49=(float)Math.round((0.79*HIV_AdultMale));
  AdultMaleHIV50=(float)Math.round((0.10*HIV_AdultMale));

  
  //positives
  ChildMaleHIV1=(float)Math.round((0.13*HIV_ChildMale));
  ChildMaleHIV4=(float)Math.round((0.37*HIV_ChildMale));
  ChildMaleHIV9=(float)Math.round((0.25*HIV_ChildMale));
  ChildMaleHIV14=(float)Math.round((0.25*HIV_ChildMale));
 
      
//            TestedAdultFemale=conn.rs.getInt(1);
//    TestedAdultMale=conn.rs.getInt(2);
//    HIV_AdultFemale=conn.rs.getInt(3);
//    HIV_AdultMale=conn.rs.getInt(4);
//    TestedChildFemale=conn.rs.getInt(5);
//    TestedChildMale=conn.rs.getInt(6);
//    HIV_ChildFemale=conn.rs.getInt(7);
//    HIV_ChildMale=conn.rs.getInt(8);
//           TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
              
      double totalpositivesmale=0;   
      double totalpositivesfemale=0;   
      double totalpositives=0;
      double totalnegatives=0; 
     double totalfemalehiv=0;
     double totalmalehiv=0;
     double totalfemaletesteddis=0;
     double totalmaletesteddis=0;
     double totalfemaletested=0;
     double totalmaletested=0;
     double negfem=0;
           double  negmale=0;
           int redalert1=0;
           int redalert2=0;
           int redalert3=0;
           int redalert4=0;
   totalpositives=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;    
   totalnegatives=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
   
               
     
//   total tested after distribution
   totalfemaletesteddis=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50;
   totalmaletesteddis=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
// totaltested after distriibution
   double totaltestedis=0;
   totaltestedis=totalfemaletesteddis+totalmaletesteddis;
TotalTested=TestedChildFemale+TestedChildMale+TestedAdultMale+TestedAdultFemale;
   
   
   totalfemaletested=TestedAdultFemale+TestedChildFemale;
   totalmaletested= TestedAdultMale+TestedChildMale;
 
   //poistives
   totalfemalehiv=HIV_AdultFemale+HIV_ChildFemale;
   totalmalehiv=HIV_AdultMale+HIV_ChildMale;
       //+ve after dist
   totalpositivesfemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
   totalpositivesmale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
// 
   
   // negative 
   negfem=totalfemaletested-totalfemalehiv;
   negmale=totalmaletested-totalmalehiv;
   double checkdiff1=0;
   double checkdiff2=0;
   double checkdiff3=0;
   int redfemalealert=0;
   int redmalealert=0;
   int finalalert=0;
   double totalcheckdiff=0;
   
   
   
   
   
    checkdiff=totalfemalehiv-totalpositivesfemale;
//      System.out.println("checkdiff female  "+checkdiff1);
   // positive female
 if(checkdiff>2 ||checkdiff<-2){
 redalert=1;
 }
 // positive male
   checkdiff1=totalmalehiv-totalpositivesmale;
//    System.out.println("checkdiff male  "+checkdiff1);
 if(checkdiff1>2 ||checkdiff1<-2){
 redalert1=1;
 }

 totalcheckdiff=TotalTested-totaltestedis;
// System.out.println("dqa  "+totalcheckdiff);
 if(totalcheckdiff>5 || totalcheckdiff<-5){
 finalalert=1;
 }
 
   
   
   
   
   
//   checkdiff=totalfemalehiv-totalpositivesfemale;
//   // positive female
// if(checkdiff>=2 ||checkdiff<=-2){
// redalert=1;
// }
// // positive male
//   checkdiff1=totalmalehiv-totalpositivesmale;
// if(checkdiff1>=2 ||checkdiff1<=-2){
// redalert1=1;
// }
//// negative female
//   checkdiff2=negfem-totalfemaletesteddis;
// if(checkdiff2>=2 ||checkdiff2<=-2){
// redalert2=1;
// }
// 
// // negativemale
//   checkdiff3=negmale-totalmaletesteddis;
// if(checkdiff3>=2 ||checkdiff3<=-2){
// redalert3=1;
// }
// 
// totalcheckdiff=checkdiff+checkdiff1+checkdiff2+checkdiff3;
// if(totalcheckdiff>=5 || totalcheckdiff<=-5){
// finalalert=1;
// }
   adderPos=0;
   double Totalhivfemale=0;
   double Totalhivmale=0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   splitData=0;  adderPos=0;
            childSplitData=0;   
//   // adult female hiv+
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50 ;
//
System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
 adderPos=0;
 
 while(splitData<HIV_AdultFemale){ 
 AdultFemaleHIV49+=1; 
 splitData++;
 
 
}
 
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50 ;
 while(splitData>HIV_AdultFemale){ 
 AdultFemaleHIV49-=1; 
 splitData--;
}
 //tested female adults
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultFemale){ 
 FemaleAdultTested49+=1; 
 splitData++;
}
 
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultFemale){ 
 FemaleAdultTested49-=1; 
 splitData--;
}
   
 
 
// adult male hiv+
 
 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50 ;
  adderPos=0;
 while(splitData<HIV_AdultMale){ 
 AdultMaleHIV49+=1; 
 splitData++;
}
 
splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50 ;
 adderPos=0;
 while(splitData>HIV_AdultMale){ 
 AdultMaleHIV49-=1; 
 splitData--;
}
 
 //tested male adults
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultMale){ 
 MaleAdultTested49+=1; 
 splitData++;
}
 
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultMale){ 
 MaleAdultTested49-=1; 
 splitData--;
}
 
 
 
 
 
 
 
// for child female tested 
  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  
  System.out.println(facilityname+" "+childSplitData+" b4 jjj "+TestedChildFemale);
   adderPos=0;
while(childSplitData<TestedChildFemale){ 
if(adderPos==0){
  FemaleTestedChild14+=1;   
 }
if(adderPos==1){
 FemaleTestedChild9+=1;    
 }
if(adderPos==2){
 FemaleTestedChild4+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildFemale){ 
 if(adderPos==0){
  FemaleTestedChild14-=1;   
 }
if(adderPos==1){
 FemaleTestedChild9-=1;    
 }
if(adderPos==2){
 FemaleTestedChild4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}
System.out.println(facilityname+"     "+childSplitData+" after jjj "+TestedChildFemale);
// for child male hiv

 
 
 
 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  System.out.println(facilityname+"  mmmm  "+childSplitData+"    "+HIV_ChildFemale);
   adderPos=0;
   double diff=0;
while(childSplitData<HIV_ChildFemale){ 
  diff=FemaleTestedChild14-ChildFemaleHIV14;
 if(adderPos==0){
  if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
 
 }
  
 if(adderPos==1){

   if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
 }
 if(adderPos==2){
  
   if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){ 
  
  if(adderPos==0){
 
  ChildFemaleHIV14-=1;   
 

 
 }
  
 if(adderPos==1){

  
   ChildFemaleHIV9-=1;   
  
 }
 if(adderPos==2){
  
 
   ChildFemaleHIV4-=1;   
  
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}
   
// tested male _______________________________________________________________________
  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
while(childSplitData<TestedChildMale){ 
 if(adderPos==0){
  MaleTestedChild14+=1;   
 }
 else if(adderPos==1){
 MaleTestedChild9+=1;    
 }
 else if(adderPos==2){
 MaleTestedChild4+=1;    
 }
 
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}

  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildMale){ 
 if(adderPos==0){
  MaleTestedChild14-=1;   
 }
 else if(adderPos==1){
 MaleTestedChild9-=1;    
 }
 else if(adderPos==2){
 MaleTestedChild4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}




// for child male +ve 
  childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
 
   adderPos=0;
while(childSplitData<HIV_ChildMale){ 
  if(adderPos==0){
   
     if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
     else if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 else if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  
 }
 else if(adderPos==1){
      if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 else if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  else if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
    
 }
 if(adderPos==2){
  
   if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  else if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
  else if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}
 childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildMale){ 
 if(adderPos==0){
   
  
  ChildMaleHIV14-=1;   
 
  
  }
  

 else if(adderPos==1){
     
   ChildMaleHIV9-=1;   
  
    
 }
 if(adderPos==2){
  
  
   ChildMaleHIV4-=1;   
  
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){
     
     adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}





///


//  System.out.println("Neg nn  "+ChildMaleHIV1Neg+ " "+ChildMaleHIV4Neg+" "+ChildMaleHIV9Neg+" "+ ChildMaleHIV14Neg);
//  System.out.println("tested nn  "+MaleTestedChild1+ " "+MaleTestedChild4+" "+MaleTestedChild9+" "+ MaleTestedChild14);
//  System.out.println("hiv+ nnn  "+ChildMaleHIV1+ " "+ChildMaleHIV4+" "+ChildMaleHIV9+" "+ ChildMaleHIV14);
//  
  // all positives

//TotalPositive=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+
//        ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 +ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//  
//TotalNegative=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+
//        ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg +ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
  System.out.println(facilityname+" KKK "+HIV_AdultFemale+" "+HIV_AdultMale+" "+HIV_ChildFemale+"  "+HIV_ChildMale);
    System.out.println(facilityname+"TestedChildFemale "+TestedChildFemale+"  HIV_ChildFemale "+HIV_ChildFemale +"  TestedChildMale "+TestedChildMale+" HIV_ChildMale   "+HIV_ChildMale);
     double totaltestedmale1=0;
     double totaltestedfemale1=0;
 TotalTested=TestedChildFemale+TestedChildMale+TestedAdultMale+TestedAdultFemale;
 totaltestedmale1=TestedChildMale+TestedAdultMale;
 totaltestedfemale1=TestedChildFemale+TestedAdultFemale;
 TotalPositiveFemale=HIV_ChildFemale + HIV_AdultFemale;
 TotalPositiveMale=HIV_ChildMale + HIV_AdultMale;
 TotalPositive=HIV_ChildFemale+HIV_AdultFemale+HIV_ChildMale+HIV_AdultMale;
 TotalNegativeFemale=totaltestedfemale1-TotalPositiveFemale;
 TotalNegativeMale=totaltestedmale1-TotalPositiveMale;
 TotalNegative=TotalNegativeMale+TotalNegativeFemale;
  //201510
 //this code was added later
 
 less15f=TestedChildFemale;
 less15m=TestedChildMale;
 gret15m=TestedAdultMale;
 gret15f=TestedAdultFemale;
 

// TotalNegativeFemale=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
//TotalNegativeMale=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;

//                TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
//                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
//                TotalPositiveMale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//               
//System.out.println(MaleTestedChild14 +" bbbbb  "+ChildMaleHIV14+"    mmmmm   "+ (MaleTestedChild14-ChildMaleHIV14));
 

         rw0.setHeightInPoints(25);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
         // the rest
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c20=rw0.createCell(16);
         c110=rw0.createCell(17);
         c111=rw0.createCell(18);
         c112=rw0.createCell(19);
         c113=rw0.createCell(20);
         c114=rw0.createCell(21);
         c115=rw0.createCell(22);
         c116=rw0.createCell(23);
         c117=rw0.createCell(24);
        
         
        //c11.setCellValue(facilname);
//String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
         c211.setCellValue(county);
         c212.setCellValue(district);
         c213.setCellValue(facilityname);
         c214.setCellValue(mflcode);
         c215.setCellValue(dsdta);
         shet3.addMergedRegion(new CellRangeAddress(2,4,0,0));
         shet3.addMergedRegion(new CellRangeAddress(2,4,1,1));
         shet3.addMergedRegion(new CellRangeAddress(2,4,2,2));
         shet3.addMergedRegion(new CellRangeAddress(2,4,3,3));
         shet3.addMergedRegion(new CellRangeAddress(2,4,4,4));
//      Female   
      c11.setCellValue(TotalTested);
      c216.setCellValue(TotalPositive);
      
      c217.setCellValue(TotalPositiveFemale);
      
         shet3.addMergedRegion(new CellRangeAddress(2,4,5,5));
         shet3.addMergedRegion(new CellRangeAddress(2,4,6,6));
         shet3.addMergedRegion(new CellRangeAddress(3,4,7,7));
      c12.setCellValue((float)Math.round(ChildFemaleHIV1));
      c13.setCellValue((float)Math.round(ChildFemaleHIV4));
      c14.setCellValue((float)Math.round(ChildFemaleHIV9));
      c15.setCellValue((float)Math.round(ChildFemaleHIV14));
      c16.setCellValue((float)Math.round(AdultFemaleHIV19));
      c17.setCellValue((float)Math.round(AdultFemaleHIV24));
      c18.setCellValue((float)Math.round(AdultFemaleHIV49));
      c19.setCellValue((float)Math.round(AdultFemaleHIV50));
      
     
      c20.setCellValue(TotalPositiveMale);
      
      //male
      c110.setCellValue((float)Math.round(ChildMaleHIV1));
      c111.setCellValue((float)Math.round(ChildMaleHIV4));
      c112.setCellValue((float)Math.round(ChildMaleHIV9));
      c113.setCellValue((float)Math.round(ChildMaleHIV14));
      c114.setCellValue((float)Math.round(AdultMaleHIV19));
      c115.setCellValue((float)Math.round(AdultMaleHIV24));
      c116.setCellValue((float)Math.round(AdultMaleHIV49));
      c117.setCellValue((float)Math.round(AdultMaleHIV50));
    
      for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
          
//        System.out.println("red "+redalert +" "+redalert1) ;
          if(redalert==1){
           c217=rw0.getCell(6);
          c217.setCellStyle(redstyle);
          }
           if(redalert1==1){
         c20=rw0.getCell(16);
          c20.setCellStyle(redstyle);
      }
      }
      
//      shet3.addMergedRegion(new CellRangeAddress(2,5,0,0));
     
   
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
         c119=rw0.createCell(43);
  



  
   int neg1male=0;
  int neg4male=0;
  int neg9male=0;
  int neg14male=0;
  int neg19male=0;
  int neg24male=0;
  int neg49male=0;
  int neg50male=0;
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);
if(AdultMaleHIV19Neg<=-1){
neg19male=1;
}
if(AdultMaleHIV24Neg<=-1){
neg24male=1;
}
if(AdultMaleHIV49Neg<=-1){
neg49male=1;
}
if(AdultMaleHIV50Neg<=-1){
neg50male=1;
}
 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(ChildMaleHIV14);

 if(ChildMaleHIV1Neg<=-1){
neg1male=1;
}
if(ChildMaleHIV4Neg<=-1){
neg4male=1;
}
if(ChildMaleHIV9Neg<=-1){
neg9male=1;
}
if(ChildMaleHIV14Neg<=-1){
neg14male=1;
} 
  
//negative

  int neg1female=0;
  int neg4female=0;
  int neg9female=0;
  int neg14female=0;
  int neg19female=0;
  int neg24female=0;
  int neg49female=0;
  int neg50female=0;
ChildFemaleHIV1Neg=(float)Math.round(FemaleTestedChild1)-(ChildFemaleHIV1);
ChildFemaleHIV4Neg=(float)Math.round(FemaleTestedChild4)-(ChildFemaleHIV4);
ChildFemaleHIV9Neg=(float)Math.round(FemaleTestedChild9)-(ChildFemaleHIV9);
ChildFemaleHIV14Neg=(float)Math.round(FemaleTestedChild14)-(ChildFemaleHIV14);


 if(ChildFemaleHIV1Neg<=-1){
neg1female=1;
}
if(ChildFemaleHIV4Neg<=-1){
neg4female=1;
}
if(ChildFemaleHIV9Neg<=-1){
neg9female=1;
}
if(ChildFemaleHIV14Neg<=-1){
neg14female=1;
} 

System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);
  
   if(AdultFemaleHIV19Neg<=-1){
neg19female=1;
}
if(AdultFemaleHIV24Neg<=-1){
neg24female=1;
}
if(AdultFemaleHIV49Neg<=-1){
neg49female=1;
}
if(AdultFemaleHIV50Neg<=-1){
neg50female=1;
} 
double TotalNegativeFemale1=0;
 double TotalNegativeMale1=0;
         TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
                TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
// negative female
   checkdiff2=negfem-TotalNegativeFemale1;
 if(checkdiff2>2 ||checkdiff2<-2){
 redalert2=1;
 }
 
 // negativemale
   checkdiff3=negmale-TotalNegativeMale1;
 if(checkdiff3>2 ||checkdiff3<-2){
 redalert3=1;
 }

      c11.setCellValue((float)Math.round(TotalNegativeFemale));
      c12.setCellValue((float)Math.round(ChildFemaleHIV1Neg));
      c13.setCellValue((float)Math.round(ChildFemaleHIV4Neg));
      c14.setCellValue((float)Math.round(ChildFemaleHIV9Neg));
      c15.setCellValue((float)Math.round(ChildFemaleHIV14Neg));
      c16.setCellValue((float)Math.round(AdultFemaleHIV19Neg));
      c17.setCellValue((float)Math.round(AdultFemaleHIV24Neg));
      c18.setCellValue((float)Math.round(AdultFemaleHIV49Neg));
      c19.setCellValue((float)Math.round(AdultFemaleHIV50Neg));
      
        
      c110.setCellValue((float)Math.round(TotalNegativeMale));
      
      c111.setCellValue((float)Math.round(ChildMaleHIV1Neg));
      c112.setCellValue((float)Math.round(ChildMaleHIV4Neg));
      c113.setCellValue((float)Math.round(ChildMaleHIV9Neg));
      c114.setCellValue((float)Math.round(ChildMaleHIV14Neg));
      c115.setCellValue((float)Math.round(AdultMaleHIV19Neg));
      c116.setCellValue((float)Math.round(AdultMaleHIV24Neg));
      c117.setCellValue((float)Math.round(AdultMaleHIV49Neg));
      c118.setCellValue((float)Math.round(AdultMaleHIV50Neg));
      c119.setCellValue(less15f);
      
   
         c120=rw0.createCell(44);
         c120.setCellStyle(stborder);
         c121=rw0.createCell(45);
         c121.setCellStyle(stborder);
         c122=rw0.createCell(46);
         c122.setCellStyle(stborder);
         c123=rw0.createCell(47);
         c123.setCellStyle(stborder);
         c124=rw0.createCell(48);
         c124.setCellStyle(stborder);
         c125=rw0.createCell(49);
         c125.setCellStyle(stborder);
         c126=rw0.createCell(50);
         c126.setCellStyle(stborder);
         c127=rw0.createCell(51);
         c127.setCellStyle(stborder);
         c128=rw0.createCell(52);
       
      
      
      
      c120.setCellValue(gret15f);
      c121.setCellValue(less15m);
      c122.setCellValue(gret15m);
      c123.setCellValue(TotalTested);
      
       c124.setCellValue(TotalPositive);
       c125.setCellValue(TotalNegative);
       c126.setCellValue(TotalTested);
       c127.setCellValue("PASSED");
       
      
      
      System.out.println(facilityname  +"    jjj  "+AdultMaleHIV19Neg+"__________"+AdultMaleHIV24Neg+"__________"+AdultMaleHIV49Neg+"__________"+AdultMaleHIV50Neg+"__________"+ChildMaleHIV1Neg+"__________"+ChildMaleHIV4Neg+"__________"+ChildMaleHIV9Neg+"__________"+ChildMaleHIV14Neg);
      
        for(int i=23; i<=43;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
          
          
           if(redalert2==1){
           c11=rw0.getCell(25);
          c11.setCellStyle(redstyle);
          }
           if(redalert3==1){
       c110=rw0.getCell(34);
          c110.setCellStyle(redstyle);
      }
           
            if(finalalert==1){
          c119=rw0.getCell(43);
          c119.setCellStyle(redstyle);
          c119.setCellValue("FAILED");
      }
            
            
                       
           
  if(neg1female==1){
             c12=rw0.getCell(26);
          c12.setCellStyle(redstyle);
  }
  if(neg4female==1){
     c12=rw0.getCell(27);
     c12.setCellStyle(redstyle);}
  if(neg9female==1){
     c13=rw0.getCell(28);
     c13.setCellStyle(redstyle);}
  if(neg14female==1){
     c14=rw0.getCell(29);
      c14.setCellStyle(redstyle);}
  if(neg19female==1){
     c15=rw0.getCell(30);
     c15.setCellStyle(redstyle);}
  if(neg24female==1){
     c16=rw0.getCell(31);
     c16.setCellStyle(redstyle);}
  if(neg49female==1){
     c17=rw0.getCell(32);
     c17.setCellStyle(redstyle);}
  if(neg50female==1){
     c18=rw0.getCell(33);
     c18.setCellStyle(redstyle);}
  
  //male
  if(neg1male==1){
             c111=rw0.getCell(35);
          c111.setCellStyle(redstyle);
  }
  if(neg4male==1){
     c112=rw0.getCell(36);
     c112.setCellStyle(redstyle);}
  if(neg9male==1){
     c113=rw0.getCell(37);
     c113.setCellStyle(redstyle);}
  if(neg14male==1){
     c114=rw0.getCell(38);
      c114.setCellStyle(redstyle);}
  if(neg19male==1){
     c115=rw0.getCell(39);
     c115.setCellStyle(redstyle);}
  if(neg24male==1){
     c116=rw0.getCell(40);
     c116.setCellStyle(redstyle);}
  if(neg49male==1){
     c117=rw0.getCell(41);
     c117.setCellStyle(redstyle);}
  if(neg50male==1){
     c118=rw0.getCell(42);
     c118.setCellStyle(redstyle);}
           
      }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}
                     
                     
                     
                     
                     
                     
                 
                 }
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
 //==============================================================================================VMMC
 //______________      HSSFWorkbook wb=new HSSFWorkbook();__________________________________________
 //_________________________________________________________________________________________________
 //_________________________________________________________________________________________________
 //_________________________________________________________________________________________________
 //==================================================================================================
            
            if(1==1){
            
            
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
            yearmonth="Annual Report For "+year;
         duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
            yearmonth="Semi Annual Report For "+prevYear+" Oct to "+year+" Mar";
       duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
            yearmonth="Semi Annual Report for Apr to  Sep "+year;
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
           
          
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" "+form+".yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
 yearmonth="Quarterly Report For "+prevYear+" "+conn.rs.getString(2);
      }
      else{
           yearmonth="Quarterly Report For "+year+" ("+conn.rs.getString(2)+")";
     duration=" "+form+".yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
                              }
        }  
        
      else if(reportDuration.equals("4")){
     monthcopy=Integer.parseInt(request.getParameter("month"));
   
     
//     month=5;
     if(monthcopy>=10){
        
           yearmonth="Monthly Report For "+prevYear+"_("+month+")";
     duration=" "+form+".yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" "+form+".yearmonth="+year+"0"+month;  
    yearmonth="Monthly Report For "+year+"_("+month+")";
     }
      }
      else {
     duration="";     
           }
        
	//======================================================================

//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   String subcountywhere="";
   
   String subcounty="";
   
   if(!request.getParameter("subcounty").equals("")){
   
       subcounty=request.getParameter("subcounty");
       
                                                    }
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and district.countyid = '"+county+"'";    
   
                          }
    
     if(!subcounty.equals("")){
   
   subcountywhere=" and subpartnera.DistrictID = '"+subcounty+"'";    
   
                              }
   
   if(!facil.equals("")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'";    
   
                        } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration+" "+countywhere+" "+subcountywhere;  
    
    
    
//=====================================================================================================    
//FINDFACILITIES 
 
   
        
//=====================================================================================================    
    
//______________________________________________________________________________________
  //                       NOW CREATE THE WORKSHEETS          
  //______________________________________________________________________________________  
            
        
              
            
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
            fontx.setBoldweight(Font.BOLDWEIGHT_BOLD);
            stylex.setFont(fontx);
            stylex.setWrapText(true);

   
   HSSFSheet shet=wb.createSheet(form.toUpperCase());   
     
    //create headers for that worksheet
      
      HSSFRow rw=shet.createRow(0);
      rw.setHeightInPoints(25);
 HSSFCell cl0= rw.createCell(0);
 cl0.setCellValue("DATIM "+yearmonth);
 cl0.setCellStyle(stylex);
    
 for(int a=1;a<=13;a++){ 
 HSSFCell clx= rw.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 
 HSSFRow rw1=shet.createRow(1);
 rw1.setHeightInPoints(23);
 HSSFCell cl= rw1.createCell(0);
 cl.setCellValue("");
 cl.setCellStyle(style2);
 
  for(int a=1;a<=13;a++){ 
 HSSFCell clx= rw1.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(style2);
                       }
  
HSSFRow rw2=shet.createRow(2);
rw2.setHeightInPoints(23);
HSSFCell cl3= rw2.createCell(0);
cl3.setCellValue("VMMC CIRC ");
cl3.setCellStyle(stylex);
     
  HSSFCell cl31= rw2.createCell(1);
 cl31.setCellValue("");
 cl31.setCellStyle(stylex);
 

  for(int a=2;a<=5;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                       }
  
  HSSFCell cl3b= rw2.createCell(6);
cl3b.setCellValue("Disaggregated by Age ");
cl3b.setCellStyle(stylex); 

   for(int a=7;a<=13;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                         }

     HSSFCell cl3c= rw2.createCell(14);
cl3c.setCellValue("Disaggregated by HIV Status ");
cl3c.setCellStyle(stylex); 

   for(int a=15;a<=16;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
   

    HSSFCell cl3d= rw2.createCell(17);
cl3d.setCellValue("Disaggregated by Circumcission Technique ");
cl3d.setCellStyle(stylex); 
 
  HSSFCell cl3e= rw2.createCell(18);
cl3e.setCellValue("Disaggregated by Followup (For surgical only) ");
cl3e.setCellStyle(stylex); 

   for(int a=19;a<20;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
   
     HSSFCell cl3f= rw2.createCell(20);
cl3f.setCellValue("VMMC_AE Disaggregated by AE Type");
cl3f.setCellStyle(stylex); 
   
 for(int a=21;a<27;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
     //
 String VMMCheaders[]={"County","Sub-County","Facility Name","Mfl Code","Type Of Support","Numerator","< 1","1-9","10-14","15-19","20-24","25-29","30-49","50+","HIV-positive clients (tested HIV positive at VMMC site","HIV-negative clients (tested HIV negative at VMMC program","Unknown HIV status/not tested for HIV on site/indeterminate HIV status/undocumented HIV status","Device-Based","Number of surgical VMMC clients who returned at least once for follow-up care within 14 days of surgery","Number of surgical VMMC clients who did not return for follow-up care within 14 days of surgery","Numerator","Surgical Intra- operative: Moderate","Surgical Intra- operative: Severe","Surgical Post- operative: Moderate","Surgical Post- operative: Severe","Medical Device-related: Moderate","Medical Device-related: Severe"};
 
 HSSFRow rw3=shet.createRow(3);
 rw3.setHeightInPoints(63);
 for(int a=0;a<VMMCheaders.length;a++){ 
 HSSFCell clx= rw3.createCell(a);
 clx.setCellValue(VMMCheaders[a]);
 clx.setCellStyle(stylex);
                                      }

  
    //shet.addMergedRegion(new CellRangeAddress(2,10,0,0));  
    shet.addMergedRegion(new CellRangeAddress(0,0,0,13));  
    shet.addMergedRegion(new CellRangeAddress(1,1,0,13));  
  //shet.addMergedRegion(new CellRangeAddress(1,1,0,4));  
    shet.addMergedRegion(new CellRangeAddress(2,2,1,5));  
    shet.addMergedRegion(new CellRangeAddress(2,2,6,13));  
    shet.addMergedRegion(new CellRangeAddress(2,2,14,16));  
    shet.addMergedRegion(new CellRangeAddress(2,2,18,19));  
    shet.addMergedRegion(new CellRangeAddress(2,2,20,26));  
    shet.setColumnWidth(0, 5000);  
    shet.setColumnWidth(1, 5000);  
    shet.setColumnWidth(2, 6000);  
    shet.setColumnWidth(3, 6000);  
    shet.setColumnWidth(4, 6000);  
    shet.setColumnWidth(5, 2500);  
    shet.setColumnWidth(14, 6500);  
    shet.setColumnWidth(15, 6500);  
    shet.setColumnWidth(16, 6500);  
    shet.setColumnWidth(17, 6500);  
    shet.setColumnWidth(18, 6500);  
    shet.setColumnWidth(19, 6500);  
    shet.setColumnWidth(20, 3000);  
    shet.setColumnWidth(21, 6000);  
    shet.setColumnWidth(22, 6000);  
    shet.setColumnWidth(23, 6000);  
    shet.setColumnWidth(24, 6000);  
    shet.setColumnWidth(25, 6000);  
    shet.setColumnWidth(26, 6000);  
    
     

    
    
        
//getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode , sum(P51DT) as P51DT, sum(P51D1) as P51D1,   sum(P51D9) as P51D9,   sum(P51D10) as P51D10,   sum(P51D19) as P51D19,sum(P51D24) as P51D24, sum(P51D29) as P51D29,  sum(P51D49) as  P51D49,   sum(P51D50) as P51D50,    sum(P51DT) as P51DT,   sum(P521DM) as  P521DM,    sum(P521DS) as P521DS,   sum(P521DT) as P521DT,   sum(P522DM) as P522DM,    sum(P522DS) as P522DS,    sum(P522DT) as P522DT,   sum(P52DM) as  P52DM,   sum(P52DS) as P52DS,    sum(P52DT) as P52DT,   sum(P511KP) as P511KP,   sum(P511KN) as P511KN,   sum(P511KU) as P511KU,   sum(P511Surg) as P511Surg,   sum(P511Dev) as P511Dev,   sum(P53DF) as P53DF,    sum(P53DO) as P53DO,   sum(P53DM) as P53DM,    sum(P53D) as P53D,   sum(P54D) as P54D  from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID  ";
getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode , sum(P51DT) as P51DT, sum(P51D1) as P51D1,   sum(P51D9) as P51D9,   sum(P51D10) as P51D10,   sum(P51D19) as P51D19,sum(P51D24) as P51D24, sum(P51D29) as P51D29,  sum(P51D49) as  P51D49,   sum(P51D50) as P51D50,     sum(P511KP) as P511KP ,   sum(P511KN) as P511KN,   sum(P511KU) as P511KU ,   sum(P511Dev) as P511Dev ,   sum(P54D) as P54D ,   sum(P511Surg) as P511Surg , sum(P521DM + P521DS + P522DM + P522DS) as aenumerator ,  sum(P521DM) as  P521DM ,  sum(P521DS) as P521DS ,   sum(P522DM) as P522DM ,    sum(P522DS) as P522DS from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID  ";

            System.out.println(getexistingdata);
            String P51D1 = "";
            String P51D9 = "";
            String P51D10 = "";
            String P51D19 = "";
            String P51D24 = "";
            String P51D29 = "";
            String P51D49 = "";
            String P51D50 = "";
            String P51DT = "";
            String P511KP = "";
            String P521DM = "";
            String P521DS = "";
//            String P521DT = "";
            String P522DM = "";
            String P522DS = "";
//            String P522DT = "";
//            String P52DM = "";
//            String P52DS = "";
//            String P52DT = "";
//            String P511KP = "";
           String P511KN = "";
            String P511KU = "";
            String P511Surg ="";
            String P511Dev ="";
//            String P53DF = "";
//            String P53DO = "";
//            String P53DM = "";
//            String P53D = "";
            String P54D = "";


String distid="";




int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    
    int r=4;
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

                 P51D29 = conn.rs.getString("P51D29");
                if (P51D29 == null) {
                    P51D29 = "";
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
//
                P521DS = conn.rs.getString("P521DS");
                if (P521DS == null) {
                    P521DS = "";
                }
//
//                P521DT = conn.rs.getString("P521DT");
//                if (P521DT == null) {
//                    P521DT = "";
//                }
//
//
//
                P522DM = conn.rs.getString("P522DM");
                if (P522DM == null) {
                    P522DM = "";
                }
//
                P522DS = conn.rs.getString("P522DS");
                if (P522DS == null) {
                    P522DS = "";
                }
//
//                P522DT = conn.rs.getString("P522DT");
//                if (P522DT == null) {
//                    P522DT = "";
//                }
//
//
//                P52DM = conn.rs.getString("P52DM");
//                if (P52DM == null) {
//                    P52DM = "";
//                }
//
//
//                P52DS = conn.rs.getString("P52DS");
//                if (P52DS == null) {
//                    P52DS = "";
//                }
//
//
//                P52DT = conn.rs.getString("P52DT");
//                if (P52DT == null) {
//                    P52DT = "";
//                }
//
//
                P511KP = conn.rs.getString("P511KP");
                if (P511KP == null) {
                    P511KP = "";
                }
//
//
                P511KN = conn.rs.getString("P511KN");
                if (P511KN == null) {
                    P511KN = "";
                }

                P511KU = conn.rs.getString("P511KU");
                if (P511KU == null) {
                    P511KU = "";
                }
//
                P511Surg = conn.rs.getString("P511Surg");
                if (P511Surg == null) {
                    P511Surg = "";
                }
//
//
                P511Dev = conn.rs.getString("P511Dev");
                if (P511Dev == null) {
                    P511Dev = "";
                }
//
//                P53DF = conn.rs.getString("P53DF");
//                if (P53DF == null) {
//                    P53DF = "";
//                }
//
//                P53DO = conn.rs.getString("P53DO");
//                if (P53DO == null) {
//                    P53DO = "";
//                }
//
//                P53DM = conn.rs.getString("P53DM");
//                if (P53DM == null) {
//                    P53DM = "";
//                }
//
//                P53D = conn.rs.getString("P53D");
//                if (P53D == null) {
//                    P53D = "";
//                }
//
                P54D = conn.rs.getString("P54D");
                if (P54D == null) {
                    P54D = "";
                }

        
       
    
   
    
    
if(1==1){ 
    
 
    
    
  if(1==1){
      
      int celpos=0;
      int celpos1=1;
      
      
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);

     //County
 HSSFCell clx0= rwx.createCell(celpos);
 clx0.setCellValue(conn.rs.getString(celpos1));
 clx0.setCellStyle(style2);
 celpos++;
 celpos1++;
 
  //SubCounty   
  HSSFCell clx= rwx.createCell(celpos);
  clx.setCellValue(conn.rs.getString(celpos1).substring(0,1).toUpperCase()+conn.rs.getString(celpos1).substring(1).toLowerCase());
  clx.setCellStyle(style2);
  celpos++;
 celpos1++;
  //Facility Name   
  if(1==1){
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getString(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
          }
  
  //Mfl Code
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getString(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
 
  //DSD/TA
  
  //im assuming that VMMC is made of DSDs as indicated in the Datim PDF screen shared by Supervisor
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue("DSD");
  clx1.setCellStyle(style2);
  celpos++;

  } 
  
  
  //Numerator
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  
   //<1
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
   //1-9
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  
   //10-24
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //15-19
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
   //20-24
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //25-29
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
   
  
   //30-49
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
        }
  
  
   //50+
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //tested positive clients  P511KP
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  
  //tested negative clients P511KN
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  //unknown untested  P511KU
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  
  //=============================================================
  //device based  P511KU
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  //=============================================================
  //Returned
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  if(1==1){
      
      int notreturned=0;
      
      if(conn.rs.getInt("P51DT")-conn.rs.getInt("P54D")>0){
      //here im subtracting surgical vmmc and males who returned for postoperative follow
      notreturned=conn.rs.getInt("P51DT")-conn.rs.getInt("P54D");
      }
      
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(notreturned);
  clx1.setCellStyle(style2);
  //increment to get past P511Surg
  celpos++;
 celpos1++;
         }
  
  
  //=============================================================
  //VMMC_AE Numerator
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  //intra-operative moderate
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
    //intra-operative severe
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
    
    
      // surgicalpost-operative moderate
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
  celpos1++;
         }
    
     // surgicalpost-operative severe
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
  celpos1++;
         }
    
     //Medical device related Moderate
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue("");
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
       //Medical device related Severe
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue("");
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
    
    
  r++;
  }
  
  
  //=====================================================================================
  
 
 
    

    
    
    }
    
    }
     
      //System.out.println(createdtable);
      
   
            
            
            
            
            
                    }
            
            
 //==================================================================================================
 //HTC RESULTS BY SDP           
 //==================================================================================================
            
            
            
            
            
            
            
            
            
            
            
            
            if(1==1){
            
            
            
            
            
            String month = "";
            String year = "";
            String facil = "361";
            String form = "moh711";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            
            
            String subheaders[]={"Tested","Positive","Negative"};
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","Tuberculosis","","","Outpatient Department","","","Inpatient","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","",""};
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            
            String reportType = "";
            if (request.getParameter("reportType") != null) {
                reportType = request.getParameter("reportType");
            }
            String reportDuration = "";
            if (request.getParameter("reportDuration") != null) {
                reportDuration = request.getParameter("reportDuration");
            }
            
            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
            }
            
            if (request.getParameter("facility") != null && reportType.equals("2")) {
                try {
                    facil = request.getParameter("facility");
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='" + facil + "'";
                    conn.rs = conn.st.executeQuery(getfacil);
                    
                    while (conn.rs.next()) {
                        
                        header += " FACILITY : " + conn.rs.getString(1).toUpperCase() + "     MFL CODE  :  " + conn.rs.getString(2) + "  ";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if (request.getParameter("county") != null && reportType.equals("2")) {
                try {
                    county = request.getParameter("county");
                    
                    String getcounty = "select County from county where CountyID='" + county + "'";
                    conn.rs = conn.st.executeQuery(getcounty);
                    
                    while (conn.rs.next()) {
                        
                        header += " COUNTY : " + conn.rs.getString(1).toUpperCase() + " ";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            if (request.getParameter("month") != null && reportDuration.equals("4")) {
                try {
                    month = request.getParameter("month");
                    
                    String getmonth = "select name as monthname from month where id='" + month + "'";
                    conn.rs = conn.st.executeQuery(getmonth);
                    
                    while (conn.rs.next()) {
                        
                        header += " MONTH : " + conn.rs.getString(1).toUpperCase() + "";
                        
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            
            header += " YEAR : " + year + "";
            
            String facilitywhere = "";
            String yearwhere = "";
            String monthwhere = "";
            String countywhere = "";
            String duration = "";
            String semi_annual = "";
            String quarter = "";
            String tbstatduration="";
            //==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            int yearcopy = Integer.parseInt(year);
            
//        reportType="2";
//        year=2015;
//        reportDuration="3";
            String yearmonth = "" + year;
            int prevYear = yearcopy - 1;
            int maxYearMonth = 0;
            int monthcopy = 0;
//        GET REPORT DURATION============================================
            //annually
            if (reportDuration.equals("1")) {
                yearmonth = "Annual Report For " + year;
                duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "09";
                tbstatduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     tbstatduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     tbstatduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     tbstatduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
                    String getMonths = "SELECT months,name FROM quarter WHERE id='" + quarter + "'";
                    conn.rs = conn.st.executeQuery(getMonths);
                    if (conn.rs.next() == true) {
                        
                        try {
                            String months[] = conn.rs.getString(1).split(",");
                            startMonth = months[0];
                            endMonth = months[2];
                            if (quarter.equals("1")) {
                                duration = " " + form + ".yearmonth BETWEEN " + prevYear + "" + startMonth + " AND " + prevYear + "" + endMonth;
                                yearmonth = "Quarterly Report For " + prevYear + " " + conn.rs.getString(2);
                            } else {
                                yearmonth = "Quarterly Report For " + year + " (" + conn.rs.getString(2) + ")";
                                duration = " " + form + ".yearmonth BETWEEN " + year + "" + startMonth + " AND " + year + "" + endMonth;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (reportDuration.equals("4")) {
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                tbstatduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = " " + form + ".yearmonth=" + prevYear + "" + month;
                } else {
                    duration = " " + form + ".yearmonth=" + year + "0" + month;
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            } else {
                duration = "";
            }
            
            //======================================================================
//==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            String subcountywhere = "";
            
            String subcounty = "";
            
            if (!request.getParameter("subcounty").equals("")) {
                
                subcounty = request.getParameter("subcounty");
                
            }
            
            String getexistingdata = "";
            
            if (!county.equals("")) {
                
                countywhere = " and district.countyid = '" + county + "'";
                
            }
            
            if (!subcounty.equals("")) {
                
                subcountywhere = " and subpartnera.DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")&& reportType.equalsIgnoreCase("2")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + duration + " " + countywhere + " " + subcountywhere;
            
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
           //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID   union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
           getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID   ";
            System.out.println("@@"+getexistingdata);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
            
          String distincttbstatfacils="select distinct(SubPartnerID) as partnerid from  tb_stat_art WHERE "+tbstatduration;  
            
      ArrayList tbstat= new ArrayList();  
      
     conn.rs1=conn.st1.executeQuery(distincttbstatfacils);
     
     
     while(conn.rs1.next())
     {
     tbstat.add(conn.rs1.getString(1));     
     }
     
     
//=====================================================================================================
//=====================================================================================================    
//______________________________________________________________________________________
            //                       NOW CREATE THE WORKSHEETS
            //______________________________________________________________________________________
           // HSSFWorkbook wb = new HSSFWorkbook();
            
            //______________________________________________________________________________________
            //______________________________________________________________________________________
            HSSFFont font = wb.createFont();
            font.setFontHeightInPoints((short) 12);
            font.setFontName("Cambria");
            font.setColor((short) 0000);
            CellStyle style = wb.createCellStyle();
            style.setFont(font);
            style.setBorderTop(HSSFCellStyle.BORDER_THIN);
            style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            style.setBorderRight(HSSFCellStyle.BORDER_THIN);
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
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
             fontx.setBoldweight(Font.BOLDWEIGHT_BOLD);
            stylex.setFont(fontx);
            stylex.setWrapText(true);
            
            HSSFSheet shet = wb.createSheet("HTC RESULTS BY SDP");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("HTC Results BY Service Delivery Point and Test Result(Required) " + yearmonth);
            cl0.setCellStyle(style);
            rowpos++;
            for (int a = 1; a < sectionheaders.length; a++) {
                HSSFCell clx = rw.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(style);
                shet.setColumnWidth(a, 5000);
            }
            
            
            
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);
            rowpos++;
            
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
              
                if(a>4&&a<sectionheaders.length){
                    if(sectionheaders[a].equals("")){}
                    else {
                        shet.addMergedRegion(new CellRangeAddress(1,1,a,a+2));
                    }
                }
            }
            
            
            shet.addMergedRegion(new CellRangeAddress(0,0,0,sectionheaders.length-1));
            
            shet.setColumnWidth(0, 5000);
            
            //add section 2
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(25);
            
            for (int a = 0; a <5; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(stylex);
                shet.addMergedRegion(new CellRangeAddress(rowpos-1,rowpos,a,a));
                                       }
            
            int b=0;
            for (int a = 5; a <sectionheaders.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(subheaders[b]);
                b++;
                if(b==3){b=0;}
                clx.setCellStyle(stylex);
                
                                                            }
            rowpos++;
          
            conn.rs= conn.st.executeQuery(getexistingdata);
            while(conn.rs.next()){
                int colpos=0; 
           int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
               //county
               if(1==1){
                       
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                 
                 //facility name
                   if(1==1){
                      
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="NA";
 if(conn.rs.getString("HTC_Support1")==null||conn.rs.getString("HTC_Support1").equals("")){
    if(conn.rs.getString("PMTCT_Support")!=null&&!conn.rs.getString("PMTCT_Support").equals("null")){              
 support=conn.rs.getString("PMTCT_Support");
    }         
                  }
 else {
    
  support=conn.rs.getString("HTC_Support1");
 }
   System.out.println("______:"+conn.rs.getString("HTC_Support1")+":__"+support);                
                  
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(support);
                clx.setCellStyle(style2);
               
            colpos++;
            //skip both pmtct support s
            conpos++;
            conpos++;
                           
               } 
                
 
                    
   //____________________Totals begin here______________                 
                    
                   //Antenatal
                   if(1==1){
//sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0206");
                tested=conn.rs.getInt("HV0201");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                 
             
                    //Labour & Delivery
                   if(1==1){
//,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0207");
                tested=conn.rs.getInt("HV0202");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                
              
                     //Under 5 Clinic
                   if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0232");
                tested=conn.rs.getInt("HV0228");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                
              
                   
                   
                     //Post Natal
                   if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0208");
                tested=conn.rs.getInt("HV0203");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
         
                   
                   
                   
//-------------------------------------------------------------------------------------------------------------------
                   
      //TB Stat
      if(1==1){
          int tested=0;
                int positive=0;
                int negative=0;
          String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'";
           conn.rs1=conn.st1.executeQuery(getstat);
           
           if(conn.rs1.next()){
           
              positive=conn.rs1.getInt("positive");
              negative=conn.rs1.getInt("negative");
              tested=negative+positive;
              //remove the current facility id
           if(tbstat.contains(conn.rs.getString("SubPartnerID"))){
           tbstat.remove(conn.rs.getString("SubPartnerID"));
           }    
           
           
                              }
          
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                
                
             
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue("");
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue("");
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue("");
                     }
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                     
                   
                   
//-------------------------------------------------------------------------------------------------------------------                   
 //sexually transmitted insfections    
      //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
       if(1==2){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                //positive=conn.rs.getInt("HV0208");
                //tested=conn.rs.getInt("HV0203");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
      //Outpatient
              if(1==1){
// sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("DTCC_HIV_Out_Tot");
                tested=conn.rs.getInt("DTCB_Test_Out_Tot");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }             
            
              
              //Inpatient
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("DTCC_HIV_In_Tot");
                tested=conn.rs.getInt("DTCB_Test_In_Tot");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }             
            
              //HIV Care and Treatment
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("DTCC_HIV_In_Tot");
                //tested=conn.rs.getInt("DTCB_Test_In_Tot");
               // negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
               
              
              
              
              
              //VMMC
              if(1==1){
//     sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("P511KP");
                negative=conn.rs.getInt("P511KN");
                tested=negative+positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
              
             
              
                 //VCT  (Co-located)
              if(1==1){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("VCTClient_HIV_TOT");
                tested=conn.rs.getInt("VCTClient_Tested_TOT");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }        
               
             
           
              //Voluntary counselling and testing (Stand alone)
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              //Mobile 
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
                //Home Based  
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              
              
              
              //Other
              
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("");
                //tested=conn.rs.getInt("");
                //negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }  
              
              
               rowpos++;    
            }
            
            
            
          //now check if any facilities were skipped 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            
            System.out.println("_____======______"+tbstat.size());
            
            
            for(int a=0;a<tbstat.size();a++)
            {
        
             System.out.println("%%%%%======______RoWno::"+tbstat.get(a));    
                
                
                if(1==1){
                
               int colpos=0; 
               int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25); 
          
           String getstat=    "select  county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype, sum(positive) as positive ,sum(negative) as negative, tb_stat_art.SubPartnerID as SubPartnerID from tb_stat_art  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on tb_stat_art.SubPartnerID = subpartnera.SubPartnerID  WHERE "+tbstatduration+" and  tb_stat_art.SubPartnerID='"+tbstat.get(a)+"'";
           conn.rs=conn.st.executeQuery(getstat);
           
           if(conn.rs.next()){      
               
            //county
               if(1==1){
                       
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                 
                 //facility name
                   if(1==1){
                      
                HSSFCell clx = rwx.createCell(colpos);
               clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="NA";

    
  support=conn.rs.getString("supporttype");
 
             
                  
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(support);
                clx.setCellStyle(style2);
               
            colpos++;
            //skip both pmtct support s
            conpos++;
          
                           
               } 
                
    //enter blanks in columns from Facility type  up to the tb column
                    
                      if(1==1){
                 for(int c=5;c<17;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue("");
                clx.setCellStyle(style2);               
            colpos++;
            
                 }     
               } 
               
               
               
               
               
               
         //tb stat     
               
               
               if(1==1){
               
                int tested=0;
                int positive=0;
                int negative=0;
          // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+tbstat.get(a)+"'";
          
         
           
              positive=conn.rs.getInt("positive");
              negative=conn.rs.getInt("negative");
              tested=negative+positive;
               
           
           
                              //}
          
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                
                
             
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue("");
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue("");
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue("");
                     }
                clx2.setCellStyle(style2);               
                colpos++; 
                
                
                }//end of tbstat if query
               
         
               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=20;c<sectionheaders.length;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue("");
                clx.setCellStyle(style2);               
            colpos++;
           // conpos++;
                 }     
               } 
               
               
               
               
               
               
               
           }//end of if query
               
               
               
               
                }//end of if 1==1
                
                
                
                
                System.out.println("____"+tbstat.get(a));    
            
                rowpos++;
                
            }//end of for loop
            
            }
            //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            
            
            
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//HTC CODE FROM PMTCT and HTC #PMTCTHTC
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(2==2){
                     
              int less15m=0;
              int less15f=0;
              int gret15m=0;
              int gret15f=0;
              
              
                     ArrayList allFacilities = new ArrayList();
                         allFacilities.clear();
                 int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;

   year=month=prevYear=maxYearMonth=0;
 reportDuration=duration=semi_annual=quarter="";
 facilityName=mflcode=countyName=districtName=facilityIds=facilityId="";

   String facilityIds1="";
        facilityIds1="(";
           if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
 
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
     
      facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
    
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
                         }
   
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";     
     }
       
        else{
  
       facilityIds1="";    
        }   
        
     }      
 int TestedAdultMale=0,TestedAdultFemale=0;
 int TestedChildMale=0,TestedChildFemale=0;
 int HIV_AdultMale=0,HIV_AdultFemale=0;
 int HIV_ChildMale=0,HIV_ChildFemale=0;
 
 
 int pmtcttested=0;
 int pmtctpositive=0;
 int pmtctnegative=0;
double FemaleAdultTested;
double FemaleTestedChild;
double AdultFemaleHIV;
double ChildFemaleHIV;
 
 double  MaleAdultTested;
 double MaleTestedChild;
 double  AdultMaleHIV;
 double ChildMaleHIV;
 
 
  
  
  double FemaleAdultTested1=0;
double FemaleAdultTested4=0;
double FemaleAdultTested9=0;
double FemaleAdultTested14=0;
double FemaleAdultTested19=0;
double FemaleAdultTested24=0;
double FemaleAdultTested49=0;
double FemaleAdultTested50=0;

double FemaleTestedChild1=0;
double FemaleTestedChild4=0;
double FemaleTestedChild9=0;
double FemaleTestedChild14=0;
double FemaleTestedChild19=0;
double FemaleTestedChild24=0;
double FemaleTestedChild49=0;
double FemaleTestedChild50=0;

double AdultFemaleHIV19Neg=0;
double AdultFemaleHIV24Neg=0;
double AdultFemaleHIV49Neg=0;
double AdultFemaleHIV50Neg=0;

double AdultFemaleHIV19=0;
double AdultFemaleHIV24=0;
double AdultFemaleHIV49=0;
double AdultFemaleHIV50=0;

double ChildFemaleHIV1=0;
double ChildFemaleHIV4=0;
double ChildFemaleHIV9=0;
double ChildFemaleHIV14=0;

double ChildFemaleHIV1Neg=0;
double ChildFemaleHIV4Neg=0;
double ChildFemaleHIV9Neg=0;
double ChildFemaleHIV14Neg=0;
 
 
// MALES
  double MaleAdultTested19Neg=0;
  double MaleAdultTested21Neg=0;
  double MaleAdultTested49Neg=0;
  double MaleAdultTested50Neg=0;
  
  double MaleAdultTested19=0;
 double  MaleAdultTested24=0;
 double MaleAdultTested49=0;
 double MaleAdultTested50=0;
  
  
  double MaleTestedChild1=0;
  double MaleTestedChild4=0;
  double MaleTestedChild9=0;
  double MaleTestedChild14=0;
  
  double MaleTestedChild1Neg=0;
  double MaleTestedChild4Neg=0;
  double MaleTestedChild9Neg=0;
  double MaleTestedChild14Neg=0;
  
  double AdultMaleHIV19Neg=0;
  double AdultMaleHIV24Neg=0;
  double AdultMaleHIV49Neg=0;
  double AdultMaleHIV50Neg=0;
  
  double AdultMaleHIV19=0;
 double  AdultMaleHIV24=0;
  double AdultMaleHIV49=0;
  double AdultMaleHIV50=0;
  
  
 double ChildMaleHIV1=0;
 double ChildMaleHIV4=0;
double  ChildMaleHIV9=0;
 double ChildMaleHIV14=0;
 
 double ChildMaleHIV1Neg=0;
 double ChildMaleHIV4Neg=0;
 double ChildMaleHIV9Neg=0;
  double ChildMaleHIV14Neg=0;
  
  double splitData=0; int adderPos=0;
           double childSplitData=0;  
           
           int redalert=0;
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
//        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration1=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period1="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration1=" moh711.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : APRIL "+year+" to SEPT "+year; 
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
      duration1=" moh711.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period1="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration1=" moh711.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
     period1="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
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
     duration1=" moh711.yearmonth="+prevYear+""+month;    
     period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration1=" moh711.yearmonth="+year+"0"+month;  
    period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration1="";     
      }
        
      HSSFSheet shet3=wb.createSheet("PMTCT , HTC & VMMC ");   
            HSSFCell  c11;
         FemaleAdultTested=0;
 FemaleTestedChild=0;
 AdultFemaleHIV=0;
 ChildFemaleHIV=0;
          double TotalTested=0;
            double    TotalPositiveFemale=0;
             double   TotalPositiveMale=0;
             double   TotalNegativeFemale=0;
             double   TotalNegativeMale=0;
 
// MALES
   MaleAdultTested=0;
  MaleTestedChild=0;
   AdultMaleHIV=0;
  ChildMaleHIV=0;
  double TotalPositive=0;
  double TotalNegative=0;
  
   String county="";
   String  district="";
    String facilityname="";
     
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
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
stylemainHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylemainHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
stylemainHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
stylemainHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
stylemainHeader.setWrapText(true);

    HSSFCellStyle styleHeader = wb.createCellStyle();
    styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
    styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);  
    
    
       
   HSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    stborder.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
    stborder.setWrapText(true);

    
      HSSFFont font1 = wb.createFont();
            font1.setFontName("Cambria");
            font1.setColor((short) 0000);
           stborder.setFont(font1);
            
    
    
    
    // for the red color
   HSSFCellStyle redstyle = wb.createCellStyle();
    redstyle.setFillForegroundColor(HSSFColor.RED.index);
    redstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    redstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    redstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    redstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    redstyle.setWrapText(true);
    

  shet3.setColumnWidth(0, 4000);  
  shet3.setColumnWidth(1, 5000);  
  shet3.setColumnWidth(2,7000);  
  shet3.setColumnWidth(6,5000);  
//    for (int i=1;i<=42;i++){
//   shet3.setColumnWidth(i, 2000);     
//    }
 HSSFRow rw0=shet3.createRow(0);
 rw0.setHeightInPoints(30);
 HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
         
         
      c1.setCellValue(period1);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=51;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      shet3.addMergedRegion(new CellRangeAddress(0,0,0,17));
     HSSFCell  c12,c13,c14,c15,c16,c17,c18,c19,c20,c110,c111,c112,c113,c114,c115,c116,c117,c118,c219;
      HSSFCell    c119,c120,c121,c122,c123,c124,c125,c126,c127,c128,c129,c130,c131,c132,c133,c134,c135,c136,c137;  
   HSSFCell c211,c212,c213,c214,c215,c216,c217;
        rw0=shet3.createRow(2); 
        rw0.setHeightInPoints(30);
 
      
       
        
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
        
        
         c211.setCellValue("COUNTY");
         c212.setCellValue("SUB-COUNTY");
         c213.setCellValue("FACILITY");
         c214.setCellValue("MFL-CODE");
         c215.setCellValue("TYPE OF SUPPORT");
        
         c11=rw0.createCell(7);
         
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
      
         c119=rw0.createCell(25);
         c120=rw0.createCell(26);
         c121=rw0.createCell(27);
         c122=rw0.createCell(28);
         c123=rw0.createCell(29);
         c124=rw0.createCell(30);
         c125=rw0.createCell(31);
         c126=rw0.createCell(32);
         c127=rw0.createCell(33);
         c128=rw0.createCell(34);
         c129=rw0.createCell(35);
         c130=rw0.createCell(36);
         c131=rw0.createCell(37);
         c132=rw0.createCell(38);
         c133=rw0.createCell(39);
         c134=rw0.createCell(40);
         c135=rw0.createCell(41);
         c136=rw0.createCell(42);
         c137=rw0.createCell(43);
         
        c216.setCellValue("Total HIV+");
             
        c217.setCellValue("Total HIV+ (F)");
      c11.setCellValue("");
  
      c12.setCellValue("FEMALE(POSITIVE)");
              c216.setCellStyle(stylemainHeader);
              c217.setCellStyle(stylemainHeader);
              c11.setCellStyle(stylemainHeader);
              c12.setCellStyle(stylemainHeader);
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("MALE (POSITIVE)");
      c111.setCellStyle(stylemainHeader);
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
    
      
//      
//       rw0=shet3.createRow(2);
//        rw0.setHeightInPoints(20);
        
     
   c119.setCellValue("NEGATIVE");
      c120.setCellValue("FEMALE (NEGATIVE)");
      c119.setCellStyle(stylemainHeader);
      c120.setCellStyle(stylemainHeader);
      c121.setCellValue("");
      c122.setCellValue("");
      c123.setCellValue("");
      c124.setCellValue("");
      c125.setCellValue("");
      c126.setCellValue("");
      c127.setCellValue("");
      
      c128.setCellValue("");
      
      c129.setCellValue("MALE (NEGATIVE)");
      c129.setCellStyle(stylemainHeader);
      c130.setCellValue("");
      c131.setCellValue("");
      c132.setCellValue("");
      c133.setCellValue("");
      c134.setCellValue("");
      c135.setCellValue("");
      c136.setCellValue("");
      c137.setCellValue("");
     for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
     //add a blank header
      for(int i=43; i<=51;i++){
          c11=rw0.createCell(i);
          c11.setCellStyle(stylemainHeader);
      }
      
     
     
    for(int i=23; i<=43;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
      
   
     shet3.addMergedRegion(new CellRangeAddress(2,2,8,15));
     shet3.addMergedRegion(new CellRangeAddress(2,2,17,24));
     
     shet3.addMergedRegion(new CellRangeAddress(2,2,26,33));
     shet3.addMergedRegion(new CellRangeAddress(2,2,35,42));
     shet3.addMergedRegion(new CellRangeAddress(2,2,43,51));
     
      
   rw0=shet3.createRow(3);
   rw0.setHeightInPoints(30);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
         
         // other data
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
        
         
      c11.setCellValue("Num");
      c216.setCellValue("TOTAL HIV+");
      c217.setCellValue("TOTAL +VE (F)");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("TOTAL +VE MALE");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
      
      
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
        
         
      c11.setCellValue("TOTAL -VE(F)");
      c12.setCellValue("Paeds <15Yr");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("Adults 15+Yr");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("TOTAL -VE(M)");
      
      c111.setCellValue("Paeds <15Yr");
      c112.setCellValue("");
      c113.setCellValue("");
      c114.setCellValue("");
      c115.setCellValue("Adults 15+Yr");
      c116.setCellValue("");
      c117.setCellValue("");
      c118.setCellValue("");
      //201510
      
         c119=rw0.createCell(43);
         c119.setCellStyle(stylemainHeader);
         c120=rw0.createCell(44);
         c120.setCellStyle(stylemainHeader);
         c121=rw0.createCell(45);
         c121.setCellStyle(stylemainHeader);
         c122=rw0.createCell(46);
         c122.setCellStyle(stylemainHeader);
         c123=rw0.createCell(47);
         c123.setCellStyle(stylemainHeader);
         c124=rw0.createCell(48);
         c124.setCellStyle(stylemainHeader);
         c125=rw0.createCell(49);
         c125.setCellStyle(stylemainHeader);
         c126=rw0.createCell(50);
         c126.setCellStyle(stylemainHeader);
         c127=rw0.createCell(51);
         c127.setCellStyle(stylemainHeader);
         c128=rw0.createCell(52);
       
      c119.setCellValue("Female");
      c120.setCellValue("");
      c121.setCellValue("Male");
      c122.setCellValue("");
      c123.setCellValue("Sub-Total");
      c124.setCellValue("Positive");
      c125.setCellValue("Negative");
      c126.setCellValue("Sub-total");
      c127.setCellValue("Verification Status");
      
       for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
      
      shet3.addMergedRegion(new CellRangeAddress(3,3,8,11));
     shet3.addMergedRegion(new CellRangeAddress(3,3,12,15));
     shet3.addMergedRegion(new CellRangeAddress(3,3,17,20));
     shet3.addMergedRegion(new CellRangeAddress(3,3,21,24)); 
//     
      for(int k=23; k<=42;k++){
          c113=rw0.getCell(k);
          c113.setCellStyle(styleHeader);
      }
     
     shet3.addMergedRegion(new CellRangeAddress(3,3,26,29));
     shet3.addMergedRegion(new CellRangeAddress(3,3,30,33));
     shet3.addMergedRegion(new CellRangeAddress(3,3,35,38));
     shet3.addMergedRegion(new CellRangeAddress(3,3,39,42)); 
     shet3.addMergedRegion(new CellRangeAddress(3,3,43,44)); 
     shet3.addMergedRegion(new CellRangeAddress(3,3,45,46)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,47,47)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,48,48)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,49,49)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,50,50)); 
     shet3.addMergedRegion(new CellRangeAddress(3,4,51,51)); 
     
      rw0=shet3.createRow(4);
      rw0.setHeightInPoints(30);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
         
         
         // for ther est
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c110=rw0.createCell(16);
         c111=rw0.createCell(17);
         c112=rw0.createCell(18);
         c113=rw0.createCell(19);
         c114=rw0.createCell(20);
         c115=rw0.createCell(21);
         c116=rw0.createCell(22);
         c117=rw0.createCell(23);
         c118=rw0.createCell(24);
         
         
      c11.setCellValue("NUM");
      c216.setCellValue("TOTAL HIV+");
      c217.setCellValue("TOTAL +VE(F)");
      c12.setCellValue("<1 ");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("TOTAL +VE MALE");
      shet3.addMergedRegion(new CellRangeAddress(3,4,16,16));
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
      
      
         for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
//     
      
      // for negative
      

  
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
         c119=rw0.createCell(43);
         
         
      c11.setCellValue("TOTAL -VE(F)");
       shet3.addMergedRegion(new CellRangeAddress(3,4,25,25));
      c12.setCellValue("<1");
      c13.setCellValue("1-4Y");
      c14.setCellValue("5-9Y");
      c15.setCellValue("10-14Y");
      c16.setCellValue("15-19Y");
      c17.setCellValue("20-24Y");
      c18.setCellValue("25-49Y");
      c19.setCellValue("50+Y");
      
      c110.setCellValue("TOTAL -VE(M)");
        shet3.addMergedRegion(new CellRangeAddress(3,4,34,34));
      c111.setCellValue("<1");
      c112.setCellValue("1-4Y");
      c113.setCellValue("5-9Y");
      c114.setCellValue("10-14Y");
      c115.setCellValue("15-19Y");
      c116.setCellValue("20-24Y");
      c117.setCellValue("25-49Y");
      c118.setCellValue("50+Y");
     
      
  //20151009
      
       
         c119=rw0.createCell(43);
         c119.setCellStyle(stylemainHeader);
         c120=rw0.createCell(44);
         c120.setCellStyle(stylemainHeader);
         c121=rw0.createCell(45);
         c121.setCellStyle(stylemainHeader);
         c122=rw0.createCell(46);
         c122.setCellStyle(stylemainHeader);
         c123=rw0.createCell(47);
         c123.setCellStyle(stylemainHeader);
         c124=rw0.createCell(48);
         c124.setCellStyle(stylemainHeader);
         c125=rw0.createCell(49);
         c125.setCellStyle(stylemainHeader);
         c126=rw0.createCell(50);
         c126.setCellStyle(stylemainHeader);
         c127=rw0.createCell(51);
         c127.setCellStyle(stylemainHeader);
         c128=rw0.createCell(52);
       
      
      
      c119.setCellValue("< 15");
      c120.setCellValue("15 +");
      c121.setCellValue("< 15");
      c122.setCellValue("15 + ");
      c123.setCellValue("Sub-total ");
      
       c124.setCellValue("Positive");
       c125.setCellValue("Negative");
       c126.setCellValue("Sub-total");
       c127.setCellValue("Verification Status");
       
  for(int l=20; l<=43;l++){
          c113=rw0.getCell(l);
          c113.setCellStyle(styleHeader);
      }
       
    
    
    
    
    
    
    
    
    
    double checkdiff=0;
    int count=4;
    TestedAdultMale=0;TestedAdultFemale=0;
 TestedChildMale=0;TestedChildFemale=0;
 HIV_AdultMale=0;HIV_AdultFemale=0;
 HIV_ChildMale=0;HIV_ChildFemale=0;
    
    //---------------------------------------------------------------------------
    
    
    String facilid="";
    String facilname="";
    String dsdta="";
     //20151010
    
    int pmtcttestedtotal=0;
    int pmtctpositivestotal=0;
    int pmtctnegativetotal=0;
    
    double pmtct15to19tes=0;
    double pmtct20to24tes=0;
    double pmtct25to49tes=0;
    double pmtct50tes=0;
    
   
    
    double pmtct15to19pos=0;
    double pmtct20to24pos=0;
    double pmtct25to49pos=0;
    double pmtct50pos=0;
   
    
    
    
    double pmtct15to19neg=0;
    double pmtct20to24neg=0;
    double pmtct25to49neg=0;
    double pmtct50neg=0;
    
    
    String get711data="SELECT(sum(VCTClient_Tested_CF) +sum( VCTClient_Tested_AF)+sum(DTCB_Test_Out_AF)+sum(DTCB_Test_In_AF))" //ADULTS TESTED FEMALE  
            + ",(sum(VCTClient_Tested_CM)+ sum(VCTClient_Tested_AM) +  sum(DTCB_Test_Out_AM) + sum(DTCB_Test_In_AM))"//ADULTS TESTED MALES
            + ", (sum(VCTClient_HIV_CF)+ sum(VCTClient_HIV_AF)+sum(DTCC_HIV_In_AF)+ sum(DTCC_HIV_Out_AF))" // ADULTS HIV+ FEMALE
            + ",(sum(VCTClient_HIV_CM)+sum(VCTClient_HIV_AM)+ sum(DTCC_HIV_In_AM) +sum(DTCC_HIV_Out_AM)) " // ADULTS HIV+ MALE
            + ", (sum(DTCB_Test_Out_CF) + sum(DTCB_Test_In_CF))" // CHILDREN TOTAL TESTED FEMALE
            + ", (sum(DTCB_Test_Out_CM) + sum(DTCB_Test_In_CM))" // CHILDREN TOTAL TESTED MALE
            + ", ( sum(DTCC_HIV_In_CF)+ sum(DTCC_HIV_Out_CF))" // CHILDREN OSITIVE FEMALE
            + ", (sum(DTCC_HIV_In_CM)+ sum(DTCC_HIV_Out_CM)), "
            
            + "county.County,district.DistrictNom," //
            + " subpartnera.SubPartnerNom,subpartnera.CentreSanteId,subpartnera.HTC_Support1, "// CHILDREN POSITIVE MALE
            
            //======================added later 20151010
            + " sum(HV0204) as PMTCTTESTED , (sum(HV0206)+sum(HV0207)+sum(HV0208) ) as PMTCTPOS "//pmtct tested and positive added on 201510
            +",P51D1 asVMMCunder1,P51D9 as VMMC1to9,P51D10 as VMMC10to14,P51D19 as VMMC15to19,P51D24 as VMMC20to24,P51D29 as VMMC25to29,P51D49 as VMMC30to49,P51D50 as VMMC50,P51DT as VMMCT "//vmmc added 20151016
            + ",HV0232,HV0228 "//under five
            + "FROM moh711 left join moh731 on moh711.ID=moh731.id left join vmmc on moh711.ID=vmmc.tableid   JOIN subpartnera "
            + "ON moh711.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && subpartnera.HTC=1  "
            + "GROUP BY moh711.SubPartnerID " ;
     System.out.println("711 : "+get711data);
    conn.rs=conn.st.executeQuery(get711data);
    while(conn.rs.next()){
     
        //=============================================now add vmmc and add under 1
        
     county=conn.rs.getString(9);
     district=conn.rs.getString(10);
     district=district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase();
     facilityname=conn.rs.getString(11);
     mflcode=conn.rs.getString(12);   
     dsdta=conn.rs.getString(13);   
     
     pmtcttestedtotal=conn.rs.getInt("PMTCTTESTED");     
     pmtctpositivestotal=conn.rs.getInt("PMTCTPOS");     
     
        //System.out.println("(((((((("+conn.rs.getInt("PMTCTPOS"));
              //15-19	20-24   25-49
//PMTCT TESTED	13.9%	27.8%	58.3%
//PMTCT Posit	5.3%	17.5%	77.2%

     
     
     
     
    TestedAdultFemale=conn.rs.getInt(1);
    TestedAdultMale=conn.rs.getInt(2);
    HIV_AdultFemale=conn.rs.getInt(3);
    HIV_AdultMale=conn.rs.getInt(4);
    TestedChildFemale=conn.rs.getInt(5);
    TestedChildMale=conn.rs.getInt(6);
    HIV_ChildFemale=conn.rs.getInt(7);
    HIV_ChildMale=conn.rs.getInt(8);
    System.out.println(facilityname+" KKK "+HIV_AdultFemale+" "+HIV_AdultMale+" "+HIV_ChildFemale+"  "+HIV_ChildMale);
    System.out.println(facilityname+"TestedChildFemale "+TestedChildFemale+"  HIV_ChildFemale "+HIV_ChildFemale +"  TestedChildMale "+TestedChildMale+" HIV_ChildMale   "+HIV_ChildMale);
     
String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
String arrayDetails []=basicDetails.split("@");

  count++;
  rw0=shet3.createRow(count);
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++){
    
  HSSFCell  S3cell=rw0.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos : "+facilno+"     det : "+arrayDetails[j]);
     facilno++;  
   }
        
   
   
         
   System.out.println(facilityname  +"   TestedAdultFemale "+TestedAdultFemale+"TestedAdultMale  "+TestedAdultMale+" TestedChildFemale  "+TestedChildFemale+" TestedChildMale "+TestedChildMale +" HIV_AdultFemale  "+HIV_AdultFemale+" HIV_AdultMale "+HIV_AdultMale+" HIV_ChildFemale "+HIV_ChildFemale +" HIV_ChildMale "+HIV_ChildMale); 
 
      
//      FEMALES

FemaleAdultTested19=(float)Math.round((0.05*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((0.11*TestedAdultFemale));
FemaleAdultTested49=(float)Math.round((0.72*TestedAdultFemale));
FemaleAdultTested50=(float)Math.round((0.12*TestedAdultFemale));

FemaleTestedChild1=(float)Math.round((0.05*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((0.26*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((0.29*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((0.40*TestedChildFemale));


//postive
AdultFemaleHIV19=(float)Math.round((0.02*HIV_AdultFemale));
AdultFemaleHIV24=(float)Math.round((0.09*HIV_AdultFemale));
AdultFemaleHIV49=(float)Math.round((0.79*HIV_AdultFemale));
AdultFemaleHIV50=(float)Math.round((0.10*HIV_AdultFemale));



//positve
ChildFemaleHIV1=(float)Math.round((0.13*HIV_ChildFemale));
ChildFemaleHIV4=(float)Math.round((0.37*HIV_ChildFemale));
ChildFemaleHIV9=(float)Math.round((0.25*HIV_ChildFemale));
ChildFemaleHIV14=(float)Math.round((0.25*HIV_ChildFemale));





 
 
// MALES

  MaleAdultTested19=(float)Math.round((0.05*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((0.11*TestedAdultMale));
  MaleAdultTested49=(float)Math.round((0.72*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((0.12*TestedAdultMale));

  
  MaleTestedChild1=(float)Math.round((0.05*TestedChildMale));
  MaleTestedChild4=(float)Math.round((0.26*TestedChildMale));
  MaleTestedChild9=(float)Math.round((0.29*TestedChildMale));
  MaleTestedChild14=(float)Math.round((0.40*TestedChildMale));

//positive
  AdultMaleHIV19=(float)Math.round((0.02*HIV_AdultMale));
  AdultMaleHIV24=(float)Math.round((0.09*HIV_AdultMale));
  AdultMaleHIV49=(float)Math.round((0.79*HIV_AdultMale));
  AdultMaleHIV50=(float)Math.round((0.10*HIV_AdultMale));

  
  //positives
  ChildMaleHIV1=(float)Math.round((0.13*HIV_ChildMale));
  ChildMaleHIV4=(float)Math.round((0.37*HIV_ChildMale));
  ChildMaleHIV9=(float)Math.round((0.25*HIV_ChildMale));
  ChildMaleHIV14=(float)Math.round((0.25*HIV_ChildMale));
 
  
              //15-19	20-24   25-49
//PMTCT TESTED	13.9%	27.8%	58.3%
//PMTCT Posit	5.3%	17.5%	77.2%
//variables to hold the total of the distributed 
  double pmtcttestedverify=0;
  double pmtctpositiveverify=0;
  
  
  pmtct15to19tes=(float)Math.round((0.139*pmtcttestedtotal));
  pmtct20to24tes=(float)Math.round((0.278*pmtcttestedtotal));
  pmtct25to49tes=(float)Math.round((0.583*pmtcttestedtotal));
 
  
  pmtct15to19pos=(float)Math.round((0.053*pmtctpositivestotal));
  pmtct20to24pos=(float)Math.round((0.175*pmtctpositivestotal));
  pmtct25to49pos=(float)Math.round((0.772*pmtctpositivestotal));
 
  
  pmtcttestedverify=pmtct15to19tes+pmtct20to24tes+pmtct25to49tes;
  pmtctpositiveverify=pmtct15to19pos+pmtct20to24pos+pmtct25to49pos;
  
  
  //do normalization for the tested
  // if the two are not equal, do a distribution
  double currdiff=0;
  if(pmtcttestedverify<pmtcttestedtotal){
     currdiff=pmtcttestedtotal-pmtcttestedverify;
     if(currdiff>2){
     //raise an alarm
     //redalert++;
     }
  //add to the groupings with the larger percentage until equal
        //25-49
        while(currdiff>0){ 
 pmtct25to49tes+=1; 
 currdiff--;
}
  
  }
  else if(pmtcttestedverify>pmtcttestedtotal) {
  //minus  until equal
    currdiff=pmtcttestedverify-pmtcttestedtotal;
  //add to the groupings with the larger percentage until equal
        //25-49
        while(currdiff>0){ 
 pmtct25to49tes-=1; 
 currdiff--;
}
  
                                               }
  
  
  
  //do normalization for the POSITIVE
  // if the two are not equal, do a distribution
 
  if(pmtctpositiveverify<pmtctpositivestotal){
     currdiff=pmtctpositivestotal-pmtctpositiveverify;
     if(currdiff>2){
     //raise an alarm
     //redalert++;
     }
  //add to the groupings with the larger percentage until equal
        //25-49
        while(currdiff>0){ 
 pmtct25to49pos+=1; 
 currdiff--;
}
  
  }
  else if(pmtctpositiveverify>pmtctpositivestotal){
  //minus  until equal
    currdiff=pmtctpositiveverify-pmtctpositivestotal;
  //add to the groupings with the larger percentage until equal
        //25-49
        while(currdiff>0){ 
 pmtct25to49pos-=1; 
 currdiff--;
}
  
                                               }
  
  
  
  
 //now get the pmtct  negatives by subtracting from the   

  
   pmtct15to19neg=   pmtct15to19tes-pmtct15to19pos;
  
   pmtct20to24neg=   pmtct20to24tes-pmtct20to24pos;
   
   pmtct25to49neg=   pmtct25to49tes-pmtct25to49pos; 
  
   pmtctnegativetotal=pmtcttestedtotal-pmtctpositivestotal;
//    TestedAdultFemale=conn.rs.getInt(1);
//    TestedAdultMale=conn.rs.getInt(2);
//    HIV_AdultFemale=conn.rs.getInt(3);
//    HIV_AdultMale=conn.rs.getInt(4);
//    TestedChildFemale=conn.rs.getInt(5);
//    TestedChildMale=conn.rs.getInt(6);
//    HIV_ChildFemale=conn.rs.getInt(7);
//    HIV_ChildMale=conn.rs.getInt(8);
//    TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
              
      double totalpositivesmale=0;   
      double totalpositivesfemale=0;   
      double totalpositives=0;
      double totalnegatives=0; 
     double totalfemalehiv=0;
     double totalmalehiv=0;
     double totalfemaletesteddis=0;
     double totalmaletesteddis=0;
     double totalfemaletested=0;
     double totalmaletested=0;
     double negfem=0;
           double  negmale=0;
           int redalert1=0;
           int redalert2=0;
           int redalert3=0;
           int redalert4=0;
   totalpositives=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;    
   totalnegatives=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
   
               
     
//   total tested after distribution
   totalfemaletesteddis=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50;
   totalmaletesteddis=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
// totaltested after distriibution
   double totaltestedis=0;
   totaltestedis=totalfemaletesteddis+totalmaletesteddis;
TotalTested=TestedChildFemale+TestedChildMale+TestedAdultMale+TestedAdultFemale;
   
   
   totalfemaletested=TestedAdultFemale+TestedChildFemale;
   totalmaletested= TestedAdultMale+TestedChildMale;
 
   //poistives
   totalfemalehiv=HIV_AdultFemale+HIV_ChildFemale;
   totalmalehiv=HIV_AdultMale+HIV_ChildMale;
       //+ve after dist
   totalpositivesfemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
   totalpositivesmale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
// 
   
   // negative 
   negfem=totalfemaletested-totalfemalehiv;
   negmale=totalmaletested-totalmalehiv;
   double checkdiff1=0;
   double checkdiff2=0;
   double checkdiff3=0;
   int redfemalealert=0;
   int redmalealert=0;
   int finalalert=0;
   double totalcheckdiff=0;
   
   
   
   
   //check a difference
    checkdiff=totalfemalehiv-totalpositivesfemale;
//      System.out.println("checkdiff female  "+checkdiff1);
   // positive female
 if(checkdiff>2 ||checkdiff<-2){
 redalert=1;
 }
 // positive male
   checkdiff1=totalmalehiv-totalpositivesmale;
//    System.out.println("checkdiff male  "+checkdiff1);
 if(checkdiff1>2 ||checkdiff1<-2){
 redalert1=1;
 }

 totalcheckdiff=TotalTested-totaltestedis;
// System.out.println("dqa  "+totalcheckdiff);
 if(totalcheckdiff>5 || totalcheckdiff<-5){
 finalalert=1;
 }
 
   
   
   
   
   
//   checkdiff=totalfemalehiv-totalpositivesfemale;
//   // positive female
// if(checkdiff>=2 ||checkdiff<=-2){
// redalert=1;
// }
// // positive male
//   checkdiff1=totalmalehiv-totalpositivesmale;
// if(checkdiff1>=2 ||checkdiff1<=-2){
// redalert1=1;
// }
//// negative female
//   checkdiff2=negfem-totalfemaletesteddis;
// if(checkdiff2>=2 ||checkdiff2<=-2){
// redalert2=1;
// }
// 
// // negativemale
//   checkdiff3=negmale-totalmaletesteddis;
// if(checkdiff3>=2 ||checkdiff3<=-2){
// redalert3=1;
// }
// 
// totalcheckdiff=checkdiff+checkdiff1+checkdiff2+checkdiff3;
// if(totalcheckdiff>=5 || totalcheckdiff<=-5){
// finalalert=1;
// }
   adderPos=0;
   double Totalhivfemale=0;
   double Totalhivmale=0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   splitData=0;  adderPos=0;
            childSplitData=0;   
//   // adult female hiv+
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50 ;
//
System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
 adderPos=0;
 
 while(splitData<HIV_AdultFemale){ 
 AdultFemaleHIV49+=1; 
 splitData++;
 
 
}
 
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50 ;
 while(splitData>HIV_AdultFemale){ 
 AdultFemaleHIV49-=1; 
 splitData--;
}
 //tested female adults
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultFemale){ 
 FemaleAdultTested49+=1; 
 splitData++;
}
 
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultFemale){ 
 FemaleAdultTested49-=1; 
 splitData--;
}
   
 
 
// adult male hiv+
 
 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50 ;
  adderPos=0;
 while(splitData<HIV_AdultMale){ 
 AdultMaleHIV49+=1; 
 splitData++;
}
 
splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50 ;
 adderPos=0;
 while(splitData>HIV_AdultMale){ 
 AdultMaleHIV49-=1; 
 splitData--;
}
 
 //tested male adults
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultMale){ 
 MaleAdultTested49+=1; 
 splitData++;
}
 
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultMale){ 
 MaleAdultTested49-=1; 
 splitData--;
}
 
 
 
 
 
 
 
// for child female tested 
  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  
  System.out.println(facilityname+" "+childSplitData+" b4 jjj "+TestedChildFemale);
   adderPos=0;
while(childSplitData<TestedChildFemale){ 
if(adderPos==0){
  FemaleTestedChild14+=1;   
 }
if(adderPos==1){
 FemaleTestedChild9+=1;    
 }
if(adderPos==2){
 FemaleTestedChild4+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildFemale){ 
 if(adderPos==0){
  FemaleTestedChild14-=1;   
 }
if(adderPos==1){
 FemaleTestedChild9-=1;    
 }
if(adderPos==2){
 FemaleTestedChild4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}
System.out.println(facilityname+"     "+childSplitData+" after jjj "+TestedChildFemale);
// for child male hiv

 
 
 
 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  System.out.println(facilityname+"  mmmm  "+childSplitData+"    "+HIV_ChildFemale);
   adderPos=0;
   double diff=0;
while(childSplitData<HIV_ChildFemale){ 
  diff=FemaleTestedChild14-ChildFemaleHIV14;
 if(adderPos==0){
  if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
 
 }
  
 if(adderPos==1){

   if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
 }
 if(adderPos==2){
  
   if(FemaleTestedChild4-ChildFemaleHIV4>0){
   ChildFemaleHIV4+=1;   
  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){
  ChildFemaleHIV14+=1;   
 }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){
   ChildFemaleHIV9+=1;   
  }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){ 
  
  if(adderPos==0){
 
  ChildFemaleHIV14-=1;   
 

 
 }
  
 if(adderPos==1){

  
   ChildFemaleHIV9-=1;   
  
 }
 if(adderPos==2){
  
 
   ChildFemaleHIV4-=1;   
  
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}
   
// tested male _______________________________________________________________________
  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
while(childSplitData<TestedChildMale){ 
 if(adderPos==0){
  MaleTestedChild14+=1;   
 }
 else if(adderPos==1){
 MaleTestedChild9+=1;    
 }
 else if(adderPos==2){
 MaleTestedChild4+=1;    
 }
 
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}

  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildMale){ 
 if(adderPos==0){
  MaleTestedChild14-=1;   
 }
 else if(adderPos==1){
 MaleTestedChild9-=1;    
 }
 else if(adderPos==2){
 MaleTestedChild4-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}




// for child male +ve 
  childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
 
   adderPos=0;
while(childSplitData<HIV_ChildMale){ 
  if(adderPos==0){
   
     if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
     else if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 else if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  
 }
 else if(adderPos==1){
      if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 else if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  else if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
    
 }
 if(adderPos==2){
  
   if(MaleTestedChild4-ChildMaleHIV4>0){
   ChildMaleHIV4+=1;   
  }
  else if(MaleTestedChild14-ChildMaleHIV14>0){
  ChildMaleHIV14+=1;   
 }
  else if(MaleTestedChild9-ChildMaleHIV9>0){
   ChildMaleHIV9+=1;   
  }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}
 childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildMale){ 
 if(adderPos==0){
   
  
  ChildMaleHIV14-=1;   
 
  
  }
  

 else if(adderPos==1){
     
   ChildMaleHIV9-=1;   
  
    
 }
 if(adderPos==2){
  
  
   ChildMaleHIV4-=1;   
  
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){
     
     adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}





///


//  System.out.println("Neg nn  "+ChildMaleHIV1Neg+ " "+ChildMaleHIV4Neg+" "+ChildMaleHIV9Neg+" "+ ChildMaleHIV14Neg);
//  System.out.println("tested nn  "+MaleTestedChild1+ " "+MaleTestedChild4+" "+MaleTestedChild9+" "+ MaleTestedChild14);
//  System.out.println("hiv+ nnn  "+ChildMaleHIV1+ " "+ChildMaleHIV4+" "+ChildMaleHIV9+" "+ ChildMaleHIV14);
//  
  // all positives

//TotalPositive=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+
//        ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 +ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//  
//TotalNegative=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+
//        ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg +ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
  System.out.println(facilityname+" KKK "+HIV_AdultFemale+" "+HIV_AdultMale+" "+HIV_ChildFemale+"  "+HIV_ChildMale);
    System.out.println(facilityname+"TestedChildFemale "+TestedChildFemale+"  HIV_ChildFemale "+HIV_ChildFemale +"  TestedChildMale "+TestedChildMale+" HIV_ChildMale   "+HIV_ChildMale);
     double totaltestedmale1=0;
     double totaltestedfemale1=0;
 TotalTested=TestedChildFemale+TestedChildMale+TestedAdultMale+TestedAdultFemale;
 totaltestedmale1=TestedChildMale+TestedAdultMale;
 totaltestedfemale1=TestedChildFemale+TestedAdultFemale;
 TotalPositiveFemale=HIV_ChildFemale + HIV_AdultFemale;
 TotalPositiveMale=HIV_ChildMale + HIV_AdultMale;
 TotalPositive=HIV_ChildFemale+HIV_AdultFemale+HIV_ChildMale+HIV_AdultMale;
 TotalNegativeFemale=totaltestedfemale1-TotalPositiveFemale;
 TotalNegativeMale=totaltestedmale1-TotalPositiveMale;
 TotalNegative=TotalNegativeMale+TotalNegativeFemale;
  //201510
 //this code was added later
 
 less15f=TestedChildFemale;
 less15m=TestedChildMale;
 gret15m=TestedAdultMale;
 gret15f=TestedAdultFemale;
 

// TotalNegativeFemale=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
//TotalNegativeMale=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;

//                TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
//                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
//                TotalPositiveMale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//               
//System.out.println(MaleTestedChild14 +" bbbbb  "+ChildMaleHIV14+"    mmmmm   "+ (MaleTestedChild14-ChildMaleHIV14));
 

         rw0.setHeightInPoints(25);
         c211=rw0.createCell(0);
         c212=rw0.createCell(1);
         c213=rw0.createCell(2);
         c214=rw0.createCell(3);
         c215=rw0.createCell(4);
         c216=rw0.createCell(5);
         c217=rw0.createCell(6);
        
         // the rest
         c11=rw0.createCell(7);
         c12=rw0.createCell(8);
         c13=rw0.createCell(9);
         c14=rw0.createCell(10);
         c15=rw0.createCell(11);
         c16=rw0.createCell(12);
         c17=rw0.createCell(13);
         c18=rw0.createCell(14);
         c19=rw0.createCell(15);
         c20=rw0.createCell(16);
         c110=rw0.createCell(17);
         c111=rw0.createCell(18);
         c112=rw0.createCell(19);
         c113=rw0.createCell(20);
         c114=rw0.createCell(21);
         c115=rw0.createCell(22);
         c116=rw0.createCell(23);
         c117=rw0.createCell(24);
        
         
        //c11.setCellValue(facilname);
//String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
         c211.setCellValue(county);
         c212.setCellValue(district);
         c213.setCellValue(facilityname);
         c214.setCellValue(mflcode);
         c215.setCellValue(dsdta);
         shet3.addMergedRegion(new CellRangeAddress(2,4,0,0));
         shet3.addMergedRegion(new CellRangeAddress(2,4,1,1));
         shet3.addMergedRegion(new CellRangeAddress(2,4,2,2));
         shet3.addMergedRegion(new CellRangeAddress(2,4,3,3));
         shet3.addMergedRegion(new CellRangeAddress(2,4,4,4));
//      Female   
      c11.setCellValue(TotalTested+pmtcttestedtotal);
      c216.setCellValue(TotalPositive+pmtctpositivestotal);
      //pmtct belongs to female
      c217.setCellValue(TotalPositiveFemale+pmtctpositivestotal);
      
         shet3.addMergedRegion(new CellRangeAddress(2,4,5,5));
         shet3.addMergedRegion(new CellRangeAddress(2,4,6,6));
         shet3.addMergedRegion(new CellRangeAddress(3,4,7,7));
      c12.setCellValue((float)Math.round(ChildFemaleHIV1));
      c13.setCellValue((float)Math.round(ChildFemaleHIV4));
      c14.setCellValue((float)Math.round(ChildFemaleHIV9));
      c15.setCellValue((float)Math.round(ChildFemaleHIV14));
      c16.setCellValue((float)Math.round(AdultFemaleHIV19+pmtct15to19pos));
      c17.setCellValue((float)Math.round(AdultFemaleHIV24+pmtct20to24pos));
      c18.setCellValue((float)Math.round(AdultFemaleHIV49+pmtct25to49pos));
      c19.setCellValue((float)Math.round(AdultFemaleHIV50));
      
     
      c20.setCellValue(TotalPositiveMale);
      
      //male
      c110.setCellValue((float)Math.round(ChildMaleHIV1));
      c111.setCellValue((float)Math.round(ChildMaleHIV4));
      c112.setCellValue((float)Math.round(ChildMaleHIV9));
      c113.setCellValue((float)Math.round(ChildMaleHIV14));
      c114.setCellValue((float)Math.round(AdultMaleHIV19));
      c115.setCellValue((float)Math.round(AdultMaleHIV24));
      c116.setCellValue((float)Math.round(AdultMaleHIV49));
      c117.setCellValue((float)Math.round(AdultMaleHIV50));
    
      for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
          
//        System.out.println("red "+redalert +" "+redalert1) ;
          if(redalert==1){
           c217=rw0.getCell(6);
          c217.setCellStyle(redstyle);
          }
           if(redalert1==1){
         c20=rw0.getCell(16);
          c20.setCellStyle(redstyle);
      }
      }
      
//      shet3.addMergedRegion(new CellRangeAddress(2,5,0,0));
     
   
         c11=rw0.createCell(25);
         c12=rw0.createCell(26);
         c13=rw0.createCell(27);
         c14=rw0.createCell(28);
         c15=rw0.createCell(29);
         c16=rw0.createCell(30);
         c17=rw0.createCell(31);
         c18=rw0.createCell(32);
         c19=rw0.createCell(33);
         c110=rw0.createCell(34);
         c111=rw0.createCell(35);
         c112=rw0.createCell(36);
         c113=rw0.createCell(37);
         c114=rw0.createCell(38);
         c115=rw0.createCell(39);
         c116=rw0.createCell(40);
         c117=rw0.createCell(41);
         c118=rw0.createCell(42);
         c119=rw0.createCell(43);
  



  
   int neg1male=0;
  int neg4male=0;
  int neg9male=0;
  int neg14male=0;
  int neg19male=0;
  int neg24male=0;
  int neg49male=0;
  int neg50male=0;
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);
if(AdultMaleHIV19Neg<=-1){
neg19male=1;
}
if(AdultMaleHIV24Neg<=-1){
neg24male=1;
}
if(AdultMaleHIV49Neg<=-1){
neg49male=1;
}
if(AdultMaleHIV50Neg<=-1){
neg50male=1;
}
 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(ChildMaleHIV14);

 if(ChildMaleHIV1Neg<=-1){
neg1male=1;
}
if(ChildMaleHIV4Neg<=-1){
neg4male=1;
}
if(ChildMaleHIV9Neg<=-1){
neg9male=1;
}
if(ChildMaleHIV14Neg<=-1){
neg14male=1;
} 
  
//negative

  int neg1female=0;
  int neg4female=0;
  int neg9female=0;
  int neg14female=0;
  int neg19female=0;
  int neg24female=0;
  int neg49female=0;
  int neg50female=0;
ChildFemaleHIV1Neg=(float)Math.round(FemaleTestedChild1)-(ChildFemaleHIV1);
ChildFemaleHIV4Neg=(float)Math.round(FemaleTestedChild4)-(ChildFemaleHIV4);
ChildFemaleHIV9Neg=(float)Math.round(FemaleTestedChild9)-(ChildFemaleHIV9);
ChildFemaleHIV14Neg=(float)Math.round(FemaleTestedChild14)-(ChildFemaleHIV14);


 if(ChildFemaleHIV1Neg<=-1){
neg1female=1;
}
if(ChildFemaleHIV4Neg<=-1){
neg4female=1;
}
if(ChildFemaleHIV9Neg<=-1){
neg9female=1;
}
if(ChildFemaleHIV14Neg<=-1){
neg14female=1;
} 

System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);
  
   if(AdultFemaleHIV19Neg<=-1){
neg19female=1;
}
if(AdultFemaleHIV24Neg<=-1){
neg24female=1;
}
if(AdultFemaleHIV49Neg<=-1){
neg49female=1;
}
if(AdultFemaleHIV50Neg<=-1){
neg50female=1;
} 
double TotalNegativeFemale1=0;
 double TotalNegativeMale1=0;
         TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
                TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
// negative female
   checkdiff2=negfem-TotalNegativeFemale1;
 if(checkdiff2>2 ||checkdiff2<-2){
 redalert2=1;
 }
 
 // negativemale
   checkdiff3=negmale-TotalNegativeMale1;
 if(checkdiff3>2 ||checkdiff3<-2){
 redalert3=1;
 }

      c11.setCellValue((float)Math.round(TotalNegativeFemale+pmtctnegativetotal));
      c12.setCellValue((float)Math.round(ChildFemaleHIV1Neg));
      c13.setCellValue((float)Math.round(ChildFemaleHIV4Neg));
      c14.setCellValue((float)Math.round(ChildFemaleHIV9Neg));
      c15.setCellValue((float)Math.round(ChildFemaleHIV14Neg));
      c16.setCellValue((float)Math.round(AdultFemaleHIV19Neg+pmtct15to19neg));
      c17.setCellValue((float)Math.round(AdultFemaleHIV24Neg+pmtct20to24neg));
      c18.setCellValue((float)Math.round(AdultFemaleHIV49Neg+pmtct25to49neg));
      c19.setCellValue((float)Math.round(AdultFemaleHIV50Neg));
      
        
      c110.setCellValue((float)Math.round(TotalNegativeMale));
      
      c111.setCellValue((float)Math.round(ChildMaleHIV1Neg));
      c112.setCellValue((float)Math.round(ChildMaleHIV4Neg));
      c113.setCellValue((float)Math.round(ChildMaleHIV9Neg));
      c114.setCellValue((float)Math.round(ChildMaleHIV14Neg));
      c115.setCellValue((float)Math.round(AdultMaleHIV19Neg));
      c116.setCellValue((float)Math.round(AdultMaleHIV24Neg));
      c117.setCellValue((float)Math.round(AdultMaleHIV49Neg));
      c118.setCellValue((float)Math.round(AdultMaleHIV50Neg));
      c119.setCellValue(less15f);
      
   
         c120=rw0.createCell(44);
         c120.setCellStyle(stborder);
         c121=rw0.createCell(45);
         c121.setCellStyle(stborder);
         c122=rw0.createCell(46);
         c122.setCellStyle(stborder);
         c123=rw0.createCell(47);
         c123.setCellStyle(stborder);
         c124=rw0.createCell(48);
         c124.setCellStyle(stborder);
         c125=rw0.createCell(49);
         c125.setCellStyle(stborder);
         c126=rw0.createCell(50);
         c126.setCellStyle(stborder);
         c127=rw0.createCell(51);
         c127.setCellStyle(stborder);
         c128=rw0.createCell(52);
       
      
      
     //this is the new addition 
      c120.setCellValue(gret15f+pmtcttestedtotal);
      c121.setCellValue(less15m);
      c122.setCellValue(gret15m);
      c123.setCellValue(TotalTested+pmtcttestedtotal);
      
       c124.setCellValue(TotalPositive+pmtctpositivestotal);
       c125.setCellValue(TotalNegative+pmtctnegativetotal);
       c126.setCellValue(TotalTested+pmtcttestedtotal);
       c127.setCellValue("PASSED");
       
      
      
      System.out.println(facilityname  +"    jjj  "+AdultMaleHIV19Neg+"__________"+AdultMaleHIV24Neg+"__________"+AdultMaleHIV49Neg+"__________"+AdultMaleHIV50Neg+"__________"+ChildMaleHIV1Neg+"__________"+ChildMaleHIV4Neg+"__________"+ChildMaleHIV9Neg+"__________"+ChildMaleHIV14Neg);
      
        for(int i=23; i<=43;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
          
          
           if(redalert2==1){
           c11=rw0.getCell(25);
          c11.setCellStyle(redstyle);
          }
           if(redalert3==1){
       c110=rw0.getCell(34);
          c110.setCellStyle(redstyle);
      }
           
            if(finalalert==1){
          c119=rw0.getCell(43);
          c119.setCellStyle(redstyle);
          c119.setCellValue("FAILED");
      }
            
            
       //String PMTCT total tested and negatives
            //System.out.println("@@@@@@@@@@@@ PMTCT TOTAL TESTED   "+pmtcttestedtotal+"   :: PMTCT POSITIVE "+pmtctpositivestotal);
           
  if(neg1female==1){
             c12=rw0.getCell(26);
          c12.setCellStyle(redstyle);
  }
  if(neg4female==1){
     c12=rw0.getCell(27);
     c12.setCellStyle(redstyle);}
  if(neg9female==1){
     c13=rw0.getCell(28);
     c13.setCellStyle(redstyle);}
  if(neg14female==1){
     c14=rw0.getCell(29);
      c14.setCellStyle(redstyle);}
  if(neg19female==1){
     c15=rw0.getCell(30);
     c15.setCellStyle(redstyle);}
  if(neg24female==1){
     c16=rw0.getCell(31);
     c16.setCellStyle(redstyle);}
  if(neg49female==1){
     c17=rw0.getCell(32);
     c17.setCellStyle(redstyle);}
  if(neg50female==1){
     c18=rw0.getCell(33);
     c18.setCellStyle(redstyle);}
  
  //male
  if(neg1male==1){
             c111=rw0.getCell(35);
          c111.setCellStyle(redstyle);
  }
  if(neg4male==1){
     c112=rw0.getCell(36);
     c112.setCellStyle(redstyle);}
  if(neg9male==1){
     c113=rw0.getCell(37);
     c113.setCellStyle(redstyle);}
  if(neg14male==1){
     c114=rw0.getCell(38);
      c114.setCellStyle(redstyle);}
  if(neg19male==1){
     c115=rw0.getCell(39);
     c115.setCellStyle(redstyle);}
  if(neg24male==1){
     c116=rw0.getCell(40);
     c116.setCellStyle(redstyle);}
  if(neg49male==1){
     c117=rw0.getCell(41);
     c117.setCellStyle(redstyle);}
  if(neg50male==1){
     c118=rw0.getCell(42);
     c118.setCellStyle(redstyle);}
           
      }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}
                     
                     
                     
                     
                     
                     
                 
                 }
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
                 
            
            
            
            
         if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            IdGenerator IG = new IdGenerator();
            String createdOn = IG.CreatedOn();
            
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=HTC-SDP_VMMC_HTC_Generatted_On_" + createdOn + ".xls");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();
        } catch (SQLException ex) {
            Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
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

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
import java.sql.SQLException;
import java.util.Calendar;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
/**
 *
 * @author Geofrey Nyabuto
 */
public class datimHTCReport extends HttpServlet {
HttpSession session;
int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityId;
int TestedAdultMale=0,TestedAdultFemale=0;
 int TestedChildMale=0,TestedChildFemale=0;
 int HIV_AdultMale=0,HIV_AdultFemale=0;
 int HIV_ChildMale=0,HIV_ChildFemale=0;
 
double FemaleAdultTested;
double FemaleTestedChild;
double AdultFemaleHIV;
double ChildFemaleHIV;
 

  
String createdOn,period;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
       dbConn conn = new dbConn();
        
// MALES
 double  MaleAdultTested;
 double MaleTestedChild;
 double  AdultMaleHIV;
 double ChildMaleHIV;
 
 
  
  
  double FemaleAdultTested1=0.0;
double FemaleAdultTested4=0.0;
double FemaleAdultTested9=0.0;
double FemaleAdultTested14=0.0;
double FemaleAdultTested19=0.0;
double FemaleAdultTested24=0.0;
double FemaleAdultTested49=0.0;
double FemaleAdultTested50=0.0;

double FemaleTestedChild1=0.0;
double FemaleTestedChild4=0.0;
double FemaleTestedChild9=0.0;
double FemaleTestedChild14=0.0;
double FemaleTestedChild19=0.0;
double FemaleTestedChild24=0.0;
double FemaleTestedChild49=0.0;
double FemaleTestedChild50=0.0;

double AdultFemaleHIV19Neg=0.0;
double AdultFemaleHIV24Neg=0.0;
double AdultFemaleHIV49Neg=0.0;
double AdultFemaleHIV50Neg=0.0;

double AdultFemaleHIV19=0.0;
double AdultFemaleHIV24=0.0;
double AdultFemaleHIV49=0.0;
double AdultFemaleHIV50=0.0;

double ChildFemaleHIV1=0.0;
double ChildFemaleHIV4=0.0;
double ChildFemaleHIV9=0.0;
double ChildFemaleHIV14=0.0;

double ChildFemaleHIV1Neg=0.0;
double ChildFemaleHIV4Neg=0.0;
double ChildFemaleHIV9Neg=0.0;
double ChildFemaleHIV14Neg=0.0;
 
 
// MALES
  double MaleAdultTested19Neg=0.0;
  double MaleAdultTested21Neg=0.0;
  double MaleAdultTested49Neg=0.0;
  double MaleAdultTested50Neg=0.0;
  
  double MaleAdultTested19=0.0;
 double  MaleAdultTested24=0.0;
 double MaleAdultTested49=0.0;
 double MaleAdultTested50=0.0;
  
  
  double MaleTestedChild1=0.0;
  double MaleTestedChild4=0.0;
  double MaleTestedChild9=0.0;
  double MaleTestedChild14=0.0;
  
  double MaleTestedChild1Neg=0.0;
  double MaleTestedChild4Neg=0.0;
  double MaleTestedChild9Neg=0.0;
  double MaleTestedChild14Neg=0.0;
  
  double AdultMaleHIV19Neg=0.0;
  double AdultMaleHIV24Neg=0.0;
  double AdultMaleHIV49Neg=0.0;
  double AdultMaleHIV50Neg=0.0;
  
  double AdultMaleHIV19=0.0;
 double  AdultMaleHIV24=0.0;
  double AdultMaleHIV49=0.0;
  double AdultMaleHIV50=0.0;
  
  
 double ChildMaleHIV1=0.0;
 double ChildMaleHIV4=0.0;
double  ChildMaleHIV9=0.0;
 double ChildMaleHIV14=0.0;
 
 double ChildMaleHIV1Neg=0.0;
 double ChildMaleHIV4Neg=0.0;
 double ChildMaleHIV9Neg=0.0;
  double ChildMaleHIV14Neg=0.0;
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
//        reportDuration="4";
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
         FemaleAdultTested=0.0;
 FemaleTestedChild=0.0;
 AdultFemaleHIV=0.0;
 ChildFemaleHIV=0.0;
          double TotalTested=0.0;
            double    TotalPositiveFemale=0.0;
             double   TotalPositiveMale=0.0;
             double   TotalNegativeFemale=0.0;
             double   TotalNegativeMale=0.0;
 
// MALES
   MaleAdultTested=0.0;
  MaleTestedChild=0.0;
   AdultMaleHIV=0.0;
  ChildMaleHIV=0.0;
  double TotalPositive=0.0;
  double TotalNegative=0.0;
  
   String county="";
   String  district="";
    String facilityname="";

//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration=" moh711.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
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
      duration=" moh711.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration=" moh711.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
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
     duration=" moh711.yearmonth="+prevYear+""+month;    
     period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration=" moh711.yearmonth="+year+"0"+month;  
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
  HSSFSheet shet3=wb.createSheet("DATIM DATA FROM MOH 711 ");

  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
    stborder.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    stborder.setWrapText(true);
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    
HSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

   HSSFCellStyle stylemainHeader = wb.createCellStyle();
stylemainHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
stylemainHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   stylemainHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    stylemainHeader.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    stylemainHeader.setWrapText(true);
    
    HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    styleHeader.setWrapText(true);
    
    HSSFCellStyle styleminiHeader = wb.createCellStyle();
styleminiHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleminiHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleminiHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleminiHeader.setAlignment(HSSFCellStyle.ALIGN_LEFT);
    styleminiHeader.setWrapText(true);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

  shet3.setColumnWidth(0, 4000);  
    for (int i=1;i<=17;i++){
   shet3.setColumnWidth(i, 2000);     
    }
 HSSFRow rw0=shet3.createRow(0);
 rw0.setHeightInPoints(25);
 HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
         
         
      c1.setCellValue(period);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=17;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      shet3.addMergedRegion(new CellRangeAddress(0,0,0,17));
      
       Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  
  String facilitiestable="subpartnera";
  
  int selectedyear=year;
  
  if(selectedyear<currentyear){
      
      if(year<2014){
          
      //db for 2014 is the smallest
          
       facilitiestable="subpartnera2014";
  
      }
      else 
      {
      
  facilitiestable="subpartnera"+selectedyear;
  
      }
  }
  
      
      
      
  String getName="SELECT "+facilitiestable+".SubPartnerNom,district.DistrictNom,county.County,"+facilitiestable+".CentreSanteId FROM "+facilitiestable+" "
          + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE "+facilitiestable+".SubPartnerID='"+facilityId+"'";
  conn.rs=conn.st.executeQuery(getName);
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
  }
     
     
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh711 WHERE moh711.SubPartnerID='"+facilityId+"' && "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
    
    
    
    // for header---------------------------------------------------------------------------
    
    
   HSSFCell  c11,c12,c13,c14,c15,c16,c17,c18,c19,c20,c110,c111,c112,c113,c114,c115,c116,c117,c118,
          c119,c120,c121,c122,c123,c124,c125,c126,c127,c128,c129,c130,c131,c132,c133,c134,c135,c136;  
   HSSFCell c211,c212,c213,c214,c215,c216,c217;
rw0=shet3.createRow(2); 
rw0.setHeightInPoints(20);
 
      
        rw0.setHeightInPoints(20);
        
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
         
        c216.setCellValue("Total HIV+");
        c217.setCellValue("Total HIV+ (F)");
   c11.setCellValue("POSITIVE");
      c12.setCellValue("FEMALE");
      c13.setCellValue("");
      c14.setCellValue("");
      c15.setCellValue("");
      c16.setCellValue("");
      c17.setCellValue("");
      c18.setCellValue("");
      c19.setCellValue("");
      
      c110.setCellValue("");
      
      c111.setCellValue("MALE");
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
      c120.setCellValue("FEMALE");
      c121.setCellValue("");
      c122.setCellValue("");
      c123.setCellValue("");
      c124.setCellValue("");
      c125.setCellValue("");
      c126.setCellValue("");
      c127.setCellValue("");
      
      c128.setCellValue("");
      
      c129.setCellValue("MALE");
      c130.setCellValue("");
      c131.setCellValue("");
      c132.setCellValue("");
      c133.setCellValue("");
      c134.setCellValue("");
      c135.setCellValue("");
      c136.setCellValue("");
     for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
     
    for(int i=23; i<=42;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stylemainHeader);
      }
      
   
     shet3.addMergedRegion(new CellRangeAddress(2,2,8,15));
     shet3.addMergedRegion(new CellRangeAddress(2,2,17,24));
     
     shet3.addMergedRegion(new CellRangeAddress(2,2,26,33));
     shet3.addMergedRegion(new CellRangeAddress(2,2,35,42));
     
      
   rw0=shet3.createRow(3);
   rw0.setHeightInPoints(20);
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
      
       for(int i=0; i<=22;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(styleHeader);
      }
      
      shet3.addMergedRegion(new CellRangeAddress(3,3,8,11));
     shet3.addMergedRegion(new CellRangeAddress(3,3,12,15));
     shet3.addMergedRegion(new CellRangeAddress(3,3,17,20));
     shet3.addMergedRegion(new CellRangeAddress(3,3,21,24)); 
//     
      for(int k=23; k<=40;k++){
          c113=rw0.getCell(k);
          c113.setCellStyle(styleHeader);
      }
     
     shet3.addMergedRegion(new CellRangeAddress(3,3,26,29));
     shet3.addMergedRegion(new CellRangeAddress(3,3,30,33));
     shet3.addMergedRegion(new CellRangeAddress(3,3,35,38));
     shet3.addMergedRegion(new CellRangeAddress(3,3,39,42)); 
     
      rw0=shet3.createRow(4);
      rw0.setHeightInPoints(20);
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
  
  for(int l=20; l<=42;l++){
          c113=rw0.getCell(l);
          c113.setCellStyle(styleHeader);
      }
       
    
    
    
    
    
    
    
    
    
    
    int count=4;
    TestedAdultMale=TestedAdultFemale=0;
 TestedChildMale=TestedChildFemale=0;
 HIV_AdultMale=HIV_AdultFemale=0;
 HIV_ChildMale=HIV_ChildFemale=0;
    
    //---------------------------------------------------------------------------
    
    
    String facilid="";
    String facilname="";
    String dsdta="";
     String getfacils="select SubPartnerId,SubPartnerNom from "+facilitiestable+"  where HTC='1' order by SubPartnerNom ";
      conn.rs2= conn.st2.executeQuery(getfacils);
            while(conn.rs2.next()) {
                
          facilname=  conn.rs2.getString(2);
          facilid=  conn.rs2.getString(1);
    String getData="SELECT  (sum(VCTClient_Tested_CF) +sum( VCTClient_Tested_AF)+sum(DTCB_Test_Out_AF)+sum(DTCB_Test_In_AF))" //ADULTS TESTED FEMALE  
            + ",(sum(VCTClient_Tested_CM)+ sum(VCTClient_Tested_AM) +  sum(DTCB_Test_Out_AM) + sum(DTCB_Test_In_AM))"//ADULTS TESTED MALES
            + ", (sum(VCTClient_HIV_CF)+ sum(VCTClient_HIV_AF)+sum(DTCC_HIV_In_AF)+ sum(DTCC_HIV_Out_AF))" // ADULTS HIV+ FEMALE
            + ",(sum(VCTClient_HIV_CM)+sum(VCTClient_HIV_AM)+ sum(DTCC_HIV_In_AM) +sum(DTCC_HIV_Out_AM)) " // ADULTS HIV+ MALE
            + ", (sum(DTCB_Test_Out_CF) +sum(DTCB_Test_In_CF))" // CHILDREN TOTAL TESTED FEMALE
            + ", (sum(DTCB_Test_Out_CM) +sum(DTCB_Test_In_CM))" // CHILDREN TOTAL TESTED MALE
            + ", ( sum(DTCC_HIV_In_CF)+ sum(DTCC_HIV_Out_CF))" // CHILDREN OSITIVE FEMALE
            + ", (sum(DTCC_HIV_In_CM)+ sum(DTCC_HIV_Out_CM)),county.County,district.DistrictNom,"+facilitiestable+".SubPartnerNom,"+facilitiestable+".CentreSanteId,"+facilitiestable+".HTC_Support1"// CHILDREN POSITIVE MALE
           +" from moh711 JOIN "+facilitiestable+" ON moh711.SubPartnerID="+facilitiestable+".SubPartnerID "
              + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
              + "JOIN county ON county.CountyID=district.CountyID "
            + " WHERE "+duration+" and moh711.SubPartnerID='"+facilid+"' " ;
     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    if(conn.rs.next()==true){
       county=conn.rs.getString(9);
     district=conn.rs.getString(10);
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
 
  
         double splitData; int adderPos=0;
           double childSplitData; 
//            TestedAdultFemale=conn.rs.getInt(1);
//    TestedAdultMale=conn.rs.getInt(2);
//    HIV_AdultFemale=conn.rs.getInt(3);
//    HIV_AdultMale=conn.rs.getInt(4);
//    TestedChildFemale=conn.rs.getInt(5);
//    TestedChildMale=conn.rs.getInt(6);
//    HIV_ChildFemale=conn.rs.getInt(7);
//    HIV_ChildMale=conn.rs.getInt(8);
//           TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
//                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
//                TotalPositiveMale1=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//                TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
//                TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
         
         
  adderPos=0;
   double Totalhivfemale=0.0;
   double Totalhivmale=0.0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   
   // adult female hiv+
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50 ;
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
 
 // for child female
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
   adderPos=0;
while(childSplitData<HIV_ChildFemale){ 
 if(adderPos<2){
  ChildFemaleHIV4+=1;   
 }
 else{
 ChildFemaleHIV9+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){ 
 if(adderPos<2){
  AdultFemaleHIV49-=1;   
 }
 else{
 AdultFemaleHIV24-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}
   


// for child female tested 
  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
   adderPos=0;
while(childSplitData<TestedChildFemale){ 
 if(adderPos<2){
  FemaleTestedChild14+=1;   
 }
 else{
 FemaleTestedChild9+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildFemale){ 
 if(adderPos<2){
  FemaleTestedChild14-=1;   
 }
 else{
 FemaleTestedChild9-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

// for child male hiv


 childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
while(childSplitData<HIV_ChildMale){ 
 if(adderPos<2){
  ChildMaleHIV4+=1;   
 }
 else{
 ChildMaleHIV9+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}

  childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildMale){ 
 if(adderPos<2){
  AdultMaleHIV49-=1;   
 }
 else{
 AdultMaleHIV24-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}


///
// for child female tested 
  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
   adderPos=0;
while(childSplitData<TestedChildMale){ 
 if(adderPos<2){
  MaleTestedChild14+=1;   
 }
 else{
 MaleTestedChild9+=1;    
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}
 childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildMale){ 
 if(adderPos<2){
  MaleTestedChild14-=1;   
 }
 else{
 MaleTestedChild9-=1;    
 }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}


  System.out.println("Neg nn  "+ChildMaleHIV1Neg+ " "+ChildMaleHIV4Neg+" "+ChildMaleHIV9Neg+" "+ ChildMaleHIV14Neg);
  System.out.println("tested nn  "+MaleTestedChild1+ " "+MaleTestedChild4+" "+MaleTestedChild9+" "+ MaleTestedChild14);
  System.out.println("hiv+ nnn  "+ChildMaleHIV1+ " "+ChildMaleHIV4+" "+ChildMaleHIV9+" "+ ChildMaleHIV14);
  
  // all positives

TotalPositive=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+
        ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 +ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
  
TotalNegative=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+
        ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg +ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
  
 
                TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
                TotalPositiveMale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
               
//System.out.println(MaleTestedChild14 +" bbbbb  "+ChildMaleHIV14+"    mmmmm   "+ (MaleTestedChild14-ChildMaleHIV14));
 

         rw0.setHeightInPoints(20);
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
  



  
  //negative
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(float)Math.round(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(float)Math.round(AdultMaleHIV24);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(float)Math.round(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(float)Math.round(AdultMaleHIV50);

 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(float)Math.round(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(float)Math.round(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(float)Math.round(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(float)Math.round(ChildMaleHIV14);

  
  
//negative
ChildFemaleHIV1Neg=(float)Math.round(FemaleTestedChild1)-(float)Math.round(ChildFemaleHIV1);
ChildFemaleHIV4Neg=(float)Math.round(FemaleTestedChild4)-(float)Math.round(ChildFemaleHIV4);
ChildFemaleHIV9Neg=(float)Math.round(FemaleTestedChild9)-(float)Math.round(ChildFemaleHIV9);
ChildFemaleHIV14Neg=(float)Math.round(FemaleTestedChild14)-(float)Math.round(ChildFemaleHIV14);

//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(float)Math.round(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(float)Math.round(AdultFemaleHIV24);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(float)Math.round(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(float)Math.round(AdultFemaleHIV50);
 TotalNegativeFemale=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
                TotalNegativeMale=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;

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
      System.out.println(AdultMaleHIV19Neg+"__________"+AdultMaleHIV24Neg+"__________"+AdultMaleHIV49Neg+"__________"+AdultMaleHIV50Neg+"__________"+ChildMaleHIV1Neg+"__________"+ChildMaleHIV4Neg+"__________"+ChildMaleHIV9Neg+"__________"+ChildMaleHIV14Neg);
      
        for(int i=23; i<=42;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}}
     
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
response.setHeader("Content-Disposition", "attachment; filename=moh711_DATIM_REPORT_CREATED_ON_"+createdOn.trim()+".xls");
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

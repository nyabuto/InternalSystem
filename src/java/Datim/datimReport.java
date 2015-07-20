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
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;
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
int artpos,carepos,pmtctpos,totalNewART,totalCurrentART,totalNewCARE,totalCurrentCARE=0; ;
String ARTSupport,PMTCTSupport,CARESuport;
    ArrayList allFacilities = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
       dbConn conn = new dbConn();
       allFacilities.clear();
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
       String headerART []="County,Sub County,Health Facility,mfl code,type of support,Numerator,<1,1-4Y,5-14Y,15-19Y,20+Y,<1,1-4Y,5-14Y,15-19Y,20+Y,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y".split(",") ;
       String headerCARE []="County,Sub County,Health Facility,mfl code,type of support,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,NUMERATOR,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y".split(",") ;
//        year=2015;
//        reportDuration="4";
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
        artpos=carepos=pmtctpos=0;
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
        
     if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
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
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";     
     }
       
        else{
       facilityIds="";    
        }   
        
     }
     System.out.println("period is : "+period);
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
    
//  facilityId=request.getParameter("facility");
//  facilityIds="403";
  
    HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet("ART");
  HSSFSheet shet2=wb.createSheet("CARE");

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
    
//    HSSFCellStyle stylex = wb.createCellStyle();
//stylex.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//stylex.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//   stylex.setBorderTop(HSSFCellStyle.BORDER_THIN);
//    stylex.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//    stylex.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//    stylex.setBorderRight(HSSFCellStyle.BORDER_THIN);
//    stylex.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//    
//HSSFFont fontx = wb.createFont();
//fontx.setColor(HSSFColor.DARK_BLUE.index);
//stylex.setFont(fontx);
//stylex.setWrapText(true);

//   HSSFCellStyle stylemainHeader = wb.createCellStyle();
//stylemainHeader.setFillForegroundColor(HSSFColor.GREY_40_PERCENT.index);
//stylemainHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
//   stylemainHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
//    stylemainHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
//    stylemainHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
//    stylemainHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
//    stylemainHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
//    stylemainHeader.setWrapText(true);
    
    HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleHeader.setWrapText(true);
    
    HSSFCellStyle styleminiHeader = wb.createCellStyle();
styleminiHeader.setFillForegroundColor(HSSFColor.ORCHID.index);
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

 
    for (int i=3;i<=33;i++){
   shet1.setColumnWidth(i, 2000);     
    }
    for (int i=0;i<=1;i++){
   shet1.setColumnWidth(i, 5000);     
    }
  shet1.setColumnWidth(2, 8000); 
  
    for (int i=3;i<=33;i++){
   shet2.setColumnWidth(i, 2000);     
    }
    for (int i=0;i<=1;i++){
   shet2.setColumnWidth(i, 5000);     
    }
  shet2.setColumnWidth(2, 8000); 
  
  shet1.setColumnWidth(5, 3500);
  shet1.setColumnWidth(16, 3500);
  
  shet2.setColumnWidth(5, 3500);
  shet2.setColumnWidth(22, 3500);
  
  HSSFRow  rw00shet1=shet1.createRow(1);
  rw00shet1.setHeightInPoints(30);
  
  HSSFRow  rw00shet2=shet2.createRow(1);
  rw00shet2.setHeightInPoints(30);
  
    HSSFCell  c011;
  
 for(int j=0;j<headerART.length;j++){
        c011=rw00shet1.createCell(j);
         c011.setCellStyle(styleHeader);
    }
     
    for(int j=0;j<headerCARE.length;j++){
        c011=rw00shet2.createCell(j);
         c011.setCellStyle(styleHeader);
    }
    
   c011=rw00shet1.getCell(0);
   c011.setCellValue(period);
   
    c011=rw00shet1.getCell(5);
   c011.setCellValue("CURRENT ON ART");
   
   c011=rw00shet1.getCell(16);
   c011.setCellValue("NEW ON ART");
  shet1.addMergedRegion(new CellRangeAddress(1,1,5,15));
  shet1.addMergedRegion(new CellRangeAddress(1,1,16,32));

   c011=rw00shet2.getCell(0);
   c011.setCellValue(period);
   
   c011=rw00shet2.getCell(5);
   c011.setCellValue("NEW ON CARE");
   
   c011=rw00shet2.getCell(22);
   c011.setCellValue("CURRENTLY ON CARE");
shet2.addMergedRegion(new CellRangeAddress(1,1,5,21));
shet2.addMergedRegion(new CellRangeAddress(1,1,22,38));
  
  
  
  HSSFRow  rw0shet1=shet1.createRow(2);
  rw0shet1.setHeightInPoints(30);
  
  HSSFRow  rw0shet2=shet2.createRow(2);
  rw0shet2.setHeightInPoints(30);
  
    HSSFCell  c001;
  
 for(int j=0;j<headerART.length;j++){
        c001=rw0shet1.createCell(j);
         c001.setCellStyle(styleHeader);
    }
     
    for(int j=0;j<headerCARE.length;j++){
        c001=rw0shet2.createCell(j);
         c001.setCellStyle(styleHeader);
    }
  
 c001=rw0shet1.getCell(5);
 c001.setCellValue("NUMERATOR"); 
 
 c001=rw0shet1.getCell(16);
 c001.setCellValue("NUMERATOR"); 
 
    
  c001=rw0shet1.getCell(6);
 c001.setCellValue("FEMALE");
   
   c001=rw0shet1.getCell(11);
   c001.setCellValue("MALE");
   
   c001=rw0shet1.getCell(17);
   c001.setCellValue("FEMALE");
   
   c001=rw0shet1.getCell(25);
   c001.setCellValue("MALE");
    
  shet1.addMergedRegion(new CellRangeAddress(2,2,6,10));
  shet1.addMergedRegion(new CellRangeAddress(2,2,11,15));
  shet1.addMergedRegion(new CellRangeAddress(2,2,17,24));
  shet1.addMergedRegion(new CellRangeAddress(2,2,25,32)); 
    
  c001=rw0shet2.getCell(5);
 c001.setCellValue("NUMERATOR"); 
 
 c001=rw0shet2.getCell(22);
 c001.setCellValue("NUMERATOR"); 
 
   c001=rw0shet2.getCell(6);
   c001.setCellValue("FEMALE");
   
   c001=rw0shet2.getCell(11);
   c001.setCellValue("MALE");
   
   c001=rw0shet2.getCell(17);
   c001.setCellValue("FEMALE");
   
   c001=rw0shet2.getCell(25);
   c001.setCellValue("MALE");
    
  shet2.addMergedRegion(new CellRangeAddress(2,2,6,13));
  shet2.addMergedRegion(new CellRangeAddress(2,2,14,21));
  shet2.addMergedRegion(new CellRangeAddress(2,2,23,30));
  shet2.addMergedRegion(new CellRangeAddress(2,2,31,38)); 
  
  
    
 //ROW 3 FOR PAEDS STARTS HERE  
  HSSFRow  rw1shet1=shet1.createRow(3);
  rw1shet1.setHeightInPoints(30);
  
  HSSFRow  rw1shet2=shet2.createRow(3);
  rw1shet2.setHeightInPoints(30);
  
    HSSFCell  c01;
  
 for(int j=0;j<headerART.length;j++){
        c01=rw1shet1.createCell(j);
         c01.setCellStyle(styleHeader);
    }
     
    for(int j=0;j<headerCARE.length;j++){
        c01=rw1shet2.createCell(j);
         c01.setCellStyle(styleHeader);
    }
   c01=rw1shet1.getCell(6);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet1.getCell(9);
   c01.setCellValue("Adults 15+ Yr");
   
   c01=rw1shet1.getCell(11);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet1.getCell(14);
   c01.setCellValue("Adults 15+ Yr");
  
   c01=rw1shet1.getCell(17);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet1.getCell(21);
   c01.setCellValue("Adults 15+ Yr");
   
   c01=rw1shet1.getCell(25);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet1.getCell(29);
   c01.setCellValue("Adults 15+ Yr");
   
  shet1.addMergedRegion(new CellRangeAddress(3,3,6,8));
  shet1.addMergedRegion(new CellRangeAddress(3,3,9,10));
  shet1.addMergedRegion(new CellRangeAddress(3,3,11,13));
  shet1.addMergedRegion(new CellRangeAddress(3,3,14,15));
  shet1.addMergedRegion(new CellRangeAddress(3,3,17,20));
  shet1.addMergedRegion(new CellRangeAddress(3,3,21,24));
  shet1.addMergedRegion(new CellRangeAddress(3,3,25,28));
  shet1.addMergedRegion(new CellRangeAddress(3,3,29,32));
  
  
  
  
//  for(int j=0;j<headerART.length;j++){
//        c01=rw1shet1.createCell(j);
//         c01.setCellStyle(styleHeader);
//    }
//     
//    for(int j=0;j<headerCARE.length;j++){
//        c01=rw1shet2.createCell(j);
//         c01.setCellStyle(styleHeader);
//    }
   c01=rw1shet2.getCell(6);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet2.getCell(10);
   c01.setCellValue("Adults 15+ Yr");
   
   c01=rw1shet2.getCell(14);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet2.getCell(18);
   c01.setCellValue("Adults 15+ Yr");
  
   c01=rw1shet2.getCell(23);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet2.getCell(27);
   c01.setCellValue("Adults 15+ Yr");
   
   c01=rw1shet2.getCell(31);
   c01.setCellValue("Paeds <15yrs");
   
   c01=rw1shet2.getCell(35);
   c01.setCellValue("Adults 15+ Yr");
   
  shet2.addMergedRegion(new CellRangeAddress(3,3,6,9));
  shet2.addMergedRegion(new CellRangeAddress(3,3,10,13));
  shet2.addMergedRegion(new CellRangeAddress(3,3,14,17));
  shet2.addMergedRegion(new CellRangeAddress(3,3,18,21));
  shet2.addMergedRegion(new CellRangeAddress(3,3,23,26));
  shet2.addMergedRegion(new CellRangeAddress(3,3,27,30));
  shet2.addMergedRegion(new CellRangeAddress(3,3,31,34));
  shet2.addMergedRegion(new CellRangeAddress(3,3,35,38));
  
    shet1.addMergedRegion(new CellRangeAddress(1,3,0,4));
    shet2.addMergedRegion(new CellRangeAddress(1,3,0,4));
  
  HSSFRow  rw2shet1=shet1.createRow(4);
  rw2shet1.setHeightInPoints(30);
  
  HSSFRow  rw2shet2=shet2.createRow(4);
  rw2shet2.setHeightInPoints(30);
  
    HSSFCell  c11;
    for(int headerpos=0;headerpos<headerART.length;headerpos++){
        String headerInfor=headerART[headerpos];
        c11=rw2shet1.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }
     System.out.println("art header length : "+headerART.length);
     
    for(int headerpos=0;headerpos<headerCARE.length;headerpos++){
        String headerInfor=headerCARE[headerpos];
        c11=rw2shet2.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }
    
    shet1.addMergedRegion(new CellRangeAddress(2,4,5,5));
    shet2.addMergedRegion(new CellRangeAddress(2,4,5,5));
    shet1.addMergedRegion(new CellRangeAddress(2,4,16,16));
    shet2.addMergedRegion(new CellRangeAddress(2,4,22,22));
    
       artpos=4;
      totalNewART= totalCurrentART=totalNewCARE=totalCurrentCARE=0;      
   
    String getData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
            + "subpartnera.CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
    + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
            + "subpartnera.SubPartnerID FROM moh731 JOIN subpartnera "
            + "ON moh731.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds+" "+duration+" && (subpartnera.PMTCT=1 || ART=1) "
            + "GROUP BY moh731.SubPartnerID " ;
     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    while(conn.rs.next()){
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
      
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
      ARTSupport=conn.rs.getString(5);
//      CARESuport=conn.rs.getString(6);
      PMTCTSupport=conn.rs.getString(6);
    HV0308=conn.rs.getInt(7);
    HV0309=conn.rs.getInt(8);
    HV0310=conn.rs.getInt(9);
    HV0311=conn.rs.getInt(10);
    HV0312=conn.rs.getInt(11);
    HV0320=conn.rs.getInt(12);
    HV0321=conn.rs.getInt(13);
    HV0322=conn.rs.getInt(14);
    HV0323=conn.rs.getInt(15);
    HV0324=conn.rs.getInt(16);
    facilityId=conn.rs.getString(17);
    if(ARTSupport!=null){
     String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE moh731.SubPartnerID='"+facilityId+"' && "+duration ;
    conn.rs2=conn.st2.executeQuery(getMaxYearMonth);
    if(conn.rs2.next()==true){
        maxYearMonth=conn.rs2.getInt(1);
    }
        
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
  double splitData; int adderPos=0;
//    VALUES FOR CURRENT ON ART
 currentART1M=(float)Math.round((0.03*HV0335));
 currentART1_4M=(float)Math.round((0.32*HV0335));
 currentART5_14M=(float)Math.round((0.65*HV0335));
 
  splitData=currentART1M+currentART1_4M+currentART5_14M;
  adderPos=0;
  
while(splitData<HV0335){ 
 if(adderPos<2){
  currentART5_14M+=1;   
 }
 else{
 currentART1_4M+=1;    
 }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}

  splitData=currentART1M+currentART1_4M+currentART5_14M;
  adderPos=0;
  
while(splitData>HV0335){ 
 if(adderPos<2){
  currentART5_14M-=1;   
 }
 else{
 currentART1_4M-=1;    
 }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}

 
 currentART15_19M=(float)Math.round((0.02*HV0337));
 currentART20M=(float)Math.round((0.98*HV0337));
 
 splitData=currentART20M+currentART15_19M;
 while(splitData<HV0337){ 
 currentART20M+=1; 
 splitData++;
}
 
 splitData=currentART20M+currentART15_19M;
 while(splitData>HV0337){ 
 currentART20M-=1; 
 splitData--;
}
 
 
currentART1F=(float)Math.round((0.03*HV0336));//NEED CLARIFICATION
currentART1_4F=(float)Math.round((0.32*HV0336));
currentART5_14F=(float)Math.round((0.65*HV0336));

  splitData=currentART5_14F+currentART1_4F+currentART1F;
  adderPos=0;
  
while(splitData<HV0336){ 
 if(adderPos<2){currentART5_14F+=1; }
 else{currentART1_4F+=1; }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

splitData=currentART5_14F+currentART1_4F+currentART1F;
  adderPos=0;
while(splitData>HV0336){ 
 if(adderPos<2){currentART5_14F-=1; }
 else{currentART1_4F-=1; }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

currentART15_19F=(float)Math.round((0.02*HV0338));
currentART20F=(float)Math.round((0.98*HV0338));

 splitData=currentART20F+currentART15_19F;
 while(splitData<HV0338){ 
 currentART20F+=1;
 splitData++;
}

  splitData=currentART20F+currentART15_19F;
 while(splitData>HV0338){ 
 currentART20F-=1;
 splitData--;
}
 
totalCurrentART=HV0338+HV0336+HV0337+HV0335;
  //    VALUES
    
        newART1M=(float)Math.round((0.034*HV0321));
        newART1_4M=(float)Math.round((0.214*HV0321));
        newART5_9M=(float)Math.round((0.37*HV0321));
        newART10_14M=(float)Math.round((0.382*HV0321));
        
splitData=newART10_14M+newART5_9M+newART1_4M+newART1M;
adderPos=0;
while(splitData<HV0321){ 
 if(adderPos==0){newART10_14M+=1; }
 else if(adderPos==1){newART5_9M+=1; }
 else{newART1_4M+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}
 
splitData=newART10_14M+newART5_9M+newART1_4M+newART1M;
adderPos=0;
while(splitData>HV0321){ 
 if(adderPos==0){newART10_14M-=1; }
 else if(adderPos==1){newART5_9M-=1; }
 else{newART1_4M-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

        
        newART15_19M=(float)Math.round((0.008*HV0323));
        newART20_24M=(float)Math.round((0.008*HV0323));
        newART25_49M=(float)Math.round((0.775*HV0323));
        newART50M=(float)Math.round((0.209*HV0323));
        
        splitData=newART25_49M+newART50M+newART20_24M+newART15_19M;
   System.out.println("split data : "+splitData+" all data "+HV0323);     
adderPos=0;
while(splitData<HV0323){ 
 if(adderPos<3){newART25_49M+=1; }
 else{newART50M+=1; }
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
  splitData=newART25_49M+newART50M+newART20_24M+newART15_19M;
//   System.out.println("split data : "+splitData+" all data"+HV0323);     
adderPos=0;
while(splitData>HV0323){ 
 if(adderPos<3){newART25_49M-=1; }
 else{newART50M-=1; }
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}      
        newART1F=(float)Math.round((0.034*HV0322));
        newART1_4F=(float)Math.round((0.214*HV0322));
        newART5_9F=(float)Math.round((0.37*HV0322));
        newART10_14F=(float)Math.round((0.382*HV0322));
    
splitData=newART10_14F+newART5_9F+newART1_4F+newART1F;
adderPos=0;
while(splitData<HV0322){ 
 if(adderPos==0){newART10_14F+=1; }
 else if(adderPos==1){newART5_9F+=1; }
 else{newART1_4F+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}   
  
splitData=newART10_14F+newART5_9F+newART1_4F+newART1F;
adderPos=0;
while(splitData>HV0322){ 
 if(adderPos==0){newART10_14F-=1; }
 else if(adderPos==1){newART5_9F-=1; }
 else{newART1_4F-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

        newART15_19F=(float)Math.round((0.008*HV0324));
        newART20_24F=(float)Math.round((0.008*HV0324));
        newART25_49F=(float)Math.round((0.775*HV0324));
        newART50F=(float)Math.round((0.209*HV0324));
      
    splitData=newART25_49F+newART50F+newART20_24F+newART15_19F;
adderPos=0;
while(splitData<HV0324){ 
 if(adderPos<3){newART25_49F+=1; }
 else{newART50F+=1; }
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}     
     splitData=newART25_49F+newART50F+newART20_24F+newART15_19F;
adderPos=0;
while(splitData>HV0324){ 
 if(adderPos<3){newART25_49F-=1; }
 else{newART50F-=1; }
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}

        totalNewART=HV0324+HV0322+HV0323+HV0321;
        System.out.println();
   //    VALUES
    
        newCARE1M=(float)Math.round((0.18*HV0309));
        newCARE1_4M=(float)Math.round((0.34*HV0309));
        newCARE5_9M=(float)Math.round((0.28*HV0309));
        newCARE10_14M=(float)Math.round((0.20*HV0309));
  
splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
while(splitData<HV0309){ 
 if(adderPos==0){newCARE1_4M+=1; }
 else if(adderPos==1){newCARE5_9M+=1; }
 else if(adderPos==2){newCARE10_14M+=1; }
 else if(adderPos==3){newCARE1M+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
while(splitData>HV0309){ 
 if(adderPos==0){newCARE1_4M-=1; }
 else if(adderPos==1){newCARE5_9M-=1; }
 else if(adderPos==2){newCARE10_14M-=1; }
 else if(adderPos==3){newCARE1M-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}

        newCARE15_19M=(float)Math.round((0.02*HV0311));
        newCARE20_24M=(float)Math.round((0.09*HV0311));
        newCARE25_49M=(float)Math.round((0.80*HV0311));
        newCARE50M=(float)Math.round((0.09*HV0311));
        
splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
while(splitData<HV0311){ 
newCARE25_49M+=1; 
splitData++;
}

splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
while(splitData>HV0311){ 
newCARE25_49M-=1; 
splitData--;
}        
        newCARE1F=(float)Math.round((0.18*HV0310));
        newCARE1_4F=(float)Math.round((0.34*HV0310));
        newCARE5_9F=(float)Math.round((0.28*HV0310));
        newCARE10_14F=(float)Math.round((0.20*HV0310));
        
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
while(splitData<HV0310){ 
 if(adderPos==0){newCARE1_4F+=1; }
 else if(adderPos==1){newCARE5_9F+=1; }
 else if(adderPos==2){newCARE10_14F+=1; }
 else if(adderPos==3){newCARE1F+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}
 
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
while(splitData>HV0310){ 
 if(adderPos==0){newCARE1_4F-=1; }
 else if(adderPos==1){newCARE5_9F-=1; }
 else if(adderPos==2){newCARE10_14F-=1; }
 else if(adderPos==3){newCARE1F-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>3){adderPos=0;}
}

        newCARE15_19F=(float)Math.round((0.02*HV0312));
        newCARE20_24F=(float)Math.round((0.09*HV0312));
        newCARE25_49F=(float)Math.round((0.80*HV0312));
        newCARE50F=(float)Math.round((0.09*HV0312));
       
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
while(splitData<HV0312){ 
newCARE25_49F+=1; 
splitData++;
}
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
while(splitData>HV0312){ 
newCARE25_49F-=1; 
splitData--;
}
        totalNewCARE=HV0312+HV0310+HV0311+HV0309;
     //    VALUES
    
        currentCARE1M=(float)Math.round((0.03*HV0315));
        currentCARE1_4M=(float)Math.round((0.22*HV0315));
        currentCARE5_9M=(float)Math.round((0.37*HV0315));
        currentCARE10_14M=(float)Math.round((0.38*HV0315));

splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
while(splitData<HV0315){ 
 if(adderPos==0){currentCARE10_14M+=1; }
 else if(adderPos==1){currentCARE5_9M+=1; }
 else if(adderPos==2){currentCARE1_4M+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}  
 splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
while(splitData>HV0315){ 
 if(adderPos==0){currentCARE10_14M-=1; }
 else if(adderPos==1){currentCARE5_9M-=1; }
 else if(adderPos==2){currentCARE1_4M-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}       
        currentCARE15_19M=(float)Math.round((0.02*HV0317));
        currentCARE20_24M=(float)Math.round((0.09*HV0317));
        currentCARE25_49M=(float)Math.round((0.80*HV0317));
        currentCARE50M=(float)Math.round((0.09*HV0317));
        
splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
while(splitData<HV0317){ 
currentCARE25_49M+=1; 
splitData++;
}
 splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
while(splitData>HV0317){ 
currentCARE25_49M-=1; 
splitData--;
}       
        currentCARE1F=(float)Math.round((0.03*HV0316));
        currentCARE1_4F=(float)Math.round((0.22*HV0316));
        currentCARE5_9F=(float)Math.round((0.37*HV0316));
        currentCARE10_14F=(float)Math.round((0.38*HV0316));
        
splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
adderPos=0;
while(splitData<HV0316){ 
 if(adderPos==0){currentCARE10_14F+=1; }
 else if(adderPos==1){currentCARE5_9F+=1; }
 else if(adderPos==2){currentCARE1_4F+=1; }
 
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}        
 splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
adderPos=0;
while(splitData>HV0316){ 
 if(adderPos==0){currentCARE10_14F-=1; }
 else if(adderPos==1){currentCARE5_9F-=1; }
 else if(adderPos==2){currentCARE1_4F-=1; }
 
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}       
        currentCARE15_19F=(float)Math.round((0.02*HV0318));
        currentCARE20_24F=(float)Math.round((0.09*HV0318));
        currentCARE25_49F=(float)Math.round((0.80*HV0318));
        currentCARE50F=(float)Math.round((0.09*HV0318));

splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
while(splitData<HV0318){ 
currentCARE25_49F+=1; 
splitData++;
}
 splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
while(splitData>HV0318){ 
currentCARE25_49F-=1; 
splitData--;
}       
       totalCurrentCARE=HV0318+HV0316+HV0317+HV0315;

       String dataART []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","+totalCurrentART+","
           + ""+currentART1F+","+currentART1_4F+","+currentART5_14F+","+currentART15_19F+","
           + ""+currentART20F+","+currentART1M+","+currentART1_4M+","+currentART5_14M+","
           + ""+currentART15_19M+","+currentART20M+","+totalNewART+","
           + ""+newART1F+","+newART1_4F+","+newART5_9F+","+newART10_14F+","+newART15_19F+","+newART20_24F+","
           + ""+newART25_49F+","+newART50F+","+newART1M+","+newART1_4M+","+newART5_9M+","+newART10_14M+","
           + ""+newART15_19M+","+newART20_24M+","+newART25_49M+","+newART50M).split(",");
   
   String dataCARE []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","+totalNewCARE+","
           + ""+newCARE1F+","+newCARE1_4F+","+newCARE5_9F+","+newCARE10_14F+","+newCARE15_19F+","+newCARE20_24F+","
           + ""+newCARE25_49F+","+newCARE50F+","+newCARE1M+","+newCARE1_4M+","+newCARE5_9M+","+newCARE10_14M+","
           + ""+newCARE15_19M+","+newCARE20_24M+","+newCARE25_49M+","+newCARE50M+","+totalCurrentCARE+","
           + ""+currentCARE1F+","+currentCARE1_4F+","+currentCARE5_9F+","+currentCARE10_14F+","+currentCARE15_19F+","
           + ""+currentCARE20_24F+","+currentCARE25_49F+","+currentCARE50F+","+currentCARE1M+","+currentCARE1_4M+","
           + ""+currentCARE5_9M+","+currentCARE10_14M+","+currentCARE15_19M+","+currentCARE20_24M+","
           + ""+currentCARE25_49M+","+currentCARE50M).split(",");
    
    artpos++;
    
      HSSFRow rw3shet1=shet1.createRow(artpos); 
       rw3shet1.setHeightInPoints(25);
       for(int positionART=0;positionART<dataART.length;positionART++){
       String value=dataART[positionART];
           c11=rw3shet1.createCell(positionART);
        if(positionART>4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionART==5 || positionART==16){ c11.setCellStyle(styleHeader);}
              }
// System.out.println("art data length : "+dataART.length);
    
 HSSFRow rw3Shet2=shet2.createRow(artpos); 
       rw3Shet2.setHeightInPoints(25);
       for(int positionCARE=0;positionCARE<dataCARE.length;positionCARE++){
       String value=dataCARE[positionCARE];
           c11=rw3Shet2.createCell(positionCARE);
          if(positionCARE>4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
         if(positionCARE==5 || positionCARE==22){ c11.setCellStyle(styleHeader);}
        
              }      
 }
    
    }  
    
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
response.setHeader("Content-Disposition", "attachment; filename=DATIM_SPECIAL_REPORT_CREATED_ON_"+createdOn.trim()+".xls");
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

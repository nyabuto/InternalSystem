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
        
       String headerART []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-14Y,15-19Y,20+Y,<1,1-4Y,5-14Y,15-19Y,20+Y,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y".split(",") ;
       String headerCARE []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,NUMERATOR,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y".split(",") ;
//        year=2015;
//        reportDuration="4";
          String facilityIds1="";
       
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
        facilityIds1="(";
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
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period="DATIM SEMI - ANNUAL DATA REPORT FOR : APRIL "+year+" to SEPT "+year; 
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
      period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
     period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
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
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
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
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";     
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";     
     }
       
        else{
       facilityIds="";    
       facilityIds1="";    
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
  HSSFSheet shet3=wb.createSheet("HTC ");
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
    
    // for sheet3
    for (int i=3;i<=42;i++){
   shet3.setColumnWidth(i, 2000);     
    }
    for (int i=0;i<=1;i++){
   shet2.setColumnWidth(i, 5000);     
    }
    for (int i=0;i<=1;i++){
   shet3.setColumnWidth(i, 5000);     
    }
  shet2.setColumnWidth(2, 8000); 
  
  shet1.setColumnWidth(5, 3500);
  shet1.setColumnWidth(16, 3500);
  
  shet2.setColumnWidth(5, 3500);
  shet2.setColumnWidth(22, 3500);
  
  shet3.setColumnWidth(7, 3500);
  shet3.setColumnWidth(25, 3500);
  
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
   c011.setCellValue("CURRENTLY ON CARE");
   
   c011=rw00shet2.getCell(22);
   c011.setCellValue("NEW ON CARE");
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
   
   c001=rw0shet2.getCell(14);
   c001.setCellValue("MALE");
   
   c001=rw0shet2.getCell(23);
   c001.setCellValue("FEMALE");
   
   c001=rw0shet2.getCell(31);
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
   
   String dataCARE []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","+totalCurrentCARE+","
           + ""+currentCARE1F+","+currentCARE1_4F+","+currentCARE5_9F+","+currentCARE10_14F+","+currentCARE15_19F+","
           + ""+currentCARE20_24F+","+currentCARE25_49F+","+currentCARE50F+","+currentCARE1M+","+currentCARE1_4M+","
           + ""+currentCARE5_9M+","+currentCARE10_14M+","+currentCARE15_19M+","+currentCARE20_24M+","
           + ""+currentCARE25_49M+","+currentCARE50M+","+totalNewCARE+","
           + ""+newCARE1F+","+newCARE1_4F+","+newCARE5_9F+","+newCARE10_14F+","+newCARE15_19F+","+newCARE20_24F+","
           + ""+newCARE25_49F+","+newCARE50F+","+newCARE1M+","+newCARE1_4M+","+newCARE5_9M+","+newCARE10_14M+","
           + ""+newCARE15_19M+","+newCARE20_24M+","+newCARE25_49M+","+newCARE50M).split(",");
    
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
    
    
    
    // 711 REPORT
    // MALES
    
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
         
         
      c1.setCellValue(period);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=42;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      shet3.addMergedRegion(new CellRangeAddress(0,0,0,17));
     HSSFCell  c12,c13,c14,c15,c16,c17,c18,c19,c20,c110,c111,c112,c113,c114,c115,c116,c117,c118;
      HSSFCell    c119,c120,c121,c122,c123,c124,c125,c126,c127,c128,c129,c130,c131,c132,c133,c134,c135,c136;  
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
         
        c216.setCellValue("Total HIV+");
             
        c217.setCellValue("Total HIV+ (F)");
      c11.setCellValue("POSITIVE");
  
      c12.setCellValue("FEMALE");
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
      
      c111.setCellValue("MALE");
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
      c120.setCellValue("FEMALE");
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
      
      c129.setCellValue("MALE");
      c129.setCellStyle(stylemainHeader);
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
            + ", ( sum(DTCC_HIV_In_CF)+ sum(DTCC_HIV_Out_CF))" // CHILDREN OSITIVE FEMALE
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
//                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
//                TotalPositiveMale1=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//                TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
//                TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
         
         
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
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);

 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(ChildMaleHIV14);

  
  
//negative
ChildFemaleHIV1Neg=(float)Math.round(FemaleTestedChild1)-(ChildFemaleHIV1);
ChildFemaleHIV4Neg=(float)Math.round(FemaleTestedChild4)-(ChildFemaleHIV4);
ChildFemaleHIV9Neg=(float)Math.round(FemaleTestedChild9)-(ChildFemaleHIV9);
ChildFemaleHIV14Neg=(float)Math.round(FemaleTestedChild14)-(ChildFemaleHIV14);
System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);



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
      System.out.println(facilityname  +"    jjj  "+AdultMaleHIV19Neg+"__________"+AdultMaleHIV24Neg+"__________"+AdultMaleHIV49Neg+"__________"+AdultMaleHIV50Neg+"__________"+ChildMaleHIV1Neg+"__________"+ChildMaleHIV4Neg+"__________"+ChildMaleHIV9Neg+"__________"+ChildMaleHIV14Neg);
      
        for(int i=23; i<=42;i++){
          c11=rw0.getCell(i);
          c11.setCellStyle(stborder);
      }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
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

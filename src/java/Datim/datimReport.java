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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
/**
 *
 * @author Geofrey Nyabuto
 * This is the DATIM ART , CARE ,PMTCT
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
int artpos,carepos,pmtctpos,tbpos,totalNewART,totalCurrentART,totalNewCARE,totalCurrentCARE=0; ;
String ARTSupport,PMTCTSupport,CARESuport;

int HV0210,HV0209,HV0205,HV0217,HV0216;
int HV0224,HV0225,HV0227,HV0232,HV0229,HV0230,HV0231;
int HV0302,HV0206,HV0207,HV0208;
int HV0319,HV0350,HV0351,HV0352,HV0353,HV0354;
Double PMTCT_FO_I_N,PMTCT_FO_I_D,PMTCT_FO_I_LINKED,PMTCT_FO_I_NOT_LINKED,PMTCT_FO_I_UNKNOWN,
        PMTCT_FO_U_NOT_BREASTFEEDING,PMTCT_FO_U_STILL_BREASTFEEDING,PMTCT_FO_U_BREASTFEEDING_UNKNOWN,
        PMTCT_FO_OTHER_INCARE,PMTCT_FO_OTHER_NOFOLLOWUP,PMTCT_FO_DIED,PMTCT_FO_TRANSFERRED;
Double PMTCT_ARV_N,PMTCT_ARV_D,PMTCT_ARV_LIFELONGART_NEW,PMTCT_ARV_LIFELONGART_EXISTING,
        PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV,PMTCT_ARV_MATERNAL_AZT,PMTCT_ARV_SINGLEDOSE;
Double PMTCT_EID_N,PMTCT_EID_VIRO_2MONTHS,PMTCT_EID_VIRO_2_12MONTHS,
        PMTCT_EID_P_VIRO_2MONTHS,PMTCT_EID_P_VIRO_2_12MONTHS;
Double PMTCT_STATN_N,PMTCT_STATN_KNOWNPOSTIVE,PMTCTN_STAT_NEWPOSTIVE;
Double PMTCT_STATD_D,PMTCT_STATD_LESS15,PMTCT_STATD_15_19,PMTCT_STATD_20_24,PMTCT_STATD_25;
Double PMTCT_CTX;
int  numerator,denominator=0;
int errorPMTCT,errorART,errorCARE;
//int TB_STAT_N,TB_STAT_D,TB_STAT_FEMALE,TB_STAT_MALE,TB_STAT_1,TB_STAT_4,TB_STAT_9,TB_STAT_14,TB_STAT_19,TB_STAT_20,
//        TB_STAT_POSTIVE,TB_STAT_NEGATIVE;
//Double TB_SCREEN_D,TB_SCREEN_N,TB_SCREEN_FEMALE,TB_SCREEN_MALE,TB_SCREEN_LESS15,TB_SCREEN_1,TB_SCREEN_4,TB_SCREEN_9,
//       TB_SCREEN_14,TB_SCREEN_MORE15,TB_SCREEN_19,TB_SCREEN_20;
//int TB_ART_N,TB_ART_D,TB_ART_FEMALE,TB_ART_MALE,TB_ART_1,TB_ART_4,TB_ART_9,TB_ART_14,TB_ART_19,TB_ART_20;
String excelDuration;
    ArrayList allFacilities = new ArrayList();
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
       dbConn conn = new dbConn();
       allFacilities.clear();
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
//        ***************************************************************************************
//                ******************SERVLET FOR ART,CARE AND PMTCT**************************
//        ***************************************************************************************
       String headerART []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-14Y,15-19Y,20+Y,<1,1-4Y,5-14Y,15-19Y,20+Y,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
       String headerCARE []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,NUMERATOR,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
       String headerPMTCT []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,HIV-infected:Linked to ART,HIV-infected: Not linked to ART,HIV-infected : Unknown link,HIV-uninfected:Not beastfeeding,HIV-uninfected: Still breastfeeeding,HIV-uninfected:Breastfeeding unknown,Other outcomes: In care but not test done, Other outcomes:Lost to follow up,Other outcomes : Died,Other outcomes:Transferred out,Numerator,Denominator,Life-long ART:New,Life-long ART: Already on treatment at the beginning of the current pregnancy,Maternal Triple-Drug ARV,Maternal AZT,Single-dose nevirapine(with or without tail),Numerator,Infants who received a virologic test within 2 months of birth, Infants who received their first virologic HIV test between 2 and 12 months of age,Infants with a postive virologic test results within 2 months of birth, Infants with a postive virologic test resultsbetween 2 and 12 months of age,Numerator,Known postive at entry,New postives,Denominator,Numerator,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
//       String headerTB[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+Y,Positive,Negative,Total PLVHIV enrolled in clinical care (HVO319),Ho of PLV in HIV clinical care screened for TB (HV0354),Female,Male, Screened for TB <15 Years,<1,1-4Y,5-9Y,10-14Y,Screened for TB >15 years,15-19Y,20+Y,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+,Verification Status".split(",") ;
// 
//        year=2015;
//        reportDuration="4";
       String facilityIds1="";
       excelDuration="";
   String  subcounty_countywhere=" (1=1) and ";//20160711     
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
      
        artpos=carepos=pmtctpos=tbpos=0;
        
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        excelDuration="year='"+year+"' && ";
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR : OCT "+prevYear+" to MARCH "+year;
     
     excelDuration=" year='"+year+"' && quarter<=2 && ";
       }
           else{
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period="DATIM SEMI - ANNUAL DATA REPORT FOR : APRIL "+year+" to SEPT "+year; 
      excelDuration=" year='"+year+"' && quarter>=3 && ";
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
      excelDuration=" year='"+year+"' && quarter='"+quarter+"' && ";
        }
        }  
        
      else if(reportDuration.equals("4")){
          excelDuration="";
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
     
     if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   
     {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
     subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";
 
    
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
  
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
     
     } 
     else {
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT subpartnera.SubPartnerID FROM subpartnera "
    + "JOIN district ON subpartnera.DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
         
           subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711
         
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";     
   
     }
       
        else {
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
//  HSSFSheet shet3=wb.createSheet("HTC ");
  HSSFSheet shetPMTCT=wb.createSheet("PMTCT");
//  HSSFSheet shetTB=wb.createSheet("TB");
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

  for(int i=3;i<=headerPMTCT.length;i++){
   shetPMTCT.setColumnWidth(i, 4000);     
    }
    for (int i=0;i<=1;i++){
   shetPMTCT.setColumnWidth(i, 5000);     
    }
  shetPMTCT.setColumnWidth(2, 8000); 
  
  for(int i=5;i<=16;i++){
    shetPMTCT.setColumnWidth(i, 4000);   
  }
  
//  for(int i=3;i<=headerTB.length;i++){
//   shetTB.setColumnWidth(i, 4000);     
//    }
//    for (int i=0;i<=1;i++){
//   shetTB.setColumnWidth(i, 5000);     
//    }
//  shetTB.setColumnWidth(2, 8000);
 
 
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
  
  
  
//  ART AND CARE HEADERS============================================================================================
  
  
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
    
//    PMTCT HEADER=====================================================================================
    
    
    HSSFRow  rw0shetPMTCT=shetPMTCT.createRow(1);
  rw0shetPMTCT.setHeightInPoints(30);

  
 for(int j=0;j<headerPMTCT.length;j++){
        c001=rw0shetPMTCT.createCell(j);
         c001.setCellStyle(styleHeader);
    } 
 c001=rw0shetPMTCT.getCell(0);
 c001.setCellValue(period); 
 
 c001=rw0shetPMTCT.getCell(5);
 c001.setCellValue("PMTCT_FO"); 
 
 c001=rw0shetPMTCT.getCell(17);
 c001.setCellValue("PMTCT_ARV"); 
 
    
  c001=rw0shetPMTCT.getCell(24);
 c001.setCellValue("PMTCT_EID");
   
   c001=rw0shetPMTCT.getCell(29);
   c001.setCellValue("PMTCT_STAT (Numerator)");
   
   c001=rw0shetPMTCT.getCell(32);
   c001.setCellValue("PMTCT_STAT (Denominator)");
   
   c001=rw0shetPMTCT.getCell(33);
   c001.setCellValue("PMTCT_CTX");
    
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,0,4));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,5,16));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,17,23));
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,24,28)); 
  shetPMTCT.addMergedRegion(new CellRangeAddress(1,1,29,31));
//  shetPMTCT.addMergedRegion(new CellRangeAddress(2,2,25,32)); 
    

  HSSFRow  rw2shetPMTCT=shetPMTCT.createRow(2);
  rw2shetPMTCT.setHeightInPoints(50);
  
    for(int headerpos=0;headerpos<headerPMTCT.length;headerpos++){
        String headerInfor=headerPMTCT[headerpos];
        c11=rw2shetPMTCT.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }
//     System.out.println("art header length : "+headerPMTCT.length);
 
    
//    // TB HEADER======================================================================
//    
//          HSSFRow  rw0shetTB=shetTB.createRow(1);
//  rw0shetTB.setHeightInPoints(30);
//
//  
// for(int j=0;j<headerTB.length;j++){
//        c001=rw0shetTB.createCell(j);
//         c001.setCellStyle(styleHeader);
//    } 
// c001=rw0shetTB.getCell(0);
// c001.setCellValue(period); 
// 
// c001=rw0shetTB.getCell(5);
// c001.setCellValue("TB_STAT"); 
// 
// c001=rw0shetTB.getCell(17);
// c001.setCellValue("TB_SCREEN"); 
// 
//    
//  c001=rw0shetTB.getCell(29);
//  c001.setCellValue("TB_ARV");
// 
//  shetTB.addMergedRegion(new CellRangeAddress(1,1,5,16));
//  shetTB.addMergedRegion(new CellRangeAddress(1,1,17,28));
//  shetTB.addMergedRegion(new CellRangeAddress(1,1,29,38)); 
//
//
//   
//    
//    
//      HSSFRow  rw1shetTB=shetTB.createRow(2);
//  rw1shetTB.setHeightInPoints(30);
//
//  
// for(int j=0;j<headerTB.length;j++){
//        c001=rw1shetTB.createCell(j);
//         c001.setCellStyle(styleHeader);
//    } 
// c001=rw1shetTB.getCell(0);
// c001.setCellValue(period); 
// 
// c001=rw1shetTB.getCell(5);
// c001.setCellValue("Numerator"); 
// 
// c001=rw1shetTB.getCell(6);
// c001.setCellValue("Denominator"); 
// 
//    
//  c001=rw1shetTB.getCell(9);
// c001.setCellValue("Paeds");
//   
//   c001=rw1shetTB.getCell(12);
//   c001.setCellValue("Adults");
//   
//   c001=rw1shetTB.getCell(15);
//   c001.setCellValue("HIV Status");
//   
//   c001=rw1shetTB.getCell(17);
//   c001.setCellValue("Denominator");
//   
//   c001=rw1shetTB.getCell(18);
// c001.setCellValue("Numerator"); 
// 
//  shetTB.addMergedRegion(new CellRangeAddress(1,2,0,4));
//  shetTB.addMergedRegion(new CellRangeAddress(2,2,9,11));
//  shetTB.addMergedRegion(new CellRangeAddress(2,2,12,14));
//  shetTB.addMergedRegion(new CellRangeAddress(2,2,15,16)); 
////  shetTB.addMergedRegion(new CellRangeAddress(2,2,29,31));
//
//    
//    
// 
//
//    
//     HSSFRow  rw2shetTB=shetTB.createRow(3);
//  rw2shetTB.setHeightInPoints(50);
//  
//    for(int headerpos=0;headerpos<headerTB.length;headerpos++){
//        String headerInfor=headerTB[headerpos];
//        c11=rw2shetTB.createCell(headerpos);
//         c11.setCellValue(headerInfor);
//         c11.setCellStyle(styleHeader);
//    }  
    
    
       artpos=tbpos=4;pmtctpos=3;
      totalNewART= totalCurrentART=totalNewCARE=totalCurrentCARE=0;      

//    String getData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
//            + "subpartnera.CentreSanteId,ART_Support,PMTCT_Support,"
//            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
//    + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
//            + "subpartnera.SubPartnerID FROM moh731 JOIN subpartnera "
//            + "ON moh731.SubPartnerID=subpartnera.SubPartnerID "
//            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
//          + "district.CountyID=county.CountyID"
//            + " WHERE "
//    + " "+facilityIds+" "+duration+" && (subpartnera.PMTCT=1 || ART=1) "
//            + "GROUP BY moh731.SubPartnerID " ;
      
      
      
             //BEFORE WHILE LOOP
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    ArrayList art_staticfacility= new ArrayList();
    ArrayList art_staticcounty= new ArrayList();
    ArrayList art_staticdistrict= new ArrayList();
    ArrayList art_staticmfl= new ArrayList();
    ArrayList art_staticdsd_ta= new ArrayList();
    ArrayList art_staticart_hv= new ArrayList();
    ArrayList art_statichtc_hv= new ArrayList();
    ArrayList art_staticpmtct_hv= new ArrayList();
    
    int art_blankrows=37;
    int care_blankrows=43;
    
   String getartstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " subpartnera.SubPartnerNom as facility, subpartnera.CentreSanteId as mflcode, subpartnera.HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    subpartnera join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = subpartnera.DistrictID    where "+subcounty_countywhere+" ( ART='1') group by subpartnera.SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getartstaticfacilities);
    while(conn.rs.next())
    {
    
     art_staticcounty.add(conn.rs.getString("county"));
     String district=conn.rs.getString("district");
     art_staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     art_staticfacility.add(conn.rs.getString("facility"));
     art_staticmfl.add(conn.rs.getString("mflcode"));   
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     art_staticdsd_ta.add(dsdta);   
     
      if(conn.rs.getString("ART_highvolume")!=null){art_staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {art_staticart_hv.add(""); }
     if(conn.rs.getString("HTC_highvolume")!=null){ art_statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { art_statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){art_staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {art_staticpmtct_hv.add("");}

     
    }
    
    
    
    
    //PMTCT
    
    
    ArrayList pmtct_staticfacility= new ArrayList();
    ArrayList pmtct_staticcounty= new ArrayList();
    ArrayList pmtct_staticdistrict= new ArrayList();
    ArrayList pmtct_staticmfl= new ArrayList();
    ArrayList pmtct_staticdsd_ta= new ArrayList();
    ArrayList pmtct_staticart_hv= new ArrayList();
    ArrayList pmtct_statichtc_hv= new ArrayList();
    ArrayList pmtct_staticpmtct_hv= new ArrayList();
    
    int pmtct_blankrows=38;
    
   String getpmtctstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " subpartnera.SubPartnerNom as facility, subpartnera.CentreSanteId as mflcode, subpartnera.HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    subpartnera join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = subpartnera.DistrictID    where "+subcounty_countywhere+" ( PMTCT='1') group by subpartnera.SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getpmtctstaticfacilities);
    while(conn.rs.next())
    {
    
     pmtct_staticcounty.add(conn.rs.getString("county"));
     String district=conn.rs.getString("district");
     pmtct_staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     pmtct_staticfacility.add(conn.rs.getString("facility"));
     pmtct_staticmfl.add(conn.rs.getString("mflcode"));   
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     pmtct_staticdsd_ta.add(dsdta); 
     
     if(conn.rs.getString("ART_highvolume")!=null){pmtct_staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {pmtct_staticart_hv.add(""); }
     if(conn.rs.getString("HTC_highvolume")!=null){ pmtct_statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { pmtct_statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){pmtct_staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {pmtct_staticpmtct_hv.add("");}

     
    }
    
    
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
      
      
       String getData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
            + "subpartnera.CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
            + "subpartnera.SubPartnerID,"
            + "SUM(HV0205),SUM(HV0209),SUM(HV0210),SUM(HV0216),SUM(HV0217),"
            + "SUM(HV0224),SUM(HV0225),SUM(HV0227),SUM(HV0229),SUM(HV0230),SUM(HV0231),SUM(HV0232),"
            + "SUM(HV0302),SUM(HV0206),SUM(HV0207),SUM(HV0208)"
            + ",SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354) ,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
            + " FROM moh731 JOIN subpartnera "
            + "ON moh731.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds+" "+duration+" && (subpartnera.PMTCT=1 || ART=1) "
            + "GROUP BY moh731.SubPartnerID " ;
       
//     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    while(conn.rs.next()){
        
        
     //INSIDE WHILE LOOP
	  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
        //REMOVE SITES THAT HAVE DATA FROM THE STATIC ARRAYLIST SET
        
        //get the index of the current facility
        int mflindex=art_staticmfl.indexOf(conn.rs.getString("CentreSanteId"));
        
        if(mflindex!=-1){        
           //remove the element from the arraylist 
             art_staticfacility.remove(mflindex);
             art_staticcounty.remove(mflindex);
             art_staticdistrict.remove(mflindex);
             art_staticmfl.remove(mflindex);
             art_staticdsd_ta.remove(mflindex);
             
             art_staticart_hv.remove(mflindex);
             art_statichtc_hv.remove(mflindex);
             art_staticpmtct_hv.remove(mflindex);
        
                         }
        
        
        //PMTCT
        
        
          int pmtctmflindex=pmtct_staticmfl.indexOf(conn.rs.getString("CentreSanteId"));
        
        if(pmtctmflindex!=-1)
                         {        
           //remove the element from the arraylist 
             pmtct_staticfacility.remove(pmtctmflindex);
             pmtct_staticcounty.remove(pmtctmflindex);
             pmtct_staticdistrict.remove(pmtctmflindex);
             pmtct_staticmfl.remove(pmtctmflindex);
             pmtct_staticdsd_ta.remove(pmtctmflindex);
             pmtct_staticart_hv.remove(pmtctmflindex);
             pmtct_statichtc_hv.remove(pmtctmflindex);
             pmtct_staticpmtct_hv.remove(pmtctmflindex);
             
        
                         }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
        
        String arthv=" ";
     String htchv=" ";
     String pmtcthv=" ";
     
      if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getString("ART_highvolume");}
      if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getString("HTC_highvolume"); }
      if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getString("PMTCT_highvolume");}
        
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
 
PMTCT_FO_I_N=PMTCT_FO_I_D=PMTCT_FO_I_LINKED=PMTCT_FO_I_NOT_LINKED=PMTCT_FO_I_UNKNOWN=
        PMTCT_FO_U_NOT_BREASTFEEDING=PMTCT_FO_U_STILL_BREASTFEEDING=PMTCT_FO_U_BREASTFEEDING_UNKNOWN=
        PMTCT_FO_OTHER_INCARE=PMTCT_FO_OTHER_NOFOLLOWUP=PMTCT_FO_DIED=PMTCT_FO_TRANSFERRED=0.0;
 PMTCT_ARV_N=PMTCT_ARV_D=PMTCT_ARV_LIFELONGART_NEW=PMTCT_ARV_LIFELONGART_EXISTING=
        PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV=PMTCT_ARV_MATERNAL_AZT=PMTCT_ARV_SINGLEDOSE=0.0;
 PMTCT_EID_N=PMTCT_EID_VIRO_2MONTHS=PMTCT_EID_VIRO_2_12MONTHS=
        PMTCT_EID_P_VIRO_2MONTHS=PMTCT_EID_P_VIRO_2_12MONTHS=0.0;
 PMTCT_STATN_N=PMTCT_STATN_KNOWNPOSTIVE=PMTCTN_STAT_NEWPOSTIVE=0.0;
 PMTCT_STATD_D=PMTCT_STATD_LESS15=PMTCT_STATD_15_19=PMTCT_STATD_20_24=PMTCT_STATD_25=0.0;
PMTCT_CTX=0.0; 
errorPMTCT=errorART=errorCARE=0;
HV0319=HV0350=HV0351=HV0352=HV0353=HV0354=0;

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
    
    HV0205=conn.rs.getInt(18);
        HV0209=conn.rs.getInt(19);
        HV0210=conn.rs.getInt(20);
        HV0216=conn.rs.getInt(21);
        HV0217=conn.rs.getInt(22);
        HV0224=conn.rs.getInt(23);
        HV0225=conn.rs.getInt(24);
        HV0227=conn.rs.getInt(25);
        HV0229=conn.rs.getInt(26);
        HV0230=conn.rs.getInt(27);
        HV0231=conn.rs.getInt(28);
        HV0232=conn.rs.getInt(29);
        HV0302=conn.rs.getInt(30);
        HV0206=conn.rs.getInt(31);
        HV0207=conn.rs.getInt(32);
        HV0208=conn.rs.getInt(33);
        HV0350=conn.rs.getInt(34);
        HV0351=conn.rs.getInt(35);
        HV0352=conn.rs.getInt(36);
        HV0353=conn.rs.getInt(37);
        HV0354=conn.rs.getInt(38);
        
//   HV0302=0;
     String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE moh731.SubPartnerID='"+facilityId+"' && "+duration ;
    conn.rs2=conn.st2.executeQuery(getMaxYearMonth);
    if(conn.rs2.next()==true){
        maxYearMonth=conn.rs2.getInt(1);
    }
        
     String getCurrent="SELECT HV0314,HV0315,HV0316,HV0317,HV0318,"
    + "HV0334,HV0335,HV0336,HV0337,HV0338,HV0302,HV0319 FROM moh731 WHERE "
    + "moh731.SubPartnerID='"+facilityId+"' && yearmonth='"+maxYearMonth+"'";
//     System.out.println("current : "+getCurrent);
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
//     HV0302=conn.rs1.getInt(11);
     HV0319=conn.rs1.getInt(12);
     }
     
      if(ARTSupport!=null){
  double splitData; int adderPos=0;
// .>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
// >>>>>>>>>>>>>>>>>>>>>>ART STARTS HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  
//    VALUES FOR CURRENT ON ART
 currentART1M=(float)Math.round((0.03*HV0335));
 currentART1_4M=(float)Math.round((0.32*HV0335));
 currentART5_14M=(float)Math.round((0.65*HV0335));
 
  splitData=currentART1M+currentART1_4M+currentART5_14M;
  adderPos=0;
 if((splitData-HV0335)>2 ||(HV0335-splitData)>2 ){errorART++;}
 else{
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

 }
 currentART15_19M=(float)Math.round((0.02*HV0337));
 currentART20M=(float)Math.round((0.98*HV0337));
 
 splitData=currentART20M+currentART15_19M;
  if((splitData-HV0337)>2 ||(HV0337-splitData)>2 ){errorART++;}
  else{
 while(splitData<HV0337){ 
 currentART20M+=1; 
 splitData++;
}
 
 splitData=currentART20M+currentART15_19M;
 while(splitData>HV0337){ 
 currentART20M-=1; 
 splitData--;
}
 
  }
currentART1F=(float)Math.round((0.03*HV0336));//NEED CLARIFICATION
currentART1_4F=(float)Math.round((0.32*HV0336));
currentART5_14F=(float)Math.round((0.65*HV0336));

  splitData=currentART5_14F+currentART1_4F+currentART1F;
  adderPos=0;
   if((splitData-HV0336)>2 ||(HV0336-splitData)>2 ){errorART++;}
   else{
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
   }
currentART15_19F=(float)Math.round((0.02*HV0338));
currentART20F=(float)Math.round((0.98*HV0338));

 splitData=currentART20F+currentART15_19F;
  if((splitData-HV0338)>2 ||(HV0338-splitData)>2 ){errorART++;}
  else{
 while(splitData<HV0338){ 
 currentART20F+=1;
 splitData++;
}

  splitData=currentART20F+currentART15_19F;
 while(splitData>HV0338){ 
 currentART20F-=1;
 splitData--;
}
  }
totalCurrentART=HV0338+HV0336+HV0337+HV0335;
  //    VALUES
    
        newART1M=(float)Math.round((0.034*HV0321));
        newART1_4M=(float)Math.round((0.214*HV0321));
        newART5_9M=(float)Math.round((0.37*HV0321));
        newART10_14M=(float)Math.round((0.382*HV0321));
        
splitData=newART10_14M+newART5_9M+newART1_4M+newART1M;
adderPos=0;
 if((splitData-HV0321)>2 || (HV0321-splitData)>2 ){errorART++;}
 else{
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
 }
        
        newART15_19M=(float)Math.round((0.008*HV0323));
        newART20_24M=(float)Math.round((0.008*HV0323));
        newART25_49M=(float)Math.round((0.775*HV0323));
        newART50M=(float)Math.round((0.209*HV0323));
        
        splitData=newART25_49M+newART50M+newART20_24M+newART15_19M;
         if((splitData-HV0323)>2 ||(HV0323-splitData)>2 ){errorART++;}
         else{
//   System.out.println("split data : "+splitData+" all data "+HV0323);     
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
         }
        newART1F=(float)Math.round((0.034*HV0322));
        newART1_4F=(float)Math.round((0.214*HV0322));
        newART5_9F=(float)Math.round((0.37*HV0322));
        newART10_14F=(float)Math.round((0.382*HV0322));
    
splitData=newART10_14F+newART5_9F+newART1_4F+newART1F;
adderPos=0;
 if((splitData-HV0322)>2 ||(HV0322-splitData)>2 ){errorART++;}
 else{
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
 }
        newART15_19F=(float)Math.round((0.008*HV0324));
        newART20_24F=(float)Math.round((0.008*HV0324));
        newART25_49F=(float)Math.round((0.775*HV0324));
        newART50F=(float)Math.round((0.209*HV0324));
      
    splitData=newART25_49F+newART50F+newART20_24F+newART15_19F;
adderPos=0;
 if((splitData-HV0324)>2 ||(HV0324-splitData)>2 ){errorART++;}
 else{
 
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
 }
        totalNewART=HV0324+HV0322+HV0323+HV0321;
//        System.out.println();
   //    VALUES
//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//     >>>>>>>>>>>>>>>>>>>>>>>>>>.CARE STARTS HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        newCARE1M=(float)Math.round((0.18*HV0309));
        newCARE1_4M=(float)Math.round((0.34*HV0309));
        newCARE5_9M=(float)Math.round((0.28*HV0309));
        newCARE10_14M=(float)Math.round((0.20*HV0309));
  
splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
 if((splitData-HV0309)>2 || (HV0309-splitData)>2 ){errorCARE++;}
 else{
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
 }
        newCARE15_19M=(float)Math.round((0.02*HV0311));
        newCARE20_24M=(float)Math.round((0.09*HV0311));
        newCARE25_49M=(float)Math.round((0.80*HV0311));
        newCARE50M=(float)Math.round((0.09*HV0311));
        
splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
 if((splitData-HV0311)>2 ||(HV0311-splitData)>2 ){errorCARE++;}
 else{
 
while(splitData<HV0311){ 
newCARE25_49M+=1; 
splitData++;
}

splitData=newCARE50M+newCARE25_49M+newCARE20_24M+newCARE15_19M;
while(splitData>HV0311){ 
newCARE25_49M-=1; 
splitData--;
}        
 }
 newCARE1F=(float)Math.round((0.18*HV0310));
        newCARE1_4F=(float)Math.round((0.34*HV0310));
        newCARE5_9F=(float)Math.round((0.28*HV0310));
        newCARE10_14F=(float)Math.round((0.20*HV0310));
        
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
 if((splitData-HV0310)>2 ||(HV0310-splitData)>2 ){errorCARE++;}
 else{
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
 }
        newCARE15_19F=(float)Math.round((0.02*HV0312));
        newCARE20_24F=(float)Math.round((0.09*HV0312));
        newCARE25_49F=(float)Math.round((0.80*HV0312));
        newCARE50F=(float)Math.round((0.09*HV0312));
       
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
 if((splitData-HV0312)>2 ||(HV0312-splitData)>2 ){errorCARE++;}
 else{
while(splitData<HV0312){ 
newCARE25_49F+=1; 
splitData++;
}
splitData=newCARE50F+newCARE25_49F+newCARE20_24F+newCARE15_19F;
while(splitData>HV0312){ 
newCARE25_49F-=1; 
splitData--;
}
 }
        totalNewCARE=HV0312+HV0310+HV0311+HV0309;
     //    VALUES
    
        currentCARE1M=(float)Math.round((0.03*HV0315));
        currentCARE1_4M=(float)Math.round((0.22*HV0315));
        currentCARE5_9M=(float)Math.round((0.37*HV0315));
        currentCARE10_14M=(float)Math.round((0.38*HV0315));

splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
 if((splitData-HV0315)>2 ||(HV0315-splitData)>2 ){errorCARE++;}
 else{
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
 }
        currentCARE15_19M=(float)Math.round((0.02*HV0317));
        currentCARE20_24M=(float)Math.round((0.09*HV0317));
        currentCARE25_49M=(float)Math.round((0.80*HV0317));
        currentCARE50M=(float)Math.round((0.09*HV0317));
        
splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
 if((splitData-HV0317)>2 ||(HV0317-splitData)>2 ){errorCARE++;}
 else{
while(splitData<HV0317){ 
currentCARE25_49M+=1; 
splitData++;
}
 splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
while(splitData>HV0317){ 
currentCARE25_49M-=1; 
splitData--;
} 
 }
        currentCARE1F=(float)Math.round((0.03*HV0316));
        currentCARE1_4F=(float)Math.round((0.22*HV0316));
        currentCARE5_9F=(float)Math.round((0.37*HV0316));
        currentCARE10_14F=(float)Math.round((0.38*HV0316));
        
splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
 if((splitData-HV0316)>2 ||(HV0316-splitData)>2 ){errorCARE++;}
 else{
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
 }
        currentCARE15_19F=(float)Math.round((0.02*HV0318));
        currentCARE20_24F=(float)Math.round((0.09*HV0318));
        currentCARE25_49F=(float)Math.round((0.80*HV0318));
        currentCARE50F=(float)Math.round((0.09*HV0318));

splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
 if((splitData-HV0318)>2 ||(HV0318-splitData)>2 ){errorCARE++;}
 else{
while(splitData<HV0318){ 
currentCARE25_49F+=1; 
splitData++;
}
 splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
while(splitData>HV0318){ 
currentCARE25_49F-=1; 
splitData--;
}
 }
       totalCurrentCARE=HV0318+HV0316+HV0317+HV0315;

       String dataART []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"+totalCurrentART+","
           + ""+currentART1F+","+currentART1_4F+","+currentART5_14F+","+currentART15_19F+","
           + ""+currentART20F+","+currentART1M+","+currentART1_4M+","+currentART5_14M+","
           + ""+currentART15_19M+","+currentART20M+","+totalNewART+","
           + ""+newART1F+","+newART1_4F+","+newART5_9F+","+newART10_14F+","+newART15_19F+","+newART20_24F+","
           + ""+newART25_49F+","+newART50F+","+newART1M+","+newART1_4M+","+newART5_9M+","+newART10_14M+","
           + ""+newART15_19M+","+newART20_24M+","+newART25_49M+","+newART50M+","+errorART+","+arthv+","+htchv+","+pmtcthv).split(",");
   
   String dataCARE []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"+totalCurrentCARE+","
           + ""+currentCARE1F+","+currentCARE1_4F+","+currentCARE5_9F+","+currentCARE10_14F+","+currentCARE15_19F+","
           + ""+currentCARE20_24F+","+currentCARE25_49F+","+currentCARE50F+","+currentCARE1M+","+currentCARE1_4M+","
           + ""+currentCARE5_9M+","+currentCARE10_14M+","+currentCARE15_19M+","+currentCARE20_24M+","
           + ""+currentCARE25_49M+","+currentCARE50M+","+totalNewCARE+","
           + ""+newCARE1F+","+newCARE1_4F+","+newCARE5_9F+","+newCARE10_14F+","+newCARE15_19F+","+newCARE20_24F+","
           + ""+newCARE25_49F+","+newCARE50F+","+newCARE1M+","+newCARE1_4M+","+newCARE5_9M+","+newCARE10_14M+","
           + ""+newCARE15_19M+","+newCARE20_24M+","+newCARE25_49M+","+newCARE50M+","+errorCARE+","+arthv+","+htchv+","+pmtcthv).split(",");
    
    artpos++;
    
      HSSFRow rw3shet1=shet1.createRow(artpos); 
       rw3shet1.setHeightInPoints(25);
       for(int positionART=0;positionART<dataART.length;positionART++){
       String value=dataART[positionART];
           c11=rw3shet1.createCell(positionART);
        if(positionART>4 && positionART<dataART.length-4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionART==5 || positionART==16){ c11.setCellStyle(styleHeader);}
          
          if(positionART==dataART.length-4){
       if(errorART>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
// System.out.println("art data length : "+dataART.length);
    
 HSSFRow rw3Shet2=shet2.createRow(artpos); 
       rw3Shet2.setHeightInPoints(25);
       for(int positionCARE=0;positionCARE<dataCARE.length;positionCARE++){
       String value=dataCARE[positionCARE];
           c11=rw3Shet2.createCell(positionCARE);
          if(positionCARE>4 && positionCARE<dataCARE.length-4 ){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
         if(positionCARE==5 || positionCARE==22){ c11.setCellStyle(styleHeader);}
       
         if(positionCARE==dataCARE.length-4){
       if(errorCARE>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
              }   
       // tb query 
        
 }
      
//  OUTPUT PMTCT DATA HERE +===========================================================================================    
  if(PMTCTSupport!=null){
      numerator=denominator=0;
     
      if(reportDuration.equals("4")){
      numerator=denominator=0;    
      }
      else{
      String getPMTCTFO="SELECT SUM(numerator),SUM(denominator) FROM pmtct_fo WHERE "+excelDuration+" SubPartnerID='"+facilityId+"' ";
      conn.rs1=conn.st1.executeQuery(getPMTCTFO);
      if(conn.rs1.next()==true){
          numerator=conn.rs1.getInt(1);
          denominator=conn.rs1.getInt(2);
      }
      }
      
 //        PMTCT_FO===================================================================================================
     PMTCT_FO_I_N=(double) numerator;
     PMTCT_FO_I_D=(double) denominator;
     PMTCT_FO_I_LINKED=(double)Math.round((0.05*numerator));
     PMTCT_FO_I_NOT_LINKED=0.0;
     PMTCT_FO_I_UNKNOWN=0.0;
     PMTCT_FO_U_NOT_BREASTFEEDING=(double)Math.round((0.83*numerator));
     PMTCT_FO_U_STILL_BREASTFEEDING=0.0;
     PMTCT_FO_U_BREASTFEEDING_UNKNOWN=0.0;
     PMTCT_FO_OTHER_INCARE=0.0;
     PMTCT_FO_OTHER_NOFOLLOWUP=(double)Math.round((0.08*numerator));
     PMTCT_FO_DIED=(double)Math.round((0.02*numerator));
     PMTCT_FO_TRANSFERRED=(double)Math.round((0.02*numerator));  
     
    double normalizer=PMTCT_FO_I_LINKED+PMTCT_FO_I_NOT_LINKED+PMTCT_FO_I_UNKNOWN+PMTCT_FO_U_NOT_BREASTFEEDING+
            PMTCT_FO_U_STILL_BREASTFEEDING+PMTCT_FO_U_BREASTFEEDING_UNKNOWN+PMTCT_FO_OTHER_INCARE+
            PMTCT_FO_OTHER_NOFOLLOWUP+PMTCT_FO_DIED+PMTCT_FO_TRANSFERRED;
     int pmtctnum=0;
     
     if((normalizer-numerator)>2 || (numerator-normalizer)>2 ){errorPMTCT++;}
     else{
     while(numerator>normalizer){
    PMTCT_FO_U_NOT_BREASTFEEDING++;
    normalizer++;
     }
     
     while(numerator<normalizer){
    PMTCT_FO_U_NOT_BREASTFEEDING--; 
    normalizer--;
     }
     }
//        PMTCT_ARV===================================================================================================
        
        
      PMTCT_ARV_N=(double) HV0217;
      PMTCT_ARV_D=(double) HV0209;
      PMTCT_ARV_LIFELONGART_NEW=(double)Math.round((0.75*HV0217));
      PMTCT_ARV_LIFELONGART_EXISTING=(double)Math.round((0.25*HV0217));
      
      normalizer=PMTCT_ARV_LIFELONGART_NEW+PMTCT_ARV_LIFELONGART_EXISTING;
      pmtctnum=0;
       if((normalizer-HV0217)>2 ||(HV0217-normalizer)>2 ){errorPMTCT++;}
       else{
      while(HV0217>normalizer){
          if(pmtctnum<3){
           PMTCT_ARV_LIFELONGART_NEW++;   
          }
          else{
          PMTCT_ARV_LIFELONGART_EXISTING++;    
          }
          if(pmtctnum==3){pmtctnum=0;}
          normalizer++;
         pmtctnum++; 
      }
      
      normalizer=PMTCT_ARV_LIFELONGART_NEW+PMTCT_ARV_LIFELONGART_EXISTING;
       pmtctnum=0;
      while(normalizer>HV0217){
          if(pmtctnum<3){
           PMTCT_ARV_LIFELONGART_NEW--;   
          }
          else{
          PMTCT_ARV_LIFELONGART_EXISTING--;    
          }
          if(pmtctnum==3){pmtctnum=0;}
          normalizer--;
         pmtctnum++; 
         
      }
     }
     
      PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV=0.0;
      PMTCT_ARV_MATERNAL_AZT=0.0;
      PMTCT_ARV_SINGLEDOSE=0.0;
      
//      PMTCT_EID===================================================================================================
 PMTCT_EID_N=(double)(HV0224+HV0225+HV0227);     
 PMTCT_EID_VIRO_2MONTHS=(double) (HV0224);    
 PMTCT_EID_VIRO_2_12MONTHS=(double) (HV0225+HV0227);     
 PMTCT_EID_P_VIRO_2MONTHS=(double)  (HV0229);    
 PMTCT_EID_P_VIRO_2_12MONTHS=(double) (HV0230+HV0231);     
  
//  PMTCT_STAT NUMERATOR======================================================================================================
      
 PMTCT_STATN_N=(double) HV0210;     
 PMTCT_STATN_KNOWNPOSTIVE=(double) HV0205;      
 PMTCTN_STAT_NEWPOSTIVE=(double) (HV0206+HV0207+HV0208);      
 PMTCT_STATD_D=(double) Math.round((1.03*HV0210));       
  
// PMTCT_CTX=====================================================================================================

         PMTCT_CTX=(double) HV0302; //Mo clarification whether to um or take most recent
       if(PMTCTSupport!=null){   
         if(reportDuration.equals("4")){
      numerator=denominator=0;    
     
         String dataPMTCT []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,,"
           + ",,,,,,,,,,,"
           + ""+PMTCT_ARV_N+","+PMTCT_ARV_D+","+PMTCT_ARV_LIFELONGART_NEW+","+PMTCT_ARV_LIFELONGART_EXISTING+","
           +PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV+","+PMTCT_ARV_MATERNAL_AZT+","
           + ""+PMTCT_ARV_SINGLEDOSE+","+PMTCT_EID_N+","+PMTCT_EID_VIRO_2MONTHS+","+PMTCT_EID_VIRO_2_12MONTHS+","
           +PMTCT_EID_P_VIRO_2MONTHS+","+PMTCT_EID_P_VIRO_2_12MONTHS+","
           + ""+PMTCT_STATN_N+","+PMTCT_STATN_KNOWNPOSTIVE+","+PMTCTN_STAT_NEWPOSTIVE+","
           + ""+PMTCT_STATD_D+","+PMTCT_CTX+","+errorPMTCT+","+arthv+","+htchv+","+pmtcthv).split(","); 
        HSSFRow rw3shetPMTCT=shetPMTCT.createRow(pmtctpos); 
       rw3shetPMTCT.setHeightInPoints(25);
       for(int positionPMTCT=0;positionPMTCT<dataPMTCT.length;positionPMTCT++){
       String value=dataPMTCT[positionPMTCT];
           c11=rw3shetPMTCT.createCell(positionPMTCT);
        if(positionPMTCT>16 &&positionPMTCT<(dataPMTCT.length-4)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if( positionPMTCT==17 || positionPMTCT==18 || positionPMTCT==24 || positionPMTCT==29 || positionPMTCT==32 || positionPMTCT==33){ c11.setCellStyle(styleHeader);}
            
       if(positionPMTCT==dataPMTCT.length-4){
       if(errorPMTCT>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }      
        pmtctpos++; 
          }
          else{
//        HAVE FORMULAS HERE AND THE OUTPUT FOR PMTCT   
   String dataPMTCT []=(countyName+","+districtName+","+facilityName+","+mflcode+","+PMTCTSupport+","+PMTCT_FO_I_N+","
           + ""+PMTCT_FO_I_D+","+PMTCT_FO_I_LINKED+","+PMTCT_FO_I_NOT_LINKED+","+PMTCT_FO_I_UNKNOWN+","
           + ""+PMTCT_FO_U_NOT_BREASTFEEDING+","+PMTCT_FO_U_STILL_BREASTFEEDING+","+PMTCT_FO_U_BREASTFEEDING_UNKNOWN+","
           +PMTCT_FO_OTHER_INCARE+","+PMTCT_FO_OTHER_NOFOLLOWUP+","+PMTCT_FO_DIED+","+PMTCT_FO_TRANSFERRED+","
           + ""+PMTCT_ARV_N+","+PMTCT_ARV_D+","+PMTCT_ARV_LIFELONGART_NEW+","+PMTCT_ARV_LIFELONGART_EXISTING+","
           +PMTCT_ARV_MATERNAL_TRIPLEDRUG_ARV+","+PMTCT_ARV_MATERNAL_AZT+","
           + ""+PMTCT_ARV_SINGLEDOSE+","+PMTCT_EID_N+","+PMTCT_EID_VIRO_2MONTHS+","+PMTCT_EID_VIRO_2_12MONTHS+","
           +PMTCT_EID_P_VIRO_2MONTHS+","+PMTCT_EID_P_VIRO_2_12MONTHS+","
           + ""+PMTCT_STATN_N+","+PMTCT_STATN_KNOWNPOSTIVE+","+PMTCTN_STAT_NEWPOSTIVE+","
           + ""+PMTCT_STATD_D+","+PMTCT_CTX+","+errorPMTCT+","+arthv+","+htchv+","+pmtcthv).split(",");   
          
      HSSFRow rw3shetPMTCT=shetPMTCT.createRow(pmtctpos); 
       rw3shetPMTCT.setHeightInPoints(25);
       for(int positionPMTCT=0;positionPMTCT<dataPMTCT.length;positionPMTCT++){
       String value=dataPMTCT[positionPMTCT];
           c11=rw3shetPMTCT.createCell(positionPMTCT);
        if(positionPMTCT>4 && positionPMTCT<(dataPMTCT.length-4)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionPMTCT==5 || positionPMTCT==6 || positionPMTCT==17 || positionPMTCT==18 || positionPMTCT==24 || positionPMTCT==29 || positionPMTCT==32 || positionPMTCT==33){ c11.setCellStyle(styleHeader);}
//          System.out.println("position "+positionPMTCT+" end v : "+dataPMTCT.length); 
       if(positionPMTCT==dataPMTCT.length-4){
//           System.out.println("entered here >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       if(errorPMTCT>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
       
        pmtctpos++;   
        
          }         
  }    
  }
    }
    
   //adding the skipped sites
    
    
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;   
        int artposcopy=artpos;
       artpos++;
 for(int a=0;a<art_staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shet1.createRow(artpos);  
 rwx.setHeightInPoints(23);  
 artpos++; 
 for(int z=0;z<art_blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   HSSFCell cellcounty=rwx.createCell(0); 
   cellcounty.setCellValue(art_staticcounty.get(a).toString());
   cellcounty.setCellStyle(stborder);
  }
  else if(z==1){
    //sub-county  
   HSSFCell cellsubcounty=rwx.createCell(1); 
   cellsubcounty.setCellValue(art_staticdistrict.get(a).toString());
   cellsubcounty.setCellStyle(stborder);
  }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(art_staticfacility.get(a).toString());
   cellfacil.setCellStyle(stborder);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(art_staticmfl.get(a).toString());
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(art_staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
		 else if(z==art_blankrows-4){
  //data status
   HSSFCell celldsd=rwx.createCell(art_blankrows-4); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }
                 	 else if(z==art_blankrows-3){
  //art high volume site
   HSSFCell celldsd=rwx.createCell(art_blankrows-3); 
   celldsd.setCellValue(art_staticart_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                         
                        else if(z==art_blankrows-2){
  //ht high volume site
   HSSFCell celldsd=rwx.createCell(art_blankrows-2); 
   celldsd.setCellValue(art_statichtc_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                        
                        else if(z==art_blankrows-1){
  //pmtct high volume site
   HSSFCell celldsd=rwx.createCell(art_blankrows-1); 
   celldsd.setCellValue(art_staticpmtct_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
                                                  }
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(stborder);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
 
 
 //===============================CARE RESULTS==============================
   //care and art share the same arraylist
      artposcopy++;
 for(int a=0;a<art_staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shet2.createRow(artposcopy);  
 rwx.setHeightInPoints(23);  
 artposcopy++;
 for(int z=0;z<care_blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   HSSFCell cellcounty=rwx.createCell(0); 
   cellcounty.setCellValue(art_staticcounty.get(a).toString());
   cellcounty.setCellStyle(stborder);
  }
  else if(z==1){
    //sub-county  
   HSSFCell cellsubcounty=rwx.createCell(1); 
   cellsubcounty.setCellValue(art_staticdistrict.get(a).toString());
   cellsubcounty.setCellStyle(stborder);
  }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(art_staticfacility.get(a).toString());
   cellfacil.setCellStyle(stborder);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(art_staticmfl.get(a).toString());
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(art_staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
  
 else if(z==care_blankrows-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(care_blankrows-4); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }
                 	 else if(z==care_blankrows-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(care_blankrows-3); 
   celldsd.setCellValue(art_staticart_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                         
                        else if(z==care_blankrows-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(care_blankrows-2); 
   celldsd.setCellValue(art_statichtc_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                        
                        else if(z==care_blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(care_blankrows-1); 
   celldsd.setCellValue(art_staticpmtct_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
                                                  }
  
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(stborder);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
      
 
 
 //pmtct============================
 
 
       //pmtctpos++;
 for(int a=0;a<pmtct_staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shetPMTCT.createRow(pmtctpos);  
 rwx.setHeightInPoints(23);  
 pmtctpos++; 
 for(int z=0;z<pmtct_blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   HSSFCell cellcounty=rwx.createCell(0); 
   cellcounty.setCellValue(pmtct_staticcounty.get(a).toString());
   cellcounty.setCellStyle(stborder);
  }
  else if(z==1){
    //sub-county  
   HSSFCell cellsubcounty=rwx.createCell(1); 
   cellsubcounty.setCellValue(pmtct_staticdistrict.get(a).toString());
   cellsubcounty.setCellStyle(stborder);
  }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(pmtct_staticfacility.get(a).toString());
   cellfacil.setCellStyle(stborder);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(pmtct_staticmfl.get(a).toString());
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(pmtct_staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
else if(z==pmtct_blankrows-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(pmtct_blankrows-4); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }
                 	 else if(z==pmtct_blankrows-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(pmtct_blankrows-3); 
   celldsd.setCellValue(pmtct_staticart_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                         
                        else if(z==pmtct_blankrows-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(pmtct_blankrows-2); 
   celldsd.setCellValue(pmtct_statichtc_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                        
                        else if(z==pmtct_blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(pmtct_blankrows-1); 
   celldsd.setCellValue(pmtct_staticpmtct_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
                                                  }
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(stborder);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
 
 
 
 
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
    
    
    
    
    
    
    
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

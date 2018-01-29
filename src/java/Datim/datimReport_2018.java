/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//last modified 26th April 2017 
package Datim;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
import org.apache.poi.ss.usermodel.Font;
/**
 *
 * @author Geofrey Nyabuto
 * This is the DATIM ART , CARE ,PMTCT
 */
public class datimReport_2018 extends HttpServlet {
double f_1,f_4,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50, m_1,m_4,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
double c_f_1,c_f_4,c_f_9,c_f_14,c_f_19,c_f_24,c_f_29,c_f_34,c_f_39,c_f_49,c_f_50, c_m_1,c_m_4,c_m_9,c_m_14,c_m_19,c_m_24,c_m_29,c_m_34,c_m_39,c_m_49,c_m_50;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
       dbConn conn = new dbConn();
       HSSFWorkbook wb=new HSSFWorkbook();
       
       if(1==1){
           
           HttpSession session;
int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
duration="";
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;
int HV0308,HV0309,HV0310,HV0311,HV0312,HV0320,HV0321,HV0322,HV0323,HV0324;
int HV0314,HV0315,HV0316,HV0317,HV0318,HV0334,HV0335,HV0336,HV0337,HV0338;
double currentART1M,currentART1_4M,currentART5_9M,currentART10_14M,currentART15_19M,currentART20_24M,currentART50M;
double currentART1F,currentART1_4F,currentART5_9F,currentART10_14F,currentART15_19F,currentART20_24F,currentART50F;

double newART25_29f,newART30_34f,newART35_39f,newART40_49f;
double currentART25_29f,currentART30_34f,currentART35_39f,currentART40_49f;
double newART25_29m,newART30_34m,newART35_39m,newART40_49m;
double currentART25_29m,currentART30_34m,currentART35_39m,currentART40_49m;
//double currentART1F,currentART1_4F,currentART5_14F,currentART15_19F,currentART20F;
double Pregnant,breastfeeding,ontbtreatment,newART1M,newART1_4M,newART5_9M,newART10_14M,newART15_19M,newART20_24M,newART50M;
double newART1F,newART1_4F,newART5_9F,newART10_14F,newART15_19F,newART20_24F,newART50F;
double newCARE1M,newCARE1_4M,newCARE5_9M,newCARE10_14M,newCARE15_19M,newCARE20_24M,newCARE25_49M,newCARE50M;
double newCARE1F,newCARE1_4F,newCARE5_9F,newCARE10_14F,newCARE15_19F,newCARE20_24F,newCARE25_49F,newCARE50F;
double currentCARE1M,currentCARE1_4M,currentCARE5_9M,currentCARE10_14M,currentCARE15_19M,currentCARE20_24M,currentCARE25_49M,currentCARE50M;
double currentCARE1F,currentCARE1_4F,currentCARE5_9F,currentCARE10_14F,currentCARE15_19F,currentCARE20_24F,currentCARE25_49F,currentCARE50F;
String createdOn,period;
int artpos,carepos,pmtctpos,tbpos,totalNewART,totalCurrentART,totalNewCARE,totalCurrentCARE=0; ;
String ARTSupport,PMTCTSupport,CARESuport;

int HV0210,HV0209,HV0205,HV0217,HV0216,HV0201;
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
String excelDuration="";
           
           session = request.getSession();
         ArrayList allFacilities = new ArrayList();
           
       allFacilities.clear();
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
//        ***************************************************************************************
//                ******************SERVLET FOR ART,CARE AND PMTCT**************************
//        ***************************************************************************************
       String headerCARE []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,NUMERATOR,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
       String headerPMTCT []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,HIV-infected:Linked to ART,HIV-infected: Not linked to ART,HIV-infected : Unknown link,HIV-uninfected:Not beastfeeding,HIV-uninfected: Still breastfeeeding,HIV-uninfected:Breastfeeding unknown,Other outcomes: In care but not test done, Other outcomes:Lost to follow up,Other outcomes : Died,Other outcomes:Transferred out,Numerator,Denominator,Life-long ART:New,Life-long ART: Already on treatment at the beginning of the current pregnancy,Maternal Triple-Drug ARV,Maternal AZT,Single-dose nevirapine(with or without tail),Numerator,Infants who received a virologic test within 2 months of birth, Infants who received their first virologic HIV test between 2 and 12 months of age,Infants with a postive virologic test results within 2 months of birth, Infants with a postive virologic test resultsbetween 2 and 12 months of age,Numerator,Known postive at entry,New postives,Denominator,Numerator,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
//       String headerTB[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+Y,Positive,Negative,Total PLVHIV enrolled in clinical care (HVO319),Ho of PLV in HIV clinical care screened for TB (HV0354),Female,Male, Screened for TB <15 Years,<1,1-4Y,5-9Y,10-14Y,Screened for TB >15 years,15-19Y,20+Y,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+,Verification Status".split(",") ;
      //updated 2017
        String headerART []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,Numerator,Pregnant,Breastfeeding,On TB treatment,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
        String headerART1 []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Paeds < 15 yrs,,,Adults 15 + yrs,,,,,,,Paeds < 15 yrs,,,Adults 15 + yrs,,,,,,,Numerator,Pregnant,Breastfeeding,On TB treatment,Paeds < 15 yrs,,,Adults 15 + yrs,,,,,,,Paeds < 15 yrs,,,Adults 15 + yrs,,,,,,,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
        String headerART2 []="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Female,,,,,,,,,,,Male,,,,,,,,,,,Numerator,Pregnant,Breastfeeding,On TB treatment,Female,,,,,,,,,,,Male,,,,,,,,,,,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
        String headerART3 []="County,Sub County,Health Facility,MFL Code,Type of support,Current On ART,,,,,,,,,,,,,,,,,,,,,,,New on ART,,,,,,,,,,,,,,,,,,,,,,,,,,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
      
       
      
        int [][] ARTmergeheaders ={{0,4,5,27,28,53,54,57},{0,4,6,16,17,27,32,42,43,53,54,57},{0,4,6,9,10,16,17,20,21,27,32,35,36,42,43,46,49,53}};
        
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
    String getDist="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
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
         String getCounty="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
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
    // for the red color
   HSSFCellStyle yellowstyle = wb.createCellStyle();
    yellowstyle.setFillForegroundColor(HSSFColor.YELLOW.index);
    yellowstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    yellowstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
    yellowstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    yellowstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    yellowstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
    yellowstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    yellowstyle.setWrapText(true);
    
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


   String mergeinfor[]={"1,1,0,4","1,1,5,19","1,1,20,37","1,4,38,38","1,4,39,39","1,4,40,40","1,4,41,41","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,2,6,12","2,2,13,19","3,3,6,8","3,3,9,12","3,3,13,15","3,3,16,19","2,4,20,20","2,4,21,21","2,4,22,22","2,4,23,23","2,2,24,30","2,2,31,37","3,3,24,26","3,3,27,30","3,3,31,33","3,3,34,37","1,4,38,38","1,4,39,39","1,4,40,40","1,4,41,41"};  
   
    //do the merging
    
//    for(int d=0;d<mergeinfor.length;d++){
//    if(!mergeinfor[d].equals("")){
//        String pos[]=mergeinfor[d].split(",");
//     shet1.addMergedRegion(new CellRangeAddress(new Integer(pos[0]),new Integer(pos[1]),new Integer(pos[2]),new Integer(pos[3])));   
//    }
//                                         }

//merge ART headers
for(int i=0;i<ARTmergeheaders.length;i++){
    int oneHeader[]=ARTmergeheaders[i];
    for(int j=0;j<oneHeader.length;j++){
        int start = oneHeader[j];
        int end = oneHeader[j+1];
        
       shet1.addMergedRegion(new CellRangeAddress(i+1,i+1,start,end)); 
        j++;
    }
}



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
  

  
    HSSFCell  c011;
  //headerARTar1
 for(int j=0;j<headerART3.length;j++){
        c011=rw00shet1.createCell(j);
         c011.setCellValue(headerART3[j]);
         c011.setCellStyle(styleHeader);
                                    }
 
 
   HSSFRow  rw00shet21=shet1.createRow(2);
   rw00shet21.setHeightInPoints(30);
 
    for(int j=0;j<headerART2.length;j++)
    {
         c011=rw00shet21.createCell(j);
         c011.setCellValue(headerART2[j]);
         c011.setCellStyle(styleHeader);
    }
 
 
    
    HSSFRow  rw00shet22=shet1.createRow(3);
   rw00shet22.setHeightInPoints(30);
 
    for(int j=0;j<headerART1.length;j++)
    {
         c011=rw00shet22.createCell(j);
         c011.setCellValue(headerART1[j]);
         c011.setCellStyle(styleHeader);
    }
    
 
    
    
    HSSFRow  rw00shet23=shet1.createRow(4);
   rw00shet23.setHeightInPoints(30);
 
    for(int j=0;j<headerART.length;j++)
    {
         c011=rw00shet23.createCell(j);
         c011.setCellValue(headerART[j]);
         c011.setCellStyle(styleHeader);
    }
    
 
 //_____________________________CARE_________________________
     
   HSSFRow  rw00shet2=shet2.createRow(1);
  rw00shet2.setHeightInPoints(30);
 
    for(int j=0;j<headerCARE.length;j++){
        c011=rw00shet2.createCell(j);
         c011.setCellStyle(styleHeader);
    }
    
    
    
    
   c011=rw00shet1.getCell(0);
   c011.setCellValue(period);
   
    
   
   c011=rw00shet2.getCell(0);
   c011.setCellValue(period);
   
   c011=rw00shet2.getCell(5);
   c011.setCellValue("CURRENTLY ON CARE");
   
   c011=rw00shet2.getCell(22);
   c011.setCellValue("NEW ON CARE");
shet2.addMergedRegion(new CellRangeAddress(1,1,5,21));
shet2.addMergedRegion(new CellRangeAddress(1,1,22,38));
  
  
  
   HSSFRow  rw0shet2=shet2.createRow(2);
  rw0shet2.setHeightInPoints(30);
  
    HSSFCell  c001;
  

     
    for(int j=0;j<headerCARE.length;j++){
        c001=rw0shet2.createCell(j);
         c001.setCellStyle(styleHeader);
    }
  
    
 
    
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
  
  
    

  
  HSSFRow  rw1shet2=shet2.createRow(3);
  rw1shet2.setHeightInPoints(30);
  
    HSSFCell  c01;
  

     
    for(int j=0;j<headerCARE.length;j++){
        c01=rw1shet2.createCell(j);
         c01.setCellStyle(styleHeader);
    }
   
 

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
  

    shet2.addMergedRegion(new CellRangeAddress(1,3,0,4));
  

  
  HSSFRow  rw2shet2=shet2.createRow(4);
  rw2shet2.setHeightInPoints(30);
  
    HSSFCell  c11;
   
   
    for(int headerpos=0;headerpos<headerCARE.length;headerpos++){
        String headerInfor=headerCARE[headerpos];
        c11=rw2shet2.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }
    
  
    shet2.addMergedRegion(new CellRangeAddress(2,4,5,5));
  
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
    
       artpos=tbpos=4;pmtctpos=3;
      totalNewART= totalCurrentART=totalNewCARE=totalCurrentCARE=0;      

      
      
      
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
    
    int art_blankrows=54;
    int care_blankrows=43;
    
   String getartstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where "+subcounty_countywhere+" ( ART='1') group by "+facilitiestable+".SubPartnerID   "; 
    
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
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where "+subcounty_countywhere+" ( PMTCT='1') group by "+facilitiestable+".SubPartnerID   "; 
    
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
      
      
    //
    
    double under1_newcare,under1_newtx,under1_curcare,under1_curtx;
    double under1_newcarem,under1_newtxm,under1_curcarem,under1_curtxm;
    double under1_newcaref,under1_newtxf,under1_curcaref,under1_curtxf;
    
     under1_newcare=under1_newtx=under1_curcare=under1_curtx=0;
     under1_newcarem=under1_newtxm=under1_curcarem=under1_curtxm=0;
     under1_newcaref=under1_newtxf=under1_curcaref=under1_curtxf=0;

    
       String getData=" SELECT "+facilitiestable+".SubPartnerNom,district.DistrictNom,county.County,"
            + ""+facilitiestable+".CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308) as HV0308,SUM(HV0309) as HV0309,SUM(HV0310) as HV0310,SUM(HV0311) as HV0311,SUM(HV0312) as HV0312,"
            + "SUM(HV0320) as HV0320 ,SUM(HV0321) as HV0321,SUM(HV0322) as HV0322,SUM(HV0323) as HV0323,SUM(HV0324) as HV0324,"
            + ""+facilitiestable+".SubPartnerID,"
            + "SUM(HV0205) as HV0205,SUM(HV0209)as HV0209,SUM(HV0210) as HV0210,SUM(HV0216) as HV0216,SUM(HV0217) as HV0217,"
            + "SUM(HV0224) as HV0224,SUM(HV0225) as HV0225,SUM(HV0227) as HV0227,SUM(HV0229) as HV0229,SUM(HV0230) as HV0230,SUM(HV0231) as HV0231,SUM(HV0232) as HV0232,"
            + "SUM(HV0302) as HV0302,SUM(HV0206) as HV0206,SUM(HV0207) as HV0207,SUM(HV0208) as HV0208"
            + ",SUM(HV0350) as HV0350,SUM(HV0351) as HV0351,SUM(HV0352) as HV0352,SUM(HV0353) as HV0353,SUM(HV0354) as HV0354,"
            + " SUM(HV0320) as under1_newtx,SUM(HV0308) as under1_newcare , "
            + " ART_highvolume, HTC_highvolume,PMTCT_highvolume,IFNULL(SUM(HV0326),0) as pregnant, SUM(HV0327) as tbtreatment, ROUND(SUM(HV0327)*0.1333) as breastfeeding,SUM(HV0201) as HV0201,  "
            + " IFNULL(f_1,0) AS f_1,IFNULL(f_4,0) AS f_4,IFNULL(f_9,0) AS f_9,IFNULL(f_14,0) AS f_14,IFNULL(f_19,0) AS f_19,IFNULL(f_24,0) AS f_24,IFNULL(f_29,0) f_29,IFNULL(f_34,0) f_34," +
                "IFNULL(f_39,0) AS f_39,IFNULL(f_49,0) f_49,IFNULL(f_50,0) f_50, IFNULL(m_1,0) AS m_1,IFNULL(m_4,0) AS m_4,IFNULL(m_9,0) AS m_9,IFNULL(m_14,0) AS m_14,IFNULL(m_19,0) AS m_19," +
                "IFNULL(m_24,0) AS m_24,IFNULL(m_29,0) m_29,IFNULL(m_34,0) AS m_34,IFNULL(m_39,0) AS m_39,IFNULL(m_49,0) AS m_49,IFNULL(m_50,0) AS m_50 "
            + " FROM moh731 "
            + " LEFT JOIN "+facilitiestable+" ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
            + " LEFT JOIN county ON district.CountyID=county.CountyID "
            + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "
            + " WHERE "
            + " "+facilityIds+" "+duration+"  "
            + " AND  indicator='TX_NEW'"
            + "GROUP BY moh731.SubPartnerID " ;
       
   System.out.println("new : "+getData);
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
        
HV0308=HV0309=HV0310=HV0311=HV0312=HV0320=HV0321=HV0322=HV0323=HV0324=HV0201=0;
HV0314=HV0315=HV0316=HV0317=HV0318=HV0334=HV0335=HV0336=HV0337=HV0338=0;
currentART1M=currentART1_4M=currentART5_9M=currentART10_14M=currentART15_19M=currentART20_24M=currentART50M=0;
currentART1F=currentART1_4F=currentART5_9F=currentART10_14F=currentART15_19F=currentART20_24F=currentART50F=0;
newART1M=newART1_4M=newART5_9M=newART10_14M=newART15_19M=newART20_24M=newART50M=Pregnant=breastfeeding=ontbtreatment=0;
newART1F=newART1_4F=newART5_9F=newART10_14F=newART15_19F=newART20_24F=newART50F=0;

//ADDED ON 2018 FY
newART25_29f=newART30_34f=newART35_39f=newART40_49f=0;
currentART25_29f=currentART30_34f=currentART35_39f=currentART40_49f=0;
newART25_29m=newART30_34m=newART35_39m=newART40_49m=0;
currentART25_29m=currentART30_34m=currentART35_39m=currentART40_49m=0;

//END

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



        facilityName = conn.rs.getString(1);
        districtName = conn.rs.getString(2);
        countyName = conn.rs.getString(3);
        mflcode = conn.rs.getString(4);
        ARTSupport = conn.rs.getString(5);
//CARESuport=conn.rs.getString(6);
        PMTCTSupport = conn.rs.getString(6);
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
        
    f_1 = conn.rs.getDouble("f_1");
    f_4 = conn.rs.getDouble("f_4");
    f_9 = conn.rs.getDouble("f_9");
    f_14 = conn.rs.getDouble("f_14");
    f_19 = conn.rs.getDouble("f_19");
    f_24 = conn.rs.getDouble("f_24");
    f_29 = conn.rs.getDouble("f_29");
    f_34 = conn.rs.getDouble("f_34");
    f_39 = conn.rs.getDouble("f_39");
    f_49 = conn.rs.getDouble("f_49");
    f_50 = conn.rs.getDouble("f_50");
    m_1 = conn.rs.getDouble("m_1");
    m_4 = conn.rs.getDouble("m_4");
    m_9 = conn.rs.getDouble("m_9");
    m_14 = conn.rs.getDouble("m_14");
    m_19 = conn.rs.getDouble("m_19");
    m_24 = conn.rs.getDouble("m_24");
    m_29 = conn.rs.getDouble("m_29");
    m_34 = conn.rs.getDouble("m_34");
    m_39 = conn.rs.getDouble("m_39");
    m_49 = conn.rs.getDouble("m_49");
    m_50 = conn.rs.getDouble("m_50"); 
    
      Pregnant=conn.rs.getInt("pregnant");
      //breastfeeding=(float)Math.round((0.1333*Pregnant));
      breastfeeding=0;
      ontbtreatment=conn.rs.getInt("tbtreatment");
    
      
      double under1txratio_male = 0.5;
      double under1txratio_female=0.5;
      
      
        double under1careratio_male = 0.5;
      double under1careratio_female=0.5;
      //if under15 male is >= under15 female, give male 0.6
      //otherwise give female 0.6
      
       if(HV0321>=HV0322){
      
           if(HV0322==0){
               //if under 15 for females is 0, the take 100% to male
             under1txratio_male=1;
      under1txratio_female=0;
           }
           else {
               
           
      under1txratio_male=0.6;
      under1txratio_female=0.4;
           }
      }
      else   if(HV0321<HV0322){ 
        if(HV0321==0){
               //if under 15 for females is 0, the take 100% to male
             under1txratio_female=1;
      under1txratio_male=0;
           }
          
          else {         
      under1txratio_female=0.6;
      under1txratio_male=0.4;
      }
      }
      
     
       
       //care
       
       
      if(HV0309>=HV0310){
          
          
          if(HV0310==0){
               //if under 15 for females is 0, the take 100% to male
             under1careratio_male=1;
      under1careratio_female=0;
           }
           else {
      
      under1careratio_male=0.6;
      under1careratio_female=0.4;
          }
      }
      else   if(HV0309<HV0310){ 
      
           if(HV0309==0){
               //if under 15 for females is 0, the take 100% to male
      under1careratio_female=1;
      under1careratio_male=0;
           }
           else {
          
          
        under1careratio_male=0.4;
      under1careratio_female=0.6;
      }
      
      }
      
     under1_newtx=conn.rs.getInt("under1_newtx");
     
     under1_newtxm=(float)Math.round((m_1*under1_newtx));   
     under1_newtxf=under1_newtx-under1_newtxm;
     
     
     under1_newcare=conn.rs.getInt("under1_newcare");   
     under1_newcarem=(float)Math.round((under1careratio_male*under1_newcare));   
     under1_newcaref=under1_newcare-under1_newcarem;
     System.out.println("Total "+under1_newcare+" = "+under1_newcarem+" + "+under1_newcaref);
     
//   HV0302=0;
     String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE moh731.SubPartnerID='"+facilityId+"' && "+duration ;
    conn.rs2=conn.st2.executeQuery(getMaxYearMonth);
    if(conn.rs2.next()==true){
        maxYearMonth=conn.rs2.getInt(1);
    }
        
     String getCurrent="SELECT HV0314,HV0315,HV0316,HV0317,HV0318,"
    + "HV0334,HV0335,HV0336,HV0337,HV0338,HV0302,HV0319, HV0314 as under1_curcare,HV0334 as under1_curtx,"
    + " IFNULL(f_1,0) AS f_1,IFNULL(f_4,0) AS f_4,IFNULL(f_9,0) AS f_9,IFNULL(f_14,0) AS f_14,IFNULL(f_19,0) AS f_19,IFNULL(f_24,0) AS f_24,IFNULL(f_29,0) f_29,IFNULL(f_34,0) f_34," +
    "IFNULL(f_39,0) AS f_39,IFNULL(f_49,0) f_49,IFNULL(f_50,0) f_50, IFNULL(m_1,0) AS m_1,IFNULL(m_4,0) AS m_4,IFNULL(m_9,0) AS m_9,IFNULL(m_14,0) AS m_14,IFNULL(m_19,0) AS m_19," +
    "IFNULL(m_24,0) AS m_24,IFNULL(m_29,0) m_29,IFNULL(m_34,0) AS m_34,IFNULL(m_39,0) AS m_39,IFNULL(m_49,0) AS m_49,IFNULL(m_50,0) AS m_50 "
    + " FROM moh731 "
    + " LEFT JOIN "+facilitiestable+" ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
    + " LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
    + " LEFT JOIN county ON district.CountyID=county.CountyID "
    + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "
    + " WHERE "
    + " moh731.SubPartnerID='"+facilityId+"' && yearmonth='"+maxYearMonth+"' "
    + " AND  indicator='TX_CURR'";
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
     
    c_f_1 = conn.rs1.getDouble("f_1");
    c_f_4 = conn.rs1.getDouble("f_4");
    c_f_9 = conn.rs1.getDouble("f_9");
    c_f_14 = conn.rs1.getDouble("f_14");
    c_f_19 = conn.rs1.getDouble("f_19");
    c_f_24 = conn.rs1.getDouble("f_24");
    c_f_29 = conn.rs1.getDouble("f_29");
    c_f_34 = conn.rs1.getDouble("f_34");
    c_f_39 = conn.rs1.getDouble("f_39");
    c_f_49 = conn.rs1.getDouble("f_49");
    c_f_50 = conn.rs1.getDouble("f_50");
    c_m_1 = conn.rs1.getDouble("m_1");
    c_m_4 = conn.rs1.getDouble("m_4");
    c_m_9 = conn.rs1.getDouble("m_9");
    c_m_14 = conn.rs1.getDouble("m_14");
    c_m_19 = conn.rs1.getDouble("m_19");
    c_m_24 = conn.rs1.getDouble("m_24");
    c_m_29 = conn.rs1.getDouble("m_29");
    c_m_34 = conn.rs1.getDouble("m_34");
    c_m_39 = conn.rs1.getDouble("m_39");
    c_m_49 = conn.rs1.getDouble("m_49");
    c_m_50 = conn.rs1.getDouble("m_50"); 
        
        
        under1_curcare=conn.rs1.getInt("under1_curcare");
        under1_curtx=conn.rs1.getInt("under1_curtx");
     
  
        
     double malepercentagecare=0.5;
            
            if(HV0316>HV0315){
           
            if(HV0315==0){
             malepercentagecare=0;
            }
            else {
            
             malepercentagecare=0.4;
            }
            }
            else if(HV0316<HV0315) {
           
            
            if(HV0316==0){
             malepercentagecare=1;
            }
            else {            
             malepercentagecare=0.6;
            }
            
            
            }
            else {
            malepercentagecare=0.5;
            }
        
            System.out.println("Facility ni: "+facilityName+"  "+malepercentagecare+" _ Male:"+HV0315+"Female: "+HV0316);
            
     under1_curcarem=(float)Math.round((malepercentagecare*under1_curcare));   
     under1_curcaref=under1_curcare-under1_curcarem;
     System.out.println("Total cur care "+under1_curcare+" = "+under1_curcarem+" + "+under1_curcaref); 
       
            
      under1_curtxm=(float)Math.round((c_m_1*under1_curtx));   
     under1_curtxf=under1_curtx-under1_curtxm;
     System.out.println(facilityName+" Total Tx "+under1_curtx+" = "+under1_curtxm+" + "+under1_curtxf);

     }
     
      if(ARTSupport!=null){
  double splitData; int adderPos=0;
// .>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
// >>>>>>>>>>>>>>>>>>>>>>ART STARTS HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
  
  
  //new variables for ART inroduced as shown below
  
  //currentART
  //currentART
 
  
  //sometimes you may distribute under 1 and end up having a number bigger on under 1 than no for < 15. This leads to a negative number
  //to avoid that, first check if below 15 per gender is less than under15/2 for that gender
  //if yes, then deduct the difference between <15 and <1 from <1 and transfer it to the other gender of <1
  
  
  
   int _HV0335=HV0335;
//    VALUES FOR CURRENT ON ART

 currentART1M=under1_curtxm;

 if(_HV0335>=under1_curtxm){     
 _HV0335=(int) (_HV0335-under1_curtxm); 
     System.out.println(facilityName+" under1 m :"+under1_curtxm+" HV0335 "+HV0335+" _HV0335 "+_HV0335);
 }
 else {
     System.out.println(facilityName+" Should have multiplied but dint ");
      }
 currentART1_4M=(float)Math.round((c_m_4*_HV0335));
 currentART5_9M=(float)Math.round((c_m_9*_HV0335));
 currentART10_14M=(float)Math.round((c_m_14*_HV0335));
 
 
  splitData=currentART1M+currentART1_4M+currentART5_9M+currentART10_14M;
  adderPos=0;
 if((splitData-HV0335)>10 ||(HV0335-splitData)>10 ){errorART++;}
 else{
while(splitData<HV0335){ 
 if(adderPos<2){
  currentART10_14M+=1;   
 }
 else{
 currentART5_9M+=1;    
 }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}

  splitData=currentART1M+currentART1_4M+currentART5_9M+currentART10_14M;
  adderPos=0;
  
while(splitData>HV0335){ 
 if(adderPos<2){
  currentART10_14M-=1;   
 }
 else{
 currentART5_9M-=1;    
 }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(splitData==HV0335){}
}
 }
 
 currentART15_19M=(float)Math.round((c_m_19*HV0337));
 currentART20_24M=(float)Math.round((c_m_24*HV0337));
currentART25_29m=(float)Math.round((c_m_29*HV0337));
currentART30_34m=(float)Math.round((c_m_34*HV0337));
currentART35_39m=(float)Math.round((c_m_39*HV0337));
currentART40_49m=(float)Math.round((c_m_49*HV0337));

 currentART50M=(float)Math.round((c_m_50*HV0337));
 
  //add values for  new and current on ART

//end of added new values


 splitData=currentART15_19M+currentART20_24M+currentART25_29m+currentART30_34m+currentART35_39m+currentART40_49m+currentART50M;
  if((splitData-HV0337)>10 ||(HV0337-splitData)>10 ){errorART++;}
  else{
 while(splitData<HV0337){ 
 currentART40_49m+=1; 
 splitData++;
}
 
 splitData=currentART15_19M+currentART20_24M+currentART25_29m+currentART30_34m+currentART35_39m+currentART40_49m+currentART50M;
 while(splitData>HV0337){ 
 currentART40_49m-=1; 
 splitData--;
}
}
currentART1F=under1_curtxf;  //NEED CLARIFICATION

int _HV0336=HV0336;
 if(_HV0336>=under1_curtxf){     
 _HV0336=(int) (_HV0336-under1_curtxf);
 System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
     System.out.println("Should have multiplied but dint ");
      }

currentART1_4F=(float)Math.round((c_f_4*_HV0336));
currentART5_9F=(float)Math.round((c_f_9*_HV0336));
currentART10_14F=(float)Math.round((c_f_14*_HV0336));



  splitData=currentART10_14F+currentART1_4F+currentART5_9F+currentART1F;
  adderPos=0;
   if((splitData-HV0336)>10 ||(HV0336-splitData)>10 ){errorART++;}
   else{
while(splitData<HV0336){ 
 if(adderPos<2){currentART10_14F+=1; }
 else{currentART5_9F+=1; }
splitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}

splitData=currentART10_14F+currentART1_4F+currentART5_9F+currentART1F;
  adderPos=0;
  
while(splitData>HV0336){ 
 if(adderPos<2){currentART10_14F-=1; }
 else{currentART5_9F-=1; }
splitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}
   }
   

   
currentART15_19F=(float)Math.round((c_f_19*HV0338));
currentART20_24F=(float)Math.round((c_f_24*HV0338));
currentART25_29f=(float)Math.round((c_f_29*HV0338));
currentART30_34f=(float)Math.round((c_f_34*HV0338));
currentART35_39f=(float)Math.round((c_f_39*HV0338));
currentART40_49f=(float)Math.round((c_f_49*HV0338));
currentART50F=(float)Math.round((c_f_50*HV0338));

 
 splitData=currentART15_19F+currentART20_24F+currentART25_29f+currentART30_34f+currentART35_39f+currentART40_49f+currentART50F;
  if((splitData-HV0338)>10 ||(HV0338-splitData)>10 ){errorART++;}
  else{
 while(splitData<HV0338){ 
 currentART20_24F+=1;
 splitData++;
}

  splitData=currentART15_19F+currentART20_24F+currentART25_29f+currentART30_34f+currentART35_39f+currentART40_49f+currentART50F;
 while(splitData>HV0338){ 
 currentART20_24F-=1;
 splitData--;
}
  }
totalCurrentART=HV0338+HV0336+HV0337+HV0335;
  //    VALUES
    
        newART1M=under1_newtxm;
        newART1_4M=(float)Math.round((m_4*HV0321));
        newART5_9M=(float)Math.round((m_9*HV0321));
        newART10_14M=(float)Math.round((m_14*HV0321));
       
        if(newART1_4M>=under1_newtxm){
 newART1_4M=newART1_4M-under1_newtxm;
    }
 else {
     System.out.println("Should have multiplied but dint ");
 }
        
splitData=newART10_14M+newART1_4M+newART5_9M+newART1M;
adderPos=0;
 if((splitData-HV0321)>10 || (HV0321-splitData)>10 ){errorART++;}
 else{
while(splitData<HV0321){ 
 if(adderPos==0){newART10_14M+=1; splitData++;}
 else if(adderPos==1){newART5_9M+=1; splitData++; }
 else{newART1_4M+=1; splitData++;}
 
adderPos++  ;
 if(adderPos>2){adderPos=0;}
}
 
splitData=newART10_14M+newART1_4M+newART5_9M+newART1M;
adderPos=0;
while(splitData>HV0321){
 if(newART10_14M==0 && newART5_9M==0 && newART1_4M==0){
     if(HV0320<=(HV0321+HV0322)){
     newART1M--; 
     newART1F++;
        if(newART1_4F!=0){
        newART1_4F--; 
        }
        else if(newART5_9F!=0){
        newART5_9F--; 
        }
        else if(newART10_14F!=0){
        newART10_14F--; 
        }
 splitData--;  
 adderPos--;
     }
     else{
         errorART  = 900000;
         break;
     }
 }
 else if(adderPos==0){if(newART10_14M>0){newART10_14M-=1; splitData--;} }
 else if(adderPos==1){ if(newART5_9M>0){newART5_9M-=1; splitData--;} }
 else{if(newART1_4M>0){newART1_4M-=1; splitData--;} }
 
adderPos++;
 if(adderPos>2){adderPos=0;}
}
 }
      
        newART15_19M=(float)Math.round((m_19*HV0323));
        newART20_24M=(float)Math.round((m_24*HV0323));
        newART25_29m=(float)Math.round((m_29*HV0323));
        newART30_34m=(float)Math.round((m_34*HV0323));
        newART35_39m=(float)Math.round((m_39*HV0323));
        newART40_49m=(float)Math.round((m_49*HV0323));
        newART50M=(float)Math.round((m_50*HV0323));
        
        
        
        splitData=newART50M+newART20_24M+newART15_19M+newART25_29m+newART30_34m+newART35_39m+newART40_49m;
         if((splitData-HV0323)>10 ||(HV0323-splitData)>10 ){errorART++;}
         else{
//   System.out.println("split data : "+splitData+" all data "+HV0323);     
adderPos=0;
while(splitData<HV0323){ 
 if(adderPos==0 || adderPos==3){newART40_49m+=1; splitData++; }
 if(adderPos==1 || adderPos==4){newART50M+=1; splitData++; }
 if(adderPos==2){newART35_39m+=1; splitData++; }
 if(adderPos==5){newART15_19M+=1; splitData++; }
 if(adderPos==6){newART30_34m+=1; splitData++; }
 if(adderPos==7){newART25_29m+=1; splitData++; }
 if(adderPos==8){newART20_24M+=1; splitData++; }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
}
  splitData=newART50M+newART20_24M+newART15_19M+newART25_29m+newART30_34m+newART35_39m+newART40_49m;
//   System.out.println("split data : "+splitData+" all data"+HV0323);     
adderPos=0;
while(splitData>HV0323){ 
 if(adderPos==0 || adderPos==3){if(newART40_49m>0){newART40_49m-=1; splitData--;} }
 if(adderPos==1 || adderPos==4){if(newART50M>0){newART50M-=1; splitData--;} }
 if(adderPos==2){if(newART35_39m>0){newART35_39m-=1; splitData--;} }
 if(adderPos==5){if(newART15_19M>0){newART15_19M-=1; splitData--;} }
  if(adderPos==6){if(newART30_34m>0){newART30_34m-=1; splitData--;} }
 if(adderPos==7){if(newART25_29m>0){newART25_29m-=1; splitData--;} }
 if(adderPos==8){if(newART20_24M>0){newART20_24M-=1; splitData--;} }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
}   }
        newART1F=under1_newtxf;
        newART1_4F=(float)Math.round((f_4*HV0322));
        newART5_9F=(float)Math.round((f_9*HV0322));
        newART10_14F=(float)Math.round((f_14*HV0322));
        
   if(newART1_4F>=under1_newtxf){
 newART1_4F=newART1_4F-under1_newtxf;
     }
 else {
     System.out.println("Should have deducted newtx but dint "+facilityName+" "+newART1_4F+" -- "+under1_newtxf);
      }
    
splitData=newART10_14F+newART1_4F+newART5_9F+newART1F;
adderPos=0;
 if((splitData-HV0322)>10 ||(HV0322-splitData)>10 ){errorART++;}
// else{
while(splitData<HV0322){ 
 if(adderPos==0){newART10_14F+=1; splitData++; }
 else if(adderPos==1){newART5_9F+=1; splitData++; }
 else{newART1_4F+=1; splitData++; }
adderPos++  ;
 if(adderPos>2){adderPos=0;}
    System.out.println("looping on HV0322+");
}   
  
splitData=newART10_14F+newART1_4F+newART5_9F+newART1F;
adderPos=0;
while(splitData>HV0322){ 
    if(newART10_14F==0 && newART5_9F==0 && newART1_4F==0){
        
        if(HV0320<=(HV0321+HV0322)){
        newART1F--; 
        newART1M++; 
        if(newART1_4M!=0){
        newART1_4M--; 
        }
        else if(newART5_9M!=0){
        newART5_9M--; 
        }
        else if(newART10_14M!=0){
        newART10_14M--; 
        }
        splitData--; 
        adderPos--;
        }
        else{
            errorART = 900000;
         break;
     }
        }
    else if(adderPos==0){if(newART10_14F>0){newART10_14F-=1; splitData--;} }
 else if(adderPos==1){ if(newART5_9F>0){newART5_9F-=1; splitData--;} }
 else{if(newART1_4F>0){newART1_4F-=1; splitData--;} }
adderPos++  ;
 if(adderPos>2){adderPos=0;}
     System.out.println("looping on HV0322- split data"+splitData+" HV0322 : "+HV0322+" "+newART1_4F+" "+newART5_9F+" "+newART10_14F);
}
 //}
  
//  NORMALIZE ROUND 2




//END OF ROUND 2
        newART15_19F=(float)Math.round((f_19*HV0324));
        newART20_24F=(float)Math.round((f_24*HV0324));
        newART25_29f=(float)Math.round((f_29*HV0324));
        newART30_34f=(float)Math.round((f_34*HV0324));
        newART35_39f=(float)Math.round((f_39*HV0324));
        newART40_49f=(float)Math.round((f_49*HV0324));
        newART50F=(float)Math.round((f_50*HV0324));


    splitData=newART25_29f+newART30_34f+newART35_39f+newART40_49f+newART50F+newART20_24F+newART15_19F;
adderPos=0;
 if((splitData-HV0324)>10 ||(HV0324-splitData)>10 ){errorART++;}
 else{
 
while(splitData<HV0324){ 
 if(adderPos==0 || adderPos==3){newART40_49f+=1; splitData++; }
 if(adderPos==1 || adderPos==4){newART50F+=1; splitData++; }
 if(adderPos==2){newART35_39f+=1; splitData++; }
 if(adderPos==5){newART15_19F+=1; splitData++; }
  if(adderPos==6){newART30_34f+=1; splitData++; }
 if(adderPos==7){newART25_29f+=1; splitData++; }
 if(adderPos==8){newART20_24F+=1; splitData++; }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
     System.out.println("looping on HV0324+");
}     
     splitData=newART25_29f+newART30_34f+newART35_39f+newART40_49f+newART50F+newART20_24F+newART15_19F;
adderPos=0;
while(splitData>HV0324){ 
 if(adderPos==0 || adderPos==3){if(newART40_49f>0){newART40_49f-=1; splitData--;} }
 if(adderPos==1 || adderPos==4){if(newART50F>0){newART50F-=1; splitData--;} }
 if(adderPos==2){if(newART35_39f>0){newART35_39f-=1; splitData--;} }
 if(adderPos==5){if(newART15_19F>0){newART15_19F-=1; splitData--;} }
 if(adderPos==6){if(newART30_34f>0){newART30_34f-=1; splitData--;} }
 if(adderPos==7){if(newART25_29f>0){newART25_29f-=1; splitData--;} }
 if(adderPos==8){if(newART20_24F>0){newART20_24F-=1; splitData--;} }
adderPos++  ;
 if(adderPos>8){adderPos=0;}
     System.out.println("looping on HV0324-");
}
 }
        totalNewART=HV0324+HV0322+HV0323+HV0321;
//        System.out.println();
   //    VALUES
//  >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
//     >>>>>>>>>>>>>>>>>>>>>>>>>>.CARE STARTS HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
        
        newCARE1M=under1_newcarem;
         int _HV0309=HV0309;
 if(_HV0309>=under1_newcarem){     
 _HV0309=(int) (_HV0309-under1_newcarem); 
     System.out.println(facilityName+" under1 care m :"+under1_newcarem+" HV0309 "+HV0309+" _HV0309 "+_HV0309);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
     System.out.println("Should have multiplied but dint ");
      }
        
        newCARE1_4M=((float)Math.round((0.52*_HV0309)) );
        newCARE5_9M=(float)Math.round((0.28*_HV0309));
        newCARE10_14M=(float)Math.round((0.20*_HV0309));
        
 
  
splitData=newCARE10_14M+newCARE5_9M+newCARE1_4M+newCARE1M;
adderPos=0;
 if((splitData-HV0309)>10 || (HV0309-splitData)>10 ){errorCARE++;}
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
 if((splitData-HV0311)>10 ||(HV0311-splitData)>10 ){errorCARE++;}
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
       newCARE1F=under1_newcaref;
       
        int _HV0310=HV0310;
 if(_HV0310>=under1_newcaref){     
 _HV0310=(int) (_HV0310-under1_newcaref); 
     System.out.println(facilityName+" under1 care f :"+under1_newcaref+" HV0310 "+HV0310+" _HV0310 "+_HV0310);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
     System.out.println("Should have multiplied but dint ");
      }
       
        newCARE1_4F=(float)Math.round((0.52*_HV0310));
        newCARE5_9F=(float)Math.round((0.28*_HV0310));
        newCARE10_14F=(float)Math.round((0.20*_HV0310));
        
  
        
splitData=newCARE10_14F+newCARE5_9F+newCARE1_4F+newCARE1F;
adderPos=0;
 if((splitData-HV0310)>10 ||(HV0310-splitData)>10 ){errorCARE++;}
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
 if((splitData-HV0312)>10 ||(HV0312-splitData)>10 ){errorCARE++;}
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
    
        currentCARE1M=under1_curcarem;
        
        int _HV0315=HV0315;
 if(_HV0315>=under1_curcarem){  
     
 _HV0315=(int) (_HV0315-under1_curcarem); 
     System.out.println(facilityName+" under1 cur care m :"+under1_curcarem+" HV0315 "+HV0315+" _HV0315 "+_HV0315);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
     
 }
 else {
     System.out.println("Should have subtracted curcare but dint "+facilityName+" "+under1_curcarem+" "+HV0315);
      }
        
        currentCARE1_4M=(float)Math.round((0.17*_HV0315));
        currentCARE5_9M=(float)Math.round((0.26*_HV0315));
        currentCARE10_14M=(float)Math.round((0.57*_HV0315));
        
  

splitData=currentCARE10_14M+currentCARE5_9M+currentCARE1_4M+currentCARE1M;
adderPos=0;
 if((splitData-HV0315)>10 ||(HV0315-splitData)>10 ){errorCARE++;}
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
 
 
        currentCARE15_19M=(float)Math.round((0.05*HV0317));
        currentCARE20_24M=(float)Math.round((0.04*HV0317));
        currentCARE25_49M=(float)Math.round((0.73*HV0317));
        currentCARE50M=(float)Math.round((0.18*HV0317));
        
splitData=currentCARE50M+currentCARE25_49M+currentCARE20_24M+currentCARE15_19M;
 if((splitData-HV0317)>10 ||(HV0317-splitData)>10 ){errorCARE++;}
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
        currentCARE1F=under1_curcaref;
           int _HV0316=HV0316;
 if(_HV0316>=under1_curcaref){     
 _HV0316=(int) (_HV0316-under1_curcaref); 
     System.out.println(facilityName+" under1 cur care f :"+under1_curcaref+" HV0316 "+HV0316+" _HV0316 "+_HV0316);
     //System.out.println(facilityName+" under1 f :"+under1_curtxf+" HV0336 "+HV0336+" _HV0336 "+_HV0336);
 }
 else {
     System.out.println("Should have multiplied but dint ");
      }
        
        currentCARE1_4F=(float)Math.round((0.172*_HV0316));
        currentCARE5_9F=(float)Math.round((0.2545*_HV0316));
        currentCARE10_14F=(float)Math.round((0.5735*_HV0316));
        
  
        
splitData=currentCARE10_14F+currentCARE5_9F+currentCARE1_4F+currentCARE1F;
 if((splitData-HV0316)>10 ||(HV0316-splitData)>10 ){errorCARE++;}
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
 
 
        currentCARE15_19F=(float)Math.round((0.03*HV0318));
        currentCARE20_24F=(float)Math.round((0.07*HV0318));
        currentCARE25_49F=(float)Math.round((0.78*HV0318));
        currentCARE50F=(float)Math.round((0.12*HV0318));

splitData=currentCARE50F+currentCARE25_49F+currentCARE20_24F+currentCARE15_19F;
 if((splitData-HV0318)>10 ||(HV0318-splitData)>10 ){errorCARE++;}
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

   if(
           newART1F>currentART1F || newART1M>currentART1M || newART1_4F>currentART1_4F || newART1_4M>currentART1_4M ||
           newART5_9F>currentART5_9F || newART5_9M>currentART5_9M || newART10_14F>currentART10_14F || newART10_14M>currentART10_14M ||
           newART15_19F>currentART15_19F || newART15_19M>currentART15_19M || newART20_24F>currentART20_24F || newART20_24M>currentART20_24M ||
           newART25_29f>currentART25_29f || newART25_29m>currentART25_29m || newART30_34f>currentART30_34f || newART30_34m>currentART30_34m ||
           newART35_39f>currentART35_39f || newART35_39m>currentART35_39m || newART40_49f>currentART40_49f || newART40_49m>currentART40_49m ||
           newART50F>currentART50F || newART50M>currentART50M
           )  {
      errorART = 2550;
   }  
   if(
           (newART1F+newART1M+newART1_4F+newART1_4M+newART5_9F+newART5_9M+newART10_14F+newART10_14M+newART15_19F+newART15_19M+newART20_24F+newART20_24M+newART25_29f+
           newART25_29m+newART30_34f+newART30_34m+newART35_39f+newART35_39m+newART40_49f+newART40_49m+newART50F+newART50M)!=totalNewART
           )  {
      errorART = 900000;
   }  
      
       String dataART []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"+totalCurrentART+","
           + ""+currentART1F+","+currentART1_4F+","+currentART5_9F+","+currentART10_14F+","+currentART15_19F+","
           + ""+currentART20_24F+","+currentART25_29f+","+currentART30_34f+","+currentART35_39f+","
           + ""+currentART40_49f+","+currentART50F+","+currentART1M+","+currentART1_4M+","+currentART5_9M+","+currentART10_14M+","
           + ""+currentART15_19M+","+currentART20_24M+","+currentART25_29m+","+currentART30_34m+","
           + ""+currentART35_39m+","+currentART40_49m+","+currentART50M+","+totalNewART+","+Pregnant+","+breastfeeding+","+ontbtreatment+","
           + ""+newART1F+","+newART1_4F+","+newART5_9F+","+newART10_14F+","+newART15_19F+","+newART20_24F+","
           + ""+newART25_29f+","+newART30_34f+","+newART35_39f+","+newART40_49f+","+newART50F+","+newART1M+","+newART1_4M+","+newART5_9M+","+newART10_14M+","
           + ""+newART15_19M+","+newART20_24M+","+newART25_29m+","+newART30_34m+","+newART35_39m+","
           + ""+newART40_49m+","+newART50M+","+errorART+","+arthv+","+htchv+","+pmtcthv).split(",");
   
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
           System.out.println("_"+value);
           c11=rw3shet1.createCell(positionART);
        if(isNumeric(value)){ 
            c11.setCellValue(Double.parseDouble(value));
        }
        else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionART==5 || positionART==20){ c11.setCellStyle(styleHeader);}
          if(positionART==dataART.length-4){
         
       if(errorART>=900000){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}  
       else if(errorART>=2550 && errorART<900000){c11.setCellValue("New on ART is more than Currently on ART");c11.setCellStyle(yellowstyle);} 
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
      double linked_art,not_linked_art,unknown_link,  not_breastfeeding,   breastfeeding_unknown, care_no_test, ltfu, died,transferred_out;
       linked_art=not_linked_art=unknown_link= not_breastfeeding= breastfeeding=breastfeeding_unknown=care_no_test=ltfu=died=transferred_out=0;
      
      
      numerator=denominator=0;
     
      if(reportDuration.equals("4")){
      numerator=denominator=0;    
      }
      else{
      String getPMTCTFO="SELECT SUM(numerator),SUM(denominator),SUM(linked_art),  SUM(not_linked_art), SUM(unknown_link),  SUM(not_breastfeeding),  SUM(breastfeeding), SUM(breastfeeding_unknown), SUM(care_no_test), SUM(ltfu), SUM(died), SUM(transferred_out)  FROM pmtct_fo WHERE "+excelDuration+" SubPartnerID='"+facilityId+"' ";
      conn.rs1=conn.st1.executeQuery(getPMTCTFO);
      if(conn.rs1.next()==true){
          
           numerator=conn.rs1.getInt(1);
           denominator=conn.rs1.getInt(2);
           linked_art=conn.rs1.getInt(3);
           not_linked_art=conn.rs1.getInt(4);
           unknown_link= conn.rs1.getInt(5);
           not_breastfeeding= conn.rs1.getInt(6);
           breastfeeding=conn.rs1.getInt(7);
           breastfeeding_unknown=conn.rs1.getInt(8);
           care_no_test=conn.rs1.getInt(9);
           ltfu=conn.rs1.getInt(10);
           died=conn.rs1.getInt(11);
           transferred_out=conn.rs1.getInt(12);
           
      }
      }
      
 //        PMTCT_FO===================================================================================================
     PMTCT_FO_I_N=(double) numerator;
     PMTCT_FO_I_D=(double) denominator;
     PMTCT_FO_I_LINKED=linked_art;
     PMTCT_FO_I_NOT_LINKED=not_linked_art;
     PMTCT_FO_I_UNKNOWN=unknown_link;
     PMTCT_FO_U_NOT_BREASTFEEDING=not_breastfeeding;
     PMTCT_FO_U_STILL_BREASTFEEDING=breastfeeding;
     PMTCT_FO_U_BREASTFEEDING_UNKNOWN=breastfeeding_unknown;
     PMTCT_FO_OTHER_INCARE=care_no_test;
     PMTCT_FO_OTHER_NOFOLLOWUP=ltfu;
     PMTCT_FO_DIED=died;
     PMTCT_FO_TRANSFERRED=transferred_out;  
     
    double normalizer=PMTCT_FO_I_LINKED+PMTCT_FO_I_NOT_LINKED+PMTCT_FO_I_UNKNOWN+PMTCT_FO_U_NOT_BREASTFEEDING+
            PMTCT_FO_U_STILL_BREASTFEEDING+PMTCT_FO_U_BREASTFEEDING_UNKNOWN+PMTCT_FO_OTHER_INCARE+
            PMTCT_FO_OTHER_NOFOLLOWUP+PMTCT_FO_DIED+PMTCT_FO_TRANSFERRED;
     int pmtctnum=0;
     //disable normalization from 11/11/2016
     //all reports are being fed from the system.
     if(1==2){
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
      
 PMTCT_STATN_N=(double) (HV0201+HV0205);       
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
	
    
    
    //Made my life veery simple...
                shetPMTCT.setDisplayGridlines(false);
                shetPMTCT.createFreezePane(5, 3);
                shetPMTCT.setActive(false);
                
                 shet1.setDisplayGridlines(false);
                shet1 .createFreezePane(5, 5);
                
                //care
                shet2.setDisplayGridlines(false);
                shet2.createFreezePane(5, 5);
 //hide PMTCT worksheet
  wb.setSheetHidden(2,true); 
    }
 
 if(2==2){
 
 
     
     
     
                                
        
                
        
        
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "eid_datim";
            
//=====================================================================================================
            year = "2016";
            month = "4";
            String county = "";
            String header = "";
            
            
           /** 
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"Facility details","","","","EID Tested","","","","EID HIV positive","","","","EID HIV negative","","","","EID HIV enrollment status","","","","Totals","","","","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders0[]={"Facility details","","","","Female","","Male","","Female","","Male","","Female","","Male","","EID HIV enrollment status","","","","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","Dead","Enrolled","Lost to Follow Up","Other","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	*/
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"County","Sub-county","Facility Name","MFL Code","Disaggregated by infant Results","","","","","","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders0[]={"County","Sub-county","Facility Name","MFL Code","PMTCT_EID: Number of infants who had a virologic test within 12 months of birth during the reporting period","","PMTCT_HEI_POS: HIV-infected infants age disaggregation","","PMTCT_HEI_POS: HIV-infected infants disaggregated by ART initiation and age at virological sample collection","","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","0-2 Months","2-12 Months","0-2 Months","2-12 Months","0-2 Months","2-12 Months","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	

           
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            String merge_row_col[]={"0,0,0,12","1,3,0,0","1,3,1,1","1,3,2,2","1,3,3,3","1,1,4,9","2,2,4,5","2,2,6,7","2,2,8,9","1,3,10,10","1,3,11,11","1,3,12,12"};
            
            String reportType = "";
            if (request.getParameter("reportType") != null) 
            {
                reportType = request.getParameter("reportType");
            }
            String reportDuration = "";
            if (request.getParameter("reportDuration") != null) 
            {
                reportDuration = request.getParameter("reportDuration");
            }
            
            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
                                                      }
            
            Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  
  String facilitiestable="subpartnera";
  
  int selectedyear=new Integer(year);
  
  if(selectedyear<currentyear){
      
      if(selectedyear<2014){
          
      //db for 2014 is the smallest
          
       facilitiestable="subpartnera2014";
  
      }
      else 
      {
      
  facilitiestable="subpartnera"+selectedyear;
  
      }
  }
            
            
            if (request.getParameter("facility") != null && reportType.equals("2")) {
                try {
                    facil = request.getParameter("facility");
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from "+facilitiestable+" where SubPartnerID='" + facil + "'";
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
            String eidduration="";
            String excelDuration;
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
                eidduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     eidduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     eidduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     eidduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
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
                //on monthly reports, i dont expect any output since viral load is entered quarterly
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                eidduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = "1=2";// this will make the report not output any data which is what i wanted
                } 
                else {
                    duration = " 1=2"; // this will make the report not output any data which is what i wanted
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            }
            else {
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
                
                subcountywhere = " and "+facilitiestable+".DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and eid_datim.SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + eidduration + " " + countywhere + " " + subcountywhere;
           //old eid format 
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
//            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode "
//           + ",sum(less1_ftes) as less1_ftes"
//           + ", sum(1to4_ftes) as 1to4_ftes"
//           + ", sum(less1_mtes) as less1_mtes"
//           + ", sum(1to4_mtes) as 1to4_mtes  "
//           + ",	sum(less1_fpos) as less1_fpos"
//           + ",	sum(1to4_fpos) as 1to4_fpos"
//           + ", sum(less1_mpos) as less1_mpos"
//           + ", sum(1to4_mpos) as 1to4_mpos"
//           + ", sum(less1_fneg) as less1_fneg"
//           + ", sum(1to4_fneg) as 1to4_fneg"
//           + ", sum(less1_mneg) as less1_mneg"
//           + ", sum(1to4_mneg) as 1to4_mneg"
//           + ", sum(dead) as dead"
//           + ", sum(enrolled) as enrolled"
//           + ", sum(ltfu) as ltfu"
//           + ", sum(other) as other"
//           + ", sum(tested) as tested"
//           + ", sum(positive) as positive"
//           + ", sum(negative) as negative"
//           + ", sum(hivenrollment) as hivenrollment "
//           + ",IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
//            
//           + " FROM eid_datim join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on eid_datim.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (subpartnera.HTC=1 || subpartnera.PMTCT=1 || subpartnera.VMMC=1) group by subpartnera.SubPartnerID ";
//            
       
            
            
             getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode "
           
                    
                   + " ,sum(0_2mpos) as 0_2_months_positive " //0_2mpos,2_12mpos,0_2mneg,2_12mneg,0_2mno_result,2_12mno_result
                   + " ,sum(2_12mpos) as 2_12_months_positive " //,,0_2mneg,2_12mneg,0_2mno_result,2_12mno_result
                   + " ,sum(0_2mneg) as 0_2_months_negative " //,,,2_12mneg,0_2mno_result,2_12mno_result
                   + " ,sum(2_12mneg) as 2_12_months_negative " //,,,,0_2mno_result,2_12mno_result
                   + " ,sum(0_2mno_result) as 0_2_months_no_result " //,,,,,2_12mno_result
                   + " ,sum(2_12mno_result) as 2_12_months_no_result " //,,,,,2_12mno_result
                   + " ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
           
           + " FROM eid_datim join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on eid_datim.SubPartnerID = "+facilitiestable+".SubPartnerID   "+joinedwhwere+" and ("+facilitiestable+".HTC=1 || "+facilitiestable+".PMTCT=1 || "+facilitiestable+".VMMC=1) group by "+facilitiestable+".SubPartnerID ";
           
          String eid_query="SELECT facility_name,district_name,county,mfl_code,ART_Support,PMTCT_Support,SUM(eid_tested_0_2months) AS eid_tested_0_2months,SUM(eid_tested_2_12months) AS eid_tested_2_12months," +
                            "SUM(eid_pos_0_2months) AS eid_pos_0_2months,SUM(eid_pos_2_12months) AS eid_pos_2_12months,SUM(eid_pos_0_2months_ART) AS eid_pos_0_2months_ART,SUM(eid_pos_2_12months_ART) AS eid_pos_2_12months_ART,"
                  + "ART_highvolume,HTC_highvolume,PMTCT_highvolume " +
                            "FROM(" +
                            "SELECT "+facilitiestable+".SubPartnerNom AS facility_name,district.DistrictNom AS district_name,county.County AS county," +
                            ""+facilitiestable+".CentreSanteId AS mfl_code,ART_Support,PMTCT_Support," +
                            "0  AS eid_tested_0_2months," +
                            "0  AS eid_tested_2_12months, " +
                            "COUNT( CASE WHEN eid_raw_pos.Age<=2 THEN '<2 POS' END)  AS eid_pos_0_2months," +
                            "COUNT( CASE WHEN eid_raw_pos.Age>2 AND eid_raw_pos.Age<=12 THEN '>2 <=12 POS' END)  AS eid_pos_2_12months," +
                            "COUNT( CASE WHEN eid_raw_pos.Age<=2 AND treatment_init_date!=\"\" THEN '<2 POS ART' END)  AS eid_pos_0_2months_ART," +
                            "COUNT( CASE WHEN eid_raw_pos.Age>2  AND treatment_init_date!=\"\" AND eid_raw_pos.Age<=12 THEN '>2  <=12 POST ART' END)  AS eid_pos_2_12months_ART,"+
                            "IFNULL(ART_highvolume,0) as ART_highvolume,"+
                            "IFNULL(HTC_highvolume,0) as HTC_highvolume,"+
                            "IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "+
                            "FROM eid_raw_pos " +
                            "LEFT JOIN "+facilitiestable+" ON eid_raw_pos.SubPartnerID="+facilitiestable+".SubPartnerID " +
                            "LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
                            "LEFT JOIN county ON district.CountyID=county.CountyID " +
                            " "+joinedwhwere+" and "+facilitiestable+".PMTCT=1 && PCR_Type='Initial PCR' && ('validation' !='A' || 'validation'!='VL') && "+facilitiestable+".active=1 GROUP BY eid_raw_pos.SubPartnerID " +
                            "   UNION ALL           " +
                            "SELECT "+facilitiestable+".SubPartnerNom AS facility_name,district.DistrictNom AS district_name,county.County AS county," +
                            ""+facilitiestable+".CentreSanteId AS mfl_code,ART_Support,PMTCT_Support," +
                            "COUNT( CASE WHEN eid_raw_tested.age_months<=2 THEN '<2 TST'  END)  AS eid_tested_0_2months," +
                            "COUNT( CASE WHEN eid_raw_tested.age_months>2 AND eid_raw_tested.age_months<=12 THEN '>2 <=12 TST' END)  AS eid_tested_2_12months," +
                            "0  AS eid_pos_0_2months," +
                            "0  AS eid_pos_2_12months," +
                            "0  AS eid_pos_0_2months_ART," +
                            "0  AS eid_pos_2_12months_ART,"+
                            "0 as ART_highvolume,"+
                            "0 as HTC_highvolume,"+
                            "0 as PMTCT_highvolume "+
                            "FROM eid_raw_tested " +
                            "LEFT JOIN "+facilitiestable+" ON eid_raw_tested.SubPartnerID="+facilitiestable+".SubPartnerID " +
                            "LEFT JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID " +
                            "LEFT JOIN county ON district.CountyID=county.CountyID " +
                            " "+joinedwhwere+" and  "+facilitiestable+".PMTCT=1 && PCR_Type='Initial PCR' && "+facilitiestable+".active=1 GROUP BY eid_raw_tested.SubPartnerID " +
                            ") AS eid_data group by mfl_code";  
            
            System.out.println("EID QUERY : "+eid_query);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
            
        
     
     
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
            
            HSSFSheet shet = wb.createSheet("EID");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("DATIM EID " + yearmonth);
            cl0.setCellStyle(stylex);
           
            
            //______header 1 facility details
            rowpos++;
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(25);
             for (int a = 0; a < sectionheaders.length; a++) 
             {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
                shet.setColumnWidth(a, 3100);
            }
            
             //______header 2 facility details
            rowpos++;
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(25);
             for (int a = 0; a < sectionheaders0.length; a++) 
             {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(sectionheaders0[a]);
                clx.setCellStyle(stylex);
                
            }              
           
             //______header 3 all details
            rowpos++;
            HSSFRow rw3 = shet.createRow(rowpos);
            rw3.setHeightInPoints(25);
             for (int a = 0; a < sectionheaders1.length; a++) 
             {
                HSSFCell clx = rw3.createCell(a);
                clx.setCellValue(sectionheaders1[a]);
                clx.setCellStyle(stylex);
                
            }   
             
             rowpos++;
            
            //do all the merging here as dictated by the merge_row_col array 
            for (int a = 0; a <merge_row_col.length; a++) {
             String points[]=merge_row_col[a].split(",");
                
           shet.addMergedRegion(new CellRangeAddress(Integer.parseInt(points[0]),Integer.parseInt(points[1]),Integer.parseInt(points[2]),Integer.parseInt(points[3])));
              
            }
           
            
            shet.setColumnWidth(0, 5000);
            
//add the rows here          
       
            
             conn.rs=conn.st.executeQuery(eid_query);
    
    
    String facility_name,district_name,mfl_code,ART_Support,PMTCT_Support,eid_tested_0_2months,
            eid_tested_2_12months,eid_pos_0_2months,eid_pos_2_12months,eid_pos_0_2months_ART,eid_pos_2_12months_ART,
             ART_highvolume,HTC_highvolume,PMTCT_highvolume,support_type;
             
    while(conn.rs.next()){
    
        facility_name = conn.rs.getString(1);
        district_name = conn.rs.getString(2);
        county = conn.rs.getString(3);
        mfl_code = conn.rs.getString(4);
        ART_Support = conn.rs.getString(5);
        PMTCT_Support = conn.rs.getString(6);
        eid_tested_0_2months = conn.rs.getString(7);
        eid_tested_2_12months = conn.rs.getString(8);
        eid_pos_0_2months = conn.rs.getString(9);
        eid_pos_2_12months = conn.rs.getString(10);
        eid_pos_0_2months_ART = conn.rs.getString(11);
        eid_pos_2_12months_ART = conn.rs.getString(12);
        ART_highvolume = conn.rs.getString(13);
        HTC_highvolume = conn.rs.getString(14);
        PMTCT_highvolume = conn.rs.getString(15);
        
        support_type = PMTCT_Support;
                    
           int colpos=0; 
           int conpos=1;
           int loopstart=5;
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
               //county
            if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            
            clx.setCellValue(county);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                //subcounty
             if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(district_name);
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                 
                 //facility name
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellValue(facility_name);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

                      }
                   //mfl
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(mfl_code);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
//support type//######################################################################################
        if (1 == 2) {
            if(support_type.equals("")){
            support_type="DSD";
            }
            
            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(support_type);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
 // 
//      tested 0-2 months  
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_tested_0_2months));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// tested 2-12 months
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_tested_2_12months));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// postive 0-2 months
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_pos_0_2months));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// positive 2-12 months
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_pos_2_12months));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// positive and initiated on art 0-2 months
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_pos_0_2months_ART));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// positive and initiated on ART 2-12 Months
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(eid_pos_2_12months_ART));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// ART High volume
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(ART_highvolume));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// HTC High volume
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(HTC_highvolume));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
// pmtct high volume
 if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Integer.parseInt(PMTCT_highvolume));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
 
     
        rowpos++;
    }
    //____autofilter______       
    shet.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length-1));

    //System.out.println("1,"+rowpos+",0,"+colposcopy);
                        for (int e = 0; e < 4; e++) {
                            shet.autoSizeColumn(e);
                        }
             //Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(4, 4);            
            
     
     
 
 
 }
 
 
 //_____________________________________________PMTCT_FO_____________________________________________
 
 if(3==3){
 
     
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "hei.results";
            
//=====================================================================================================
            year = "2016";
            month = "4";
            String county = "";
            String header = "";
            
            
           /** 
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"Facility details","","","","EID Tested","","","","EID HIV positive","","","","EID HIV negative","","","","EID HIV enrollment status","","","","Totals","","","","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders0[]={"Facility details","","","","Female","","Male","","Female","","Male","","Female","","Male","","EID HIV enrollment status","","","","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","Dead","Enrolled","Lost to Follow Up","Other","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	*/
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"County","Sub-county","Facility Name","MFL Code","Support Type","Denominator","Numerator","HIV-infected","HIV-uninfected","HIV-final status Unknown","Died without status known","ART High Volume","HTC High Volume","PMTCT High Volume"};
            //String sectionheaders0[]={"County","Sub-county","Facility Name","MFL Code","Positive","","Negative","","Collected/sent but no result recorded","","ART High Volume","HTC High Volume","PMTCT High Volume"};
           // String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","0-2 Months","2-12 Months","0-2 Months","2-12 Months","0-2 Months","2-12 Months","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	

           
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
           String merge_row_col[]={"0,0,0,"+(sectionheaders.length-1)};
            
            String reportType = "";
            if (request.getParameter("reportType") != null) 
            {
                reportType = request.getParameter("reportType");
            }
            String reportDuration = "";
            if (request.getParameter("reportDuration") != null) 
            {
                reportDuration = request.getParameter("reportDuration");
            }
            
            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
                                                      }
            
            
 Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  
  String facilitiestable="internal_system.subpartnera";
  
  int selectedyear=new Integer(year);
  
  if(selectedyear<currentyear){
      
      if(selectedyear<2014){
          
      //db for 2014 is the smallest
          
       facilitiestable="internal_system.subpartnera2014";
  
      }
      else 
      {
      
  facilitiestable="internal_system.subpartnera"+selectedyear;
  
      }
  }
 
            
            
            
            if (request.getParameter("facility") != null && reportType.equals("2")) {
                try {
                    facil = request.getParameter("facility");
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from "+facilitiestable+" where SubPartnerID='" + facil + "'";
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
            String eidduration="";
            String excelDuration;
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
                duration = " " + form + ".reportingyearmonth BETWEEN " + prevYear + "10 AND " + year + "09";
                eidduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".reportingyearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     eidduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".reportingyearmonth BETWEEN " + year + "04 AND " + year + "09";
                     eidduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     eidduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
                    String getMonths = "SELECT months,name FROM quarter WHERE id='" + quarter + "'";
                    conn.rs = conn.st.executeQuery(getMonths);
                    if (conn.rs.next() == true) {
                        
                        try {
                            String months[] = conn.rs.getString(1).split(",");
                            startMonth = months[0];
                            endMonth = months[2];
                            if (quarter.equals("1")) {
                                duration = " " + form + ".reportingyearmonth BETWEEN " + prevYear + "" + startMonth + " AND " + prevYear + "" + endMonth;
                                yearmonth = "Quarterly Report For " + prevYear + " " + conn.rs.getString(2);
                            } else {
                                yearmonth = "Quarterly Report For " + year + " (" + conn.rs.getString(2) + ")";
                                duration = " " + form + ".reportingyearmonth BETWEEN " + year + "" + startMonth + " AND " + year + "" + endMonth;
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimHTCResults.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (reportDuration.equals("4")) {
                //on monthly reports, i dont expect any output since viral load is entered quarterly
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                eidduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = "1=2";// this will make the report not output any data which is what i wanted
                } 
                else {
                    duration = " 1=2"; // this will make the report not output any data which is what i wanted
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            }
            else {
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
            
            if (!county.equals("")) 
            {
                
                countywhere = " and internal_system.district.countyid = '" + county + "'";
                
            }
            
            if (!subcounty.equals("")) {
                
                subcountywhere = " and "+facilitiestable+".DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and results.facility_id = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + duration + " " + countywhere + " " + subcountywhere;
           //old eid format 
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
//            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode "
//           + ",sum(less1_ftes) as less1_ftes"
//           + ", sum(1to4_ftes) as 1to4_ftes"
//           + ", sum(less1_mtes) as less1_mtes"
//           + ", sum(1to4_mtes) as 1to4_mtes  "
//           + ",	sum(less1_fpos) as less1_fpos"
//           + ",	sum(1to4_fpos) as 1to4_fpos"
//           + ", sum(less1_mpos) as less1_mpos"
//           + ", sum(1to4_mpos) as 1to4_mpos"
//           + ", sum(less1_fneg) as less1_fneg"
//           + ", sum(1to4_fneg) as 1to4_fneg"
//           + ", sum(less1_mneg) as less1_mneg"
//           + ", sum(1to4_mneg) as 1to4_mneg"
//           + ", sum(dead) as dead"
//           + ", sum(enrolled) as enrolled"
//           + ", sum(ltfu) as ltfu"
//           + ", sum(other) as other"
//           + ", sum(tested) as tested"
//           + ", sum(positive) as positive"
//           + ", sum(negative) as negative"
//           + ", sum(hivenrollment) as hivenrollment "
//           + ",IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
//            
//           + " FROM eid_datim join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on eid_datim.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (subpartnera.HTC=1 || subpartnera.PMTCT=1 || subpartnera.VMMC=1) group by subpartnera.SubPartnerID ";
//            
       
            
            
             getexistingdata=" SELECT county as County ,DistrictNom as SubCounty, SubPartnerNom as Facility, CentreSanteId as MFLCode " +
" ,sum( case when indicator_id=23 then denominator end) as Denominator " +
" ,sum( case when (indicator_id=21 or indicator_id=22 or indicator_id=23 or  indicator_id=24 or  indicator_id=25  or indicator_id=26 )  then numerator end) as Numerator " +
" ,sum( case when (indicator_id=23)  then numerator end) as HIV_infected " +
" ,sum( case when (indicator_id=21)  then numerator end) as HIV_uninfected " +
" ,sum( case when (indicator_id=22 or  indicator_id=24 or  indicator_id=25 )  then numerator end) as HIV_final_status_unknown " +
" ,sum( case when indicator_id=26   then numerator end) as Died_without_status_known " +
" ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume " +

" FROM hei.results join "+facilitiestable+" on hei.results.facility_id="+facilitiestable+".SubPartnerID join (internal_system.district join internal_system.county on internal_system.county.CountyID=internal_system.district.CountyID ) on internal_system.district.DistrictID="+facilitiestable+".DistrictID " +
" "+joinedwhwere+" and ( "+facilitiestable+".PMTCT=1 ) " +
" group by "+facilitiestable+".SubPartnerID ";
           
           //+ " FROM pmtct_fo join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on pmtct_fo.SubPartnerID = "+facilitiestable+".SubPartnerID   "+joinedwhwere+" and ( "+facilitiestable+".PMTCT=1 ) group by "+facilitiestable+".SubPartnerID ";
           
            
            
            System.out.println(getexistingdata);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
            
        
     
     
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
            
            HSSFSheet shet = wb.createSheet("PMTCT_FO");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("DATIM PMTCT_FO " + yearmonth);
            cl0.setCellStyle(stylex);
           
            
            //______header 1 facility details
            rowpos++;
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(25);
             for (int a = 0; a < sectionheaders.length; a++) 
             {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
                shet.setColumnWidth(a, 3100);
            }
            
//             //______header 2 facility details
            rowpos++;
//            HSSFRow rw2 = shet.createRow(rowpos);
//            rw2.setHeightInPoints(25);
//                         
           
            //do all the merging here as dictated by the merge_row_col array 
            for (int a = 0; a <merge_row_col.length; a++) {
             String points[]=merge_row_col[a].split(",");
                
           shet.addMergedRegion(new CellRangeAddress(Integer.parseInt(points[0]),Integer.parseInt(points[1]),Integer.parseInt(points[2]),Integer.parseInt(points[3])));
              
            }
           
            
            shet.setColumnWidth(0, 5000);
            
//add the rows here          
       
            
             conn.rs=conn.st.executeQuery(getexistingdata);
    
    
    while(conn.rs.next()){
    
        
        
    
           int colpos=0; 
           int conpos=1;
           int loopstart=5;
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
               //county
            if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                //subcounty
             if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                 
                 //facility name
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

                      }
                   //mfl
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getInt(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
//support type//######################################################################################
        if (1 == 1) {
//String supporttype=conn.rs.getString("DSD");
String supporttype="DSD";
if(supporttype.equals("")){
supporttype="DSD";
}
            
            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(supporttype);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
 //___   a for loop  
        
        for (int e=loopstart;e<sectionheaders.length;e++)
        {
      if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getInt(e));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }               
              
        }
     
     
        rowpos++;
    }
    //____autofilter______       
    shet.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(1, rowpos - 1, 0, sectionheaders.length-1));

    //System.out.println("1,"+rowpos+",0,"+colposcopy);
                        for (int e = 0; e < 4; e++) {
                            shet.autoSizeColumn(e);
                        }
             //Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(5, 2);            
            
     
     
 
 
 
 
 
 }  
  
 
 if(4==4){
 
     
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "pmtct_arv";
            
//=====================================================================================================
            year = "2016";
            month = "4";
            String county = "";
            String header = "";
            
            
           /** 
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"Facility details","","","","EID Tested","","","","EID HIV positive","","","","EID HIV negative","","","","EID HIV enrollment status","","","","Totals","","","","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders0[]={"Facility details","","","","Female","","Male","","Female","","Male","","Female","","Male","","EID HIV enrollment status","","","","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","<1","1-4","Dead","Enrolled","Lost to Follow Up","Other","EID Tested","EID HIV positive","EID HIV negative","EID HIV enrollment","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	*/
           // String subheaders[]={"Female","Male"};
            String sectionheaders[]={"County","Sub-county","Facility Name","MFL Code","Support Type","New on ART","Already on ART","Total Started ART at ANC","ART High Volume","HTC High Volume","PMTCT High Volume"};
            //String sectionheaders0[]={"County","Sub-county","Facility Name","MFL Code","Positive","","Negative","","Collected/sent but no result recorded","","ART High Volume","HTC High Volume","PMTCT High Volume"};
           // String sectionheaders1[]={"County","Sub-county","Facility Name","MFL Code","0-2 Months","2-12 Months","0-2 Months","2-12 Months","0-2 Months","2-12 Months","ART High Volume","HTC High Volume","PMTCT High Volume"};		
	

           
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            String merge_row_col[]={"0,0,0,"+(sectionheaders.length-1)};
            
            String reportType = "";
            if (request.getParameter("reportType") != null) 
            {
                reportType = request.getParameter("reportType");
            }
            String reportDuration = "";
            if (request.getParameter("reportDuration") != null) 
            {
                reportDuration = request.getParameter("reportDuration");
            }
            
            if (request.getParameter("year") != null) {
                year = request.getParameter("year");
                                                      }
            
            
 Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  
  String facilitiestable="subpartnera";
  
  int selectedyear=new Integer(year);
  
  if(selectedyear<currentyear){
      
      if(selectedyear<2014){
          
      //db for 2014 is the smallest
          
       facilitiestable="subpartnera2014";
  
      }
      else 
      {
      
  facilitiestable="subpartnera"+selectedyear;
  
      }
  }
 
            
            
            
            if (request.getParameter("facility") != null && reportType.equals("2")) {
                try {
                    facil = request.getParameter("facility");
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from "+facilitiestable+" where SubPartnerID='" + facil + "'";
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
            String eidduration="";
            String excelDuration;
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
                eidduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     eidduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     eidduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     eidduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
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
                //on monthly reports, i dont expect any output since viral load is entered quarterly
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                eidduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = "1=2";// this will make the report not output any data which is what i wanted
                } 
                else {
                    duration = " 1=2"; // this will make the report not output any data which is what i wanted
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            }
            else {
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
                
                subcountywhere = " and "+facilitiestable+".DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and pmtct_art.SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + eidduration + " " + countywhere + " " + subcountywhere;
           //old eid format 
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
//            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode "
//           + ",sum(less1_ftes) as less1_ftes"
//           + ", sum(1to4_ftes) as 1to4_ftes"
//           + ", sum(less1_mtes) as less1_mtes"
//           + ", sum(1to4_mtes) as 1to4_mtes  "
//           + ",	sum(less1_fpos) as less1_fpos"
//           + ",	sum(1to4_fpos) as 1to4_fpos"
//           + ", sum(less1_mpos) as less1_mpos"
//           + ", sum(1to4_mpos) as 1to4_mpos"
//           + ", sum(less1_fneg) as less1_fneg"
//           + ", sum(1to4_fneg) as 1to4_fneg"
//           + ", sum(less1_mneg) as less1_mneg"
//           + ", sum(1to4_mneg) as 1to4_mneg"
//           + ", sum(dead) as dead"
//           + ", sum(enrolled) as enrolled"
//           + ", sum(ltfu) as ltfu"
//           + ", sum(other) as other"
//           + ", sum(tested) as tested"
//           + ", sum(positive) as positive"
//           + ", sum(negative) as negative"
//           + ", sum(hivenrollment) as hivenrollment "
//           + ",IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
//            
//           + " FROM eid_datim join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on eid_datim.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (subpartnera.HTC=1 || subpartnera.PMTCT=1 || subpartnera.VMMC=1) group by subpartnera.SubPartnerID ";
//            
       
            
            
             getexistingdata="select pmtct_art.county,pmtct_art.subcounty,  facility,  mflcode,support_type ,"
                   + " SUM(new_on_art) as New_on_art, "//__Denominator
                   + " SUM(already_on_art) as Already_On_Art ,  "//__Numerator
                   + " SUM(total_started_art_at_anc)as Total_Started_ART_at_ANC, " //__HIV_infected
                   + " IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
           
           + " FROM pmtct_art join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on pmtct_art.mflcode = "+facilitiestable+".CentreSanteId   "+joinedwhwere+" and ( "+facilitiestable+".PMTCT=1 ) group by "+facilitiestable+".SubPartnerID ";
           
            
            
            System.out.println(getexistingdata);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
       
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
            
            HSSFSheet shet = wb.createSheet("PMTCT ART");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("DATIM PMTCT_ART " + yearmonth);
            cl0.setCellStyle(stylex);
           
            
            //______header 1 facility details
            rowpos++;
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(25);
             for (int a = 0; a < sectionheaders.length; a++) 
             {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
                shet.setColumnWidth(a, 3100);
            }
            
//             //______header 2 facility details
            rowpos++;
//            HSSFRow rw2 = shet.createRow(rowpos);
//            rw2.setHeightInPoints(25);
//                         
           
            //do all the merging here as dictated by the merge_row_col array 
            for (int a = 0; a <merge_row_col.length; a++) {
             
                String points[]=merge_row_col[a].split(",");
                
           shet.addMergedRegion(new CellRangeAddress(Integer.parseInt(points[0]),Integer.parseInt(points[1]),Integer.parseInt(points[2]),Integer.parseInt(points[3])));
              
            }
           
            
            shet.setColumnWidth(0, 5000);
            
//add the rows here          
       
            
             conn.rs=conn.st.executeQuery(getexistingdata);
    
    
    while(conn.rs.next()){
    
        
        
    
           int colpos=0; 
           int conpos=1;
           int loopstart=6;
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
               //county
            if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                //subcounty
             if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                 
                 //facility name
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            //clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

                      }
                   //mfl
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getInt(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
//support type//######################################################################################
        if (1 == 1) {
//String supporttype=conn.rs.getString("DSD");
String supporttype="DSD";
if(supporttype.equals("")){
supporttype="DSD";
}
            
            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(supporttype);
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
 //___   a for loop  
        
        for (int e=loopstart;e<=sectionheaders.length;e++)
        {
      if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getInt(e));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }               
              
        }
     
     
        rowpos++;
    }
    //____autofilter______       
    shet.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(1, rowpos - 1, 0, sectionheaders.length-1));

    //System.out.println("1,"+rowpos+",0,"+colposcopy);
                        for (int e = 0; e < 4; e++) {
                            shet.autoSizeColumn(e);
                        }
             //Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(5, 2);            
            
     
     
 
 
 
 
 
 }  

    
    
    
      if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
     IdGenerator IG = new IdGenerator();
        String createdOn = IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=DATIM_ART_CARE_PMTCT_2018_CREATED_ON_"+createdOn.trim()+".xls");
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
            Logger.getLogger(datimReport_2018.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(datimReport_2018.class.getName()).log(Level.SEVERE, null, ex);
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
}

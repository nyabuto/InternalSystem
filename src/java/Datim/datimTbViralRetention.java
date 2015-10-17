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
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Emmanuel E
 */
public class datimTbViralRetention extends HttpServlet {

    HttpSession session;
    
    int TB_STAT_N,TB_STAT_D,TB_STAT_FEMALE,TB_STAT_MALE,TB_STAT_1,TB_STAT_4,TB_STAT_9,TB_STAT_14,TB_STAT_19,TB_STAT_20,
        TB_STAT_POSTIVE,TB_STAT_NEGATIVE;
Double TB_SCREEN_D,TB_SCREEN_N,TB_SCREEN_FEMALE,TB_SCREEN_MALE,TB_SCREEN_LESS15,TB_SCREEN_1,TB_SCREEN_4,TB_SCREEN_9,
       TB_SCREEN_14,TB_SCREEN_MORE15,TB_SCREEN_19,TB_SCREEN_20;
int TB_ART_N,TB_ART_D,TB_ART_FEMALE,TB_ART_MALE,TB_ART_1,TB_ART_4,TB_ART_9,TB_ART_14,TB_ART_19,TB_ART_20;
int errorTB,tbpos;
String excelDuration;
 ArrayList allFacilities = new ArrayList();
 int year,month,prevYear,maxYearMonth;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;
String createdOn,period,ARTSupport;

int HV0210,HV0209,HV0205,HV0217,HV0216;
int HV0224,HV0225,HV0227,HV0232,HV0229,HV0230,HV0231;
int HV0302,HV0206,HV0207,HV0208;
int HV0319,HV0350,HV0351,HV0352,HV0353,HV0354;
int HV0308,HV0309,HV0310,HV0311,HV0312,HV0320,HV0321,HV0322,HV0323,HV0324;
int HV0314,HV0315,HV0316,HV0317,HV0318,HV0334,HV0335,HV0336,HV0337,HV0338;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
              try {
            response.setContentType("text/html;charset=UTF-8");
            allFacilities.clear();
            session = request.getSession();
            dbConn conn = new dbConn();
                  
       year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
            String headerTB[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+Y,Positive,Negative,Total PLVHIV enrolled in clinical care (HVO319),Ho of PLV in HIV clinical care screened for TB (HV0354),Female,Male, Screened for TB <15 Years,<1,1-4Y,5-9Y,10-14Y,Screened for TB >15 years,15-19Y,20+Y,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+,Verification Status".split(",") ;
 
            String facilityIds1="";
       excelDuration="";
       
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
        tbpos=0;
        
        
        
        HSSFWorkbook wb=new HSSFWorkbook(); 
//============================================================================================
//TB    Place tb code on the if      
//============================================================================================
if(2==2){
//        GET REPORT DURATION================TB============================

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
       facilityIds1="";    
        }   
        
     }
     System.out.println("period is : "+period);
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";   
    
     HSSFSheet shetTB=wb.createSheet("TB"); 
  
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

 for(int i=3;i<=headerTB.length;i++){
   shetTB.setColumnWidth(i, 4000);     
    }
    for (int i=0;i<=1;i++){
   shetTB.setColumnWidth(i, 5000);     
    }
  shetTB.setColumnWidth(2, 8000);
  
  
  
   // TB HEADER======================================================================
    
          HSSFRow  rw0shetTB=shetTB.createRow(1);
  rw0shetTB.setHeightInPoints(30);
HSSFCell c001;
  
 for(int j=0;j<headerTB.length;j++){
        c001=rw0shetTB.createCell(j);
         c001.setCellStyle(styleHeader);
    } 
 c001=rw0shetTB.getCell(0);
 c001.setCellValue(period); 
 
 c001=rw0shetTB.getCell(5);
 c001.setCellValue("TB_STAT"); 
 
 c001=rw0shetTB.getCell(17);
 c001.setCellValue("TB_SCREEN"); 
 
    
  c001=rw0shetTB.getCell(29);
  c001.setCellValue("TB_ARV");
 
  shetTB.addMergedRegion(new CellRangeAddress(1,1,5,16));
  shetTB.addMergedRegion(new CellRangeAddress(1,1,17,28));
  shetTB.addMergedRegion(new CellRangeAddress(1,1,29,38)); 


   
    
    
      HSSFRow  rw1shetTB=shetTB.createRow(2);
  rw1shetTB.setHeightInPoints(30);

  
 for(int j=0;j<headerTB.length;j++){
        c001=rw1shetTB.createCell(j);
         c001.setCellStyle(styleHeader);
    } 
 c001=rw1shetTB.getCell(0);
 c001.setCellValue(period); 
 
 c001=rw1shetTB.getCell(5);
 c001.setCellValue("Numerator"); 
 
 c001=rw1shetTB.getCell(6);
 c001.setCellValue("Denominator"); 
 
    
  c001=rw1shetTB.getCell(9);
 c001.setCellValue("Paeds");
   
   c001=rw1shetTB.getCell(12);
   c001.setCellValue("Adults");
   
   c001=rw1shetTB.getCell(15);
   c001.setCellValue("HIV Status");
   
   c001=rw1shetTB.getCell(17);
   c001.setCellValue("Denominator");
   
   c001=rw1shetTB.getCell(18);
 c001.setCellValue("Numerator"); 
 
  shetTB.addMergedRegion(new CellRangeAddress(1,2,0,4));
  shetTB.addMergedRegion(new CellRangeAddress(2,2,9,11));
  shetTB.addMergedRegion(new CellRangeAddress(2,2,12,14));
  shetTB.addMergedRegion(new CellRangeAddress(2,2,15,16)); 
//  shetTB.addMergedRegion(new CellRangeAddress(2,2,29,31));

    
    
 

    
     HSSFRow  rw2shetTB=shetTB.createRow(3);
  rw2shetTB.setHeightInPoints(50);
  HSSFCell c11;
    for(int headerpos=0;headerpos<headerTB.length;headerpos++){
        String headerInfor=headerTB[headerpos];
        c11=rw2shetTB.createCell(headerpos);
         c11.setCellValue(headerInfor);
         c11.setCellStyle(styleHeader);
    }  
    
    tbpos=4;
  
    String getData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
            + "subpartnera.CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
            + "subpartnera.SubPartnerID,"
            + "SUM(HV0205),SUM(HV0209),SUM(HV0210),SUM(HV0216),SUM(HV0217),"
            + "SUM(HV0224),SUM(HV0225),SUM(HV0227),SUM(HV0229),SUM(HV0230),SUM(HV0231),SUM(HV0232),"
            + "SUM(HV0302),SUM(HV0206),SUM(HV0207),SUM(HV0208)"
            + ",SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354) "
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
HV0308=HV0309=HV0310=HV0311=HV0312=HV0320=HV0321=HV0322=HV0323=HV0324=0;
HV0314=HV0315=HV0316=HV0317=HV0318=HV0334=HV0335=HV0336=HV0337=HV0338=0;
errorTB=0;
HV0319=HV0350=HV0351=HV0352=HV0353=HV0354=0;

TB_STAT_N=TB_STAT_D=TB_STAT_FEMALE=TB_STAT_MALE=TB_STAT_1=TB_STAT_4=TB_STAT_9=TB_STAT_14=TB_STAT_19=TB_STAT_20=
        TB_STAT_POSTIVE=TB_STAT_NEGATIVE=0;
TB_SCREEN_D=TB_SCREEN_N=TB_SCREEN_FEMALE=TB_SCREEN_MALE=TB_SCREEN_LESS15=TB_SCREEN_1=TB_SCREEN_4=TB_SCREEN_9=
       TB_SCREEN_14=TB_SCREEN_MORE15=TB_SCREEN_19=TB_SCREEN_20=0.0;
TB_ART_N=TB_ART_D=TB_ART_FEMALE=TB_ART_MALE=TB_ART_1=TB_ART_4=TB_ART_9=TB_ART_14=TB_ART_19=TB_ART_20=0;

      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
      ARTSupport=conn.rs.getString(5);
//      CARESuport=conn.rs.getString(6);
//      PMTCTSupport=conn.rs.getString(6);
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

       // tb query 
    
         if(reportDuration.equals("4")){
  
      }
      else{
             
         
            String getTB="SELECT SUM(numerator),SUM(denominator),SUM(female),SUM(male),SUM(less1),SUM(1to4),SUM(5to9),SUM(10to14),SUM(15to19),SUM(20above),SUM(positive),SUM(negative"
                    + "),SUM(art_numerator),SUM(art_denominator),SUM(art_female),SUM("
            + "art_male),SUM(art_less1),SUM(art_1to4),SUM(art_5to9),SUM(art_10to14),SUM(art_15to19),SUM(art_20above) FROM tb_stat_art WHERE "+excelDuration+" SubPartnerID='"+facilityId+"'  ";
       conn.rs4= conn.st4.executeQuery(getTB);
      if(conn.rs4.next()==true){
          
        
        TB_STAT_N=conn.rs4.getInt(1);
        TB_STAT_D=conn.rs4.getInt(2);
        TB_STAT_FEMALE=conn.rs4.getInt(3);
        TB_STAT_MALE=conn.rs4.getInt(4);
        TB_STAT_1=conn.rs4.getInt(5);
        TB_STAT_4=conn.rs4.getInt(6);
        TB_STAT_9=conn.rs4.getInt(7);
        TB_STAT_14=conn.rs4.getInt(8);
        TB_STAT_19=conn.rs4.getInt(9);
        TB_STAT_20=conn.rs4.getInt(10);
        TB_STAT_POSTIVE=conn.rs4.getInt(11);
        TB_STAT_NEGATIVE=conn.rs4.getInt(12);

        TB_ART_N=conn.rs4.getInt(13);
        TB_ART_D=conn.rs4.getInt(14);
        TB_ART_FEMALE=conn.rs4.getInt(15);
        TB_ART_MALE=conn.rs4.getInt(16);
        TB_ART_1=conn.rs4.getInt(17);
        TB_ART_4=conn.rs4.getInt(18);
        TB_ART_9=conn.rs4.getInt(19);
        TB_ART_14=conn.rs4.getInt(20);
        TB_ART_19=conn.rs4.getInt(21);
        TB_ART_20=conn.rs4.getInt(22); 
          
          
          
          
      
      }
         }
            
            
//       TB OUTPUT HERE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
       
//       TB STAT----------------------------------------------------------------------
       
       
//       TB SCREEN--------------------------------------------------------------------
      TB_SCREEN_D=(double) HV0319;
      TB_SCREEN_N= (double) HV0354;       
      TB_SCREEN_FEMALE=(double)(HV0351+HV0353);
      TB_SCREEN_MALE= (double) (HV0350+HV0352);
      TB_SCREEN_LESS15=(double)(HV0350+HV0351); 
      TB_SCREEN_MORE15=(double)(HV0352+HV0353);
      
      TB_SCREEN_1= (double)Math.round((0.034*TB_SCREEN_LESS15));
      TB_SCREEN_4= (double)Math.round((0.214*TB_SCREEN_LESS15));       
      TB_SCREEN_9=(double)Math.round((0.37*TB_SCREEN_LESS15));        
      TB_SCREEN_14=(double)Math.round((0.382*TB_SCREEN_LESS15)); 
      
      TB_SCREEN_19=(double)Math.round((0.02*TB_SCREEN_MORE15));        
      TB_SCREEN_20=(double)Math.round((0.98*TB_SCREEN_MORE15));  
      
//      Normalizing=====
      double normalizer=TB_SCREEN_1+TB_SCREEN_4+TB_SCREEN_9+TB_SCREEN_14;
     int tbnum=0;
     
     if((normalizer-TB_SCREEN_LESS15)>2 || (TB_SCREEN_LESS15-normalizer)>2 ){errorTB++;}
     else{
     while(TB_SCREEN_LESS15>normalizer){
         if(tbnum==0){TB_SCREEN_14++;}
    else if(tbnum==1){TB_SCREEN_9++;}
    else if(tbnum==2){TB_SCREEN_4++;}
    else{tbnum=0;}
    normalizer++;
    tbnum++;
     }
     
    normalizer=TB_SCREEN_1+TB_SCREEN_4+TB_SCREEN_9+TB_SCREEN_14;
    tbnum=0;
      while(TB_SCREEN_LESS15<normalizer){
         if(tbnum==0){TB_SCREEN_14--;}
    else if(tbnum==1){TB_SCREEN_9--;}
    else if(tbnum==2){TB_SCREEN_4--;}
    else{tbnum=0;}
    normalizer--;
    tbnum++;
     }
      
     }
     
     normalizer=TB_SCREEN_19+TB_SCREEN_20;
     tbnum=0;
     
     if((normalizer-TB_SCREEN_MORE15)>2 || (TB_SCREEN_MORE15-normalizer)>2 ){errorTB++;}
     else{
     while(TB_SCREEN_MORE15>normalizer){
     TB_SCREEN_20++;
    normalizer++;
    tbnum++;
     }
     
    normalizer=TB_SCREEN_19+TB_SCREEN_20;
     tbnum=0;
      while(TB_SCREEN_MORE15<normalizer){
    TB_SCREEN_20--;
    normalizer--;
    tbnum++;
     }
      
     }
     
      
//       TB ARV-------------------------------------------------------------------------
       
       
       
       
//       OUTPUT----------------------------------------------------------------------------
     if(reportDuration.equals("4")){
        
      
        String dataTB []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","
        + ",,,,,"
        + ",,,,,,,"+TB_SCREEN_D+","+TB_SCREEN_N+","+TB_SCREEN_FEMALE+","
        + ""+TB_SCREEN_MALE+","+TB_SCREEN_LESS15+","+TB_SCREEN_1+","+TB_SCREEN_4+","+TB_SCREEN_9+","+
       TB_SCREEN_14+","+TB_SCREEN_MORE15+","+TB_SCREEN_19+","+TB_SCREEN_20+",,,,,,,,,,,"+errorTB).split(",");
     
     
        HSSFRow rw3shet5=shetTB.createRow(tbpos); 
       rw3shet5.setHeightInPoints(25);
       for(int positionTB=0;positionTB<dataTB.length;positionTB++){
       String value=dataTB[positionTB];
           c11=rw3shet5.createCell(positionTB);
        if(positionTB>=17 && positionTB<=28){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionTB==5 || positionTB==6 || positionTB==17 || positionTB==18 || positionTB==29 || positionTB==30){ c11.setCellStyle(styleHeader);}
          
          if(positionTB==dataTB.length-1){
       if(errorTB>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
     
     
     }
      else{
     String dataTB []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","
        + ""+TB_STAT_N+","+TB_STAT_D+","+TB_STAT_FEMALE+","+TB_STAT_MALE+","+TB_STAT_1+","
        + ""+TB_STAT_4+","+TB_STAT_9+","+TB_STAT_14+","+TB_STAT_19+","+TB_STAT_20+","+
        TB_STAT_POSTIVE+","+TB_STAT_NEGATIVE+","+TB_SCREEN_D+","+TB_SCREEN_N+","+TB_SCREEN_FEMALE+","
        + ""+TB_SCREEN_MALE+","+TB_SCREEN_LESS15+","+TB_SCREEN_1+","+TB_SCREEN_4+","+TB_SCREEN_9+","+
       TB_SCREEN_14+","+TB_SCREEN_MORE15+","+TB_SCREEN_19+","+TB_SCREEN_20+","+
	   TB_ART_N+","+TB_ART_D+","+TB_ART_FEMALE+","+TB_ART_MALE+","+TB_ART_1+","+TB_ART_4+","
        + ""+TB_ART_9+","+TB_ART_14+","+TB_ART_19+","+TB_ART_20+","+errorTB).split(",");
     
     
        HSSFRow rw3shet5=shetTB.createRow(tbpos); 
       rw3shet5.setHeightInPoints(25);
       for(int positionTB=0;positionTB<dataTB.length;positionTB++){
       String value=dataTB[positionTB];
           c11=rw3shet5.createCell(positionTB);
        if(positionTB>4 && positionTB<dataTB.length-1){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionTB==5 || positionTB==6 || positionTB==17 || positionTB==18 || positionTB==29 || positionTB==30){ c11.setCellStyle(styleHeader);}
          
          if(positionTB==dataTB.length-1){
       if(errorTB>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
     }
         
  tbpos++;     
 }

    }
  
}      
        
        
        
        
        
//============================================================================================
//RETENTION REPORT        
//============================================================================================        
        
        if(3==3){
        
        
        
        
        
        
        			
			
 int retentionPos=0;
int HV0325=0;
double SA_Numerator,SA_PG = 0,SA_BF = 0,SA_Sub1,SA_Sub2,SA_5F,SA_14F,SA_19F,SA_20F,SA_5M,SA_14M,SA_19M,SA_20M;
double IA_Denominator,IA_PG = 0,IA_BF = 0,IA_Sub1,IA_Sub2,IA_5F,IA_14F,IA_19F,IA_20F,IA_5M,IA_14M,IA_19M,IA_20M;
double SA_F_Paeds,SA_M_Paeds,IA_F_Paeds,IA_M_Paeds,SA_F_Adult,SA_M_Adult,IA_F_Adult,IA_M_Adult;

int percentage,retentionPOS,errorRETENTION;
				
    
     
       allFacilities.clear();
//       year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
        
        
        String headerRETENTION[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Pregnant,Breastfeeding,sub-total,<5,10-14Y,15-19Y,20+Y,<5,10-14Y,15-19Y,20+Y,sub-total,Denominator,Pregnant,Breastfeeding,sub-total,<5,10-14Y,15-19Y,20+Y,<5,10-14Y,15-19Y,20+Y,sub-total,Verification Status ".split(",") ;
 percentage=81;
        
//              year=2015;
//        reportDuration="3";
        
       facilityIds1="";
       excelDuration="";
       
        period="";
        prevYear=year-1; 
        maxYearMonth=0;
        facilityIds="(";
        facilityIds1="(";
        retentionPos=0;
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
//       quarter=request.getParameter("quarter");
       quarter="3";
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

  HSSFSheet shetRETENTION=wb.createSheet("RETENTION");
  
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

for(int i=0;i<=5;i++){
    shetRETENTION.setColumnWidth(i, 4000);   
  }
  
HSSFRow  rw0shetRETENTION=shetRETENTION.createRow(0);
  rw0shetRETENTION.setHeightInPoints(30);
  HSSFCell  c1;
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw0shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
    }
 
 c1=rw0shetRETENTION.getCell(5);
 c1.setCellValue("NO. STILL ALIVE AND ON TREATMENT 12 MONTHS AFTER INITIATING ART"); 
 
 
 c1=rw0shetRETENTION.getCell(18);
 c1.setCellValue("NO. INITIATED ART IN 12 MONTHS PRIOR TO THE BEGINNING OF REPORTING PERIOD, DIED, STOPPED ART AND LOST TO FOLLOW-UPS."); 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,5,17));
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,18,30));
 
 HSSFRow  rw1shetRETENTION=shetRETENTION.createRow(1);
  rw1shetRETENTION.setHeightInPoints(30);
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw1shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
    }
 c1=rw1shetRETENTION.getCell(9);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 
 
 c1=rw1shetRETENTION.getCell(22);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 

   shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,9,17));
  shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,22,30)); 
  
  HSSFRow  rw2shetRETENTION=shetRETENTION.createRow(2);
  rw2shetRETENTION.setHeightInPoints(30);
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw2shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
         if(j<=5 || j==17 || j==18 || j==30){
        String headerInfor=headerRETENTION[j];
         c1.setCellValue(headerInfor);
         }
    }
  c1=rw2shetRETENTION.getCell(6);
 c1.setCellValue("DISAGGREGATED BY "); 
 
 c1=rw2shetRETENTION.getCell(9);
 c1.setCellValue("FEMALE"); 
 
 c1=rw2shetRETENTION.getCell(13);
 c1.setCellValue("MALE"); 
 
   c1=rw2shetRETENTION.getCell(19);
 c1.setCellValue("DISAGGREGATED BY "); 
 
 c1=rw2shetRETENTION.getCell(22);
 c1.setCellValue("FEMALE"); 
 
 c1=rw2shetRETENTION.getCell(26);
 c1.setCellValue("MALE"); 
 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,6,7));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,9,12)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,13,16));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,19,20)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,22,25));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,26,29)); 
  
  
  HSSFRow  rw3shetRETENTION=shetRETENTION.createRow(3);
  rw3shetRETENTION.setHeightInPoints(50);
  
    for(int headerpos=0;headerpos<headerRETENTION.length;headerpos++){
        String headerInfor=headerRETENTION[headerpos];
        c1=rw3shetRETENTION.createCell(headerpos);
         c1.setCellValue(headerInfor);
         c1.setCellStyle(styleHeader);
    }
 for (int i=0;i<=5;i++){
shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,i,i));     
 }  
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,17,17)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,18,18));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,30,30)); 
    
// GET STARTING ART DATA
 
 retentionPOS=3;
 String getData="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,"
            + "subpartnera.CentreSanteId,ART_Support,"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),SUM(HV0325) "
            + " FROM moh731 JOIN subpartnera "
            + "ON moh731.SubPartnerID=subpartnera.SubPartnerID "
            + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds+" "+duration+" && subpartnera.ART=1 "
            + "GROUP BY moh731.SubPartnerID " ;
       
//     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    while(conn.rs.next()){
        retentionPOS++;errorRETENTION=0;
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
      ARTSupport=conn.rs.getString(5);
      
 HV0321=conn.rs.getInt(6);
 HV0322=conn.rs.getInt(7);
 HV0323=conn.rs.getInt(8);
 HV0324=conn.rs.getInt(9);
 HV0325=conn.rs.getInt(10);       
 
 SA_F_Paeds=(float)Math.round((percentage*HV0322)/100);
 SA_F_Adult=(float)Math.round((percentage*HV0324)/100);
 
 SA_M_Paeds=(float)Math.round((percentage*HV0321)/100);
 SA_M_Adult=(float)Math.round((percentage*HV0323)/100);

 IA_F_Paeds=(float)Math.round((percentage*HV0322)/100);
 IA_F_Adult=(float)Math.round((percentage*HV0324)/100);
 
 IA_M_Paeds=(float)Math.round((percentage*HV0321)/100);
 IA_M_Adult=(float)Math.round((percentage*HV0323)/100);
 
 
SA_5F=(float)Math.round((percentage*35*HV0322)/(100*100)); 
SA_14F=(float)Math.round((percentage*65*HV0322)/(100*100));
SA_19F=(float)Math.round((percentage*2*HV0324)/(100*100)); 
SA_20F=(float)Math.round((percentage*98*HV0324)/(100*100));

SA_5M=(float)Math.round((percentage*35*HV0321)/(100*100)); 
SA_14M=(float)Math.round((percentage*65*HV0321)/(100*100));
SA_19M=(float)Math.round((percentage*2*HV0323)/(100*100)); 
SA_20M=(float)Math.round((percentage*98*HV0323)/(100*100)); 

SA_Sub1= (float)Math.round(SA_BF+SA_PG);

SA_Sub2= SA_F_Paeds+SA_F_Adult+SA_M_Paeds+SA_M_Adult;
SA_Numerator=SA_Sub2+SA_Sub1; 

IA_5F=(float)Math.round((percentage*35*HV0322)/(100*100)); 
IA_14F=(float)Math.round((percentage*65*HV0322)/(100*100));
IA_19F=(float)Math.round((percentage*2*HV0324)/(100*100)); 
IA_20F=(float)Math.round((percentage*98*HV0324)/(100*100));

IA_5M=(float)Math.round((percentage*35*HV0321)/(100*100)); 
IA_14M=(float)Math.round((percentage*65*HV0321)/(100*100));
IA_19M=(float)Math.round((percentage*2*HV0323)/(100*100)); 
IA_20M=(float)Math.round((percentage*98*HV0323)/(100*100)); 

IA_Sub1= (float)Math.round(IA_BF+IA_PG);
IA_Sub2= IA_F_Paeds+IA_F_Adult+IA_M_Paeds+IA_M_Adult;
IA_Denominator=IA_Sub1+IA_Sub2; 
//Normalizer code here========================================================================

//Normalize SA FEMALE PAEDS

double totalAdded=SA_5F+SA_14F;
if(totalAdded!=SA_F_Paeds){
if(totalAdded-SA_F_Paeds >2 || SA_F_Paeds-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>SA_F_Paeds) {
    if(pos<2){SA_14F--;} 
    else if(pos==2){SA_5F--;}
    else{pos=0;}
     totalAdded--;  
       pos++;
   }  
 
   while(totalAdded<SA_F_Paeds) {
    if(pos<2){SA_14F++;} 
    else if(pos==2){SA_5F++;}
    else{pos=0;}
     totalAdded++;  
       pos++;
   }
   
    
}
}
//NOMARLIZE SA FEMALE ADULTS
totalAdded=SA_19F+SA_20F;
if(totalAdded!=SA_F_Adult){
if(totalAdded-SA_F_Adult >2 || SA_F_Adult-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>SA_F_Adult) {
   SA_20F--; 
totalAdded--;  
 pos++;
   }  
 
   while(totalAdded<SA_F_Adult) {
    SA_20F++; 
totalAdded++;  
 pos++;
   }
   
    
}
}



//Normalize SA MALE PAEDS

totalAdded=SA_5M+SA_14M;
if(totalAdded!=SA_M_Paeds){
if(totalAdded-SA_M_Paeds >2 || SA_M_Paeds-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>SA_M_Paeds) {
    if(pos<2){SA_14M--;} 
    else if(pos==2){SA_5M--;}
    else{pos=0;}
     totalAdded--;  
       pos++;
   }  
 
   while(totalAdded<SA_M_Paeds) {
    if(pos<2){SA_14M++;} 
    else if(pos==2){SA_5M++;}
    else{pos=0;}
     totalAdded++;  
       pos++;
   }
   
    
}
}
//NOMARLIZE SA MALE ADULTS
totalAdded=SA_19M+SA_20M;
if(totalAdded!=SA_M_Adult){
if(totalAdded-SA_M_Adult >2 || SA_M_Adult-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>SA_M_Adult) {
   SA_20M--; 
totalAdded--;  
 pos++;
   }  
 
   while(totalAdded<SA_M_Adult) {
    SA_20M++; 
totalAdded++;  
 pos++;
   }
   
    
}
}

//IA NORMALIZER

//Normalize IA FEMALE PAEDS

totalAdded=IA_5F+IA_14F;
if(totalAdded!=IA_F_Paeds){
if(totalAdded-IA_F_Paeds >2 || IA_F_Paeds-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>IA_F_Paeds) {
    if(pos<2){IA_14F--;} 
    else if(pos==2){IA_5F--;}
    else{pos=0;}
     totalAdded--;  
       pos++;
   }  
 
   while(totalAdded<IA_F_Paeds) {
    if(pos<2){IA_14F++;} 
    else if(pos==2){IA_5F++;}
    else{pos=0;}
     totalAdded++;  
       pos++;
   }
   
    
}
}
//NOMARLIZE IA FEMALE ADULTS
totalAdded=IA_19F+IA_20F;
if(totalAdded!=IA_F_Adult){
if(totalAdded-IA_F_Adult >2 || IA_F_Adult-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>IA_F_Adult) {
   IA_20F--; 
totalAdded--;  
 pos++;
   }  
 
   while(totalAdded<IA_F_Adult) {
    IA_20F++; 
totalAdded++;  
 pos++;
   }
   
    
}
}



//Normalize IA MALE PAEDS

totalAdded=IA_5M+IA_14M;
if(totalAdded!=IA_M_Paeds){
if(totalAdded-IA_M_Paeds >2 || IA_M_Paeds-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>IA_M_Paeds) {
    if(pos<2){IA_14M--;} 
    else if(pos==2){IA_5M--;}
    else{pos=0;}
     totalAdded--;  
       pos++;
   }  
 
   while(totalAdded<IA_M_Paeds) {
    if(pos<2){IA_14M++;} 
    else if(pos==2){IA_5M++;}
    else{pos=0;}
     totalAdded++;  
       pos++;
   }
   
    
}
}
//NOMARLIZE IA MALE ADULTS
totalAdded=IA_19M+IA_20M;
if(totalAdded!=IA_M_Adult){
if(totalAdded-IA_M_Adult >2 || IA_M_Adult-totalAdded>2 ){
    errorRETENTION++;
}
//nomarlixer code
else{
 int pos=0;   
   while(totalAdded>IA_M_Adult) {
   IA_20M--; 
totalAdded--;  
 pos++;
   }  
 
   while(totalAdded<IA_M_Adult) {
    IA_20M++; 
totalAdded++;  
 pos++;
   }
   
    
}
}




String dataRETENTION []=(countyName+","+districtName+","+facilityName+","+mflcode+","+ARTSupport+","+SA_Numerator+","
           + ""+SA_PG+","+SA_BF+","+SA_Sub1+","+SA_5F+","
           + ""+SA_14F+","+SA_19F+","+SA_20F+","+SA_5M+","+SA_14M+","+SA_19M+","+SA_20M+","+SA_Sub2+","
           + ""+IA_Denominator+","+IA_PG+","+IA_BF+","+IA_Sub1+","+IA_5F+","+IA_14F+","
           + ""+IA_19F+","+IA_20F+","+IA_5M+","+IA_14M+","+IA_19M+","+IA_20M+","+IA_Sub2+",status").split(","); 

  HSSFRow rw4shetRETENTION=shetRETENTION.createRow(retentionPOS); 
       rw4shetRETENTION.setHeightInPoints(25);
       for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++){
       String value=dataRETENTION[positionRETENTION];
       HSSFCell    c11=rw4shetRETENTION.createCell(positionRETENTION);
        if(positionRETENTION>4 && positionRETENTION<(dataRETENTION.length-1)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionRETENTION==5 || positionRETENTION==8 || positionRETENTION==17 || positionRETENTION==18 || positionRETENTION==21 || positionRETENTION==30){ c11.setCellStyle(styleHeader);}
//          System.out.println("position "+positionPMTCT+" end v : "+dataPMTCT.length); 
       if(positionRETENTION==dataRETENTION.length-1){
//           System.out.println("entered here >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       if(errorRETENTION>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
        
    }
    
        
        
        
        
        
        
        
        
        
        }
        
        
        
        
        
        
    if(2==2){    
        
               
  //=============================VIRAL LOAD============================================
        
        
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
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Numerator","Denominator","<1","1-4","5-14","15-19","20+","<1","1-4","5-14","15-19","20+","Sub- Total","Numerator","Denominator","<1","1-4","5-14","15-19","20+","<1","1-4","5-14","15-19","20+","Sub-Total"};
            String sectionheaders0[]={"","","","","","No. Of Viral load tests conducted in the past 12 months with < 1000","No. of load tests performed in the reporting period","Female","","","","","Male","","","","","","No. of ART patients with viral load result documented within the past 12 months","No. on ART at least 6 months whose medical records were reviewed by Age and Sex","Female","","","","","Male","","","","","Sub-Total"};
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            String merge_row_col[]={"0,1,0,4","0,1,5,5","0,1,6,6","0,0,7,17","0,1,18,18","0,1,19,19","0,0,20,30","1,1,7,11","1,1,12,17","1,1,20,24","1,1,25,29"};
            
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
            String viralloadduration="";
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
                viralloadduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     viralloadduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     viralloadduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     viralloadduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
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
                viralloadduration=" 1=2 "; 
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
                
                subcountywhere = " and subpartnera.DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + viralloadduration + " " + countywhere + " " + subcountywhere;
            
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype,sum(numerator_un) as numerator_un  ,sum(denominator_un) as denominator_un,sum(less1_fun) as less1_fun,sum(1to4_fun) as 1to4_fun,sum(5to14_fun) as 5to14_fun, sum(15to19_fun) as 5to14_fun, sum(20_fun) as 20_fun ,sum(less1_mun) as less1_mun, sum(1to4_mun) as 1to4_mun,sum(5to14_mun) as 5to14_mun,sum(15to19_mun) as 15to19_mun ,sum(20_mun) as 20_mun,sum(subtotal_un) as subtotal_un ,sum(numerator_vi) as numerator_vi,sum(denominator_vi) as denominator_vi,sum(less1_fvi) as less1_fvi,sum(1to4_fvi) as 1to4_fvi ,sum(5to14_fvi) as 5to14_fvi,sum(15to19_fvi) as 15to19_fvi,sum(20_fvi) as 20_fvi,sum(less1_mvi) as less1_mvi ,sum(1to4_mvi) as 1to4_mvi, sum(5to14_mvi) as 5to14_mvi,sum(15to19_mvi) as 15to19_mvi,sum(20_mvi) as 20_mvi ,sum(subtotal_vi) as subtotal_vi, subpartnera.SubPartnerID as SubPartnerID  FROM viral_load join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on viral_load.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID ";
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
            
            HSSFSheet shet = wb.createSheet("VIRAL LOAD");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
             for (int a = 0; a < sectionheaders.length; a++) {
                HSSFCell clx = rw.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(style);
                shet.setColumnWidth(a, 3100);
            }
            
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("DATIM Viral Load Report For " + yearmonth);
            cl0.setCellStyle(stylex);              
           
            //create the first row
        
             HSSFCell cl00 = rw.createCell(5);
            cl00.setCellValue(sectionheaders0[5]);
            cl00.setCellStyle(stylex);
            
             HSSFCell cl01 = rw.createCell(6);
            cl01.setCellValue(sectionheaders0[6]);
            cl01.setCellStyle(stylex);
            
             HSSFCell cl02 = rw.createCell(18);
            cl02.setCellValue(sectionheaders0[18]);
            cl02.setCellStyle(stylex);
            
            HSSFCell cl03 = rw.createCell(19);
            cl03.setCellValue(sectionheaders0[19]);
            cl03.setCellStyle(stylex);
            
            HSSFCell cl1 = rw.createCell(7);
            cl1.setCellValue("TX_UNDETECT");
            cl1.setCellStyle(stylex);
           // shet.addMergedRegion(new CellRangeAddress(0,0,7,17));
            
             HSSFCell cl2 = rw.createCell(20);
            cl2.setCellValue("TX_VIRAL");
            cl2.setCellStyle(stylex);
           // shet.addMergedRegion(new CellRangeAddress(0,0,20,30));
            
          
            rowpos++;
            
            
            //row two
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);        
            
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders0[a]);
                clx.setCellStyle(stylex);
              
            }
              rowpos++;
                          
            //row three
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(38);
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(sectionheaders[a]);
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
       
            
             conn.rs=conn.st.executeQuery(getexistingdata);
    
    
    while(conn.rs.next()){
    
    
         int colpos=0; 
           int conpos=1; 
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
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                 
                 //facility name
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                   //mfl
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
//support type//######################################################################################
        if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
 //___   numerator_un       
      if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }               
         //denominator_un
        if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
         //fun_less1          
        if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                      
     //fun_1to4
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

                        }
          // fun_5to14
           
               if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
        
                     //fun_15to19               
                       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
        
      //fun_20
                       
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }              
   
         // mun_less1             
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }              
   
       
      //mun_1to4
       
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
       
       //mun_5to14,
        if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
       
       
      // mun_15to19,
        
         if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
        //mun_20,
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
   //subtotal_un,
          
            if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
         // numerator_vi
              if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
            
       //denominator_vi
              
                if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
  // fvi_less1
                
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }            
   //fvi_1to4
       
         if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
      //fvi_5to14
         
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
         
      //fvi_15to19
        if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }      
           
           
       //fvi_20,
        
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
        
        
   //mvi_less1
     if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }        
          
          
     //mvi_1to4,
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
     
     
     //mvi_5to14,
         if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
       
       //mvi_15to19,
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        } 
         
     //mvi_20 ,
           
       if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }       
           
   //subtotal_vi,
       
     if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }     
               
        rowpos++;
    }
    
    
    }//end of viral load report
    
 //============================================================================================
    
   // new report pep smear
 //============================================================================================
    if(4==5){
    
     
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "moh731";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            
            
            String mainheaders[]={"","","Female","","","","","","Male","","","","","Disagggregated by Type of Service",""};
            String sectionheaders[]={"Numerator","Female","<10","10-14","15-17","18-24","25+","Male","<10","10-14","15-17","18-24","25+","Sexual Violence (Post Rape Care)","PHYSICAL and/or EMOTIONAL Violence (Other Post GBV Care)"};
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            String merge_row_col[]={"0,0,0,14","1,1,0,1","1,1,2,6","1,1,8,12","1,1,13,14"};
            
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
            String viralloadduration="";
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
                viralloadduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     viralloadduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     viralloadduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     viralloadduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
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
                            Logger.getLogger(datimTbViralRetention.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(datimTbViralRetention.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (reportDuration.equals("4")) {
                //on monthly reports, i dont expect any output since viral load is entered quarterly
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                viralloadduration=" 1=2 "; 
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
                
                subcountywhere = " and subpartnera.DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + viralloadduration + " " + countywhere + " " + subcountywhere;
            
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype,sum(HV0508) as numerator  ,(sum(HV0502)+sum(HV0504)+sum(HV0506)) as femaletotal,(sum(HV0501)+sum(HV0503)+sum(HV0505)) as maletotal ,(sum(HV0503)+sum(HV0504)) as postrapecare, (sum(HV0501)+sum(HV0502)+sum(HV0505)+sum(HV0506)) as otherpostgbv, subpartnera.SubPartnerID as SubPartnerID  FROM moh731 join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on moh731.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID ";
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
            
            HSSFSheet shet = wb.createSheet("POST-GBV Care");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
             for (int a = 0; a < mainheaders.length; a++) {
                HSSFCell clx = rw.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(style);
                shet.setColumnWidth(a, 3100);
            }
            
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("Number of people receiving post-GBV care Report For " + yearmonth);
            cl0.setCellStyle(stylex);              
           
            //create the first row
       //ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
           // shet.addMergedRegion(new CellRangeAddress(0,0,20,30));
            
          
            rowpos++;
            
            
            //row two
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);        
            
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
              //ZZZ   clx.setCellValue(sectionheaders0[a]);
                clx.setCellStyle(stylex);
              
            }
              rowpos++;
                          
            //row three
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(38);
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(sectionheaders[a]);
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
       
            
             conn.rs=conn.st.executeQuery(getexistingdata);
    
    
    while(conn.rs.next()){
    
    
         int colpos=0; 
           int conpos=1; 
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
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                 
                 //facility name
           if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase() + conn.rs.getString(conpos).substring(1).toLowerCase());
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
                   //mfl
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellStyle(style2);

            colpos++;
            conpos++;

        }
        
    } 
        
        
    
    
    }
    //======================================================================================
    //======================================================================================
    
    
            
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
            response.setHeader("Content-Disposition", "attachment; filename=TB_RETENTION_VIRALLOAD_Generatted_On_" + createdOn + ".xls");
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

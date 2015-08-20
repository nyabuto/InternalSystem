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
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
              try {
            response.setContentType("text/html;charset=UTF-8");
            
            dbConn conn = new dbConn();
        HSSFWorkbook wb=new HSSFWorkbook(); 
//============================================================================================
//TB    Place tb code on the if      
//============================================================================================
if(2==2){

    
  
  
}      
        
        
        
        
        
//============================================================================================
//RETENTION REPORT        
//============================================================================================        
        
        if(3==3){
        
        
        
        
        
        
        			
			
int year,month,prevYear,maxYearMonth,retentionPos;
String reportDuration,duration = null,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;

int HV0321,HV0322,HV0323,HV0324,HV0325;
double SA_Numerator,SA_PG = 0,SA_BF = 0,SA_Sub1,SA_Sub2,SA_5F,SA_14F,SA_19F,SA_20F,SA_5M,SA_14M,SA_19M,SA_20M;
double IA_Denominator,IA_PG = 0,IA_BF = 0,IA_Sub1,IA_Sub2,IA_5F,IA_14F,IA_19F,IA_20F,IA_5M,IA_14M,IA_19M,IA_20M;
double SA_F_Paeds,SA_M_Paeds,IA_F_Paeds,IA_M_Paeds,SA_F_Adult,SA_M_Adult,IA_F_Adult,IA_M_Adult;

String excelDuration;
ArrayList allFacilities = new ArrayList();
String ARTSupport;
String createdOn,period;
int percentage,retentionPOS,errorRETENTION;
				
       session = request.getSession();
     
       allFacilities.clear();
//       year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
        
        
        String headerRETENTION[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Pregnant,Breastfeeding,sub-total,<5,10-14Y,15-19Y,20+Y,<5,10-14Y,15-19Y,20+Y,sub-total,Denominator,Pregnant,Breastfeeding,sub-total,<5,10-14Y,15-19Y,20+Y,<5,10-14Y,15-19Y,20+Y,sub-total,Verification Status ".split(",") ;
 percentage=81;
        
              year=2015;
        reportDuration="3";
        
       String facilityIds1="";
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

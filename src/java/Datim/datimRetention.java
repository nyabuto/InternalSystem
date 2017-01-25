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
public class datimRetention extends HttpServlet {
HttpSession session;
int year,month,prevYear,maxYearMonth,retentionPos;
String reportDuration,duration,semi_annual,quarter;
String facilityName,mflcode,countyName,districtName,facilityIds,facilityId;

int HV0321,HV0322,HV0323,HV0324,HV0325;
double SA_Numerator,SA_PG,SA_BF,SA_Sub1,SA_Sub2,SA_5F,SA_14F,SA_19F,SA_20F,SA_5M,SA_14M,SA_19M,SA_20M;
double IA_Denominator,IA_PG,IA_BF,IA_Sub1,IA_Sub2,IA_5F,IA_14F,IA_19F,IA_20F,IA_5M,IA_14M,IA_19M,IA_20M;
double SA_F_Paeds,SA_M_Paeds,IA_F_Paeds,IA_M_Paeds,SA_F_Adult,SA_M_Adult,IA_F_Adult,IA_M_Adult;

String excelDuration;
ArrayList allFacilities = new ArrayList();
String ARTSupport;
String createdOn,period;
int percentage,retentionPOS,errorRETENTION;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       session = request.getSession();
       dbConn conn = new dbConn();
       
        

  Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  
  String mwaka=request.getParameter("year");
  
  String facilitiestable="subpartnera";
  
  int selectedyear=new Integer(mwaka);
  
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
       
       
       allFacilities.clear();
//       year=Integer.parseInt(request.getParameter("year"));
//        reportDuration=request.getParameter("reportDuration");
        
        
        String headerRETENTION[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Pregnant,Breastfeeding,sub-total,<5,5-14Y,15-19Y,20+Y,<5,5-14Y,15-19Y,20+Y,sub-total,Denominator,Pregnant,Breastfeeding,sub-total,<5,5-14Y,15-19Y,20+Y,<5,5-14Y,15-19Y,20+Y,sub-total,Verification Status ".split(",") ;
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
    String getDist="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
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
         String getCounty="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
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
     
     HSSFWorkbook wb=new HSSFWorkbook();
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
 String getData="SELECT "+facilitiestable+".SubPartnerNom,district.DistrictNom,county.County,"
            + ""+facilitiestable+".CentreSanteId,ART_Support,"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),SUM(HV0325) "
            + " FROM moh731 JOIN "+facilitiestable+" "
            + "ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds+" "+duration+" && "+facilitiestable+".ART=1 "
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
        Logger.getLogger(datimRetention.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(datimRetention.class.getName()).log(Level.SEVERE, null, ex);
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

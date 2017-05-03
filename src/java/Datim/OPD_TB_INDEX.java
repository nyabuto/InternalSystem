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
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

/**
 *
 * @author Emmanuel E
 */
public class OPD_TB_INDEX extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            dbConn conn = new dbConn();
                 HSSFWorkbook wb=new HSSFWorkbook(); 
               String subcounty_countywhere=" (1=1) and ";  
                 
    
             
                 
            boolean isOPD=false;
            boolean isVCT=false;
            boolean isIPD=false;
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC OPD
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 int totalopdglobal=0;
                 if(6==6){ //new htc for PITC 
                  
                     isOPD=true;
                     isIPD=false;
                     isVCT=false;
                     
                     //2017
                     String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Positive","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","Total OPD Tested","Total Pediatric tested ","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total OPD Tested","Total Pediatric tested ","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","F","M","<1","<1","1-9Y","1-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-49Y","25-49Y","50+","50+","F","M","<1","<1","1-9Y","1-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-49Y","25-49Y","50+","50+","Total OPD Tested","Total Pediatric tested ","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     
                     
                     
             
              
              
                     ArrayList allFacilities = new ArrayList();
                     allFacilities.clear();
                 int year,month,prevYear,maxYearMonth,mflcode;
String reportDuration,duration,semi_annual,quarter;
String facilityName,countyName,districtName,facilityIds,facilityId;

   year=month=prevYear=maxYearMonth=mflcode=0;
 reportDuration=duration=semi_annual=quarter="";
 facilityName=countyName=districtName=facilityIds=facilityId="";

 
  year=Integer.parseInt(request.getParameter("year"));
  
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
  
 
   String facilityIds1="";
        facilityIds1="(";
           if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
           subcounty_countywhere=" ( district.DistrictID='"+subcounty+"') and ";//20160711
    
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
 
     facilityIds1+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
     
      facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
         
         subcounty_countywhere=" (county.CountyID='"+county+"') and  ";//20160711
         
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
    
     facilityIds1+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
                         }
   
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";     
     }
       
        else{
  
       facilityIds1="";    
        }   
        
     }      
        reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
//        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration1=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period1="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM SEMI - ANNUAL DATA REPORT FOR PERIOD : OCT "+prevYear+" to MARCH "+year;
       }
           else{
       duration1=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
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
      duration1=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period1="DATIM QUARTERLY DATA REPORT FOR PERIOD : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration1=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
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
     duration1=" moh731.yearmonth="+prevYear+""+month;    
     period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration1=" moh731.yearmonth="+year+"0"+month;  
    period1="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration1="";     
      }
        
      HSSFSheet shet3=wb.createSheet("Other PITC (OPD)");   
            HSSFCell  c11;
            
            
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
  shet3.setColumnWidth(2,5000);  
  //shet3.setColumnWidth(6,5000);
  
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXnew 20160725
  //String newheader0="COUNTY,SUB-COUNTY,FACILITY,MFL-CODE,TYPE OF SUPPORT,ART High Volume,HTC High Volume,PMTCT High Volume,TOTAL HIV+,TOTAL +VE(F),NUM,FEMALE(POSITIVE),,,,,,,,,MALE (POSITIVE),,,,,,,,NEGATIVE,FEMALE (NEGATIVE),,,,,,,,,MALE (NEGATIVE),,,,,,,, ,,,,,,,,";
  //String newheader1="COUNTY,SUB-COUNTY,FACILITY,MFL-CODE,TYPE OF SUPPORT,ART High Volume,HTC High Volume,PMTCT High Volume,TOTAL HIV+,TOTAL +VE(F),NUM,Paeds <15Yr,,,,Adults 15+Yr,,,,TOTAL +VE MALE,Paeds < 15Yr,,,,Adults 15+Yr,,,,TOTAL -VE(F),Paeds <15Yr,,,,Adults 15+Yr,,,,TOTAL -VE(M),Paeds <15Yr,,,,Adults 15+Yr,,,,Female,,Male,,Sub-total,Positive,Negative,Sub-total,Verification Status";
  //String newheader2="COUNTY,SUB-COUNTY,FACILITY,MFL-CODE,TYPE OF SUPPORT,ART High Volume,HTC High Volume,PMTCT High Volume,TOTAL HIV+,TOTAL +VE(F),NUM,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,TOTAL +VE MALE,<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,TOTAL -VE(F),<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,TOTAL -VE(M),<1,1-4Y,5-9Y,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,< 15,15 +,< 15,15 +,Sub-total,Positive,Negative,Sub-total,Verification Status";

  //String header0array[]=newheader0.split(",");
  //String header1array[]=newheader1.split(",");
  //String header2array[]=newheader2.split(",");
  
  //create header1
   HSSFRow rw0=shet3.createRow(0);
           rw0.setHeightInPoints(30);
           
           HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
         
      //_____________________________________________________________report heading row 0   
      c1.setCellValue(period1);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=pitc_ipd_header1.length-1;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      
      //-----------------------------------row 1 header 
       rw0=shet3.createRow(2); 
       rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header1.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header1[i]);
      clx.setCellStyle(stylemainHeader);
        }
 //-----------------------------------row 2 header 
       rw0=shet3.createRow(3); 
       rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header2.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header2[i]);
      clx.setCellStyle(stylemainHeader);
        }
      
      
     
    //-----------------------------------row 3 header 
   rw0=shet3.createRow(4); 
   rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header3.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header3[i]);
      clx.setCellStyle(stylemainHeader);
       }
    String mergeinfor[]={"0,0,0,40","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,2,5,20","2,2,21,36","2,4,37,37","2,4,38,38","2,4,39,39","2,4,40,40","3,3,5,6","3,3,21,22","2,4,42,42","2,4,41,41","2,4,43,43","2,4,44,44","2,4,45,45"};  
   
    //do the merging
    
    for(int d=0;d<mergeinfor.length;d++){
    if(!mergeinfor[d].equals("")){
        String pos[]=mergeinfor[d].split(",");
     shet3.addMergedRegion(new CellRangeAddress(new Integer(pos[0]),new Integer(pos[1]),new Integer(pos[2]),new Integer(pos[3])));   
    }
                                         }
    
  //20151009
      
       
    
    
    
    
    
    
    
    
    
    double checkdiff=0;
    int count=4;
   
    
    //---------------------------------------------------------------------------
    
 
 
 
      //BEFORE WHILE LOOP
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    ArrayList staticfacility= new ArrayList();
    ArrayList staticcounty= new ArrayList();
    ArrayList staticdistrict= new ArrayList();
    ArrayList staticmfl= new ArrayList();
    ArrayList staticdsd_ta= new ArrayList();
    
    ArrayList staticart_hv= new ArrayList();
    ArrayList statichtc_hv= new ArrayList();
    ArrayList staticpmtct_hv= new ArrayList();
     ArrayList staticishtc= new ArrayList();
    ArrayList staticispmtct= new ArrayList();
    
    int blankrows=pitc_ipd_header1.length;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport, IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume , IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT"
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 or PMTCT=1) group by "+facilitiestable+".SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getstaticfacilities);
    while(conn.rs.next())
    {
    
     staticcounty.add(conn.rs.getString("county"));
     district=conn.rs.getString("district");
     staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     staticfacility.add(conn.rs.getString("facility"));
     staticmfl.add(conn.rs.getString("mflcode"));
     if(conn.rs.getString("ART_highvolume")!=null){staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {staticart_hv.add(""); }
    if(conn.rs.getString("HTC_highvolume")!=null){statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {staticpmtct_hv.add("");}
     if(conn.rs.getString("HTC")!=null){staticishtc.add(conn.rs.getString("HTC"));} else {staticishtc.add("");}
if(conn.rs.getString("PMTCT")!=null){staticispmtct.add(conn.rs.getString("PMTCT"));} else {staticispmtct.add("");}
     //staticmfl.add(conn.rs.getString("mflcode"));
     
     
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     staticdsd_ta.add(dsdta);   
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 
 
    
    String facilid="";
    String facilname="";
    String dsdta="";
   
    
    
    
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
  
          TestedAdultMale=0;TestedAdultFemale=0;
 TestedChildMale=0;TestedChildFemale=0;
 HIV_AdultMale=0;HIV_AdultFemale=0;
 HIV_ChildMale=0;HIV_ChildFemale=0;
	
    
 
  //deduct   
          
//    String get731data="SELECT "
//            + " sum(HV0103) as 711_totaltested, "
//            + " sum(HV0110) as 711_less15m ,"
//            + " sum(HV0111) as 711_less15f ,"
//            + " sum(HV0112) as 711_15_24m ,"
//            + " sum(HV0113) as 711_15_24f ,"
//            + " sum(HV0114) as 711_25m ,"
//            + " sum(HV0115) as 711_25f ,"
//            + " sum(HV0110+HV0111+HV0112+HV0113+HV0114+HV0115) as 711_totalpositive ," //updated in 201607            
//            + " county.County,district.DistrictNom,"
//            + ""+facilitiestable+".SubPartnerNom, "+facilitiestable+".CentreSanteId,"+facilitiestable+".HTC_Support1 ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
//           +" ,sum( HV0103 + HV0201 ) as NUM,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0)  SUM(HV0226) as serology "//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
//          
    
    String get731data="SELECT  "
            + " numerator as 711_totaltested,"
            + "sum(pos_female_noage+ pos_female_less1 +  pos_female_1to9+pos_female_10to14) as 711_less15f,"
            + "sum(pos_male_noage+pos_male_less1+pos_male_1to9+ pos_male_10to14) as 711_less15m,"
            + "sum(pos_female_15to19+ pos_female_20to24) as 711_15_24f,"
            + "sum(pos_male_15to19+pos_male_20to24) as 711_15_24m,"
            + "sum(pos_female_25to49+ pos_female_50) as 711_25f,"
            + "sum(pos_male_25to49+pos_male_50) as 711_25m, "
            + " totalpositive as 711_totalpositive ,"
            +" county.county as County,DistrictNom as DistrictNom, SubPartnerNom as SubPartnerNom, mflcode as CentreSanteId, HTC_Support1 ,"
            + "  IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, "
            + " "
            +" subpartnera.HTC, "
            +" subpartnera.PMTCT,indicator "
            + "FROM internal_system.opd_index_tb  join subpartnera on opd_index_tb.mflcode=subpartnera.CentreSanteId  JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON district.CountyID=county.CountyID where indicator='1. OPD' group by opd_index_tb_id";
    
     System.out.println("2017q1 IPD : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
    while(conn.rs.next()){
     
        
        
        double tbtested=0;
        double indextested=0;
        
        //below15
        double tbless15posm=0;
        double tbless15posf=0;
        double indexless15posm=0;
        double indexless15posf=0;
        //15to24
        double tb15to24posm=0;
        double tb15to24posf=0;
        double index15to24posm=0;
        double index15to24posf=0;
        
        //50+        
        double tb50posm=0;
        double tb50posf=0;
        double index50posm=0;
        double index50posf=0;
        
        
        double tbpositivettl=0;
        double indexpositivettl=0;
        
        
       //get TB numbers
        
        String gettb=" SELECT  "
            + " IFNULL(numerator,0) as 711_totaltested,"
            + " IFNULL(sum(pos_female_noage+ pos_female_less1 +  pos_female_1to9+pos_female_10to14),0) as 711_less15f,"
            + " IFNULL(sum(pos_male_noage+pos_male_less1+pos_male_1to9+ pos_male_10to14),0) as 711_less15m,"
            + " IFNULL(sum(pos_female_15to19+ pos_female_20to24),0) as 711_15_24f,"
            + " IFNULL(sum(pos_male_15to19+pos_male_20to24),0) as 711_15_24m,"
            + " IFNULL(sum(pos_female_25to49+ pos_female_50),0) as 711_25f,"
            + " IFNULL(sum(pos_male_25to49+pos_male_50),0) as 711_25m, "
            + " IFNULL(totalpositive,0) as 711_totalpositive "
            + " FROM internal_system.opd_index_tb where indicator='3. TB' and mflcode='"+conn.rs.getString("CentreSanteId")+"'" ;
        
      conn.rs2=conn.st2.executeQuery(gettb);
      while (conn.rs2.next()){
      
           tbtested=conn.rs2.getDouble(1);
           
           tbless15posf=conn.rs2.getDouble(2);
           tbless15posm=conn.rs2.getDouble(3);
         
           tb15to24posf=conn.rs2.getDouble(4);
           tb15to24posm=conn.rs2.getDouble(5);
          
           tb50posf=conn.rs2.getDouble(6);
           tb50posm=conn.rs2.getDouble(7);
           
           tbpositivettl=conn.rs2.getDouble(8);
          
      }
        
      
      
       //get Index numbers
        
        String getindex=" SELECT  "
            + " IFNULL(numerator,0) as 711_totaltested,"
            + " IFNULL(sum(pos_female_noage+ pos_female_less1 +  pos_female_1to9+pos_female_10to14),0) as 711_less15f,"
            + " IFNULL(sum(pos_male_noage+pos_male_less1+pos_male_1to9+ pos_male_10to14),0) as 711_less15m,"
            + " IFNULL(sum(pos_female_15to19+ pos_female_20to24),0) as 711_15_24f,"
            + " IFNULL(sum(pos_male_15to19+pos_male_20to24),0) as 711_15_24m,"
            + " IFNULL(sum(pos_female_25to49+ pos_female_50),0) as 711_25f,"
            + " IFNULL(sum(pos_male_25to49+pos_male_50),0) as 711_25m, "
            + " IFNULL(totalpositive,0) as 711_totalpositive "
            + " FROM internal_system.opd_index_tb where indicator='2. Index Testing' and mflcode='"+conn.rs.getString("CentreSanteId")+"'" ;
        
      conn.rs2=conn.st2.executeQuery(getindex);
      while (conn.rs2.next()){
      
           indextested=conn.rs2.getDouble(1);
           
           indexless15posf=conn.rs2.getDouble(2);
           indexless15posm=conn.rs2.getDouble(3);
         
           index15to24posf=conn.rs2.getDouble(4);
           index15to24posm=conn.rs2.getDouble(5);
          
           index50posf=conn.rs2.getDouble(6);
           index50posm=conn.rs2.getDouble(7);
           
           indexpositivettl=conn.rs2.getDouble(8);
          
      }
        
        
     
      
           indextested+=tbtested;           
           indexless15posf+=tbless15posf;
           indexless15posm+=tbless15posm;         
           index15to24posf+=tb15to24posf;
           index15to24posm+=tb15to24posm;          
           index50posf+=tb50posf;
           index50posm+=tb50posm;           
           indexpositivettl+=tbpositivettl;
      
                
//INSIDE WHILE LOOP
	  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
        //REMOVE SITES THAT HAVE DATA FROM THE STATIC ARRAYLIST SET
        
        //get the index of the current facility
        int mflindex=staticmfl.indexOf(conn.rs.getString("CentreSanteId"));
        
        if(mflindex!=-1){        
           //remove the element from the arraylist 
             staticfacility.remove(mflindex);
             staticcounty.remove(mflindex);
             staticdistrict.remove(mflindex);
             staticmfl.remove(mflindex);
             staticdsd_ta.remove(mflindex);
             staticart_hv.remove(mflindex);
             statichtc_hv.remove(mflindex);
             staticpmtct_hv.remove(mflindex);
             staticishtc.remove(mflindex);
staticispmtct.remove(mflindex);
        
                        }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
        
        
       county=conn.rs.getString(9);
     district=conn.rs.getString(10);
     district=district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase();
     facilityname=conn.rs.getString(11);
     mflcode=conn.rs.getInt(12); 
     if(conn.rs.getString(13)==null){
     dsdta="DSD";
     }
     else {
     dsdta=conn.rs.getString(13);   
     }
     
     int arthv=0;
     int htchv=0;
     int pmtcthv=0;
     
      if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getInt("ART_highvolume");}
      if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getInt("HTC_highvolume"); }
      if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getInt("PMTCT_highvolume");}
     
      
      
      //==============================================NEW2017Q1===========================================================
      
      
       //________________________________________________________________________________________________________________________________________________________________________
       //________________________________________________________________________________________________________________________________________________________________________
       //________________________________________________________________________________________________________________________________________________________________________
       //________________________________________________________________________________________________________________________________________________________________________
       //________________________________________________________________________________________________________________________________________________________________________
       //NEW CHANGES 201701  CHANGING DATA OUTPUT into the new datim format 
       //Here Ratios are being applied
       //isARTsite
       double htctestedratio=1;
       double htcpositiveratio=1;
       
    
       double opd_tes_balancing=1;
       double ipd_tes_balancing=1;
       double vct_tes_balancing=1;
       
       
       
       //double ipdpositiveratio=0;
       //double opdpositiveratio=0;
      // double vctpositiveratio=0;
       
       
    //============================================================================================================================START NEW RATIOS===================================
    
    
    
  //%%%%%%%%%%%%%%%%added 201606 %%%%%%%%%%%%%%%%%%%%%% 
     // 38 (M)%  ---	62% (F)

     
   
  double tested_new711=(float)Math.round((conn.rs.getInt("711_totaltested")*htctestedratio));
  
  //|__|
        System.out.println(" 2017Q1__"+tested_new711);
        
        tested_new711-=indextested;//deduct tb and index testing tested values
        
       
       
  double testedmale_711=(float)Math.round((0.38*tested_new711));
  double testedfemale_711=(float)Math.round((0.62*tested_new711));
  
   double tofautimpya=tested_new711-(testedmale_711+testedfemale_711);
  if(tofautimpya!=0){
 
  testedfemale_711+=tofautimpya;
  
  
  }
        //System.out.println("**2016_06_ "+testedmale_711+ "~ "+testedfemale_711+" ~ "+ tested_new711);
        
     //12%      88%
       
    //this will be defined from ratios 
    TestedAdultFemale=(int)Math.round((0.88*testedfemale_711));  //adult   
    TestedChildFemale=(int)Math.round((0.12*testedfemale_711)); //child
    
   tofautimpya=testedfemale_711 -(TestedAdultFemale+TestedChildFemale);   
    if(tofautimpya!=0){
  TestedAdultFemale+=tofautimpya;  
  }
    //  17%  83%   
    
    //TestedAdultMale=conn.rs.getInt(2);
    //TestedChildMale=conn.rs.getInt(6);
   
    
    TestedAdultMale=(int)Math.round((0.83*testedmale_711));  //adult   
    TestedChildMale=(int)Math.round((0.17*testedmale_711)); //child
    
   tofautimpya=testedmale_711 -(TestedAdultMale+TestedChildMale);   
    if(tofautimpya!=0){
        
  TestedAdultMale+=tofautimpya;  
   }
     
    
    
    
    
    int hivpos_711_15_24f=(int) Math.round((conn.rs.getInt("711_15_24f")*htcpositiveratio));//|__|
    int hivpos_711_15_24m=(int) Math.round((conn.rs.getInt("711_15_24m")*htcpositiveratio));//|__|
    
        if (hivpos_711_15_24f < index15to24posf) {

            System.out.println(facilityname + " ~~~15to24F" + hivpos_711_15_24f + "-" + index15to24posf + "=" + (hivpos_711_15_24f - index15to24posf));

        }

        if (hivpos_711_15_24m < index15to24posm) {

            System.out.println(facilityname + "  ~~~15to24M" + hivpos_711_15_24m + "-" + index15to24posm + "=" + (hivpos_711_15_24m - index15to24posm));

        }
    
    hivpos_711_15_24f=(int) (hivpos_711_15_24f-index15to24posf);
    hivpos_711_15_24m=(int) (hivpos_711_15_24m-index15to24posm);
    
 
    
      
    
    int hivpos_711_25m=(int) Math.round((conn.rs.getInt("711_25m")*htcpositiveratio));//|__|
    int hivpos_711_25f=(int) Math.round((conn.rs.getInt("711_25f")*htcpositiveratio));//|__|
    
    
    if (hivpos_711_25f < index50posf) {

            System.out.println(facilityname + "  ~~~25to50+F" + hivpos_711_25f + "-" + index50posf + "=" + (hivpos_711_25f - index50posf));

        }

        if (hivpos_711_25m < index50posm) {

            System.out.println(facilityname + "~~~25to50+M" + hivpos_711_25m + "-" + index50posm + "=" + (hivpos_711_25m - index50posm));

        }
    
    
    hivpos_711_25m=(int) (hivpos_711_25m-index50posm);
    hivpos_711_25f=(int) (hivpos_711_25f-index50posf);
            
            
    HIV_AdultFemale=hivpos_711_15_24f+ hivpos_711_25f;//
    HIV_AdultMale=hivpos_711_15_24m+ hivpos_711_25m; 
    
    HIV_ChildFemale=(int) Math.round((conn.rs.getInt("711_less15f")*htcpositiveratio)); //|__|
    HIV_ChildMale=(int) Math.round((conn.rs.getInt("711_less15m")*htcpositiveratio));   //|__|
    
    
      if (HIV_ChildFemale < indexless15posf) {

            System.out.println(facilityname + "~~~<15F " + HIV_ChildFemale + "-" + indexless15posf + "=" + (HIV_ChildFemale - indexless15posf));

        }

       if (HIV_ChildMale < indexless15posm) {

            System.out.println(facilityname + "~~~<15M " + HIV_ChildMale + "-" + indexless15posm + "=" + (HIV_ChildMale - indexless15posm));

        }
    
    
    HIV_ChildFemale=(int) (HIV_ChildFemale-indexless15posf);
    HIV_ChildMale=(int) (HIV_ChildMale-indexless15posm);
    
    
                      
           indexpositivettl+=tbpositivettl;
    
    
    
    //============================================================================================================================END NEW RATIOS====================
    
    
    
   
String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
String arrayDetails []=basicDetails.split("@");

  count++;
  rw0=shet3.createRow(count);
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++)
   {
    
  HSSFCell  S3cell=rw0.createCell(facilno);
    S3cell.setCellValue(arrayDetails[j]);
//    System.out.println(arrayDetails[j]);
    S3cell.setCellStyle(stborder);  
    System.out.println("facildet pos : "+facilno+"     det : "+arrayDetails[j]);
     facilno++;  
   }
        
   
   
 
 
  //========================NEW FINE AGE RATIOS added 201607=====================================
  //prior to this level, ensure all the main variables below being subjected to a ratio have already been subjected to the respective percentage per service area 
   //i.e. IPD, OPD, VCT 
  //the main variables are 
   //TestedAdultFemale   TestedChildFemale  hivpos_711_15_24f  hivpos_711_25f HIV_ChildFemale
   //TestedAdultMale   TestedChildMale  hivpos_711_15_24m  hivpos_711_25m HIV_ChildMale
//      FEMALES
//adult
FemaleAdultTested19=(float)Math.round((0.13*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((0.26*TestedAdultFemale));
FemaleAdultTested49=(float)Math.round((0.54*TestedAdultFemale));
FemaleAdultTested50=(float)Math.round((0.07*TestedAdultFemale));
//children
FemaleTestedChild1=(float)Math.round((0*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((0.27*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((0.30*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((0.43*TestedChildFemale));

//postive 
//adult  ** remaining 
//hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
AdultFemaleHIV19=(float)Math.round((0.23*hivpos_711_15_24f));
AdultFemaleHIV24=(float)Math.round((0.77*hivpos_711_15_24f));
AdultFemaleHIV49=(float)Math.round((0.90*hivpos_711_25f));
AdultFemaleHIV50=(float)Math.round((0.10*hivpos_711_25f));

    
//positive

//children
ChildFemaleHIV1=(float)Math.round((0*HIV_ChildFemale));
ChildFemaleHIV4=(float)Math.round((0.45*HIV_ChildFemale));
ChildFemaleHIV9=(float)Math.round((0.25*HIV_ChildFemale));
ChildFemaleHIV14=(float)Math.round((0.30*HIV_ChildFemale));



// MALES  
//adult
  MaleAdultTested19=(float)Math.round((0.13*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((0.20*TestedAdultMale));
  MaleAdultTested49=(float)Math.round((0.56*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((0.11*TestedAdultMale));

  //children
  MaleTestedChild1=(float)Math.round((0*TestedChildMale));
  MaleTestedChild4=(float)Math.round((0.26*TestedChildMale));
  MaleTestedChild9=(float)Math.round((0.29*TestedChildMale));
  MaleTestedChild14=(float)Math.round((0.45*TestedChildMale));

//positive
  //adult ** remaining 
  //hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
  AdultMaleHIV19=(float)Math.round((0.36*hivpos_711_15_24m));
  AdultMaleHIV24=(float)Math.round((0.64*hivpos_711_15_24m));
  AdultMaleHIV49=(float)Math.round((0.79*hivpos_711_25m));
  AdultMaleHIV50=(float)Math.round((0.21*hivpos_711_25m));

  
  //positives
  //children
  ChildMaleHIV1=(float)Math.round((0*HIV_ChildMale));
  ChildMaleHIV4=(float)Math.round((0.37*HIV_ChildMale));
  ChildMaleHIV9=(float)Math.round((0.27*HIV_ChildMale));
  ChildMaleHIV14=(float)Math.round((0.36*HIV_ChildMale));
 
  
  
  
  
  
  
          
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
 
   
   
   
   
   

   adderPos=0;
   double Totalhivfemale=0;
   double Totalhivmale=0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   splitData=0;  adderPos=0;
            childSplitData=0;   
//   // adult female hiv+
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
 
 

  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  

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

 
 
 
 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 

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
 
 /*
 
   int balancingvalue=(int) (conn.rs.getInt("711_totaltested")-(ipd_tes_balancing+tested_new711+vct_tes_balancing));
        tested_new711+=balancingvalue;//add the missing value that you got when you deducted vct+opd+IPD from HTC in moh731 
        AdultMaleHIV24Neg+=balancingvalue;//add the missing value that you got when you deducted vct+opd+IPD from HTC in moh731 to male 20-25 negative 
 
        */
       				     
 
double posotiveyote= ChildFemaleHIV1+ChildMaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildMaleHIV4+ChildMaleHIV9+ChildFemaleHIV14+ChildMaleHIV14+AdultFemaleHIV19+AdultMaleHIV19+AdultFemaleHIV24+AdultMaleHIV24+AdultFemaleHIV49+AdultMaleHIV49+AdultFemaleHIV50+AdultMaleHIV50;
 
 
   //double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f);
double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(posotiveyote);
   //711_totalpositive 
    
    
   /** 
       // System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+" Total positive :"+(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))+"-"+(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f)+"="+balancegeneral);
   
       
while(balancegeneral<0){
    if(AdultFemaleHIV49!=0){
         //deduct from pos and add to negative since the negatives were achieved from tested-positive
 AdultFemaleHIV49-=1; 
 AdultFemaleHIV49Neg+=1; 
    }
    else {
        //deduct from pos and add to negative since the negatives were achieved from tested-positive
    AdultFemaleHIV24-=1;
    AdultFemaleHIV24Neg+=1;
    
    }
 balancegeneral++;
  
                 }

while(balancegeneral>0){
 //add to pos and add deduct from negative since the negatives were achieved from tested-positive
 AdultFemaleHIV49+=1; 
    
 AdultFemaleHIV49Neg-=1;
    
 balancegeneral--;  
                 }


**/


/** */
 

//positive balancing  2 main one




/**
 
double posotiveyoteglobal= (int) Math.round((conn.rs.getInt("711_totalpositive")*ipdpositiveratio))+(int) Math.round((conn.rs.getInt("711_totalpositive")*opdpositiveratio))+(int) Math.round((conn.rs.getInt("711_totalpositive")*vctpositiveratio));
 
 
   //double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f);
double balancegeneralglobal=(int) Math.round((conn.rs.getInt("711_totalpositive")))-(posotiveyoteglobal);
   //711_totalpositive 
    
 
    
       // System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+" Total positive :"+(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))+"-"+(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f)+"="+balancegeneral);
   
       
while(balancegeneralglobal<0){
    if(AdultFemaleHIV49!=0){
         //deduct from pos and add to negative since the negatives were achieved from tested-positive
 AdultFemaleHIV49-=1; 
 AdultFemaleHIV49Neg+=1; 
    }
    else {
        //deduct from pos and add to negative since the negatives were achieved from tested-positive
    AdultFemaleHIV24-=1;
    AdultFemaleHIV24Neg+=1;
    
    }
 balancegeneralglobal++;
  
                 }

while(balancegeneralglobal>0){
 //add to pos and add deduct from negative since the negatives were achieved from tested-positive
 AdultFemaleHIV49+=1; 
    
 AdultFemaleHIV49Neg-=1;
    
 balancegeneralglobal--;  
                 }



   **/
//============================================================================
        
 
  String alldatavals[]={county,district,facilityname,""+mflcode,dsdta
        ,"0","0"
        ,""+ChildFemaleHIV1,""+ChildMaleHIV1
        ,""+(ChildFemaleHIV4+ChildFemaleHIV9),""+(ChildFemaleHIV4+ChildMaleHIV9)
        ,""+ChildFemaleHIV14,""+ChildMaleHIV14
        ,""+AdultFemaleHIV19,""+AdultMaleHIV19
        ,""+AdultFemaleHIV24,""+AdultMaleHIV24
        ,""+AdultFemaleHIV49,""+AdultMaleHIV49
        ,""+AdultFemaleHIV50,""+AdultMaleHIV50        
         //negative starts here 
        ,"0","0"
        ,""+ChildFemaleHIV1Neg,""+ChildMaleHIV1Neg
        ,""+(ChildFemaleHIV4Neg+ChildFemaleHIV9Neg),""+(ChildMaleHIV4Neg+ChildMaleHIV9Neg)
        ,""+ChildFemaleHIV14Neg,""+ChildMaleHIV14Neg
        ,""+AdultFemaleHIV19Neg,""+AdultMaleHIV19Neg
        ,""+AdultFemaleHIV24Neg,""+AdultMaleHIV24Neg
        ,""+AdultFemaleHIV49Neg,""+AdultMaleHIV49Neg
        ,""+AdultFemaleHIV50Neg,""+AdultMaleHIV50Neg,""+tested_new711,"0","0",""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")
          
        }; 



HSSFCell  cxy;
 

         rw0.setHeightInPoints(25);
         int mypos=0;
     
         
  for(int a=0;a<alldatavals.length;a++){
      
       cxy=rw0.createCell(mypos);mypos++;
       if(a==4||a<3){
           //non numeric characters
        cxy.setCellValue(alldatavals[a]);
       }
       else {
        cxy.setCellValue(new Double(alldatavals[a]).intValue());
       
       }
      
       cxy.setCellStyle(stborder);
  
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop
    
  
                     
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                for (int e = 0; e < 4; e++) {
                shet3.autoSizeColumn(e);
                }
                //Made my life veery simple...
                shet3.setDisplayGridlines(false);
                shet3.createFreezePane(5,5);                 
                     
                     
                     
                     
                 
                 }//end of 6==6 OPD
                 
               
         

                 
            
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
            response.setHeader("Content-Disposition", "attachment; filename=datim_OPD_2017_Gen_On_" + createdOn + ".xls");
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(newdatimHTCResults_2017Q1.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(newdatimHTCResults_2017Q1.class.getName()).log(Level.SEVERE, null, ex);
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

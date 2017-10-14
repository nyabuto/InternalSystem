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
import org.json.simple.JSONArray;

/**
 *
 * @author Emmanuel E
 */
public class datimTbViralRetention_2017Q4 extends HttpServlet {

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
               String    subcounty_countywhere=" (1=1) and ";
                  
            response.setContentType("text/html;charset=UTF-8");
            allFacilities.clear();
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
                  
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
            String headerTB[]="County,Sub County,Health Facility,MFL Code,Type of support,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+Y,Positive,Negative,Total PLVHIV enrolled in clinical care (HV0319),Ho of PLV in HIV clinical care screened for TB (HV0354),Female,Male, Screened for TB <15 Years,<1,1-4Y,5-9Y,10-14Y,Screened for TB >15 years,15-19Y,20+Y,Numerator,Denominator,Female,Male,<1,1-4Y,5-9Y,10-14Y,15-19Y,20+,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
 
            String facilityIds1="";
       excelDuration="";
       String tbscreenperiod="";
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
       int tbstartmonth=new Integer(startMonth)+1;
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN '"+prevYear+""+startMonth+"' AND '"+prevYear+""+endMonth+"'";    
      tbscreenperiod=" moh731.yearmonth BETWEEN '"+prevYear+""+tbstartmonth+"' AND '"+prevYear+""+endMonth+"'";    
      period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else {
          
     duration=" moh731.yearmonth BETWEEN '"+year+""+startMonth+"' AND '"+year+""+endMonth+"'";
     tbscreenperiod=" moh731.yearmonth BETWEEN '"+year+"0"+tbstartmonth+"' AND '"+year+"0"+endMonth+"'"; //eg 20161101 and 20161230 
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
     subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";
    
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
    int blankrows=43;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where "+subcounty_countywhere+" ( PMTCT=1 || ART=1) group by "+facilitiestable+".SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getstaticfacilities);
    while(conn.rs.next())
    {
    
     staticcounty.add(conn.rs.getString("county"));
     String district=conn.rs.getString("district");
     staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     staticfacility.add(conn.rs.getString("facility"));
     staticmfl.add(conn.rs.getString("mflcode"));   
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     staticdsd_ta.add(dsdta); 
      if(conn.rs.getString("ART_highvolume")!=null){staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {staticart_hv.add(""); }
     if(conn.rs.getString("HTC_highvolume")!=null){ statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {staticpmtct_hv.add("");}
     
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //For tb screen , when a quarterly report is generated, we need to get data for the last two months.
    //otherwise, get data from the specified period
  
    String getData="SELECT "+facilitiestable+".SubPartnerNom,district.DistrictNom,county.County,"
            + ""+facilitiestable+".CentreSanteId,ART_Support,PMTCT_Support,"
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),"
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),"
            + ""+facilitiestable+".SubPartnerID,"
            + "SUM(HV0205),SUM(HV0209),SUM(HV0210),SUM(HV0216),SUM(HV0217),"
            + "SUM(HV0224),SUM(HV0225),SUM(HV0227),SUM(HV0229),SUM(HV0230),SUM(HV0231),SUM(HV0232),"
            + "SUM(HV0302),SUM(HV0206),SUM(HV0207),SUM(HV0208)"
            + ",SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354) ,moh731.SubPartnerID as subpartnerid ,ART_highvolume, HTC_highvolume,PMTCT_highvolume"
            + " FROM moh731 JOIN "+facilitiestable+" "
            + "ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID   "
            + "  "
            + " WHERE "
    + " "+facilityIds+" "+duration+" && ("+facilitiestable+".PMTCT=1 || ART=1)  "
            + "GROUP BY moh731.SubPartnerID   "
            + " union "
            + "SELECT "+facilitiestable+".SubPartnerNom,district.DistrictNom,county.County, "
            + ""+facilitiestable+".CentreSanteId,ART_Support,PMTCT_Support, "
            + "SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312), "
            + "SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324), "
            + ""+facilitiestable+".SubPartnerID,"
            + "SUM(HV0205),SUM(HV0209),SUM(HV0210),SUM(HV0216),SUM(HV0217), "
            + "SUM(HV0224),SUM(HV0225),SUM(HV0227),SUM(HV0229),SUM(HV0230),SUM(HV0231),SUM(HV0232), "
            + "SUM(HV0302),SUM(HV0206),SUM(HV0207),SUM(HV0208) "
            + ",SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354),tb_stat_art.SubPartnerID as subpartnerid ,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
            + " FROM moh731 JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + " district.CountyID=county.CountyID   "
            + " Right join tb_stat_art on tb_stat_art.SubPartnerID=moh731.SubpartnerID "
            + " WHERE   moh731.subPartnerID in ( " +

" SELECT tb_stat_art.SubPartnerID FROM tb_stat_art join "+facilitiestable+" on  tb_stat_art.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " where  ART=1 and tb_stat_art.SubPartnerID not in  "
            + " ( select moh731.subPartnerID from moh731 join "+facilitiestable+" on moh731.SubPartnerID="+facilitiestable+".SubPartnerID where "+facilityIds+" "+duration+" && ("+facilitiestable+".PMTCT=1 || ART=1)  ) " +

" )   " ;
       System.out.println("^"+getData);
//     System.out.println("new : "+getData);
    conn.rs=conn.st.executeQuery(getData);
    while(conn.rs.next()){
        
        
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
        HV0350=conn.rs.getInt(34);//note that in quarterly report, this value will be replaced by the rsults of the query below
        HV0351=conn.rs.getInt(35);
        HV0352=conn.rs.getInt(36);
        HV0353=conn.rs.getInt(37);
        HV0354=conn.rs.getInt(38);
        
//   HV0302=0;
        //get values for cumulative indicators 
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
  
       
      
         else {
             
                if(reportDuration.equals("3")){
  //get the data for the two months for the quarter
     
    //SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354)   
      String gettbscreenquarterly="SELECT SUM(HV0350) as HV0350,SUM(HV0351) as HV0351,SUM(HV0352) as HV0352,SUM(HV0353) as HV0353,SUM(HV0354) as HV0354 FROM moh731 WHERE "
    + "moh731.SubPartnerID='"+facilityId+"' && "+tbscreenperiod+"";
     //System.out.println("tbscreen : "+gettbscreenquarterly);
     conn.rs1=conn.st1.executeQuery(gettbscreenquarterly);
     if(conn.rs1.next()){
         
       
        HV0350=conn.rs1.getInt("HV0350");
        HV0351=conn.rs1.getInt("HV0351");
        HV0352=conn.rs1.getInt("HV0352");
        HV0353=conn.rs1.getInt("HV0353");
        HV0354=conn.rs1.getInt("HV0354");
         
         
     }
     }  
             
         
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
        
      
        String dataTB []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"
        + ",,,,,"
        + ",,,,,,,"+TB_SCREEN_D+","+TB_SCREEN_N+","+TB_SCREEN_FEMALE+","
        + ""+TB_SCREEN_MALE+","+TB_SCREEN_LESS15+","+TB_SCREEN_1+","+TB_SCREEN_4+","+TB_SCREEN_9+","+
       TB_SCREEN_14+","+TB_SCREEN_MORE15+","+TB_SCREEN_19+","+TB_SCREEN_20+",,,,,,,,,,,"+errorTB+","+arthv+","+htchv+","+pmtcthv).split(",");
     
     
        HSSFRow rw3shet5=shetTB.createRow(tbpos); 
       rw3shet5.setHeightInPoints(25);
       for(int positionTB=0;positionTB<dataTB.length;positionTB++){
       String value=dataTB[positionTB];
           c11=rw3shet5.createCell(positionTB);
        if(positionTB>=17 && positionTB<=28){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionTB==5 || positionTB==6 || positionTB==17 || positionTB==18 || positionTB==29 || positionTB==30){ c11.setCellStyle(styleHeader);}
          
          if(positionTB==dataTB.length-4){
       if(errorTB>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
     
     
     }
      else{
     String dataTB []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"
        + ""+TB_STAT_N+","+TB_STAT_D+","+TB_STAT_FEMALE+","+TB_STAT_MALE+","+TB_STAT_1+","
        + ""+TB_STAT_4+","+TB_STAT_9+","+TB_STAT_14+","+TB_STAT_19+","+TB_STAT_20+","+
        TB_STAT_POSTIVE+","+TB_STAT_NEGATIVE+","+TB_SCREEN_D+","+TB_SCREEN_N+","+TB_SCREEN_FEMALE+","
        + ""+TB_SCREEN_MALE+","+TB_SCREEN_LESS15+","+TB_SCREEN_1+","+TB_SCREEN_4+","+TB_SCREEN_9+","+
       TB_SCREEN_14+","+TB_SCREEN_MORE15+","+TB_SCREEN_19+","+TB_SCREEN_20+","+
	   TB_ART_N+","+TB_ART_D+","+TB_ART_FEMALE+","+TB_ART_MALE+","+TB_ART_1+","+TB_ART_4+","
        + ""+TB_ART_9+","+TB_ART_14+","+TB_ART_19+","+TB_ART_20+","+errorTB+","+arthv+","+htchv+","+pmtcthv).split(",");
     
     
        HSSFRow rw3shet5=shetTB.createRow(tbpos); 
       rw3shet5.setHeightInPoints(25);
       for(int positionTB=0;positionTB<dataTB.length;positionTB++){
       String value=dataTB[positionTB];
           c11=rw3shet5.createCell(positionTB);
        if(positionTB>4 && positionTB<dataTB.length-4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
          if(positionTB==5 || positionTB==6 || positionTB==17 || positionTB==18 || positionTB==29 || positionTB==30){ c11.setCellStyle(styleHeader);}
          
          if(positionTB==dataTB.length-4){
       if(errorTB>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
     }
         
  tbpos++;     
 }

    }//end of while
    
 
    
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shetTB.createRow(tbpos);  
 rwx.setHeightInPoints(23);  
 tbpos++;
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   HSSFCell cellcounty=rwx.createCell(0); 
   cellcounty.setCellValue(staticcounty.get(a).toString());
   cellcounty.setCellStyle(stborder);
  }
  else if(z==1){
    //sub-county  
   HSSFCell cellsubcounty=rwx.createCell(1); 
   cellsubcounty.setCellValue(staticdistrict.get(a).toString());
   cellsubcounty.setCellStyle(stborder);
  }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(staticfacility.get(a).toString());
   cellfacil.setCellStyle(stborder);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(staticmfl.get(a).toString());
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
		 
 else if(z==blankrows-4){
  //data status
   HSSFCell celldsd=rwx.createCell(blankrows-4); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }
                 	 else if(z==blankrows-3){
  //art high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-3); 
   celldsd.setCellValue(staticart_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                         
                        else if(z==blankrows-2){
  //ht high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-2); 
   celldsd.setCellValue(statichtc_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                        
                        else if(z==blankrows-1){
  //pmtct high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue(staticpmtct_hv.get(a).toString());
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
        
        
String headerRETENTION[]="County,Sub County,Health Facility,MFL Code,Type of support,12 Months,24 Months,36 Months,Pregnant,Breastfeeding,Sub-total,<1,1-9,subtotal,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,subtotal,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,Subtotal,Sub-Total,12 Months,24 Months,36 Months,Pregnant,Breastfeeding,Sub-total,<1,1-9,subtotal,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,subtotal,10-14Y,15-19Y,20-24Y,25-49Y,50+Y,Subtotal,Sub-Total,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
 percentage=81;
        
//              year=2015;
//        reportDuration="3";
          reportDuration=request.getParameter("reportDuration");
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
       quarter=request.getParameter("quarter");
     //  quarter="3";
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
     subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";
    
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
         
           subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711
         
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
 c1.setCellValue("Number of adults and children who are still alive and on treatment at 12, 24 and 36 months after initiating ART"); 
 
 
 c1=rw0shetRETENTION.getCell(27);
 c1.setCellValue("Total Number of Adults and children who initiated ART in the 12, 24, and 36 months prior to the beginning of the reporting period, including those who have died, those who have stopped ART and those lost to follow-up"); 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,5,26));
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,27,48));
 
 HSSFRow  rw1shetRETENTION=shetRETENTION.createRow(1);
  rw1shetRETENTION.setHeightInPoints(30);
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw1shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
    }
 c1=rw1shetRETENTION.getCell(10);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 
 
 c1=rw1shetRETENTION.getCell(33);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 

   shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,10,26));
  shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,33,48)); 
  
  HSSFRow  rw2shetRETENTION=shetRETENTION.createRow(2);
  rw2shetRETENTION.setHeightInPoints(30);
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw2shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
//         if(j<=5 || j==17 || j==18 || j==30){
//        String headerInfor=headerRETENTION[j];
//         c1.setCellValue(headerInfor);
//         }
    }
  c1=rw2shetRETENTION.getCell(8);
 c1.setCellValue("Disaggregated by "); 
 
 c1=rw2shetRETENTION.getCell(11);
 c1.setCellValue("Under 10"); 
 
  c1=rw2shetRETENTION.getCell(14);
 c1.setCellValue("Female"); 
 
 c1=rw2shetRETENTION.getCell(20);
 c1.setCellValue("Male"); 
 
   c1=rw2shetRETENTION.getCell(30);
 c1.setCellValue("Disaggregated by "); 
 
 c1=rw2shetRETENTION.getCell(33);
 c1.setCellValue("Under 10"); 
 
 c1=rw2shetRETENTION.getCell(36);
 c1.setCellValue("Female"); 
 
 c1=rw2shetRETENTION.getCell(42);
 c1.setCellValue("Male"); 
 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,8,10));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,11,13)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,14,19));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,20,25)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,30,32));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,33,35)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,36,41)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,42,47)); 
  
  
  HSSFRow  rw3shetRETENTION=shetRETENTION.createRow(3);
  rw3shetRETENTION.setHeightInPoints(50);
  
    for(int headerpos=0;headerpos<headerRETENTION.length;headerpos++){
        String headerInfor=headerRETENTION[headerpos];
        c1=rw3shetRETENTION.createCell(headerpos);
         c1.setCellValue(headerInfor);
         c1.setCellStyle(styleHeader);
    }
 for (int i=0;i<=4;i++){
//shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,i,i));     
 }  
//  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,17,17)); 
//  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,18,18));
//  shetRETENTION.addMergedRegion(new CellRangeAddress(2,3,30,30)); 
  
  
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

    int blankrows=35;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( ART='1' || PMTCT='1') group by "+facilitiestable+".SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getstaticfacilities);
    while(conn.rs.next()){
    
     staticcounty.add(conn.rs.getString("county"));
     String district=conn.rs.getString("district");
     staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     staticfacility.add(conn.rs.getString("facility"));
     staticmfl.add(conn.rs.getString("mflcode"));   
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     staticdsd_ta.add(dsdta); 
     
      if(conn.rs.getString("ART_highvolume")!=null){staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {staticart_hv.add(""); }
     if(conn.rs.getString("HTC_highvolume")!=null){ statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {staticpmtct_hv.add("");}

     
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
  
  
// GET STARTING ART DATA

//Retention both numerator and denominator From oct 2017
if(1==1){
int new_retentionPOS=0;
 new_retentionPOS=retentionPOS=3;
// numerator
    String getNumerator="  " +
"  SELECT " + facilitiestable + ".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
""+facilitiestable+".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"ROUND(SUM(HV0349)) AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,  " +
"ROUND(SUM(tl_12m)) AS pregnant,  " +
"0 AS breastfeeding,  " +
"ROUND(SUM(HV0349*0.01)) as f_1, " +
"ROUND(SUM(HV0349*0.01)) as m_1, " +
"ROUND(SUM(HV0349*0.02)) as f_9, " +
"ROUND(SUM(HV0349*0.07)) as m_9, " +
"ROUND(SUM(HV0349*0.02)) as f_14, " +
"ROUND(SUM(HV0349*0.05)) as f_19, " +
"ROUND(SUM(HV0349*0.08)) as f_24, " +
"ROUND(SUM(HV0349*0.41)) as f_49, " +
"ROUND(SUM(HV0349*0.06)) as f_50, " +
"ROUND(SUM(HV0349*0.01)) as m_14, " +
"ROUND(SUM(HV0349*0.07)) as m_19, " +
"ROUND(SUM(HV0349*0.02)) as m_24, " +
"ROUND(SUM(HV0349*0.15)) as m_49, " +
"ROUND(SUM(HV0349*0.05)) as m_50 " +
" FROM internal_system.moh731  " +
" JOIN internal_system."+facilitiestable+" ON internal_system.moh731.SubPartnerID=internal_system."+facilitiestable+".SubPartnerID  " +
"JOIN internal_system.district ON internal_system."+facilitiestable+".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"LEFT JOIN pmtct_art_cohort.pmtct_cohort ON (pmtct_art_cohort.pmtct_cohort.mflcode=internal_system."+facilitiestable+".CentreSanteId AND  pmtct_art_cohort.pmtct_cohort.yearmonth=internal_system.moh731.yearmonth)  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 )  " +
"AND (pmtct_art_cohort.pmtct_cohort.indicator=21 OR pmtct_art_cohort.pmtct_cohort.indicator=9)  " +
" GROUP BY internal_system.moh731.SubPartnerID  " +
"  " +
"	UNION " +
"     " +
" SELECT "+facilitiestable+".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
""+facilitiestable+".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"ROUND(SUM(HV0345)) AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,  " +
"0 AS pregnant,  " +
"0 AS breastfeeding,  " +
"ROUND(SUM(HV0345*0.01)) as f_1, " +
"ROUND(SUM(HV0345*0.01)) as m_1, " +
"ROUND(SUM(HV0345*0.02)) as f_9, " +
"ROUND(SUM(HV0345*0.07)) as m_9, " +
"ROUND(SUM(HV0345*0.02)) as f_14, " +
"ROUND(SUM(HV0345*0.05)) as f_19, " +
"ROUND(SUM(HV0345*0.08)) as f_24, " +
"ROUND(SUM(HV0345*0.41)) as f_49, " +
"ROUND(SUM(HV0345*0.06)) as f_50, " +
"ROUND(SUM(HV0345*0.01)) as m_14, " +
"ROUND(SUM(HV0345*0.07)) as m_19, " +
"ROUND(SUM(HV0345*0.02)) as m_24, " +
"ROUND(SUM(HV0345*0.15)) as m_49, " +
"ROUND(SUM(HV0345*0.05)) as m_50 " +
" FROM moh731 JOIN "+facilitiestable+" " +
"ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID  " +
"JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON  " +
"district.CountyID=county.CountyID  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 ) and moh731.SubPartnerID NOT IN ( " +
"SELECT moh731.SubPartnerID " +
"FROM internal_system.moh731  " +
"JOIN internal_system."+facilitiestable+" ON internal_system.moh731.SubPartnerID=internal_system."+facilitiestable+".SubPartnerID  " +
"JOIN internal_system.district ON internal_system."+facilitiestable+".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"LEFT JOIN pmtct_art_cohort.pmtct_cohort ON (pmtct_art_cohort.pmtct_cohort.mflcode=internal_system."+facilitiestable+".CentreSanteId AND  pmtct_art_cohort.pmtct_cohort.yearmonth=internal_system.moh731.yearmonth)  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 )  " +
" AND (pmtct_art_cohort.pmtct_cohort.indicator=21 OR pmtct_art_cohort.pmtct_cohort.indicator=9)  " +
" GROUP BY internal_system.moh731.SubPartnerID  " +
" ) " +
" GROUP BY moh731.SubPartnerID ";   
     conn.rs=conn.st.executeQuery(getNumerator);
        while(conn.rs.next()){

         int mflindex=staticmfl.indexOf(conn.rs.getString("CentreSanteId"));

            if(mflindex!=-1) {        
               //remove the element from the arraylist 
                 staticfacility.remove(mflindex);
                 staticcounty.remove(mflindex);
                 staticdistrict.remove(mflindex);
                 staticmfl.remove(mflindex);
                 staticdsd_ta.remove(mflindex);
                 staticart_hv.remove(mflindex);
                statichtc_hv.remove(mflindex);
                staticpmtct_hv.remove(mflindex);

                            } 

            String arthv=" ";
         String htchv=" ";
         String pmtcthv=" ";

          if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getString("ART_highvolume");}
          if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getString("HTC_highvolume"); }
          if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getString("PMTCT_highvolume");}


            retentionPOS++;
            errorRETENTION=0;
          facilityName=conn.rs.getString(1);
          districtName=conn.rs.getString(2);
          countyName=conn.rs.getString(3);
          mflcode=conn.rs.getString(4);
          ARTSupport=conn.rs.getString(5);

     //HV0321=conn.rs.getInt(6);
     //HV0322=conn.rs.getInt(7);
     //HV0323=conn.rs.getInt(8);
     //HV0324=conn.rs.getInt(9);
     //HV0325=conn.rs.getInt(10);

        int numerator=conn.rs.getInt("retention_12months");
        int num_24 = conn.rs.getInt("retention_24months");
        int num_36 = conn.rs.getInt("retention_36months");
        int pregnant= conn.rs.getInt("pregnant");
        int breastfeeding = conn.rs.getInt("breastfeeding");

        int f_1,m_1,f_9,m_9,f_14,f_19,f_24,f_49,f_50,m_14,m_19,m_24,m_49,m_50;
            f_1 = conn.rs.getInt("f_1");
            m_1 = conn.rs.getInt("m_1");
            f_9 = conn.rs.getInt("f_9");
            m_9 = conn.rs.getInt("m_9");
            f_14 = conn.rs.getInt("f_14");
            f_19 = conn.rs.getInt("f_19");
            f_24 = conn.rs.getInt("f_24");
            f_49 = conn.rs.getInt("f_49");
            f_50 = conn.rs.getInt("f_50");
            m_14 = conn.rs.getInt("m_14");
            m_19 = conn.rs.getInt("m_19");
            m_24 = conn.rs.getInt("m_24");
            m_49 = conn.rs.getInt("m_49");
            m_50 = conn.rs.getInt("m_50");

            int newsummed = f_1+m_1+f_9+m_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50;

            if(newsummed>numerator){
                int rounds=newsummed-numerator;
                int counter=0;
                while(rounds>0){
                  counter++;
                  if(counter<5){
                  f_49--;    
                  }
                  else if(counter==5){
                  m_49--;
                  counter=0;
                  }

                    rounds--;
                }
            }
          else  if(newsummed<numerator){
                int rounds=numerator-newsummed;
                int counter=0;
                while(rounds>0){
                  counter++;
                  if(counter<5){
                  f_49++;    
                  }
                  else if(counter==5){
                  m_49++;
                  counter=0;
                  }

                    rounds--;
                }
            }

          else{
    //          They are equal hence no normalization
          }
      int  total_1_9 = m_1+m_9+f_1+f_9;    
    //     completed normalization
int sumedtotalsafter=total_1_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50;
            System.out.println("initial totals:"+newsummed+" final summed : "+sumedtotalsafter+" fetched total : "+numerator);
    // create row and add data
    String dataRETENTION []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"+numerator+","+num_24+","+num_36+","
           + ""+pregnant+","+breastfeeding+","+(pregnant+breastfeeding)+","
           + ""+(f_1+m_1)+","+(f_9+m_9)+","+total_1_9+","+f_14+","+f_19+","+f_24+","+f_49+","+f_50+","+(f_14+f_19+f_24+f_49+f_50)+","
           + ""+m_14+","+m_19+","+m_24+","+m_49+","+m_50+","+(m_14+m_19+m_24+m_49+m_50)+","+(total_1_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50)).split(","); 

  HSSFRow rw4shetRETENTION=shetRETENTION.createRow(retentionPOS); 
       rw4shetRETENTION.setHeightInPoints(25);
       for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++)
       {
       String value=dataRETENTION[positionRETENTION];
       HSSFCell    c11=rw4shetRETENTION.createCell(positionRETENTION);
        if(positionRETENTION>4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
//          if(positionRETENTION==5 || positionRETENTION==8 || positionRETENTION==17 || positionRETENTION==18 || positionRETENTION==21 || positionRETENTION==30){ c11.setCellStyle(styleHeader);}
//          System.out.println("position "+positionPMTCT+" end v : "+dataPMTCT.length); 
       }
        }  
        
        
        
        
        
        
        int initial_colpos=27;
 //denominator 
String getDenominator="  " +
"  SELECT "+facilitiestable+".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
""+facilitiestable+".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"ROUND(SUM(HV0345)) AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,  " +
"ROUND(SUM(tl_12m)) AS pregnant,  " +
"0 AS breastfeeding,  " +
"ROUND(SUM(HV0345*0.0091)) as f_1, " +
"ROUND(SUM(HV0345*0.0058)) as m_1, " +
"ROUND(SUM(HV0345*0.0347)) as f_9, " +
"ROUND(SUM(HV0345*0.0281)) as m_9, " +
"ROUND(SUM(HV0345*0.0132)) as f_14, " +
"ROUND(SUM(HV0345*0.0285)) as f_19, " +
"ROUND(SUM(HV0345*0.0819)) as f_24, " +
"ROUND(SUM(HV0345*0.4205)) as f_49, " +
"ROUND(SUM(HV0345*0.0575)) as f_50, " +
"ROUND(SUM(HV0345*0.0099)) as m_14, " +
"ROUND(SUM(HV0345*0.0062)) as m_19, " +
"ROUND(SUM(HV0345*0.0141)) as m_24, " +
"ROUND(SUM(HV0345*0.2418)) as m_49, " +
"ROUND(SUM(HV0345*0.0488)) as m_50 " +
" FROM internal_system.moh731  " +
" JOIN internal_system."+facilitiestable+" ON internal_system.moh731.SubPartnerID=internal_system."+facilitiestable+".SubPartnerID  " +
"JOIN internal_system.district ON internal_system."+facilitiestable+".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"LEFT JOIN pmtct_art_cohort.pmtct_cohort ON (pmtct_art_cohort.pmtct_cohort.mflcode=internal_system."+facilitiestable+".CentreSanteId AND  pmtct_art_cohort.pmtct_cohort.yearmonth=internal_system.moh731.yearmonth)  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 )  " +
" AND (pmtct_art_cohort.pmtct_cohort.indicator=4 OR pmtct_art_cohort.pmtct_cohort.indicator=16)  " +
" GROUP BY internal_system.moh731.SubPartnerID  " +
"  " +
"	UNION " +
"     " +
" SELECT "+facilitiestable+".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
""+facilitiestable+".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"ROUND(SUM(HV0345)) AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,  " +
"0 AS pregnant,  " +
"0 AS breastfeeding,  " +
"ROUND(SUM(HV0345*0.0091)) as f_1, " +
"ROUND(SUM(HV0345*0.0058)) as m_1, " +
"ROUND(SUM(HV0345*0.0347)) as f_9, " +
"ROUND(SUM(HV0345*0.0281)) as m_9, " +
"ROUND(SUM(HV0345*0.0132)) as f_14, " +
"ROUND(SUM(HV0345*0.0285)) as f_19, " +
"ROUND(SUM(HV0345*0.0819)) as f_24, " +
"ROUND(SUM(HV0345*0.4205)) as f_49, " +
"ROUND(SUM(HV0345*0.0575)) as f_50, " +
"ROUND(SUM(HV0345*0.0099)) as m_14, " +
"ROUND(SUM(HV0345*0.0062)) as m_19, " +
"ROUND(SUM(HV0345*0.0141)) as m_24, " +
"ROUND(SUM(HV0345*0.2418)) as m_49, " +
"ROUND(SUM(HV0345*0.0488)) as m_50 " +
" FROM moh731 JOIN "+facilitiestable+" " +
"ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID  " +
"JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON  " +
"district.CountyID=county.CountyID  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 ) and moh731.SubPartnerID NOT IN ( " +
"SELECT moh731.SubPartnerID " +
"FROM internal_system.moh731  " +
"JOIN internal_system."+facilitiestable+" ON internal_system.moh731.SubPartnerID=internal_system."+facilitiestable+".SubPartnerID  " +
"JOIN internal_system.district ON internal_system."+facilitiestable+".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"LEFT JOIN pmtct_art_cohort.pmtct_cohort ON (pmtct_art_cohort.pmtct_cohort.mflcode=internal_system."+facilitiestable+".CentreSanteId AND  pmtct_art_cohort.pmtct_cohort.yearmonth=internal_system.moh731.yearmonth)  " +
" WHERE  "+facilityIds+" "+duration+" && ("+facilitiestable+".ART=1 ||"+facilitiestable+".PMTCT=1 )  " +
" AND (pmtct_art_cohort.pmtct_cohort.indicator=4 OR pmtct_art_cohort.pmtct_cohort.indicator=16)  " +
" GROUP BY internal_system.moh731.SubPartnerID  " +
" ) " +
" GROUP BY moh731.SubPartnerID ";

     conn.rs=conn.st.executeQuery(getDenominator);
        while(conn.rs.next()){

         int mflindex=staticmfl.indexOf(conn.rs.getString("CentreSanteId"));

            if(mflindex!=-1) {        
               //remove the element from the arraylist 
                 staticfacility.remove(mflindex);
                 staticcounty.remove(mflindex);
                 staticdistrict.remove(mflindex);
                 staticmfl.remove(mflindex);
                 staticdsd_ta.remove(mflindex);
                 staticart_hv.remove(mflindex);
                statichtc_hv.remove(mflindex);
                staticpmtct_hv.remove(mflindex);

                            } 

            String arthv=" ";
         String htchv=" ";
         String pmtcthv=" ";

          if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getString("ART_highvolume");}
          if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getString("HTC_highvolume"); }
          if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getString("PMTCT_highvolume");}


            new_retentionPOS++;
            errorRETENTION=0;
          facilityName=conn.rs.getString(1);
          districtName=conn.rs.getString(2);
          countyName=conn.rs.getString(3);
          mflcode=conn.rs.getString(4);
          ARTSupport=conn.rs.getString(5);

     //HV0321=conn.rs.getInt(6);
     //HV0322=conn.rs.getInt(7);
     //HV0323=conn.rs.getInt(8);
     //HV0324=conn.rs.getInt(9);
     //HV0325=conn.rs.getInt(10);

        int denominator=conn.rs.getInt("retention_12months");
        int den_24 = conn.rs.getInt("retention_24months");
        int den_36 = conn.rs.getInt("retention_36months");
        int pregnant= conn.rs.getInt("pregnant");
        int breastfeeding = conn.rs.getInt("breastfeeding");

        int f_1,m_1,f_9,m_9,f_14,f_19,f_24,f_49,f_50,m_14,m_19,m_24,m_49,m_50;
            f_1 = conn.rs.getInt("f_1");
            m_1 = conn.rs.getInt("m_1");
            f_9 = conn.rs.getInt("f_9");
            m_9 = conn.rs.getInt("m_9");
            f_14 = conn.rs.getInt("f_14");
            f_19 = conn.rs.getInt("f_19");
            f_24 = conn.rs.getInt("f_24");
            f_49 = conn.rs.getInt("f_49");
            f_50 = conn.rs.getInt("f_50");
            m_14 = conn.rs.getInt("m_14");
            m_19 = conn.rs.getInt("m_19");
            m_24 = conn.rs.getInt("m_24");
            m_49 = conn.rs.getInt("m_49");
            m_50 = conn.rs.getInt("m_50");

            int newsummed = f_1+m_1+f_9+m_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50;

            if(newsummed>denominator){
                int rounds=newsummed-denominator;
                int counter=0;
                while(rounds>0){
                  counter++;
                  if(counter<5){
                  f_49--;    
                  }
                  else if(counter==5){
                  m_49--;
                  counter=0;
                  }

                    rounds--;
                }
            }
          else  if(newsummed<denominator){
                int rounds=denominator-newsummed;
                int counter=0;
                while(rounds>0){
                  counter++;
                  if(counter<5){
                  f_49++;    
                  }
                  else if(counter==5){
                  m_49++;
                  counter=0;
                  }

                    rounds--;
                }
            }

          else{
    //          They are equal hence no normalization
          }
      int  total_1_9 = m_1+m_9+f_1+f_9;    
    //     completed normalization
    int sumedtotalsafter=total_1_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50;
    System.out.println("initial totals:"+newsummed+" final summed : "+sumedtotalsafter+" fetched total : "+denominator);
    // create row and add data
    String dataRETENTION []=(denominator+","+den_24+","+den_36+","
           + ""+pregnant+","+breastfeeding+","+(pregnant+breastfeeding)+","
           + ""+(f_1+m_1)+","+(f_9+m_9)+","+total_1_9+","+f_14+","+f_19+","+f_24+","+f_49+","+f_50+","+(f_14+f_19+f_24+f_49+f_50)+","
           + ""+m_14+","+m_19+","+m_24+","+m_49+","+m_50+","+(m_14+m_19+m_24+m_49+m_50)+","+(total_1_9+f_14+f_19+f_24+f_49+f_50+m_14+m_19+m_24+m_49+m_50)+"," 
           + "status"+","+arthv+","+htchv+","+pmtcthv).split(","); 

  HSSFRow rw4shetRETENTION=shetRETENTION.getRow(new_retentionPOS); 
       rw4shetRETENTION.setHeightInPoints(25);
       for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++)
       {
       String value=dataRETENTION[positionRETENTION];
       HSSFCell c11=rw4shetRETENTION.createCell(positionRETENTION+initial_colpos);
        if(positionRETENTION<(dataRETENTION.length-4) || positionRETENTION>(dataRETENTION.length-4)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
//        c11.setCellValue(value);
         c11.setCellStyle(stborder);
//          if(positionRETENTION==5 || positionRETENTION==8 || positionRETENTION==17 || positionRETENTION==18 || positionRETENTION==21 || positionRETENTION==30){ c11.setCellStyle(styleHeader);}
//          System.out.println("position "+positionPMTCT+" end v : "+dataPMTCT.length); 
       if(positionRETENTION==dataRETENTION.length-4){
//           System.out.println("entered here >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
       if(errorRETENTION>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
       }
             

        } 
  // ouput the values      
        
}   
        }
    
               
  //=============================VIRAL LOAD============================================
     if(1==1){       
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "vl_validation";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            
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
            
            String facilitywhere = "";
            String yearwhere = "";
            String monthwhere = "";
            String countywhere = "";
            String quarter = "";
            String viralloadduration;
            String start_date="",end_date="";
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
            String viralloadquarter="";
//        GET REPORT DURATION============================================
            //annually
            if (reportDuration.equals("1")) {
                start_date=(Integer.parseInt(year)-1)+"-10-01";
                end_date=Integer.parseInt(year)+"-09-31";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
                
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                     start_date=(Integer.parseInt(year)-1)+"-10-01";
                     end_date=Integer.parseInt(year)+"-03-31";
                } else {
                     start_date=Integer.parseInt(year)+"-04-01";
                     end_date=Integer.parseInt(year)+"-09-31";
                }
            } else if (reportDuration.equals("3")) {
               
                    quarter = request.getParameter("quarter");
                      if (quarter.equals("1")) {
                                
                                start_date=(Integer.parseInt(year)-1)+"-10-01";
                                end_date=(Integer.parseInt(year)-1)+"-12-31";
                            } 
                      else if(quarter.equals("2")) {
                               start_date=Integer.parseInt(year)+"-01-01";
                               end_date=Integer.parseInt(year)+"-03-31";
                            }
                      else if(quarter.equals("3")) {
                               start_date=Integer.parseInt(year)+"-04-01";
                               end_date=Integer.parseInt(year)+"-06-31";
                            }
                      else if(quarter.equals("4")) {
                               start_date=Integer.parseInt(year)+"-07-01";
                               end_date=Integer.parseInt(year)+"-09-31";
                            }
                        }
                    
                
            
                else if (reportDuration.equals("4")) {
                //on monthly reports, i dont expect any output since viral load is entered quarterly
                monthcopy = Integer.parseInt(request.getParameter("month"));
                if (monthcopy >= 10) {
                    start_date=(Integer.parseInt(year)-1)+"-"+monthcopy+"-01";
                    end_date=(Integer.parseInt(year)-1)+"-"+monthcopy+"-31";
                } 
                else {
                start_date=Integer.parseInt(year)+"-0"+monthcopy+"-01";
                end_date=Integer.parseInt(year)+"-0"+monthcopy+"-31";
                }
            }
            else {
                duration = "";
                
            }
          
            viralloadduration = " Date_Dispatched BETWEEN '"+start_date+"' AND '"+end_date+"' ";
             
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
                
//                facilitywhere = " and " + facilitiestable + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + viralloadduration + " " + countywhere + " " +subcountywhere+" "+facilitywhere+" "
            + " and "+facilitiestable+".active=1 and Gender !='' and (AgeYrs!='' and AgeYrs>=0 && AgeYrs<100) ";

         System.out.println("where:"+joinedwhwere);
//    ====================START OF NEW VIRAL LOAD REPORT OCT 2017 -------------
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
            HSSFRow rw; 
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            String header1[] = {"DSD:TX_PVLS (Numerator): Number of adults and pediatric patients on ART with suppressed viral load results (<1000 copies/ml) documented in the medical records and/or supporting laboratory results within the past 12 months","DSD:TX_PVLS (Denominator) : Number of adult and pediatric ART patients with viral load results documented in the patient medical record and/or laboratory records in the past 12 months."};
            String header2[] = {"Disaggregated by Indication","Disaggregated by Preg/BF/Indication","Disaggregated by Age/Sex/Testing Indication (Fine Disaggregate).","Disaggregated by Indication","Disaggregated by Preg/BF/Indication","Disaggregated by Age/Sex/Testing Indication (Fine Disaggregate)."};
           String header3[] = {"Routine","Targeted","Not Documented","Routine","Targeted","Not Documented"};
            String header4[] = {"Indication","Pregnant","Breastfeeding","Under 10","Female","Male","Under 10","Female","Male","Under 10","Female","Male","Indication","Pregnamt","Breastfeeding","Under 10","Female","Male","Under 10","Female","Male","Under 10","Female","Male"};
            HSSFCell cx1; 
            cx1 = rw.createCell(5);
            cx1.setCellValue(header1[0]);
            
            cx1 = rw.createCell(63);
            cx1.setCellValue(header1[1]);
            //format cell here
           
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,5,62));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,63,120));
           
            
            //end of formatting and merging
            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            cx1 = rw.createCell(6);
            cx1.setCellValue(header2[0]);
            
            cx1 = rw.createCell(9);
            cx1.setCellValue(header2[1]);
            
            cx1 = rw.createCell(15);
            cx1.setCellValue(header2[2]);
            
            cx1 = rw.createCell(64);
            cx1.setCellValue(header2[3]);
            
            cx1 = rw.createCell(67);
            cx1.setCellValue(header2[4]);
            
            cx1 = rw.createCell(73);
            cx1.setCellValue(header2[5]);
//            start of formatting and merging
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,6,8));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,9,14));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,62));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,64,66));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,67,72));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,73,120));


//          end of formatting and merging



 
            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            cx1 = rw.createCell(15);
            cx1.setCellValue(header3[0]);
            cx1 = rw.createCell(31);
            cx1.setCellValue(header3[1]);
            cx1 = rw.createCell(47);
            cx1.setCellValue(header3[2]);
            cx1 = rw.createCell(73);
            cx1.setCellValue(header3[3]);
            cx1 = rw.createCell(91);
            cx1.setCellValue(header3[4]);
            cx1 = rw.createCell(105);
            cx1.setCellValue(header3[5]);
            
            
            
            //start of formatting
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,30));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,31,46));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,47,62));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,73,88));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,91,104));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,105,120));
              
            
            
            //end of formatting
            
            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            cx1 = rw.createCell(6);
            cx1.setCellValue(header4[0]);
            
            cx1 = rw.createCell(9);
            cx1.setCellValue(header4[1]);
            
            cx1 = rw.createCell(12);
            cx1.setCellValue(header4[2]);
            
            cx1 = rw.createCell(15);
            cx1.setCellValue(header4[3]);
            
            cx1 = rw.createCell(18);
            cx1.setCellValue(header4[4]);
            
            cx1 = rw.createCell(24);
            cx1.setCellValue(header4[5]);
            
            cx1 = rw.createCell(31);
            cx1.setCellValue(header4[6]);
            
            cx1 = rw.createCell(34);
            cx1.setCellValue(header4[7]);
            
            cx1 = rw.createCell(40);
            cx1.setCellValue(header4[8]);
            
            cx1 = rw.createCell(47);
            cx1.setCellValue(header4[9]);
            
            cx1 = rw.createCell(50);
            cx1.setCellValue(header4[10]);
            
            cx1 = rw.createCell(56);
            cx1.setCellValue(header4[11]);
            
            cx1 = rw.createCell(64);
            cx1.setCellValue(header4[12]);
            
            cx1 = rw.createCell(67);
            cx1.setCellValue(header4[13]);
            
            cx1 = rw.createCell(70);
            cx1.setCellValue(header4[14]);
            
            cx1 = rw.createCell(73);
            cx1.setCellValue(header4[15]);
            
            cx1 = rw.createCell(76);
            cx1.setCellValue(header4[16]);
            
            cx1 = rw.createCell(82);
            cx1.setCellValue(header4[17]);
            
            cx1 = rw.createCell(89);
            cx1.setCellValue(header4[18]);
            
            cx1 = rw.createCell(92);
            cx1.setCellValue(header4[19]);
            
            cx1 = rw.createCell(98);
            cx1.setCellValue(header4[20]);
            
            cx1 = rw.createCell(105);
            cx1.setCellValue(header4[21]);
            
            cx1 = rw.createCell(108);
            cx1.setCellValue(header4[22]);
            
            cx1 = rw.createCell(114);
            cx1.setCellValue(header4[23]);


              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,6,8));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,9,11));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,12,14));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,17));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,18,23));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,24,29));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,31,33));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,34,39));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,40,45));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,47,49));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,50,55));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,56,61));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,64,67));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,67,69));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,70,72));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,73,75));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,76,81));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,82,87));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,89,91));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,92,97));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,98,103));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,105,107));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,108,113));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,114,119));


            
           String heading[] = ("County,Sun County,Health Facility,MFL Code,Type of Support,"
            +"Numerator,Routine,Targeted,Not Documented,Routine,Targeted,Not Documented,"
            +"Routine,Targeted,Not Documented,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal,"
            +"Denominator,Routine,Targeted,Not Documented,Routine,Targeted,Not Documented,"
            +"Routine,Targeted,Not Documented,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,10-14,15-19,20-24,25-49,50+,subtotal,subtotal").split(",");
           
           for(int j=0;j<4;j++){
               
           rw = shet.getRow(j);
           for(int i=0;i<121;i++){
                cx1 = rw.getCell(i);
               if(cx1!=null){
                cx1.setCellStyle(stylex);    
               }
               else{
                    cx1 = rw.createCell(i);
                cx1.setCellStyle(stylex);    
               }
   
            }
           }
           
           String totals = ",17,23,29,30,33,39,45,46,49,55,61,62,75,81,87,88,91,97,103,104,107,113,119,120,";
           //add titles

            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(42);
            int colpos=0;
            HSSFCell cell_vl;
            for(String value: heading){
             cell_vl = rw.createCell(colpos);
             cell_vl.setCellValue(value);
             cell_vl.setCellStyle(stylex);
             
             colpos++;
          }
        String titles[] = ("County,DistrictNom,SubPartnerNom,mfl_code,support_type,"
        +"Numerator,n_Routine,n_Targeted,n_Not_Documented,n_Preg_Routine,n_Preg_Targeted,n_Preg_Not_Documented,"
        +"n_Breastfeeding_Routine,n_Breastfeeding_Targeted,n_Breastfeeding_Not_Documented,"
        +"n_r_1,n_r_9,n_r_0_9,n_r_f_14,n_r_f_19,n_r_f_24,n_r_f_49,n_r_f_50,n_r_10_50_f,n_r_m_14,n_r_m_19,n_r_m_24,n_r_m_49,n_r_m_50,n_r_10_50_m,n_r_0_50,"
        +"n_t_1,n_t_9,n_t_0_9,n_t_f_14,n_t_f_19,n_t_f_24,n_t_f_49,n_t_f_50,n_t_10_50_f,n_t_m_14,n_t_m_19,n_t_m_24,n_t_m_49,n_t_m_50,n_t_10_50_m,n_t_0_50,"
        +"n_nd_1,n_nd_9,n_nd_0_9,n_nd_f_14,n_nd_f_19,n_nd_f_24,n_nd_f_49,n_nd_f_50,n_nd_10_50_f,n_nd_m_14,n_nd_m_19,n_nd_m_24,n_nd_m_49,n_nd_m_50,n_nd_10_50_m,n_nd_0_50,"
        +"Denominator,d_Routine,d_Targeted,d_Not_Documented,d_Preg_Routine,d_Preg_Targeted,d_Preg_Not_Documented,"
        +"d_Breastfeeding_Routine,d_Breastfeeding_Targeted,d_Breastfeeding_Not_Documented,"
        +"d_r_1,d_r_9,d_r_0_9,d_r_f_14,d_r_f_19,d_r_f_24,d_r_f_49,d_r_f_50,d_r_10_50_f,d_r_m_14,d_r_m_19,d_r_m_24,d_r_m_49,d_r_m_50,d_r_10_50_m,d_r_0_50,"
        +"d_t_1,d_t_9,d_t_0_9,d_t_f_14,d_t_f_19,d_t_f_24,d_t_f_49,d_t_f_50,d_t_10_50_f,d_t_m_14,d_t_m_19,d_t_m_24,d_t_m_49,d_t_m_50,d_t_10_50_m,d_t_0_50,"
        +"d_nd_1,d_nd_9,d_nd_0_9,d_nd_f_14,d_nd_f_19,d_nd_f_24,d_nd_f_49,d_nd_f_50,d_nd_10_50_f,d_nd_m_14,d_nd_m_19,d_nd_m_24,d_nd_m_49,d_nd_m_50,d_nd_10_50_m,d_nd_0_50").split(",");       
  
        String getVLData = "/*DSD TX_PVLS (Denominator) */ " +
"SELECT county.County AS County, DistrictNom,constituency,ward,"+facilitiestable+".SubPartnerNom AS SubPartnerNom ,CentreSanteId AS mfl_code, " +
"GSN,ART_Support,PMTCT_Support,HTC_Support1,Type,ART_highvolume,PMTCT_highvolume, " +
"HTC_highvolume,latitude,longitude, 'DSD' AS support_type, " +
"COUNT( CASE WHEN Suppressed='Y' THEN  'Numerator' END) AS Numerator, " +
"COUNT( CASE WHEN Suppressed='Y' AND Justification='Routine VL' THEN  'Routine' END) AS n_Routine, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') THEN  'Targeted' END) AS n_Targeted, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') THEN  'Not_Documented' END) AS n_Not_Documented, " +
" " +
"0 AS n_Preg_Routine, " +
"0 AS n_Preg_Targeted, " +
"0 AS n_Preg_Not_Documented, " +
"0 AS n_Breastfeeding_Routine, " +
"0 AS n_Breastfeeding_Targeted, " +
"0 AS n_Breastfeeding_Not_Documented, " +
" " +
"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs <1) THEN  'n_r_f_1' END) AS n_r_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_r_f_9' END) AS n_r_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs <1) THEN  'n_r_m_1' END) AS n_r_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_r_m_9' END) AS n_r_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs <1) THEN  'n_r_1' END) AS n_r_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'n_r_9' END) AS n_r_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs<=9) THEN  'n_r_0_9' END) AS n_r_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_r_f_14' END) AS n_r_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_r_f_19' END) AS n_r_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_r_f_24' END) AS n_r_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_r_f_49' END) AS n_r_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_r_f_50' END) AS n_r_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_r_10_50_f' END) AS n_r_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_r_m_14' END) AS n_r_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_r_m_19' END) AS n_r_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_r_m_24' END) AS n_r_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_r_m_49' END) AS n_r_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_r_m_50' END) AS n_r_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_r_10_50_m' END) AS n_r_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs<=100) THEN  'n_r_0_50' END) AS n_r_0_50, " +
" " +
"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers' ) AND (Sex='F' AND AgeYrs <1) THEN  'n_t_f_1' END) AS n_t_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_r_t_9' END) AS n_t_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs <1) THEN  'n_t_m_1' END) AS n_t_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_t_m_9' END) AS n_t_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs <1) THEN  'n_t_1' END) AS n_t_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'n_t_9' END) AS n_t_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs<=9) THEN  'n_t_0_9' END) AS n_t_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_t_f_14' END) AS n_t_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_t_f_19' END) AS n_t_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_t_f_24' END) AS n_t_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_t_f_49' END) AS n_t_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_t_f_50' END) AS n_t_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_t_10_50_f' END) AS n_t_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_t_m_14' END) AS n_t_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_t_m_19' END) AS n_t_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_t_m_24' END) AS n_t_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_t_m_49' END) AS n_t_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_t_m_50' END) AS n_t_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_t_10_50_m' END) AS n_t_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs<=100) THEN  'n_t_0_50' END) AS n_t_0_50, " +
" " +
" " +
"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs <1) THEN  'n_nd_f_1' END) AS n_nd_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_nd_t_9' END) AS n_nd_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs <1) THEN  'n_nd_m_1' END) AS n_nd_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'n_nd_m_9' END) AS n_nd_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs <1) THEN  'n_nd_1' END) AS n_nd_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'n_nd_9' END) AS n_nd_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs<=9) THEN  'n_nd_0_9' END) AS n_nd_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_nd_f_14' END) AS n_nd_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_nd_f_19' END) AS n_nd_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_nd_f_24' END) AS n_nd_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_nd_f_49' END) AS n_nd_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_nd_f_50' END) AS n_nd_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_nd_10_50_f' END) AS n_nd_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'n_nd_m_14' END) AS n_nd_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'n_nd_m_19' END) AS n_nd_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'n_nd_m_24' END) AS n_nd_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'n_nd_m_49' END) AS n_nd_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'n_nd_m_50' END) AS n_nd_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'n_nd_10_50_m' END) AS n_nd_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs<=100) THEN  'n_nd_0_50' END) AS n_nd_0_50, " +
" " +
" " +
"COUNT(*) AS Denominator, " +
"COUNT( CASE WHEN Justification='Routine VL' THEN  'Routine' END) AS d_Routine, " +
"COUNT( CASE WHEN (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') THEN  'Targeted' END) AS d_Targeted, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') THEN  'Not_Documented' END) AS d_Not_Documented, " +
" " +
"0 d_Preg_Routine, " +
"0 AS d_Preg_Targeted, " +
"0 AS d_Preg_Not_Documented, " +
"0 AS d_Breastfeeding_Routine, " +
"0 AS d_Breastfeeding_Targeted, " +
"0 AS d_Breastfeeding_Not_Documented, " +
" " +
"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs <1) THEN  'd_r_f_1' END) AS d_r_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_r_f_9' END) AS d_r_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs <1) THEN  'd_r_m_1' END) AS d_r_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_r_m_9' END) AS d_r_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs <1) THEN  'd_r_1' END) AS d_r_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'd_r_9' END) AS d_r_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs<=9) THEN  'd_r_0_9' END) AS d_r_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_r_f_14' END) AS d_r_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_r_f_19' END) AS d_r_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_r_f_24' END) AS d_r_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_r_f_49' END) AS d_r_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_r_f_50' END) AS d_r_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_r_10_50_f' END) AS d_r_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_r_m_14' END) AS d_r_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_r_m_19' END) AS d_r_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_r_m_24' END) AS d_r_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_r_m_49' END) AS d_r_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_r_m_50' END) AS d_r_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_r_10_50_m' END) AS d_r_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Routine VL' ) AND (AgeYrs<=100) THEN  'd_r_0_50' END) AS d_r_0_50, " +
" " +
"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers' ) AND (Sex='F' AND AgeYrs <1) THEN  'd_t_f_1' END) AS d_t_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_r_t_9' END) AS d_t_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs <1) THEN  'd_t_m_1' END) AS d_t_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_t_m_9' END) AS d_t_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs <1) THEN  'd_t_1' END) AS d_t_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'd_t_9' END) AS d_t_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs<=9) THEN  'd_t_0_9' END) AS d_t_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_t_f_14' END) AS d_t_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_t_f_19' END) AS d_t_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_t_f_24' END) AS d_t_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_t_f_49' END) AS d_t_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_t_f_50' END) AS d_t_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_t_10_50_f' END) AS d_t_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_t_m_14' END) AS d_t_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_t_m_19' END) AS d_t_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_t_m_24' END) AS d_t_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_t_m_49' END) AS d_t_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_t_m_50' END) AS d_t_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_t_10_50_m' END) AS d_t_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Pregnant Mother' OR Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure' OR Justification='Breast Feeding Mothers') AND (AgeYrs<=100) THEN  'd_t_0_50' END) AS d_t_0_50, " +
" " +
" " +
"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs <1) THEN  'd_nd_f_1' END) AS d_nd_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_nd_t_9' END) AS d_nd_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs <1) THEN  'd_nd_m_1' END) AS d_nd_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=1 AND AgeYrs<=9) THEN  'd_nd_m_9' END) AS d_nd_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs <1) THEN  'd_nd_1' END) AS d_nd_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs >=1 AND AgeYrs<=9) THEN  'd_nd_9' END) AS d_nd_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs<=9) THEN  'd_nd_0_9' END) AS d_nd_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_nd_f_14' END) AS d_nd_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_nd_f_19' END) AS d_nd_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_nd_f_24' END) AS d_nd_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_nd_f_49' END) AS d_nd_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_nd_f_50' END) AS d_nd_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_nd_10_50_f' END) AS d_nd_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=14) THEN  'd_nd_m_14' END) AS d_nd_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=15 AND AgeYrs<=19) THEN  'd_nd_m_19' END) AS d_nd_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=20 AND AgeYrs<=24) THEN  'd_nd_m_24' END) AS d_nd_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=25 AND AgeYrs<=49) THEN  'd_nd_m_49' END) AS d_nd_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=50 AND AgeYrs<=100) THEN  'd_nd_m_50' END) AS d_nd_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs >=10 AND AgeYrs<=100) THEN  'd_nd_10_50_m' END) AS d_nd_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (AgeYrs<=100) THEN  'd_nd_0_50' END) AS d_nd_0_50 " +
" " +
"FROM vl_validation join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  " +
" on "+facilitiestable+".DistrictID=district.DistrictID ) on vl_validation.MFL_Code="+facilitiestable+".CentreSanteId "
+ "   "+joinedwhwere+"  GROUP BY mfl_code " +

""; 
        
        conn.rs=conn.st.executeQuery(getVLData);
    
    while(conn.rs.next()){
            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
         colpos=0;
         for(String element:titles){
             String value = conn.rs.getString(element);
             cell_vl = rw.createCell(colpos);
             cell_vl.setCellStyle(stborder);
             if(isNumeric(value)){
               cell_vl.setCellValue(Integer.parseInt(value));  
                 if(totals.contains(","+colpos+",")){
                    cell_vl.setCellStyle(stylex); 
                 } 
             }
             else{
                 cell_vl.setCellValue(value);
             }
             
             
             colpos++;
         }
        
              }
    
              }
 //============================================================================================
    
   // new report post gbv
 //============================================================================================
    if(4==4){
    
     
        
            String month = "";
            String year = "";
            String facil = "361";
            String form = "moh731";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            
            
            String mainheaders[]={"","","","","","Female","","","","","","Male","","","","","Disagggregated by Type of Service","",""};
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Support type","Numerator","Female","<10","10-14","15-17","18-24","25+","Male","<10","10-14","15-17","18-24","25+","Sexual Violence (Post Rape Care)","PHYSICAL and/or EMOTIONAL Violence (Other Post GBV Care)","Verification","ART High Volume","HTC High Volume","PMTCT High Volume"};
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
            String merge_row_col[]={"0,0,0,23","1,1,0,4","1,1,5,6","1,1,7,11","1,1,13,17","1,1,18,19"};
            
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
                viralloadduration="Annee='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     viralloadduration="Annee='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     viralloadduration="Annee='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     viralloadduration="Annee='"+year+"' and quarter='"+quarter+"'  ";
                     
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
            
             if (!request.getParameter("county").equals("")) {
                
                county = request.getParameter("county");
                  subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711
             }
            
            if (!request.getParameter("subcounty").equals("")) {
                
                subcounty = request.getParameter("subcounty");
                 subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";
            }
            
            String getexistingdata = "";
            
            if (!county.equals("")) {
                
                countywhere = " and district.countyid = '" + county + "'";
                subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711  
            }
            
            if (!subcounty.equals("")) {
                
                subcountywhere = " and "+facilitiestable+".DistrictID = '" + subcounty + "'";
                
            }
            
            if (!facil.equals("")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where ("+facilitiestable+".PEP=1) " + yearwhere + " && " + duration + " " + countywhere + " " + subcountywhere;
        
            
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%        
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%        
            
            
       
            
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
    
    int blankrows=24;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport,ART_highvolume, HTC_highvolume,PMTCT_highvolume "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( PEP='1') group by "+facilitiestable+".SubPartnerID   "; 
    
   conn.rs=conn.st.executeQuery(getstaticfacilities);
    while(conn.rs.next()){
    
     staticcounty.add(conn.rs.getString("county"));
     String district=conn.rs.getString("district");
     staticdistrict.add(district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase());
     staticfacility.add(conn.rs.getString("facility"));
     staticmfl.add(conn.rs.getString("mflcode"));   
     //dsdta=conn.rs.getString("htcsupport");   
     String dsdta="DSD"; //static as of 201606 
     staticdsd_ta.add(dsdta);  
      if(conn.rs.getString("ART_highvolume")!=null){staticart_hv.add(conn.rs.getString("ART_highvolume"));} else {staticart_hv.add(""); }
     if(conn.rs.getString("HTC_highvolume")!=null){ statichtc_hv.add(conn.rs.getString("HTC_highvolume"));} else { statichtc_hv.add(""); }
     if(conn.rs.getString("PMTCT_highvolume")!=null){staticpmtct_hv.add(conn.rs.getString("PMTCT_highvolume"));} else {staticpmtct_hv.add("");}

     
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%         
            
            
            
            
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, "+facilitiestable+".SubPartnerID as SubPartnerID  FROM moh711 left join moh731 on moh731.id=moh711.id left join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county  union select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN, subpartnera.SubPartnerID as SubPartnerID  FROM moh711 right join moh731 on moh731.id=moh711.id right join vmmc on moh711.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID  order by county";
           //    getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode,ART_Support , sum(HV0507) as numerator  ,(sum(HV0502)+sum(HV0504)+sum(HV0506)) as femaletotal, (sum(HV0501)+sum(HV0503)+sum(HV0505)) as maletotal ,(sum(HV0503)+sum(HV0504)) as postrapecare, (sum(HV0501)+sum(HV0502)+sum(HV0505)+sum(HV0506)) as otherpostgbv, subpartnera.SubPartnerID as SubPartnerID  FROM moh731 join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on moh731.SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID ";
            getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode,ART_Support , (sum(HV0503)+sum(HV0504)) as numerator  ,(sum(HV0504)) as femaletotal, (sum(HV0503)) as maletotal ,(sum(HV0503)+sum(HV0504)) as postrapecare, "+facilitiestable+".SubPartnerID as SubPartnerID ,ART_highvolume, HTC_highvolume,PMTCT_highvolume FROM moh731 join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on moh731.SubPartnerID = "+facilitiestable+".SubPartnerID   "+joinedwhwere+" group by "+facilitiestable+".SubPartnerID ";
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
            
            
             //this font will be used to show errors on negatives
            HSSFCellStyle errorstyle = wb.createCellStyle();
            errorstyle.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
            errorstyle.setFillBackgroundColor(HSSFColor.RED.index);
            errorstyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            errorstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
            errorstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
            errorstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
            errorstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
            errorstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            
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
    
   	 
	 
	 
	 
	 //INSIDE WHILE LOOP
	 //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
        //REMOVE SITES THAT HAVE DATA FROM THE STATIC ARRAYLIST SET
        
        //get the index of the current facility
        int mflindex=staticmfl.indexOf(conn.rs.getString("mflcode"));
        
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
        
                         }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
	 
	 
	 
	    String arthv=" ";
     String htchv=" ";
     String pmtcthv=" ";
     
      if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getString("ART_highvolume");}
      if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getString("HTC_highvolume"); }
      if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getString("PMTCT_highvolume");}
        
        
        
        
        
        
        
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
            conpos++;//connection position

        }
        
                    //support type
          if (1 == 1) {

            HSSFCell clx = rwx.createCell(colpos);
            //clx.setCellValue(conn.rs.getString(conpos));
            clx.setCellValue("DSD");
            clx.setCellStyle(style2);

            colpos++;
            conpos++;//connection position

        }
          
        //get the variables
          int Numerator=0;
          int gbvfemaletotal=0;
          int gbvmaletotal=0;
          
          //female proportions
          double gbvless10f=0;
          double gbv10to14f=0;
          double gbv15to17f=0;
          double gbv18to24f=0;
          double gbv25f=0;          
          
          //male proportions
          double gbvless10m=0;
          double gbv10to14m=0;
          double gbv15to17m=0;
          double gbv18to24m=0;
          double gbv25m=0;
         int redalert=0;
          
          double maleverify=0; 
          double femaleverify=0;
          
          int postrapecare=0;
          int otherpostgbv=0;
          
          // (sum(HV0501)+sum(HV0502)+sum(HV0505)+sum(HV0506)) as otherpostgbv, subpartnera.SubPartnerID as SubPartnerID 
          
          Numerator=conn.rs.getInt("numerator");
          gbvmaletotal=conn.rs.getInt("maletotal");
          gbvfemaletotal=conn.rs.getInt("femaletotal");
          postrapecare=conn.rs.getInt("postrapecare");
          //otherpostgbv=conn.rs.getInt("otherpostgbv"); this was ignored later
          
          
         //begin the distributions 
        //< 10	10-14	15-17	18-24	25+	Male	< 10	10-14	15-17	18-24	25+
        //11.8%	18.4%	23.3%	10.3%	36.3%		4.3%	20.2%	14.6%	32.2%	28.8%

          
           gbvless10f=(float)Math.round((0.118*gbvfemaletotal));
           gbv10to14f=(float)Math.round((0.184*gbvfemaletotal));
           gbv15to17f=(float)Math.round((0.233*gbvfemaletotal));
           gbv18to24f=(float)Math.round((0.103*gbvfemaletotal));
           gbv25f=(float)Math.round((0.363*gbvfemaletotal));
          
           //then do the normalization
          femaleverify= gbvless10f+gbv10to14f+gbv15to17f+gbv18to24f+gbv25f;
          //do normalization for the female
  // if the two are not equal, do a distribution
  double currdiff=0;
  if(femaleverify<gbvfemaletotal){
     currdiff=gbvfemaletotal-femaleverify;
     if(currdiff>2){
     //raise an alarm
     redalert++;
     }
  //add to the groupings with the larger percentage until equal
        //25-49
      while(currdiff>0){ 
      gbv25f+=1; 
      currdiff--;
                       }
  
  }
  else if(femaleverify>gbvfemaletotal) {
  //minus  until equal
    currdiff=femaleverify-gbvfemaletotal;
  //add to the groupings with the larger percentage until equal
        //25-49
     if(currdiff>2){
     //raise an alarm
     redalert++;
     }
    
        while(currdiff>0){ 
 gbv25f-=1; 
 currdiff--;
                         }
  
                                               }
  
  //=====================work on males now==========================================================
     //begin the distributions 
        //< 10	10-14	15-17	18-24	25+	Male	< 10	10-14	15-17	18-24	25+
        //11.8%	18.4%	23.3%	10.3%	36.3%		4.3%	20.2%	14.6%	32.2%	28.8%

          
           gbvless10m=(float)Math.round((0.043*gbvmaletotal));
           gbv10to14m=(float)Math.round((0.202*gbvmaletotal));
           gbv15to17m=(float)Math.round((0.146*gbvmaletotal));
           gbv18to24m=(float)Math.round((0.322*gbvmaletotal));
               gbv25m=(float)Math.round((0.288*gbvmaletotal));
          
           //then do the normalization
          maleverify= gbvless10m+gbv10to14m+gbv15to17m+gbv18to24m+gbv25m;
        
   
                    //do normalization for the male
  // if the two are not equal, do a distribution
   currdiff=0;
  if(maleverify<gbvmaletotal){
     currdiff=gbvmaletotal-maleverify;
     if(currdiff>2){
     //raise an alarm
     redalert++;
     }
  //add to the groupings with the larger percentage until equal
        //25-49
      while(currdiff>0){ 
      gbv18to24m+=1; 
      currdiff--;
                       }
  
  }
  else if(maleverify>gbvmaletotal) {
  //minus  until equal
    currdiff=maleverify-gbvmaletotal;
  //add to the groupings with the larger percentage until equal
        //25-49
     if(currdiff>2){
     //raise an alarm
     redalert++;
     }
    
        while(currdiff>0){ 
 gbv18to24m-=1; 
 currdiff--;
                         }
  
                                               }
          
  ArrayList al=new ArrayList();
  al.add(Numerator);
  al.add(gbvfemaletotal);
  al.add(gbvless10f);
  al.add(gbv10to14f);
  al.add(gbv15to17f);
  al.add(gbv18to24f);
  al.add(gbv25f);
  al.add(gbvmaletotal);
  al.add(gbvless10m);
  al.add(gbv10to14m);
  al.add(gbv15to17m);
  al.add(gbv18to24m);
  al.add(gbv25m);
  al.add(postrapecare);
  al.add(otherpostgbv);
  
  

  
  
  //________________________________________________________________________________________________________
  //FINISH UP THE POST GBV
  //________________________________________________________________________________________________________
  for(int a=0;a<al.size();a++){
  
  
             //data from the arraylist
         

            HSSFCell clx = rwx.createCell(colpos);
            clx.setCellValue(Double.parseDouble(al.get(a).toString()));
            clx.setCellStyle(style2);

            colpos++;
          //
        
  
  
  }
  
  if(1==1){
  
  HSSFCell clx = rwx.createCell(colpos);
  if(redalert==0){
            clx.setCellValue("PASSED");
            clx.setCellStyle(style2);
  }
  else {
  clx.setCellValue("FAILED");
            clx.setCellStyle(errorstyle);
  }
  }
  
   if(1==1){
  colpos++;
  HSSFCell clx = rwx.createCell(colpos);
 
            clx.setCellValue(arthv);
            clx.setCellStyle(style2);
  
          }
   if(1==1){
  colpos++;
  HSSFCell clx = rwx.createCell(colpos);
 
            clx.setCellValue(htchv);
            clx.setCellStyle(style2);
  
          }
   
     if(1==1){
  colpos++;
  HSSFCell clx = rwx.createCell(colpos);
 
            clx.setCellValue(pmtcthv);
            clx.setCellStyle(style2);
  
            }
  
         //all the rows should come before this line  
          rowpos++;
          
          
          
    }//end of while loop 
        
        
   
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shet.createRow(rowpos);  
 rwx.setHeightInPoints(23);  
 rowpos++;
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   HSSFCell cellcounty=rwx.createCell(0); 
   cellcounty.setCellValue(staticcounty.get(a).toString());
   cellcounty.setCellStyle(style2);
  }
  else if(z==1){
    //sub-county  
   HSSFCell cellsubcounty=rwx.createCell(1); 
   cellsubcounty.setCellValue(staticdistrict.get(a).toString());
   cellsubcounty.setCellStyle(style2);
  }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(staticfacility.get(a).toString());
   cellfacil.setCellStyle(style2);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(staticmfl.get(a).toString());
   cellmfl.setCellStyle(style2);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(style2);
   
        }
else if(z==blankrows-4){
  //data status
   HSSFCell celldsd=rwx.createCell(blankrows-4); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }
                 	 else if(z==blankrows-3){
  //art high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-3); 
   celldsd.setCellValue(staticart_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
                         
                        else if(z==blankrows-2){
  //ht high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-2); 
   celldsd.setCellValue(statichtc_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
                                             }
                        
                        else if(z==blankrows-1){
  //pmtct high volume site
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue(staticpmtct_hv.get(a).toString());
   celldsd.setCellStyle(stborder);
   
                                                  }
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(style2);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
 
    
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
public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
    }
}

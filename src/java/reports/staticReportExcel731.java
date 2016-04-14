/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
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

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author Geofrey Nyabuto
 */
public class staticReportExcel731 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId;
String county,district,facilityname,mflcode;       
  String isValidated,validity;
int maxYearMonth;
String subcountyid,facility;
String reportType,duration,reportDuration,quarter,semi_annual;
int year,prevYear,month;
String header,facilityName,countyName,districtName,monthName;
String createdOn,elementName;
int counter,counterPMTCT,counterART,counterPEP,counterPMTCT1,counterART1,counterPEP1;
int pos,elementCounter,valueCounter;
String subsection,shortlabel,label;
int isPMTCT,isART,isPEP;
int startPMTCT,startART,startPEP,noPMTCT,noART,noPEP;
int specialElement;
String prevSection,currentSection;
int secCounter;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        dbConn conn = new dbConn();
        session=request.getSession();
        
        
          //--------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------
        //added later to accomodate the years
           String subpartnerid="SubPartnerID";
           String subpartnera="subpartnera";
           
           
           int monthint=0;
           int yearint=0;
           
        
        reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        yearint=year;
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
        startPMTCT=startART=startPEP=noPMTCT=noART=noPEP=0;
        if(reportDuration.equals("1")){
            
            //_________________________________annualy_____________________________________
	   
	       //solve subpartner table and facil_id first            
        if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
                         }
       else if(yearint==2015) {
           //this should be skipped since it picks both facil tables. 
		   //It has been disabled at the interface position
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
            
         duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        //_________________________________SemiAnnualy_________________________________
	 
	     //oct-mar
          if(quarter.equals("1")||quarter.equals("2")){
	   if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
		   //for oct-mar, use old database list
	 subpartnerid="SP_ID";
       subpartnera="subpartnera2014";  
		   
	   }
            
          }
          else if(quarter.equals("3")||quarter.equals("4")){
          //apr-sep
          
               //apr-sep
           
              if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
	     subpartnerid="SubPartnerID";
        subpartnera="subpartnera";	   
		   
	   }
              
          }
	      
            
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
           
           //oct-mar            
           if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
               //for oct-mar, use old database list
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";	   
		   
	                         }
           
       duration=" moh731.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
           
                //apr-sep
           
              if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
	subpartnerid="SubPartnerID";
        subpartnera="subpartnera";	   
		   
	   }
	  
           
       duration=" moh731.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
           }
       }
        
        else if(reportDuration.equals("3")){
            //quarterly
            
            String startMonth,endMonth;
     
       
       
       //_________________________________Quarterly__________________________________
	   
	   
	   quarter=request.getParameter("quarter");
            //specify subparter table and facil id first
            
              //oct-mar
          if(quarter.equals("1")||quarter.equals("2")){
	   if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
		   //for oct-mar, use old database list
	 subpartnerid="SP_ID";
       subpartnera="subpartnera2014";  
		   
	   }
            
          }
          else if(quarter.equals("3")||quarter.equals("4")){
          //apr-sep
          
               //apr-sep
           
              if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
	   else if(yearint==2015){
	     subpartnerid="SubPartnerID";
        subpartnera="subpartnera";	   
		   
	   }
              
          }
	   
       
       
       
       
//       quarter="3";
       String getMonths="SELECT months FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh731.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      }
      else{
     duration=" moh731.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
          
//_____________________________monthly________________________________
          
 //__________________________monthly reports_________________________


     //deal with subpartnertable and facilid first
      
        monthint=month;
     
       if(yearint==2015){
           
       if(monthint==10|| monthint==11 || monthint==12 || monthint==1||monthint==2|| monthint==3){
       //here use a different subpartner id
        subpartnerid="SP_ID";   
       subpartnera="subpartnera2014";
                                                                                                }
       else  {
           
       subpartnerid="SubPartnerID";
       subpartnera="subpartnera";
       
             }
           
                        }
       else  if(yearint<=2014){
       subpartnerid="SP_ID";
       subpartnera="subpartnera2014";
       }
       else if(yearint>2015) {
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
       }
       //---------------------------------------------------------------------------------------
       //---------------------------------------------------------------------------------------
	        
          
          
     month=Integer.parseInt(request.getParameter("month"));
//     month=5;
     if(month>=10){
     duration=" moh731.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh731.yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility=""; 
    
      facilityName="ALL APHIA PLUS SUPPORTED HEALTH FACILITIES";
      districtName="ALL";
      countyName="ALL COUNTIES";
      mflcode="NONE";
    }
      
      else{
  facilityId=request.getParameter("facility");
  
  String spid="";
	   
	 
//  facilityId="403";
  facility="moh731.SubPartnerID='"+facilityId+"' &&";    
  
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId   , SP_ID FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  conn.rs=conn.st.executeQuery(getName);
  
  if(conn.rs.next()==true){
      facilityName=conn.rs.getString(1);
      districtName=conn.rs.getString(2);
      countyName=conn.rs.getString(3);
      mflcode=conn.rs.getString(4);
     
	   
	   spid= conn.rs.getString(5);
	   
                         }
  
   if(subpartnerid.equalsIgnoreCase("SP_ID"))
  {
      
  facility="moh731.SubPartnerID='"+spid+"' &&";
  
  }
  
  
     }
     
    header+="</table>";  
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh731 WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
   System.out.println("max year month : "+maxYearMonth);
        
   
    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   HSSFWorkbook wb=new HSSFWorkbook();
  HSSFSheet shet1=wb.createSheet("PMTCT");
  HSSFSheet shet2=wb.createSheet("Care and Treatment");
  HSSFSheet shet3=wb.createSheet("PEP");
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
    
    HSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
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

   HSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.LIME.index);
styleHeader.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
HSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);

    
    for (int i=0;i<=1;i++){
   shet1.setColumnWidth(i, 14000);     
    }
    
    
  for (int i=0;i<=1;i++){
   shet2.setColumnWidth(i, 14000);     
    }
   
    
 for (int i=0;i<=1;i++){
   shet3.setColumnWidth(i, 14000);     
    }
   
 shet1.setColumnWidth(2, 4000);
 shet2.setColumnWidth(2, 4000);  
 shet3.setColumnWidth(2, 4000);
  
  String  headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
    String arrayHeader []=headers.split(",");
    int headerno=0;int valueNo=0; int arrayCounter=0;
   
//   XSSFRow rw0S1=shet1.createRow(0);
   HSSFRow rw1S1=shet1.createRow(0);  
   
//    XSSFRow rw0S2=shet2.createRow(0);
   HSSFRow rw1S2=shet2.createRow(0);
    
//    XSSFRow rw0S3=shet3.createRow(0);
   HSSFRow rw1S3=shet3.createRow(0);

  
    String getMonth="SELECT name FROM month WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     
   
  counterPMTCT=counterART=counterPEP=3; 

      
//   START OUTPUTTING THE RESULTS=================================================   
 

     System.out.println("facility : "+facility+"   duration : "+duration);
 
prevSection=currentSection="";
secCounter=0;


          String checker="SELECT "
+ "SUM(HV0101),SUM(HV0102),SUM(HV0103),SUM(HV0105),SUM(HV0106),SUM(HV0107),SUM(HV0108),SUM(HV0109),SUM(HV0110),SUM(HV0111),SUM(HV0112),SUM(HV0113),SUM(HV0114)," +
"SUM(HV0115),SUM(HV0116)," +
"SUM(HV0201),SUM(HV0202),SUM(HV0203),SUM(HV0204),SUM(HV0205),SUM(HV0206),SUM(HV0207),SUM(HV0208),SUM(HV0209),SUM(HV0210),SUM(HV0211),SUM(HV0212),SUM(HV0213)," +
"SUM(HV0214),SUM(HV0215),SUM(HV0216),SUM(HV0217),SUM(HV0218),SUM(HV0219),SUM(HV0220),SUM(HV0221),SUM(HV0224),SUM(HV0225),SUM(HV0226),SUM(HV0227),SUM(HV0228),SUM(HV0229)," +
"SUM(HV0230),SUM(HV0231),SUM(HV0232),SUM(HV0233),SUM(HV0234),SUM(HV0235),SUM(HV0236),SUM(HV0237),SUM(HV0238),SUM(HV0239),SUM(HV0240),SUM(HV0241),SUM(HV0242)," +
"SUM(HV0243),SUM(HV0244)," +
"SUM(HV0301),SUM(HV0302),SUM(HV0303),SUM(HV0304),SUM(HV0305),SUM(HV0306),SUM(HV0307),SUM(HV0308),SUM(HV0309),SUM(HV0310),SUM(HV0311),SUM(HV0312),SUM(HV0313),SUM(HV0314)," +
"SUM(HV0315),SUM(HV0316),SUM(HV0317),SUM(HV0318),SUM(HV0319),SUM(HV0320),SUM(HV0321),SUM(HV0322),SUM(HV0323),SUM(HV0324),SUM(HV0325),SUM(HV0326),SUM(HV0327),SUM(HV0328)," +
"SUM(HV0329),SUM(HV0330),SUM(HV0331),SUM(HV0332),SUM(HV0333),SUM(HV0334),SUM(HV0335),SUM(HV0336),SUM(HV0337),SUM(HV0338),SUM(HV0339),SUM(HV0340),SUM(HV0341)," +
"SUM(HV0342),SUM(HV0343),SUM(HV0344),SUM(HV0345),SUM(HV0346),SUM(HV0347),SUM(HV0348),SUM(HV0349),SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353)," +
"SUM(HV0354),SUM(HV0355),SUM(HV0904),SUM(HV0905),SUM(HV0370),SUM(HV0371),SUM(HV0372),SUM(HV0373)," +
"SUM(HV0401),SUM(HV0402),SUM(HV0403),SUM(HV0406),SUM(HV0407),SUM(HV0408),SUM(HV0409),SUM(HV0410),SUM(HV0411),SUM(HV0412),SUM(HV0413),SUM(HV0414),SUM(HV0415)," +
"SUM(HV0501),SUM(HV0502),SUM(HV0503),SUM(HV0504),SUM(HV0505),SUM(HV0506),SUM(HV0507),SUM(HV0508),SUM(HV0509),SUM(HV0510),SUM(HV0511),SUM(HV0512),SUM(HV0513),SUM(HV0514)," +
"SUM(HV0601),SUM(HV0602),SUM(HV0605),"+subpartnera+".PMTCT, "+subpartnera+".ART,"+subpartnera+".PEP "
 +"FROM moh731 JOIN "+subpartnera+" ON moh731.SubPartnerID="+subpartnera+"."+subpartnerid+" WHERE "+facility+" "+duration;
 
        System.out.println("@@@  "+checker);   
          conn.rs=conn.st.executeQuery(checker);
          if(conn.rs.next()==true){
//              countyName=conn.rs.getString(1);
//              districtName=conn.rs.getString(2);
//              facilityName=conn.rs.getString(3);
//              mflcode=conn.rs.getString(4);
              
              isPMTCT=conn.rs.getInt(149);
              isART=conn.rs.getInt(150);
              isPEP=conn.rs.getInt(151);
              
          String headerValues=countyName+","+districtName+","+facilityName+","+mflcode;
          String arrayValues[]=headerValues.split(",");
          String headerValue="";  
//   CREATE HEADERS
     for(String headername:arrayHeader){
    headerValue=arrayValues[arrayCounter];
   HSSFRow rw1S10=shet1.createRow(pos);  
   HSSFRow rw1S20=shet2.createRow(pos);
   HSSFRow rw1S30=shet3.createRow(pos);
    
    HSSFCell  S1cell=rw1S10.createCell(0);
    S1cell.setCellValue(headername);
    S1cell.setCellStyle(stylex);
    
    HSSFCell  S1cellX=rw1S10.createCell(1);
    S1cellX.setCellValue(headerValue);
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(2);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(3);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
   
    HSSFCell  S2cell=rw1S20.createCell(0);
    S2cell.setCellValue(headername);
    S2cell.setCellStyle(stylex);
    
    HSSFCell  S2cellX=rw1S20.createCell(1);
    S2cellX.setCellValue(headerValue);
    S2cellX.setCellStyle(stylex);
    
    S2cellX=rw1S20.createCell(2);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    S2cellX=rw1S20.createCell(3);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    
    HSSFCell  S3cell=rw1S30.createCell(0);
    S3cell.setCellValue(headername);
    S3cell.setCellStyle(stylex);
    
    HSSFCell  S3cellX=rw1S30.createCell(1);
    S3cellX.setCellValue(headerValue);
    S3cellX.setCellStyle(stylex);
    
    S3cellX=rw1S30.createCell(2);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
    S3cellX=rw1S30.createCell(3);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
   
      arrayCounter++;
      pos++;
     }      
     
//     pos+=1;
//  OUTPUT ELEMENT HEADING
     
   HSSFRow rw1S10=shet1.createRow(pos);  
   HSSFRow rw1S20=shet2.createRow(pos);
   HSSFRow rw1S30=shet3.createRow(pos);
   
    rw1S10.setHeightInPoints(25);
    rw1S20.setHeightInPoints(25);
    rw1S30.setHeightInPoints(25);
    
    HSSFCell  S1cell=rw1S10.createCell(0);
    S1cell.setCellValue("SUB SECTION");
    S1cell.setCellStyle(styleHeader);
    
    HSSFCell  S1cellX=rw1S10.createCell(1);
    S1cellX.setCellValue("ELEMENT TITLE");
    S1cellX.setCellStyle(styleHeader);
    
    HSSFCell  S1cellX2=rw1S10.createCell(2);
    S1cellX2.setCellValue("LABEL");
    S1cellX2.setCellStyle(styleHeader);
    
    S1cellX2=rw1S10.createCell(3);
    S1cellX2.setCellValue("VALUE");
    S1cellX2.setCellStyle(styleHeader);
    
   
    HSSFCell  S2cell=rw1S20.createCell(0);
    S2cell.setCellValue("SUB SECTION");
    S2cell.setCellStyle(styleHeader);
    
    HSSFCell  S2cellX=rw1S20.createCell(1);
    S2cellX.setCellValue("ELEMENT TITLE");
    S2cellX.setCellStyle(styleHeader);
    
    HSSFCell  S2cellX2=rw1S20.createCell(2);
    S2cellX2.setCellValue("LABEL");
    S2cellX2.setCellStyle(styleHeader);
    
    S2cellX2=rw1S20.createCell(3);
    S2cellX2.setCellValue("VALUE");
    S2cellX2.setCellStyle(styleHeader);
    
    HSSFCell  S3cell=rw1S30.createCell(0);
    S3cell.setCellValue("SUB SECTION");
    S3cell.setCellStyle(styleHeader);
    
    HSSFCell  S3cellX=rw1S30.createCell(1);
    S3cellX.setCellValue("ELEMENT TITLE");
    S3cellX.setCellStyle(styleHeader);
    
    HSSFCell  S3cellX2=rw1S30.createCell(2);
    S3cellX2.setCellValue("LABEL");
    S3cellX2.setCellStyle(styleHeader);
    
    S3cellX2=rw1S30.createCell(3);
    S3cellX2.setCellValue("VALUE");
    S3cellX2.setCellStyle(styleHeader);
     
     elementCounter=1;
     valueCounter=1;
     specialElement=0;
      String getCummulatives="SELECT "
//              + "SUM(HV0301),SUM(HV0302),"
     + "SUM(HV0303),SUM(HV0304),SUM(HV0305),SUM(HV0306),SUM(HV0307),"
     + "SUM(HV0314),SUM(HV0315),SUM(HV0316),SUM(HV0317),SUM(HV0318),SUM(HV0319),"
     + "SUM(HV0328),SUM(HV0329),SUM(HV0330),SUM(HV0331),SUM(HV0332),SUM(HV0333),SUM(HV0334),SUM(HV0335),"
     + "SUM(HV0336),SUM(HV0337),SUM(HV0338),SUM(HV0339),SUM(HV0340),SUM(HV0341),SUM(HV0342),SUM(HV0343),SUM(HV0344), "
     + "SUM(HV0350),SUM(HV0351),SUM(HV0352),SUM(HV0353),SUM(HV0354),SUM(HV0355) "
     +"FROM moh731 join subpartnera on moh731.subpartnerid=subpartnera.subpartnerid WHERE "+facility+" art=1 && yearmonth="+maxYearMonth;
    conn.rs2=conn.st2.executeQuery(getCummulatives);
    if(conn.rs2.next()==true){
System.out.println("entered to get cumulatives : "+maxYearMonth);
   

      int j=5;
      int i=5;
      int k=5;
     String getElements="SELECT subsection,shortlabel,label FROM pivottable WHERE form='moh731' ORDER BY tableid";
     conn.rs1=conn.st1.executeQuery(getElements);
     while(conn.rs1.next()){
//     subsection,shortlabel,label;  
      subsection=conn.rs1.getString(1);
       shortlabel=conn.rs1.getString(2);
       label=conn.rs1.getString(3);
       
      elementCounter++; 
      valueCounter=elementCounter+4;
      
      if(elementCounter>=17 && elementCounter<=58){
      if(isPMTCT==1 && j<=47){
       int valuePos=j+15-4;
      HSSFRow rw1S11=shet1.createRow(j);     
      HSSFCell  S1cell1=rw1S11.createCell(0);
    S1cell1.setCellValue(subsection);
    S1cell1.setCellStyle(stborder);
    
    HSSFCell  S1cellX1=rw1S11.createCell(1);
    S1cellX1.setCellValue(shortlabel);
    S1cellX1.setCellStyle(stborder);
    
    HSSFCell  S1cellX21=rw1S11.createCell(2);
    S1cellX21.setCellValue(label);
    S1cellX21.setCellStyle(stborder);
    
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs.getInt(valuePos));
    S1cellX21.setCellStyle(stborder);
    
    currentSection=subsection;
    
     if(prevSection.equals(currentSection) && !prevSection.equals("")){
   secCounter++;      
//    System.out.println("THey are equal  :"+prevSection+" current sec:   "+currentSection);
     }
   else if(j==5){
  prevSection=currentSection=subsection;
//secCounter++;         
//     System.out.println("entered j=5 :"+j+"  :"+prevSection+" current sec:  "+currentSection);
    }
   
   else if(!prevSection.equals(currentSection)){
        int startMerger=j-secCounter-1;
        int endMerger=j-1;
      shet1.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));  
    secCounter=0;   
//    System.out.println("merged cells from :"+startMerger+" to :"+endMerger);
   
   }
  
   else{
         System.out.println("cant think anymore");
   }
       prevSection=currentSection;   
      
          j++;
//          System.out.println("j values : "+j);
             if(j==47){
       System.out.println("entered end here j "+j);
    int startMerger=j-secCounter-1;
    int endMerger=j-1;
      shet1.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));  
    secCounter=0;       
  prevSection=currentSection="";
   }
      }
      
      }
     if(elementCounter>=59 && elementCounter<=119){ 
          if(isART==1 && i<=66){
      int valuePos=i+57-4;
      HSSFRow rw1S11=shet2.createRow(i);     
      HSSFCell  S1cell1=rw1S11.createCell(0);
    S1cell1.setCellValue(subsection);
    S1cell1.setCellStyle(stborder);
    
    HSSFCell  S1cellX1=rw1S11.createCell(1);
    S1cellX1.setCellValue(shortlabel);
    S1cellX1.setCellStyle(stborder);
    
   HSSFCell  S1cellX21=rw1S11.createCell(2);
    S1cellX21.setCellValue(label);
    S1cellX21.setCellStyle(stborder); 
    
    if(elementCounter>=61 && elementCounter<=65){
        specialElement++;
//        System.out.println("entered on cumus for ctx>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
//        System.out.println("Value is >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+conn.rs2.getInt(specialElement));
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs2.getInt(specialElement));
//    S1cellX21.setCellValue("");
    S1cellX21.setCellStyle(stborder);    
          
    }
    
  else if(elementCounter>=72 && elementCounter<=77){
        specialElement++;
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs2.getInt(specialElement));
    S1cellX21.setCellStyle(stborder);    
          
    }
   
   else if(elementCounter>=86 && elementCounter<=91){
        specialElement++;
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs2.getInt(specialElement));
    S1cellX21.setCellStyle(stborder);    
          
    }
    
    else if(elementCounter>=92 && elementCounter<=102){
       specialElement++;
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs2.getInt(specialElement));
    S1cellX21.setCellStyle(stborder);        
    }
    else if(elementCounter>=108 && elementCounter<=113){
       specialElement++;
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs2.getInt(specialElement));
    S1cellX21.setCellStyle(stborder);        
    }
    else{
     S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs.getInt(valuePos));
    S1cellX21.setCellStyle(stborder);     
    }
    
    currentSection=subsection;
    
     if(prevSection.equals(currentSection) && !prevSection.equals("")){
   secCounter++;      
//    System.out.println("THey are equal  :"+prevSection+" current sec:   "+currentSection);
     }
   else if(i==5){
  prevSection=currentSection=subsection;
//secCounter++;         
//    System.out.println("entered j=5 :"+i+"  :"+prevSection+" current sec:  "+currentSection);
    }
   
   else if(!prevSection.equals(currentSection)){
        int startMerger=i-secCounter-1;
        int endMerger=i-1;
      shet2.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));  
    secCounter=0;   
//    System.out.println("merged cells from :"+startMerger+" to :"+endMerger);
   
   }
  
   else{
//         System.out.println("cant think anymore");
   }
       prevSection=currentSection;   
      
          i++;

             if(i==66){
//       System.out.println("entered end here i "+i);
    int startMerger=i-secCounter-1;
    int endMerger=i-1;
      shet2.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));  
    secCounter=0;       
  prevSection=currentSection="";
   }
    
          }
     }
     if(elementCounter>=133 && elementCounter<=146){
    if(isPEP==1 && k<=18){
      int valuePos=k+131-4;
      System.out.println("k values : "+k);
      HSSFRow rw1S11=shet3.createRow(k);     
      HSSFCell  S1cell1=rw1S11.createCell(0);
    S1cell1.setCellValue(subsection);
    S1cell1.setCellStyle(stborder);
    
    HSSFCell  S1cellX1=rw1S11.createCell(1);
    S1cellX1.setCellValue(shortlabel);
    S1cellX1.setCellStyle(stborder);
    
    HSSFCell  S1cellX21=rw1S11.createCell(2);
    S1cellX21.setCellValue(label);
    S1cellX21.setCellStyle(stborder);    
     
    S1cellX21=rw1S11.createCell(3);
    S1cellX21.setCellValue(conn.rs.getInt(valuePos));
    S1cellX21.setCellStyle(stborder);
    
    currentSection=subsection;
    
     if(prevSection.equals(currentSection) && !prevSection.equals("")){
   secCounter++;      
     }
   else if(k==5){
  prevSection=currentSection=subsection;
    }
   
   else if(!prevSection.equals(currentSection)){
        int startMerger=k-secCounter-1;
        int endMerger=k-1;
        if(startMerger==endMerger){}
        else{
      shet3.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));
        }
    secCounter=0;   
   System.out.println("merged cells from :"+startMerger+" to :"+endMerger);
   
   }
  
   else{
         System.out.println("cant think anymore");
   }
       prevSection=currentSection;   
      
          k++;

             if(k==18){
    int startMerger=k-secCounter-1;
    int endMerger=k;
      shet3.addMergedRegion(new CellRangeAddress(startMerger,endMerger,0,0));
    secCounter=0;       
  prevSection=currentSection="";
   }
             
      }
    
     }
     } 
     }
 System.out.println("Data already exist loading............................");

        counter++;  
   }
         
          System.out.println("Validity checker : "+isValidated);
    
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
response.setHeader("Content-Disposition", "attachment; filename=MOH731_STATIC_REPORT_CREATED_"+createdOn.trim()+".xls");
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
        Logger.getLogger(staticReportExcel731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(staticReportExcel731.class.getName()).log(Level.SEVERE, null, ex);
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

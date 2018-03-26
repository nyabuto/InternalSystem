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
import java.sql.ResultSetMetaData;
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
 * @author Emmanuel E
 */
public class datimTbViralRetention_2018Q1 extends HttpServlet {

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
String facilityName,mflcode,countyName,districtName,facilityIds,facilityIdsCohort,facilityId;
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
if(3==3){

    
    
 //__________________________________________________________________________________________



ArrayList allFacilities = new ArrayList();
                     
int year,month,prevYear,maxYearMonth,mflcode;
			  
String reportDuration,duration,semi_annual,quarter;

String facilityName,countyName,districtName,facilityIds,facilityId;

year=month=prevYear=maxYearMonth=mflcode=0;
   
reportDuration=duration=semi_annual=quarter="";
 
facilityName=countyName=districtName=facilityIds=facilityId="";



//_________________startdate________________________


String startdate="";
String enddate="";


//_________________enddate__________________________


 
year=Integer.parseInt(request.getParameter("year"));
  
  
Calendar cale= Calendar.getInstance();
  
 currentyear=cale.get(Calendar.YEAR);
  
 facilitiestable="subpartnera";
  
 selectedyear=year;
  
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
  
 
 facilityIds1="";

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
 

 facilityIds1+=" tibu_tb_raw.SubPartnerID='"+conn.rs.getString(1)+"' || ";
 
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
    

facilityIds1+=" tibu_tb_raw.SubPartnerID='"+conn.rs.getString(1)+"' || ";
                         }
   
facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
	
     facilityIds1+=") && "; 
	 
     }
       
        else{
  
       facilityIds1=""; 
	   
        }   
        
     }      
        reportDuration=request.getParameter("reportDuration");
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
			
         duration1=" tibu_tb_raw.yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM TB STAT , TB ART TX TB report for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" tibu_tb_raw.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM TB STAT , TB ART TX TB report for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" tibu_tb_raw.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM TB STAT , TB ART TX TB report for April "+year+" to September "+year; 
       
      //______start and end date_________ 
     startdate=year+"0401";
        enddate=year+"0930";
       
       
       }
       }
        
        else if(reportDuration.equals("3")){
            
            String startMonth,endMonth;
            
       quarter=request.getParameter("quarter");
//       quarter="3";
       String getMonths="SELECT months,name,enddate FROM quarter WHERE id='"+quarter+"'";
       
       conn.rs=conn.st.executeQuery(getMonths);
       
       if(conn.rs.next()==true){
           
      String months []=conn.rs.getString(1).split(",");
      
       startMonth=months[0];
       
       endMonth=months[2];
       
      if(quarter.equals("1")){
          
      duration1=" tibu_tb_raw.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM TB STAT , TB ART TX TB report for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" tibu_tb_raw.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM TB STAT , TB ART TX TB report for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10)
   {
     duration1=" tibu_tb_raw.yearmonth="+prevYear+""+month;    
     period1="DATIM TB STAT , TB ART TX TB report for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
   else {
  duration1=" tibu_tb_raw.yearmonth="+year+"0"+month;  
    period1="DATIM TB STAT , TB ART TX TB report for : "+conn.rs.getString(1)+"("+year+")";
    
    //___start and end date____
     startdate=year+"0"+month+"01";
     enddate=year+"0"+month+conn.rs.getString("days");
    
     }
      }
      }
      else{
     duration1="";     
      }


 
 //__________________________________________________________________________________________ 
    
    
    
    //_____________________________________________________________________________________
    //Headers
    //_____________________________________________________________________________________
    
//String header1[]={"Facility Details","","","","","TBSTAT (Numerator)","","","","","","","","","","","","","","","","","","","","","","","","","","","","TB_STAT (Denominator)","","","","","","","","","","TB_ART(Numerator)","","","","","","","","","","","","","","","","","","","","","TB_ART (Denominator)","","","","","","","","","","","","","","","","","","","","","TX_TB(Numerator)","","","","","","","TX_TB (Denominator)","","","","","","","","","",""};


//String header2[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","Unknown Sex","","","","","","","","","Female","","","","","","","","","Male","","","","","","","","","Denominator","Unknown Sex","","","Female","","","Male","","","Numerator","Disaggregated by Result","","Disaggregated by Age / Sex (Fine Disaggregate)","","","","","","","","","","","","","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Denominator","Disaggregated by Result","","Disaggregated by Age / Sex (Fine Disaggregate)","","","","","","","","","","","","","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Numerator","Disaggregated by Current / New on ART","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Denominator","Disaggregated by Screen Result","","Disaggregated by Specimen sent","[Disagg of specimen Sent] Diagonistic Test","","","Disaggregated by age/Sex(Coarse Disaggregate)","","",""};

//String header3[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Denominator","Unknown Sex","","","Female","","","Male","","","Numerator","Disaggregated by Result","","Female","","","","","","","Male","","","","","","","Female","","Male","","Denominator","Disaggregated by Result","","Female","","","","","","","Male","","","","","","","Female","","Male","","Numertaor","The number of patients starting TB treatment who newly started ART during the reporting period","The number of patients starting TB treatment who were already ART during the reporting period","Female","","Male","","Denominator","Disaggregated by Screen Result","","Disaggregated by Specimen sent","Number of Patients whose specimens were sent for the following diagnostic tests","","","Female","","Male",""};

//String header4[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","unknown","<15",">15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","> 15","> 15","unknown","<15","> 15","Denominator","Unknown","<15","15+","Unknown","<15","15+","Unknown","<15","15+","Numerator","Already on ART","New on ART","<1","1-9","10-14","15-19","20-24","25-49","50+","<1","1-9","10-14","15-19","20-24","25-49","50+","<15","15+","<15","15+","Denominator","On ART","Not on ART","<1","1-9","10-14","15-19","20-24","25-49","50+","<1","1-9","10-14","15-19","20-24","25-49","50+","<15","15+","<15","15+","Numerator","The number of patients starting TB treatment who newly started ART during the reporting period","The number of patients starting TB treatment who were already ART during the reporting period","<15","15+","<15","15+","Denominator","Number of ART patients who had atleast one positive screen during the reporting period.","Number of ART patients who had all negative screens during the reporting period.","Number of ART Patients who had a specimen sent for bacteriologic diagnosis of active TB disease","Smear Only","Gene Xpert MTB / R if Assay","Other (No Xpert)","<15","15+","<15","15+"} ;   

//---------------------------

String header1[]={"Facility Details","","","","","TBSTAT (Numerator)","","","","","","","","","","","","","","","","","","","","","","","","","","","","TB_STAT (Denominator)","","","","","","","","","","TB_ART(Numerator)","","","","","","","","","","","","","","","","","","","","","","","","","","","TB_ART (Denominator)","","","","","","","","","","","","","","","","","","","","","","","","","","","TX_TB(Numerator)","","","","","","","TX_TB (Denominator)","","","","","","","","","",""};


String header2[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","Unknown Sex","","","","","","","","","Female","","","","","","","","","Male","","","","","","","","","Denominator","Unknown Sex","","","Female","","","Male","","","Numerator","Disaggregated by Result","","Disaggregated by Age / Sex (Fine Disaggregate)","","","","","","","","","","","","","","","","","","","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Denominator","Disaggregated by Result","","Disaggregated by Age / Sex (Fine Disaggregate)","","","","","","","","","","","","","","","","","","","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Numerator","Disaggregated by Current / New on ART","","Disaggregated by age/Sex(Coarse Disaggregate)","","","","Denominator","Disaggregated by Screen Result","","Disaggregated by Specimen sent","[Disagg of specimen Sent] Diagonistic Test","","","Disaggregated by age/Sex(Coarse Disaggregate)","","",""};

String header3[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Known Positives","","","Newly Tested Positives","","","New Negatives","","","Denominator","Unknown Sex","","","Female","","","Male","","","Numerator","Disaggregated by Result","","Female","","","","","","","","","","Male","","","","","","","","","","Female","","Male","","Denominator","Disaggregated by Result","","Female","","","","","","","","","","Male","","","","","","","","","","Female","","Male","","Numertaor","The number of patients starting TB treatment who newly started ART during the reporting period","The number of patients starting TB treatment who were already ART during the reporting period","Female","","Male","","Denominator","Disaggregated by Screen Result","","Disaggregated by Specimen sent","Number of Patients whose specimens were sent for the following diagnostic tests","","","Female","","Male",""};

String header4[]={"County","Sub-County","Health Facility","MFL Code","Type of Support","Numerator","unknown","<15",">15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","unknown","<15","> 15","unknown","<15","> 15","unknown","<15","> 15","> 15","unknown","<15","> 15","Denominator","Unknown","<15","15+","Unknown","<15","15+","Unknown","<15","15+","Numerator","Already on ART","New on ART","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","<15","15+","<15","15+","Denominator","On ART","Not on ART","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","<15","15+","<15","15+","Numerator","The number of patients starting TB treatment who newly started ART during the reporting period","The number of patients starting TB treatment who were already ART during the reporting period","<15","15+","<15","15+","Denominator","Number of ART patients who had atleast one positive screen during the reporting period.","Number of ART patients who had all negative screens during the reporting period.","Number of ART Patients who had a specimen sent for bacteriologic diagnosis of active TB disease","Smear Only","Gene Xpert MTB / R if Assay","Other (No Xpert)","<15","15+","<15","15+"} ;   




//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

        HSSFFont font = wb.createFont();
        font.setFontHeightInPoints((short) 18);
        font.setFontName("Cambria");
        font.setColor((short) 0000);
        CellStyle style = wb.createCellStyle();
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
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
        stylex.setAlignment(HSSFCellStyle.ALIGN_LEFT);

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        HSSFFont fontx = wb.createFont();
        fontx.setColor(HSSFColor.BLACK.index);
        fontx.setFontName("Cambria");
        stylex.setFont(fontx);
        stylex.setWrapText(true);

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("TB STAT , TB ART, TX TB");

         mwaka="";
        
        if(request.getParameter("year")!=null)
        {
        
            mwaka=request.getParameter("year");
        
        }
        
        
        
        
        //_____________________header rows _____________________
        
         int count1  = 1;
//header rows
            HSSFRow rw = shet.createRow(count1);
            rw.setHeightInPoints(26);
            
            for (int i = 0; i < header1.length; i++) 
            {                  
                    HSSFCell cell0 = rw.createCell(i);
                    cell0.setCellValue(header1[i]);
                    cell0.setCellStyle(stylex);
                    //create row header
            }//end of for loop
            count1++;
             rw = shet.createRow(count1);
             for (int i = 0; i < header2.length; i++) 
            {                  
                    HSSFCell cell0 = rw.createCell(i);
                    cell0.setCellValue(header2[i]);
                    cell0.setCellStyle(stylex);
                    //create row header
            }//end of for loop
            
              count1++;
             rw = shet.createRow(count1);
             for (int i = 0; i < header3.length; i++) 
            {                  
                    HSSFCell cell0 = rw.createCell(i);
                    cell0.setCellValue(header3[i]);
                    cell0.setCellStyle(stylex);
                    //create row header
            }//end of for loop
        
             
              count1++;
             rw = shet.createRow(count1);
             for (int i = 0; i < header4.length; i++) 
            {                  
                    HSSFCell cell0 = rw.createCell(i);
                    cell0.setCellValue(header4[i]);
                    cell0.setCellStyle(stylex);
                    //create row header
            }//end of for loop
          count1++;
        //_____________________header rows _____________________
        
        
       // dbConn conn = new dbConn();
        //========Query 1=================
        // String mergerecords[]={"0,0,0,6","1,1,0,4","1,1,5,32","1,1,33,42","1,1,43,63","1,1,64,84","1,1,85,91","1,1,92,102","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,2,6,14","2,2,15,23","2,2,24,32","2,3,34,36","2,3,37,39","2,3,40,42","2,4,33,33","2,4,43,43","2,3,44,45","2,3,44,45","2,2,46,59","2,2,60,63","2,4,64,64","2,3,65,66","2,2,67,80","2,2,81,84","2,4,85,85","2,2,86,87","2,2,88,91","2,4,92,92","2,3,93,94","2,3,95,95","2,2,96,98","2,2,99,102","3,3,6,8","3,3,9,11","3,3,12,14","3,3,15,17","3,3,18,20","3,3,21,23","3,3,24,26","3,3,27,29","3,3,30,32","3,3,46,52","3,3,53,59","3,3,60,61","3,3,62,63","3,3,67,73","3,3,74,80","3,3,81,82","3,3,83,84","3,3,88,89","3,3,90,91","3,4,86,86","3,4,87,87","3,3,96,98","3,3,99,100","3,3,101,102"};
        
        String mergerecords[]={"0,0,0,6","1,1,0,4","1,1,5,32","1,1,33,42","1,1,43,69","1,1,70,96","1,1,97,103","1,1,104,114","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,2,6,14","2,2,15,23","2,2,24,32","2,3,34,36","2,3,37,39","2,3,40,42","2,4,33,33","2,4,43,43","2,3,44,45","2,3,44,45","2,2,46,65","2,2,66,69","2,4,70,70","2,3,71,72","2,2,73,92","2,2,93,96","2,4,97,97","2,2,98,99","2,2,100,103","2,4,104,104","2,3,105,106","2,3,107,107","2,2,108,110","2,2,111,114","3,3,6,8","3,3,9,11","3,3,12,14","3,3,15,17","3,3,18,20","3,3,21,23","3,3,24,26","3,3,27,29","3,3,30,32","3,3,46,55","3,3,56,65","3,3,66,67","3,3,68,69","3,3,73,82","3,3,83,92","3,3,93,94","3,3,95,96","3,3,100,101","3,3,102,103","3,4,98,98","3,4,99,99","3,3,108,110","3,3,111,112","3,3,113,114"};
       
        HSSFRow rw0=shet.createRow(0);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        //shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
          
        
         for (int a = 0; a <mergerecords.length; a++) 
        {
           String points[]=mergerecords[a].split(",");
                
           shet.addMergedRegion(new CellRangeAddress(Integer.parseInt(points[0]),Integer.parseInt(points[1]),Integer.parseInt(points[2]),Integer.parseInt(points[3])));
              
         }
        
         //merging zone      
        
        
        
  String qry1 = "call rpt_tb_stat(\""+startdate+"\",\""+enddate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
    System.out.println("==="+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

           
            
            //data rows     
             rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3)
                 {
                
                    cell0.setCellValue(conn.rs.getInt(a+1));
                    
                   }
                 else if(a > 4)
                 {
                
                    cell0.setCellValue(conn.rs.getInt(a+1));
                    
                   }
                else 
                 {
                    
                    cell0.setCellValue(conn.rs.getString(a+1));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }

     
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) 
        {
           // shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5,5);  


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
        
        
String headerRETENTION[]="County,Sub County,Health Facility,MFL Code,Type of support,12 Months,24 Months,36 Months,Pregnant,Breastfeeding,Sub-total,<1 Y,1-9Y,subtotal,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,subtotal,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,Subtotal,Sub-Total,12 Months,24 Months,36 Months,Pregnant,Breastfeeding,Sub-total,<1Y,1-9Y,subtotal,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,subtotal,10-14Y,15-19Y,20-24Y,25-29Y,30-34Y,35-39Y,40-49Y,50+Y,Subtotal,Sub-Total,Verification Status,ART High Volume,HTC High Volume,PMTCT High Volume".split(",") ;
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
        facilityIdsCohort="(";
        facilityIds1="(";
        retentionPos=0;
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
         duration=" .yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        period="DATIM ANNUAL DATA REPORT FOR PEPFAR YEAR : "+year;
        excelDuration="year='"+year+"' && ";
                                       }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration=" .yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period="DATIM SEMI - ANNUAL DATA REPORT FOR : OCT "+prevYear+" to MARCH "+year;
     
     excelDuration=" year='"+year+"' && quarter<=2 && ";
       }
           else{
       duration=" .yearmonth BETWEEN "+year+"04 AND "+year+"09";      
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
      duration=" .yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      period="DATIM QUARTERLY DATA REPORT FOR : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      }
      else{
     duration=" .yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
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
     duration=" .yearmonth="+prevYear+""+month;    
     period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+prevYear+")"; 
     }
     else{
  duration=" .yearmonth="+year+"0"+month;  
    period="DATIM MONTHLY DATA REPORT FOR : "+conn.rs.getString(1)+"("+year+")";
     }
      }
      }
      else{
     duration="";     
      }
     
     if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT "+facilitiestable+".SubPartnerID,"+facilitiestable+".CentreSanteId AS mfl_code  FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
     subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";
    
    conn.rs=conn.st.executeQuery(getDist);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
     facilityIdsCohort+=" pmtct_cohort.mflcode='"+conn.rs.getString(2)+"' || ";
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
      facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";   
      facilityIdsCohort = facilityIdsCohort.substring(0, facilityIdsCohort.length()-3);
     facilityIdsCohort+=") && ";   
      facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";   
     } 
     else{
        if(request.getParameter("county")!=null && !request.getParameter("county").equals(""))   {  
         String county=request.getParameter("county");
         String getCounty="SELECT "+facilitiestable+".SubPartnerID,"+facilitiestable+".CentreSanteId AS mfl_code FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
     + "JOIN county ON district.CountyID=county.CountyID WHERE county.CountyID='"+county+"'" ;
         
           subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711
         
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || ";
     facilityIdsCohort+=" pmtct_cohort.mflcode='"+conn.rs.getString(2)+"' || ";
     facilityIds1+=" moh711.SubPartnerID='"+conn.rs.getString(1)+"' || ";
    }
    facilityIds = facilityIds.substring(0, facilityIds.length()-3);
     facilityIds+=") && ";     
    facilityIdsCohort = facilityIdsCohort.substring(0, facilityIdsCohort.length()-3);
     facilityIdsCohort+=") && ";     
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && "; 
     
     }
       
        else{
       facilityIds="";    
       facilityIdsCohort="";    
       facilityIds1="";    
        }   
        
     }

  HSSFSheet shetRETENTION=wb.createSheet("RETENTION");
  
  HSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Cambria");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
     HSSFFont font2=wb.createFont();
    font2.setFontName("Cambria");
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
    
    HSSFCellStyle styleError = wb.createCellStyle();
    styleError.setFillForegroundColor(HSSFColor.RED.index);
    styleError.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
    styleError.setBorderTop(HSSFCellStyle.BORDER_THIN);
    styleError.setBorderBottom(HSSFCellStyle.BORDER_THIN);
    styleError.setBorderLeft(HSSFCellStyle.BORDER_THIN);
    styleError.setBorderRight(HSSFCellStyle.BORDER_THIN);
    styleError.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    styleError.setWrapText(true);
    
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
 
 
 c1=rw0shetRETENTION.getCell(33);
 c1.setCellValue("Total Number of Adults and children who initiated ART in the 12, 24, and 36 months prior to the beginning of the reporting period, including those who have died, those who have stopped ART and those lost to follow-up"); 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,5,32));
  shetRETENTION.addMergedRegion(new CellRangeAddress(0,0,33,60));
 
 HSSFRow  rw1shetRETENTION=shetRETENTION.createRow(1);
  rw1shetRETENTION.setHeightInPoints(30);
 for(int j=0;j<headerRETENTION.length;j++){
        c1=rw1shetRETENTION.createCell(j);
         c1.setCellStyle(styleHeader);
    }
 c1=rw1shetRETENTION.getCell(10);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 
 
 c1=rw1shetRETENTION.getCell(39);
 c1.setCellValue("DISAGGREGATED BY AGE AND SEX"); 

   shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,10,32));
  shetRETENTION.addMergedRegion(new CellRangeAddress(1,1,39,60)); 
  
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
 
 c1=rw2shetRETENTION.getCell(23);
 c1.setCellValue("Male"); 
 
   c1=rw2shetRETENTION.getCell(36);
 c1.setCellValue("Disaggregated by "); 
 
 c1=rw2shetRETENTION.getCell(39);
 c1.setCellValue("Under 10"); 
 
 c1=rw2shetRETENTION.getCell(42);
 c1.setCellValue("Female"); 
 
 c1=rw2shetRETENTION.getCell(51);
 c1.setCellValue("Male"); 
 
 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,8,10));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,11,13)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,14,22));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,23,31)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,36,38));
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,39,41)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,42,50)); 
  shetRETENTION.addMergedRegion(new CellRangeAddress(2,2,51,59)); 
  
  
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
  
  
// GET STARTING ART RETENTION DATA

//Retention both numerator and denominator From oct 2017
if(1==1){
    if(facilityId==null){
        facilityId="";
    }
    System.out.println("i wonder facility ids:"+facilityId);
int new_retentionPOS=0;
 new_retentionPOS=retentionPOS=3;
// numerator
String getNumerator="  " +
"SELECT SubPartnerNom,DistrictNom,County,CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume," +
"SUM(retention_12months) AS retention_12months, SUM(retention_24months) AS retention_24months, SUM(retention_36months) AS retention_36months, " +
"SUM(pregnant) as pregnant, SUM(breastfeeding) as breastfeeding,SUM(f_1) as f_1, SUM(m_1) as m_1, SUM(f_9) as f_9, SUM(m_9) as m_9, " +
"SUM(f_14) as f_14, SUM(m_14) as m_14, SUM(f_19) as f_19, SUM(m_19) as m_19, SUM(f_24) as f_24, SUM(m_24) as m_24, SUM(f_29) as f_29, SUM(m_29) as m_29, "
+"  SUM(f_34) as f_34, SUM(m_34) as m_34, SUM(f_39) as f_39, SUM(m_39) as m_39, SUM(f_49) as f_49, SUM(m_49) as m_49, SUM(f_50) as f_50, SUM(m_50) as m_50 " +
        
"FROM (SELECT 1 as unid, " + facilitiestable + ".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County," +
"" + facilitiestable + ".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume," +
"ROUND(SUM(HV0349)) AS retention_12months, " +
"0 AS retention_24months, " +
"0 AS retention_36months, " +
"0 as pregnant, " +
"0 as breastfeeding, " +
"ROUND(SUM(HV0349)*f_1) as 'f_1'," +
"ROUND(SUM(HV0349)*m_1) as 'm_1'," +
"ROUND(SUM(HV0349)*(f_4+f_9)) as 'f_9'," +
"ROUND(SUM(HV0349)*(m_4+m_9)) as 'm_9'," +
"ROUND(SUM(HV0349)*f_14) as 'f_14'," +
"ROUND(SUM(HV0349)*m_14) as 'm_14'," +
"ROUND(SUM(HV0349)*f_19) as 'f_19'," +
"ROUND(SUM(HV0349)*m_19) as 'm_19', " +
"ROUND(SUM(HV0349)*f_24) as 'f_24'," +
"ROUND(SUM(HV0349)*m_24) as 'm_24'," +
"ROUND(SUM(HV0349)*f_29) as 'f_29', " +
"ROUND(SUM(HV0349)*m_29) as 'm_29', " +
"ROUND(SUM(HV0349)*f_34) as 'f_34', " +
"ROUND(SUM(HV0349)*m_34) as 'm_34', " +
"ROUND(SUM(HV0349)*f_39) as 'f_39', " +
"ROUND(SUM(HV0349)*m_39) as 'm_39', " +
"ROUND(SUM(HV0349)*f_49) as 'f_49', " +
"ROUND(SUM(HV0349)*m_49) as 'm_49', " +
"ROUND(SUM(HV0349)*f_50) as 'f_50', " +
"ROUND(SUM(HV0349)*m_50) as 'm_50' " +
" FROM internal_system.moh731 " +
" JOIN internal_system." + facilitiestable + " ON internal_system.moh731.SubPartnerID=internal_system." + facilitiestable + ".SubPartnerID " +
"JOIN internal_system.district ON internal_system." + facilitiestable + ".DistrictID=internal_system.district.DistrictID " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID "+
"JOIN ratios ON county.CountyID=ratios.county_id " +
"WHERE "+facilityIds+" internal_system.moh731"+duration+" && "+facilitiestable+".ART=1  AND ratios.indicator='TX_RETENTION_NUM'" +
"GROUP BY internal_system." + facilitiestable + ".SubPartnerID " +
" " +
"UNION " +
"" +
"SELECT 2 as unid, " + facilitiestable + ".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County," +
"" + facilitiestable + ".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume," +
"0 AS retention_12months, " +
"0 AS retention_24months, " +
"0 AS retention_36months, " +
"ROUND(f_34*SUM(np_12m)) as pregnant, " +
"SUM(np_12m)-ROUND(f_34*SUM(np_12m)) as breastfeeding, " +
"0 as f_1, " +
"0 as m_1, " +
"0 as f_9, " +
"0 as m_9, " +
"0 as f_14, " +
"0 as m_14, " +
"0 as f_19, " +
"0 as m_19, " +
"0 as f_24, " +
"0 as m_24, " +
        
"0 as f_29, " +
"0 as m_29, " +
"0 as f_34, " +
"0 as m_34, " +
"0 as f_39, " +
"0 as m_39, " +
        
"0 as f_49, " +
"0 as m_49, " +
"0 as f_50, " +
"0 as m_50" +
" FROM pmtct_art_cohort.pmtct_cohort " +
" JOIN internal_system." + facilitiestable + " ON pmtct_art_cohort.pmtct_cohort.mflcode=internal_system." + facilitiestable + ".CentreSanteId " +
"JOIN internal_system.district ON internal_system." + facilitiestable + ".DistrictID=internal_system.district.DistrictID " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID " +
"JOIN ratios ON county.CountyID=ratios.county_id " +
"WHERE "+facilityIdsCohort+" pmtct_art_cohort.pmtct_cohort"+duration+" && "+facilitiestable+".ART=1  AND ratios.indicator='TX_RETENTION_NUM_PREG' " +
"AND (pmtct_art_cohort.pmtct_cohort.indicator=21 OR pmtct_art_cohort.pmtct_cohort.indicator=9) " +
"GROUP BY internal_system." + facilitiestable + ".SubPartnerID ) AS all_data group by CentreSanteId ORDER BY CentreSanteId" ;

    System.out.println("num : "+getNumerator);
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

        int f_1,m_1,f_9,m_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
            f_1 = conn.rs.getInt("f_1");
            m_1 = conn.rs.getInt("m_1");
            f_9 = conn.rs.getInt("f_9");
            m_9 = conn.rs.getInt("m_9");
            f_14 = conn.rs.getInt("f_14");
            f_19 = conn.rs.getInt("f_19");
            f_24 = conn.rs.getInt("f_24");
            f_29 = conn.rs.getInt("f_29");
            f_34 = conn.rs.getInt("f_34");
            f_39 = conn.rs.getInt("f_39");
            f_49 = conn.rs.getInt("f_49");
            f_50 = conn.rs.getInt("f_50");
            m_14 = conn.rs.getInt("m_14");
            m_19 = conn.rs.getInt("m_19");
            m_24 = conn.rs.getInt("m_24");
            m_29 = conn.rs.getInt("m_29");
            m_34 = conn.rs.getInt("m_34");
            m_39 = conn.rs.getInt("m_39");
            m_49 = conn.rs.getInt("m_49");
            m_50 = conn.rs.getInt("m_50");

            int newsummed = f_1+f_9+f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50+m_1+m_9+m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50;

            if(newsummed>numerator){
                int rounds=newsummed-numerator;
                int counter=0;
                while(rounds>0){
                  counter++;
                  if(counter==1){
                  m_34--;    
                  }
                  if(counter==2){
                  f_24--;    
                  }
                  if(counter==3){
                  m_24--;    
                  }
                  if(counter==4){
                  f_34--;    
                  }
                  else if(counter==5){
                  m_39--;
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
                  if(counter==1){
                  m_34++;    
                  }
                  if(counter==2){
                  f_24++;    
                  }
                  if(counter==3){
                  m_24++;    
                  }
                  if(counter==4){
                  f_34++;    
                  }
                  else if(counter==5){
                  m_39++;
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
int sumedtotalsafter=f_1+f_9+f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50+m_1+m_9+m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50;
    // create row and add data
    String dataRETENTION []=(countyName+","+districtName+","+facilityName+","+mflcode+",DSD,"+numerator+","+num_24+","+num_36+","
           + ""+pregnant+","+breastfeeding+","+(pregnant+breastfeeding)+","
           + ""+(f_1+m_1)+","+(f_9+m_9)+","+total_1_9+","+f_14+","+f_19+","+f_24+","+f_29+","+f_34+","+f_39+","+f_49+","+f_50+","+(f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50)+","
           + ""+m_14+","+m_19+","+m_24+","+m_29+","+m_34+","+m_39+","+m_49+","+m_50+","+(m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50)+","+sumedtotalsafter).split(","); 

  HSSFRow rw4shetRETENTION=shetRETENTION.createRow(retentionPOS); 
       rw4shetRETENTION.setHeightInPoints(25);
       for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++)
       {
           if(pregnant>numerator){
              
         String value=dataRETENTION[positionRETENTION];
        HSSFCell    c11=rw4shetRETENTION.createCell(positionRETENTION);
        if(positionRETENTION>4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
        c11.setCellStyle(styleError); 
           }
           else{
            String value=dataRETENTION[positionRETENTION];
           HSSFCell    c11=rw4shetRETENTION.createCell(positionRETENTION);
           if(positionRETENTION>4){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
           c11.setCellStyle(stborder);    
           }
//          if(positionRETENTION==5 || positionRETENTION==8 || positionRETENTION==17 || positionRETENTION==18 || positionRETENTION==21 || positionRETENTION==30){ c11.setCellStyle(styleHeader);}
//          System.out.println("position "+positionPMTCT+" end v : "+dataPMTCT.length); 
       }
        }  
        
        
        
        
        
        
        int initial_colpos=33;
 //denominator 
 String getDenominator="  SELECT SubPartnerNom,DistrictNom,County,CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"SUM(retention_12months) AS retention_12months, SUM(retention_24months) AS retention_24months, SUM(retention_36months) AS retention_36months,  " +
"SUM(pregnant) as pregnant, SUM(breastfeeding) as breastfeeding,SUM(f_1) as f_1, SUM(m_1) as m_1, SUM(f_9) as f_9, SUM(m_9) as m_9,  " +
"SUM(f_14) as f_14, SUM(m_14) as m_14, SUM(f_19) as f_19, SUM(m_19) as m_19, SUM(f_24) as f_24, SUM(m_24) as m_24, SUM(f_29) as f_29, SUM(m_29) as m_29,"
+ " SUM(f_34) as f_34, SUM(m_34) as m_34, SUM(f_39) as f_39, SUM(m_39) as m_39, SUM(f_49) as f_49, SUM(m_49) as m_49, SUM(f_50) as f_50, SUM(m_50) as m_50  " +
         
"FROM (SELECT 1 AS unid, " + facilitiestable + ".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
"" + facilitiestable + ".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"ROUND(SUM(HV0345)) AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,     " +
"0 as pregnant,  " +
"0 as breastfeeding,  " +
"ROUND(SUM(HV0345)*f_1) as 'f_1'," +
"ROUND(SUM(HV0345)*m_1) as 'm_1'," +
"ROUND(SUM(HV0345)*(f_4+f_9)) as 'f_9'," +
"ROUND(SUM(HV0345)*(m_4+m_9)) as 'm_9'," +
"ROUND(SUM(HV0345)*f_14) as 'f_14'," +
"ROUND(SUM(HV0345)*m_14) as 'm_14'," +
"ROUND(SUM(HV0345)*f_19) as 'f_19'," +
"ROUND(SUM(HV0345)*m_19) as 'm_19', " +
"ROUND(SUM(HV0345)*f_24) as 'f_24'," +
"ROUND(SUM(HV0345)*m_24) as 'm_24'," +
"ROUND(SUM(HV0345)*f_29) as 'f_29', " +
"ROUND(SUM(HV0345)*m_29) as 'm_29', " +
"ROUND(SUM(HV0345)*f_34) as 'f_34', " +
"ROUND(SUM(HV0345)*m_34) as 'm_34', " +
"ROUND(SUM(HV0345)*f_39) as 'f_39', " +
"ROUND(SUM(HV0345)*m_39) as 'm_39', " +
"ROUND(SUM(HV0345)*f_49) as 'f_49', " +
"ROUND(SUM(HV0345)*m_49) as 'm_49', " +
"ROUND(SUM(HV0345)*f_50) as 'f_50', " +
"ROUND(SUM(HV0345)*m_50) as 'm_50' " +
"FROM internal_system.moh731  " +
"JOIN internal_system." + facilitiestable + " ON internal_system.moh731.SubPartnerID=internal_system." + facilitiestable + ".SubPartnerID  " +
"JOIN internal_system.district ON internal_system." + facilitiestable + ".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"JOIN ratios ON county.CountyID=ratios.county_id " +
" WHERE "+facilityIds+" internal_system.moh731"+duration+" && "+facilitiestable+".ART=1   AND ratios.indicator='TX_RETENTION_DEN'" +
" GROUP BY internal_system." + facilitiestable + ".SubPartnerID  " +
"  " +
" UNION  " +
"   " +
"SELECT 2 AS unid, " + facilitiestable + ".SubPartnerNom AS SubPartnerNom,district.DistrictNom AS DistrictNom,county.County AS County, " +
"" + facilitiestable + ".CentreSanteId AS CentreSanteId,ART_Support,PMTCT_Support,ART_highvolume, HTC_highvolume,PMTCT_highvolume, " +
"0 AS retention_12months,  " +
"0 AS retention_24months,  " +
"0 AS retention_36months,     " +
"ROUND(f_34*SUM(np_12m)) as pregnant, " +
"SUM(np_12m)-ROUND(f_34*SUM(np_12m)) as breastfeeding, " +
"0 as f_1,  " +
"0 as m_1,  " +
"0 as f_9,  " +
"0 as m_9,  " +
"0 as f_14,  " +
"0 as m_14, " +
"0 as f_19,    " +
"0 as m_19, " +
"0 as f_24,  " +
"0 as m_24,  " +
"0 as f_29,  " +
"0 as m_29,  " +
"0 as f_34,  " +
"0 as m_34,  " +
"0 as f_39,  " +
"0 as m_39,  " +
"0 as f_49,  " +
"0 as m_49,  " +
"0 as f_50,  " +
"0 as m_50  " +
" FROM pmtct_art_cohort.pmtct_cohort  " +
" JOIN internal_system." + facilitiestable + " ON pmtct_art_cohort.pmtct_cohort.mflcode=internal_system." + facilitiestable + ".CentreSanteId  " +
"JOIN internal_system.district ON internal_system." + facilitiestable + ".DistrictID=internal_system.district.DistrictID  " +
"JOIN internal_system.county ON internal_system.district.CountyID=internal_system.county.CountyID  " +
"JOIN ratios ON county.CountyID=ratios.county_id " +
" WHERE "+facilityIdsCohort+" pmtct_art_cohort.pmtct_cohort"+duration+" && "+facilitiestable+".ART=1 AND ratios.indicator='TX_RETENTION_DEN_PREG' " +
" AND (pmtct_art_cohort.pmtct_cohort.indicator=4 OR pmtct_art_cohort.pmtct_cohort.indicator=16) " +
" GROUP BY internal_system." + facilitiestable + ".SubPartnerID ) AS all_data group by CentreSanteId  ORDER BY CentreSanteId";

    System.out.println("denominator : "+getDenominator);
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

        int f_1,m_1,f_9,m_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50;
            f_1 = conn.rs.getInt("f_1");
            m_1 = conn.rs.getInt("m_1");
            f_9 = conn.rs.getInt("f_9");
            m_9 = conn.rs.getInt("m_9");
            f_14 = conn.rs.getInt("f_14");
            f_19 = conn.rs.getInt("f_19");
            f_24 = conn.rs.getInt("f_24");
            f_29 = conn.rs.getInt("f_29");
            f_34 = conn.rs.getInt("f_34");
            f_39 = conn.rs.getInt("f_39");
            f_49 = conn.rs.getInt("f_49");
            f_50 = conn.rs.getInt("f_50");
            m_14 = conn.rs.getInt("m_14");
            m_19 = conn.rs.getInt("m_19");
            m_24 = conn.rs.getInt("m_24");
            m_29 = conn.rs.getInt("m_29");
            m_34 = conn.rs.getInt("m_34");
            m_39 = conn.rs.getInt("m_39");
            m_49 = conn.rs.getInt("m_49");
            m_50 = conn.rs.getInt("m_50");

            int newsummed = f_1+f_9+f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50+m_1+m_9+m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50;

            if(newsummed>denominator){
                int rounds=newsummed-denominator;
                int counter=0;
                while(rounds>0){
                  counter++;
                   if(counter==1){
                  m_34--;    
                  }
                  if(counter==2){
                  f_24--;    
                  }
                  if(counter==3){
                  m_24--;    
                  }
                  if(counter==4){
                  f_34--;    
                  }
                  else if(counter==5){
                  m_39--;
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
                  if(counter==1){
                  m_34++;    
                  }
                  if(counter==2){
                  f_24++;    
                  }
                  if(counter==3){
                  m_24++;    
                  }
                  if(counter==4){
                  f_34++;    
                  }
                  else if(counter==5){
                  m_39++;
                  counter=0;
                  }

                    rounds--;
                }
            }

          else{
    //  They are equal hence no normalization
          }
      int  total_1_9 = m_1+m_9+f_1+f_9;    
    //     completed normalization
    int sumedtotalsafter=f_1+f_9+f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50+m_1+m_9+m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50;
    // create row and add data
    String dataRETENTION []=(denominator+","+den_24+","+den_36+","
           + ""+pregnant+","+breastfeeding+","+(pregnant+breastfeeding)+","
           + ""+(f_1+m_1)+","+(f_9+m_9)+","+total_1_9+","+f_14+","+f_19+","+f_24+","+f_29+","+f_34+","+f_39+","+f_49+","+f_50+","+(f_14+f_19+f_24+f_29+f_34+f_39+f_49+f_50)+","
           + ""+m_14+","+m_19+","+m_24+","+m_29+","+m_34+","+m_39+","+m_49+","+m_50+","+(m_14+m_19+m_24+m_29+m_34+m_39+m_49+m_50)+","+sumedtotalsafter+"," 
           + "status"+","+arthv+","+htchv+","+pmtcthv).split(","); 

  HSSFRow rw4shetRETENTION=shetRETENTION.getRow(new_retentionPOS); 
       rw4shetRETENTION.setHeightInPoints(25);
      
       if(pregnant>denominator){
      for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++)
       {
       String value=dataRETENTION[positionRETENTION];
       HSSFCell c11=rw4shetRETENTION.createCell(positionRETENTION+initial_colpos);
        if(positionRETENTION<(dataRETENTION.length-4) || positionRETENTION>(dataRETENTION.length-4)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}

         c11.setCellStyle(styleError);
       if(positionRETENTION==dataRETENTION.length-4){
       if(errorRETENTION>0){c11.setCellValue("FAILED");c11.setCellStyle(styleError);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(styleError);}   
       }
       }     
       }
       else{
        for(int positionRETENTION=0;positionRETENTION<dataRETENTION.length;positionRETENTION++)
       {
       String value=dataRETENTION[positionRETENTION];
       HSSFCell c11=rw4shetRETENTION.createCell(positionRETENTION+initial_colpos);
        if(positionRETENTION<(dataRETENTION.length-4) || positionRETENTION>(dataRETENTION.length-4)){ c11.setCellValue(Double.parseDouble(value));}else{ c11.setCellValue(value);}
         c11.setCellStyle(stborder);
       if(positionRETENTION==dataRETENTION.length-4){
       if(errorRETENTION>0){c11.setCellValue("FAILED");c11.setCellStyle(redstyle);}    
       else{c11.setCellValue("PASSED");c11.setCellStyle(stborder);}   
       }
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
          
            viralloadduration = " Date_Tested BETWEEN '"+start_date+"' AND '"+end_date+"' ";
             
            //======================================================================
//==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            String subcountywhere = "";
            
            String subcounty = "";
            
            if (!request.getParameter("county").equals("")) {
                
                county = request.getParameter("county");
                
            }
            
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
            
            String joinedwhere = " where 1=1 " + yearwhere + " && " + viralloadduration + " " + countywhere + " " +subcountywhere+" "+facilitywhere+" "
            + " and ("+facilitiestable+".ART=1 OR "+facilitiestable+".PMTCT=1) and "+facilitiestable+".active=1  and Gender !='' and (AgeYrs!='' and AgeYrs>=0) AND Valid_Result='Y' ";

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
            
            cx1 = rw.createCell(81);
            cx1.setCellValue(header1[1]);
            //format cell here
           
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,5,80));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,81,156));
           
            
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
            
            cx1 = rw.createCell(82);
            cx1.setCellValue(header2[3]);
            
            cx1 = rw.createCell(85);
            cx1.setCellValue(header2[4]);
            
            cx1 = rw.createCell(91);
            cx1.setCellValue(header2[5]);
//            start of formatting and merging
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,6,8));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,9,14));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,80));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,82,84));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,85,90));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,91,156));


//          end of formatting and merging



 
            rowpos++;
            rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            
            cx1 = rw.createCell(15);
            cx1.setCellValue(header3[0]);
            cx1 = rw.createCell(37);
            cx1.setCellValue(header3[1]);
            cx1 = rw.createCell(59);
            cx1.setCellValue(header3[2]);
            cx1 = rw.createCell(91);
            cx1.setCellValue(header3[3]);
            cx1 = rw.createCell(113);
            cx1.setCellValue(header3[4]);
            cx1 = rw.createCell(135);
            cx1.setCellValue(header3[5]);
            
            
            
            //start of formatting
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,36));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,37,58));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,59,80));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,91,112));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,113,134));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,135,156));
              
            
            
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
            
            cx1 = rw.createCell(27);
            cx1.setCellValue(header4[5]);
            
            cx1 = rw.createCell(37);
            cx1.setCellValue(header4[6]);
            
            cx1 = rw.createCell(40);
            cx1.setCellValue(header4[7]);
            
            cx1 = rw.createCell(49);
            cx1.setCellValue(header4[8]);
            
            cx1 = rw.createCell(59);
            cx1.setCellValue(header4[9]);
            
            cx1 = rw.createCell(62);
            cx1.setCellValue(header4[10]);
            
            cx1 = rw.createCell(71);
            cx1.setCellValue(header4[11]);
            
            cx1 = rw.createCell(82);
            cx1.setCellValue(header4[12]);
            
            cx1 = rw.createCell(85);
            cx1.setCellValue(header4[13]);
            
            cx1 = rw.createCell(88);
            cx1.setCellValue(header4[14]);
            
            cx1 = rw.createCell(91);
            cx1.setCellValue(header4[15]);
            
            cx1 = rw.createCell(94);
            cx1.setCellValue(header4[16]);
            
            cx1 = rw.createCell(103);
            cx1.setCellValue(header4[17]);
            
            cx1 = rw.createCell(113);
            cx1.setCellValue(header4[18]);
            
            cx1 = rw.createCell(116);
            cx1.setCellValue(header4[19]);
            
            cx1 = rw.createCell(125);
            cx1.setCellValue(header4[20]);
            
            cx1 = rw.createCell(135);
            cx1.setCellValue(header4[21]);
            
            cx1 = rw.createCell(138);
            cx1.setCellValue(header4[22]);
            
            cx1 = rw.createCell(147);
            cx1.setCellValue(header4[23]);


              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,6,8));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,9,11));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,12,14));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,15,17));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,18,26));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,27,35));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,37,39));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,40,48));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,49,57));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,59,61));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,62,70));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,71,79));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,82,84));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,85,87));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,88,90));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,91,93));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,94,102));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,103,111));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,113,115));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,116,124));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,125,133));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,135,137));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,138,146));
              shet.addMergedRegion(new CellRangeAddress(rowpos,rowpos,147,155));


            
           String heading[] = ("County,Sub County,Health Facility,MFL Code,Type of Support,"
            +"Numerator,Routine,Targeted,Not Documented,Routine,Targeted,Not Documented,"
            +"Routine,Targeted,Not Documented,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal,"
            +"Denominator,Routine,Targeted,Not Documented,Routine,Targeted,Not Documented,"
            +"Routine,Targeted,Not Documented,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal,"
            +"<1,1-9,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,10-14,15-19,20-24,25-29,30-34,35-39,40-49,50+,subtotal,subtotal").split(",");
           
           for(int j=0;j<4;j++){
               
           rw = shet.getRow(j);
           for(int i=0;i<157;i++){
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
           
           String totals = ",17,26,35,36,39,48,57,58,61,70,79,80,93,102,111,112,115,124,133,134,137,146,155,156,";
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
    +"n_r_1,n_r_9,n_r_0_9,n_r_f_14,n_r_f_19,n_r_f_24,n_r_f_29,n_r_f_34,n_r_f_39,n_r_f_49,n_r_f_50,n_r_10_50_f,n_r_m_14,n_r_m_19,n_r_m_24,n_r_m_29,n_r_m_34,n_r_m_39,n_r_m_49,n_r_m_50,n_r_10_50_m,n_r_0_50,"
    +"n_t_1,n_t_9,n_t_0_9,n_t_f_14,n_t_f_19,n_t_f_24,n_t_f_29,n_t_f_34,n_t_f_39,n_t_f_49,n_t_f_50,n_t_10_50_f,n_t_m_14,n_t_m_19,n_t_m_24,n_t_m_29,n_t_m_34,n_t_m_39,n_t_m_49,n_t_m_50,n_t_10_50_m,n_t_0_50,"
    +"n_nd_1,n_nd_9,n_nd_0_9,n_nd_f_14,n_nd_f_19,n_nd_f_24,n_nd_f_29,n_nd_f_34,n_nd_f_39,n_nd_f_49,n_nd_f_50,n_nd_10_50_f,n_nd_m_14,n_nd_m_19,n_nd_m_24,n_nd_m_29,n_nd_m_34,n_nd_m_39,n_nd_m_49,n_nd_m_50,n_nd_10_50_m,n_nd_0_50,"
    +"Denominator,d_Routine,d_Targeted,d_Not_Documented,d_Preg_Routine,d_Preg_Targeted,d_Preg_Not_Documented,"
    +"d_Breastfeeding_Routine,d_Breastfeeding_Targeted,d_Breastfeeding_Not_Documented,"
    +"d_r_1,d_r_9,d_r_0_9,d_r_f_14,d_r_f_19,d_r_f_24,d_r_f_29,d_r_f_34,d_r_f_39,d_r_f_49,d_r_f_50,d_r_10_50_f,d_r_m_14,d_r_m_19,d_r_m_24,d_r_m_29,d_r_m_34,d_r_m_39,d_r_m_49,d_r_m_50,d_r_10_50_m,d_r_0_50,"
    +"d_t_1,d_t_9,d_t_0_9,d_t_f_14,d_t_f_19,d_t_f_24,d_t_f_29,d_t_f_34,d_t_f_39,d_t_f_49,d_t_f_50,d_t_10_50_f,d_t_m_14,d_t_m_19,d_t_m_24,d_t_m_29,d_t_m_34,d_t_m_39,d_t_m_49,d_t_m_50,d_t_10_50_m,d_t_0_50,"
    +"d_nd_1,d_nd_9,d_nd_0_9,d_nd_f_14,d_nd_f_19,d_nd_f_24,d_nd_f_29,d_nd_f_34,d_nd_f_39,d_nd_f_49,d_nd_f_50,d_nd_10_50_f,d_nd_m_14,d_nd_m_19,d_nd_m_24,d_nd_m_29,d_nd_m_34,d_nd_m_39,d_nd_m_49,d_nd_m_50,d_nd_10_50_m,d_nd_0_50").split(",");       

//    With no duplicates
//        String getVLData = "/*DSD TX_PVLS (Denominator) */ " +
//"SELECT County, DistrictNom,constituency,ward,SubPartnerNom ,mfl_code, " +
//"GSN,ART_Support,PMTCT_Support,HTC_Support1,Type,ART_highvolume,PMTCT_highvolume, " +
//"HTC_highvolume,latitude,longitude,support_type, " +
//"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Numerator' END) AS Numerator, " +
//"COUNT( CASE WHEN Suppressed='Y' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS n_Routine, " +
//"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND  (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') THEN  'Targeted' END) AS n_Targeted, " +
//"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other') THEN  'Not_Documented' END) AS n_Not_Documented, " +
//" " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Preg_Routine' END) AS n_Preg_Routine, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Preg_Targeted' END) AS n_Preg_Targeted, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Preg_Not_Documented' END) AS n_Preg_Not_Documented, " +
//" " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Breastfeeding_Routine' END) AS n_Breastfeeding_Routine, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Breastfeeding_Targeted' END) AS n_Breastfeeding_Targeted, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Breastfeeding_Not_Documented' END) AS n_Breastfeeding_Not_Documented, " +
//" " +
//"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_f_1' END) AS n_r_f_1, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_f_9' END) AS n_r_f_9, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_m_1' END) AS n_r_m_1, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_m_9' END) AS n_r_m_9, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_1' END) AS n_r_1, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_9' END) AS n_r_9, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND  9.99999))) THEN  'n_r_0_9' END) AS n_r_0_9, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_f_14' END) AS n_r_f_14, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_f_19' END) AS n_r_f_19, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_f_24' END) AS n_r_f_24, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_f_29' END) AS n_r_f_29, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_f_34' END) AS n_r_f_34, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_f_39' END) AS n_r_f_39, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_f_49' END) AS n_r_f_49, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_f_50' END) AS n_r_f_50, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_f' END) AS n_r_10_50_f, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_m_14' END) AS n_r_m_14, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_m_19' END) AS n_r_m_19, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_m_24' END) AS n_r_m_24, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_m_29' END) AS n_r_m_29, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_m_34' END) AS n_r_m_34, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_m_39' END) AS n_r_m_39, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_m_49' END) AS n_r_m_49, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_m_50' END) AS n_r_m_50, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_m' END) AS n_r_10_50_m, " +
//"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100))) THEN  'n_r_0_50' END) AS n_r_0_50, " +
//" " +
//"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_f_1' END) AS n_t_f_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_r_t_9' END) AS n_t_f_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_m_1' END) AS n_t_m_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_t_m_9' END) AS n_t_m_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_t_1' END) AS n_t_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ( (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999)) THEN  'n_t_9' END) AS n_t_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_t_0_9' END) AS n_t_0_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_f_14' END) AS n_t_f_14, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_f_19' END) AS n_t_f_19, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_f_24' END) AS n_t_f_24, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_f_29' END) AS n_t_f_29, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_f_34' END) AS n_t_f_34, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_f_39' END) AS n_t_f_39, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_f_49' END) AS n_t_f_49, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_f_50' END) AS n_t_f_50, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_f' END) AS n_t_10_50_f, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_m_14' END) AS n_t_m_14, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_m_19' END) AS n_t_m_19, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_m_24' END) AS n_t_m_24, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_m_29' END) AS n_t_m_29, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_m_34' END) AS n_t_m_34, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_m_39' END) AS n_t_m_39, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_m_49' END) AS n_t_m_49, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_m_50' END) AS n_t_m_50, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_m' END) AS n_t_10_50_m, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_t_0_50' END) AS n_t_0_50, " +
//" " +
//" " +
//"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_f_1' END) AS n_nd_f_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_t_9' END) AS n_nd_f_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_m_1' END) AS n_nd_m_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_m_9' END) AS n_nd_m_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_nd_1' END) AS n_nd_1, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs  BETWEEN 1 AND 9.9999)) THEN  'n_nd_9' END) AS n_nd_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_nd_0_9' END) AS n_nd_0_9, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_f_14' END) AS n_nd_f_14, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_f_19' END) AS n_nd_f_19, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_f_24' END) AS n_nd_f_24, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_f_29' END) AS n_nd_f_29, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_f_34' END) AS n_nd_f_34, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_f_39' END) AS n_nd_f_39, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_f_49' END) AS n_nd_f_49, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_f_50' END) AS n_nd_f_50, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_f' END) AS n_nd_10_50_f, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_m_14' END) AS n_nd_m_14, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_m_19' END) AS n_nd_m_19, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_m_24' END) AS n_nd_m_24, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_m_29' END) AS n_nd_m_29, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_m_34' END) AS n_nd_m_34, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_m_39' END) AS n_nd_m_39, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_m_49' END) AS n_nd_m_49, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_m_50' END) AS n_nd_m_50, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_m' END) AS n_nd_10_50_m, " +
//"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_nd_0_50' END) AS n_nd_0_50, " +
//" " +
//" " +
//"COUNT( CASE WHEN ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Denominator' END) AS Denominator, " +
//"COUNT( CASE WHEN Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS d_Routine, " +
//"COUNT( CASE WHEN (((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Targeted' END) AS d_Targeted, " +
//"COUNT( CASE WHEN ((Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Not_Documented' END) AS d_Not_Documented, " +
//" " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Preg_Routine' END) AS d_Preg_Routine, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Preg_Targeted' END) AS d_Preg_Targeted, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Preg_Not_Documented' END) AS d_Preg_Not_Documented, " +
//" " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL' )THEN  'd_Breastfeeding_Routine' END) AS d_Breastfeeding_Routine, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Breastfeeding_Targeted' END) AS d_Breastfeeding_Targeted, " +
//"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Breastfeeding_Not_Documented' END) AS d_Breastfeeding_Not_Documented, " +
//" " +
//"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_f_1' END) AS d_r_f_1, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_f_9' END) AS d_r_f_9, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_m_1' END) AS d_r_m_1, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_m_9' END) AS d_r_m_9, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_1' END) AS d_r_1, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_9' END) AS d_r_9, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_r_0_9' END) AS d_r_0_9, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_f_14' END) AS d_r_f_14, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_f_19' END) AS d_r_f_19, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_f_24' END) AS d_r_f_24, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_f_29' END) AS d_r_f_29, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_f_34' END) AS d_r_f_34, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_f_39' END) AS d_r_f_39, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_f_49' END) AS d_r_f_49, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_f_50' END) AS d_r_f_50, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_f' END) AS d_r_10_50_f, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_m_14' END) AS d_r_m_14, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_m_19' END) AS d_r_m_19, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_m_24' END) AS d_r_m_24, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_m_29' END) AS d_r_m_29, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_m_34' END) AS d_r_m_34, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_m_39' END) AS d_r_m_39, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_m_49' END) AS d_r_m_49, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_m_50' END) AS d_r_m_50, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_m' END) AS d_r_10_50_m, " +
//"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_r_0_50' END) AS d_r_0_50, " +
//" " +
//"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_f_1' END) AS d_t_f_1, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_t_9' END) AS d_t_f_9, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_m_1' END) AS d_t_m_1, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_m_9' END) AS d_t_m_9, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_1' END) AS d_t_1, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_9' END) AS d_t_9, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_t_0_9' END) AS d_t_0_9, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_f_14' END) AS d_t_f_14, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_f_19' END) AS d_t_f_19, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_f_24' END) AS d_t_f_24, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_f_29' END) AS d_t_f_29, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_f_34' END) AS d_t_f_34, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_f_39' END) AS d_t_f_39, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_f_49' END) AS d_t_f_49, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_f_50' END) AS d_t_f_50, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_f' END) AS d_t_10_50_f, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_m_14' END) AS d_t_m_14, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_m_19' END) AS d_t_m_19, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_m_24' END) AS d_t_m_24, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_m_29' END) AS d_t_m_29, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_m_34' END) AS d_t_m_34, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_m_39' END) AS d_t_m_39, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_m_49' END) AS d_t_m_49, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_m_50' END) AS d_t_m_50, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_m' END) AS d_t_10_50_m, " +
//"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_t_0_50' END) AS d_t_0_50, " +
//" " +
//" " +
//"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_f_1' END) AS d_nd_f_1, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_t_9' END) AS d_nd_f_9, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_m_1' END) AS d_nd_m_1, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_m_9' END) AS d_nd_m_9, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_1' END) AS d_nd_1, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_9' END) AS d_nd_9, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_nd_0_9' END) AS d_nd_0_9, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_f_14' END) AS d_nd_f_14, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_f_19' END) AS d_nd_f_19, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_f_24' END) AS d_nd_f_24, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_f_29' END) AS d_nd_f_29, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_f_34' END) AS d_nd_f_34, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_f_39' END) AS d_nd_f_39, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_f_49' END) AS d_nd_f_49, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_f_50' END) AS d_nd_f_50, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_f' END) AS d_nd_10_50_f, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_m_14' END) AS d_nd_m_14, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_m_19' END) AS d_nd_m_19, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_m_24' END) AS d_nd_m_24, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_m_29' END) AS d_nd_m_29, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_m_34' END) AS d_nd_m_34, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_m_39' END) AS d_nd_m_39, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_m_49' END) AS d_nd_m_49, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_m_50' END) AS d_nd_m_50, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_m' END) AS d_nd_10_50_m, " +
//"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_nd_0_50' END) AS d_nd_0_50 " +
//
//"FROM ("
//+ ""+
//"SELECT MAX(Date_Dispatched),county.County AS County, DistrictNom,constituency,ward,"+facilitiestable+".SubPartnerNom AS SubPartnerNom ,CentreSanteId AS mfl_code, " +
//"GSN,ART_Support,PMTCT_Support,HTC_Support1,Type,ART_highvolume,PMTCT_highvolume, " +
//"HTC_highvolume,latitude,longitude,'DSD' support_type, " +
//"autokey,`#`,System_ID,Batch_No,Patient_CCC_No,Testing_Lab,Partner,Sub_County,Facility_Name,MFL_Code AS mflcode,"
//+ "Sex,AgeYrs,Sample_Type,Date_Collected,Received_Status,`Reason for Repeat / Rejection`,Regimen,Other_Regimen,Justification,"
//+ "vl_validation.PMTCT AS PMTCT,ART_Initiation_Date,Date_Received,Date_Tested,Date_Dispatched,Valid_Result,Value,Suppressed,year,quarter " +      
//"FROM vl_validation join "+facilitiestable+" ON vl_validation.MFL_Code="+facilitiestable+".CentreSanteId "
//+"join district on "+facilitiestable+".DistrictID=district.DistrictID join county on county.CountyID=district.CountyID " +
//""+joinedwhere+" GROUP BY Patient_CCC_No) AS vl_validation GROUP BY mfl_code ";  
        
        
//     With duplicates   
String getVLData = "/*DSD TX_PVLS (Denominator) */ " +
"SELECT county.County AS County, DistrictNom,constituency,ward,"+facilitiestable+".SubPartnerNom AS SubPartnerNom ,CentreSanteId AS mfl_code, " +
"GSN,ART_Support,PMTCT_Support,HTC_Support1,Type,ART_highvolume,PMTCT_highvolume, " +
"HTC_highvolume,latitude,longitude, 'DSD' AS support_type, " +
"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Numerator' END) AS Numerator, " +
"COUNT( CASE WHEN Suppressed='Y' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS n_Routine, " +
"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND  (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') THEN  'Targeted' END) AS n_Targeted, " +
"COUNT( CASE WHEN Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other') THEN  'Not_Documented' END) AS n_Not_Documented, " +
" " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Preg_Routine' END) AS n_Preg_Routine, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Preg_Targeted' END) AS n_Preg_Targeted, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Preg_Not_Documented' END) AS n_Preg_Not_Documented, " +
" " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL') THEN  'n_Breastfeeding_Routine' END) AS n_Breastfeeding_Routine, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'n_Breastfeeding_Targeted' END) AS n_Breastfeeding_Targeted, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND Suppressed='Y' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'n_Breastfeeding_Not_Documented' END) AS n_Breastfeeding_Not_Documented, " +
" " +
"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_f_1' END) AS n_r_f_1, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_f_9' END) AS n_r_f_9, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_m_1' END) AS n_r_m_1, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_m_9' END) AS n_r_m_9, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.99999))) THEN  'n_r_1' END) AS n_r_1, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.99999))) THEN  'n_r_9' END) AS n_r_9, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND  9.99999))) THEN  'n_r_0_9' END) AS n_r_0_9, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_f_14' END) AS n_r_f_14, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_f_19' END) AS n_r_f_19, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_f_24' END) AS n_r_f_24, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_f_29' END) AS n_r_f_29, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_f_34' END) AS n_r_f_34, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_f_39' END) AS n_r_f_39, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_f_49' END) AS n_r_f_49, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_f_50' END) AS n_r_f_50, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='F' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_f' END) AS n_r_10_50_f, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 14.99999))) THEN  'n_r_m_14' END) AS n_r_m_14, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 15 AND 19.99999))) THEN  'n_r_m_19' END) AS n_r_m_19, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 20 AND 24.99999))) THEN  'n_r_m_24' END) AS n_r_m_24, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 25 AND 29.99999))) THEN  'n_r_m_29' END) AS n_r_m_29, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 30 AND 34.99999))) THEN  'n_r_m_34' END) AS n_r_m_34, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 35 AND 39.99999))) THEN  'n_r_m_39' END) AS n_r_m_39, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 40 AND 49.99999))) THEN  'n_r_m_49' END) AS n_r_m_49, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 50 AND 100))) THEN  'n_r_m_50' END) AS n_r_m_50, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND (Sex='M' AND (AgeYrs BETWEEN 10 AND 100))) THEN  'n_r_10_50_m' END) AS n_r_10_50_m, " +
"COUNT( CASE WHEN (Suppressed='Y' AND (Justification='Routine VL' ) AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100))) THEN  'n_r_0_50' END) AS n_r_0_50, " +
" " +
"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_f_1' END) AS n_t_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_r_t_9' END) AS n_t_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_t_m_1' END) AS n_t_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_t_m_9' END) AS n_t_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_t_1' END) AS n_t_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ( (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999)) THEN  'n_t_9' END) AS n_t_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_t_0_9' END) AS n_t_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_f_14' END) AS n_t_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_f_19' END) AS n_t_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_f_24' END) AS n_t_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_f_29' END) AS n_t_f_29, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_f_34' END) AS n_t_f_34, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_f_39' END) AS n_t_f_39, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_f_49' END) AS n_t_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_f_50' END) AS n_t_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_f' END) AS n_t_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_t_m_14' END) AS n_t_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_t_m_19' END) AS n_t_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_t_m_24' END) AS n_t_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_t_m_29' END) AS n_t_m_29, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_t_m_34' END) AS n_t_m_34, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_t_m_39' END) AS n_t_m_39, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_t_m_49' END) AS n_t_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_t_m_50' END) AS n_t_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_t_10_50_m' END) AS n_t_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_t_0_50' END) AS n_t_0_50, " +
" " +
" " +
"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_f_1' END) AS n_nd_f_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_t_9' END) AS n_nd_f_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'n_nd_m_1' END) AS n_nd_m_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'n_nd_m_9' END) AS n_nd_m_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999)) THEN  'n_nd_1' END) AS n_nd_1, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs  BETWEEN 1 AND 9.9999)) THEN  'n_nd_9' END) AS n_nd_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999)) THEN  'n_nd_0_9' END) AS n_nd_0_9, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_f_14' END) AS n_nd_f_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_f_19' END) AS n_nd_f_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_f_24' END) AS n_nd_f_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_f_29' END) AS n_nd_f_29, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_f_34' END) AS n_nd_f_34, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_f_39' END) AS n_nd_f_39, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_f_49' END) AS n_nd_f_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_f_50' END) AS n_nd_f_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_f' END) AS n_nd_10_50_f, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'n_nd_m_14' END) AS n_nd_m_14, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'n_nd_m_19' END) AS n_nd_m_19, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'n_nd_m_24' END) AS n_nd_m_24, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'n_nd_m_29' END) AS n_nd_m_29, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'n_nd_m_34' END) AS n_nd_m_34, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'n_nd_m_39' END) AS n_nd_m_39, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'n_nd_m_49' END) AS n_nd_m_49, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'n_nd_m_50' END) AS n_nd_m_50, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'n_nd_10_50_m' END) AS n_nd_10_50_m, " +
"COUNT( CASE WHEN Suppressed='Y' AND (Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'n_nd_0_50' END) AS n_nd_0_50, " +
" " +
" " +
"COUNT( CASE WHEN ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Denominator' END) AS Denominator, " +
"COUNT( CASE WHEN Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) THEN  'Routine' END) AS d_Routine, " +
"COUNT( CASE WHEN (((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Targeted' END) AS d_Targeted, " +
"COUNT( CASE WHEN ((Justification='No Data' OR Justification='' OR Justification='Other') AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Not_Documented' END) AS d_Not_Documented, " +
" " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND Justification='Routine VL' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) ) THEN  'd_Preg_Routine' END) AS d_Preg_Routine, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Preg_Targeted' END) AS d_Preg_Targeted, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Pregnant' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Preg_Not_Documented' END) AS d_Preg_Not_Documented, " +
" " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND Justification='Routine VL' )THEN  'd_Breastfeeding_Routine' END) AS d_Breastfeeding_Routine, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure')) THEN  'd_Breastfeeding_Targeted' END) AS d_Breastfeeding_Targeted, " +
"COUNT( CASE WHEN (vl_validation.PMTCT='Breast Feeding' AND ((Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100)) AND (Justification='No Data' OR Justification='' OR Justification='Other')) THEN  'd_Breastfeeding_Not_Documented' END) AS d_Breastfeeding_Not_Documented, " +
" " +
"/*ROUTINE NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_f_1' END) AS d_r_f_1, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_f_9' END) AS d_r_f_9, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_m_1' END) AS d_r_m_1, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_m_9' END) AS d_r_m_9, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_r_1' END) AS d_r_1, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_9' END) AS d_r_9, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_r_0_9' END) AS d_r_0_9, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_f_14' END) AS d_r_f_14, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_f_19' END) AS d_r_f_19, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_f_24' END) AS d_r_f_24, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_f_29' END) AS d_r_f_29, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_f_34' END) AS d_r_f_34, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_f_39' END) AS d_r_f_39, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_f_49' END) AS d_r_f_49, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_f_50' END) AS d_r_f_50, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_f' END) AS d_r_10_50_f, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_r_m_14' END) AS d_r_m_14, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_r_m_19' END) AS d_r_m_19, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_r_m_24' END) AS d_r_m_24, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_r_m_29' END) AS d_r_m_29, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_r_m_34' END) AS d_r_m_34, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_r_m_39' END) AS d_r_m_39, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_r_m_49' END) AS d_r_m_49, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_r_m_50' END) AS d_r_m_50, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_r_10_50_m' END) AS d_r_10_50_m, " +
"COUNT( CASE WHEN (Justification='Routine VL' ) AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_r_0_50' END) AS d_r_0_50, " +
" " +
"/*TARGETED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_f_1' END) AS d_t_f_1, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_r_t_9' END) AS d_t_f_9, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_m_1' END) AS d_t_m_1, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_m_9' END) AS d_t_m_9, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_t_1' END) AS d_t_1, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_t_9' END) AS d_t_9, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_t_0_9' END) AS d_t_0_9, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_f_14' END) AS d_t_f_14, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_f_19' END) AS d_t_f_19, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_f_24' END) AS d_t_f_24, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_f_29' END) AS d_t_f_29, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_f_34' END) AS d_t_f_34, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_f_39' END) AS d_t_f_39, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_f_49' END) AS d_t_f_49, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_f_50' END) AS d_t_f_50, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_f' END) AS d_t_10_50_f, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_t_m_14' END) AS d_t_m_14, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_t_m_19' END) AS d_t_m_19, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_t_m_24' END) AS d_t_m_24, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_t_m_29' END) AS d_t_m_29, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_t_m_34' END) AS d_t_m_34, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_t_m_39' END) AS d_t_m_39, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_t_m_49' END) AS d_t_m_49, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_t_m_50' END) AS d_t_m_50, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_t_10_50_m' END) AS d_t_10_50_m, " +
"COUNT( CASE WHEN (Justification='Baseline' OR Justification='Confirmation of Treatment Failure (Repeat VL)' OR Justification='Single Drug Substitution' OR Justification='Clinical Failure') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_t_0_50' END) AS d_t_0_50, " +
" " +
" " +
"/*NOT DOCUMENTED NUMERATOR DISAGGREGATION BY GENDER AND AGE */ " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_f_1' END) AS d_nd_f_1, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_t_9' END) AS d_nd_f_9, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_m_1' END) AS d_nd_m_1, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_m_9' END) AS d_nd_m_9, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 0.9999) THEN  'd_nd_1' END) AS d_nd_1, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 1 AND 9.9999) THEN  'd_nd_9' END) AS d_nd_9, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 9.9999) THEN  'd_nd_0_9' END) AS d_nd_0_9, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_f_14' END) AS d_nd_f_14, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_f_19' END) AS d_nd_f_19, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_f_24' END) AS d_nd_f_24, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_f_29' END) AS d_nd_f_29, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_f_34' END) AS d_nd_f_34, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_f_39' END) AS d_nd_f_39, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_f_49' END) AS d_nd_f_49, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_f_50' END) AS d_nd_f_50, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='F' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_f' END) AS d_nd_10_50_f, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 14.9999) THEN  'd_nd_m_14' END) AS d_nd_m_14, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 15 AND 19.9999) THEN  'd_nd_m_19' END) AS d_nd_m_19, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 20 AND 24.9999) THEN  'd_nd_m_24' END) AS d_nd_m_24, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 25 AND 29.9999) THEN  'd_nd_m_29' END) AS d_nd_m_29, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 30 AND 34.9999) THEN  'd_nd_m_34' END) AS d_nd_m_34, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 35 AND 39.9999) THEN  'd_nd_m_39' END) AS d_nd_m_39, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 40 AND 49.9999) THEN  'd_nd_m_49' END) AS d_nd_m_49, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 50 AND 100) THEN  'd_nd_m_50' END) AS d_nd_m_50, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' AND AgeYrs BETWEEN 10 AND 100) THEN  'd_nd_10_50_m' END) AS d_nd_10_50_m, " +
"COUNT( CASE WHEN (Justification='No Data' OR Justification='' OR Justification='Other') AND (Sex='M' OR Sex='F') AND (AgeYrs BETWEEN 0 AND 100) THEN  'd_nd_0_50' END) AS d_nd_0_50 " +
"FROM vl_validation join "+facilitiestable+" ON vl_validation.MFL_Code="+facilitiestable+".CentreSanteId "
+"join district on "+facilitiestable+".DistrictID=district.DistrictID join county on county.CountyID=district.CountyID " +
""+joinedwhere+" GROUP BY mfl_code ";      
        
        
        System.out.println("vl query : "+getVLData);
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
            String form = "sgbv";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            
            
                String mainheaders[]={"County","Sub-county","Health Facility","Mfl Code","Support type","Numerator","Sexual Violence","Physical and/or Emotional Violence","Number of people receiving PEP Service","Sexual violence","","","","","","","","","","","","","","Physical and / or Emotional Violence","","","","","","","","","","","","","","Verification","ART High Volume","HTC High Volume","PMTCT High Volume"};
            String sectionheaders1[]={"County","Sub-county","Health Facility","Mfl Code","Support type","Numerator","Sexual Violence","Physical and/or Emotional Violence","Number of people receiving PEP Service","Female","Female","Female","Female","Female","Female","Female","Male","Male","Male","Male","Male","Male","Male","Female","Female","Female","Female","Female","Female","Female","Male","Male","Male","Male","Male","Male","Male","Verification","ART High Volume","HTC High Volume","PMTCT High Volume"};
             String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Support type","Numerator","Sexual Violence","Physical and/or Emotional Violence","Number of people receiving PEP Service","Unknown","<10","10-14","15-19","20-24","25-49","50+","Unknown","<10","10-14","15-19","20-24","25-49","50+","Unknown","<10","10-14","15-19","20-24","25-49","50+","Unknown","<10","10-14","15-19","20-24","25-49","50+","Verification","ART High Volume","HTC High Volume","PMTCT High Volume"};
            //String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","TB_STAT","","","Sexually Transmitted Infections","","","Outpatient Department","","","Inpatient","","","Hiv Care and Treatment Clinic","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Voluntary Counselling & Testing (Standalone)","","","Mobile","","","Home-based","","","Other","",""};
              String merge_row_col[]={"0,0,0,"+(mainheaders.length-1),"1,3,0,0","1,3,1,1","1,3,2,2","1,3,3,3","1,3,4,4","1,3,5,5","1,3,6,6","1,3,7,7","1,3,8,8","1,1,9,22","2,2,9,15","2,2,16,22","1,1,23,36","2,2,23,29","2,2,30,36","1,3,37,37","1,3,38,38","1,3,39,39","1,3,40,40"};
            
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
    
    int blankrows=mainheaders.length;
    
   String getstaticfacilities="SELECT  county.County as county,district.DistrictNom as district," //
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
            //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode, ART_Support , sum(IFNULL(HV0503,0)+IFNULL(HV0504,0)) as numerator , sum(IFNULL(HV0503,0)+IFNULL(HV0504,0)) as sexual_violence,'0' as physical_violence  ,(sum(HV0504)) as femaletotal, (sum(HV0503)) as maletotal ,(sum(HV0503)+sum(HV0504)) as postrapecare, "+facilitiestable+".SubPartnerID as SubPartnerID ,ART_highvolume, HTC_highvolume,PMTCT_highvolume FROM moh731 join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on moh731.SubPartnerID = "+facilitiestable+".SubPartnerID   "+joinedwhwere+" group by "+facilitiestable+".SubPartnerID ";
            getexistingdata=" select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode, ART_Support , sum(IFNULL(rapesurvivor_M,0)+IFNULL(rapesurvivor_F,0)) as numerator , sum(IFNULL(rapesurvivor_M,0)+IFNULL(rapesurvivor_F,0)) as sexual_violence,'0' as physical_violence  , (sum(rapesurvivor_F)) as femaletotal, (sum(rapesurvivor_M)) as maletotal ,sum(initiatedpep_T) as pep_service,  "+facilitiestable+".SubPartnerID as SubPartnerID ,ART_highvolume, HTC_highvolume,PMTCT_highvolume FROM sgbv join ( "+facilitiestable+" join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = "+facilitiestable+".DistrictID )  on sgbv.SubPartnerID = "+facilitiestable+".SubPartnerID   "+joinedwhwere+" group by "+facilitiestable+".SubPartnerID ";
           
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
            
            HSSFSheet shet = wb.createSheet("GEND_GBV");
            
            
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
            cl0.setCellValue("Number of people receiving post-GBV care based on minimum package Report For " + yearmonth);
            cl0.setCellStyle(stylex);              
           
            //create the first row
       //ZZZZZZZZZZZZZZZZZZZZZZZZZZZZ
           // shet.addMergedRegion(new CellRangeAddress(0,0,20,30));
            
          
            rowpos++;
            
            
            //row two
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);        
            
            for (int a = 0; a <mainheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
               clx.setCellValue(mainheaders[a]);
                clx.setCellStyle(stylex);
              
            }
              rowpos++;
                      
              
               HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(38);        
            
            for (int a = 0; a <sectionheaders1.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                 clx.setCellValue(sectionheaders1[a]);
                clx.setCellStyle(stylex);
              
            }
              rowpos++;
              
            //row four
            HSSFRow rw3 = shet.createRow(rowpos);
            rw2.setHeightInPoints(38);
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw3.createCell(a);
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
          int sexualviolence=0;
          
          //female proportions
          double gbvless10f=0;
          double gbv10to14f=0;
          double gbv15to19f=0;
          double gbv20to24f=0;
          double gbv25to49f=0;          
          double gbv50f=0;          
          
          //male proportions
          double gbvless10m=0;
          double gbv10to14m=0;
          double gbv15to19m=0;
          double gbv20to24m=0;
          double gbv25to49m=0;
          double gbv50m=0;
         int redalert=0;
          
          double maleverify=0; 
          double femaleverify=0;
          
          int pepservice=0;
          int otherpostgbv=0;
          
          // (sum(HV0501)+sum(HV0502)+sum(HV0505)+sum(HV0506)) as otherpostgbv, subpartnera.SubPartnerID as SubPartnerID 
          
          Numerator=conn.rs.getInt("numerator");
          gbvmaletotal=conn.rs.getInt("maletotal");
          gbvfemaletotal=conn.rs.getInt("femaletotal");
          pepservice=conn.rs.getInt("pep_service");
          sexualviolence=conn.rs.getInt("numerator");
          //otherpostgbv=conn.rs.getInt("otherpostgbv"); this was ignored later
          
          
         //begin the distributions 
        //< 10	10-14	15-19	20-24	25-49  50+	Male	< 10	10-14	15-19	20-24	25-49 50+
        //0.23	0.17	0.38	0.09	0.11   0.02		0.24	0.12	0.36	0.16	0.12  0

          
           gbvless10f=(float)Math.round((0.23*gbvfemaletotal));
           gbv10to14f=(float)Math.round((0.17*gbvfemaletotal));
           gbv15to19f=(float)Math.round((0.38*gbvfemaletotal));
           gbv20to24f=(float)Math.round((0.09*gbvfemaletotal));
           gbv25to49f=(float)Math.round((0.11*gbvfemaletotal));
           gbv50f=(float)Math.round((0.02*gbvfemaletotal));
          
           //then do the normalization
          femaleverify= gbvless10f+gbv10to14f+gbv15to19f+gbv20to24f+gbv25to49f+gbv50f;
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
      gbv15to19f+=1; 
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
 gbv15to19f-=1; 
 currdiff--;
                         }
  
                                               }
  
  //=====================work on males now==========================================================
     //begin the distributions 
        //	Male	< 10	10-14	15-19	20-24	25-49 50+
        //	0.24	0.12	0.36	0.16	0.12  0

          
          
           gbvless10m=(float)Math.round((0.24*gbvmaletotal));
           gbv10to14m=(float)Math.round((0.12*gbvmaletotal));
           gbv15to19m=(float)Math.round((0.36*gbvmaletotal));
           gbv20to24m=(float)Math.round((0.16*gbvmaletotal));
           gbv25to49m=(float)Math.round((0.12*gbvmaletotal));
           gbv50m=(float)Math.round((0*gbvmaletotal));
          
           //then do the normalization
          maleverify= gbvless10m+gbv10to14m+gbv15to19m+gbv20to24m+gbv25to49m+gbv50m;
        
   
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
      gbv15to19m+=1; 
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
 gbv15to19m-=1; 
 currdiff--;
                         }
  
                                               }
          
  ArrayList al=new ArrayList();
  al.add(Numerator);
  al.add(sexualviolence);
  al.add(0);//physical or emotional violence
  al.add(pepservice);//
  al.add("0");//unknown Female
  al.add(gbvless10f);
  al.add(gbv10to14f);
  al.add(gbv15to19f);
  al.add(gbv20to24f);
  al.add(gbv25to49f);
  al.add(gbv50f);
  al.add("0");//unknown male
  al.add(gbvless10m);
  al.add(gbv10to14m);
  al.add(gbv15to19m);
  al.add(gbv20to24m);
  al.add(gbv25to49m);
  al.add(gbv50m);
 //physical/emotional 
 al.add("0");//unknown Female
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");//unknown male
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
  al.add("0");
 
  
  

  
  
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
            String created_On = IG.CreatedOn();
            
            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
            wb.write(outByteStream);
            byte[] outArray = outByteStream.toByteArray();
            response.setContentType("application/ms-excel");
            response.setContentLength(outArray.length);
            response.setHeader("Expires:", "0"); // eliminates browser caching
            response.setHeader("Content-Disposition", "attachment; filename=TB_RETENTION_VIRALLOAD_GEND_GBV_Gen_On_" + created_On + ".xls");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(datimTbViralRetention_2018Q1.class.getName()).log(Level.SEVERE, null, ex);
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

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
import java.util.HashMap;
import java.util.Map;
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
public class newdatimHTCResults_2018Q3 extends HttpServlet {
    
    
     String subcounty_countywhere=" (1=1) and ";
   
            private boolean isIPD=false;
            private boolean isOPD=false;
            private boolean isVCT=false;
            private  int totalopdglobal=0;
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
            response.setContentType("text/html;charset=UTF-8");
            
            dbConn conn = new dbConn();
                  HSSFWorkbook wb=new HSSFWorkbook();  
              
               //call the main method which calls other totals
                HtsTst(conn, request, wb);
               
                 
            
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
            response.setHeader("Content-Disposition", "attachment; filename=datim_HTC_TST_2018_Gen_On_" + createdOn + ".xls");
            OutputStream outStream = response.getOutputStream();
            outStream.write(outArray);
            outStream.flush();
            outStream.close();

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
        Logger.getLogger(newdatimHTCResults_2018Q1.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(newdatimHTCResults_2018Q1.class.getName()).log(Level.SEVERE, null, ex);
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

    
 
 public void HtsTst(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
 
 
 
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
               
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
                 //VMMC
                 HashMap<String,String> ipdhm;
                 HashMap<String,String> opdhm;
                 HashMap<String,String> vcthm;
                 HashMap<String,String> pmtcthm;
                 
                 
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
  if(1==1){VMMC(conn,request,wb);}
   if(15==15){
    VMMC_services(conn, request,wb);
                         }
  
 //==================================================================================================
 //HTC RESULTS BY SDP           
 //==================================================================================================
           
            if(1==1){
            
                HtsSdp(conn, request, wb);
            }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
          
            //_______________2017Q1 changes start here___________________
          
            //IPD, OPD and VCT are similar in many ways. we need to have global variables that can be used to alert the system the report it is generating
  
            //the below will be activated each at its own loop/worksheet
           
           
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC INPATIENT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(5==5){
                     ipdhm=Ipd(conn,request,wb);
                 }//end of IPD 5==5
            
               
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC OPD
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                
                 if(6==6){
                    opdhm= Opd(conn,request,wb);
                 }//end of 6==6 OPD

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC VCT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(8==8){
                 
                vcthm= Vct(conn,request,wb);
                 }//end of 8==8
         
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PMTCT ANC AND STAT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                if(9==9){
                   pmtcthm = Pmtct(conn, request,wb);
                        }//end of 9==9
         
               if(10==10){
    TbClinics(conn, request,wb);
                         }
               //malnutrition
               if(11==11){
    Malnutrition(conn, request,wb);
                         }
               //malnutrition
               if(12==12){
    Sti(conn, request,wb);
                         }
               if(13==13){
    Emergency(conn, request,wb);
                         }
                if(14==14){
    IndexTesting(conn, request,wb);
                         }
               
               if(12==12){
               //create the final report set
   
//_____________________________________________________________________________

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

+ "WHERE district.DistrictID='"+subcounty+"'  AND "+facilitiestable+".active=1  " ;

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
         
subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1 and  ";//20160711
         
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
    
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
			
         
        period1="DATIM HTS_TST for October "+prevYear+" to September "+year;
        
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
    
       
     period1="DATIM HTS_TST PITC-TB for October "+prevYear+" to March "+year;
   
     
       }
       else {
     
      period1="DATIM HTS_TST for April "+year+" to September "+year; 
       
            
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
          
  
      
      period1="DATIM HTS_TST  for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
    
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     
     enddate=year+endMonth+""+conn.rs.getString("enddate");
     
     period1="DATIM HTS_TST for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
     
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
     String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
     
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
   
     period1="DATIM HTS_TST  for : "+conn.rs.getString(1)+"("+prevYear+")";
     
     }
     else{

    period1="DATIM HTS_TST for : "+conn.rs.getString(1)+"("+year+")";
   
   
    
     }
      }
      }
      else{
        period1="";
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("HTS (HTC + PMTCT )");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(0);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 1;
        
                //output header 
                
        String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
        String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
        String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                    
					
					
					//create header1
   HSSFRow rw1=shet.createRow( count1);  count1++;
           rw1.setHeightInPoints(30);
           
           HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw1.createCell(0);
         
      //_____________________________________________________________report heading row 0   
      c1.setCellValue(period1);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=pitc_ipd_header1.length-1;j++){
      c1=rw1.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      
      //-----------------------------------row 1 header 
       rw0=shet.createRow( count1);  count1++; 
       rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header1.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header1[i]);
      clx.setCellStyle(stylemainHeader);
        }
 //-----------------------------------row 2 header 
       rw0=shet.createRow( count1);  count1++; 
       rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header2.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header2[i]);
      clx.setCellStyle(stylemainHeader);
        }
   
    //-----------------------------------row 3 header 
   rw0=shet.createRow( count1);   count1++; 
   rw0.setHeightInPoints(30);
 
      
    for (int i=0;i<pitc_ipd_header3.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_ipd_header3[i]);
      clx.setCellStyle(stylemainHeader);
       }
    String mergeinfor[]={"0,0,0,"+pitc_ipd_header1.length+"","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,4,6,6","2,2,7,30","2,2,31,54","2,4,55,55","2,4,56,56","2,4,57,57","2,4,41,41","3,3,7,8","3,3,31,32","2,4,58,58","2,4,59,59","2,4,60,60"};  
   
    for(int d=0;d<mergeinfor.length;d++){
    if(!mergeinfor[d].equals("")){
        String pos[]=mergeinfor[d].split(",");
     shet.addMergedRegion(new CellRangeAddress(new Integer(pos[0]),new Integer(pos[1]),new Integer(pos[2]),new Integer(pos[3])));   
    }
                                         }
    
                
                
               // count1++;
              
                int totalcolumns=0;
   
  String getsites="SELECT  county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport, IFNULL(ART_highvolume,0) as arthv,  IFNULL(HTC_highvolume,0) as htchv,  IFNULL(PMTCT_highvolume,0) as pmtcthv, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT"
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 OR PMTCT=1 )  AND "+facilitiestable+".active=1  group by "+facilitiestable+".SubPartnerID  "; 
    
   
   conn.rs=conn.st.executeQuery(getsites);
     
        while(conn.rs.next()){
            
            String mfl=conn.rs.getString("mflcode");
            
     //use the opd to get the facility names
     opdhm.get("mfl_"+mfl);
     
     
     String arthv=conn.rs.getString("arthv");
     String pmtcthv=conn.rs.getString("pmtcthv");
     String htchv=conn.rs.getString("htchv");
     
     
String cty=conn.rs.getString("county");
String sty=conn.rs.getString("district");
String fac=conn.rs.getString("facility");
mfl=conn.rs.getString("mflcode");
String st=conn.rs.getString("htcsupport");
  double gtt=new Double(ipdhm.getOrDefault("gtt_"+mfl,"0"));
  double gtp=new Double(ipdhm.getOrDefault("gtp_"+mfl,"0"));
 double ukpf=(new Double(ipdhm.getOrDefault("ukpf_"+mfl,"0")) + new Double(opdhm.getOrDefault("ukpf_"+mfl,"0")) + new Double(vcthm.getOrDefault("ukpf_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("ukpf_"+mfl,"0")));
 double ukpm=(new Double(ipdhm.getOrDefault("ukpm_"+mfl,"0")) + new Double(opdhm.getOrDefault("ukpm_"+mfl,"0")) + new Double(vcthm.getOrDefault("ukpm_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("ukpm_"+mfl,"0")));
 double pf1=(new Double(ipdhm.getOrDefault("pf1_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf1_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf1_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf1_"+mfl,"0")));
 double pm1=(new Double(ipdhm.getOrDefault("pm1_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm1_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm1_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm1_"+mfl,"0")));
 double pf4=(new Double(ipdhm.getOrDefault("pf4_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf4_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf4_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf4_"+mfl,"0")));
 double pm4=(new Double(ipdhm.getOrDefault("pm4_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm4_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm4_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm4_"+mfl,"0")));
 double pf9=(new Double(ipdhm.getOrDefault("pf9_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf9_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf9_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf9_"+mfl,"0")));
 double pm9=(new Double(ipdhm.getOrDefault("pm9_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm9_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm9_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm9_"+mfl,"0")));
 double pf14=(new Double(ipdhm.getOrDefault("pf14_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf14_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf14_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf14_"+mfl,"0")));
 double pm14=(new Double(ipdhm.getOrDefault("pm14_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm14_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm14_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm14_"+mfl,"0")));
 double pf19=(new Double(ipdhm.getOrDefault("pf19_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf19_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf19_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf19_"+mfl,"0")));
 double pm19=(new Double(ipdhm.getOrDefault("pm19_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm19_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm19_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm19_"+mfl,"0")));
 double pf24=(new Double(ipdhm.getOrDefault("pf24_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf24_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf24_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf24_"+mfl,"0")));
 double pm24=(new Double(ipdhm.getOrDefault("pm24_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm24_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm24_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm24_"+mfl,"0")));
 double pf29=(new Double(ipdhm.getOrDefault("pf29_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf29_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf29_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf29_"+mfl,"0")));
 double pm29=(new Double(ipdhm.getOrDefault("pm29_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm29_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm29_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm29_"+mfl,"0")));
 double pf34=(new Double(ipdhm.getOrDefault("pf34_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf34_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf34_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf34_"+mfl,"0")));
 double pm34=(new Double(ipdhm.getOrDefault("pm34_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm34_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm34_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm34_"+mfl,"0")));
 double pf39=(new Double(ipdhm.getOrDefault("pf39_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf39_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf39_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf39_"+mfl,"0")));
 double pm39=(new Double(ipdhm.getOrDefault("pm39_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm39_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm39_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm39_"+mfl,"0")));
 double pf49=(new Double(ipdhm.getOrDefault("pf49_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf49_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf49_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf49_"+mfl,"0")));
 double pm49=(new Double(ipdhm.getOrDefault("pm49_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm49_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm49_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm49_"+mfl,"0")));
 double pf50=(new Double(ipdhm.getOrDefault("pf50_"+mfl,"0")) + new Double(opdhm.getOrDefault("pf50_"+mfl,"0")) + new Double(vcthm.getOrDefault("pf50_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pf50_"+mfl,"0")));
 double pm50=(new Double(ipdhm.getOrDefault("pm50_"+mfl,"0")) + new Double(opdhm.getOrDefault("pm50_"+mfl,"0")) + new Double(vcthm.getOrDefault("pm50_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("pm50_"+mfl,"0")));
 
 
 
 double uknf=(new Double(ipdhm.getOrDefault("uknf_"+mfl,"0")) + new Double(opdhm.getOrDefault("uknf_"+mfl,"0")) + new Double(vcthm.getOrDefault("uknf_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("uknf_"+mfl,"0")));
 double uknm=(new Double(ipdhm.getOrDefault("uknm_"+mfl,"0")) + new Double(opdhm.getOrDefault("uknm_"+mfl,"0")) + new Double(vcthm.getOrDefault("uknm_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("uknm_"+mfl,"0")));
 double nf1=(new Double(ipdhm.getOrDefault("nf1_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf1_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf1_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf1_"+mfl,"0")));
 double nm1=(new Double(ipdhm.getOrDefault("nm1_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm1_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm1_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm1_"+mfl,"0")));
 double nf4=(new Double(ipdhm.getOrDefault("nf4_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf4_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf4_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf4_"+mfl,"0")));
 double nm4=(new Double(ipdhm.getOrDefault("nm4_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm4_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm4_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm4_"+mfl,"0")));
 double nf9=(new Double(ipdhm.getOrDefault("nf9_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf9_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf9_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf9_"+mfl,"0")));
 double nm9=(new Double(ipdhm.getOrDefault("nm9_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm9_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm9_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm9_"+mfl,"0")));
 double nf14=(new Double(ipdhm.getOrDefault("nf14_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf14_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf14_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf14_"+mfl,"0")));
 double nm14=(new Double(ipdhm.getOrDefault("nm14_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm14_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm14_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm14_"+mfl,"0")));
 double nf19=(new Double(ipdhm.getOrDefault("nf19_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf19_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf19_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf19_"+mfl,"0")));
 double nm19=(new Double(ipdhm.getOrDefault("nm19_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm19_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm19_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm19_"+mfl,"0")));
 double nf24=(new Double(ipdhm.getOrDefault("nf24_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf24_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf24_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf24_"+mfl,"0")));
 double nm24=(new Double(ipdhm.getOrDefault("nm24_"+mfl,"0")) 
         + new Double(opdhm.getOrDefault("nm24_"+mfl,"0")) 
         + new Double(vcthm.getOrDefault("nm24_"+mfl,"0"))
         + new Double(pmtcthm.getOrDefault("nm24_"+mfl,"0")));
 double nf29=(new Double(ipdhm.getOrDefault("nf29_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf29_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf29_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf29_"+mfl,"0")));
 double nm29=(new Double(ipdhm.getOrDefault("nm29_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm29_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm29_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm29_"+mfl,"0")));
 double nf34=(new Double(ipdhm.getOrDefault("nf34_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf34_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf34_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf34_"+mfl,"0")));
 double nm34=(new Double(ipdhm.getOrDefault("nm34_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm34_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm34_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm34_"+mfl,"0")));
 double nf39=(new Double(ipdhm.getOrDefault("nf39_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf39_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf39_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf39_"+mfl,"0")));
 double nm39=(new Double(ipdhm.getOrDefault("nm39_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm39_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm39_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm39_"+mfl,"0")));
 double nf49=(new Double(ipdhm.getOrDefault("nf49_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf49_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf49_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf49_"+mfl,"0")));
 double nm49=(new Double(ipdhm.getOrDefault("nm49_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm49_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm49_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm49_"+mfl,"0")));
 double nf50=(new Double(ipdhm.getOrDefault("nf50_"+mfl,"0")) + new Double(opdhm.getOrDefault("nf50_"+mfl,"0")) + new Double(vcthm.getOrDefault("nf50_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nf50_"+mfl,"0")));
 double nm50=(new Double(ipdhm.getOrDefault("nm50_"+mfl,"0")) + new Double(opdhm.getOrDefault("nm50_"+mfl,"0")) + new Double(vcthm.getOrDefault("nm50_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("nm50_"+mfl,"0")));
 double tt  =(new Double(ipdhm.getOrDefault("tt_"+mfl,"0")) + new Double(opdhm.getOrDefault("tt_"+mfl,"0")) + new Double(vcthm.getOrDefault("tt_"+mfl,"0"))+ new Double(pmtcthm.getOrDefault("tt_"+mfl,"0")));
            
           



  String alldatavals[]={
        cty,sty,fac,""+mfl,st
        ,""+gtt,""+gtp
        ,""+ukpf,""+ukpm
        ,""+pf1,""+pm1
        ,""+pf4,""+pm4
        ,""+pf9,""+pm9
        ,""+pf14,""+pm14
        ,""+pf19,""+pm19
        ,""+pf24,""+pm24
        ,""+pf29,""+pm29
        ,""+pf34,""+pm34
        ,""+pf39,""+pm39
        ,""+pf49,""+pm49
        ,""+pf50,""+pm50 
          
         //negative starts here 
        ,""+uknf,""+uknm
        ,""+nf1,""+nm1
        ,""+nf4,""+nm4
        ,""+nf9,""+nm9
        ,""+nf14,""+nm14
        ,""+nf19,""+nm19
        ,""+nf24,""+nm24
        ,""+nf29,""+nm29
        ,""+nf34,""+nm34
        ,""+nf39,""+nm39
        ,""+nf49,""+nm49
        ,""+nf50,""+nm50
        ,""+tt,""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")
          
        };

 totalcolumns=alldatavals.length;
            //data rows     
            HSSFRow rw = shet.createRow(count1);
int mypos=0;
HSSFCell cxy = null;
for(int a=0;a<alldatavals.length;a++){
      
       cxy=rw.createCell(mypos);mypos++;
       if(a==4||a<3)
       {
           //non numeric characters
        cxy.setCellValue(alldatavals[a]);
       }
       else {
        cxy.setCellValue(new Double(alldatavals[a]).intValue());
       
       }
      
       cxy.setCellStyle(stborder);
  
  }

            // System.out.println("");
            count1++;
        }
        
        
        
        
        
        
        
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        shet.setAutoFilter(new CellRangeAddress(4, count1 - 1, 0, totalcolumns-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= 5; i++) 
        {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 5); 

 
               }
 
 
 }
 public void HtsSdp(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
   //new htc for PITC 
 String facilitiestable="subpartnera";
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
            
            
            String month = "";
            String year = "";
            String facil = "361";
            String form = "moh731";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            String subheaders[]={"Tested","Positive","Negative"};
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","ART High Volume","HTC High Volume","PMTCT High Volume","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","Tuberculosis","","","Outpatient Department","","","Inpatient","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Serology","",""};
           
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
                    
                    String getfacil = "select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='" + facil + "'  AND "+facilitiestable+".active=1 ";
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
                   subcounty_countywhere=" (county.CountyID='"+county+"') and ";//20160711   
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
            String tbstatduration="";
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
                tbstatduration="year='"+year+"'";
            } else if (reportDuration.equals("2")) {
                semi_annual = request.getParameter("semi_annual");
//        semi_annual="2";
                if (semi_annual.equals("1")) {
                    yearmonth = "Semi Annual Report For " + prevYear + " Oct to " + year + " Mar";
                    duration = " " + form + ".yearmonth BETWEEN " + prevYear + "10 AND " + year + "03";
                     tbstatduration="year='"+year+"' and (quarter='1' || quarter='2') ";
                } else {
                    yearmonth = "Semi Annual Report for Apr to  Sep " + year;
                    duration = " " + form + ".yearmonth BETWEEN " + year + "04 AND " + year + "09";
                     tbstatduration="year='"+year+"' and (quarter='2' || quarter='3') ";
                }
            } else if (reportDuration.equals("3")) {
                try {
                    
                    //quarterly
                    String startMonth, endMonth;
                    quarter = request.getParameter("quarter");
                    //       quarter="3";
                    
                     tbstatduration="year='"+year+"' and quarter='"+quarter+"'  ";
                     
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
                monthcopy = Integer.parseInt(request.getParameter("month"));
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                tbstatduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                    yearmonth = "Monthly Report For " + prevYear + "_(" + month + ")";
                    duration = " " + form + ".yearmonth=" + prevYear + "" + month;
                } else {
                    duration = " " + form + ".yearmonth=" + year + "0" + month;
                    yearmonth = "Monthly Report For " + year + "_(" + month + ")";
                }
            } else {
                duration = "";
            }
            
            //======================================================================
//==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            String subcountywhere = "";
            
            String subcounty = "";
            
            if (!request.getParameter("subcounty").equals("")) {
                
                subcounty = request.getParameter("subcounty");
                  subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and "; 
            }
            
            if (!request.getParameter("county").equals("")) {
                county=request.getParameter("county");
                   subcounty_countywhere=" (district.countyid='"+request.getParameter("county")+"') and ";//20160711 
            }
            
            String getexistingdata = "";
            
            if (!county.equals("")) {
                
                countywhere = " and district.countyid = '" + county + "'";
           
            }
            
            if (!subcounty.equals("")) {
                
                subcountywhere = " and subpartnera.DistrictID = '" + subcounty + "'";
              
            }
            
            if (!facil.equals("")&& reportType.equalsIgnoreCase("2")) {
                
                facilitywhere = " and " + form + ".SubPartnerID = '" + facil + "'";
                
            }
            
            String joinedwhwere = " where 1=1 " + yearwhere + " && " + duration + " " + countywhere + " " + subcountywhere;
           
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
    int blankrows=38;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " subpartnera.SubPartnerNom as facility, subpartnera.CentreSanteId as mflcode, subpartnera.HTC_Support1 as htcsupport,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume "
           + " FROM  subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID   where "+subcounty_countywhere+" (HTC='1'|| PMTCT='1'|| VMMC='1')  AND "+facilitiestable+".active=1  group by subpartnera.SubPartnerID   "; 
                System.out.println("~~~~~~~~"+getstaticfacilities);
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
            
           //getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, (sum(P511KN) + sum(P511KU)) as P511KN,sum(HV0103) as HV0103,sum(HV0116) as HV0116  subpartnera.SubPartnerID as SubPartnerID  FROM moh731 left join moh711_new on moh731.id=moh711_new.id left join vmmc on moh731.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1') group by subpartnera.SubPartnerID   ";
           getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1,PMTCT_Support, sum(HV0201) as HV0201,sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232,     sum(P511KP) as P511KP, (sum(P511KN) + sum(P511KU)) as P511KN,sum(HV0103) as HV0103, sum(HV0110+HV0111+HV0112+HV0113+HV0114+HV0115) as HV0116 , SUM(IFNULL(0,0)) as serology_tes ,'0' as serology_pos , SUM(IFNULL(0,0)) as serology_neg , subpartnera.SubPartnerID as SubPartnerID , subpartnera.ART ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume FROM moh731 left join moh711_new on moh731.id=moh711_new.id left join vmmc on moh731.id=vmmc.tableid join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" and (HTC='1'||PMTCT='1'||VMMC='1')  AND "+facilitiestable+".active=1  group by subpartnera.SubPartnerID   ";
              System.out.println("@@"+getexistingdata);
              String Tbid=year+"_"+quarter+"_"+facil;
           // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration;
            
     String distincttbstatfacils="select distinct(SubPartnerID) as partnerid from  tb_stat_art WHERE "+tbstatduration;  
            
      ArrayList tbstat= new ArrayList();  
 
     conn.rs1=conn.st1.executeQuery(distincttbstatfacils);
     
     while(conn.rs1.next())
     {
     tbstat.add(conn.rs1.getString(1));     
     }
     
     //distinct eid sites
     
       String distincteidfacils="select distinct(SubPartnerID) as partnerid from  eid_datim WHERE "+tbstatduration;  
            
      
      ArrayList eidal= new ArrayList();  
      
     conn.rs1=conn.st1.executeQuery(distincteidfacils);
     
     
     while(conn.rs1.next())
     {
     eidal.add(conn.rs1.getString(1));     
     }
     
//=====================================================================================================
     //HTC RESULTS BY SDP
//=====================================================================================================    
//______________________________________________________________________________________
            //                       NOW CREATE THE WORKSHEETS
            //______________________________________________________________________________________
         
            HSSFSheet shet = wb.createSheet("HTC RESULTS BY SDP");
            
            
            int rowpos=0;
            //create headers for that worksheet
            HSSFRow rw = shet.createRow(rowpos);
            rw.setHeightInPoints(25);
            HSSFCell cl0 = rw.createCell(0);
            cl0.setCellValue("HTC Results BY Service Delivery Point and Test Result(Required) " + yearmonth);
            cl0.setCellStyle(style);
            rowpos++;
            for (int a = 1; a < sectionheaders.length; a++) {
                HSSFCell clx = rw.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(style);
                shet.setColumnWidth(a, 5000);
            }
           
            HSSFRow rw1 = shet.createRow(rowpos);
            rw1.setHeightInPoints(38);
            rowpos++;
            
            for (int a = 0; a <sectionheaders.length; a++) {
                HSSFCell clx = rw1.createCell(a);
                clx.setCellValue(sectionheaders[a]);
                clx.setCellStyle(stylex);
              
                if(a>7&&a<sectionheaders.length){
                    if(sectionheaders[a].equals("")){}
                    else {
                        shet.addMergedRegion(new CellRangeAddress(1,1,a,a+2));
                    }
                }
            }
            shet.addMergedRegion(new CellRangeAddress(0,0,0,sectionheaders.length-1));
            
            shet.setColumnWidth(0, 5000);
            
            //add section 2
            HSSFRow rw2 = shet.createRow(rowpos);
            rw2.setHeightInPoints(25);
            
            for (int a = 0; a <8; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue("");
                clx.setCellStyle(stylex);
                shet.addMergedRegion(new CellRangeAddress(rowpos-1,rowpos,a,a));
                                       }
            
            int b=0;
            for (int a = 8; a <sectionheaders.length; a++) {
                HSSFCell clx = rw2.createCell(a);
                clx.setCellValue(subheaders[b]);
                b++;
                if(b==3){b=0;}
                clx.setCellStyle(stylex);
                
                                                            }
            rowpos++;
          
            conn.rs= conn.st.executeQuery(getexistingdata);
            while(conn.rs.next()){
          String isARTsite=conn.rs.getString("ART");      
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
                
                
                int colpos=0; 
           int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
               
                int arthv=0;
                int htchv=0;
                int pmtcthv=0;
                
               if(1==1){
               
      if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getInt("ART_highvolume");}
      if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getInt("HTC_highvolume"); }
      if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getInt("PMTCT_highvolume");}
               
               }
               //county
               if(1==1){
                       
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                 //facility name
                   if(1==1){
                      
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="DSD";
 if(conn.rs.getString("HTC_Support1")==null||conn.rs.getString("HTC_Support1").equals("")){
     /** commented on 201607
    if(conn.rs.getString("PMTCT_Support")!=null&&!conn.rs.getString("PMTCT_Support").equals("null")){              
 support=conn.rs.getString("PMTCT_Support");
    }   */      
                  }
 else {
    
  support=conn.rs.getString("HTC_Support1");
 }
   System.out.println("______:"+conn.rs.getString("HTC_Support1")+":__"+support);                
                  
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(support/*"DSD" *//**/);
                clx.setCellStyle(style2);
               
            colpos++;
            //skip both pmtct support s
            conpos++;
            conpos++;     
               } 
               
            //arthv
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(arthv);
                clx.setCellStyle(style2);               
            colpos++;           
                           
               }          
                    
            //htchv
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(htchv);
                clx.setCellStyle(style2);               
            colpos++;           
                           
               }
                   //pmtcthv
                   if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(pmtcthv);
                clx.setCellStyle(style2);               
            colpos++;           
                           
                          } 
                   
                    
   //____________________Totals begin here______________                 
                    
                   //Antenatal
                   if(1==1){
//sum(HV0202) as HV0202,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0206");
                tested=conn.rs.getInt("HV0201");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                 
             
                    //Labour & Delivery
                   if(1==1){
//,sum(HV0203) as HV0203,sum(HV0206) as HV0206,sum(HV0207) as HV0207,sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                
                
                positive=conn.rs.getInt("HV0207");
                tested=conn.rs.getInt("HV0202");
                negative=tested-positive;
                
              
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                   
                   //-------------------------------------------------------------------------------------------------------------------
                   
      //under 5 from nascop
      if(1==1){
          
          //Note: on 201607, it was suggested that we be reading tb data from opd and not from tbstat as it used to be. That proporsal however was later discaerded 
          //The old status of reading tb data from tibu still remains
         
                int tested=0;
                int positive=0;
                int negative=0;
                
                
          String getstat="select sum(positive) as positive ,sum(negative) as negative from   eid_datim join subpartnera on eid_datim.SubPartnerID=subpartnera.SubPartnerID   WHERE "+tbstatduration+" and  eid_datim.SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and (subpartnera.HTC=1 || subpartnera.PMTCT=1 )   AND "+facilitiestable+".active=1 ";
          conn.rs1=conn.st1.executeQuery(getstat);
           
          if(conn.rs1.next()){ //uncomment if to get data from tbstat
           //if(1==1){
           
             // positive=conn.rs1.getInt("positive"); negative=conn.rs1.getInt("negative"); tested=negative+positive;
              
              //positive=tbpositive; negative=tbnegative; tested=tbtested;
            
             ///** 
           if(eidal.contains(conn.rs.getString("SubPartnerID")))
            { 
           eidal.remove(conn.rs.getString("SubPartnerID"));
           }    
           //*/
           
                   }
          
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){         //if generating a monthly report, dont show data since tb stat data is quarterly                  
               //if(1==1){                          
                clx.setCellValue(tested);
                       }
                else {
                clx.setCellValue("");
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){        //if generating a monthly report, dont show data since tb stat data is quarterly                      
                //if(1==1){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue("");
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){    //if generating a monthly report, dont show data since tb stat data is quarterly                          
                //if(1==1){                          
                clx2.setCellValue(negative);
                        }
                else {
                clx2.setCellValue("");
                     }
                clx2.setCellStyle(style2);               
                colpos++;
                
               }                     
                  
//-------------------------------------------------------------------------------------------------------------------
                   
              
                     //old Under 5 Clinic is not active now..
      //_____________________________

      //____COMMENTED FOR NOW________
      //_____________________________
      //_____________________________
                   if(1==2){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0232");
                tested=conn.rs.getInt("HV0228");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                   
                     //Post Natal
                   if(1==1){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("HV0208");
                tested=conn.rs.getInt("HV0203");
                negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }       
                  
                       
      //Outpatient
      
       //________________________________________________________________________________________________________________________________________________________________________
  //NEW CHANGES 201607  CHANGING DATA SOURCE FROM old 711 to new 711 
       //Here Ratios are being applied
       //isARTsite
       int htctested=conn.rs.getInt("HV0103");
       int htcpositive=conn.rs.getInt("HV0116");
       
       double inpatienttested=0;
       double inpatientpos=0;
       double inpatientneg=0;
       
       double outpatienttested=0;
       double outpatientpos=0;
       double outpatientneg=0;
       
       double vcttested=0;
       double vctpos=0;
       double vctneg=0;
       
           int tbtested=0;
           int tbpositive=0;
           int tbnegative=0;
       
       //____Discard the suggestion below. it was disregarded_____
       //NOTE ..TB TESTED DATA WILL NOW BE 1 % OF OPD DATA
       // TB POS will be 33% of the tested, if only there is some positive data in the opd section..
       //note, this will only happen on the art sites, otherwise the opd data will not be edited
	 
	
	//do a query that calculates the sites supporting inpatient and outpatient for the last six months
       //since we are already in a loop, see if the current site calculates 
      // String issiteipd=" select sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot from moh711 where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ( yearmonth between '201510' and '201603') ";
       String issiteipd=" select sum(IPD) as DTCB_Test_In_Tot from subpartnera where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'  AND "+facilitiestable+".active=1  ";
               
       //String issiteipd=" select IPD as DTCB_Test_In_Tot from "+facilitiestable+" where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and IPD='1' ";
                    
        conn.rs1=conn.st1.executeQuery(issiteipd);
       if(conn.rs1.next()){
         
       int semiannualinpatient=0;
       if(conn.rs1.getString("DTCB_Test_In_Tot")!=null){ semiannualinpatient=conn.rs1.getInt("DTCB_Test_In_Tot");}
       
       
           if(semiannualinpatient>0)
              {
                  
              System.out.println("%%"+issiteipd);     
       	//Sites with Inpatient services
 	              //OPD	IPD	VCT

//Testing ratios	72%	17%	11%
//Positivity ratios	62%	19%	19%
           //tested
       outpatienttested=(double)Math.round((0.72*htctested));    
       inpatienttested=(double)Math.round((0.17*htctested));    
       vcttested=(double)Math.round((0.11*htctested));    
       
       //positive
       outpatientpos=(double)Math.round((0.62*htcpositive));    
       inpatientpos=(double)Math.round((0.19*htcpositive));    
       vctpos=(double)Math.round((0.19*htcpositive));
       
       //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+inpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+inpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
       
       //now get the negative values 
       vctneg=vcttested-vctpos;
       inpatientneg=inpatienttested-inpatientpos;
       outpatientneg=outpatienttested-outpatientpos;
       
            }
       else {
           
       //site not supporting inpatient
      //Sites without Inpatient services	
	           //OPD	VCT	 
//HIV Tested	     86%	14%	 
//Tested Positive    85%	15% 
      outpatienttested=(double)Math.round((0.86*htctested));    
      // inpatienttested=(double)Math.round((0.17*htctested));    
       vcttested=(double)Math.round((0.14*htctested));    
       
       //positive
       outpatientpos=(double)Math.round((0.85*htcpositive));    
       //inpatientpos=(double)Math.round((0.19*htcpositive));    
         vctpos=(double)Math.round((0.15*htcpositive));   
       
         
         //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
       //now get the negative values 
       vctneg=vcttested-vctpos;
      // inpatientneg=inpatienttested-inpatientpos;
       outpatientneg=outpatienttested-outpatientpos;  
         
       
       } 
          
            }//end of conn
       
 //_______________________________________________________________________________________________________________________________________________
 
                
//-------------------------------------------------------------------------------------------------------------------
                   
      //TB Stat
      if(1==1){
          //Note: on 201607, it was suggested that we be reading tb data from opd and not from tbstat as it used to be. That proporsal however was not followed 
          //The old status of reading tb data still remains
                int tested=0;
                int positive=0;
                int negative=0;
          String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'";
          conn.rs1=conn.st1.executeQuery(getstat);
           
          if(conn.rs1.next()){ //uncomment if to get data from tbstat
           //if(1==1){
           
              /**
               * commented following a request from komen on 17th October 2016 .
              positive=conn.rs1.getInt("positive");
              negative=conn.rs1.getInt("negative");
              tested=negative+positive;
              */
              
              //positive=tbpositive; //uncomment if tb data will appear
              //negative=tbnegative; //uncomment if tb data will appear
              //tested=tbtested;    //uncomment if tb data will appear
              
              //continue from here
              //remove the current facility id
              
              
             //uncomment if commented
             ///** 
           if(tbstat.contains(conn.rs.getString("SubPartnerID")))
            { 
           tbstat.remove(conn.rs.getString("SubPartnerID"));
           }    
          
                   }
              
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){  clx.setCellValue(tested);}
                else { clx.setCellValue(""); }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){ clx1.setCellValue(positive);  }
                else {  clx1.setCellValue("");  }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){    //if generating a monthly report, dont show data since tb stat data is quarterly                          
                clx2.setCellValue(negative);}
                else { clx2.setCellValue("");}
                clx2.setCellStyle(style2);    colpos++;}                     
                   
                   
//-------------------------------------------------------------------------------------------------------------------                   
 //sexually transmitted insfections    
      //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
       if(1==2){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
                
               } 
       
       
       
       
       if(1==1){
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(outpatienttested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(outpatientpos);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(outpatientneg);
                clx2.setCellStyle(style2);               
                colpos++;
                
               }             
            
              
              //Inpatient
              if(1==1){

                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(inpatienttested);
                clx.setCellStyle(style2);               
                colpos++;
                
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(inpatientpos);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(inpatientneg);
                clx2.setCellStyle(style2);               
                colpos++;
               }             
            
              //HIV Care and Treatment
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
               // positive=conn.rs.getInt("DTCC_HIV_In_Tot");
                //tested=conn.rs.getInt("DTCB_Test_In_Tot");
               // negative=tested-positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
               }        
               
              
              
              //VMMC
              if(1==1){
//     sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                positive=conn.rs.getInt("P511KP");
                negative=conn.rs.getInt("P511KN");
                tested=negative+positive;
                
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
               }        
              
             
              
                 //VCT  (Co-located)
              if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(vcttested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(vctpos);
                clx1.setCellStyle(style2);               
                colpos++;
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(vctneg);
                clx2.setCellStyle(style2);               
                colpos++;
               }        
               
             //serology
              if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt("serology_tes"));
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(conn.rs.getInt("serology_pos"));
                clx1.setCellStyle(style2);               
                colpos++;
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(conn.rs.getInt("serology_neg"));
                clx2.setCellStyle(style2);               
                colpos++;
               } 
           
              //Voluntary counselling and testing (Stand alone)
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
              
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2);               
                colpos++;
               }  
              
              //Mobile 
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){   
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                //tested
                HSSFCell clx = rwx.createCell(colpos); clx.setCellValue(tested); clx.setCellStyle(style2);  colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);  clx1.setCellValue(positive);  clx1.setCellStyle(style2);  colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos); clx2.setCellValue(negative); clx2.setCellStyle(style2);  colpos++;}  
              
                //Home Based  
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
              
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                //negative
                HSSFCell clx2 = rwx.createCell(colpos); clx2.setCellValue(negative); clx2.setCellStyle(style2);    colpos++; }  
             
              //Other
              
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                //tested
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(tested);
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                HSSFCell clx1 = rwx.createCell(colpos);
                clx1.setCellValue(positive);
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                clx2.setCellValue(negative);
                clx2.setCellStyle(style2); colpos++; }  
              
               rowpos++;    
            }
              
//________________________________________________EID SKIPPED SITES_______________________________________________________            
//________________________________________________EID SKIPPED SITES_______________________________________________________
            
            for(int a=0;a<eidal.size();a++)
            {
          
                if(1==1){
                
               int colpos=0; 
               int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25); 
          
           String getstat=  "select  county,DistrictNom,   SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1 as supporttype , IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, sum(eid_datim.positive) as positive ,sum(eid_datim.negative) as negative,sum(tb_stat_art.positive) as tbpositive ,sum(tb_stat_art.negative) as tbnegative, eid_datim.SubPartnerID as SubPartnerID, IFNULL(subpartnera.HTC,0) as HTC, IFNULL(subpartnera.PMTCT=1,0) as PMTCT  from eid_datim  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on eid_datim.SubPartnerID = subpartnera.SubPartnerID left join tb_stat_art on eid_datim.SubPartnerID=tb_stat_art.SubPartnerID WHERE "+tbstatduration.replace("year", "eid_datim.year").replace("quarter", "eid_datim.quarter")+" and  eid_datim.SubPartnerID='"+eidal.get(a)+"'  AND "+facilitiestable+".active=1   ";
                    System.out.println("~~~"+getstat);
            // String getstat="select sum(positive) as positive ,sum(negative) as negative from   eid_datim join subpartnera on eid_datim.SubPartnerID=subpartnera.SubPartnerID   WHERE "+tbstatduration+" and  eid_datim.SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and (subpartnera.HTC=1 || subpartnera.PMTCT=1 )";
        
           conn.rs=conn.st.executeQuery(getstat);
           
           if(conn.rs.next()){
           if(conn.rs.getString("mflcode")!=null) {    
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
             staticdsd_ta.remove(mflindex); }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
               
               
            //county
               if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos).substring(0, 1).toUpperCase()+conn.rs.getString(conpos).substring(1).toLowerCase());
                clx.setCellStyle(style2);
               colpos++; conpos++;} 
                //subcounty
                 if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
             colpos++; conpos++;} 
                 
                 //facility name
                   if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
               clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
            colpos++;
            conpos++;} 
                   //mfl
                   if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
            colpos++; conpos++; } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="DSD";

    if(conn.rs.getString("supporttype")!=null && !conn.rs.getString("supporttype").equals("")){
  support=conn.rs.getString("supporttype");
                                              }
               
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(/*"DSD" **/support);
                clx.setCellStyle(style2);
            colpos++;
            //skip both pmtct support s
            conpos++;
                           
               } 
              
                    //ART HIGH VOLUME
                if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
            colpos++;
            conpos++;    
               }     
                
                    //HTC HIGH VOLUME
                if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
            colpos++;
            conpos++;     
               } 
                    //PMTCT HIGH VOLUME
                if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               }
    //enter blanks in columns from Facility type  up to the tb column
                    
                if(1==1){
                 for(int c=0;c<6;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(0);
                clx.setCellStyle(style2);               
            colpos++;
                                      }     
                              } 
               
               
               if(1==1){
               
                int tested=0;
                int positive=0;
                int negative=0;
          // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+tbstat.get(a)+"'";
          
         
          if(conn.rs.getInt("HTC")==1 || conn.rs.getInt("PMTCT")==1){
              positive=conn.rs.getInt("positive");
              negative=conn.rs.getInt("negative");
              tested=negative+positive;
             
          }
           
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue(0);
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue(0);
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue(0);
                     }
                clx2.setCellStyle(style2);               
                colpos++; 
                
                }//end of eid if query
               
               //post blanks on postnatal columns
               
                    if(1==1){
                 for(int c=0;c<3;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(0);
                clx.setCellStyle(style2);               
            colpos++;
           // conpos++;
                           }     
                           }
               
         //post tb values and remove tb too
           
               if(1==1){
               
                int tested=0;
                int positive=0;
                int negative=0;
          // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+tbstat.get(a)+"'";
          
            /**
            * commented following a request from komen on 17th October 2016
              positive=conn.rs.getInt("tbpositive");
              negative=conn.rs.getInt("tbnegative");
              tested=negative+positive;
             */  
                
           if(tbstat.contains(conn.rs.getString("SubPartnerID"))){
               
               tbstat.remove(conn.rs.getString("SubPartnerID"));
           
           }
              
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){                          
                clx.setCellValue(tested);
                                                }
                else {
                clx.setCellValue(0);
                     }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){                          
                clx1.setCellValue(positive);
                                                }
                else {
                clx1.setCellValue(0);
                     }
                clx1.setCellStyle(style2);               
                colpos++;
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){                          
                clx2.setCellValue(negative);
                                                }
                else {
                clx2.setCellValue(0);
                     }
                clx2.setCellStyle(style2);               
                colpos++; 
               
                }//end of tbstat if query
                 
               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=23;c<sectionheaders.length;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(0);
                clx.setCellStyle(style2);               
            colpos++;
           // conpos++;
                 }     
               } 
           }  
           }//end of if query
             
                }//end of if 1==1
            
                rowpos++;
                
            }//end of for loop
             
//________________________________________________END EID SKIPPED SITES_______________________________________________________            
//________________________________________________END EID SKIPPED SITES_______________________________________________________            
           
            //TB SKIPPED SITES
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
            
            System.out.println("_____======______"+tbstat.size());
            
            for(int a=0;a<tbstat.size();a++)
            {
             //System.out.println("%%%%%======______RoWno::"+tbstat.get(a));    
                
                if(1==1){
               int colpos=0; 
               int conpos=1; 
               HSSFRow rwx = shet.createRow(rowpos); 
               rwx.setHeightInPoints(25);
           String getstat=    "select  county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, sum(positive) as positive ,sum(negative) as negative, tb_stat_art.SubPartnerID as SubPartnerID , IFNULL(subpartnera.HTC,0) as HTC, IFNULL(subpartnera.PMTCT=1,0) as PMTCT from tb_stat_art  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on tb_stat_art.SubPartnerID = subpartnera.SubPartnerID  WHERE "+tbstatduration+" and  tb_stat_art.SubPartnerID='"+tbstat.get(a)+"'   AND "+facilitiestable+".active=1  ";
           //and (subpartnera.HTC=1 || subpartnera.PMTCT=1 )
           conn.rs=conn.st.executeQuery(getstat);
           
           if(conn.rs.next()){ 
                
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
             staticdsd_ta.remove(mflindex);}
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
              
            //county
               if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
            colpos++;
            conpos++;     
               } 
                //subcounty
                 if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
            colpos++; conpos++;} 
                 
                 //facility name
                   if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
               clx.setCellValue(conn.rs.getString(conpos));
                clx.setCellStyle(style2);
              colpos++; conpos++;} 
                   //mfl
       if(1==1){ HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
           colpos++; conpos++; } 
//support type//######################################################################################
                    if(1==1){
 String support="NA";
  support=conn.rs.getString("supporttype");
  HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue("DSD" /** support */);
                clx.setCellStyle(style2);
            colpos++;
            //skip both pmtct support s
            conpos++;
           } 
            
                    
                    //ART HIGH VOLUME
                if(1==1){
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
            colpos++; conpos++;
                           
               }     
               
                    //HTC HIGH VOLUME
                if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
              colpos++;conpos++;
             } 
               
                    //PMTCT HIGH VOLUME
                if(1==1){
                         
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(conn.rs.getInt(conpos));
                clx.setCellStyle(style2);
               
            colpos++;
            conpos++;
                           
               } 
                    
    //enter blanks in columns from Facility type  up to the tb column
                    
                      if(1==1){
                 for(int c=8;c<20;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(0);
                clx.setCellStyle(style2);               
            colpos++;
            
                 }     
               } 
               
               if(1==1){
                int tested=0;
                int positive=0;
                int negative=0;
          // String getstat="select sum(positive) as positive ,sum(negative) as negative from   tb_stat_art WHERE "+tbstatduration+" and  SubPartnerID='"+tbstat.get(a)+"'";
          
         
                if(conn.rs.getInt("HTC")==1 || conn.rs.getInt("PMTCT")==1)
                {
                /**
            * commented following a request from komen on 17th October 2016
                
              positive=conn.rs.getInt("positive");
              negative=conn.rs.getInt("negative");
              tested=negative+positive;
              * */
                }
           
                //tested
                HSSFCell clx = rwx.createCell(colpos);
               if(!reportDuration.equals("4")){  clx.setCellValue(tested);}
                else { clx.setCellValue(0); }
                clx.setCellStyle(style2);               
                colpos++;
                //positive
                   HSSFCell clx1 = rwx.createCell(colpos);   
                if(!reportDuration.equals("4")){  clx1.setCellValue(positive); }
                else { clx1.setCellValue(0); }
                clx1.setCellStyle(style2);               
                colpos++;
                
                //negative
                HSSFCell clx2 = rwx.createCell(colpos);
                if(!reportDuration.equals("4")){   clx2.setCellValue(negative); }
                else {clx2.setCellValue(0); }
                clx2.setCellStyle(style2);  colpos++; }//end of tbstat if query

               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=23;c<sectionheaders.length;c++){     
                HSSFCell clx = rwx.createCell(colpos);
                clx.setCellValue(0);
                clx.setCellStyle(style2);               
            colpos++;
           // conpos++;
                 }     
               } 
 
           }//end of if query
               
 
                }//end of if 1==1
                
    
                System.out.println("____"+tbstat.get(a));    
            
                rowpos++;
                
            }//end of for loop
            
          
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
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(style2);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(style2);
   
        }
   
else if(z==5){
  //dsdta
   HSSFCell celldsd=rwx.createCell(5); 
   celldsd.setCellValue(new Integer(staticart_hv.get(a).toString()));
   celldsd.setCellStyle(style2);
   
        }   
    else if(z==6){
  //dsdta
   HSSFCell celldsd=rwx.createCell(6); 
   celldsd.setCellValue(new Integer(statichtc_hv.get(a).toString()));
   celldsd.setCellStyle(style2);
   
        }
    else if(z==7){
  //dsdta
   HSSFCell celldsd=rwx.createCell(7); 
   celldsd.setCellValue(new Integer(staticpmtct_hv.get(a).toString()));
   celldsd.setCellStyle(style2);
   
        }
  
		 else if(z==blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue(0);
   celldsd.setCellStyle(style2); 
        }
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(style2);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	
  //____autofilter______       
    shet.setAutoFilter(new CellRangeAddress(2, rowpos - 1, 0, sectionheaders.length));

    //System.out.println("1,"+rowpos+",0,"+colposcopy);
                   for (int e = 0; e < 5; e++) {
                            shet.autoSizeColumn(e);
                        }
                   for (int e = 8; e < sectionheaders.length; e++) {
                            shet.autoSizeColumn(e);
                        }
             //Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(5,3);
            
            
            
            
            
            
 }
            
 public void VMMC(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
   //new htc for PITC 
  String facilitiestable="subpartnera";
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
            
  
            
            
            
            
    //get the existing data for the month, year and facility that is already on session
    
    String month="";      
    String year="";      
    String facil="361";
  
    String form="vmmc_new";
      
   

//=====================================================================================================
    
    year="2015";
     month="5";
    String county="";
   
    String header="";
    
  String  reportType="";
    if(request.getParameter("reportType")!=null){
      reportType=request.getParameter("reportType");
    }
    String  reportDuration="";
    if(request.getParameter("reportDuration")!=null){
    reportDuration=request.getParameter("reportDuration");
    }
    
    if(request.getParameter("year")!=null){
    year=request.getParameter("year");
    }
    
     if(request.getParameter("facility")!=null && reportType.equals("2")){
    facil=request.getParameter("facility");
    
    String getfacil="select SubPartnerNom,CentreSanteId as mflcode from subpartnera where SubPartnerID='"+facil+"'   AND "+facilitiestable+".active=1  ";
    conn.rs=conn.st.executeQuery(getfacil);
    
    while(conn.rs.next()){
    
    header+=" FACILITY : "+conn.rs.getString(1).toUpperCase()+"     MFL CODE  :  "+conn.rs.getString(2)+"  ";
    
    }
    
    
    
    }
    
    if(request.getParameter("county")!=null && reportType.equals("2")){
    county=request.getParameter("county");
    
    
    String getcounty="select County from county where CountyID='"+county+"'";
    conn.rs=conn.st.executeQuery(getcounty);
    
    while(conn.rs.next()){
    
    header+=" COUNTY : "+conn.rs.getString(1).toUpperCase()+" ";
    
    }
    
    }
    
    if(request.getParameter("month")!=null && reportDuration.equals("4")){
    month=request.getParameter("month");
    
    
      String getmonth="select name as monthname from month where id='"+month+"'";
    conn.rs=conn.st.executeQuery(getmonth);
    
    while(conn.rs.next()){
    
    header+=" MONTH : "+conn.rs.getString(1).toUpperCase()+"";
    
    }
    
    
    }
    
     
    header+=" YEAR : "+year+"";
     
     
     
     
     
   String facilitywhere="";
   String yearwhere="";
   String monthwhere="";
   String countywhere="";
   String duration="";
   String semi_annual="";
   String quarter="";
 
   //==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
  
  
       int  yearcopy=Integer.parseInt(year);
      
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        String yearmonth=""+year;
       int prevYear=yearcopy-1; 
       int maxYearMonth=0;
       int monthcopy=0; 
//        GET REPORT DURATION============================================

        if(reportDuration.equals("1")){
            yearmonth="Annual Report For "+year;
         duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
            yearmonth="Semi Annual Report For "+prevYear+" Oct to "+year+" Mar";
       duration=" "+form+".yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
       }
           else{
            yearmonth="Semi Annual Report for Apr to  Sep "+year;
       duration=" "+form+".yearmonth BETWEEN "+year+"04 AND "+year+"09";      
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
      duration=" "+form+".yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
 yearmonth="Quarterly Report For "+prevYear+" "+conn.rs.getString(2);
      }
      else{
           yearmonth="Quarterly Report For "+year+" ("+conn.rs.getString(2)+")";
     duration=" "+form+".yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
                              }
        }  
        
      else if(reportDuration.equals("4")){
     monthcopy=Integer.parseInt(request.getParameter("month"));
   
     
//     month=5;
     if(monthcopy>=10){
        
           yearmonth="Monthly Report For "+prevYear+"_("+month+")";
     duration=" "+form+".yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" "+form+".yearmonth="+year+"0"+month;  
    yearmonth="Monthly Report For "+year+"_("+month+")";
     }
      }
      else {
     duration="";     
           }
        
	//======================================================================

//==================================================================================================
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
   
   
   
   String subcountywhere=" 1=1 ";
   
   String subcounty="";
   
   if(!request.getParameter("subcounty").equals("")){
   
       subcounty=request.getParameter("subcounty");
        
                                                    }
   
   
   
   
   
   
   String getexistingdata="";
   
   
    if(!county.equals("")){
   
   countywhere=" and district.countyid = '"+county+"'";    
   subcounty_countywhere=" (district.countyid='"+county+"') and ";//20160711
                          }
    
     if(!subcounty.equals("")){
   
   subcountywhere+=" and subpartnera.DistrictID = '"+subcounty+"'";    
   subcounty_countywhere=" (district.DistrictID='"+subcounty+"') and ";//20160711
                              }
   
   if(!facil.equals("")){
   
   facilitywhere=" and "+form+".SubPartnerID = '"+facil+"'   AND "+facilitiestable+".active=1 ";    
   
                        } 
   
    
    
 String joinedwhwere=" where 1=1 "+yearwhere+" && "+duration+" "+countywhere+" AND "+subcountywhere;  
    
    
    
//=====================================================================================================    
//FINDFACILITIES 
 
   
        
//=====================================================================================================    
    
//______________________________________________________________________________________
  //                       NOW CREATE THE WORKSHEETS          
  //______________________________________________________________________________________  

   HSSFSheet shet=wb.createSheet(form.toUpperCase());   
     
    //create headers for that worksheet
      
      HSSFRow rw=shet.createRow(0);
      rw.setHeightInPoints(25);
 HSSFCell cl0= rw.createCell(0);
 cl0.setCellValue("DATIM "+yearmonth);
 cl0.setCellStyle(stylex);
    
 for(int a=1;a<=13;a++){ 
 HSSFCell clx= rw.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                     }
 
 HSSFRow rw1=shet.createRow(1);
 rw1.setHeightInPoints(23);
 HSSFCell cl= rw1.createCell(0);
 cl.setCellValue("");
 cl.setCellStyle(style2);
 
  for(int a=1;a<=13;a++){ 
 HSSFCell clx= rw1.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(style2);
                       }
  
HSSFRow rw2=shet.createRow(2);
rw2.setHeightInPoints(23);
HSSFCell cl3= rw2.createCell(0);
cl3.setCellValue("VMMC CIRC ");
cl3.setCellStyle(stylex);
     
  HSSFCell cl31= rw2.createCell(1);
 cl31.setCellValue("");
 cl31.setCellStyle(stylex);
 

  for(int a=2;a<=5;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                       }
  
  HSSFCell cl3b= rw2.createCell(6);
cl3b.setCellValue("Disaggregated by Age ");
cl3b.setCellStyle(stylex); 

   for(int a=6;a<=15;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                         }

     HSSFCell cl3c= rw2.createCell(16);
cl3c.setCellValue("Disaggregated by HIV Status");
cl3c.setCellStyle(stylex); 

   for(int a=17;a<=18;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
   

    HSSFCell cl3d= rw2.createCell(19);
cl3d.setCellValue("Disaggregated by Circumcission Technique ");
cl3d.setCellStyle(stylex); 
 
  HSSFCell cl3e= rw2.createCell(21);
cl3e.setCellValue("Disaggregated by Followup (For surgical only) ");
cl3e.setCellStyle(stylex); 

   for(int a=22;a<23;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
   
  HSSFCell cl3e1= rw2.createCell(23);
cl3e1.setCellValue("Disaggregated by Followup (For Device Based only) ");
cl3e1.setCellStyle(stylex); 

   for(int a=24;a<25;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
   
HSSFCell cl3f= rw2.createCell(25);
cl3f.setCellValue("VMMC_AE Disaggregated by AE Type");
cl3f.setCellStyle(stylex); 
   
 for(int a=26;a<42;a++){ 
 HSSFCell clx= rw2.createCell(a);
 clx.setCellValue("");
 clx.setCellStyle(stylex);
                          }
 
   HSSFCell cl3f1= rw2.createCell(30);
cl3f1.setCellValue("VMMC_Services");
cl3f1.setCellStyle(stylex);
     //
 //String VMMCheaders[]={"County","Sub-County","Facility Name","Mfl Code","Type Of Support","Numerator","< 1","1-9","10-14","15-19","20-24","25-29","30-49","50+","Number of HIV-positive clients (tested HIV positive at VMMC site)","Number of HIV-negative clients (tested HIV negative at VMMC site)","Number of clients with indeterminate HIV status or  not tested for HIV at site (regardless of previous documentation) ","Device-Based","Follow-up Status within 14 days of surgery","Surgical VMMC","Numerator","Surgical Intra- operative: Moderate","Surgical Intra- operative: Severe","Surgical Post- operative: Moderate","Surgical Post- operative: Severe","Medical Device-related: Moderate","Medical Device-related: Severe","Numerator","<60 days","2Mo-9Yr","10-14","15-19","20-24","25-29","30-49","50+"};
 String VMMCheaders[]={"County","Sub-County","Facility Name","Mfl Code","Type Of Support","Numerator","< 1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","Number of HIV-positive clients (tested HIV positive at VMMC site)","Number of HIV-negative clients (tested HIV negative at VMMC site)","Number of clients with indeterminate HIV status or  not tested for HIV at site (regardless of previous documentation) ","Surgical VMMC","Device-Based VMMC","Returned for postoperative follow-up care within 14 days of surgery","Did not return for postoperative follow-up care within 14 days of surgery","Within 14 days of device placement. May include device removal","NOT within 14 days, or did not follow-up within reporting period","Numerator","During Circumcision Moderate","During Circumcision Severe","Post Circumcision Moderate","Post Circumcision Severe","Numerator","<60 days","2Mo-4Yr","5-9Yr","10-14Yr","15-19Yr","20-24Yr","25-29Yr","30-34Yr","35-39Yr","40-49Yr","50+Yr"};
 
 HSSFRow rw3=shet.createRow(3);
 rw3.setHeightInPoints(63);
 for(int a=0;a<VMMCheaders.length;a++){ 
 HSSFCell clx= rw3.createCell(a);
 clx.setCellValue(VMMCheaders[a]);
 clx.setCellStyle(stylex);
                                      }

  
    //shet.addMergedRegion(new CellRangeAddress(2,10,0,0));  
    shet.addMergedRegion(new CellRangeAddress(0,0,0,15));  
    shet.addMergedRegion(new CellRangeAddress(1,1,0,41));  
  //shet.addMergedRegion(new CellRangeAddress(1,1,0,4));  
    shet.addMergedRegion(new CellRangeAddress(2,2,1,5));  
    shet.addMergedRegion(new CellRangeAddress(2,2,6,15));  
    shet.addMergedRegion(new CellRangeAddress(2,2,16,18));  
    shet.addMergedRegion(new CellRangeAddress(2,2,19,20));  
    shet.addMergedRegion(new CellRangeAddress(2,2,21,22));  
    shet.addMergedRegion(new CellRangeAddress(2,2,23,24));  
    shet.addMergedRegion(new CellRangeAddress(2,2,25,29));  
    shet.addMergedRegion(new CellRangeAddress(2,2,30,41));  
    shet.setColumnWidth(0, 5000);  
    shet.setColumnWidth(1, 5000);  
    shet.setColumnWidth(2, 6000);  
    shet.setColumnWidth(3, 6000);  
    shet.setColumnWidth(4, 6000);  
    shet.setColumnWidth(5, 2500); 
    shet.setColumnWidth(16, 6500);  
    shet.setColumnWidth(17, 6500);  
    shet.setColumnWidth(18, 6500);  
    shet.setColumnWidth(19, 6500);  
    shet.setColumnWidth(20, 6500);  
    shet.setColumnWidth(21, 6500);  
    shet.setColumnWidth(22, 6500);    
    shet.setColumnWidth(23, 6000);  
    shet.setColumnWidth(24, 6000);  
    shet.setColumnWidth(25, 6000);  
    shet.setColumnWidth(26, 6000);
    shet.setColumnWidth(27, 6000);  
    shet.setColumnWidth(28, 6000);
    shet.setColumnWidth(29, 6000);  
    shet.setColumnWidth(30, 6000);
    
     

          //BEFORE WHILE LOOP
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    
    ArrayList staticfacility= new ArrayList();
    ArrayList staticcounty= new ArrayList();
    ArrayList staticdistrict= new ArrayList();
    ArrayList staticmfl= new ArrayList();
    ArrayList staticdsd_ta= new ArrayList();
    int blankrows=42;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " subpartnera.SubPartnerNom as facility, subpartnera.CentreSanteId as mflcode, subpartnera.HTC_Support1 as htcsupport "
           + " FROM    subpartnera join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = subpartnera.DistrictID    where "+subcountywhere+" AND ( VMMC='1')  AND "+facilitiestable+".active=1  group by subpartnera.SubPartnerID   "; 
   
   System.out.println("query::::"+getstaticfacilities);
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
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% 
    
        
//getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode , sum(P51DT) as P51DT, sum(P51D1) as P51D1,   sum(P51D9) as P51D9,   sum(P51D10) as P51D10,   sum(P51D19) as P51D19,sum(P51D24) as P51D24, sum(P51D29) as P51D29,  sum(P51D49) as  P51D49,   sum(P51D50) as P51D50,    sum(P51DT) as P51DT,   sum(P521DM) as  P521DM,    sum(P521DS) as P521DS,   sum(P521DT) as P521DT,   sum(P522DM) as P522DM,    sum(P522DS) as P522DS,    sum(P522DT) as P522DT,   sum(P52DM) as  P52DM,   sum(P52DS) as P52DS,    sum(P52DT) as P52DT,   sum(P511KP) as P511KP,   sum(P511KN) as P511KN,   sum(P511KU) as P511KU,   sum(P511Surg) as P511Surg,   sum(P511Dev) as P511Dev,   sum(P53DF) as P53DF,    sum(P53DO) as P53DO,   sum(P53DM) as P53DM,    sum(P53D) as P53D,   sum(P54D) as P54D  from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID  ";
getexistingdata="select county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode , sum(v1_total) as v1_total, sum(v1_60d) as v1_60d,  sum(v1_4y) as v1_4y,   sum(v1_9y) as v1_9y,   sum(v1_14y) as v1_14y,   sum(v1_19y) as v1_19y,sum(v1_24y) as v1_24y, sum(v1_29y) as v1_29y, sum(v1_34y) as v1_34y, sum(v1_39y) as v1_39y,  sum(v1_49y) as  v1_49y,   sum(v1_50y) as v1_50y,"
        + " sum(v2_dc_m_total) AS v2_dc_m_total,sum(v2_dc_s_total) as v2_dc_s_total, sum(v2_pc_m_total) AS v2_pc_m_total, sum(v2_pc_s_total) AS v2_pc_s_total,  "
        + " sum(v3_tp_total) AS v3_tp_total, sum(v3_srp_total) as v3_srp_total , sum(v3_tn_total) as v3_tn_total, sum(v3_nt_total) as v3_nt_total ,   sum(v3_us_total) as v3_us_total ,   sum(v3_srn_total) as v3_srn_total ,   sum(v4_s_vmmc_total) as v4_s_vmmc_total , sum(v4_db_vmmc_total) AS v4_db_vmmc_total, sum(v5_followup_total) as v5_followup_total , sum(v6_nofollowup_total) as v6_nofollowup_total,"
        + "SUM(0) AS db_followup,SUM(0) AS db_no_followup from "+form+" join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on "+form+".SubPartnerID = subpartnera.SubPartnerID   "+joinedwhwere+" group by subpartnera.SubPartnerID  ";

            System.out.println("VMMV_____"+getexistingdata);
            String v1_60d = "";
            String v1_4y = "";
            String v1_9y = "";
            String v1_14y = "";
            String v1_19y = "";
            String v1_24y = "";
            String v1_29y = "";
            String v1_34y = "";
            String v1_39y = "";
            String v1_49y = "";
            String v1_50y = "";
            String v1_total = "";
            
            String v2_dc_m_total="";
            String v2_dc_s_total="";
            String v2_pc_m_total="";
            String v2_pc_s_total="";
            String tp = "";
            String srp = "";
            String tn = "";
            String not_tested = "";
            String unknown_status = "";
            String srn = "";
            String surgical_vmmc="";
            String device_based_vmmc="";
            String surgical_follow_up="";
            String surgical_no_follow_up="";
            String db_follow_up="";
            String db_no_follow_up="";

            String distid="";




int counter=0;
 
 
 
 
 
    
    conn.rs=conn.st.executeQuery(getexistingdata);
    
    int r=4;
    while(conn.rs.next()){
   //now check if form was updated and if its one month after data entry
//now load the column values here
        
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
        
                         }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   
        
        
                v1_60d = conn.rs.getString("v1_60d");
                if (v1_60d == null) {
                    v1_60d = "0";
                }
                
                v1_4y = conn.rs.getString("v1_4y");
                if (v1_4y == null) {
                    v1_4y = "0";
                }

                v1_9y = conn.rs.getString("v1_9y");
                if (v1_9y == null) {
                    v1_9y = "0";
                }

                v1_14y = conn.rs.getString("v1_14y");
                if (v1_14y == null) {
                    v1_14y = "0";
                }

                v1_19y = conn.rs.getString("v1_19y");
                if (v1_19y == null) {
                    v1_19y = "0";
                }

                v1_24y = conn.rs.getString("v1_24y");
                if (v1_24y == null) {
                    v1_24y = "0";
                }

                 v1_29y = conn.rs.getString("v1_29y");
                if (v1_29y == null) {
                    v1_29y = "0";
                }

                 v1_34y = conn.rs.getString("v1_34y");
                if (v1_34y == null) {
                    v1_34y = "0";
                }

                 v1_39y = conn.rs.getString("v1_39y");
                if (v1_39y == null) {
                    v1_39y = "0";
                }
                
                v1_49y = conn.rs.getString("v1_49y");
                if (v1_49y == null) {
                    v1_49y = "0";
                }

                v1_50y = conn.rs.getString("v1_50y");
                if (v1_50y == null) {
                    v1_50y = "0";
                }

                v1_total = conn.rs.getString("v1_total");
                if (v1_total == null) {
                    v1_total = "0";
                }
                
                v2_dc_m_total = conn.rs.getString("v2_dc_m_total");
                if (v2_dc_m_total == null) {
                    v2_dc_m_total = "0";
                }
                v2_dc_s_total = conn.rs.getString("v2_dc_s_total");
                if (v2_dc_s_total == null) {
                    v2_dc_s_total = "0";
                }
                v2_pc_m_total = conn.rs.getString("v2_pc_m_total");
                if (v2_pc_m_total == null) {
                    v2_pc_m_total = "0";
                }
                v2_pc_s_total = conn.rs.getString("v2_pc_s_total");
                if (v2_pc_s_total == null) {
                    v2_pc_s_total = "0";
                }

                
                
                tp = conn.rs.getString("v3_tp_total");
                if (tp == null) {
                    tp = "0";
                }
//
                srp = conn.rs.getString("v3_srp_total");
                if (srp == null) {
                    srp = "0";
                }
//
                tn = conn.rs.getString("v3_tn_total");
                if (tn == null) {
                    tn = "0";
                }

                not_tested = conn.rs.getString("v3_nt_total");
                if (not_tested == null) {
                    not_tested = "0";
                }
//
                unknown_status = conn.rs.getString("v3_us_total");
                if (unknown_status == null) {
                    unknown_status = "0";
                }
//
                srn = conn.rs.getString("v3_srn_total");
                if (srn == null) {
                    srn = "0";
                }

                surgical_vmmc = conn.rs.getString("v4_s_vmmc_total");
                if (surgical_vmmc == null) {
                    surgical_vmmc = "0";
                }
//
//
                device_based_vmmc = conn.rs.getString("v4_db_vmmc_total");
                if (device_based_vmmc == null) {
                    device_based_vmmc = "0";
                }

                surgical_follow_up = conn.rs.getString("v5_followup_total");
                if (surgical_follow_up == null) {
                    surgical_follow_up = "0";
                }
//
                surgical_no_follow_up = conn.rs.getString("v6_nofollowup_total");
                if (surgical_no_follow_up == null) {
                    surgical_no_follow_up = "0";
                }
                
                db_follow_up = conn.rs.getString("db_followup");
                if (db_follow_up == null) {
                    db_follow_up = "0";
                }
//
                db_no_follow_up = conn.rs.getString("db_no_followup");
                if (db_no_follow_up == null) {
                    db_no_follow_up = "0";
                }

        
       
    
   
    
    
if(1==1){ 
    
 
    
    
  if(1==1){
      
      int celpos=0;
      int celpos1=1;
      
      
   HSSFRow rwx=shet.createRow(r);
     rwx.setHeightInPoints(23);

     //County
 HSSFCell clx0= rwx.createCell(celpos);
 clx0.setCellValue(conn.rs.getString(celpos1));
 clx0.setCellStyle(style2);
 celpos++;
 celpos1++;
 
  //SubCounty   
  HSSFCell clx= rwx.createCell(celpos);
  clx.setCellValue(conn.rs.getString(celpos1).substring(0,1).toUpperCase()+conn.rs.getString(celpos1).substring(1).toLowerCase());
  clx.setCellStyle(style2);
  celpos++;
 celpos1++;
  //Facility Name   
  if(1==1){
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getString(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
          }
  
  //Mfl Code
  if(1==1)
  {  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(conn.rs.getInt(celpos1));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
 
  //DSD/TA
  
  //im assuming that VMMC is made of DSDs as indicated in the Datim PDF screen shared by Supervisor
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue("DSD");
  clx1.setCellStyle(style2);
  celpos++;

  } 
  
  
  //Numerator
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_total));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  
   //<1
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_60d));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
   //1-9
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_4y)+new Integer(v1_9y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  
   //10-14
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_14y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //15-19
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_19y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
   //20-24
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_24y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //25-29
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_29y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //30-34
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_34y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //35-39
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_39y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
   
  
   //40-49
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_49y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
        }
  
  
   //50+
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_50y));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  //tested positive clients  P511KP
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(tp));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  
  //tested negative clients P511KN
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(tn));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
  }
  
  
  //unknown untested  
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(not_tested));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  
  //=============================================================
  //surgical based  
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(surgical_vmmc));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  //=============================================================
  //device based  
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(device_based_vmmc));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  //=============================================================
  //Returned Surgical
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(surgical_follow_up));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  if(1==1){ 
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(surgical_no_follow_up));
  clx1.setCellStyle(style2);
  //increment to get past P511Surg
  celpos++;
 celpos1++;
         }
  //Returned Device Based
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(db_follow_up));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  if(1==1){ 
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(db_no_follow_up));
  clx1.setCellStyle(style2);
  //increment to get past P511Surg
  celpos++;
 celpos1++;
         }

          int vmmc_ae=  new Integer(v2_dc_m_total)+new Integer(v2_dc_s_total)+new Integer(v2_pc_m_total)+new Integer(v2_pc_s_total);
  //=============================================================
  //VMMC_AE Numerator
  if(1==1){ 
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(vmmc_ae);
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
  //intra-operative moderate
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v2_dc_m_total));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
    //intra-operative severe
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v2_dc_s_total));
  clx1.setCellStyle(style2);
  celpos++;
 celpos1++;
         }
    
    
      // surgicalpost-operative moderate
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v2_pc_m_total));
  clx1.setCellStyle(style2);
  celpos++;
  celpos1++;
         }
    
     // surgicalpost-operative severe
    if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v2_pc_s_total));
  clx1.setCellStyle(style2);
  celpos++;
  celpos1++;
         }

    
  
  //____________________________________________________________________________________new worksheet added 2017_____________________________
 //_____numerator
   //celpos++;
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_total));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
  
  //<60 days
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_60d));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
  //2Mo-4yr
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_4y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  //5y-9yr
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_9y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
  
   //10-14
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_14y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
   //15-19
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_19y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
  
   //20-24
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_24y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  //25-29
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_29y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  
   //30-34
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_34y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
   //34-39
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_39y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
   //40-49
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_49y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
  }
  
  //50
  if(1==1){  
  HSSFCell clx1= rwx.createCell(celpos);
  clx1.setCellValue(new Integer(v1_50y));
  clx1.setCellStyle(style2);
  celpos++;
 //celpos1++;
          }

  r++;
  }
  
  
  //=====================================================================================
 
    }
    
    }
    
    
     

  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
     
  rwx=shet.createRow(r);  
 rwx.setHeightInPoints(23);  
 r++;
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
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(style2);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(style2);
   
        }
		 else if(z==blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue(0);
   celldsd.setCellStyle(style2);
   
        }
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(style2);
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
	       
            
            //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

    //System.out.println("1,"+rowpos+",0,"+colposcopy);
                        for (int e = 0; e < 4; e++) {
                            shet.autoSizeColumn(e);
                        }
             //Made my life veery simple...
                shet.setDisplayGridlines(false);
                shet.createFreezePane(5,5); 
            
            
                    
            
 }   
			public HashMap Ipd(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
   //new htc for PITC 
 
   
    HashMap<String, String> ipdhm = new HashMap<String, String>();
   
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
            
            
            
            
            
            
             //new htc for PITC 
                      isIPD=true;
                      isOPD=false;
                      isVCT=false;
                     
                     
                     String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + PMTCT ANC, L&D,PNC)","Total positive","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + PMTCT ANC, L&D,PNC)","Total positive","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + PMTCT ANC, L&D,PNC)","Total positive","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                    
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
           subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    
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
         
         subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1  and  ";//20160711
         
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
        
        else if (reportDuration.equals("3")) 
        {
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
        
      HSSFSheet shet3=wb.createSheet("PITC IPD");   
            HSSFCell  c11;
            
            
   String county="";
   String  district="";
    String facilityname="";

  shet3.setColumnWidth(0, 4000);  
  shet3.setColumnWidth(1, 5000);  
  shet3.setColumnWidth(2,5000);  
  //shet3.setColumnWidth(6,5000);

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
    String mergeinfor[]={"0,0,0,"+pitc_ipd_header1.length+"","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,4,6,6","2,2,7,30","2,2,31,54","2,4,55,55","2,4,56,56","2,4,57,57","2,4,41,41","3,3,7,8","3,3,31,32","2,4,58,58","2,4,59,59","2,4,60,60"};  
   
    for(int d=0;d<mergeinfor.length;d++){
    if(!mergeinfor[d].equals("")){
        String pos[]=mergeinfor[d].split(",");
     shet3.addMergedRegion(new CellRangeAddress(new Integer(pos[0]),new Integer(pos[1]),new Integer(pos[2]),new Integer(pos[3])));   
    }
                                         }
    

    double checkdiff=0;
    int count=4;

 
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
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport, IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT"
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 OR PMTCT=1 )   AND "+facilitiestable+".active=1 group by "+facilitiestable+".SubPartnerID   "; 
    
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
   
                String countyidcopy="0",countyid="0";

    double Tm=0,Tf=0; //Tested male, Tested female

    //by gender by age breakdown variables
    double Tmc=0,Tma=0,Tfc=0,Tfa=0; //Tested male children, Tested male adult, tested female children, tested female adult. 
    //Tested and Positive fine age	 
    double Tf1=0,Tm1=0,Pf1=0,Pm1=0;
    double Tf4=0,Tm4=0,Pf4=0,Pm4=0; //may or may not be there
    double Tf9=0,Tm9=0,Pf9=0,Pm9=0;
    double Tf14=0,Tm14=0,Pf14=0,Pm14=0,PTf14=0,PPf14=0;
    double Tf19=0,Tm19=0,Pf19=0,Pm19=0,PTf19=0,PPf19=0;
    double Tf24=0,Tm24=0,Pf24=0,Pm24=0,PTf24=0,PPf24=0;
    double Tf29=0,Tm29=0,Pf29=0,Pm29=0,PTf29=0,PPf29=0;
    double Tf34=0,Tm34=0,Pf34=0,Pm34=0,PTf34=0,PPf34=0;
    double Tf39=0,Tm39=0,Pf39=0,Pm39=0,PTf39=0,PPf39=0;
    double Tf49=0,Tm49=0,Pf49=0,Pm49=0,PTf49=0,PPf49=0;
    double Tf50=0,Tm50=0,Pf50=0,Pm50=0,PTf50=0,PPf50=0;
          
          
    String get731data="SELECT "
            + " sum(IFNULL(HV0103,0)) as 711_totaltested, "
            + " sum(IFNULL(HV0110,0)) as 711_less15m ,"
            + " sum(IFNULL(HV0111,0)) as 711_less15f ,"
            + " sum(IFNULL(HV0112,0)) as 711_15_24m ,"
            + " sum(IFNULL(HV0113,0)) as 711_15_24f ,"
            + " sum(IFNULL(HV0114,0)) as 711_25m ,"
            + " sum(IFNULL(HV0115,0)) as 711_25f ,"
            + " sum(IFNULL(HV0110,0)+IFNULL(HV0111,0)+IFNULL(HV0112,0)+IFNULL(HV0113,0)+IFNULL(HV0114,0)+IFNULL(HV0115,0)) as 711_totalpositive ," //updated in 201607            
            + " county.County,district.DistrictNom,"
            + ""+facilitiestable+".SubPartnerNom,"+facilitiestable+".CentreSanteId,"+facilitiestable+".HTC_Support1 ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
           +" ,sum( IFNULL(HV0103,0) + IFNULL(HV0201,0) + IFNULL(0,0) + IFNULL(HV0202,0) + IFNULL(HV0203,0) ) as NUM,  "+facilitiestable+".SubPartnerID ,  IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT "//new numerator for 2017 Q1 is serology + anc only + htc 
            + " ,county.CountyID as CountyID,sum(IFNULL(HV0110,0)+IFNULL(HV0111,0)+IFNULL(HV0112,0)+IFNULL(HV0113,0)+IFNULL(HV0114,0)+IFNULL(HV0115,0)+IFNULL(HV0206,0)+IFNULL(HV0207,0)+IFNULL(HV0208,0)) as grandtotalpositive "
            +" ,sum(IFNULL(HV0202,0)) as ld_tes , sum(IFNULL(HV0207,0)) as ld_pos "
           
            + " FROM moh731 JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + " district.CountyID=county.CountyID "
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && ("+facilitiestable+".HTC=1 || PMTCT=1  )  AND "+facilitiestable+".active=1  "
            + "GROUP BY moh731.SubPartnerID order by county.County" ;
 
     System.out.println("IPD QRY : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
    int tala=0;
    while(conn.rs.next())  {
    
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
//by gender breakdown variables
    
    //master county changes. if the countycopy is different from the previous version, then fetch all variables for the current county
    
     countyid=conn.rs.getString("CountyID");
	 if(!countyid.equals(countyidcopy)){
         //change countyidcopy to be same as countyid
         countyidcopy=countyid;
         //now fetch the variables from proportions table
         
         
         String getratios=" select * from htc_proportion where isactive='1' and county_id='"+countyid+"'";
         conn.rs4=conn.st4.executeQuery(getratios);
         while(conn.rs4.next()){
         String indicator=conn.rs4.getString("indicator");
           
           if(indicator.equals("tested by gender")){
                 
           Tm=conn.rs4.getDouble("male_or_child");
           Tf =conn.rs4.getDouble("female_or_adult");    
             }
             
             
            if(indicator.equals("tested by broad age female")){
             Tfc=conn.rs4.getDouble("male_or_child");
             Tfa=conn.rs4.getDouble("female_or_adult");
             } 
            
             if(indicator.equals("tested by broad age male"))
             {
             Tmc=conn.rs4.getDouble("male_or_child");
             Tma=conn.rs4.getDouble("female_or_adult");
             }
            
             
             if(indicator.equals("tested by fine age")){
             
    Tf1=conn.rs4.getDouble("f_1"); Tm1=conn.rs4.getDouble("m_1");
    Tf4=conn.rs4.getDouble("f_4");Tm4=conn.rs4.getDouble("m_4");;
    Tf9=conn.rs4.getDouble("f_9");Tm9=conn.rs4.getDouble("m_9");
    Tf14=conn.rs4.getDouble("f_14");Tm14=conn.rs4.getDouble("m_14");
    Tf19=conn.rs4.getDouble("f_19");Tm19=conn.rs4.getDouble("m_19");
    Tf24=conn.rs4.getDouble("f_24");Tm24=conn.rs4.getDouble("m_24");
    Tf29=conn.rs4.getDouble("f_29");Tm29=conn.rs4.getDouble("m_29");
    Tf34=conn.rs4.getDouble("f_34");Tm34=conn.rs4.getDouble("m_34");
    Tf39=conn.rs4.getDouble("f_39");Tm39=conn.rs4.getDouble("m_39");
    Tf49=conn.rs4.getDouble("f_49");Tm49=conn.rs4.getDouble("m_49");
    Tf50=conn.rs4.getDouble("f_50");Tm50=conn.rs4.getDouble("m_50");              
                                                          } 
         if(indicator.equals("positive by fine age")){
             
                 
   Pf1=conn.rs4.getDouble("f_1");Pm1=conn.rs4.getDouble("m_1");
   Pf4=conn.rs4.getDouble("f_4");Pm4=conn.rs4.getDouble("m_4");
   Pf9=conn.rs4.getDouble("f_9");Pm9=conn.rs4.getDouble("m_9");
   Pf14=conn.rs4.getDouble("f_14");Pm14=conn.rs4.getDouble("m_14");
   Pf19=conn.rs4.getDouble("f_19");Pm19=conn.rs4.getDouble("m_19");
   Pf24=conn.rs4.getDouble("f_24");Pm24=conn.rs4.getDouble("m_24");
   Pf29=conn.rs4.getDouble("f_29");Pm29=conn.rs4.getDouble("m_29");
   Pf34=conn.rs4.getDouble("f_34");Pm34=conn.rs4.getDouble("m_34");
   Pf39=conn.rs4.getDouble("f_39");Pm39=conn.rs4.getDouble("m_39");
   Pf49=conn.rs4.getDouble("f_49");Pm49=conn.rs4.getDouble("m_49");
   Pf50=conn.rs4.getDouble("f_50");Pm50=conn.rs4.getDouble("m_50");
   
             }
         
         
  if(indicator.equals("PMTCT_ANC")){
    //use same proportion
    PTf14=conn.rs4.getDouble("f_14");
    PTf19=conn.rs4.getDouble("f_19");
    PTf24=conn.rs4.getDouble("f_24");
    PTf29=conn.rs4.getDouble("f_29");
    PTf34=conn.rs4.getDouble("f_34");
    PTf39=conn.rs4.getDouble("f_39");
    PTf49=conn.rs4.getDouble("f_49");
    PTf50=conn.rs4.getDouble("f_50");              
                                    //}
            
  // if(indicator.equals("PMTCT_New_Postive")){
                
   PPf14=conn.rs4.getDouble("f_14");
   PPf19=conn.rs4.getDouble("f_19");
   PPf24=conn.rs4.getDouble("f_24");
   PPf29=conn.rs4.getDouble("f_29");
   PPf34=conn.rs4.getDouble("f_34");
   PPf39=conn.rs4.getDouble("f_39");
   PPf49=conn.rs4.getDouble("f_49");
   PPf50=conn.rs4.getDouble("f_50");   }
         
         
         
         
         
                 
             }//end of while for fetching county ratios 
             
         }//end of countyid if
  
         
         double pmtct_t=0;
         double pmtct_t_14=0;
         double pmtct_t_19=0;
         double pmtct_t_24=0;
         double pmtct_t_29=0;
         double pmtct_t_34=0;
         double pmtct_t_39=0;
         double pmtct_t_49=0;
         double pmtct_t_50=0;
         
         double pmtct_p=0;
         double pmtct_p_14=0;
         double pmtct_p_19=0;
         double pmtct_p_24=0;
         double pmtct_p_29=0;
         double pmtct_p_34=0;
         double pmtct_p_39=0;
         double pmtct_p_49=0;
         double pmtct_p_50=0;
         
              int less15m=0;
              int less15f=0;
              int gret15m=0;
              int gret15f=0;
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

//f_29	f_34	f_39
//_______new age brackets added in 201710_______
double FemaleAdultTested29=0;
double FemaleAdultTested34=0;
double FemaleAdultTested39=0;

double FemaleAdultTested49=0;
double FemaleAdultTested50=0;

double FemaleTestedChild1=0;
double FemaleTestedChild4=0;
double FemaleTestedChild9=0;
double FemaleTestedChild14=0;

double AdultFemaleHIV19Neg=0;
double AdultFemaleHIV24Neg=0;

double AdultFemaleHIV29Neg=0;
double AdultFemaleHIV34Neg=0;
double AdultFemaleHIV39Neg=0;

double AdultFemaleHIV49Neg=0;
double AdultFemaleHIV50Neg=0;

double AdultFemaleHIV19=0;
double AdultFemaleHIV24=0;


double AdultFemaleHIV29=0;
double AdultFemaleHIV34=0;
double AdultFemaleHIV39=0;

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
 
 double  MaleAdultTested29=0;
 double  MaleAdultTested34=0;
 double  MaleAdultTested39=0;
 
 
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
  
  double AdultMaleHIV29Neg=0;
  double AdultMaleHIV34Neg=0;
  double AdultMaleHIV39Neg=0;
  
  double AdultMaleHIV49Neg=0;
  double AdultMaleHIV50Neg=0;
  
  double AdultMaleHIV19=0;
 double  AdultMaleHIV24=0;
 
 double  AdultMaleHIV29=0;
 double  AdultMaleHIV34=0;
 double  AdultMaleHIV39=0;
 
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
    
    //---------------------------------------------------------------------------
       
       county=conn.rs.getString(9);
     district=conn.rs.getString(10);
     district=district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase();
     facilityname=conn.rs.getString(11);
     mflcode=conn.rs.getInt(12); 
     if(conn.rs.getString(13)==null){
         
     dsdta="DSD";
     }
     else if(conn.rs.getString(13).equals("")){
         
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
      
       //____Discard the suggestion below. it was disregarded_____
       //NOTE ..TB TESTED DATA WILL NOW BE 1 % OF OPD DATA
       // TB POS will be 33% of the tested, if only there is some positive data in the opd section..
       //note, this will only happen on the art sites, otherwise the opd data will not be edited
	 
	
      //do a query that calculates the sites supporting inpatient and outpatient for the last six months
      //since we are already in a loop, see if the current site calculates 
       //String issiteipd=" select sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot from moh711 where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ( yearmonth between '201510' and '201603') ";
      String issiteipd=" select sum(IPD) as DTCB_Test_In_Tot from "+facilitiestable+" where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'  AND "+facilitiestable+".active=1  ";
          
       System.out.println("%%"+issiteipd);
                  
        conn.rs1=conn.st1.executeQuery(issiteipd);
       if(conn.rs1.next()){
         
       int semiannualinpatient=0;
       
            tala++;  
           //System.out.println(tala+"*"+county+countyName+countyid+"|"+facilityName+facilityname+facilname+"%%%%"+issiteipd);   
       
       
       if(conn.rs1.getString("DTCB_Test_In_Tot")!=null){ semiannualinpatient=conn.rs1.getInt("DTCB_Test_In_Tot");}
       
       
           if(semiannualinpatient>0)
              {
                
       	//Sites with Inpatient services
 	              //OPD	IPD	VCT

//Testing ratios	72%	17%	11%
//Positivity ratios	62%	19%	19%
           //tested
                  //for IPD
                  if(isIPD==true) {
                      
                  htctestedratio=0.17;                      
                  htcpositiveratio=0.19;                      
                      
                                 }
                  
                  else if(isOPD==true){
                      
                  htctestedratio=0.72;                      
                  htcpositiveratio=0.62;                      
                      
                                       }
                  
                  else if(isVCT==true)
                  {
                      
                  htctestedratio=0.11;                      
                  htcpositiveratio=0.19;                      
                      
                  }
          
        /**          
       //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+inpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+inpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
      */ 
 
            }
       else {
           
       //site not supporting inpatient
      //Sites without Inpatient services	
	           //OPD	VCT	 
//HIV Tested	     86%	14%	 
//Tested Positive    85%	15% 
               
               
               if(isIPD==true){
                      
                  htctestedratio=0;                      
                  htcpositiveratio=0;                      
                 }
                  
                  else if(isOPD==true){
                      
                  htctestedratio=0.86;                      
                  htcpositiveratio=0.85;                      
                  }
                  
                  else if(isVCT==true)
                  {
                      
                  htctestedratio=0.14;                      
                  htcpositiveratio=0.15;                      
               }

      /**   
         //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
    */
 } 
   
            }//end of conn
			
			
   //TestedAdultFemale   TestedChildFemale  hivpos_711_15_24f  hivpos_711_25f HIV_ChildFemale
   //TestedAdultMale     TestedChildMale    hivpos_711_15_24m  hivpos_711_25m HIV_ChildMale
     
      //==============================================NEW2017Q1===========================================================

    
    //============================================================================================================================START NEW RATIOS===================================
    
       pmtct_t=(float)Math.round((conn.rs.getInt("ld_tes")));
       pmtct_p=(float)Math.round((conn.rs.getInt("ld_pos")));
    
    
  //%%%%%%%%%%%%%%%%added 201606 %%%%%%%%%%%%%%%%%%%%%% 
     // 38 (M)%  ---	62% (F)

  double tested_new711=(float)Math.round((conn.rs.getInt("711_totaltested")*htctestedratio));
 
  //|__|
        System.out.println(" 2017Q1__"+tested_new711);
  
  double testedmale_711=(float)Math.round((Tm*tested_new711));
  double testedfemale_711=(float)Math.round((Tf*tested_new711));
       
   double tofautimpya=tested_new711-(testedmale_711+testedfemale_711);
  if(tofautimpya!=0){
 
  testedfemale_711+=tofautimpya;
  
  
  }
        //System.out.println("**2016_06_ "+testedmale_711+ "~ "+testedfemale_711+" ~ "+ tested_new711);
        
     //12%      88%
       
    //this will be defined from ratios 
    TestedAdultFemale=(int)Math.round((Tfa*testedfemale_711));  //adult   
    TestedChildFemale=(int)Math.round((Tfc*testedfemale_711)); //child
    
   tofautimpya=testedfemale_711 -(TestedAdultFemale+TestedChildFemale);   
    if(tofautimpya!=0){
  TestedAdultFemale+=tofautimpya;  
                      }
    //  17%  83%   
    
    //TestedAdultMale=conn.rs.getInt(2);
    //TestedChildMale=conn.rs.getInt(6);
 
    TestedAdultMale=(int)Math.round((Tma*testedmale_711));  //adult   
    TestedChildMale=(int)Math.round((Tmc*testedmale_711)); //child
    
     System.out.println("Tracking ratios "+county+" Male adult "+Tma+"  Female:"+Tmc);
    
   tofautimpya=testedmale_711 -(TestedAdultMale+TestedChildMale);   
    if(tofautimpya!=0){
        
  TestedAdultMale+=tofautimpya;  
   }
        
    double hivpos_711_15_24f=(int) Math.round((conn.rs.getInt("711_15_24f")*htcpositiveratio));//|__|
    double hivpos_711_15_24m=(int) Math.round((conn.rs.getInt("711_15_24m")*htcpositiveratio));//|__|
    
    double hivpos_711_25m=(int) Math.round((conn.rs.getInt("711_25m")*htcpositiveratio));//|__|
    double hivpos_711_25f=(int) Math.round((conn.rs.getInt("711_25f")*htcpositiveratio));//|__|
    

    HIV_AdultFemale=(int) Math.round((hivpos_711_15_24f+ hivpos_711_25f));//
    HIV_AdultMale=(int) Math.round((hivpos_711_15_24m+ hivpos_711_25m)); 
    
    HIV_ChildFemale=(int)Math.round( (conn.rs.getInt("711_less15f")*htcpositiveratio)); //|__|
    double hivpos_711_less15f=(int)Math.round( (conn.rs.getInt("711_less15f")*htcpositiveratio)); //|__|
    HIV_ChildMale=(int) Math.round((conn.rs.getInt("711_less15m")*htcpositiveratio));   //|__|
    double hivpos_711_less15m=(int) Math.round((conn.rs.getInt("711_less15m")*htcpositiveratio));   //|__|
    
        System.out.println(" HTC Positive Ratio ni "+htcpositiveratio+" isIPS ="+isIPD+" isOPD="+isOPD+" isVCT="+isVCT);
    
    
    
//        System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+": percentage "+htcpositiveratio +" Males below 15 yrs: "+conn.rs.getInt("711_less15m")+" x "+htcpositiveratio+"="+HIV_ChildMale);
//        System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+": percentage "+htcpositiveratio +" females below 15 yrs: "+conn.rs.getInt("711_less15f")+" x "+htcpositiveratio+"="+HIV_ChildFemale);
//    
//        System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+": percentage "+htcpositiveratio +" Males 15-24: "+conn.rs.getInt("711_15_24m")+" x "+htcpositiveratio+"="+hivpos_711_15_24m);
//        System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+": percentage "+htcpositiveratio +" Males 15-24: "+conn.rs.getInt("711_15_24f")+" x "+htcpositiveratio+"="+hivpos_711_15_24f);
//    

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
  
   
   
   
       
         pmtct_t_14=(float)Math.round((PTf14*pmtct_t));
         pmtct_t_19=(float)Math.round((PTf19*pmtct_t));
         pmtct_t_24=(float)Math.round((PTf24*pmtct_t));
         pmtct_t_29=(float)Math.round((PTf29*pmtct_t));
         pmtct_t_34=(float)Math.round((PTf34*pmtct_t));
         pmtct_t_39=(float)Math.round((PTf39*pmtct_t));
         pmtct_t_49=(float)Math.round((PTf49*pmtct_t));
         pmtct_t_50=(float)Math.round((PTf50*pmtct_t));
         
         
         pmtct_p_14=(float)Math.round((PPf14*pmtct_p));
         pmtct_p_19=(float)Math.round((PPf19*pmtct_p));
         pmtct_p_24=(float)Math.round((PPf24*pmtct_p));
         pmtct_p_29=(float)Math.round((PPf29*pmtct_p));
         pmtct_p_34=(float)Math.round((PPf34*pmtct_p));
         pmtct_p_39=(float)Math.round((PPf39*pmtct_p));
         pmtct_p_49=(float)Math.round((PPf49*pmtct_p));
         pmtct_p_50=(float)Math.round((PPf50*pmtct_p));
         
 
         
         double pmtcttestedyote=pmtct_t_14+pmtct_t_19+pmtct_t_24+pmtct_t_29+pmtct_t_34+pmtct_t_39+pmtct_t_49+pmtct_t_50;
         
        
         double tofautini=pmtct_t-pmtcttestedyote;
         while(tofautini>0){
         pmtct_t_24+=1;
         tofautini--;    
         }
         
         
         while(tofautini<0){
     if(pmtct_t_24>=pmtct_t_29 && pmtct_t_24>0 ){
         pmtct_t_24-=1;
         tofautini++;  
             }
    else  if(pmtct_t_29>=pmtct_t_24 && pmtct_t_29>0){
         pmtct_t_29-=1;
         tofautini++;  
                          }
    else  if(pmtct_t_34>0){
         pmtct_t_34-=1;
         tofautini++;  
                          }
     
    else  if(pmtct_t_39>0){
         pmtct_t_39-=1;
         tofautini++;  
                          }
    else {
         System.out.println(" PMTCT tested balancing refused");
         break;
    }
         }
         
         
         
         
   
   double pmtctposyote=pmtct_p_14+pmtct_p_19+pmtct_p_24+pmtct_p_29+pmtct_p_34+pmtct_p_39+pmtct_p_49+pmtct_p_50;
         tofautini=pmtct_p-pmtctposyote;
         
           System.out.println(""+facilityname+" Positive yote sasa  ni "+pmtct_p+" ya kuongeza ni "+(pmtct_p_14+pmtct_p_19+pmtct_p_24+pmtct_p_29+pmtct_p_34+pmtct_p_39+pmtct_p_49+pmtct_p_50)+" tofauti ni "+tofautini);
          //System.out.println(" Facility ni hii @"+mflcode+" ("+hivpos_711_15_24f+"+"+hivpos_711_15_24m+"+"+hivpos_711_25m+"+"+hivpos_711_25f+"+"+hivpos_711_less15m+"+"+hivpos_711_less15f+") = "+(hivpos_711_15_24f+hivpos_711_15_24m+hivpos_711_25m+hivpos_711_25f+hivpos_711_less15m+hivpos_711_less15f));   
       
         
         while(tofautini>0){
         pmtct_p_24+=1;
         tofautini--;    
         }
         
         
         while(tofautini<0){
     if(pmtct_p_24>=pmtct_p_29 && pmtct_p_24>0 ){
         pmtct_p_24-=1;
         tofautini++;  
             }
    else  if(pmtct_p_29>=pmtct_p_24 && pmtct_p_29>0){
         pmtct_p_29-=1;
         tofautini++;  
                          }
    else  if(pmtct_p_34>0){
         pmtct_p_34-=1;
         tofautini++;  
                          }
     
    else  if(pmtct_p_39>0){
         pmtct_p_39-=1;
         tofautini++;  
                          }
    else {
         System.out.println(" PMTCT positive balancing refused");
         break;
    }
         }
   
       System.out.println(""+facilityname+" Positive yote sasa  ni "+pmtct_p+" ya kuongeza ni "+(pmtct_p_14+pmtct_p_19+pmtct_p_24+pmtct_p_29+pmtct_p_34+pmtct_p_39+pmtct_p_49+pmtct_p_50)+" tofauti ni "+tofautini);
          
   
//      FEMALES
//adult
FemaleAdultTested19=(float)Math.round((Tf19*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((Tf24*TestedAdultFemale));

FemaleAdultTested29=(float)Math.round((Tf29*TestedAdultFemale));
FemaleAdultTested34=(float)Math.round((Tf34*TestedAdultFemale));
FemaleAdultTested39=(float)Math.round((Tf39*TestedAdultFemale));


FemaleAdultTested49=(float)Math.round((Tf49*TestedAdultFemale));
FemaleAdultTested50=(float)Math.round((Tf50*TestedAdultFemale));
//children
FemaleTestedChild1=(float)Math.round((Tf1*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((Tf4*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((Tf9*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((Tf14*TestedChildFemale));

//positive 
//adult  ** remaining 
//hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
AdultFemaleHIV19=(float)Math.round((Pf19*hivpos_711_15_24f));
AdultFemaleHIV24=(float)Math.round((Pf24*hivpos_711_15_24f));

AdultFemaleHIV29=(float)Math.round((Pf29*hivpos_711_25f));
AdultFemaleHIV34=(float)Math.round((Pf34*hivpos_711_25f));
AdultFemaleHIV39=(float)Math.round((Pf39*hivpos_711_25f));

AdultFemaleHIV49=(float)Math.round((Pf49*hivpos_711_25f));
AdultFemaleHIV50=(float)Math.round((Pf50*hivpos_711_25f));

  // balance imediately

//positive

//children
ChildFemaleHIV1=(float)Math.round((Pf1*hivpos_711_less15f));
ChildFemaleHIV4=(float)Math.round((Pf4*hivpos_711_less15f));
ChildFemaleHIV9=(float)Math.round((Pf9*hivpos_711_less15f));
ChildFemaleHIV14=(float)Math.round((Pf14*hivpos_711_less15f));


// MALES  
//adult
  MaleAdultTested19=(float)Math.round((Tm19*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((Tm24*TestedAdultMale));
  
  MaleAdultTested29=(float)Math.round((Tm29*TestedAdultMale));
  MaleAdultTested34=(float)Math.round((Tm34*TestedAdultMale));
  MaleAdultTested39=(float)Math.round((Tm39*TestedAdultMale));
  
  MaleAdultTested49=(float)Math.round((Tm49*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((Tm50*TestedAdultMale));

  //children
  MaleTestedChild1=(float)Math.round((Tm1*TestedChildMale));
  MaleTestedChild4=(float)Math.round((Tm4*TestedChildMale));
  MaleTestedChild9=(float)Math.round((Tm9*TestedChildMale));
  MaleTestedChild14=(float)Math.round((Tm14*TestedChildMale));

//positive
  //adult ** remaining 

  
  AdultMaleHIV19=(float)Math.round((Pm19*hivpos_711_15_24m));
  AdultMaleHIV24=(float)Math.round((Pm24*hivpos_711_15_24m));
  
  AdultMaleHIV29=(float)Math.round((Pm29*hivpos_711_25m));
  AdultMaleHIV34=(float)Math.round((Pm34*hivpos_711_25m));
  AdultMaleHIV39=(float)Math.round((Pm39*hivpos_711_25m));
  
  AdultMaleHIV49=(float)Math.round((Pm49*hivpos_711_25m));
  AdultMaleHIV50=(float)Math.round((Pm50*hivpos_711_25m));

  //positives
  //children
  ChildMaleHIV1=(float)Math.round((Pm1*hivpos_711_less15m));
  ChildMaleHIV4=(float)Math.round((Pm4*hivpos_711_less15m));
  ChildMaleHIV9=(float)Math.round((Pm9*hivpos_711_less15m));
  ChildMaleHIV14=(float)Math.round((Pm14*hivpos_711_less15m));
 
 
          
     double totalpositivesmale=0;   
     double totalpositivesfemale=0;   
       
     double totalfemalehiv=0;
     double totalmalehiv=0;
     double totalfemaletesteddis=0;
     double totalmaletesteddis=0;
     double totalfemaletested=0;
     double totalmaletested=0;
     double negfem=0;
     double  negmale=0;
          
  
               
     
//   total tested after distribution
   totalfemaletesteddis=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
   totalmaletesteddis=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
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
   totalpositivesfemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
   totalpositivesmale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
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
 if(checkdiff>2 ||checkdiff<-2){redalert=1;}
 // positive male
   checkdiff1=totalmalehiv-totalpositivesmale;
if(checkdiff1>2 ||checkdiff1<-2){}

 totalcheckdiff=TotalTested-totaltestedis;
// System.out.println("dqa  "+totalcheckdiff);
 if(totalcheckdiff>5 || totalcheckdiff<-5){finalalert=1;}
 
   adderPos=0;
   double Totalhivfemale=0;
   double Totalhivmale=0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   splitData=0;  
   adderPos=0;
   childSplitData=0;   
//   // adult female hiv+
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50 ;

 adderPos=0;
 
 while(splitData<HIV_AdultFemale){  AdultFemaleHIV29+=1; splitData++;}
 
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50 ;
 while(splitData>HIV_AdultFemale){
     if(AdultFemaleHIV29>0){  AdultFemaleHIV29-=1; splitData--; }
else if(AdultFemaleHIV34>0){ AdultFemaleHIV34-=1; splitData--; }
 else if(AdultFemaleHIV39>0){AdultFemaleHIV39-=1; splitData--; }
 else if(AdultFemaleHIV49>0){ AdultFemaleHIV49-=1; splitData--; }
}
 //tested female adults
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultFemale){FemaleAdultTested34+=1; splitData++;}
 
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultFemale){
 if(FemaleAdultTested34>0){ FemaleAdultTested34-=1; splitData--;}
 else if(FemaleAdultTested39>0){FemaleAdultTested39-=1; splitData--; } 
  else if(FemaleAdultTested49>0){ FemaleAdultTested49-=1; splitData--; }
  else if(FemaleAdultTested50>0){ FemaleAdultTested50-=1; splitData--; }
 else if(FemaleAdultTested24>0){ FemaleAdultTested24-=1; splitData--; }
  else if(FemaleAdultTested19>0){  FemaleAdultTested19-=1; splitData--; }
  else {System.out.println(" Normalization not working for Tested Female IPD");}
     
 
}

 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
  adderPos=0;
 while(splitData<HIV_AdultMale){  AdultMaleHIV34+=1; splitData++;}
 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
 adderPos=0;
 while(splitData>HIV_AdultMale){
     if(AdultMaleHIV29>0){AdultMaleHIV29-=1;  splitData--;}
     else if(AdultMaleHIV34>0){ AdultMaleHIV34-=1; splitData--; }
     else if(AdultMaleHIV39>0){AdultMaleHIV39-=1; splitData--; }
    else if(AdultMaleHIV49>0){ AdultMaleHIV49-=1;splitData--;}
    else if(AdultMaleHIV24>0){AdultMaleHIV24-=1;  splitData--; }
     else if(AdultMaleHIV19>0){AdultMaleHIV19-=1; splitData--;}
     
     else {System.out.println(facilityname+" Normalization not working for IPD adult positive"); break; }
     
}
 
 //tested male adults
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50;
 adderPos=0;
 while(splitData<TestedAdultMale){MaleAdultTested29+=1; splitData++;}
 
splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50;
 adderPos=0;
 while(splitData>TestedAdultMale){
if(MaleAdultTested29>0){MaleAdultTested29-=1;splitData--;}
else if(MaleAdultTested34>0){MaleAdultTested34-=1; splitData--;}
else if(MaleAdultTested24>0){MaleAdultTested24-=1; splitData--;}
else if(MaleAdultTested39>0){ MaleAdultTested39-=1;splitData--;}
else if(MaleAdultTested49>0){MaleAdultTested49-=1;  splitData--;}
else if(MaleAdultTested50>0){ MaleAdultTested50-=1; splitData--;}
else if(MaleAdultTested19>0){MaleAdultTested19-=1; splitData--;}
else {System.out.println(facilityname+" normalization Not working for Male tested Adult"); break;}
}
 
 
 
// for child female tested 
  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 

   adderPos=0;
while(childSplitData<TestedChildFemale){ 
if(adderPos==0){ FemaleTestedChild14+=1;  }
if(adderPos==1){ FemaleTestedChild9+=1;    }
if(adderPos==2){FemaleTestedChild4+=1;  }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;

  while(childSplitData>TestedChildFemale){ 
 if(adderPos==0){FemaleTestedChild14-=1; }
if(adderPos==1){FemaleTestedChild9-=1; }
if(adderPos==2){FemaleTestedChild4-=1;  }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}
System.out.println(facilityname+"     "+childSplitData+" after jjj "+TestedChildFemale);
// for child male hiv

 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 

   adderPos=0;
   double diff=0;
while(childSplitData<HIV_ChildFemale){ 
  diff=FemaleTestedChild14-ChildFemaleHIV14;
 if(adderPos==0){
       if(FemaleTestedChild14-ChildFemaleHIV14>0){ChildFemaleHIV14+=1; }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){ChildFemaleHIV9+=1; }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;} }
  
 if(adderPos==1){
       if(FemaleTestedChild9-ChildFemaleHIV9>0){ChildFemaleHIV9+=1; }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1; }
                }
 if(adderPos==2){
   if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ChildFemaleHIV14+=1;}
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1;}
                }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){
  if(adderPos==0){ChildFemaleHIV14-=1; }
 if(adderPos==1){ ChildFemaleHIV9-=1; }
 if(adderPos==2){ ChildFemaleHIV4-=1;   }
childSplitData--;
adderPos++  ;
 if(adderPos>2){ adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}  
// tested male _______________________________________________________________________
  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
while(childSplitData<TestedChildMale){ 
      if(adderPos==0){MaleTestedChild14+=1;}
 else if(adderPos==1){MaleTestedChild9+=1; }
 else if(adderPos==2){MaleTestedChild4+=1; }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildMale){}
}

  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildMale){ 
 if(adderPos==0){MaleTestedChild14-=1; }
 else if(adderPos==1){ MaleTestedChild9-=1;}
 else if(adderPos==2){ MaleTestedChild4-=1; }
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
      if(MaleTestedChild14-ChildMaleHIV14>0){ChildMaleHIV14+=1;}
 else if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1; }
 else if(MaleTestedChild4-ChildMaleHIV4>0){  ChildMaleHIV4+=1; }
  
 }
 else if(adderPos==1){
      if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1; }
 else if(MaleTestedChild4-ChildMaleHIV4>0){ ChildMaleHIV4+=1;  }
  else if(MaleTestedChild14-ChildMaleHIV14>0){ ChildMaleHIV14+=1;   }
    
                    }
 if(adderPos==2){
  
       if(MaleTestedChild4-ChildMaleHIV4>0){ ChildMaleHIV4+=1;   }
  else if(MaleTestedChild14-ChildMaleHIV14>0){ ChildMaleHIV14+=1;  }
  else if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1;  }
 }
 
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}
 childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildMale){ 
     if(adderPos==0){ ChildMaleHIV14-=1; }
else if(adderPos==1){ChildMaleHIV9-=1;  }
 if(adderPos==2){ ChildMaleHIV4-=1;   }
childSplitData--;
adderPos++  ;
 if(adderPos>2){ adderPos=0; }
 if(childSplitData==HIV_ChildMale){}
}

  
  // all positives
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
  //201510
 //this code was added later
 
 less15f=TestedChildFemale;
 less15m=TestedChildMale;
 gret15m=TestedAdultMale;
 gret15f=TestedAdultFemale;
 
 
 
 double posotiveyote = ChildFemaleHIV1+ChildMaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildMaleHIV4+ChildMaleHIV9+ChildFemaleHIV14+ChildMaleHIV14+AdultFemaleHIV19+AdultMaleHIV19+AdultFemaleHIV24+AdultMaleHIV24+AdultFemaleHIV29+AdultMaleHIV29+AdultFemaleHIV34+AdultMaleHIV34+AdultFemaleHIV39+AdultMaleHIV39+AdultFemaleHIV49+AdultMaleHIV49+AdultFemaleHIV50+AdultMaleHIV50;

 double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(posotiveyote);
   //711_totalpositive 

while(balancegeneral<0){
         if( AdultFemaleHIV34>0){ AdultFemaleHIV34-=1;balancegeneral++; }
    else if( AdultFemaleHIV39>0){ AdultFemaleHIV39-=1;balancegeneral++; }
    else if( AdultFemaleHIV49>0){ AdultFemaleHIV49-=1;balancegeneral++; }
     else if( AdultFemaleHIV29>0){ AdultFemaleHIV29-=1;balancegeneral++;}
     else if( AdultFemaleHIV24>0){ AdultFemaleHIV24-=1;balancegeneral++; }
     else if( AdultFemaleHIV19>0){ AdultFemaleHIV19-=1;balancegeneral++; }
    else   { System.out.println(" Normalization failed"); break;}
                 }

while(balancegeneral>0){AdultFemaleHIV34+=1; balancegeneral--;  }
 

  int neg1male=0;
  int neg4male=0;
  int neg9male=0;
  int neg14male=0;
  int neg19male=0;
  int neg24male=0;
  int neg29male=0;
  int neg34male=0;
  int neg39male=0;
  int neg49male=0;
  int neg50male=0;
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  AdultMaleHIV29Neg=(float)Math.round(MaleAdultTested29)-(AdultMaleHIV29);
  AdultMaleHIV34Neg=(float)Math.round(MaleAdultTested34)-(AdultMaleHIV34);
  AdultMaleHIV39Neg=(float)Math.round(MaleAdultTested39)-(AdultMaleHIV39);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);
if(AdultMaleHIV19Neg<=-1){neg19male=1;}
if(AdultMaleHIV24Neg<=-1){neg24male=1;}

if(AdultMaleHIV29Neg<=-1){neg29male=1;}
if(AdultMaleHIV34Neg<=-1){neg34male=1;}
if(AdultMaleHIV39Neg<=-1){neg39male=1;}
if(AdultMaleHIV49Neg<=-1){neg49male=1;}
if(AdultMaleHIV50Neg<=-1){neg50male=1;}
 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(ChildMaleHIV14);

if(ChildMaleHIV1Neg<=-1){neg1male=1;}
if(ChildMaleHIV4Neg<=-1){ neg4male=1;}
if(ChildMaleHIV9Neg<=-1){ neg9male=1;}
if(ChildMaleHIV14Neg<=-1){neg14male=1;}
//negative

  int neg1female=0;
  int neg4female=0;
  int neg9female=0;
  int neg14female=0;
  int neg19female=0;
  int neg24female=0;
  
  int neg29female=0;
  int neg34female=0;
  int neg39female=0;
  
  
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

System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);

AdultFemaleHIV29Neg =(float)Math.round(FemaleAdultTested29)-(AdultFemaleHIV29);
AdultFemaleHIV34Neg =(float)Math.round(FemaleAdultTested34)-(AdultFemaleHIV34);
AdultFemaleHIV39Neg =(float)Math.round(FemaleAdultTested39)-(AdultFemaleHIV39);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);
  

if(AdultFemaleHIV19Neg<0){
while(AdultFemaleHIV19Neg<=-1){
         if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1; }
    else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1; }
    else if(AdultFemaleHIV29Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1; }
    else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else   { System.out.println("No solution here"); break; }
  
}
}

if(AdultFemaleHIV24Neg<0){
while(AdultFemaleHIV24Neg<=-1){
    
    if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1; }
    else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1; }
    else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1; }
     else if(AdultFemaleHIV19Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else { System.out.println("No solution here"); break;}
    
}

}

if(AdultFemaleHIV29Neg<0){
while(AdultFemaleHIV29Neg<=-1){
    if(AdultFemaleHIV50Neg>0){  AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;  AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1; }
else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
     else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else { System.out.println("No solution here");break;}
    
}
}


if(AdultFemaleHIV34Neg<0){
while(AdultFemaleHIV34Neg<=-1){
    if(AdultFemaleHIV50Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1; }
    else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else if(AdultFemaleHIV29Neg>0) {AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else if(AdultFemaleHIV19Neg>0) { AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else {System.out.println("No solution here");break;}
    
}
}


if(AdultFemaleHIV39Neg<0){
while(AdultFemaleHIV39Neg<=-1){
    
    if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1; AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
    else if(AdultFemaleHIV49Neg>0) { AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
    else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
     else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
     else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
     else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
    else {System.out.println("No solution here"); break; }
    
}

}

if(AdultFemaleHIV49Neg<0){
while(AdultFemaleHIV49Neg<=-1){
    System.out.println(" In a long looop AdultFemaleHIV49Neg: "+AdultFemaleHIV49Neg);
   if(AdultFemaleHIV50Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;  AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV50Neg>0 : "+AdultFemaleHIV49Neg);}
    else if(AdultFemaleHIV39Neg>0) {  AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;  AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV39Neg>0 : "+AdultFemaleHIV49Neg);  }
    else if(AdultFemaleHIV34Neg>0) {  AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV34Neg>0 : "+AdultFemaleHIV49Neg);  }
    else if(AdultFemaleHIV29Neg>0) {  AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV29Neg>0 : "+AdultFemaleHIV49Neg);}
     else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;  AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;  System.out.println(" In a long looop AdultFemaleHIV24Neg>0 : "+AdultFemaleHIV49Neg);}
     else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV19Neg>0 : "+AdultFemaleHIV49Neg);}
     else {System.out.println("No solution here 49"); break;}
    
}
}


if(AdultFemaleHIV50Neg<0){
while(AdultFemaleHIV50Neg<=-1){
     if(AdultFemaleHIV49Neg>0) { AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
    else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
    else if(AdultFemaleHIV34Neg>0) { AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;}
    
     else if(AdultFemaleHIV29Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;}
     else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
     else if(AdultFemaleHIV19Neg>0) { AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
     else { System.out.println("No solution here");  break;}
    
}
}
double TotalNegativeFemale1=0;
 double TotalNegativeMale1=0;
         TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV29Neg+AdultFemaleHIV34Neg+AdultFemaleHIV39Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
           TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV29Neg+AdultMaleHIV34Neg+AdultMaleHIV39Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
// negative female
   checkdiff2=negfem-TotalNegativeFemale1;
 if(checkdiff2>2 ||checkdiff2<-2){

 }
 // negativemale
   checkdiff3=negmale-TotalNegativeMale1;
 if(checkdiff3>2 ||checkdiff3<-2){

 }
 
 
 double pmtct_n_14= pmtct_t_14-pmtct_p_14;
 double pmtct_n_19= pmtct_t_19-pmtct_p_19;
 double pmtct_n_24= pmtct_t_24-pmtct_p_24;
 double pmtct_n_29= pmtct_t_29-pmtct_p_29;
 double pmtct_n_34= pmtct_t_34-pmtct_p_34;
 double pmtct_n_39= pmtct_t_39-pmtct_p_39;
 double pmtct_n_49= pmtct_t_49-pmtct_p_49;
 double pmtct_n_50= pmtct_t_50-pmtct_p_50;
 
 
 
 Double posotiveyote1 = ChildFemaleHIV1+ChildMaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildMaleHIV4+ChildMaleHIV9+ChildFemaleHIV14+ChildMaleHIV14+AdultFemaleHIV19+AdultMaleHIV19+AdultFemaleHIV24+AdultMaleHIV24+AdultFemaleHIV29+AdultMaleHIV29+AdultFemaleHIV34+AdultMaleHIV34+AdultFemaleHIV39+AdultMaleHIV39+AdultFemaleHIV49+AdultMaleHIV49+AdultFemaleHIV50+AdultMaleHIV50;
 
 
  String alldatavals[]={county,district,facilityname,""+mflcode,dsdta
        ,""+conn.rs.getInt("NUM"),""+conn.rs.getInt("grandtotalpositive")
        ,"0","0"
        ,""+ChildFemaleHIV1,""+ChildMaleHIV1
        ,""+(ChildFemaleHIV4),""+(ChildMaleHIV4)
        ,""+(ChildFemaleHIV9),""+(ChildMaleHIV9)
        ,""+(ChildFemaleHIV14+pmtct_p_14),""+ChildMaleHIV14
        ,""+(AdultFemaleHIV19+pmtct_p_19),""+AdultMaleHIV19
        ,""+(AdultFemaleHIV24+pmtct_p_24),""+AdultMaleHIV24
          
        ,""+(AdultFemaleHIV29+pmtct_p_29),""+AdultMaleHIV29
        ,""+(AdultFemaleHIV34+pmtct_p_34),""+AdultMaleHIV34
        ,""+(AdultFemaleHIV39+pmtct_p_39),""+AdultMaleHIV39
          
        ,""+(AdultFemaleHIV49+pmtct_p_49),""+AdultMaleHIV49
        ,""+(AdultFemaleHIV50+pmtct_p_50),""+AdultMaleHIV50        
         //negative starts here 
        ,"0","0"
        ,""+ChildFemaleHIV1Neg,""+ChildMaleHIV1Neg
        ,""+(ChildFemaleHIV4Neg),""+(ChildMaleHIV4Neg)
        ,""+(ChildFemaleHIV9Neg),""+(ChildMaleHIV9Neg)
        ,""+(ChildFemaleHIV14Neg+(pmtct_n_14)),""+ChildMaleHIV14Neg
        ,""+(AdultFemaleHIV19Neg+(pmtct_n_19)),""+AdultMaleHIV19Neg
        ,""+(AdultFemaleHIV24Neg+(pmtct_n_24) ),""+AdultMaleHIV24Neg
          
        ,""+(AdultFemaleHIV29Neg+(pmtct_n_29)),""+AdultMaleHIV29Neg
        ,""+(AdultFemaleHIV34Neg+(pmtct_n_34)),""+AdultMaleHIV34Neg
        ,""+(AdultFemaleHIV39Neg+(pmtct_n_39)),""+AdultMaleHIV39Neg
          
        ,""+(AdultFemaleHIV49Neg+(pmtct_n_49)),""+AdultMaleHIV49Neg
        ,""+(AdultFemaleHIV50Neg+(pmtct_n_50)),""+AdultMaleHIV50Neg,""+((tested_new711)+(pmtct_t)),""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")
          
        };
  
 
  ipdhm.put("cty"+"_"+mflcode, county);
  ipdhm.put("sty"+"_"+mflcode, district);
  ipdhm.put("fac"+"_"+mflcode, facilityname);
  ipdhm.put("mfl"+"_"+mflcode, ""+mflcode);
  ipdhm.put("st"+"_"+mflcode, dsdta);
  ipdhm.put("gtt"+"_"+mflcode,""+conn.rs.getInt("NUM"));
  ipdhm.put("gtp"+"_"+mflcode,""+conn.rs.getInt("grandtotalpositive"));
  ipdhm.put("ukpf"+"_"+mflcode,"0");
  ipdhm.put("ukpm"+"_"+mflcode,"0");
  ipdhm.put("pf1"+"_"+mflcode,""+ChildFemaleHIV1);
  ipdhm.put("pm1"+"_"+mflcode,""+ChildMaleHIV1);
  ipdhm.put("pf4"+"_"+mflcode,""+ChildFemaleHIV4);
  ipdhm.put("pm4"+"_"+mflcode,""+ChildMaleHIV4);
  ipdhm.put("pf9"+"_"+mflcode,""+ChildFemaleHIV9);
  ipdhm.put("pm9"+"_"+mflcode,""+ChildMaleHIV9);
  ipdhm.put("pf14"+"_"+mflcode,""+(ChildFemaleHIV14+pmtct_p_14));
  ipdhm.put("pm14"+"_"+mflcode,""+ChildMaleHIV14);
  ipdhm.put("pf19"+"_"+mflcode,""+(AdultFemaleHIV19+pmtct_p_19));
  ipdhm.put("pm19"+"_"+mflcode,""+AdultMaleHIV19);
  ipdhm.put("pf24"+"_"+mflcode,""+(AdultFemaleHIV24+pmtct_p_24));
  ipdhm.put("pm24"+"_"+mflcode,""+AdultMaleHIV24);
  ipdhm.put("pf29"+"_"+mflcode,""+(AdultFemaleHIV29+pmtct_p_29));
  ipdhm.put("pm29"+"_"+mflcode,""+AdultMaleHIV29);
  ipdhm.put("pf34"+"_"+mflcode,""+(AdultFemaleHIV34+pmtct_p_34));
  ipdhm.put("pm34"+"_"+mflcode,""+AdultMaleHIV34);
  ipdhm.put("pf39"+"_"+mflcode,""+(AdultFemaleHIV39+pmtct_p_39));
  ipdhm.put("pm39"+"_"+mflcode,""+AdultMaleHIV39);
  ipdhm.put("pf49"+"_"+mflcode,""+(AdultFemaleHIV49+pmtct_p_49));
  ipdhm.put("pm49"+"_"+mflcode,""+AdultMaleHIV49);
  ipdhm.put("pf50"+"_"+mflcode,""+(AdultFemaleHIV50+pmtct_p_50));
  ipdhm.put("pm50"+"_"+mflcode,""+AdultMaleHIV50);
  //_____negative
  ipdhm.put("uknf"+"_"+mflcode,"0");
  ipdhm.put("uknm"+"_"+mflcode,"0");
  ipdhm.put("nf1"+"_"+mflcode,""+ChildFemaleHIV1Neg);
  ipdhm.put("nm1"+"_"+mflcode,""+ChildMaleHIV1Neg);
  ipdhm.put("nf4"+"_"+mflcode,""+ChildFemaleHIV4Neg);
  ipdhm.put("nm4"+"_"+mflcode,""+ChildMaleHIV4Neg);
  ipdhm.put("nf9"+"_"+mflcode,""+ChildFemaleHIV9Neg);
  ipdhm.put("nm9"+"_"+mflcode,""+ChildMaleHIV9Neg);
  ipdhm.put("nf14"+"_"+mflcode,""+(ChildFemaleHIV14Neg+pmtct_n_14));
  ipdhm.put("nm14"+"_"+mflcode,""+ChildMaleHIV14Neg);
  ipdhm.put("nf19"+"_"+mflcode,""+(AdultFemaleHIV19Neg+pmtct_n_19));
  ipdhm.put("nm19"+"_"+mflcode,""+AdultMaleHIV19Neg);
  ipdhm.put("nf24"+"_"+mflcode,""+(AdultFemaleHIV24Neg+pmtct_n_24));
  ipdhm.put("nm24"+"_"+mflcode,""+AdultMaleHIV24Neg);
  ipdhm.put("nf29"+"_"+mflcode,""+(AdultFemaleHIV29Neg+pmtct_n_29));
  ipdhm.put("nm29"+"_"+mflcode,""+AdultMaleHIV29Neg);
  ipdhm.put("nf34"+"_"+mflcode,""+(AdultFemaleHIV34Neg+pmtct_n_34));
  ipdhm.put("nm34"+"_"+mflcode,""+AdultMaleHIV34Neg);
  ipdhm.put("nf39"+"_"+mflcode,""+(AdultFemaleHIV39Neg+pmtct_n_39));
  ipdhm.put("nm39"+"_"+mflcode,""+AdultMaleHIV39Neg);
  ipdhm.put("nf49"+"_"+mflcode,""+(AdultFemaleHIV49Neg+pmtct_n_49));
  ipdhm.put("nm49"+"_"+mflcode,""+AdultMaleHIV49Neg);
  ipdhm.put("nf50"+"_"+mflcode,""+(AdultFemaleHIV50Neg+pmtct_n_50));
  ipdhm.put("nm50"+"_"+mflcode,""+AdultMaleHIV50Neg);
  ipdhm.put("tt"+"_"+mflcode,""+((tested_new711)+(pmtct_t)));
  


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
    
    
    
    
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  rwx=shet3.createRow(count);  
 rwx.setHeightInPoints(23);  
 
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
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
   
        }
   else if(z==pitc_ipd_header1.length-5){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticart_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
   }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(statichtc_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticpmtct_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticishtc.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticispmtct.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
  
  
		/** else if(z==blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }  */
                 
  else {
                     
   HSSFCell celldata=rwx.createCell(z); 
   celldata.setCellValue(0);
   celldata.setCellStyle(stborder);
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                for (int e = 0; e < 4; e++) {
                shet3.autoSizeColumn(e);
                }
                //Made my life veery simple...
                shet3.setDisplayGridlines(false);
                shet3.createFreezePane(5,5);                 
              
                 
            
  return ipdhm;          
            
    
}

 public HashMap Opd(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
   //new htc for PITC 
   
   HashMap<String, String> opdhm = new HashMap<String, String>(); 
   
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
    
                  
                     isOPD=true;
                     isIPD=false;
                     isVCT=false;
                     
                     
                     
                     
                     
                     //2017
                     String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total OPD + Postnatal Tested","Total Pediatric tested ","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total OPD + Postnatal Tested","Total Pediatric tested ","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total OPD Tested","Total OPD + Postnatal Tested","Positive","Negative","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     
    
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
           subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    
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
         
         subcounty_countywhere=" (county.CountyID='"+county+"')   AND "+facilitiestable+".active=1 and  ";//20160711
         
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
    String mergeinfor[]={"0,0,0,61","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,2,5,28","2,2,29,52","2,4,53,53","2,4,54,54","2,4,55,55","3,3,5,6","3,3,29,30","2,4,56,56","2,4,57,57","2,4,58,58","2,4,59,59","2,4,60,60","2,4,61,61"};  
   
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
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 or PMTCT=1)   AND "+facilitiestable+".active=1  group by "+facilitiestable+".SubPartnerID   "; 
    
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
               
 
  //deduct   
       String countyidcopy="0",countyid="0";
       
      

double Tm=0,Tf=0; //Tested male, Tested female

//by gender by age breakdown variables
double Tmc=0,Tma=0,Tfc=0,Tfa=0; //Tested male children, Tested male adult, tested female children, tested female adult. 
//Tested and Positive fine age	 
    double Tf1=0,Tm1=0,Pf1=0,Pm1=0;
    double Tf4=0,Tm4=0,Pf4=0,Pm4=0; //may or may not be there
    double Tf9=0,Tm9=0,Pf9=0,Pm9=0;
    double Tf14=0,Tm14=0,Pf14=0,Pm14=0,PTf14=0,PPf14=0;
    double Tf19=0,Tm19=0,Pf19=0,Pm19=0,PTf19=0,PPf19=0;
    double Tf24=0,Tm24=0,Pf24=0,Pm24=0,PTf24=0,PPf24=0;
    double Tf29=0,Tm29=0,Pf29=0,Pm29=0,PTf29=0,PPf29=0;
    double Tf34=0,Tm34=0,Pf34=0,Pm34=0,PTf34=0,PPf34=0;
    double Tf39=0,Tm39=0,Pf39=0,Pm39=0,PTf39=0,PPf39=0;
    double Tf49=0,Tm49=0,Pf49=0,Pm49=0,PTf49=0,PPf49=0;
    double Tf50=0,Tm50=0,Pf50=0,Pm50=0,PTf50=0,PPf50=0;
       
       
       
    String get731data="SELECT "
            + " sum(HV0103) as 711_totaltested, "
            + " sum(HV0110) as 711_less15m ,"
            + " sum(HV0111) as 711_less15f ,"
            + " sum(HV0112) as 711_15_24m ,"
            + " sum(HV0113) as 711_15_24f ,"
            + " sum(HV0114) as 711_25m ,"
            + " sum(HV0115) as 711_25f ,"
            + " sum(HV0110+HV0111+HV0112+HV0113+HV0114+HV0115) as 711_totalpositive ," //updated in 201607            
            + " county.County,district.DistrictNom,"
            + ""+facilitiestable+".SubPartnerNom, "+facilitiestable+".CentreSanteId,"+facilitiestable+".HTC_Support1 ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
            +" ,sum( HV0103 + HV0201 ) as NUM,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT, SUM(IFNULL(0,0)) as serology "//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
            + " ,county.CountyID as CountyID "
            +", sum(IFNULL(HV0203,0)) as pnc_tes ,sum(IFNULL(HV0208,0)) as pnc_pos "
            + " FROM moh731  JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + " district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && ("+facilitiestable+".HTC=1 or PMTCT=1)  AND "+facilitiestable+".active=1   "
            + "  GROUP BY moh731.SubPartnerID order by county.County " ;
    
    
    
    
     System.out.println("2017q1 IPD : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
    while(conn.rs.next()){
 
        //f_29	f_34	f_39
//_______new age brackets added in 201710_______
double FemaleAdultTested29=0;
double FemaleAdultTested34=0;
double FemaleAdultTested39=0;



double AdultFemaleHIV29Neg=0;
double AdultFemaleHIV34Neg=0;
double AdultFemaleHIV39Neg=0;


double AdultFemaleHIV29=0;
double AdultFemaleHIV34=0;
double AdultFemaleHIV39=0;

//male 

 double  MaleAdultTested29=0;
 double  MaleAdultTested34=0;
 double  MaleAdultTested39=0;
 
 double AdultMaleHIV29Neg=0;
  double AdultMaleHIV34Neg=0;
  double AdultMaleHIV39Neg=0;
  
   double  AdultMaleHIV29=0;
 double  AdultMaleHIV34=0;
 double  AdultMaleHIV39=0;

    
    //master county changes. if the countycopy is different from the previous version, then fetch all variables for the current county
    
     countyid=conn.rs.getString("CountyID");
	 if(!countyid.equals(countyidcopy)){
         //change countyidcopy to be same as countyid
         System.out.println("county confirmation "+countyid+" vs "+countyidcopy);
         countyidcopy=countyid;
         //now fetch the variables from proportions table
             
         
         String getratios=" select * from htc_proportion where isactive='1' and county_id='"+countyid+"'";
         conn.rs4=conn.st4.executeQuery(getratios);
         while(conn.rs4.next()){
         String indicator=conn.rs4.getString("indicator");
           
           if(indicator.equals("tested by gender")){
                 
           Tm=conn.rs4.getDouble("male_or_child");
           Tf =conn.rs4.getDouble("female_or_adult"); 
               System.out.println(countyName+"  OPD = Male: "+Tm +" Female :"+Tf);
             }
             
             
            if(indicator.equals("tested by broad age female")){
             Tfc=conn.rs4.getDouble("male_or_child");
             Tfa=conn.rs4.getDouble("female_or_adult");
             
                System.out.println(countyName+" OPD = = Female Child: "+Tfc +" Female Adult :"+Tfa);
             } 
            
             if(indicator.equals("tested by broad age male"))
             {
             Tmc=conn.rs4.getDouble("male_or_child");
             Tma=conn.rs4.getDouble("female_or_adult");
             
              System.out.println(countyName+" OPD = Male Child: "+Tmc +" Female Adult :"+Tma);
              
             }
            
             
             if(indicator.equals("tested by fine age")){
             
    Tf1=conn.rs4.getDouble("f_1"); Tm1=conn.rs4.getDouble("m_1");
    Tf4=conn.rs4.getDouble("f_4");Tm4=conn.rs4.getDouble("m_4");
    Tf9=conn.rs4.getDouble("f_9");Tm9=conn.rs4.getDouble("m_9");
    Tf14=conn.rs4.getDouble("f_14");Tm14=conn.rs4.getDouble("m_14");
    Tf19=conn.rs4.getDouble("f_19");Tm19=conn.rs4.getDouble("m_19");
    Tf24=conn.rs4.getDouble("f_24");Tm24=conn.rs4.getDouble("m_24");
    Tf29=conn.rs4.getDouble("f_29");Tm29=conn.rs4.getDouble("m_29");
    Tf34=conn.rs4.getDouble("f_34");Tm34=conn.rs4.getDouble("m_34");
    Tf39=conn.rs4.getDouble("f_39");Tm39=conn.rs4.getDouble("m_39");
    Tf49=conn.rs4.getDouble("f_49");Tm49=conn.rs4.getDouble("m_49");
    Tf50=conn.rs4.getDouble("f_50");Tm50=conn.rs4.getDouble("m_50");
 
                                                          } 
             
             
             if(indicator.equals("positive by fine age")){
   Pf1=conn.rs4.getDouble("f_1");Pm1=conn.rs4.getDouble("m_1");
   Pf4=conn.rs4.getDouble("f_4");Pm4=conn.rs4.getDouble("m_4");
   Pf9=conn.rs4.getDouble("f_9");Pm9=conn.rs4.getDouble("m_9");
   Pf14=conn.rs4.getDouble("f_14");Pm14=conn.rs4.getDouble("m_14");
   Pf19=conn.rs4.getDouble("f_19");Pm19=conn.rs4.getDouble("m_19");
   Pf24=conn.rs4.getDouble("f_24");Pm24=conn.rs4.getDouble("m_24");
   Pf29=conn.rs4.getDouble("f_29");Pm29=conn.rs4.getDouble("m_29");
   Pf34=conn.rs4.getDouble("f_34");Pm34=conn.rs4.getDouble("m_34");
   Pf39=conn.rs4.getDouble("f_39");Pm39=conn.rs4.getDouble("m_39");
   Pf49=conn.rs4.getDouble("f_49");Pm49=conn.rs4.getDouble("m_49");
   Pf50=conn.rs4.getDouble("f_50");Pm50=conn.rs4.getDouble("m_50"); 
  } 
           
             
             
                      
  if(indicator.equals("PMTCT_ANC")){
    //use same proportion for pos and negative
    PTf14=conn.rs4.getDouble("f_14");
    PTf19=conn.rs4.getDouble("f_19");
    PTf24=conn.rs4.getDouble("f_24");
    PTf29=conn.rs4.getDouble("f_29");
    PTf34=conn.rs4.getDouble("f_34");
    PTf39=conn.rs4.getDouble("f_39");
    PTf49=conn.rs4.getDouble("f_49");
    PTf50=conn.rs4.getDouble("f_50");              
                                   // }
            
  // if(indicator.equals("PMTCT_New_Postive")){
                
   PPf14=conn.rs4.getDouble("f_14");
   PPf19=conn.rs4.getDouble("f_19");
   PPf24=conn.rs4.getDouble("f_24");
   PPf29=conn.rs4.getDouble("f_29");
   PPf34=conn.rs4.getDouble("f_34");
   PPf39=conn.rs4.getDouble("f_39");
   PPf49=conn.rs4.getDouble("f_49");
   PPf50=conn.rs4.getDouble("f_50");   }
             
             
                 
             } 
             
         }
  
         
         double pmtct_t=0;
         double pmtct_t_14=0;
         double pmtct_t_19=0;
         double pmtct_t_24=0;
         double pmtct_t_29=0;
         double pmtct_t_34=0;
         double pmtct_t_39=0;
         double pmtct_t_49=0;
         double pmtct_t_50=0;
         
         double pmtct_p=0;
         double pmtct_p_14=0;
         double pmtct_p_19=0;
         double pmtct_p_24=0;
         double pmtct_p_29=0;
         double pmtct_p_34=0;
         double pmtct_p_39=0;
         double pmtct_p_49=0;
         double pmtct_p_50=0;
         
         
         
        //--------------added ratios fetching form 2017---------
        
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
 

double FemaleAdultTested19=0;
double FemaleAdultTested24=0;
double FemaleAdultTested49=0;
double FemaleAdultTested50=0;

double FemaleTestedChild1=0;
double FemaleTestedChild4=0;
double FemaleTestedChild9=0;
double FemaleTestedChild14=0;


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
       
    
       double opd_tes_balancing=0;
       double ipd_tes_balancing=0;
       double vct_tes_balancing=0;
       
       
       
       double ipdpositiveratio=0;
       double opdpositiveratio=0;
       double vctpositiveratio=0;
       
       //ipd_tes_balancing+opd_tes_balancing+vct_tes_balancing
       
       //____Discard the suggestion below. it was disregarded_____
       //NOTE ..TB TESTED DATA WILL NOW BE 1 % OF OPD DATA
       // TB POS will be 33% of the tested, if only there is some positive data in the opd section..
       //note, this will only happen on the art sites, otherwise the opd data will not be edited
	
      //do a query that calculates the sites supporting inpatient and outpatient for the last six months
      //since we are already in a loop, see if the current site calculates 
       //String issiteipd=" select sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot from moh711 where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ( yearmonth between '201510' and '201603') ";
                //System.out.println("%%"+issiteipd);
        String issiteipd=" select sum(IPD) as DTCB_Test_In_Tot from "+facilitiestable+" where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'  AND "+facilitiestable+".active=1  ";
               
        conn.rs1=conn.st1.executeQuery(issiteipd);
       if(conn.rs1.next()){
         
       int semiannualinpatient=0;
       if(conn.rs1.getString("DTCB_Test_In_Tot")!=null){ semiannualinpatient=conn.rs1.getInt("DTCB_Test_In_Tot");}
       
       
           if(semiannualinpatient>0)
              {
       	//Sites with Inpatient services
 	              //OPD	IPD	VCT

//Testing ratios	72%	17%	11%
//Positivity ratios	62%	19%	19%
           //tested
                  //for IPD
                  if(isIPD==true){
                      
                  htctestedratio=0.17;                      
                  htcpositiveratio=0.19;
                      
                  }
                  
                  else if(isOPD==true){
                      
                  htctestedratio=0.72;                      
                  htcpositiveratio=0.62;  
                      
                  }
                  
                  else if(isVCT==true)
                  {
                      
                  htctestedratio=0.11;                      
                  htcpositiveratio=0.19;                      
                      
                  }
                  ipdpositiveratio=0.19;
                  opdpositiveratio=0.62;
                  vctpositiveratio=0.19;
                  
                  ipd_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0.17));
                  opd_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0.72));
                  vct_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0.11));
          
        /**          
       //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+inpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+inpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
      */ 
      
            }
       else {
           
       //site not supporting inpatient
      //Sites without Inpatient services	
	           //OPD	VCT	 
//HIV Tested	     86%	14%	 
//Tested Positive    85%	15% 
               
               
               if(isIPD==true){
                      
                  htctestedratio=0;                      
                  htcpositiveratio=0;                      
                      
                  }
                  
                  else if(isOPD==true){
                      
                  htctestedratio=0.86;                      
                  htcpositiveratio=0.85;                      
                      
                  }
                  
                  else if(isVCT==true)
                  {
                      
                  htctestedratio=0.14;                      
                  htcpositiveratio=0.15;                      
                      
                  }
          
               
                  ipd_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0));
                  opd_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0.86));
                  vct_tes_balancing=(float)Math.round((conn.rs.getInt("711_totaltested")*0.14));
       
                  ipdpositiveratio=0;
                  opdpositiveratio=0.85;
                  vctpositiveratio=0.15;
                  
      /**   
         //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
    */
       
       } 
            }//end of conn
			
			
   //TestedAdultFemale   TestedChildFemale  hivpos_711_15_24f  hivpos_711_25f HIV_ChildFemale
   //TestedAdultMale     TestedChildMale    hivpos_711_15_24m  hivpos_711_25m HIV_ChildMale
      
  
      
      //==============================================NEW2017Q1===========================================================
      
     
    //============================================================================================================================START NEW RATIOS===================================
    
    
    
  //%%%%%%%%%%%%%%%%added 201606 %%%%%%%%%%%%%%%%%%%%%% 
     // 38 (M)%  ---	62% (F)

  double tested_new711=(float)Math.round((conn.rs.getInt("711_totaltested")*htctestedratio));
  
  //|__|
        System.out.println(" 2017Q1__"+tested_new711);
     
  double testedmale_711=(float)Math.round((Tm*tested_new711));
  double testedfemale_711=(float)Math.round((Tf*tested_new711));
  

   double tofautimpya=tested_new711-(testedmale_711+testedfemale_711);
  if(tofautimpya!=0){
  testedfemale_711+=tofautimpya;
 
  }
 
        //System.out.println("**2016_06_ "+testedmale_711+ "~ "+testedfemale_711+" ~ "+ tested_new711);
        
     //12%      88%
       
    //this will be defined from ratios 
    TestedAdultFemale=(int)Math.round((Tfa*testedfemale_711));  //adult   
    TestedChildFemale=(int)Math.round((Tfc*testedfemale_711)); //child
    
   tofautimpya=testedfemale_711 -(TestedAdultFemale+TestedChildFemale);   
    if(tofautimpya!=0){
  TestedAdultFemale+=tofautimpya;  
  }
    //  17%  83%   
    
    //TestedAdultMale=conn.rs.getInt(2);
    //TestedChildMale=conn.rs.getInt(6);
   
    TestedAdultMale=(int)Math.round((Tma*testedmale_711));  //adult   
    TestedChildMale=(int)Math.round((Tmc*testedmale_711)); //child
    
   
 
   tofautimpya=testedmale_711 -(TestedAdultMale+TestedChildMale);   
    if(tofautimpya!=0){
        
  TestedAdultMale+=tofautimpya;  
   }
     
  	 pmtct_t=(float)Math.round((conn.rs.getInt("pnc_tes")));
       pmtct_p=(float)Math.round((conn.rs.getInt("pnc_pos")));
    
    int hivpos_711_15_24f=(int) Math.round((conn.rs.getInt("711_15_24f")*htcpositiveratio));//|__|
    int hivpos_711_15_24m=(int) Math.round((conn.rs.getInt("711_15_24m")*htcpositiveratio));//|__|
    
    int hivpos_711_25m=(int) Math.round((conn.rs.getInt("711_25m")*htcpositiveratio));//|__|
    int hivpos_711_25f=(int) Math.round((conn.rs.getInt("711_25f")*htcpositiveratio));//|__|
    
    
    HIV_AdultFemale=hivpos_711_15_24f+ hivpos_711_25f;//
    HIV_AdultMale=hivpos_711_15_24m+ hivpos_711_25m; 
    
    HIV_ChildFemale=(int) Math.round((conn.rs.getInt("711_less15f")*htcpositiveratio)); //|__|
    HIV_ChildMale=(int) Math.round((conn.rs.getInt("711_less15m")*htcpositiveratio));   //|__|
    
    
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
        
  
   
   
   
   
   
   
   
   pmtct_t_14=(float)Math.round((PTf14*pmtct_t));
         pmtct_t_19=(float)Math.round((PTf19*pmtct_t));
         pmtct_t_24=(float)Math.round((PTf24*pmtct_t));
         pmtct_t_29=(float)Math.round((PTf29*pmtct_t));
         pmtct_t_34=(float)Math.round((PTf34*pmtct_t));
         pmtct_t_39=(float)Math.round((PTf39*pmtct_t));
         pmtct_t_49=(float)Math.round((PTf49*pmtct_t));
         pmtct_t_50=(float)Math.round((PTf50*pmtct_t));
         
         
         pmtct_p_14=(float)Math.round((PPf14*pmtct_p));
         pmtct_p_19=(float)Math.round((PPf19*pmtct_p));
         pmtct_p_24=(float)Math.round((PPf24*pmtct_p));
         pmtct_p_29=(float)Math.round((PPf29*pmtct_p));
         pmtct_p_34=(float)Math.round((PPf34*pmtct_p));
         pmtct_p_39=(float)Math.round((PPf39*pmtct_p));
         pmtct_p_49=(float)Math.round((PPf49*pmtct_p));
         pmtct_p_50=(float)Math.round((PPf50*pmtct_p));
         
         
         double pmtcttestedyote=pmtct_t_14+pmtct_t_19+pmtct_t_24+pmtct_t_29+pmtct_t_34+pmtct_t_39+pmtct_t_49+pmtct_t_50;
         double tofautini=pmtct_t-pmtcttestedyote;
         while(tofautini>0){
         pmtct_t_24+=1;
         tofautini--;    
         }
         
         
         while(tofautini<0){
     if(pmtct_t_24>=pmtct_t_29 && pmtct_t_24>0 ){
         pmtct_t_24-=1;
         tofautini++;  
             }
    else  if(pmtct_t_29>=pmtct_t_24 && pmtct_t_29>0){
         pmtct_t_29-=1;
         tofautini++;  
                          }
    else  if(pmtct_t_34>0){
         pmtct_t_34-=1;
         tofautini++;  
                          }
     
    else  if(pmtct_t_39>0){
         pmtct_t_39-=1;
         tofautini++;  
                          }
    else {
         System.out.println(" PMTCT tested balancing refused");
         break;
    }
         }
         
         
         
         
   
   double pmtctposyote=pmtct_p_14+pmtct_p_19+pmtct_p_24+pmtct_p_29+pmtct_p_34+pmtct_p_39+pmtct_p_49+pmtct_p_50;
         tofautini=pmtct_p-pmtctposyote;
         
         while(tofautini>0){
         pmtct_p_24+=1;
         tofautini--;    
         }
         
         
         while(tofautini<0){
     if(pmtct_p_24>=pmtct_p_29 && pmtct_p_24>0 ){
         pmtct_p_24-=1;
         tofautini++;  
             }
    else  if(pmtct_p_29>=pmtct_p_24 && pmtct_p_29>0){
         pmtct_p_29-=1;
         tofautini++;  
                          }
    else  if(pmtct_p_34>0){
         pmtct_p_34-=1;
         tofautini++;  
                          }
     
    else  if(pmtct_p_39>0){
         pmtct_p_39-=1;
         tofautini++;  
                          }
    else {
         System.out.println(" PMTCT positive balancing refused");
         break;
    }
         }
		 
		 
		 
		 
		 
		 
   
   
   
   
   
  //========================NEW FINE AGE RATIOS added 201607=====================================
  //prior to this level, ensure all the main variables below being subjected to a ratio have already been subjected to the respective percentage per service area 
   //i.e. IPD, OPD, VCT 
  //the main variables are 
   //TestedAdultFemale   TestedChildFemale  hivpos_711_15_24f  hivpos_711_25f HIV_ChildFemale
   //TestedAdultMale   TestedChildMale  hivpos_711_15_24m  hivpos_711_25m HIV_ChildMale
//      FEMALES
//adult
FemaleAdultTested19=(float)Math.round((Tf19*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((Tf24*TestedAdultFemale));

FemaleAdultTested29=(float)Math.round((Tf29*TestedAdultFemale));
FemaleAdultTested34=(float)Math.round((Tf34*TestedAdultFemale));
FemaleAdultTested39=(float)Math.round((Tf39*TestedAdultFemale));

FemaleAdultTested49=(float)Math.round((Tf49*TestedAdultFemale));
FemaleAdultTested50=(float)Math.round((Tf50*TestedAdultFemale));
//children
FemaleTestedChild1=(float)Math.round((Tf1*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((Tf4*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((Tf9*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((Tf14*TestedChildFemale));

//postive 
//adult  ** remaining 
//hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
AdultFemaleHIV19=(float)Math.round((Pf19*hivpos_711_15_24f));
AdultFemaleHIV24=(float)Math.round((Pf24*hivpos_711_15_24f));

AdultFemaleHIV29=(float)Math.round((Pf29*hivpos_711_25f));
AdultFemaleHIV34=(float)Math.round((Pf34*hivpos_711_25f));
AdultFemaleHIV39=(float)Math.round((Pf39*hivpos_711_25f));

AdultFemaleHIV49=(float)Math.round((Pf49*hivpos_711_25f));
AdultFemaleHIV50=(float)Math.round((Pf50*hivpos_711_25f));
   
//positive

//children
ChildFemaleHIV1=(float)Math.round((Pf1*HIV_ChildFemale));
ChildFemaleHIV4=(float)Math.round((Pf4*HIV_ChildFemale));
ChildFemaleHIV9=(float)Math.round((Pf9*HIV_ChildFemale));
ChildFemaleHIV14=(float)Math.round((Pf14*HIV_ChildFemale));

// MALES  
//adult

  MaleAdultTested19=(float)Math.round((Tm19*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((Tm24*TestedAdultMale));
  
  MaleAdultTested29=(float)Math.round((Tm29*TestedAdultMale));
  MaleAdultTested34=(float)Math.round((Tm34*TestedAdultMale));
  MaleAdultTested39=(float)Math.round((Tm39*TestedAdultMale));
  
  MaleAdultTested49=(float)Math.round((Tm49*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((Tm50*TestedAdultMale));

  //children
  MaleTestedChild1=(float)Math.round((Tm1*TestedChildMale));
  MaleTestedChild4=(float)Math.round((Tm4*TestedChildMale));
  MaleTestedChild9=(float)Math.round((Tm9*TestedChildMale));
  MaleTestedChild14=(float)Math.round((Tm14*TestedChildMale));

  
    System.out.println("Before Normalization "+facilityname+" OPD normalization  Tested Male prop Tested child="+TestedChildMale+" M1="+Tm1+" M4="+Tm4+" M9="+Tm9+" M14="+Tm14+"  Tested adult "+TestedAdultMale+"  M19="+Tm19+" M24="+Tm24+" M29="+Tm29+"  M34="+Tm34+" M39="+Tm39+" 49="+Tm49+" 50="+Tm50);
    System.out.println("Before Normalization "+facilityname+" OPD normalization  Tested Male values Tested child="+TestedChildMale+" M1="+MaleTestedChild1+" M4="+MaleTestedChild4+" M9="+MaleTestedChild9+" M14="+MaleTestedChild14+"  Tested adult "+TestedAdultMale+"   M19="+MaleAdultTested19+" M24="+MaleAdultTested24+" M29="+MaleAdultTested29+"  M34="+MaleAdultTested34+" M39="+MaleAdultTested39+" 49="+MaleAdultTested49+" 50="+MaleAdultTested50);
  
//positive
  //adult ** remaining 
  //hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
   
  AdultMaleHIV19=(float)Math.round((Pm19*hivpos_711_15_24m));
  AdultMaleHIV24=(float)Math.round((Pm24*hivpos_711_15_24m));
  
  AdultMaleHIV29=(float)Math.round((Pm29*hivpos_711_25m));
  AdultMaleHIV34=(float)Math.round((Pm34*hivpos_711_25m));
  AdultMaleHIV39=(float)Math.round((Pm39*hivpos_711_25m));
  
  AdultMaleHIV49=(float)Math.round((Pm49*hivpos_711_25m));
  AdultMaleHIV50=(float)Math.round((Pm50*hivpos_711_25m));

  //positives
  //children
  ChildMaleHIV1=(float)Math.round((Pm1*HIV_ChildMale));
  ChildMaleHIV4=(float)Math.round((Pm4*HIV_ChildMale));
  ChildMaleHIV9=(float)Math.round((Pm9*HIV_ChildMale));
  ChildMaleHIV14=(float)Math.round((Pm14*HIV_ChildMale));
  
     
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
   totalpositives=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;    
   totalnegatives=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV29Neg+AdultFemaleHIV34Neg+AdultFemaleHIV39Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV29Neg+AdultMaleHIV34Neg+AdultMaleHIV39Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
  
//   total tested after distribution
   totalfemaletesteddis=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
   totalmaletesteddis=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
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
 totalpositivesfemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
 totalpositivesmale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
 
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
splitData=AdultFemaleHIV19+AdultFemaleHIV24 ;
//
System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
 adderPos=0;
 
 while(splitData<hivpos_711_15_24f){ AdultFemaleHIV24+=1; splitData++;}
 
splitData=AdultFemaleHIV19+AdultFemaleHIV24 ;
 while(splitData>hivpos_711_15_24f){ 
 if(AdultFemaleHIV24<AdultFemaleHIV19  ){ AdultFemaleHIV19-=1; splitData--;}
 else if(AdultFemaleHIV24 > 0) { AdultFemaleHIV24-=1; splitData--;}
 else { System.out.println("  balancing not working for opd Adult Female 19 to 24"); break;}
 
}
 
 //positive
 
 splitData=AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50;

 adderPos=0;
 while(splitData<hivpos_711_25f){ AdultFemaleHIV29+=1; splitData++; }
splitData=AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50; ;
 while(splitData>hivpos_711_25f){
     
     if(AdultFemaleHIV29>0){ AdultFemaleHIV29-=1; splitData--;}
     else if(AdultFemaleHIV34>0){ AdultFemaleHIV34-=1; splitData--; }
     else if(AdultFemaleHIV39>0){ AdultFemaleHIV39-=1; splitData--;}
     else if(AdultFemaleHIV49>0){ AdultFemaleHIV49-=1; splitData--;}
     else if(AdultFemaleHIV50>0){AdultFemaleHIV50-=1; splitData--;}
     else {break;}
     
 
}
 
 
 //tested female adults
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
 adderPos=0;
 while(splitData<TestedAdultFemale){ FemaleAdultTested34+=1; splitData++;}
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
 adderPos=0;
 while(splitData>TestedAdultFemale){ 
           if(FemaleAdultTested29>0){ FemaleAdultTested29-=1; splitData--; }
     else  if(FemaleAdultTested34>0){ FemaleAdultTested34-=1; splitData--;}
     else  if(FemaleAdultTested39>0){ FemaleAdultTested39-=1; splitData--; }
     else  if(FemaleAdultTested49>0){ FemaleAdultTested49-=1; splitData--;}
     else  if(FemaleAdultTested50>0) { FemaleAdultTested50-=1;  splitData--;}
}
   
 
 
// adult male hiv+
 
 splitData=AdultMaleHIV19+AdultMaleHIV24; adderPos=0;
 while(splitData<hivpos_711_15_24m){ AdultMaleHIV24+=1; splitData++; }
 splitData=AdultMaleHIV19+AdultMaleHIV24;
 adderPos=0;
 while(splitData>hivpos_711_15_24m){
  
     if(AdultMaleHIV19<AdultMaleHIV24 && AdultMaleHIV24> 0){  AdultMaleHIV24-=1; splitData--;}
  
  else if(AdultMaleHIV19>AdultMaleHIV24 && AdultMaleHIV19> 0){ AdultMaleHIV19-=1; splitData--;}
  else if(AdultMaleHIV29> 0 ){ AdultMaleHIV29-=1; splitData--;}
  else if(AdultMaleHIV34> 0 ){ AdultMaleHIV34-=1; splitData--;}
  else if(AdultMaleHIV39> 0 ){  AdultMaleHIV39-=1; splitData--;}
  else if(AdultMaleHIV49> 0 ){ AdultMaleHIV49-=1; splitData--;}
   else if(AdultMaleHIV50> 0 ){ AdultMaleHIV50-=1; splitData--; }
  else {  System.out.println(" balancing not working for opd 19 to 24 _"+facilityname); break;}

}
 
 splitData=AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
  adderPos=0;
 while(splitData<hivpos_711_25m)
 { AdultMaleHIV34+=1; splitData++; }
 
 splitData=AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
 adderPos=0;
 while(splitData>hivpos_711_25m)
 { 

  if(AdultMaleHIV34<AdultMaleHIV29){  AdultMaleHIV29-=1; splitData--;}
  
  else if(AdultMaleHIV34<AdultMaleHIV39){ AdultMaleHIV39-=1; splitData--;}
  else if(AdultMaleHIV34<AdultMaleHIV24){  AdultMaleHIV24-=1; splitData--;}
  else if(AdultMaleHIV34>0){AdultMaleHIV34-=1; splitData--;}
  else {System.out.println(" balancing not working for opd male 25+ _"+facilityname); break;}
 }
 
 
 
 
 //tested male adults
 splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultMale){ MaleAdultTested29+=1; splitData++; }
 
 splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultMale){ MaleAdultTested29-=1; splitData--; }
 

// for child female tested 
  childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 

   adderPos=0;
while(childSplitData<TestedChildFemale){ 
if(adderPos==0){ FemaleTestedChild14+=1; }
if(adderPos==1){ FemaleTestedChild9+=1;  }
if(adderPos==2){ FemaleTestedChild4+=1;    }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildFemale){ 
 if(adderPos==0){ FemaleTestedChild14-=1;  }
if(adderPos==1){ FemaleTestedChild9-=1;   }
if(adderPos==2){FemaleTestedChild4-=1; }
childSplitData--; adderPos++  ; if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}}

 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  
   adderPos=0;
   double diff=0;
while(childSplitData<HIV_ChildFemale){ 
  diff=FemaleTestedChild14-ChildFemaleHIV14;
 if(adderPos==0){
  if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1; }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1; }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;   }
 
 }
  
 if(adderPos==1){

   if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1;    }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1;   }
 }
 if(adderPos==2){
  
   if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1;   }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1; }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){  
  if(adderPos==0){ ChildFemaleHIV14-=1;  }
 if(adderPos==1){ ChildFemaleHIV9-=1;   }
 if(adderPos==2){ ChildFemaleHIV4-=1;  }
childSplitData--;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}
   
// tested male _______________________________________________________________________
  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
while(childSplitData<TestedChildMale){ 
     if(adderPos==0){ MaleTestedChild14+=1; }
 else if(adderPos==1){ MaleTestedChild9+=1; }
 else if(adderPos==2){ MaleTestedChild4+=1;    }
 
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;} if(childSplitData==TestedChildMale){}
}

  childSplitData=MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildMale){ 
 if(adderPos==0){ MaleTestedChild14-=1;}
 else if(adderPos==1){MaleTestedChild9-=1; }
 else if(adderPos==2){MaleTestedChild4-=1; }
childSplitData--;adderPos++  ; if(adderPos>2){adderPos=0;} if(childSplitData==TestedChildMale){} }


// for child male +ve 
  childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
 
   adderPos=0;
while(childSplitData<HIV_ChildMale){ 
  if(adderPos==0){
     if(MaleTestedChild14-ChildMaleHIV14>0){ ChildMaleHIV14+=1;   
 }
     else if(MaleTestedChild9-ChildMaleHIV9>0){   ChildMaleHIV9+=1;  }
 else if(MaleTestedChild4-ChildMaleHIV4>0){ ChildMaleHIV4+=1; }
  }
 else if(adderPos==1){
 if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1;} else if(MaleTestedChild4-ChildMaleHIV4>0){  ChildMaleHIV4+=1;   } else if(MaleTestedChild14-ChildMaleHIV14>0){ChildMaleHIV14+=1;  }
    }
 if(adderPos==2){
   if(MaleTestedChild4-ChildMaleHIV4>0){ ChildMaleHIV4+=1;   }
  else if(MaleTestedChild14-ChildMaleHIV14>0){ ChildMaleHIV14+=1; }
  else if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1;   }
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildMale){}
}
 childSplitData=ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildMale){ 
    
 if(adderPos==0){ ChildMaleHIV14-=1;   }
 
 else if(adderPos==1){ ChildMaleHIV9-=1;}
 if(adderPos==2){  ChildMaleHIV4-=1; }
childSplitData--;
adderPos++  ;if(adderPos>2){adderPos=0;}if(childSplitData==HIV_ChildMale){}}


///


//  
  // all positives

//TotalPositive=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+
//        ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 +ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//  
//TotalNegative=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+
//        ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg +ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
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
  //201510
 //this code was added later
 

// TotalNegativeFemale=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
//TotalNegativeMale=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;

//                TotalTested=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested49+FemaleAdultTested50+ MaleAdultTested19+MaleAdultTested24+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
//                TotalPositiveFemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
//                TotalPositiveMale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
//               
//System.out.println(MaleTestedChild14 +" bbbbb  "+ChildMaleHIV14+"    mmmmm   "+ (MaleTestedChild14-ChildMaleHIV14));
 

        //c11.setCellValue(facilname);
//String basicDetails=county+"@"+district+"@"+facilityname+"@"+mflcode+"@"+dsdta;
       
       
//      Female      
    
  
  int neg1male=0,neg1female=0;
  int neg4male=0,neg4female=0;
  int neg9male=0,neg9female=0;
  int neg14male=0,neg14female=0;
  int neg19male=0,neg19female=0;
  int neg24male=0,neg24female=0;
   int neg29male=0,neg29female=0;
  int neg34male=0,neg34female=0;
  int neg39male=0,neg39female=0;
  int neg49male=0,neg49female=0;
  int neg50male=0,neg50female=0;
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  AdultMaleHIV29Neg=(float)Math.round(MaleAdultTested29)-(AdultMaleHIV29);
  AdultMaleHIV34Neg=(float)Math.round(MaleAdultTested34)-(AdultMaleHIV34);
  AdultMaleHIV39Neg=(float)Math.round(MaleAdultTested39)-(AdultMaleHIV39);
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);
  
  
        System.out.println("Before Normalization "+facilityname+" OPD  Neg Validation  M19="+AdultMaleHIV19Neg+" M24="+AdultMaleHIV24Neg+" M29="+AdultMaleHIV29Neg+"  M34="+AdultMaleHIV34Neg+" M39="+AdultMaleHIV39Neg+" 49="+AdultMaleHIV49Neg+" 50="+AdultMaleHIV50Neg);
  
if(AdultMaleHIV19Neg<=-1){
neg19male=1;

while(AdultMaleHIV19Neg<=-1){   System.out.println(facilityname+"___mara kadhaa kuna shida"); 
if(AdultMaleHIV24Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;
    }
    
 else if(AdultMaleHIV29Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;} 
 else if(AdultMaleHIV34Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; } 
 else if(AdultMaleHIV39Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV39Neg=AdultMaleHIV39Neg-1; } 
 else if(AdultMaleHIV49Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;} 
     
      else if(AdultMaleHIV50Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;} 
         else { System.out.println(" Negative 19 Male unsolvable"); break; }
}

}


if(AdultMaleHIV24Neg<=-1){
neg24male=1;
//try to find a stable age bracket to remove duplicates from
while(AdultMaleHIV24Neg<=-1){ if(AdultMaleHIV19Neg>0){  AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; }    
 else if(AdultMaleHIV29Neg>0){ AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;} 
 else if(AdultMaleHIV34Neg>0){ AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; } 
 else if(AdultMaleHIV39Neg>0){ AdultMaleHIV24Neg=AdultMaleHIV24Neg+1;  AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;  } 
else if(AdultMaleHIV49Neg>0){  AdultMaleHIV24Neg=AdultMaleHIV24Neg+1;  AdultMaleHIV49Neg=AdultMaleHIV49Neg-1; }
else if(AdultMaleHIV50Neg>0){ AdultMaleHIV24Neg=AdultMaleHIV24Neg+1;  AdultMaleHIV50Neg=AdultMaleHIV50Neg-1; } 
else { System.out.println(" Negative 24 Male unsolvable");  break;}


}

}





if(AdultMaleHIV29Neg<=-1)
{
neg29male=1;

while(AdultMaleHIV29Neg<=-1){
 if(AdultMaleHIV19Neg>0){ AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; }
 else if(AdultMaleHIV24Neg>0){  AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;  }
else if(AdultMaleHIV34Neg>0){  AdultMaleHIV29Neg=AdultMaleHIV29Neg+1;  AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; } 
 else if(AdultMaleHIV39Neg>0){ AdultMaleHIV29Neg=AdultMaleHIV29Neg+1;  AdultMaleHIV39Neg=AdultMaleHIV39Neg-1; } 
else if(AdultMaleHIV49Neg>0){  AdultMaleHIV29Neg=AdultMaleHIV29Neg+1;  AdultMaleHIV49Neg=AdultMaleHIV49Neg-1; } 
else if(AdultMaleHIV50Neg>0){  AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; AdultMaleHIV50Neg=AdultMaleHIV50Neg-1; } 
else { System.out.println(" Negative 29 male unsolvable");  break;}
        
}

}

if(AdultMaleHIV34Neg<=-1)
{
neg34male=1;
   while(AdultMaleHIV34Neg<=-1){    

        if(AdultMaleHIV19Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV19Neg=AdultMaleHIV19Neg-1;
                               }
        else if(AdultMaleHIV24Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;
    }
    else if(AdultMaleHIV29Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;
    } 
//     else if(AdultMaleHIV34Neg>0){
//    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
//    AdultMaleHIV34Neg=AdultMaleHIV34Neg-1;
//    } 
 else if(AdultMaleHIV39Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;
                             } 

 else if(AdultMaleHIV49Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;
                             } 
        
         else if(AdultMaleHIV50Neg>0){
    AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;
    AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;
                                     } 
         else {
         
            System.out.println(" Negative 34 male unsolvable");
            break;
         }
    
}
  
    System.out.println(" Positive is more than tested");
   
}

if(AdultMaleHIV39Neg<=-1){
neg39male=1;

 while(AdultMaleHIV39Neg<=-1){
if(AdultMaleHIV19Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; }
else if(AdultMaleHIV24Neg>0){  AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;}
else if(AdultMaleHIV29Neg>0){   AdultMaleHIV39Neg=AdultMaleHIV39Neg+1;  AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;} 
else if(AdultMaleHIV34Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; AdultMaleHIV34Neg=AdultMaleHIV34Neg-1;} 
else if(AdultMaleHIV49Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg+1;AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;} 
else if(AdultMaleHIV50Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg+1;AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;} 
else {System.out.println(" Negative 39 male unsolvable");break;}
        
}

}

if(AdultMaleHIV49Neg<=-1){
neg49male=1;

while(AdultMaleHIV49Neg<=-1){    
if(AdultMaleHIV39Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg+1; AdultMaleHIV19Neg=AdultMaleHIV19Neg-1;}
else if(AdultMaleHIV24Neg>0){AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;}
else if(AdultMaleHIV29Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;} 
else if(AdultMaleHIV34Neg>0){AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;AdultMaleHIV34Neg=AdultMaleHIV34Neg-1;} 
else if(AdultMaleHIV39Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg+1; AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;} 
else if(AdultMaleHIV50Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg+1; AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;} 
else {System.out.println(" Negative 49 male unsolvable");break;}
        
}

}

if(AdultMaleHIV50Neg<=-1){
neg50male=1;

while(AdultMaleHIV50Neg<=-1){    
if(AdultMaleHIV39Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg+1; AdultMaleHIV19Neg=AdultMaleHIV19Neg-1;}
else if(AdultMaleHIV24Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;}
 else if(AdultMaleHIV29Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;} 
else if(AdultMaleHIV34Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;AdultMaleHIV34Neg=AdultMaleHIV34Neg-1;} 
 else if(AdultMaleHIV39Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;} 
else if(AdultMaleHIV49Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;} 
else {System.out.println(" Negative 50 male unsolvable"); break;}
        
}

}

 // child male negatives
  ChildMaleHIV1Neg=(float)Math.round(MaleTestedChild1)-(ChildMaleHIV1);
  ChildMaleHIV4Neg=(float)Math.round(MaleTestedChild4)-(ChildMaleHIV4);
  ChildMaleHIV9Neg=(float)Math.round(MaleTestedChild9)-(ChildMaleHIV9);
  ChildMaleHIV14Neg=(float)Math.round(MaleTestedChild14)-(ChildMaleHIV14);

if(ChildMaleHIV1Neg<=-1){neg1male=1;}
if(ChildMaleHIV4Neg<=-1){neg4male=1;}
if(ChildMaleHIV9Neg<=-1){neg9male=1;}
if(ChildMaleHIV14Neg<=-1){neg14male=1;}
//negative

ChildFemaleHIV1Neg=(float)Math.round(FemaleTestedChild1)-(ChildFemaleHIV1);
ChildFemaleHIV4Neg=(float)Math.round(FemaleTestedChild4)-(ChildFemaleHIV4);
ChildFemaleHIV9Neg=(float)Math.round(FemaleTestedChild9)-(ChildFemaleHIV9);
ChildFemaleHIV14Neg=(float)Math.round(FemaleTestedChild14)-(ChildFemaleHIV14);

if(ChildFemaleHIV1Neg<=-1){neg1female=1;}
if(ChildFemaleHIV4Neg<=-1){neg4female=1;}
if(ChildFemaleHIV9Neg<=-1){neg9female=1;}
if(ChildFemaleHIV14Neg<=-1){neg14female=1;} 

System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);
AdultFemaleHIV29Neg=(float)Math.round(FemaleAdultTested29)-(AdultFemaleHIV29);
AdultFemaleHIV34Neg=(float)Math.round(FemaleAdultTested34)-(AdultFemaleHIV34);
AdultFemaleHIV39Neg=(float)Math.round(FemaleAdultTested39)-(AdultFemaleHIV39);
AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);
  
if(AdultFemaleHIV19Neg<=-1){neg19female=1;}
if(AdultFemaleHIV24Neg<=-1){neg24female=1;}
if(AdultFemaleHIV29Neg<=-1){neg29female=1;}
if(AdultFemaleHIV34Neg<=-1){neg34female=1;}
if(AdultFemaleHIV39Neg<=-1){neg39female=1;}
if(AdultFemaleHIV49Neg<=-1){neg49female=1;}
if(AdultFemaleHIV50Neg<=-1){neg50female=1;} 

if(AdultFemaleHIV19Neg<=-1){
neg19female=1;
while(AdultFemaleHIV19Neg<=-1){    
    System.out.println(facilityname+"___mara kadhaa kuna shida");
if(AdultFemaleHIV24Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;}
else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;} 
else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;} 
 else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;} 
else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;} 
else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
else {System.out.println(" Negative 19 Female unsolvable");break;}
}

}

if(AdultFemaleHIV24Neg<=-1){
neg24female=1;
//try to find a stable age bracket to remove duplicates from
while(AdultFemaleHIV24Neg<=-1){
    if(AdultFemaleHIV19Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
   else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;} 
     else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;} 
 else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;} 
else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;}
 else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
         else {System.out.println(" Negative 24 Female unsolvable");break;}


}

}

if(AdultFemaleHIV29Neg<=-1)
{
neg29female=1;
while(AdultFemaleHIV29Neg<=-1){
if(AdultFemaleHIV19Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;}
else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;} 
else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;} 
else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;} 
else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
else {System.out.println(" Negative 29 Female unsolvable");break;}
        
}

}

if(AdultFemaleHIV34Neg<=-1)
{
neg34female=1;
   while(AdultFemaleHIV34Neg<=-1){
if(AdultFemaleHIV19Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;}   
else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;} 
else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;} 
else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;} 
 else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
else {System.out.println(" Negative 34 Female unsolvable");break;}
   }  
   System.out.println(" Positive is more than tested");  }

if(AdultFemaleHIV39Neg<=-1){
neg39female=1;
 while(AdultFemaleHIV39Neg<=-1){
if(AdultFemaleHIV19Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
 else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; }
else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;} 
     else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;} 
else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;} 
else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
else { System.out.println(" Negative 39 Female unsolvable");break;}
        
}


}

if(AdultFemaleHIV49Neg<=-1){
neg49female=1;
while(AdultFemaleHIV49Neg<=-1){    
if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
else if(AdultFemaleHIV24Neg>0){  AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;}
else if(AdultFemaleHIV29Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;} 
else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;} 
else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;} 
else if(AdultFemaleHIV50Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;} 
         else {System.out.println(" Negative 49 Female unsolvable");break;}
        
}

}

if(AdultFemaleHIV50Neg<=-1){
neg50female=1;
while(AdultFemaleHIV50Neg<=-1){    
if(AdultFemaleHIV39Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;}
else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;}
else if(AdultFemaleHIV29Neg>0){   AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;  AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;  } 
else if(AdultFemaleHIV34Neg>0) {  AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;  AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1; } 
else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;  } 
else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;} 
        
 
         else {System.out.println(" Negative 50 Female unsolvable");break;}
        
}

}
double TotalNegativeFemale1=0;
 double TotalNegativeMale1=0;
TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV29Neg+AdultFemaleHIV34Neg+AdultFemaleHIV39Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV29Neg+AdultMaleHIV34Neg+AdultMaleHIV39Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
// negative female
  checkdiff2=negfem-TotalNegativeFemale1;
 if(checkdiff2>2 ||checkdiff2<-2){redalert2=1;}
 // negativemale
   checkdiff3=negmale-TotalNegativeMale1;
 if(checkdiff3>2 ||checkdiff3<-2){redalert3=1;}
 
 
   int balancingvalue=(int) (conn.rs.getInt("711_totaltested")-(ipd_tes_balancing+tested_new711+vct_tes_balancing));
        tested_new711+=balancingvalue;//add the missing value that you got when you deducted vct+opd+IPD from HTC in moh731 
        AdultMaleHIV24Neg+=balancingvalue;//add the missing value that you got when you deducted vct+opd+IPD from HTC in moh731 to male 20-25 negative 
 
double posotiveyote= ChildFemaleHIV1+ChildMaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildMaleHIV4+ChildMaleHIV9+ChildFemaleHIV14+ChildMaleHIV14+AdultFemaleHIV19+AdultMaleHIV19+AdultFemaleHIV24+AdultMaleHIV24+AdultFemaleHIV29+AdultMaleHIV29+AdultFemaleHIV34+AdultMaleHIV34+AdultFemaleHIV39+AdultMaleHIV39+AdultFemaleHIV49+AdultMaleHIV49+AdultFemaleHIV50+AdultMaleHIV50;

   //double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f);
double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(posotiveyote);
   //711_totalpositive 
    
    
    
       System.out.println("POSITIVE VALIDATION _OPD: "+facilityname+" Total positive :"+(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))+"-"+(posotiveyote)+"="+balancegeneral);
   
       
while(balancegeneral<0){
   
     if(AdultFemaleHIV34>0){ AdultFemaleHIV34-=1;  AdultFemaleHIV34Neg+=1;  balancegeneral++; }
     else if(AdultFemaleHIV49>0){ AdultFemaleHIV49-=1; AdultFemaleHIV49Neg+=1;  balancegeneral++; }
    else if(AdultFemaleHIV29>0) {AdultFemaleHIV29-=1; AdultFemaleHIV29Neg+=1;  balancegeneral++;}
    else if(AdultFemaleHIV24>0) {AdultFemaleHIV24-=1; AdultFemaleHIV24Neg+=1; balancegeneral++;}
    else if(AdultFemaleHIV19>0) { AdultFemaleHIV19-=1; AdultFemaleHIV19Neg+=1; balancegeneral++;}
    else if(ChildFemaleHIV14>0) { ChildFemaleHIV14-=1; ChildFemaleHIV14Neg+=1; balancegeneral++;}
    else if(ChildFemaleHIV9>0) { ChildFemaleHIV9-=1; ChildFemaleHIV9Neg+=1;  balancegeneral++;}
   
    //if female cant help, then use male
    else if(AdultMaleHIV24>0) {  AdultMaleHIV24-=1; AdultMaleHIV24Neg+=1;  balancegeneral++; }
    else if(AdultMaleHIV29>0) {  AdultMaleHIV29-=1; AdultMaleHIV29Neg+=1;  balancegeneral++; }
    else if(AdultMaleHIV34>0){ AdultMaleHIV34-=1;  AdultMaleHIV34Neg+=1;   balancegeneral++;    }
    else if(AdultMaleHIV49>0){ AdultMaleHIV49-=1; AdultMaleHIV49Neg+=1;   balancegeneral++;    }
    else if(AdultMaleHIV39>0){ AdultMaleHIV39-=1;  AdultMaleHIV39Neg+=1;   balancegeneral++;    }
    else{System.out.println("  balancing not working for opd at last level "+facilityname);break;}

  
                 }

while(balancegeneral>0){
    System.out.println(" endless loop line number 7261");
 //add to pos and add deduct from negative since the negatives were achieved from tested-positive
 if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49+=1;AdultFemaleHIV49Neg-=1;balancegeneral--;}
 else if (AdultFemaleHIV39Neg>0){AdultFemaleHIV39+=1;AdultFemaleHIV39Neg-=1;balancegeneral--;}
 else if (AdultFemaleHIV34Neg>0){ AdultFemaleHIV34+=1;AdultFemaleHIV34Neg-=1;balancegeneral--;}
 else if (AdultFemaleHIV29Neg>0){AdultFemaleHIV29+=1;AdultFemaleHIV29Neg-=1;balancegeneral--;}
 else if (AdultFemaleHIV24Neg>0){AdultFemaleHIV24+=1;AdultFemaleHIV24Neg-=1;balancegeneral--;} 
 else if (AdultFemaleHIV19Neg>0){AdultFemaleHIV19+=1; AdultFemaleHIV19Neg-=1;balancegeneral--;} 
  else if (ChildFemaleHIV14Neg>0){  ChildFemaleHIV14+=1;ChildFemaleHIV14Neg-=1;balancegeneral--;}
  else if (ChildFemaleHIV9Neg>0){ChildFemaleHIV9+=1;ChildFemaleHIV9Neg-=1;balancegeneral--;}
  else { System.out.println("global Positive unsolvable "+facilityname); break;}
 }

if(balancegeneral!=0){  System.out.println("This facility has refused to balance "+facilityname);}
double posotiveyoteglobal= (int) Math.round((conn.rs.getInt("711_totalpositive")*ipdpositiveratio))+(int) Math.round((conn.rs.getInt("711_totalpositive")*opdpositiveratio))+(int) Math.round((conn.rs.getInt("711_totalpositive")*vctpositiveratio));
double balancegeneralglobal=(int) Math.round((conn.rs.getInt("711_totalpositive")))-(posotiveyoteglobal);

 System.out.println(facilityname+" Before global positive accuracy = " +balancegeneralglobal+" = "+(int) Math.round((conn.rs.getInt("711_totalpositive")))+" - "+posotiveyoteglobal);
  

   //711_totalpositive 
while(balancegeneralglobal<0){
    
         if(AdultFemaleHIV49>0){AdultFemaleHIV49-=1;AdultFemaleHIV49Neg+=1; balancegeneralglobal++;}
    else if(AdultFemaleHIV39>0){AdultFemaleHIV39-=1; AdultFemaleHIV39Neg+=1; balancegeneralglobal++;}
    else if(AdultFemaleHIV34>0){AdultFemaleHIV34-=1; AdultFemaleHIV34Neg+=1; balancegeneralglobal++;}
     else if(AdultFemaleHIV29>0){AdultFemaleHIV29-=1;AdultFemaleHIV29Neg+=1; balancegeneralglobal++;}
     else if(AdultFemaleHIV24>0) {AdultFemaleHIV24-=1;AdultFemaleHIV24Neg+=1;balancegeneralglobal++;}    
     else if(AdultFemaleHIV19>0) {AdultFemaleHIV19-=1; AdultFemaleHIV19Neg+=1;balancegeneralglobal++;}    
     else if(ChildFemaleHIV14>0) {ChildFemaleHIV14-=1;ChildFemaleHIV14Neg+=1;balancegeneralglobal++;}    
    else if(ChildFemaleHIV9>0) {ChildFemaleHIV9-=1;ChildFemaleHIV9Neg+=1; balancegeneralglobal++;}
    
    //male
       else if(AdultMaleHIV49>0){AdultMaleHIV49-=1;AdultMaleHIV49Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV39>0){AdultMaleHIV39-=1; AdultMaleHIV39Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV34>0){AdultMaleHIV34-=1; AdultMaleHIV34Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV29>0){AdultMaleHIV29-=1;AdultMaleHIV29Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV24>0) {AdultMaleHIV24-=1;AdultMaleHIV24Neg+=1;balancegeneralglobal++;}    
    else if(AdultMaleHIV19>0) {AdultMaleHIV19-=1; AdultMaleHIV19Neg+=1;balancegeneralglobal++;}    
    else if(ChildMaleHIV14>0) {ChildMaleHIV14-=1;ChildMaleHIV14Neg+=1;balancegeneralglobal++;}    
    else if(ChildMaleHIV9>0) {ChildMaleHIV9-=1;ChildMaleHIV9Neg+=1; balancegeneralglobal++;}
    
    
    else { System.out.println(" Negative global Positive unsolvable ");  break;}
  
                 }

while(balancegeneralglobal>0){
 //add to pos and add deduct from negative since the negatives were achieved from tested-positive
 if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV49+=1;  AdultFemaleHIV49Neg-=1; balancegeneralglobal--;  }
 
 else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39+=1; AdultFemaleHIV39Neg-=1; balancegeneralglobal--;  }
 
  else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV34+=1; AdultFemaleHIV34Neg-=1; balancegeneralglobal--;  }
 else if( AdultFemaleHIV29Neg>0){AdultFemaleHIV29+=1;AdultFemaleHIV29Neg-=1; balancegeneralglobal--;}
  else if(AdultFemaleHIV24Neg>0) { AdultFemaleHIV24+=1; AdultFemaleHIV24Neg-=1; balancegeneralglobal--;  }
   else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19+=1; AdultFemaleHIV19Neg-=1; balancegeneralglobal--; }
  else if(ChildFemaleHIV14Neg>0){ ChildFemaleHIV14+=1; ChildFemaleHIV14Neg-=1; balancegeneralglobal--; }
  else if(ChildFemaleHIV9Neg>0){ChildFemaleHIV9+=1; ChildFemaleHIV9Neg-=1; balancegeneralglobal--;  }
  else { System.out.println(" global Positive unsolvable "); break;}
  }


 System.out.println(facilityname+" After global positive accuracy = " +balancegeneralglobal+" = "+(int) Math.round((conn.rs.getInt("711_totalpositive")))+" - "+posotiveyoteglobal);
 
//============================================================================
        
     

 double pmtct_n_14=(float)Math.round(pmtct_t_14)-(pmtct_p_14);
 double pmtct_n_19= (float)Math.round(pmtct_t_19)-(pmtct_p_19);
 double pmtct_n_24= (float)Math.round(pmtct_t_24)-(pmtct_p_24);
 double pmtct_n_29= (float)Math.round(pmtct_t_29)-(pmtct_p_29);
 double pmtct_n_34= (float)Math.round(pmtct_t_34)-(pmtct_p_34);
 double pmtct_n_39= (float)Math.round(pmtct_t_39)-(pmtct_p_39);
 double pmtct_n_49=(float)Math.round(pmtct_t_49)-(pmtct_p_49);
 double pmtct_n_50= (float)Math.round(pmtct_t_50)-(pmtct_p_50);

       // System.out.println(facilityname+" pmtct data accuracy" +pmtct_t+" = "+pmtct_n_14+" | "+pmtct_n_19+" | "+pmtct_n_24+" | "+pmtct_n_29+" | "+pmtct_n_34+" | "+pmtct_n_39+" | "+pmtct_n_49+" | "+pmtct_n_50);
       // System.out.println(facilityname+" pmtct data accuracy" +pmtct_p+" = "+pmtct_p_14+" | "+pmtct_p_19+" | "+pmtct_p_24+" | "+pmtct_p_29+" | "+pmtct_p_34+" | "+pmtct_p_39+" | "+pmtct_p_49+" | "+pmtct_p_50);
       // System.out.println(facilityname+" pmtct data accuracy" +pmtct_p+" = "+pmtct_t_14+" | "+pmtct_t_19+" | "+pmtct_t_24+" | "+pmtct_t_29+" | "+pmtct_t_34+" | "+pmtct_t_39+" | "+pmtct_t_49+" | "+pmtct_t_50);
       
  String alldatavals[]={county,district,facilityname,""+mflcode,dsdta
        ,"0","0"
        ,""+ChildFemaleHIV1,""+ChildMaleHIV1
        ,"0","0"//transfered to Pediatrics
        ,""+(ChildFemaleHIV9),""+(ChildMaleHIV9)
        ,""+(ChildFemaleHIV14+(pmtct_p_14)),""+ChildMaleHIV14
        ,""+(AdultFemaleHIV19+(pmtct_p_19)),""+AdultMaleHIV19
        ,""+(AdultFemaleHIV24+(pmtct_p_24)),""+AdultMaleHIV24
        ,""+(AdultFemaleHIV29+(pmtct_p_29)),""+AdultMaleHIV29
        ,""+(AdultFemaleHIV34+(pmtct_p_34)),""+AdultMaleHIV34
        ,""+(AdultFemaleHIV39+(pmtct_p_39)),""+AdultMaleHIV39
        ,""+(AdultFemaleHIV49+(pmtct_p_49)),""+AdultMaleHIV49
        ,""+(AdultFemaleHIV50+(pmtct_p_50)),""+AdultMaleHIV50        
         //negative starts here 
        ,"0","0"
        ,""+ChildFemaleHIV1Neg,""+ChildMaleHIV1Neg
        ,"0","0"//transfered to Pediatrics
        ,""+(ChildFemaleHIV9Neg),""+(ChildMaleHIV9Neg)
        ,""+(ChildFemaleHIV14Neg+(pmtct_n_14)),""+ChildMaleHIV14Neg
        ,""+(AdultFemaleHIV19Neg+(pmtct_n_19)),""+AdultMaleHIV19Neg
        ,""+(AdultFemaleHIV24Neg+(pmtct_n_24) ),""+AdultMaleHIV24Neg
          
        ,""+(AdultFemaleHIV29Neg+(pmtct_n_29)),""+AdultMaleHIV29Neg
        ,""+(AdultFemaleHIV34Neg+(pmtct_n_34)),""+AdultMaleHIV34Neg
        ,""+(AdultFemaleHIV39Neg+(pmtct_n_39)),""+AdultMaleHIV39Neg
        ,""+(AdultFemaleHIV49Neg+(pmtct_n_49)),""+AdultMaleHIV49Neg
        ,""+(AdultFemaleHIV50Neg+(pmtct_n_50)),""+AdultMaleHIV50Neg,""+(((tested_new711)-(ChildMaleHIV4Neg+ChildMaleHIV4+ChildFemaleHIV4Neg+ChildFemaleHIV4))+(pmtct_t)),(ChildMaleHIV4Neg+ChildMaleHIV4+ChildFemaleHIV4Neg+ChildFemaleHIV4+conn.rs.getInt("serology"))+"",(ChildMaleHIV4+ChildFemaleHIV4)+"",(ChildMaleHIV4Neg+ChildFemaleHIV4Neg+conn.rs.getInt("serology"))+"",""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")
          
        }; 
//the above numbers appearing in serology are for 1-4



  opdhm.put("cty"+"_"+mflcode, county);
  opdhm.put("sty"+"_"+mflcode, district);
  opdhm.put("fac"+"_"+mflcode, facilityname);
  opdhm.put("mfl"+"_"+mflcode, ""+mflcode);
  opdhm.put("st"+"_"+mflcode, dsdta);
  opdhm.put("ukpf"+"_"+mflcode,"0");
  opdhm.put("ukpm"+"_"+mflcode,"0");
  opdhm.put("pf1"+"_"+mflcode,""+(ChildFemaleHIV1 ));//serology added to female
  opdhm.put("pm1"+"_"+mflcode,""+ChildMaleHIV1);
  opdhm.put("pf4"+"_"+mflcode,""+ChildFemaleHIV4);
  opdhm.put("pm4"+"_"+mflcode,""+ChildMaleHIV4);
  opdhm.put("pf9"+"_"+mflcode,""+ChildFemaleHIV9);
  opdhm.put("pm9"+"_"+mflcode,""+ChildMaleHIV9);
  opdhm.put("pf14"+"_"+mflcode,""+(ChildFemaleHIV14+pmtct_p_14));
  opdhm.put("pm14"+"_"+mflcode,""+ChildMaleHIV14);
  opdhm.put("pf19"+"_"+mflcode,""+(AdultFemaleHIV19+pmtct_p_19));
  opdhm.put("pm19"+"_"+mflcode,""+AdultMaleHIV19);
  opdhm.put("pf24"+"_"+mflcode,""+(AdultFemaleHIV24+pmtct_p_24));
  opdhm.put("pm24"+"_"+mflcode,""+AdultMaleHIV24);
  opdhm.put("pf29"+"_"+mflcode,""+(AdultFemaleHIV29+pmtct_p_29));
  opdhm.put("pm29"+"_"+mflcode,""+AdultMaleHIV29);
  opdhm.put("pf34"+"_"+mflcode,""+(AdultFemaleHIV34+pmtct_p_34));
  opdhm.put("pm34"+"_"+mflcode,""+AdultMaleHIV34);
  opdhm.put("pf39"+"_"+mflcode,""+(AdultFemaleHIV39+pmtct_p_39));
  opdhm.put("pm39"+"_"+mflcode,""+AdultMaleHIV39);
  opdhm.put("pf49"+"_"+mflcode,""+(AdultFemaleHIV49+pmtct_p_49));
  opdhm.put("pm49"+"_"+mflcode,""+AdultMaleHIV49);
  opdhm.put("pf50"+"_"+mflcode,""+(AdultFemaleHIV50+pmtct_p_50));
  opdhm.put("pm50"+"_"+mflcode,""+AdultMaleHIV50);
  //_____negative
  opdhm.put("uknf"+"_"+mflcode,"0");
  opdhm.put("uknm"+"_"+mflcode,"0");
  opdhm.put("nf1"+"_"+mflcode,""+ChildFemaleHIV1Neg);
  opdhm.put("nm1"+"_"+mflcode,""+(ChildMaleHIV1Neg+ conn.rs.getInt("serology")));
  opdhm.put("nf4"+"_"+mflcode,""+ChildFemaleHIV4Neg);
  opdhm.put("nm4"+"_"+mflcode,""+ChildMaleHIV4Neg);
  opdhm.put("nf9"+"_"+mflcode,""+ChildFemaleHIV9Neg);
  opdhm.put("nm9"+"_"+mflcode,""+ChildMaleHIV9Neg);
  opdhm.put("nf14"+"_"+mflcode,""+(ChildFemaleHIV14Neg+pmtct_n_14));
  opdhm.put("nm14"+"_"+mflcode,""+ChildMaleHIV14Neg);
  opdhm.put("nf19"+"_"+mflcode,""+(AdultFemaleHIV19Neg+pmtct_n_19));
  opdhm.put("nm19"+"_"+mflcode,""+AdultMaleHIV19Neg);
  opdhm.put("nf24"+"_"+mflcode,""+(AdultFemaleHIV24Neg+pmtct_n_24));
  opdhm.put("nm24"+"_"+mflcode,""+AdultMaleHIV24Neg);
  opdhm.put("nf29"+"_"+mflcode,""+(AdultFemaleHIV29Neg+pmtct_n_29));
  opdhm.put("nm29"+"_"+mflcode,""+AdultMaleHIV29Neg);
  opdhm.put("nf34"+"_"+mflcode,""+(AdultFemaleHIV34Neg+pmtct_n_34));
  opdhm.put("nm34"+"_"+mflcode,""+AdultMaleHIV34Neg);
  opdhm.put("nf39"+"_"+mflcode,""+(AdultFemaleHIV39Neg+pmtct_n_39));
  opdhm.put("nm39"+"_"+mflcode,""+AdultMaleHIV39Neg);
  opdhm.put("nf49"+"_"+mflcode,""+(AdultFemaleHIV49Neg+pmtct_n_49));
  opdhm.put("nm49"+"_"+mflcode,""+AdultMaleHIV49Neg);
  opdhm.put("nf50"+"_"+mflcode,""+(AdultFemaleHIV50Neg+pmtct_n_50));
  opdhm.put("nm50"+"_"+mflcode,""+AdultMaleHIV50Neg);
  opdhm.put("tt"+"_"+mflcode,""+((tested_new711)+(pmtct_t)+(conn.rs.getInt("serology"))));





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
    
 
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  rwx=shet3.createRow(count);  
 rwx.setHeightInPoints(23);  
 
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
   cellsubcounty.setCellStyle(stborder); }
  else if(z==2){
   //facility
   HSSFCell cellfacil=rwx.createCell(2); 
   cellfacil.setCellValue(staticfacility.get(a).toString());
   cellfacil.setCellStyle(stborder);
  }
  else if(z==3){
   //mfl
   HSSFCell cellmfl=rwx.createCell(3); 
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
        }
   //last section of blank rows
  else if(z==pitc_ipd_header1.length-5){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticart_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(statichtc_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
   
        }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticpmtct_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticishtc.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticispmtct.get(a).toString()));
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
	 
                     
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                for (int e = 0; e < 4; e++) {
                shet3.autoSizeColumn(e);
                }
                //Made my life veery simple...
                shet3.setDisplayGridlines(false);
                shet3.createFreezePane(5,5);                 

         return opdhm;          
     
 }
    
    
    
    
    public HashMap Vct(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException {
        
        
      HashMap<String , String> vcthm = new HashMap<String,String>();  
                    
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
    
        
    
     //new htc for PITC 
                  
                     isOPD=false;
                     isIPD=false;
                     isVCT=true;
                    //2017
                     String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total VCT Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total VCT Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total VCT Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                   
              int less15m=0;
              int less15f=0;
              int gret15m=0;
              int gret15f=0;
              
              
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
           subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    
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
         
         subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1  and  ";//20160711
         
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
        
      HSSFSheet shet3=wb.createSheet("VCT");   
            HSSFCell  c11;
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

  shet3.setColumnWidth(0, 4000);  
  shet3.setColumnWidth(1, 5000);  
  shet3.setColumnWidth(2,5000);  
  //shet3.setColumnWidth(6,5000);
  
  
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
    String mergeinfor[]={"0,0,0,58","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,2,5,28","2,2,29,52","2,4,53,53","2,4,54,54","2,4,55,55","2,4,56,56","3,3,5,6","3,3,29,30","2,4,57,57","2,4,58,58"};  
   
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
    TestedAdultMale=0;TestedAdultFemale=0;
 TestedChildMale=0;TestedChildFemale=0;
 HIV_AdultMale=0;HIV_AdultFemale=0;
 HIV_ChildMale=0;HIV_ChildFemale=0;
    
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
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".HTC_Support1 as htcsupport, IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 or PMTCT=1)  AND "+facilitiestable+".active=1  group by "+facilitiestable+".SubPartnerID  "; 
    
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
   
                
  String countyidcopy="0",countyid="0";

double Tm=0,Tf=0; //Tested male, Tested female

//by gender by age breakdown variables
double Tmc=0,Tma=0,Tfc=0,Tfa=0; //Tested male children, Tested male adult, tested female children, tested female adult. 
//Tested and Positive fine age	 
    double Tf1=0,Tm1=0,Pf1=0,Pm1=0;
    double Tf4=0,Tm4=0,Pf4=0,Pm4=0; //may or may not be there
    double Tf9=0,Tm9=0,Pf9=0,Pm9=0;
    double Tf14=0,Tm14=0,Pf14=0,Pm14=0;
    double Tf19=0,Tm19=0,Pf19=0,Pm19=0;
    double Tf24=0,Tm24=0,Pf24=0,Pm24=0;
    double Tf29=0,Tm29=0,Pf29=0,Pm29=0;
    double Tf34=0,Tm34=0,Pf34=0,Pm34=0;
    double Tf39=0,Tm39=0,Pf39=0,Pm39=0;
    double Tf49=0,Tm49=0,Pf49=0,Pm49=0;
    double Tf50=0,Tm50=0,Pf50=0,Pm50=0;
          
    String get731data="SELECT "
            + " sum(HV0103) as 711_totaltested, "
            + " sum(HV0110) as 711_less15m ,"
            + " sum(HV0111) as 711_less15f ,"
            + " sum(HV0112) as 711_15_24m ,"
            + " sum(HV0113) as 711_15_24f ,"
            + " sum(HV0114) as 711_25m ,"
            + " sum(HV0115) as 711_25f ,"
            + " sum(HV0110+HV0111+HV0112+HV0113+HV0114+HV0115) as 711_totalpositive ," //updated in 201607            
            + " county.County,district.DistrictNom,"
            + ""+facilitiestable+".SubPartnerNom,"+facilitiestable+".CentreSanteId,"+facilitiestable+".HTC_Support1 ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
           +" ,sum( HV0103 + HV0201 ) as NUM,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT "//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
            + " ,county.CountyID as CountyID FROM moh731 JOIN "+facilitiestable+" "
            + "ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && ("+facilitiestable+".HTC=1 or PMTCT=1)  AND "+facilitiestable+".active=1   "
            + "GROUP BY moh731.SubPartnerID order by county.County";
    
    
     System.out.println("2017q1 IPD : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
    while(conn.rs.next()){
     
       //f_29	f_34	f_39
//_______new age brackets added in 201710_______
double FemaleAdultTested29=0;
double FemaleAdultTested34=0;
double FemaleAdultTested39=0;

double AdultFemaleHIV29Neg=0;
double AdultFemaleHIV34Neg=0;
double AdultFemaleHIV39Neg=0;

double AdultFemaleHIV29=0;
double AdultFemaleHIV34=0;
double AdultFemaleHIV39=0;

//male 

 double  MaleAdultTested29=0;
 double  MaleAdultTested34=0;
 double  MaleAdultTested39=0;
 
 double AdultMaleHIV29Neg=0;
  double AdultMaleHIV34Neg=0;
  double AdultMaleHIV39Neg=0;
  
   double  AdultMaleHIV29=0;
 double  AdultMaleHIV34=0;
 double  AdultMaleHIV39=0;

    //master county changes. if the countycopy is different from the previous version, then fetch all variables for the current county
    
     countyid=conn.rs.getString("CountyID");
	 if(!countyid.equals(countyidcopy)){
         //change countyidcopy to be same as countyid
         countyidcopy=countyid;
         //now fetch the variables from proportions table
         
         
         String getratios=" select * from htc_proportion where isactive='1' and county_id='"+countyid+"'";
         conn.rs4=conn.st4.executeQuery(getratios);
         while(conn.rs4.next()){
         String indicator=conn.rs4.getString("indicator");
           
           if(indicator.equals("tested by gender")){
                 
           Tm=conn.rs4.getDouble("male_or_child");
           Tf =conn.rs4.getDouble("female_or_adult");    
             }
            
           if(indicator.equals("tested by broad age female")){
             Tfc=conn.rs4.getDouble("male_or_child");
             Tfa=conn.rs4.getDouble("female_or_adult");
             } 
            
             if(indicator.equals("tested by broad age male"))
             {
             Tmc=conn.rs4.getDouble("male_or_child");
             Tma=conn.rs4.getDouble("female_or_adult");
             }
            
              if(indicator.equals("tested by fine age")){
             
    Tf1=conn.rs4.getDouble("f_1"); Tm1=conn.rs4.getDouble("m_1");
    Tf4=conn.rs4.getDouble("f_4");Tm4=conn.rs4.getDouble("m_4");
    Tf9=conn.rs4.getDouble("f_9");Tm9=conn.rs4.getDouble("m_9");
    Tf14=conn.rs4.getDouble("f_14");Tm14=conn.rs4.getDouble("m_14");
    Tf19=conn.rs4.getDouble("f_19");Tm19=conn.rs4.getDouble("m_19");
    Tf24=conn.rs4.getDouble("f_24");Tm24=conn.rs4.getDouble("m_24");
    Tf29=conn.rs4.getDouble("f_29");Tm29=conn.rs4.getDouble("m_29");
    Tf34=conn.rs4.getDouble("f_34");Tm34=conn.rs4.getDouble("m_34");
    Tf39=conn.rs4.getDouble("f_39");Tm39=conn.rs4.getDouble("m_39");
    Tf49=conn.rs4.getDouble("f_49");Tm49=conn.rs4.getDouble("m_49");
    Tf50=conn.rs4.getDouble("f_50");Tm50=conn.rs4.getDouble("m_50");
 
                                                          } 
             
             
             if(indicator.equals("positive by fine age")){
   Pf1=conn.rs4.getDouble("f_1");Pm1=conn.rs4.getDouble("m_1");
   Pf4=conn.rs4.getDouble("f_4");Pm4=conn.rs4.getDouble("m_4");
   Pf9=conn.rs4.getDouble("f_9");Pm9=conn.rs4.getDouble("m_9");
   Pf14=conn.rs4.getDouble("f_14");Pm14=conn.rs4.getDouble("m_14");
   Pf19=conn.rs4.getDouble("f_19");Pm19=conn.rs4.getDouble("m_19");
   Pf24=conn.rs4.getDouble("f_24");Pm24=conn.rs4.getDouble("m_24");
   Pf29=conn.rs4.getDouble("f_29");Pm29=conn.rs4.getDouble("m_29");
   Pf34=conn.rs4.getDouble("f_34");Pm34=conn.rs4.getDouble("m_34");
   Pf39=conn.rs4.getDouble("f_39");Pm39=conn.rs4.getDouble("m_39");
   Pf49=conn.rs4.getDouble("f_49");Pm49=conn.rs4.getDouble("m_49");
   Pf50=conn.rs4.getDouble("f_50");Pm50=conn.rs4.getDouble("m_50"); 
  } 
                 
             }//end of while 
             
         }//end of if
         
    
 
         
	 //-------------------------------fetch proportions -----
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
       //NEW CHANGES 201701  CHANGING DATA OUTPUT into the new datim format 
       //Here Ratios are being applied
       //isARTsite
       double htctestedratio=1;
       double htcpositiveratio=1;
      
       
       //____Discard the suggestion below. it was disregarded_____
       //NOTE ..TB TESTED DATA WILL NOW BE 1 % OF OPD DATA
       // TB POS will be 33% of the tested, if only there is some positive data in the opd section..
       //note, this will only happen on the art sites, otherwise the opd data will not be edited
	 
	
      //do a query that calculates the sites supporting inpatient and outpatient for the last six months
      //since we are already in a loop, see if the current site calculates 
      // String issiteipd=" select sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot from moh711 where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ( yearmonth between '201510' and '201603') ";
       
       String issiteipd=" select sum(IPD) as DTCB_Test_In_Tot from "+facilitiestable+" where  SubPartnerID='"+conn.rs.getString("SubPartnerID")+"'   AND "+facilitiestable+".active=1  ";
      
      System.out.println("%%"+issiteipd);
                
        conn.rs1=conn.st1.executeQuery(issiteipd);
       if(conn.rs1.next()){
         
       int semiannualinpatient=0;
       if(conn.rs1.getString("DTCB_Test_In_Tot")!=null){ semiannualinpatient=conn.rs1.getInt("DTCB_Test_In_Tot");}
       
       
           if(semiannualinpatient>0)
              {
       	//Sites with Inpatient services
 	              //OPD	IPD	VCT

//Testing ratios	72%	17%	11%
//Positivity ratios	62%	19%	19%
           //tested
                  //for IPD
                  if(isIPD==true){
                  htctestedratio=0.17;                      
                  htcpositiveratio=0.19;                      
                  }
                  
                  else if(isOPD==true){
                 htctestedratio=0.72;                      
                  htcpositiveratio=0.62;                      
                }
                  
                  else if(isVCT==true)
                  {
                      
                  htctestedratio=0.11;                      
                  htcpositiveratio=0.19;                      
                      
                  }
          
        /**          
       //do normalization here.. add the values not matching to
       int totaltestedratios=(int) (outpatienttested+inpatienttested+vcttested);
       int testedtofauti=htctested-totaltestedratios;
       
       int totalpositiveratios=(int) (outpatientpos+inpatientpos+vctpos);
       int positivetofauti=htcpositive-totalpositiveratios;
       
       //apply the difference to the highest rated service area , which is outpatient
       
       outpatienttested+=testedtofauti;
       outpatientpos+=positivetofauti;
      */ 
      }
       else {
           
       //site not supporting inpatient
      //Sites without Inpatient services	
	           //OPD	VCT	 
//HIV Tested	     86%	14%	 
//Tested Positive    85%	15% 
               
               
               if(isIPD==true){
                 htctestedratio=0;                      
                  htcpositiveratio=0;                      
                 }
                  
                  else if(isOPD==true){
                 htctestedratio=0.86;                      
                  htcpositiveratio=0.85;                      
                 }
                  
                  else if(isVCT==true)
                  {
                  htctestedratio=0.14;                      
                  htcpositiveratio=0.15;                      
                   }
         
       } 
        
            }//end of conn
	
     //==============================================NEW2017Q1===========================================================
    //============================================================================================================================START NEW RATIOS===================================
   
  //%%%%%%%%%%%%%%%%added 201606 %%%%%%%%%%%%%%%%%%%%%% 
     // 38 (M)%  ---	62% (F)

  double tested_new711=(float)Math.round((conn.rs.getInt("711_totaltested")*htctestedratio));
 
  //|__|
        System.out.println(" 2017Q1__"+tested_new711);
  
  double testedmale_711=(float)Math.round((Tm*tested_new711));
  double testedfemale_711=(float)Math.round((Tf*tested_new711));
  
   double tofautimpya=tested_new711-(testedmale_711+testedfemale_711);
  if(tofautimpya!=0){
 
  testedfemale_711+=tofautimpya;
  
  
  }
        //System.out.println("**2016_06_ "+testedmale_711+ "~ "+testedfemale_711+" ~ "+ tested_new711);
        
     //12%      88%
       
    //this will be defined from ratios 
    TestedAdultFemale=(int)Math.round((Tfa*testedfemale_711));  //adult   
    TestedChildFemale=(int)Math.round((Tfc*testedfemale_711)); //child
    
   tofautimpya=testedfemale_711 -(TestedAdultFemale+TestedChildFemale);   
    if(tofautimpya!=0){
  TestedAdultFemale+=tofautimpya;  
  }
    //  17%  83%   
    
    //TestedAdultMale=conn.rs.getInt(2);
    //TestedChildMale=conn.rs.getInt(6);
   
    
    TestedAdultMale=(int)Math.round((Tma*testedmale_711));  //adult   
    TestedChildMale=(int)Math.round((Tmc*testedmale_711)); //child
    
   tofautimpya=testedmale_711 -(TestedAdultMale+TestedChildMale);   
    if(tofautimpya!=0){
        
  TestedAdultMale+=tofautimpya;  
   }
     
    int hivpos_711_15_24f=(int) Math.round((conn.rs.getInt("711_15_24f")*htcpositiveratio));//|__|
    int hivpos_711_15_24m=(int) Math.round((conn.rs.getInt("711_15_24m")*htcpositiveratio));//|__|
    
    int hivpos_711_25m=(int) Math.round((conn.rs.getInt("711_25m")*htcpositiveratio));//|__|
    int hivpos_711_25f=(int) Math.round((conn.rs.getInt("711_25f")*htcpositiveratio));//|__|
    
    
    HIV_AdultFemale=hivpos_711_15_24f+ hivpos_711_25f;//
    HIV_AdultMale=hivpos_711_15_24m+ hivpos_711_25m; 
    
    HIV_ChildFemale=(int) Math.round((conn.rs.getInt("711_less15f")*htcpositiveratio)); //|__|
    HIV_ChildMale=(int) Math.round((conn.rs.getInt("711_less15m")*htcpositiveratio));   //|__|
    
    
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

   //TestedAdultMale   TestedChildMale  hivpos_711_15_24m  hivpos_711_25m HIV_ChildMale
//      FEMALES
//adult
FemaleAdultTested19=(float)Math.round((Tf19*TestedAdultFemale));
FemaleAdultTested24=(float)Math.round((Tf24*TestedAdultFemale));
FemaleAdultTested49=(float)Math.round((Tf49*TestedAdultFemale));

FemaleAdultTested29=(float)Math.round((Tf29*TestedAdultFemale));
FemaleAdultTested34=(float)Math.round((Tf34*TestedAdultFemale));
FemaleAdultTested39=(float)Math.round((Tf39*TestedAdultFemale));

FemaleAdultTested50=(float)Math.round((Tf50*TestedAdultFemale));
//children
FemaleTestedChild1=(float)Math.round((Tf1*TestedChildFemale));
FemaleTestedChild4=(float)Math.round((Tf4*TestedChildFemale));
FemaleTestedChild9=(float)Math.round((Tf9*TestedChildFemale));
FemaleTestedChild14=(float)Math.round((Tf14*TestedChildFemale));

//postive 

//hivpos_711_25f#hivpos_711_25m#hivpos_711_15_24m#hivpos_711_15_24f
AdultFemaleHIV19=(float)Math.round((Pf19*hivpos_711_15_24f));
AdultFemaleHIV24=(float)Math.round((Pf24*hivpos_711_15_24f));

AdultFemaleHIV29=(float)Math.round((Pf29*hivpos_711_25f));
AdultFemaleHIV34=(float)Math.round((Pf34*hivpos_711_25f));
AdultFemaleHIV39=(float)Math.round((Pf39*hivpos_711_25f));

AdultFemaleHIV49=(float)Math.round((Pf49*hivpos_711_25f));
AdultFemaleHIV50=(float)Math.round((Pf50*hivpos_711_25f));

//positive

//children
ChildFemaleHIV1=(float)Math.round((Pf1*HIV_ChildFemale));
ChildFemaleHIV4=(float)Math.round((Pf4*HIV_ChildFemale));
ChildFemaleHIV9=(float)Math.round((Pf9*HIV_ChildFemale));
ChildFemaleHIV14=(float)Math.round((Pf14*HIV_ChildFemale));

// MALES  
//adult
  MaleAdultTested19=(float)Math.round((Tm19*TestedAdultMale));
  MaleAdultTested24=(float)Math.round((Tm24*TestedAdultMale));
  
  MaleAdultTested29=(float)Math.round((Tm29*TestedAdultMale));
  MaleAdultTested34=(float)Math.round((Tm34*TestedAdultMale));
  MaleAdultTested39=(float)Math.round((Tm39*TestedAdultMale));
  
  MaleAdultTested49=(float)Math.round((Tm49*TestedAdultMale));
  MaleAdultTested50=(float)Math.round((Tm50*TestedAdultMale));

  //children
  MaleTestedChild1=(float)Math.round((Tm1*TestedChildMale));
  MaleTestedChild4=(float)Math.round((Tm4*TestedChildMale));
  MaleTestedChild9=(float)Math.round((Tm9*TestedChildMale));
  MaleTestedChild14=(float)Math.round((Tm14*TestedChildMale));


   AdultMaleHIV19=(float)Math.round((Pm19*hivpos_711_15_24m));
  AdultMaleHIV24=(float)Math.round((Pm24*hivpos_711_15_24m));
  
  AdultMaleHIV29=(float)Math.round((Pm29*hivpos_711_25m));
  AdultMaleHIV34=(float)Math.round((Pm34*hivpos_711_25m));
  AdultMaleHIV39=(float)Math.round((Pm39*hivpos_711_25m));
  
  AdultMaleHIV49=(float)Math.round((Pm49*hivpos_711_25m));
  AdultMaleHIV50=(float)Math.round((Pm50*hivpos_711_25m));


  //positives
  //children
  ChildMaleHIV1=(float)Math.round((Pm1*HIV_ChildMale));
  ChildMaleHIV4=(float)Math.round((Pm4*HIV_ChildMale));
  ChildMaleHIV9=(float)Math.round((Pm9*HIV_ChildMale));
  ChildMaleHIV14=(float)Math.round((Pm14*HIV_ChildMale));
 
  
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
  totalpositives=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14+AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;    
   totalnegatives=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV29Neg+AdultFemaleHIV34Neg+AdultFemaleHIV39Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg+AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV29Neg+AdultMaleHIV34Neg+AdultMaleHIV39Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;

//   total tested after distribution
   totalfemaletesteddis=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14+FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
   totalmaletesteddis=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50+MaleTestedChild1+MaleTestedChild4+MaleTestedChild9+MaleTestedChild14;
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
   totalpositivesfemale=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50+ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14 ;
  totalpositivesmale=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50+ChildMaleHIV1+ChildMaleHIV4+ChildMaleHIV9+ChildMaleHIV14;
  // 
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

 if(checkdiff>2 ||checkdiff<-2){redalert=1;}
   checkdiff1=totalmalehiv-totalpositivesmale;
if(checkdiff1>2 ||checkdiff1<-2){redalert1=1;}

 totalcheckdiff=TotalTested-totaltestedis;
if(totalcheckdiff>5 || totalcheckdiff<-5){finalalert=1;}
 
   
  
   adderPos=0;
   double Totalhivfemale=0;
   double Totalhivmale=0;
   Totalhivfemale=HIV_AdultFemale+HIV_ChildFemale;
   Totalhivmale=HIV_AdultMale+HIV_ChildMale;
   splitData=0;  adderPos=0; childSplitData=0;   
//   // adult female hiv+
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50 ;
//
System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
 adderPos=0;
 
 while(splitData<HIV_AdultFemale){ AdultFemaleHIV34+=1; splitData++;}
 
splitData=AdultFemaleHIV19+AdultFemaleHIV24+AdultFemaleHIV29+AdultFemaleHIV34+AdultFemaleHIV39+AdultFemaleHIV49+AdultFemaleHIV50 ;
 while(splitData>HIV_AdultFemale){ AdultFemaleHIV34-=1; splitData--;}
 //tested female adults
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
 adderPos=0;
 while(splitData<TestedAdultFemale){ FemaleAdultTested34+=1;  splitData++;}
 
splitData=FemaleAdultTested19+FemaleAdultTested24+FemaleAdultTested29+FemaleAdultTested34+FemaleAdultTested39+FemaleAdultTested49+FemaleAdultTested50;
 adderPos=0;
 while(splitData>TestedAdultFemale){ FemaleAdultTested34-=1; splitData--;}

// adult male hiv+
 
 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
  adderPos=0;
 while(splitData<HIV_AdultMale){ AdultMaleHIV34+=1;splitData++;}
 
 splitData=AdultMaleHIV19+AdultMaleHIV24+AdultMaleHIV29+AdultMaleHIV34+AdultMaleHIV39+AdultMaleHIV49+AdultMaleHIV50;
 adderPos=0;
 while(splitData>HIV_AdultMale){ 
     
           if(AdultMaleHIV34>0){ AdultMaleHIV34-=1; splitData--;}
     else  if(AdultMaleHIV24>0){ AdultMaleHIV24-=1; splitData--;}
     else  if(AdultMaleHIV29>0){ AdultMaleHIV29-=1; splitData--;}
     else  if(AdultMaleHIV39>0){ AdultMaleHIV39-=1; splitData--;}
     else  if(AdultMaleHIV49>0){ AdultMaleHIV49-=1; splitData--;}
     else  if(AdultMaleHIV50>0){ AdultMaleHIV50-=1; splitData--;}
     else  if(AdultMaleHIV19>0){ AdultMaleHIV19-=1; splitData--;}
     else{
               System.out.println(" VCT haikusolviwa "+facilityname);
     } 
     
     }
 
 //tested male adults
 splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData<TestedAdultMale){ MaleAdultTested34+=1; splitData++;}
 
 splitData=MaleAdultTested19+MaleAdultTested24+MaleAdultTested29+MaleAdultTested34+MaleAdultTested39+MaleAdultTested49+MaleAdultTested50 ;
 adderPos=0;
 while(splitData>TestedAdultMale){ MaleAdultTested34-=1; splitData--;}
 

// for child female tested 
  childSplitData= FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  
  System.out.println(facilityname+" "+childSplitData+" b4 jjj "+TestedChildFemale);
   adderPos=0;
while(childSplitData<TestedChildFemale){ 
if(adderPos==0){FemaleTestedChild14+=1;   }
if(adderPos==1){FemaleTestedChild9+=1;    }
if(adderPos==2){FemaleTestedChild4+=1;    }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==TestedChildFemale){}
}

   childSplitData=FemaleTestedChild1+FemaleTestedChild4+FemaleTestedChild9+FemaleTestedChild14; 
  adderPos=0;
  
while(childSplitData>TestedChildFemale){ 
 if(adderPos==0){FemaleTestedChild14-=1;   }
if(adderPos==1){ FemaleTestedChild9-=1;    }
if(adderPos==2){FemaleTestedChild4-=1;    }
childSplitData--;
adderPos++  ;if(adderPos>2){adderPos=0;}if(childSplitData==TestedChildFemale){}  }

 // for child female +ve
  childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
 // System.out.println(facilityname+"  mmmm  "+childSplitData+"    "+HIV_ChildFemale);
   adderPos=0;
   double diff=0;
while(childSplitData<HIV_ChildFemale){ 
  diff=FemaleTestedChild14-ChildFemaleHIV14;
 if(adderPos==0){
  if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1;  }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){  ChildFemaleHIV9+=1;  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
 
 }
  
 if(adderPos==1){

       if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1;  }
  else if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;  }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ ChildFemaleHIV14+=1;    }
 }
 if(adderPos==2){
  
       if(FemaleTestedChild4-ChildFemaleHIV4>0){ ChildFemaleHIV4+=1;   }
  else if(FemaleTestedChild14-ChildFemaleHIV14>0){ChildFemaleHIV14+=1;  }
  else if(FemaleTestedChild9-ChildFemaleHIV9>0){ ChildFemaleHIV9+=1;}
 }
childSplitData++;
adderPos++  ;
 if(adderPos>2){adderPos=0;}
 if(childSplitData==HIV_ChildFemale){}
}

childSplitData=ChildFemaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildFemaleHIV14; 
  adderPos=0;
  
while(childSplitData>HIV_ChildFemale){ 
  
  if(adderPos==0){ ChildFemaleHIV14-=1;}
  
 if(adderPos==1){ ChildFemaleHIV9-=1;}
 if(adderPos==2){ ChildFemaleHIV4-=1;}
childSplitData--;
adderPos++;
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
 if(adderPos==0){ MaleTestedChild14-=1;}
 else if(adderPos==1){ MaleTestedChild9-=1;    }
 else if(adderPos==2){MaleTestedChild4-=1;  }
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
      if(MaleTestedChild14-ChildMaleHIV14>0){ ChildMaleHIV14+=1;}
     else if(MaleTestedChild9-ChildMaleHIV9>0){ ChildMaleHIV9+=1; }
     else if(MaleTestedChild4-ChildMaleHIV4>0){ChildMaleHIV4+=1;  }  
 }
 else if(adderPos==1){
      if(MaleTestedChild9-ChildMaleHIV9>0){ChildMaleHIV9+=1;   }
 else if(MaleTestedChild4-ChildMaleHIV4>0){ChildMaleHIV4+=1;   }
  else if(MaleTestedChild14-ChildMaleHIV14>0){ChildMaleHIV14+=1;   }
    
 }
 if(adderPos==2){
   if(MaleTestedChild4-ChildMaleHIV4>0){ChildMaleHIV4+=1; }
  else if(MaleTestedChild14-ChildMaleHIV14>0){ChildMaleHIV14+=1;}
  else if(MaleTestedChild9-ChildMaleHIV9>0){ChildMaleHIV9+=1;   }
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
  //201510
 //this code was added later
 
 less15f=TestedChildFemale;
 less15m=TestedChildMale;
 gret15m=TestedAdultMale;
 gret15f=TestedAdultFemale;
 
 
 double posotiveyote= ChildFemaleHIV1+ChildMaleHIV1+ChildFemaleHIV4+ChildFemaleHIV9+ChildMaleHIV4+ChildMaleHIV9+ChildFemaleHIV14+ChildMaleHIV14+AdultFemaleHIV19+AdultMaleHIV19+AdultFemaleHIV24+AdultMaleHIV24+AdultFemaleHIV29+AdultMaleHIV29+AdultFemaleHIV34+AdultMaleHIV34+AdultFemaleHIV39+AdultMaleHIV39+AdultFemaleHIV49+AdultMaleHIV49+AdultFemaleHIV50+AdultMaleHIV50;
 
 
   //double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f);
   double balancegeneral=(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))-(posotiveyote);
   //711_totalpositive 
  
        //System.out.println("POSITIVE VALIDATION _IPD: "+facilityname+" Total positive :"+(int) Math.round((conn.rs.getInt("711_totalpositive")*htcpositiveratio))+"-"+(hivpos_711_less15m+hivpos_711_less15f+hivpos_711_25m+hivpos_711_25f+hivpos_711_15_24m+hivpos_711_15_24f)+"="+balancegeneral);
   if(3==3){
       
while(balancegeneral<0){
if(AdultFemaleHIV49>0){AdultFemaleHIV49-=1;  balancegeneral++; }
else if(AdultFemaleHIV24>0) { AdultFemaleHIV24-=1;  balancegeneral++; }
else if(AdultFemaleHIV34>0){ AdultFemaleHIV34-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultFemaleHIV29>0){AdultFemaleHIV29-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultFemaleHIV39>0){AdultFemaleHIV39-=1; balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultFemaleHIV50>0){AdultFemaleHIV50-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname); }
else if(AdultMaleHIV49>0){AdultMaleHIV49-=1;  balancegeneral++; }
else if(AdultMaleHIV24>0) { AdultMaleHIV24-=1;  balancegeneral++; }
else if(AdultMaleHIV34>0){ AdultMaleHIV34-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultMaleHIV29>0){AdultMaleHIV29-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultMaleHIV39>0){AdultMaleHIV39-=1; balancegeneral++;System.out.println(" solved at last bad stage "+facilityname);}
else if(AdultMaleHIV50>0){AdultMaleHIV50-=1;  balancegeneral++;System.out.println(" solved at last bad stage "+facilityname); }
else { System.out.println("Not solved at last bad stage VCT"+facilityname);   }
    
    
                 }

while(balancegeneral>0){

 AdultFemaleHIV49+=1; balancegeneral--;  
                 }
 
    }
     


  
  int neg1male=0;
  int neg4male=0;
  int neg9male=0;
  int neg14male=0;
  int neg19male=0;
  int neg24male=0;
  
  int neg29male=0;
  int neg34male=0;
  int neg39male=0;
  
  int neg49male=0;
  int neg50male=0;
  AdultMaleHIV19Neg=(float)Math.round(MaleAdultTested19)-(AdultMaleHIV19);
  AdultMaleHIV24Neg=(float)Math.round(MaleAdultTested24)-(AdultMaleHIV24);
  
  AdultMaleHIV29Neg=(float)Math.round(MaleAdultTested29)-(AdultMaleHIV29);
  AdultMaleHIV34Neg=(float)Math.round(MaleAdultTested34)-(AdultMaleHIV34);
  AdultMaleHIV39Neg=(float)Math.round(MaleAdultTested39)-(AdultMaleHIV39);
  
  AdultMaleHIV49Neg=(float)Math.round(MaleAdultTested49)-(AdultMaleHIV49);
  AdultMaleHIV50Neg=(float)Math.round(MaleAdultTested50)-(AdultMaleHIV50);
if(AdultMaleHIV19Neg<=-1){
neg19male=1;
}
if(AdultMaleHIV24Neg<=-1){
neg24male=1;
}

if(AdultMaleHIV29Neg<=-1){
neg29male=1;
}

if(AdultMaleHIV34Neg<=-1){
neg34male=1;
}


if(AdultMaleHIV39Neg<=-1){
neg39male=1;
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
  
  int neg29female=0;
  int neg34female=0;
  int neg39female=0;  
  
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

System.out.println(facilityname+" fffff "+ChildFemaleHIV1Neg+" "+ChildFemaleHIV4Neg+" "+ChildFemaleHIV9Neg +"  "+ChildFemaleHIV14Neg);
//negative

AdultFemaleHIV19Neg=(float)Math.round(FemaleAdultTested19)-(AdultFemaleHIV19);
AdultFemaleHIV24Neg=(float)Math.round(FemaleAdultTested24)-(AdultFemaleHIV24);


AdultFemaleHIV29Neg=(float)Math.round(FemaleAdultTested29)-(AdultFemaleHIV29);
AdultFemaleHIV34Neg=(float)Math.round(FemaleAdultTested34)-(AdultFemaleHIV34);
AdultFemaleHIV39Neg=(float)Math.round(FemaleAdultTested39)-(AdultFemaleHIV39);


AdultFemaleHIV49Neg=(float)Math.round(FemaleAdultTested49)-(AdultFemaleHIV49);
AdultFemaleHIV50Neg=(float)Math.round(FemaleAdultTested50)-(AdultFemaleHIV50);
  
   if(AdultFemaleHIV19Neg<=-1){neg19female=1;}
if(AdultFemaleHIV24Neg<=-1){neg24female=1;}

if(AdultFemaleHIV29Neg<=-1){neg29female=1;}
if(AdultFemaleHIV34Neg<=-1){neg34female=1;}
if(AdultFemaleHIV39Neg<=-1){neg39female=1;}
if(AdultFemaleHIV49Neg<=-1){neg49female=1;}
if(AdultFemaleHIV50Neg<=-1){neg50female=1;} 

if(AdultFemaleHIV19Neg<0){
while(AdultFemaleHIV19Neg<=-1){
    
    if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV49Neg>0){ AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
     else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; AdultFemaleHIV19Neg=AdultFemaleHIV19Neg+1;}
    else {System.out.println("No solution here"); break;}
   
}
}

if(AdultFemaleHIV24Neg<0){
while(AdultFemaleHIV24Neg<=-1){
if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
   else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else if(AdultFemaleHIV29Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1; }
    else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV24Neg=AdultFemaleHIV24Neg+1;}
    else {System.out.println("No solution here");break;}
    
}

}

if(AdultFemaleHIV29Neg<0){
while(AdultFemaleHIV29Neg<=-1){
    
    if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV39Neg>0){AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
    else if(AdultFemaleHIV34Neg>0){ AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
 else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1;}
     else if(AdultFemaleHIV19Neg>0) { AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV29Neg=AdultFemaleHIV29Neg+1; }
    else {System.out.println("No solution here"); break;}
    
}
}

if(AdultFemaleHIV34Neg<0){
while(AdultFemaleHIV34Neg<=-1){
    
    if(AdultFemaleHIV50Neg>0){AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    
    else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}

    else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
     else if(AdultFemaleHIV24Neg>0) {AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
     else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV34Neg=AdultFemaleHIV34Neg+1;}
    else {System.out.println("No solution here"); break;}
    
}
}


if(AdultFemaleHIV39Neg<0){
while(AdultFemaleHIV39Neg<=-1){
 if(AdultFemaleHIV50Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1; }
else if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
 else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
    else if(AdultFemaleHIV29Neg>0){ AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
     else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1;}
     else if(AdultFemaleHIV19Neg>0){AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1;AdultFemaleHIV39Neg=AdultFemaleHIV39Neg+1; }
    else {System.out.println("No solution here");break; }
    
}

}

if(AdultFemaleHIV49Neg<0){
while(AdultFemaleHIV49Neg<=-1){
    System.out.println(" In a long looop AdultFemaleHIV49Neg: "+AdultFemaleHIV49Neg);
    
    if(AdultFemaleHIV50Neg>0){ AdultFemaleHIV50Neg=AdultFemaleHIV50Neg-1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1; System.out.println(" In a long looop AdultFemaleHIV50Neg>0 : "+AdultFemaleHIV49Neg);}
   else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;System.out.println(" In a long looop AdultFemaleHIV39Neg>0 : "+AdultFemaleHIV49Neg);}
    else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;System.out.println(" In a long looop AdultFemaleHIV34Neg>0 : "+AdultFemaleHIV49Neg);}
    else if(AdultFemaleHIV29Neg>0) {AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;System.out.println(" In a long looop AdultFemaleHIV29Neg>0 : "+AdultFemaleHIV49Neg);}
     else if(AdultFemaleHIV24Neg>0){ AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1;AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;System.out.println(" In a long looop AdultFemaleHIV24Neg>0 : "+AdultFemaleHIV49Neg);}
     else if(AdultFemaleHIV19Neg>0){ AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV49Neg=AdultFemaleHIV49Neg+1;System.out.println(" In a long looop AdultFemaleHIV19Neg>0 : "+AdultFemaleHIV49Neg);
    }
     else {System.out.println("No solution here 49"); break;}
    
}
}


if(AdultFemaleHIV50Neg<0){
while(AdultFemaleHIV50Neg<=-1){
     if(AdultFemaleHIV49Neg>0){AdultFemaleHIV49Neg=AdultFemaleHIV49Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
   else if(AdultFemaleHIV39Neg>0){ AdultFemaleHIV39Neg=AdultFemaleHIV39Neg-1;AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;}
    else if(AdultFemaleHIV34Neg>0){AdultFemaleHIV34Neg=AdultFemaleHIV34Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
    
    else if(AdultFemaleHIV29Neg>0){AdultFemaleHIV29Neg=AdultFemaleHIV29Neg-1;  AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;}
     else if(AdultFemaleHIV24Neg>0){AdultFemaleHIV24Neg=AdultFemaleHIV24Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1; }
     else if(AdultFemaleHIV19Neg>0) { AdultFemaleHIV19Neg=AdultFemaleHIV19Neg-1; AdultFemaleHIV50Neg=AdultFemaleHIV50Neg+1;}
     else {System.out.println("No solution here");break;}
    
}
}

//----------------------normalize male --------------------


if(AdultMaleHIV19Neg<0){
while(AdultMaleHIV19Neg<=-1){
 if(AdultMaleHIV50Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;AdultMaleHIV19Neg=AdultMaleHIV19Neg+1;}
   else if(AdultMaleHIV49Neg>0){AdultMaleHIV49Neg=AdultMaleHIV49Neg-1; AdultMaleHIV19Neg=AdultMaleHIV19Neg+1;}
    else if(AdultMaleHIV39Neg>0){AdultMaleHIV39Neg=AdultMaleHIV39Neg-1; AdultMaleHIV19Neg=AdultMaleHIV19Neg+1;}
    else if(AdultMaleHIV34Neg>0){ AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV19Neg=AdultMaleHIV19Neg+1;}
    else if(AdultMaleHIV29Neg>0) {AdultMaleHIV29Neg=AdultMaleHIV29Neg-1; AdultMaleHIV19Neg=AdultMaleHIV19Neg+1; }
     else if(AdultMaleHIV24Neg>0){AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;AdultMaleHIV19Neg=AdultMaleHIV19Neg+1;}
    else { System.out.println("No solution here"); break;}

    
}
}

if(AdultMaleHIV24Neg<0){
while(AdultMaleHIV24Neg<=-1){   
    if(AdultMaleHIV50Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg-1; AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; }    
    else if(AdultMaleHIV49Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg-1; AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; }
    else if(AdultMaleHIV39Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;  AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; }
    else if(AdultMaleHIV34Neg>0){ AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV24Neg=AdultMaleHIV24Neg+1;}
    else if(AdultMaleHIV29Neg>0)  {  AdultMaleHIV29Neg=AdultMaleHIV29Neg-1; AdultMaleHIV24Neg=AdultMaleHIV24Neg+1; }

     else if(AdultMaleHIV19Neg>0) { AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV24Neg=AdultMaleHIV24Neg+1;}
    else { System.out.println("No solution here");  break; }
    
}

}

if(AdultMaleHIV29Neg<0){
while(AdultMaleHIV29Neg<=-1){
    
    if(AdultMaleHIV50Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; }
    else if(AdultMaleHIV49Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; }
    else if(AdultMaleHIV39Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;AdultMaleHIV29Neg=AdultMaleHIV29Neg+1;}
    else if(AdultMaleHIV34Neg>0){ AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; }
    else if(AdultMaleHIV24Neg>0) { AdultMaleHIV24Neg=AdultMaleHIV24Neg-1; AdultMaleHIV29Neg=AdultMaleHIV29Neg+1;}
    else if(AdultMaleHIV19Neg>0) { AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV29Neg=AdultMaleHIV29Neg+1; }
    else {  System.out.println("No solution here");   break;  }
    
}
}


if(AdultMaleHIV34Neg<0){
while(AdultMaleHIV34Neg<=-1){
    
    if(AdultMaleHIV50Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg-1;  AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;}
   else if(AdultMaleHIV49Neg>0){ AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;  AdultMaleHIV34Neg=AdultMaleHIV34Neg+1; }
    else if(AdultMaleHIV39Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;  AdultMaleHIV34Neg=AdultMaleHIV34Neg+1; }
 else if(AdultMaleHIV29Neg>0) { AdultMaleHIV29Neg=AdultMaleHIV29Neg-1; AdultMaleHIV34Neg=AdultMaleHIV34Neg+1;  }
     else if(AdultMaleHIV24Neg>0) { AdultMaleHIV24Neg=AdultMaleHIV24Neg-1; AdultMaleHIV34Neg=AdultMaleHIV34Neg+1; }
     else if(AdultMaleHIV19Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV34Neg=AdultMaleHIV34Neg+1; }
    else {  System.out.println("No solution here");  break; }
    
}
}


if(AdultMaleHIV39Neg<0){while(AdultMaleHIV39Neg<=-1){  if(AdultMaleHIV50Neg>0){ AdultMaleHIV50Neg=AdultMaleHIV50Neg-1; AdultMaleHIV39Neg=AdultMaleHIV39Neg+1;  }
 else if(AdultMaleHIV49Neg>0) { AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;  AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; }
 else if(AdultMaleHIV34Neg>0) {AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; }
   else if(AdultMaleHIV29Neg>0) { AdultMaleHIV29Neg=AdultMaleHIV29Neg-1; AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; }
     else if(AdultMaleHIV24Neg>0){AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;  AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; }
     else if(AdultMaleHIV19Neg>0){ AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV39Neg=AdultMaleHIV39Neg+1; }
    else {System.out.println("No solution here"); break;}
    
}

}

if(AdultMaleHIV49Neg<0){
while(AdultMaleHIV49Neg<=-1){
    System.out.println(" In a long looop AdultMaleHIV49Neg: "+AdultMaleHIV49Neg);
    if(AdultMaleHIV50Neg>0){AdultMaleHIV50Neg=AdultMaleHIV50Neg-1; AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;System.out.println(" In a long looop AdultMaleHIV50Neg>0 : "+AdultMaleHIV49Neg);}
   else if(AdultMaleHIV39Neg>0){AdultMaleHIV39Neg=AdultMaleHIV39Neg-1;AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;System.out.println(" In a long looop AdultMaleHIV39Neg>0 : "+AdultMaleHIV49Neg);}
    else if(AdultMaleHIV34Neg>0){ AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;System.out.println(" In a long looop AdultMaleHIV34Neg>0 : "+AdultMaleHIV49Neg);}
    else if(AdultMaleHIV29Neg>0) {AdultMaleHIV29Neg=AdultMaleHIV29Neg-1; AdultMaleHIV49Neg=AdultMaleHIV49Neg+1; System.out.println(" In a long looop AdultMaleHIV29Neg>0 : "+AdultMaleHIV49Neg);}
    else if(AdultMaleHIV24Neg>0) { AdultMaleHIV24Neg=AdultMaleHIV24Neg-1;AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;System.out.println(" In a long looop AdultMaleHIV24Neg>0 : "+AdultMaleHIV49Neg);}
    else if(AdultMaleHIV19Neg>0) { AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV49Neg=AdultMaleHIV49Neg+1;System.out.println(" In a long looop AdultMaleHIV19Neg>0 : "+AdultMaleHIV49Neg);}
    else {System.out.println("No solution here 49"); break;}
    
}
}


if(AdultMaleHIV50Neg<0){
while(AdultMaleHIV50Neg<=-1){
    if(AdultMaleHIV49Neg>0) { AdultMaleHIV49Neg=AdultMaleHIV49Neg-1;AdultMaleHIV50Neg=AdultMaleHIV50Neg+1; }
   else if(AdultMaleHIV39Neg>0){ AdultMaleHIV39Neg=AdultMaleHIV39Neg-1; AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;}
   else if(AdultMaleHIV34Neg>0) {  AdultMaleHIV34Neg=AdultMaleHIV34Neg-1; AdultMaleHIV50Neg=AdultMaleHIV50Neg+1; }
   else if(AdultMaleHIV29Neg>0){ AdultMaleHIV29Neg=AdultMaleHIV29Neg-1;AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;}
   else if(AdultMaleHIV24Neg>0){AdultMaleHIV24Neg=AdultMaleHIV24Neg-1; AdultMaleHIV50Neg=AdultMaleHIV50Neg+1;}
   else if(AdultMaleHIV19Neg>0) { AdultMaleHIV19Neg=AdultMaleHIV19Neg-1; AdultMaleHIV50Neg=AdultMaleHIV50Neg+1; }
   else { System.out.println("No solution here"); break;}
    
}
}

//----------------------end of normalize male --------------------

double TotalNegativeFemale1=0;
 double TotalNegativeMale1=0;
TotalNegativeFemale1=AdultFemaleHIV19Neg+AdultFemaleHIV24Neg+AdultFemaleHIV29Neg+AdultFemaleHIV34Neg+AdultFemaleHIV39Neg+AdultFemaleHIV49Neg+AdultFemaleHIV50Neg+ ChildFemaleHIV1Neg+ChildFemaleHIV4Neg+ChildFemaleHIV9Neg+ChildFemaleHIV14Neg;
TotalNegativeMale1=AdultMaleHIV19Neg+AdultMaleHIV24Neg+AdultMaleHIV29Neg+AdultMaleHIV39Neg+AdultMaleHIV49Neg+AdultMaleHIV50Neg+ChildMaleHIV1Neg+ChildMaleHIV4Neg+ChildMaleHIV9Neg+ChildMaleHIV14Neg;
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
 
 
  String alldatavals[]={county,district,facilityname,""+mflcode,dsdta
        ,"0","0"
        ,""+ChildFemaleHIV1,""+ChildMaleHIV1
        //,""+(ChildFemaleHIV4),""+(ChildMaleHIV4)//
        //,""+(ChildFemaleHIV9),""+(ChildMaleHIV9)//
        //,""+ChildFemaleHIV14,""+ChildMaleHIV14//
        ,"0","0"//
        ,"0","0"//
        ,"0","0"//
        ,""+(AdultFemaleHIV19+(ChildFemaleHIV4)),""+(AdultMaleHIV19+(ChildMaleHIV4))
        ,""+(AdultFemaleHIV24+(ChildFemaleHIV9)),""+(AdultMaleHIV24+(ChildMaleHIV9))
        ,""+(AdultFemaleHIV29+ChildFemaleHIV14),""+(AdultMaleHIV29+ChildMaleHIV14)
          ,""+AdultFemaleHIV34,""+AdultMaleHIV34
          ,""+AdultFemaleHIV39,""+AdultMaleHIV39
          
        ,""+AdultFemaleHIV49,""+AdultMaleHIV49
        ,""+AdultFemaleHIV50,""+AdultMaleHIV50        
         //negative starts here 
        ,"0","0"
        ,""+ChildFemaleHIV1Neg,""+ChildMaleHIV1Neg
        //,""+(ChildFemaleHIV4Neg),""+(ChildMaleHIV4Neg)//
        //,""+(ChildFemaleHIV9Neg),""+(ChildMaleHIV9Neg)//
        //,""+ChildFemaleHIV14Neg,""+ChildMaleHIV14Neg//
        ,"0","0"//
        ,"0","0"//
        ,"0","0"//
        ,""+(AdultFemaleHIV19Neg+(ChildFemaleHIV4Neg)),""+(AdultMaleHIV19Neg+(ChildMaleHIV4Neg))
          
        ,""+(AdultFemaleHIV24Neg+(ChildFemaleHIV9Neg)),""+(AdultMaleHIV24Neg+(ChildMaleHIV9Neg))
        ,""+(AdultFemaleHIV29Neg+ChildFemaleHIV14Neg),""+(AdultMaleHIV29Neg+ChildMaleHIV14Neg)
        ,""+AdultFemaleHIV34Neg,""+AdultMaleHIV34Neg
        ,""+AdultFemaleHIV39Neg,""+AdultMaleHIV39Neg
          
        ,""+AdultFemaleHIV49Neg,""+AdultMaleHIV49Neg
        ,""+AdultFemaleHIV50Neg,""+AdultMaleHIV50Neg,""+tested_new711,""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")
          
        }; 

  
  
  
  vcthm.put("cty"+"_"+mflcode, county);
  vcthm.put("sty"+"_"+mflcode, district);
  vcthm.put("fac"+"_"+mflcode, facilityname);
  vcthm.put("mfl"+"_"+mflcode, ""+mflcode);
  vcthm.put("st"+"_"+mflcode, dsdta);
  vcthm.put("ukpf"+"_"+mflcode,"0");
  vcthm.put("ukpm"+"_"+mflcode,"0");
  vcthm.put("pf1"+"_"+mflcode,""+(ChildFemaleHIV1 ));
  vcthm.put("pm1"+"_"+mflcode,""+ChildMaleHIV1);
//  vcthm.put("pf4"+"_"+mflcode,""+ChildFemaleHIV4);//
//  vcthm.put("pm4"+"_"+mflcode,""+ChildMaleHIV4);//
//  vcthm.put("pf9"+"_"+mflcode,""+ChildFemaleHIV9);//
//  vcthm.put("pm9"+"_"+mflcode,""+ChildMaleHIV9);//
//  vcthm.put("pf14"+"_"+mflcode,""+(ChildFemaleHIV14));//
//  vcthm.put("pm14"+"_"+mflcode,""+ChildMaleHIV14);//
  vcthm.put("pf4"+"_"+mflcode,"0");//
  vcthm.put("pm4"+"_"+mflcode,"0");//
  vcthm.put("pf9"+"_"+mflcode,"0");//
  vcthm.put("pm9"+"_"+mflcode,"0");//
  vcthm.put("pf14"+"_"+mflcode,"0");//
  vcthm.put("pm14"+"_"+mflcode,"0");//
  vcthm.put("pf19"+"_"+mflcode,""+(AdultFemaleHIV19+ChildFemaleHIV4));
  vcthm.put("pm19"+"_"+mflcode,""+(AdultMaleHIV19+ChildMaleHIV4));
  vcthm.put("pf24"+"_"+mflcode,""+(AdultFemaleHIV24+ChildFemaleHIV9));
  vcthm.put("pm24"+"_"+mflcode,""+(AdultMaleHIV24+ChildMaleHIV9));
  vcthm.put("pf29"+"_"+mflcode,""+(AdultFemaleHIV29+ChildFemaleHIV14));
  vcthm.put("pm29"+"_"+mflcode,""+(AdultMaleHIV29+ChildMaleHIV14));
  vcthm.put("pf34"+"_"+mflcode,""+(AdultFemaleHIV34));
  vcthm.put("pm34"+"_"+mflcode,""+AdultMaleHIV34);
  vcthm.put("pf39"+"_"+mflcode,""+(AdultFemaleHIV39));
  vcthm.put("pm39"+"_"+mflcode,""+AdultMaleHIV39);
  vcthm.put("pf49"+"_"+mflcode,""+(AdultFemaleHIV49));
  vcthm.put("pm49"+"_"+mflcode,""+AdultMaleHIV49);
  vcthm.put("pf50"+"_"+mflcode,""+(AdultFemaleHIV50));
  vcthm.put("pm50"+"_"+mflcode,""+AdultMaleHIV50);
  //_____negative
  vcthm.put("uknf"+"_"+mflcode,"0");
  vcthm.put("uknm"+"_"+mflcode,"0");
  vcthm.put("nf1"+"_"+mflcode,""+ChildFemaleHIV1Neg);
  vcthm.put("nm1"+"_"+mflcode,""+ChildMaleHIV1Neg);
//  vcthm.put("nf4"+"_"+mflcode,""+ChildFemaleHIV4Neg);//
//  vcthm.put("nm4"+"_"+mflcode,""+ChildMaleHIV4Neg);//
//  vcthm.put("nf9"+"_"+mflcode,""+ChildFemaleHIV9Neg);//
//  vcthm.put("nm9"+"_"+mflcode,""+ChildMaleHIV9Neg);//
//  vcthm.put("nf14"+"_"+mflcode,""+(ChildFemaleHIV14Neg));//
//  vcthm.put("nm14"+"_"+mflcode,""+ChildMaleHIV14Neg);//
  vcthm.put("nf4"+"_"+mflcode,"0");//
  vcthm.put("nm4"+"_"+mflcode,"0");//
  vcthm.put("nf9"+"_"+mflcode,"0");//
  vcthm.put("nm9"+"_"+mflcode,"0");//
  vcthm.put("nf14"+"_"+mflcode,"0");//
  vcthm.put("nm14"+"_"+mflcode,"0");//
  vcthm.put("nf19"+"_"+mflcode,""+(AdultFemaleHIV19Neg+ChildFemaleHIV4Neg));
  vcthm.put("nm19"+"_"+mflcode,""+(AdultMaleHIV19Neg+ChildMaleHIV4Neg));
  vcthm.put("nf24"+"_"+mflcode,""+(AdultFemaleHIV24Neg+ChildFemaleHIV9Neg));
  vcthm.put("nm24"+"_"+mflcode,""+(AdultMaleHIV24Neg+ChildMaleHIV9Neg));
  vcthm.put("nf29"+"_"+mflcode,""+(AdultFemaleHIV29Neg+(ChildFemaleHIV14Neg)));
  vcthm.put("nm29"+"_"+mflcode,""+(AdultMaleHIV29Neg+ChildMaleHIV14Neg));
  vcthm.put("nf34"+"_"+mflcode,""+(AdultFemaleHIV34Neg));
  vcthm.put("nm34"+"_"+mflcode,""+AdultMaleHIV34Neg);
  vcthm.put("nf39"+"_"+mflcode,""+(AdultFemaleHIV39Neg));
  vcthm.put("nm39"+"_"+mflcode,""+AdultMaleHIV39Neg);
  vcthm.put("nf49"+"_"+mflcode,""+(AdultFemaleHIV49Neg));
  vcthm.put("nm49"+"_"+mflcode,""+AdultMaleHIV49Neg);
  vcthm.put("nf50"+"_"+mflcode,""+(AdultFemaleHIV50Neg));
  vcthm.put("nm50"+"_"+mflcode,""+AdultMaleHIV50Neg);
  vcthm.put("tt"+"_"+mflcode,""+((tested_new711)));


  

HSSFCell  cxy;
 

         rw0.setHeightInPoints(25);
         int mypos=0;
     
         
  for(int a=0;a<alldatavals.length;a++){
      
       cxy=rw0.createCell(mypos);mypos++;
       if(a==4||a<3){
           //non numeric characters
        cxy.setCellValue(alldatavals[a]);
       }
       else {cxy.setCellValue(new Double(alldatavals[a]).intValue()); }
      
       cxy.setCellStyle(stborder);
  
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop

  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  rwx=shet3.createRow(count);  
 rwx.setHeightInPoints(23);
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
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(stborder);
  }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
    }
  //last section of blank rows
  else if(z==pitc_ipd_header1.length-5){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticart_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
    }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(statichtc_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
   }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticpmtct_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
   }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticishtc.get(a).toString()));
   celldsd.setCellStyle(stborder);
    }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticispmtct.get(a).toString()));
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
              
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                for (int e = 0; e < 4; e++) {
                shet3.autoSizeColumn(e);
                }
                //Made my life veery simple...
                shet3.setDisplayGridlines(false);
                shet3.createFreezePane(5,5); 
           return vcthm;      
    
    }
    
    
    public HashMap Pmtct(dbConn conn,HttpServletRequest request,HSSFWorkbook wb) throws SQLException{
        
              HashMap<String , String> Pmtcthm = new HashMap<String,String>(); 
     
                   
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
    
        
    
     //new PMTCT_ANC 
                 
                     //2017
                     String pitc_pmtct_header0[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","PITC PMTCT(ANC ONLY)","","","","","","","","","","","","","","","","","","","","","","","PMTCT STAT","","","","","","","","","","","PMTCT STAT","","","","","","","","","","","PMTCT STAT","","","","","","","","","","","PMTCT STAT","","","","","","","","","","","","","","","","","","","","","","1st ANC","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_pmtct_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Positive","","","","","","","","","","","Negative","","","","","","","","","","","Total ANC Tested","Disaggregated by Age (Fine Disaggregate)","","","","","","","","","","","Disaggregated by status and Age (Fine Disaggregate)","","","","","","","","","","","Disaggregated by status and Age (Fine Disaggregate)","","","","","","","","","","","Disaggregated by status and Age (Fine Disaggregate)","","","","","","","","","","","Disaggregated by Age (Fine Disaggregate)","","","","","","","","","","","1st ANC","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_pmtct_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Unknown age","F","F","F","F","F","F","F","F","F","F","unknown age","F","F","F","F","F","F","F","F","F","F","Total ANC Tested","Numerator","F","F","F","F","F","F","F","F","F","F","Known Positives","F","F","F","F","F","F","F","F","F","F","Newly tested positives","F","F","F","F","F","F","F","F","F","F","New Negatives","F","F","F","F","F","F","F","F","F","F","Denominator","F","F","F","F","F","F","F","F","F","F","1st ANC","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_pmtct_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","F","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","F","<1","1-9","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","Total ANC Tested","Numerator","unknown age","<10","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","Known Positives","unknown age","<10","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","Newly Tested Positives","unknown age","<10","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","New Negatives","unknown age","<10","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","Denominator","unknown age","<10","10-14","15-19","20-24","25-29","30-34","35-39","40-49","50+","1st ANC","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
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
      if(year<2014){ facilitiestable="subpartnera2014"; }
      else  {facilitiestable="subpartnera"+selectedyear;}
  }
   String facilityIds1="";
        facilityIds1="(";
           if(request.getParameter("subcounty")!=null && !request.getParameter("subcounty").equals(""))   {
         String subcounty=request.getParameter("subcounty");
    String getDist="SELECT "+facilitiestable+".SubPartnerID FROM "+facilitiestable+" "
    + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID "
     + "WHERE district.DistrictID='"+subcounty+"'" ;
           subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    
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
         
         subcounty_countywhere=" (county.CountyID='"+county+"')   AND "+facilitiestable+".active=1 and  ";//20160711
         
    conn.rs=conn.st.executeQuery(getCounty);
    while(conn.rs.next()){
     allFacilities.add(conn.rs.getString(1));
     facilityIds1+=" moh731.SubPartnerID='"+conn.rs.getString(1)+"' || "; }
   
    facilityIds1 = facilityIds1.substring(0, facilityIds1.length()-3);
     facilityIds1+=") && ";  } 
        else{ facilityIds1=""; }   
        
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
        
      HSSFSheet shet3=wb.createSheet("PMTCT ANC & STAT"); 
   String county="";
   String  district="";
    String facilityname="";

  shet3.setColumnWidth(0, 4000);  
  shet3.setColumnWidth(1, 5000);  
  shet3.setColumnWidth(2,5000);  
  //shet3.setColumnWidth(6,5000);
   HSSFRow rw0=shet3.createRow(0);
           rw0.setHeightInPoints(25);
           
           HSSFCell  c1,c2,c3,c4,c5,c6,c7,c8;
         c1=rw0.createCell(0);
      //_____________________________________________________________report heading row 0   
      c1.setCellValue(period1);
      c1.setCellStyle(stylemainHeader);
      for(int j=1;j<=pitc_pmtct_header0.length-1;j++){
      c1=rw0.createCell(j);
       c1.setCellStyle(stylemainHeader);
      }
      
      //-----------------------------------row 1 header 
       rw0=shet3.createRow(2); 
       rw0.setHeightInPoints(25);
    for (int i=0;i<pitc_pmtct_header0.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_pmtct_header0[i]);
      clx.setCellStyle(stylemainHeader);
        }
      //-----------------------------------row 1 header b 
       rw0=shet3.createRow(3); 
       rw0.setHeightInPoints(25);
    for (int i=0;i<pitc_pmtct_header1.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_pmtct_header1[i]);
      clx.setCellStyle(stylemainHeader);
        }
 //-----------------------------------row 2 header 
       rw0=shet3.createRow(4); 
       rw0.setHeightInPoints(25);
    for (int i=0;i<pitc_pmtct_header2.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_pmtct_header2[i]);
      clx.setCellStyle(stylemainHeader);
        }
      
    //-----------------------------------row 3 header 
   rw0=shet3.createRow(5); 
   rw0.setHeightInPoints(25);
 
      
    for (int i=0;i<pitc_pmtct_header3.length;i++)
       {
      HSSFCell clx=rw0.createCell(i);
      clx.setCellValue(pitc_pmtct_header3[i]);
      clx.setCellStyle(stylemainHeader);
       }
    String mergeinfor[]={"0,0,0,"+(pitc_pmtct_header0.length-1)+"","2,5,0,0","2,5,1,1","2,5,2,2","2,5,3,3","2,5,4,4","3,5,27,27","4,5,28,28","4,5,39,39","4,5,50,50","4,5,61,61","4,5,72,72","2,2,5,27","2,2,28,49","3,3,5,15","3,3,16,26","3,3,28,49","2,5,83,83","2,5,84,84","2,5,85,85","2,5,86,86","2,5,87,87","2,5,88,88","2,2,50,82","3,3,50,82"};  
   
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
    int blankrows=pitc_pmtct_header1.length;
    
   String getstaticfacilities="SELECT   county.County as county,district.DistrictNom as district," //
            + " "+facilitiestable+".SubPartnerNom as facility, "+facilitiestable+".CentreSanteId as mflcode, "+facilitiestable+".PMTCT_Support as htcsupport, IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT "
           + " FROM    "+facilitiestable+" join (district join county on county.CountyID=district.CountyID)  on district.DistrictID = "+facilitiestable+".DistrictID    where ( HTC=1 or PMTCT=1)  AND "+facilitiestable+".active=1  group by "+facilitiestable+".SubPartnerID  "; 
    
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
     
     
     //dsdta=conn.rs.getString("pmtctsupport");   
     String dsdta="DSD"; //static as of 201606 
     staticdsd_ta.add(dsdta);   
    }
   
     //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
 
 
    String facilid="";
    String facilname="";
    String dsdta="";
     
    String get731data="SELECT "+
            //--------pmtct anc tested -------------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN HV0201 end)) as pmtct_anc_tes," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*0) end)) as pmtct_anc_tes_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_1) end)) as pmtct_anc_tes_1," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*(f_4+f_9)) end)) as pmtct_anc_tes_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_14) end)) as pmtct_anc_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_19) end)) as pmtct_anc_tes_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_24) end)) as pmtct_anc_tes_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_29) end)) as pmtct_anc_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_34) end)) as pmtct_anc_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_39) end)) as pmtct_anc_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_49) end)) as pmtct_anc_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_50) end)) as pmtct_anc_tes_50, "+
            
            //-------pmtct anc new_positive------------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN HV0206 end)) as pmtct_newpositive ," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*0) end)) as pmtct_pos_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_1) end)) as pmtct_pos_1," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*(f_4+f_9)) end)) as pmtct_pos_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_14) end)) as pmtct_pos_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_19) end)) as pmtct_pos_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_24) end)) as pmtct_pos_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_29) end)) as pmtct_pos_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_34) end)) as pmtct_pos_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_39) end)) as pmtct_pos_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_49) end)) as pmtct_pos_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_50) end)) as pmtct_pos_50,"+
             
            
             //-------pmtct anc negative------------------\
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206) end)))  AS pmtct_new_negatives, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*0) end)))   - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*0) end))) AS pmtct_neg_unknown, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_1) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_1) end))) AS pmtct_neg_1, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*(f_4+f_9)) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*(f_4+f_9)) end)))   AS pmtct_neg_1_9, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_14) end))) - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_14) end)))  AS pmtct_neg_10_14, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_19) end))) - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_19) end)))   AS pmtct_neg_15_19, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_24) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_24) end)))  AS pmtct_neg_20_24, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_29) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_29) end)))  AS pmtct_neg_25_29, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_34) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_34) end)))   AS pmtct_neg_30_34, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_39) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_39) end)))   AS pmtct_neg_35_39, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_49) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_49) end)))   AS pmtct_neg_40_49, " +
            "(ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201*f_50) end)))  - (ROUND(SUM( CASE WHEN indicator='PMTCT_New_Postive' THEN (HV0206*f_50) end)))   AS pmtct_neg_50,"+

            
                //-------pmtct stat numerator----------------
        
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (HV0201 + HV0205) end)) as pmtct_tes_numerator," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*0) end)) as pmtct_statnum_tes_unknown," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*(f_1+f_4+f_9)) end)) as pmtct_statnum_tes_10," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_14) end)) as pmtct_statnum_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_19) end)) as pmtct_statnum_tes_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_24) end)) as pmtct_statnum_tes_20_24," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_29) end)) as pmtct_statnum_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_34) end)) as pmtct_statnum_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_39) end)) as pmtct_statnum_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_49) end)) as pmtct_statnum_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN ((HV0201 + HV0205)*f_50) end)) as pmtct_statnum_tes_50,"+
            
            //-------pmtct stat denominator----------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)) end)) as pmtct_tes_denominator , " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*0) end)) as pmtct_statden_tes_unknown, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*(f_1+f_4+f_9)) end)) as pmtct_statden_tes_10, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_14) end)) as pmtct_statden_tes_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_19) end)) as pmtct_statden_tes_15_19, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_24) end)) as pmtct_statden_tes_20_24, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_29) end)) as pmtct_statden_tes_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_34) end)) as pmtct_statden_tes_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_39) end)) as pmtct_statden_tes_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_49) end)) as pmtct_statden_tes_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_ANC' THEN (IFNULL(new_anc,0)*f_50) end)) as pmtct_statden_tes_50,"+
            
            //-------pmtct stat numerator----------------
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205) end)) as pmtct_knownpositive ," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*0) end)) as pmtct_kp_unknown, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*(f_4+f_9)) end)) as pmtct_kp_1_9," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_14) end)) as pmtct_kp_10_14," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_19) end)) as pmtct_kp_15_19," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_24) end)) as pmtct_kp_20_24, " +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_29) end)) as pmtct_kp_25_29," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_34) end)) as pmtct_kp_30_34," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_39) end)) as pmtct_kp_35_39," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_49) end)) as pmtct_kp_40_49," +
            "ROUND(SUM( CASE WHEN indicator='PMTCT_Known_Postive' THEN (HV0205*f_50) end)) as pmtct_kp_50,"
            
            + " county.County,district.DistrictNom,"
            + ""+facilitiestable+".SubPartnerNom,"+facilitiestable+".CentreSanteId,"+facilitiestable+".PMTCT_Support ,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume"// facility details
            +" ,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT,IFNULL(SUM(new_anc),0) AS new_anc "//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
            + " FROM moh731 JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
            + " district.CountyID=county.CountyID "
            + " left JOIN new_anc on moh731.id=new_anc.id "//added on 9th October 2017
            + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "//added on 4th Jan 2018
            + " WHERE "
            + " "+facilityIds1+" "+duration1+" && ( "+facilitiestable+".PMTCT=1) "
            + " AND (indicator='PMTCT_Known_Postive' OR indicator='PMTCT_New_Postive' OR indicator='PMTCT_ANC')  AND "+facilitiestable+".active=1  "
            + " GROUP BY moh731.SubPartnerID " ;
    
    
    int rowposit=6;
    
     System.out.println("2018q1 PMTCT : "+get731data);
    conn.rs=conn.st.executeQuery(get731data);
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
             staticishtc.remove(mflindex);
             staticispmtct.remove(mflindex);
        
                        }
        
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
        
     county=conn.rs.getString("county");
     district=conn.rs.getString("DistrictNom");
     district=district.substring(0,1).toUpperCase()+district.substring(1).toLowerCase();
     facilityname=conn.rs.getString("SubPartnerNom");
     mflcode=conn.rs.getInt("CentreSanteId"); 
     if(conn.rs.getString("PMTCT_Support")==null){
     dsdta="DSD";
     }
     else  if(conn.rs.getString("PMTCT_Support").equals("")){
     dsdta="DSD";
     }
     else {
     dsdta=conn.rs.getString("PMTCT_Support");   
     }
     
     int arthv=0;
     int htchv=0;
     int pmtcthv=0;
     
      if(conn.rs.getString("ART_highvolume")!=null){arthv=conn.rs.getInt("ART_highvolume");}
      if(conn.rs.getString("HTC_highvolume")!=null){htchv=conn.rs.getInt("HTC_highvolume"); }
      if(conn.rs.getString("PMTCT_highvolume")!=null){pmtcthv=conn.rs.getInt("PMTCT_highvolume");}
     
double pmtct_anc_tes=conn.rs.getDouble("pmtct_anc_tes");
double pmtct_anc_tes_unknown=conn.rs.getDouble("pmtct_anc_tes_unknown");
double pmtct_anc_tes_1=conn.rs.getDouble("pmtct_anc_tes_1");
double pmtct_anc_tes_1_9=conn.rs.getDouble("pmtct_anc_tes_1_9");
double pmtct_anc_tes_10_14=conn.rs.getDouble("pmtct_anc_tes_10_14");
double pmtct_anc_tes_15_19=conn.rs.getDouble("pmtct_anc_tes_15_19");
double pmtct_anc_tes_20_24=conn.rs.getDouble("pmtct_anc_tes_20_24");
double pmtct_anc_tes_25_29=conn.rs.getDouble("pmtct_anc_tes_25_29");
double pmtct_anc_tes_30_34=conn.rs.getDouble("pmtct_anc_tes_30_34");
double pmtct_anc_tes_35_39=conn.rs.getDouble("pmtct_anc_tes_35_39");
double pmtct_anc_tes_40_49=conn.rs.getDouble("pmtct_anc_tes_40_49");
double pmtct_anc_tes_50=conn.rs.getDouble("pmtct_anc_tes_50");



double pmtct_knownpositive=conn.rs.getDouble("pmtct_knownpositive");
double pmtct_kp_unknown=conn.rs.getDouble("pmtct_kp_unknown");
double pmtct_kp_1_9=conn.rs.getDouble("pmtct_kp_1_9");
double pmtct_kp_10_14=conn.rs.getDouble("pmtct_kp_10_14");
double pmtct_kp_15_19=conn.rs.getDouble("pmtct_kp_15_19");
double pmtct_kp_20_24=conn.rs.getDouble("pmtct_kp_20_24");
double pmtct_kp_25_29=conn.rs.getDouble("pmtct_kp_25_29");
double pmtct_kp_30_34=conn.rs.getDouble("pmtct_kp_30_34");
double pmtct_kp_35_39=conn.rs.getDouble("pmtct_kp_35_39");
double pmtct_kp_40_49=conn.rs.getDouble("pmtct_kp_40_49");
double pmtct_kp_50=conn.rs.getDouble("pmtct_kp_50");


double pmtct_newpositive=conn.rs.getDouble("pmtct_newpositive");
double pmtct_pos_unknown=conn.rs.getDouble("pmtct_pos_unknown");
double pmtct_pos_1=conn.rs.getDouble("pmtct_pos_1");
double pmtct_pos_1_9=conn.rs.getDouble("pmtct_pos_1_9");
double pmtct_pos_10_14=conn.rs.getDouble("pmtct_pos_10_14");
double pmtct_pos_15_19=conn.rs.getDouble("pmtct_pos_15_19");
double pmtct_pos_20_24=conn.rs.getDouble("pmtct_pos_20_24");
double pmtct_pos_25_29=conn.rs.getDouble("pmtct_pos_25_29");
double pmtct_pos_30_34=conn.rs.getDouble("pmtct_pos_30_34");
double pmtct_pos_35_39=conn.rs.getDouble("pmtct_pos_35_39");
double pmtct_pos_40_49=conn.rs.getDouble("pmtct_pos_40_49");
double pmtct_pos_50=conn.rs.getDouble("pmtct_pos_50");

double pmtct_new_negatives=conn.rs.getDouble("pmtct_new_negatives");
double pmtct_neg_unknown=conn.rs.getDouble("pmtct_neg_unknown");
double pmtct_neg_1=conn.rs.getDouble("pmtct_neg_1");
double pmtct_neg_1_9=conn.rs.getDouble("pmtct_neg_1_9");
double pmtct_neg_10_14=conn.rs.getDouble("pmtct_neg_10_14");
double pmtct_neg_15_19=conn.rs.getDouble("pmtct_neg_15_19");
double pmtct_neg_20_24=conn.rs.getDouble("pmtct_neg_20_24");
double pmtct_neg_25_29=conn.rs.getDouble("pmtct_neg_25_29");
double pmtct_neg_30_34=conn.rs.getDouble("pmtct_neg_30_34");
double pmtct_neg_35_39=conn.rs.getDouble("pmtct_neg_35_39");
double pmtct_neg_40_49=conn.rs.getDouble("pmtct_neg_40_49");
double pmtct_neg_50=conn.rs.getDouble("pmtct_neg_50");



double pmtct_tes_numerator=conn.rs.getDouble("pmtct_tes_numerator");
double pmtct_statnum_tes_unknown=conn.rs.getDouble("pmtct_statnum_tes_unknown");
double pmtct_statnum_tes_10=conn.rs.getDouble("pmtct_statnum_tes_10");
double pmtct_statnum_tes_10_14=conn.rs.getDouble("pmtct_statnum_tes_10_14");
double pmtct_statnum_tes_15_19=conn.rs.getDouble("pmtct_statnum_tes_15_19");
double pmtct_statnum_tes_20_24=conn.rs.getDouble("pmtct_statnum_tes_20_24");
double pmtct_statnum_tes_25_29=conn.rs.getDouble("pmtct_statnum_tes_25_29");
double pmtct_statnum_tes_30_34=conn.rs.getDouble("pmtct_statnum_tes_30_34");
double pmtct_statnum_tes_35_39=conn.rs.getDouble("pmtct_statnum_tes_35_39");
double pmtct_statnum_tes_40_49=conn.rs.getDouble("pmtct_statnum_tes_40_49");
double pmtct_statnum_tes_50=conn.rs.getDouble("pmtct_statnum_tes_50");

double pmtct_tes_denominator=conn.rs.getDouble("pmtct_tes_denominator");
double pmtct_statden_tes_unknown=conn.rs.getDouble("pmtct_statden_tes_unknown");
double pmtct_statden_tes_10=conn.rs.getDouble("pmtct_statden_tes_10");
double pmtct_statden_tes_10_14=conn.rs.getDouble("pmtct_statden_tes_10_14");
double pmtct_statden_tes_15_19=conn.rs.getDouble("pmtct_statden_tes_15_19");
double pmtct_statden_tes_20_24=conn.rs.getDouble("pmtct_statden_tes_20_24");
double pmtct_statden_tes_25_29=conn.rs.getDouble("pmtct_statden_tes_25_29");
double pmtct_statden_tes_30_34=conn.rs.getDouble("pmtct_statden_tes_30_34");
double pmtct_statden_tes_35_39=conn.rs.getDouble("pmtct_statden_tes_35_39");
double pmtct_statden_tes_40_49=conn.rs.getDouble("pmtct_statden_tes_40_49");
double pmtct_statden_tes_50=conn.rs.getDouble("pmtct_statden_tes_50");
int ancno=conn.rs.getInt("new_anc");
      
     double ancdiff=0; 
     double numeratordiff=0;
     double denominatordiff=0;
     double knownpositivediff=0;
     double newpositivediff=0;
     double diff15to19=0;

     int negativediff=0;
     
    ancdiff=(pmtct_anc_tes-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50+pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50));
    numeratordiff=(pmtct_tes_numerator-(pmtct_statnum_tes_unknown+pmtct_statnum_tes_10+pmtct_statnum_tes_10_14+pmtct_statnum_tes_15_19+pmtct_statnum_tes_20_24+pmtct_statnum_tes_25_29+pmtct_statnum_tes_30_34+pmtct_statnum_tes_35_39+pmtct_statnum_tes_40_49+pmtct_statnum_tes_50));
    denominatordiff=(pmtct_tes_denominator-(pmtct_statden_tes_unknown+pmtct_statden_tes_10+pmtct_statden_tes_10_14+pmtct_statden_tes_15_19+pmtct_statden_tes_20_24+pmtct_statden_tes_25_29+pmtct_statden_tes_30_34+pmtct_statden_tes_35_39+pmtct_statden_tes_40_49+pmtct_statden_tes_50) );
    knownpositivediff=(pmtct_knownpositive-(pmtct_kp_unknown+pmtct_kp_1_9+pmtct_kp_10_14+pmtct_kp_15_19+pmtct_kp_20_24+pmtct_kp_25_29+pmtct_kp_30_34+pmtct_kp_35_39+pmtct_kp_40_49+pmtct_kp_50));
    newpositivediff=(pmtct_newpositive-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50));
    negativediff=((int) Math.round(pmtct_new_negatives)-(int) Math.round((pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50)));
     //compare anc_positive, anc_negative, knownpos against stat numerator   
    
    diff15to19=(pmtct_statden_tes_15_19-(pmtct_pos_15_19+pmtct_neg_15_19+pmtct_kp_15_19));
//    diff25to49=(pmtct_statden_tes_25_49-(pmtct_pos_25_49+pmtct_neg_25_49+pmtct_kp_25_49));
    
        System.out.print("PMTCT_Facility_"+facilityname);
        System.out.print(" ANC DIFF_"+ancdiff);
        
        System.out.println(" ");
     
      //do normalization
     if(1==1){
     while(ancdiff>0){ pmtct_neg_20_24=pmtct_neg_20_24+1; ancdiff--;}
     while(ancdiff<0){  pmtct_neg_20_24=pmtct_neg_20_24-1; ancdiff++;}
     //numerator normalization
      while(numeratordiff>0){ pmtct_statnum_tes_20_24=pmtct_statnum_tes_20_24+1;  numeratordiff--; }
      
       while(numeratordiff<0){  pmtct_statnum_tes_20_24=pmtct_statnum_tes_20_24-1;   numeratordiff++;  }
      
      //denominator normalization
      while(denominatordiff>0){ pmtct_statden_tes_20_24=pmtct_statden_tes_20_24+1;  denominatordiff--;}
       while(denominatordiff<0){  pmtct_statden_tes_20_24=pmtct_statden_tes_20_24-1;   denominatordiff++;  }
    }  
     
    ///**
     while(newpositivediff>0){ pmtct_pos_20_24=pmtct_pos_20_24+1;   newpositivediff--; }
     while(newpositivediff<0){   pmtct_pos_20_24=pmtct_pos_20_24-1;    newpositivediff++; }
 
     while(knownpositivediff>0){ pmtct_kp_20_24=pmtct_kp_20_24+1; knownpositivediff--; }
     while(knownpositivediff<0){   pmtct_kp_20_24=pmtct_kp_20_24-1;  knownpositivediff++;  }
     
     //repeat anc normalization again due to the other normalizations that have happened after
   ancdiff=(pmtct_anc_tes-(pmtct_pos_unknown+pmtct_pos_1+pmtct_pos_1_9+pmtct_pos_10_14+pmtct_pos_15_19+pmtct_pos_20_24+pmtct_pos_25_29+pmtct_pos_30_34+pmtct_pos_35_39+pmtct_pos_40_49+pmtct_pos_50+pmtct_neg_unknown+pmtct_neg_1+pmtct_neg_1_9+pmtct_neg_10_14+pmtct_neg_15_19+pmtct_neg_20_24+pmtct_neg_25_29+pmtct_neg_30_34+pmtct_neg_35_39+pmtct_neg_40_49+pmtct_neg_50));
 
      while(ancdiff>0){ pmtct_neg_20_24=pmtct_neg_20_24+1;  ancdiff--; }
     while(ancdiff<0){   pmtct_neg_20_24=pmtct_neg_20_24-1;  ancdiff++; }
     
     //to get pmtct stat numerator, add neg+pos _+ kps
     
     pmtct_statnum_tes_unknown=(pmtct_pos_unknown+pmtct_neg_unknown+pmtct_kp_unknown);
     pmtct_statnum_tes_10=(pmtct_pos_1_9+pmtct_neg_1_9+pmtct_kp_1_9);
     pmtct_statnum_tes_10_14=(pmtct_pos_10_14+pmtct_neg_10_14+pmtct_kp_10_14);
     pmtct_statnum_tes_15_19=(pmtct_pos_15_19+pmtct_neg_15_19+pmtct_kp_15_19);
     pmtct_statnum_tes_20_24=(pmtct_pos_20_24+pmtct_neg_20_24+pmtct_kp_20_24);
     pmtct_statnum_tes_25_29=(pmtct_pos_25_29+pmtct_neg_25_29+pmtct_kp_25_29);
     pmtct_statnum_tes_30_34=(pmtct_pos_30_34+pmtct_neg_30_34+pmtct_kp_30_34);
     pmtct_statnum_tes_35_39=(pmtct_pos_35_39+pmtct_neg_35_39+pmtct_kp_35_39);
     pmtct_statnum_tes_40_49=(pmtct_pos_40_49+pmtct_neg_40_49+pmtct_kp_40_49);
     pmtct_statnum_tes_50=(pmtct_pos_50+pmtct_neg_50+pmtct_kp_50);
     
             String alldatavals[]={county,district,facilityname,""+mflcode,dsdta
             ,""+pmtct_pos_unknown,""+pmtct_pos_1,""+pmtct_pos_1_9,""+pmtct_pos_10_14,""+pmtct_pos_15_19,""+pmtct_pos_20_24,""+pmtct_pos_25_29,""+pmtct_pos_30_34,""+pmtct_pos_35_39,""+pmtct_pos_40_49,""+pmtct_pos_50
             ,""+pmtct_neg_unknown,""+pmtct_neg_1,""+pmtct_neg_1_9,""+pmtct_neg_10_14,""+pmtct_neg_15_19,""+pmtct_neg_20_24,""+pmtct_neg_25_29,""+pmtct_neg_30_34,""+pmtct_neg_35_39,""+pmtct_neg_40_49,""+pmtct_neg_50
             ,""+pmtct_anc_tes//total anc tested        
             ,""+pmtct_tes_numerator//numerator
             ,""+pmtct_statnum_tes_unknown,""+pmtct_statnum_tes_10,""+pmtct_statnum_tes_10_14,""+pmtct_statnum_tes_15_19,""+pmtct_statnum_tes_20_24,""+pmtct_statnum_tes_25_29,""+pmtct_statnum_tes_30_34,""+pmtct_statnum_tes_35_39,""+pmtct_statnum_tes_40_49,""+pmtct_statnum_tes_50
             
             
             ,""+pmtct_knownpositive//known positive
             ,""+pmtct_kp_unknown,""+pmtct_kp_1_9,""+pmtct_kp_10_14,""+pmtct_kp_15_19,""+pmtct_kp_20_24,""+pmtct_kp_25_29,""+pmtct_kp_30_34,""+pmtct_kp_35_39,""+pmtct_kp_40_49,""+pmtct_kp_50
             
             ,""+pmtct_newpositive//new positives
             ,""+pmtct_pos_unknown,""+pmtct_pos_1_9,""+pmtct_pos_10_14,""+pmtct_pos_15_19,""+pmtct_pos_20_24,""+pmtct_pos_25_29,""+pmtct_pos_30_34,""+pmtct_pos_35_39,""+pmtct_pos_40_49,""+pmtct_pos_50
             
             ,""+pmtct_new_negatives//new negatives
             ,""+pmtct_neg_unknown,""+pmtct_neg_1_9,""+pmtct_neg_10_14,""+pmtct_neg_15_19,""+pmtct_neg_20_24,""+pmtct_neg_25_29,""+pmtct_neg_30_34,""+pmtct_neg_35_39,""+pmtct_neg_40_49,""+pmtct_neg_50
                      
             ,""+pmtct_tes_denominator//denominator
             ,""+pmtct_statden_tes_unknown,""+pmtct_statden_tes_10,""+pmtct_statden_tes_10_14,""+pmtct_statden_tes_15_19,""+pmtct_statden_tes_20_24,""+pmtct_statden_tes_25_29,""+pmtct_statden_tes_30_34,""+pmtct_statden_tes_35_39,""+pmtct_statden_tes_40_49,""+pmtct_statden_tes_50,""+pmtct_tes_denominator
             ,""+arthv,""+htchv,""+pmtcthv,""+conn.rs.getString("HTC"),""+conn.rs.getString("PMTCT")};
             
             
  Pmtcthm.put("cty"+"_"+mflcode, county);
  Pmtcthm.put("sty"+"_"+mflcode, district);
  Pmtcthm.put("fac"+"_"+mflcode, facilityname);
  Pmtcthm.put("mfl"+"_"+mflcode, ""+mflcode);
  Pmtcthm.put("st"+"_"+mflcode, dsdta);
  Pmtcthm.put("ukpf"+"_"+mflcode,"0");
  Pmtcthm.put("ukpm"+"_"+mflcode,"0");
  Pmtcthm.put("pf1"+"_"+mflcode,"0");
  Pmtcthm.put("pm1"+"_"+mflcode,"0");
  Pmtcthm.put("pf4"+"_"+mflcode,"0");
  Pmtcthm.put("pm4"+"_"+mflcode,"0");
  Pmtcthm.put("pf9"+"_"+mflcode,"0");
  Pmtcthm.put("pm9"+"_"+mflcode,"0");
  Pmtcthm.put("pf14"+"_"+mflcode,""+pmtct_pos_10_14);
  Pmtcthm.put("pm14"+"_"+mflcode,"0");
  Pmtcthm.put("pf19"+"_"+mflcode,""+(pmtct_pos_15_19));
  Pmtcthm.put("pm19"+"_"+mflcode,"0");
  Pmtcthm.put("pf24"+"_"+mflcode,""+(pmtct_pos_20_24));
  Pmtcthm.put("pm24"+"_"+mflcode,"0");
  Pmtcthm.put("pf29"+"_"+mflcode,""+(pmtct_pos_25_29));
  Pmtcthm.put("pm29"+"_"+mflcode,"0");
  Pmtcthm.put("pf34"+"_"+mflcode,""+(pmtct_pos_30_34));
  Pmtcthm.put("pm34"+"_"+mflcode,"0");
  Pmtcthm.put("pf39"+"_"+mflcode,""+(pmtct_pos_35_39));
  Pmtcthm.put("pm39"+"_"+mflcode,"0");
  Pmtcthm.put("pf49"+"_"+mflcode,""+(pmtct_pos_40_49));
  Pmtcthm.put("pm49"+"_"+mflcode,"0");
  Pmtcthm.put("pf50"+"_"+mflcode,""+(pmtct_pos_50));
  Pmtcthm.put("pm50"+"_"+mflcode,"0");
  //_____negative
  Pmtcthm.put("uknf"+"_"+mflcode,"0");
  Pmtcthm.put("uknm"+"_"+mflcode,"0");
  Pmtcthm.put("nf1"+"_"+mflcode,"0");
  Pmtcthm.put("nm1"+"_"+mflcode,"0");
  Pmtcthm.put("nf4"+"_"+mflcode,"0");
  Pmtcthm.put("nm4"+"_"+mflcode,"0");
  Pmtcthm.put("nf9"+"_"+mflcode,"0");
  Pmtcthm.put("nm9"+"_"+mflcode,"0");
  Pmtcthm.put("nf14"+"_"+mflcode,""+(pmtct_neg_10_14));
  Pmtcthm.put("nm14"+"_"+mflcode,"0");
  Pmtcthm.put("nf19"+"_"+mflcode,""+(pmtct_neg_15_19));
  Pmtcthm.put("nm19"+"_"+mflcode,"0");
  Pmtcthm.put("nf24"+"_"+mflcode,""+(pmtct_neg_20_24));
  Pmtcthm.put("nm24"+"_"+mflcode,"0");
  Pmtcthm.put("nf29"+"_"+mflcode,""+(pmtct_neg_25_29));
  Pmtcthm.put("nm29"+"_"+mflcode,"0");
  Pmtcthm.put("nf34"+"_"+mflcode,""+(pmtct_neg_30_34));
  Pmtcthm.put("nm34"+"_"+mflcode,"0");
  Pmtcthm.put("nf39"+"_"+mflcode,""+(pmtct_neg_35_39));
  Pmtcthm.put("nm39"+"_"+mflcode,"0");
  Pmtcthm.put("nf49"+"_"+mflcode,""+(pmtct_neg_40_49));
  Pmtcthm.put("nm49"+"_"+mflcode,"0");
  Pmtcthm.put("nf50"+"_"+mflcode,""+(pmtct_neg_50));
  Pmtcthm.put("nm50"+"_"+mflcode,"0");
  Pmtcthm.put("tt"+"_"+mflcode,""+((pmtct_anc_tes)));

             
             

HSSFCell  cxy;
 rw0=shet3.createRow(rowposit);
 rowposit++;
 
         rw0.setHeightInPoints(22);
         int mypos=0;
  for(int a=0;a<alldatavals.length;a++){
       cxy=rw0.createCell(mypos);mypos++;
       if(a==4||a<3){
           //non numeric characters
           //System.out.println(rowposit+"_______SIPITALI"+alldatavals[2]);
        cxy.setCellValue(alldatavals[a]);
       }
       else {
        cxy.setCellValue(new Double(alldatavals[a]).intValue());
       }  cxy.setCellStyle(stborder);  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop

    count=rowposit-1;
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  rwx=shet3.createRow(count);  
 rwx.setHeightInPoints(23);  
 
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
   cellmfl.setCellValue(new Integer(staticmfl.get(a).toString()));
   cellmfl.setCellStyle(stborder);
               }
   
  else if(z==4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(4); 
   celldsd.setCellValue(staticdsd_ta.get(a).toString());
   celldsd.setCellStyle(stborder);
                }
  
  //last section of blank rows
  else if(z==pitc_pmtct_header1.length-5){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticart_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
   
    else if(z==pitc_pmtct_header1.length-4){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(statichtc_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
    }
    
    else if(z==pitc_pmtct_header1.length-3){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticpmtct_hv.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_pmtct_header1.length-2){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticishtc.get(a).toString()));
   celldsd.setCellStyle(stborder);
        }
    else if(z==pitc_pmtct_header1.length-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(z); 
   celldsd.setCellValue(new Integer(staticispmtct.get(a).toString()));
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
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                for (int e = 0; e < 3; e++) {
                shet3.autoSizeColumn(e);
                }
                //Made my life veery simple...
                shet3.setDisplayGridlines(false);
                shet3.createFreezePane(5,6);                 
               
      return Pmtcthm;           
    
    }// end of pmtct
    
    
    
public void TbClinics(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    

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
         
subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1  and  ";//20160711
         
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
        period1="DATIM HTS_TST PITC-TB Clinics for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" tibu_tb_raw.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST PITC-TB Clinics for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" tibu_tb_raw.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST PITC-TB Clinics for April "+year+" to September "+year; 
       
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
      
      period1="DATIM HTS_TST PITC-TB Clinics for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" tibu_tb_raw.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST PITC-TB Clinics for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" tibu_tb_raw.yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST PITC-TB Clinics for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" tibu_tb_raw.yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST PITC-TB Clinics for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("TB Clinics");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_tbclinics_2018(\""+startdate+"\",\""+enddate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}




public void Sti(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    

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
         
subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1  and  ";//20160711
         
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
			
         duration1=" yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for April "+year+" to September "+year; 
       
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
          
      duration1=" yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST (Facility) PITC Modality STI Clinic for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("STI");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_sti(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"sti\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}

public void Emergency(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    

conn.rs=conn.st.executeQuery(getDist);

while(conn.rs.next()){

allFacilities.add(conn.rs.getString(1));
 

 facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
 
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
         
subcounty_countywhere=" (county.CountyID='"+county+"')   AND "+facilitiestable+".active=1  and  ";//20160711
         
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
			
         duration1=" yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for April "+year+" to September "+year; 
       
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
          
      duration1=" yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST (Facility) PITC Modality Emergency Ward for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("Emergency");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_sti(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"emergency\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}

public void Malnutrition(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')   AND "+facilitiestable+".active=1  and ";//20160711
    

conn.rs=conn.st.executeQuery(getDist);

while(conn.rs.next()){

allFacilities.add(conn.rs.getString(1));
 

 facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
 
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
         
subcounty_countywhere=" (county.CountyID='"+county+"')   AND "+facilitiestable+".active=1  and  ";//20160711
         
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
			
         duration1=" yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for April "+year+" to September "+year; 
       
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
          
      duration1=" yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST (Facility) PITC Modality Malnutrition Clinics for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("Malnutrition");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_malnutrition(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"malnutrition\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}
public void IndexTesting(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')  AND "+facilitiestable+".active=1  and ";//20160711
    

conn.rs=conn.st.executeQuery(getDist);

while(conn.rs.next()){

allFacilities.add(conn.rs.getString(1));
 

 facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
 
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
         
subcounty_countywhere=" (county.CountyID='"+county+"')   AND "+facilitiestable+".active=1  and  ";//20160711
         
conn.rs=conn.st.executeQuery(getCounty);

while(conn.rs.next()){
		
		
allFacilities.add(conn.rs.getString(1));
    

facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
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
			
         duration1=" yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for April "+year+" to September "+year; 
       
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
          
      duration1=" yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST (Facility) PITC Modality Index Testing for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("Index Testing");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
               // String qry1 = "call rpt_indextesting(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
                String qry1 = "call rpt_indexpns(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}

public void VMMC_services(dbConn conn, HttpServletRequest request,HSSFWorkbook wb) throws SQLException{

    
    
    
                
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

subcounty_countywhere=" ( district.DistrictID='"+subcounty+"')   AND "+facilitiestable+".active=1 and ";//20160711
    

conn.rs=conn.st.executeQuery(getDist);

while(conn.rs.next()){

allFacilities.add(conn.rs.getString(1));
 

 facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
 
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
         
subcounty_countywhere=" (county.CountyID='"+county+"')  AND "+facilitiestable+".active=1  and  ";//20160711
         
conn.rs=conn.st.executeQuery(getCounty);

while(conn.rs.next()){
		
		
allFacilities.add(conn.rs.getString(1));
    

facilityIds1+=" SubPartnerID='"+conn.rs.getString(1)+"' || ";
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
			
         duration1=" yearmonth Between "+prevYear+"10 and "+year+"09";   
        period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for October "+prevYear+" to September "+year;
        
        startdate=prevYear+"1001";
        enddate=year+"0930";
        
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
//        semi_annual="2";
       if(semi_annual.equals("1")){
     duration1=" yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
     period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for October "+prevYear+" to March "+year;
    //______start and end date_________ 
     startdate=prevYear+"1001";
        enddate=year+"0331";
     
       }
           else{
       duration1=" yearmonth BETWEEN "+year+"04 AND "+year+"09";      
      period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for April "+year+" to September "+year; 
       
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
          
      duration1=" yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth; 
      
      period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for  : "+conn.rs.getString(2).replace("-", " "+prevYear+" TO ")+" "+prevYear+"";
      
     //___start and end date____
     startdate=prevYear+"1001";
     enddate=prevYear+"1231";
      
      }
      else{
          
           //___start and end date____
     startdate=year+startMonth+"01";
     enddate=year+endMonth+""+conn.rs.getString("enddate");
      
          
     duration1=" yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth; 
     
     period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for  : "+conn.rs.getString(2).replace("-", " "+year+" TO ")+" "+year+"";
      }
        }
       
        }//end of quarrtely if  
        
      else if(reportDuration.equals("4")){
          
     month=Integer.parseInt(request.getParameter("month"));
//            month=5;
           String getMonthName="SELECT name,days FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
   if(month>=10){
     duration1=" yearmonth="+prevYear+""+month;    
     period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for : "+conn.rs.getString(1)+"("+prevYear+")";
                 
     //___start and end date____
     startdate=prevYear+month+"01";
     enddate=prevYear+month+conn.rs.getString("days");
     
     }
     else{
  duration1=" yearmonth="+year+"0"+month;  
    period1="DATIM HTS_TST (Facility) PITC Modality VMMC Services for : "+conn.rs.getString(1)+"("+year+")";
    
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
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        HSSFCellStyle stylesum = wb.createCellStyle();
        stylesum.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        stylesum.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        stylesum.setBorderTop(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        stylesum.setBorderRight(HSSFCellStyle.BORDER_THIN);
        stylesum.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        

        stylesum.setFont(fontx);
        stylesum.setWrapText(true);

        HSSFSheet shet = wb.createSheet("VMMC_Services");

        String mwaka="";
        
        if(request.getParameter("year")!=null){
        
            mwaka=request.getParameter("year");
        
        }
       // dbConn conn = new dbConn();
        //========Query 1=================
        
        HSSFRow rw0=shet.createRow(1);
        HSSFCell cell = rw0.createCell(0);
                    cell.setCellValue(""+period1);
                    cell.setCellStyle(style);
        shet.addMergedRegion(new CellRangeAddress(1, 1, 0,5));
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_vmmcserv(\""+startdate.substring(0, 6)+"\",\""+enddate.substring(0, 6)+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                HSSFRow rw = shet.createRow(count1);
rw.setHeightInPoints(26);
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    HSSFCell cell0 = rw.createCell(i - 1);
                    cell0.setCellValue(metaData.getColumnLabel(i).replace("_"," "));
                    cell0.setCellStyle(stylex);

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            HSSFRow rw = shet.createRow(count1);

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

                HSSFCell cell0 = rw.createCell(a);
                 if(a==3){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                 else if(a > 4){
                
                    cell0.setCellValue(conn.rs.getInt(mycolumns1.get(a).toString()));
                    
                   }
                else {
                    
                    cell0.setCellValue(conn.rs.getString("" + mycolumns1.get(a)));
                }
            
                cell0.setCellStyle(style2);

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
        for (int i = 0; i <= columnCount; i++) {
            shet.autoSizeColumn(i);
        }

        shet.setDisplayGridlines(false);
        shet.createFreezePane(5, 4); 



}

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboards;

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
 * @author Emmanuel E
 */
public class htsdataset extends HttpServlet {
    
    
     String subcounty_countywhere=" (1=1) and ";
   
            private boolean isIPD=false;
            private boolean isOPD=false;
            private boolean isVCT=false;
            private  int totalopdglobal=0;
              HttpSession session=null;
    
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       
            response.setContentType("text/html;charset=UTF-8");
            
             session = request.getSession();
             
                  dbConn conn = new dbConn();
            
                  HSSFWorkbook wb=new HSSFWorkbook();  
              
               //call the main method which calls other totals
               
               String startyearmonth="201803";
               String endyearmonth="201803";
               String facil="";
               
                HtsTst(conn, startyearmonth,endyearmonth,facil);
               
                 
            
         if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
            
           
            IdGenerator IG = new IdGenerator();
            String createdOn = IG.CreatedOn();
            
//            ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
//            wb.write(outByteStream);
//            byte[] outArray = outByteStream.toByteArray();
//            response.setContentType("application/ms-excel");
//            response.setContentLength(outArray.length);
//            response.setHeader("Expires:", "0"); // eliminates browser caching
//            response.setHeader("Content-Disposition", "attachment; filename=datim_HTC_TST_2018_Gen_On_" + createdOn + ".xls");
//            OutputStream outStream = response.getOutputStream();
//            outStream.write(outArray);
//            outStream.flush();
//            outStream.close();

    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(htsdataset.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        processRequest(request, response);
    } catch (SQLException ex) {
        Logger.getLogger(htsdataset.class.getName()).log(Level.SEVERE, null, ex);
    }
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
 
 public HashMap HtsTst(dbConn conn,String sdate,String edate,String facil) throws SQLException {
 
 
 
                 //VMMC
                 HashMap<String,String> ipdhm;
                 HashMap<String,String> opdhm;
                 HashMap<String,String> vcthm;
                 HashMap<String,String> pmtcthm;
                 HashMap<String,String> htshm;
                 
                 htshm= new HashMap<String, String>();
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
 // if(1==1){VMMC(conn,sdate,edate,facil);}
  
   if(15==16){
    VMMC_services(conn,sdate,edate,facil);
             }
  
 //==================================================================================================
 //HTC RESULTS BY SDP           
 //==================================================================================================
           
            if(1==2){
            
                HtsSdp(conn,sdate,edate,facil);
            }

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%                 
          
            //_______________2017Q1 changes start here___________________
          
            //IPD, OPD and VCT are similar in many ways. we need to have global variables that can be used to alert the system the report it is generating
  
            //the below will be activated each at its own loop/worksheet
           
           
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC INPATIENT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(5==5){
                     ipdhm=Ipd(conn,sdate,edate,facil);
                 }//end of IPD 5==5
            
               
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC OPD
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                
                 if(6==6){
                    opdhm= Opd(conn,sdate,edate,facil);
                 }//end of 6==6 OPD

//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PITC VCT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                 if(8==8){
                 
                vcthm= Vct(conn,sdate,edate,facil);
                 }//end of 8==8
         
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
//PMTCT ANC AND STAT
//%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%  
                 
                if(9==9){
                   pmtcthm = Pmtct(conn,sdate,edate,facil);
                        }//end of 9==9
         
               if(10==11){
    TbClinics(conn,sdate,edate,facil);
                         }
               //malnutrition
               if(11==12){
    Malnutrition(conn,sdate,edate,facil);
                         }
               //malnutrition
               if(12==13){
    Sti(conn,sdate,edate,facil);
                         }
               if(13==14){
    Emergency(conn,sdate,edate,facil);
                         }
                if(14==15){
    IndexTesting(conn,sdate,edate,facil);
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

year=Integer.parseInt(edate.substring(0,4));


  
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


 if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+")"; }




 
  reportDuration="4";


        
        
      
        

        String period1="";
    
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

    
        
         
        
    
          
     month=new Integer(edate.substring(4,6));
     
      

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

      

        String mwaka="";
        
       
        
        mwaka=""+year;
        
   
                    
                int count1  = 1;
        
                //output header 
                
        String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
        String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
        String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Total tested","Total positive","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total Tested","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                    
					

    String mergeinfor[]={"0,0,0,"+pitc_ipd_header1.length+"","2,4,0,0","2,4,1,1","2,4,2,2","2,4,3,3","2,4,4,4","2,4,5,5","2,4,6,6","2,2,7,30","2,2,31,54","2,4,55,55","2,4,56,56","2,4,57,57","2,4,41,41","3,3,7,8","3,3,31,32","2,4,58,58","2,4,59,59","2,4,60,60"};  
             
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
 for(int ymn=new Integer(sdate);ymn<=new Integer(edate);ymn++){
 
 double gtt=new Double(ipdhm.getOrDefault("gtt_"+mfl+ymn,"0"));
 double gtp=new Double(ipdhm.getOrDefault("gtp_"+mfl+ymn,"0"));
 double ukpf=(new Double(ipdhm.getOrDefault("ukpf_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("ukpf_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("ukpf_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("ukpf_"+mfl+ymn,"0")));
 double ukpm=(new Double(ipdhm.getOrDefault("ukpm_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("ukpm_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("ukpm_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("ukpm_"+mfl+ymn,"0")));
 double pf1=(new Double(ipdhm.getOrDefault("pf1_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf1_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf1_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf1_"+mfl+ymn,"0")));
 double pm1=(new Double(ipdhm.getOrDefault("pm1_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm1_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm1_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm1_"+mfl+ymn,"0")));
 double pf4=(new Double(ipdhm.getOrDefault("pf4_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf4_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf4_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf4_"+mfl+ymn,"0")));
 double pm4=(new Double(ipdhm.getOrDefault("pm4_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm4_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm4_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm4_"+mfl+ymn,"0")));
 double pf9=(new Double(ipdhm.getOrDefault("pf9_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf9_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf9_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf9_"+mfl+ymn,"0")));
 double pm9=(new Double(ipdhm.getOrDefault("pm9_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm9_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm9_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm9_"+mfl+ymn,"0")));
 double pf14=(new Double(ipdhm.getOrDefault("pf14_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf14_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf14_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf14_"+mfl+ymn,"0")));
 double pm14=(new Double(ipdhm.getOrDefault("pm14_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm14_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm14_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm14_"+mfl+ymn,"0")));
 double pf19=(new Double(ipdhm.getOrDefault("pf19_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf19_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf19_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf19_"+mfl+ymn,"0")));
 double pm19=(new Double(ipdhm.getOrDefault("pm19_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm19_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm19_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm19_"+mfl+ymn,"0")));
 double pf24=(new Double(ipdhm.getOrDefault("pf24_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf24_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf24_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf24_"+mfl+ymn,"0")));
 double pm24=(new Double(ipdhm.getOrDefault("pm24_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm24_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm24_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm24_"+mfl+ymn,"0")));
 double pf29=(new Double(ipdhm.getOrDefault("pf29_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf29_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf29_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf29_"+mfl+ymn,"0")));
 double pm29=(new Double(ipdhm.getOrDefault("pm29_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm29_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm29_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm29_"+mfl+ymn,"0")));
 double pf34=(new Double(ipdhm.getOrDefault("pf34_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf34_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf34_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf34_"+mfl+ymn,"0")));
 double pm34=(new Double(ipdhm.getOrDefault("pm34_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm34_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm34_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm34_"+mfl+ymn,"0")));
 double pf39=(new Double(ipdhm.getOrDefault("pf39_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf39_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf39_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf39_"+mfl+ymn,"0")));
 double pm39=(new Double(ipdhm.getOrDefault("pm39_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm39_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm39_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm39_"+mfl+ymn,"0")));
 double pf49=(new Double(ipdhm.getOrDefault("pf49_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf49_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf49_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf49_"+mfl+ymn,"0")));
 double pm49=(new Double(ipdhm.getOrDefault("pm49_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm49_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm49_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm49_"+mfl+ymn,"0")));
 double pf50=(new Double(ipdhm.getOrDefault("pf50_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pf50_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pf50_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pf50_"+mfl+ymn,"0")));
 double pm50=(new Double(ipdhm.getOrDefault("pm50_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("pm50_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("pm50_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("pm50_"+mfl+ymn,"0")));
 
 
 
 double uknf=(new Double(ipdhm.getOrDefault("uknf_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("uknf_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("uknf_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("uknf_"+mfl+ymn,"0")));
 double uknm=(new Double(ipdhm.getOrDefault("uknm_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("uknm_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("uknm_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("uknm_"+mfl+ymn,"0")));
 double nf1=(new Double(ipdhm.getOrDefault("nf1_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf1_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf1_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf1_"+mfl+ymn,"0")));
 double nm1=(new Double(ipdhm.getOrDefault("nm1_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm1_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm1_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm1_"+mfl+ymn,"0")));
 double nf4=(new Double(ipdhm.getOrDefault("nf4_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf4_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf4_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf4_"+mfl+ymn,"0")));
 double nm4=(new Double(ipdhm.getOrDefault("nm4_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm4_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm4_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm4_"+mfl+ymn,"0")));
 double nf9=(new Double(ipdhm.getOrDefault("nf9_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf9_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf9_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf9_"+mfl+ymn,"0")));
 double nm9=(new Double(ipdhm.getOrDefault("nm9_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm9_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm9_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm9_"+mfl+ymn,"0")));
 double nf14=(new Double(ipdhm.getOrDefault("nf14_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf14_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf14_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf14_"+mfl+ymn,"0")));
 double nm14=(new Double(ipdhm.getOrDefault("nm14_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm14_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm14_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm14_"+mfl+ymn,"0")));
 double nf19=(new Double(ipdhm.getOrDefault("nf19_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf19_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf19_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf19_"+mfl+ymn,"0")));
 double nm19=(new Double(ipdhm.getOrDefault("nm19_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm19_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm19_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm19_"+mfl+ymn,"0")));
 double nf24=(new Double(ipdhm.getOrDefault("nf24_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf24_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf24_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf24_"+mfl+ymn,"0")));
 double nm24=(new Double(ipdhm.getOrDefault("nm24_"+mfl+ymn,"0")) 
         + new Double(opdhm.getOrDefault("nm24_"+mfl+ymn,"0")) 
         + new Double(vcthm.getOrDefault("nm24_"+mfl+ymn,"0"))
         + new Double(pmtcthm.getOrDefault("nm24_"+mfl+ymn,"0")));
 double nf29=(new Double(ipdhm.getOrDefault("nf29_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf29_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf29_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf29_"+mfl+ymn,"0")));
 double nm29=(new Double(ipdhm.getOrDefault("nm29_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm29_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm29_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm29_"+mfl+ymn,"0")));
 double nf34=(new Double(ipdhm.getOrDefault("nf34_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf34_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf34_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf34_"+mfl+ymn,"0")));
 double nm34=(new Double(ipdhm.getOrDefault("nm34_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm34_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm34_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm34_"+mfl+ymn,"0")));
 double nf39=(new Double(ipdhm.getOrDefault("nf39_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf39_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf39_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf39_"+mfl+ymn,"0")));
 double nm39=(new Double(ipdhm.getOrDefault("nm39_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm39_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm39_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm39_"+mfl+ymn,"0")));
 double nf49=(new Double(ipdhm.getOrDefault("nf49_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf49_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf49_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf49_"+mfl+ymn,"0")));
 double nm49=(new Double(ipdhm.getOrDefault("nm49_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm49_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm49_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm49_"+mfl+ymn,"0")));
 double nf50=(new Double(ipdhm.getOrDefault("nf50_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nf50_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nf50_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nf50_"+mfl+ymn,"0")));
 double nm50=(new Double(ipdhm.getOrDefault("nm50_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("nm50_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("nm50_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("nm50_"+mfl+ymn,"0")));
 double tt  =(new Double(ipdhm.getOrDefault("tt_"+mfl+ymn,"0")) + new Double(opdhm.getOrDefault("tt_"+mfl+ymn,"0")) + new Double(vcthm.getOrDefault("tt_"+mfl+ymn,"0"))+ new Double(pmtcthm.getOrDefault("tt_"+mfl+ymn,"0")));
 String burdencategory = opdhm.getOrDefault("burdencategory_"+mfl+ymn,"");
 String constituency = opdhm.getOrDefault("constituency_"+mfl+ymn,"");
 String ward = opdhm.getOrDefault("ward_"+mfl+ymn,"");
 String yr = opdhm.getOrDefault("year_"+mfl+ymn,"");
 String semiannual = opdhm.getOrDefault("semiannual_"+mfl+ymn,"");
 String qtr = opdhm.getOrDefault("quarter_"+mfl+ymn,"");
 String mn = opdhm.getOrDefault("month_"+mfl+ymn,"");
 String ym = opdhm.getOrDefault("yearmonth_"+mfl+ymn,"");
 String ownedby = opdhm.getOrDefault("ownedby_"+mfl+ymn,"");
 String facilitytype = opdhm.getOrDefault("facilitytype_"+mfl+ymn,"");
 String art_hv = opdhm.getOrDefault("art_hv_"+mfl+ymn,"0");
 String htc_hv = opdhm.getOrDefault("htc_hv_"+mfl+ymn,"0");
 String pmtct_hv = opdhm.getOrDefault("pmtct_hv_"+mfl+ymn,"0");
 String activity_hv = opdhm.getOrDefault("activity_hv_"+mfl+ymn,"0");
 String latitude = opdhm.getOrDefault("latitude_"+mfl+ymn,"");
 String longitude = opdhm.getOrDefault("longitude_"+mfl+ymn,"");
 String maleclinic = opdhm.getOrDefault("maleclinic_"+mfl+ymn,"0");
 String adoleclinic = opdhm.getOrDefault("adoleclinic_"+mfl+ymn,"0");
 String viremiaclinic = ipdhm.getOrDefault("viremiaclinic_"+mfl+ymn,"0");
 String emrsite = opdhm.getOrDefault("emrsite_"+mfl+ymn,"0");
 String linkdesk = opdhm.getOrDefault("linkdesk_"+mfl+ymn,"0");
 String islocked = opdhm.getOrDefault("islocked_"+mfl+ymn,"0");

 //now put the values in an output
 
 
  htshm.put("cty"+"_"+mfl+ymn, cty);
  htshm.put("sty"+"_"+mfl+ymn, sty);
  htshm.put("fac"+"_"+mfl+ymn, fac);
  htshm.put("mfl"+"_"+mfl+ymn, ""+mfl);
  htshm.put("st"+"_"+mfl+ymn, st);
  htshm.put("gtt"+"_"+mfl+ymn,""+gtt);
  htshm.put("gtp"+"_"+mfl+ymn,""+gtp);
  htshm.put("ukpf"+"_"+mfl+ymn,""+ukpf);
  htshm.put("ukpm"+"_"+mfl+ymn,""+ukpm);
  htshm.put("pf1"+"_"+mfl+ymn,""+pf1);
  htshm.put("pm1"+"_"+mfl+ymn,""+pm1);
  htshm.put("pf4"+"_"+mfl+ymn,""+pf4);
  htshm.put("pm4"+"_"+mfl+ymn,""+pm4);
  htshm.put("pf9"+"_"+mfl+ymn,""+pf9);
  htshm.put("pm9"+"_"+mfl+ymn,""+pm9);
  htshm.put("pf14"+"_"+mfl+ymn,""+pf14);
  htshm.put("pm14"+"_"+mfl+ymn,""+pm14);
  htshm.put("pf19"+"_"+mfl+ymn,""+pf19);
  htshm.put("pm19"+"_"+mfl+ymn,""+pm19);
  htshm.put("pf24"+"_"+mfl+ymn,""+pf24);
  htshm.put("pm24"+"_"+mfl+ymn,""+pm24);
  htshm.put("pf29"+"_"+mfl+ymn,""+pf29);
  htshm.put("pm29"+"_"+mfl+ymn,""+pm29);
  htshm.put("pf34"+"_"+mfl+ymn,""+pf34);
  htshm.put("pm34"+"_"+mfl+ymn,""+pm34);
  htshm.put("pf39"+"_"+mfl+ymn,""+pf39);
  htshm.put("pm39"+"_"+mfl+ymn,""+pm39);
  htshm.put("pf49"+"_"+mfl+ymn,""+pf49);
  htshm.put("pm49"+"_"+mfl+ymn,""+pm49);
  htshm.put("pf50"+"_"+mfl+ymn,""+pf50);
  htshm.put("pm50"+"_"+mfl+ymn,""+pm50);
  //_____negative
  htshm.put("uknf"+"_"+mfl+ymn,""+uknf);
  htshm.put("uknm"+"_"+mfl+ymn,""+uknm);
  htshm.put("nf1"+"_"+mfl+ymn,""+nf1);
  htshm.put("nm1"+"_"+mfl+ymn,""+nm1);
  htshm.put("nf4"+"_"+mfl+ymn,""+nf4);
  htshm.put("nm4"+"_"+mfl+ymn,""+nm4);
  htshm.put("nf9"+"_"+mfl+ymn,""+nf9);
  htshm.put("nm9"+"_"+mfl+ymn,""+nm9);
  htshm.put("nf14"+"_"+mfl+ymn,""+nf14);
  htshm.put("nm14"+"_"+mfl+ymn,""+nm14);
  htshm.put("nf19"+"_"+mfl+ymn,""+nf19);
  htshm.put("nm19"+"_"+mfl+ymn,""+nm19);
  htshm.put("nf24"+"_"+mfl+ymn,""+nf24);
  htshm.put("nm24"+"_"+mfl+ymn,""+nm24);
  htshm.put("nf29"+"_"+mfl+ymn,""+nf29);
  htshm.put("nm29"+"_"+mfl+ymn,""+nm29);
  htshm.put("nf34"+"_"+mfl+ymn,""+nf34);
  htshm.put("nm34"+"_"+mfl+ymn,""+nm34);
  htshm.put("nf39"+"_"+mfl+ymn,""+nf39);
  htshm.put("nm39"+"_"+mfl+ymn,""+nm39);
  htshm.put("nf49"+"_"+mfl+ymn,""+nf49);
  htshm.put("nm49"+"_"+mfl+ymn,""+nm49);
  htshm.put("nf50"+"_"+mfl+ymn,""+nf50);
  htshm.put("nm50"+"_"+mfl+ymn,""+nm50);
  htshm.put("tt"+"_"+mfl+ymn,""+tt);
  htshm.put("burdencategory"+"_"+mfl+ymn, burdencategory);
  htshm.put("constituency"+"_"+mfl+ymn, constituency);
  htshm.put("ward"+"_"+mfl+ymn, ward);
  htshm.put("year"+"_"+mfl+ymn, yr);
  htshm.put("semiannual"+"_"+mfl+ymn, semiannual);
  htshm.put("quarter"+"_"+mfl+ymn, qtr);
  htshm.put("month"+"_"+mfl+ymn, mn);
  htshm.put("yearmonth"+"_"+mfl+ymn, ym);
  htshm.put("ownedby"+"_"+mfl+ymn, ownedby);
  htshm.put("facilitytype"+"_"+mfl+ymn, facilitytype);
  htshm.put("art_hv"+"_"+mfl+ymn, art_hv);
  htshm.put("htc_hv"+"_"+mfl+ymn, htc_hv);
  htshm.put("pmtct_hv"+"_"+mfl+ymn, pmtct_hv);
  htshm.put("activity_hv"+"_"+mfl+ymn, activity_hv);
  htshm.put("latitude"+"_"+mfl+ymn, latitude);
  htshm.put("longitude"+"_"+mfl+ymn, longitude);
  htshm.put("maleclinic"+"_"+mfl+ymn, maleclinic);
  htshm.put("adoleclinic"+"_"+mfl+ymn, adoleclinic);
  htshm.put("viremiaclinic"+"_"+mfl+ymn, viremiaclinic);
  htshm.put("emrsite"+"_"+mfl+ymn, emrsite);
  htshm.put("linkdesk"+"_"+mfl+ymn, linkdesk);
  htshm.put("islocked"+"_"+mfl+ymn, islocked);
 



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
        ,""+tt
        ,""+arthv
        ,""+htchv
        ,""+pmtcthv
        ,""+conn.rs.getString("HTC")
        ,""+conn.rs.getString("PMTCT")
          
        };

 totalcolumns=alldatavals.length;
            //data rows     
           
int mypos=0;

//for(int a=0;a<alldatavals.length;a++)
//{
//      
//       mypos++;
//     
//  }
 count1++;
        }
        }
        
        
}
 
 return htshm;
 }
 public void HtsSdp(dbConn conn,String sdate,String edate,String facil) throws SQLException {
   //new htc for PITC 
 String facilitiestable="subpartnera";
 
         
            
            String month = "";
            String year = "";
          
            String form = "moh731";
            
//=====================================================================================================
            year = "2015";
            month = "5";
            String county = "";
            String header = "";
            
            String subheaders[]={"Tested","Positive","Negative"};
            String sectionheaders[]={"County","Sub-county","Health Facility","Mfl Code","Type Of Support","ART High Volume","HTC High Volume","PMTCT High Volume","Antenatal Clinic","","","Labour & Delivery","","","Under 5 Clinic","","","Postnatal","","","Tuberculosis","","","Outpatient Department","","","Inpatient","","","Voluntary Medical Male Circumcission","","","Voluntary Counselling & Testing (Co-located)","","","Serology","",""};
           
            String reportType = "";
           
            String reportDuration = "";
           
            
                reportDuration = "4";
            
           
              year = edate.substring(0,4);
           
          
      
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
            if (reportDuration.equals("4")) {
                
                monthcopy =new Integer(edate.substring(4,6));
                
                
                //since we dont want data to appear for monthly reports, we set an impossible 
                tbstatduration=" 1=2 "; 
//     month=5;
                if (monthcopy >= 10) {
                    
                  
                    duration = " (" + form + ".yearmonth between " + sdate + " and " + edate+" )";
                    
                } else {
                    
                    duration = " (" + form + ".yearmonth between " + sdate + " and " + edate+" )";
                    
                    
                }
                
            } else {
                
                duration = "";
            }
            
            //======================================================================
//==================================================================================================
            //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
            String subcountywhere = "";
            
            String subcounty = "";
            
        
            
            
           
            
            
            
            String getexistingdata = "";
            
        
            
            if (!facil.equals("")) {
                
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
         
           
            
            
            int rowpos=0;
          
            rowpos++;
        
         
            rowpos++;
            
          
       
            
            int b=0;
          
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
                       
               
               
            colpos++;
            conpos++;
                           
               } 
                //subcounty
                 if(1==1){
                        
              
               
            colpos++;
            conpos++;
                           
               } 
                 //facility name
                   if(1==1){
                      
              
               
            colpos++;
            conpos++;
                           
               } 
                   //mfl
                   if(1==1){
                         
                
               
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
                  
              
               
            colpos++;
            //skip both pmtct support s
            conpos++;
            conpos++;     
               } 
               
            //arthv
                   if(1==1){
                         
                              
            colpos++;           
                           
               }          
                    
            //htchv
                   if(1==1){
                         
                              
            colpos++;           
                           
               }
                   //pmtcthv
                   if(1==1){
                         
                            
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
                               
                colpos++;
                //positive
           
                             
                colpos++;
                
                //negative
             
                              
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
                             
                colpos++;
                //positive
                            
                colpos++;
                
                //negative
                              
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
                
                
          String getstat="select sum(positive) as positive ,sum(negative) as negative from   eid_datim join subpartnera on eid_datim.SubPartnerID=subpartnera.SubPartnerID   WHERE "+tbstatduration+" and  eid_datim.SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ("+facilitiestable+".HTC=1 || subpartnera.PMTCT=1 )   AND "+facilitiestable+".active=1 ";
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
             
               if(!reportDuration.equals("4")){         //if generating a monthly report, dont show data since tb stat data is quarterly                  
               //if(1==1){                          
              
                       }
                else {
             
                     }
              
                colpos++;
                //positive
                    
                if(!reportDuration.equals("4")){        //if generating a monthly report, dont show data since tb stat data is quarterly                      
                //if(1==1){                          
                
                                                }
                else {
               
                     }
                              
                colpos++;
                
                //negative
            
                if(!reportDuration.equals("4")){    //if generating a monthly report, dont show data since tb stat data is quarterly                          
                //if(1==1){                          
              
                        }
                else {
                
                     }
                            
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
              
                          
                colpos++;
                //positive
                              
                colpos++;
                
                //negative
                              
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
                              
                colpos++;
                //positive
                              
                colpos++;
                
                //negative
                             
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
            
               if(!reportDuration.equals("4")){
                  
               }
                else {}
                             
                colpos++;
                //positive
                 
                if(!reportDuration.equals("4")){   }
                else {   }
                            
                colpos++;
                
                //negative
            
                if(!reportDuration.equals("4")){    //if generating a monthly report, dont show data since tb stat data is quarterly                          
                }
                else { }
               colpos++;}                     
                   
                   
//-------------------------------------------------------------------------------------------------------------------                   
 //sexually transmitted insfections    
      //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
       if(1==2){
//sum(HV0208) as HV0208,sum(HV0228) as HV0228,sum(HV0232) as HV0232, sum(DTCB_Test_Out_Tot) as DTCB_Test_Out_Tot,sum(DTCB_Test_In_Tot) as DTCB_Test_In_Tot , sum(DTCC_HIV_Out_Tot) as DTCC_HIV_Out_Tot,  sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                
                //tested
                              
                colpos++;
                //positive
                               
                colpos++;
                
                //negative
                               
                colpos++;
                
               } 
       
       
       
       
       if(1==1){
                //tested
                              
                colpos++;
                //positive
                               
                colpos++;
                
                //negative
                              
                colpos++;
                
               }             
            
              
              //Inpatient
              if(1==1){

                
                //tested
                               
                colpos++;
                
                //positive
                              
                colpos++;
                
                //negative
                              
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
                               
                colpos++;
                //positive
                              
                colpos++;
                //negative
                              
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
                               
                colpos++;
                //positive
                               
                colpos++;
                
                //negative
                               
                colpos++;
               }        
              
             
              
                 //VCT  (Co-located)
              if(1==1){
                               
                colpos++;
                //positive
                               
                colpos++;
                //negative
                             
                colpos++;
               }        
               
             //serology
              if(1==1){
                               
                colpos++;
                //positive
                              
                colpos++;
                //negative
                              
                colpos++;
               } 
           
              //Voluntary counselling and testing (Stand alone)
              //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
              
                               
                colpos++;
                //positive
                              
                colpos++;
                
                //negative
                              
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
                colpos++;
                //positive
                 colpos++;
                
                //negative
                 colpos++;}  
              
                //Home Based  
  //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
              
                //tested
                              
                colpos++;
                //positive
                             
                colpos++;
                //negative
                 colpos++; }  
             
              //Other
              
   //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX COMMENTED FOR NOW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
              if(1==2){
//    sum(DTCC_HIV_In_Tot) as DTCC_HIV_In_Tot, sum(VCTClient_Tested_TOT) as VCTClient_Tested_TOT, sum(VCTClient_HIV_TOT) as VCTClient_HIV_TOT, sum(P511KP) as P511KP, sum(P511KN) as P511KN                 
                int tested=0;
                int positive=0;
                int negative=0;
                //tested
                             
                colpos++;
                //positive
                            
                colpos++;
                
                //negative
              colpos++; 
              }  
              
               rowpos++;    
            }
              
//________________________________________________EID SKIPPED SITES_______________________________________________________            
//________________________________________________EID SKIPPED SITES_______________________________________________________
            
            for(int a=0;a<eidal.size();a++)
            {
          
                if(1==1){
                
               int colpos=0; 
               int conpos=1; 
              
           String getstat=  "select  county,DistrictNom,   SubPartnerNom, CentreSanteId as mflcode ,HTC_Support1 as supporttype , IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, sum(eid_datim.positive) as positive ,sum(eid_datim.negative) as negative,sum(tb_stat_art.positive) as tbpositive ,sum(tb_stat_art.negative) as tbnegative, eid_datim.SubPartnerID as SubPartnerID, IFNULL("+facilitiestable+".HTC,0) as HTC, IFNULL("+facilitiestable+".PMTCT=1,0) as PMTCT  from eid_datim  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on eid_datim.SubPartnerID = subpartnera.SubPartnerID left join tb_stat_art on eid_datim.SubPartnerID=tb_stat_art.SubPartnerID WHERE "+tbstatduration.replace("year", "eid_datim.year").replace("quarter", "eid_datim.quarter")+" and  eid_datim.SubPartnerID='"+eidal.get(a)+"'  AND "+facilitiestable+".active=1   ";
                    System.out.println("~~~"+getstat);
            // String getstat="select sum(positive) as positive ,sum(negative) as negative from   eid_datim join subpartnera on eid_datim.SubPartnerID=subpartnera.SubPartnerID   WHERE "+tbstatduration+" and  eid_datim.SubPartnerID='"+conn.rs.getString("SubPartnerID")+"' and ("+facilitiestable+".HTC=1 || subpartnera.PMTCT=1 )";
        
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
                
               colpos++; conpos++;} 
                //subcounty
                 if(1==1){
               
             colpos++; conpos++;} 
                 
                 //facility name
                   if(1==1){
                
            colpos++;
            conpos++;} 
                   //mfl
                   if(1==1){
                
            colpos++; conpos++; } 
//support type//######################################################################################
                    if(1==1){
                         
 String support="DSD";

    if(conn.rs.getString("supporttype")!=null && !conn.rs.getString("supporttype").equals("")){
  support=conn.rs.getString("supporttype");
                                              }
               
                
            colpos++;
            //skip both pmtct support s
            conpos++;
                           
               } 
              
                    //ART HIGH VOLUME
                if(1==1){
                
            colpos++;
            conpos++;    
               }     
                
                    //HTC HIGH VOLUME
                if(1==1){
                
            colpos++;
            conpos++;     
               } 
                    //PMTCT HIGH VOLUME
                if(1==1){
                         
                
               
            colpos++;
            conpos++;
                           
               }
    //enter blanks in columns from Facility type  up to the tb column
                    
                if(1==1){
                 for(int c=0;c<6;c++){     
                               
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
              
               if(!reportDuration.equals("4")){                          
               
                                                }
                else {
              
                     }
                         
                colpos++;
                //positive
                     
                if(!reportDuration.equals("4")){                          
               
                                                }
                else {
               
                     }
                            
                colpos++;
                
                //negative
             
                if(!reportDuration.equals("4")){                          
          
                                                }
                else {
                
                     }
                           
                colpos++; 
                
                }//end of eid if query
               
               //post blanks on postnatal columns
               
                    if(1==1){
                 for(int c=0;c<3;c++){     
                
                             
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
               
               if(!reportDuration.equals("4")){                          
                
                                                }
                else {
               
                     }
                              
                colpos++;
                //positive
                  
                if(!reportDuration.equals("4")){                          
               
                                                }
                else {
                
                     }
                              
                colpos++;
                //negative
                
                if(!reportDuration.equals("4")){                          
               
                                                }
                else {
              
                     }
                             
                colpos++; 
               
                }//end of tbstat if query
                 
               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=23;c<sectionheaders.length;c++){     
              
                       
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
         
           String getstat=    "select  county,DistrictNom,  SubPartnerNom, CentreSanteId as mflcode ,supporttype,IFNULL(ART_highvolume,0) as ART_highvolume,  IFNULL(HTC_highvolume,0) as HTC_highvolume,  IFNULL(PMTCT_highvolume,0) as PMTCT_highvolume, sum(positive) as positive ,sum(negative) as negative, tb_stat_art.SubPartnerID as SubPartnerID , IFNULL("+facilitiestable+".HTC,0) as HTC, IFNULL("+facilitiestable+".PMTCT=1,0) as PMTCT from tb_stat_art  join ( subpartnera join (district join county on county.CountyID=district.CountyID ) on district.DistrictID = subpartnera.DistrictID )  on tb_stat_art.SubPartnerID = subpartnera.SubPartnerID  WHERE "+tbstatduration+" and  tb_stat_art.SubPartnerID='"+tbstat.get(a)+"'   AND "+facilitiestable+".active=1  ";
           //and ("+facilitiestable+".HTC=1 || subpartnera.PMTCT=1 )
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
                
            colpos++;
            conpos++;     
               } 
                //subcounty
                 if(1==1){
               
            colpos++; conpos++;} 
                 
                 //facility name
                   if(1==1){
              
              colpos++; conpos++;} 
                   //mfl
       if(1==1){ 
           colpos++; conpos++; } 
//support type//######################################################################################
                    if(1==1){
 String support="NA";
  support=conn.rs.getString("supporttype");
 
            colpos++;
            //skip both pmtct support s
            conpos++;
           } 
            
                    
                    //ART HIGH VOLUME
                if(1==1){
                
            colpos++; conpos++;
                           
               }     
               
                    //HTC HIGH VOLUME
                if(1==1){
                         
              
              colpos++;conpos++;
             } 
               
                    //PMTCT HIGH VOLUME
                if(1==1){
                         
              
               
            colpos++;
            conpos++;
                           
               } 
                    
    //enter blanks in columns from Facility type  up to the tb column
                    
                      if(1==1){
                 for(int c=8;c<20;c++){     
                              
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
               
               if(!reportDuration.equals("4")){ }
                else {  }
                              
                colpos++;
                //positive
                     
                if(!reportDuration.equals("4")){   }
                else { }
                              
                colpos++;
                
                //negative
               
                if(!reportDuration.equals("4")){    }
                else { }
               colpos++; }//end of tbstat if query

               //finish posting blanks to the remaining columns
               
                    if(1==1){
                 for(int c=23;c<sectionheaders.length;c++){     
                
                            
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
 
 rowpos++;
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  

   
  }
  else if(z==1){
    //sub-county  
 
  }
  else if(z==2){
   //facility
  
  }
  else if(z==3){
   //mfl
  
  }
   
  else if(z==4){
  //dsdta
  
   
        }
   
else if(z==5){
  //dsdta
  
   
        }   
    else if(z==6){
  //dsdta
  
   
        }
    else if(z==7){
  //dsdta
  
   
        }
  
		 else if(z==blankrows-1){
  //dsdta
   
        }
  else {
                     
   
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

            
            
            
            
            
            
 }
            
  
public HashMap Ipd(dbConn conn,String sdate,String edate,String facil) throws SQLException {
   //new htc for PITC 
 
   
    HashMap<String, String> ipdhm = new HashMap<String, String>();
   
    
            
            
             //new htc for PITC 
                      isIPD=true;
                      isOPD=false;
                      isVCT=false;
                     
                     
                     String pitc_ipd_header1[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + ANC + Serology)","Total positive","Positive","","","","","","","","","","","","","","","","","","","","","","","","Negative","","","","","","","","","","","","","","","","","","","","","","","","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header2[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + ANC + Serology)","Total positive","Unknown age","","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Unknown age","Unknown age","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","F","M","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                     String pitc_ipd_header3[]={"County","Sub-county","Facility","Mfl-Code","Type of Support","Numerator (HTC + ANC + Serology)","Total positive","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","F","M","<1","<1","1-4Y","1-4Y","5-9Y","5-9Y","10-14Y","10-14Y","15-19Y","15-19Y","20-24Y","20-24Y","25-29Y","25-29Y","30-34Y","30-34Y","35-39Y","35-39Y","40-49Y","40-49Y","50+","50+","Total IPD Tested(IPD + L&D)","ART High Volume","HTC High Volume","PMTCT High Volume","HTC","PMTCT"};
                    
                     ArrayList allFacilities = new ArrayList();
                     allFacilities.clear();
                 int year,month,prevYear,maxYearMonth,mflcode;
String reportDuration,duration,semi_annual,quarter;
String facilityName,countyName,districtName,facilityIds,facilityId;

   year=month=prevYear=maxYearMonth=mflcode=0;
 reportDuration=duration=semi_annual=quarter="";
 facilityName=countyName=districtName=facilityIds=facilityId="";

 
 year = new Integer(edate.substring(0,4));
  
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
   
       if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }
          
    
            
        //reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        
        
        
          
        
       if(reportDuration.equals("4")){
      
          month =new Integer(edate.substring(4,6));
          
           duration1=" ( moh731.yearmonth between "+sdate+" and "+edate+" )";
          

      }
      else{
     duration1="";     
      }
        
     
            
            
   String county="";
   String  district="";
    String facilityname="";

  
    
    

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
            +",sum(IFNULL(HV0202,0)) as ld_tes , sum(IFNULL(HV0207,0)) as ld_pos "
            + " ,county.burden_cartegory as burdencategory,"+facilitiestable+".constituency as constituency,"+facilitiestable+".ward as ward, "
           
            + " substring(moh731.yearmonth,1,4) as year " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
"or substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03')  then '1. Oct-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
"or substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '2. Apr-Sep' end  as semiannual " +
"            " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
")  then '1. Oct-Dec'  " +
"           when (  " +
" " +
"substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03' " +
"           )  then '2. Jan-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
")  then '3. Apr-Jun'  " +
"           when (  " +
"substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '4. Jul-Sep' end  as quarter " +
" " +
",case when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Oct' then '10. Oct' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Nov' then '11. Nov' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Dec' then '12. dec' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jan' then '01. Jan' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Feb' then '02. Feb' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Mar' then '03. Mar' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Apr' then '04. Apr' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'May' then '05. May' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jun' then '06. Jun' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jul' then '07. Jul' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Aug' then '08. Aug' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Sep' then '09. Sep' " +
" end  as month " +
" " +
",  moh731.yearmonth " +
" ,IFNULL("+facilitiestable+".Owner,\"\") as ownedby " +
" ,IFNULL("+facilitiestable+".Type,\"\") as facilitytype " +
" ,IFNULL("+facilitiestable+".ART_highvolume,\"0\") as art_hv " +
" ,IFNULL("+facilitiestable+".HTC_highvolume,\"0\") as htc_hv " +
" ,IFNULL("+facilitiestable+".PMTCT_highvolume,\"0\") as pmtct_hv " +
" ,IFNULL("+facilitiestable+".all_highvolume,\"0\") as activity_hv " +
" ,IFNULL("+facilitiestable+".latitude,\"\") as latitude " +
" ,IFNULL("+facilitiestable+".longitude,\"\") as longitude " +
" ,IFNULL("+facilitiestable+".Male_clinics,\"0\") as maleclinic " +
" ,IFNULL("+facilitiestable+".Adolescent_clinics,\"0\") as adoleclinic " +
" ,IFNULL("+facilitiestable+".Viremia_clinics,\"0\") as viremiaclinic " +
" ,IFNULL("+facilitiestable+".EMR_Sites,\"0\") as emrsite " +
" ,IFNULL("+facilitiestable+".Link_desks,\"0\") as linkdesk " +
" ,'0'as islocked "
           
            + " FROM moh731 JOIN "+facilitiestable+" "
            + "ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && ("+facilitiestable+".HTC=1 || PMTCT=1  )  AND "+facilitiestable+".active=1  "
           + " GROUP BY moh731.SubPartnerID,moh731.yearmonth order by county.County ,moh731.yearmonth " ;
 
     System.out.println("2017q1IPD : "+get731data);
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
 
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++)
   {
    
  
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
  

   String ym = conn.rs.getString("yearmonth");
 
  ipdhm.put("cty"+"_"+mflcode+ym, county);
  ipdhm.put("sty"+"_"+mflcode+ym, district);
  ipdhm.put("fac"+"_"+mflcode+ym, facilityname);
  ipdhm.put("mfl"+"_"+mflcode+ym, ""+mflcode);
  ipdhm.put("st"+"_"+mflcode+ym, dsdta);
  ipdhm.put("gtt"+"_"+mflcode+ym,""+conn.rs.getInt("NUM"));
  ipdhm.put("gtp"+"_"+mflcode+ym,""+conn.rs.getInt("grandtotalpositive"));
  ipdhm.put("ukpf"+"_"+mflcode+ym,"0");
  ipdhm.put("ukpm"+"_"+mflcode+ym,"0");
  ipdhm.put("pf1"+"_"+mflcode+ym,""+ChildFemaleHIV1);
  ipdhm.put("pm1"+"_"+mflcode+ym,""+ChildMaleHIV1);
  ipdhm.put("pf4"+"_"+mflcode+ym,""+ChildFemaleHIV4);
  ipdhm.put("pm4"+"_"+mflcode+ym,""+ChildMaleHIV4);
  ipdhm.put("pf9"+"_"+mflcode+ym,""+ChildFemaleHIV9);
  ipdhm.put("pm9"+"_"+mflcode+ym,""+ChildMaleHIV9);
  ipdhm.put("pf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14+pmtct_p_14));
  ipdhm.put("pm14"+"_"+mflcode+ym,""+ChildMaleHIV14);
  ipdhm.put("pf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19+pmtct_p_19));
  ipdhm.put("pm19"+"_"+mflcode+ym,""+AdultMaleHIV19);
  ipdhm.put("pf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24+pmtct_p_24));
  ipdhm.put("pm24"+"_"+mflcode+ym,""+AdultMaleHIV24);
  ipdhm.put("pf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29+pmtct_p_29));
  ipdhm.put("pm29"+"_"+mflcode+ym,""+AdultMaleHIV29);
  ipdhm.put("pf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34+pmtct_p_34));
  ipdhm.put("pm34"+"_"+mflcode+ym,""+AdultMaleHIV34);
  ipdhm.put("pf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39+pmtct_p_39));
  ipdhm.put("pm39"+"_"+mflcode+ym,""+AdultMaleHIV39);
  ipdhm.put("pf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49+pmtct_p_49));
  ipdhm.put("pm49"+"_"+mflcode+ym,""+AdultMaleHIV49);
  ipdhm.put("pf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50+pmtct_p_50));
  ipdhm.put("pm50"+"_"+mflcode+ym,""+AdultMaleHIV50);
  //_____negative
  ipdhm.put("uknf"+"_"+mflcode+ym,"0");
  ipdhm.put("uknm"+"_"+mflcode+ym,"0");
  ipdhm.put("nf1"+"_"+mflcode+ym,""+ChildFemaleHIV1Neg);
  ipdhm.put("nm1"+"_"+mflcode+ym,""+ChildMaleHIV1Neg);
  ipdhm.put("nf4"+"_"+mflcode+ym,""+ChildFemaleHIV4Neg);
  ipdhm.put("nm4"+"_"+mflcode+ym,""+ChildMaleHIV4Neg);
  ipdhm.put("nf9"+"_"+mflcode+ym,""+ChildFemaleHIV9Neg);
  ipdhm.put("nm9"+"_"+mflcode+ym,""+ChildMaleHIV9Neg);
  ipdhm.put("nf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14Neg+pmtct_n_14));
  ipdhm.put("nm14"+"_"+mflcode+ym,""+ChildMaleHIV14Neg);
  ipdhm.put("nf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19Neg+pmtct_n_19));
  ipdhm.put("nm19"+"_"+mflcode+ym,""+AdultMaleHIV19Neg);
  ipdhm.put("nf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24Neg+pmtct_n_24));
  ipdhm.put("nm24"+"_"+mflcode+ym,""+AdultMaleHIV24Neg);
  ipdhm.put("nf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29Neg+pmtct_n_29));
  ipdhm.put("nm29"+"_"+mflcode+ym,""+AdultMaleHIV29Neg);
  ipdhm.put("nf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34Neg+pmtct_n_34));
  ipdhm.put("nm34"+"_"+mflcode+ym,""+AdultMaleHIV34Neg);
  ipdhm.put("nf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39Neg+pmtct_n_39));
  ipdhm.put("nm39"+"_"+mflcode+ym,""+AdultMaleHIV39Neg);
  ipdhm.put("nf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49Neg+pmtct_n_49));
  ipdhm.put("nm49"+"_"+mflcode+ym,""+AdultMaleHIV49Neg);
  ipdhm.put("nf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50Neg+pmtct_n_50));
  ipdhm.put("nm50"+"_"+mflcode+ym,""+AdultMaleHIV50Neg);
  ipdhm.put("tt"+"_"+mflcode+ym,""+((tested_new711)+(pmtct_t)));
  
 String burdencategory = conn.rs.getString("burdencategory");
 String constituency = conn.rs.getString("constituency");
 String ward = conn.rs.getString("ward");
 String yr = conn.rs.getString("year");
 String semiannual = conn.rs.getString("semiannual");
 String qtr = conn.rs.getString("quarter");
 String mn = conn.rs.getString("month");

 String ownedby = conn.rs.getString("ownedby");
 String facilitytype = conn.rs.getString("facilitytype");
 String art_hv = conn.rs.getString("art_hv");
 String htc_hv = conn.rs.getString("htc_hv");
 String pmtct_hv = conn.rs.getString("pmtct_hv");
 String activity_hv = conn.rs.getString("activity_hv");
 String latitude = conn.rs.getString("latitude");
 String longitude = conn.rs.getString("longitude");
 String maleclinic = conn.rs.getString("maleclinic");
 String adoleclinic = conn.rs.getString("adoleclinic");
 String viremiaclinic = conn.rs.getString("viremiaclinic");
 String emrsite = conn.rs.getString("emrsite");
 String linkdesk = conn.rs.getString("linkdesk");
 String islocked = conn.rs.getString("islocked");
  
  ipdhm.put("burdencategory"+"_"+mflcode+ym, burdencategory);
  ipdhm.put("constituency"+"_"+mflcode+ym, constituency);
  ipdhm.put("ward"+"_"+mflcode+ym, ward);
  ipdhm.put("year"+"_"+mflcode+ym, yr);
  ipdhm.put("semiannual"+"_"+mflcode+ym, semiannual);
  ipdhm.put("quarter"+"_"+mflcode+ym, qtr);
  ipdhm.put("month"+"_"+mflcode+ym, mn);
  ipdhm.put("yearmonth"+"_"+mflcode+ym, ym);
  ipdhm.put("ownedby"+"_"+mflcode+ym, ownedby);
  ipdhm.put("facilitytype"+"_"+mflcode+ym, facilitytype);
  ipdhm.put("art_hv"+"_"+mflcode+ym, art_hv);
  ipdhm.put("htc_hv"+"_"+mflcode+ym, htc_hv);
  ipdhm.put("pmtct_hv"+"_"+mflcode+ym, pmtct_hv);
  ipdhm.put("activity_hv"+"_"+mflcode+ym, activity_hv);
  ipdhm.put("latitude"+"_"+mflcode+ym, latitude);
  ipdhm.put("longitude"+"_"+mflcode+ym, longitude);
  ipdhm.put("maleclinic"+"_"+mflcode+ym, maleclinic);
  ipdhm.put("adoleclinic"+"_"+mflcode+ym, adoleclinic);
  ipdhm.put("viremiaclinic"+"_"+mflcode+ym, viremiaclinic);
  ipdhm.put("emrsite"+"_"+mflcode+ym, emrsite);
  ipdhm.put("linkdesk"+"_"+mflcode+ym, linkdesk);
  ipdhm.put("islocked"+"_"+mflcode+ym, islocked);
  
//String burdencategory
//String constituency
//String ward
//String year
//String semiannual
//String quarter
//String month
//String yearmonth
//String ownedby
//String facilitytype
//String art_hv
//String htc_hv
//String pmtct_hv
//String activity_hv
//String latitude
//String longitude
//String maleclinic
//String adoleclinic
//String viremiaclinic
//String emrsite
//String linkdesk
//String islocked


 

    
         int mypos=0;
     
         
  for(int a=0;a<alldatavals.length;a++){
      
     mypos++;
       if(a==4||a<3){
           //non numeric characters
       
       }
       else {
        
       
       }
      
      
  
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop
    
    
    
    
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	                    
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  
 
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   
  }
  else if(z==1){
    //sub-county  
  
  }
  else if(z==2){
   //facility
   
  }
  else if(z==3){
   //mfl
  
  }
   
  else if(z==4){
  //dsdta
   
   
        }
   else if(z==pitc_ipd_header1.length-5){
  //dsdta
  
   }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
  
        }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   
        }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
  
        }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
  
        }
  
  
		/** else if(z==blankrows-1){
  //dsdta
   HSSFCell celldsd=rwx.createCell(blankrows-1); 
   celldsd.setCellValue("NO DATA");
   celldsd.setCellStyle(stborder);
   
        }  */
                 
  else {
                     
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
                
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                               
              
                 
            
  return ipdhm;          
            
    
}

public HashMap Opd(dbConn conn,String sdate,String edate,String facil) throws SQLException {
   //new htc for PITC 
   
   HashMap<String, String> opdhm = new HashMap<String, String>(); 
   
    
    
                  
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

year = new Integer(edate.substring(0,4));
  
  

  
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
         if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }
         
          
       // reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        
       
        
         
        
       if(reportDuration.equals("4")){
       
          month =new Integer(edate.substring(4,6));
         duration1=" ( moh731.yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }
        
      
            
            
   String county="";
   String  district="";
    String facilityname="";
     


  
   
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
             + " ,county.burden_cartegory as burdencategory,"+facilitiestable+".constituency as constituency,"+facilitiestable+".ward as ward, "
           
            + " substring(moh731.yearmonth,1,4) as year " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
"or substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03')  then '1. Oct-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
"or substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '2. Apr-Sep' end  as semiannual " +
"            " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
")  then '1. Oct-Dec'  " +
"           when (  " +
" " +
"substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03' " +
"           )  then '2. Jan-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
")  then '3. Apr-Jun'  " +
"           when (  " +
"substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '4. Jul-Sep' end  as quarter " +
" " +
",case when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Oct' then '10. Oct' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Nov' then '11. Nov' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Dec' then '12. dec' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jan' then '01. Jan' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Feb' then '02. Feb' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Mar' then '03. Mar' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Apr' then '04. Apr' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'May' then '05. May' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jun' then '06. Jun' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jul' then '07. Jul' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Aug' then '08. Aug' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Sep' then '09. Sep' " +
" end  as month " +
" " +
",  moh731.yearmonth " +
" ,IFNULL("+facilitiestable+".Owner,\"\") as ownedby " +
" ,IFNULL("+facilitiestable+".Type,\"\") as facilitytype " +
" ,IFNULL("+facilitiestable+".ART_highvolume,\"0\") as art_hv " +
" ,IFNULL("+facilitiestable+".HTC_highvolume,\"0\") as htc_hv " +
" ,IFNULL("+facilitiestable+".PMTCT_highvolume,\"0\") as pmtct_hv " +
" ,IFNULL("+facilitiestable+".all_highvolume,\"0\") as activity_hv " +
" ,IFNULL("+facilitiestable+".latitude,\"\") as latitude " +
" ,IFNULL("+facilitiestable+".longitude,\"\") as longitude " +
" ,IFNULL("+facilitiestable+".Male_clinics,\"0\") as maleclinic " +
" ,IFNULL("+facilitiestable+".Adolescent_clinics,\"0\") as adoleclinic " +
" ,IFNULL("+facilitiestable+".Viremia_clinics,\"0\") as viremiaclinic " +
" ,IFNULL("+facilitiestable+".EMR_Sites,\"0\") as emrsite " +
" ,IFNULL("+facilitiestable+".Link_desks,\"0\") as linkdesk " +
" ,'0'as islocked "
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
 
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++)
   {
    
 
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
//System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
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
    
    else if(AdultMaleHIV49>0){AdultMaleHIV49-=1;AdultMaleHIV49Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV39>0){AdultMaleHIV39-=1; AdultMaleHIV39Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV34>0){AdultMaleHIV34-=1; AdultMaleHIV34Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV29>0){AdultMaleHIV29-=1;AdultMaleHIV29Neg+=1; balancegeneralglobal++;}
    else if(AdultMaleHIV24>0) {AdultMaleHIV24-=1;AdultMaleHIV24Neg+=1;balancegeneralglobal++;}    
    else if(AdultMaleHIV19>0) {AdultMaleHIV19-=1; AdultMaleHIV19Neg+=1;balancegeneralglobal++;}    
    else if(ChildMaleHIV14>0) {ChildMaleHIV14-=1;ChildMaleHIV14Neg+=1;balancegeneralglobal++;}    
    else if(ChildMaleHIV9>0) {ChildMaleHIV9-=1;ChildMaleHIV9Neg+=1; balancegeneralglobal++;}
    
    
    else { break;}
  
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


//============================================================================
        


 double pmtct_n_14=(float)Math.round(pmtct_t_14)-(pmtct_p_14);
 double pmtct_n_19= (float)Math.round(pmtct_t_19)-(pmtct_p_19);
 double pmtct_n_24= (float)Math.round(pmtct_t_24)-(pmtct_p_24);
 double pmtct_n_29= (float)Math.round(pmtct_t_29)-(pmtct_p_29);
 double pmtct_n_34= (float)Math.round(pmtct_t_34)-(pmtct_p_34);
 double pmtct_n_39= (float)Math.round(pmtct_t_39)-(pmtct_p_39);
 double pmtct_n_49=(float)Math.round(pmtct_t_49)-(pmtct_p_49);
 double pmtct_n_50= (float)Math.round(pmtct_t_50)-(pmtct_p_50);

     
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

//opd
 double opdp_u_f=0;
 double opdp_u_m=0;
 double opdp_1_f=ChildFemaleHIV1;
 double opdp_1_m=ChildMaleHIV1;
 double opdp_4_f=ChildFemaleHIV4;
 double opdp_4_m=ChildMaleHIV4;
 double opdp_9_f=ChildFemaleHIV9;
 double opdp_9_m=ChildMaleHIV9;
 double opdp_14_f=(ChildFemaleHIV14+pmtct_p_14);
 double opdp_14_m=ChildMaleHIV14;
 double opdp_19_f=(AdultFemaleHIV19+(pmtct_p_19));
 double opdp_19_m=AdultMaleHIV19;
 double opdp_24_f=(AdultFemaleHIV24+(pmtct_p_24));
 double opdp_24_m=AdultMaleHIV24;
 double opdp_29_f=(AdultFemaleHIV29+(pmtct_p_29));
 double opdp_29_m=AdultMaleHIV29;
 double opdp_34_f=(AdultFemaleHIV34+(pmtct_p_34));
 double opdp_34_m=AdultMaleHIV34;
 double opdp_39_f=(AdultFemaleHIV39+(pmtct_p_39));
 double opdp_39_m=AdultMaleHIV39;
 double opdp_49_f=(AdultFemaleHIV49+(pmtct_p_49));
 double opdp_49_m=AdultMaleHIV49;
 double opdp_50_f=(AdultFemaleHIV50+(pmtct_p_50));
 double opdp_50_m=AdultMaleHIV50;
 double opdn_u_f=0;
 double opdn_u_m=0;
 double opdn_1_f=ChildFemaleHIV1Neg;
 double opdn_1_m=ChildMaleHIV1Neg;
 double opdn_4_f=ChildFemaleHIV4Neg;
 double opdn_4_m=ChildMaleHIV4Neg;
 double opdn_9_f=ChildFemaleHIV9Neg;
 double opdn_9_m=ChildMaleHIV9Neg;
 double opdn_14_f=(ChildFemaleHIV14Neg+(pmtct_n_14));
 double opdn_14_m=ChildMaleHIV14Neg;
 double opdn_19_f=(AdultFemaleHIV19Neg+(pmtct_n_19));
 double opdn_19_m=AdultMaleHIV19Neg;
 double opdn_24_f=(AdultFemaleHIV24Neg+(pmtct_n_24));
 double opdn_24_m=AdultMaleHIV24Neg;
 double opdn_29_f=(AdultFemaleHIV29Neg+(pmtct_n_29));
 double opdn_29_m=AdultMaleHIV29Neg;
 double opdn_34_f=(AdultFemaleHIV34Neg+(pmtct_n_34));
 double opdn_34_m=AdultMaleHIV34Neg;
 double opdn_39_f=(AdultFemaleHIV39Neg+(pmtct_n_39));
 double opdn_39_m=AdultMaleHIV39Neg;
 double opdn_49_f=(AdultFemaleHIV49Neg+(pmtct_n_49));
 double opdn_49_m=AdultMaleHIV49Neg;
 double opdn_50_f=(AdultFemaleHIV50Neg+(pmtct_n_50));
 double opdn_50_m=AdultMaleHIV50Neg;
 double opdsubtotal=(((tested_new711))+(pmtct_t));

double p_u_f=0;
 double p_u_m=0;
 double p_1_f=0;
 double p_1_m=0;
 double p_9_f=0;
 double p_9_m=0;
 double p_14_f=0;
 double p_14_m=0;
 double p_19_f=0;
 double p_19_m=0;
 double p_24_f=0;
 double p_24_m=0;
 double p_29_f=0;
 double p_29_m=0;
 double p_34_f=0;
 double p_34_m=0;
 double p_39_f=0;
 double p_39_m=0;
 double p_49_f=0;
 double p_49_m=0;
 double p_50_f=0;
 double p_50_m=0;
 double n_u_f=0;
 double n_u_m=0;
 double n_1_f=0;
 double n_1_m=0;
 double n_9_f=0;
 double n_9_m=0;
 double n_14_f=0;
 double n_14_m=0;
 double n_19_f=0;
 double n_19_m=0;
 double n_24_f=0;
 double n_24_m=0;
 double n_29_f=0;
 double n_29_m=0;
 double n_34_f=0;
 double n_34_m=0;
 double n_39_f=0;
 double n_39_m=0;
 double n_49_f=0;
 double n_49_m=0;
 double n_50_f=0;
 double n_50_m=0;
 double subtotal=0;

if(1==2){
//__________________________________________________________________________________________________________________________________

String qry1 = "call scrp_totalotheropd_2018Q3(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
    System.out.println(""+qry1);
                
        conn.rs3 = conn.st3.executeQuery(qry1);


        while (conn.rs3.next()) {
            
p_u_f =opdp_u_f- conn.rs3.getDouble("p_u_f");
p_u_m =opdp_u_m- conn.rs3.getDouble("p_u_m");
p_1_f =opdp_1_f- conn.rs3.getDouble("p_1_f");
p_1_m =opdp_1_m- conn.rs3.getDouble("p_1_m");
p_9_f =opdp_9_f- conn.rs3.getDouble("p_9_f");
p_9_m =opdp_9_m- conn.rs3.getDouble("p_9_m");
p_14_f =opdp_14_f- conn.rs3.getDouble("p_14_f");
p_14_m =opdp_14_m- conn.rs3.getDouble("p_14_m");
p_19_f =opdp_19_f- conn.rs3.getDouble("p_19_f");
p_19_m =opdp_19_m- conn.rs3.getDouble("p_19_m");
p_24_f =opdp_24_f- conn.rs3.getDouble("p_24_f");
p_24_m =opdp_24_m- conn.rs3.getDouble("p_24_m");
p_29_f =opdp_29_f- conn.rs3.getDouble("p_29_f");
p_29_m =opdp_29_m- conn.rs3.getDouble("p_29_m");
p_34_f =opdp_34_f- conn.rs3.getDouble("p_34_f");
p_34_m =opdp_34_m- conn.rs3.getDouble("p_34_m");
p_39_f =opdp_39_f- conn.rs3.getDouble("p_39_f");
p_39_m =opdp_39_m- conn.rs3.getDouble("p_39_m");
p_49_f =opdp_49_f- conn.rs3.getDouble("p_49_f");
p_49_m =opdp_49_m- conn.rs3.getDouble("p_49_m");
p_50_f =opdp_50_f- conn.rs3.getDouble("p_50_f");
p_50_m =opdp_50_m- conn.rs3.getDouble("p_50_m");
n_u_f =opdn_u_f- conn.rs3.getDouble("n_u_f");
n_u_m =opdn_u_m- conn.rs3.getDouble("n_u_m");
n_1_f =opdn_1_f- conn.rs3.getDouble("n_1_f");
n_1_m =opdn_1_m- conn.rs3.getDouble("n_1_m");
n_9_f =opdn_9_f- conn.rs3.getDouble("n_9_f");
n_9_m =opdn_9_m- conn.rs3.getDouble("n_9_m");
n_14_f =opdn_14_f- conn.rs3.getDouble("n_14_f");
n_14_m =opdn_14_m- conn.rs3.getDouble("n_14_m");
n_19_f =opdn_19_f- conn.rs3.getDouble("n_19_f");
n_19_m =opdn_19_m- conn.rs3.getDouble("n_19_m");
n_24_f =opdn_24_f- conn.rs3.getDouble("n_24_f");
n_24_m =opdn_24_m- conn.rs3.getDouble("n_24_m");
n_29_f =opdn_29_f- conn.rs3.getDouble("n_29_f");
n_29_m =opdn_29_m- conn.rs3.getDouble("n_29_m");
n_34_f =opdn_34_f- conn.rs3.getDouble("n_34_f");
n_34_m =opdn_34_m- conn.rs3.getDouble("n_34_m");
n_39_f =opdn_39_f- conn.rs3.getDouble("n_39_f");
n_39_m =opdn_39_m- conn.rs3.getDouble("n_39_m");
n_49_f =opdn_49_f- conn.rs3.getDouble("n_49_f");
n_49_m =opdn_49_m- conn.rs3.getDouble("n_49_m");
n_50_f =opdn_50_f- conn.rs3.getDouble("n_50_f");
n_50_m =opdn_50_m- conn.rs3.getDouble("n_50_m");
subtotal =opdsubtotal- conn.rs3.getDouble("subtotal");
            
                               }
    }

        //balancing process
        
//__________________________________________________________________________________________________________________________________



 String ym = conn.rs.getString("yearmonth");

  opdhm.put("cty"+"_"+mflcode+ym, county);
  opdhm.put("sty"+"_"+mflcode+ym, district);
  opdhm.put("fac"+"_"+mflcode+ym, facilityname);
  opdhm.put("mfl"+"_"+mflcode+ym, ""+mflcode);
  opdhm.put("st"+"_"+mflcode+ym, dsdta);
  opdhm.put("ukpf"+"_"+mflcode+ym,"0");
  opdhm.put("ukpm"+"_"+mflcode+ym,"0");
  opdhm.put("pf1"+"_"+mflcode+ym,""+(ChildFemaleHIV1 ));//serology added to female
  opdhm.put("pm1"+"_"+mflcode+ym,""+ChildMaleHIV1);
  opdhm.put("pf4"+"_"+mflcode+ym,""+ChildFemaleHIV4);
  opdhm.put("pm4"+"_"+mflcode+ym,""+ChildMaleHIV4);
  opdhm.put("pf9"+"_"+mflcode+ym,""+ChildFemaleHIV9);
  opdhm.put("pm9"+"_"+mflcode+ym,""+ChildMaleHIV9);
  opdhm.put("pf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14+pmtct_p_14));
  opdhm.put("pm14"+"_"+mflcode+ym,""+ChildMaleHIV14);
  opdhm.put("pf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19+pmtct_p_19));
  opdhm.put("pm19"+"_"+mflcode+ym,""+AdultMaleHIV19);
  opdhm.put("pf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24+pmtct_p_24));
  opdhm.put("pm24"+"_"+mflcode+ym,""+AdultMaleHIV24);
  opdhm.put("pf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29+pmtct_p_29));
  opdhm.put("pm29"+"_"+mflcode+ym,""+AdultMaleHIV29);
  opdhm.put("pf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34+pmtct_p_34));
  opdhm.put("pm34"+"_"+mflcode+ym,""+AdultMaleHIV34);
  opdhm.put("pf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39+pmtct_p_39));
  opdhm.put("pm39"+"_"+mflcode+ym,""+AdultMaleHIV39);
  opdhm.put("pf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49+pmtct_p_49));
  opdhm.put("pm49"+"_"+mflcode+ym,""+AdultMaleHIV49);
  opdhm.put("pf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50+pmtct_p_50));
  opdhm.put("pm50"+"_"+mflcode+ym,""+AdultMaleHIV50);
  //_____negative
  opdhm.put("uknf"+"_"+mflcode+ym,"0");
  opdhm.put("uknm"+"_"+mflcode+ym,"0");
  opdhm.put("nf1"+"_"+mflcode+ym,""+ChildFemaleHIV1Neg);
  opdhm.put("nm1"+"_"+mflcode+ym,""+(ChildMaleHIV1Neg+ conn.rs.getInt("serology")));
  opdhm.put("nf4"+"_"+mflcode+ym,""+ChildFemaleHIV4Neg);
  opdhm.put("nm4"+"_"+mflcode+ym,""+ChildMaleHIV4Neg);
  opdhm.put("nf9"+"_"+mflcode+ym,""+ChildFemaleHIV9Neg);
  opdhm.put("nm9"+"_"+mflcode+ym,""+ChildMaleHIV9Neg);
  opdhm.put("nf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14Neg+pmtct_n_14));
  opdhm.put("nm14"+"_"+mflcode+ym,""+ChildMaleHIV14Neg);
  opdhm.put("nf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19Neg+pmtct_n_19));
  opdhm.put("nm19"+"_"+mflcode+ym,""+AdultMaleHIV19Neg);
  opdhm.put("nf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24Neg+pmtct_n_24));
  opdhm.put("nm24"+"_"+mflcode+ym,""+AdultMaleHIV24Neg);
  opdhm.put("nf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29Neg+pmtct_n_29));
  opdhm.put("nm29"+"_"+mflcode+ym,""+AdultMaleHIV29Neg);
  opdhm.put("nf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34Neg+pmtct_n_34));
  opdhm.put("nm34"+"_"+mflcode+ym,""+AdultMaleHIV34Neg);
  opdhm.put("nf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39Neg+pmtct_n_39));
  opdhm.put("nm39"+"_"+mflcode+ym,""+AdultMaleHIV39Neg);
  opdhm.put("nf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49Neg+pmtct_n_49));
  opdhm.put("nm49"+"_"+mflcode+ym,""+AdultMaleHIV49Neg);
  opdhm.put("nf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50Neg+pmtct_n_50));
  opdhm.put("nm50"+"_"+mflcode+ym,""+AdultMaleHIV50Neg);
  opdhm.put("tt"+"_"+mflcode+ym,""+((tested_new711)+(pmtct_t)+(conn.rs.getInt("serology"))));

String burdencategory = conn.rs.getString("burdencategory");
 String constituency = conn.rs.getString("constituency");
 String ward = conn.rs.getString("ward");
 String yr = conn.rs.getString("year");
 String semiannual = conn.rs.getString("semiannual");
 String qtr = conn.rs.getString("quarter");
 String mn = conn.rs.getString("month");

 String ownedby = conn.rs.getString("ownedby");
 String facilitytype = conn.rs.getString("facilitytype");
 String art_hv = conn.rs.getString("art_hv");
 String htc_hv = conn.rs.getString("htc_hv");
 String pmtct_hv = conn.rs.getString("pmtct_hv");
 String activity_hv = conn.rs.getString("activity_hv");
 String latitude = conn.rs.getString("latitude");
 String longitude = conn.rs.getString("longitude");
 String maleclinic = conn.rs.getString("maleclinic");
 String adoleclinic = conn.rs.getString("adoleclinic");
 String viremiaclinic = conn.rs.getString("viremiaclinic");
 String emrsite = conn.rs.getString("emrsite");
 String linkdesk = conn.rs.getString("linkdesk");
 String islocked = conn.rs.getString("islocked");
  
  opdhm.put("burdencategory"+"_"+mflcode+ym, burdencategory);
  opdhm.put("constituency"+"_"+mflcode+ym, constituency);
  opdhm.put("ward"+"_"+mflcode+ym, ward);
  opdhm.put("year"+"_"+mflcode+ym, yr);
  opdhm.put("semiannual"+"_"+mflcode+ym, semiannual);
  opdhm.put("quarter"+"_"+mflcode+ym, qtr);
  opdhm.put("month"+"_"+mflcode+ym, mn);
  opdhm.put("yearmonth"+"_"+mflcode+ym, ym);
  opdhm.put("ownedby"+"_"+mflcode+ym, ownedby);
  opdhm.put("facilitytype"+"_"+mflcode+ym, facilitytype);
  opdhm.put("art_hv"+"_"+mflcode+ym, art_hv);
  opdhm.put("htc_hv"+"_"+mflcode+ym, htc_hv);
  opdhm.put("pmtct_hv"+"_"+mflcode+ym, pmtct_hv);
  opdhm.put("activity_hv"+"_"+mflcode+ym, activity_hv);
  opdhm.put("latitude"+"_"+mflcode+ym, latitude);
  opdhm.put("longitude"+"_"+mflcode+ym, longitude);
  opdhm.put("maleclinic"+"_"+mflcode+ym, maleclinic);
  opdhm.put("adoleclinic"+"_"+mflcode+ym, adoleclinic);
  opdhm.put("viremiaclinic"+"_"+mflcode+ym, viremiaclinic);
  opdhm.put("emrsite"+"_"+mflcode+ym, emrsite);
  opdhm.put("linkdesk"+"_"+mflcode+ym, linkdesk);
  opdhm.put("islocked"+"_"+mflcode+ym, islocked);


 // if(facilityname.equalsIgnoreCase("Kapua Dispensary")){ System.out.println(" Facility is "+facilityname+" and yearmonth is:"+ym);}
  
  


         int mypos=0;
         
  for(int a=0;a<alldatavals.length;a++){
      
    
       if(a==4||a<3){
           //non numeric characters
       
       }
       else {
    
       }
      
  
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop
    
 
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
 
 
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
  
  }
  else if(z==1){
    //sub-county  
   
  }
  else if(z==2){
   //facility
   
  }
  else if(z==3){
   //mfl
  
  }
   
  else if(z==4){
  //dsdta
 
        }
   //last section of blank rows
  else if(z==pitc_ipd_header1.length-5){
  //dsdta
   
        }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
  
   
        }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   
        }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
   
        }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
  
   
        }
            
  else {
                     
  
   
  
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	 
                     
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                                

         return opdhm;          
     
 }
    
    
    
    
public HashMap Vct(dbConn conn,String sdate,String edate,String facil) throws SQLException {
        
        
      HashMap<String , String> vcthm = new HashMap<String,String>();  
                    
    
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

 
year = new Integer(edate.substring(0,4));
  
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
   
         if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }
          
          
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
      
        //reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
       reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

       
        
       if(reportDuration.equals("4")){
          
    month =new Integer(edate.substring(4,6));
 
  duration1=" ( moh731.yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }
        
   
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


  //shet3.setColumnWidth(6,5000);
  

         
      //_____________________________________________________________report heading row 0   
     
    
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
            + " ,county.CountyID as CountyID "
             + " ,county.burden_cartegory as burdencategory,"+facilitiestable+".constituency as constituency,"+facilitiestable+".ward as ward, "
           
            + " substring(moh731.yearmonth,1,4) as year " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
"or substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03')  then '1. Oct-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
"or substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '2. Apr-Sep' end  as semiannual " +
"            " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
")  then '1. Oct-Dec'  " +
"           when (  " +
" " +
"substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03' " +
"           )  then '2. Jan-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
")  then '3. Apr-Jun'  " +
"           when (  " +
"substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '4. Jul-Sep' end  as quarter " +
" " +
",case when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Oct' then '10. Oct' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Nov' then '11. Nov' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Dec' then '12. dec' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jan' then '01. Jan' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Feb' then '02. Feb' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Mar' then '03. Mar' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Apr' then '04. Apr' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'May' then '05. May' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jun' then '06. Jun' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jul' then '07. Jul' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Aug' then '08. Aug' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Sep' then '09. Sep' " +
" end  as month " +
" " +
",  moh731.yearmonth " +
" ,IFNULL("+facilitiestable+".Owner,\"\") as ownedby " +
" ,IFNULL("+facilitiestable+".Type,\"\") as facilitytype " +
" ,IFNULL("+facilitiestable+".ART_highvolume,\"0\") as art_hv " +
" ,IFNULL("+facilitiestable+".HTC_highvolume,\"0\") as htc_hv " +
" ,IFNULL("+facilitiestable+".PMTCT_highvolume,\"0\") as pmtct_hv " +
" ,IFNULL("+facilitiestable+".all_highvolume,\"0\") as activity_hv " +
" ,IFNULL("+facilitiestable+".latitude,\"\") as latitude " +
" ,IFNULL("+facilitiestable+".longitude,\"\") as longitude " +
" ,IFNULL("+facilitiestable+".Male_clinics,\"0\") as maleclinic " +
" ,IFNULL("+facilitiestable+".Adolescent_clinics,\"0\") as adoleclinic " +
" ,IFNULL("+facilitiestable+".Viremia_clinics,\"0\") as viremiaclinic " +
" ,IFNULL("+facilitiestable+".EMR_Sites,\"0\") as emrsite " +
" ,IFNULL("+facilitiestable+".Link_desks,\"0\") as linkdesk " +
" ,'0'as islocked "
            + " FROM moh731 JOIN "+facilitiestable+" "
            + "ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + "JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID"
            + " WHERE "
    + " "+facilityIds1+" "+duration1+" && ("+facilitiestable+".HTC=1 or PMTCT=1)  AND "+facilitiestable+".active=1   "
              + " GROUP BY moh731.SubPartnerID,moh731.yearmonth order by county.County ,moh731.yearmonth " ;
    
    
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
  
 int facilno=0;
  
   for(int j=0;j<arrayDetails.length;j++)
   {
    
 
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
//System.out.println(facilityname+" lllll added "+splitData+" from db  "+HIV_AdultFemale);
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
   else { System.out.println("Not solved at last bad stage VCT"+facilityname); break;  }
    

    
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

   String ym = conn.rs.getString("yearmonth");
  
  
  vcthm.put("cty"+"_"+mflcode+ym, county);
  vcthm.put("sty"+"_"+mflcode+ym, district);
  vcthm.put("fac"+"_"+mflcode+ym, facilityname);
  vcthm.put("mfl"+"_"+mflcode+ym, ""+mflcode);
  vcthm.put("st"+"_"+mflcode+ym, dsdta);
  vcthm.put("ukpf"+"_"+mflcode+ym,"0");
  vcthm.put("ukpm"+"_"+mflcode+ym,"0");
  vcthm.put("pf1"+"_"+mflcode+ym,""+(ChildFemaleHIV1 ));
  vcthm.put("pm1"+"_"+mflcode+ym,""+ChildMaleHIV1);
//  vcthm.put("pf4"+"_"+mflcode+ym,""+ChildFemaleHIV4);//
//  vcthm.put("pm4"+"_"+mflcode+ym,""+ChildMaleHIV4);//
//  vcthm.put("pf9"+"_"+mflcode+ym,""+ChildFemaleHIV9);//
//  vcthm.put("pm9"+"_"+mflcode+ym,""+ChildMaleHIV9);//
//  vcthm.put("pf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14));//
//  vcthm.put("pm14"+"_"+mflcode+ym,""+ChildMaleHIV14);//
  vcthm.put("pf4"+"_"+mflcode+ym,"0");//
  vcthm.put("pm4"+"_"+mflcode+ym,"0");//
  vcthm.put("pf9"+"_"+mflcode+ym,"0");//
  vcthm.put("pm9"+"_"+mflcode+ym,"0");//
  vcthm.put("pf14"+"_"+mflcode+ym,"0");//
  vcthm.put("pm14"+"_"+mflcode+ym,"0");//
  vcthm.put("pf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19+ChildFemaleHIV4));
  vcthm.put("pm19"+"_"+mflcode+ym,""+(AdultMaleHIV19+ChildMaleHIV4));
  vcthm.put("pf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24+ChildFemaleHIV9));
  vcthm.put("pm24"+"_"+mflcode+ym,""+(AdultMaleHIV24+ChildMaleHIV9));
  vcthm.put("pf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29+ChildFemaleHIV14));
  vcthm.put("pm29"+"_"+mflcode+ym,""+(AdultMaleHIV29+ChildMaleHIV14));
  vcthm.put("pf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34));
  vcthm.put("pm34"+"_"+mflcode+ym,""+AdultMaleHIV34);
  vcthm.put("pf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39));
  vcthm.put("pm39"+"_"+mflcode+ym,""+AdultMaleHIV39);
  vcthm.put("pf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49));
  vcthm.put("pm49"+"_"+mflcode+ym,""+AdultMaleHIV49);
  vcthm.put("pf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50));
  vcthm.put("pm50"+"_"+mflcode+ym,""+AdultMaleHIV50);
  //_____negative
  vcthm.put("uknf"+"_"+mflcode+ym,"0");
  vcthm.put("uknm"+"_"+mflcode+ym,"0");
  vcthm.put("nf1"+"_"+mflcode+ym,""+ChildFemaleHIV1Neg);
  vcthm.put("nm1"+"_"+mflcode+ym,""+ChildMaleHIV1Neg);
//  vcthm.put("nf4"+"_"+mflcode+ym,""+ChildFemaleHIV4Neg);//
//  vcthm.put("nm4"+"_"+mflcode+ym,""+ChildMaleHIV4Neg);//
//  vcthm.put("nf9"+"_"+mflcode+ym,""+ChildFemaleHIV9Neg);//
//  vcthm.put("nm9"+"_"+mflcode+ym,""+ChildMaleHIV9Neg);//
//  vcthm.put("nf14"+"_"+mflcode+ym,""+(ChildFemaleHIV14Neg));//
//  vcthm.put("nm14"+"_"+mflcode+ym,""+ChildMaleHIV14Neg);//
  vcthm.put("nf4"+"_"+mflcode+ym,"0");//
  vcthm.put("nm4"+"_"+mflcode+ym,"0");//
  vcthm.put("nf9"+"_"+mflcode+ym,"0");//
  vcthm.put("nm9"+"_"+mflcode+ym,"0");//
  vcthm.put("nf14"+"_"+mflcode+ym,"0");//
  vcthm.put("nm14"+"_"+mflcode+ym,"0");//
  vcthm.put("nf19"+"_"+mflcode+ym,""+(AdultFemaleHIV19Neg+ChildFemaleHIV4Neg));
  vcthm.put("nm19"+"_"+mflcode+ym,""+(AdultMaleHIV19Neg+ChildMaleHIV4Neg));
  vcthm.put("nf24"+"_"+mflcode+ym,""+(AdultFemaleHIV24Neg+ChildFemaleHIV9Neg));
  vcthm.put("nm24"+"_"+mflcode+ym,""+(AdultMaleHIV24Neg+ChildMaleHIV9Neg));
  vcthm.put("nf29"+"_"+mflcode+ym,""+(AdultFemaleHIV29Neg+(ChildFemaleHIV14Neg)));
  vcthm.put("nm29"+"_"+mflcode+ym,""+(AdultMaleHIV29Neg+ChildMaleHIV14Neg));
  vcthm.put("nf34"+"_"+mflcode+ym,""+(AdultFemaleHIV34Neg));
  vcthm.put("nm34"+"_"+mflcode+ym,""+AdultMaleHIV34Neg);
  vcthm.put("nf39"+"_"+mflcode+ym,""+(AdultFemaleHIV39Neg));
  vcthm.put("nm39"+"_"+mflcode+ym,""+AdultMaleHIV39Neg);
  vcthm.put("nf49"+"_"+mflcode+ym,""+(AdultFemaleHIV49Neg));
  vcthm.put("nm49"+"_"+mflcode+ym,""+AdultMaleHIV49Neg);
  vcthm.put("nf50"+"_"+mflcode+ym,""+(AdultFemaleHIV50Neg));
  vcthm.put("nm50"+"_"+mflcode+ym,""+AdultMaleHIV50Neg);
  vcthm.put("tt"+"_"+mflcode+ym,""+((tested_new711)));

String burdencategory = conn.rs.getString("burdencategory");
 String constituency = conn.rs.getString("constituency");
 String ward = conn.rs.getString("ward");
 String yr = conn.rs.getString("year");
 String semiannual = conn.rs.getString("semiannual");
 String qtr = conn.rs.getString("quarter");
 String mn = conn.rs.getString("month");

 String ownedby = conn.rs.getString("ownedby");
 String facilitytype = conn.rs.getString("facilitytype");
 String art_hv = conn.rs.getString("art_hv");
 String htc_hv = conn.rs.getString("htc_hv");
 String pmtct_hv = conn.rs.getString("pmtct_hv");
 String activity_hv = conn.rs.getString("activity_hv");
 String latitude = conn.rs.getString("latitude");
 String longitude = conn.rs.getString("longitude");
 String maleclinic = conn.rs.getString("maleclinic");
 String adoleclinic = conn.rs.getString("adoleclinic");
 String viremiaclinic = conn.rs.getString("viremiaclinic");
 String emrsite = conn.rs.getString("emrsite");
 String linkdesk = conn.rs.getString("linkdesk");
 String islocked = conn.rs.getString("islocked");
  
  vcthm.put("burdencategory"+"_"+mflcode+ym, burdencategory);
  vcthm.put("constituency"+"_"+mflcode+ym, constituency);
  vcthm.put("ward"+"_"+mflcode+ym, ward);
  vcthm.put("year"+"_"+mflcode+ym, yr);
  vcthm.put("semiannual"+"_"+mflcode+ym, semiannual);
  vcthm.put("quarter"+"_"+mflcode+ym, qtr);
  vcthm.put("month"+"_"+mflcode+ym, mn);
  vcthm.put("yearmonth"+"_"+mflcode+ym, ym);
  vcthm.put("ownedby"+"_"+mflcode+ym, ownedby);
  vcthm.put("facilitytype"+"_"+mflcode+ym, facilitytype);
  vcthm.put("art_hv"+"_"+mflcode+ym, art_hv);
  vcthm.put("htc_hv"+"_"+mflcode+ym, htc_hv);
  vcthm.put("pmtct_hv"+"_"+mflcode+ym, pmtct_hv);
  vcthm.put("activity_hv"+"_"+mflcode+ym, activity_hv);
  vcthm.put("latitude"+"_"+mflcode+ym, latitude);
  vcthm.put("longitude"+"_"+mflcode+ym, longitude);
  vcthm.put("maleclinic"+"_"+mflcode+ym, maleclinic);
  vcthm.put("adoleclinic"+"_"+mflcode+ym, adoleclinic);
  vcthm.put("viremiaclinic"+"_"+mflcode+ym, viremiaclinic);
  vcthm.put("emrsite"+"_"+mflcode+ym, emrsite);
  vcthm.put("linkdesk"+"_"+mflcode+ym, linkdesk);
  vcthm.put("islocked"+"_"+mflcode+ym, islocked);
  

         int mypos=0;
     
         
  for(int a=0;a<alldatavals.length;a++){
      
       mypos++;
       if(a==4||a<3){
           //non numeric characters
       
       }
       else {
       }
      
      
  
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop

  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	HSSFRow rwx=null;                     
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
  
  }
  else if(z==1){
    //sub-county  
  
  }
  else if(z==2){
   //facility
   
  }
  else if(z==3){
   //mfl
   
  }
   
  else if(z==4){
  //dsdta
   
    }
  //last section of blank rows
  else if(z==pitc_ipd_header1.length-5){
  //dsdta
   
    }
   
    else if(z==pitc_ipd_header1.length-4){
  //dsdta
   
   }
    else if(z==pitc_ipd_header1.length-3){
  //dsdta
   
   }
    else if(z==pitc_ipd_header1.length-2){
  //dsdta
   
    }
    else if(z==pitc_ipd_header1.length-1){
  //dsdta
   
        }
         
  else {
                     
   

  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
              
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

               
           return vcthm;      
    
    }
    
    
public HashMap Pmtct(dbConn conn,String sdate,String edate,String facil) throws SQLException{
        
              HashMap<String , String> Pmtcthm = new HashMap<String,String>(); 
     
                   
   
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
 
year = new Integer(edate.substring(0,4));
  
  Calendar ca= Calendar.getInstance();
  int currentyear=ca.get(Calendar.YEAR);
  String facilitiestable="subpartnera";
  int selectedyear=year;
  if(selectedyear<currentyear){
      if(year<2014){ facilitiestable="subpartnera2014"; }
      else  {facilitiestable="subpartnera"+selectedyear;}
  }
   String facilityIds1="";
      if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }
         
          
       // reportDuration=request.getParameter("reportDuration");
        
//        year=2015;
        reportDuration="4";
        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================

        
        
       if(reportDuration.equals("4")){
    month =new Integer(edate.substring(4,6));
 
  duration1=" ( moh731.yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }
        

   String county="";
   String  district="";
    String facilityname="";

  
      
      
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
            +" ,  "+facilitiestable+".SubPartnerID, IFNULL(HTC,0) as HTC, IFNULL(PMTCT,0) as PMTCT,IFNULL(SUM(new_anc),0) AS new_anc "
             + " ,county.burden_cartegory as burdencategory,"+facilitiestable+".constituency as constituency,"+facilitiestable+".ward as ward, "
           
            + " substring(moh731.yearmonth,1,4) as year " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
"or substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03')  then '1. Oct-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
"or substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '2. Apr-Sep' end  as semiannual " +
"            " +
", case when (  " +
"   substring(moh731.yearmonth,5,2)  like '10' " +
"or substring(moh731.yearmonth,5,2)  like '11' " +
"or substring(moh731.yearmonth,5,2)  like '12' " +
")  then '1. Oct-Dec'  " +
"           when (  " +
" " +
"substring(moh731.yearmonth,5,2)  like '01' " +
"or substring(moh731.yearmonth,5,2)  like '02' " +
"or substring(moh731.yearmonth,5,2)  like '03' " +
"           )  then '2. Jan-Mar'  " +
"           when (  " +
"   substring(moh731.yearmonth,5,2)  like '04' " +
"or substring(moh731.yearmonth,5,2)  like '05' " +
"or substring(moh731.yearmonth,5,2)  like '06' " +
")  then '3. Apr-Jun'  " +
"           when (  " +
"substring(moh731.yearmonth,5,2)  like '07' " +
"or substring(moh731.yearmonth,5,2)  like '08' " +
"or substring(moh731.yearmonth,5,2)  like '09' " +
"           )  then '4. Jul-Sep' end  as quarter " +
" " +
",case when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Oct' then '10. Oct' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Nov' then '11. Nov' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Dec' then '12. dec' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jan' then '01. Jan' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Feb' then '02. Feb' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Mar' then '03. Mar' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Apr' then '04. Apr' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'May' then '05. May' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jun' then '06. Jun' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Jul' then '07. Jul' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Aug' then '08. Aug' " +
" when substring(MONTHNAME(concat(moh731.yearmonth,'01')),1,3) like 'Sep' then '09. Sep' " +
" end  as month " +
" " +
",  moh731.yearmonth " +
" ,IFNULL("+facilitiestable+".Owner,\"\") as ownedby " +
" ,IFNULL("+facilitiestable+".Type,\"\") as facilitytype " +
" ,IFNULL("+facilitiestable+".ART_highvolume,\"0\") as art_hv " +
" ,IFNULL("+facilitiestable+".HTC_highvolume,\"0\") as htc_hv " +
" ,IFNULL("+facilitiestable+".PMTCT_highvolume,\"0\") as pmtct_hv " +
" ,IFNULL("+facilitiestable+".all_highvolume,\"0\") as activity_hv " +
" ,IFNULL("+facilitiestable+".latitude,\"\") as latitude " +
" ,IFNULL("+facilitiestable+".longitude,\"\") as longitude " +
" ,IFNULL("+facilitiestable+".Male_clinics,\"0\") as maleclinic " +
" ,IFNULL("+facilitiestable+".Adolescent_clinics,\"0\") as adoleclinic " +
" ,IFNULL("+facilitiestable+".Viremia_clinics,\"0\") as viremiaclinic " +
" ,IFNULL("+facilitiestable+".EMR_Sites,\"0\") as emrsite " +
" ,IFNULL("+facilitiestable+".Link_desks,\"0\") as linkdesk " +
" ,'0'as islocked "
            + ""//new numerator for 2017 //_raise athe issue of monthly and quartely data for eid
           
            + " FROM moh731 JOIN "+facilitiestable+" "
            + " ON moh731.SubPartnerID="+facilitiestable+".SubPartnerID "
            + " JOIN district ON "+facilitiestable+".DistrictID=district.DistrictID JOIN county ON "
            + " district.CountyID=county.CountyID "
            + " left JOIN new_anc on moh731.id=new_anc.id "//added on 9th October 2017
            + " LEFT JOIN ratios ON county.CountyID=ratios.county_id "//added on 4th Jan 2018
            + " WHERE "
            + " "+facilityIds1+" "+duration1+" && ( "+facilitiestable+".PMTCT=1) "
            + " AND (indicator='PMTCT_Known_Postive' OR indicator='PMTCT_New_Postive' OR indicator='PMTCT_ANC')  AND "+facilitiestable+".active=1  "
              + " GROUP BY moh731.SubPartnerID,moh731.yearmonth order by county.County ,moh731.yearmonth " ;
    
    
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
    
        //System.out.print("PMTCT_Facility_"+facilityname);
        //System.out.print(" ANC DIFF_"+ancdiff);
        
       // System.out.println(" ");
     
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
     
//     pmtct_statnum_tes_unknown=(pmtct_pos_unknown+pmtct_neg_unknown+pmtct_kp_unknown);
//     pmtct_statnum_tes_10=(pmtct_pos_1_9+pmtct_neg_1_9+pmtct_kp_1_9);
//     pmtct_statnum_tes_10_14=(pmtct_pos_10_14+pmtct_neg_10_14+pmtct_kp_10_14);
//     pmtct_statnum_tes_15_19=(pmtct_pos_15_19+pmtct_neg_15_19+pmtct_kp_15_19);
//     pmtct_statnum_tes_20_24=(pmtct_pos_20_24+pmtct_neg_20_24+pmtct_kp_20_24);
//     pmtct_statnum_tes_25_29=(pmtct_pos_25_29+pmtct_neg_25_29+pmtct_kp_25_29);
//     pmtct_statnum_tes_30_34=(pmtct_pos_30_34+pmtct_neg_30_34+pmtct_kp_30_34);
//     pmtct_statnum_tes_35_39=(pmtct_pos_35_39+pmtct_neg_35_39+pmtct_kp_35_39);
//     pmtct_statnum_tes_40_49=(pmtct_pos_40_49+pmtct_neg_40_49+pmtct_kp_40_49);
//     pmtct_statnum_tes_50=(pmtct_pos_50+pmtct_neg_50+pmtct_kp_50);
     
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
             
            
             
    String ym = conn.rs.getString("yearmonth");          
             
  Pmtcthm.put("cty"+"_"+mflcode+ym, county);
  Pmtcthm.put("sty"+"_"+mflcode+ym, district);
  Pmtcthm.put("fac"+"_"+mflcode+ym, facilityname);
  Pmtcthm.put("mfl"+"_"+mflcode+ym, ""+mflcode);
  Pmtcthm.put("st"+"_"+mflcode+ym, dsdta);
  Pmtcthm.put("ukpf"+"_"+mflcode+ym,"0");
  Pmtcthm.put("ukpm"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf1"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pm1"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf4"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pm4"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf9"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pm9"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf14"+"_"+mflcode+ym,""+pmtct_pos_10_14);
  Pmtcthm.put("pm14"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf19"+"_"+mflcode+ym,""+(pmtct_pos_15_19));
  Pmtcthm.put("pm19"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf24"+"_"+mflcode+ym,""+(pmtct_pos_20_24));
  Pmtcthm.put("pm24"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf29"+"_"+mflcode+ym,""+(pmtct_pos_25_29));
  Pmtcthm.put("pm29"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf34"+"_"+mflcode+ym,""+(pmtct_pos_30_34));
  Pmtcthm.put("pm34"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf39"+"_"+mflcode+ym,""+(pmtct_pos_35_39));
  Pmtcthm.put("pm39"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf49"+"_"+mflcode+ym,""+(pmtct_pos_40_49));
  Pmtcthm.put("pm49"+"_"+mflcode+ym,"0");
  Pmtcthm.put("pf50"+"_"+mflcode+ym,""+(pmtct_pos_50));
  Pmtcthm.put("pm50"+"_"+mflcode+ym,"0");
  //_____negative
  Pmtcthm.put("uknf"+"_"+mflcode+ym,"0");
  Pmtcthm.put("uknm"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf1"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nm1"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf4"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nm4"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf9"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nm9"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf14"+"_"+mflcode+ym,""+(pmtct_neg_10_14));
  Pmtcthm.put("nm14"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf19"+"_"+mflcode+ym,""+(pmtct_neg_15_19));
  Pmtcthm.put("nm19"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf24"+"_"+mflcode+ym,""+(pmtct_neg_20_24));
  Pmtcthm.put("nm24"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf29"+"_"+mflcode+ym,""+(pmtct_neg_25_29));
  Pmtcthm.put("nm29"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf34"+"_"+mflcode+ym,""+(pmtct_neg_30_34));
  Pmtcthm.put("nm34"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf39"+"_"+mflcode+ym,""+(pmtct_neg_35_39));
  Pmtcthm.put("nm39"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf49"+"_"+mflcode+ym,""+(pmtct_neg_40_49));
  Pmtcthm.put("nm49"+"_"+mflcode+ym,"0");
  Pmtcthm.put("nf50"+"_"+mflcode+ym,""+(pmtct_neg_50));
  Pmtcthm.put("nm50"+"_"+mflcode+ym,"0");
  Pmtcthm.put("tt"+"_"+mflcode+ym,""+((pmtct_anc_tes)));

    String burdencategory = conn.rs.getString("burdencategory");
 String constituency = conn.rs.getString("constituency");
 String ward = conn.rs.getString("ward");
 String yr = conn.rs.getString("year");
 String semiannual = conn.rs.getString("semiannual");
 String qtr = conn.rs.getString("quarter");
 String mn = conn.rs.getString("month");

 String ownedby = conn.rs.getString("ownedby");
 String facilitytype = conn.rs.getString("facilitytype");
 String art_hv = conn.rs.getString("art_hv");
 String htc_hv = conn.rs.getString("htc_hv");
 String pmtct_hv = conn.rs.getString("pmtct_hv");
 String activity_hv = conn.rs.getString("activity_hv");
 String latitude = conn.rs.getString("latitude");
 String longitude = conn.rs.getString("longitude");
 String maleclinic = conn.rs.getString("maleclinic");
 String adoleclinic = conn.rs.getString("adoleclinic");
 String viremiaclinic = conn.rs.getString("viremiaclinic");
 String emrsite = conn.rs.getString("emrsite");
 String linkdesk = conn.rs.getString("linkdesk");
 String islocked = conn.rs.getString("islocked");
  
  Pmtcthm.put("burdencategory"+"_"+mflcode+ym, burdencategory);
  Pmtcthm.put("constituency"+"_"+mflcode+ym, constituency);
  Pmtcthm.put("ward"+"_"+mflcode+ym, ward);
  Pmtcthm.put("year"+"_"+mflcode+ym, yr);
  Pmtcthm.put("semiannual"+"_"+mflcode+ym, semiannual);
  Pmtcthm.put("quarter"+"_"+mflcode+ym, qtr);
  Pmtcthm.put("month"+"_"+mflcode+ym, mn);
  Pmtcthm.put("yearmonth"+"_"+mflcode+ym, ym);
  Pmtcthm.put("ownedby"+"_"+mflcode+ym, ownedby);
  Pmtcthm.put("facilitytype"+"_"+mflcode+ym, facilitytype);
  Pmtcthm.put("art_hv"+"_"+mflcode+ym, art_hv);
  Pmtcthm.put("htc_hv"+"_"+mflcode+ym, htc_hv);
  Pmtcthm.put("pmtct_hv"+"_"+mflcode+ym, pmtct_hv);
  Pmtcthm.put("activity_hv"+"_"+mflcode+ym, activity_hv);
  Pmtcthm.put("latitude"+"_"+mflcode+ym, latitude);
  Pmtcthm.put("longitude"+"_"+mflcode+ym, longitude);
  Pmtcthm.put("maleclinic"+"_"+mflcode+ym, maleclinic);
  Pmtcthm.put("adoleclinic"+"_"+mflcode+ym, adoleclinic);
  Pmtcthm.put("viremiaclinic"+"_"+mflcode+ym, viremiaclinic);
  Pmtcthm.put("emrsite"+"_"+mflcode+ym, emrsite);
  Pmtcthm.put("linkdesk"+"_"+mflcode+ym, linkdesk);
  Pmtcthm.put("islocked"+"_"+mflcode+ym, islocked);          
             

 rowposit++;
 
      
         int mypos=0;
  for(int a=0;a<alldatavals.length;a++){
       mypos++;
       if(a==4||a<3){
    
       }
       else {
       
       }
      
  }
//      shet3.addMergedRegion(new CellRangeAddress(2,5,20,20));
}//end of while loop

    count=rowposit-1;
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
	                    
 for(int a=0;a<staticfacility.size();a++){ //outer loop taking care of the no of rows
  count++;   
  
 
 for(int z=0;z<blankrows;z++){ //inner loop taking care of the number of columns
 //create a row
  if(z==0){
    //county  
   
  }
  else if(z==1){
    //sub-county  
   
  }
  else if(z==2){
   //facility
   
  }
  else if(z==3){
   //mfl
  
               }
   
  else if(z==4){
  //dsdta
   
                }
  
  //last section of blank rows
  else if(z==pitc_pmtct_header1.length-5){
  //dsdta
   
        }
   
    else if(z==pitc_pmtct_header1.length-4){
  //dsdta
  
    }
    
    else if(z==pitc_pmtct_header1.length-3){
  //dsdta
   
        }
    else if(z==pitc_pmtct_header1.length-2){
  //dsdta
   
        }
    else if(z==pitc_pmtct_header1.length-1){
  //dsdta
   
   
        }
           
  else {            
   
  }//end of else
  
 }//end of inner loop                    
 } //end of outer loop                    
                     
  //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% IMPLEMENT STATIC FACILITY LIST METHOD %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
     //____autofilter______       
    //shet3.setAutoFilter(new org.apache.poi.ss.util.CellRangeAddress(3, rowpos - 1, 0, sectionheaders.length+3));

                //System.out.println("1,"+rowpos+",0,"+colposcopy);
                               
               
      return Pmtcthm;           
    
    }// end of pmtct
    
    
    
public void TbClinics(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    
                
   
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

  year = new Integer(edate.substring(0,4));
  
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

 if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }

    
       // reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

       if(reportDuration.equals("4")){

 month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
   
      }
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();


        String mwaka="";
        
        mwaka=""+year;
       // dbConn conn = new dbConn();
        //========Query 1=================
         
                int count1  = 3;
    
                String qry1 = "call rpt_tbclinics_2018(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                

                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    
                    //metaData.getColumnLabel(i).replace("_"," ")
                    

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
          

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

               
                 if(a==3){
                
                   // conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                    //conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                   // conn.rs.getString("" + mycolumns1.get(a));
                }
            
           

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
       



}




public void Sti(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    
                
    
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

year = new Integer(edate.substring(0,4));
  
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


if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+")"; }
  
       // reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

      

     if(reportDuration.equals("4")){
          
    month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        
        String mwaka=""+year;
        
       
            int count1  = 3;
        
        
        
                String qry1 = "call rpt_sti(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"sti\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
               
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                   
                   //metaData.getColumnLabel(i).replace("_"," ");
                    

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
           

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

               
                 if(a==3){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                   conn.rs.getString("" + mycolumns1.get(a));
                }
            
             

            }

            // System.out.println("");
            count1++;
        }
  
        
       



}

public void Emergency(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    
                
    

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

year = new Integer(edate.substring(0,4));
  
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

 if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+")"; }

   
        //reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

    
        
      
      if(reportDuration.equals("4")){
          
   month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
      }
      
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

        String mwaka="";
        
        
       // dbConn conn = new dbConn();
        //========Query 1=================
        
       
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_sti(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"emergency\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
             

                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                  
                 metaData.getColumnLabel(i).replace("_"," ");
                   

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
            

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

               
                 if(a==3){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                    conn.rs.getString("" + mycolumns1.get(a));
                }
            
             

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
    



}

public void Malnutrition(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    


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

year = new Integer(edate.substring(0,4));

  
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

  if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }

 
	 
        
       // reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

       
      
        
      
       if(reportDuration.equals("4")){
          
      month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       

       

        String mwaka=""+year;
        
        
       // dbConn conn = new dbConn();
        //========Query 1=================
        
       
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_malnutrition(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\",\"malnutrition\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
             

                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
         

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

               
                 if(a==3){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                    conn.rs.getString("" + mycolumns1.get(a));
                }
            
               

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
       



}
public void IndexTesting(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    
                
    
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

year = new Integer(edate.substring(0,4));
  

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

if(!facil.equals("")){ facilityIds1=" moh731.SubPartnerID in ("+facil+") and "; }


	 
          
        //reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

        

       if(reportDuration.equals("4")){
    month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  


        String mwaka=""+year;
        
        
       // dbConn conn = new dbConn();
        //========Query 1=================
        
      
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_indextesting(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
                
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                    metaData.getColumnLabel(i).replace("_"," ");
                    

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
           

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

               
                 if(a==3){
                
                   conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                    conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                   conn.rs.getString("" + mycolumns1.get(a));
                }
            
              

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
       



}

public void VMMC_services(dbConn conn,String sdate,String edate,String facil) throws SQLException{

    
    
    
                
    
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

year = new Integer(edate.substring(0,4));
  
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

 if(!facil.equals("")){ facilityIds1=" SubPartnerID in '("+facil+")'"; }
	 
      
        //reportDuration=request.getParameter("reportDuration");
        reportDuration="4";
        

        String period1="";
        String duration1="";
        prevYear=year-1; 
        maxYearMonth=0;
        
//    GET REPORT DURATION============================================

   
       if(reportDuration.equals("4")){
          
    month =new Integer(edate.substring(4,6));
 
  duration1=" ( yearmonth between "+sdate+" and "+edate+" )";
      }
      else{
     duration1="";     
      }

 //__________________________________________________________________________________________ 
    
//______________________________________________________________________________________
//                       CREATE THE WORKSHEETS          
//______________________________________________________________________________________  
       // HSSFWorkbook wb = new HSSFWorkbook();

       
        String mwaka=""+year;
        
       
       // dbConn conn = new dbConn();
        //========Query 1=================
 
                    
                int count1  = 3;
        
        
        
                String qry1 = "call rpt_vmmcserv(\""+sdate+"\",\""+edate+"\",\""+facilitiestable+"\",\""+facilityIds1+"\")";
                //call internal_system.rpt_sti(201710, 201803, 'subpartnera', '', 'sti');
    System.out.println(""+qry1);
                
        conn.rs = conn.st.executeQuery(qry1);

        ResultSetMetaData metaData = conn.rs.getMetaData();
        int columnCount = metaData.getColumnCount();
       
        ArrayList mycolumns1 = new ArrayList();

        while (conn.rs.next()) {

            if (count1 == 3) {
//header rows
               
                for (int i = 1; i <= columnCount; i++) 
                {

                    mycolumns1.add(metaData.getColumnLabel(i));
                  
                    metaData.getColumnLabel(i).replace("_"," ");
                    

                    //create row header
                }//end of for loop
                count1++;
            }//end of if
            //data rows     
           

            for (int a = 0; a < columnCount; a++) {
               // System.out.print(mycolumns1.get(a) + ":" + conn.rs.getString("" + mycolumns1.get(a)));

             
                 if(a==3){
                
               conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                 else if(a > 4){
                
                conn.rs.getInt(mycolumns1.get(a).toString());
                    
                   }
                else {
                    
                conn.rs.getString("" + mycolumns1.get(a));
                }
            
             

            }

            // System.out.println("");
            count1++;
        }
  
        
        //Autofreeze  || Autofilter  || Remove Gridlines ||  
        
        //shet.setAutoFilter(new CellRangeAddress(3, count1 - 1, 0, columnCount-1));

        //System.out.println("1,"+rowpos+",0,"+colposcopy);
      



}

}

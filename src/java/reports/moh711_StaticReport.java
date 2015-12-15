/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.exceptions.CssResolverException;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 *
 * @author Geofrey Nyabuto
 */
public class moh711_StaticReport extends HttpServlet {
    HttpSession session;

//String facilityId,year,month;
//HttpSession session;
//String data,id;
//String facilityId,year,month;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException, CssResolverException {
       session=request.getSession();
       String data,id = null;
       
String FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,
FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR;
String FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,
FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection;
String MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,
MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead;
String VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;
String DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,Userid;
String isValidated="";
String validity="";

String FamilyPlanninng, pmct,maternity,vct,dtc;
    
         String invalidFPTXT,invalidPMTCTTXT,invalidMATTXT,invalidHTCTXT="";
          int expectedFP=0;
 int expectedPMTCT=0;
 int expectedMAT=0;
 int expectedHTC=0;
 int validPMTCT=0;
 int invalidPMTCT=0;
 int totalPMTCT=0;

 int   validFP,invalidFP,totalFP,validMAT,invalidMAT,totalMAT,validHTC,invalidHTC,totalHTC;
 
 int maxYearMonth;
String subcountyid,facility,period;
String reportType,duration,reportDuration,quarter,semi_annual;
int year=0,prevYear=0,month=0;
String header,facilityName,countyName,districtName,mflcode,monthName,facilityId;
       
      

    //--------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------
        //added later to accomodate the years
           String subpartnerid="SubPartnerID";
           String subpartnera="subpartnera";
           
           
           int monthint=0;
           int yearint=0;
           yearint=year;
         

       dbConn conn = new dbConn();
 String validitychecker="";
            session=request.getSession();
            String FP_TAB="";
            String MCH_TAB="";
            String MATERNITY_TAB="";
            String VCT_TAB="";
            String DTC_TAB="";
               String enterdby="";
                // String validity;

//           if(session.getAttribute("forms_holder")!=null && !(session.getAttribute("forms_holder").toString().equals(","))){
           data="";
      subcountyid=facility=period="";
 reportType=duration=reportDuration=quarter=semi_annual="";

 header=facilityName=countyName=districtName=mflcode=monthName="";      
     facilityId="";         
        reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
        header="<table><tr><td colspan=\"12\">REPUBLIC OF KENYA-MINISTRY OF HEALTH</td></tr>";
        header+="<tr><td colspan=\"12\"> NATIONAL INTEGRATED FORM FOR REPRODUCTIVE HEALTH,HIV/AIDS,MALARIA,TB and CHILD NUTRITION</td></tr>";
//        reportType="1";
//        year=2015;
//        reportDuration="4";
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
        
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
            
         duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
      period="Annual Report ";
        }
        else if(reportDuration.equals("2")){
        semi_annual=request.getParameter("semi_annual");
        
        //_________________________________SemiAnnualy_________________________________
	   
	   
	  
//        semi_annual="2";
       if(semi_annual.equals("1")){
           
           
                                  }
           else{
           
       
       }
        
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
           
       duration=" moh711.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03";      
      period="Semi-Annual : <b> OCT ("+prevYear+") -  MARCH ("+year+")</b>"; 
      
      
      
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
           
       duration=" moh711.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
       period="Semi-Annual : <b> APRIL ("+year+") -  SEPT ("+year+")</b>";  
       }
       }
        
        else if(reportDuration.equals("3")){
            String startMonth,endMonth;
       quarter=request.getParameter("quarter");
       
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
       String getMonths="SELECT months,name FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh711.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", prevYear+" - ")+" "+prevYear+"</b>";
      }
      else{
     duration=" moh711.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;
     period="Quarter: <b>"+conn.rs.getString(2).replace("-", year+" - ")+" "+year+"</b>";
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
          
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
//     month=4;
     if(month>=10){
     duration=" moh711.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh711.yearmonth="+year+"0"+month;  
         }
    String getMonthName="SELECT name FROM month WHERE id='"+month+"'" ;
    conn.rs=conn.st.executeQuery(getMonthName);
    if(conn.rs.next()==true){
       if(month>=10){
   period="Month : <b>"+conn.rs.getString(1)+"("+prevYear+")</b>"; 
     }
       else{
        period="Month : <b>"+conn.rs.getString(1)+"("+year+")</b>";
    }
     
    }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility="";  
  header+="<tr><td colspan=\"3\"> All health facilities.</td> <td>Year</td><td> <b>"+year+"</b></td><td colspan=\"7\"> "+period+"</td></tr>"  ;
     }
      
      else 
      {
          String spid="";
  facilityId=request.getParameter("facility");
//  facilityId="403";
  facility="SubPartnerID='"+facilityId+"' &&";    
  //this part should remain constant since the facility parameters being passed are using the subpartnera table
  
  String getName=" SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId, SP_ID FROM subpartnera "
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
  
   //if its subpartner2014, use SP_ID not subpartnerid
  
          System.out.println("____SubPartnerID::"+subpartnerid);
  if(subpartnerid.equalsIgnoreCase("SP_ID"))
  {
      
  facility="moh711.SubPartnerID='"+spid+"' &&";
  
  }
  
  header+="<tr><td>District</td><td> <b>"+districtName+"</b></td><td>  County</td><td> <b>"+countyName+"</b></td><td>   Facility</td><td> <b>"+facilityName+"</b></td><td colspan=\"2\">"+period+"</td><td>   Year</td><td> <b>"+year+"</b></td><td>   MFL Code</td><td> <b>"+mflcode+"</b></td><td></tr>";
      }
     
    header+="</table>";  

 
    System.out.println("id is  "+id);
      invalidFPTXT=invalidPMTCTTXT=invalidMATTXT=invalidHTCTXT="";     
//          id="2015_1_14498";
    String fppane="";
    String pmctpane="";
    String matpane="";
    String htcpane=""; 
    String activeclass="";
    activeclass="active";
    int counter=0;
  
    String ul="  <ul class=\"nav nav-tabs\">\n" ;

                    
              // INITIALIZING VARIABLES 
              
              
  // FAMILY PLANNING            
FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR="";
FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="";
FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
FPIMPLANTSRemoval="";
//PMCT VARIABLES
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";

 MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=MATDischargealive=MATReferral=MATNeoNatalD=
MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
 
 VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
 
 DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

           
          String checker="SELECT sum(FPMicrolutN),"
            + " sum(FPMicrolutR), sum(FPMicrolutT), sum(FPMicrogynonN), sum(FPMicrogynonR), sum(FPMicrogynonT),"
            + " sum(FPINJECTIONSN), sum(FPINJECTIONSR), sum(FPINJECTIONST), sum(FPIUCDN), sum(FPIUCDR),sum(FPIUCDT),sum(FPIMPLANTSN),sum(FPIMPLANTSR), "
            + " sum(FPIMPLANTST), sum(FPBTLN), sum(FPBTLR),sum(FPBTLT),sum(FPVasectomyN),sum(FPVasectomyR),sum(FPVasectomyT),sum(FPCONDOMSN),sum(FPCONDOMSR), "
            + "sum(FPCONDOMST), sum(FPOTHERN), sum(FPOTHERR), sum(FPOTHERT), sum(FPCLIENTSN), sum(FPCLIENTSR), sum(FPCLIENTST), sum(FPIUCDRemoval),"
            + "sum(FPIMPLANTSRemoval), sum(PMCTA_1stVisit_ANC),sum(PMCTA_ReVisit_ANC), sum(PMCTANCClientsT),sum(PMCTHB7), sum(PMCTIPT1),"
            + "sum(PMCTIPT2), sum(PMCTANCClients4),sum(PMCTITN), sum(MATNormalDelivery), sum(MATCSection), sum(MATBreech),sum(MATAssistedVag), "
            + "sum(MATDeliveryT),sum(MATLiveBirth), sum(MATStillBirth), sum(MATWeight2500), sum(MATPreTerm), sum(MATDischargealive), sum(MATReferral), sum(MATNeoNatalD),"
            + " sum(MATMaternalD), sum(MATAPHAlive), sum(MATAPHDead), sum(MATPPHAlive), sum(MATPPHDead), sum(MATEclampAlive),sum( MATEclampDead),"
            + " sum(MATRupUtAlive), sum(MATRupUtDead), sum(MATObstrLaborAlive), sum(MATObstrLaborDead), sum(MATSepsisAlive), sum(MATSepsisDead), "
            + "sum(VCTClient_Couns_CM), sum(VCTClient_Couns_CF), sum(VCTClient_Couns_AM), sum(VCTClient_Couns_AF), "
            + "sum(VCTClient_Couns_TOT), sum(VCTClient_Tested_CM), sum(VCTClient_Tested_CF), sum(VCTClient_Tested_AM),"
            + "sum( VCTClient_Tested_AF), sum(VCTClient_Tested_TOT), sum(VCTClient_HIV_CM), sum(VCTClient_HIV_CF), sum(VCTClient_HIV_AM),sum(VCTClient_HIV_AF),"
            + "sum( VCTClient_HIV_TOT), sum(VCTPartner_Couns_TOT), sum(VCTPartner_Tested_TOT), sum(VCTPartner_HIV_TOT),sum(VCTPartner_Disc_TOT),"
            + " sum(DTCA_Couns_In_CM), sum(DTCA_Couns_In_CF), sum(DTCA_Couns_In_AM), sum(DTCA_Couns_In_AF), sum(DTCA_Couns_In_Tot), sum(DTCA_Couns_Out_CM), "
            + "sum(DTCA_Couns_Out_CF), sum(DTCA_Couns_Out_AM), sum(DTCA_Couns_Out_AF), sum(DTCA_Couns_Out_Tot),sum(DTCB_Test_In_CM), sum(DTCB_Test_In_CF),"
            + " sum(DTCB_Test_In_AM), sum(DTCB_Test_In_AF), sum(DTCB_Test_In_Tot), sum(DTCB_Test_Out_CM), sum(DTCB_Test_Out_CF), sum(DTCB_Test_Out_AM),"
            + " sum(DTCB_Test_Out_AF), sum(DTCB_Test_Out_Tot),sum(DTCC_HIV_In_CM), sum(DTCC_HIV_In_CF), sum(DTCC_HIV_In_AM), sum(DTCC_HIV_In_AF),"
            + " sum(DTCC_HIV_In_Tot),sum( DTCC_HIV_Out_CM), sum(DTCC_HIV_Out_CF), sum(DTCC_HIV_Out_AM),sum(DTCC_HIV_Out_AF),sum(DTCC_HIV_Out_Tot) "
            + "FROM moh711 WHERE "+facility+" "+duration ;
          System.out.println(checker);
          
          conn.rs=conn.st.executeQuery(checker);
          
          if(conn.rs.next()==true){
              
              System.out.println("Data already exist loading............................");
              
              
  
          if(conn.rs.getString(1)!=null){FPMicrolutN=conn.rs.getString(1);}
          else if (FPMicrolutN==null){FPMicrolutN="";}
    if(conn.rs.getString(2)!=null){       
  FPMicrolutR=conn.rs.getString(2);}
    else if(FPMicrolutR==null){FPMicrolutR="";}
  
  if(conn.rs.getString(3)!=null){FPMicrolutT=conn.rs.getString(3);}else{FPMicrolutT="";}
  if(conn.rs.getString(4)!=null){FPMicrogynonN=conn.rs.getString(4);}else{FPMicrogynonN="";}
  if(conn.rs.getString(5)!=null){FPMicrogynonR=conn.rs.getString(5);}else{FPMicrogynonR="";}
  if(conn.rs.getString(6)!=null){FPMicrogynonT=conn.rs.getString(6);}else{FPMicrogynonT="";}
  if(conn.rs.getString(7)!=null){FPINJECTIONSN=conn.rs.getString(7);}else{FPINJECTIONSN="";}
  if(conn.rs.getString(8)!=null){FPINJECTIONSR=conn.rs.getString(8);}else{FPINJECTIONSR="";}
  if(conn.rs.getString(9)!=null){FPINJECTIONST=conn.rs.getString(9);}else{FPINJECTIONST="";}
  if(conn.rs.getString(10)!=null){FPIUCDN=conn.rs.getString(10);}else{FPIUCDN="";}
  if(conn.rs.getString(11)!=null){FPIUCDR=conn.rs.getString(11);}else{FPIUCDR="";}
  if(conn.rs.getString(12)!=null){FPIUCDT=conn.rs.getString(12);}else{FPIUCDT="";}
  if(conn.rs.getString(13)!=null){FPIMPLANTSN=conn.rs.getString(13);}else{FPIMPLANTSN="";}
  if(conn.rs.getString(14)!=null){FPIMPLANTSR=conn.rs.getString(14);}else{FPIMPLANTSR="";}
  if(conn.rs.getString(15)!=null){FPIMPLANTST=conn.rs.getString(15);}else{FPIMPLANTST="";}
  if(conn.rs.getString(16)!=null){FPBTLN=conn.rs.getString(16);}else{FPBTLN="";}
  if(conn.rs.getString(17)!=null){FPBTLR=conn.rs.getString(17);}else{FPBTLR="";}
  if(conn.rs.getString(18)!=null){FPBTLT=conn.rs.getString(18);}else{FPBTLT="";}
  if(conn.rs.getString(19)!=null){FPVasectomyN=conn.rs.getString(19);}else{FPVasectomyN="";}
  if(conn.rs.getString(20)!=null){FPVasectomyR=conn.rs.getString(20);}else{FPVasectomyR="";}
  if(conn.rs.getString(21)!=null){FPVasectomyT=conn.rs.getString(21);}else{FPVasectomyT="";}
  if(conn.rs.getString(22)!=null){FPCONDOMSN=conn.rs.getString(22);}else{FPCONDOMSN="";}
  if(conn.rs.getString(23)!=null){FPCONDOMSR=conn.rs.getString(23);}else{FPCONDOMSR="";}
  if(conn.rs.getString(24)!=null){FPCONDOMST=conn.rs.getString(24);}else{FPCONDOMST="";}
         
  if(conn.rs.getString(25)!=null){FPOTHERN=conn.rs.getString(25);}else{FPOTHERN="";}
  if(conn.rs.getString(26)!=null){FPOTHERR=conn.rs.getString(26);}else{FPOTHERR="";}
  if(conn.rs.getString(27)!=null){FPOTHERT=conn.rs.getString(27);}else{FPOTHERT="";}
         
  if(conn.rs.getString(28)!=null){FPCLIENTSN=conn.rs.getString(28);}else{
  FPCLIENTSN="";}
  if(conn.rs.getString(29)!=null){FPCLIENTSR=conn.rs.getString(29);}else{
  FPCLIENTSR="";
  }
  if(conn.rs.getString(30)!=null){FPCLIENTST=conn.rs.getString(30);}
  else{FPCLIENTST="";
  }
  if(conn.rs.getString(31)!=null){FPIUCDRemoval=conn.rs.getString(31);}else{FPIUCDRemoval="";}
  if(conn.rs.getString(32)!=null){FPIMPLANTSRemoval=conn.rs.getString(32);}else{FPIMPLANTSRemoval="";}
         
          
          
          
          // mch 
 
   if(conn.rs.getString(33)!=null){PMCTA_1stVisit_ANC=conn.rs.getString(33);}else{PMCTA_1stVisit_ANC="";}
  if(conn.rs.getString(34)!=null){PMCTA_ReVisit_ANC=conn.rs.getString(34);}else{PMCTA_ReVisit_ANC="";}
  if(conn.rs.getString(35)!=null){PMCTANCClientsT=conn.rs.getString(35);}else{PMCTANCClientsT="";}
  if(conn.rs.getString(36)!=null){PMCTHB7=conn.rs.getString(36);}else{PMCTHB7="";}
  if(conn.rs.getString(37)!=null){PMCTIPT1=conn.rs.getString(37);}else{PMCTIPT1="";}
  if(conn.rs.getString(38)!=null){PMCTIPT2=conn.rs.getString(38);}else{PMCTIPT2="";}
  if(conn.rs.getString(39)!=null){PMCTANCClients4=conn.rs.getString(39);}else{PMCTANCClients4="";}
  if(conn.rs.getString(40)!=null){PMCTITN=conn.rs.getString(40);}else{PMCTITN="";}
  

  if(conn.rs.getString(41)!=null){MATNormalDelivery=conn.rs.getString(41);}else{MATNormalDelivery="";}
  if(conn.rs.getString(42)!=null){MATCSection=conn.rs.getString(42);}else{MATCSection="";}
  if(conn.rs.getString(43)!=null){MATBreech=conn.rs.getString(43);}else{MATBreech="";}
  if(conn.rs.getString(44)!=null){MATAssistedVag=conn.rs.getString(44);}else{MATAssistedVag="";}
  if(conn.rs.getString(45)!=null){MATDeliveryT=conn.rs.getString(45);}else{MATDeliveryT="";}
  if(conn.rs.getString(46)!=null){MATLiveBirth=conn.rs.getString(46);}else{MATLiveBirth="";}
  if(conn.rs.getString(47)!=null){MATStillBirth=conn.rs.getString(47);}else{MATStillBirth="";}

  if(conn.rs.getString(48)!=null){MATWeight2500=conn.rs.getString(48);}else{MATWeight2500="";}
  if(conn.rs.getString(49)!=null){MATPreTerm=conn.rs.getString(49);}else{MATPreTerm="";}
  if(conn.rs.getString(50)!=null){MATDischargealive=conn.rs.getString(50);}else{MATDischargealive="";}
  if(conn.rs.getString(51)!=null){MATReferral=conn.rs.getString(51);}else{MATReferral="";}
  if(conn.rs.getString(52)!=null){MATNeoNatalD=conn.rs.getString(52);}else{MATNeoNatalD="";}
  if(conn.rs.getString(53)!=null){MATMaternalD=conn.rs.getString(53);}else{MATMaternalD="";}
  if(conn.rs.getString(54)!=null){MATAPHAlive=conn.rs.getString(54);}else{MATAPHAlive="";}
  if(conn.rs.getString(55)!=null){MATAPHDead=conn.rs.getString(55);}else{MATAPHDead="";}
  if(conn.rs.getString(56)!=null){MATPPHAlive=conn.rs.getString(56);}else{MATPPHAlive="";}
  if(conn.rs.getString(57)!=null){MATPPHDead=conn.rs.getString(57);}else{MATPPHDead="";}
  if(conn.rs.getString(58)!=null){MATEclampAlive=conn.rs.getString(58);}else{MATEclampAlive="";}
  if(conn.rs.getString(59)!=null){MATEclampDead=conn.rs.getString(59);}else{MATEclampDead="";}
  if(conn.rs.getString(60)!=null){MATRupUtAlive=conn.rs.getString(60);}else{MATRupUtAlive="";}
  if(conn.rs.getString(61)!=null){MATRupUtDead=conn.rs.getString(61);}else{MATRupUtDead="";}
  if(conn.rs.getString(62)!=null){MATObstrLaborAlive=conn.rs.getString(62);}else{MATObstrLaborAlive="";}
  if(conn.rs.getString(63)!=null){MATObstrLaborDead=conn.rs.getString(63);}else{MATObstrLaborDead="";}
  if(conn.rs.getString(64)!=null){MATSepsisAlive=conn.rs.getString(64);}else{MATSepsisAlive="";}
  if(conn.rs.getString(65)!=null){MATSepsisDead=conn.rs.getString(65);}else{MATSepsisDead="";}
  if(conn.rs.getString(66)!=null){VCTClient_Couns_CM=conn.rs.getString(66);}else{VCTClient_Couns_CM="";}
  if(conn.rs.getString(67)!=null){VCTClient_Couns_CF=conn.rs.getString(67);}else{VCTClient_Couns_CF="";}
  if(conn.rs.getString(68)!=null){VCTClient_Couns_AM=conn.rs.getString(68);}else{VCTClient_Couns_AM="";}
  if(conn.rs.getString(69)!=null){VCTClient_Couns_AF=conn.rs.getString(69);}else{VCTClient_Couns_AF="";}
  if(conn.rs.getString(70)!=null){VCTClient_Couns_TOT=conn.rs.getString(70);}else{VCTClient_Couns_TOT="";}
  if(conn.rs.getString(71)!=null){VCTClient_Tested_CM=conn.rs.getString(71);}else{VCTClient_Tested_CM="";}
  if(conn.rs.getString(72)!=null){VCTClient_Tested_CF=conn.rs.getString(72);}else{VCTClient_Tested_CF="";}
  if(conn.rs.getString(73)!=null){VCTClient_Tested_AM=conn.rs.getString(73);}else{VCTClient_Tested_AM="";}
  if(conn.rs.getString(74)!=null){VCTClient_Tested_AF=conn.rs.getString(74);}else{VCTClient_Tested_AF="";}
  if(conn.rs.getString(75)!=null){VCTClient_Tested_TOT=conn.rs.getString(75);}else{VCTClient_Tested_TOT="";}
  if(conn.rs.getString(76)!=null){VCTClient_HIV_CM=conn.rs.getString(76);}else{VCTClient_HIV_CM="";}
  if(conn.rs.getString(77)!=null){VCTClient_HIV_CF=conn.rs.getString(77);}else{VCTClient_HIV_CF="";}
  if(conn.rs.getString(78)!=null){VCTClient_HIV_AM=conn.rs.getString(78);}else{VCTClient_HIV_AM="";}
  if(conn.rs.getString(79)!=null){VCTClient_HIV_AF=conn.rs.getString(79);}else{VCTClient_HIV_AF="";}
  if(conn.rs.getString(80)!=null){VCTClient_HIV_TOT=conn.rs.getString(80);}else{VCTClient_HIV_TOT="";}
  if(conn.rs.getString(81)!=null){VCTPartner_Couns_TOT=conn.rs.getString(81);}else{VCTPartner_Couns_TOT="";}
  if(conn.rs.getString(82)!=null){VCTPartner_Tested_TOT=conn.rs.getString(82);}else{VCTPartner_Tested_TOT="";}
  if(conn.rs.getString(83)!=null){VCTPartner_HIV_TOT=conn.rs.getString(83);}else{VCTPartner_HIV_TOT="";}
  if(conn.rs.getString(84)!=null){VCTPartner_Disc_TOT=conn.rs.getString(84);}else{VCTPartner_Disc_TOT="";}
  
  
  
//  DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=
  //DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot
  //=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

  
  //dtc
  if(conn.rs.getString(85)!=null){DTCA_Couns_In_CM=conn.rs.getString(85);}else{DTCA_Couns_In_CM="";}
  if(conn.rs.getString(86)!=null){DTCA_Couns_In_CF=conn.rs.getString(86);}else{DTCA_Couns_In_CF="";}
  if(conn.rs.getString(87)!=null){DTCA_Couns_In_AM=conn.rs.getString(87);}else{DTCA_Couns_In_AM="";}
  if(conn.rs.getString(88)!=null){DTCA_Couns_In_AF=conn.rs.getString(88);}else{DTCA_Couns_In_AF="";}
  if(conn.rs.getString(89)!=null){DTCA_Couns_In_Tot=conn.rs.getString(89);}else{DTCA_Couns_In_Tot="";}
  if(conn.rs.getString(90)!=null){DTCA_Couns_Out_CM=conn.rs.getString(90);}else{DTCA_Couns_Out_CM="";}
  if(conn.rs.getString(91)!=null){DTCA_Couns_Out_CF=conn.rs.getString(91);}else{DTCA_Couns_Out_CF="";}
  if(conn.rs.getString(92)!=null){DTCA_Couns_Out_AM=conn.rs.getString(92);}else{DTCA_Couns_Out_AM="";}
  if(conn.rs.getString(93)!=null){DTCA_Couns_Out_AF=conn.rs.getString(93);}else{DTCA_Couns_Out_AF="";}
  if(conn.rs.getString(94)!=null){DTCA_Couns_Out_Tot=conn.rs.getString(94);}else{DTCA_Couns_Out_Tot="";}
  if(conn.rs.getString(95)!=null){DTCB_Test_In_CM=conn.rs.getString(95);}else{DTCB_Test_In_CM="";}
  if(conn.rs.getString(96)!=null){DTCB_Test_In_CF=conn.rs.getString(96);}else{DTCB_Test_In_CF="";}
  if(conn.rs.getString(97)!=null){DTCB_Test_In_AM=conn.rs.getString(97);}else{DTCB_Test_In_AM="";}
  if(conn.rs.getString(98)!=null){DTCB_Test_In_AF=conn.rs.getString(98);}else{DTCB_Test_In_AF="";}
  if(conn.rs.getString(99)!=null){DTCB_Test_In_Tot=conn.rs.getString(99);}else{DTCB_Test_In_Tot="";}
  if(conn.rs.getString(100)!=null){DTCB_Test_Out_CM=conn.rs.getString(100);}else{DTCB_Test_Out_CM="";}
  if(conn.rs.getString(101)!=null){DTCB_Test_Out_CF=conn.rs.getString(101);}else{DTCB_Test_Out_CF="";}
  if(conn.rs.getString(102)!=null){DTCB_Test_Out_AM=conn.rs.getString(102);}else{DTCB_Test_Out_AM="";}
  if(conn.rs.getString(103)!=null){DTCB_Test_Out_AF=conn.rs.getString(103);}else{DTCB_Test_Out_AF="";}
  if(conn.rs.getString(104)!=null){DTCB_Test_Out_Tot=conn.rs.getString(104);}else{DTCB_Test_Out_Tot="";}
  if(conn.rs.getString(105)!=null){DTCC_HIV_In_CM=conn.rs.getString(105);}else{DTCC_HIV_In_CM="";}
  if(conn.rs.getString(106)!=null){DTCC_HIV_In_CF=conn.rs.getString(106);}else{DTCC_HIV_In_CF="";}
  if(conn.rs.getString(107)!=null){DTCC_HIV_In_AM=conn.rs.getString(107);}else{DTCC_HIV_In_AM="";}
  if(conn.rs.getString(108)!=null){DTCC_HIV_In_AF=conn.rs.getString(108);}else{DTCC_HIV_In_AF="";}
  if(conn.rs.getString(109)!=null){DTCC_HIV_In_Tot=conn.rs.getString(109);}else{DTCC_HIV_In_Tot="";}
  if(conn.rs.getString(110)!=null){DTCC_HIV_Out_CM=conn.rs.getString(110);}else{DTCC_HIV_Out_CM="";}
  if(conn.rs.getString(111)!=null){DTCC_HIV_Out_CF=conn.rs.getString(111);}else{DTCC_HIV_Out_CF="";}
  if(conn.rs.getString(112)!=null){DTCC_HIV_Out_AM=conn.rs.getString(112);}else{DTCC_HIV_Out_AM="";}
  if(conn.rs.getString(113)!=null){DTCC_HIV_Out_AF=conn.rs.getString(113);}else{DTCC_HIV_Out_AF="";}
  if(conn.rs.getString(114)!=null){DTCC_HIV_Out_Tot=conn.rs.getString(114);}else{DTCC_HIV_Out_Tot="";}
  
  
       


     

          
    }
    
            FamilyPlanninng=pmct=maternity=vct=dtc="";    
          FP_TAB+="";
          
        
//          FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR=
//FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="";
//FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
//FPIMPLANTSRemoval="";"
      FamilyPlanninng=""
              +  "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> FAMILY PLANNING </b></legend>"
              + "<table frame=\"box\"  border=\"1\" style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"2\" class=\"form-actions\"><b>A: Family Planning </b></td>"
              + "<td class=\"form-actions\"> <b>NEW CLIENTS </b></td>"
              + "<td class=\"form-actions\"> <b>RE-VISITS </b></td>"
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
              + "<tr>"
              + "<td rowspan=\"2\">1. PILLS</td><td  >MICROLUT</td>"
              + "<td >"+FPMicrolutN+"</td>"
              + "<td >"+FPMicrolutR+"</td>"
              + "<td >"+FPMicrolutT+"</td>"
         
              + "<tr>"
//              + "<td>2</td>"
//              + "<td >PILLS</td>"
              + "<td  >MICROGYNON</td>"
              + "<td >"+FPMicrogynonN+"</td>"
              + "<td >"+FPMicrogynonR+"</td>"
              + "<td >"+FPMicrogynonT+"</td>"
              + "</tr>"
              
              + "<tr>"
              + "<td >2. INJECTIONS</td>"
              + "<td  >INJECTIONS</td>"
              + "<td >"+FPINJECTIONSN+"</td>"
              + "<td >"+FPINJECTIONSR+"</td>"
              + "<td >"+FPINJECTIONST+"</td>"
              + "</tr>"
              
              
              + "<tr>"
              + "<td >3. I.U.C.D.</td>"
              + "<td  >Insertion</td>"
              + "<td >"+FPIUCDN+" </td>"
              + "<td >"+FPIUCDR+" </td>"
              + "<td >"+FPIUCDT+" </td>"
              + "</tr>"
              
              + "<tr>"
              + "<td >4. IMPLANTS</td><td  >Insertion</td>"
              + "<td >"+FPIMPLANTSN+"</td>"
              + "<td >"+FPIMPLANTSR+"</td>"
              + "<td >"+FPIMPLANTST+"</td>"
              + "</tr>"
              
              + "<tr>"
              + "<td  rowspan=\"2\">5. STERILIZATION</td><td  >B.T.L</td>"
              + "<td >"+FPBTLN+"</td>"
              + "<td >"+FPBTLR+"</td>"
              + "<td >"+FPBTLT+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td  >Vasectomy</td>"
              + "<td >"+FPVasectomyN+"</td>"
              + "<td >"+FPVasectomyR+"</td>"
              + "<td >"+FPVasectomyT+"</td>"
              + "</tr>"
                 + "<tr>"
              + "<td >6. CONDOMS</td><td  >No. of clients receiving</td>"
              + "<td >"+FPCONDOMSN+"</td>"
              + "<td >"+FPCONDOMSR+"</td>"
              + "<td >"+FPCONDOMST+"</td>"
              + "</tr>"
                 + "<tr>"
              + "<td colspan=\"2\">7. ALL OTHERS:(Specify)</td>"
           
              + "<td >"+FPOTHERN+"</td>"
              + "<td >"+FPOTHERR+"</td>"
              + "<td >"+FPOTHERT+"</td>"
              + "</tr>"
              
                 + "<tr>"
              + "<td colspan=\"2\">8. TOTAL NO. OF CLIENTS</td>"
            
              + "<td >"+FPCLIENTSN+"</td>"
              + "<td >"+FPCLIENTSR+"</td>"
              + "<td >"+FPCLIENTST+"</td>"
              + "</tr>"
                 + "<tr>"
              + "<td >9. REMOVALS</td>"
              + "<td  > IUCD </td>"
              + "<td >"+FPIUCDRemoval+"</td>"
              + "<td >IMPLANTS</td>"
              + "<td >"+FPIMPLANTSRemoval+"</td>"
              + "</tr></table></fieldset>" ;
                 FP_TAB+=FamilyPlanninng;
                 
                 pmct+="";
           pmct+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> B: MCH-ANC/PMTCT </b></legend>"
                   + "<table border=\"1\" frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
                   + "<tr>"
              + "<td  class=\"form-actions\"><b> </b></td>"
              + "<td class=\"form-actions\"> <b>NEW  </b></td>"
              + "<td class=\"form-actions\"> <b>RE-VISIT </b></td>"
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
                  + "<tr>"
              + "<td>1.No of ANC Clients</td>"
                   + "<td >"+PMCTA_1stVisit_ANC+"</td>"
                   + "<td >"+PMCTA_ReVisit_ANC+"</td>"
                   + "<td >"+PMCTANCClientsT+"</td>"
              + "</tr></table>"
               + "<table border=\"1\" frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
               + " <td colspan=\"3\" >2. No of clients with Hb <7 g/dl </td>"
                   + "<td >"+PMCTHB7+"</td>"
              + "</tr>"
               + "<tr>"
               + "  <td colspan=\"3\" >3. No of clients given IPT (1st Dose) </td>"
                   + "<td >"+PMCTIPT1+" </td>"
              + "</tr>"
               + "<tr>"
               + "   <td colspan=\"3\" >4. No of clients given IPT (2nd Dose) </td>"
                   + "<td >"+PMCTIPT2+"</td>"
              + "</tr>"
               + "<tr>"
               + "<td colspan=\"3\" >5. No of clients completed 4th Antenatal Visit </td>"
                   + "<td >"+PMCTANCClients4+"</td>"
              + "</tr>"
               + "<tr>"
               + "<tr>"
               + "   <td colspan=\"3\" >6. No of ITNs distributed to ANC Clients </td>"
                   + "<td >"+PMCTITN+"</td>"
              + "</tr></table></fieldset>"
              
              
              + ""
              + "";
      
                  MCH_TAB+=pmct;
                  
                  //MATERNITY DELIVERIES
                  
     // maternity MATNormalDelivery=MATCSection=""MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=
                  //MATPreTerm=
  //MATDischargealive=MATReferral=MATNeoNatalD=
//MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive
  //=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
          maternity+="";    
     maternity+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> C: MATERNITY / SAFE DELIVERIES </b></legend>"+
             "<table border=\"1\" frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"1\" class=\"form-actions\"><b></b></td>"
              + "<td  colspan=\"2\" class=\"form-actions\"> <b>NUMBER  </b></td>"
               + "</tr>"
                  + "<tr>"
              + "<td>1.Normal Deliveries </td>"
                   + "<td  colspan=\"2\">"+MATNormalDelivery+"</td>"
              + "</tr>"
               + "<tr>"
               + " <td>2. Caesarian Sections </td>"
                   + "<td  colspan=\"2\">"+MATCSection+"</td>"
              + "</tr>"
               + "<tr>"
               + "  <td>3. Breech Delivery </td>"
                   + "<td  colspan=\"2\">"+MATBreech+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>4. Assisted vaginal delivery </td>"
                   + "<td  colspan=\"2\">"+MATAssistedVag+"</td>"
              + "</tr>"
               + "<tr>"
               + "<td>5. TOTAL DELIVERIES</td>"
                   + "<td  colspan=\"2\">"+MATDeliveryT+"</td>"
              + "</tr>"
               
               + "<tr>"
               + "   <td>6. Live Births </td>"
                   + "<td  colspan=\"2\">"+MATLiveBirth+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>7. Still Births </td>"
                   + "<td  colspan=\"2\">"+MATStillBirth+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>8. Under Weight Babies (Weight below 2500 grams) </td>"
                   + "<td  colspan=\"2\">"+MATWeight2500+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>9. Pre-Term babies  </td>"
                   + "<td  colspan=\"2\">"+MATPreTerm+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>10. No of babies discharged alive </td>"
                   + "<td style=\"padding-right:20px;\" colspan=\"2\" >"+MATDischargealive+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>11. Referrals </td>"
                   + "<td  colspan=\"2\">"+MATReferral+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>12.Neonatal Deaths </td>"
                   + "<td  colspan=\"2\">"+MATNeoNatalD+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>13. Maternal Deaths </td>"
                   + "<td  colspan=\"2\">"+MATMaternalD+"</td>"
              + "</tr>"
               + "<tr>"
               + "    <td class=\"form-actions\"><b> Maternal Complications </b></td>"
                   + "<td  class=\"form-actions\"><b>Alive </b></td>"
                   + "<td class=\"form-actions\" ><b>Dead </b></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>14. A.P.H. (Ante Partum Haemorrhage) </td>"
                   + "<td >"+MATAPHAlive+"</td>"
                   + "<td >"+MATAPHDead+"</td>"
              + "</tr>"
             + ""
              + "<tr>"
               + "   <td>15. P.P.H. (Post Partum Haemorrhage) </td>"
                   + "<td >"+MATPPHAlive+"</td>"
                   + "<td >"+MATPPHDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>16. Eclampsia</td>"
                   + "<td >"+MATEclampAlive+"</td>"
                   + "<td >"+MATEclampDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>17. Ruptured Uterus</td>"
                   + "<td >"+MATRupUtAlive+"</td>"
                   + "<td >"+MATRupUtDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>18. Obstructed Labour</td>"
                   + "<td >"+MATObstrLaborAlive+"</td>"
                   + "<td >"+MATObstrLaborDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>19. Sepsis</td>"
                   + "<td >"+MATSepsisAlive+"</td>"
                   + "<td >"+MATSepsisDead+"</td>"
              + "</tr>"
             + ""
             + ""
             + "</table></fieldset>"
              
              
              + ""
              + "";
     MATERNITY_TAB+=maternity;
               //MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive
  //=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
                   
                  
    //vct
     vct+="";
   
  vct+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> H: VCT </b></legend>"+
             "<table border=\"1\" frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
          + "<tr>"
              + "<td rowspan=\"2\" colspan=\"2\" class=\"form-actions\"><b></b></td>"
              + "<td  colspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"> <b>15-24  </b></td>"
              + "<td colspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"> <b> >=25 Years </b></td>"
              + "<td rowspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"> <b>TOTAL </b></td>"
              + "</tr>"
          + "<tr>"
              + "<td  class=\"form-actions\" style=\"text-align:center;\"><b>F </b></td>"
              + "<td   class=\"form-actions\" style=\"text-align:center;\"> <b>M </b></td>"
              + "<td  class=\"form-actions\" style=\"text-align:center;\"> <b>F</b></td>"
              + "<td class=\"form-actions\" style=\"text-align:center;\"> <b>M </b></td>"
              + "</tr>"
          
                  + "<tr>"
              + "<td rowspan=\"3\">1. VCT Clients</td>"
                    + "<td>Counselled</td>"
                   + "<td  style=\"text-align:center;\" >"+VCTClient_Couns_CF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Couns_CM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Couns_AF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Couns_AM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Couns_TOT+"</td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>Tested</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Tested_CF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Tested_CM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Tested_AF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Tested_AM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_Tested_TOT+"</td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>HIV+</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_HIV_CF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_HIV_CM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_HIV_AF+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_HIV_AM+"</td>"
                   + "<td  style=\"text-align:center;\">"+VCTClient_HIV_TOT+"</td>"
              + "</tr>"
            + "<tr>"
              + "<td rowspan=\"4\">2. No of couples</td>"
                      + "<td>Counselled</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td  style=\"text-align:center;\">"+VCTPartner_Couns_TOT+"</td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Tested</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td  style=\"text-align:center;\" >"+VCTPartner_Tested_TOT+"</td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Both HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td  style=\"text-align:center;\">"+VCTPartner_HIV_TOT+"</td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>with discordant HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td  style=\"text-align:center;\" >"+VCTPartner_Disc_TOT+"</td>"
                  
              + "</tr>"
             
             +"</table></fieldset>"
              
              
              + ""
              + "";
           VCT_TAB+=vct;
          //  VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=
  // VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
//=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT
  //=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
 
           
           // dtc 
           
       dtc+="";    
           
        dtc+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><p id=\"demo\" hidden=\"true\"></p><b style=\"text-align:center;\"> I: DTC </b></legend>"+
             "<table border=\"1\" frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
                + "<tr>"
              + "<td rowspan=\"2\" colspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"><b>I: DTC </b></td>"
              + "<td colspan=\"2\"  class=\"form-actions\" style=\"text-align:center;\"> <b>Children  </b></td>"
              + "<td colspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"> <b> Adults </b></td>"
              + "<td rowspan=\"2\" class=\"form-actions\" style=\"text-align:center;\"> <b>TOTAL </b></td>"
              + "</tr>"
                + "<tr>"
             
              + "<td  class=\"form-actions\" style=\"text-align:center;\"> <b>F  </b></td>"
              + "<td  class=\"form-actions\" style=\"text-align:center;\"> <b>M </b></td>"
              + "<td  class=\"form-actions\" style=\"text-align:center;\"> <b>F </b></td>"
              + "<td  class=\"form-actions\" style=\"text-align:center;\"> <b>M </b></td>"
              + "</tr>"
              + "<tr>"
              + "<td rowspan=\"2\">1. No. Counselled</td>"
              + "<td>Outpatient</td>"
              + "<td   style=\"text-align:center;\">"+DTCA_Couns_Out_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_Out_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_Out_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_Out_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_Out_Tot+"</td>"
              + "</tr>"
              + "<tr>"
              + ""
              + "<td>Inpatient</td>"
              + "<td  style=\"text-align:center;\" >"+DTCA_Couns_In_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_In_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_In_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_In_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCA_Couns_In_Tot+"</td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">2. No. tested</td><td>Outpatient</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_Out_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_Out_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_Out_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_Out_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_Out_Tot+"</td>"
              + "</tr>"
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_Tot+"</td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">3. No. HIV+</td><td>Outpatient</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_Out_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_Out_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_Out_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_Out_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_Out_Tot+"</td>"
              + "</tr>"  
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_In_CF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_In_CM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_In_AF+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_In_AM+"</td>"
              + "<td  style=\"text-align:center;\">"+DTCC_HIV_In_Tot+"</td>"
              + "</tr>" 
            +"</table></fieldset>"; 
           
           
           DTC_TAB+=dtc;
           
//           DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=
        //DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=
       // DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF
      //  =DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=
      //  DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

           
           
           
           
           
           
         
           FP_TAB+="</div></div></div>";
           MCH_TAB+="</div></div></div>";
           MATERNITY_TAB+="</div></div></div>";
           VCT_TAB+="</div></div></div>";
           DTC_TAB+="</div></div></div>";

String str="";
//      HTMLWorker htmlWorker = new HTMLWorker(document);
//     String str = "<html><head></head><body>"+header+""+FP_TAB+" </br></br> "+MCH_TAB+" </br></br> "+MATERNITY_TAB+"</br></br>  "+VCT_TAB+"  </br></br></br></br> "+DTC_TAB+" </body></html>";
  str+="<table border=\"1\" style=\"color:black; font-size:6px;\" width=\"90%\">"
               + "<tr>"
               + "<td>"+header+"</td>"
               + "</tr>"
               + "<tr>"
               + "<td>"+FP_TAB+"</td>"
              + "</tr>"
              + "<tr>"
               + "<td>"+MCH_TAB+"</td>"
              + "</tr>"
                + "<tr>"
              + "<td>"+MATERNITY_TAB+"</td>"
               + "</tr>"
                + "<tr>"
               + "<td>"+VCT_TAB+"</td>"
               + "</tr>"
               + "<tr>"
               + "<td>"+DTC_TAB+"</td>"
               + "</tr>"
                
               + "</table>"
               + "";
        System.out.println( "PDF Created!" );
           Document document = new Document();
  ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
PdfWriter.getInstance(document, outByteStream);
      document.open();
      
      HTMLWorker htmlWorker = new HTMLWorker(document);
       htmlWorker.parse(new StringReader(str));
       
       CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);  
cssResolver.addCss("", true);

   document.close();    
response.setContentType("application/pdf");
response.setContentLength(outByteStream.size());
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=MOH711.pdf");

ServletOutputStream sos;
sos = response.getOutputStream();
outByteStream.writeTo(sos);
sos.flush();
       
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
                    try {
                        processRequest(request, response);
                    } catch (CssResolverException ex) {
                        Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
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
                    try {
                        processRequest(request, response);
                    } catch (CssResolverException ex) {
                        Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
                    }
        } catch (SQLException ex) {
            Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(moh711_StaticReport.class.getName()).log(Level.SEVERE, null, ex);
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

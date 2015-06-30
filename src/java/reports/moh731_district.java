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
import database.dbConn;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




/**
 *
 * @author Geofrey Nyabuto
 */
public class moh731_district extends HttpServlet {
    HttpSession session;
String data,id;
String facilityId,year,month;
//HttpSession session;
//String data,id;
//String facilityId,year,month;

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
        String subcountyid="";
         String invalidFPTXT,invalidPMTCTTXT,invalidMATTXT,invalidHTCTXT="";
          int expectedFP=0;
 int expectedPMTCT=0;
 int expectedMAT=0;
 int expectedHTC=0;
 int validPMTCT=0;
 int invalidPMTCT=0;
 int totalPMTCT=0;

 int   validFP,invalidFP,totalFP,validMAT,invalidMAT,totalMAT,validHTC,invalidHTC,totalHTC;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException, DocumentException {
       session=request.getSession();
       dbConn conn = new dbConn();
 String validitychecker="";
            session=request.getSession();
            String FP_TAB="";
            String MCH_TAB="";
            String MATERNITY_TAB="";
            String VCT_TAB="";
            String DTC_TAB="";
               String enterdby="";
//           if(session.getAttribute("forms_holder")!=null && !(session.getAttribute("forms_holder").toString().equals(","))){
           data="";
//           if(session.getAttribute("year")!=null){        
//   year=session.getAttribute("year").toString();
   year="2015";
//    }
//      if(session.getAttribute("monthid")!=null){        
//   month=session.getAttribute("monthid").toString();
//    }
   month="4";
   
//        if(session.getAttribute("facilityid")!=null){        
//   facilityId=session.getAttribute("facilityid").toString();
//    }
   facilityId="403";
//        if(session.getAttribute("subcountyid")!=null){        
//   subcountyid=session.getAttribute("subcountyid").toString();
//    }
        
        subcountyid="8";
    id=year+"_"+month+"_"+facilityId; 
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
     isValidated="";
 validity="";
  expectedFP=0;
  expectedPMTCT=0;
  expectedMAT=0;
  expectedHTC=0;
  validPMTCT=0;
  invalidPMTCT=0;
  totalPMTCT=0;
    validFP=invalidFP=totalFP=validMAT=invalidMAT=totalMAT=validHTC=invalidHTC=totalHTC;
 String getExpectedForms="SELECT SUM(FP),SUM(PMTCT),SUM(Maternity),SUM(HTC) FROM subpartnera WHERE subpartnera.DistrictID='"+subcountyid+"'" ;
   conn.rs1=conn.st1.executeQuery(getExpectedForms);
   if(conn.rs1.next()==true){
//       System.out.println("pmtct : "+conn.rs1.getString(1)+"  care : "+conn.rs1.getInt(2)+" pep : "+conn.rs1.getInt(3));
           expectedFP=conn.rs1.getInt(1);
           expectedPMTCT=conn.rs1.getInt(2);
           expectedMAT=conn.rs1.getInt(3);
           expectedHTC=conn.rs1.getInt(4);
   }
    validPMTCT=invalidPMTCT=totalPMTCT=0;
     validFP=invalidFP=totalFP=0;
     validMAT=invalidMAT=totalMAT=0;
     validHTC=invalidHTC=totalHTC=0;
     
        String getEntered="SELECT moh711.isValidated,SUM(subpartnera.FP),SUM(subpartnera.PMTCT),SUM(subpartnera.Maternity),SUM(subpartnera.HTC)"
            + " FROM subpartnera JOIN moh711 ON subpartnera.SubPartnerID=moh711.SubPartnerID WHERE "
            + "moh711.Mois='"+month+"' AND moh711.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY moh711.isValidated";
    conn.rs1=conn.st1.executeQuery(getEntered);
    while(conn.rs1.next()){
        System.out.println("isvalidated : "+conn.rs1.getInt(1)+"  num : "+conn.rs1.getInt(2));
   if(conn.rs1.getInt(1)==1){
    validFP=conn.rs1.getInt(2);
    validPMTCT=conn.rs1.getInt(3);
    validMAT=conn.rs1.getInt(4);
    validHTC=conn.rs1.getInt(5);
   }
   if(conn.rs1.getInt(1)==0){
      invalidFP=conn.rs1.getInt(2);
    invalidPMTCT=conn.rs1.getInt(3);
    invalidMAT=conn.rs1.getInt(4);
    invalidHTC=conn.rs1.getInt(5);
      
   }
    }
    totalFP=validFP+invalidFP;
    totalPMTCT=validPMTCT+invalidPMTCT;
    totalMAT=validMAT+invalidMAT;
    totalHTC=validHTC+invalidHTC;

   
  invalidFPTXT=" Unvalidated Form(s) : 0";
  invalidPMTCTTXT=" Unvalidated Form(s) : 0";
 invalidMATTXT=" Unvalidated Form(s) : 0";
 invalidHTCTXT=" Unvalidated Form(s) : 0";
 
    if(invalidFP>0){
   invalidFPTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidFP+"</span></button>";
    }
    if(invalidPMTCT>0){
   invalidPMTCTTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidPMTCT+"</span></button>";
    }
   
    if(invalidMAT>0){
  invalidMATTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidMAT+"</span></button>";       
    }
    
    if(invalidHTC>0){
  invalidHTCTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidHTC+"</span></button>";       
    }
    
 

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

           
          String checker="SELECT * FROM moh711 WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
              
              System.out.println("Data already exist loading............................");
              
              
  
          if(conn.rs.getString("FPMicrolutN")!=null){FPMicrolutN=conn.rs.getString("FPMicrolutN");}
          else if (FPMicrolutN==null){FPMicrolutN="";}
          
  FPMicrolutR=conn.rs.getString("FPMicrolutR");
  if(FPMicrolutR==null){FPMicrolutR="";}
  
  if(conn.rs.getString("FPMicrolutT")!=null){FPMicrolutT=conn.rs.getString("FPMicrolutT");}else{FPMicrolutT="";}
  if(conn.rs.getString("FPMicrogynonN")!=null){FPMicrogynonN=conn.rs.getString("FPMicrogynonN");}else{FPMicrogynonN="";}
  if(conn.rs.getString("FPMicrogynonR")!=null){FPMicrogynonR=conn.rs.getString("FPMicrogynonR");}else{FPMicrogynonR="";}
  if(conn.rs.getString("FPMicrogynonT")!=null){FPMicrogynonT=conn.rs.getString("FPMicrogynonT");}else{FPMicrogynonT="";}
  if(conn.rs.getString("FPINJECTIONSN")!=null){FPINJECTIONSN=conn.rs.getString("FPINJECTIONSN");}else{FPINJECTIONSN="";}
  if(conn.rs.getString("FPINJECTIONSR")!=null){FPINJECTIONSR=conn.rs.getString("FPINJECTIONSR");}else{FPINJECTIONSR="";}
  if(conn.rs.getString("FPINJECTIONST")!=null){FPINJECTIONST=conn.rs.getString("FPINJECTIONST");}else{FPINJECTIONST="";}
  if(conn.rs.getString("FPIUCDN")!=null){FPIUCDN=conn.rs.getString("FPIUCDN");}else{FPIUCDN="";}
  if(conn.rs.getString("FPIUCDR")!=null){FPIUCDR=conn.rs.getString("FPIUCDR");}else{FPIUCDR="";}
  if(conn.rs.getString("FPIUCDT")!=null){FPIUCDT=conn.rs.getString("FPIUCDT");}else{FPIUCDT="";}
  if(conn.rs.getString("FPIMPLANTSN")!=null){FPIMPLANTSN=conn.rs.getString("FPIMPLANTSN");}else{FPIMPLANTSN="";}
  if(conn.rs.getString("FPIMPLANTSR")!=null){FPIMPLANTSR=conn.rs.getString("FPIMPLANTSR");}else{FPIMPLANTSR="";}
  if(conn.rs.getString("FPIMPLANTST")!=null){FPIMPLANTST=conn.rs.getString("FPIMPLANTST");}else{FPIMPLANTST="";}
  if(conn.rs.getString("FPBTLN")!=null){FPBTLN=conn.rs.getString("FPBTLN");}else{FPBTLN="";}
  if(conn.rs.getString("FPBTLR")!=null){FPBTLR=conn.rs.getString("FPBTLR");}else{FPBTLR="";}
  if(conn.rs.getString("FPBTLT")!=null){FPBTLT=conn.rs.getString("FPBTLT");}else{FPBTLT="";}
  if(conn.rs.getString("FPVasectomyN")!=null){FPVasectomyN=conn.rs.getString("FPVasectomyN");}else{FPVasectomyN="";}
  if(conn.rs.getString("FPVasectomyR")!=null){FPVasectomyR=conn.rs.getString("FPVasectomyR");}else{FPVasectomyR="";}
  if(conn.rs.getString("FPVasectomyT")!=null){FPVasectomyT=conn.rs.getString("FPVasectomyT");}else{FPVasectomyT="";}
  if(conn.rs.getString("FPCONDOMSN")!=null){FPCONDOMSN=conn.rs.getString("FPCONDOMSN");}else{FPCONDOMSN="";}
  if(conn.rs.getString("FPCONDOMSR")!=null){FPCONDOMSR=conn.rs.getString("FPCONDOMSR");}else{FPCONDOMSR="";}
  if(conn.rs.getString("FPCONDOMST")!=null){FPCONDOMST=conn.rs.getString("FPCONDOMST");}else{FPCONDOMST="";}
         
  if(conn.rs.getString("FPOTHERN")!=null){FPOTHERN=conn.rs.getString("FPOTHERN");}else{FPOTHERN="";}
  if(conn.rs.getString("FPOTHERR")!=null){FPOTHERR=conn.rs.getString("FPOTHERR");}else{FPOTHERR="";}
  if(conn.rs.getString("FPOTHERT")!=null){FPOTHERT=conn.rs.getString("FPOTHERT");}else{FPOTHERT="";}
         
  if(conn.rs.getString("FPCLIENTSN")!=null){FPCLIENTSN=conn.rs.getString("FPCLIENTSN");}else{
  FPCLIENTSN="";}
  if(conn.rs.getString("FPCLIENTSR")!=null){FPCLIENTSR=conn.rs.getString("FPCLIENTSR");}else{
  FPCLIENTSR="";
  }
  if(conn.rs.getString("FPCLIENTST")!=null){FPCLIENTST=conn.rs.getString("FPCLIENTST");}
  else{FPCLIENTST="";
  }
  if(conn.rs.getString("FPIUCDRemoval")!=null){FPIUCDRemoval=conn.rs.getString("FPIUCDRemoval");}else{FPIUCDRemoval="";}
  if(conn.rs.getString("FPIMPLANTSRemoval")!=null){FPIMPLANTSRemoval=conn.rs.getString("FPIMPLANTSRemoval");}else{FPIMPLANTSRemoval="";}
         
          
          
          
          // mch 
 
   if(conn.rs.getString("PMCTA_1stVisit_ANC")!=null){PMCTA_1stVisit_ANC=conn.rs.getString("PMCTA_1stVisit_ANC");}else{PMCTA_1stVisit_ANC="";}
  if(conn.rs.getString("PMCTA_ReVisit_ANC")!=null){PMCTA_ReVisit_ANC=conn.rs.getString("PMCTA_ReVisit_ANC");}else{PMCTA_ReVisit_ANC="";}
  if(conn.rs.getString("PMCTANCClientsT")!=null){PMCTANCClientsT=conn.rs.getString("PMCTANCClientsT");}else{PMCTANCClientsT="";}
  if(conn.rs.getString("PMCTHB7")!=null){PMCTHB7=conn.rs.getString("PMCTHB7");}else{PMCTHB7="";}
  if(conn.rs.getString("PMCTIPT1")!=null){PMCTIPT1=conn.rs.getString("PMCTIPT1");}else{PMCTIPT1="";}
  if(conn.rs.getString("PMCTIPT2")!=null){PMCTIPT2=conn.rs.getString("PMCTIPT2");}else{PMCTIPT2="";}
  if(conn.rs.getString("PMCTANCClients4")!=null){PMCTANCClients4=conn.rs.getString("PMCTANCClients4");}else{PMCTANCClients4="";}
  if(conn.rs.getString("PMCTITN")!=null){PMCTITN=conn.rs.getString("PMCTITN");}else{PMCTITN="";}
  

  if(conn.rs.getString("MATNormalDelivery")!=null){MATNormalDelivery=conn.rs.getString("MATNormalDelivery");}else{MATNormalDelivery="";}
  if(conn.rs.getString("MATCSection")!=null){MATCSection=conn.rs.getString("MATCSection");}else{MATCSection="";}
  if(conn.rs.getString("MATBreech")!=null){MATBreech=conn.rs.getString("MATBreech");}else{MATBreech="";}
  if(conn.rs.getString("MATAssistedVag")!=null){MATAssistedVag=conn.rs.getString("MATAssistedVag");}else{MATAssistedVag="";}
  if(conn.rs.getString("MATDeliveryT")!=null){MATDeliveryT=conn.rs.getString("MATDeliveryT");}else{MATDeliveryT="";}
  if(conn.rs.getString("MATLiveBirth")!=null){MATLiveBirth=conn.rs.getString("MATLiveBirth");}else{MATLiveBirth="";}
  if(conn.rs.getString("MATStillBirth")!=null){MATStillBirth=conn.rs.getString("MATStillBirth");}else{MATStillBirth="";}
  if(conn.rs.getString("FPBTLT")!=null){FPBTLT=conn.rs.getString("FPBTLT");}else{FPBTLT="";}
  if(conn.rs.getString("MATWeight2500")!=null){MATWeight2500=conn.rs.getString("MATWeight2500");}else{MATWeight2500="";}
  if(conn.rs.getString("MATPreTerm")!=null){MATPreTerm=conn.rs.getString("MATPreTerm");}else{MATPreTerm="";}
  if(conn.rs.getString("MATDischargealive")!=null){MATDischargealive=conn.rs.getString("MATDischargealive");}else{MATDischargealive="";}
  if(conn.rs.getString("MATReferral")!=null){MATReferral=conn.rs.getString("MATReferral");}else{MATReferral="";}
  if(conn.rs.getString("MATNeoNatalD")!=null){MATNeoNatalD=conn.rs.getString("MATNeoNatalD");}else{MATNeoNatalD="";}
  if(conn.rs.getString("MATMaternalD")!=null){MATMaternalD=conn.rs.getString("MATMaternalD");}else{MATMaternalD="";}
  if(conn.rs.getString("MATAPHAlive")!=null){MATAPHAlive=conn.rs.getString("MATAPHAlive");}else{MATAPHAlive="";}
  if(conn.rs.getString("MATAPHDead")!=null){MATAPHDead=conn.rs.getString("MATAPHDead");}else{MATAPHDead="";}
  if(conn.rs.getString("MATPPHAlive")!=null){MATPPHAlive=conn.rs.getString("MATPPHAlive");}else{MATPPHAlive="";}
  if(conn.rs.getString("MATPPHDead")!=null){MATPPHDead=conn.rs.getString("MATPPHDead");}else{MATPPHDead="";}
  if(conn.rs.getString("MATEclampAlive")!=null){MATEclampAlive=conn.rs.getString("MATEclampAlive");}else{MATEclampAlive="";}
  if(conn.rs.getString("MATEclampDead")!=null){MATEclampDead=conn.rs.getString("MATEclampDead");}else{MATEclampDead="";}
  if(conn.rs.getString("MATRupUtAlive")!=null){MATRupUtAlive=conn.rs.getString("MATRupUtAlive");}else{MATRupUtAlive="";}
  if(conn.rs.getString("MATRupUtDead")!=null){MATRupUtDead=conn.rs.getString("MATRupUtDead");}else{MATRupUtDead="";}
  if(conn.rs.getString("MATObstrLaborAlive")!=null){MATObstrLaborAlive=conn.rs.getString("MATObstrLaborAlive");}else{MATObstrLaborAlive="";}
  if(conn.rs.getString("MATObstrLaborDead")!=null){MATObstrLaborDead=conn.rs.getString("MATObstrLaborDead");}else{MATObstrLaborDead="";}
  if(conn.rs.getString("MATSepsisAlive")!=null){MATSepsisAlive=conn.rs.getString("MATSepsisAlive");}else{MATSepsisAlive="";}
  if(conn.rs.getString("MATSepsisDead")!=null){MATSepsisDead=conn.rs.getString("MATSepsisDead");}else{MATSepsisDead="";}
  if(conn.rs.getString("VCTClient_Couns_CM")!=null){VCTClient_Couns_CM=conn.rs.getString("VCTClient_Couns_CM");}else{VCTClient_Couns_CM="";}
  if(conn.rs.getString("VCTClient_Couns_CF")!=null){VCTClient_Couns_CF=conn.rs.getString("VCTClient_Couns_CF");}else{VCTClient_Couns_CF="";}
  if(conn.rs.getString("VCTClient_Couns_AM")!=null){VCTClient_Couns_AM=conn.rs.getString("VCTClient_Couns_AM");}else{VCTClient_Couns_AM="";}
  if(conn.rs.getString("VCTClient_Couns_AF")!=null){VCTClient_Couns_AF=conn.rs.getString("VCTClient_Couns_AF");}else{VCTClient_Couns_AF="";}
  if(conn.rs.getString("VCTClient_Couns_TOT")!=null){VCTClient_Couns_TOT=conn.rs.getString("VCTClient_Couns_TOT");}else{VCTClient_Couns_TOT="";}
  if(conn.rs.getString("VCTClient_Tested_CM")!=null){VCTClient_Tested_CM=conn.rs.getString("VCTClient_Tested_CM");}else{VCTClient_Tested_CM="";}
  if(conn.rs.getString("VCTClient_Tested_CF")!=null){VCTClient_Tested_CF=conn.rs.getString("VCTClient_Tested_CF");}else{VCTClient_Tested_CF="";}
  if(conn.rs.getString("VCTClient_Tested_AM")!=null){VCTClient_Tested_AM=conn.rs.getString("VCTClient_Tested_AM");}else{VCTClient_Tested_AM="";}
  if(conn.rs.getString("VCTClient_Tested_AF")!=null){VCTClient_Tested_AF=conn.rs.getString("VCTClient_Tested_AF");}else{VCTClient_Tested_AF="";}
  if(conn.rs.getString("VCTClient_Tested_TOT")!=null){VCTClient_Tested_TOT=conn.rs.getString("VCTClient_Tested_TOT");}else{VCTClient_Tested_TOT="";}
  if(conn.rs.getString("VCTClient_HIV_CM")!=null){VCTClient_HIV_CM=conn.rs.getString("VCTClient_HIV_CM");}else{VCTClient_HIV_CM="";}
  if(conn.rs.getString("VCTClient_HIV_CF")!=null){VCTClient_HIV_CF=conn.rs.getString("VCTClient_HIV_CF");}else{VCTClient_HIV_CF="";}
  if(conn.rs.getString("VCTClient_HIV_AM")!=null){VCTClient_HIV_AM=conn.rs.getString("VCTClient_HIV_AM");}else{VCTClient_HIV_AM="";}
  if(conn.rs.getString("VCTClient_HIV_AF")!=null){VCTClient_HIV_AF=conn.rs.getString("VCTClient_HIV_AF");}else{VCTClient_HIV_AF="";}
  if(conn.rs.getString("VCTClient_HIV_TOT")!=null){VCTClient_HIV_TOT=conn.rs.getString("VCTClient_HIV_TOT");}else{VCTClient_HIV_TOT="";}
  if(conn.rs.getString("VCTPartner_Couns_TOT")!=null){VCTPartner_Couns_TOT=conn.rs.getString("VCTPartner_Couns_TOT");}else{VCTPartner_Couns_TOT="";}
  if(conn.rs.getString("VCTPartner_Tested_TOT")!=null){VCTPartner_Tested_TOT=conn.rs.getString("VCTPartner_Tested_TOT");}else{VCTPartner_Tested_TOT="";}
  if(conn.rs.getString("VCTPartner_HIV_TOT")!=null){VCTPartner_HIV_TOT=conn.rs.getString("VCTPartner_HIV_TOT");}else{VCTPartner_HIV_TOT="";}
  if(conn.rs.getString("VCTPartner_Disc_TOT")!=null){VCTPartner_Disc_TOT=conn.rs.getString("VCTPartner_Disc_TOT");}else{VCTPartner_Disc_TOT="";}
  
  
  
//  DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=
  //DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot
  //=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

  
  //dtc
  if(conn.rs.getString("DTCA_Couns_In_CM")!=null){DTCA_Couns_In_CM=conn.rs.getString("DTCA_Couns_In_CM");}else{DTCA_Couns_In_CM="";}
  if(conn.rs.getString("DTCA_Couns_In_CF")!=null){DTCA_Couns_In_CF=conn.rs.getString("DTCA_Couns_In_CF");}else{DTCA_Couns_In_CF="";}
  if(conn.rs.getString("DTCA_Couns_In_AM")!=null){DTCA_Couns_In_AM=conn.rs.getString("DTCA_Couns_In_AM");}else{DTCA_Couns_In_AM="";}
  if(conn.rs.getString("DTCA_Couns_In_AF")!=null){DTCA_Couns_In_AF=conn.rs.getString("DTCA_Couns_In_AF");}else{DTCA_Couns_In_AF="";}
  if(conn.rs.getString("DTCA_Couns_In_Tot")!=null){DTCA_Couns_In_Tot=conn.rs.getString("DTCA_Couns_In_Tot");}else{DTCA_Couns_In_Tot="";}
  if(conn.rs.getString("DTCA_Couns_Out_CM")!=null){DTCA_Couns_Out_CM=conn.rs.getString("DTCA_Couns_Out_CM");}else{DTCA_Couns_Out_CM="";}
  if(conn.rs.getString("DTCA_Couns_Out_CF")!=null){DTCA_Couns_Out_CF=conn.rs.getString("DTCA_Couns_Out_CF");}else{DTCA_Couns_Out_CF="";}
  if(conn.rs.getString("DTCA_Couns_Out_AM")!=null){DTCA_Couns_Out_AM=conn.rs.getString("DTCA_Couns_Out_AM");}else{DTCA_Couns_Out_AM="";}
  if(conn.rs.getString("DTCA_Couns_Out_AF")!=null){DTCA_Couns_Out_AF=conn.rs.getString("DTCA_Couns_Out_AF");}else{DTCA_Couns_Out_AF="";}
  if(conn.rs.getString("DTCA_Couns_Out_Tot")!=null){DTCA_Couns_Out_Tot=conn.rs.getString("DTCA_Couns_Out_Tot");}else{DTCA_Couns_Out_Tot="";}
  if(conn.rs.getString("DTCB_Test_In_CM")!=null){DTCB_Test_In_CM=conn.rs.getString("DTCB_Test_In_CM");}else{DTCB_Test_In_CM="";}
  if(conn.rs.getString("DTCB_Test_In_CF")!=null){DTCB_Test_In_CF=conn.rs.getString("DTCB_Test_In_CF");}else{DTCB_Test_In_CF="";}
  if(conn.rs.getString("DTCB_Test_In_AM")!=null){DTCB_Test_In_AM=conn.rs.getString("DTCB_Test_In_AM");}else{DTCB_Test_In_AM="";}
  if(conn.rs.getString("DTCB_Test_In_AF")!=null){DTCB_Test_In_AF=conn.rs.getString("DTCB_Test_In_AF");}else{DTCB_Test_In_AF="";}
  if(conn.rs.getString("DTCB_Test_In_Tot")!=null){DTCB_Test_In_Tot=conn.rs.getString("DTCB_Test_In_Tot");}else{DTCB_Test_In_Tot="";}
  if(conn.rs.getString("DTCB_Test_Out_CM")!=null){DTCB_Test_Out_CM=conn.rs.getString("DTCB_Test_Out_CM");}else{DTCB_Test_Out_CM="";}
  if(conn.rs.getString("DTCB_Test_Out_CF")!=null){DTCB_Test_Out_CF=conn.rs.getString("DTCB_Test_Out_CF");}else{DTCB_Test_Out_CF="";}
  if(conn.rs.getString("DTCB_Test_Out_AM")!=null){DTCB_Test_Out_AM=conn.rs.getString("DTCB_Test_Out_AM");}else{DTCB_Test_Out_AM="";}
  if(conn.rs.getString("DTCB_Test_Out_AF")!=null){DTCB_Test_Out_AF=conn.rs.getString("DTCB_Test_Out_AF");}else{DTCB_Test_Out_AF="";}
  if(conn.rs.getString("DTCB_Test_Out_Tot")!=null){DTCB_Test_Out_Tot=conn.rs.getString("DTCB_Test_Out_Tot");}else{DTCB_Test_Out_Tot="";}
  if(conn.rs.getString("DTCC_HIV_In_CM")!=null){DTCC_HIV_In_CM=conn.rs.getString("DTCC_HIV_In_CM");}else{DTCC_HIV_In_CM="";}
  if(conn.rs.getString("DTCC_HIV_In_CF")!=null){DTCC_HIV_In_CF=conn.rs.getString("DTCC_HIV_In_CF");}else{DTCC_HIV_In_CF="";}
  if(conn.rs.getString("DTCC_HIV_In_AM")!=null){DTCC_HIV_In_AM=conn.rs.getString("DTCC_HIV_In_AM");}else{DTCC_HIV_In_AM="";}
  if(conn.rs.getString("DTCC_HIV_In_AF")!=null){DTCC_HIV_In_AF=conn.rs.getString("DTCC_HIV_In_AF");}else{DTCC_HIV_In_AF="";}
  if(conn.rs.getString("DTCC_HIV_In_Tot")!=null){DTCC_HIV_In_Tot=conn.rs.getString("DTCC_HIV_In_Tot");}else{DTCC_HIV_In_Tot="";}
  if(conn.rs.getString("DTCC_HIV_Out_CM")!=null){DTCC_HIV_Out_CM=conn.rs.getString("DTCC_HIV_Out_CM");}else{DTCC_HIV_Out_CM="";}
  if(conn.rs.getString("DTCC_HIV_Out_CF")!=null){DTCC_HIV_Out_CF=conn.rs.getString("DTCC_HIV_Out_CF");}else{DTCC_HIV_Out_CF="";}
  if(conn.rs.getString("DTCC_HIV_Out_AM")!=null){DTCC_HIV_Out_AM=conn.rs.getString("DTCC_HIV_Out_AM");}else{DTCC_HIV_Out_AM="";}
  if(conn.rs.getString("DTCC_HIV_Out_AF")!=null){DTCC_HIV_Out_AF=conn.rs.getString("DTCC_HIV_Out_AF");}else{DTCC_HIV_Out_AF="";}
  if(conn.rs.getString("DTCC_HIV_Out_Tot")!=null){DTCC_HIV_Out_Tot=conn.rs.getString("DTCC_HIV_Out_Tot");}else{DTCC_HIV_Out_Tot="";}
  
     if(conn.rs.getString("isValidated")!=null){      
    isValidated=conn.rs.getString("isValidated");
     }
     else{isValidated="";}
        //get the name of the person who entered the form 

        String enterer = "select * from user where userid='" + conn.rs.getString("userid") + "'";
System.out.println(enterer);
        conn.rs1 = conn.st1.executeQuery(enterer);
        //add details of person who entered
        if (conn.rs1.next()) {
            enterdby = "<font color='green'>Data 1st entered by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("timestamp") + "</b></font>";
        }


        //now check if form was updated and if its one month after data entry

        if (conn.rs.getString("updatedOn") != null) {
            //get difference in months between entered date and updated date
            String compdate = "SELECT TIMESTAMPDIFF(MONTH,'" + conn.rs.getString("timestamp") + "','" + conn.rs.getString("updatedOn") + "')";
            conn.rs2 = conn.st2.executeQuery(compdate);
            if (conn.rs2.next()) {
                //now get the details of the person who updated the form
                //if the difference is greater than or equal to one, 


                if (conn.rs2.getInt(1) >= 1) {
                    String updater = "select * from user where userid='" + conn.rs.getString("updatedBy") + "'";

                    conn.rs1 = conn.st1.executeQuery(updater);
                    //add details of person who entered
                    if (conn.rs1.next()) {
                        enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
                    }
                } //end of if month >=1 
            }//end of date comparison if 

        }//end of if updated !=null
   System.out.println("entry by "+enterdby);
          
    }
       System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
  validity="<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
}
      else if(isValidated.equals("1")){
   validity="<font color=\"green\"><b>Form Validated.<img style=\"margin-left:10px;\" src=\"images/validated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;  
}
      else{
     
        validity="<font color=\"blue\"><b>New Entry</b></font>"  ;          
              } 
            FamilyPlanninng=pmct=maternity=vct=dtc="";    
          FP_TAB+="";
          
          validitychecker+="<p id=\"checkValidity\" hidden=\"hidden\">"+validity+"</p>";
//          FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR=
//FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="";
//FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
//FPIMPLANTSRemoval="";"
      FamilyPlanninng=""
              +  "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> FAMILY PLANNING </b></legend>"
              + "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
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
              + "<td >7. ALL OTHERS:(Specify)</td>"
           
              + "<td >"+FPOTHERN+"</td>"
              + "<td >"+FPOTHERR+"</td>"
              + "<td >"+FPOTHERT+"</td>"
              + "</tr>"
              
                 + "<tr>"
              + "<td >8. TOTAL NO. OF CLIENTS</td>"
              + "<td  ></td>"
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
                   + "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
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
               + "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
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
             "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"2\" class=\"form-actions\"><b></b></td>"
              + "<td  colspan=\"2\" class=\"form-actions\"> <b>NUMBER  </b></td>"
               + "</tr>"
                  + "<tr>"
              + "<td>1.</td><td >Normal Deliveries </td>"
                   + "<td  colspan=\"2\">"+MATNormalDelivery+"</td>"
              + "</tr>"
               + "<tr>"
               + " <td>2.</td>   <td > Caesarian Sections </td>"
                   + "<td  colspan=\"2\">"+MATCSection+"</td>"
              + "</tr>"
               + "<tr>"
               + "  <td>3.</td>  <td > Breech Delivery </td>"
                   + "<td  colspan=\"2\">"+MATBreech+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>4.</td> <td > Assisted vaginal delivery </td>"
                   + "<td  colspan=\"2\">"+MATAssistedVag+"</td>"
              + "</tr>"
               + "<tr>"
               + "<td>5.</td>    <td > TOTAL DELIVERIES</td>"
                   + "<td  colspan=\"2\">"+MATDeliveryT+"</td>"
              + "</tr>"
               
               + "<tr>"
               + "   <td>6.</td> <td > Live Births </td>"
                   + "<td  colspan=\"2\">"+MATLiveBirth+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>7.</td> <td > Still Births </td>"
                   + "<td  colspan=\"2\">"+MATStillBirth+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>8.</td> <td > Under Weight Babies (Weight below 2500 grams) </td>"
                   + "<td  colspan=\"2\">"+MATWeight2500+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>9.</td> <td > Pre-Term babies  </td>"
                   + "<td  colspan=\"2\">"+MATPreTerm+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>10.</td> <td > No of babies discharged alive </td>"
                   + "<td style=\"padding-right:20px;\" colspan=\"2\" >"+MATDischargealive+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>11.</td> <td > Referrals </td>"
                   + "<td  colspan=\"2\">"+MATReferral+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>12.</td> <td > Neonatal Deaths </td>"
                   + "<td  colspan=\"2\">"+MATNeoNatalD+"</td>"
              + "</tr>"
               + "<tr>"
               + "   <td>13.</td> <td  > Maternal Deaths </td>"
                   + "<td  colspan=\"2\">"+MATMaternalD+"</td>"
              + "</tr>"
               + "<tr>"
               + "    <td  colspan=\"2\" class=\"form-actions\"><b> Maternal Complications </b></td>"
                   + "<td  class=\"form-actions\"><b>Alive </b></td>"
                   + "<td class=\"form-actions\" ><b>Dead </b></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>14.</td> <td > A.P.H. (Ante Partum Haemorrhage) </td>"
                   + "<td >"+MATAPHAlive+"</td>"
                   + "<td >"+MATAPHDead+"</td>"
              + "</tr>"
             + ""
              + "<tr>"
               + "   <td>15.</td> <td > P.P.H. (Post Partum Haemorrhage) </td>"
                   + "<td >"+MATPPHAlive+"</td>"
                   + "<td >"+MATPPHDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>16.</td> <td > Eclampsia</td>"
                   + "<td >"+MATEclampAlive+"</td>"
                   + "<td >"+MATEclampDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>17.</td> <td > Ruptured Uterus</td>"
                   + "<td >"+MATRupUtAlive+"</td>"
                   + "<td >"+MATRupUtDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>18.</td> <td > Obstructed Labour</td>"
                   + "<td >"+MATObstrLaborAlive+"</td>"
                   + "<td >"+MATObstrLaborDead+"</td>"
              + "</tr>"
              + "<tr>"
               + "   <td>19.</td> <td > Sepsis</td>"
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
             "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
          + "<tr>"
              + "<td rowspan=\"2\" colspan=\"3\" class=\"form-actions\"><b></b></td>"
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
              + "<td rowspan=\"3\">1.</td><td rowspan=\"3\">VCT Clients</td>"
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
              + "<td rowspan=\"4\">2.</td><td rowspan=\"4\">No of couples</td>"
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
             "<table frame=\"box\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
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
              + "<td  style=\"text-align:center;\">"+DTCB_Test_In_CM+"></td>"
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

                 
//           System.out.println("aaaaa"+FP_TAB);//}
//System.out.println("aaaaa"+FP_TAB);
   
   try {
      Document document = new Document(PageSize.LETTER);
      PdfWriter.getInstance(document, new FileOutputStream("D://testpdf2.pdf"));
      document.open();
      document.addAuthor("Real Gagnon");
      document.addCreator("Real's HowTo");
      document.addSubject("Thanks for your support");
      document.addCreationDate();
      document.addTitle("Please read this");

      HTMLWorker htmlWorker = new HTMLWorker(document);
      String str = "<html><head></head><body>"+FP_TAB+"  "+MCH_TAB+"  "+MATERNITY_TAB+"  "+VCT_TAB+"   "+DTC_TAB+" </body></html>";
      
      
      
      
      
      
      
      
      
      htmlWorker.parse(new StringReader(str));
      document.close();
      System.out.println("Done");
      }
    catch (Exception e) {
      e.printStackTrace();
    }
        System.out.println( "PDF Created!" );
       
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
            Logger.getLogger(moh731_district.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(moh731_district.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(moh731_district.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(moh731_district.class.getName()).log(Level.SEVERE, null, ex);
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

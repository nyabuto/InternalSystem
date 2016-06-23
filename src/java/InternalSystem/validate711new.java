/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Emmanuel Kaunda
 */
public class validate711new extends HttpServlet {
HttpSession session=null;

String VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;
String DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,Userid;
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       String data_elements,description=""; 
    response.setContentType("text/html;charset=UTF-8");
  try{
    session =request.getSession();
        PrintWriter out = response.getWriter();
    
//    String col=request.getParameter("col");
//    String achieved=request.getParameter("achieved");
//    
    
    dbConn conn=new dbConn();
//get the existing data for the month, year and facility that is already on session

String month="";      
String year="";      
String facil="";
String userid="unknown";
 String error="";

//        if(achieved.equals("")){achieved="0";}
    if(session.getAttribute("userid")!=null){        
userid=session.getAttribute("userid").toString();
}

if(session.getAttribute("year")!=null){        
year=session.getAttribute("year").toString();
}
  if(session.getAttribute("monthid")!=null){        
month=session.getAttribute("monthid").toString();
}

    if(session.getAttribute("facilityid")!=null){        
facil=session.getAttribute("facilityid").toString();
}
     data_elements=request.getParameter("data_elements");
  description=request.getParameter("description");
    

  
String FPProgestinN,FPProgestinR,FPProgestinT,FPCocN,FPCocR,FPCocT,FPEcpN,FPEcpR,FPEcpT,FPINJECTABLESN,FPINJECTABLESR,FPINJECTABLEST,FPINJECTIONSN,FPINJECTIONSR,FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR,FPVasectomyT,FPCONDOMSMN,FPCONDOMSFN,FPCONDOMST,FPNaturalN,FPNaturalR,FPNaturalT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPADOLESCENT10_14N,FPADOLESCENT10_14R,FPADOLESCENT10_14T,FPADOLESCENT15_19N,FPADOLESCENT15_19R,FPADOLESCENT15_19T,FPADOLESCENT20_24N,FPADOLESCENT20_24R,FPADOLESCENT20_24T,FPIUCDRemoval,FPIMPLANTSRemoval;	
        
String PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTIPT1,PMCTIPT2,PMCTHB11,PMCTANCClients4,PMCTITN1,PMCTITN,PMTCTSYPHILISTES,PMTCTSYPHILISPOS,PMTCTCOUNSELLEDFEED,PMTCTBREAST,PMTCTEXERCISE,PMTCTPREG10_14,PMTCTPREG15_19,PMTCTIRON,PMTCTFOLIC,PMTCTFERROUS;	
                
String MATNormalDelivery,MATCSection,MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATFreshStillBirth,MATMeceratedStillBirth,MATDeformities,MATLowAPGAR,MATWeight2500,MATTetracycline,MATPreTerm,MATDischargealive,MATbreastfeeding1,MATDeliveriesPos,MATNeoNatalD,MATMaternalD10_19,MATMaternalD,MATMaternalDAudited,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead,MATREFFromOtherFacility,MATREFFromCU,MATREFToOtherFacility,MATREFToCU;	
                        
String SGBVRape72_0_9,SGBVRape72_10_17,SGBVRape72_18_49,SGBVRape72_50,SGBVRape72T,SGBVinitPEP0_9,SGBVinitPEP10_17,SGBVinitPEP18_49,SGBVinitPEP50,SGBVinitPEPT,SGBVcompPEP0_9,SGBVcompPEP10_17,SGBVcompPEP18_49,SGBVcompPEP50,SGBVcompPEPT,SGBVPregnant0_9,SGBVPregnant10_17,SGBVPregnant18_49,SGBVPregnant50,SGBVPregnantT,SGBVseroconverting0_9,SGBVseroconverting10_17,SGBVseroconverting18_49,SGBVseroconverting50,SGBVseroconvertingT,SGBVsurvivors0_9,SGBVsurvivors10_17,SGBVsurvivors18_49,SGBVsurvivors50,SGBVsurvivorsT	;
                                
String PAC10_19,PACT;	
                                        
String CHANIS0_5NormalweightF,CHANIS0_5NormalweightM,CHANIS0_5NormalweightT,CHANIS0_5UnderweightF,CHANIS0_5UnderweightM,CHANIS0_5UnderweightT,CHANIS0_5sevUnderweightF,CHANIS0_5sevUnderweightM,CHANIS0_5sevUnderweightT,CHANIS0_5OverweightF,CHANIS0_5OverweightM,CHANIS0_5OverweightT,CHANIS0_5ObeseF,CHANIS0_5ObeseM,CHANIS0_5ObeseT,CHANIS0_5TWF,CHANIS0_5TWM,CHANIS0_5TW,CHANIS6_23NormalweightF,CHANIS6_23NormalweightM,CHANIS6_23NormalweightT,CHANIS6_23UnderweightF,CHANIS6_23UnderweightM,CHANIS6_23UnderweightT,CHANIS6_23sevUnderweightF,CHANIS6_23sevUnderweightM,CHANIS6_23sevUnderweightT,CHANIS6_23OverweightF,CHANIS6_23OverweightM,CHANIS6_23OverweightT,CHANIS6_23ObeseF,CHANIS6_23ObeseM,CHANIS6_23ObeseT,CHANIS6_23TWF,CHANIS6_23TWM,CHANIS6_23TW,CHANIS24_59NormalweightF,CHANIS24_59NormalweightM,CHANIS24_59NormalweightT,CHANIS24_59UnderweightF,CHANIS24_59UnderweightM,CHANIS24_59UnderweightT,CHANIS24_59sevUnderweightF,CHANIS24_59sevUnderweightM,CHANIS24_59sevUnderweightT,CHANIS24_59OverweightF,CHANIS24_59OverweightM,CHANIS24_59OverweightT,CHANIS24_59ObeseF,CHANIS24_59ObeseM,CHANIS24_59ObeseT,CHANIS24_59TWF,CHANIS24_59TWM,CHANIS24_59TW,CHANISMUACNormalF,CHANISMUACNormalM,CHANISMUACNormalT,CHANISMUACModerateF,CHANISMUACModerateM,CHANISMUACModerateT,CHANISMUACSevereF,CHANISMUACSevereM,CHANISMUACSevereT,CHANISMUACMeasuredF,CHANISMUACMeasuredM,CHANISMUACMeasuredT;

String CHANIS0_5NormalHeightF,CHANIS0_5NormalHeightM,CHANIS0_5NormalHeightT,CHANIS0_5StuntedF,CHANIS0_5StuntedM,CHANIS0_5StuntedT,CHANIS0_5sevStuntedF,CHANIS0_5sevStuntedM,CHANIS0_5sevStuntedT,CHANIS0_5TMeasF,CHANIS0_5TMeasM,CHANIS0_5TMeas,CHANIS6_23NormalHeightF,CHANIS6_23NormalHeightM,CHANIS6_23NormalHeightT,CHANIS6_23StuntedF,CHANIS6_23StuntedM,CHANIS6_23StuntedT,CHANIS6_23sevStuntedF,CHANIS6_23sevStuntedM,CHANIS6_23sevStuntedT,CHANIS6_23TMeasF,CHANIS6_23TMeasM,CHANIS6_23TMeas,CHANIS24_59NormalHeightF,CHANIS24_59NormalHeightM,CHANIS24_59NormalHeightT,CHANIS24_59StuntedF,CHANIS24_59StuntedM,CHANIS24_59StuntedT,CHANIS24_59sevStuntedF,CHANIS24_59sevStuntedM,CHANIS24_59sevStuntedT,CHANIS24_59TMeasF,CHANIS24_59TMeasM,CHANIS24_59TMeas,CHANIS0_59NewVisitsF,CHANIS0_59NewVisitsM,CHANIS0_59NewVisitsT,CHANIS0_59KwashiakorF,CHANIS0_59KwashiakorM,CHANIS0_59KwashiakorT,CHANIS0_59MarasmusF,CHANIS0_59MarasmusM,CHANIS0_59MarasmusT,CHANIS0_59FalgrowthF,CHANIS0_59FalgrowthM,CHANIS0_59FalgrowthT,CHANIS0_59F,CHANIS0_59M,CHANIS0_59T,CHANIS0_5EXCLBreastF,CHANIS0_5EXCLBreastM,CHANIS0_5EXCLBreastT,CHANIS12_59DewormedF,CHANIS12_59DewormedM,CHANIS12_59DewormedT,CHANIS6_23MNPsF,CHANIS6_23MNPsM,CHANIS6_23MNPsT,CHANIS0_59DisabilityF,CHANIS0_59DisabilityM,CHANIS0_59DisabilityT;	
  

String CCSSUSPICIOUSLES24,CCSSUSPICIOUSLES25_49,CCSSUSPICIOUSLES50,  CCSVVH24,CCSVVH25_49,CCSVVH50,CCSPAPSMEAR24,CCSPAPSMEAR25_49,CCSPAPSMEAR50,CCSHPV24,CCSHPV25_49,CCSHPV50,CCSVIAVILIPOS24,CCSVIAVILIPOS25_49,CCSVIAVILIPOS50,CCSCYTOLPOS24,CCSCYTOLPOS25_49,CCSCYTOLPOS50,CCSHPVPOS24,CCSHPVPOS25_49,CCSHPVPOS50,CCSCryotherapy24,CCSCryotherapy25_49,CCSCryotherapy50,CCSLEEP24,CCSLEEP25_49,CCSLEEP50,CCSHIVPOSSCREENED24,CCSHIVPOSSCREENED25_49,CCSHIVPOSSCREENED50;	
                                                
String PNCBreastExam,PNCCounselled,PNCFistula,PNCExerNegative,PNCExerPositive,PNCCCSsuspect,PNCmotherspostpartum2_3,PNCmotherspostpartum6,PNCinfantspostpartum2_3,PNCinfantspostpartum6,PNCreferralsfromotherHF,PNCreferralsfromotherCU,PNCreferralsTootherHF,PNCreferralsTootherCU;	
                                                        
String RsAssessed,Rstreated,RsRehabilitated,Rsreffered,RsIntergrated;	
                                                                
String MSWpscounselling,MSWdrugabuse,MSWMental,MSWAdolescent,MSWPsAsses,MSWsocialinv,MSWsocialRehab,MSWoutreach,MSWreferrals,MSWwaivedpatients;	
                                                                        
String PsotherOPD4,PsotherOPD5_19,PsotherOPD20,Psotherinpatient4,Psotherinpatient5_19,Psotherinpatient20, PsPWDOPD4,PsPWDOPD5_19,PsPWDOPD20,PsPWDinpatient4,PsPWDinpatient5_19,PsPWDinpatient20,PsTreatments4,PsTreatments5_19,PsTreatments20,PsAssessed4,PsAssessed5_19,PsAssessed20,PsServices4,PsServices5_19,PsServices20,PsANCCounsel5_19,PsANCCounsel20,PsExercise5_19,PsExercise20,PsFIFcollected5_19,PsFIFcollected20,PsFIFwaived5_19,PsFIFwaived20,PsFIFexempted4,PsFIFexempted5_19,PsFIFexempted20,PsDiasbilitymeeting4,PsDiasbilitymeeting5_19,PsDiasbilitymeeting20;


//initialize variables

 FPProgestinN=FPProgestinR=FPProgestinT=FPCocN=FPCocR=FPCocT=FPEcpN=FPEcpR=FPEcpT=FPINJECTABLESN=FPINJECTABLESR=FPINJECTABLEST=FPINJECTIONSN=FPINJECTIONSR=FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR=FPVasectomyT=FPCONDOMSMN=FPCONDOMSFN=FPCONDOMST=FPNaturalN=FPNaturalR=FPNaturalT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPADOLESCENT10_14N=FPADOLESCENT10_14R=FPADOLESCENT10_14T=FPADOLESCENT15_19N=FPADOLESCENT15_19R=FPADOLESCENT15_19T=FPADOLESCENT20_24N=FPADOLESCENT20_24R=FPADOLESCENT20_24T=FPIUCDRemoval=FPIMPLANTSRemoval="0";	
        
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTIPT1=PMCTIPT2=PMCTHB11=PMCTANCClients4=PMCTITN1=PMCTITN=PMTCTSYPHILISTES=PMTCTSYPHILISPOS=PMTCTCOUNSELLEDFEED=PMTCTBREAST=PMTCTEXERCISE=PMTCTPREG10_14=PMTCTPREG15_19=PMTCTIRON=PMTCTFOLIC=PMTCTFERROUS="0";	
                
 MATNormalDelivery=MATCSection=MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATFreshStillBirth=MATMeceratedStillBirth=MATDeformities=MATLowAPGAR=MATWeight2500=MATTetracycline=MATPreTerm=MATDischargealive=MATbreastfeeding1=MATDeliveriesPos=MATNeoNatalD=MATMaternalD10_19=MATMaternalD=MATMaternalDAudited=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead=MATREFFromOtherFacility=MATREFFromCU=MATREFToOtherFacility=MATREFToCU="0";	
                        
 SGBVRape72_0_9=SGBVRape72_10_17=SGBVRape72_18_49=SGBVRape72_50=SGBVRape72T=SGBVinitPEP0_9=SGBVinitPEP10_17=SGBVinitPEP18_49=SGBVinitPEP50=SGBVinitPEPT=SGBVcompPEP0_9=SGBVcompPEP10_17=SGBVcompPEP18_49=SGBVcompPEP50=SGBVcompPEPT=SGBVPregnant0_9=SGBVPregnant10_17=SGBVPregnant18_49=SGBVPregnant50=SGBVPregnantT=SGBVseroconverting0_9=SGBVseroconverting10_17=SGBVseroconverting18_49=SGBVseroconverting50=SGBVseroconvertingT=SGBVsurvivors0_9=SGBVsurvivors10_17=SGBVsurvivors18_49=SGBVsurvivors50=SGBVsurvivorsT="0";
                                
 PAC10_19=PACT="0";	
                                        
 CHANIS0_5NormalweightF=CHANIS0_5NormalweightM=CHANIS0_5NormalweightT=CHANIS0_5UnderweightF=CHANIS0_5UnderweightM=CHANIS0_5UnderweightT=CHANIS0_5sevUnderweightF=CHANIS0_5sevUnderweightM=CHANIS0_5sevUnderweightT=CHANIS0_5OverweightF=CHANIS0_5OverweightM=CHANIS0_5OverweightT=CHANIS0_5ObeseF=CHANIS0_5ObeseM=CHANIS0_5ObeseT=CHANIS0_5TWF=CHANIS0_5TWM=CHANIS0_5TW=CHANIS6_23NormalweightF=CHANIS6_23NormalweightM=CHANIS6_23NormalweightT=CHANIS6_23UnderweightF=CHANIS6_23UnderweightM=CHANIS6_23UnderweightT=CHANIS6_23sevUnderweightF=CHANIS6_23sevUnderweightM=CHANIS6_23sevUnderweightT=CHANIS6_23OverweightF=CHANIS6_23OverweightM=CHANIS6_23OverweightT=CHANIS6_23ObeseF=CHANIS6_23ObeseM=CHANIS6_23ObeseT=CHANIS6_23TWF=CHANIS6_23TWM=CHANIS6_23TW=CHANIS24_59NormalweightF=CHANIS24_59NormalweightM=CHANIS24_59NormalweightT=CHANIS24_59UnderweightF=CHANIS24_59UnderweightM=CHANIS24_59UnderweightT=CHANIS24_59sevUnderweightF=CHANIS24_59sevUnderweightM=CHANIS24_59sevUnderweightT=CHANIS24_59OverweightF=CHANIS24_59OverweightM=CHANIS24_59OverweightT=CHANIS24_59ObeseF=CHANIS24_59ObeseM=CHANIS24_59ObeseT=CHANIS24_59TWF=CHANIS24_59TWM=CHANIS24_59TW=CHANISMUACNormalF=CHANISMUACNormalM=CHANISMUACNormalT=CHANISMUACModerateF=CHANISMUACModerateM=CHANISMUACModerateT=CHANISMUACSevereF=CHANISMUACSevereM=CHANISMUACSevereT=CHANISMUACMeasuredF=CHANISMUACMeasuredM=CHANISMUACMeasuredT="0";

 CHANIS0_5NormalHeightF=CHANIS0_5NormalHeightM=CHANIS0_5NormalHeightT=CHANIS0_5StuntedF=CHANIS0_5StuntedM=CHANIS0_5StuntedT=CHANIS0_5sevStuntedF=CHANIS0_5sevStuntedM=CHANIS0_5sevStuntedT=CHANIS0_5TMeasF=CHANIS0_5TMeasM=CHANIS0_5TMeas=CHANIS6_23NormalHeightF=CHANIS6_23NormalHeightM=CHANIS6_23NormalHeightT=CHANIS6_23StuntedF=CHANIS6_23StuntedM=CHANIS6_23StuntedT=CHANIS6_23sevStuntedF=CHANIS6_23sevStuntedM=CHANIS6_23sevStuntedT=CHANIS6_23TMeasF=CHANIS6_23TMeasM=CHANIS6_23TMeas=CHANIS24_59NormalHeightF=CHANIS24_59NormalHeightM=CHANIS24_59NormalHeightT=CHANIS24_59StuntedF=CHANIS24_59StuntedM=CHANIS24_59StuntedT=CHANIS24_59sevStuntedF=CHANIS24_59sevStuntedM=CHANIS24_59sevStuntedT=CHANIS24_59TMeasF=CHANIS24_59TMeasM=CHANIS24_59TMeas=CHANIS0_59NewVisitsF=CHANIS0_59NewVisitsM=CHANIS0_59NewVisitsT=CHANIS0_59KwashiakorF=CHANIS0_59KwashiakorM=CHANIS0_59KwashiakorT=CHANIS0_59MarasmusF=CHANIS0_59MarasmusM=CHANIS0_59MarasmusT=CHANIS0_59FalgrowthF=CHANIS0_59FalgrowthM=CHANIS0_59FalgrowthT=CHANIS0_59F=CHANIS0_59M=CHANIS0_59T=CHANIS0_5EXCLBreastF=CHANIS0_5EXCLBreastM=CHANIS0_5EXCLBreastT=CHANIS12_59DewormedF=CHANIS12_59DewormedM=CHANIS12_59DewormedT=CHANIS6_23MNPsF=CHANIS6_23MNPsM=CHANIS6_23MNPsT=CHANIS0_59DisabilityF=CHANIS0_59DisabilityM=CHANIS0_59DisabilityT="0";	
  
 CCSSUSPICIOUSLES24=CCSSUSPICIOUSLES25_49=CCSSUSPICIOUSLES50=CCSVVH24=CCSVVH25_49=CCSVVH50=CCSPAPSMEAR24=CCSPAPSMEAR25_49=CCSPAPSMEAR50=CCSHPV24=CCSHPV25_49=CCSHPV50=CCSVIAVILIPOS24=CCSVIAVILIPOS25_49=CCSVIAVILIPOS50=CCSCYTOLPOS24=CCSCYTOLPOS25_49=CCSCYTOLPOS50=CCSHPVPOS24=CCSHPVPOS25_49=CCSHPVPOS50=CCSCryotherapy24=CCSCryotherapy25_49=CCSCryotherapy50=CCSLEEP24=CCSLEEP25_49=CCSLEEP50=CCSHIVPOSSCREENED24=CCSHIVPOSSCREENED25_49=CCSHIVPOSSCREENED50="0";	
                                                
 PNCBreastExam=PNCCounselled=PNCFistula=PNCExerNegative=PNCExerPositive=PNCCCSsuspect=PNCmotherspostpartum2_3=PNCmotherspostpartum6=PNCinfantspostpartum2_3=PNCinfantspostpartum6=PNCreferralsfromotherHF=PNCreferralsfromotherCU=PNCreferralsTootherHF=PNCreferralsTootherCU="0";	
                                                        
 RsAssessed=Rstreated=RsRehabilitated=Rsreffered=RsIntergrated="0";	
                                                                
 MSWpscounselling=MSWdrugabuse=MSWMental=MSWAdolescent=MSWPsAsses=MSWsocialinv=MSWsocialRehab=MSWoutreach=MSWreferrals=MSWwaivedpatients="0";	
                                                                        
 PsotherOPD4=PsotherOPD5_19=PsotherOPD20=Psotherinpatient4=Psotherinpatient5_19=Psotherinpatient20= PsPWDOPD4=PsPWDOPD5_19=PsPWDOPD20=PsPWDinpatient4=PsPWDinpatient5_19=PsPWDinpatient20=PsTreatments4=PsTreatments5_19=PsTreatments20=PsAssessed4=PsAssessed5_19=PsAssessed20=PsServices4=PsServices5_19=PsServices20=PsANCCounsel5_19=PsANCCounsel20=PsExercise5_19=PsExercise20=PsFIFcollected5_19=PsFIFcollected20=PsFIFwaived5_19=PsFIFwaived20=PsFIFexempted4=PsFIFexempted5_19=PsFIFexempted20=PsDiasbilitymeeting4=PsDiasbilitymeeting5_19=PsDiasbilitymeeting20="0";



 
 /** 
  
 VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="0";
 
 DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="0";
*/
           
String tableid=year+"_"+month+"_"+facil;

 
  if(request.getParameter("FPINJECTIONSN")!=null && !request.getParameter("FPINJECTIONSN").equals("")){FPINJECTIONSN=request.getParameter("FPINJECTIONSN");}
 
  
  
  if(request.getParameter("FPProgestinN")!=null && !request.getParameter("FPProgestinN").equals("") ){ FPProgestinN=request.getParameter("FPProgestinN");}
  if(request.getParameter("FPProgestinR")!=null && !request.getParameter("FPProgestinR").equals("") ){ FPProgestinR=request.getParameter("FPProgestinR");}
  if(request.getParameter("FPProgestinT")!=null && !request.getParameter("FPProgestinT").equals("") ){ FPProgestinT=request.getParameter("FPProgestinT");}
  if(request.getParameter("FPCocN")!=null && !request.getParameter("FPCocN").equals("") ){ FPCocN=request.getParameter("FPCocN");}
  if(request.getParameter("FPCocR")!=null && !request.getParameter("FPCocR").equals("") ){ FPCocR=request.getParameter("FPCocR");}
  if(request.getParameter("FPCocT")!=null && !request.getParameter("FPCocT").equals("") ){ FPCocT=request.getParameter("FPCocT");}
  if(request.getParameter("FPEcpN")!=null && !request.getParameter("FPEcpN").equals("") ){ FPEcpN=request.getParameter("FPEcpN");}
  if(request.getParameter("FPEcpR")!=null && !request.getParameter("FPEcpR").equals("") ){ FPEcpR=request.getParameter("FPEcpR");}
  if(request.getParameter("FPEcpT")!=null && !request.getParameter("FPEcpT").equals("") ){ FPEcpT=request.getParameter("FPEcpT");}
  if(request.getParameter("FPINJECTABLESN")!=null && !request.getParameter("FPINJECTABLESN").equals("") ){ FPINJECTABLESN=request.getParameter("FPINJECTABLESN");}
  if(request.getParameter("FPINJECTABLESR")!=null && !request.getParameter("FPINJECTABLESR").equals("") ){ FPINJECTABLESR=request.getParameter("FPINJECTABLESR");}
  if(request.getParameter("FPINJECTABLEST")!=null && !request.getParameter("FPINJECTABLEST").equals("") ){ FPINJECTABLEST=request.getParameter("FPINJECTABLEST");}
  if(request.getParameter("FPINJECTIONSN")!=null && !request.getParameter("FPINJECTIONSN").equals("") ){ FPINJECTIONSN=request.getParameter("FPINJECTIONSN");}
  if(request.getParameter("FPINJECTIONSR")!=null && !request.getParameter("FPINJECTIONSR").equals("") ){ FPINJECTIONSR=request.getParameter("FPINJECTIONSR");}
  if(request.getParameter("FPINJECTIONST")!=null && !request.getParameter("FPINJECTIONST").equals("") ){ FPINJECTIONST=request.getParameter("FPINJECTIONST");}
  if(request.getParameter("FPIUCDN")!=null && !request.getParameter("FPIUCDN").equals("") ){ FPIUCDN=request.getParameter("FPIUCDN");}
  if(request.getParameter("FPIUCDR")!=null && !request.getParameter("FPIUCDR").equals("") ){ FPIUCDR=request.getParameter("FPIUCDR");}
  if(request.getParameter("FPIUCDT")!=null && !request.getParameter("FPIUCDT").equals("") ){ FPIUCDT=request.getParameter("FPIUCDT");}
  if(request.getParameter("FPIMPLANTSN")!=null && !request.getParameter("FPIMPLANTSN").equals("") ){ FPIMPLANTSN=request.getParameter("FPIMPLANTSN");}
  if(request.getParameter("FPIMPLANTSR")!=null && !request.getParameter("FPIMPLANTSR").equals("") ){ FPIMPLANTSR=request.getParameter("FPIMPLANTSR");}
  if(request.getParameter("FPIMPLANTST")!=null && !request.getParameter("FPIMPLANTST").equals("") ){ FPIMPLANTST=request.getParameter("FPIMPLANTST");}
  if(request.getParameter("FPBTLN")!=null && !request.getParameter("FPBTLN").equals("") ){ FPBTLN=request.getParameter("FPBTLN");}
  if(request.getParameter("FPBTLR")!=null && !request.getParameter("FPBTLR").equals("") ){ FPBTLR=request.getParameter("FPBTLR");}
  if(request.getParameter("FPBTLT")!=null && !request.getParameter("FPBTLT").equals("") ){ FPBTLT=request.getParameter("FPBTLT");}
  if(request.getParameter("FPVasectomyN")!=null && !request.getParameter("FPVasectomyN").equals("") ){ FPVasectomyN=request.getParameter("FPVasectomyN");}
  if(request.getParameter("FPVasectomyR")!=null && !request.getParameter("FPVasectomyR").equals("") ){ FPVasectomyR=request.getParameter("FPVasectomyR");}
  if(request.getParameter("FPVasectomyT")!=null && !request.getParameter("FPVasectomyT").equals("") ){ FPVasectomyT=request.getParameter("FPVasectomyT");}
  if(request.getParameter("FPCONDOMSMN")!=null && !request.getParameter("FPCONDOMSMN").equals("") ){ FPCONDOMSMN=request.getParameter("FPCONDOMSMN");}
  if(request.getParameter("FPCONDOMSFN")!=null && !request.getParameter("FPCONDOMSFN").equals("") ){ FPCONDOMSFN=request.getParameter("FPCONDOMSFN");}
  if(request.getParameter("FPCONDOMST")!=null && !request.getParameter("FPCONDOMST").equals("") ){ FPCONDOMST=request.getParameter("FPCONDOMST");}
  if(request.getParameter("FPNaturalN")!=null && !request.getParameter("FPNaturalN").equals("") ){ FPNaturalN=request.getParameter("FPNaturalN");}
  if(request.getParameter("FPNaturalN")!=null && !request.getParameter("FPNaturalN").equals("") ){ FPNaturalN=request.getParameter("FPNaturalN");}
  if(request.getParameter("FPNaturalR")!=null && !request.getParameter("FPNaturalR").equals("") ){ FPNaturalR=request.getParameter("FPNaturalR");}
  if(request.getParameter("FPNaturalT")!=null && !request.getParameter("FPNaturalT").equals("") ){ FPNaturalT=request.getParameter("FPNaturalT");}
  if(request.getParameter("FPCLIENTSN")!=null && !request.getParameter("FPCLIENTSN").equals("") ){ FPCLIENTSN=request.getParameter("FPCLIENTSN");}
  if(request.getParameter("FPCLIENTSR")!=null && !request.getParameter("FPCLIENTSR").equals("") ){ FPCLIENTSR=request.getParameter("FPCLIENTSR");}
  if(request.getParameter("FPCLIENTST")!=null && !request.getParameter("FPCLIENTST").equals("") ){ FPCLIENTST=request.getParameter("FPCLIENTST");}
  if(request.getParameter("FPADOLESCENT10_14N")!=null && !request.getParameter("FPADOLESCENT10_14N").equals("") ){ FPADOLESCENT10_14N=request.getParameter("FPADOLESCENT10_14N");}
  if(request.getParameter("FPADOLESCENT10_14R")!=null && !request.getParameter("FPADOLESCENT10_14R").equals("") ){ FPADOLESCENT10_14R=request.getParameter("FPADOLESCENT10_14R");}
  if(request.getParameter("FPADOLESCENT10_14T")!=null && !request.getParameter("FPADOLESCENT10_14T").equals("") ){ FPADOLESCENT10_14T=request.getParameter("FPADOLESCENT10_14T");}
  if(request.getParameter("FPADOLESCENT15_19N")!=null && !request.getParameter("FPADOLESCENT15_19N").equals("") ){ FPADOLESCENT15_19N=request.getParameter("FPADOLESCENT15_19N");}
  if(request.getParameter("FPADOLESCENT15_19R")!=null && !request.getParameter("FPADOLESCENT15_19R").equals("") ){ FPADOLESCENT15_19R=request.getParameter("FPADOLESCENT15_19R");}
  if(request.getParameter("FPADOLESCENT15_19T")!=null && !request.getParameter("FPADOLESCENT15_19T").equals("") ){ FPADOLESCENT15_19T=request.getParameter("FPADOLESCENT15_19T");}
  if(request.getParameter("FPADOLESCENT20_24N")!=null && !request.getParameter("FPADOLESCENT20_24N").equals("") ){ FPADOLESCENT20_24N=request.getParameter("FPADOLESCENT20_24N");}
  if(request.getParameter("FPADOLESCENT20_24R")!=null && !request.getParameter("FPADOLESCENT20_24R").equals("") ){ FPADOLESCENT20_24R=request.getParameter("FPADOLESCENT20_24R");}
  if(request.getParameter("FPADOLESCENT20_24T")!=null && !request.getParameter("FPADOLESCENT20_24T").equals("") ){ FPADOLESCENT20_24T=request.getParameter("FPADOLESCENT20_24T");}
  if(request.getParameter("FPIUCDRemoval")!=null && !request.getParameter("FPIUCDRemoval").equals("") ){ FPIUCDRemoval=request.getParameter("FPIUCDRemoval");}
  if(request.getParameter("FPIMPLANTSRemoval")!=null && !request.getParameter("FPIMPLANTSRemoval").equals("") ){ FPIMPLANTSRemoval=request.getParameter("FPIMPLANTSRemoval");}

  
  
  //=======================================================PMTCT ANC===================================================
  
       
    
    if(request.getParameter("PMCTA_1stVisit_ANC")!=null && !request.getParameter("PMCTA_1stVisit_ANC").equals("") ){ PMCTA_1stVisit_ANC=request.getParameter("PMCTA_1stVisit_ANC");}
    if(request.getParameter("PMCTA_ReVisit_ANC")!=null && !request.getParameter("PMCTA_ReVisit_ANC").equals("") ){ PMCTA_ReVisit_ANC=request.getParameter("PMCTA_ReVisit_ANC");}
    if(request.getParameter("PMCTANCClientsT")!=null && !request.getParameter("PMCTANCClientsT").equals("") ){ PMCTANCClientsT=request.getParameter("PMCTANCClientsT");}
    if(request.getParameter("PMCTIPT1")!=null && !request.getParameter("PMCTIPT1").equals("") ){ PMCTIPT1=request.getParameter("PMCTIPT1");}
    if(request.getParameter("PMCTIPT2")!=null && !request.getParameter("PMCTIPT2").equals("") ){ PMCTIPT2=request.getParameter("PMCTIPT2");}
    if(request.getParameter("PMCTHB11")!=null && !request.getParameter("PMCTHB11").equals("") ){ PMCTHB11=request.getParameter("PMCTHB11");}
    if(request.getParameter("PMCTANCClients4")!=null && !request.getParameter("PMCTANCClients4").equals("") ){ PMCTANCClients4=request.getParameter("PMCTANCClients4");}
    if(request.getParameter("PMCTITN1")!=null && !request.getParameter("PMCTITN1").equals("") ){ PMCTITN1=request.getParameter("PMCTITN1");}
    if(request.getParameter("PMCTITN")!=null && !request.getParameter("PMCTITN").equals("") ){ PMCTITN=request.getParameter("PMCTITN");}
    if(request.getParameter("PMTCTSYPHILISTES")!=null && !request.getParameter("PMTCTSYPHILISTES").equals("") ){ PMTCTSYPHILISTES=request.getParameter("PMTCTSYPHILISTES");}
    if(request.getParameter("PMTCTSYPHILISPOS")!=null && !request.getParameter("PMTCTSYPHILISPOS").equals("") ){ PMTCTSYPHILISPOS=request.getParameter("PMTCTSYPHILISPOS");}
    if(request.getParameter("PMTCTCOUNSELLEDFEED")!=null && !request.getParameter("PMTCTCOUNSELLEDFEED").equals("") ){ PMTCTCOUNSELLEDFEED=request.getParameter("PMTCTCOUNSELLEDFEED");}
    if(request.getParameter("PMTCTBREAST")!=null && !request.getParameter("PMTCTBREAST").equals("") ){ PMTCTBREAST=request.getParameter("PMTCTBREAST");}
    if(request.getParameter("PMTCTEXERCISE")!=null && !request.getParameter("PMTCTEXERCISE").equals("") ){ PMTCTEXERCISE=request.getParameter("PMTCTEXERCISE");}
    if(request.getParameter("PMTCTPREG10_14")!=null && !request.getParameter("PMTCTPREG10_14").equals("") ){ PMTCTPREG10_14=request.getParameter("PMTCTPREG10_14");}
    if(request.getParameter("PMTCTPREG15_19")!=null && !request.getParameter("PMTCTPREG15_19").equals("") ){ PMTCTPREG15_19=request.getParameter("PMTCTPREG15_19");}
    if(request.getParameter("PMTCTIRON")!=null && !request.getParameter("PMTCTIRON").equals("") ){ PMTCTIRON=request.getParameter("PMTCTIRON");}
    if(request.getParameter("PMTCTFOLIC")!=null && !request.getParameter("PMTCTFOLIC").equals("") ){ PMTCTFOLIC=request.getParameter("PMTCTFOLIC");}
    if(request.getParameter("PMTCTFERROUS")!=null && !request.getParameter("PMTCTFERROUS").equals("") ){ PMTCTFERROUS=request.getParameter("PMTCTFERROUS");}
    
    
  
  
   //===================================================MATERNITY================================================
    
  
     if(request.getParameter("MATNormalDelivery")!=null && !request.getParameter("MATNormalDelivery").equals("") ){ MATNormalDelivery=request.getParameter("MATNormalDelivery");}
    if(request.getParameter("MATCSection")!=null && !request.getParameter("MATCSection").equals("") ){ MATCSection=request.getParameter("MATCSection");}
    if(request.getParameter("MATBreech")!=null && !request.getParameter("MATBreech").equals("") ){ MATBreech=request.getParameter("MATBreech");}
    if(request.getParameter("MATAssistedVag")!=null && !request.getParameter("MATAssistedVag").equals("") ){ MATAssistedVag=request.getParameter("MATAssistedVag");}
    if(request.getParameter("MATDeliveryT")!=null && !request.getParameter("MATDeliveryT").equals("") ){ MATDeliveryT=request.getParameter("MATDeliveryT");}
    if(request.getParameter("MATLiveBirth")!=null && !request.getParameter("MATLiveBirth").equals("") ){ MATLiveBirth=request.getParameter("MATLiveBirth");}
    if(request.getParameter("MATFreshStillBirth")!=null && !request.getParameter("MATFreshStillBirth").equals("") ){ MATFreshStillBirth=request.getParameter("MATFreshStillBirth");}
    if(request.getParameter("MATMeceratedStillBirth")!=null && !request.getParameter("MATMeceratedStillBirth").equals("") ){ MATMeceratedStillBirth=request.getParameter("MATMeceratedStillBirth");}
    if(request.getParameter("MATDeformities")!=null && !request.getParameter("MATDeformities").equals("") ){ MATDeformities=request.getParameter("MATDeformities");}
    if(request.getParameter("MATLowAPGAR")!=null && !request.getParameter("MATLowAPGAR").equals("") ){ MATLowAPGAR=request.getParameter("MATLowAPGAR");}
    if(request.getParameter("MATWeight2500")!=null && !request.getParameter("MATWeight2500").equals("") ){ MATWeight2500=request.getParameter("MATWeight2500");}
    if(request.getParameter("MATTetracycline")!=null && !request.getParameter("MATTetracycline").equals("") ){ MATTetracycline=request.getParameter("MATTetracycline");}
    if(request.getParameter("MATPreTerm")!=null && !request.getParameter("MATPreTerm").equals("") ){ MATPreTerm=request.getParameter("MATPreTerm");}
    if(request.getParameter("MATDischargealive")!=null && !request.getParameter("MATDischargealive").equals("") ){ MATDischargealive=request.getParameter("MATDischargealive");}
    if(request.getParameter("MATbreastfeeding1")!=null && !request.getParameter("MATbreastfeeding1").equals("") ){ MATbreastfeeding1=request.getParameter("MATbreastfeeding1");}
    if(request.getParameter("MATDeliveriesPos")!=null && !request.getParameter("MATDeliveriesPos").equals("") ){ MATDeliveriesPos=request.getParameter("MATDeliveriesPos");}
    if(request.getParameter("MATNeoNatalD")!=null && !request.getParameter("MATNeoNatalD").equals("") ){ MATNeoNatalD=request.getParameter("MATNeoNatalD");}
    if(request.getParameter("MATMaternalD10_19")!=null && !request.getParameter("MATMaternalD10_19").equals("") ){ MATMaternalD10_19=request.getParameter("MATMaternalD10_19");}
    if(request.getParameter("MATMaternalD")!=null && !request.getParameter("MATMaternalD").equals("") ){ MATMaternalD=request.getParameter("MATMaternalD");}
    if(request.getParameter("MATMaternalDAudited")!=null && !request.getParameter("MATMaternalDAudited").equals("") ){ MATMaternalDAudited=request.getParameter("MATMaternalDAudited");}
    if(request.getParameter("MATAPHAlive")!=null && !request.getParameter("MATAPHAlive").equals("") ){ MATAPHAlive=request.getParameter("MATAPHAlive");}
    if(request.getParameter("MATAPHDead")!=null && !request.getParameter("MATAPHDead").equals("") ){ MATAPHDead=request.getParameter("MATAPHDead");}
    if(request.getParameter("MATPPHAlive")!=null && !request.getParameter("MATPPHAlive").equals("") ){ MATPPHAlive=request.getParameter("MATPPHAlive");}
    if(request.getParameter("MATPPHDead")!=null && !request.getParameter("MATPPHDead").equals("") ){ MATPPHDead=request.getParameter("MATPPHDead");}
    if(request.getParameter("MATEclampAlive")!=null && !request.getParameter("MATEclampAlive").equals("") ){ MATEclampAlive=request.getParameter("MATEclampAlive");}
    if(request.getParameter("MATEclampDead")!=null && !request.getParameter("MATEclampDead").equals("") ){ MATEclampDead=request.getParameter("MATEclampDead");}
    if(request.getParameter("MATRupUtAlive")!=null && !request.getParameter("MATRupUtAlive").equals("") ){ MATRupUtAlive=request.getParameter("MATRupUtAlive");}
    if(request.getParameter("MATRupUtDead")!=null && !request.getParameter("MATRupUtDead").equals("") ){ MATRupUtDead=request.getParameter("MATRupUtDead");}
    if(request.getParameter("MATObstrLaborAlive")!=null && !request.getParameter("MATObstrLaborAlive").equals("") ){ MATObstrLaborAlive=request.getParameter("MATObstrLaborAlive");}
    if(request.getParameter("MATObstrLaborDead")!=null && !request.getParameter("MATObstrLaborDead").equals("") ){ MATObstrLaborDead=request.getParameter("MATObstrLaborDead");}
    if(request.getParameter("MATSepsisAlive")!=null && !request.getParameter("MATSepsisAlive").equals("") ){ MATSepsisAlive=request.getParameter("MATSepsisAlive");}
    if(request.getParameter("MATSepsisDead")!=null && !request.getParameter("MATSepsisDead").equals("") ){ MATSepsisDead=request.getParameter("MATSepsisDead");}
    if(request.getParameter("MATREFFromOtherFacility")!=null && !request.getParameter("MATREFFromOtherFacility").equals("") ){ MATREFFromOtherFacility=request.getParameter("MATREFFromOtherFacility");}
    if(request.getParameter("MATREFFromCU")!=null && !request.getParameter("MATREFFromCU").equals("") ){ MATREFFromCU=request.getParameter("MATREFFromCU");}
    if(request.getParameter("MATREFToOtherFacility")!=null && !request.getParameter("MATREFToOtherFacility").equals("") ){ MATREFToOtherFacility=request.getParameter("MATREFToOtherFacility");}
    if(request.getParameter("MATREFToCU")!=null && !request.getParameter("MATREFToCU").equals("") ){ MATREFToCU=request.getParameter("MATREFToCU");}
    
    
    // =========================================================SGBV ============================================================
  
    
    if(request.getParameter("SGBVRape72_0_9")!=null && !request.getParameter("SGBVRape72_0_9").equals("") ){ SGBVRape72_0_9=request.getParameter("SGBVRape72_0_9");}
    if(request.getParameter("SGBVRape72_10_17")!=null && !request.getParameter("SGBVRape72_10_17").equals("") ){ SGBVRape72_10_17=request.getParameter("SGBVRape72_10_17");}
    if(request.getParameter("SGBVRape72_18_49")!=null && !request.getParameter("SGBVRape72_18_49").equals("") ){ SGBVRape72_18_49=request.getParameter("SGBVRape72_18_49");}
    if(request.getParameter("SGBVRape72_50")!=null && !request.getParameter("SGBVRape72_50").equals("") ){ SGBVRape72_50=request.getParameter("SGBVRape72_50");}
    if(request.getParameter("SGBVRape72T")!=null && !request.getParameter("SGBVRape72T").equals("") ){ SGBVRape72T=request.getParameter("SGBVRape72T");}
    if(request.getParameter("SGBVinitPEP0_9")!=null && !request.getParameter("SGBVinitPEP0_9").equals("") ){ SGBVinitPEP0_9=request.getParameter("SGBVinitPEP0_9");}
    if(request.getParameter("SGBVinitPEP10_17")!=null && !request.getParameter("SGBVinitPEP10_17").equals("") ){ SGBVinitPEP10_17=request.getParameter("SGBVinitPEP10_17");}
    if(request.getParameter("SGBVinitPEP18_49")!=null && !request.getParameter("SGBVinitPEP18_49").equals("") ){ SGBVinitPEP18_49=request.getParameter("SGBVinitPEP18_49");}
    if(request.getParameter("SGBVinitPEP50")!=null && !request.getParameter("SGBVinitPEP50").equals("") ){ SGBVinitPEP50=request.getParameter("SGBVinitPEP50");}
    if(request.getParameter("SGBVinitPEPT")!=null && !request.getParameter("SGBVinitPEPT").equals("") ){ SGBVinitPEPT=request.getParameter("SGBVinitPEPT");}
    if(request.getParameter("SGBVcompPEP0_9")!=null && !request.getParameter("SGBVcompPEP0_9").equals("") ){ SGBVcompPEP0_9=request.getParameter("SGBVcompPEP0_9");}
    if(request.getParameter("SGBVcompPEP10_17")!=null && !request.getParameter("SGBVcompPEP10_17").equals("") ){ SGBVcompPEP10_17=request.getParameter("SGBVcompPEP10_17");}
    if(request.getParameter("SGBVcompPEP18_49")!=null && !request.getParameter("SGBVcompPEP18_49").equals("") ){ SGBVcompPEP18_49=request.getParameter("SGBVcompPEP18_49");}
    if(request.getParameter("SGBVcompPEP50")!=null && !request.getParameter("SGBVcompPEP50").equals("") ){ SGBVcompPEP50=request.getParameter("SGBVcompPEP50");}
    if(request.getParameter("SGBVcompPEPT")!=null && !request.getParameter("SGBVcompPEPT").equals("") ){ SGBVcompPEPT=request.getParameter("SGBVcompPEPT");}
    if(request.getParameter("SGBVPregnant0_9")!=null && !request.getParameter("SGBVPregnant0_9").equals("") ){ SGBVPregnant0_9=request.getParameter("SGBVPregnant0_9");}
    if(request.getParameter("SGBVPregnant10_17")!=null && !request.getParameter("SGBVPregnant10_17").equals("") ){ SGBVPregnant10_17=request.getParameter("SGBVPregnant10_17");}
    if(request.getParameter("SGBVPregnant18_49")!=null && !request.getParameter("SGBVPregnant18_49").equals("") ){ SGBVPregnant18_49=request.getParameter("SGBVPregnant18_49");}
    if(request.getParameter("SGBVPregnant50")!=null && !request.getParameter("SGBVPregnant50").equals("") ){ SGBVPregnant50=request.getParameter("SGBVPregnant50");}
    if(request.getParameter("SGBVPregnantT")!=null && !request.getParameter("SGBVPregnantT").equals("") ){ SGBVPregnantT=request.getParameter("SGBVPregnantT");}
    if(request.getParameter("SGBVseroconverting0_9")!=null && !request.getParameter("SGBVseroconverting0_9").equals("") ){ SGBVseroconverting0_9=request.getParameter("SGBVseroconverting0_9");}
    if(request.getParameter("SGBVseroconverting10_17")!=null && !request.getParameter("SGBVseroconverting10_17").equals("") ){ SGBVseroconverting10_17=request.getParameter("SGBVseroconverting10_17");}
    if(request.getParameter("SGBVseroconverting18_49")!=null && !request.getParameter("SGBVseroconverting18_49").equals("") ){ SGBVseroconverting18_49=request.getParameter("SGBVseroconverting18_49");}
    if(request.getParameter("SGBVseroconverting50")!=null && !request.getParameter("SGBVseroconverting50").equals("") ){ SGBVseroconverting50=request.getParameter("SGBVseroconverting50");}
    if(request.getParameter("SGBVseroconvertingT")!=null && !request.getParameter("SGBVseroconvertingT").equals("") ){ SGBVseroconvertingT=request.getParameter("SGBVseroconvertingT");}
    if(request.getParameter("SGBVsurvivors0_9")!=null && !request.getParameter("SGBVsurvivors0_9").equals("") ){ SGBVsurvivors0_9=request.getParameter("SGBVsurvivors0_9");}
    if(request.getParameter("SGBVsurvivors10_17")!=null && !request.getParameter("SGBVsurvivors10_17").equals("") ){ SGBVsurvivors10_17=request.getParameter("SGBVsurvivors10_17");}
    if(request.getParameter("SGBVsurvivors18_49")!=null && !request.getParameter("SGBVsurvivors18_49").equals("") ){ SGBVsurvivors18_49=request.getParameter("SGBVsurvivors18_49");}
    if(request.getParameter("SGBVsurvivors50")!=null && !request.getParameter("SGBVsurvivors50").equals("") ){ SGBVsurvivors50=request.getParameter("SGBVsurvivors50");}
    if(request.getParameter("SGBVsurvivorsT")!=null && !request.getParameter("SGBVsurvivorsT").equals("") ){ SGBVsurvivorsT=request.getParameter("SGBVsurvivorsT");}
 
    
  //==================================================PAC====================================================
    

    
     if(request.getParameter("PAC10_19")!=null && !request.getParameter("PAC10_19").equals("") ){ PAC10_19=request.getParameter("PAC10_19");}
    if(request.getParameter("PACT")!=null && !request.getParameter("PACT").equals("") ){ PACT=request.getParameter("PACT");}
    
    
    	
                                        
//=================================================CHANIS WEIGHT FOR AGE========================================================================

    if(request.getParameter("CHANIS0_5NormalweightF")!=null && !request.getParameter("CHANIS0_5NormalweightF").equals("") ){ CHANIS0_5NormalweightF=request.getParameter("CHANIS0_5NormalweightF");}
    if(request.getParameter("CHANIS0_5NormalweightM")!=null && !request.getParameter("CHANIS0_5NormalweightM").equals("") ){ CHANIS0_5NormalweightM=request.getParameter("CHANIS0_5NormalweightM");}
    if(request.getParameter("CHANIS0_5NormalweightT")!=null && !request.getParameter("CHANIS0_5NormalweightT").equals("") ){ CHANIS0_5NormalweightT=request.getParameter("CHANIS0_5NormalweightT");}
    if(request.getParameter("CHANIS0_5UnderweightF")!=null && !request.getParameter("CHANIS0_5UnderweightF").equals("") ){ CHANIS0_5UnderweightF=request.getParameter("CHANIS0_5UnderweightF");}
    if(request.getParameter("CHANIS0_5UnderweightM")!=null && !request.getParameter("CHANIS0_5UnderweightM").equals("") ){ CHANIS0_5UnderweightM=request.getParameter("CHANIS0_5UnderweightM");}
    if(request.getParameter("CHANIS0_5UnderweightT")!=null && !request.getParameter("CHANIS0_5UnderweightT").equals("") ){ CHANIS0_5UnderweightT=request.getParameter("CHANIS0_5UnderweightT");}
    if(request.getParameter("CHANIS0_5sevUnderweightF")!=null && !request.getParameter("CHANIS0_5sevUnderweightF").equals("") ){ CHANIS0_5sevUnderweightF=request.getParameter("CHANIS0_5sevUnderweightF");}
    if(request.getParameter("CHANIS0_5sevUnderweightM")!=null && !request.getParameter("CHANIS0_5sevUnderweightM").equals("") ){ CHANIS0_5sevUnderweightM=request.getParameter("CHANIS0_5sevUnderweightM");}
    if(request.getParameter("CHANIS0_5sevUnderweightT")!=null && !request.getParameter("CHANIS0_5sevUnderweightT").equals("") ){ CHANIS0_5sevUnderweightT=request.getParameter("CHANIS0_5sevUnderweightT");}
    if(request.getParameter("CHANIS0_5OverweightF")!=null && !request.getParameter("CHANIS0_5OverweightF").equals("") ){ CHANIS0_5OverweightF=request.getParameter("CHANIS0_5OverweightF");}
    if(request.getParameter("CHANIS0_5OverweightM")!=null && !request.getParameter("CHANIS0_5OverweightM").equals("") ){ CHANIS0_5OverweightM=request.getParameter("CHANIS0_5OverweightM");}
    if(request.getParameter("CHANIS0_5OverweightT")!=null && !request.getParameter("CHANIS0_5OverweightT").equals("") ){ CHANIS0_5OverweightT=request.getParameter("CHANIS0_5OverweightT");}
    if(request.getParameter("CHANIS0_5ObeseF")!=null && !request.getParameter("CHANIS0_5ObeseF").equals("") ){ CHANIS0_5ObeseF=request.getParameter("CHANIS0_5ObeseF");}
    if(request.getParameter("CHANIS0_5ObeseM")!=null && !request.getParameter("CHANIS0_5ObeseM").equals("") ){ CHANIS0_5ObeseM=request.getParameter("CHANIS0_5ObeseM");}
    if(request.getParameter("CHANIS0_5ObeseT")!=null && !request.getParameter("CHANIS0_5ObeseT").equals("") ){ CHANIS0_5ObeseT=request.getParameter("CHANIS0_5ObeseT");}
    if(request.getParameter("CHANIS0_5TWF")!=null && !request.getParameter("CHANIS0_5TWF").equals("") ){ CHANIS0_5TWF=request.getParameter("CHANIS0_5TWF");}
    if(request.getParameter("CHANIS0_5TWM")!=null && !request.getParameter("CHANIS0_5TWM").equals("") ){ CHANIS0_5TWM=request.getParameter("CHANIS0_5TWM");}
    if(request.getParameter("CHANIS0_5TW")!=null && !request.getParameter("CHANIS0_5TW").equals("") ){ CHANIS0_5TW=request.getParameter("CHANIS0_5TW");}
    if(request.getParameter("CHANIS6_23NormalweightF")!=null && !request.getParameter("CHANIS6_23NormalweightF").equals("") ){ CHANIS6_23NormalweightF=request.getParameter("CHANIS6_23NormalweightF");}
    if(request.getParameter("CHANIS6_23NormalweightM")!=null && !request.getParameter("CHANIS6_23NormalweightM").equals("") ){ CHANIS6_23NormalweightM=request.getParameter("CHANIS6_23NormalweightM");}
    if(request.getParameter("CHANIS6_23NormalweightT")!=null && !request.getParameter("CHANIS6_23NormalweightT").equals("") ){ CHANIS6_23NormalweightT=request.getParameter("CHANIS6_23NormalweightT");}
    if(request.getParameter("CHANIS6_23UnderweightF")!=null && !request.getParameter("CHANIS6_23UnderweightF").equals("") ){ CHANIS6_23UnderweightF=request.getParameter("CHANIS6_23UnderweightF");}
    if(request.getParameter("CHANIS6_23UnderweightM")!=null && !request.getParameter("CHANIS6_23UnderweightM").equals("") ){ CHANIS6_23UnderweightM=request.getParameter("CHANIS6_23UnderweightM");}
    if(request.getParameter("CHANIS6_23UnderweightT")!=null && !request.getParameter("CHANIS6_23UnderweightT").equals("") ){ CHANIS6_23UnderweightT=request.getParameter("CHANIS6_23UnderweightT");}
    if(request.getParameter("CHANIS6_23sevUnderweightF")!=null && !request.getParameter("CHANIS6_23sevUnderweightF").equals("") ){ CHANIS6_23sevUnderweightF=request.getParameter("CHANIS6_23sevUnderweightF");}
    if(request.getParameter("CHANIS6_23sevUnderweightM")!=null && !request.getParameter("CHANIS6_23sevUnderweightM").equals("") ){ CHANIS6_23sevUnderweightM=request.getParameter("CHANIS6_23sevUnderweightM");}
    if(request.getParameter("CHANIS6_23sevUnderweightT")!=null && !request.getParameter("CHANIS6_23sevUnderweightT").equals("") ){ CHANIS6_23sevUnderweightT=request.getParameter("CHANIS6_23sevUnderweightT");}
    if(request.getParameter("CHANIS6_23OverweightF")!=null && !request.getParameter("CHANIS6_23OverweightF").equals("") ){ CHANIS6_23OverweightF=request.getParameter("CHANIS6_23OverweightF");}
    if(request.getParameter("CHANIS6_23OverweightM")!=null && !request.getParameter("CHANIS6_23OverweightM").equals("") ){ CHANIS6_23OverweightM=request.getParameter("CHANIS6_23OverweightM");}
    if(request.getParameter("CHANIS6_23OverweightT")!=null && !request.getParameter("CHANIS6_23OverweightT").equals("") ){ CHANIS6_23OverweightT=request.getParameter("CHANIS6_23OverweightT");}
    if(request.getParameter("CHANIS6_23ObeseF")!=null && !request.getParameter("CHANIS6_23ObeseF").equals("") ){ CHANIS6_23ObeseF=request.getParameter("CHANIS6_23ObeseF");}
    if(request.getParameter("CHANIS6_23ObeseM")!=null && !request.getParameter("CHANIS6_23ObeseM").equals("") ){ CHANIS6_23ObeseM=request.getParameter("CHANIS6_23ObeseM");}
    if(request.getParameter("CHANIS6_23ObeseT")!=null && !request.getParameter("CHANIS6_23ObeseT").equals("") ){ CHANIS6_23ObeseT=request.getParameter("CHANIS6_23ObeseT");}
    if(request.getParameter("CHANIS6_23TWF")!=null && !request.getParameter("CHANIS6_23TWF").equals("") ){ CHANIS6_23TWF=request.getParameter("CHANIS6_23TWF");}
    if(request.getParameter("CHANIS6_23TWM")!=null && !request.getParameter("CHANIS6_23TWM").equals("") ){ CHANIS6_23TWM=request.getParameter("CHANIS6_23TWM");}
    if(request.getParameter("CHANIS6_23TW")!=null && !request.getParameter("CHANIS6_23TW").equals("") ){ CHANIS6_23TW=request.getParameter("CHANIS6_23TW");}
    if(request.getParameter("CHANIS24_59NormalweightF")!=null && !request.getParameter("CHANIS24_59NormalweightF").equals("") ){ CHANIS24_59NormalweightF=request.getParameter("CHANIS24_59NormalweightF");}
    if(request.getParameter("CHANIS24_59NormalweightM")!=null && !request.getParameter("CHANIS24_59NormalweightM").equals("") ){ CHANIS24_59NormalweightM=request.getParameter("CHANIS24_59NormalweightM");}
    if(request.getParameter("CHANIS24_59NormalweightT")!=null && !request.getParameter("CHANIS24_59NormalweightT").equals("") ){ CHANIS24_59NormalweightT=request.getParameter("CHANIS24_59NormalweightT");}
    if(request.getParameter("CHANIS24_59UnderweightF")!=null && !request.getParameter("CHANIS24_59UnderweightF").equals("") ){ CHANIS24_59UnderweightF=request.getParameter("CHANIS24_59UnderweightF");}
    if(request.getParameter("CHANIS24_59UnderweightM")!=null && !request.getParameter("CHANIS24_59UnderweightM").equals("") ){ CHANIS24_59UnderweightM=request.getParameter("CHANIS24_59UnderweightM");}
    if(request.getParameter("CHANIS24_59UnderweightT")!=null && !request.getParameter("CHANIS24_59UnderweightT").equals("") ){ CHANIS24_59UnderweightT=request.getParameter("CHANIS24_59UnderweightT");}
    if(request.getParameter("CHANIS24_59sevUnderweightF")!=null && !request.getParameter("CHANIS24_59sevUnderweightF").equals("") ){ CHANIS24_59sevUnderweightF=request.getParameter("CHANIS24_59sevUnderweightF");}
    if(request.getParameter("CHANIS24_59sevUnderweightM")!=null && !request.getParameter("CHANIS24_59sevUnderweightM").equals("") ){ CHANIS24_59sevUnderweightM=request.getParameter("CHANIS24_59sevUnderweightM");}
    if(request.getParameter("CHANIS24_59sevUnderweightT")!=null && !request.getParameter("CHANIS24_59sevUnderweightT").equals("") ){ CHANIS24_59sevUnderweightT=request.getParameter("CHANIS24_59sevUnderweightT");}
    if(request.getParameter("CHANIS24_59OverweightF")!=null && !request.getParameter("CHANIS24_59OverweightF").equals("") ){ CHANIS24_59OverweightF=request.getParameter("CHANIS24_59OverweightF");}
    if(request.getParameter("CHANIS24_59OverweightM")!=null && !request.getParameter("CHANIS24_59OverweightM").equals("") ){ CHANIS24_59OverweightM=request.getParameter("CHANIS24_59OverweightM");}
     if(request.getParameter("CHANIS24_59OverweightT")!=null && !request.getParameter("CHANIS24_59OverweightT").equals("") ){ CHANIS24_59OverweightT=request.getParameter("CHANIS24_59OverweightT");}
    if(request.getParameter("CHANIS24_59ObeseF")!=null && !request.getParameter("CHANIS24_59ObeseF").equals("") ){ CHANIS24_59ObeseF=request.getParameter("CHANIS24_59ObeseF");}
    if(request.getParameter("CHANIS24_59ObeseM")!=null && !request.getParameter("CHANIS24_59ObeseM").equals("") ){ CHANIS24_59ObeseM=request.getParameter("CHANIS24_59ObeseM");}
    if(request.getParameter("CHANIS24_59ObeseT")!=null && !request.getParameter("CHANIS24_59ObeseT").equals("") ){ CHANIS24_59ObeseT=request.getParameter("CHANIS24_59ObeseT");}
    if(request.getParameter("CHANIS24_59TWF")!=null && !request.getParameter("CHANIS24_59TWF").equals("") ){ CHANIS24_59TWF=request.getParameter("CHANIS24_59TWF");}
    if(request.getParameter("CHANIS24_59TWM")!=null && !request.getParameter("CHANIS24_59TWM").equals("") ){ CHANIS24_59TWM=request.getParameter("CHANIS24_59TWM");}
    if(request.getParameter("CHANIS24_59TW")!=null && !request.getParameter("CHANIS24_59TW").equals("") ){ CHANIS24_59TW=request.getParameter("CHANIS24_59TW");}
    if(request.getParameter("CHANISMUACNormalF")!=null && !request.getParameter("CHANISMUACNormalF").equals("") ){ CHANISMUACNormalF=request.getParameter("CHANISMUACNormalF");}
    if(request.getParameter("CHANISMUACNormalM")!=null && !request.getParameter("CHANISMUACNormalM").equals("") ){ CHANISMUACNormalM=request.getParameter("CHANISMUACNormalM");}
    if(request.getParameter("CHANISMUACNormalT")!=null && !request.getParameter("CHANISMUACNormalT").equals("") ){ CHANISMUACNormalT=request.getParameter("CHANISMUACNormalT");}
    if(request.getParameter("CHANISMUACModerateF")!=null && !request.getParameter("CHANISMUACModerateF").equals("") ){ CHANISMUACModerateF=request.getParameter("CHANISMUACModerateF");}
    if(request.getParameter("CHANISMUACModerateM")!=null && !request.getParameter("CHANISMUACModerateM").equals("") ){ CHANISMUACModerateM=request.getParameter("CHANISMUACModerateM");}
    if(request.getParameter("CHANISMUACModerateT")!=null && !request.getParameter("CHANISMUACModerateT").equals("") ){ CHANISMUACModerateT=request.getParameter("CHANISMUACModerateT");}
    if(request.getParameter("CHANISMUACSevereF")!=null && !request.getParameter("CHANISMUACSevereF").equals("") ){ CHANISMUACSevereF=request.getParameter("CHANISMUACSevereF");}
    if(request.getParameter("CHANISMUACSevereM")!=null && !request.getParameter("CHANISMUACSevereM").equals("") ){ CHANISMUACSevereM=request.getParameter("CHANISMUACSevereM");}
    if(request.getParameter("CHANISMUACSevereT")!=null && !request.getParameter("CHANISMUACSevereT").equals("") ){ CHANISMUACSevereT=request.getParameter("CHANISMUACSevereT");}
    if(request.getParameter("CHANISMUACMeasuredF")!=null && !request.getParameter("CHANISMUACMeasuredF").equals("") ){ CHANISMUACMeasuredF=request.getParameter("CHANISMUACMeasuredF");}
    if(request.getParameter("CHANISMUACMeasuredM")!=null && !request.getParameter("CHANISMUACMeasuredM").equals("") ){ CHANISMUACMeasuredM=request.getParameter("CHANISMUACMeasuredM");}
    if(request.getParameter("CHANISMUACMeasuredT")!=null && !request.getParameter("CHANISMUACMeasuredT").equals("") ){ CHANISMUACMeasuredT=request.getParameter("CHANISMUACMeasuredT");}

    //=================================================================CHANIS HEIGHT=======================================================
    
 
    if(request.getParameter("CHANIS0_5NormalHeightF")!=null && !request.getParameter("CHANIS0_5NormalHeightF").equals("") ){ CHANIS0_5NormalHeightF=request.getParameter("CHANIS0_5NormalHeightF");}
    if(request.getParameter("CHANIS0_5NormalHeightM")!=null && !request.getParameter("CHANIS0_5NormalHeightM").equals("") ){ CHANIS0_5NormalHeightM=request.getParameter("CHANIS0_5NormalHeightM");}
    if(request.getParameter("CHANIS0_5NormalHeightM")!=null && !request.getParameter("CHANIS0_5NormalHeightM").equals("") ){ CHANIS0_5NormalHeightM=request.getParameter("CHANIS0_5NormalHeightM");}
    if(request.getParameter("CHANIS0_5NormalHeightT")!=null && !request.getParameter("CHANIS0_5NormalHeightT").equals("") ){ CHANIS0_5NormalHeightT=request.getParameter("CHANIS0_5NormalHeightT");}
    if(request.getParameter("CHANIS0_5StuntedF")!=null && !request.getParameter("CHANIS0_5StuntedF").equals("") ){ CHANIS0_5StuntedF=request.getParameter("CHANIS0_5StuntedF");}
    if(request.getParameter("CHANIS0_5StuntedM")!=null && !request.getParameter("CHANIS0_5StuntedM").equals("") ){ CHANIS0_5StuntedM=request.getParameter("CHANIS0_5StuntedM");}
    if(request.getParameter("CHANIS0_5StuntedT")!=null && !request.getParameter("CHANIS0_5StuntedT").equals("") ){ CHANIS0_5StuntedT=request.getParameter("CHANIS0_5StuntedT");}
    if(request.getParameter("CHANIS0_5sevStuntedF")!=null && !request.getParameter("CHANIS0_5sevStuntedF").equals("") ){ CHANIS0_5sevStuntedF=request.getParameter("CHANIS0_5sevStuntedF");}
    if(request.getParameter("CHANIS0_5sevStuntedM")!=null && !request.getParameter("CHANIS0_5sevStuntedM").equals("") ){ CHANIS0_5sevStuntedM=request.getParameter("CHANIS0_5sevStuntedM");}
    if(request.getParameter("CHANIS0_5sevStuntedT")!=null && !request.getParameter("CHANIS0_5sevStuntedT").equals("") ){ CHANIS0_5sevStuntedT=request.getParameter("CHANIS0_5sevStuntedT");}
    if(request.getParameter("CHANIS0_5TMeasF")!=null && !request.getParameter("CHANIS0_5TMeasF").equals("") ){ CHANIS0_5TMeasF=request.getParameter("CHANIS0_5TMeasF");}
    if(request.getParameter("CHANIS0_5TMeasM")!=null && !request.getParameter("CHANIS0_5TMeasM").equals("") ){ CHANIS0_5TMeasM=request.getParameter("CHANIS0_5TMeasM");}
    if(request.getParameter("CHANIS0_5TMeas")!=null && !request.getParameter("CHANIS0_5TMeas").equals("") ){ CHANIS0_5TMeas=request.getParameter("CHANIS0_5TMeas");}
    if(request.getParameter("CHANIS6_23NormalHeightF")!=null && !request.getParameter("CHANIS6_23NormalHeightF").equals("") ){ CHANIS6_23NormalHeightF=request.getParameter("CHANIS6_23NormalHeightF");}
    if(request.getParameter("CHANIS6_23NormalHeightM")!=null && !request.getParameter("CHANIS6_23NormalHeightM").equals("") ){ CHANIS6_23NormalHeightM=request.getParameter("CHANIS6_23NormalHeightM");}
    if(request.getParameter("CHANIS6_23NormalHeightT")!=null && !request.getParameter("CHANIS6_23NormalHeightT").equals("") ){ CHANIS6_23NormalHeightT=request.getParameter("CHANIS6_23NormalHeightT");}
    if(request.getParameter("CHANIS6_23StuntedF")!=null && !request.getParameter("CHANIS6_23StuntedF").equals("") ){ CHANIS6_23StuntedF=request.getParameter("CHANIS6_23StuntedF");}
    if(request.getParameter("CHANIS6_23StuntedM")!=null && !request.getParameter("CHANIS6_23StuntedM").equals("") ){ CHANIS6_23StuntedM=request.getParameter("CHANIS6_23StuntedM");}
    if(request.getParameter("CHANIS6_23StuntedT")!=null && !request.getParameter("CHANIS6_23StuntedT").equals("") ){ CHANIS6_23StuntedT=request.getParameter("CHANIS6_23StuntedT");}
    if(request.getParameter("CHANIS6_23sevStuntedF")!=null && !request.getParameter("CHANIS6_23sevStuntedF").equals("") ){ CHANIS6_23sevStuntedF=request.getParameter("CHANIS6_23sevStuntedF");}
    if(request.getParameter("CHANIS6_23sevStuntedM")!=null && !request.getParameter("CHANIS6_23sevStuntedM").equals("") ){ CHANIS6_23sevStuntedM=request.getParameter("CHANIS6_23sevStuntedM");}
    if(request.getParameter("CHANIS6_23sevStuntedT")!=null && !request.getParameter("CHANIS6_23sevStuntedT").equals("") ){ CHANIS6_23sevStuntedT=request.getParameter("CHANIS6_23sevStuntedT");}
    if(request.getParameter("CHANIS6_23TMeasF")!=null && !request.getParameter("CHANIS6_23TMeasF").equals("") ){ CHANIS6_23TMeasF=request.getParameter("CHANIS6_23TMeasF");}
    if(request.getParameter("CHANIS6_23TMeasM")!=null && !request.getParameter("CHANIS6_23TMeasM").equals("") ){ CHANIS6_23TMeasM=request.getParameter("CHANIS6_23TMeasM");}
    if(request.getParameter("CHANIS6_23TMeas")!=null && !request.getParameter("CHANIS6_23TMeas").equals("") ){ CHANIS6_23TMeas=request.getParameter("CHANIS6_23TMeas");}
    if(request.getParameter("CHANIS24_59NormalHeightF")!=null && !request.getParameter("CHANIS24_59NormalHeightF").equals("") ){ CHANIS24_59NormalHeightF=request.getParameter("CHANIS24_59NormalHeightF");}
    if(request.getParameter("CHANIS24_59NormalHeightM")!=null && !request.getParameter("CHANIS24_59NormalHeightM").equals("") ){ CHANIS24_59NormalHeightM=request.getParameter("CHANIS24_59NormalHeightM");}
    if(request.getParameter("CHANIS24_59NormalHeightT")!=null && !request.getParameter("CHANIS24_59NormalHeightT").equals("") ){ CHANIS24_59NormalHeightT=request.getParameter("CHANIS24_59NormalHeightT");}
    if(request.getParameter("CHANIS24_59StuntedF")!=null && !request.getParameter("CHANIS24_59StuntedF").equals("") ){ CHANIS24_59StuntedF=request.getParameter("CHANIS24_59StuntedF");}
    if(request.getParameter("CHANIS24_59StuntedM")!=null && !request.getParameter("CHANIS24_59StuntedM").equals("") ){ CHANIS24_59StuntedM=request.getParameter("CHANIS24_59StuntedM");}
    if(request.getParameter("CHANIS24_59StuntedT")!=null && !request.getParameter("CHANIS24_59StuntedT").equals("") ){ CHANIS24_59StuntedT=request.getParameter("CHANIS24_59StuntedT");}
    if(request.getParameter("CHANIS24_59sevStuntedF")!=null && !request.getParameter("CHANIS24_59sevStuntedF").equals("") ){ CHANIS24_59sevStuntedF=request.getParameter("CHANIS24_59sevStuntedF");}
    if(request.getParameter("CHANIS24_59sevStuntedM")!=null && !request.getParameter("CHANIS24_59sevStuntedM").equals("") ){ CHANIS24_59sevStuntedM=request.getParameter("CHANIS24_59sevStuntedM");}
    if(request.getParameter("CHANIS24_59sevStuntedT")!=null && !request.getParameter("CHANIS24_59sevStuntedT").equals("") ){ CHANIS24_59sevStuntedT=request.getParameter("CHANIS24_59sevStuntedT");}
    if(request.getParameter("CHANIS24_59TMeasF")!=null && !request.getParameter("CHANIS24_59TMeasF").equals("") ){ CHANIS24_59TMeasF=request.getParameter("CHANIS24_59TMeasF");}
    if(request.getParameter("CHANIS24_59TMeasM")!=null && !request.getParameter("CHANIS24_59TMeasM").equals("") ){ CHANIS24_59TMeasM=request.getParameter("CHANIS24_59TMeasM");}
    if(request.getParameter("CHANIS24_59TMeas")!=null && !request.getParameter("CHANIS24_59TMeas").equals("") ){ CHANIS24_59TMeas=request.getParameter("CHANIS24_59TMeas");}
    if(request.getParameter("CHANIS0_59NewVisitsF")!=null && !request.getParameter("CHANIS0_59NewVisitsF").equals("") ){ CHANIS0_59NewVisitsF=request.getParameter("CHANIS0_59NewVisitsF");}
    if(request.getParameter("CHANIS0_59NewVisitsM")!=null && !request.getParameter("CHANIS0_59NewVisitsM").equals("") ){ CHANIS0_59NewVisitsM=request.getParameter("CHANIS0_59NewVisitsM");}
    if(request.getParameter("CHANIS0_59NewVisitsT")!=null && !request.getParameter("CHANIS0_59NewVisitsT").equals("") ){ CHANIS0_59NewVisitsT=request.getParameter("CHANIS0_59NewVisitsT");}
    if(request.getParameter("CHANIS0_59KwashiakorF")!=null && !request.getParameter("CHANIS0_59KwashiakorF").equals("") ){ CHANIS0_59KwashiakorF=request.getParameter("CHANIS0_59KwashiakorF");}
    if(request.getParameter("CHANIS0_59KwashiakorM")!=null && !request.getParameter("CHANIS0_59KwashiakorM").equals("") ){ CHANIS0_59KwashiakorM=request.getParameter("CHANIS0_59KwashiakorM");}
    if(request.getParameter("CHANIS0_59KwashiakorT")!=null && !request.getParameter("CHANIS0_59KwashiakorT").equals("") ){ CHANIS0_59KwashiakorT=request.getParameter("CHANIS0_59KwashiakorT");}
    if(request.getParameter("CHANIS0_59MarasmusF")!=null && !request.getParameter("CHANIS0_59MarasmusF").equals("") ){ CHANIS0_59MarasmusF=request.getParameter("CHANIS0_59MarasmusF");}
    if(request.getParameter("CHANIS0_59MarasmusM")!=null && !request.getParameter("CHANIS0_59MarasmusM").equals("") ){ CHANIS0_59MarasmusM=request.getParameter("CHANIS0_59MarasmusM");}
    if(request.getParameter("CHANIS0_59MarasmusT")!=null && !request.getParameter("CHANIS0_59MarasmusT").equals("") ){ CHANIS0_59MarasmusT=request.getParameter("CHANIS0_59MarasmusT");}
    if(request.getParameter("CHANIS0_59FalgrowthF")!=null && !request.getParameter("CHANIS0_59FalgrowthF").equals("") ){ CHANIS0_59FalgrowthF=request.getParameter("CHANIS0_59FalgrowthF");}
    if(request.getParameter("CHANIS0_59FalgrowthM")!=null && !request.getParameter("CHANIS0_59FalgrowthM").equals("") ){ CHANIS0_59FalgrowthM=request.getParameter("CHANIS0_59FalgrowthM");}
    if(request.getParameter("CHANIS0_59FalgrowthT")!=null && !request.getParameter("CHANIS0_59FalgrowthT").equals("") ){ CHANIS0_59FalgrowthT=request.getParameter("CHANIS0_59FalgrowthT");}
    if(request.getParameter("CHANIS0_59F")!=null && !request.getParameter("CHANIS0_59F").equals("") ){ CHANIS0_59F=request.getParameter("CHANIS0_59F");}
    if(request.getParameter("CHANIS0_59M")!=null && !request.getParameter("CHANIS0_59M").equals("") ){ CHANIS0_59M=request.getParameter("CHANIS0_59M");}
    if(request.getParameter("CHANIS0_59T")!=null && !request.getParameter("CHANIS0_59T").equals("") ){ CHANIS0_59T=request.getParameter("CHANIS0_59T");}
    if(request.getParameter("CHANIS0_5EXCLBreastF")!=null && !request.getParameter("CHANIS0_5EXCLBreastF").equals("") ){ CHANIS0_5EXCLBreastF=request.getParameter("CHANIS0_5EXCLBreastF");}
    if(request.getParameter("CHANIS0_5EXCLBreastM")!=null && !request.getParameter("CHANIS0_5EXCLBreastM").equals("") ){ CHANIS0_5EXCLBreastM=request.getParameter("CHANIS0_5EXCLBreastM");}
    if(request.getParameter("CHANIS0_5EXCLBreastT")!=null && !request.getParameter("CHANIS0_5EXCLBreastT").equals("") ){ CHANIS0_5EXCLBreastT=request.getParameter("CHANIS0_5EXCLBreastT");}
    if(request.getParameter("CHANIS12_59DewormedF")!=null && !request.getParameter("CHANIS12_59DewormedF").equals("") ){ CHANIS12_59DewormedF=request.getParameter("CHANIS12_59DewormedF");}
    if(request.getParameter("CHANIS12_59DewormedM")!=null && !request.getParameter("CHANIS12_59DewormedM").equals("") ){ CHANIS12_59DewormedM=request.getParameter("CHANIS12_59DewormedM");}
    if(request.getParameter("CHANIS12_59DewormedT")!=null && !request.getParameter("CHANIS12_59DewormedT").equals("") ){ CHANIS12_59DewormedT=request.getParameter("CHANIS12_59DewormedT");}
    if(request.getParameter("CHANIS6_23MNPsF")!=null && !request.getParameter("CHANIS6_23MNPsF").equals("") ){ CHANIS6_23MNPsF=request.getParameter("CHANIS6_23MNPsF");}
    if(request.getParameter("CHANIS6_23MNPsM")!=null && !request.getParameter("CHANIS6_23MNPsM").equals("") ){ CHANIS6_23MNPsM=request.getParameter("CHANIS6_23MNPsM");}
    if(request.getParameter("CHANIS6_23MNPsT")!=null && !request.getParameter("CHANIS6_23MNPsT").equals("") ){ CHANIS6_23MNPsT=request.getParameter("CHANIS6_23MNPsT");}
     if(request.getParameter("CHANIS0_59DisabilityF")!=null && !request.getParameter("CHANIS0_59DisabilityF").equals("") ){ CHANIS0_59DisabilityF=request.getParameter("CHANIS0_59DisabilityF");}
    if(request.getParameter("CHANIS0_59DisabilityM")!=null && !request.getParameter("CHANIS0_59DisabilityM").equals("") ){ CHANIS0_59DisabilityM=request.getParameter("CHANIS0_59DisabilityM");}
    if(request.getParameter("CHANIS0_59DisabilityT")!=null && !request.getParameter("CHANIS0_59DisabilityT").equals("") ){ CHANIS0_59DisabilityT=request.getParameter("CHANIS0_59DisabilityT");}
    
    //================================================Cervical cancer Screening CS====================================================================

 if(request.getParameter("CCSVVH24")!=null && !request.getParameter("CCSVVH24").equals("") ){ CCSVVH24=request.getParameter("CCSVVH24");}
    if(request.getParameter("CCSVVH25_49")!=null && !request.getParameter("CCSVVH25_49").equals("") ){ CCSVVH25_49=request.getParameter("CCSVVH25_49");}
    if(request.getParameter("CCSVVH50")!=null && !request.getParameter("CCSVVH50").equals("") ){ CCSVVH50=request.getParameter("CCSVVH50");}
    if(request.getParameter("CCSPAPSMEAR24")!=null && !request.getParameter("CCSPAPSMEAR24").equals("") ){ CCSPAPSMEAR24=request.getParameter("CCSPAPSMEAR24");}
    if(request.getParameter("CCSPAPSMEAR25_49")!=null && !request.getParameter("CCSPAPSMEAR25_49").equals("") ){ CCSPAPSMEAR25_49=request.getParameter("CCSPAPSMEAR25_49");}
    if(request.getParameter("CCSPAPSMEAR50")!=null && !request.getParameter("CCSPAPSMEAR50").equals("") ){ CCSPAPSMEAR50=request.getParameter("CCSPAPSMEAR50");}
    if(request.getParameter("CCSHPV24")!=null && !request.getParameter("CCSHPV24").equals("") ){ CCSHPV24=request.getParameter("CCSHPV24");}
    if(request.getParameter("CCSHPV25_49")!=null && !request.getParameter("CCSHPV25_49").equals("") ){ CCSHPV25_49=request.getParameter("CCSHPV25_49");}
     if(request.getParameter("CCSHPV50")!=null && !request.getParameter("CCSHPV50").equals("") ){ CCSHPV50=request.getParameter("CCSHPV50");}
    if(request.getParameter("CCSVIAVILIPOS24")!=null && !request.getParameter("CCSVIAVILIPOS24").equals("") ){ CCSVIAVILIPOS24=request.getParameter("CCSVIAVILIPOS24");}
    if(request.getParameter("CCSVIAVILIPOS25_49")!=null && !request.getParameter("CCSVIAVILIPOS25_49").equals("") ){ CCSVIAVILIPOS25_49=request.getParameter("CCSVIAVILIPOS25_49");}
    if(request.getParameter("CCSVIAVILIPOS50")!=null && !request.getParameter("CCSVIAVILIPOS50").equals("") ){ CCSVIAVILIPOS50=request.getParameter("CCSVIAVILIPOS50");}
    if(request.getParameter("CCSCYTOLPOS24")!=null && !request.getParameter("CCSCYTOLPOS24").equals("") ){ CCSCYTOLPOS24=request.getParameter("CCSCYTOLPOS24");}
    if(request.getParameter("CCSCYTOLPOS25_49")!=null && !request.getParameter("CCSCYTOLPOS25_49").equals("") ){ CCSCYTOLPOS25_49=request.getParameter("CCSCYTOLPOS25_49");}
    if(request.getParameter("CCSCYTOLPOS50")!=null && !request.getParameter("CCSCYTOLPOS50").equals("") ){ CCSCYTOLPOS50=request.getParameter("CCSCYTOLPOS50");}
    if(request.getParameter("CCSHPVPOS24")!=null && !request.getParameter("CCSHPVPOS24").equals("") ){ CCSHPVPOS24=request.getParameter("CCSHPVPOS24");}
    if(request.getParameter("CCSHPVPOS25_49")!=null && !request.getParameter("CCSHPVPOS25_49").equals("") ){ CCSHPVPOS25_49=request.getParameter("CCSHPVPOS25_49");}
    if(request.getParameter("CCSHPVPOS50")!=null && !request.getParameter("CCSHPVPOS50").equals("") ){ CCSHPVPOS50=request.getParameter("CCSHPVPOS50");}
    
    if(request.getParameter("CCSCryotherapy24")!=null && !request.getParameter("CCSCryotherapy24").equals("") ){ CCSCryotherapy24=request.getParameter("CCSCryotherapy24");}
    if(request.getParameter("CCSCryotherapy25_49")!=null && !request.getParameter("CCSCryotherapy25_49").equals("") ){ CCSCryotherapy25_49=request.getParameter("CCSCryotherapy25_49");}
    if(request.getParameter("CCSCryotherapy50")!=null && !request.getParameter("CCSCryotherapy50").equals("") ){ CCSCryotherapy50=request.getParameter("CCSCryotherapy50");}
    
    

    if(request.getParameter("CCSSUSPICIOUSLES24")!=null && !request.getParameter("CCSSUSPICIOUSLES24").equals("") ){ CCSSUSPICIOUSLES24=request.getParameter("CCSSUSPICIOUSLES24");}
    if(request.getParameter("CCSSUSPICIOUSLES25_49")!=null && !request.getParameter("CCSSUSPICIOUSLES25_49").equals("") ){ CCSSUSPICIOUSLES25_49=request.getParameter("CCSSUSPICIOUSLES25_49");}
    if(request.getParameter("CCSSUSPICIOUSLES50")!=null && !request.getParameter("CCSSUSPICIOUSLES50").equals("") ){ CCSSUSPICIOUSLES50=request.getParameter("CCSSUSPICIOUSLES50");}
    
    
    if(request.getParameter("CCSLEEP24")!=null && !request.getParameter("CCSLEEP24").equals("") ){ CCSLEEP24=request.getParameter("CCSLEEP24");}
    if(request.getParameter("CCSLEEP25_49")!=null && !request.getParameter("CCSLEEP25_49").equals("") ){ CCSLEEP25_49=request.getParameter("CCSLEEP25_49");}
    if(request.getParameter("CCSLEEP50")!=null && !request.getParameter("CCSLEEP50").equals("") ){ CCSLEEP50=request.getParameter("CCSLEEP50");}
    if(request.getParameter("CCSHIVPOSSCREENED24")!=null && !request.getParameter("CCSHIVPOSSCREENED24").equals("") ){ CCSHIVPOSSCREENED24=request.getParameter("CCSHIVPOSSCREENED24");}
    if(request.getParameter("CCSHIVPOSSCREENED25_49")!=null && !request.getParameter("CCSHIVPOSSCREENED25_49").equals("") ){ CCSHIVPOSSCREENED25_49=request.getParameter("CCSHIVPOSSCREENED25_49");}
    if(request.getParameter("CCSHIVPOSSCREENED50")!=null && !request.getParameter("CCSHIVPOSSCREENED50").equals("") ){ CCSHIVPOSSCREENED50=request.getParameter("CCSHIVPOSSCREENED50");}

 //===========================================================PNC===========================================================   
    
    if(request.getParameter("PNCBreastExam")!=null && !request.getParameter("PNCBreastExam").equals("") ){ PNCBreastExam=request.getParameter("PNCBreastExam");}
    if(request.getParameter("PNCCounselled")!=null && !request.getParameter("PNCCounselled").equals("") ){ PNCCounselled=request.getParameter("PNCCounselled");}
    if(request.getParameter("PNCFistula")!=null && !request.getParameter("PNCFistula").equals("") ){ PNCFistula=request.getParameter("PNCFistula");}
    if(request.getParameter("PNCExerNegative")!=null && !request.getParameter("PNCExerNegative").equals("") ){ PNCExerNegative=request.getParameter("PNCExerNegative");}
    if(request.getParameter("PNCExerPositive")!=null && !request.getParameter("PNCExerPositive").equals("") ){ PNCExerPositive=request.getParameter("PNCExerPositive");}
    if(request.getParameter("PNCCCSsuspect")!=null && !request.getParameter("PNCCCSsuspect").equals("") ){ PNCCCSsuspect=request.getParameter("PNCCCSsuspect");}
    if(request.getParameter("PNCmotherspostpartum2_3")!=null && !request.getParameter("PNCmotherspostpartum2_3").equals("") ){ PNCmotherspostpartum2_3=request.getParameter("PNCmotherspostpartum2_3");}
    if(request.getParameter("PNCmotherspostpartum6")!=null && !request.getParameter("PNCmotherspostpartum6").equals("") ){ PNCmotherspostpartum6=request.getParameter("PNCmotherspostpartum6");}
     if(request.getParameter("PNCinfantspostpartum2_3")!=null && !request.getParameter("PNCinfantspostpartum2_3").equals("") ){ PNCinfantspostpartum2_3=request.getParameter("PNCinfantspostpartum2_3");}
    if(request.getParameter("PNCinfantspostpartum6")!=null && !request.getParameter("PNCinfantspostpartum6").equals("") ){ PNCinfantspostpartum6=request.getParameter("PNCinfantspostpartum6");}
    if(request.getParameter("PNCreferralsfromotherHF")!=null && !request.getParameter("PNCreferralsfromotherHF").equals("") ){ PNCreferralsfromotherHF=request.getParameter("PNCreferralsfromotherHF");}
    if(request.getParameter("PNCreferralsfromotherCU")!=null && !request.getParameter("PNCreferralsfromotherCU").equals("") ){ PNCreferralsfromotherCU=request.getParameter("PNCreferralsfromotherCU");}
    if(request.getParameter("PNCreferralsTootherHF")!=null && !request.getParameter("PNCreferralsTootherHF").equals("") ){ PNCreferralsTootherHF=request.getParameter("PNCreferralsTootherHF");}
    if(request.getParameter("PNCreferralsTootherCU")!=null && !request.getParameter("PNCreferralsTootherCU").equals("") ){ PNCreferralsTootherCU=request.getParameter("PNCreferralsTootherCU");}
    
    
  //=====================================================RS=======================================================
    
	
 
    if(request.getParameter("RsAssessed")!=null && !request.getParameter("RsAssessed").equals("") ){ RsAssessed=request.getParameter("RsAssessed");}
    if(request.getParameter("Rstreated")!=null && !request.getParameter("Rstreated").equals("") ){ Rstreated=request.getParameter("Rstreated");}
    if(request.getParameter("RsRehabilitated")!=null && !request.getParameter("RsRehabilitated").equals("") ){ RsRehabilitated=request.getParameter("RsRehabilitated");}
    if(request.getParameter("Rsreffered")!=null && !request.getParameter("Rsreffered").equals("") ){ Rsreffered=request.getParameter("Rsreffered");}
    if(request.getParameter("RsIntergrated")!=null && !request.getParameter("RsIntergrated").equals("") ){ RsIntergrated=request.getParameter("RsIntergrated");}
   
    
 //=======================================================MSW======================================================  

 
    if(request.getParameter("MSWpscounselling")!=null && !request.getParameter("MSWpscounselling").equals("") ){ MSWpscounselling=request.getParameter("MSWpscounselling");}
    if(request.getParameter("MSWdrugabuse")!=null && !request.getParameter("MSWdrugabuse").equals("") ){ MSWdrugabuse=request.getParameter("MSWdrugabuse");}
    if(request.getParameter("MSWMental")!=null && !request.getParameter("MSWMental").equals("") ){ MSWMental=request.getParameter("MSWMental");}
    if(request.getParameter("MSWAdolescent")!=null && !request.getParameter("MSWAdolescent").equals("") ){ MSWAdolescent=request.getParameter("MSWAdolescent");}
    if(request.getParameter("MSWPsAsses")!=null && !request.getParameter("MSWPsAsses").equals("") ){ MSWPsAsses=request.getParameter("MSWPsAsses");}
    if(request.getParameter("MSWsocialinv")!=null && !request.getParameter("MSWsocialinv").equals("") ){ MSWsocialinv=request.getParameter("MSWsocialinv");}
    if(request.getParameter("MSWsocialRehab")!=null && !request.getParameter("MSWsocialRehab").equals("") ){ MSWsocialRehab=request.getParameter("MSWsocialRehab");}
    if(request.getParameter("MSWoutreach")!=null && !request.getParameter("MSWoutreach").equals("") ){ MSWoutreach=request.getParameter("MSWoutreach");}
    if(request.getParameter("MSWreferrals")!=null && !request.getParameter("MSWreferrals").equals("") ){ MSWreferrals=request.getParameter("MSWreferrals");}
    if(request.getParameter("MSWwaivedpatients")!=null && !request.getParameter("MSWwaivedpatients").equals("") ){ MSWwaivedpatients=request.getParameter("MSWwaivedpatients");}
      
    
   //===========================================PS===========================================================
    
    if(request.getParameter("PsPWDOPD4")!=null && !request.getParameter("PsPWDOPD4").equals("") ){ PsPWDOPD4=request.getParameter("PsPWDOPD4");}
    if(request.getParameter("PsPWDOPD5_19")!=null && !request.getParameter("PsPWDOPD5_19").equals("") ){ PsPWDOPD5_19=request.getParameter("PsPWDOPD5_19");}
    if(request.getParameter("PsPWDOPD20")!=null && !request.getParameter("PsPWDOPD20").equals("") ){ PsPWDOPD20=request.getParameter("PsPWDOPD20");}
    if(request.getParameter("PsPWDinpatient4")!=null && !request.getParameter("PsPWDinpatient4").equals("") ){ PsPWDinpatient4=request.getParameter("PsPWDinpatient4");}
    if(request.getParameter("PsPWDinpatient5_19")!=null && !request.getParameter("PsPWDinpatient5_19").equals("") ){ PsPWDinpatient5_19=request.getParameter("PsPWDinpatient5_19");}
    if(request.getParameter("PsPWDinpatient20")!=null && !request.getParameter("PsPWDinpatient20").equals("") ){ PsPWDinpatient20=request.getParameter("PsPWDinpatient20");}
    
    if(request.getParameter("PsotherOPD4")!=null && !request.getParameter("PsotherOPD4").equals("") ){ PsotherOPD4=request.getParameter("PsotherOPD4");}
    if(request.getParameter("PsotherOPD5_19")!=null && !request.getParameter("PsotherOPD5_19").equals("") ){ PsotherOPD5_19=request.getParameter("PsotherOPD5_19");}
    if(request.getParameter("PsotherOPD20")!=null && !request.getParameter("PsotherOPD20").equals("") ){ PsotherOPD20=request.getParameter("PsotherOPD20");}
    if(request.getParameter("Psotherinpatient4")!=null && !request.getParameter("Psotherinpatient4").equals("") ){ Psotherinpatient4=request.getParameter("Psotherinpatient4");}
    if(request.getParameter("Psotherinpatient5_19")!=null && !request.getParameter("Psotherinpatient5_19").equals("") ){ Psotherinpatient5_19=request.getParameter("Psotherinpatient5_19");}
    if(request.getParameter("Psotherinpatient20")!=null && !request.getParameter("Psotherinpatient20").equals("") ){ Psotherinpatient20=request.getParameter("Psotherinpatient20");}
    
    
    if(request.getParameter("PsTreatments4")!=null && !request.getParameter("PsTreatments4").equals("") ){ PsTreatments4=request.getParameter("PsTreatments4");}
    if(request.getParameter("PsTreatments5_19")!=null && !request.getParameter("PsTreatments5_19").equals("") ){ PsTreatments5_19=request.getParameter("PsTreatments5_19");}
    if(request.getParameter("PsTreatments20")!=null && !request.getParameter("PsTreatments20").equals("") ){ PsTreatments20=request.getParameter("PsTreatments20");}
    if(request.getParameter("PsAssessed4")!=null && !request.getParameter("PsAssessed4").equals("") ){ PsAssessed4=request.getParameter("PsAssessed4");}
    if(request.getParameter("PsAssessed5_19")!=null && !request.getParameter("PsAssessed5_19").equals("") ){ PsAssessed5_19=request.getParameter("PsAssessed5_19");}
    if(request.getParameter("PsAssessed20")!=null && !request.getParameter("PsAssessed20").equals("") ){ PsAssessed20=request.getParameter("PsAssessed20");}
    if(request.getParameter("PsServices4")!=null && !request.getParameter("PsServices4").equals("") ){ PsServices4=request.getParameter("PsServices4");}
    if(request.getParameter("PsServices5_19")!=null && !request.getParameter("PsServices5_19").equals("") ){ PsServices5_19=request.getParameter("PsServices5_19");}
    if(request.getParameter("PsServices20")!=null && !request.getParameter("PsServices20").equals("") ){ PsServices20=request.getParameter("PsServices20");}
    if(request.getParameter("PsANCCounsel5_19")!=null && !request.getParameter("PsANCCounsel5_19").equals("") ){ PsANCCounsel5_19=request.getParameter("PsANCCounsel5_19");}
    if(request.getParameter("PsANCCounsel20")!=null && !request.getParameter("PsANCCounsel20").equals("") ){ PsANCCounsel20=request.getParameter("PsANCCounsel20");}
    if(request.getParameter("PsExercise5_19")!=null && !request.getParameter("PsExercise5_19").equals("") ){ PsExercise5_19=request.getParameter("PsExercise5_19");}
    if(request.getParameter("PsExercise20")!=null && !request.getParameter("PsExercise20").equals("") ){ PsExercise20=request.getParameter("PsExercise20");}
    if(request.getParameter("PsFIFcollected5_19")!=null && !request.getParameter("PsFIFcollected5_19").equals("") ){ PsFIFcollected5_19=request.getParameter("PsFIFcollected5_19");}
    if(request.getParameter("PsFIFcollected20")!=null && !request.getParameter("PsFIFcollected20").equals("") ){ PsFIFcollected20=request.getParameter("PsFIFcollected20");}
    if(request.getParameter("PsFIFwaived5_19")!=null && !request.getParameter("PsFIFwaived5_19").equals("") ){ PsFIFwaived5_19=request.getParameter("PsFIFwaived5_19");}
    if(request.getParameter("PsFIFwaived20")!=null && !request.getParameter("PsFIFwaived20").equals("") ){ PsFIFwaived20=request.getParameter("PsFIFwaived20");}
    if(request.getParameter("PsFIFexempted4")!=null && !request.getParameter("PsFIFexempted4").equals("") ){ PsFIFexempted4=request.getParameter("PsFIFexempted4");}
    if(request.getParameter("PsFIFexempted5_19")!=null && !request.getParameter("PsFIFexempted5_19").equals("") ){ PsFIFexempted5_19=request.getParameter("PsFIFexempted5_19");}
    if(request.getParameter("PsFIFexempted20")!=null && !request.getParameter("PsFIFexempted20").equals("") ){ PsFIFexempted20=request.getParameter("PsFIFexempted20");}
    if(request.getParameter("PsDiasbilitymeeting4")!=null && !request.getParameter("PsDiasbilitymeeting4").equals("") ){ PsDiasbilitymeeting4=request.getParameter("PsDiasbilitymeeting4");}
    if(request.getParameter("PsDiasbilitymeeting5_19")!=null && !request.getParameter("PsDiasbilitymeeting5_19").equals("") ){ PsDiasbilitymeeting5_19=request.getParameter("PsDiasbilitymeeting5_19");}
    if(request.getParameter("PsDiasbilitymeeting20")!=null && !request.getParameter("PsDiasbilitymeeting20").equals("") ){ PsDiasbilitymeeting20=request.getParameter("PsDiasbilitymeeting20");}
   
    
   
  
  
  
  /***
  
          
  if(request.getParameter("VCTClient_Couns_CM")!=null  && !request.getParameter("VCTClient_Couns_CM").equals("")){VCTClient_Couns_CM=request.getParameter("VCTClient_Couns_CM");}
  if(request.getParameter("VCTClient_Couns_CF")!=null  && !request.getParameter("VCTClient_Couns_CF").equals("")){VCTClient_Couns_CF=request.getParameter("VCTClient_Couns_CF");}
  if(request.getParameter("VCTClient_Couns_AM")!=null  && !request.getParameter("VCTClient_Couns_AM").equals("")){VCTClient_Couns_AM=request.getParameter("VCTClient_Couns_AM");}
  if(request.getParameter("VCTClient_Couns_AF")!=null  && !request.getParameter("VCTClient_Couns_AF").equals("")){VCTClient_Couns_AF=request.getParameter("VCTClient_Couns_AF");}
  if(request.getParameter("VCTClient_Couns_TOT")!=null  && !request.getParameter("VCTClient_Couns_TOT").equals("")){VCTClient_Couns_TOT=request.getParameter("VCTClient_Couns_TOT");}
  if(request.getParameter("VCTClient_Tested_CM")!=null  && !request.getParameter("VCTClient_Tested_CM").equals("")){VCTClient_Tested_CM=request.getParameter("VCTClient_Tested_CM");}
  if(request.getParameter("VCTClient_Tested_CF")!=null  && !request.getParameter("VCTClient_Tested_CF").equals("")){VCTClient_Tested_CF=request.getParameter("VCTClient_Tested_CF");}
  if(request.getParameter("VCTClient_Tested_AM")!=null  && !request.getParameter("VCTClient_Tested_AM").equals("")){VCTClient_Tested_AM=request.getParameter("VCTClient_Tested_AM");}
  if(request.getParameter("VCTClient_Tested_AF")!=null  && !request.getParameter("VCTClient_Tested_AF").equals("")){VCTClient_Tested_AF=request.getParameter("VCTClient_Tested_AF");}
  if(request.getParameter("VCTClient_Tested_TOT")!=null  && !request.getParameter("VCTClient_Tested_TOT").equals("")){VCTClient_Tested_TOT=request.getParameter("VCTClient_Tested_TOT");}
  if(request.getParameter("VCTClient_HIV_CM")!=null  && !request.getParameter("VCTClient_HIV_CM").equals("")){VCTClient_HIV_CM=request.getParameter("VCTClient_HIV_CM");}
  if(request.getParameter("VCTClient_HIV_CF")!=null  && !request.getParameter("VCTClient_HIV_CF").equals("")){VCTClient_HIV_CF=request.getParameter("VCTClient_HIV_CF");}
  if(request.getParameter("VCTClient_HIV_AM")!=null   && !request.getParameter("VCTClient_HIV_AM").equals("")){VCTClient_HIV_AM=request.getParameter("VCTClient_HIV_AM");}
  if(request.getParameter("VCTClient_HIV_AF")!=null  && !request.getParameter("VCTClient_HIV_AF").equals("")){VCTClient_HIV_AF=request.getParameter("VCTClient_HIV_AF");}
  if(request.getParameter("VCTClient_HIV_TOT")!=null  && !request.getParameter("VCTClient_HIV_TOT").equals("")){VCTClient_HIV_TOT=request.getParameter("VCTClient_HIV_TOT");}
  if(request.getParameter("VCTPartner_Couns_TOT")!=null  && !request.getParameter("VCTPartner_Couns_TOT").equals("")){VCTPartner_Couns_TOT=request.getParameter("VCTPartner_Couns_TOT");}
  if(request.getParameter("VCTPartner_Tested_TOT")!=null  && !request.getParameter("VCTPartner_Tested_TOT").equals("")){VCTPartner_Tested_TOT=request.getParameter("VCTPartner_Tested_TOT");}
  if(request.getParameter("VCTPartner_HIV_TOT")!=null  && !request.getParameter("VCTPartner_HIV_TOT").equals("")){VCTPartner_HIV_TOT=request.getParameter("VCTPartner_HIV_TOT");}
  if(request.getParameter("VCTPartner_Disc_TOT")!=null  && !request.getParameter("VCTPartner_Disc_TOT").equals("")){VCTPartner_Disc_TOT=request.getParameter("VCTPartner_Disc_TOT");}
  
  
  

  
  //dtc
  if(request.getParameter("DTCA_Couns_In_CM")!=null && !request.getParameter("DTCA_Couns_In_CM").equals("")){DTCA_Couns_In_CM=request.getParameter("DTCA_Couns_In_CM");}
  if(request.getParameter("DTCA_Couns_In_CF")!=null && !request.getParameter("DTCA_Couns_In_CF").equals("")){DTCA_Couns_In_CF=request.getParameter("DTCA_Couns_In_CF");}
  if(request.getParameter("DTCA_Couns_In_AM")!=null && !request.getParameter("DTCA_Couns_In_AM").equals("")){DTCA_Couns_In_AM=request.getParameter("DTCA_Couns_In_AM");}
  if(request.getParameter("DTCA_Couns_In_AF")!=null && !request.getParameter("DTCA_Couns_In_AF").equals("")){DTCA_Couns_In_AF=request.getParameter("DTCA_Couns_In_AF");}
  if(request.getParameter("DTCA_Couns_In_Tot")!=null && !request.getParameter("DTCA_Couns_In_Tot").equals("")){DTCA_Couns_In_Tot=request.getParameter("DTCA_Couns_In_Tot");}
  if(request.getParameter("DTCA_Couns_Out_CM")!=null && !request.getParameter("DTCA_Couns_Out_CM").equals("")){DTCA_Couns_Out_CM=request.getParameter("DTCA_Couns_Out_CM");}
  if(request.getParameter("DTCA_Couns_Out_CF")!=null && !request.getParameter("DTCA_Couns_Out_CF").equals("")){DTCA_Couns_Out_CF=request.getParameter("DTCA_Couns_Out_CF");}
  if(request.getParameter("DTCA_Couns_Out_AM")!=null && !request.getParameter("DTCA_Couns_Out_AM").equals("")){DTCA_Couns_Out_AM=request.getParameter("DTCA_Couns_Out_AM");}
  if(request.getParameter("DTCA_Couns_Out_AF")!=null && !request.getParameter("DTCA_Couns_Out_AF").equals("")){DTCA_Couns_Out_AF=request.getParameter("DTCA_Couns_Out_AF");}
  if(request.getParameter("DTCA_Couns_Out_Tot")!=null && !request.getParameter("DTCA_Couns_Out_Tot").equals("")){DTCA_Couns_Out_Tot=request.getParameter("DTCA_Couns_Out_Tot");}
  if(request.getParameter("DTCB_Test_In_CM")!=null && !request.getParameter("DTCB_Test_In_CM").equals("")){DTCB_Test_In_CM=request.getParameter("DTCB_Test_In_CM");}
  if(request.getParameter("DTCB_Test_In_CF")!=null && !request.getParameter("DTCB_Test_In_CF").equals("")){DTCB_Test_In_CF=request.getParameter("DTCB_Test_In_CF");}
  if(request.getParameter("DTCB_Test_In_AM")!=null && !request.getParameter("DTCB_Test_In_AM").equals("")){DTCB_Test_In_AM=request.getParameter("DTCB_Test_In_AM");}
  if(request.getParameter("DTCB_Test_In_AF")!=null && !request.getParameter("DTCB_Test_In_AF").equals("")){DTCB_Test_In_AF=request.getParameter("DTCB_Test_In_AF");}
  if(request.getParameter("DTCB_Test_In_Tot")!=null && !request.getParameter("DTCB_Test_In_Tot").equals("")){DTCB_Test_In_Tot=request.getParameter("DTCB_Test_In_Tot");}
  if(request.getParameter("DTCB_Test_Out_CM")!=null && !request.getParameter("DTCB_Test_Out_CM").equals("")){DTCB_Test_Out_CM=request.getParameter("DTCB_Test_Out_CM");}
  if(request.getParameter("DTCB_Test_Out_CF")!=null && !request.getParameter("DTCB_Test_Out_CF").equals("")){DTCB_Test_Out_CF=request.getParameter("DTCB_Test_Out_CF");}
  if(request.getParameter("DTCB_Test_Out_AM")!=null && !request.getParameter("DTCB_Test_Out_AM").equals("")){DTCB_Test_Out_AM=request.getParameter("DTCB_Test_Out_AM");}
  if(request.getParameter("DTCB_Test_Out_AF")!=null && !request.getParameter("DTCB_Test_Out_AF").equals("")){DTCB_Test_Out_AF=request.getParameter("DTCB_Test_Out_AF");}
  if(request.getParameter("DTCB_Test_Out_Tot")!=null && !request.getParameter("DTCB_Test_Out_Tot").equals("")){DTCB_Test_Out_Tot=request.getParameter("DTCB_Test_Out_Tot");}
  if(request.getParameter("DTCC_HIV_In_CM")!=null && !request.getParameter("DTCC_HIV_In_CM").equals("")){DTCC_HIV_In_CM=request.getParameter("DTCC_HIV_In_CM");}
  if(request.getParameter("DTCC_HIV_In_CF")!=null && !request.getParameter("DTCC_HIV_In_CF").equals("")){DTCC_HIV_In_CF=request.getParameter("DTCC_HIV_In_CF");}
  if(request.getParameter("DTCC_HIV_In_AM")!=null && !request.getParameter("DTCC_HIV_In_AM").equals("")){DTCC_HIV_In_AM=request.getParameter("DTCC_HIV_In_AM");}
  if(request.getParameter("DTCC_HIV_In_AF")!=null && !request.getParameter("DTCC_HIV_In_AF").equals("")){DTCC_HIV_In_AF=request.getParameter("DTCC_HIV_In_AF");}
  if(request.getParameter("DTCC_HIV_In_Tot")!=null && !request.getParameter("DTCC_HIV_In_Tot").equals("")){DTCC_HIV_In_Tot=request.getParameter("DTCC_HIV_In_Tot");}
  if(request.getParameter("DTCC_HIV_Out_CM")!=null && !request.getParameter("DTCC_HIV_Out_CM").equals("")){DTCC_HIV_Out_CM=request.getParameter("DTCC_HIV_Out_CM");}
  if(request.getParameter("DTCC_HIV_Out_CF")!=null && !request.getParameter("DTCC_HIV_Out_CF").equals("")){DTCC_HIV_Out_CF=request.getParameter("DTCC_HIV_Out_CF");}
  if(request.getParameter("DTCC_HIV_Out_AM")!=null && !request.getParameter("DTCC_HIV_Out_AM").equals("")){DTCC_HIV_Out_AM=request.getParameter("DTCC_HIV_Out_AM");}
  if(request.getParameter("DTCC_HIV_Out_AF")!=null && !request.getParameter("DTCC_HIV_Out_AF").equals("")){DTCC_HIV_Out_AF=request.getParameter("DTCC_HIV_Out_AF");}
  if(request.getParameter("DTCC_HIV_Out_Tot")!=null && !request.getParameter("DTCC_HIV_Out_Tot").equals("")){DTCC_HIV_Out_Tot=request.getParameter("DTCC_HIV_Out_Tot");}
  */
  
  String runvalidate="update moh711_new set "
          + "FPProgestinN='"+FPProgestinN+"',"
+ "FPProgestinR='"+FPProgestinR+"',"
+ "FPProgestinT='"+FPProgestinT+"',"
+ "FPCocN='"+FPCocN+"',"
+ "FPCocR='"+FPCocR+"',"
+ "FPCocT='"+FPCocT+"',"
+ "FPEcpN='"+FPEcpN+"',"
+ "FPEcpR='"+FPEcpR+"',"
+ "FPEcpT='"+FPEcpT+"',"
+ "FPINJECTIONSN='"+FPINJECTIONSN+"',"
+ "FPINJECTIONSR='"+FPINJECTIONSR+"',"
+ "FPINJECTIONST='"+FPINJECTIONST+"',"
+ "FPIUCDN='"+FPIUCDN+"',"
+ "FPIUCDR='"+FPIUCDR+"',"
+ "FPIUCDT='"+FPIUCDT+"',"
+ "FPIMPLANTSN='"+FPIMPLANTSN+"',"
+ "FPIMPLANTSR='"+FPIMPLANTSR+"',"
+ "FPIMPLANTST='"+FPIMPLANTST+"',"
+ "FPBTLN='"+FPBTLN+"',"
+ "FPBTLR='"+FPBTLR+"',"
+ "FPBTLT='"+FPBTLT+"',"
+ "FPVasectomyN='"+FPVasectomyN+"',"
+ "FPVasectomyR='"+FPVasectomyR+"',"
+ "FPVasectomyT='"+FPVasectomyT+"',"
+ "FPCONDOMSMN='"+FPCONDOMSMN+"',"
+ "FPCONDOMSFN='"+FPCONDOMSFN+"',"
+ "FPCONDOMST='"+FPCONDOMST+"',"
+ "FPNaturalN='"+FPNaturalN+"',"
+ "FPNaturalR='"+FPNaturalR+"',"
+ "FPNaturalT='"+FPNaturalT+"',"
+ "FPCLIENTSN='"+FPCLIENTSN+"',"
+ "FPCLIENTSR='"+FPCLIENTSR+"',"
+ "FPCLIENTST='"+FPCLIENTST+"',"
+ "FPADOLESCENT10_14N='"+FPADOLESCENT10_14N+"',"
+ "FPADOLESCENT10_14R='"+FPADOLESCENT10_14R+"',"
+ "FPADOLESCENT10_14T='"+FPADOLESCENT10_14T+"',"
+ "FPADOLESCENT15_19N='"+FPADOLESCENT15_19N+"',"
+ "FPADOLESCENT15_19R='"+FPADOLESCENT15_19R+"',"
+ "FPADOLESCENT15_19T='"+FPADOLESCENT15_19T+"',"
+ "FPADOLESCENT20_24N='"+FPADOLESCENT20_24N+"',"
+ "FPADOLESCENT20_24R='"+FPADOLESCENT20_24R+"',"
+ "FPADOLESCENT20_24T='"+FPADOLESCENT20_24T+"',"
+ "FPIUCDRemoval='"+FPIUCDRemoval+"',"
+ "FPIMPLANTSRemoval='"+FPIMPLANTSRemoval+"',"
+ "PMCTA_1stVisit_ANC='"+PMCTA_1stVisit_ANC+"',"
+ "PMCTA_ReVisit_ANC='"+PMCTA_ReVisit_ANC+"',"
+ "PMCTANCClientsT='"+PMCTANCClientsT+"',"
+ "PMCTIPT1='"+PMCTIPT1+"',"
+ "PMCTIPT2='"+PMCTIPT2+"',"
+ "PMCTHB11='"+PMCTHB11+"',"
+ "PMCTANCClients4='"+PMCTANCClients4+"',"
+ "PMCTITN1='"+PMCTITN1+"',"
+ "PMCTITN='"+PMCTITN+"',"
+ "PMTCTSYPHILISTES='"+PMTCTSYPHILISTES+"',"
+ "PMTCTSYPHILISPOS='"+PMTCTSYPHILISPOS+"',"
+ "PMTCTCOUNSELLEDFEED='"+PMTCTCOUNSELLEDFEED+"',"
+ "PMTCTBREAST='"+PMTCTBREAST+"',"
+ "PMTCTEXERCISE='"+PMTCTEXERCISE+"',"
+ "PMTCTPREG10_14='"+PMTCTPREG10_14+"',"
+ "PMTCTPREG15_19='"+PMTCTPREG15_19+"',"
+ "PMTCTIRON='"+PMTCTIRON+"',"
+ "PMTCTFOLIC='"+PMTCTFOLIC+"',"
+ "PMTCTFERROUS='"+PMTCTFERROUS+"',"
+ "MATNormalDelivery='"+MATNormalDelivery+"',"
+ "MATCSection='"+MATCSection+"',"
+ "MATBreech='"+MATBreech+"',"
+ "MATAssistedVag='"+MATAssistedVag+"',"
+ "MATDeliveryT='"+MATDeliveryT+"',"
+ "MATLiveBirth='"+MATLiveBirth+"',"
+ "MATFreshStillBirth='"+MATFreshStillBirth+"',"
+ "MATMeceratedStillBirth='"+MATMeceratedStillBirth+"',"
+ "MATDeformities='"+MATDeformities+"',"
+ "MATLowAPGAR='"+MATLowAPGAR+"',"
+ "MATWeight2500='"+MATWeight2500+"',"
+ "MATTetracycline='"+MATTetracycline+"',"
+ "MATPreTerm='"+MATPreTerm+"',"
+ "MATDischargealive='"+MATDischargealive+"',"
+ "MATbreastfeeding1='"+MATbreastfeeding1+"',"
+ "MATDeliveriesPos='"+MATDeliveriesPos+"',"
+ "MATNeoNatalD='"+MATNeoNatalD+"',"
+ "MATMaternalD10_19='"+MATMaternalD10_19+"',"
+ "MATMaternalD='"+MATMaternalD+"',"
+ "MATMaternalDAudited='"+MATMaternalDAudited+"',"
+ "MATAPHAlive='"+MATAPHAlive+"',"
+ "MATAPHDead='"+MATAPHDead+"',"
+ "MATPPHAlive='"+MATPPHAlive+"',"
+ "MATPPHDead='"+MATPPHDead+"',"
+ "MATEclampAlive='"+MATEclampAlive+"',"
+ "MATEclampDead='"+MATEclampDead+"',"
+ "MATRupUtAlive='"+MATRupUtAlive+"',"
+ "MATRupUtDead='"+MATRupUtDead+"',"
+ "MATObstrLaborAlive='"+MATObstrLaborAlive+"',"
+ "MATObstrLaborDead='"+MATObstrLaborDead+"',"
+ "MATSepsisAlive='"+MATSepsisAlive+"',"
+ "MATSepsisDead='"+MATSepsisDead+"',"
+ "MATREFFromOtherFacility='"+MATREFFromOtherFacility+"',"
+ "MATREFFromCU='"+MATREFFromCU+"',"
+ "MATREFToOtherFacility='"+MATREFToOtherFacility+"',"
+ "MATREFToCU='"+MATREFToCU+"',"
+ "SGBVRape72_0_9='"+SGBVRape72_0_9+"',"
+ "SGBVRape72_10_17='"+SGBVRape72_10_17+"',"
+ "SGBVRape72_18_49='"+SGBVRape72_18_49+"',"
+ "SGBVRape72_50='"+SGBVRape72_50+"',"
+ "SGBVRape72T='"+SGBVRape72T+"',"
+ "SGBVinitPEP0_9='"+SGBVinitPEP0_9+"',"
+ "SGBVinitPEP10_17='"+SGBVinitPEP10_17+"',"
+ "SGBVinitPEP18_49='"+SGBVinitPEP18_49+"',"
+ "SGBVinitPEP50='"+SGBVinitPEP50+"',"
+ "SGBVinitPEPT='"+SGBVinitPEPT+"',"
+ "SGBVcompPEP0_9='"+SGBVcompPEP0_9+"',"
+ "SGBVcompPEP10_17='"+SGBVcompPEP10_17+"',"
+ "SGBVcompPEP18_49='"+SGBVcompPEP18_49+"',"
+ "SGBVcompPEP50='"+SGBVcompPEP50+"',"
+ "SGBVcompPEPT='"+SGBVcompPEPT+"',"
+ "SGBVPregnant0_9='"+SGBVPregnant0_9+"',"
+ "SGBVPregnant10_17='"+SGBVPregnant10_17+"',"
+ "SGBVPregnant18_49='"+SGBVPregnant18_49+"',"
+ "SGBVPregnant50='"+SGBVPregnant50+"',"
+ "SGBVPregnantT='"+SGBVPregnantT+"',"
+ "SGBVseroconverting0_9='"+SGBVseroconverting0_9+"',"
+ "SGBVseroconverting10_17='"+SGBVseroconverting10_17+"',"
+ "SGBVseroconverting18_49='"+SGBVseroconverting18_49+"',"
+ "SGBVseroconverting50='"+SGBVseroconverting50+"',"
+ "SGBVseroconvertingT='"+SGBVseroconvertingT+"',"
+ "SGBVsurvivors0_9='"+SGBVsurvivors0_9+"',"
+ "SGBVsurvivors10_17='"+SGBVsurvivors10_17+"',"
+ "SGBVsurvivors18_49='"+SGBVsurvivors18_49+"',"
+ "SGBVsurvivors50='"+SGBVsurvivors50+"',"
+ "SGBVsurvivorsT='"+SGBVsurvivorsT+"',"
+ "PAC10_19='"+PAC10_19+"',"
+ "PACT='"+PACT+"',"
+ "CHANIS0_5NormalweightF='"+CHANIS0_5NormalweightF+"',"
+ "CHANIS0_5NormalweightM='"+CHANIS0_5NormalweightM+"',"
+ "CHANIS0_5NormalweightT='"+CHANIS0_5NormalweightT+"',"
+ "CHANIS0_5UnderweightF='"+CHANIS0_5UnderweightF+"',"
+ "CHANIS0_5UnderweightM='"+CHANIS0_5UnderweightM+"',"
+ "CHANIS0_5UnderweightT='"+CHANIS0_5UnderweightT+"',"
+ "CHANIS0_5sevUnderweightF='"+CHANIS0_5sevUnderweightF+"',"
+ "CHANIS0_5sevUnderweightM='"+CHANIS0_5sevUnderweightM+"',"
+ "CHANIS0_5sevUnderweightT='"+CHANIS0_5sevUnderweightT+"',"
+ "CHANIS0_5OverweightF='"+CHANIS0_5OverweightF+"',"
+ "CHANIS0_5OverweightM='"+CHANIS0_5OverweightM+"',"
+ "CHANIS0_5OverweightT='"+CHANIS0_5OverweightT+"',"
+ "CHANIS0_5ObeseF='"+CHANIS0_5ObeseF+"',"
+ "CHANIS0_5ObeseM='"+CHANIS0_5ObeseM+"',"
+ "CHANIS0_5ObeseT='"+CHANIS0_5ObeseT+"',"
+ "CHANIS0_5TWF='"+CHANIS0_5TWF+"',"
+ "CHANIS0_5TWM='"+CHANIS0_5TWM+"',"
+ "CHANIS0_5TW='"+CHANIS0_5TW+"',"
+ "CHANIS6_23NormalweightF='"+CHANIS6_23NormalweightF+"',"
+ "CHANIS6_23NormalweightM='"+CHANIS6_23NormalweightM+"',"
+ "CHANIS6_23NormalweightT='"+CHANIS6_23NormalweightT+"',"
+ "CHANIS6_23UnderweightF='"+CHANIS6_23UnderweightF+"',"
+ "CHANIS6_23UnderweightM='"+CHANIS6_23UnderweightM+"',"
+ "CHANIS6_23UnderweightT='"+CHANIS6_23UnderweightT+"',"
+ "CHANIS6_23sevUnderweightF='"+CHANIS6_23sevUnderweightF+"',"
+ "CHANIS6_23sevUnderweightM='"+CHANIS6_23sevUnderweightM+"',"
+ "CHANIS6_23sevUnderweightT='"+CHANIS6_23sevUnderweightT+"',"
+ "CHANIS6_23OverweightF='"+CHANIS6_23OverweightF+"',"
+ "CHANIS6_23OverweightM='"+CHANIS6_23OverweightM+"',"
+ "CHANIS6_23OverweightT='"+CHANIS6_23OverweightT+"',"
+ "CHANIS6_23ObeseF='"+CHANIS6_23ObeseF+"',"
+ "CHANIS6_23ObeseM='"+CHANIS6_23ObeseM+"',"
+ "CHANIS6_23ObeseT='"+CHANIS6_23ObeseT+"',"
+ "CHANIS6_23TWF='"+CHANIS6_23TWF+"',"
+ "CHANIS6_23TWM='"+CHANIS6_23TWM+"',"
+ "CHANIS6_23TW='"+CHANIS6_23TW+"',"
+ "CHANIS24_59NormalweightF='"+CHANIS24_59NormalweightF+"',"
+ "CHANIS24_59NormalweightM='"+CHANIS24_59NormalweightM+"',"
+ "CHANIS24_59NormalweightT='"+CHANIS24_59NormalweightT+"',"
+ "CHANIS24_59UnderweightF='"+CHANIS24_59UnderweightF+"',"
+ "CHANIS24_59UnderweightM='"+CHANIS24_59UnderweightM+"',"
+ "CHANIS24_59UnderweightT='"+CHANIS24_59UnderweightT+"',"
+ "CHANIS24_59sevUnderweightF='"+CHANIS24_59sevUnderweightF+"',"
+ "CHANIS24_59sevUnderweightM='"+CHANIS24_59sevUnderweightM+"',"
+ "CHANIS24_59sevUnderweightT='"+CHANIS24_59sevUnderweightT+"',"
+ "CHANIS24_59OverweightF='"+CHANIS24_59OverweightF+"',"
+ "CHANIS24_59OverweightM='"+CHANIS24_59OverweightM+"',"
+ "CHANIS24_59OverweightT='"+CHANIS24_59OverweightT+"',"
+ "CHANIS24_59ObeseF='"+CHANIS24_59ObeseF+"',"
+ "CHANIS24_59ObeseM='"+CHANIS24_59ObeseM+"',"
+ "CHANIS24_59ObeseT='"+CHANIS24_59ObeseT+"',"
+ "CHANIS24_59TWF='"+CHANIS24_59TWF+"',"
+ "CHANIS24_59TWM='"+CHANIS24_59TWM+"',"
+ "CHANIS24_59TW='"+CHANIS24_59TW+"',"
+ "CHANISMUACNormalF='"+CHANISMUACNormalF+"',"
+ "CHANISMUACNormalM='"+CHANISMUACNormalM+"',"
+ "CHANISMUACNormalT='"+CHANISMUACNormalT+"',"
+ "CHANISMUACModerateF='"+CHANISMUACModerateF+"',"
+ "CHANISMUACModerateM='"+CHANISMUACModerateM+"',"
+ "CHANISMUACModerateT='"+CHANISMUACModerateT+"',"
+ "CHANISMUACSevereF='"+CHANISMUACSevereF+"',"
+ "CHANISMUACSevereM='"+CHANISMUACSevereM+"',"
+ "CHANISMUACSevereT='"+CHANISMUACSevereT+"',"
+ "CHANISMUACMeasuredF='"+CHANISMUACMeasuredF+"',"
+ "CHANISMUACMeasuredM='"+CHANISMUACMeasuredM+"',"
+ "CHANISMUACMeasuredT='"+CHANISMUACMeasuredT+"',"
+ "CHANIS0_5NormalHeightF='"+CHANIS0_5NormalHeightF+"',"
+ "CHANIS0_5NormalHeightM='"+CHANIS0_5NormalHeightM+"',"
+ "CHANIS0_5NormalHeightT='"+CHANIS0_5NormalHeightT+"',"
+ "CHANIS0_5StuntedF='"+CHANIS0_5StuntedF+"',"
+ "CHANIS0_5StuntedM='"+CHANIS0_5StuntedM+"',"
+ "CHANIS0_5StuntedT='"+CHANIS0_5StuntedT+"',"
+ "CHANIS0_5sevStuntedF='"+CHANIS0_5sevStuntedF+"',"
+ "CHANIS0_5sevStuntedM='"+CHANIS0_5sevStuntedM+"',"
+ "CHANIS0_5sevStuntedT='"+CHANIS0_5sevStuntedT+"',"
+ "CHANIS0_5TMeasF='"+CHANIS0_5TMeasF+"',"
+ "CHANIS0_5TMeasM='"+CHANIS0_5TMeasM+"',"
+ "CHANIS0_5TMeas='"+CHANIS0_5TMeas+"',"
+ "CHANIS6_23NormalHeightF='"+CHANIS6_23NormalHeightF+"',"
+ "CHANIS6_23NormalHeightM='"+CHANIS6_23NormalHeightM+"',"
+ "CHANIS6_23NormalHeightT='"+CHANIS6_23NormalHeightT+"',"
+ "CHANIS6_23StuntedF='"+CHANIS6_23StuntedF+"',"
+ "CHANIS6_23StuntedM='"+CHANIS6_23StuntedM+"',"
+ "CHANIS6_23StuntedT='"+CHANIS6_23StuntedT+"',"
+ "CHANIS6_23sevStuntedF='"+CHANIS6_23sevStuntedF+"',"
+ "CHANIS6_23sevStuntedM='"+CHANIS6_23sevStuntedM+"',"
+ "CHANIS6_23sevStuntedT='"+CHANIS6_23sevStuntedT+"',"
+ "CHANIS6_23TMeasF='"+CHANIS6_23TMeasF+"',"
+ "CHANIS6_23TMeasM='"+CHANIS6_23TMeasM+"',"
+ "CHANIS6_23TMeas='"+CHANIS6_23TMeas+"',"
+ "CHANIS24_59NormalHeightF='"+CHANIS24_59NormalHeightF+"',"
+ "CHANIS24_59NormalHeightM='"+CHANIS24_59NormalHeightM+"',"
+ "CHANIS24_59NormalHeightT='"+CHANIS24_59NormalHeightT+"',"
+ "CHANIS24_59StuntedF='"+CHANIS24_59StuntedF+"',"
+ "CHANIS24_59StuntedM='"+CHANIS24_59StuntedM+"',"
+ "CHANIS24_59StuntedT='"+CHANIS24_59StuntedT+"',"
+ "CHANIS24_59sevStuntedF='"+CHANIS24_59sevStuntedF+"',"
+ "CHANIS24_59sevStuntedM='"+CHANIS24_59sevStuntedM+"',"
+ "CHANIS24_59sevStuntedT='"+CHANIS24_59sevStuntedT+"',"
+ "CHANIS24_59TMeasF='"+CHANIS24_59TMeasF+"',"
+ "CHANIS24_59TMeasM='"+CHANIS24_59TMeasM+"',"
+ "CHANIS24_59TMeas='"+CHANIS24_59TMeas+"',"
+ "CHANIS0_59NewVisitsF='"+CHANIS0_59NewVisitsF+"',"
+ "CHANIS0_59NewVisitsM='"+CHANIS0_59NewVisitsM+"',"
+ "CHANIS0_59NewVisitsT='"+CHANIS0_59NewVisitsT+"',"
+ "CHANIS0_59KwashiakorF='"+CHANIS0_59KwashiakorF+"',"
+ "CHANIS0_59KwashiakorM='"+CHANIS0_59KwashiakorM+"',"
+ "CHANIS0_59KwashiakorT='"+CHANIS0_59KwashiakorT+"',"
+ "CHANIS0_59MarasmusF='"+CHANIS0_59MarasmusF+"',"
+ "CHANIS0_59MarasmusM='"+CHANIS0_59MarasmusM+"',"
+ "CHANIS0_59MarasmusT='"+CHANIS0_59MarasmusT+"',"
+ "CHANIS0_59FalgrowthF='"+CHANIS0_59FalgrowthF+"',"
+ "CHANIS0_59FalgrowthM='"+CHANIS0_59FalgrowthM+"',"
+ "CHANIS0_59FalgrowthT='"+CHANIS0_59FalgrowthT+"',"
+ "CHANIS0_59F='"+CHANIS0_59F+"',"
+ "CHANIS0_59M='"+CHANIS0_59M+"',"
+ "CHANIS0_59T='"+CHANIS0_59T+"',"
+ "CHANIS0_5EXCLBreastF='"+CHANIS0_5EXCLBreastF+"',"
+ "CHANIS0_5EXCLBreastM='"+CHANIS0_5EXCLBreastM+"',"
+ "CHANIS0_5EXCLBreastT='"+CHANIS0_5EXCLBreastT+"',"
+ "CHANIS12_59DewormedF='"+CHANIS12_59DewormedF+"',"
+ "CHANIS12_59DewormedM='"+CHANIS12_59DewormedM+"',"
+ "CHANIS12_59DewormedT='"+CHANIS12_59DewormedT+"',"
+ "CHANIS6_23MNPsF='"+CHANIS6_23MNPsF+"',"
+ "CHANIS6_23MNPsM='"+CHANIS6_23MNPsM+"',"
+ "CHANIS6_23MNPsT='"+CHANIS6_23MNPsT+"',"
+ "CHANIS0_59DisabilityF='"+CHANIS0_59DisabilityF+"',"
+ "CHANIS0_59DisabilityM='"+CHANIS0_59DisabilityM+"',"
+ "CHANIS0_59DisabilityT='"+CHANIS0_59DisabilityT+"',"
+ "CCSVVH24='"+CCSVVH24+"',"
+ "CCSVVH25_49='"+CCSVVH25_49+"',"
+ "CCSVVH50='"+CCSVVH50+"',"
+ "CCSPAPSMEAR24='"+CCSPAPSMEAR24+"',"
+ "CCSPAPSMEAR25_49='"+CCSPAPSMEAR25_49+"',"
+ "CCSPAPSMEAR50='"+CCSPAPSMEAR50+"',"
+ "CCSHPV24='"+CCSHPV24+"',"
+ "CCSHPV25_49='"+CCSHPV25_49+"',"
+ "CCSHPV50='"+CCSHPV50+"',"
+ "CCSVIAVILIPOS24='"+CCSVIAVILIPOS24+"',"
+ "CCSVIAVILIPOS25_49='"+CCSVIAVILIPOS25_49+"',"
+ "CCSVIAVILIPOS50='"+CCSVIAVILIPOS50+"',"
+ "CCSSUSPICIOUSLES24='"+CCSSUSPICIOUSLES24+"',"
+ "CCSSUSPICIOUSLES25_49='"+CCSSUSPICIOUSLES25_49+"',"
+ "CCSSUSPICIOUSLES50='"+CCSSUSPICIOUSLES50+"',"
+ "CCSCYTOLPOS24='"+CCSCYTOLPOS24+"',"
+ "CCSCYTOLPOS25_49='"+CCSCYTOLPOS25_49+"',"
+ "CCSCYTOLPOS50='"+CCSCYTOLPOS50+"',"
+ "CCSHPVPOS24='"+CCSHPVPOS24+"',"
+ "CCSHPVPOS25_49='"+CCSHPVPOS25_49+"',"
+ "CCSHPVPOS50='"+CCSHPVPOS50+"',"
+ "CCSCryotherapy24='"+CCSCryotherapy24+"',"
+ "CCSCryotherapy25_49='"+CCSCryotherapy25_49+"',"
+ "CCSCryotherapy50='"+CCSCryotherapy50+"',"
+ "CCSLEEP24='"+CCSLEEP24+"',"
+ "CCSLEEP25_49='"+CCSLEEP25_49+"',"
+ "CCSLEEP50='"+CCSLEEP50+"',"
+ "CCSHIVPOSSCREENED24='"+CCSHIVPOSSCREENED24+"',"
+ "CCSHIVPOSSCREENED25_49='"+CCSHIVPOSSCREENED25_49+"',"
+ "CCSHIVPOSSCREENED50='"+CCSHIVPOSSCREENED50+"',"
+ "PNCBreastExam='"+PNCBreastExam+"',"
+ "PNCCounselled='"+PNCCounselled+"',"
+ "PNCFistula='"+PNCFistula+"',"
+ "PNCExerNegative='"+PNCExerNegative+"',"
+ "PNCExerPositive='"+PNCExerPositive+"',"
+ "PNCCCSsuspect='"+PNCCCSsuspect+"',"
+ "PNCmotherspostpartum2_3='"+PNCmotherspostpartum2_3+"',"
+ "PNCmotherspostpartum6='"+PNCmotherspostpartum6+"',"
+ "PNCinfantspostpartum2_3='"+PNCinfantspostpartum2_3+"',"
+ "PNCinfantspostpartum6='"+PNCinfantspostpartum6+"',"
+ "PNCreferralsfromotherHF='"+PNCreferralsfromotherHF+"',"
+ "PNCreferralsfromotherCU='"+PNCreferralsfromotherCU+"',"
+ "PNCreferralsTootherHF='"+PNCreferralsTootherHF+"',"
+ "PNCreferralsTootherCU='"+PNCreferralsTootherCU+"',"
+ "RsAssessed='"+RsAssessed+"',"
+ "Rstreated='"+Rstreated+"',"
+ "RsRehabilitated='"+RsRehabilitated+"',"
+ "Rsreffered='"+Rsreffered+"',"
+ "RsIntergrated='"+RsIntergrated+"',"
+ "MSWpscounselling='"+MSWpscounselling+"',"
+ "MSWdrugabuse='"+MSWdrugabuse+"',"
+ "MSWMental='"+MSWMental+"',"
+ "MSWAdolescent='"+MSWAdolescent+"',"
+ "MSWPsAsses='"+MSWPsAsses+"',"
+ "MSWsocialinv='"+MSWsocialinv+"',"
+ "MSWsocialRehab='"+MSWsocialRehab+"',"
+ "MSWoutreach='"+MSWoutreach+"',"
+ "MSWreferrals='"+MSWreferrals+"',"
+ "MSWwaivedpatients='"+MSWwaivedpatients+"',"
+ "PsPWDOPD4='"+PsPWDOPD4+"',"
+ "PsPWDOPD5_19='"+PsPWDOPD5_19+"',"
+ "PsPWDOPD20='"+PsPWDOPD20+"',"
+ "PsPWDinpatient4='"+PsPWDinpatient4+"',"
+ "PsPWDinpatient5_19='"+PsPWDinpatient5_19+"',"
+ "PsPWDinpatient20='"+PsPWDinpatient20+"',"
+ "PsotherOPD4='"+PsotherOPD4+"',"
+ "PsotherOPD5_19='"+PsotherOPD5_19+"',"
+ "PsotherOPD20='"+PsotherOPD20+"',"
+ "Psotherinpatient4='"+Psotherinpatient4+"',"
+ "Psotherinpatient5_19='"+Psotherinpatient5_19+"',"
+ "Psotherinpatient20='"+Psotherinpatient20+"',"
+ "PsTreatments4='"+PsTreatments4+"',"
+ "PsTreatments5_19='"+PsTreatments5_19+"',"
+ "PsTreatments20='"+PsTreatments20+"',"
+ "PsAssessed4='"+PsAssessed4+"',"
+ "PsAssessed5_19='"+PsAssessed5_19+"',"
+ "PsAssessed20='"+PsAssessed20+"',"
+ "PsServices4='"+PsServices4+"',"
+ "PsServices5_19='"+PsServices5_19+"',"
+ "PsServices20='"+PsServices20+"',"
+ "PsANCCounsel5_19='"+PsANCCounsel5_19+"',"
+ "PsANCCounsel20='"+PsANCCounsel20+"',"
+ "PsExercise5_19='"+PsExercise5_19+"',"
+ "PsExercise20='"+PsExercise20+"',"
+ "PsFIFcollected5_19='"+PsFIFcollected5_19+"',"
+ "PsFIFcollected20='"+PsFIFcollected20+"',"
+ "PsFIFwaived5_19='"+PsFIFwaived5_19+"',"
+ "PsFIFwaived20='"+PsFIFwaived20+"',"
+ "PsFIFexempted4='"+PsFIFexempted4+"',"
+ "PsFIFexempted5_19='"+PsFIFexempted5_19+"',"
+ "PsFIFexempted20='"+PsFIFexempted20+"',"
+ "PsDiasbilitymeeting4='"+PsDiasbilitymeeting4+"',"
+ "PsDiasbilitymeeting5_19='"+PsDiasbilitymeeting5_19+"',"
+ "PsDiasbilitymeeting20='"+PsDiasbilitymeeting20+"',"
+ "isValidated='1' where ID='"+tableid+"'";        
System.out.println(runvalidate);  
   conn.st.executeUpdate(runvalidate);
    session.setAttribute("validate711", "<font color=\"green\"><b>Form MOH 711 Validated Successfully.</b></font>");
  
    
     int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM moh711_new WHERE ID='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE moh711_new SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE ID='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
     }
     
     
     
       String dqaid="";
      System.out.println("++++++++++++++++++++++++++++++++here++++++++++++++++++++++++++++++++++++++++++++");
     String checker="SELECT id FROM dqa WHERE year='"+year+"' && month='"+month+"' && facilityid='"+facil+"' && form='moh711' LIMIT 1";
     conn.rs=conn.st.executeQuery(checker);
     if(conn.rs.next()==true){
        dqaid=conn.rs.getString(1);
        
        if(!data_elements.equals("")){
//           UPDATE DQA TABLE
             System.out.println("to update");
            String updator="UPDATE dqa SET columns=?, errors=? WHERE id=? ";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, data_elements);
            conn.pst.setString(2, description);
            conn.pst.setString(3, dqaid);
            conn.pst.executeUpdate();
        }
        else{
         System.out.println("to delete");
            String updator="DELETE FROM dqa WHERE id=? ";
            conn.pst=conn.conn.prepareStatement(updator);
            conn.pst.setString(1, dqaid);
           
            conn.pst.executeUpdate();    
        }
     }
     else{
         
    if(!data_elements.equals("")) {
      System.out.println("to insert");
    String inserter="INSERT INTO dqa (tableid,form,facilityid,year,month,columns,errors) VALUES(?,?,?,?,?,?,?)";
    conn.pst=conn.conn.prepareStatement(inserter);
    conn.pst.setString(1, tableid);
    conn.pst.setString(2, "moh711");
    conn.pst.setString(3, facil);
    conn.pst.setString(4, year);
    conn.pst.setString(5, month);
    conn.pst.setString(6, data_elements);
    conn.pst.setString(7, description);
    
    conn.pst.executeUpdate();
    }    
         
     } 
     
     
     
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     if(conn.st3!=null){conn.st3.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.rs3!=null){conn.rs3.close();}
     if(conn.conn!=null){conn.conn.close();}
   
    
    
}
        
  finally {
           // out.close();
        }
        
   
        response.sendRedirect("loadnew711.jsp");
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
            Logger.getLogger(validate711.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(validate711.class.getName()).log(Level.SEVERE, null, ex);
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

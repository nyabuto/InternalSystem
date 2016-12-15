/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package reports;

import General.IdGenerator;
import database.dbConn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Maureen
 */
public class newmoh711_StaticReport extends HttpServlet {

   
    HttpSession session;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
       // response.setContentType("text/html;charset=UTF-8");
        
        
String data,id;
String facilityId;
String county,district,facilityname,mflcode;       
  String isValidated,validity;
int maxYearMonth;
String subcountyid,facility;
String reportType,duration,reportDuration,quarter,semi_annual;
int year,prevYear,month;
String header,facilityName,countyName,districtName,monthName;
int pos=0;
int newpos=0;
month=0;

int FPProgestinN,FPProgestinR,FPProgestinT,FPCocN,FPCocR,FPCocT,FPEcpN,FPEcpR,FPEcpT,FPINJECTABLESN,FPINJECTABLESR,FPINJECTABLEST,FPINJECTIONSN,FPINJECTIONSR,FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR,FPVasectomyT,FPCONDOMSMN,FPCONDOMSFN,FPCONDOMST,FPNaturalN,FPNaturalR,FPNaturalT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPADOLESCENT10_14N,FPADOLESCENT10_14R,FPADOLESCENT10_14T,FPADOLESCENT15_19N,FPADOLESCENT15_19R,FPADOLESCENT15_19T,FPADOLESCENT20_24N,FPADOLESCENT20_24R,FPADOLESCENT20_24T,FPIUCDRemoval,FPIMPLANTSRemoval;	
        
int PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTIPT1,PMCTIPT2,PMCTHB11,PMCTANCClients4,PMCTITN1,PMCTITN,PMTCTSYPHILISTES,PMTCTSYPHILISPOS,PMTCTCOUNSELLEDFEED,PMTCTBREAST,PMTCTEXERCISE,PMTCTPREG10_14,PMTCTPREG15_19,PMTCTIRON,PMTCTFOLIC,PMTCTFERROUS;	
                
int MATNormalDelivery,MATCSection,MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATFreshStillBirth,MATMeceratedStillBirth,MATDeformities,MATLowAPGAR,MATWeight2500,MATTetracycline,MATPreTerm,MATDischargealive,MATbreastfeeding1,MATDeliveriesPos,MATNeoNatalD,MATMaternalD10_19,MATMaternalD,MATMaternalDAudited,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead,MATREFFromOtherFacility,MATREFFromCU,MATREFToOtherFacility,MATREFToCU;	
                        
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

 FPProgestinN=FPProgestinR=FPProgestinT=FPCocN=FPCocR=FPCocT=FPEcpN=FPEcpR=FPEcpT=FPINJECTABLESN=FPINJECTABLESR=FPINJECTABLEST=FPINJECTIONSN=FPINJECTIONSR=FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR=FPVasectomyT=FPCONDOMSMN=FPCONDOMSFN=FPCONDOMST=FPNaturalN=FPNaturalR=FPNaturalT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPADOLESCENT10_14N=FPADOLESCENT10_14R=FPADOLESCENT10_14T=FPADOLESCENT15_19N=FPADOLESCENT15_19R=FPADOLESCENT15_19T=FPADOLESCENT20_24N=FPADOLESCENT20_24R=FPADOLESCENT20_24T=FPIUCDRemoval=FPIMPLANTSRemoval=0;	
        
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTIPT1=PMCTIPT2=PMCTHB11=PMCTANCClients4=PMCTITN1=PMCTITN=PMTCTSYPHILISTES=PMTCTSYPHILISPOS=PMTCTCOUNSELLEDFEED=PMTCTBREAST=PMTCTEXERCISE=PMTCTPREG10_14=PMTCTPREG15_19=PMTCTIRON=PMTCTFOLIC=PMTCTFERROUS=0;	
                
 MATNormalDelivery=MATCSection=MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATFreshStillBirth=MATMeceratedStillBirth=MATDeformities=MATLowAPGAR=MATWeight2500=MATTetracycline=MATPreTerm=MATDischargealive=MATbreastfeeding1=MATDeliveriesPos=MATNeoNatalD=MATMaternalD10_19=MATMaternalD=MATMaternalDAudited=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead=MATREFFromOtherFacility=MATREFFromCU=MATREFToOtherFacility=MATREFToCU=0;	
                        
 SGBVRape72_0_9=SGBVRape72_10_17=SGBVRape72_18_49=SGBVRape72_50=SGBVRape72T=SGBVinitPEP0_9=SGBVinitPEP10_17=SGBVinitPEP18_49=SGBVinitPEP50=SGBVinitPEPT=SGBVcompPEP0_9=SGBVcompPEP10_17=SGBVcompPEP18_49=SGBVcompPEP50=SGBVcompPEPT=SGBVPregnant0_9=SGBVPregnant10_17=SGBVPregnant18_49=SGBVPregnant50=SGBVPregnantT=SGBVseroconverting0_9=SGBVseroconverting10_17=SGBVseroconverting18_49=SGBVseroconverting50=SGBVseroconvertingT=SGBVsurvivors0_9=SGBVsurvivors10_17=SGBVsurvivors18_49=SGBVsurvivors50=SGBVsurvivorsT="";
                                
 PAC10_19=PACT="";	
                                        
 CHANIS0_5NormalweightF=CHANIS0_5NormalweightM=CHANIS0_5NormalweightT=CHANIS0_5UnderweightF=CHANIS0_5UnderweightM=CHANIS0_5UnderweightT=CHANIS0_5sevUnderweightF=CHANIS0_5sevUnderweightM=CHANIS0_5sevUnderweightT=CHANIS0_5OverweightF=CHANIS0_5OverweightM=CHANIS0_5OverweightT=CHANIS0_5ObeseF=CHANIS0_5ObeseM=CHANIS0_5ObeseT=CHANIS0_5TWF=CHANIS0_5TWM=CHANIS0_5TW=CHANIS6_23NormalweightF=CHANIS6_23NormalweightM=CHANIS6_23NormalweightT=CHANIS6_23UnderweightF=CHANIS6_23UnderweightM=CHANIS6_23UnderweightT=CHANIS6_23sevUnderweightF=CHANIS6_23sevUnderweightM=CHANIS6_23sevUnderweightT=CHANIS6_23OverweightF=CHANIS6_23OverweightM=CHANIS6_23OverweightT=CHANIS6_23ObeseF=CHANIS6_23ObeseM=CHANIS6_23ObeseT=CHANIS6_23TWF=CHANIS6_23TWM=CHANIS6_23TW=CHANIS24_59NormalweightF=CHANIS24_59NormalweightM=CHANIS24_59NormalweightT=CHANIS24_59UnderweightF=CHANIS24_59UnderweightM=CHANIS24_59UnderweightT=CHANIS24_59sevUnderweightF=CHANIS24_59sevUnderweightM=CHANIS24_59sevUnderweightT=CHANIS24_59OverweightF=CHANIS24_59OverweightM=CHANIS24_59OverweightT=CHANIS24_59ObeseF=CHANIS24_59ObeseM=CHANIS24_59ObeseT=CHANIS24_59TWF=CHANIS24_59TWM=CHANIS24_59TW=CHANISMUACNormalF=CHANISMUACNormalM=CHANISMUACNormalT=CHANISMUACModerateF=CHANISMUACModerateM=CHANISMUACModerateT=CHANISMUACSevereF=CHANISMUACSevereM=CHANISMUACSevereT=CHANISMUACMeasuredF=CHANISMUACMeasuredM=CHANISMUACMeasuredT="";

 CHANIS0_5NormalHeightF=CHANIS0_5NormalHeightM=CHANIS0_5NormalHeightT=CHANIS0_5StuntedF=CHANIS0_5StuntedM=CHANIS0_5StuntedT=CHANIS0_5sevStuntedF=CHANIS0_5sevStuntedM=CHANIS0_5sevStuntedT=CHANIS0_5TMeasF=CHANIS0_5TMeasM=CHANIS0_5TMeas=CHANIS6_23NormalHeightF=CHANIS6_23NormalHeightM=CHANIS6_23NormalHeightT=CHANIS6_23StuntedF=CHANIS6_23StuntedM=CHANIS6_23StuntedT=CHANIS6_23sevStuntedF=CHANIS6_23sevStuntedM=CHANIS6_23sevStuntedT=CHANIS6_23TMeasF=CHANIS6_23TMeasM=CHANIS6_23TMeas=CHANIS24_59NormalHeightF=CHANIS24_59NormalHeightM=CHANIS24_59NormalHeightT=CHANIS24_59StuntedF=CHANIS24_59StuntedM=CHANIS24_59StuntedT=CHANIS24_59sevStuntedF=CHANIS24_59sevStuntedM=CHANIS24_59sevStuntedT=CHANIS24_59TMeasF=CHANIS24_59TMeasM=CHANIS24_59TMeas=CHANIS0_59NewVisitsF=CHANIS0_59NewVisitsM=CHANIS0_59NewVisitsT=CHANIS0_59KwashiakorF=CHANIS0_59KwashiakorM=CHANIS0_59KwashiakorT=CHANIS0_59MarasmusF=CHANIS0_59MarasmusM=CHANIS0_59MarasmusT=CHANIS0_59FalgrowthF=CHANIS0_59FalgrowthM=CHANIS0_59FalgrowthT=CHANIS0_59F=CHANIS0_59M=CHANIS0_59T=CHANIS0_5EXCLBreastF=CHANIS0_5EXCLBreastM=CHANIS0_5EXCLBreastT=CHANIS12_59DewormedF=CHANIS12_59DewormedM=CHANIS12_59DewormedT=CHANIS6_23MNPsF=CHANIS6_23MNPsM=CHANIS6_23MNPsT=CHANIS0_59DisabilityF=CHANIS0_59DisabilityM=CHANIS0_59DisabilityT="";	
  
 CCSSUSPICIOUSLES24=CCSSUSPICIOUSLES25_49=CCSSUSPICIOUSLES50=CCSVVH24=CCSVVH25_49=CCSVVH50=CCSPAPSMEAR24=CCSPAPSMEAR25_49=CCSPAPSMEAR50=CCSHPV24=CCSHPV25_49=CCSHPV50=CCSVIAVILIPOS24=CCSVIAVILIPOS25_49=CCSVIAVILIPOS50=CCSCYTOLPOS24=CCSCYTOLPOS25_49=CCSCYTOLPOS50=CCSHPVPOS24=CCSHPVPOS25_49=CCSHPVPOS50=CCSCryotherapy24=CCSCryotherapy25_49=CCSCryotherapy50=CCSLEEP24=CCSLEEP25_49=CCSLEEP50=CCSHIVPOSSCREENED24=CCSHIVPOSSCREENED25_49=CCSHIVPOSSCREENED50="";	
                                                
 PNCBreastExam=PNCCounselled=PNCFistula=PNCExerNegative=PNCExerPositive=PNCCCSsuspect=PNCmotherspostpartum2_3=PNCmotherspostpartum6=PNCinfantspostpartum2_3=PNCinfantspostpartum6=PNCreferralsfromotherHF=PNCreferralsfromotherCU=PNCreferralsTootherHF=PNCreferralsTootherCU="";	
                                                        
 RsAssessed=Rstreated=RsRehabilitated=Rsreffered=RsIntergrated="";	
                                                                
 MSWpscounselling=MSWdrugabuse=MSWMental=MSWAdolescent=MSWPsAsses=MSWsocialinv=MSWsocialRehab=MSWoutreach=MSWreferrals=MSWwaivedpatients="";	
                                                                        
 PsotherOPD4=PsotherOPD5_19=PsotherOPD20=Psotherinpatient4=Psotherinpatient5_19=Psotherinpatient20= PsPWDOPD4=PsPWDOPD5_19=PsPWDOPD20=PsPWDinpatient4=PsPWDinpatient5_19=PsPWDinpatient20=PsTreatments4=PsTreatments5_19=PsTreatments20=PsAssessed4=PsAssessed5_19=PsAssessed20=PsServices4=PsServices5_19=PsServices20=PsANCCounsel5_19=PsANCCounsel20=PsExercise5_19=PsExercise20=PsFIFcollected5_19=PsFIFcollected20=PsFIFwaived5_19=PsFIFwaived20=PsFIFexempted4=PsFIFexempted5_19=PsFIFexempted20=PsDiasbilitymeeting4=PsDiasbilitymeeting5_19=PsDiasbilitymeeting20="";



//String VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
//,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;
//
//String DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
//,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
//,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,Userid;


String FamilyPlanninng, pmct,maternity,vct,dtc;
      header="";  
      duration="";
//        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
             reportType=request.getParameter("reportType");
        year=Integer.parseInt(request.getParameter("year"));
        reportDuration=request.getParameter("reportDuration");
        
//        reportType="2";
//        year=2015;
//        reportDuration="3";
        
        
        
        
        //--------------------------------------------------------------------------------
        //--------------------------------------------------------------------------------
        //added later to accomodate the years
           String subpartnerid="SubPartnerID";
           String subpartnera="subpartnera";
           
           
           int monthint=0;
           int yearint=0;
           yearint=year;
         
      
        
        
        
        
        prevYear=year-1; 
        maxYearMonth=0;
        
//        GET REPORT DURATION============================================
//        startPMTCT=startART=startPEP=noPMTCT=noART=noPEP=0;
        if(reportDuration.equals("1")){
           
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
           //this should be skipped since it picks both facil tables. It has been disabled at the interface position
        subpartnerid="SubPartnerID";
        subpartnera="subpartnera";
                              }
            
         duration=" moh711_new.yearmonth BETWEEN "+prevYear+"10 AND "+year+"09";   
         
         
         
                                      }
        else if(reportDuration.equals("2")){
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
           
           
       duration=" moh711_new.yearmonth BETWEEN "+prevYear+"10 AND "+year+"03"; 
       
       
       
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
           
       duration=" moh711_new.yearmonth BETWEEN "+year+"04 AND "+year+"09";      
               }
                                            }
        
        else if(reportDuration.equals("3")){
            //quarterly
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
            
            
            
       String startMonth,endMonth;
     
//       quarter="3";
       String getMonths="SELECT months FROM quarter WHERE id='"+quarter+"'";
       conn.rs=conn.st.executeQuery(getMonths);
       if(conn.rs.next()==true){
      String months []=conn.rs.getString(1).split(",");
       startMonth=months[0];
       endMonth=months[2];
      if(quarter.equals("1")){
      duration=" moh711_new.yearmonth BETWEEN "+prevYear+""+startMonth+" AND "+prevYear+""+endMonth;    
      }
      else{
     duration=" moh711_new.yearmonth BETWEEN "+year+""+startMonth+" AND "+year+""+endMonth;   
      }
        }
        }  
        
      else if(reportDuration.equals("4")){
     month=Integer.parseInt(request.getParameter("month"));
     
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
      
     
     
     
     
//     month=5;
     if(month>=10){
     duration=" moh711_new.yearmonth="+prevYear+""+month;    
     }
     else{
  duration=" moh711_new.yearmonth="+year+"0"+month;  
     }
      }
      else{
     duration="";     
      }
           
     
//     GET FACILITIES TO OUTPUT.................................
    mflcode=countyName=districtName=facilityName="";    
        
      if(reportType.equals("1")){  
    facility=""; 
    
      facilityName="ALL APHIAPLUS SUPPORTED HEALTH FACILITIES";
      districtName="ALL";
      countyName="ALL COUNTIES";
      mflcode="NONE";
    }
      
      else{
  facilityId=request.getParameter("facility");
  String spid="";
  //get the correct facil id based on the year id...
//  facilityId="403";
  facility="moh711_new.SubPartnerID='"+facilityId+"' &&";    
  //this part should remain constant since the facility parameters being passed are using the subpartnera table
  String getName="SELECT subpartnera.SubPartnerNom,district.DistrictNom,county.County,subpartnera.CentreSanteId, SP_ID FROM subpartnera "
          + "JOIN district ON subpartnera.DistrictID=district.DistrictID JOIN county ON "
          + "district.CountyID=county.CountyID WHERE subpartnera.SubPartnerID='"+facilityId+"'";
  
  
   System.out.println("getname__"+getName);
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
      
  facility="moh711_new.SubPartnerID='"+spid+"' &&";
  
  }
  
  
  
     }
     
    header+="</table>";  
      
    String getMaxYearMonth="SELECT MAX(yearmonth) FROM moh711_new WHERE "+facility+" "+duration ;
    conn.rs=conn.st.executeQuery(getMaxYearMonth);
    if(conn.rs.next()==true){
        maxYearMonth=conn.rs.getInt(1);
    }
   System.out.println("max year month : "+maxYearMonth);
        
   
    //            ^^^^^^^^^^^^^CREATE STATIC AND WRITE STATIC DATA TO THE EXCELL^^^^^^^^^^^^
   XSSFWorkbook wb=new XSSFWorkbook();

  XSSFSheet shet2=wb.createSheet("A. ANC _ PMCT");
  XSSFSheet shet3=wb.createSheet("B. MATERNITY AND DELIVERY");
  XSSFSheet shet1=wb.createSheet("D. FAMILY PLANNING");
  //XSSFSheet shet4=wb.createSheet("VCT");
  //XSSFSheet shet5=wb.createSheet("DTC");
  XSSFFont font=wb.createFont();
 font.setFontHeightInPoints((short)18);
    font.setFontName("Arial Black");
    font.setColor((short)0000);
    CellStyle style=wb.createCellStyle();
    style.setFont(font);
    style.setAlignment(XSSFCellStyle.ALIGN_LEFT);
     XSSFFont font2=wb.createFont();
    font2.setFontName("Arial Black");
    font2.setColor((short)0000);
    CellStyle style2=wb.createCellStyle();
    style2.setFont(font2);
   
   XSSFCellStyle stborder = wb.createCellStyle();
    stborder.setBorderTop(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    stborder.setBorderRight(XSSFCellStyle.BORDER_THIN);
    stborder.setAlignment(XSSFCellStyle.ALIGN_LEFT);
    stborder.setVerticalAlignment(VerticalAlignment.CENTER);
    
    //stborder.setWrapText(true);
    
    XSSFCellStyle stylex = wb.createCellStyle();
stylex.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
stylex.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
   stylex.setBorderTop(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    stylex.setBorderRight(XSSFCellStyle.BORDER_THIN);
    stylex.setAlignment(XSSFCellStyle.ALIGN_LEFT);
    
XSSFFont fontx = wb.createFont();
fontx.setColor(HSSFColor.DARK_BLUE.index);
stylex.setFont(fontx);
stylex.setWrapText(true);

   XSSFCellStyle styleHeader = wb.createCellStyle();
styleHeader.setFillForegroundColor(HSSFColor.LIME.index);
styleHeader.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
   styleHeader.setBorderTop(XSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderBottom(XSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderLeft(XSSFCellStyle.BORDER_THIN);
    styleHeader.setBorderRight(XSSFCellStyle.BORDER_THIN);
    styleHeader.setAlignment(XSSFCellStyle.ALIGN_LEFT);
    
XSSFFont fontHeader = wb.createFont();
fontHeader.setColor(HSSFColor.DARK_BLUE.index);
styleHeader.setFont(fontHeader);
styleHeader.setWrapText(true);
  
 shet1.setColumnWidth(0, 16000);
 shet2.setColumnWidth(0, 16000);  
 shet3.setColumnWidth(0, 16000);
 //shet4.setColumnWidth(0, 8000);
 //shet5.setColumnWidth(0, 8000);


 shet1.setColumnWidth(1, 10000);
 shet2.setColumnWidth(1, 8000);  
 shet3.setColumnWidth(1, 8000);
 //shet4.setColumnWidth(1, 8000);
 //shet5.setColumnWidth(1, 8000);

   
 shet1.setColumnWidth(2, 4000);
 shet2.setColumnWidth(2, 4000);  
 shet3.setColumnWidth(2, 4000);
 //shet4.setColumnWidth(2, 4000);
 //shet5.setColumnWidth(2, 4000);
 
 shet1.setColumnWidth(3, 3500);
 shet2.setColumnWidth(3, 3500);  
 shet3.setColumnWidth(3, 3500);
 //shet4.setColumnWidth(3, 3500);
 //shet5.setColumnWidth(3, 3500);
 
 shet1.setColumnWidth(4, 3500);
 //shet2.setColumnWidth(4, 3500);  
 shet3.setColumnWidth(4, 3500);
 //shet4.setColumnWidth(4, 3500);
 //shet5.setColumnWidth(4, 3500);
  
 shet1.setColumnWidth(5, 3500);
 shet2.setColumnWidth(5, 3500);  
 shet3.setColumnWidth(5, 3500);
 //shet4.setColumnWidth(5, 3500);
 //shet5.setColumnWidth(5, 3500);
  
  String  headers="COUNTY,SUB COUNTY,FACILITY NAME,MFL CODE";
   
    String arrayHeader []=headers.split(",");
    int headerno=0;int valueNo=0; int arrayCounter=0;
   
//   XSSFRow rw0S1=shet1.createRow(0);
   XSSFRow rw1S1=shet1.createRow(0);  
   
//    XSSFRow rw0S2=shet2.createRow(0);
   XSSFRow rw1S2=shet2.createRow(0);
    
//    XSSFRow rw0S3=shet3.createRow(0);
   XSSFRow rw1S3=shet3.createRow(0);

  
    String getMonth="SELECT name FROM month WHERE id='"+month+"'";
    conn.rs=conn.st.executeQuery(getMonth);
    if(conn.rs.next()==true){
        monthName=conn.rs.getString(1);
    }
     
     
   
//  counterPMTCT=counterART=counterPEP=3; 
              
    
    
    
//initialize variables

 FPProgestinN=FPProgestinR=FPProgestinT=FPCocN=FPCocR=FPCocT=FPEcpN=FPEcpR=FPEcpT=FPINJECTABLESN=FPINJECTABLESR=FPINJECTABLEST=FPINJECTIONSN=FPINJECTIONSR=FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR=FPVasectomyT=FPCONDOMSMN=FPCONDOMSFN=FPCONDOMST=FPNaturalN=FPNaturalR=FPNaturalT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPADOLESCENT10_14N=FPADOLESCENT10_14R=FPADOLESCENT10_14T=FPADOLESCENT15_19N=FPADOLESCENT15_19R=FPADOLESCENT15_19T=FPADOLESCENT20_24N=FPADOLESCENT20_24R=FPADOLESCENT20_24T=FPIUCDRemoval=FPIMPLANTSRemoval=0;	
        
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTIPT1=PMCTIPT2=PMCTHB11=PMCTANCClients4=PMCTITN1=PMCTITN=PMTCTSYPHILISTES=PMTCTSYPHILISPOS=PMTCTCOUNSELLEDFEED=PMTCTBREAST=PMTCTEXERCISE=PMTCTPREG10_14=PMTCTPREG15_19=PMTCTIRON=PMTCTFOLIC=PMTCTFERROUS=0;	
                
 MATNormalDelivery=MATCSection=MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATFreshStillBirth=MATMeceratedStillBirth=MATDeformities=MATLowAPGAR=MATWeight2500=MATTetracycline=MATPreTerm=MATDischargealive=MATbreastfeeding1=MATDeliveriesPos=MATNeoNatalD=MATMaternalD10_19=MATMaternalD=MATMaternalDAudited=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead=MATREFFromOtherFacility=MATREFFromCU=MATREFToOtherFacility=MATREFToCU=0;	
                        
 SGBVRape72_0_9=SGBVRape72_10_17=SGBVRape72_18_49=SGBVRape72_50=SGBVRape72T=SGBVinitPEP0_9=SGBVinitPEP10_17=SGBVinitPEP18_49=SGBVinitPEP50=SGBVinitPEPT=SGBVcompPEP0_9=SGBVcompPEP10_17=SGBVcompPEP18_49=SGBVcompPEP50=SGBVcompPEPT=SGBVPregnant0_9=SGBVPregnant10_17=SGBVPregnant18_49=SGBVPregnant50=SGBVPregnantT=SGBVseroconverting0_9=SGBVseroconverting10_17=SGBVseroconverting18_49=SGBVseroconverting50=SGBVseroconvertingT=SGBVsurvivors0_9=SGBVsurvivors10_17=SGBVsurvivors18_49=SGBVsurvivors50=SGBVsurvivorsT="";
                                
 PAC10_19=PACT="";	
                                        
 CHANIS0_5NormalweightF=CHANIS0_5NormalweightM=CHANIS0_5NormalweightT=CHANIS0_5UnderweightF=CHANIS0_5UnderweightM=CHANIS0_5UnderweightT=CHANIS0_5sevUnderweightF=CHANIS0_5sevUnderweightM=CHANIS0_5sevUnderweightT=CHANIS0_5OverweightF=CHANIS0_5OverweightM=CHANIS0_5OverweightT=CHANIS0_5ObeseF=CHANIS0_5ObeseM=CHANIS0_5ObeseT=CHANIS0_5TWF=CHANIS0_5TWM=CHANIS0_5TW=CHANIS6_23NormalweightF=CHANIS6_23NormalweightM=CHANIS6_23NormalweightT=CHANIS6_23UnderweightF=CHANIS6_23UnderweightM=CHANIS6_23UnderweightT=CHANIS6_23sevUnderweightF=CHANIS6_23sevUnderweightM=CHANIS6_23sevUnderweightT=CHANIS6_23OverweightF=CHANIS6_23OverweightM=CHANIS6_23OverweightT=CHANIS6_23ObeseF=CHANIS6_23ObeseM=CHANIS6_23ObeseT=CHANIS6_23TWF=CHANIS6_23TWM=CHANIS6_23TW=CHANIS24_59NormalweightF=CHANIS24_59NormalweightM=CHANIS24_59NormalweightT=CHANIS24_59UnderweightF=CHANIS24_59UnderweightM=CHANIS24_59UnderweightT=CHANIS24_59sevUnderweightF=CHANIS24_59sevUnderweightM=CHANIS24_59sevUnderweightT=CHANIS24_59OverweightF=CHANIS24_59OverweightM=CHANIS24_59OverweightT=CHANIS24_59ObeseF=CHANIS24_59ObeseM=CHANIS24_59ObeseT=CHANIS24_59TWF=CHANIS24_59TWM=CHANIS24_59TW=CHANISMUACNormalF=CHANISMUACNormalM=CHANISMUACNormalT=CHANISMUACModerateF=CHANISMUACModerateM=CHANISMUACModerateT=CHANISMUACSevereF=CHANISMUACSevereM=CHANISMUACSevereT=CHANISMUACMeasuredF=CHANISMUACMeasuredM=CHANISMUACMeasuredT="";

 CHANIS0_5NormalHeightF=CHANIS0_5NormalHeightM=CHANIS0_5NormalHeightT=CHANIS0_5StuntedF=CHANIS0_5StuntedM=CHANIS0_5StuntedT=CHANIS0_5sevStuntedF=CHANIS0_5sevStuntedM=CHANIS0_5sevStuntedT=CHANIS0_5TMeasF=CHANIS0_5TMeasM=CHANIS0_5TMeas=CHANIS6_23NormalHeightF=CHANIS6_23NormalHeightM=CHANIS6_23NormalHeightT=CHANIS6_23StuntedF=CHANIS6_23StuntedM=CHANIS6_23StuntedT=CHANIS6_23sevStuntedF=CHANIS6_23sevStuntedM=CHANIS6_23sevStuntedT=CHANIS6_23TMeasF=CHANIS6_23TMeasM=CHANIS6_23TMeas=CHANIS24_59NormalHeightF=CHANIS24_59NormalHeightM=CHANIS24_59NormalHeightT=CHANIS24_59StuntedF=CHANIS24_59StuntedM=CHANIS24_59StuntedT=CHANIS24_59sevStuntedF=CHANIS24_59sevStuntedM=CHANIS24_59sevStuntedT=CHANIS24_59TMeasF=CHANIS24_59TMeasM=CHANIS24_59TMeas=CHANIS0_59NewVisitsF=CHANIS0_59NewVisitsM=CHANIS0_59NewVisitsT=CHANIS0_59KwashiakorF=CHANIS0_59KwashiakorM=CHANIS0_59KwashiakorT=CHANIS0_59MarasmusF=CHANIS0_59MarasmusM=CHANIS0_59MarasmusT=CHANIS0_59FalgrowthF=CHANIS0_59FalgrowthM=CHANIS0_59FalgrowthT=CHANIS0_59F=CHANIS0_59M=CHANIS0_59T=CHANIS0_5EXCLBreastF=CHANIS0_5EXCLBreastM=CHANIS0_5EXCLBreastT=CHANIS12_59DewormedF=CHANIS12_59DewormedM=CHANIS12_59DewormedT=CHANIS6_23MNPsF=CHANIS6_23MNPsM=CHANIS6_23MNPsT=CHANIS0_59DisabilityF=CHANIS0_59DisabilityM=CHANIS0_59DisabilityT="";	
  
 CCSSUSPICIOUSLES24=CCSSUSPICIOUSLES25_49=CCSSUSPICIOUSLES50=CCSVVH24=CCSVVH25_49=CCSVVH50=CCSPAPSMEAR24=CCSPAPSMEAR25_49=CCSPAPSMEAR50=CCSHPV24=CCSHPV25_49=CCSHPV50=CCSVIAVILIPOS24=CCSVIAVILIPOS25_49=CCSVIAVILIPOS50=CCSCYTOLPOS24=CCSCYTOLPOS25_49=CCSCYTOLPOS50=CCSHPVPOS24=CCSHPVPOS25_49=CCSHPVPOS50=CCSCryotherapy24=CCSCryotherapy25_49=CCSCryotherapy50=CCSLEEP24=CCSLEEP25_49=CCSLEEP50=CCSHIVPOSSCREENED24=CCSHIVPOSSCREENED25_49=CCSHIVPOSSCREENED50="";	
                                                
 PNCBreastExam=PNCCounselled=PNCFistula=PNCExerNegative=PNCExerPositive=PNCCCSsuspect=PNCmotherspostpartum2_3=PNCmotherspostpartum6=PNCinfantspostpartum2_3=PNCinfantspostpartum6=PNCreferralsfromotherHF=PNCreferralsfromotherCU=PNCreferralsTootherHF=PNCreferralsTootherCU="";	
                                                        
 RsAssessed=Rstreated=RsRehabilitated=Rsreffered=RsIntergrated="";	
                                                                
 MSWpscounselling=MSWdrugabuse=MSWMental=MSWAdolescent=MSWPsAsses=MSWsocialinv=MSWsocialRehab=MSWoutreach=MSWreferrals=MSWwaivedpatients="";	
                                                                        
 PsotherOPD4=PsotherOPD5_19=PsotherOPD20=Psotherinpatient4=Psotherinpatient5_19=Psotherinpatient20= PsPWDOPD4=PsPWDOPD5_19=PsPWDOPD20=PsPWDinpatient4=PsPWDinpatient5_19=PsPWDinpatient20=PsTreatments4=PsTreatments5_19=PsTreatments20=PsAssessed4=PsAssessed5_19=PsAssessed20=PsServices4=PsServices5_19=PsServices20=PsANCCounsel5_19=PsANCCounsel20=PsExercise5_19=PsExercise20=PsFIFcollected5_19=PsFIFcollected20=PsFIFwaived5_19=PsFIFwaived20=PsFIFexempted4=PsFIFexempted5_19=PsFIFexempted20=PsDiasbilitymeeting4=PsDiasbilitymeeting5_19=PsDiasbilitymeeting20="";

    
    

// VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
//=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
// 
// DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

           
          String checker="SELECT  SUM(FPProgestinN) as FPProgestinN  ,SUM(FPProgestinR) as FPProgestinR ,SUM(FPProgestinT) as FPProgestinT ,SUM(FPCocN) as FPCocN  ,SUM(FPCocR) as FPCocR  ,SUM(FPCocT) as FPCocT  ,SUM(FPEcpN) as FPEcpN  ,SUM(FPEcpR) as FPEcpR  ,SUM(FPEcpT) as FPEcpT  ,SUM(FPINJECTABLESN) as FPINJECTABLESN "
                  + ",SUM(FPINJECTABLESR) as FPINJECTABLESR  ,SUM(FPINJECTABLEST) as FPINJECTABLEST  ,SUM(FPINJECTIONSN) as FPINJECTIONSN  ,SUM(FPINJECTIONSR) as FPINJECTIONSR  ,SUM(FPINJECTIONST) as FPINJECTIONST  ,SUM(FPIUCDN) as FPIUCDN  ,SUM(FPIUCDR) as FPIUCDR  ,SUM(FPIUCDT) as FPIUCDT ,SUM(FPIMPLANTSN) as FPIMPLANTSN  "
                  + ",SUM(FPIMPLANTSR) as FPIMPLANTSR  ,SUM(FPIMPLANTST) as FPIMPLANTST  ,SUM(FPBTLN) as FPBTLN  ,SUM(FPBTLR) as FPBTLR  ,SUM(FPBTLT) as FPBTLT  ,SUM(FPVasectomyN) as FPVasectomyN  ,SUM(FPVasectomyR) as FPVasectomyR  ,SUM(FPVasectomyT) as FPVasectomyT  ,SUM(FPCONDOMSMN) as FPCONDOMSMN  ,SUM(FPCONDOMSFN) as FPCONDOMSFN "
                  + " ,SUM(FPCONDOMST) as FPCONDOMST  ,SUM(FPNaturalN) as FPNaturalN  ,SUM(FPNaturalR) as FPNaturalR  ,SUM(FPNaturalT) as FPNaturalT  ,SUM(FPCLIENTSN) as FPCLIENTSN  ,SUM(FPCLIENTSR) as FPCLIENTSR  ,SUM(FPCLIENTST) as FPCLIENTST  ,SUM(FPADOLESCENT10_14N) as FPADOLESCENT10_14N  ,SUM(FPADOLESCENT10_14R) as FPADOLESCENT10_14R "
                  + " ,SUM(FPADOLESCENT10_14T) as FPADOLESCENT10_14T  ,SUM(FPADOLESCENT15_19N) as FPADOLESCENT15_19N  ,SUM(FPADOLESCENT15_19R) as FPADOLESCENT15_19R  ,SUM(FPADOLESCENT15_19T) as FPADOLESCENT15_19T  ,SUM(FPADOLESCENT20_24N) as FPADOLESCENT20_24N  ,SUM(FPADOLESCENT20_24R) as FPADOLESCENT20_24R ,SUM(FPADOLESCENT20_24T) as FPADOLESCENT20_24T "
                  + " ,SUM(FPIUCDRemoval) as FPIUCDRemoval ,SUM(FPIMPLANTSRemoval) as FPIMPLANTSRemoval  ,  SUM(FPProgestinN) as FPProgestinN, SUM(PMCTA_1stVisit_ANC) as PMCTA_1stVisit_ANC  ,SUM(PMCTA_ReVisit_ANC) as PMCTA_ReVisit_ANC  ,SUM(PMCTANCClientsT) as PMCTANCClientsT  ,SUM(PMCTIPT1) as PMCTIPT1  ,SUM(PMCTIPT2) as PMCTIPT2  ,SUM(PMCTHB11) as PMCTHB11 "
                  + " ,SUM(PMCTANCClients4) as PMCTANCClients4  ,SUM(PMCTITN1) as PMCTITN1  ,SUM(PMCTITN) as PMCTITN  ,SUM(PMTCTSYPHILISTES) as PMTCTSYPHILISTES  ,SUM(PMTCTSYPHILISPOS) as PMTCTSYPHILISPOS  ,SUM(PMTCTCOUNSELLEDFEED) as PMTCTCOUNSELLEDFEED  ,SUM(PMTCTBREAST) as PMTCTBREAST  ,SUM(PMTCTEXERCISE) as PMTCTEXERCISE  ,SUM(PMTCTPREG10_14) as PMTCTPREG10_14 "
                  + " ,SUM(PMTCTPREG15_19) as PMTCTPREG15_19  ,SUM(PMTCTIRON) as PMTCTIRON  ,SUM(PMTCTFOLIC) as PMTCTFOLIC  ,SUM(PMTCTFERROUS) as PMTCTFERROUS  ,  SUM(MATNormalDelivery) as MATNormalDelivery  ,SUM(MATCSection) as MATCSection  ,SUM(MATBreech) as MATBreech  ,SUM(MATAssistedVag) as MATAssistedVag  ,SUM(MATDeliveryT) as MATDeliveryT "
                  + " ,SUM(MATLiveBirth) as MATLiveBirth  ,SUM(MATFreshStillBirth) as MATFreshStillBirth  ,SUM(MATMeceratedStillBirth) as MATMeceratedStillBirth  ,SUM(MATDeformities) as MATDeformities  ,SUM(MATLowAPGAR) as MATLowAPGAR  ,SUM(MATWeight2500) as MATWeight2500  ,SUM(MATTetracycline) as MATTetracycline  ,SUM(MATPreTerm) as MATPreTerm "
                  + " ,SUM(MATDischargealive) as MATDischargealive  ,SUM(MATbreastfeeding1) as MATbreastfeeding1  ,SUM(MATDeliveriesPos) as MATDeliveriesPos  ,SUM(MATNeoNatalD) as MATNeoNatalD  ,SUM(MATMaternalD10_19) as MATMaternalD10_19  ,SUM(MATMaternalD) as MATMaternalD  ,SUM(MATMaternalDAudited) as MATMaternalDAudited  ,SUM(MATAPHAlive) as MATAPHAlive "
                  + " ,SUM(MATAPHDead) as MATAPHDead  ,SUM(MATPPHAlive) as MATPPHAlive  ,SUM(MATPPHDead) as MATPPHDead  ,SUM(MATEclampAlive) as MATEclampAlive  ,SUM(MATEclampDead) as MATEclampDead  ,SUM(MATRupUtAlive) as MATRupUtAlive  ,SUM(MATRupUtDead) as MATRupUtDead  ,SUM(MATObstrLaborAlive) as MATObstrLaborAlive  ,SUM(MATObstrLaborDead) as MATObstrLaborDead "
                  + " ,SUM(MATSepsisAlive) as MATSepsisAlive  ,SUM(MATSepsisDead) as MATSepsisDead  ,SUM(MATREFFromOtherFacility) as MATREFFromOtherFacility  ,SUM(MATREFFromCU) as MATREFFromCU  ,SUM(MATREFToOtherFacility) as MATREFToOtherFacility  ,SUM(MATREFToCU) as MATREFToCU  "+	
" FROM moh711_new WHERE "+facility+" "+duration ;
          System.out.println("@@@"+checker);
          
          conn.rs=conn.st.executeQuery(checker);
      

if(conn.rs.next()==true){
        
          String headerValues=countyName+","+districtName+","+facilityName+","+mflcode;
          String arrayValues[]=headerValues.split(",");
          String headerValue="";  
//   CREATE HEADERS
     for(String headername:arrayHeader){
   headerValue=arrayValues[arrayCounter];
   XSSFRow rw1S10=shet1.createRow(pos);  
   XSSFRow rw1S20=shet2.createRow(pos);
   XSSFRow rw1S30=shet3.createRow(pos);
   //XSSFRow rw1S40=shet4.createRow(pos);
   //XSSFRow rw1S50=shet5.createRow(pos);
  
    
    XSSFCell  S1cell=rw1S10.createCell(0);
    S1cell.setCellValue(headername+" : "+headerValue);
    S1cell.setCellStyle(stylex);
    
    XSSFCell  S1cellX=rw1S10.createCell(1);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(2);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    S1cellX=rw1S10.createCell(3);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
   
     S1cellX=rw1S10.createCell(4);
    S1cellX.setCellValue("");
    S1cellX.setCellStyle(stylex);
    
    XSSFCell  S2cell=rw1S20.createCell(0);
    S2cell.setCellValue(headername +" : "+headerValue);
    S2cell.setCellStyle(stylex);
    
    XSSFCell  S2cellX=rw1S20.createCell(1);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
    S2cellX=rw1S20.createCell(2);
    S2cellX.setCellValue("");
    S2cellX.setCellStyle(stylex);
    
//    S2cellX=rw1S20.createCell(3);
//    S2cellX.setCellValue("");
//    S2cellX.setCellStyle(stylex);
    
    
    XSSFCell  S3cell=rw1S30.createCell(0);
    S3cell.setCellValue(headername +" : "+headerValue);
    S3cell.setCellStyle(stylex);
    
    XSSFCell  S3cellX=rw1S30.createCell(1);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
    S3cellX=rw1S30.createCell(2);
    S3cellX.setCellValue("");
    S3cellX.setCellStyle(stylex);
    
      arrayCounter++;
     
      shet1.addMergedRegion(new CellRangeAddress(pos,pos,0,4));
      shet2.addMergedRegion(new CellRangeAddress(pos,pos,0,2));
      shet3.addMergedRegion(new CellRangeAddress(pos,pos,0,2));
  
      pos++;
     } 
      
   
     
  if(conn.rs.getString("FPProgestinN")!=null){ FPProgestinN=conn.rs.getInt("FPProgestinN");}else{ FPProgestinN=0;}
  if(conn.rs.getString("FPProgestinR")!=null){ FPProgestinR=conn.rs.getInt("FPProgestinR");}else{ FPProgestinR=0;}
  if(conn.rs.getString("FPProgestinT")!=null){ FPProgestinT=conn.rs.getInt("FPProgestinT");}else{ FPProgestinT=0;}
  if(conn.rs.getString("FPCocN")!=null){ FPCocN=conn.rs.getInt("FPCocN");}else{ FPCocN=0;}
  if(conn.rs.getString("FPCocR")!=null){ FPCocR=conn.rs.getInt("FPCocR");}else{ FPCocR=0;}
  if(conn.rs.getString("FPCocT")!=null){ FPCocT=conn.rs.getInt("FPCocT");}else{ FPCocT=0;}
  if(conn.rs.getString("FPEcpN")!=null){ FPEcpN=conn.rs.getInt("FPEcpN");}else{ FPEcpN=0;}
  if(conn.rs.getString("FPEcpR")!=null){ FPEcpR=conn.rs.getInt("FPEcpR");}else{ FPEcpR=0;}
  if(conn.rs.getString("FPEcpT")!=null){ FPEcpT=conn.rs.getInt("FPEcpT");}else{ FPEcpT=0;}
  if(conn.rs.getString("FPINJECTABLESN")!=null){ FPINJECTABLESN=conn.rs.getInt("FPINJECTABLESN");}else{ FPINJECTABLESN=0;}
  if(conn.rs.getString("FPINJECTABLESR")!=null){ FPINJECTABLESR=conn.rs.getInt("FPINJECTABLESR");}else{ FPINJECTABLESR=0;}
  if(conn.rs.getString("FPINJECTABLEST")!=null){ FPINJECTABLEST=conn.rs.getInt("FPINJECTABLEST");}else{ FPINJECTABLEST=0;}
  if(conn.rs.getString("FPINJECTIONSN")!=null){ FPINJECTIONSN=conn.rs.getInt("FPINJECTIONSN");}else{ FPINJECTIONSN=0;}
  if(conn.rs.getString("FPINJECTIONSR")!=null){ FPINJECTIONSR=conn.rs.getInt("FPINJECTIONSR");}else{ FPINJECTIONSR=0;}
  if(conn.rs.getString("FPINJECTIONST")!=null){ FPINJECTIONST=conn.rs.getInt("FPINJECTIONST");}else{ FPINJECTIONST=0;}
  if(conn.rs.getString("FPIUCDN")!=null){ FPIUCDN=conn.rs.getInt("FPIUCDN");}else{ FPIUCDN=0;}
  if(conn.rs.getString("FPIUCDR")!=null){ FPIUCDR=conn.rs.getInt("FPIUCDR");}else{ FPIUCDR=0;}
  if(conn.rs.getString("FPIUCDT")!=null){ FPIUCDT=conn.rs.getInt("FPIUCDT");}else{ FPIUCDT=0;}
  if(conn.rs.getString("FPIMPLANTSN")!=null){ FPIMPLANTSN=conn.rs.getInt("FPIMPLANTSN");}else{ FPIMPLANTSN=0;}
  if(conn.rs.getString("FPIMPLANTSR")!=null){ FPIMPLANTSR=conn.rs.getInt("FPIMPLANTSR");}else{ FPIMPLANTSR=0;}
  if(conn.rs.getString("FPIMPLANTST")!=null){ FPIMPLANTST=conn.rs.getInt("FPIMPLANTST");}else{ FPIMPLANTST=0;}
  if(conn.rs.getString("FPBTLN")!=null){ FPBTLN=conn.rs.getInt("FPBTLN");}else{ FPBTLN=0;}
  if(conn.rs.getString("FPBTLR")!=null){ FPBTLR=conn.rs.getInt("FPBTLR");}else{ FPBTLR=0;}
  if(conn.rs.getString("FPBTLT")!=null){ FPBTLT=conn.rs.getInt("FPBTLT");}else{ FPBTLT=0;}
  if(conn.rs.getString("FPVasectomyN")!=null){ FPVasectomyN=conn.rs.getInt("FPVasectomyN");}else{ FPVasectomyN=0;}
  if(conn.rs.getString("FPVasectomyR")!=null){ FPVasectomyR=conn.rs.getInt("FPVasectomyR");}else{ FPVasectomyR=0;}
  if(conn.rs.getString("FPVasectomyT")!=null){ FPVasectomyT=conn.rs.getInt("FPVasectomyT");}else{ FPVasectomyT=0;}
  if(conn.rs.getString("FPCONDOMSMN")!=null){ FPCONDOMSMN=conn.rs.getInt("FPCONDOMSMN");}else{ FPCONDOMSMN=0;}
  if(conn.rs.getString("FPCONDOMSFN")!=null){ FPCONDOMSFN=conn.rs.getInt("FPCONDOMSFN");}else{ FPCONDOMSFN=0;}
  if(conn.rs.getString("FPCONDOMST")!=null){ FPCONDOMST=conn.rs.getInt("FPCONDOMST");}else{ FPCONDOMST=0;}
  if(conn.rs.getString("FPNaturalN")!=null){ FPNaturalN=conn.rs.getInt("FPNaturalN");}else{ FPNaturalN=0;}
  if(conn.rs.getString("FPNaturalR")!=null){ FPNaturalR=conn.rs.getInt("FPNaturalR");}else{ FPNaturalR=0;}
  if(conn.rs.getString("FPNaturalT")!=null){ FPNaturalT=conn.rs.getInt("FPNaturalT");}else{ FPNaturalT=0;}
  if(conn.rs.getString("FPCLIENTSN")!=null){ FPCLIENTSN=conn.rs.getInt("FPCLIENTSN");}else{ FPCLIENTSN=0;}
  if(conn.rs.getString("FPCLIENTSR")!=null){ FPCLIENTSR=conn.rs.getInt("FPCLIENTSR");}else{ FPCLIENTSR=0;}
  if(conn.rs.getString("FPCLIENTST")!=null){ FPCLIENTST=conn.rs.getInt("FPCLIENTST");}else{ FPCLIENTST=0;}
  if(conn.rs.getString("FPADOLESCENT10_14N")!=null){ FPADOLESCENT10_14N=conn.rs.getInt("FPADOLESCENT10_14N");}else{ FPADOLESCENT10_14N=0;}
  if(conn.rs.getString("FPADOLESCENT10_14R")!=null){ FPADOLESCENT10_14R=conn.rs.getInt("FPADOLESCENT10_14R");}else{ FPADOLESCENT10_14R=0;}
  if(conn.rs.getString("FPADOLESCENT10_14T")!=null){ FPADOLESCENT10_14T=conn.rs.getInt("FPADOLESCENT10_14T");}else{ FPADOLESCENT10_14T=0;}
  if(conn.rs.getString("FPADOLESCENT15_19N")!=null){ FPADOLESCENT15_19N=conn.rs.getInt("FPADOLESCENT15_19N");}else{ FPADOLESCENT15_19N=0;}
  if(conn.rs.getString("FPADOLESCENT15_19R")!=null){ FPADOLESCENT15_19R=conn.rs.getInt("FPADOLESCENT15_19R");}else{ FPADOLESCENT15_19R=0;}
  if(conn.rs.getString("FPADOLESCENT15_19T")!=null){ FPADOLESCENT15_19T=conn.rs.getInt("FPADOLESCENT15_19T");}else{ FPADOLESCENT15_19T=0;}
  if(conn.rs.getString("FPADOLESCENT20_24N")!=null){ FPADOLESCENT20_24N=conn.rs.getInt("FPADOLESCENT20_24N");}else{ FPADOLESCENT20_24N=0;}
  if(conn.rs.getString("FPADOLESCENT20_24R")!=null){ FPADOLESCENT20_24R=conn.rs.getInt("FPADOLESCENT20_24R");}else{ FPADOLESCENT20_24R=0;}
  if(conn.rs.getString("FPADOLESCENT20_24T")!=null){ FPADOLESCENT20_24T=conn.rs.getInt("FPADOLESCENT20_24T");}else{ FPADOLESCENT20_24T=0;}
  if(conn.rs.getString("FPIUCDRemoval")!=null){ FPIUCDRemoval=conn.rs.getInt("FPIUCDRemoval");}else{ FPIUCDRemoval=0;}
  if(conn.rs.getString("FPIMPLANTSRemoval")!=null){ FPIMPLANTSRemoval=conn.rs.getInt("FPIMPLANTSRemoval");}else{ FPIMPLANTSRemoval=0;}

  
  
  //=======================================================PMTCT ANC===================================================
  
       
    
    if(conn.rs.getString("PMCTA_1stVisit_ANC")!=null){ PMCTA_1stVisit_ANC=conn.rs.getInt("PMCTA_1stVisit_ANC");}else{ PMCTA_1stVisit_ANC=0;}
    if(conn.rs.getString("PMCTA_ReVisit_ANC")!=null){ PMCTA_ReVisit_ANC=conn.rs.getInt("PMCTA_ReVisit_ANC");}else{ PMCTA_ReVisit_ANC=0;}
    if(conn.rs.getString("PMCTANCClientsT")!=null){ PMCTANCClientsT=conn.rs.getInt("PMCTANCClientsT");}else{ PMCTANCClientsT=0;}
    if(conn.rs.getString("PMCTIPT1")!=null){ PMCTIPT1=conn.rs.getInt("PMCTIPT1");}else{ PMCTIPT1=0;}
    if(conn.rs.getString("PMCTIPT2")!=null){ PMCTIPT2=conn.rs.getInt("PMCTIPT2");}else{ PMCTIPT2=0;}
    if(conn.rs.getString("PMCTHB11")!=null){ PMCTHB11=conn.rs.getInt("PMCTHB11");}else{ PMCTHB11=0;}
    if(conn.rs.getString("PMCTANCClients4")!=null){ PMCTANCClients4=conn.rs.getInt("PMCTANCClients4");}else{ PMCTANCClients4=0;}
    if(conn.rs.getString("PMCTITN1")!=null){ PMCTITN1=conn.rs.getInt("PMCTITN1");}else{ PMCTITN1=0;}
    if(conn.rs.getString("PMCTITN")!=null){ PMCTITN=conn.rs.getInt("PMCTITN");}else{ PMCTITN=0;}
    if(conn.rs.getString("PMTCTSYPHILISTES")!=null){ PMTCTSYPHILISTES=conn.rs.getInt("PMTCTSYPHILISTES");}else{ PMTCTSYPHILISTES=0;}
    if(conn.rs.getString("PMTCTSYPHILISPOS")!=null){ PMTCTSYPHILISPOS=conn.rs.getInt("PMTCTSYPHILISPOS");}else{ PMTCTSYPHILISPOS=0;}
    if(conn.rs.getString("PMTCTCOUNSELLEDFEED")!=null){ PMTCTCOUNSELLEDFEED=conn.rs.getInt("PMTCTCOUNSELLEDFEED");}else{ PMTCTCOUNSELLEDFEED=0;}
    if(conn.rs.getString("PMTCTBREAST")!=null){ PMTCTBREAST=conn.rs.getInt("PMTCTBREAST");}else{ PMTCTBREAST=0;}
    if(conn.rs.getString("PMTCTEXERCISE")!=null){ PMTCTEXERCISE=conn.rs.getInt("PMTCTEXERCISE");}else{ PMTCTEXERCISE=0;}
    if(conn.rs.getString("PMTCTPREG10_14")!=null){ PMTCTPREG10_14=conn.rs.getInt("PMTCTPREG10_14");}else{ PMTCTPREG10_14=0;}
    if(conn.rs.getString("PMTCTPREG15_19")!=null){ PMTCTPREG15_19=conn.rs.getInt("PMTCTPREG15_19");}else{ PMTCTPREG15_19=0;}
    if(conn.rs.getString("PMTCTIRON")!=null){ PMTCTIRON=conn.rs.getInt("PMTCTIRON");}else{ PMTCTIRON=0;}
    if(conn.rs.getString("PMTCTFOLIC")!=null){ PMTCTFOLIC=conn.rs.getInt("PMTCTFOLIC");}else{ PMTCTFOLIC=0;}
    if(conn.rs.getString("PMTCTFERROUS")!=null){ PMTCTFERROUS=conn.rs.getInt("PMTCTFERROUS");}else{ PMTCTFERROUS=0;}
    
    
  
  
  
  
   //===================================================MATERNITY================================================
    
  
    if(conn.rs.getString("MATNormalDelivery")!=null){ MATNormalDelivery=conn.rs.getInt("MATNormalDelivery");}else{ MATNormalDelivery=0;}
    if(conn.rs.getString("MATCSection")!=null){ MATCSection=conn.rs.getInt("MATCSection");}else{ MATCSection=0;}
    if(conn.rs.getString("MATBreech")!=null){ MATBreech=conn.rs.getInt("MATBreech");}else{ MATBreech=0;}
    if(conn.rs.getString("MATAssistedVag")!=null){ MATAssistedVag=conn.rs.getInt("MATAssistedVag");}else{ MATAssistedVag=0;}
    if(conn.rs.getString("MATDeliveryT")!=null){ MATDeliveryT=conn.rs.getInt("MATDeliveryT");}else{ MATDeliveryT=0;}
    if(conn.rs.getString("MATLiveBirth")!=null){ MATLiveBirth=conn.rs.getInt("MATLiveBirth");}else{ MATLiveBirth=0;}
    if(conn.rs.getString("MATFreshStillBirth")!=null){ MATFreshStillBirth=conn.rs.getInt("MATFreshStillBirth");}else{ MATFreshStillBirth=0;}
    if(conn.rs.getString("MATMeceratedStillBirth")!=null){ MATMeceratedStillBirth=conn.rs.getInt("MATMeceratedStillBirth");}else{ MATMeceratedStillBirth=0;}
    if(conn.rs.getString("MATDeformities")!=null){ MATDeformities=conn.rs.getInt("MATDeformities");}else{ MATDeformities=0;}
    if(conn.rs.getString("MATLowAPGAR")!=null){ MATLowAPGAR=conn.rs.getInt("MATLowAPGAR");}else{ MATLowAPGAR=0;}
    if(conn.rs.getString("MATWeight2500")!=null){ MATWeight2500=conn.rs.getInt("MATWeight2500");}else{ MATWeight2500=0;}
    if(conn.rs.getString("MATTetracycline")!=null){ MATTetracycline=conn.rs.getInt("MATTetracycline");}else{ MATTetracycline=0;}
    if(conn.rs.getString("MATPreTerm")!=null){ MATPreTerm=conn.rs.getInt("MATPreTerm");}else{ MATPreTerm=0;}
    if(conn.rs.getString("MATDischargealive")!=null){ MATDischargealive=conn.rs.getInt("MATDischargealive");}else{ MATDischargealive=0;}
    if(conn.rs.getString("MATbreastfeeding1")!=null){ MATbreastfeeding1=conn.rs.getInt("MATbreastfeeding1");}else{ MATbreastfeeding1=0;}
    if(conn.rs.getString("MATDeliveriesPos")!=null){ MATDeliveriesPos=conn.rs.getInt("MATDeliveriesPos");}else{ MATDeliveriesPos=0;}
    if(conn.rs.getString("MATNeoNatalD")!=null){ MATNeoNatalD=conn.rs.getInt("MATNeoNatalD");}else{ MATNeoNatalD=0;}
    if(conn.rs.getString("MATMaternalD10_19")!=null){ MATMaternalD10_19=conn.rs.getInt("MATMaternalD10_19");}else{ MATMaternalD10_19=0;}
    if(conn.rs.getString("MATMaternalD")!=null){ MATMaternalD=conn.rs.getInt("MATMaternalD");}else{ MATMaternalD=0;}
    if(conn.rs.getString("MATMaternalDAudited")!=null){ MATMaternalDAudited=conn.rs.getInt("MATMaternalDAudited");}else{ MATMaternalDAudited=0;}
    if(conn.rs.getString("MATAPHAlive")!=null){ MATAPHAlive=conn.rs.getInt("MATAPHAlive");}else{ MATAPHAlive=0;}
    if(conn.rs.getString("MATAPHDead")!=null){ MATAPHDead=conn.rs.getInt("MATAPHDead");}else{ MATAPHDead=0;}
    if(conn.rs.getString("MATPPHAlive")!=null){ MATPPHAlive=conn.rs.getInt("MATPPHAlive");}else{ MATPPHAlive=0;}
    if(conn.rs.getString("MATPPHDead")!=null){ MATPPHDead=conn.rs.getInt("MATPPHDead");}else{ MATPPHDead=0;}
    if(conn.rs.getString("MATEclampAlive")!=null){ MATEclampAlive=conn.rs.getInt("MATEclampAlive");}else{ MATEclampAlive=0;}
    if(conn.rs.getString("MATEclampDead")!=null){ MATEclampDead=conn.rs.getInt("MATEclampDead");}else{ MATEclampDead=0;}
    if(conn.rs.getString("MATRupUtAlive")!=null){ MATRupUtAlive=conn.rs.getInt("MATRupUtAlive");}else{ MATRupUtAlive=0;}
    if(conn.rs.getString("MATRupUtDead")!=null){ MATRupUtDead=conn.rs.getInt("MATRupUtDead");}else{ MATRupUtDead=0;}
    if(conn.rs.getString("MATObstrLaborAlive")!=null){ MATObstrLaborAlive=conn.rs.getInt("MATObstrLaborAlive");}else{ MATObstrLaborAlive=0;}
    if(conn.rs.getString("MATObstrLaborDead")!=null){ MATObstrLaborDead=conn.rs.getInt("MATObstrLaborDead");}else{ MATObstrLaborDead=0;}
    if(conn.rs.getString("MATSepsisAlive")!=null){ MATSepsisAlive=conn.rs.getInt("MATSepsisAlive");}else{ MATSepsisAlive=0;}
    if(conn.rs.getString("MATSepsisDead")!=null){ MATSepsisDead=conn.rs.getInt("MATSepsisDead");}else{ MATSepsisDead=0;}
    if(conn.rs.getString("MATREFFromOtherFacility")!=null){ MATREFFromOtherFacility=conn.rs.getInt("MATREFFromOtherFacility");}else{ MATREFFromOtherFacility=0;}
    if(conn.rs.getString("MATREFFromCU")!=null){ MATREFFromCU=conn.rs.getInt("MATREFFromCU");}else{ MATREFFromCU=0;}
    if(conn.rs.getString("MATREFToOtherFacility")!=null){ MATREFToOtherFacility=conn.rs.getInt("MATREFToOtherFacility");}else{ MATREFToOtherFacility=0;}
    if(conn.rs.getString("MATREFToCU")!=null){ MATREFToCU=conn.rs.getInt("MATREFToCU");}else{ MATREFToCU=0;}
    
  
     
     
     
         System.out.println("xxxxx "+newpos+" "+pos);
//        newpos=pos+1;  
       int rowxx=5;
  //----fp header row----
    XSSFRow rw1S10=shet1.createRow(rowxx);  
  
    XSSFCell  rw1cell1=rw1S10.createCell(0);
    rw1cell1.setCellValue("D. Family Planning (Number of Clients issued with contraceptives)");
    rw1cell1.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell2=rw1S10.createCell(1);
    rw1cell2.setCellValue("");
    rw1cell2.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell3=rw1S10.createCell(2);
    rw1cell3.setCellValue("NEW");
    rw1cell3.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell4=rw1S10.createCell(3);
    rw1cell4.setCellValue("RE-VISITS");
    rw1cell4.setCellStyle(styleHeader);
    
  XSSFCell  rw1cell5=rw1S10.createCell(4);
    rw1cell5.setCellValue("TOTAL");
    rw1cell5.setCellStyle(styleHeader);
    
  //-----fp first row  
     rowxx++;
    XSSFRow rw2S10=shet1.createRow(rowxx);    
    XSSFCell  rw2cell2=rw2S10.createCell(0);
    rw2cell2.setCellValue("Pills ");
    rw2cell2.setCellStyle(stborder);
    
    XSSFCell  rw2cell3=rw2S10.createCell(1);
    rw2cell3.setCellValue("Progestin Only pills ");
    rw2cell3.setCellStyle(stborder);
    
    XSSFCell  rw2cell4=rw2S10.createCell(2);
    rw2cell4.setCellValue(FPProgestinN);
    rw2cell4.setCellStyle(stborder);
    XSSFCell  rw2cell5=rw2S10.createCell(3);
    rw2cell5.setCellValue(FPProgestinR);
    rw2cell5.setCellStyle(stborder);
    XSSFCell  rw2cell6=rw2S10.createCell(4);
    rw2cell6.setCellValue(FPProgestinT);
    rw2cell6.setCellStyle(stborder);
    rowxx++;
    //fp row 2 combined oral contraceptives
    if(3==3){ //this helps in using the same variable name severally
    XSSFRow rw3S10=shet1.createRow(rowxx);    
    XSSFCell  rw3cell2=rw3S10.createCell(0);
    rw3cell2.setCellValue("");
    rw3cell2.setCellStyle(stborder);
    
    XSSFCell  rw3cell3=rw3S10.createCell(1);
    rw3cell3.setCellValue("Combined Oral Contraceptive pills");
    rw3cell3.setCellStyle(stborder);
    
    XSSFCell  rw3cell4=rw3S10.createCell(2);
    rw3cell4.setCellValue(FPCocN);
    rw3cell4.setCellStyle(stborder);
    
    XSSFCell  rw3cell5=rw3S10.createCell(3);
    rw3cell5.setCellValue(FPCocR);
    rw3cell5.setCellStyle(stborder);
    
    XSSFCell  rw3cell6=rw3S10.createCell(4);
    rw3cell6.setCellValue(FPCocT);
    rw3cell6.setCellStyle(stborder);
    
}
   rowxx++; 
    //fp row 3 Emergency contraceptive pills
    XSSFRow rw3S10=shet1.createRow(rowxx);    
    XSSFCell  rw3cell2=rw3S10.createCell(0);
    rw3cell2.setCellValue("");
    rw3cell2.setCellStyle(stborder);
    
    XSSFCell  rw3cell3=rw3S10.createCell(1);
    rw3cell3.setCellValue("Emergency Contraceptive pills");
    rw3cell3.setCellStyle(stborder);
    
    XSSFCell  rw3cell4=rw3S10.createCell(2);
    rw3cell4.setCellValue(FPEcpN);
    rw3cell4.setCellStyle(stborder);
    
    XSSFCell  rw3cell5=rw3S10.createCell(3);
    rw3cell5.setCellValue(FPEcpR);
    rw3cell5.setCellStyle(stborder);
    
    XSSFCell  rw3cell6=rw3S10.createCell(4);
    rw3cell6.setCellValue(FPEcpT);
    rw3cell6.setCellStyle(stborder);
    
     shet1.addMergedRegion(new CellRangeAddress(rowxx-2,rowxx,0,0)); 
     
    // fp row 4 injectables 
     rowxx++;
    XSSFRow rw4S10=shet1.createRow(rowxx);    
    XSSFCell  rw4cell2=rw4S10.createCell(0);
    rw4cell2.setCellValue("");
    rw4cell2.setCellStyle(stborder);
    
    XSSFCell  rw4cell3=rw4S10.createCell(1);
    rw4cell3.setCellValue("Injectables");
    rw4cell3.setCellStyle(stborder);
    
    XSSFCell  rw4cell4=rw4S10.createCell(2);
    rw4cell4.setCellValue(FPINJECTIONSN);
    rw4cell4.setCellStyle(stborder);
    
    XSSFCell  rw4cell5=rw4S10.createCell(3);
    rw4cell5.setCellValue(FPINJECTIONSR);
    rw4cell5.setCellStyle(stborder);
    
    XSSFCell  rw4cell6=rw4S10.createCell(4);
    rw4cell6.setCellValue(FPINJECTIONST);
    rw4cell6.setCellStyle(stborder);
    
    rowxx++;
      XSSFRow rw5S10=shet1.createRow(rowxx);    
    XSSFCell  rw5cell2=rw5S10.createCell(0);
    rw5cell2.setCellValue("Injections");
    rw5cell2.setCellStyle(stborder);
    
    XSSFCell  rw5cell3=rw5S10.createCell(1);
    rw5cell3.setCellValue("Insertion ");
    rw5cell3.setCellStyle(stborder);
    
    XSSFCell  rw5cell4=rw5S10.createCell(2);
    rw5cell4.setCellValue(FPIUCDN);
    rw5cell4.setCellStyle(stborder);
    
    XSSFCell  rw5cell5=rw5S10.createCell(3);
    rw5cell5.setCellValue(FPIUCDR);
    rw5cell5.setCellStyle(stborder);
    
    XSSFCell  rw5cell6=rw5S10.createCell(4);
    rw5cell6.setCellValue(FPIUCDT);
    rw5cell6.setCellStyle(stborder);
    
    rowxx++;
    
     XSSFRow rw6S10=shet1.createRow(rowxx);    
    XSSFCell  rw6cell2=rw6S10.createCell(0);
    rw6cell2.setCellValue("I.U.C.D.");
    rw6cell2.setCellStyle(stborder);
    
    XSSFCell  rw6cell3=rw6S10.createCell(1);
    rw6cell3.setCellValue("Insertion ");
    rw6cell3.setCellStyle(stborder);
    
    XSSFCell  rw6cell4=rw6S10.createCell(2);
    rw6cell4.setCellValue(FPIMPLANTSN);
    rw6cell4.setCellStyle(stborder);
    
    XSSFCell  rw6cell5=rw6S10.createCell(3);
    rw6cell5.setCellValue(FPIMPLANTSR);
    rw6cell5.setCellStyle(stborder);
    
    XSSFCell  rw6cell6=rw6S10.createCell(4);
    rw6cell6.setCellValue(FPIMPLANTST);
    rw6cell6.setCellStyle(stborder);
    
    rowxx++;
     XSSFRow rw7S10=shet1.createRow(rowxx);    
    XSSFCell  rw7cell2=rw7S10.createCell(0);
    rw7cell2.setCellValue("Implants");
    rw7cell2.setCellStyle(stborder);
    
    XSSFCell  rw7cell3=rw7S10.createCell(1);
    rw7cell3.setCellValue("B.T.L. ");
    rw7cell3.setCellStyle(stborder);
    
    XSSFCell  rw7cell4=rw7S10.createCell(2);
    rw7cell4.setCellValue(FPBTLN);
    rw7cell4.setCellStyle(stborder);
    
    XSSFCell  rw7cell5=rw7S10.createCell(3);
    rw7cell5.setCellValue(FPBTLR);
    rw7cell5.setCellStyle(stborder);
    
    XSSFCell  rw7cell6=rw7S10.createCell(4);
    rw7cell6.setCellValue(FPBTLT);
    rw7cell6.setCellStyle(stborder);
    
    rowxx++;
     XSSFRow rw8S10=shet1.createRow(rowxx);    
    XSSFCell  rw8cell2=rw8S10.createCell(0);
    rw8cell2.setCellValue("Sterilization");
    rw8cell2.setCellStyle(stborder);
    
    XSSFCell  rw8cell3=rw8S10.createCell(1);
    rw8cell3.setCellValue("Vasectomy");
    rw8cell3.setCellStyle(stborder);
    
    XSSFCell  rw8cell4=rw8S10.createCell(2);
    rw8cell4.setCellValue(FPVasectomyN);
    rw8cell4.setCellStyle(stborder);
    
    XSSFCell  rw8cell5=rw8S10.createCell(3);
    rw8cell5.setCellValue(FPVasectomyR);
    rw8cell5.setCellStyle(stborder);
    
    XSSFCell  rw8cell6=rw8S10.createCell(4);
    rw8cell6.setCellValue(FPVasectomyT);
    rw8cell6.setCellStyle(stborder);
   //shet1.addMergedRegion(new CellRangeAddress(rowxx-1,rowxx,0,0));
    
    rowxx++;
     if(13==13){
     XSSFRow rw9S10=shet1.createRow(rowxx);    
    XSSFCell  rw9cell2=rw9S10.createCell(0);
    rw9cell2.setCellValue("Condoms");
    rw9cell2.setCellStyle(stborder);
    
    XSSFCell  rw9cell3=rw9S10.createCell(1);
    rw9cell3.setCellValue("No. of clients received Male Condoms ");
    rw9cell3.setCellStyle(stborder);
    
    XSSFCell  rw9cell4=rw9S10.createCell(2);
    rw9cell4.setCellValue(FPCONDOMSFN);
    rw9cell4.setCellStyle(stborder);
    
    XSSFCell  rw9cell5=rw9S10.createCell(3);
    rw9cell5.setCellValue("");
    rw9cell5.setCellStyle(stborder);
    
    XSSFCell  rw9cell6=rw9S10.createCell(4);
    rw9cell6.setCellValue(FPCONDOMSFN);
    rw9cell6.setCellStyle(stborder);
     }
    rowxx++;
    //fp condoms male
    if(14==14){
     XSSFRow rw10S10=shet1.createRow(rowxx);    
    XSSFCell  rw10cell2=rw10S10.createCell(0);
    rw10cell2.setCellValue("");
    rw10cell2.setCellStyle(stborder);
    
    XSSFCell  rw10cell3=rw10S10.createCell(1);
    rw10cell3.setCellValue("No. of clients received (Female condoms)");
    rw10cell3.setCellStyle(stborder);
    
    XSSFCell  rw10cell4=rw10S10.createCell(2);
    rw10cell4.setCellValue(FPCONDOMSMN);
    rw10cell4.setCellStyle(stborder);
    
    XSSFCell  rw10cell5=rw10S10.createCell(3);
    rw10cell5.setCellValue("");
    rw10cell5.setCellStyle(stborder);
    
    XSSFCell  rw10cell6=rw10S10.createCell(4);
    rw10cell6.setCellValue(FPCONDOMSMN);
    rw10cell6.setCellStyle(stborder);
    }
    
    shet1.addMergedRegion(new CellRangeAddress(rowxx-1,rowxx,0,0)); 
    //
    rowxx++;
    XSSFRow rw11S10=shet1.createRow(rowxx);    
    XSSFCell  rw11cell2=rw11S10.createCell(0);
    rw11cell2.setCellValue("Natural Family Planning");
    rw11cell2.setCellStyle(stborder);
    
    XSSFCell  rw11cell3=rw11S10.createCell(1);
    rw11cell3.setCellValue(" ");
    rw11cell3.setCellStyle(stborder);
    
    XSSFCell  rw11cell4=rw11S10.createCell(2);
    rw11cell4.setCellValue(FPNaturalN);
    rw11cell4.setCellStyle(stborder);
    
    XSSFCell  rw11cell5=rw11S10.createCell(3);
    rw11cell5.setCellValue(FPNaturalR);
    rw11cell5.setCellStyle(stborder);
    
    XSSFCell  rw11cell6=rw11S10.createCell(4);
    rw11cell6.setCellValue(FPNaturalT);
    rw11cell6.setCellStyle(stborder);
    
   rowxx++; 
    //total number of clients 
    if(16==16){
     XSSFRow rw12S10=shet1.createRow(rowxx);    
    XSSFCell  rw12cell2=rw12S10.createCell(0);
    rw12cell2.setCellValue("Total Number of Clients");
    rw12cell2.setCellStyle(stborder);
    
    XSSFCell  rw12cell3=rw12S10.createCell(1);
    rw12cell3.setCellValue("");
    rw12cell3.setCellStyle(stborder);
    
    XSSFCell  rw12cell4=rw12S10.createCell(2);
    rw12cell4.setCellValue(FPCLIENTSN);
    rw12cell4.setCellStyle(stborder);
    
    XSSFCell  rw12cell5=rw12S10.createCell(3);
    rw12cell5.setCellValue(FPCLIENTSR);
    rw12cell5.setCellStyle(stborder);
    
    XSSFCell  rw12cell6=rw12S10.createCell(4);
    rw12cell6.setCellValue(FPCLIENTST);
    rw12cell6.setCellStyle(stborder);
}
    //continue with the rest of the rows here
    
    rowxx++;
       if(16==16){
     XSSFRow rw12S10=shet1.createRow(rowxx);    
    XSSFCell  rw12cell2=rw12S10.createCell(0);
    rw12cell2.setCellValue("Total adolescent clients (10-14YRS) receiving FP Services");
    rw12cell2.setCellStyle(stborder);
    
    XSSFCell  rw12cell3=rw12S10.createCell(1);
    rw12cell3.setCellValue("");
    rw12cell3.setCellStyle(stborder);
    
    XSSFCell  rw12cell4=rw12S10.createCell(2);
    rw12cell4.setCellValue(FPADOLESCENT10_14N);
    rw12cell4.setCellStyle(stborder);
    
    XSSFCell  rw12cell5=rw12S10.createCell(3);
    rw12cell5.setCellValue(FPADOLESCENT10_14R);
    rw12cell5.setCellStyle(stborder);
    
    XSSFCell  rw12cell6=rw12S10.createCell(4);
    rw12cell6.setCellValue(FPADOLESCENT10_14T);
    rw12cell6.setCellStyle(stborder);
}
    
    
       
  rowxx++;
       if(17==17){
     XSSFRow rw12S10=shet1.createRow(rowxx);    
    XSSFCell  rw12cell2=rw12S10.createCell(0);
    rw12cell2.setCellValue("Total adolescent clients (15-19YRS) receiving FP Services");
    rw12cell2.setCellStyle(stborder);
    
    XSSFCell  rw12cell3=rw12S10.createCell(1);
    rw12cell3.setCellValue("");
    rw12cell3.setCellStyle(stborder);
    
    XSSFCell  rw12cell4=rw12S10.createCell(2);
    rw12cell4.setCellValue(FPADOLESCENT15_19N);
    rw12cell4.setCellStyle(stborder);
    
    XSSFCell  rw12cell5=rw12S10.createCell(3);
    rw12cell5.setCellValue(FPADOLESCENT15_19R);
    rw12cell5.setCellStyle(stborder);
    
    XSSFCell  rw12cell6=rw12S10.createCell(4);
    rw12cell6.setCellValue(FPADOLESCENT15_19T);
    rw12cell6.setCellStyle(stborder);
}       
    
       
         rowxx++;
       if(18==18)
       {
     XSSFRow rw12S10=shet1.createRow(rowxx);    
    XSSFCell  rw12cell2=rw12S10.createCell(0);
    rw12cell2.setCellValue("Total youth clients (20-24YRS) receiving FP Services");
    rw12cell2.setCellStyle(stborder);
    
    XSSFCell  rw12cell3=rw12S10.createCell(1);
    rw12cell3.setCellValue("");
    rw12cell3.setCellStyle(stborder);
    
    XSSFCell  rw12cell4=rw12S10.createCell(2);
    rw12cell4.setCellValue(FPADOLESCENT20_24N);
    rw12cell4.setCellStyle(stborder);
    
    XSSFCell  rw12cell5=rw12S10.createCell(3);
    rw12cell5.setCellValue(FPADOLESCENT20_24R);
    rw12cell5.setCellStyle(stborder);
    
    XSSFCell  rw12cell6=rw12S10.createCell(4);
    rw12cell6.setCellValue(FPADOLESCENT20_24T);
    rw12cell6.setCellStyle(stborder);
}   
  
         
    rowxx++;
     if(19==19){
     XSSFRow rw9S10=shet1.createRow(rowxx);    
    XSSFCell  rw9cell2=rw9S10.createCell(0);
    rw9cell2.setCellValue("Removals");
    rw9cell2.setCellStyle(stborder);
    
    XSSFCell  rw9cell3=rw9S10.createCell(1);
    rw9cell3.setCellValue("I.U.C.D ");
    rw9cell3.setCellStyle(stborder);
    
    XSSFCell  rw9cell4=rw9S10.createCell(2);
    rw9cell4.setCellValue(FPIUCDRemoval);
    rw9cell4.setCellStyle(stborder);
    
    XSSFCell  rw9cell5=rw9S10.createCell(3);
    rw9cell5.setCellValue("");
    rw9cell5.setCellStyle(stborder);
    
    XSSFCell  rw9cell6=rw9S10.createCell(4);
    rw9cell6.setCellValue(FPIUCDRemoval);
    rw9cell6.setCellStyle(stborder);
}
    rowxx++;
    //fp condoms male
    if(20==20){
     XSSFRow rw10S10=shet1.createRow(rowxx);    
    XSSFCell  rw10cell2=rw10S10.createCell(0);
    rw10cell2.setCellValue("");
    rw10cell2.setCellStyle(stborder);
    
    XSSFCell  rw10cell3=rw10S10.createCell(1);
    rw10cell3.setCellValue("Removals Implants");
    rw10cell3.setCellStyle(stborder);
    
    XSSFCell  rw10cell4=rw10S10.createCell(2);
    rw10cell4.setCellValue(FPIMPLANTSRemoval);
    rw10cell4.setCellStyle(stborder);
    
    XSSFCell  rw10cell5=rw10S10.createCell(3);
    rw10cell5.setCellValue("");
    rw10cell5.setCellStyle(stborder);
    
    XSSFCell  rw10cell6=rw10S10.createCell(4);
    rw10cell6.setCellValue(FPIMPLANTSRemoval);
    rw10cell6.setCellStyle(stborder);
    }
    
    shet1.addMergedRegion(new CellRangeAddress(rowxx-1,rowxx,0,0)); 
       
       
// =====================================================================================A. ANC / PMTCT================================================
   
    rowxx=5;  
    XSSFRow rw1S20=shet2.createRow(rowxx);    
    XSSFCell  rw1cells1=rw1S20.createCell(0);
    rw1cells1.setCellValue("A: ANC / PMTCT");
    rw1cells1.setCellStyle(styleHeader);
    
    XSSFCell  rw1cells2=rw1S20.createCell(1);
    rw1cells2.setCellValue("");
    rw1cells2.setCellStyle(styleHeader);    
  
    
    XSSFCell  rw1cells4=rw1S20.createCell(2);
    rw1cells4.setCellValue("TOTAL");
    rw1cells4.setCellStyle(styleHeader);
    
    rowxx++;
    
    if(6==6){
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of ANC Clients");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("New");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMCTA_1stVisit_ANC);
    rw2cells3.setCellStyle(stborder);
    }
   
    rowxx++;
    
    if(7==7){
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("Re-visit");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMCTA_ReVisit_ANC);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx-1,rowxx,0,0));      
    rowxx++;
   
    
    XSSFRow rw3S20=shet2.createRow(rowxx);    
    XSSFCell  rw3cells1=rw3S20.createCell(0);
    rw3cells1.setCellValue("No of Clients with Hb <11 g/dl ");
    rw3cells1.setCellStyle(stborder);
    
    XSSFCell  rw3cells2=rw3S20.createCell(1);
    rw3cells2.setCellValue("");
    rw3cells2.setCellStyle(stborder);
   
    XSSFCell  rw3cells4=rw3S20.createCell(2);
    rw3cells4.setCellValue(PMCTHB11);
    rw3cells4.setCellStyle(stborder);
  shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
       rowxx++;
    
      XSSFRow rw4S20=shet2.createRow(rowxx);    
    XSSFCell  rw4cells1=rw4S20.createCell(0);
    rw4cells1.setCellValue("No of clients with IPT(1st Dose)");
    rw4cells1.setCellStyle(stborder);
    
    XSSFCell  rw4cells2=rw4S20.createCell(1);
    rw4cells2.setCellValue("");
    rw4cells2.setCellStyle(stborder);
    
    XSSFCell  rw4cells4=rw4S20.createCell(2);
    rw4cells4.setCellValue(PMCTIPT1);
    rw4cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
    
    
    rowxx++;
    XSSFRow rw5S20=shet2.createRow(rowxx);    
    XSSFCell  rw5cells1=rw5S20.createCell(0);
    rw5cells1.setCellValue("No of clients with IPT(2nd Dose)");
    rw5cells1.setCellStyle(stborder);
    
    XSSFCell  rw5cells2=rw5S20.createCell(1);
    rw5cells2.setCellValue("");
    rw5cells2.setCellStyle(stborder);
    
    XSSFCell  rw5cells4=rw5S20.createCell(2);
    rw5cells4.setCellValue(PMCTIPT2);
    rw5cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
    
    
    rowxx++;   
    XSSFRow rw6S20=shet2.createRow(rowxx);    
    XSSFCell  rw6cells1=rw6S20.createCell(0);
    rw6cells1.setCellValue("No of Clients completed 4th Antenatal Visits ");
    rw6cells1.setCellStyle(stborder);
    
    XSSFCell  rw6cells2=rw6S20.createCell(1);
    rw6cells2.setCellValue("");
    rw6cells2.setCellStyle(stborder);  
    
    
    XSSFCell  rw6cells4=rw6S20.createCell(2);
    rw6cells4.setCellValue(PMCTANCClients4);
    rw6cells4.setCellStyle(stborder);
   
          
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
    rowxx++;
    if(9==9){
     XSSFRow rw7S20=shet2.createRow(rowxx);    
    XSSFCell  rw7cells1=rw7S20.createCell(0);
    rw7cells1.setCellValue("No of LLITNs distributed to under 1 year");
    rw7cells1.setCellStyle(stborder);
    
    XSSFCell  rw7cells2=rw7S20.createCell(1);
    rw7cells2.setCellValue("");
    rw7cells2.setCellStyle(stborder);
    
    
    XSSFCell  rw7cells4=rw7S20.createCell(2);
    rw7cells4.setCellValue(PMCTITN1);
    rw7cells4.setCellStyle(stborder);
}
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
  
    
    rowxx++;
    
     XSSFRow rw7S20=shet2.createRow(rowxx);    
    XSSFCell  rw7cells1=rw7S20.createCell(0);
    rw7cells1.setCellValue("No of LLITNs distributed to ANC Clients");
    rw7cells1.setCellStyle(stborder);
    
    XSSFCell  rw7cells2=rw7S20.createCell(1);
    rw7cells2.setCellValue("");
    rw7cells2.setCellStyle(stborder);
    
    
    XSSFCell  rw7cells4=rw7S20.createCell(2);
    rw7cells4.setCellValue(PMCTITN);
    rw7cells4.setCellStyle(stborder);
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));
    
    
    //=======continue with new indicators here
    
    
    rowxx++;
    
    if(6==6){
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of Clients");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("Tested for Syphilis");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTSYPHILISTES);
    rw2cells3.setCellStyle(stborder);
    }
   
    rowxx++;
    
    if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("Positive (+ve)");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTSYPHILISPOS);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx-1,rowxx,0,0));      
    rowxx++;
   
    if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of mothers counselled on infant feeding options");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTCOUNSELLEDFEED);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
    
    
    
   if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("Total women done breast examination");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTBREAST);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++; 
    
  
    if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("Total women given exercise");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTEXERCISE);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
    
    
     if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of adolescents (10-14 years) presenting with pregnancy");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTPREG10_14);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
    
    
    
     if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of adolescents (15-19 years) presenting with pregnancy");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTPREG15_19);
    rw2cells3.setCellStyle(stborder);
    }
     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
    
    
    if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of clients issued with Iron");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTIRON);
    rw2cells3.setCellStyle(stborder);
    }     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
    
    
    
     if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of clients issued with Folic");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTFOLIC);
    rw2cells3.setCellStyle(stborder);
    }     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    rowxx++;
   
    
     if(7==7)
    {
    XSSFRow rw2S20=shet2.createRow(rowxx);    
    XSSFCell  rw2cells1=rw2S20.createCell(0);
    rw2cells1.setCellValue("No. of clients issued with Combined Ferrous Folate");
    rw2cells1.setCellStyle(stborder);
    
    XSSFCell  rw2cells2=rw2S20.createCell(1);
    rw2cells2.setCellValue("");
    rw2cells2.setCellStyle(stborder);
    
    XSSFCell  rw2cells3=rw2S20.createCell(2);
    rw2cells3.setCellValue(PMTCTFOLIC);
    rw2cells3.setCellStyle(stborder);
    }     
    shet2.addMergedRegion(new CellRangeAddress(rowxx,rowxx,0,1));      
    
    
    //===============================================================================================B. MATERNITY AND DELIVERY============================
    
    
  //    ---------------------------------------------
  rowxx=5;
    
    XSSFRow rw3S30=shet3.createRow(rowxx);    
    XSSFCell  rw1cellS3=rw3S30.createCell(0);
    rw1cellS3.setCellValue("E: MATERNITY SAFE DELIVERIES");
    rw1cellS3.setCellStyle(styleHeader);
    
    if(5==5){
      XSSFCell  rw2cellS3=rw3S30.createCell(1);
    rw2cellS3.setCellValue("");
    rw2cellS3.setCellStyle(styleHeader);
    }
    
    XSSFCell  rw2cellS3=rw3S30.createCell(2);
    rw2cellS3.setCellValue("Total");
    rw2cellS3.setCellStyle(styleHeader);
    
    rowxx++;
    
    //    ---------------------------------------------
     XSSFRow rw4S30=shet3.createRow(rowxx);  
    XSSFCell  rw3cellS3=rw4S30.createCell(0);
    rw3cellS3.setCellValue("Normal Deliveries");
    rw3cellS3.setCellStyle(stborder);
    
     if(5==5){
     XSSFCell  rw4cellS3=rw4S30.createCell(1);
    rw4cellS3.setCellValue("");
    rw4cellS3.setCellStyle(stborder);
    }
    
    XSSFCell  rw4cellS3=rw4S30.createCell(2);
    rw4cellS3.setCellValue(MATNormalDelivery);
    rw4cellS3.setCellStyle(stborder);
    //_______________________________________
    
     rowxx++;
     
     XSSFRow rw5S30=shet3.createRow(rowxx);  
    XSSFCell  rw5cellS3=rw5S30.createCell(0);
    rw5cellS3.setCellValue("Caesarean Section");
    rw5cellS3.setCellStyle(stborder);
    
      if(5==5){
    XSSFCell  rw6cellS3=rw5S30.createCell(1);
    rw6cellS3.setCellValue("");
    rw6cellS3.setCellStyle(stborder);
    }
    
    XSSFCell  rw6cellS3=rw5S30.createCell(2);
    rw6cellS3.setCellValue(MATCSection);
    rw6cellS3.setCellStyle(stborder);
//    ---------------------------------------------
    
     rowxx++;
    XSSFRow rw6S30=shet3.createRow(rowxx);  
    XSSFCell  rw7cellS3=rw6S30.createCell(0);
    rw7cellS3.setCellValue("Breech Delivery");
    rw7cellS3.setCellStyle(stborder);
    
      if(5==5){
   XSSFCell  rw8cellS3=rw6S30.createCell(1);
    rw8cellS3.setCellValue("");
    rw8cellS3.setCellStyle(stborder);
    }
    
    XSSFCell  rw8cellS3=rw6S30.createCell(2);
    rw8cellS3.setCellValue(MATBreech);
    rw8cellS3.setCellStyle(stborder);
     rowxx++;
    
  //    ---------------------------------------------
    XSSFRow rw7S30=shet3.createRow(rowxx);  
    XSSFCell  rw9cellS3=rw7S30.createCell(0);
    rw9cellS3.setCellValue("Assisted Vaginal Delivery");
    rw9cellS3.setCellStyle(stborder);
    
      if(5==5){
  XSSFCell  rw10cellS3=rw7S30.createCell(1);
    rw10cellS3.setCellValue("");
    rw10cellS3.setCellStyle(stborder);
    }
    
    XSSFCell  rw10cellS3=rw7S30.createCell(2);
    rw10cellS3.setCellValue(MATAssistedVag);
    rw10cellS3.setCellStyle(stborder);
  //    ---------------------------------------------
  
     rowxx++;
    
    XSSFRow rw8S30=shet3.createRow(rowxx);  
    XSSFCell  rw11cellS3=rw8S30.createCell(0);
    rw11cellS3.setCellValue("TOTAL DELIVERIES");
    rw11cellS3.setCellStyle(stylex);
    
    if(5==5){
    XSSFCell  rw12cellS3=rw8S30.createCell(1);
    rw12cellS3.setCellValue("");
    rw12cellS3.setCellStyle(stylex);
           }
    
    XSSFCell  rw12cellS3=rw8S30.createCell(2);
    rw12cellS3.setCellValue(MATDeliveryT);
    rw12cellS3.setCellStyle(stylex);
    
     rowxx++;
  //    ---------------------------------------------
    XSSFRow rw9S30=shet3.createRow(rowxx);  
    XSSFCell  rw13cellS3=rw9S30.createCell(0);
    rw13cellS3.setCellValue("Live Births");
    rw13cellS3.setCellStyle(stborder);
    
    if(5==5){
    XSSFCell  rw14cellS3=rw9S30.createCell(1);
    rw14cellS3.setCellValue("");
    rw14cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw14cellS3=rw9S30.createCell(2);
    rw14cellS3.setCellValue(MATLiveBirth);
    rw14cellS3.setCellStyle(stborder);
     rowxx++;
  //    ---------------------------------------------
  
    XSSFRow rw10S30=shet3.createRow(rowxx);  
    XSSFCell  rw15cellS3=rw10S30.createCell(0);
    rw15cellS3.setCellValue("Macerated Still Births");
    rw15cellS3.setCellStyle(stborder);
    
     if(5==5){
    XSSFCell  rw16cellS3=rw10S30.createCell(1);
    rw16cellS3.setCellValue("");
    rw16cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw16cellS3=rw10S30.createCell(2);
    rw16cellS3.setCellValue(MATMeceratedStillBirth);
    rw16cellS3.setCellStyle(stborder);
    
     rowxx++;
  //    ---------------------------------------------
     if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("Fresh Still Births");
    rw17cellS3.setCellStyle(stborder);
    
     if(5==5){
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATFreshStillBirth);
    rw18cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
  
    rowxx++;
    
    
     if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("Birth with Deformities");
    rw17cellS3.setCellStyle(stborder);
    
    if(5==5){
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATDeformities);
    rw18cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
  
    rowxx++;
    
      if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("No with Low APGAR Score");
    rw17cellS3.setCellStyle(stborder);
    
     if(5==5){
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATLowAPGAR);
    rw18cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
  
    rowxx++;
    
    XSSFRow rw12S30=shet3.createRow(rowxx);  
    XSSFCell  rw19cellS3=rw12S30.createCell(0);
    rw19cellS3.setCellValue("No. of Low birth weight Babies (below 2500 grams)");
    rw19cellS3.setCellStyle(stborder);
   
    if(5==5){
   XSSFCell  rw20cellS3=rw12S30.createCell(1);
    rw20cellS3.setCellValue("");
    rw20cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw20cellS3=rw12S30.createCell(2);
    rw20cellS3.setCellValue(MATWeight2500);
    rw20cellS3.setCellStyle(stborder);
    rowxx++;
    
      if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("No. of babies given tetracycline at birth");
    rw17cellS3.setCellStyle(stborder);
    
      if(5==5){
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATTetracycline);
    rw18cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
  
    rowxx++;
  //    ---------------------------------------------
    XSSFRow rw13S30=shet3.createRow(rowxx);  
    XSSFCell  rw21cellS3=rw13S30.createCell(0);
    rw21cellS3.setCellValue("Pre-Term Babies");
    rw21cellS3.setCellStyle(stborder);
    
    if(5==5){
   XSSFCell  rw22cellS3=rw13S30.createCell(1);
    rw22cellS3.setCellValue("");
    rw22cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw22cellS3=rw13S30.createCell(2);
    rw22cellS3.setCellValue(MATPreTerm);
    rw22cellS3.setCellStyle(stborder);
  
      rowxx++;
  //    ---------------------------------------------
    XSSFRow rw14S30=shet3.createRow(rowxx);  
    XSSFCell  rw23cellS3=rw14S30.createCell(0);
    rw23cellS3.setCellValue("No. of babies discharged alive");
    rw23cellS3.setCellStyle(stborder);
    
     if(5==5){
   XSSFCell  rw24cellS3=rw14S30.createCell(1);
    rw24cellS3.setCellValue("");
    rw24cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw24cellS3=rw14S30.createCell(2);
    rw24cellS3.setCellValue(MATDischargealive);
    rw24cellS3.setCellStyle(stborder);
    rowxx++;
    
       if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("No. of Infants intiatied on breastfeeding within 1 hour after birth");
    rw17cellS3.setCellStyle(stborder);
    
      if(5==5)
      {
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATbreastfeeding1);
    rw18cellS3.setCellStyle(stborder);
     }
       
          rowxx++;
  //    ---------------------------------------------
  
     if(3==3){
    XSSFRow rw11S30=shet3.createRow(rowxx);  
    XSSFCell  rw17cellS3=rw11S30.createCell(0);
    rw17cellS3.setCellValue("Total Deliveries from HIV +ve women");
    rw17cellS3.setCellStyle(stborder);
    
    if(5==5)
      {
   XSSFCell  rw18cellS3=rw11S30.createCell(1);
    rw18cellS3.setCellValue("");
    rw18cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw18cellS3=rw11S30.createCell(2);
    rw18cellS3.setCellValue(MATDeliveriesPos);
    rw18cellS3.setCellStyle(stborder);
     }
       
          rowxx++;
    

  //    ---------------------------------------------
          if(4==4){
    XSSFRow rw17S30=shet3.createRow(rowxx);  
    XSSFCell  rw29cellS3=rw17S30.createCell(0);
    rw29cellS3.setCellValue("Neonatal Deaths");
    rw29cellS3.setCellStyle(stborder);
    
      if(5==5)
      {
   XSSFCell  rw30cellS3=rw17S30.createCell(1);
    rw30cellS3.setCellValue("");
    rw30cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw30cellS3=rw17S30.createCell(2);
    rw30cellS3.setCellValue(MATNeoNatalD);
    rw30cellS3.setCellStyle(stborder);
          }
    
    rowxx++;
    
     if(4==4){
    XSSFRow rw17S30=shet3.createRow(rowxx);  
    XSSFCell  rw29cellS3=rw17S30.createCell(0);
    rw29cellS3.setCellValue("No. of adolescents (10-19YRS) Maternal Deaths");
    rw29cellS3.setCellStyle(stborder);
    
       if(5==5)
      {
   XSSFCell  rw30cellS3=rw17S30.createCell(1);
    rw30cellS3.setCellValue("");
    rw30cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw30cellS3=rw17S30.createCell(2);
    rw30cellS3.setCellValue(MATMaternalD10_19);
    rw30cellS3.setCellStyle(stborder);
    }
    rowxx++;
    
    
    
  //    ---------------------------------------------
    
    XSSFRow rw18S30=shet3.createRow(rowxx);  
    XSSFCell  rw31cellS3=rw18S30.createCell(0);
    rw31cellS3.setCellValue("Maternal Deaths");
    rw31cellS3.setCellStyle(stborder);
    
      if(5==5)
      {
   XSSFCell  rw32cellS3=rw18S30.createCell(1);
    rw32cellS3.setCellValue("");
    rw32cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw32cellS3=rw18S30.createCell(2);
    rw32cellS3.setCellValue(MATMaternalD);
    rw32cellS3.setCellStyle(stborder);
    rowxx++;
  
    
     if(4==4){
    XSSFRow rw17S30=shet3.createRow(rowxx);  
    XSSFCell  rw29cellS3=rw17S30.createCell(0);
    rw29cellS3.setCellValue("Maternal deaths audited");
    rw29cellS3.setCellStyle(stborder);
    
      if(5==5)
      {
   XSSFCell  rw30cellS3=rw17S30.createCell(1);
    rw30cellS3.setCellValue("");
    rw30cellS3.setCellStyle(stborder);
           }
    
    XSSFCell  rw30cellS3=rw17S30.createCell(2);
    rw30cellS3.setCellValue(MATMaternalDAudited);
    rw30cellS3.setCellStyle(stborder);
          }
    
    rowxx++; 
    
    
    
  //    ---------------------------------------------
      XSSFRow rw19S30=shet3.createRow(rowxx);  
    XSSFCell  rw33cellS3=rw19S30.createCell(0);
    rw33cellS3.setCellValue("Maternal Complications");
    rw33cellS3.setCellStyle(stylex);
    
    XSSFCell  rw34cellS3=rw19S30.createCell(1);
    rw34cellS3.setCellValue("Alive");
    rw34cellS3.setCellStyle(stylex);
    
    XSSFCell  rw35cellS3=rw19S30.createCell(2);
    rw35cellS3.setCellValue("Deaths");
    rw35cellS3.setCellStyle(stylex);
    
     rowxx++; 
    
  //    ---------------------------------------------
      XSSFRow rw20S30=shet3.createRow(rowxx);  
    XSSFCell  rw305cellS3=rw20S30.createCell(0);
    rw305cellS3.setCellValue("A.P.H. (Ante Partum Heamorrhage)");
    rw305cellS3.setCellStyle(stborder);
    
    XSSFCell  rw36cellS3=rw20S30.createCell(1);
    rw36cellS3.setCellValue(MATAPHAlive);
    rw36cellS3.setCellStyle(stborder);
    
    XSSFCell  rw37cellS3=rw20S30.createCell(2);
    rw37cellS3.setCellValue(MATAPHDead);
    rw37cellS3.setCellStyle(stborder);
    
     rowxx++; 
  //    ---------------------------------------------
      XSSFRow rw21S30=shet3.createRow(rowxx);  
    XSSFCell  rw38cellS3=rw21S30.createCell(0);
    rw38cellS3.setCellValue("P.P.H. (Post Partum Heamorrhage)");
    rw38cellS3.setCellStyle(stborder);
    
    XSSFCell  rw39cellS3=rw21S30.createCell(1);
    rw39cellS3.setCellValue(MATPPHAlive);
    rw39cellS3.setCellStyle(stborder);
    
    XSSFCell  rw40cellS3=rw21S30.createCell(2);
    rw40cellS3.setCellValue(MATPPHDead);
    rw40cellS3.setCellStyle(stborder);
     rowxx++; 
    
  //    ---------------------------------------------
      XSSFRow rw22S30=shet3.createRow(rowxx);  
    XSSFCell  rw41cellS3=rw22S30.createCell(0);
    rw41cellS3.setCellValue("Eclampsia");
    rw41cellS3.setCellStyle(stborder);
    
    XSSFCell  rw42cellS3=rw22S30.createCell(1);
    rw42cellS3.setCellValue(MATEclampAlive);
    rw42cellS3.setCellStyle(stborder);
    
    XSSFCell  rw43cellS3=rw22S30.createCell(2);
    rw43cellS3.setCellValue(MATEclampDead);
    rw43cellS3.setCellStyle(stborder);
    
     rowxx++; 
    
  //    ---------------------------------------------
      XSSFRow rw23S30=shet3.createRow(rowxx);  
    XSSFCell  rw44cellS3=rw23S30.createCell(0);
    rw44cellS3.setCellValue("Ruptured Uterus");
    rw44cellS3.setCellStyle(stborder);
    
    XSSFCell  rw45cellS3=rw23S30.createCell(1);
    rw45cellS3.setCellValue(MATRupUtAlive);
    rw45cellS3.setCellStyle(stborder);
    
    XSSFCell  rw46cellS3=rw23S30.createCell(2);
    rw46cellS3.setCellValue(MATRupUtDead);
    rw46cellS3.setCellStyle(stborder);
     rowxx++; 
    
  //    ---------------------------------------------
      XSSFRow rw24S30=shet3.createRow(rowxx);  
    XSSFCell  rw47cellS3=rw24S30.createCell(0);
    rw47cellS3.setCellValue("Obstructed Labor");
    rw47cellS3.setCellStyle(stborder);
    
    XSSFCell  rw48cellS3=rw24S30.createCell(1);
    rw48cellS3.setCellValue(MATObstrLaborAlive);
    rw48cellS3.setCellStyle(stborder);
    
    XSSFCell  rw49cellS3=rw24S30.createCell(2);
    rw49cellS3.setCellValue(MATObstrLaborDead);
    rw49cellS3.setCellStyle(stborder);
    
     rowxx++; 
  //    ---------------------------------------------
    
     if(6==6)
     {
     XSSFRow rw25S30=shet3.createRow(rowxx);  
    XSSFCell  rw50cellS3=rw25S30.createCell(0);
    rw50cellS3.setCellValue("No of Referrals");
    rw50cellS3.setCellStyle(stborder);
    
    XSSFCell  rw51cellS3=rw25S30.createCell(1);
    rw51cellS3.setCellValue("From Other Health Facility");
    rw51cellS3.setCellStyle(stborder);
    
    XSSFCell  rw52cellS3=rw25S30.createCell(2);
    rw52cellS3.setCellValue(MATREFFromOtherFacility);
    rw52cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
   rowxx++; 
   
   
    if(6==6)
     {
     XSSFRow rw25S30=shet3.createRow(rowxx);  
    XSSFCell  rw50cellS3=rw25S30.createCell(0);
    rw50cellS3.setCellValue("");
    rw50cellS3.setCellStyle(stborder);
    
    XSSFCell  rw51cellS3=rw25S30.createCell(1);
    rw51cellS3.setCellValue("From Community Unit");
    rw51cellS3.setCellStyle(stborder);
    
    XSSFCell  rw52cellS3=rw25S30.createCell(2);
    rw52cellS3.setCellValue(MATREFFromCU);
    rw52cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
   rowxx++; 
   
  
    if(6==6)
     {
     XSSFRow rw25S30=shet3.createRow(rowxx);  
    XSSFCell  rw50cellS3=rw25S30.createCell(0);
    rw50cellS3.setCellValue("");
    rw50cellS3.setCellStyle(stborder);
    
    XSSFCell  rw51cellS3=rw25S30.createCell(1);
    rw51cellS3.setCellValue("To Other Health Facility");
    rw51cellS3.setCellStyle(stborder);
    
    XSSFCell  rw52cellS3=rw25S30.createCell(2);
    rw52cellS3.setCellValue(MATREFToOtherFacility);
    rw52cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
   rowxx++;
   
   if(6==6)
     {
     XSSFRow rw25S30=shet3.createRow(rowxx);  
    XSSFCell  rw50cellS3=rw25S30.createCell(0);
    rw50cellS3.setCellValue("");
    rw50cellS3.setCellStyle(stborder);
    
    XSSFCell  rw51cellS3=rw25S30.createCell(1);
    rw51cellS3.setCellValue("To Community Unit");
    rw51cellS3.setCellStyle(stborder);
    
    XSSFCell  rw52cellS3=rw25S30.createCell(2);
    rw52cellS3.setCellValue(MATREFToCU);
    rw52cellS3.setCellStyle(stborder);
     }
  //    ---------------------------------------------
   shet3.addMergedRegion(new CellRangeAddress(rowxx-3,rowxx,0,0));    
   
  
//  if(conn.rs.getString(66)!=null){VCTClient_Couns_CM=conn.rs.getString(66);}else{VCTClient_Couns_CM="";}
//  if(conn.rs.getString(67)!=null){VCTClient_Couns_CF=conn.rs.getString(67);}else{VCTClient_Couns_CF="";}
//  if(conn.rs.getString(68)!=null){VCTClient_Couns_AM=conn.rs.getString(68);}else{VCTClient_Couns_AM="";}
//  if(conn.rs.getString(69)!=null){VCTClient_Couns_AF=conn.rs.getString(69);}else{VCTClient_Couns_AF="";}
//  if(conn.rs.getString(70)!=null){VCTClient_Couns_TOT=conn.rs.getString(70);}else{VCTClient_Couns_TOT="";}
//  if(conn.rs.getString(71)!=null){VCTClient_Tested_CM=conn.rs.getString(71);}else{VCTClient_Tested_CM="";}
//  if(conn.rs.getString(72)!=null){VCTClient_Tested_CF=conn.rs.getString(72);}else{VCTClient_Tested_CF="";}
//  if(conn.rs.getString(73)!=null){VCTClient_Tested_AM=conn.rs.getString(73);}else{VCTClient_Tested_AM="";}
//  if(conn.rs.getString(74)!=null){VCTClient_Tested_AF=conn.rs.getString(74);}else{VCTClient_Tested_AF="";}
//  if(conn.rs.getString(75)!=null){VCTClient_Tested_TOT=conn.rs.getString(75);}else{VCTClient_Tested_TOT="";}
//  if(conn.rs.getString(76)!=null){VCTClient_HIV_CM=conn.rs.getString(76);}else{VCTClient_HIV_CM="";}
//  if(conn.rs.getString(77)!=null){VCTClient_HIV_CF=conn.rs.getString(77);}else{VCTClient_HIV_CF="";}
//  if(conn.rs.getString(78)!=null){VCTClient_HIV_AM=conn.rs.getString(78);}else{VCTClient_HIV_AM="";}
//  if(conn.rs.getString(79)!=null){VCTClient_HIV_AF=conn.rs.getString(79);}else{VCTClient_HIV_AF="";}
//  if(conn.rs.getString(80)!=null){VCTClient_HIV_TOT=conn.rs.getString(80);}else{VCTClient_HIV_TOT="";}
//  if(conn.rs.getString(81)!=null){VCTPartner_Couns_TOT=conn.rs.getString(81);}else{VCTPartner_Couns_TOT="";}
//  if(conn.rs.getString(82)!=null){VCTPartner_Tested_TOT=conn.rs.getString(82);}else{VCTPartner_Tested_TOT="";}
//  if(conn.rs.getString(83)!=null){VCTPartner_HIV_TOT=conn.rs.getString(83);}else{VCTPartner_HIV_TOT="";}
//  if(conn.rs.getString(84)!=null){VCTPartner_Disc_TOT=conn.rs.getString(84);}else{VCTPartner_Disc_TOT="";}
    
  System.out.println(conn.rs.getString(1)+" "+conn.rs.getString(2))  ;
 
}


  if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
//     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
//     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();} 
     
     IdGenerator IG = new IdGenerator();
     String createdOn=IG.CreatedOn();
     
      // write it as an excel attachment
ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
wb.write(outByteStream);
byte [] outArray = outByteStream.toByteArray();
response.setContentType("application/ms-excel");
response.setContentLength(outArray.length);
response.setHeader("Expires:", "0"); // eliminates browser caching
response.setHeader("Content-Disposition", "attachment; filename=NEW711_STATIC_REPORT_GEN_"+createdOn.trim()+".xlsx");
OutputStream outStream = response.getOutputStream();
outStream.write(outArray);
outStream.flush();
outStream.close();   

          



        }
     
        finally {            
//            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
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
            Logger.getLogger(moh711_excelReport.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
            Logger.getLogger(moh711_excelReport.class.getName()).log(Level.SEVERE, null, ex);
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

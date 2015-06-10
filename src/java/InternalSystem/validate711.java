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
 * @author Geofrey Nyabuto
 */
public class validate711 extends HttpServlet {
HttpSession session=null;
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
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
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
    
    
      // FAMILY PLANNING            
 FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR="0";
FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="0";
FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
FPIMPLANTSRemoval="0";
//PMCT VARIABLES
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="0";

 MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=MATDischargealive=MATReferral=MATNeoNatalD=
MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="0";
 
 VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="0";
 
 DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="0";

           
String tableid=year+"_"+month+"_"+facil;
//String checker="select * from moh711 WHERE id=?" ;
//          conn.pst=conn.conn.prepareStatement(checker);
//          conn.pst.setString(1, tableid);
//          conn.rs=conn.pst.executeQuery();
//          
//          if(conn.rs.next()==true){
  if(request.getParameter("FPMicrolutN")!=null && !request.getParameter("FPMicrolutN").equals("")){FPMicrolutN=request.getParameter("FPMicrolutN");}
  if(request.getParameter("FPMicrolutR")!=null && !request.getParameter("FPMicrolutR").equals("")){FPMicrolutR=request.getParameter("FPMicrolutR");}
  if(request.getParameter("FPMicrolutT")!=null && !request.getParameter("FPMicrolutT").equals("")){FPMicrolutT=request.getParameter("FPMicrolutT");}
  if(request.getParameter("FPMicrogynonN")!=null && !request.getParameter("FPMicrogynonN").equals("")){FPMicrogynonN=request.getParameter("FPMicrogynonN");}
  if(request.getParameter("FPMicrogynonR")!=null && !request.getParameter("FPMicrogynonR").equals("")){FPMicrogynonR=request.getParameter("FPMicrogynonR");}
  if(request.getParameter("FPMicrogynonT")!=null && !request.getParameter("FPMicrogynonT").equals("")){FPMicrogynonT=request.getParameter("FPMicrogynonT");}
  if(request.getParameter("FPINJECTIONSN")!=null && !request.getParameter("FPINJECTIONSN").equals("")){FPINJECTIONSN=request.getParameter("FPINJECTIONSN");}
  if(request.getParameter("FPINJECTIONST")!=null &&  !request.getParameter("FPINJECTIONST").equals("")){FPINJECTIONST=request.getParameter("FPINJECTIONST");}
  if(request.getParameter("FPINJECTIONSR")!=null &&  !request.getParameter("FPINJECTIONSR").equals("")){FPINJECTIONSR=request.getParameter("FPINJECTIONSR");}
  if(request.getParameter("FPIUCDN")!=null &&  !request.getParameter("FPIUCDN").equals("")){FPIUCDN=request.getParameter("FPIUCDN");}
  if(request.getParameter("FPIUCDR")!=null &&  !request.getParameter("FPIUCDR").equals("")){FPIUCDR=request.getParameter("FPIUCDR");}
  if(request.getParameter("FPIUCDT")!=null && !request.getParameter("FPIUCDT").equals("")){FPIUCDT=request.getParameter("FPIUCDT");}
  if(request.getParameter("FPIMPLANTSN")!=null && !request.getParameter("FPIMPLANTSN").equals("")){FPIMPLANTSN=request.getParameter("FPIMPLANTSN");}
  if(request.getParameter("FPIMPLANTSR")!=null && !request.getParameter("FPIMPLANTSR").equals("")){FPIMPLANTSR=request.getParameter("FPIMPLANTSR");}
  if(request.getParameter("FPIMPLANTST")!=null && !request.getParameter("FPIMPLANTST").equals("") ){FPIMPLANTST=request.getParameter("FPIMPLANTST");}
  if(request.getParameter("FPBTLN")!=null && !request.getParameter("FPBTLN").equals("")){FPBTLN=request.getParameter("FPBTLN");}
  if(request.getParameter("FPBTLR")!=null  && !request.getParameter("FPBTLR").equals("")){FPBTLR=request.getParameter("FPBTLR");}
  if(request.getParameter("FPBTLT")!=null && !request.getParameter("FPBTLT").equals("")){FPBTLT=request.getParameter("FPBTLT");}
  if(request.getParameter("FPVasectomyN")!=null && !request.getParameter("FPVasectomyN").equals("")){FPVasectomyN=request.getParameter("FPVasectomyN");}
  if(request.getParameter("FPVasectomyR")!=null && !request.getParameter("FPVasectomyR").equals("")){FPVasectomyR=request.getParameter("FPVasectomyR");}
  if(request.getParameter("FPVasectomyT")!=null && !request.getParameter("FPVasectomyT").equals("")){FPVasectomyT=request.getParameter("FPVasectomyT");}
  if(request.getParameter("FPCONDOMSN")!=null && !request.getParameter("FPCONDOMSN").equals("")){FPCONDOMSN=request.getParameter("FPCONDOMSN");}
  if(request.getParameter("FPCONDOMSR")!=null && !request.getParameter("FPCONDOMSR").equals("")){FPCONDOMSR=request.getParameter("FPCONDOMSR");}
  if(request.getParameter("FPCONDOMST")!=null && !request.getParameter("FPCONDOMST").equals("")){FPCONDOMST=request.getParameter("FPCONDOMST");}
         
  if(request.getParameter("FPOTHERN")!=null && !request.getParameter("FPOTHERN").equals("")){FPOTHERN=request.getParameter("FPOTHERN");}
  if(request.getParameter("FPOTHERR")!=null && !request.getParameter("FPOTHERR").equals("")){FPOTHERR=request.getParameter("FPOTHERR");}
  if(request.getParameter("FPOTHERT")!=null && !request.getParameter("FPOTHERT").equals("")){FPOTHERT=request.getParameter("FPOTHERT");}
         
  if(request.getParameter("FPCLIENTSN")!=null  && !request.getParameter("FPCLIENTSN").equals("")){FPCLIENTSN=request.getParameter("FPCLIENTSN");}
  if(request.getParameter("FPCLIENTSR")!=null  && !request.getParameter("FPCLIENTSR").equals("")){FPCLIENTSR=request.getParameter("FPCLIENTSR");}
  if(request.getParameter("FPCLIENTST")!=null  && !request.getParameter("FPCLIENTST").equals("")){FPCLIENTST=request.getParameter("FPCLIENTST");}
   if(request.getParameter("FPIUCDRemoval")!=null  && !request.getParameter("FPIUCDRemoval").equals("")){FPIUCDRemoval=request.getParameter("FPIUCDRemoval");}
  if(request.getParameter("FPIMPLANTSRemoval")!=null  && !request.getParameter("FPIMPLANTSRemoval").equals("")){FPIMPLANTSRemoval=request.getParameter("FPIMPLANTSRemoval");}
          
          
          
          // mch 
  // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";

   if(request.getParameter("PMCTA_1stVisit_ANC")!=null  && !request.getParameter("PMCTA_1stVisit_ANC").equals("")){PMCTA_1stVisit_ANC=request.getParameter("PMCTA_1stVisit_ANC");}
  if(request.getParameter("PMCTA_ReVisit_ANC")!=null  && !request.getParameter("PMCTA_ReVisit_ANC").equals("")  ){PMCTA_ReVisit_ANC=request.getParameter("PMCTA_ReVisit_ANC");}
  if(request.getParameter("PMCTANCClientsT")!=null  && !request.getParameter("PMCTANCClientsT").equals("")){PMCTANCClientsT=request.getParameter("PMCTANCClientsT");}
  if(request.getParameter("PMCTHB7")!=null  && !request.getParameter("PMCTHB7").equals("")){PMCTHB7=request.getParameter("PMCTHB7");}
  if(request.getParameter("PMCTIPT1")!=null  && !request.getParameter("PMCTIPT1").equals("")){PMCTIPT1=request.getParameter("PMCTIPT1");}
  if(request.getParameter("PMCTIPT2")!=null  && !request.getParameter("PMCTIPT2").equals("")){PMCTIPT2=request.getParameter("PMCTIPT2");}
  if(request.getParameter("PMCTANCClients4")!=null  && !request.getParameter("PMCTANCClients4").equals("")){PMCTANCClients4=request.getParameter("PMCTANCClients4");}
  if(request.getParameter("PMCTITN")!=null  && !request.getParameter("PMCTITN").equals("")){PMCTITN=request.getParameter("PMCTITN");}
  
  // maternity MATNormalDelivery=MATCSection=""MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=
  //MATDischargealive=MATReferral=MATNeoNatalD=
//MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive
  //=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
 
  if(request.getParameter("MATNormalDelivery")!=null &&  !request.getParameter("MATNormalDelivery").equals("")){MATNormalDelivery=request.getParameter("MATNormalDelivery");}
  if(request.getParameter("MATCSection")!=null && !request.getParameter("MATCSection").equals("")){MATCSection=request.getParameter("MATCSection");}
  if(request.getParameter("MATBreech")!=null && !request.getParameter("MATBreech").equals("")){MATBreech=request.getParameter("MATBreech");}
  if(request.getParameter("MATAssistedVag")!=null && !request.getParameter("MATAssistedVag").equals("")){MATAssistedVag=request.getParameter("MATAssistedVag");}
  if(request.getParameter("MATDeliveryT")!=null && !request.getParameter("MATDeliveryT").equals("")){MATDeliveryT=request.getParameter("MATDeliveryT");}
  if(request.getParameter("MATLiveBirth")!=null && !request.getParameter("MATLiveBirth").equals("")){MATLiveBirth=request.getParameter("MATLiveBirth");}
  if(request.getParameter("MATStillBirth")!=null && !request.getParameter("MATStillBirth").equals("")){MATStillBirth=request.getParameter("MATStillBirth");}
  if(request.getParameter("FPBTLT")!=null && !request.getParameter("FPBTLT").equals("")){FPBTLT=request.getParameter("FPBTLT");}
  if(request.getParameter("MATWeight2500")!=null && !request.getParameter("MATWeight2500").equals("")){MATWeight2500=request.getParameter("MATWeight2500");}
  if(request.getParameter("MATPreTerm")!=null && !request.getParameter("MATPreTerm").equals("")){MATPreTerm=request.getParameter("MATPreTerm");}
  if(request.getParameter("MATDischargealive")!=null && !request.getParameter("MATDischargealive").equals("")){MATDischargealive=request.getParameter("MATDischargealive");}
  if(request.getParameter("MATReferral")!=null && !request.getParameter("MATReferral").equals("")){MATReferral=request.getParameter("MATReferral");}
  if(request.getParameter("MATNeoNatalD")!=null && !request.getParameter("MATNeoNatalD").equals("")){MATNeoNatalD=request.getParameter("MATNeoNatalD");}
  if(request.getParameter("MATMaternalD")!=null && !request.getParameter("MATMaternalD").equals("")){MATMaternalD=request.getParameter("MATMaternalD");}
  if(request.getParameter("MATAPHAlive")!=null && !request.getParameter("MATAPHAlive").equals("")){MATAPHAlive=request.getParameter("MATAPHAlive");}
  if(request.getParameter("MATAPHDead")!=null && !request.getParameter("MATAPHDead").equals("")){MATAPHDead=request.getParameter("MATAPHDead");}
  if(request.getParameter("MATPPHAlive")!=null && !request.getParameter("MATPPHAlive").equals("")){MATPPHAlive=request.getParameter("MATPPHAlive");}
  if(request.getParameter("MATPPHDead")!=null && !request.getParameter("MATPPHDead").equals("")){MATPPHDead=request.getParameter("MATPPHDead");}
  if(request.getParameter("MATEclampAlive")!=null && !request.getParameter("MATEclampAlive").equals("")){MATEclampAlive=request.getParameter("MATEclampAlive");}
  if(request.getParameter("MATEclampDead")!=null && !request.getParameter("MATEclampDead").equals("")){MATEclampDead=request.getParameter("MATEclampDead");}
  if(request.getParameter("MATRupUtAlive")!=null && !request.getParameter("MATRupUtAlive").equals("")){MATRupUtAlive=request.getParameter("MATRupUtAlive");}
  if(request.getParameter("MATRupUtDead")!=null && !request.getParameter("MATRupUtDead").equals("")){MATRupUtDead=request.getParameter("MATRupUtDead");}
  if(request.getParameter("MATObstrLaborAlive")!=null && !request.getParameter("MATObstrLaborAlive").equals("")){MATObstrLaborAlive=request.getParameter("MATObstrLaborAlive");}
  if(request.getParameter("MATObstrLaborDead")!=null && !request.getParameter("MATObstrLaborDead").equals("")){MATObstrLaborDead=request.getParameter("MATObstrLaborDead");}
  if(request.getParameter("MATSepsisAlive")!=null && !request.getParameter("MATSepsisAlive").equals("")){MATSepsisAlive=request.getParameter("MATSepsisAlive");}
  if(request.getParameter("MATSepsisDead")!=null && !request.getParameter("MATSepsisDead").equals("")){MATSepsisDead=request.getParameter("MATSepsisDead");}
//      VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF
  //=VCTClient_Tested_AM=VCTClient_Tested_AF
//=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=
  //VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
//     
          
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
  
  
  
//  DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=
  //DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot
  //=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

  
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
  
  String runvalidate="update moh711 set FPMicrolutN='"+FPMicrolutN+"',"
          + "FPMicrolutR='"+FPMicrolutR+"',"
          + "FPMicrolutT='"+FPMicrolutT+"',"
          + "FPMicrogynonN='"+FPMicrogynonN+"',"
          + "FPMicrogynonR='"+FPMicrogynonR+"',"
          + "FPMicrogynonT='"+FPMicrogynonT+"',"
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
          + "FPVasectomyN='"+FPVasectomyN +"',"
          + "FPVasectomyR='"+FPVasectomyR+"',"
          + "FPVasectomyT='"+FPVasectomyT+"',"
          + "FPCONDOMSN='"+FPCONDOMSN+"',"
          + "FPCONDOMSR='"+FPCONDOMSR+"',"
          + "FPCONDOMST='"+FPCONDOMST+"',"
          + "FPOTHERN='"+FPOTHERN+"',"
          + "FPOTHERR='"+FPOTHERR+"',"
          + "FPOTHERT='"+FPOTHERT+"',"
          + "FPCLIENTSN='"+FPCLIENTSN+"',"
          + "FPCLIENTSR='"+FPCLIENTSR+"',"
          + "FPCLIENTST='"+FPCLIENTST+"',"
          + "FPIUCDRemoval='"+FPIUCDRemoval+"',"
          + "FPIMPLANTSRemoval='"+FPIMPLANTSRemoval+"',"
          + "PMCTA_1stVisit_ANC='"+PMCTA_1stVisit_ANC+"',"
          + "PMCTA_ReVisit_ANC='"+PMCTA_ReVisit_ANC+"',"
          + "PMCTANCClientsT='"+PMCTANCClientsT+"',"
          + "PMCTHB7='"+PMCTHB7+"',"
          + "PMCTIPT1='"+PMCTIPT1+"',"
          + "PMCTIPT2='"+PMCTIPT2+"',"
          + "PMCTANCClients4='"+PMCTANCClients4+"',"
          + "PMCTITN='"+PMCTITN+"',"
          + "MATNormalDelivery='"+MATNormalDelivery+"',"
          + "MATCSection='"+MATCSection+"',"
          + " MATBreech='"+MATBreech+"',"
          + "MATAssistedVag='"+MATAssistedVag+"',"
          + "MATDeliveryT='"+MATDeliveryT+"',"
          + "MATLiveBirth='"+MATLiveBirth+"',"
          + "MATStillBirth='"+MATStillBirth+"',"
          + "MATWeight2500='"+MATWeight2500+"',"
          + "MATPreTerm='"+MATPreTerm+"',"
          + "MATDischargealive='"+MATDischargealive+"',"
          + "MATReferral='"+MATReferral+"',"
          + "MATNeoNatalD='"+MATNeoNatalD+"',"
          + "MATMaternalD='"+MATMaternalD+"',"
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
          + "VCTClient_Couns_CM='"+VCTClient_Couns_CM+"',"
          + "VCTClient_Couns_CF='"+VCTClient_Couns_CF+"',"
          + "VCTClient_Couns_AM='"+VCTClient_Couns_AM+"',"
          + "VCTClient_Couns_AF='"+VCTClient_Couns_AF+"',"
          + "VCTClient_Couns_TOT='"+VCTClient_Couns_TOT+"',"
          + "VCTClient_Tested_CM='"+VCTClient_Tested_CM+"',"
          + "VCTClient_Tested_CF='"+VCTClient_Tested_CF+"',"
          + "VCTClient_Tested_AM='"+VCTClient_Tested_AM+"',"
          + "VCTClient_Tested_AF='"+VCTClient_Tested_AF+"',"
          + "VCTClient_Tested_TOT='"+VCTClient_Tested_TOT+"',"
          + "VCTClient_HIV_CM='"+VCTClient_HIV_CM+"',"
          + "VCTClient_HIV_CF='"+VCTClient_HIV_CF+"',"
          + "VCTClient_HIV_AM='"+VCTClient_HIV_AM+"',"
          + "VCTClient_HIV_AF='"+VCTClient_HIV_AF+"',"
          + "VCTClient_HIV_TOT='"+VCTClient_HIV_TOT+"',"
          + "VCTPartner_Couns_TOT='"+VCTPartner_Couns_TOT+"',"
          + "VCTPartner_Tested_TOT='"+VCTPartner_Tested_TOT+"',"
          + "VCTPartner_HIV_TOT='"+VCTPartner_HIV_TOT+"',"
          + "VCTPartner_Disc_TOT='"+VCTPartner_Disc_TOT+"',"
          + " DTCA_Couns_In_CM='"+DTCA_Couns_In_CM+"',"
          + "DTCA_Couns_In_CF='"+DTCA_Couns_In_CF+"',"
          + "DTCA_Couns_In_AM='"+DTCA_Couns_In_AM+"',"
          + "DTCA_Couns_In_AF='"+DTCA_Couns_In_AF+"',"
          + "DTCA_Couns_In_Tot='"+DTCA_Couns_In_Tot+"',"
          + "DTCA_Couns_Out_CM='"+DTCA_Couns_Out_CM+"',"
          + "DTCA_Couns_Out_CF='"+DTCA_Couns_Out_CF+"',"
          + "DTCA_Couns_Out_AM='"+DTCA_Couns_Out_AM+"',"
          + "DTCA_Couns_Out_AF='"+DTCA_Couns_Out_AF+"',"
          + "DTCA_Couns_Out_Tot='"+DTCA_Couns_Out_Tot+"',"
          + "DTCB_Test_In_CM='"+DTCB_Test_In_CM+"',"
          + "DTCB_Test_In_CF='"+DTCB_Test_In_CF+"',"
          + "DTCB_Test_In_AM='"+DTCB_Test_In_AM+"',"
          + "DTCB_Test_In_AF='"+DTCB_Test_In_AF+"',"
          + "DTCB_Test_In_Tot='"+DTCB_Test_In_Tot+"',"
          + "DTCB_Test_Out_CM='"+DTCB_Test_Out_CM+"',"
          + "DTCB_Test_Out_CF='"+DTCB_Test_Out_CF+"',"
          + "DTCB_Test_Out_AM='"+DTCB_Test_Out_AM+"',"
          + "DTCB_Test_Out_AF='"+DTCB_Test_Out_AF+"',"
          + "DTCB_Test_Out_Tot='"+DTCB_Test_Out_Tot+"',"
          + "DTCC_HIV_In_CM='"+DTCC_HIV_In_CM+"',"
          + "DTCC_HIV_In_CF='"+DTCC_HIV_In_CF+"',"
          + "DTCC_HIV_In_AM='"+DTCC_HIV_In_AM+"',"
          + "DTCC_HIV_In_AF='"+DTCC_HIV_In_AF+"',"
          + " DTCC_HIV_In_Tot='"+DTCC_HIV_In_Tot+"',"
          + "DTCC_HIV_Out_CM='"+DTCC_HIV_Out_CM+"',"
          + "DTCC_HIV_Out_CF='"+DTCC_HIV_Out_CF+"',"
          + "DTCC_HIV_Out_AM='"+DTCC_HIV_Out_AM+"',"
          + "DTCC_HIV_Out_AF='"+DTCC_HIV_Out_AF+"',"
          + "DTCC_HIV_Out_Tot='"+DTCC_HIV_Out_Tot+"',"
          + "isValidated='1' where ID='"+tableid+"'";        
     System.out.println(runvalidate);  
   conn.st.executeUpdate(runvalidate);
    session.setAttribute("validate711", "<font color=\"green\"><b>Form MOH 711 Validated Successfully.</b></font>");
  
    
     int monthDiff=0;
     String getMonths="SELECT TIMESTAMPDIFF(MONTH, timestamp, now()) FROM moh711 WHERE ID='"+tableid+"'";
     conn.rs1=conn.st1.executeQuery(getMonths);
     if(conn.rs1.next()==true){
         System.out.println("months are : "+conn.rs1.getString(1));
    monthDiff=conn.rs1.getInt(1);
     }
     if(monthDiff>0){
//      UPDATE THE COLUMN   
       java.util.Date date= new java.util.Date();
Timestamp lastUpdatedOn =new Timestamp(date.getTime()); 

     String updateLast="UPDATE moh711 SET updatedBy='"+userid+"', updatedOn='"+lastUpdatedOn+"' WHERE ID='"+tableid+"'" ;   
       conn.st2.executeUpdate(updateLast);
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
        
   
        response.sendRedirect("load711.jsp");
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

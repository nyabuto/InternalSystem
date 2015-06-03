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
String checker="select * from moh711 WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, tableid);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
  if(conn.rs.getString("FPMicrolutN")!=null){FPMicrolutN=conn.rs.getString("FPMicrolutN");}
  if(conn.rs.getString("FPMicrolutR")!=null){FPMicrolutR=conn.rs.getString("FPMicrolutR");}
  if(conn.rs.getString("FPMicrolutT")!=null){FPMicrolutT=conn.rs.getString("FPMicrolutT");}
  if(conn.rs.getString("FPMicrogynonN")!=null){FPMicrogynonN=conn.rs.getString("FPMicrogynonN");}
  if(conn.rs.getString("FPMicrogynonR")!=null){FPMicrogynonR=conn.rs.getString("FPMicrogynonR");}
  if(conn.rs.getString("FPMicrogynonT")!=null){FPMicrogynonT=conn.rs.getString("FPMicrogynonT");}
  if(conn.rs.getString("FPINJECTIONSN")!=null){FPINJECTIONSN=conn.rs.getString("FPINJECTIONSN");}
  if(conn.rs.getString("FPINJECTIONSR")!=null){FPINJECTIONSR=conn.rs.getString("FPINJECTIONSR");}
  if(conn.rs.getString("FPINJECTIONST")!=null){FPINJECTIONST=conn.rs.getString("FPINJECTIONST");}
  if(conn.rs.getString("FPIUCDN")!=null){FPIUCDN=conn.rs.getString("FPIUCDN");}
  if(conn.rs.getString("FPIUCDR")!=null){FPIUCDR=conn.rs.getString("FPIUCDR");}
  if(conn.rs.getString("FPIUCDT")!=null){FPIUCDT=conn.rs.getString("FPIUCDT");}
  if(conn.rs.getString("FPIMPLANTSN")!=null){FPIMPLANTSN=conn.rs.getString("FPIMPLANTSN");}
  if(conn.rs.getString("FPIMPLANTSR")!=null){FPIMPLANTSR=conn.rs.getString("FPIMPLANTSR");}
  if(conn.rs.getString("FPIMPLANTST")!=null){FPIMPLANTST=conn.rs.getString("FPIMPLANTST");}
  if(conn.rs.getString("FPBTLN")!=null){FPBTLN=conn.rs.getString("FPBTLN");}
  if(conn.rs.getString("FPBTLR")!=null){FPBTLR=conn.rs.getString("FPBTLR");}
  if(conn.rs.getString("FPBTLT")!=null){FPBTLT=conn.rs.getString("FPBTLT");}
  if(conn.rs.getString("FPVasectomyN")!=null){FPVasectomyN=conn.rs.getString("FPVasectomyN");}
  if(conn.rs.getString("FPVasectomyR")!=null){FPVasectomyR=conn.rs.getString("FPVasectomyR");}
  if(conn.rs.getString("FPVasectomyT")!=null){FPVasectomyT=conn.rs.getString("FPVasectomyT");}
  if(conn.rs.getString("FPCONDOMSN")!=null){FPCONDOMSN=conn.rs.getString("FPCONDOMSN");}
  if(conn.rs.getString("FPCONDOMSR")!=null){FPCONDOMSR=conn.rs.getString("FPCONDOMSR");}
  if(conn.rs.getString("FPCONDOMST")!=null){FPCONDOMST=conn.rs.getString("FPCONDOMST");}
         
  if(conn.rs.getString("FPOTHERN")!=null){FPOTHERN=conn.rs.getString("FPOTHERN");}
  if(conn.rs.getString("FPOTHERR")!=null){FPOTHERR=conn.rs.getString("FPOTHERR");}
  if(conn.rs.getString("FPOTHERT")!=null){FPOTHERT=conn.rs.getString("FPOTHERT");}
         
  if(conn.rs.getString("FPCLIENTSN")!=null){FPCLIENTSN=conn.rs.getString("FPCLIENTSN");}
  if(conn.rs.getString("FPCLIENTSR")!=null){FPCLIENTSR=conn.rs.getString("FPCLIENTSR");}
  if(conn.rs.getString("FPCLIENTST")!=null){FPCLIENTST=conn.rs.getString("FPCLIENTST");}
   if(conn.rs.getString("FPIUCDRemoval")!=null){FPIUCDRemoval=conn.rs.getString("FPIUCDRemoval");}
  if(conn.rs.getString("FPIMPLANTSRemoval")!=null){FPIMPLANTSRemoval=conn.rs.getString("FPIMPLANTSRemoval");}
          
          
          
          // mch 
  // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";

   if(conn.rs.getString("PMCTA_1stVisit_ANC")!=null){PMCTA_1stVisit_ANC=conn.rs.getString("PMCTA_1stVisit_ANC");}
  if(conn.rs.getString("PMCTA_ReVisit_ANC")!=null){PMCTA_ReVisit_ANC=conn.rs.getString("PMCTA_ReVisit_ANC");}
  if(conn.rs.getString("PMCTANCClientsT")!=null){PMCTANCClientsT=conn.rs.getString("PMCTANCClientsT");}
  if(conn.rs.getString("PMCTHB7")!=null){PMCTHB7=conn.rs.getString("PMCTHB7");}
  if(conn.rs.getString("PMCTIPT1")!=null){PMCTIPT1=conn.rs.getString("PMCTIPT1");}
  if(conn.rs.getString("PMCTIPT2")!=null){PMCTIPT2=conn.rs.getString("PMCTIPT2");}
  if(conn.rs.getString("PMCTANCClients4")!=null){PMCTANCClients4=conn.rs.getString("PMCTANCClients4");}
  if(conn.rs.getString("PMCTITN")!=null){PMCTITN=conn.rs.getString("PMCTITN");}
  
  // maternity MATNormalDelivery=MATCSection=""MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=
  //MATDischargealive=MATReferral=MATNeoNatalD=
//MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive
  //=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
 
  if(conn.rs.getString("MATNormalDelivery")!=null){MATNormalDelivery=conn.rs.getString("MATNormalDelivery");}
  if(conn.rs.getString("MATCSection")!=null){MATCSection=conn.rs.getString("MATCSection");}
  if(conn.rs.getString("MATBreech")!=null){MATBreech=conn.rs.getString("MATBreech");}
  if(conn.rs.getString("MATAssistedVag")!=null){MATAssistedVag=conn.rs.getString("MATAssistedVag");}
  if(conn.rs.getString("MATDeliveryT")!=null){MATDeliveryT=conn.rs.getString("MATDeliveryT");}
  if(conn.rs.getString("MATLiveBirth")!=null){MATLiveBirth=conn.rs.getString("MATLiveBirth");}
  if(conn.rs.getString("MATStillBirth")!=null){MATStillBirth=conn.rs.getString("MATStillBirth");}
  if(conn.rs.getString("FPBTLT")!=null){FPBTLT=conn.rs.getString("FPBTLT");}
  if(conn.rs.getString("MATWeight2500")!=null){MATWeight2500=conn.rs.getString("MATWeight2500");}
  if(conn.rs.getString("MATPreTerm")!=null){MATPreTerm=conn.rs.getString("MATPreTerm");}
  if(conn.rs.getString("MATDischargealive")!=null){MATDischargealive=conn.rs.getString("MATDischargealive");}
  if(conn.rs.getString("MATReferral")!=null){MATReferral=conn.rs.getString("MATReferral");}
  if(conn.rs.getString("MATNeoNatalD")!=null){MATNeoNatalD=conn.rs.getString("MATNeoNatalD");}
  if(conn.rs.getString("MATMaternalD")!=null){MATMaternalD=conn.rs.getString("MATMaternalD");}
  if(conn.rs.getString("MATAPHAlive")!=null){MATAPHAlive=conn.rs.getString("MATAPHAlive");}
  if(conn.rs.getString("MATAPHDead")!=null){MATAPHDead=conn.rs.getString("MATAPHDead");}
  if(conn.rs.getString("MATPPHAlive")!=null){MATPPHAlive=conn.rs.getString("MATPPHAlive");}
  if(conn.rs.getString("MATPPHDead")!=null){MATPPHDead=conn.rs.getString("MATPPHDead");}
  if(conn.rs.getString("MATEclampAlive")!=null){MATEclampAlive=conn.rs.getString("MATEclampAlive");}
  if(conn.rs.getString("MATEclampDead")!=null){MATEclampDead=conn.rs.getString("MATEclampDead");}
  if(conn.rs.getString("MATRupUtAlive")!=null){MATRupUtAlive=conn.rs.getString("MATRupUtAlive");}
  if(conn.rs.getString("MATRupUtDead")!=null){MATRupUtDead=conn.rs.getString("MATRupUtDead");}
  if(conn.rs.getString("MATObstrLaborAlive")!=null){MATObstrLaborAlive=conn.rs.getString("MATObstrLaborAlive");}
  if(conn.rs.getString("MATObstrLaborDead")!=null){MATObstrLaborDead=conn.rs.getString("MATObstrLaborDead");}
  if(conn.rs.getString("MATSepsisAlive")!=null){MATSepsisAlive=conn.rs.getString("MATSepsisAlive");}
  if(conn.rs.getString("MATSepsisDead")!=null){MATSepsisDead=conn.rs.getString("MATSepsisDead");}
//      VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF
  //=VCTClient_Tested_AM=VCTClient_Tested_AF
//=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=
  //VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
//     
          
  if(conn.rs.getString("VCTClient_Couns_CM")!=null){VCTClient_Couns_CM=conn.rs.getString("VCTClient_Couns_CM");}
  if(conn.rs.getString("VCTClient_Couns_CF")!=null){VCTClient_Couns_CF=conn.rs.getString("VCTClient_Couns_CF");}
  if(conn.rs.getString("VCTClient_Couns_AM")!=null){VCTClient_Couns_AM=conn.rs.getString("VCTClient_Couns_AM");}
  if(conn.rs.getString("VCTClient_Couns_AF")!=null){VCTClient_Couns_AF=conn.rs.getString("VCTClient_Couns_AF");}
  if(conn.rs.getString("VCTClient_Couns_TOT")!=null){VCTClient_Couns_TOT=conn.rs.getString("VCTClient_Couns_TOT");}
  if(conn.rs.getString("VCTClient_Tested_CM")!=null){VCTClient_Tested_CM=conn.rs.getString("VCTClient_Tested_CM");}
  if(conn.rs.getString("VCTClient_Tested_CF")!=null){VCTClient_Tested_CF=conn.rs.getString("VCTClient_Tested_CF");}
  if(conn.rs.getString("VCTClient_Tested_AM")!=null){VCTClient_Tested_AM=conn.rs.getString("VCTClient_Tested_AM");}
  if(conn.rs.getString("VCTClient_Tested_AF")!=null){VCTClient_Tested_AF=conn.rs.getString("VCTClient_Tested_AF");}
  if(conn.rs.getString("VCTClient_Tested_TOT")!=null){VCTClient_Tested_TOT=conn.rs.getString("VCTClient_Tested_TOT");}
  if(conn.rs.getString("VCTClient_HIV_CM")!=null){VCTClient_HIV_CM=conn.rs.getString("VCTClient_HIV_CM");}
  if(conn.rs.getString("VCTClient_HIV_CF")!=null){VCTClient_HIV_CF=conn.rs.getString("VCTClient_HIV_CF");}
  if(conn.rs.getString("VCTClient_HIV_AM")!=null){VCTClient_HIV_AM=conn.rs.getString("VCTClient_HIV_AM");}
  if(conn.rs.getString("VCTClient_HIV_AF")!=null){VCTClient_HIV_AF=conn.rs.getString("VCTClient_HIV_AF");}
  if(conn.rs.getString("VCTClient_HIV_TOT")!=null){VCTClient_HIV_TOT=conn.rs.getString("VCTClient_HIV_TOT");}
  if(conn.rs.getString("VCTPartner_Couns_TOT")!=null){VCTPartner_Couns_TOT=conn.rs.getString("VCTPartner_Couns_TOT");}
  if(conn.rs.getString("VCTPartner_Tested_TOT")!=null){VCTPartner_Tested_TOT=conn.rs.getString("VCTPartner_Tested_TOT");}
  if(conn.rs.getString("VCTPartner_HIV_TOT")!=null){VCTPartner_HIV_TOT=conn.rs.getString("VCTPartner_HIV_TOT");}
  if(conn.rs.getString("VCTPartner_Disc_TOT")!=null){VCTPartner_Disc_TOT=conn.rs.getString("VCTPartner_Disc_TOT");}
  
  
  
//  DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=
  //DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
//=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot
  //=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
//=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

  
  //dtc
  if(conn.rs.getString("DTCA_Couns_In_CM")!=null){DTCA_Couns_In_CM=conn.rs.getString("DTCA_Couns_In_CM");}
  if(conn.rs.getString("DTCA_Couns_In_CF")!=null){DTCA_Couns_In_CF=conn.rs.getString("DTCA_Couns_In_CF");}
  if(conn.rs.getString("DTCA_Couns_In_AM")!=null){DTCA_Couns_In_AM=conn.rs.getString("DTCA_Couns_In_AM");}
  if(conn.rs.getString("DTCA_Couns_In_AF")!=null){DTCA_Couns_In_AF=conn.rs.getString("DTCA_Couns_In_AF");}
  if(conn.rs.getString("DTCA_Couns_In_Tot")!=null){DTCA_Couns_In_Tot=conn.rs.getString("DTCA_Couns_In_Tot");}
  if(conn.rs.getString("DTCA_Couns_Out_CM")!=null){DTCA_Couns_Out_CM=conn.rs.getString("DTCA_Couns_Out_CM");}
  if(conn.rs.getString("DTCA_Couns_Out_CF")!=null){DTCA_Couns_Out_CF=conn.rs.getString("DTCA_Couns_Out_CF");}
  if(conn.rs.getString("DTCA_Couns_Out_AM")!=null){DTCA_Couns_Out_AM=conn.rs.getString("DTCA_Couns_Out_AM");}
  if(conn.rs.getString("DTCA_Couns_Out_AF")!=null){DTCA_Couns_Out_AF=conn.rs.getString("DTCA_Couns_Out_AF");}
  if(conn.rs.getString("DTCA_Couns_Out_Tot")!=null){DTCA_Couns_Out_Tot=conn.rs.getString("DTCA_Couns_Out_Tot");}
  if(conn.rs.getString("DTCB_Test_In_CM")!=null){DTCB_Test_In_CM=conn.rs.getString("DTCB_Test_In_CM");}
  if(conn.rs.getString("DTCB_Test_In_CF")!=null){DTCB_Test_In_CF=conn.rs.getString("DTCB_Test_In_CF");}
  if(conn.rs.getString("DTCB_Test_In_AM")!=null){DTCB_Test_In_AM=conn.rs.getString("DTCB_Test_In_AM");}
  if(conn.rs.getString("DTCB_Test_In_AF")!=null){DTCB_Test_In_AF=conn.rs.getString("DTCB_Test_In_AF");}
  if(conn.rs.getString("DTCB_Test_In_Tot")!=null){DTCB_Test_In_Tot=conn.rs.getString("DTCB_Test_In_Tot");}
  if(conn.rs.getString("DTCB_Test_Out_CM")!=null){DTCB_Test_Out_CM=conn.rs.getString("DTCB_Test_Out_CM");}
  if(conn.rs.getString("DTCB_Test_Out_CF")!=null){DTCB_Test_Out_CF=conn.rs.getString("DTCB_Test_Out_CF");}
  if(conn.rs.getString("DTCB_Test_Out_AM")!=null){DTCB_Test_Out_AM=conn.rs.getString("DTCB_Test_Out_AM");}
  if(conn.rs.getString("DTCB_Test_Out_AF")!=null){DTCB_Test_Out_AF=conn.rs.getString("DTCB_Test_Out_AF");}
  if(conn.rs.getString("DTCB_Test_Out_Tot")!=null){DTCB_Test_Out_Tot=conn.rs.getString("DTCB_Test_Out_Tot");}
  if(conn.rs.getString("DTCC_HIV_In_CM")!=null){DTCC_HIV_In_CM=conn.rs.getString("DTCC_HIV_In_CM");}
  if(conn.rs.getString("DTCC_HIV_In_CF")!=null){DTCC_HIV_In_CF=conn.rs.getString("DTCC_HIV_In_CF");}
  if(conn.rs.getString("DTCC_HIV_In_AM")!=null){DTCC_HIV_In_AM=conn.rs.getString("DTCC_HIV_In_AM");}
  if(conn.rs.getString("DTCC_HIV_In_AF")!=null){DTCC_HIV_In_AF=conn.rs.getString("DTCC_HIV_In_AF");}
  if(conn.rs.getString("DTCC_HIV_In_Tot")!=null){DTCC_HIV_In_Tot=conn.rs.getString("DTCC_HIV_In_Tot");}
  if(conn.rs.getString("DTCC_HIV_Out_CM")!=null){DTCC_HIV_Out_CM=conn.rs.getString("DTCC_HIV_Out_CM");}
  if(conn.rs.getString("DTCC_HIV_Out_CF")!=null){DTCC_HIV_Out_CF=conn.rs.getString("DTCC_HIV_Out_CF");}
  if(conn.rs.getString("DTCC_HIV_Out_AM")!=null){DTCC_HIV_Out_AM=conn.rs.getString("DTCC_HIV_Out_AM");}
  if(conn.rs.getString("DTCC_HIV_Out_AF")!=null){DTCC_HIV_Out_AF=conn.rs.getString("DTCC_HIV_Out_AF");}
  if(conn.rs.getString("DTCC_HIV_Out_Tot")!=null){DTCC_HIV_Out_Tot=conn.rs.getString("DTCC_HIV_Out_Tot");}
  
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
  
}
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

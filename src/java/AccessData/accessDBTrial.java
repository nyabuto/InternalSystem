/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccessData;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nyabuto Geofrey
 */
public class accessDBTrial extends HttpServlet {

String id;
Database db;
int position;
Object Counter,Updated,DistrictID,SubPartnerID,Annee,Mois;

Object FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,
FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR;

Object FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,
FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection;

Object MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,
MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead;

Object VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;

Object DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,Userid;

String userid, isValidated,updatedBy,updatedOn,yearmonth,isLocked;


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         position=0;
         
         
         
    File dbFile = new File("D:/SYSTEM UPDATES/Internal_System_ 25_ Nov_2014.mdb");
    if(dbFile.exists() && !dbFile.isDirectory()) {
db = DatabaseBuilder.open(dbFile);
        System.out.println("file has been found");
//        WHEN TABLE IS FOUND, WE GET DATA FROM THIS TABLE
        
        try {
            for(String tableName:db.getTableNames()){
//               
             if(tableName.contains("TblInternal711_2009")){
                 System.out.println("table name is : "+tableName);  
             Table table = db.getTable(tableName);
//             position=0;
      for(Row row : table) {
          FPMicrolutN=FPMicrolutR=FPMicrolutT=FPMicrogynonN=FPMicrogynonR=FPMicrogynonT=FPINJECTIONSN=FPINJECTIONSR=
FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR="0";
FPVasectomyT=FPCONDOMSN=FPCONDOMSR=FPCONDOMST=FPOTHERN=FPOTHERR=FPOTHERT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPIUCDRemoval=
FPIMPLANTSRemoval=PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="0";
MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=MATDischargealive=MATReferral=MATNeoNatalD=
MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="0";

VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="0";

DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="0";
 
Counter=Updated=DistrictID=SubPartnerID=Annee=Mois="0";         
        userid=isValidated=updatedBy=updatedOn=yearmonth=isLocked="";         
          
 Counter = row.get("Counter");
  Updated = row.get("Updated");
  DistrictID = row.get("DistrictID");
  SubPartnerID = row.get("SubPartnerID");
  Annee = row.get("Annee");
  Mois = row.get("Mois");
//      System.out.println("FP");
         if(row.get("FP_Pills_Microlut_NC")!=null){FPMicrolutN=row.get("FP_Pills_Microlut_NC");}
         if(row.get("FP_Pills_Microlut_RV")!=null){FPMicrolutR=row.get("FP_Pills_Microlut_RV");}
         if(row.get("FP_Pills_Microlut_T")!=null){FPMicrolutT=row.get("FP_Pills_Microlut_T");}
         
         if(row.get("FP_Pills_Microgynon_NC")!=null){FPMicrogynonN=row.get("FP_Pills_Microgynon_NC");}
         if(row.get("FP_Pills_Microgynon_RV")!=null){FPMicrogynonR=row.get("FP_Pills_Microgynon_RV");}
         if(row.get("FP_Pills_Microgynon_T")!=null){FPMicrogynonT=row.get("FP_Pills_Microgynon_T");}
         
         if(row.get("FP_Injections_NC")!=null){FPINJECTIONSN=row.get("FP_Injections_NC");}
         if(row.get("FP_Injections_RV")!=null){FPINJECTIONSR=row.get("FP_Injections_RV");}
         if(row.get("FP_Injections_T")!=null){FPINJECTIONST=row.get("FP_Injections_T");}
         
         if(row.get("FP_IUCD_NC")!=null){FPIUCDN=row.get("FP_IUCD_NC");}
         if(row.get("FP_IUCD_RV")!=null){FPIUCDR=row.get("FP_IUCD_RV");}
         if(row.get("FP_IUCD_T")!=null){FPIUCDT=row.get("FP_IUCD_T");}
         
         if(row.get("FP_Implants_NC")!=null){FPIMPLANTSN=row.get("FP_Implants_NC");}
         if(row.get("FP_Implants_RV")!=null){FPIMPLANTSR=row.get("FP_Implants_RV");}
         if(row.get("FP_Implants_T")!=null){FPIMPLANTST=row.get("FP_Implants_T");}
         
         if(row.get("FP_Steril_BTL_NC")!=null){FPBTLN=row.get("FP_Steril_BTL_NC");}
//         FPBTLR=row.get("");
         if(row.get("FP_Steril_BTL_T")!=null){FPBTLT=row.get("FP_Steril_BTL_T");}
         
         if(row.get("FP_Steril_Vas_NC")!=null){FPVasectomyN=row.get("FP_Steril_Vas_NC");}
//         FPVasectomyR=row.get("");
         if(row.get("FP_Steril_Vas_T")!=null){FPVasectomyT=row.get("FP_Steril_Vas_T");}
         

         if(row.get("FP_Condom_NC")!=null){FPCONDOMSN=row.get("FP_Condom_NC");}
         if(row.get("FP_Condom_RV")!=null){FPCONDOMSR=row.get("FP_Condom_RV");}
         if(row.get("FP_Condom_T")!=null){FPCONDOMST=row.get("FP_Condom_T");}
         
         if(row.get("FP_Others_NC")!=null){FPOTHERN=row.get("FP_Others_NC");}
         if(row.get("FP_Others_RV")!=null){FPOTHERR=row.get("FP_Others_RV");}
         if(row.get("FP_Others_T")!=null){FPOTHERT=row.get("FP_Others_T");}
         
         if(row.get("FP_TClients_NC")!=null){FPCLIENTSN=row.get("FP_TClients_NC");}
         if(row.get("FP_TClients_RV")!=null){FPCLIENTSR=row.get("FP_TClients_RV");}
         if(row.get("FP_TClients_T")!=null){FPCLIENTST=row.get("FP_TClients_T");}
         
         if(row.get("FP_Removals_IUCD")!=null){FPIUCDRemoval=row.get("FP_Removals_IUCD");}
         if(row.get("FP_Removals_Implants")!=null){FPIMPLANTSRemoval=row.get("FP_Removals_Implants");}
         
//              System.out.println("PMTCT");
         if(row.get("PMCTB1_N")!=null){PMCTA_1stVisit_ANC=row.get("PMCTB1_N");}
         if(row.get("PMCTB1_R")!=null){PMCTA_ReVisit_ANC=row.get("PMCTB1_R");}
         if(row.get("PMCTB1_T")!=null){PMCTANCClientsT=row.get("PMCTB1_T");}
         if(row.get("PMCTB2_T")!=null){PMCTHB7=row.get("PMCTB2_T");}
         if(row.get("PMCTB3_T")!=null){PMCTIPT1=row.get("PMCTB3_T");}
         if(row.get("PMCTB4_T")!=null){PMCTIPT2=row.get("PMCTB4_T");}
         if(row.get("PMCTB5_T")!=null){PMCTANCClients4=row.get("PMCTB5_T");}
         if(row.get("PMCTB6_T")!=null){PMCTITN=row.get("PMCTB6_T");}
//              System.out.println("MATERNIRTY");
         if(row.get("MATE1_N")!=null){MATNormalDelivery=row.get("MATE1_N");}
         if(row.get("MATE2_N")!=null){MATCSection=row.get("MATE2_N");}
         if(row.get("MATE3_N")!=null){MATBreech=row.get("MATE3_N");}
         if(row.get("MATE4_N")!=null){MATAssistedVag=row.get("MATE4_N");}
         if(row.get("MATE5_N")!=null){MATDeliveryT=row.get("MATE5_N");}
         if(row.get("MATE6_N")!=null){MATLiveBirth=row.get("MATE6_N");}
         if(row.get("MATE7_N")!=null){MATStillBirth=row.get("MATE7_N");}
         if(row.get("MATE8_N")!=null){MATWeight2500=row.get("MATE8_N");}
         if(row.get("MATE9_N")!=null){MATPreTerm=row.get("MATE9_N");}
         if(row.get("MATE10_N")!=null){MATDischargealive=row.get("MATE10_N");}
         if(row.get("MATE11_N")!=null){MATReferral=row.get("MATE11_N");}
         if(row.get("MATE12_N")!=null){MATNeoNatalD=row.get("MATE12_N");}
         if(row.get("MATE13_N")!=null){MATMaternalD=row.get("MATE13_N");}
         if(row.get("MATE14_A")!=null){MATAPHAlive=row.get("MATE14_A");}
         if(row.get("MATE14_D")!=null){MATAPHDead=row.get("MATE14_D");}
         if(row.get("MATE15_A")!=null){MATPPHAlive=row.get("MATE15_A");}
         if(row.get("MATE15_D")!=null){MATPPHDead=row.get("MATE15_D");}
         if(row.get("MATE16_A")!=null){MATEclampAlive=row.get("MATE16_A");}
         if(row.get("MATE16_D")!=null){MATEclampDead=row.get("MATE16_D");}
         if(row.get("MATE17_A")!=null){MATRupUtAlive=row.get("MATE17_A");}
         if(row.get("MATE17_D")!=null){MATRupUtDead=row.get("MATE17_D");}
         if(row.get("MATE18_A")!=null){MATObstrLaborAlive=row.get("MATE18_A");}
         if(row.get("MATE18_D")!=null){MATObstrLaborDead=row.get("MATE18_D");}
         if(row.get("MATE19_A")!=null){MATSepsisAlive=row.get("MATE19_A");}
         if(row.get("MATE19_D")!=null){MATSepsisDead=row.get("MATE19_D");}
//         System.out.println("VCT");
         if(row.get("VCTH1_C_YM")!=null){VCTClient_Couns_CM=row.get("VCTH1_C_YM");}
         if(row.get("VCTH1_C_YF")!=null){VCTClient_Couns_CF=row.get("VCTH1_C_YF");}
         if(row.get("VCTH1_C_AM")!=null){VCTClient_Couns_AM=row.get("VCTH1_C_AM");}
         if(row.get("VCTH1_C_AF")!=null){VCTClient_Couns_AF=row.get("VCTH1_C_AF");}
         if(row.get("VCTH1_C_T")!=null){VCTClient_Couns_TOT=row.get("VCTH1_C_T");}
         
         if(row.get("VCTH1_T_YM")!=null){VCTClient_Tested_CM=row.get("VCTH1_T_YM");}
         if(row.get("VCTH1_T_YF")!=null){VCTClient_Tested_CF=row.get("VCTH1_T_YF");}
         if(row.get("VCTH1_T_AM")!=null){VCTClient_Tested_AM=row.get("VCTH1_T_AM");}
         if(row.get("VCTH1_T_AF")!=null){VCTClient_Tested_AF=row.get("VCTH1_T_AF");}
         if(row.get("VCTH1_T_T")!=null){VCTClient_Tested_TOT=row.get("VCTH1_T_T");}
         
         if(row.get("VCTH1_H_YM")!=null){VCTClient_HIV_CM=row.get("VCTH1_H_YM");}
         if(row.get("VCTH1_H_YF")!=null){VCTClient_HIV_CF=row.get("VCTH1_H_YF");}
         if(row.get("VCTH1_H_AM")!=null){VCTClient_HIV_AM=row.get("VCTH1_H_AM");}
         if(row.get("VCTH1_H_AF")!=null){VCTClient_HIV_AF=row.get("VCTH1_H_AF");}
         if(row.get("VCTH1_H_T")!=null){VCTClient_HIV_TOT=row.get("VCTH1_H_T");}
         if(row.get("VCTH2_C_T")!=null){VCTPartner_Couns_TOT=row.get("VCTH2_C_T");}
         if(row.get("VCTH2_T_T")!=null){VCTPartner_Tested_TOT=row.get("VCTH2_T_T");}
         if(row.get("VCTH2_B_T")!=null){VCTPartner_HIV_TOT=row.get("VCTH2_B_T");}
         if(row.get("VCTH2_W_T")!=null){VCTPartner_Disc_TOT=row.get("VCTH2_W_T");}
//         System.out.println("DTC A");
         if(row.get("DTCI1_I_CM")!=null){DTCA_Couns_In_CM=row.get("DTCI1_I_CM");}
         if(row.get("DTCI1_I_CF")!=null){DTCA_Couns_In_CF=row.get("DTCI1_I_CF");}
         if(row.get("DTCI1_I_AM")!=null){DTCA_Couns_In_AM=row.get("DTCI1_I_AM");}
         if(row.get("DTCI1_I_AF")!=null){DTCA_Couns_In_AF=row.get("DTCI1_I_AF");}
         if(row.get("DTCI1_I_T")!=null){DTCA_Couns_In_Tot=row.get("DTCI1_I_T");}
         if(row.get("DTCI1_O_CM")!=null){DTCA_Couns_Out_CM=row.get("DTCI1_O_CM");}
         if(row.get("DTCI1_O_CF")!=null){DTCA_Couns_Out_CF=row.get("DTCI1_O_CF");}
         if(row.get("DTCI1_O_AM")!=null){DTCA_Couns_Out_AM=row.get("DTCI1_O_AM");}
         if(row.get("DTCI1_O_AF")!=null){DTCA_Couns_Out_AF=row.get("DTCI1_O_AF");}
         if(row.get("DTCI1_O_T")!=null){DTCA_Couns_Out_Tot=row.get("DTCI1_O_T");}
//         System.out.println("DTC B");
         if(row.get("DTCI2_I_CM")!=null){DTCB_Test_In_CM=row.get("DTCI2_I_CM");}
         if(row.get("DTCI2_I_CF")!=null){DTCB_Test_In_CF=row.get("DTCI2_I_CF");}
         if(row.get("DTCI2_I_AM")!=null){DTCB_Test_In_AM=row.get("DTCI2_I_AM");}
         if(row.get("DTCI2_I_AF")!=null){DTCB_Test_In_AF=row.get("DTCI2_I_AF");}
         if(row.get("DTCI2_I_T")!=null){DTCB_Test_In_Tot=row.get("DTCI2_I_T");}
         if(row.get("DTCI2_O_CM")!=null){DTCB_Test_Out_CM=row.get("DTCI2_O_CM");}
         if(row.get("DTCI2_O_CF")!=null){DTCB_Test_Out_CF=row.get("DTCI2_O_CF");}
         if(row.get("DTCI2_O_AM")!=null){DTCB_Test_Out_AM=row.get("DTCI2_O_AM");}
         if(row.get("DTCI2_O_AF")!=null){DTCB_Test_Out_AF=row.get("DTCI2_O_AF");}
         if(row.get("DTCI2_O_T")!=null){DTCB_Test_Out_Tot=row.get("DTCI2_O_T");}
//         System.out.println("DTC C");
        if(row.get("DTCI3_I_CM")!=null){DTCC_HIV_In_CM=row.get("DTCI3_I_CM");}
        if(row.get("DTCI3_I_CF")!=null){DTCC_HIV_In_CF=row.get("DTCI3_I_CF");}
        if(row.get("DTCI3_I_AM")!=null){DTCC_HIV_In_AM=row.get("DTCI3_I_AM");}
        if(row.get("DTCI3_I_AF")!=null){DTCC_HIV_In_AF= row.get("DTCI3_I_AF");}
        if(row.get("DTCI3_I_T")!=null){DTCC_HIV_In_Tot=row.get("DTCI3_I_T");}
        
        if(row.get("DTCI3_O_CM")!=null){DTCC_HIV_Out_CM=row.get("DTCI3_O_CM");}
        if(row.get("DTCI3_O_CF")!=null){DTCC_HIV_Out_CF=row.get("DTCI3_O_CF");}
        if(row.get("DTCI3_O_AM")!=null){DTCC_HIV_Out_AM=row.get("DTCI3_O_AM");}
        if(row.get("DTCI3_O_AF")!=null){DTCC_HIV_Out_AF=row.get("DTCI3_O_AF");}
        if(row.get("DTCI3_O_T")!=null){DTCC_HIV_Out_Tot=row.get("DTCI3_O_T");}
             
        Userid="0";

id=Annee.toString().replace(".0", "")+"_"+Mois.toString().replace(".0", "")+"_"+SubPartnerID.toString().replace(".0", "");
      
       userid="joelData";
       isValidated="1";
       updatedBy="";
       updatedOn="";
       int month=((Double) Mois).intValue();
       
               if(month<10){
       yearmonth=Annee.toString().replace(".0", "")+"0"+Mois.toString().replace(".0", "");
               }
               else{
              yearmonth=Annee.toString().replace(".0", "")+"0"+Mois.toString().replace(".0", "");     
               }
       isLocked="0";
//          System.out.println("id Is : "+id);
 
    position++;
    System.out.println("position : "+position); 
      }
      
             }
            }
        }
             catch(IOException e){
                     
                     }
        
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

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package AccessData;

import com.healthmarketscience.jackcess.Column;
import com.healthmarketscience.jackcess.ColumnBuilder;
import com.healthmarketscience.jackcess.DataType;
import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.IndexBuilder;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.TableBuilder;
import database.dbConn;
import database.dbConnAccess;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Nyabuto Geofrey
 */
@MultipartConfig(fileSizeThreshold=1024*1024*5, 	// 50 MB 
                 maxFileSize=1024*1024*200,      	// 200 MB
                 maxRequestSize=1024*1024*500)   	// 500 MB


public class moh711AccessData extends HttpServlet {
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
int tableNo;
String userid, isValidated,updatedBy,updatedOn,yearmonth,isLocked, tableDescription;
   File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";

String full_path="";
 String fileName="";
 
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            dbConn conn = new dbConn();
            String applicationPath = request.getServletContext().getRealPath("");
         String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
         session=request.getSession();
         
         
         tableDescription="loading file.....  <br>";
         session.setAttribute("mergingAccess", tableDescription);
                    
         
         
          File fileSaveDir = new File(uploadFilePath);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdirs();
        }
        System.out.println("Upload File Directory="+fileSaveDir.getAbsolutePath());
        
        for (Part part : request.getParts()) {
           fileName = getFileName(part);
            part.write(uploadFilePath + File.separator + fileName);
            System.out.println("file name is  :  "+fileName);
        }
        if(!fileName.endsWith(".mdb")){
  session.setAttribute("upload_success", "<font color=\"red\">Failed to load the access file. Please choose the correct File.</font>");   
        }
        else{
 full_path=fileSaveDir.getAbsolutePath()+"\\"+fileName;
 
 System.out.println("the saved file directory is  :  "+full_path);
 
 tableDescription+="Completed loading file.....  <br> Started merging data.... <br>";
 session.setAttribute("mergingAccess", tableDescription);
        
  try {
         tableNo=position=0;
         
//    File dbFile = new File("D:/SYSTEM UPDATES/Internal_System_ 25_ Nov_2014.mdb");
     File dbFile = new File(full_path);
     
    if(dbFile.exists() && !dbFile.isDirectory()) {
db = DatabaseBuilder.open(dbFile);
        System.out.println("file has been found");
//        WHEN TABLE IS FOUND, WE GET DATA FROM THIS TABLE
        
        try {
            for(String tableName:db.getTableNames()){
//               TblInternal711_2014upto 7th apr 2014
             if(tableName.contains("TblInternal711_")){
              tableNo++;position=0;
              session.setAttribute("currentTable", tableName);
              
                 tableDescription+=tableNo+". Merging Table  : <b>"+tableName+"</b> <br>";
                    session.setAttribute("mergingAccess", tableDescription);
                 System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>>table name is : "+tableName); 
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
          
          position++;
//    System.out.println("Look ma, a row: " + row);
//          System.out.println("row number : "+position);
        
//  for(Column column : table.getColumns()){
//    String columnName = column.getName();
//// Object value = row.get(columnName);
//  } 
// System.out.println("counter................."+position);   
  Counter = row.get("Counter");
  Updated = row.get("Updated");
  DistrictID = row.get("DistrictID");
  SubPartnerID = row.get("SubPartnerID");
  Annee = row.get("Annee");
  Mois = row.get("Mois");
//      System.out.println("FP");
  int currentYear=((Double) Annee).intValue();
  if(currentYear!=2015){
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
       session.removeAttribute("positionAccess");
        session.setAttribute("positionAccess",position);    
        System.out.println("at pos : "+session.getAttribute("positionAccess"));
        
    String checker="SELECT id FROM moh711 WHERE id='"+id+"'";
    conn.rs=conn.st.executeQuery(checker);
    if(conn.rs.next()==true){
        System.out.println("data already exist................."+position);   
    }
    else{
//       add as a new entry
        System.out.println("add as  new entry>>>>>>>>>>>"+position+" id is : "+id);
        String inserter=" INSERT INTO moh711 (ID,SubPartnerID,Annee,Mois,FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,FPINJECTIONST,FPIUCDN,FPIUCDT,FPIUCDR,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR,FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection,MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead,VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT,DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM,DTCC_HIV_In_AF,DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,userid,isValidated,updatedBy,yearmonth,isLocked) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
       
        conn.pst.setString(1 ,id);
        conn.pst.setString(2 , SubPartnerID.toString().replace(".0", ""));
        conn.pst.setString(3 , Annee.toString().replace(".0", ""));
        conn.pst.setString(4 , Mois.toString().replace(".0", ""));
        conn.pst.setString(5 , FPMicrolutN.toString().replace(".0", ""));
        conn.pst.setString(6 , FPMicrolutR.toString().replace(".0", ""));
        conn.pst.setString(7 , FPMicrolutT.toString().replace(".0", ""));
        conn.pst.setString(8 , FPMicrogynonN.toString().replace(".0", ""));
        conn.pst.setString( 9, FPMicrogynonR.toString().replace(".0", ""));
        conn.pst.setString(10 , FPMicrogynonT.toString().replace(".0", ""));
//        System.out.println("break 1");
        conn.pst.setString( 11, FPINJECTIONSN.toString().replace(".0", ""));
        conn.pst.setString( 12, FPINJECTIONSR.toString().replace(".0", ""));
        conn.pst.setString( 13, FPINJECTIONST.toString().replace(".0", ""));
        conn.pst.setString( 14, FPIUCDN.toString().replace(".0", ""));
        conn.pst.setString( 15, FPIUCDT.toString().replace(".0", ""));
        conn.pst.setString( 16, FPIUCDR.toString().replace(".0", ""));
        conn.pst.setString( 17, FPIMPLANTSN.toString().replace(".0", ""));
        conn.pst.setString( 18, FPIMPLANTSR.toString().replace(".0", ""));
        conn.pst.setString( 19, FPIMPLANTST.toString().replace(".0", ""));
        conn.pst.setString( 20, FPBTLN.toString().replace(".0", ""));
//        System.out.println("break 2");
        conn.pst.setString( 21, "0");
//        System.out.println("p1");
        conn.pst.setString( 22, FPBTLT.toString().replace(".0", ""));
//        System.out.println("p2");
        conn.pst.setString( 23, FPVasectomyN.toString().replace(".0", ""));
//        System.out.println("p3");
        conn.pst.setString( 24, "0");
//        System.out.println("p4");
        conn.pst.setString( 25, FPVasectomyT.toString().replace(".0", ""));
//        System.out.println("p5");
        conn.pst.setString( 26, FPCONDOMSN.toString().replace(".0", ""));
//        System.out.println("p6");
        conn.pst.setString( 27, FPCONDOMSR.toString().replace(".0", ""));
//        System.out.println("p7");
        conn.pst.setString( 28, FPCONDOMST.toString().replace(".0", ""));
//        System.out.println("p8  : "+FPOTHERN);
        conn.pst.setString( 29, FPOTHERN.toString().replace(".0", ""));
//        System.out.println("p9");
        conn.pst.setString( 30, FPOTHERR.toString().replace(".0", ""));
//        System.out.println("break 3");
        conn.pst.setString( 31, FPOTHERT.toString().replace(".0", ""));
        conn.pst.setString( 32, FPCLIENTSN.toString().replace(".0", ""));
        conn.pst.setString( 33, FPCLIENTSR.toString().replace(".0", ""));
        conn.pst.setString( 34, FPCLIENTST.toString().replace(".0", ""));
        conn.pst.setString( 35, FPIUCDRemoval.toString().replace(".0", ""));
        conn.pst.setString( 36, FPIMPLANTSRemoval.toString().replace(".0", ""));
        conn.pst.setString( 37, PMCTA_1stVisit_ANC.toString().replace(".0", ""));
        conn.pst.setString( 38, PMCTA_ReVisit_ANC.toString().replace(".0", ""));
        conn.pst.setString( 39, PMCTANCClientsT.toString().replace(".0", ""));
        conn.pst.setString( 40, PMCTHB7.toString().replace(".0", ""));
//        System.out.println("break 4");
        conn.pst.setString( 41, PMCTIPT1.toString().replace(".0", ""));
        conn.pst.setString( 42, PMCTIPT2.toString().replace(".0", ""));
        conn.pst.setString( 43, PMCTANCClients4.toString().replace(".0", ""));
        conn.pst.setString( 44, PMCTITN.toString().replace(".0", ""));
        conn.pst.setString( 45, MATNormalDelivery.toString().replace(".0", ""));
        conn.pst.setString( 46, MATCSection.toString().replace(".0", ""));
        conn.pst.setString( 47, MATBreech.toString().replace(".0", ""));
        conn.pst.setString( 48, MATAssistedVag.toString().replace(".0", ""));
        conn.pst.setString( 49, MATDeliveryT.toString().replace(".0", ""));
        conn.pst.setString( 50, MATLiveBirth.toString().replace(".0", ""));
//        System.out.println("break 5");
        conn.pst.setString( 51, MATStillBirth.toString().replace(".0", ""));
        conn.pst.setString( 52, MATWeight2500.toString().replace(".0", ""));
        conn.pst.setString( 53, MATPreTerm.toString().replace(".0", ""));
        conn.pst.setString( 54, MATDischargealive.toString().replace(".0", ""));
        conn.pst.setString( 55, MATReferral.toString().replace(".0", ""));
        conn.pst.setString( 56, MATNeoNatalD.toString().replace(".0", ""));
        conn.pst.setString( 57, MATMaternalD.toString().replace(".0", ""));
        conn.pst.setString( 58, MATAPHAlive.toString().replace(".0", ""));
        conn.pst.setString( 59, MATAPHDead.toString().replace(".0", ""));
        conn.pst.setString( 60, MATPPHAlive.toString().replace(".0", ""));
//        System.out.println("break 6");
        conn.pst.setString( 61, MATPPHDead.toString().replace(".0", ""));
        conn.pst.setString( 62, MATEclampAlive.toString().replace(".0", ""));
        conn.pst.setString( 63, MATEclampDead.toString().replace(".0", ""));
        conn.pst.setString( 64, MATRupUtAlive.toString().replace(".0", ""));
        conn.pst.setString( 65, MATRupUtDead.toString().replace(".0", ""));
        conn.pst.setString( 66, MATObstrLaborAlive.toString().replace(".0", ""));
        conn.pst.setString( 67, MATObstrLaborDead.toString().replace(".0", ""));
        conn.pst.setString( 68, MATSepsisAlive.toString().replace(".0", ""));
        conn.pst.setString( 69, MATSepsisDead.toString().replace(".0", ""));
        conn.pst.setString( 70, VCTClient_Couns_CM.toString().replace(".0", ""));
//        System.out.println("break 7");
        conn.pst.setString( 71, VCTClient_Couns_CF.toString().replace(".0", ""));
        conn.pst.setString( 72, VCTClient_Couns_AM.toString().replace(".0", ""));
        conn.pst.setString( 73, VCTClient_Couns_AF.toString().replace(".0", ""));
        conn.pst.setString( 74, VCTClient_Couns_TOT.toString().replace(".0", ""));
        conn.pst.setString( 75, VCTClient_Tested_CM.toString().replace(".0", ""));
        conn.pst.setString( 76, VCTClient_Tested_CF.toString().replace(".0", ""));
        conn.pst.setString( 77, VCTClient_Tested_AM.toString().replace(".0", ""));
        conn.pst.setString( 78, VCTClient_Tested_AF.toString().replace(".0", ""));
        conn.pst.setString( 79, VCTClient_Tested_TOT.toString().replace(".0", ""));
        conn.pst.setString( 80, VCTClient_HIV_CM.toString().replace(".0", ""));
//                System.out.println("break 8");
        conn.pst.setString( 81, VCTClient_HIV_CF.toString().replace(".0", ""));
        conn.pst.setString( 82, VCTClient_HIV_AM.toString().replace(".0", ""));
        conn.pst.setString( 83, VCTClient_HIV_AF.toString().replace(".0", ""));
        conn.pst.setString( 84, VCTClient_HIV_TOT.toString().replace(".0", ""));
        conn.pst.setString( 85, VCTPartner_Couns_TOT.toString().replace(".0", ""));
        conn.pst.setString( 86, VCTPartner_Tested_TOT.toString().replace(".0", ""));
        conn.pst.setString( 87, VCTPartner_HIV_TOT.toString().replace(".0", ""));
        conn.pst.setString( 88, VCTPartner_Disc_TOT.toString().replace(".0", ""));
        conn.pst.setString( 89, DTCA_Couns_In_CM.toString().replace(".0", ""));
        conn.pst.setString( 90, DTCA_Couns_In_CF.toString().replace(".0", ""));
//        System.out.println("break 9");
        conn.pst.setString( 91, DTCA_Couns_In_AM.toString().replace(".0", ""));
        conn.pst.setString( 92, DTCA_Couns_In_AF.toString().replace(".0", ""));
        conn.pst.setString( 93, DTCA_Couns_In_Tot.toString().replace(".0", ""));
        conn.pst.setString( 94, DTCA_Couns_Out_CM.toString().replace(".0", ""));
        conn.pst.setString( 95, DTCA_Couns_Out_CF.toString().replace(".0", ""));
        conn.pst.setString( 96, DTCA_Couns_Out_AM.toString().replace(".0", ""));
        conn.pst.setString( 97, DTCA_Couns_Out_AF.toString().replace(".0", ""));
        conn.pst.setString( 98, DTCA_Couns_Out_Tot.toString().replace(".0", ""));
        conn.pst.setString( 99, DTCB_Test_In_CM.toString().replace(".0", ""));
        conn.pst.setString( 100, DTCB_Test_In_CF.toString().replace(".0", ""));
//        System.out.println("break 10");
        conn.pst.setString( 101, DTCB_Test_In_AM.toString().replace(".0", ""));
        conn.pst.setString( 102, DTCB_Test_In_AF.toString().replace(".0", ""));
        conn.pst.setString( 103, DTCB_Test_In_Tot.toString().replace(".0", ""));
        conn.pst.setString( 104, DTCB_Test_Out_CM.toString().replace(".0", ""));
        conn.pst.setString( 105, DTCB_Test_Out_CF.toString().replace(".0", ""));
        conn.pst.setString( 106, DTCB_Test_Out_AM.toString().replace(".0", ""));
        conn.pst.setString( 107, DTCB_Test_Out_AF.toString().replace(".0", ""));
        conn.pst.setString( 108, DTCB_Test_Out_Tot.toString().replace(".0", ""));
        conn.pst.setString( 109, DTCC_HIV_In_CM.toString().replace(".0", ""));
        conn.pst.setString( 110, DTCC_HIV_In_CF.toString().replace(".0", ""));
//        System.out.println("break 11");
        conn.pst.setString( 111, DTCC_HIV_In_AM.toString().replace(".0", ""));
        conn.pst.setString( 112, DTCC_HIV_In_AF.toString().replace(".0", ""));
        conn.pst.setString( 113, DTCC_HIV_In_Tot.toString().replace(".0", ""));
        conn.pst.setString( 114, DTCC_HIV_Out_CM.toString().replace(".0", ""));
        conn.pst.setString( 115, DTCC_HIV_Out_CF.toString().replace(".0", ""));
        conn.pst.setString( 116, DTCC_HIV_Out_AM.toString().replace(".0", ""));
        conn.pst.setString( 117, DTCC_HIV_Out_AF.toString().replace(".0", ""));
        conn.pst.setString( 118, DTCC_HIV_Out_Tot.toString().replace(".0", ""));
        conn.pst.setString( 119,userid);
//        System.out.println("break 13");
//        conn.pst.setString( ,timestamp);
        conn.pst.setString( 120,isValidated);
        conn.pst.setString( 121,updatedBy);
//        conn.pst.setString( 122,updatedOn);
        conn.pst.setString( 122,yearmonth);
        conn.pst.setString( 123,isLocked);


      int res=conn.pst.executeUpdate();

    
    }
    
        
//  System.out.println("AT POSITION>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> :"+position);
        
//  if(position>100){
//      break;
//  }
  }      
      }   
      tableDescription+=" COMPLETED MERGING>>>>>>>------"+tableName+" <br><br>";
        session.setAttribute("mergingAccess", tableDescription);
             }
            }
               tableDescription+="......COMPLETED MERGING ALL TABLES.....<br>"; 
   session.setAttribute("mergingAccess", tableDescription);
            }
   catch (Exception e) { }
        
   
    
        
    } else {
//db = DatabaseBuilder.create(Database.FileFormat.V2007, dbFile);
//        System.out.println("missing database");
        System.out.println("new database should be created");
    }
} 
  catch (IOException e) {
e.printStackTrace();
System.out.println("Failed to do anything...................");
}
  
  
 

    } // end of else //
     
        
      response.sendRedirect("loadAccessDataBase.jsp");
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

    private String getFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        System.out.println("content-disposition header= "+contentDisp);
        String[] tokens = contentDisp.split(";");
      
        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf("=") + 2, token.length()-1);
            }
        }
        return contentDisp;
    }
}
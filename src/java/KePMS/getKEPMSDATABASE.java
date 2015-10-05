/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package KePMS;


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
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
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
@MultipartConfig(fileSizeThreshold=1024*1024*50, 	// 50 MB 
                 maxFileSize=1024*1024*500,      	// 500 MB
                 maxRequestSize=1024*1024*1000)   	// 1000 MB

public class getKEPMSDATABASE extends HttpServlet {
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

Object HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,HV0115,HV0116,HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,HV0243,HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341,HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,HV0354,HV0355,HV0370,HV0371,HV0372,HV0373,HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415,HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514,HV0601,HV0602,HV0605,HV0904,HV0905,HV0244;

int tableNo,year,month;
String userid, isValidated,updatedBy,updatedOn,yearmonth,isLocked, tableDescription;
   File file_source;
  HttpSession session;
  private static final long serialVersionUID = 205242440643911308L;
  private static final String UPLOAD_DIR = "uploads";

String full_path="";
 String fileName="";
 String id;
int rowCount=0;
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
 
 tableDescription+="Completed loading file....."+fileName+"  <br> Started merging data.... <br>";
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
             if(tableName.equalsIgnoreCase("MOH711")){
              tableNo++;position=0;
              session.setAttribute("currentTable", tableName);
              
               
                
                 System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>>table name is : "+tableName); 
             Table table = db.getTable(tableName);
                 System.out.println("data here >>>>>>>>>>>>");
//             position=0;
                 rowCount=table.getRowCount();
     
                 tableDescription+=tableNo+". Merging Table  : <b>"+tableName+"  >>>> <b>"+rowCount+"</b> rows to be merged.</b> <br>"+tableName+"currentPosition <br>";
          session.setAttribute("mergingAccess", tableDescription);
          
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
HV0101=HV0102=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=HV0115=HV0116=HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=HV0243=HV0301=HV0302=HV0303=HV0304=HV0305=HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=HV0336=HV0337=HV0338=HV0339=HV0340=HV0341=HV0342=HV0343=HV0344=HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=HV0351=HV0352=HV0353=HV0354=HV0355=HV0370=HV0371=HV0372=HV0373=HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415=HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=HV0509=HV0510=HV0511=HV0512=HV0513=HV0514=HV0601=HV0602=HV0605=HV0904=HV0905=HV0244="0";
 
Counter=Updated=DistrictID=SubPartnerID=Annee=Mois="0";         
        userid=isValidated=updatedBy=updatedOn=yearmonth=isLocked="";         
          
          position++;
//   System.out.println("Look ma, a row: " + row);
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
 
 String yr=(Annee.toString()).replace(".0", "");
 String mnth=(Mois.toString()).replace(".0", "");
 
  year=Integer.parseInt(yr);
  month=Integer.parseInt(mnth);

  if(month<4){year-=1;month+=9;}
  else{month-=3;}
//     System.out.println("year : "+year+" month : "+month);
//          System.out.println("date received : "+row.get("DateMAJ"));
  if(row.get("DateMAJ")!=null && !row.get("DateMAJ").equals("")){
         if(row.get("FPMicrolutN")!=null){FPMicrolutN=row.get("FPMicrolutN");}
         if(row.get("FPMicrolutR")!=null){FPMicrolutR=row.get("FPMicrolutR");}
         if(row.get("FPMicrolutT")!=null){FPMicrolutT=row.get("FPMicrolutT");}
         
         if(row.get("FPMicrogynonN")!=null){FPMicrogynonN=row.get("FPMicrogynonN");}
         if(row.get("FPMicrogynonR")!=null){FPMicrogynonR=row.get("FPMicrogynonR");}
         if(row.get("FPMicrogynonT")!=null){FPMicrogynonT=row.get("FPMicrogynonT");}
         
         if(row.get("FPINJECTIONSN")!=null){FPINJECTIONSN=row.get("FPINJECTIONSN");}
         if(row.get("FPINJECTIONSR")!=null){FPINJECTIONSR=row.get("FPINJECTIONSR");}
         if(row.get("FPINJECTIONST")!=null){FPINJECTIONST=row.get("FPINJECTIONST");}
         
         if(row.get("FPIUCDN")!=null){FPIUCDN=row.get("FPIUCDN");}
         if(row.get("FPIUCDR")!=null){FPIUCDR=row.get("FPIUCDR");}
         if(row.get("FPIUCDT")!=null){FPIUCDT=row.get("FPIUCDT");}
         
         if(row.get("FPIMPLANTSN")!=null){FPIMPLANTSN=row.get("FPIMPLANTSN");}
         if(row.get("FPIMPLANTSR")!=null){FPIMPLANTSR=row.get("FPIMPLANTSR");}
         if(row.get("FPIMPLANTST")!=null){FPIMPLANTST=row.get("FPIMPLANTST");}
         
         if(row.get("FPBTLN")!=null){FPBTLN=row.get("FPBTLN");}
         if(row.get("FPBTLR")!=null){FPBTLR=row.get("FPBTLR");}
         if(row.get("FPBTLT")!=null){FPBTLT=row.get("FPBTLT");}
         
         if(row.get("FPVasectomyN")!=null){FPVasectomyN=row.get("FPVasectomyN");}
         if(row.get("FPVasectomyR")!=null){FPVasectomyR=row.get("FPVasectomyR");}
         if(row.get("FPVasectomyT")!=null){FPVasectomyT=row.get("FPVasectomyT");}
         

         if(row.get("FPCONDOMSN")!=null){FPCONDOMSN=row.get("FPCONDOMSN");}
         if(row.get("FPCONDOMSR")!=null){FPCONDOMSR=row.get("FPCONDOMSR");}
         if(row.get("FPCONDOMST")!=null){FPCONDOMST=row.get("FPCONDOMST");}
         
         if(row.get("FPOTHERN")!=null){FPOTHERN=row.get("FPOTHERN");}
         if(row.get("FPOTHERR")!=null){FPOTHERR=row.get("FPOTHERR");}
         if(row.get("FPOTHERT")!=null){FPOTHERT=row.get("FPOTHERT");}
         
         if(row.get("FPCLIENTSN")!=null){FPCLIENTSN=row.get("FPCLIENTSN");}
         if(row.get("FPCLIENTSR")!=null){FPCLIENTSR=row.get("FPCLIENTSR");}
         if(row.get("FPCLIENTST")!=null){FPCLIENTST=row.get("FPCLIENTST");}
         
         if(row.get("FPIUCDRemoval")!=null){FPIUCDRemoval=row.get("FPIUCDRemoval");}
         if(row.get("FPIMPLANTSRemoval")!=null){FPIMPLANTSRemoval=row.get("FPIMPLANTSRemoval");}
         
//              System.out.println("PMTCT");
         if(row.get("PMCTA_1stVisit_ANC")!=null){PMCTA_1stVisit_ANC=row.get("PMCTA_1stVisit_ANC");}
         if(row.get("PMCTA_ReVisit_ANC")!=null){PMCTA_ReVisit_ANC=row.get("PMCTA_ReVisit_ANC");}
         if(row.get("PMCTANCClientsT")!=null){PMCTANCClientsT=row.get("PMCTANCClientsT");}
         if(row.get("PMCTHB7")!=null){PMCTHB7=row.get("PMCTHB7");}
         if(row.get("PMCTIPT1")!=null){PMCTIPT1=row.get("PMCTIPT1");}
         if(row.get("PMCTIPT2")!=null){PMCTIPT2=row.get("PMCTIPT2");}
         if(row.get("PMCTANCClients4")!=null){PMCTANCClients4=row.get("PMCTANCClients4");}
         if(row.get("PMCTITN")!=null){PMCTITN=row.get("PMCTITN");}
//              System.out.println("MATERNIRTY");
         if(row.get("MATNormalDelivery")!=null){MATNormalDelivery=row.get("MATNormalDelivery");}
         if(row.get("MATCSection")!=null){MATCSection=row.get("MATCSection");}
         if(row.get("MATBreech")!=null){MATBreech=row.get("MATBreech");}
         if(row.get("MATAssistedVag")!=null){MATAssistedVag=row.get("MATAssistedVag");}
         if(row.get("MATDeliveryT")!=null){MATDeliveryT=row.get("MATDeliveryT");}
         if(row.get("MATLiveBirth")!=null){MATLiveBirth=row.get("MATLiveBirth");}
         if(row.get("MATStillBirth")!=null){MATStillBirth=row.get("MATStillBirth");}
         if(row.get("MATWeight2500")!=null){MATWeight2500=row.get("MATWeight2500");}
         if(row.get("MATPreTerm")!=null){MATPreTerm=row.get("MATPreTerm");}
         if(row.get("MATDischargealive")!=null){MATDischargealive=row.get("MATDischargealive");}
         if(row.get("MATReferral")!=null){MATReferral=row.get("MATReferral");}
         if(row.get("MATNeoNatalD")!=null){MATNeoNatalD=row.get("MATNeoNatalD");}
         if(row.get("MATMaternalD")!=null){MATMaternalD=row.get("MATMaternalD");}
         if(row.get("MATAPHAlive")!=null){MATAPHAlive=row.get("MATAPHAlive");}
         if(row.get("MATAPHDead")!=null){MATAPHDead=row.get("MATAPHDead");}
         if(row.get("MATPPHAlive")!=null){MATPPHAlive=row.get("MATPPHAlive");}
         if(row.get("MATPPHDead")!=null){MATPPHDead=row.get("MATPPHDead");}
         if(row.get("MATEclampAlive")!=null){MATEclampAlive=row.get("MATEclampAlive");}
         if(row.get("MATEclampDead")!=null){MATEclampDead=row.get("MATEclampDead");}
         if(row.get("MATRupUtAlive")!=null){MATRupUtAlive=row.get("MATRupUtAlive");}
         if(row.get("MATRupUtDead")!=null){MATRupUtDead=row.get("MATRupUtDead");}
         if(row.get("MATObstrLaborAlive")!=null){MATObstrLaborAlive=row.get("MATObstrLaborAlive");}
         if(row.get("MATObstrLaborDead")!=null){MATObstrLaborDead=row.get("MATObstrLaborDead");}
         if(row.get("MATSepsisAlive")!=null){MATSepsisAlive=row.get("MATSepsisAlive");}
         if(row.get("MATSepsisDead")!=null){MATSepsisDead=row.get("MATSepsisDead");}
//         System.out.println("VCT");
         if(row.get("VCTClient_Couns_CM")!=null){VCTClient_Couns_CM=row.get("VCTClient_Couns_CM");}
         if(row.get("VCTClient_Couns_CF")!=null){VCTClient_Couns_CF=row.get("VCTClient_Couns_CF");}
         if(row.get("VCTClient_Couns_AM")!=null){VCTClient_Couns_AM=row.get("VCTClient_Couns_AM");}
         if(row.get("VCTClient_Couns_AF")!=null){VCTClient_Couns_AF=row.get("VCTClient_Couns_AF");}
         if(row.get("VCTClient_Couns_TOT")!=null){VCTClient_Couns_TOT=row.get("VCTClient_Couns_TOT");}
         
         if(row.get("VCTClient_Tested_CM")!=null){VCTClient_Tested_CM=row.get("VCTClient_Tested_CM");}
         if(row.get("VCTClient_Tested_CF")!=null){VCTClient_Tested_CF=row.get("VCTClient_Tested_CF");}
         if(row.get("VCTClient_Tested_AM")!=null){VCTClient_Tested_AM=row.get("VCTClient_Tested_AM");}
         if(row.get("VCTClient_Tested_AF")!=null){VCTClient_Tested_AF=row.get("VCTClient_Tested_AF");}
         if(row.get("VCTClient_Tested_TOT")!=null){VCTClient_Tested_TOT=row.get("VCTClient_Tested_TOT");}
         
         if(row.get("VCTClient_HIV_CM")!=null){VCTClient_HIV_CM=row.get("VCTClient_HIV_CM");}
         if(row.get("VCTClient_HIV_CF")!=null){VCTClient_HIV_CF=row.get("VCTClient_HIV_CF");}
         if(row.get("VCTClient_HIV_AM")!=null){VCTClient_HIV_AM=row.get("VCTClient_HIV_AM");}
         if(row.get("VCTClient_HIV_AF")!=null){VCTClient_HIV_AF=row.get("VCTClient_HIV_AF");}
         if(row.get("VCTClient_HIV_TOT")!=null){VCTClient_HIV_TOT=row.get("VCTClient_HIV_TOT");}
         if(row.get("VCTPartner_Couns_TOT")!=null){VCTPartner_Couns_TOT=row.get("VCTPartner_Couns_TOT");}
         if(row.get("VCTPartner_Tested_TOT")!=null){VCTPartner_Tested_TOT=row.get("VCTPartner_Tested_TOT");}
         if(row.get("VCTPartner_HIV_TOT")!=null){VCTPartner_HIV_TOT=row.get("VCTPartner_HIV_TOT");}
         if(row.get("VCTPartner_Disc_TOT")!=null){VCTPartner_Disc_TOT=row.get("VCTPartner_Disc_TOT");}
//         System.out.println("DTC A");
         if(row.get("DTCA_Couns_In_CM")!=null){DTCA_Couns_In_CM=row.get("DTCA_Couns_In_CM");}
         if(row.get("DTCA_Couns_In_CF")!=null){DTCA_Couns_In_CF=row.get("DTCA_Couns_In_CF");}
         if(row.get("DTCA_Couns_In_AM")!=null){DTCA_Couns_In_AM=row.get("DTCA_Couns_In_AM");}
         if(row.get("DTCA_Couns_In_AF")!=null){DTCA_Couns_In_AF=row.get("DTCA_Couns_In_AF");}
         if(row.get("DTCA_Couns_In_Tot")!=null){DTCA_Couns_In_Tot=row.get("DTCA_Couns_In_Tot");}
         if(row.get("DTCA_Couns_Out_CM")!=null){DTCA_Couns_Out_CM=row.get("DTCA_Couns_Out_CM");}
         if(row.get("DTCA_Couns_Out_CF")!=null){DTCA_Couns_Out_CF=row.get("DTCA_Couns_Out_CF");}
         if(row.get("DTCA_Couns_Out_AM")!=null){DTCA_Couns_Out_AM=row.get("DTCA_Couns_Out_AM");}
         if(row.get("DTCA_Couns_Out_AF")!=null){DTCA_Couns_Out_AF=row.get("DTCA_Couns_Out_AF");}
         if(row.get("DTCA_Couns_Out_Tot")!=null){DTCA_Couns_Out_Tot=row.get("DTCA_Couns_Out_Tot");}
//         System.out.println("DTC B");
         if(row.get("DTCB_Test_In_CM")!=null){DTCB_Test_In_CM=row.get("DTCB_Test_In_CM");}
         if(row.get("DTCB_Test_In_CF")!=null){DTCB_Test_In_CF=row.get("DTCB_Test_In_CF");}
         if(row.get("DTCB_Test_In_AM")!=null){DTCB_Test_In_AM=row.get("DTCB_Test_In_AM");}
         if(row.get("DTCB_Test_In_AF")!=null){DTCB_Test_In_AF=row.get("DTCB_Test_In_AF");}
         if(row.get("DTCB_Test_In_Tot")!=null){DTCB_Test_In_Tot=row.get("DTCB_Test_In_Tot");}
         if(row.get("DTCB_Test_Out_CM")!=null){DTCB_Test_Out_CM=row.get("DTCB_Test_Out_CM");}
         if(row.get("DTCB_Test_Out_CF")!=null){DTCB_Test_Out_CF=row.get("DTCB_Test_Out_CF");}
         if(row.get("DTCB_Test_Out_AM")!=null){DTCB_Test_Out_AM=row.get("DTCB_Test_Out_AM");}
         if(row.get("DTCB_Test_Out_AF")!=null){DTCB_Test_Out_AF=row.get("DTCB_Test_Out_AF");}
         if(row.get("DTCB_Test_Out_Tot")!=null){DTCB_Test_Out_Tot=row.get("DTCB_Test_Out_Tot");}
//         System.out.println("DTC C");
        if(row.get("DTCC_HIV_In_CM")!=null){DTCC_HIV_In_CM=row.get("DTCC_HIV_In_CM");}
        if(row.get("DTCC_HIV_In_CF")!=null){DTCC_HIV_In_CF=row.get("DTCC_HIV_In_CF");}
        if(row.get("DTCC_HIV_In_AM")!=null){DTCC_HIV_In_AM=row.get("DTCC_HIV_In_AM");}
        if(row.get("DTCC_HIV_In_AF")!=null){DTCC_HIV_In_AF= row.get("DTCC_HIV_In_AF");}
        if(row.get("DTCC_HIV_In_Tot")!=null){DTCC_HIV_In_Tot=row.get("DTCC_HIV_In_Tot");}
        
        if(row.get("DTCC_HIV_Out_CM")!=null){DTCC_HIV_Out_CM=row.get("DTCC_HIV_Out_CM");}
        if(row.get("DTCC_HIV_Out_CF")!=null){DTCC_HIV_Out_CF=row.get("DTCC_HIV_Out_CF");}
        if(row.get("DTCC_HIV_Out_AM")!=null){DTCC_HIV_Out_AM=row.get("DTCC_HIV_Out_AM");}
        if(row.get("DTCC_HIV_Out_AF")!=null){DTCC_HIV_Out_AF=row.get("DTCC_HIV_Out_AF");}
        if(row.get("DTCC_HIV_Out_Tot")!=null){DTCC_HIV_Out_Tot=row.get("DTCC_HIV_Out_Tot");}
             
        Userid="0";
id=year+"_"+month+"_"+SubPartnerID.toString().replace(".0", "");
      
       userid="joelData";
       isValidated="1";
       updatedBy="";
       updatedOn="";
       
               if(month<10){
       yearmonth=year+"0"+month;
               }
               else{
              yearmonth=year+""+month;     
               }
       isLocked="0";
//          System.out.println("id Is : "+id);
       session.removeAttribute("positionAccess");
        session.setAttribute("positionAccess","<i>"+position+"</i> out of <i>"+rowCount+"</i>");    
//        System.out.println("at pos : "+session.getAttribute("positionAccess"));
        
    String checker="SELECT id FROM moh711 WHERE id='"+id+"'";
    conn.rs=conn.st.executeQuery(checker);
    if(conn.rs.next()==true){
//        System.out.println("data already exist................."+position);   
    }
    else{
//       add as a new entry
//        System.out.println("add as  new entry>>>>>>>>>>>"+position+" id is : "+id);
        String inserter=" INSERT INTO moh711 (ID,SubPartnerID,Annee,Mois,FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,FPINJECTIONST,FPIUCDN,FPIUCDT,FPIUCDR,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR,FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection,MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead,VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT,DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM,DTCC_HIV_In_AF,DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot,userid,isValidated,updatedBy,yearmonth,isLocked) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
       
        conn.pst.setString(1 ,id);
        conn.pst.setString(2 , SubPartnerID.toString().replace(".0", ""));
        conn.pst.setInt(3 , year);
        conn.pst.setInt(4 , month);
        conn.pst.setString(5 , FPMicrolutN.toString().replace(".0", ""));
        conn.pst.setString(6 , FPMicrolutR.toString().replace(".0", ""));
        conn.pst.setString(7 , FPMicrolutT.toString().replace(".0", ""));
        conn.pst.setString(8 , FPMicrogynonN.toString().replace(".0", ""));
        conn.pst.setString( 9, FPMicrogynonR.toString().replace(".0", ""));
        conn.pst.setString(10 , FPMicrogynonT.toString().replace(".0", ""));
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
        conn.pst.setString( 21, FPBTLR.toString().replace(".0", ""));
        conn.pst.setString( 22, FPBTLT.toString().replace(".0", ""));
        conn.pst.setString( 23, FPVasectomyN.toString().replace(".0", ""));
        conn.pst.setString( 24, FPVasectomyR.toString().replace(".0", ""));
        conn.pst.setString( 25, FPVasectomyT.toString().replace(".0", ""));
        conn.pst.setString( 26, FPCONDOMSN.toString().replace(".0", ""));
        conn.pst.setString( 27, FPCONDOMSR.toString().replace(".0", ""));
        conn.pst.setString( 28, FPCONDOMST.toString().replace(".0", ""));
        conn.pst.setString( 29, FPOTHERN.toString().replace(".0", ""));
        conn.pst.setString( 30, FPOTHERR.toString().replace(".0", ""));
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
             
             else if(tableName.equalsIgnoreCase("MOH731")){
              tableNo++;position=0;
              session.setAttribute("currentTable", tableName);
              
//                 tableDescription+=tableNo+". Merging Table  : <b>"+tableName+"</b> <br>";
                  
//                 System.out.println("<<<<<<<<<<<<<>>>>>>>>>>>>>>>>table name is : "+tableName); 
             Table table = db.getTable(tableName);
//             position=0;
                   rowCount=table.getRowCount();
                   tableDescription+=tableNo+". Merging Table  : <b>"+tableName+"</b>  >>>> <b>"+rowCount+"</b> rows to be merged. <br>"+tableName+"currentPosition <br>";
       session.setAttribute("mergingAccess", tableDescription);
                   for(Row row : table) {
HV0101=HV0102=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=HV0115=HV0116=HV0201=
        HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=HV0214=HV0215=HV0216=
        HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=HV0230=HV0231=HV0232=HV0233=
        HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=HV0243=HV0301=HV0302=HV0303=HV0304=HV0305=
        HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=
        HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=
        HV0336=HV0337=HV0338=HV0339=HV0340=HV0341=HV0342=HV0343=HV0344=HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=
        HV0351=HV0352=HV0353=HV0354=HV0355=HV0370=HV0371=HV0372=HV0373=HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=
        HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415=HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=
        HV0509=HV0510=HV0511=HV0512=HV0513=HV0514=HV0601=HV0602=HV0605=HV0904=HV0905=HV0244="0";
 
Counter=Updated=DistrictID=SubPartnerID=Annee=Mois="0";         
        userid=isValidated=updatedBy=updatedOn=yearmonth=isLocked="";         
          
          position++;month=0;
          
//  Counter = row.get("Counter");
//  Updated = row.get("Updated");
  DistrictID = row.get("DistrictID");
  SubPartnerID = row.get("SubPartnerID");
  Annee = row.get("Annee");
  Mois = row.get("Mois");
  
 String yr=(Annee.toString()).replace(".0", "");
 String mnth=(Mois.toString()).replace(".0", "");
 
  year=Integer.parseInt(yr);
  month=Integer.parseInt(mnth);
        
  if(month<4){year-=1;month+=9;}
  else{month-=3;}
//    System.out.println("year : "+year+" month : "+month+" date maj : "+row.get("DateMAJ")+" facil id : "+SubPartnerID);
  if(row.get("DateMAJ")!=null && !row.get("DateMAJ").equals("")){
       if(row.get("HV0101")!=null){HV0101=row.get("HV0101");}
         if(row.get("HV0102")!=null){HV0102=row.get("HV0102");}
         if(row.get("HV0103")!=null){HV0103=row.get("HV0103");}
         if(row.get("HV0105")!=null){HV0105=row.get("HV0105");}
         if(row.get("HV0106")!=null){HV0106=row.get("HV0106");}
         if(row.get("HV0107")!=null){HV0107=row.get("HV0107");}
         if(row.get("HV0108")!=null){HV0108=row.get("HV0108");}
         if(row.get("HV0109")!=null){HV0109=row.get("HV0109");}
         if(row.get("HV0110")!=null){HV0110=row.get("HV0110");}
         if(row.get("HV0111")!=null){HV0111=row.get("HV0111");}
         if(row.get("HV0112")!=null){HV0112=row.get("HV0112");}
         if(row.get("HV0113")!=null){HV0113=row.get("HV0113");}
         if(row.get("HV0114")!=null){HV0114=row.get("HV0114");}
         if(row.get("HV0115")!=null){HV0115=row.get("HV0115");}
         if(row.get("HV0116")!=null){HV0116=row.get("HV0116");}

         if(row.get("HV0201")!=null){HV0201=row.get("HV0201");}
         if(row.get("HV0202")!=null){HV0202=row.get("HV0202");}
         if(row.get("HV0203")!=null){HV0203=row.get("HV0203");}
         if(row.get("HV0204")!=null){HV0204=row.get("HV0204");}
         if(row.get("HV0205")!=null){HV0205=row.get("HV0205");}
         if(row.get("HV0206")!=null){HV0206=row.get("HV0206");}
         if(row.get("HV0207")!=null){HV0207=row.get("HV0207");}
         if(row.get("HV0208")!=null){HV0208=row.get("HV0208");}
         if(row.get("HV0209")!=null){HV0209=row.get("HV0209");}
         if(row.get("HV0210")!=null){HV0210=row.get("HV0210");}
         if(row.get("HV0211")!=null){HV0211=row.get("HV0211");}
         if(row.get("HV0212")!=null){HV0212=row.get("HV0212");}
         if(row.get("HV0213")!=null){HV0213=row.get("HV0213");}
         if(row.get("HV0214")!=null){HV0214=row.get("HV0214");}
         if(row.get("HV0215")!=null){HV0215=row.get("HV0215");}
         if(row.get("HV0216")!=null){HV0216=row.get("HV0216");}
         if(row.get("HV0217")!=null){HV0217=row.get("HV0217");}
         if(row.get("HV0218")!=null){HV0218=row.get("HV0218");}
         if(row.get("HV0219")!=null){HV0219=row.get("HV0219");}
         if(row.get("HV0220")!=null){HV0220=row.get("HV0220");}
         if(row.get("HV0221")!=null){HV0221=row.get("HV0221");}
         if(row.get("HV0224")!=null){HV0224=row.get("HV0224");}
         if(row.get("HV0225")!=null){HV0225=row.get("HV0225");}
         if(row.get("HV0226")!=null){HV0226=row.get("HV0226");}
         if(row.get("HV0227")!=null){HV0227=row.get("HV0227");}
         if(row.get("HV0228")!=null){HV0228=row.get("HV0228");}
         if(row.get("HV0229")!=null){HV0229=row.get("HV0229");}
         if(row.get("HV0230")!=null){HV0230=row.get("HV0230");}
         if(row.get("HV0231")!=null){HV0231=row.get("HV0231");}
         if(row.get("HV0232")!=null){HV0232=row.get("HV0232");}
         if(row.get("HV0233")!=null){HV0233=row.get("HV0233");}
         if(row.get("HV0234")!=null){HV0234=row.get("HV0234");}
         if(row.get("HV0235")!=null){HV0235=row.get("HV0235");}
         if(row.get("HV0236")!=null){HV0236=row.get("HV0236");}
         if(row.get("HV0237")!=null){HV0237=row.get("HV0237");}
         if(row.get("HV0238")!=null){HV0238=row.get("HV0238");}
         if(row.get("HV0239")!=null){HV0239=row.get("HV0239");}
         if(row.get("HV0240")!=null){HV0240=row.get("HV0240");}
         if(row.get("HV0241")!=null){HV0241=row.get("HV0241");}
         if(row.get("HV0242")!=null){HV0242=row.get("HV0242");}
         if(row.get("HV0243")!=null){HV0243=row.get("HV0243");}
         if(row.get("HV0244")!=null){HV0244=row.get("HV0244");}
         
         if(row.get("HV0301")!=null){HV0301=row.get("HV0301");}
         if(row.get("HV0302")!=null){HV0302=row.get("HV0302");}
         if(row.get("HV0303")!=null){HV0303=row.get("HV0303");}
         if(row.get("HV0304")!=null){HV0304=row.get("HV0304");}
         if(row.get("HV0305")!=null){HV0305=row.get("HV0305");}
         if(row.get("HV0306")!=null){HV0306=row.get("HV0306");}
         if(row.get("HV0307")!=null){HV0307=row.get("HV0307");}
         if(row.get("HV0308")!=null){HV0308=row.get("HV0308");}
         if(row.get("HV0309")!=null){HV0309=row.get("HV0309");}
         if(row.get("HV0310")!=null){HV0310=row.get("HV0310");}
         if(row.get("HV0311")!=null){HV0311=row.get("HV0311");}
         if(row.get("HV0312")!=null){HV0312=row.get("HV0312");}
         if(row.get("HV0313")!=null){HV0313=row.get("HV0313");}
         if(row.get("HV0314")!=null){HV0314=row.get("HV0314");}
         if(row.get("HV0315")!=null){HV0315=row.get("HV0315");}
         if(row.get("HV0316")!=null){HV0316=row.get("HV0316");}
         if(row.get("HV0317")!=null){HV0317=row.get("HV0317");}
         if(row.get("HV0318")!=null){HV0318=row.get("HV0318");}
         if(row.get("HV0319")!=null){HV0319=row.get("HV0319");}
         if(row.get("HV0320")!=null){HV0320=row.get("HV0320");}
         if(row.get("HV0321")!=null){HV0321=row.get("HV0321");}
         if(row.get("HV0322")!=null){HV0322=row.get("HV0322");}
         if(row.get("HV0323")!=null){HV0323=row.get("HV0323");}
         if(row.get("HV0324")!=null){HV0324=row.get("HV0324");}
         if(row.get("HV0325")!=null){HV0325=row.get("HV0325");}
         if(row.get("HV0326")!=null){HV0326=row.get("HV0326");}
         if(row.get("HV0327")!=null){HV0327=row.get("HV0327");}
         if(row.get("HV0328")!=null){HV0328=row.get("HV0328");}
         if(row.get("HV0329")!=null){HV0329=row.get("HV0329");}
         if(row.get("HV0330")!=null){HV0330=row.get("HV0330");}
         if(row.get("HV0331")!=null){HV0331=row.get("HV0331");}
         if(row.get("HV0332")!=null){HV0332=row.get("HV0332");}
         if(row.get("HV0333")!=null){HV0333=row.get("HV0333");}
         if(row.get("HV0334")!=null){HV0334=row.get("HV0334");}
         if(row.get("HV0335")!=null){HV0335=row.get("HV0335");}
         if(row.get("HV0336")!=null){HV0336=row.get("HV0336");}
         if(row.get("HV0337")!=null){HV0337=row.get("HV0337");}
         if(row.get("HV0338")!=null){HV0338=row.get("HV0338");}
         if(row.get("HV0339")!=null){HV0339=row.get("HV0339");}
         if(row.get("HV0340")!=null){HV0340=row.get("HV0340");}
         if(row.get("HV0341")!=null){HV0341=row.get("HV0341");}
         if(row.get("HV0342")!=null){HV0342=row.get("HV0342");}
         if(row.get("HV0343")!=null){HV0343=row.get("HV0343");}
         if(row.get("HV0344")!=null){HV0344=row.get("HV0344");}
         if(row.get("HV0345")!=null){HV0345=row.get("HV0345");}
         if(row.get("HV0346")!=null){HV0346=row.get("HV0346");}
         if(row.get("HV0347")!=null){HV0347=row.get("HV0347");}
        if(row.get("HV0348")!=null){HV0348=row.get("HV0348");}
        if(row.get("HV0349")!=null){HV0349=row.get("HV0349");}
        if(row.get("HV0350")!=null){HV0350=row.get("HV0350");}
        if(row.get("HV0351")!=null){HV0351= row.get("HV0351");}
        if(row.get("HV0352")!=null){HV0352=row.get("HV0352");}
        
        if(row.get("HV0353")!=null){HV0353=row.get("HV0353");}
        if(row.get("HV0354")!=null){HV0354=row.get("HV0354");}
        if(row.get("HV0355")!=null){HV0355=row.get("HV0355");}
        if(row.get("HV0370")!=null){HV0370=row.get("HV0370");}
        if(row.get("HV0371")!=null){HV0371=row.get("HV0371");}
        if(row.get("HV0372")!=null){HV0372=row.get("HV0372");}
         if(row.get("HV0373")!=null){HV0373=row.get("HV0373");}

        if(row.get("HV0401")!=null){HV0401=row.get("HV0401");}
         if(row.get("HV0402")!=null){HV0402=row.get("HV0402");}
         if(row.get("HV0403")!=null){HV0403=row.get("HV0403");}
         if(row.get("HV0406")!=null){HV0406=row.get("HV0406");}
         if(row.get("HV0407")!=null){HV0407=row.get("HV0407");}
         if(row.get("HV0408")!=null){HV0408=row.get("HV0408");}
         if(row.get("HV0409")!=null){HV0409=row.get("HV0409");}
         if(row.get("HV0410")!=null){HV0410=row.get("HV0410");}
         if(row.get("HV0411")!=null){HV0411=row.get("HV0411");}
         if(row.get("HV0412")!=null){HV0412=row.get("HV0412");}
        if(row.get("HV0413")!=null){HV0413=row.get("HV0413");}
        if(row.get("HV0414")!=null){HV0414=row.get("HV0414");}
        if(row.get("HV0415")!=null){HV0415=row.get("HV0415");}
        
         if(row.get("HV0501")!=null){HV0501=row.get("HV0501");}
         if(row.get("HV0502")!=null){HV0502=row.get("HV0502");}
         if(row.get("HV0503")!=null){HV0503=row.get("HV0503");}
         if(row.get("HV0504")!=null){HV0504=row.get("HV0504");}
         if(row.get("HV0505")!=null){HV0505=row.get("HV0505");}
         if(row.get("HV0506")!=null){HV0506=row.get("HV0506");}
         if(row.get("HV0507")!=null){HV0507=row.get("HV0507");}
         if(row.get("HV0508")!=null){HV0508=row.get("HV0508");}
         if(row.get("HV0509")!=null){HV0509=row.get("HV0509");}
         if(row.get("HV0510")!=null){HV0510= row.get("HV0510");}
         if(row.get("HV0511")!=null){HV0511=row.get("HV0511");}
         if(row.get("HV0512")!=null){HV0512=row.get("HV0512");}
         if(row.get("HV0513")!=null){HV0513=row.get("HV0513");}
         if(row.get("HV0514")!=null){HV0514=row.get("HV0514");}
         if(row.get("HV0601")!=null){HV0601=row.get("HV0601");}
         if(row.get("HV0602")!=null){HV0602=row.get("HV0602");}
         if(row.get("HV0605")!=null){HV0605=row.get("HV0605");}
         if(row.get("HV0904")!=null){HV0904=row.get("HV0904");}
         if(row.get("HV0905")!=null){HV0905=row.get("HV0905");}
        
        Userid="0";

id=year+"_"+month+"_"+SubPartnerID.toString().replace(".0", "");
      
       userid="joelData";
       isValidated="1";
       updatedBy="";
       updatedOn="";
       
               if(month<10){
       yearmonth=year+"0"+month;
               }
               else{
              yearmonth=year+""+month;     
               }
       isLocked="0";
//          System.out.println("id Is : "+id);
       session.removeAttribute("positionAccess");
       session.setAttribute("positionAccess","<i>"+position+" out of "+rowCount+"</i>.");       
//        System.out.println("at pos : "+session.getAttribute("positionAccess"));
        
    String checker="SELECT id FROM moh731 WHERE id='"+id+"'";
    conn.rs=conn.st.executeQuery(checker);
    if(conn.rs.next()==true){
//        System.out.println("data already exist................."+position);   
    }
    else{
//       add as a new entry
//        System.out.println("add as  new entry>>>>>>>>>>>"+position+" id is : "+id);
        String inserter=" INSERT INTO moh731 (id,SubPartnerID,Annee,Mois,HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,HV0115,HV0116,HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,HV0243,HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341,HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,HV0354,HV0355,HV0370,HV0371,HV0372,HV0373,HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415,HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514,HV0601,HV0602,HV0605,HV0904,HV0905,HV0244,user_id,isValidated,yearmonth,updatedBy,isLocked) "
                + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn.pst=conn.conn.prepareStatement(inserter);
       
        conn.pst.setString(1 ,id);
        conn.pst.setString(2 , SubPartnerID.toString().replace(".0", ""));
        conn.pst.setInt(3 , year);
        conn.pst.setInt(4 , month);
        
        conn.pst.setString(5 , HV0101.toString().replace(".0", ""));
        conn.pst.setString(6 , HV0102.toString().replace(".0", ""));
        conn.pst.setString(7 , HV0103.toString().replace(".0", ""));
        conn.pst.setString(8 , HV0105.toString().replace(".0", ""));
        conn.pst.setString( 9, HV0106.toString().replace(".0", ""));
        conn.pst.setString(10 , HV0107.toString().replace(".0", ""));
        conn.pst.setString( 11, HV0108.toString().replace(".0", ""));
        conn.pst.setString( 12, HV0109.toString().replace(".0", ""));
        conn.pst.setString( 13, HV0110.toString().replace(".0", ""));
        conn.pst.setString( 14, HV0111.toString().replace(".0", ""));
        conn.pst.setString( 15, HV0112.toString().replace(".0", ""));
        conn.pst.setString( 16, HV0113.toString().replace(".0", ""));
        conn.pst.setString( 17, HV0114.toString().replace(".0", ""));
        conn.pst.setString( 18, HV0115.toString().replace(".0", ""));
        conn.pst.setString( 19, HV0116.toString().replace(".0", ""));
        conn.pst.setString( 20, HV0201.toString().replace(".0", ""));
        conn.pst.setString( 21, HV0202.toString().replace(".0", ""));
        conn.pst.setString( 22, HV0203.toString().replace(".0", ""));
        conn.pst.setString( 23, HV0204.toString().replace(".0", ""));
        conn.pst.setString( 24, HV0205.toString().replace(".0", ""));
        conn.pst.setString( 25, HV0206.toString().replace(".0", ""));
        conn.pst.setString( 26, HV0207.toString().replace(".0", ""));
        conn.pst.setString( 27, HV0208.toString().replace(".0", ""));
        conn.pst.setString( 28, HV0209.toString().replace(".0", ""));
        conn.pst.setString( 29, HV0210.toString().replace(".0", ""));
        conn.pst.setString( 30, HV0211.toString().replace(".0", ""));
        conn.pst.setString( 31, HV0212.toString().replace(".0", ""));
        conn.pst.setString( 32, HV0213.toString().replace(".0", ""));
        conn.pst.setString( 33, HV0214.toString().replace(".0", ""));
        conn.pst.setString( 34, HV0215.toString().replace(".0", ""));
        conn.pst.setString( 35, HV0216.toString().replace(".0", ""));
        conn.pst.setString( 36, HV0217.toString().replace(".0", ""));
        conn.pst.setString( 37, HV0218.toString().replace(".0", ""));
        conn.pst.setString( 38, HV0219.toString().replace(".0", ""));
        conn.pst.setString( 39, HV0220.toString().replace(".0", ""));
        conn.pst.setString( 40, HV0221.toString().replace(".0", ""));
        conn.pst.setString( 41, HV0224.toString().replace(".0", ""));
        conn.pst.setString( 42, HV0225.toString().replace(".0", ""));
        conn.pst.setString( 43, HV0226.toString().replace(".0", ""));
        conn.pst.setString( 44, HV0227.toString().replace(".0", ""));
        conn.pst.setString( 45, HV0228.toString().replace(".0", ""));
        conn.pst.setString( 46, HV0229.toString().replace(".0", ""));
        conn.pst.setString( 47, HV0230.toString().replace(".0", ""));
        conn.pst.setString( 48, HV0231.toString().replace(".0", ""));
        conn.pst.setString( 49, HV0232.toString().replace(".0", ""));
        conn.pst.setString( 50, HV0233.toString().replace(".0", ""));
//        System.out.println("break 5");
        conn.pst.setString( 51, HV0234.toString().replace(".0", ""));
        conn.pst.setString( 52, HV0235.toString().replace(".0", ""));
        conn.pst.setString( 53, HV0236.toString().replace(".0", ""));
        conn.pst.setString( 54, HV0237.toString().replace(".0", ""));
        conn.pst.setString( 55, HV0238.toString().replace(".0", ""));
        conn.pst.setString( 56, HV0239.toString().replace(".0", ""));
        conn.pst.setString( 57, HV0240.toString().replace(".0", ""));
        conn.pst.setString( 58, HV0241.toString().replace(".0", ""));
        conn.pst.setString( 59, HV0242.toString().replace(".0", ""));
        conn.pst.setString( 60, HV0243.toString().replace(".0", ""));
        conn.pst.setString( 61, HV0301.toString().replace(".0", ""));
        conn.pst.setString( 62, HV0302.toString().replace(".0", ""));
        conn.pst.setString( 63, HV0303.toString().replace(".0", ""));
        conn.pst.setString( 64, HV0304.toString().replace(".0", ""));
        conn.pst.setString( 65, HV0305.toString().replace(".0", ""));
        conn.pst.setString( 66, HV0306.toString().replace(".0", ""));
        conn.pst.setString( 67, HV0307.toString().replace(".0", ""));
        conn.pst.setString( 68, HV0308.toString().replace(".0", ""));
        conn.pst.setString( 69, HV0309.toString().replace(".0", ""));
        
        
        conn.pst.setString( 70, HV0310.toString().replace(".0", ""));
        conn.pst.setString( 71, HV0311.toString().replace(".0", ""));
        conn.pst.setString( 72, HV0312.toString().replace(".0", ""));
        conn.pst.setString( 73, HV0313.toString().replace(".0", ""));
        conn.pst.setString( 74, HV0314.toString().replace(".0", ""));
        conn.pst.setString( 75, HV0315.toString().replace(".0", ""));
        conn.pst.setString( 76, HV0316.toString().replace(".0", ""));
        conn.pst.setString( 77, HV0317.toString().replace(".0", ""));
        conn.pst.setString( 78, HV0318.toString().replace(".0", ""));
        conn.pst.setString( 79, HV0319.toString().replace(".0", ""));
        conn.pst.setString( 80, HV0320.toString().replace(".0", ""));
        conn.pst.setString( 81, HV0321.toString().replace(".0", ""));
        conn.pst.setString( 82, HV0322.toString().replace(".0", ""));
        conn.pst.setString( 83, HV0323.toString().replace(".0", ""));
        conn.pst.setString( 84, HV0324.toString().replace(".0", ""));
        conn.pst.setString( 85, HV0325.toString().replace(".0", ""));
        conn.pst.setString( 86, HV0326.toString().replace(".0", ""));
        conn.pst.setString( 87, HV0327.toString().replace(".0", ""));
        conn.pst.setString( 88, HV0328.toString().replace(".0", ""));
        conn.pst.setString( 89, HV0329.toString().replace(".0", ""));
        conn.pst.setString( 90, HV0330.toString().replace(".0", ""));
        conn.pst.setString( 91, HV0331.toString().replace(".0", ""));
        conn.pst.setString( 92, HV0332.toString().replace(".0", ""));
        conn.pst.setString( 93, HV0333.toString().replace(".0", ""));
        conn.pst.setString( 94, HV0334.toString().replace(".0", ""));
        conn.pst.setString( 95, HV0335.toString().replace(".0", ""));
        conn.pst.setString( 96, HV0336.toString().replace(".0", ""));
        conn.pst.setString( 97, HV0337.toString().replace(".0", ""));
        conn.pst.setString( 98, HV0338.toString().replace(".0", ""));
        conn.pst.setString( 99, HV0339.toString().replace(".0", ""));
        conn.pst.setString( 100, HV0340.toString().replace(".0", ""));
        conn.pst.setString( 101, HV0341.toString().replace(".0", ""));
        conn.pst.setString( 102, HV0342.toString().replace(".0", ""));
        conn.pst.setString( 103, HV0343.toString().replace(".0", ""));
        conn.pst.setString( 104, HV0344.toString().replace(".0", ""));
        conn.pst.setString( 105, HV0345.toString().replace(".0", ""));
        conn.pst.setString( 106, HV0346.toString().replace(".0", ""));
        conn.pst.setString( 107, HV0347.toString().replace(".0", ""));
        conn.pst.setString( 108, HV0348.toString().replace(".0", ""));
        conn.pst.setString( 109, HV0349.toString().replace(".0", ""));
        conn.pst.setString( 110, HV0350.toString().replace(".0", ""));
        conn.pst.setString( 111, HV0351.toString().replace(".0", ""));
        conn.pst.setString( 112, HV0352.toString().replace(".0", ""));
        conn.pst.setString( 113, HV0353.toString().replace(".0", ""));
        conn.pst.setString( 114, HV0354.toString().replace(".0", ""));
        conn.pst.setString( 115, HV0355.toString().replace(".0", ""));
        conn.pst.setString( 116, HV0370.toString().replace(".0", ""));
        conn.pst.setString( 117, HV0371.toString().replace(".0", ""));
        conn.pst.setString( 118, HV0372.toString().replace(".0", ""));
     
      
        conn.pst.setString( 119, HV0373.toString().replace(".0", ""));
        conn.pst.setString( 120, HV0401.toString().replace(".0", ""));
        conn.pst.setString( 121, HV0402.toString().replace(".0", ""));
        conn.pst.setString( 122, HV0403.toString().replace(".0", ""));
        conn.pst.setString( 123, HV0406.toString().replace(".0", ""));
        conn.pst.setString( 124, HV0407.toString().replace(".0", ""));
        conn.pst.setString( 125, HV0408.toString().replace(".0", ""));
        conn.pst.setString( 126, HV0409.toString().replace(".0", ""));
        conn.pst.setString( 127, HV0410.toString().replace(".0", ""));
        conn.pst.setString( 128, HV0411.toString().replace(".0", ""));
        conn.pst.setString( 129, HV0412.toString().replace(".0", ""));
        conn.pst.setString( 130, HV0413.toString().replace(".0", ""));
        conn.pst.setString( 131, HV0414.toString().replace(".0", ""));
        conn.pst.setString( 132, HV0415.toString().replace(".0", ""));
        conn.pst.setString( 133, HV0501.toString().replace(".0", ""));
        conn.pst.setString( 134, HV0502.toString().replace(".0", ""));
        conn.pst.setString( 135, HV0503.toString().replace(".0", ""));
        conn.pst.setString( 136, HV0504.toString().replace(".0", ""));
        conn.pst.setString( 137, HV0505.toString().replace(".0", ""));
        conn.pst.setString( 138, HV0506.toString().replace(".0", ""));
        conn.pst.setString( 139, HV0507.toString().replace(".0", ""));
        conn.pst.setString( 140, HV0508.toString().replace(".0", ""));
        conn.pst.setString( 141, HV0509.toString().replace(".0", ""));
        conn.pst.setString( 142, HV0510.toString().replace(".0", ""));
        conn.pst.setString( 143, HV0511.toString().replace(".0", ""));
        conn.pst.setString( 144, HV0512.toString().replace(".0", ""));
        conn.pst.setString( 145, HV0513.toString().replace(".0", ""));
        conn.pst.setString( 146, HV0514.toString().replace(".0", ""));
        conn.pst.setString( 147, HV0601.toString().replace(".0", ""));
        conn.pst.setString( 148, HV0602.toString().replace(".0", ""));
        conn.pst.setString( 149, HV0605.toString().replace(".0", ""));
        conn.pst.setString( 150, HV0904.toString().replace(".0", ""));
        conn.pst.setString( 151, HV0905.toString().replace(".0", ""));
        conn.pst.setString( 152, HV0244.toString().replace(".0", ""));
      
         conn.pst.setString( 153,userid);
//        conn.pst.setString( ,timestamp);
        conn.pst.setString( 154,isValidated);
        conn.pst.setString( 155,yearmonth);
        conn.pst.setString( 156,updatedBy);
//        conn.pst.setString( 122,updatedOn);
        conn.pst.setString( 157,isLocked);


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
   
     dbFile.delete();
  
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
     
        String sessionData=session.getAttribute("mergingAccess").toString();
        sessionData=sessionData.replace("MOH711currentPosition", "");
        sessionData=sessionData.replace("MOH731currentPosition", "");
        session.setAttribute("mergingAccess", sessionData);
        
      
        
      response.sendRedirect("loadKEPMS.jsp");
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

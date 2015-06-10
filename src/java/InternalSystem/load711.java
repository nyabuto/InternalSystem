/*
 * To change this license header= choose License Headers in Project Properties.
 * To change this template file= choose Tools | Templates
 * and open the template in the editor.
 */

package InternalSystem;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class load711 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId,year,month;

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
 String validitychecker="";
 int   validFP,invalidFP,totalFP,validMAT,invalidMAT,totalMAT,validHTC,invalidHTC,totalHTC;
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            dbConn conn = new dbConn();
        try {
           
            session=request.getSession();
            String FP_TAB="";
            String MCH_TAB="";
            String MATERNITY_TAB="";
            String VCT_TAB="";
            String DTC_TAB="";
               String enterdby="";
           if(session.getAttribute("forms_holder")!=null && !(session.getAttribute("forms_holder").toString().equals(","))){
           data="";
           if(session.getAttribute("year")!=null){        
   year=session.getAttribute("year").toString();
    }
      if(session.getAttribute("monthid")!=null){        
   month=session.getAttribute("monthid").toString();
    }
   
        if(session.getAttribute("facilityid")!=null){        
   facilityId=session.getAttribute("facilityid").toString();
    }
        if(session.getAttribute("subcountyid")!=null){        
   subcountyid=session.getAttribute("subcountyid").toString();
    }
    id=year+"_"+month+"_"+facilityId; 
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
// invalidPMTCTTXT="Unvalidated Form(s) : "+invalidPMTCT;
// invalidCARETXT="Unvalidated Form(s) : "+invalidCARE;
// invalidPEPTXT="Unvalidated Form(s) : "+invalidPEP;
   
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
    if(session.getAttribute("forms_holder").toString().contains(",FP,")){
        counter++;
        
//        int fpoccu=0; 
//        int fpoccu1=0; 
//int facilityfpcount=0; 
//// String counterFpcheck="SELECT count(FPMicrolutN) FROM moh711 where Annee ='"+year+"' and Mois='"+month+"' and (FPMicrolutN!=null ||FPMicrolutN!='')  ";
//String counterFpcheck="SELECT  * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where Annee ='"+year+"' and Mois='"+month+"'   and DistrictID='"+subcountyid+"' and (FPMicrolutN is not null  ||FPMicrolutN!='')  ";
// System.out.println(counterFpcheck); 
//conn.rs1 = conn.st1.executeQuery(counterFpcheck);
// if(conn.rs1.next()==true){
// fpoccu++;
//  }
// String counterFpcheck1="SELECT *  FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where Annee ='"+year+"' and Mois='"+month+"' and DistrictID='"+subcountyid+"' and   isValidated='0' ";
// conn.rs2 = conn.st2.executeQuery(counterFpcheck1);
// if(conn.rs2.next()==true){
// fpoccu1++;
//  }
// String countfacility="Select count(FP) from subpartnera where FP='1' and DistrictID='"+subcountyid+"'  ";
// System.out.println("_______"+countfacility);
//// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
// conn.rs3 = conn.st3.executeQuery(countfacility);
// if(conn.rs3.next()==true){
// facilityfpcount=conn.rs3.getInt(1);
// }
 
// System.out.println(fpoccu +" out of "+facilityfpcount);
          if(counter==1){
 fppane+=" <li class="+activeclass+"><a href=\"#tab_"+counter+"\" data-toggle=\"tab\">A: FAMILY PLANNING</a></li>\n" ;
    
      FP_TAB="<div class=\"tab-pane "+activeclass+"\" id=\"tab_"+counter+"\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">A: FAMILY PLANNING  </h4> <b style=\"color:yellow; font-family:cambria; text-align: left;  margin-left:25%; font-size:16px;\"> Record Counter: "+validFP+" out of "+expectedFP+  "&nbsp; "+invalidFPTXT+"</b>" +
                              "</div><div class=\"portlet-body form\">";
          }else{
 fppane+=" <li class=\"active\"><a href=\"#tab_1\" data-toggle=\"tab\">A: FAMILY PLANNING</a></li>\n" ;
    
      FP_TAB="<div class=\"tab-pane active\" id=\"tab_1\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">A: FAMILY PLANNING </h4><b style=\"color:yellow; font-family:cambria; text-align: left;  margin-left:25%; font-size:16px;\">Record Counter: "+validFP+" out of "+expectedFP+"  &nbsp;  "+invalidFPTXT+" </b>" +
                              "</div><div class=\"portlet-body form\">";}
    
    
    }else{fppane="";}
     if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
         
         counter++;
         
//           int pmctoccu=0; 
//           int pmctoccu1=0; 
//int facilitypmctcount=0; 
// String counterpmtctcheck="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and  Annee ='"+year+"' and Mois='"+month+"' and (PMCTA_1stVisit_ANC!=null ||PMCTA_1stVisit_ANC!='')  ";
// conn.rs1 = conn.st1.executeQuery(counterpmtctcheck);
// while(conn.rs1.next()){
// pmctoccu++;
//  }
// String counterpmtctcheck1="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and Annee ='"+year+"' and Mois='"+month+"' and isValidated='0'  ";
// conn.rs3 = conn.st3.executeQuery(counterpmtctcheck1);
// while(conn.rs3.next()){
// pmctoccu1++;
//  }
// String countpmctfacility="Select count(PMTCT) from subpartnera where PMTCT ='1'  and DistrictID='"+subcountyid+"'";
//// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
// conn.rs2 = conn.st2.executeQuery(countpmctfacility);
// if(conn.rs2.next()==true){
// facilitypmctcount=conn.rs2.getInt(1);
// }
// System.out.println("pmtct"+pmctoccu+" "+facilitypmctcount) ;  
         if(counter==1){
 pmctpane+="  <li class="+activeclass+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_"+counter+"\" data-toggle=\"tab\">B: MCH- ANC/PMCT</a></li>\n" ;
    
       MCH_TAB="<div class=\"tab-pane "+activeclass+"\" id=\"tab_"+counter+"\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">B: MCH - ANC/PMCT </h4> &nbsp <b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validPMTCT+" out of "+expectedPMTCT+" &nbsp; "+invalidPMTCTTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";
         }else{
    pmctpane+="  <li><a class=\"advance_form_with_chosen_element\" href=\"#tab_2\" data-toggle=\"tab\">B: MCH- ANC/PMCT &nbsp </a></li>\n" ;
    
       MCH_TAB="<div class=\"tab-pane \" id=\"tab_2\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">B: MCH - ANC/PMCT  &nbsp </h4><b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validPMTCT+" out of "+expectedPMTCT+" &nbsp; "+invalidPMTCTTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";      
         
         
         
         }
     
     }else{pmctpane="";}
      if(session.getAttribute("forms_holder").toString().contains(",Maternity,")){
//          int matoccu=0; 
//          int matoccu1=0; 
//int facilitymatcount=0; 
// String countermatcheck="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and  Annee ='"+year+"' and Mois='"+month+"' and (MATNormalDelivery!=null ||MATNormalDelivery!='')  ";
// conn.rs1 = conn.st1.executeQuery(countermatcheck);
// while(conn.rs1.next()){
// matoccu++;
//  }
// String countermatcheck1="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and  Annee ='"+year+"' and Mois='"+month+"' and isValidated='0'  ";
// conn.rs1 = conn.st1.executeQuery(countermatcheck1);
// while(conn.rs1.next()){
// matoccu1++;
//  }
// String countmatfacility="Select * from subpartnera where Maternity ='1' and  DistrictID='"+subcountyid+"' ";
//// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
// conn.rs2 = conn.st2.executeQuery(countmatfacility);
// while(conn.rs2.next()){
// facilitymatcount++;
// }
//   System.out.println("mat"+matoccu+" "+facilitymatcount) ;      
          
           counter++;
         if(counter==1){
       matpane+=" <li class="+activeclass+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_"+counter+"\" data-toggle=\"tab\">C:MATERNITY/ SAFE DELIVERIES</a></li>\n" ;
     
      
        MATERNITY_TAB="<div class=\"tab-pane "+activeclass+"\" id=\"tab_"+counter+"\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 >C: MATERNITY / SAFE DELIVERIESS  &nbsp  &nbsp </h4><b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validMAT+" out of "+expectedMAT+" &nbsp; "+invalidMATTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";}
         else{
 matpane+=" <li><a class=\"advance_form_with_chosen_element\" href=\"#tab_3\" data-toggle=\"tab\">C:MATERNITY/ SAFE DELIVERIES </a></li>\n" ;
     
      
        MATERNITY_TAB="<div class=\"tab-pane \" id=\"tab_3\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">C: MATERNITY / SAFE DELIVERIES &nbsp&nbsp</h4> <b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validMAT+" out of "+expectedMAT+" &nbsp; "+invalidMATTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";}
      
      
      }else{matpane="";}
       if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
            counter++;
            
            
//                int vctoccu=0; 
//                int vctoccu1=0; 
//                int dtcoccu=0; 
//                int dtcoccu1=0; 
//int facilityvctcount=0; 
// String countervctcheck="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and  Annee ='"+year+"' and Mois='"+month+"' and (VCTClient_Couns_CM!=null || VCTClient_Couns_CM!='')  ";
// conn.rs1 = conn.st1.executeQuery(countervctcheck);
// while(conn.rs1.next()){
// vctoccu++;
//  }
// String countervctcheck1="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and   Annee ='"+year+"' and Mois='"+month+"' and  isValidated='0' ";
// conn.rs1 = conn.st1.executeQuery(countervctcheck1);
// while(conn.rs1.next()){
// vctoccu1++;
//  }
// String counterdctcheck="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and  Annee ='"+year+"' and Mois='"+month+"' and (DTCA_Couns_In_CM!=null || DTCA_Couns_In_CM!='')  ";
// conn.rs1 = conn.st1.executeQuery(counterdctcheck);
// while(conn.rs1.next()){
// dtcoccu++;
//  }
// String counterdctcheck1="SELECT * FROM moh711 join subpartnera on moh711.SubPartnerID=subpartnera.SubPartnerID  where  DistrictID='"+subcountyid+"' and   Annee ='"+year+"' and Mois='"+month+"' and  isValidated='0' ";
// conn.rs1 = conn.st1.executeQuery(counterdctcheck1);
// while(conn.rs1.next()){
// dtcoccu1++;
//  }
// String countpmctfacility="Select * from subpartnera where HTC='1' and DistrictID='"+subcountyid+"' ";
//// String countfacility="Select * from subpartnera where FP='1' || PMTCT ='1' || Maternity='1' || HTC='1' ";
// conn.rs2 = conn.st2.executeQuery(countpmctfacility);
// while(conn.rs2.next()){
// facilityvctcount++;
// }System.out.println("pmtct"+vctoccu+" "+facilityvctcount +""+dtcoccu) ;  
         if(counter==1){
 htcpane+="  <li class="+activeclass+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_"+counter+"\" data-toggle=\"tab\">D: VCT</a></li>"
        + "<li><a class=\"advance_form_with_chosen_element\" href=\"#tab_2\" data-toggle=\"tab\" onclick=\"checkdispensary();\">E: DTC</a></li>\n" ;
    VCT_TAB="<div class=\"tab-pane "+activeclass+"\" id=\"tab_"+counter+"\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">D: VCT &nbsp  </h4><b  style=\"color:yellow; font-family:cambria; text-align: center; margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+" &nbsp; "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";
    DTC_TAB="<div class=\"tab-pane \" id=\"tab_2\"><div class=\"portlet box blue\">" +
                             "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">E: DTC  </h4> <b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+"   &nbsp;  "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">"; }
         else{
    
 htcpane+="  <li><a class=\"advance_form_with_chosen_element\" href=\"#tab_4\" data-toggle=\"tab\">D: VCT</a></li>"
        + "<li><a class=\"advance_form_with_chosen_element\" href=\"#tab_5\" data-toggle=\"tab\" onclick=\"checkdispensary();\">E DTC</a></li>\n" ;
    VCT_TAB="<div class=\"tab-pane \" id=\"tab_4\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\" ><h4 style=\"margin-left:20%;\">D: VCT </h4>  <b  style=\"color:yellow; font-family:cambria; text-align: center; margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+" &nbsp;   "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";
    DTC_TAB="<div class=\"tab-pane \" id=\"tab_5\"><div class=\"portlet box blue\">" +
                             "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">E: DTC   </h4>  <b  style=\"color:yellow; font-family:cambria; text-align: center; margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+" &nbsp;  "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">"; 
         }
       
       
       }
       else{htcpane="";}
String validatepane=" <li style=\"margin-left:150px;\" id=\"isValidated\"></li>                    </ul> </div>\n" ;
String mainpane="<div class=\"tab-content\" >";
    
   
    
      
        
     
                    
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
  // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";

   if(conn.rs.getString("PMCTA_1stVisit_ANC")!=null){PMCTA_1stVisit_ANC=conn.rs.getString("PMCTA_1stVisit_ANC");}else{PMCTA_1stVisit_ANC="";}
  if(conn.rs.getString("PMCTA_ReVisit_ANC")!=null){PMCTA_ReVisit_ANC=conn.rs.getString("PMCTA_ReVisit_ANC");}else{PMCTA_ReVisit_ANC="";}
  if(conn.rs.getString("PMCTANCClientsT")!=null){PMCTANCClientsT=conn.rs.getString("PMCTANCClientsT");}else{PMCTANCClientsT="";}
  if(conn.rs.getString("PMCTHB7")!=null){PMCTHB7=conn.rs.getString("PMCTHB7");}else{PMCTHB7="";}
  if(conn.rs.getString("PMCTIPT1")!=null){PMCTIPT1=conn.rs.getString("PMCTIPT1");}else{PMCTIPT1="";}
  if(conn.rs.getString("PMCTIPT2")!=null){PMCTIPT2=conn.rs.getString("PMCTIPT2");}else{PMCTIPT2="";}
  if(conn.rs.getString("PMCTANCClients4")!=null){PMCTANCClients4=conn.rs.getString("PMCTANCClients4");}else{PMCTANCClients4="";}
  if(conn.rs.getString("PMCTITN")!=null){PMCTITN=conn.rs.getString("PMCTITN");}else{PMCTITN="";}
  
  // maternity MATNormalDelivery=MATCSection=""MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATStillBirth=MATWeight2500=MATPreTerm=
  //MATDischargealive=MATReferral=MATNeoNatalD=
//MATMaternalD=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive
  //=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead="";
 
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
//      VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF
  //=VCTClient_Tested_AM=VCTClient_Tested_AF
//=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=
  //VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
//     
          
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
              + "<table frame=\"box\" cellpadding=\"2px\" style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"3\" class=\"form-actions\"><b>A: Family Planning </b></td>"
              + "<td class=\"form-actions\"> <b>NEW CLIENTS </b></td>"
              + "<td class=\"form-actions\"> <b>RE-VISITS </b></td>"
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
              + "<tr><td rowspan=\"2\" style=\"width:7px;\">1</td>"
              + "<td rowspan=\"2\">PILLS</td><td  >MICROLUT</td>"
              + "<td ><input type=\"text\" name=\"FPMicrolutN\" id=\"FPMicrolutN\" value=\""+FPMicrolutN+"\" onblur=\"autosave('FPMicrolutN');Microluttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPMicrolutR\" id=\"FPMicrolutR\" value=\""+FPMicrolutR+"\" onblur=\"autosave('FPMicrolutR');Microluttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPMicrolutT\" id=\"FPMicrolutT\" value=\""+FPMicrolutT+"\" onblur=\"autosave('FPMicrolutT');Microluttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
         
              + "<tr>"
//              + "<td>2</td>"
//              + "<td >PILLS</td>"
              + "<td  >MICROGYNON</td>"
              + "<td ><input type=\"text\" name=\"FPMicrogynonN\" id=\"FPMicrogynonN\" value=\""+FPMicrogynonN+"\" onblur=\"autosave('FPMicrogynonN');FPMicrogynon();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPMicrogynonR\" id=\"FPMicrogynonR\" value=\""+FPMicrogynonR+"\" onblur=\"autosave('FPMicrogynonR');FPMicrogynon();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPMicrogynonT\" id=\"FPMicrogynonT\" value=\""+FPMicrogynonT+"\" onblur=\"autosave('FPMicrogynonT');FPMicrogynon();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + "<tr><td>2</td>"
              + "<td >INJECTIONS</td>"
              + "<td  >INJECTIONS</td>"
              + "<td ><input type=\"text\" name=\"FPINJECTIONSN\" id=\"FPINJECTIONSN\"  value=\""+FPINJECTIONSN+"\" onblur=\"autosave('FPINJECTIONSN');FPInjectionsTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPINJECTIONSR\" id=\"FPINJECTIONSR\"  value=\""+FPINJECTIONSR+"\" onblur=\"autosave('FPINJECTIONSR');FPInjectionsTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPINJECTIONST\" id=\"FPINJECTIONST\"  value=\""+FPINJECTIONST+"\" onblur=\"autosave('FPINJECTIONST');FPInjectionsTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              
              + "<tr><td>3</td>"
              + "<td >I.U.C.D.</td>"
              + "<td  >Insertion</td>"
              + "<td ><input type=\"text\" name=\"FPIUCDN\" id=\"FPIUCDN\" value=\""+FPIUCDN+"\" onblur=\"autosave('FPIUCDN');FPIUCDTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPIUCDR\" id=\"FPIUCDR\" value=\""+FPIUCDR+"\" onblur=\"autosave('FPIUCDR');FPIUCDTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\"  tabindex='-1' readonly name=\"FPIUCDT\" id=\"FPIUCDT\" value=\""+FPIUCDT+"\" onblur=\"autosave('FPIUCDT');FPIUCDTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + "<tr><td>4</td>"
              + "<td >IMPLANTS</td><td  >Insertion</td>"
              + "<td ><input type=\"text\" name=\"FPIMPLANTSN\" id=\"FPIMPLANTSN\" value=\""+FPIMPLANTSN+"\" onblur=\"autosave('FPIMPLANTSN');FPIMPLANTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPIMPLANTSR\" id=\"FPIMPLANTSR\" value=\""+FPIMPLANTSR+"\" onblur=\"autosave('FPIMPLANTSR');FPIMPLANTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly  name=\"FPIMPLANTST\" id=\"FPIMPLANTST\" value=\""+FPIMPLANTST+"\" onblur=\"autosave('FPIMPLANTST');FPIMPLANTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + "<tr><td rowspan=\"2\">5</td>"
              + "<td  rowspan=\"2\">STERILIZATION</td><td  >B.T.L</td>"
              + "<td ><input type=\"text\" name=\"FPBTLN\" id=\"FPBTLN\" value=\""+FPBTLN+"\" onblur=\"autosave('FPBTLN');FPBTLTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPBTLR\" tabindex='-1' readonly id=\"FPBTLR\" value=\""+FPBTLR+"\" onblur=\"autosave('FPBTLR');FPBTLTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' name=\"FPBTLT\" readonly id=\"FPBTLT\" value=\""+FPBTLT+"\" onblur=\"autosave('FPBTLT');FPBTLTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td  >Vasectomy</td>"
              + "<td ><input type=\"text\" name=\"FPVasectomyN\" id=\"FPVasectomyN\" value=\""+FPVasectomyN+"\" onblur=\"autosave('FPVasectomyN');FPVasectomyTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPVasectomyR\" tabindex='-1' readonly  id=\"FPVasectomyR\" value=\""+FPVasectomyR+"\" onblur=\"autosave('FPVasectomyR');FPVasectomyTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPVasectomyT\" id=\"FPVasectomyT\" value=\""+FPVasectomyT+"\" onblur=\"autosave('FPVasectomyT');FPVasectomyTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                 + "<tr><td>6</td>"
              + "<td >CONDOMS</td><td  >No. of clients receiving</td>"
              + "<td ><input type=\"text\" name=\"FPCONDOMSN\" id=\"FPCONDOMSN\" value=\""+FPCONDOMSN+"\" onblur=\"autosave('FPCONDOMSN');FPCONDOMSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPCONDOMSR\" id=\"FPCONDOMSR\" value=\""+FPCONDOMSR+"\" onblur=\"autosave('FPCONDOMSR');FPCONDOMSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPCONDOMST\" id=\"FPCONDOMST\" value=\""+FPCONDOMST+"\" onblur=\"autosave('FPCONDOMST');FPCONDOMSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                 + "<tr><td>7</td>"
              + "<td >ALL OTHERS :(Specify)</td>"
              + "<td  ></td>"
              + "<td ><input type=\"text\" name=\"FPOTHERN\" id=\"FPOTHERN\" value=\""+FPOTHERN+"\" onblur=\"autosave('FPOTHERN');FPOTHERTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"FPOTHERR\" id=\"FPOTHERR\" value=\""+FPOTHERR+"\" onblur=\"autosave('FPOTHERR');FPOTHERTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\"tabindex='-1'  readonly name=\"FPOTHERT\" id=\"FPOTHERT\" value=\""+FPOTHERT+"\" onblur=\"autosave('FPOTHERT');FPOTHERTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
                 + "<tr><td>8</td>"
              + "<td >TOTAL NO. OF CLIENTS</td>"
              + "<td  ></td>"
              + "<td ><input type=\"text\"  tabindex='-1' readonly name=\"FPCLIENTSN\" id=\"FPCLIENTSN\" value=\""+FPCLIENTSN+"\" onblur=\"autosave('FPCLIENTSN');FPCLIENTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1' readonly name=\"FPCLIENTSR\" id=\"FPCLIENTSR\" value=\""+FPCLIENTSR+"\" onblur=\"autosave('FPCLIENTSR');FPCLIENTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" tabindex='-1'  readonly name=\"FPCLIENTST\" id=\"FPCLIENTST\" value=\""+FPCLIENTST+"\" onblur=\"autosave('FPCLIENTST');FPCLIENTSTotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                 + "<tr><td>9</td>"
              + "<td >REMOVALS</td>"
              + "<td  > IUCD </td>"
              + "<td ><input type=\"text\" name=\"FPIUCDRemoval\" id=\"FPIUCDRemoval\" value=\""+FPIUCDRemoval+"\" onblur=\"autosave('FPIUCDRemoval');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td >IMPLANTS</td>"
              + "<td ><input type=\"text\" name=\"FPIMPLANTSRemoval\" id=\"FPIMPLANTSRemoval\" value=\""+FPIMPLANTSRemoval+"\" onblur=\"autosave('FPIMPLANTSRemoval');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr></table></fieldset>" ;
                 FP_TAB+=FamilyPlanninng;
             // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";
pmct+="";
           pmct+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> B: MCH-ANC/PMTCT </b></legend>"
                   + "<table frame=\"box\" cellpadding=\"2px\" style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"2\" class=\"form-actions\"><b> </b></td>"
              + "<td class=\"form-actions\"> <b>NEW  </b></td>"
              + "<td class=\"form-actions\"> <b>RE-VISIT </b></td>"
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
                  + "<tr>"
              + "<td>1.</td><td >No of ANC Clients</td>"
                   + "<td ><input type=\"text\" name=\"PMCTA_1stVisit_ANC\" id=\"PMCTA_1stVisit_ANC\" value=\""+PMCTA_1stVisit_ANC+"\" onblur=\"autosave('PMCTA_1stVisit_ANC');anctotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"PMCTA_ReVisit_ANC\" id=\"PMCTA_ReVisit_ANC\" value=\""+PMCTA_ReVisit_ANC+"\" onblur=\"autosave('PMCTA_ReVisit_ANC');anctotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"PMCTANCClientsT\" readonly id=\"PMCTANCClientsT\" value=\""+PMCTANCClientsT+"\" onblur=\"autosave('PMCTANCClientsT');anctotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + " <td>2.</td>   <td > No of clients with Hb <7 g/dl </td>"
                   + "<td ><input type=\"text\" name=\"PMCTHB7\" id=\"PMCTHB7\" value=\""+PMCTHB7+"\" onblur=\"autosave('PMCTHB7');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "  <td>3.</td>  <td > No of clients given IPT (1st Dose) </td>"
                   + "<td ><input type=\"text\" name=\"PMCTIPT1\" id=\"PMCTIPT1\" value=\""+PMCTIPT1+"\" onblur=\"autosave('PMCTIPT1');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>4.</td> <td > No of clients given IPT (2nd Dose) </td>"
                   + "<td ><input type=\"text\" name=\"PMCTIPT2\" id=\"PMCTIPT2\" value=\""+PMCTIPT2+"\" onblur=\"autosave('PMCTIPT2');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "<td>5.</td>    <td > No of clients completed 4th Antenatal Visit </td>"
                   + "<td ><input type=\"text\" name=\"PMCTANCClients4\" id=\"PMCTANCClients4\" value=\""+PMCTANCClients4+"\" onblur=\"autosave('PMCTANCClients4');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "<tr>"
               + "   <td>6.</td> <td > No of ITNs distributed to ANC Clients </td>"
                   + "<td ><input type=\"text\" name=\"PMCTITN\" id=\"PMCTITN\" value=\""+PMCTITN+"\" onblur=\"autosave('PMCTITN');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
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
             "<table frame=\"box\" cellpadding=\"2px\" style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\"><tr>"
              + "<td colspan=\"2\" class=\"form-actions\"><b></b></td>"
              + "<td  colspan=\"2\" class=\"form-actions\"> <b>NUMBER  </b></td>"
               + "</tr>"
                  + "<tr>"
              + "<td>1.</td><td >Normal Deliveries </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATNormalDelivery\" id=\"MATNormalDelivery\" value=\""+MATNormalDelivery+"\" onblur=\"autosave('MATNormalDelivery');maternitytotals();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + " <td>2.</td>   <td > Caesarian Sections </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATCSection\" id=\"MATCSection\" value=\""+MATCSection+"\" onblur=\"autosave('MATCSection');maternitytotals();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "  <td>3.</td>  <td > Breech Delivery </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATBreech\" id=\"MATBreech\" value=\""+MATBreech+"\" onblur=\"autosave('MATBreech');maternitytotals();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>4.</td> <td > Assisted vaginal delivery </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATAssistedVag\" id=\"MATAssistedVag\" value=\""+MATAssistedVag+"\" onblur=\"autosave('MATAssistedVag');maternitytotals();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "<td>5.</td>    <td > TOTAL DELIVERIES</td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATDeliveryT\" id=\"MATDeliveryT\" value=\""+MATDeliveryT+"\" onblur=\"autosave('MATDeliveryT');\" readonly   tabindex='-1' maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               
               + "<tr>"
               + "   <td>6.</td> <td > Live Births </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATLiveBirth\" id=\"MATLiveBirth\" value=\""+MATLiveBirth+"\" onblur=\"autosave('MATLiveBirth');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>7.</td> <td > Still Births </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATStillBirth\" id=\"MATStillBirth\" value=\""+MATStillBirth+"\" onblur=\"autosave('MATStillBirth');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>8.</td> <td > Under Weight Babies (Weight below 2500 grams) </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATWeight2500\" id=\"MATWeight2500\" value=\""+MATWeight2500+"\" onblur=\"autosave('MATWeight2500');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>9.</td> <td > Pre-Term babies  </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATPreTerm\" id=\"MATPreTerm\" value=\""+MATPreTerm+"\" onblur=\"autosave('MATPreTerm');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>10.</td> <td > No of babies discharged alive </td>"
                   + "<td style=\"padding-right:20px;\" colspan=\"2\" ><input type=\"text\" name=\"MATDischargealive\" id=\"MATDischargealive\" value=\""+MATDischargealive+"\" onblur=\"autosave('MATDischargealive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>11.</td> <td > Referrals </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATReferral\" id=\"MATReferral\" value=\""+MATReferral+"\" onblur=\"autosave('MATReferral');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>12.</td> <td > Neonatal Deaths </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATNeoNatalD\" id=\"MATNeoNatalD\" value=\""+MATNeoNatalD+"\" onblur=\"autosave('MATNeoNatalD');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "   <td>13.</td> <td  > Maternal Deaths </td>"
                   + "<td  colspan=\"2\"><input type=\"text\" name=\"MATMaternalD\" id=\"MATMaternalD\" value=\""+MATMaternalD+"\" onblur=\"autosave('MATMaternalD');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
               + "    <td  colspan=\"2\" class=\"form-actions\"><b> Maternal Complications </b></td>"
                   + "<td  class=\"form-actions\"><b>Alive </b></td>"
                   + "<td class=\"form-actions\" ><b>Dead </b></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>14.</td> <td > A.P.H. (Ante Partum Haemorrhage) </td>"
                   + "<td ><input type=\"text\" name=\"MATAPHAlive\" id=\"MATAPHAlive\" value=\""+MATAPHAlive+"\" onblur=\"autosave('MATAPHAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATAPHDead\" id=\"MATAPHDead\" value=\""+MATAPHDead+"\" onblur=\"autosave('MATAPHDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
             + ""
              + "<tr>"
               + "   <td>15.</td> <td > P.P.H. (Post Partum Haemorrhage) </td>"
                   + "<td ><input type=\"text\" name=\"MATPPHAlive\" id=\"MATPPHAlive\" value=\""+MATPPHAlive+"\" onblur=\"autosave('MATPPHAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATPPHDead\" id=\"MATPPHDead\" value=\""+MATPPHDead+"\" onblur=\"autosave('MATPPHDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>16.</td> <td > Eclampsia</td>"
                   + "<td ><input type=\"text\" name=\"MATEclampAlive\" id=\"MATEclampAlive\" value=\""+MATEclampAlive+"\" onblur=\"autosave('MATEclampAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATEclampDead\" id=\"MATEclampDead\" value=\""+MATEclampDead+"\" onblur=\"autosave('MATEclampDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>17.</td> <td > Ruptured Uterus</td>"
                   + "<td ><input type=\"text\" name=\"MATRupUtAlive\" id=\"MATRupUtAlive\" value=\""+MATRupUtAlive+"\" onblur=\"autosave('MATRupUtAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATRupUtDead\" id=\"MATRupUtDead\" value=\""+MATRupUtDead+"\" onblur=\"autosave('MATRupUtDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>18.</td> <td > Obstructed Labour</td>"
                   + "<td ><input type=\"text\" name=\"MATObstrLaborAlive\" id=\"MATObstrLaborAlive\" value=\""+MATObstrLaborAlive+"\" onblur=\"autosave('MATObstrLaborAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATObstrLaborDead\" id=\"MATObstrLaborDead\" value=\""+MATObstrLaborDead+"\" onblur=\"autosave('MATObstrLaborDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
               + "   <td>19.</td> <td > Sepsis</td>"
                   + "<td ><input type=\"text\" name=\"MATSepsisAlive\" id=\"MATSepsisAlive\" value=\""+MATSepsisAlive+"\" onblur=\"autosave('MATSepsisAlive');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"MATSepsisDead\" id=\"MATSepsisDead\" value=\""+MATSepsisDead+"\" onblur=\"autosave('MATSepsisDead');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
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
             "<table frame=\"box\" cellpadding=\"2px\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
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
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_CF\" id=\"VCTClient_Couns_CF\" value=\""+VCTClient_Couns_CF+"\" onblur=\"autosave('VCTClient_Couns_CF');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_CM\" id=\"VCTClient_Couns_CM\" value=\""+VCTClient_Couns_CM+"\" onblur=\"autosave('VCTClient_Couns_CM');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_AF\" id=\"VCTClient_Couns_AF\" value=\""+VCTClient_Couns_AF+"\" onblur=\"autosave('VCTClient_Couns_AF');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_AM\" id=\"VCTClient_Couns_AM\" value=\""+VCTClient_Couns_AM+"\" onblur=\"autosave('VCTClient_Couns_AM');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" readonly  tabindex='-1' name=\"VCTClient_Couns_TOT\" id=\"VCTClient_Couns_TOT\" value=\""+VCTClient_Couns_TOT+"\" onblur=\"autosave('VCTClient_Couns_TOT');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>Tested</td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_CF\" id=\"VCTClient_Tested_CF\" value=\""+VCTClient_Tested_CF+"\" onblur=\"autosave('VCTClient_Tested_CF');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_CM\" id=\"VCTClient_Tested_CM\" value=\""+VCTClient_Tested_CM+"\" onblur=\"autosave('VCTClient_Tested_CM'); vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_AF\" id=\"VCTClient_Tested_AF\" value=\""+VCTClient_Tested_AF+"\" onblur=\"autosave('VCTClient_Tested_AF');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_AM\" id=\"VCTClient_Tested_AM\" value=\""+VCTClient_Tested_AM+"\" onblur=\"autosave('VCTClient_Tested_AM');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" readonly  tabindex='-1' name=\"VCTClient_Tested_TOT\" id=\"VCTClient_Tested_TOT\" value=\""+VCTClient_Tested_TOT+"\" onblur=\"autosave('VCTClient_Tested_TOT');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>HIV+</td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_CF\" id=\"VCTClient_HIV_CF\" value=\""+VCTClient_HIV_CF+"\" onblur=\"autosave('VCTClient_HIV_CF');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_CM\" id=\"VCTClient_HIV_CM\" value=\""+VCTClient_HIV_CM+"\" onblur=\"autosave('VCTClient_HIV_CM');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_AF\" id=\"VCTClient_HIV_AF\" value=\""+VCTClient_HIV_AF+"\" onblur=\"autosave('VCTClient_HIV_AF');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_AM\" id=\"VCTClient_HIV_AM\" value=\""+VCTClient_HIV_AM+"\" onblur=\"autosave('VCTClient_HIV_AM');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_TOT\" tabindex='-1' readonly id=\"VCTClient_HIV_TOT\" value=\""+VCTClient_HIV_TOT+"\" onblur=\"autosave('VCTClient_HIV_TOT');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
            + "<tr>"
              + "<td rowspan=\"4\">2.</td><td rowspan=\"4\">No of couples</td>"
                      + "<td>Counselled</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Couns_TOT\" id=\"VCTPartner_Couns_TOT\" value=\""+VCTPartner_Couns_TOT+"\" onblur=\"autosave('VCTPartner_Couns_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Tested</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Tested_TOT\" id=\"VCTPartner_Tested_TOT\" value=\""+VCTPartner_Tested_TOT+"\" onblur=\"autosave('VCTPartner_Tested_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Both HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_HIV_TOT\" id=\"VCTPartner_HIV_TOT\" value=\""+VCTPartner_HIV_TOT+"\" onblur=\"autosave('VCTPartner_HIV_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>with discordant HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Disc_TOT\" id=\"VCTPartner_Disc_TOT\" value=\""+VCTPartner_Disc_TOT+"\" onblur=\"autosave('VCTPartner_Disc_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
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
             "<table frame=\"box\" cellpadding=\"2px\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
                + "<tr>"
              + "<td rowspan=\"2\" colspan=\"3\" class=\"form-actions\" style=\"text-align:center;\"><b>I: DTC </b></td>"
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
              + "<td rowspan=\"2\">1.</td><td rowspan=\"2\" >No. Counselled</td>"
              + "<td>Outpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_CF\" id=\"DTCA_Couns_Out_CF\" value=\""+DTCA_Couns_Out_CF+"\" onblur=\"autosave('DTCA_Couns_Out_CF');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_CM\" id=\"DTCA_Couns_Out_CM\" value=\""+DTCA_Couns_Out_CM+"\" onblur=\"autosave('DTCA_Couns_Out_CM');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_AF\" id=\"DTCA_Couns_Out_AF\" value=\""+DTCA_Couns_Out_AF+"\" onblur=\"autosave('DTCA_Couns_Out_AF');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_AM\" id=\"DTCA_Couns_Out_AM\" value=\""+DTCA_Couns_Out_AM+"\" onblur=\"autosave('DTCA_Couns_Out_AM');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_Tot\" id=\"DTCA_Couns_Out_Tot\" value=\""+DTCA_Couns_Out_Tot+"\" onblur=\"autosave('DTCA_Couns_Out_Tot');counsouttotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + ""
              + "<td>Inpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_CF\" id=\"DTCA_Couns_In_CF\" value=\""+DTCA_Couns_In_CF+"\" onblur=\"autosave('DTCA_Couns_In_CF');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_CM\" id=\"DTCA_Couns_In_CM\" value=\""+DTCA_Couns_In_CM+"\" onblur=\"autosave('DTCA_Couns_In_CM');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_AF\" id=\"DTCA_Couns_In_AF\" value=\""+DTCA_Couns_In_AF+"\" onblur=\"autosave('DTCA_Couns_In_AF');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_AM\" id=\"DTCA_Couns_In_AM\" value=\""+DTCA_Couns_In_AM+"\" onblur=\"autosave('DTCA_Couns_In_AM');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_Tot\" id=\"DTCA_Couns_In_Tot\" value=\""+DTCA_Couns_In_Tot+"\" onblur=\"autosave('DTCA_Couns_In_Tot');counsintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">2.</td><td rowspan=\"2\" >No. tested</td><td>Outpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_CF\" id=\"DTCB_Test_Out_CF\" value=\""+DTCB_Test_Out_CF+"\" onblur=\"autosave('DTCB_Test_Out_CF');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_CM\" id=\"DTCB_Test_Out_CM\" value=\""+DTCB_Test_Out_CM+"\" onblur=\"autosave('DTCB_Test_Out_CM');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_AF\" id=\"DTCB_Test_Out_AF\" value=\""+DTCB_Test_Out_AF+"\" onblur=\"autosave('DTCB_Test_Out_AF');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_AM\" id=\"DTCB_Test_Out_AM\" value=\""+DTCB_Test_Out_AM+"\" onblur=\"autosave('DTCB_Test_Out_AM');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_Tot\" id=\"DTCB_Test_Out_Tot\" value=\""+DTCB_Test_Out_Tot+"\" onblur=\"autosave('DTCB_Test_Out_Tot');testedouttotal(); tabindex='-1'  \" readonly  maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_CF\" id=\"DTCB_Test_In_CF\" value=\""+DTCB_Test_In_CF+"\" onblur=\"autosave('DTCB_Test_In_CF');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_CM\" id=\"DTCB_Test_In_CM\" value=\""+DTCB_Test_In_CM+"\" onblur=\"autosave('DTCB_Test_In_CM');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_AF\" id=\"DTCB_Test_In_AF\" value=\""+DTCB_Test_In_AF+"\" onblur=\"autosave('DTCB_Test_In_AF');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_AM\" id=\"DTCB_Test_In_AM\" value=\""+DTCB_Test_In_AM+"\" onblur=\"autosave('DTCB_Test_In_AM');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_Tot\" id=\"DTCB_Test_In_Tot\" value=\""+DTCB_Test_In_Tot+"\" onblur=\"autosave('DTCB_Test_In_Tot');testedintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">3.</td><td rowspan=\"2\" >No. HIV+</td><td>Outpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_CF\" id=\"DTCC_HIV_Out_CF\" value=\""+DTCC_HIV_Out_CF+"\" onblur=\"autosave('DTCC_HIV_Out_CF');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_CM\" id=\"DTCC_HIV_Out_CM\" value=\""+DTCC_HIV_Out_CM+"\" onblur=\"autosave('DTCC_HIV_Out_CM');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_AF\" id=\"DTCC_HIV_Out_AF\" value=\""+DTCC_HIV_Out_AF+"\" onblur=\"autosave('DTCC_HIV_Out_AF');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_AM\" id=\"DTCC_HIV_Out_AM\" value=\""+DTCC_HIV_Out_AM+"\" onblur=\"autosave('DTCC_HIV_Out_AM');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_Tot\" id=\"DTCC_HIV_Out_Tot\" value=\""+DTCC_HIV_Out_Tot+"\" onblur=\"autosave('DTCC_HIV_Out_Tot');hivouttotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"  
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_CF\" id=\"DTCC_HIV_In_CF\" value=\""+DTCC_HIV_In_CF+"\" onblur=\"autosave('DTCC_HIV_In_CF');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_CM\" id=\"DTCC_HIV_In_CM\" value=\""+DTCC_HIV_In_CM+"\" onblur=\"autosave('DTCC_HIV_In_CM');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_AF\" id=\"DTCC_HIV_In_AF\" value=\""+DTCC_HIV_In_AF+"\" onblur=\"autosave('DTCC_HIV_In_AF');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_AM\" id=\"DTCC_HIV_In_AM\" value=\""+DTCC_HIV_In_AM+"\" onblur=\"autosave('DTCC_HIV_In_AM');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_Tot\" id=\"DTCC_HIV_In_Tot\" value=\""+DTCC_HIV_In_Tot+"\" onblur=\"autosave('DTCC_HIV_In_Tot');hivintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
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
           data+=validitychecker+" "+ ul+" "+fppane+""+pmctpane+""+matpane+""+htcpane+""+validatepane+" "+mainpane;
    
          if(session.getAttribute("forms_holder").toString().contains(",FP,")){
          data+=FP_TAB;
          }
             if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
          data+=MCH_TAB;
          }
              if(session.getAttribute("forms_holder").toString().contains(",Maternity,")){
          data+=MATERNITY_TAB;
          }
          if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
          data+=VCT_TAB+""+DTC_TAB;
          }
         
       
          data+="<div class=\"form-actions\">\n" +
"                              <button type=\"submit\" class=\"btn blue\">Run Validation</button>" +
"                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   "+ enterdby+"" +
"                           </div>";
           data+="  </div> ";
           }else{
               data="<font color=\"red\" size=\"6px;\" ><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support module MOH 711A.</font>";
               }
           
           
              String unvalidatedLink="";int counter=0;
     if(invalidFP>0 || invalidHTC>0||invalidMAT>0||invalidPMTCT>0){
     String getUnvalidated="SELECT moh711.SubPartnerID,subpartnera.SubPartnerNom FROM moh711 JOIN subpartnera ON moh711.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND moh711.Mois='"+month+"' AND moh711.Annee='"+year+"' AND moh711.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=load711.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     }
    }
     
    data+="<p hidden=\"true\" id=\"invalidatedData\">"+unvalidatedLink+"</p>"; 
     
       
      // System.out.println(MCH_TAB);
//        data+=MCH_TAB+""+MATERNITY_TAB;
            out.println(data);
          
        } finally {
              if(conn.conn!=null){ conn.conn.close();}
         if(conn.rs!=null){ conn.rs.close();}
         if(conn.rs1!=null){ conn.rs1.close();}
         if(conn.rs2!=null){ conn.rs2.close();}
         if(conn.st!=null){ conn.st.close();}
         if(conn.st2!=null){ conn.st2.close();}
            out.close();
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
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
        
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

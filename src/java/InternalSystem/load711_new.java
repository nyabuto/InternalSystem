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


public class load711_new extends HttpServlet {
HttpSession session;//201605
String data,id;//201605
String facilityId,year,month;//201605




//String FPMicrolutN,FPMicrolutR,FPMicrolutT,FPMicrogynonN,FPMicrogynonR,FPMicrogynonT,FPINJECTIONSN,FPINJECTIONSR,
//FPINJECTIONST,FPIUCDN,FPIUCDR,FPIUCDT,FPIMPLANTSN,FPIMPLANTSR,FPIMPLANTST,FPBTLN,FPBTLR,FPBTLT,FPVasectomyN,FPVasectomyR;
//String FPVasectomyT,FPCONDOMSN,FPCONDOMSR,FPCONDOMST,FPOTHERN,FPOTHERR,FPOTHERT,FPCLIENTSN,FPCLIENTSR,FPCLIENTST,FPIUCDRemoval,
//FPIMPLANTSRemoval,PMCTA_1stVisit_ANC,PMCTA_ReVisit_ANC,PMCTANCClientsT,PMCTHB7,PMCTIPT1,PMCTIPT2,PMCTANCClients4,PMCTITN,MATNormalDelivery,MATCSection;
//String MATBreech,MATAssistedVag,MATDeliveryT,MATLiveBirth,MATStillBirth,MATWeight2500,MATPreTerm,MATDischargealive,MATReferral,MATNeoNatalD,
//MATMaternalD,MATAPHAlive,MATAPHDead,MATPPHAlive,MATPPHDead,MATEclampAlive,MATEclampDead,MATRupUtAlive,MATRupUtDead,MATObstrLaborAlive,MATObstrLaborDead,MATSepsisAlive,MATSepsisDead;
String VCTClient_Couns_CM,VCTClient_Couns_CF,VCTClient_Couns_AM,VCTClient_Couns_AF,VCTClient_Couns_TOT,VCTClient_Tested_CM,VCTClient_Tested_CF,VCTClient_Tested_AM,VCTClient_Tested_AF
,VCTClient_Tested_TOT,VCTClient_HIV_CM,VCTClient_HIV_CF,VCTClient_HIV_AM,VCTClient_HIV_AF,VCTClient_HIV_TOT,VCTPartner_Couns_TOT,VCTPartner_Tested_TOT,VCTPartner_HIV_TOT,VCTPartner_Disc_TOT;
String DTCA_Couns_In_CM,DTCA_Couns_In_CF,DTCA_Couns_In_AM,DTCA_Couns_In_AF,DTCA_Couns_In_Tot,DTCA_Couns_Out_CM,DTCA_Couns_Out_CF,DTCA_Couns_Out_AM,DTCA_Couns_Out_AF,DTCA_Couns_Out_Tot,DTCB_Test_In_CM,DTCB_Test_In_CF
,DTCB_Test_In_AM,DTCB_Test_In_AF,DTCB_Test_In_Tot,DTCB_Test_Out_CM,DTCB_Test_Out_CF,DTCB_Test_Out_AM,DTCB_Test_Out_AF,DTCB_Test_Out_Tot,DTCC_HIV_In_CM,DTCC_HIV_In_CF,DTCC_HIV_In_AM
,DTCC_HIV_In_AF, DTCC_HIV_In_Tot,DTCC_HIV_Out_CM,DTCC_HIV_Out_CF,DTCC_HIV_Out_AM,DTCC_HIV_Out_AF,DTCC_HIV_Out_Tot;


String isValidated="";//201605
String validity="";//201605
String Userid="";//201605


//A. ANC / PMCT
//B. Maternity and Delivery
//C. Sexual and Gender Based Violence (SGBV)
//D. Family Planning (Number of clients Issued with contraceptives)
//E. Post Abortal Care (PAC) Services
//F. Child Health and Nutrition Information System(CHANIS) Height for Age
//F. Child Health and Nutrition Information System(CHANIS) weight for Age
//G. Cervical cancer screening
//H. Post Natal Care (PNC)
//I. Rehabilitation Services
//J. Medical Social Work
//K. Physiotherapy Service



String FamilyPlanninng, ancpmct,maternity,sgbv,pac, chanisheight,chanisweight,cervicalcancer, pnc, rehabilitation, msw,physiotherapy; //201605

        String subcountyid="";//201605
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
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
            dbConn conn = new dbConn();
        try {
            String validitychecker="";
            session=request.getSession();
            String FP_TAB="";
            String MCH_TAB="";
            String MATERNITY_TAB="";
            String VCT_TAB="";
            String DTC_TAB="";
            
            //define tab variables            
            String FamilyPlanninng_tab="";//2016
            String ancpmct_tab="";//2016
            String maternity_tab="";//2016
            String sgbv_tab="";//2016
            String pac_tab="";//2016
            String chanisheight_tab="";//2016
            String chanisweight_tab="";//2016
            String cervicalcancer_tab="";//2016
            String pnc_tab="";//2016
            String rehabilitation_tab="";//2016
            String msw_tab="";//2016
            String physiotherapy_tab=""; //201605
            String dtc="";
            String vct="";
               String enterdby="";
               //if the session is still active
               
           if(session.getAttribute("forms_holder")!=null && !(session.getAttribute("forms_holder").toString().equals(","))){
               //data holds all the results that will be displayed in the interface
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
  
    String ancpmctpane="";
    String matpane="";
    String sgbvpane="";
    String fppane="";
    String pacpane="";
    String chanisheight_pane="";
    String chanisweight_pane="";
    String cervical_pane="";
    String pnc_pane="";
    String rs_pane="";
    String msw_pane="";
    String ps_pane="";
    
    String htcpane="";
    
    

    
    
    String activeclass="";
    activeclass="";
    String activeclassname="";
    
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
     
        String getEntered="SELECT moh711_new.isValidated,SUM(subpartnera.FP),SUM(subpartnera.PMTCT),SUM(subpartnera.Maternity),SUM(subpartnera.HTC)"
            + " FROM subpartnera JOIN moh711_new ON subpartnera.SubPartnerID=moh711_new.SubPartnerID WHERE "
            + "moh711_new.Mois='"+month+"' AND moh711_new.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY moh711_new.isValidated";
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
    
    if(invalidHTC>0)
    {
  invalidHTCTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidHTC+"</span></button>";       
    }
    
 

    String ul="  <ul class=\"nav nav-tabs\">\n" ;
    
    
    
    
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
    //create a variable that will be used to determine if the new tables creted in new 711 will be active. This will be set to a default of 2(diabled).. to make all new tables active, it should be et to a value of 1 
     int newtablesactive=2; 
    //%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
   
   
   
      if(session.getAttribute("forms_holder").toString().contains(",PMTCT,"))
      {        
         counter++;

                     if(counter==1)
                       {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
                     
    ancpmctpane+=" <li "+activeclass+" ><a class='advance_form_with_chosen_element' href='#tab_1' data-toggle='tab'>(A). ANC / PMCT </a></li>" ;
    
       ancpmct_tab="<div class='tab-pane "+activeclassname+"' id='tab_1'><div class='portlet box blue'> " +
               "<div class='portlet-title'><h4 style='margin-left:20%;'>A. ANC / PMCT  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:"+validPMTCT+" out of "+expectedPMTCT+" &nbsp; "+invalidPMTCTTXT+"</b> " +
               "</div><div class='portlet-body form'>";      
          activeclassname="";
             activeclass=""; 
     }
      else{ancpmctpane="";}
    
    
      
      //MATERNITY
      
         if(session.getAttribute("forms_holder").toString().contains(",Maternity,")){
           counter++;
           
           
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       matpane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_2' data-toggle='tab'>(B). Maternity and Delivery </a> </li>" ;
     
      
        maternity_tab="<div class='tab-pane "+activeclassname+"' id='tab_2'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >B. Maternity and Delivery  &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:"+validMAT+" out of "+expectedMAT+" &nbsp; "+invalidMATTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{matpane="";}
     
         //sgbv
         //C. Sexual and Gender Based Violence (SGBV)
         
     if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       sgbvpane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_3' data-toggle='tab'>(C).SGBV</a> </li>" ;
     
      
        sgbv_tab="<div class='tab-pane "+activeclassname+"' id='tab_3'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >C. Sexual and Gender Based Violence (SGBV)  &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       activeclassname="";
             activeclass=""; 
      
      
      }else{sgbvpane="";}     
         
     //FAMILY PLANNING  
    if(session.getAttribute("forms_holder").toString().contains(",FP,")){
        counter++;

         if(counter==1)
                       {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
        
         
         fppane+=" <li "+activeclass+" ><a href='#tab_4' data-toggle='tab'>(D). Family Planning </a></li>";
    
         FamilyPlanninng_tab+="<div class='tab-pane "+activeclassname+"' id='tab_4' ><div class='portlet box blue' >" +
         "<div class='portlet-title'><h4 style='margin-left:20%;'>D. Family Planning (Number of clients Issued with contraceptives)  </h4> <b style='color:yellow; font-family:cambria; text-align: left;  margin-left:25%; font-size:16px;'> Record Counter: "+validFP+" out of "+expectedFP+  "&nbsp; "+invalidFPTXT+"</b>" +
         "</div><div class='portlet-body form' >";
          activeclassname="";
             activeclass="";              
    
    
    }else{fppane="";}
   
    
    
   //htc section is disabled  
   if(newtablesactive==1){
       if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
            counter++;
  
         if(counter==1){
 htcpane+="  <li class="+activeclass+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_5\" data-toggle=\"tab\">D: VCT</a></li>"
        + "<li><a class=\"advance_form_with_chosen_element\" href=\"#tab_5\" data-toggle=\"tab\" onclick=\"checkdispensary();\">E: DTC</a></li>\n" ;
    VCT_TAB="<div class=\"tab-pane "+activeclass+"\" id=\"tab_"+counter+"\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">D: VCT &nbsp  </h4><b  style=\"color:yellow; font-family:cambria; text-align: center; margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+" &nbsp; "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">";
    DTC_TAB="<div class=\"tab-pane \" id=\"tab_2\"><div class=\"portlet box blue\">" +
                             "<div class=\"portlet-title\"><h4 style=\"margin-left:20%;\">E: DTC  </h4> <b  style=\"color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;\">Record Counter:"+validHTC+" out of "+expectedHTC+"   &nbsp;  "+invalidHTCTXT+"</b> " +
                              "</div><div class=\"portlet-body form\">"; 
         }
       
       }
       else{htcpane="";}
       
           }
   
   //________E. Post Abortal Care (PAC) Services____________
   
  if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       pacpane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_6' data-toggle='tab'>(E).Post Abortal Care</a> </li>" ;
     
      
        pac_tab="<div class='tab-pane "+activeclassname+"' id='tab_6'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >E. Post Abortal Care (PAC) Services   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
      activeclass=""; 
      
      }else{pacpane="";}    
   
   
  //CHANIS Weight for Age
  
  
   if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       chanisweight_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_7' data-toggle='tab'>(F). CHANIS (Weight for Age) </a> </li>" ;
     
      
        chanisweight_tab="<div class='tab-pane "+activeclassname+"' id='tab_7'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >F. Child Health and Nutrition Information System(CHANIS) weight for Age   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
      activeclass=""; 
      
      }else{chanisweight_pane="";}
  
   
   
   
   
   //---------------------CHANIS Height for age----------------------
   
   
     if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       chanisheight_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_8' data-toggle='tab'>(F). CHANIS(Height for Age) </a> </li>" ;
     
      
        chanisheight_tab="<div class='tab-pane "+activeclassname+"' id='tab_8'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >F. Child Health and Nutrition Information System(CHANIS) Height for Age   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{chanisheight_pane="";}
   
 
//------------   G. Cervical cancer screening---------------  
     
 
  if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       cervical_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_9' data-toggle='tab'>(G). Cervical cancer screening </a> </li>" ;
     
      
        cervicalcancer_tab="<div class='tab-pane "+activeclassname+"' id='tab_9'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >G. Cervical cancer screening   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{cervical_pane="";}   
     
 //------------------------H. Post Natal Care (PNC)---------------------
  
   if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       pnc_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_10' data-toggle='tab'>(H). PNC </a> </li>" ;
     
      
        pnc_tab="<div class='tab-pane "+activeclassname+"' id='tab_10'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >H. Post Natal Care (PNC)   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{pnc_pane="";}    
  
   
   
   //___________________________I. Rehabilitation Services________________________________________________
   
    if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       rs_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_11' data-toggle='tab'>(I). Rehabilitation Services </a> </li>" ;
     
      
        rehabilitation_tab="<div class='tab-pane "+activeclassname+"' id='tab_11'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >I. Rehabilitation Services   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{rs_pane="";} 
   
  //-----------------------------------------
    
    
      if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       msw_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_12' data-toggle='tab'>(J). Medical Social Work </a> </li>" ;
     
      
        msw_tab="<div class='tab-pane "+activeclassname+"' id='tab_12'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >J. Medical Social Work   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       activeclassname="";
             activeclass=""; 
      
      
      }else{msw_pane="";} 
   
   
      
      //----------------------------------------------------
        if(1==newtablesactive){
           counter++;
          
         if(counter==1)   {
             activeclassname="active";
             activeclass="class='active'"; 
                       }
    
         
       ps_pane+=" <li "+activeclass+"><a class='advance_form_with_chosen_element' href='#tab_13' data-toggle='tab'>(K). Physiotherapy Service </a> </li>" ;
     
      
       physiotherapy_tab="<div class='tab-pane "+activeclassname+"' id='tab_13'><div class='portlet box blue' >" +
                              "<div class='portlet-title'><h4 >K. Physiotherapy Service   &nbsp  &nbsp </h4><b  style='color:yellow; font-family:cambria; text-align: center;  margin-left:25%; font-size:16px;'>Record Counter:0 out of 0 &nbsp; </b> " +
                              "</div><div class=\"portlet-body form\">";
       
      activeclassname="";
             activeclass=""; 
      
      }else{ps_pane="";}
      
      
String validatepane=" <li style=\"margin-left:150px;\" id=\"isValidated\"></li>                    </ul> </div>\n" ;
String mainpane="<div class=\"tab-content\" >";
    
   
    
      
        
     
                    
              // INITIALIZING VARIABLES 
              
//FPProgestinN,FPProgestinR,FPProgestinT,FPCocN,FPCocR,FPCocT,FPEcpN,FPEcpR,FPEcpT,FPINJECTABLESN,FPINJECTABLESR,FPINJECTABLEST,FPCONDOMSMN,FPCONDOMSFN,FPNaturalN,FPNaturalR,FPNaturalT,FPADOLESCENT10_14N,FPADOLESCENT10_14R,FPADOLESCENT10_14T,FPADOLESCENT15_19N,FPADOLESCENT15_19R,FPADOLESCENT15_19T,FPADOLESCENT20_24N,FPADOLESCENT20_24R,FPADOLESCENT20_24T,

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

 FPProgestinN=FPProgestinR=FPProgestinT=FPCocN=FPCocR=FPCocT=FPEcpN=FPEcpR=FPEcpT=FPINJECTABLESN=FPINJECTABLESR=FPINJECTABLEST=FPINJECTIONSN=FPINJECTIONSR=FPINJECTIONST=FPIUCDN=FPIUCDR=FPIUCDT=FPIMPLANTSN=FPIMPLANTSR=FPIMPLANTST=FPBTLN=FPBTLR=FPBTLT=FPVasectomyN=FPVasectomyR=FPVasectomyT=FPCONDOMSMN=FPCONDOMSFN=FPCONDOMST=FPNaturalN=FPNaturalR=FPNaturalT=FPCLIENTSN=FPCLIENTSR=FPCLIENTST=FPADOLESCENT10_14N=FPADOLESCENT10_14R=FPADOLESCENT10_14T=FPADOLESCENT15_19N=FPADOLESCENT15_19R=FPADOLESCENT15_19T=FPADOLESCENT20_24N=FPADOLESCENT20_24R=FPADOLESCENT20_24T=FPIUCDRemoval=FPIMPLANTSRemoval="";	
        
 PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTIPT1=PMCTIPT2=PMCTHB11=PMCTANCClients4=PMCTITN1=PMCTITN=PMTCTSYPHILISTES=PMTCTSYPHILISPOS=PMTCTCOUNSELLEDFEED=PMTCTBREAST=PMTCTEXERCISE=PMTCTPREG10_14=PMTCTPREG15_19=PMTCTIRON=PMTCTFOLIC=PMTCTFERROUS="";	
                
 MATNormalDelivery=MATCSection=MATBreech=MATAssistedVag=MATDeliveryT=MATLiveBirth=MATFreshStillBirth=MATMeceratedStillBirth=MATDeformities=MATLowAPGAR=MATWeight2500=MATTetracycline=MATPreTerm=MATDischargealive=MATbreastfeeding1=MATDeliveriesPos=MATNeoNatalD=MATMaternalD10_19=MATMaternalD=MATMaternalDAudited=MATAPHAlive=MATAPHDead=MATPPHAlive=MATPPHDead=MATEclampAlive=MATEclampDead=MATRupUtAlive=MATRupUtDead=MATObstrLaborAlive=MATObstrLaborDead=MATSepsisAlive=MATSepsisDead=MATREFFromOtherFacility=MATREFFromCU=MATREFToOtherFacility=MATREFToCU="";	
                        
 SGBVRape72_0_9=SGBVRape72_10_17=SGBVRape72_18_49=SGBVRape72_50=SGBVRape72T=SGBVinitPEP0_9=SGBVinitPEP10_17=SGBVinitPEP18_49=SGBVinitPEP50=SGBVinitPEPT=SGBVcompPEP0_9=SGBVcompPEP10_17=SGBVcompPEP18_49=SGBVcompPEP50=SGBVcompPEPT=SGBVPregnant0_9=SGBVPregnant10_17=SGBVPregnant18_49=SGBVPregnant50=SGBVPregnantT=SGBVseroconverting0_9=SGBVseroconverting10_17=SGBVseroconverting18_49=SGBVseroconverting50=SGBVseroconvertingT=SGBVsurvivors0_9=SGBVsurvivors10_17=SGBVsurvivors18_49=SGBVsurvivors50=SGBVsurvivorsT="";
                                
 PAC10_19=PACT="";	
                                        
 CHANIS0_5NormalweightF=CHANIS0_5NormalweightM=CHANIS0_5NormalweightT=CHANIS0_5UnderweightF=CHANIS0_5UnderweightM=CHANIS0_5UnderweightT=CHANIS0_5sevUnderweightF=CHANIS0_5sevUnderweightM=CHANIS0_5sevUnderweightT=CHANIS0_5OverweightF=CHANIS0_5OverweightM=CHANIS0_5OverweightT=CHANIS0_5ObeseF=CHANIS0_5ObeseM=CHANIS0_5ObeseT=CHANIS0_5TWF=CHANIS0_5TWM=CHANIS0_5TW=CHANIS6_23NormalweightF=CHANIS6_23NormalweightM=CHANIS6_23NormalweightT=CHANIS6_23UnderweightF=CHANIS6_23UnderweightM=CHANIS6_23UnderweightT=CHANIS6_23sevUnderweightF=CHANIS6_23sevUnderweightM=CHANIS6_23sevUnderweightT=CHANIS6_23OverweightF=CHANIS6_23OverweightM=CHANIS6_23OverweightT=CHANIS6_23ObeseF=CHANIS6_23ObeseM=CHANIS6_23ObeseT=CHANIS6_23TWF=CHANIS6_23TWM=CHANIS6_23TW=CHANIS24_59NormalweightF=CHANIS24_59NormalweightM=CHANIS24_59NormalweightT=CHANIS24_59UnderweightF=CHANIS24_59UnderweightM=CHANIS24_59UnderweightT=CHANIS24_59sevUnderweightF=CHANIS24_59sevUnderweightM=CHANIS24_59sevUnderweightT=CHANIS24_59OverweightF=CHANIS24_59OverweightM=CHANIS24_59OverweightT=CHANIS24_59ObeseF=CHANIS24_59ObeseM=CHANIS24_59ObeseT=CHANIS24_59TWF=CHANIS24_59TWM=CHANIS24_59TW=CHANISMUACNormalF=CHANISMUACNormalM=CHANISMUACNormalT=CHANISMUACModerateF=CHANISMUACModerateM=CHANISMUACModerateT=CHANISMUACSevereF=CHANISMUACSevereM=CHANISMUACSevereT=CHANISMUACMeasuredF=CHANISMUACMeasuredM=CHANISMUACMeasuredT="";

 CHANIS0_5NormalHeightF=CHANIS0_5NormalHeightM=CHANIS0_5NormalHeightT=CHANIS0_5StuntedF=CHANIS0_5StuntedM=CHANIS0_5StuntedT=CHANIS0_5sevStuntedF=CHANIS0_5sevStuntedM=CHANIS0_5sevStuntedT=CHANIS0_5TMeasF=CHANIS0_5TMeasM=CHANIS0_5TMeas=CHANIS6_23NormalHeightF=CHANIS6_23NormalHeightM=CHANIS6_23NormalHeightT=CHANIS6_23StuntedF=CHANIS6_23StuntedM=CHANIS6_23StuntedT=CHANIS6_23sevStuntedF=CHANIS6_23sevStuntedM=CHANIS6_23sevStuntedT=CHANIS6_23TMeasF=CHANIS6_23TMeasM=CHANIS6_23TMeas=CHANIS24_59NormalHeightF=CHANIS24_59NormalHeightM=CHANIS24_59NormalHeightT=CHANIS24_59StuntedF=CHANIS24_59StuntedM=CHANIS24_59StuntedT=CHANIS24_59sevStuntedF=CHANIS24_59sevStuntedM=CHANIS24_59sevStuntedT=CHANIS24_59TMeasF=CHANIS24_59TMeasM=CHANIS24_59TMeas=CHANIS0_59NewVisitsF=CHANIS0_59NewVisitsM=CHANIS0_59NewVisitsT=CHANIS0_59KwashiakorF=CHANIS0_59KwashiakorM=CHANIS0_59KwashiakorT=CHANIS0_59MarasmusF=CHANIS0_59MarasmusM=CHANIS0_59MarasmusT=CHANIS0_59FalgrowthF=CHANIS0_59FalgrowthM=CHANIS0_59FalgrowthT=CHANIS0_59F=CHANIS0_59M=CHANIS0_59T=CHANIS0_5EXCLBreastF=CHANIS0_5EXCLBreastM=CHANIS0_5EXCLBreastT=CHANIS12_59DewormedF=CHANIS12_59DewormedM=CHANIS12_59DewormedT=CHANIS6_23MNPsF=CHANIS6_23MNPsM=CHANIS6_23MNPsT=CHANIS0_59DisabilityF=CHANIS0_59DisabilityM=CHANIS0_59DisabilityT="";	
  
 CCSSUSPICIOUSLES24=CCSSUSPICIOUSLES25_49=CCSSUSPICIOUSLES50=CCSVVH24=CCSVVH25_49=CCSVVH50=CCSPAPSMEAR24=CCSPAPSMEAR25_49=CCSPAPSMEAR50=CCSHPV24=CCSHPV25_49=CCSHPV50=CCSVIAVILIPOS24=CCSVIAVILIPOS25_49=CCSVIAVILIPOS50=CCSCYTOLPOS24=CCSCYTOLPOS25_49=CCSCYTOLPOS50=CCSHPVPOS24=CCSHPVPOS25_49=CCSHPVPOS50=CCSCryotherapy24=CCSCryotherapy25_49=CCSCryotherapy50=CCSLEEP24=CCSLEEP25_49=CCSLEEP50=CCSHIVPOSSCREENED24=CCSHIVPOSSCREENED25_49=CCSHIVPOSSCREENED50="";	
                                                
 PNCBreastExam=PNCCounselled=PNCFistula=PNCExerNegative=PNCExerPositive=PNCCCSsuspect=PNCmotherspostpartum2_3=PNCmotherspostpartum6=PNCinfantspostpartum2_3=PNCinfantspostpartum6=PNCreferralsfromotherHF=PNCreferralsfromotherCU=PNCreferralsTootherHF=PNCreferralsTootherCU="";	
                                                        
 RsAssessed=Rstreated=RsRehabilitated=Rsreffered=RsIntergrated="";	
                                                                
 MSWpscounselling=MSWdrugabuse=MSWMental=MSWAdolescent=MSWPsAsses=MSWsocialinv=MSWsocialRehab=MSWoutreach=MSWreferrals=MSWwaivedpatients="";	
                                                                        
 PsotherOPD4=PsotherOPD5_19=PsotherOPD20=Psotherinpatient4=Psotherinpatient5_19=Psotherinpatient20= PsPWDOPD4=PsPWDOPD5_19=PsPWDOPD20=PsPWDinpatient4=PsPWDinpatient5_19=PsPWDinpatient20=PsTreatments4=PsTreatments5_19=PsTreatments20=PsAssessed4=PsAssessed5_19=PsAssessed20=PsServices4=PsServices5_19=PsServices20=PsANCCounsel5_19=PsANCCounsel20=PsExercise5_19=PsExercise20=PsFIFcollected5_19=PsFIFcollected20=PsFIFwaived5_19=PsFIFwaived20=PsFIFexempted4=PsFIFexempted5_19=PsFIFexempted20=PsDiasbilitymeeting4=PsDiasbilitymeeting5_19=PsDiasbilitymeeting20="";



 
 //leave the htc section for now but dont display it in the form
 VCTClient_Couns_CM=VCTClient_Couns_CF=VCTClient_Couns_AM=VCTClient_Couns_AF=VCTClient_Couns_TOT=VCTClient_Tested_CM=VCTClient_Tested_CF=VCTClient_Tested_AM=VCTClient_Tested_AF
=VCTClient_Tested_TOT=VCTClient_HIV_CM=VCTClient_HIV_CF=VCTClient_HIV_AM=VCTClient_HIV_AF=VCTClient_HIV_TOT=VCTPartner_Couns_TOT=VCTPartner_Tested_TOT=VCTPartner_HIV_TOT=VCTPartner_Disc_TOT="";
 
 DTCA_Couns_In_CM=DTCA_Couns_In_CF=DTCA_Couns_In_AM=DTCA_Couns_In_AF=DTCA_Couns_In_Tot=DTCA_Couns_Out_CM=DTCA_Couns_Out_CF=DTCA_Couns_Out_AM=DTCA_Couns_Out_AF=DTCA_Couns_Out_Tot=DTCB_Test_In_CM=DTCB_Test_In_CF
=DTCB_Test_In_AM=DTCB_Test_In_AF=DTCB_Test_In_Tot=DTCB_Test_Out_CM=DTCB_Test_Out_CF=DTCB_Test_Out_AM=DTCB_Test_Out_AF=DTCB_Test_Out_Tot=DTCC_HIV_In_CM=DTCC_HIV_In_CF=DTCC_HIV_In_AM
=DTCC_HIV_In_AF= DTCC_HIV_In_Tot=DTCC_HIV_Out_CM=DTCC_HIV_Out_CF=DTCC_HIV_Out_AM=DTCC_HIV_Out_AF=DTCC_HIV_Out_Tot=Userid="";

           
          String checker="SELECT * FROM moh711_new WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
           String islocked="";
          if(conn.rs.next()==true){
              
              System.out.println("Data already exist loading............................");
              
              if(conn.rs.getString("isLocked").equals("1")){islocked="disabled='true'";}
  
  if(conn.rs.getString("FPProgestinN")!=null){ FPProgestinN=conn.rs.getString("FPProgestinN");}else{ FPProgestinN="";}
  if(conn.rs.getString("FPProgestinR")!=null){ FPProgestinR=conn.rs.getString("FPProgestinR");}else{ FPProgestinR="";}
  if(conn.rs.getString("FPProgestinT")!=null){ FPProgestinT=conn.rs.getString("FPProgestinT");}else{ FPProgestinT="";}
  if(conn.rs.getString("FPCocN")!=null){ FPCocN=conn.rs.getString("FPCocN");}else{ FPCocN="";}
  if(conn.rs.getString("FPCocR")!=null){ FPCocR=conn.rs.getString("FPCocR");}else{ FPCocR="";}
  if(conn.rs.getString("FPCocT")!=null){ FPCocT=conn.rs.getString("FPCocT");}else{ FPCocT="";}
  if(conn.rs.getString("FPEcpN")!=null){ FPEcpN=conn.rs.getString("FPEcpN");}else{ FPEcpN="";}
  if(conn.rs.getString("FPEcpR")!=null){ FPEcpR=conn.rs.getString("FPEcpR");}else{ FPEcpR="";}
  if(conn.rs.getString("FPEcpT")!=null){ FPEcpT=conn.rs.getString("FPEcpT");}else{ FPEcpT="";}
  if(conn.rs.getString("FPINJECTABLESN")!=null){ FPINJECTABLESN=conn.rs.getString("FPINJECTABLESN");}else{ FPINJECTABLESN="";}
  if(conn.rs.getString("FPINJECTABLESR")!=null){ FPINJECTABLESR=conn.rs.getString("FPINJECTABLESR");}else{ FPINJECTABLESR="";}
  if(conn.rs.getString("FPINJECTABLEST")!=null){ FPINJECTABLEST=conn.rs.getString("FPINJECTABLEST");}else{ FPINJECTABLEST="";}
  if(conn.rs.getString("FPINJECTIONSN")!=null){ FPINJECTIONSN=conn.rs.getString("FPINJECTIONSN");}else{ FPINJECTIONSN="";}
  if(conn.rs.getString("FPINJECTIONSR")!=null){ FPINJECTIONSR=conn.rs.getString("FPINJECTIONSR");}else{ FPINJECTIONSR="";}
  if(conn.rs.getString("FPINJECTIONST")!=null){ FPINJECTIONST=conn.rs.getString("FPINJECTIONST");}else{ FPINJECTIONST="";}
  if(conn.rs.getString("FPIUCDN")!=null){ FPIUCDN=conn.rs.getString("FPIUCDN");}else{ FPIUCDN="";}
  if(conn.rs.getString("FPIUCDR")!=null){ FPIUCDR=conn.rs.getString("FPIUCDR");}else{ FPIUCDR="";}
  if(conn.rs.getString("FPIUCDT")!=null){ FPIUCDT=conn.rs.getString("FPIUCDT");}else{ FPIUCDT="";}
  if(conn.rs.getString("FPIMPLANTSN")!=null){ FPIMPLANTSN=conn.rs.getString("FPIMPLANTSN");}else{ FPIMPLANTSN="";}
  if(conn.rs.getString("FPIMPLANTSR")!=null){ FPIMPLANTSR=conn.rs.getString("FPIMPLANTSR");}else{ FPIMPLANTSR="";}
  if(conn.rs.getString("FPIMPLANTST")!=null){ FPIMPLANTST=conn.rs.getString("FPIMPLANTST");}else{ FPIMPLANTST="";}
  if(conn.rs.getString("FPBTLN")!=null){ FPBTLN=conn.rs.getString("FPBTLN");}else{ FPBTLN="";}
  if(conn.rs.getString("FPBTLR")!=null){ FPBTLR=conn.rs.getString("FPBTLR");}else{ FPBTLR="";}
  if(conn.rs.getString("FPBTLT")!=null){ FPBTLT=conn.rs.getString("FPBTLT");}else{ FPBTLT="";}
  if(conn.rs.getString("FPVasectomyN")!=null){ FPVasectomyN=conn.rs.getString("FPVasectomyN");}else{ FPVasectomyN="";}
  if(conn.rs.getString("FPVasectomyR")!=null){ FPVasectomyR=conn.rs.getString("FPVasectomyR");}else{ FPVasectomyR="";}
  if(conn.rs.getString("FPVasectomyT")!=null){ FPVasectomyT=conn.rs.getString("FPVasectomyT");}else{ FPVasectomyT="";}
  if(conn.rs.getString("FPCONDOMSMN")!=null){ FPCONDOMSMN=conn.rs.getString("FPCONDOMSMN");}else{ FPCONDOMSMN="";}
  if(conn.rs.getString("FPCONDOMSFN")!=null){ FPCONDOMSFN=conn.rs.getString("FPCONDOMSFN");}else{ FPCONDOMSFN="";}
  if(conn.rs.getString("FPCONDOMST")!=null){ FPCONDOMST=conn.rs.getString("FPCONDOMST");}else{ FPCONDOMST="";}
  if(conn.rs.getString("FPNaturalN")!=null){ FPNaturalN=conn.rs.getString("FPNaturalN");}else{ FPNaturalN="";}
  if(conn.rs.getString("FPNaturalN")!=null){ FPNaturalN=conn.rs.getString("FPNaturalN");}else{ FPNaturalN="";}
  if(conn.rs.getString("FPNaturalR")!=null){ FPNaturalR=conn.rs.getString("FPNaturalR");}else{ FPNaturalR="";}
  if(conn.rs.getString("FPNaturalT")!=null){ FPNaturalT=conn.rs.getString("FPNaturalT");}else{ FPNaturalT="";}
  if(conn.rs.getString("FPCLIENTSN")!=null){ FPCLIENTSN=conn.rs.getString("FPCLIENTSN");}else{ FPCLIENTSN="";}
  if(conn.rs.getString("FPCLIENTSR")!=null){ FPCLIENTSR=conn.rs.getString("FPCLIENTSR");}else{ FPCLIENTSR="";}
  if(conn.rs.getString("FPCLIENTST")!=null){ FPCLIENTST=conn.rs.getString("FPCLIENTST");}else{ FPCLIENTST="";}
  if(conn.rs.getString("FPADOLESCENT10_14N")!=null){ FPADOLESCENT10_14N=conn.rs.getString("FPADOLESCENT10_14N");}else{ FPADOLESCENT10_14N="";}
  if(conn.rs.getString("FPADOLESCENT10_14R")!=null){ FPADOLESCENT10_14R=conn.rs.getString("FPADOLESCENT10_14R");}else{ FPADOLESCENT10_14R="";}
  if(conn.rs.getString("FPADOLESCENT10_14T")!=null){ FPADOLESCENT10_14T=conn.rs.getString("FPADOLESCENT10_14T");}else{ FPADOLESCENT10_14T="";}
  if(conn.rs.getString("FPADOLESCENT15_19N")!=null){ FPADOLESCENT15_19N=conn.rs.getString("FPADOLESCENT15_19N");}else{ FPADOLESCENT15_19N="";}
  if(conn.rs.getString("FPADOLESCENT15_19R")!=null){ FPADOLESCENT15_19R=conn.rs.getString("FPADOLESCENT15_19R");}else{ FPADOLESCENT15_19R="";}
  if(conn.rs.getString("FPADOLESCENT15_19T")!=null){ FPADOLESCENT15_19T=conn.rs.getString("FPADOLESCENT15_19T");}else{ FPADOLESCENT15_19T="";}
  if(conn.rs.getString("FPADOLESCENT20_24N")!=null){ FPADOLESCENT20_24N=conn.rs.getString("FPADOLESCENT20_24N");}else{ FPADOLESCENT20_24N="";}
  if(conn.rs.getString("FPADOLESCENT20_24R")!=null){ FPADOLESCENT20_24R=conn.rs.getString("FPADOLESCENT20_24R");}else{ FPADOLESCENT20_24R="";}
  if(conn.rs.getString("FPADOLESCENT20_24T")!=null){ FPADOLESCENT20_24T=conn.rs.getString("FPADOLESCENT20_24T");}else{ FPADOLESCENT20_24T="";}
  if(conn.rs.getString("FPIUCDRemoval")!=null){ FPIUCDRemoval=conn.rs.getString("FPIUCDRemoval");}else{ FPIUCDRemoval="";}
  if(conn.rs.getString("FPIMPLANTSRemoval")!=null){ FPIMPLANTSRemoval=conn.rs.getString("FPIMPLANTSRemoval");}else{ FPIMPLANTSRemoval="";}

  
  
  //=======================================================PMTCT ANC===================================================
  
       
    
    if(conn.rs.getString("PMCTA_1stVisit_ANC")!=null){ PMCTA_1stVisit_ANC=conn.rs.getString("PMCTA_1stVisit_ANC");}else{ PMCTA_1stVisit_ANC="";}
    if(conn.rs.getString("PMCTA_ReVisit_ANC")!=null){ PMCTA_ReVisit_ANC=conn.rs.getString("PMCTA_ReVisit_ANC");}else{ PMCTA_ReVisit_ANC="";}
    if(conn.rs.getString("PMCTANCClientsT")!=null){ PMCTANCClientsT=conn.rs.getString("PMCTANCClientsT");}else{ PMCTANCClientsT="";}
    if(conn.rs.getString("PMCTIPT1")!=null){ PMCTIPT1=conn.rs.getString("PMCTIPT1");}else{ PMCTIPT1="";}
    if(conn.rs.getString("PMCTIPT2")!=null){ PMCTIPT2=conn.rs.getString("PMCTIPT2");}else{ PMCTIPT2="";}
    if(conn.rs.getString("PMCTHB11")!=null){ PMCTHB11=conn.rs.getString("PMCTHB11");}else{ PMCTHB11="";}
    if(conn.rs.getString("PMCTANCClients4")!=null){ PMCTANCClients4=conn.rs.getString("PMCTANCClients4");}else{ PMCTANCClients4="";}
    if(conn.rs.getString("PMCTITN1")!=null){ PMCTITN1=conn.rs.getString("PMCTITN1");}else{ PMCTITN1="";}
    if(conn.rs.getString("PMCTITN")!=null){ PMCTITN=conn.rs.getString("PMCTITN");}else{ PMCTITN="";}
    if(conn.rs.getString("PMTCTSYPHILISTES")!=null){ PMTCTSYPHILISTES=conn.rs.getString("PMTCTSYPHILISTES");}else{ PMTCTSYPHILISTES="";}
    if(conn.rs.getString("PMTCTSYPHILISPOS")!=null){ PMTCTSYPHILISPOS=conn.rs.getString("PMTCTSYPHILISPOS");}else{ PMTCTSYPHILISPOS="";}
    if(conn.rs.getString("PMTCTCOUNSELLEDFEED")!=null){ PMTCTCOUNSELLEDFEED=conn.rs.getString("PMTCTCOUNSELLEDFEED");}else{ PMTCTCOUNSELLEDFEED="";}
    if(conn.rs.getString("PMTCTBREAST")!=null){ PMTCTBREAST=conn.rs.getString("PMTCTBREAST");}else{ PMTCTBREAST="";}
    if(conn.rs.getString("PMTCTEXERCISE")!=null){ PMTCTEXERCISE=conn.rs.getString("PMTCTEXERCISE");}else{ PMTCTEXERCISE="";}
    if(conn.rs.getString("PMTCTPREG10_14")!=null){ PMTCTPREG10_14=conn.rs.getString("PMTCTPREG10_14");}else{ PMTCTPREG10_14="";}
    if(conn.rs.getString("PMTCTPREG15_19")!=null){ PMTCTPREG15_19=conn.rs.getString("PMTCTPREG15_19");}else{ PMTCTPREG15_19="";}
    if(conn.rs.getString("PMTCTIRON")!=null){ PMTCTIRON=conn.rs.getString("PMTCTIRON");}else{ PMTCTIRON="";}
    if(conn.rs.getString("PMTCTFOLIC")!=null){ PMTCTFOLIC=conn.rs.getString("PMTCTFOLIC");}else{ PMTCTFOLIC="";}
    if(conn.rs.getString("PMTCTFERROUS")!=null){ PMTCTFERROUS=conn.rs.getString("PMTCTFERROUS");}else{ PMTCTFERROUS="";}
    
    
  
  
  
  
   //===================================================MATERNITY================================================
    
  
     if(conn.rs.getString("MATNormalDelivery")!=null){ MATNormalDelivery=conn.rs.getString("MATNormalDelivery");}else{ MATNormalDelivery="";}
    if(conn.rs.getString("MATCSection")!=null){ MATCSection=conn.rs.getString("MATCSection");}else{ MATCSection="";}
    if(conn.rs.getString("MATBreech")!=null){ MATBreech=conn.rs.getString("MATBreech");}else{ MATBreech="";}
    if(conn.rs.getString("MATAssistedVag")!=null){ MATAssistedVag=conn.rs.getString("MATAssistedVag");}else{ MATAssistedVag="";}
    if(conn.rs.getString("MATDeliveryT")!=null){ MATDeliveryT=conn.rs.getString("MATDeliveryT");}else{ MATDeliveryT="";}
    if(conn.rs.getString("MATLiveBirth")!=null){ MATLiveBirth=conn.rs.getString("MATLiveBirth");}else{ MATLiveBirth="";}
    if(conn.rs.getString("MATFreshStillBirth")!=null){ MATFreshStillBirth=conn.rs.getString("MATFreshStillBirth");}else{ MATFreshStillBirth="";}
    if(conn.rs.getString("MATMeceratedStillBirth")!=null){ MATMeceratedStillBirth=conn.rs.getString("MATMeceratedStillBirth");}else{ MATMeceratedStillBirth="";}
    if(conn.rs.getString("MATDeformities")!=null){ MATDeformities=conn.rs.getString("MATDeformities");}else{ MATDeformities="";}
    if(conn.rs.getString("MATLowAPGAR")!=null){ MATLowAPGAR=conn.rs.getString("MATLowAPGAR");}else{ MATLowAPGAR="";}
    if(conn.rs.getString("MATWeight2500")!=null){ MATWeight2500=conn.rs.getString("MATWeight2500");}else{ MATWeight2500="";}
    if(conn.rs.getString("MATTetracycline")!=null){ MATTetracycline=conn.rs.getString("MATTetracycline");}else{ MATTetracycline="";}
    if(conn.rs.getString("MATPreTerm")!=null){ MATPreTerm=conn.rs.getString("MATPreTerm");}else{ MATPreTerm="";}
    if(conn.rs.getString("MATDischargealive")!=null){ MATDischargealive=conn.rs.getString("MATDischargealive");}else{ MATDischargealive="";}
    if(conn.rs.getString("MATbreastfeeding1")!=null){ MATbreastfeeding1=conn.rs.getString("MATbreastfeeding1");}else{ MATbreastfeeding1="";}
    if(conn.rs.getString("MATDeliveriesPos")!=null){ MATDeliveriesPos=conn.rs.getString("MATDeliveriesPos");}else{ MATDeliveriesPos="";}
    if(conn.rs.getString("MATNeoNatalD")!=null){ MATNeoNatalD=conn.rs.getString("MATNeoNatalD");}else{ MATNeoNatalD="";}
    if(conn.rs.getString("MATMaternalD10_19")!=null){ MATMaternalD10_19=conn.rs.getString("MATMaternalD10_19");}else{ MATMaternalD10_19="";}
    if(conn.rs.getString("MATMaternalD")!=null){ MATMaternalD=conn.rs.getString("MATMaternalD");}else{ MATMaternalD="";}
    if(conn.rs.getString("MATMaternalDAudited")!=null){ MATMaternalDAudited=conn.rs.getString("MATMaternalDAudited");}else{ MATMaternalDAudited="";}
    if(conn.rs.getString("MATAPHAlive")!=null){ MATAPHAlive=conn.rs.getString("MATAPHAlive");}else{ MATAPHAlive="";}
    if(conn.rs.getString("MATAPHDead")!=null){ MATAPHDead=conn.rs.getString("MATAPHDead");}else{ MATAPHDead="";}
    if(conn.rs.getString("MATPPHAlive")!=null){ MATPPHAlive=conn.rs.getString("MATPPHAlive");}else{ MATPPHAlive="";}
    if(conn.rs.getString("MATPPHDead")!=null){ MATPPHDead=conn.rs.getString("MATPPHDead");}else{ MATPPHDead="";}
    if(conn.rs.getString("MATEclampAlive")!=null){ MATEclampAlive=conn.rs.getString("MATEclampAlive");}else{ MATEclampAlive="";}
    if(conn.rs.getString("MATEclampDead")!=null){ MATEclampDead=conn.rs.getString("MATEclampDead");}else{ MATEclampDead="";}
    if(conn.rs.getString("MATRupUtAlive")!=null){ MATRupUtAlive=conn.rs.getString("MATRupUtAlive");}else{ MATRupUtAlive="";}
    if(conn.rs.getString("MATRupUtDead")!=null){ MATRupUtDead=conn.rs.getString("MATRupUtDead");}else{ MATRupUtDead="";}
    if(conn.rs.getString("MATObstrLaborAlive")!=null){ MATObstrLaborAlive=conn.rs.getString("MATObstrLaborAlive");}else{ MATObstrLaborAlive="";}
    if(conn.rs.getString("MATObstrLaborDead")!=null){ MATObstrLaborDead=conn.rs.getString("MATObstrLaborDead");}else{ MATObstrLaborDead="";}
    if(conn.rs.getString("MATSepsisAlive")!=null){ MATSepsisAlive=conn.rs.getString("MATSepsisAlive");}else{ MATSepsisAlive="";}
    if(conn.rs.getString("MATSepsisDead")!=null){ MATSepsisDead=conn.rs.getString("MATSepsisDead");}else{ MATSepsisDead="";}
    if(conn.rs.getString("MATREFFromOtherFacility")!=null){ MATREFFromOtherFacility=conn.rs.getString("MATREFFromOtherFacility");}else{ MATREFFromOtherFacility="";}
    if(conn.rs.getString("MATREFFromCU")!=null){ MATREFFromCU=conn.rs.getString("MATREFFromCU");}else{ MATREFFromCU="";}
    if(conn.rs.getString("MATREFToOtherFacility")!=null){ MATREFToOtherFacility=conn.rs.getString("MATREFToOtherFacility");}else{ MATREFToOtherFacility="";}
    if(conn.rs.getString("MATREFToCU")!=null){ MATREFToCU=conn.rs.getString("MATREFToCU");}else{ MATREFToCU="";}
    
    
    // =========================================================SGBV ============================================================
  
    
    if(conn.rs.getString("SGBVRape72_0_9")!=null){ SGBVRape72_0_9=conn.rs.getString("SGBVRape72_0_9");}else{ SGBVRape72_0_9="";}
    if(conn.rs.getString("SGBVRape72_10_17")!=null){ SGBVRape72_10_17=conn.rs.getString("SGBVRape72_10_17");}else{ SGBVRape72_10_17="";}
    if(conn.rs.getString("SGBVRape72_18_49")!=null){ SGBVRape72_18_49=conn.rs.getString("SGBVRape72_18_49");}else{ SGBVRape72_18_49="";}
    if(conn.rs.getString("SGBVRape72_50")!=null){ SGBVRape72_50=conn.rs.getString("SGBVRape72_50");}else{ SGBVRape72_50="";}
    if(conn.rs.getString("SGBVRape72T")!=null){ SGBVRape72T=conn.rs.getString("SGBVRape72T");}else{ SGBVRape72T="";}
    if(conn.rs.getString("SGBVinitPEP0_9")!=null){ SGBVinitPEP0_9=conn.rs.getString("SGBVinitPEP0_9");}else{ SGBVinitPEP0_9="";}
    if(conn.rs.getString("SGBVinitPEP10_17")!=null){ SGBVinitPEP10_17=conn.rs.getString("SGBVinitPEP10_17");}else{ SGBVinitPEP10_17="";}
    if(conn.rs.getString("SGBVinitPEP18_49")!=null){ SGBVinitPEP18_49=conn.rs.getString("SGBVinitPEP18_49");}else{ SGBVinitPEP18_49="";}
    if(conn.rs.getString("SGBVinitPEP50")!=null){ SGBVinitPEP50=conn.rs.getString("SGBVinitPEP50");}else{ SGBVinitPEP50="";}
    if(conn.rs.getString("SGBVinitPEPT")!=null){ SGBVinitPEPT=conn.rs.getString("SGBVinitPEPT");}else{ SGBVinitPEPT="";}
    if(conn.rs.getString("SGBVcompPEP0_9")!=null){ SGBVcompPEP0_9=conn.rs.getString("SGBVcompPEP0_9");}else{ SGBVcompPEP0_9="";}
    if(conn.rs.getString("SGBVcompPEP10_17")!=null){ SGBVcompPEP10_17=conn.rs.getString("SGBVcompPEP10_17");}else{ SGBVcompPEP10_17="";}
    if(conn.rs.getString("SGBVcompPEP18_49")!=null){ SGBVcompPEP18_49=conn.rs.getString("SGBVcompPEP18_49");}else{ SGBVcompPEP18_49="";}
    if(conn.rs.getString("SGBVcompPEP50")!=null){ SGBVcompPEP50=conn.rs.getString("SGBVcompPEP50");}else{ SGBVcompPEP50="";}
    if(conn.rs.getString("SGBVcompPEPT")!=null){ SGBVcompPEPT=conn.rs.getString("SGBVcompPEPT");}else{ SGBVcompPEPT="";}
    if(conn.rs.getString("SGBVPregnant0_9")!=null){ SGBVPregnant0_9=conn.rs.getString("SGBVPregnant0_9");}else{ SGBVPregnant0_9="";} 
    if(conn.rs.getString("SGBVPregnant10_17")!=null){ SGBVPregnant10_17=conn.rs.getString("SGBVPregnant10_17");}else{ SGBVPregnant10_17="";}
    if(conn.rs.getString("SGBVPregnant18_49")!=null){ SGBVPregnant18_49=conn.rs.getString("SGBVPregnant18_49");}else{ SGBVPregnant18_49="";}
    if(conn.rs.getString("SGBVPregnant50")!=null){ SGBVPregnant50=conn.rs.getString("SGBVPregnant50");}else{ SGBVPregnant50="";}
    if(conn.rs.getString("SGBVPregnantT")!=null){ SGBVPregnantT=conn.rs.getString("SGBVPregnantT");}else{ SGBVPregnantT="";}
    if(conn.rs.getString("SGBVseroconverting0_9")!=null){ SGBVseroconverting0_9=conn.rs.getString("SGBVseroconverting0_9");}else{ SGBVseroconverting0_9="";}
    if(conn.rs.getString("SGBVseroconverting10_17")!=null){ SGBVseroconverting10_17=conn.rs.getString("SGBVseroconverting10_17");}else{ SGBVseroconverting10_17="";}
    if(conn.rs.getString("SGBVseroconverting18_49")!=null){ SGBVseroconverting18_49=conn.rs.getString("SGBVseroconverting18_49");}else{ SGBVseroconverting18_49="";}
    if(conn.rs.getString("SGBVseroconverting50")!=null){ SGBVseroconverting50=conn.rs.getString("SGBVseroconverting50");}else{ SGBVseroconverting50="";}
    if(conn.rs.getString("SGBVseroconvertingT")!=null){ SGBVseroconvertingT=conn.rs.getString("SGBVseroconvertingT");}else{ SGBVseroconvertingT="";}
    if(conn.rs.getString("SGBVsurvivors0_9")!=null){ SGBVsurvivors0_9=conn.rs.getString("SGBVsurvivors0_9");}else{ SGBVsurvivors0_9="";}
    if(conn.rs.getString("SGBVsurvivors10_17")!=null){ SGBVsurvivors10_17=conn.rs.getString("SGBVsurvivors10_17");}else{ SGBVsurvivors10_17="";}
    if(conn.rs.getString("SGBVsurvivors18_49")!=null){ SGBVsurvivors18_49=conn.rs.getString("SGBVsurvivors18_49");}else{ SGBVsurvivors18_49="";}
    if(conn.rs.getString("SGBVsurvivors50")!=null){ SGBVsurvivors50=conn.rs.getString("SGBVsurvivors50");}else{ SGBVsurvivors50="";}
    if(conn.rs.getString("SGBVsurvivorsT")!=null){ SGBVsurvivorsT=conn.rs.getString("SGBVsurvivorsT");}else{ SGBVsurvivorsT="";}
 
    
  //==================================================PAC====================================================
    

    
     if(conn.rs.getString("PAC10_19")!=null){ PAC10_19=conn.rs.getString("PAC10_19");}else{ PAC10_19="";}
    if(conn.rs.getString("PACT")!=null){ PACT=conn.rs.getString("PACT");}else{ PACT="";}
    
    
    	
                                        
//=================================================CHANIS WEIGHT FOR AGE========================================================================

    if(conn.rs.getString("CHANIS0_5NormalweightF")!=null){ CHANIS0_5NormalweightF=conn.rs.getString("CHANIS0_5NormalweightF");}else{ CHANIS0_5NormalweightF="";}
    if(conn.rs.getString("CHANIS0_5NormalweightM")!=null){ CHANIS0_5NormalweightM=conn.rs.getString("CHANIS0_5NormalweightM");}else{ CHANIS0_5NormalweightM="";}
    if(conn.rs.getString("CHANIS0_5NormalweightT")!=null){ CHANIS0_5NormalweightT=conn.rs.getString("CHANIS0_5NormalweightT");}else{ CHANIS0_5NormalweightT="";}
    if(conn.rs.getString("CHANIS0_5UnderweightF")!=null){ CHANIS0_5UnderweightF=conn.rs.getString("CHANIS0_5UnderweightF");}else{ CHANIS0_5UnderweightF="";}
    if(conn.rs.getString("CHANIS0_5UnderweightM")!=null){ CHANIS0_5UnderweightM=conn.rs.getString("CHANIS0_5UnderweightM");}else{ CHANIS0_5UnderweightM="";}
    if(conn.rs.getString("CHANIS0_5UnderweightT")!=null){ CHANIS0_5UnderweightT=conn.rs.getString("CHANIS0_5UnderweightT");}else{ CHANIS0_5UnderweightT="";}
    if(conn.rs.getString("CHANIS0_5sevUnderweightF")!=null){ CHANIS0_5sevUnderweightF=conn.rs.getString("CHANIS0_5sevUnderweightF");}else{ CHANIS0_5sevUnderweightF="";}
    if(conn.rs.getString("CHANIS0_5sevUnderweightM")!=null){ CHANIS0_5sevUnderweightM=conn.rs.getString("CHANIS0_5sevUnderweightM");}else{ CHANIS0_5sevUnderweightM="";}
    if(conn.rs.getString("CHANIS0_5sevUnderweightT")!=null){ CHANIS0_5sevUnderweightT=conn.rs.getString("CHANIS0_5sevUnderweightT");}else{ CHANIS0_5sevUnderweightT="";}
    if(conn.rs.getString("CHANIS0_5OverweightF")!=null){ CHANIS0_5OverweightF=conn.rs.getString("CHANIS0_5OverweightF");}else{ CHANIS0_5OverweightF="";}
    if(conn.rs.getString("CHANIS0_5OverweightM")!=null){ CHANIS0_5OverweightM=conn.rs.getString("CHANIS0_5OverweightM");}else{ CHANIS0_5OverweightM="";}
    if(conn.rs.getString("CHANIS0_5OverweightT")!=null){ CHANIS0_5OverweightT=conn.rs.getString("CHANIS0_5OverweightT");}else{ CHANIS0_5OverweightT="";}
    if(conn.rs.getString("CHANIS0_5ObeseF")!=null){ CHANIS0_5ObeseF=conn.rs.getString("CHANIS0_5ObeseF");}else{ CHANIS0_5ObeseF="";}
    if(conn.rs.getString("CHANIS0_5ObeseM")!=null){ CHANIS0_5ObeseM=conn.rs.getString("CHANIS0_5ObeseM");}else{ CHANIS0_5ObeseM="";}
    if(conn.rs.getString("CHANIS0_5ObeseT")!=null){ CHANIS0_5ObeseT=conn.rs.getString("CHANIS0_5ObeseT");}else{ CHANIS0_5ObeseT="";}
    if(conn.rs.getString("CHANIS0_5TWF")!=null){ CHANIS0_5TWF=conn.rs.getString("CHANIS0_5TWF");}else{ CHANIS0_5TWF="";}
    if(conn.rs.getString("CHANIS0_5TWM")!=null){ CHANIS0_5TWM=conn.rs.getString("CHANIS0_5TWM");}else{ CHANIS0_5TWM="";}
    if(conn.rs.getString("CHANIS0_5TW")!=null){ CHANIS0_5TW=conn.rs.getString("CHANIS0_5TW");}else{ CHANIS0_5TW="";}
    if(conn.rs.getString("CHANIS6_23NormalweightF")!=null){ CHANIS6_23NormalweightF=conn.rs.getString("CHANIS6_23NormalweightF");}else{ CHANIS6_23NormalweightF="";}
    if(conn.rs.getString("CHANIS6_23NormalweightM")!=null){ CHANIS6_23NormalweightM=conn.rs.getString("CHANIS6_23NormalweightM");}else{ CHANIS6_23NormalweightM="";}
    if(conn.rs.getString("CHANIS6_23NormalweightT")!=null){ CHANIS6_23NormalweightT=conn.rs.getString("CHANIS6_23NormalweightT");}else{ CHANIS6_23NormalweightT="";}
    if(conn.rs.getString("CHANIS6_23UnderweightF")!=null){ CHANIS6_23UnderweightF=conn.rs.getString("CHANIS6_23UnderweightF");}else{ CHANIS6_23UnderweightF="";}
    if(conn.rs.getString("CHANIS6_23UnderweightM")!=null){ CHANIS6_23UnderweightM=conn.rs.getString("CHANIS6_23UnderweightM");}else{ CHANIS6_23UnderweightM="";}
    if(conn.rs.getString("CHANIS6_23UnderweightT")!=null){ CHANIS6_23UnderweightT=conn.rs.getString("CHANIS6_23UnderweightT");}else{ CHANIS6_23UnderweightT="";}
    if(conn.rs.getString("CHANIS6_23sevUnderweightF")!=null){ CHANIS6_23sevUnderweightF=conn.rs.getString("CHANIS6_23sevUnderweightF");}else{ CHANIS6_23sevUnderweightF="";}
    if(conn.rs.getString("CHANIS6_23sevUnderweightM")!=null){ CHANIS6_23sevUnderweightM=conn.rs.getString("CHANIS6_23sevUnderweightM");}else{ CHANIS6_23sevUnderweightM="";}
    if(conn.rs.getString("CHANIS6_23sevUnderweightT")!=null){ CHANIS6_23sevUnderweightT=conn.rs.getString("CHANIS6_23sevUnderweightT");}else{ CHANIS6_23sevUnderweightT="";}
    if(conn.rs.getString("CHANIS6_23OverweightF")!=null){ CHANIS6_23OverweightF=conn.rs.getString("CHANIS6_23OverweightF");}else{ CHANIS6_23OverweightF="";}
    if(conn.rs.getString("CHANIS6_23OverweightM")!=null){ CHANIS6_23OverweightM=conn.rs.getString("CHANIS6_23OverweightM");}else{ CHANIS6_23OverweightM="";}
    if(conn.rs.getString("CHANIS6_23OverweightT")!=null){ CHANIS6_23OverweightT=conn.rs.getString("CHANIS6_23OverweightT");}else{ CHANIS6_23OverweightT="";}
    if(conn.rs.getString("CHANIS6_23ObeseF")!=null){ CHANIS6_23ObeseF=conn.rs.getString("CHANIS6_23ObeseF");}else{ CHANIS6_23ObeseF="";}
    if(conn.rs.getString("CHANIS6_23ObeseM")!=null){ CHANIS6_23ObeseM=conn.rs.getString("CHANIS6_23ObeseM");}else{ CHANIS6_23ObeseM="";}
    if(conn.rs.getString("CHANIS6_23ObeseT")!=null){ CHANIS6_23ObeseT=conn.rs.getString("CHANIS6_23ObeseT");}else{ CHANIS6_23ObeseT="";}
    if(conn.rs.getString("CHANIS6_23TWF")!=null){ CHANIS6_23TWF=conn.rs.getString("CHANIS6_23TWF");}else{ CHANIS6_23TWF="";}
    if(conn.rs.getString("CHANIS6_23TWM")!=null){ CHANIS6_23TWM=conn.rs.getString("CHANIS6_23TWM");}else{ CHANIS6_23TWM="";}
    if(conn.rs.getString("CHANIS6_23TW")!=null){ CHANIS6_23TW=conn.rs.getString("CHANIS6_23TW");}else{ CHANIS6_23TW="";}
    if(conn.rs.getString("CHANIS24_59NormalweightF")!=null){ CHANIS24_59NormalweightF=conn.rs.getString("CHANIS24_59NormalweightF");}else{ CHANIS24_59NormalweightF="";}
    if(conn.rs.getString("CHANIS24_59NormalweightM")!=null){ CHANIS24_59NormalweightM=conn.rs.getString("CHANIS24_59NormalweightM");}else{ CHANIS24_59NormalweightM="";}
    if(conn.rs.getString("CHANIS24_59NormalweightT")!=null){ CHANIS24_59NormalweightT=conn.rs.getString("CHANIS24_59NormalweightT");}else{ CHANIS24_59NormalweightT="";}
    if(conn.rs.getString("CHANIS24_59UnderweightF")!=null){ CHANIS24_59UnderweightF=conn.rs.getString("CHANIS24_59UnderweightF");}else{ CHANIS24_59UnderweightF="";}
    if(conn.rs.getString("CHANIS24_59UnderweightM")!=null){ CHANIS24_59UnderweightM=conn.rs.getString("CHANIS24_59UnderweightM");}else{ CHANIS24_59UnderweightM="";}
    if(conn.rs.getString("CHANIS24_59UnderweightT")!=null){ CHANIS24_59UnderweightT=conn.rs.getString("CHANIS24_59UnderweightT");}else{ CHANIS24_59UnderweightT="";}
    if(conn.rs.getString("CHANIS24_59sevUnderweightF")!=null){ CHANIS24_59sevUnderweightF=conn.rs.getString("CHANIS24_59sevUnderweightF");}else{ CHANIS24_59sevUnderweightF="";}
    if(conn.rs.getString("CHANIS24_59sevUnderweightM")!=null){ CHANIS24_59sevUnderweightM=conn.rs.getString("CHANIS24_59sevUnderweightM");}else{ CHANIS24_59sevUnderweightM="";}
    if(conn.rs.getString("CHANIS24_59sevUnderweightT")!=null){ CHANIS24_59sevUnderweightT=conn.rs.getString("CHANIS24_59sevUnderweightT");}else{ CHANIS24_59sevUnderweightT="";}
    if(conn.rs.getString("CHANIS24_59OverweightF")!=null){ CHANIS24_59OverweightF=conn.rs.getString("CHANIS24_59OverweightF");}else{ CHANIS24_59OverweightF="";}
    if(conn.rs.getString("CHANIS24_59OverweightM")!=null){ CHANIS24_59OverweightM=conn.rs.getString("CHANIS24_59OverweightM");}else{ CHANIS24_59OverweightM="";}
     if(conn.rs.getString("CHANIS24_59OverweightT")!=null){ CHANIS24_59OverweightT=conn.rs.getString("CHANIS24_59OverweightT");}else{ CHANIS24_59OverweightT="";}
    if(conn.rs.getString("CHANIS24_59ObeseF")!=null){ CHANIS24_59ObeseF=conn.rs.getString("CHANIS24_59ObeseF");}else{ CHANIS24_59ObeseF="";}
    if(conn.rs.getString("CHANIS24_59ObeseM")!=null){ CHANIS24_59ObeseM=conn.rs.getString("CHANIS24_59ObeseM");}else{ CHANIS24_59ObeseM="";}
    if(conn.rs.getString("CHANIS24_59ObeseT")!=null){ CHANIS24_59ObeseT=conn.rs.getString("CHANIS24_59ObeseT");}else{ CHANIS24_59ObeseT="";}
    if(conn.rs.getString("CHANIS24_59TWF")!=null){ CHANIS24_59TWF=conn.rs.getString("CHANIS24_59TWF");}else{ CHANIS24_59TWF="";}
    if(conn.rs.getString("CHANIS24_59TWM")!=null){ CHANIS24_59TWM=conn.rs.getString("CHANIS24_59TWM");}else{ CHANIS24_59TWM="";}
    if(conn.rs.getString("CHANIS24_59TW")!=null){ CHANIS24_59TW=conn.rs.getString("CHANIS24_59TW");}else{ CHANIS24_59TW="";}
    if(conn.rs.getString("CHANISMUACNormalF")!=null){ CHANISMUACNormalF=conn.rs.getString("CHANISMUACNormalF");}else{ CHANISMUACNormalF="";}
    if(conn.rs.getString("CHANISMUACNormalM")!=null){ CHANISMUACNormalM=conn.rs.getString("CHANISMUACNormalM");}else{ CHANISMUACNormalM="";}
    if(conn.rs.getString("CHANISMUACNormalT")!=null){ CHANISMUACNormalT=conn.rs.getString("CHANISMUACNormalT");}else{ CHANISMUACNormalT="";}
    if(conn.rs.getString("CHANISMUACModerateF")!=null){ CHANISMUACModerateF=conn.rs.getString("CHANISMUACModerateF");}else{ CHANISMUACModerateF="";}
    if(conn.rs.getString("CHANISMUACModerateM")!=null){ CHANISMUACModerateM=conn.rs.getString("CHANISMUACModerateM");}else{ CHANISMUACModerateM="";}
    if(conn.rs.getString("CHANISMUACModerateT")!=null){ CHANISMUACModerateT=conn.rs.getString("CHANISMUACModerateT");}else{ CHANISMUACModerateT="";}
    if(conn.rs.getString("CHANISMUACSevereF")!=null){ CHANISMUACSevereF=conn.rs.getString("CHANISMUACSevereF");}else{ CHANISMUACSevereF="";}
    if(conn.rs.getString("CHANISMUACSevereM")!=null){ CHANISMUACSevereM=conn.rs.getString("CHANISMUACSevereM");}else{ CHANISMUACSevereM="";}
    if(conn.rs.getString("CHANISMUACSevereT")!=null){ CHANISMUACSevereT=conn.rs.getString("CHANISMUACSevereT");}else{ CHANISMUACSevereT="";}
    if(conn.rs.getString("CHANISMUACMeasuredF")!=null){ CHANISMUACMeasuredF=conn.rs.getString("CHANISMUACMeasuredF");}else{ CHANISMUACMeasuredF="";}
    if(conn.rs.getString("CHANISMUACMeasuredM")!=null){ CHANISMUACMeasuredM=conn.rs.getString("CHANISMUACMeasuredM");}else{ CHANISMUACMeasuredM="";}
    if(conn.rs.getString("CHANISMUACMeasuredT")!=null){ CHANISMUACMeasuredT=conn.rs.getString("CHANISMUACMeasuredT");}else{ CHANISMUACMeasuredT="";}

    //=================================================================CHANIS HEIGHT=======================================================
    
 
    if(conn.rs.getString("CHANIS0_5NormalHeightF")!=null){ CHANIS0_5NormalHeightF=conn.rs.getString("CHANIS0_5NormalHeightF");}else{ CHANIS0_5NormalHeightF="";}
    if(conn.rs.getString("CHANIS0_5NormalHeightM")!=null){ CHANIS0_5NormalHeightM=conn.rs.getString("CHANIS0_5NormalHeightM");}else{ CHANIS0_5NormalHeightM="";}
    if(conn.rs.getString("CHANIS0_5NormalHeightM")!=null){ CHANIS0_5NormalHeightM=conn.rs.getString("CHANIS0_5NormalHeightM");}else{ CHANIS0_5NormalHeightM="";}
    if(conn.rs.getString("CHANIS0_5NormalHeightT")!=null){ CHANIS0_5NormalHeightT=conn.rs.getString("CHANIS0_5NormalHeightT");}else{ CHANIS0_5NormalHeightT="";}
    if(conn.rs.getString("CHANIS0_5StuntedF")!=null){ CHANIS0_5StuntedF=conn.rs.getString("CHANIS0_5StuntedF");}else{ CHANIS0_5StuntedF="";}
    if(conn.rs.getString("CHANIS0_5StuntedM")!=null){ CHANIS0_5StuntedM=conn.rs.getString("CHANIS0_5StuntedM");}else{ CHANIS0_5StuntedM="";}
    if(conn.rs.getString("CHANIS0_5StuntedT")!=null){ CHANIS0_5StuntedT=conn.rs.getString("CHANIS0_5StuntedT");}else{ CHANIS0_5StuntedT="";}
    if(conn.rs.getString("CHANIS0_5sevStuntedF")!=null){ CHANIS0_5sevStuntedF=conn.rs.getString("CHANIS0_5sevStuntedF");}else{ CHANIS0_5sevStuntedF="";}
    if(conn.rs.getString("CHANIS0_5sevStuntedM")!=null){ CHANIS0_5sevStuntedM=conn.rs.getString("CHANIS0_5sevStuntedM");}else{ CHANIS0_5sevStuntedM="";}
    if(conn.rs.getString("CHANIS0_5sevStuntedT")!=null){ CHANIS0_5sevStuntedT=conn.rs.getString("CHANIS0_5sevStuntedT");}else{ CHANIS0_5sevStuntedT="";}
    if(conn.rs.getString("CHANIS0_5TMeasF")!=null){ CHANIS0_5TMeasF=conn.rs.getString("CHANIS0_5TMeasF");}else{ CHANIS0_5TMeasF="";}
    if(conn.rs.getString("CHANIS0_5TMeasM")!=null){ CHANIS0_5TMeasM=conn.rs.getString("CHANIS0_5TMeasM");}else{ CHANIS0_5TMeasM="";}
    if(conn.rs.getString("CHANIS0_5TMeas")!=null){ CHANIS0_5TMeas=conn.rs.getString("CHANIS0_5TMeas");}else{ CHANIS0_5TMeas="";}
    if(conn.rs.getString("CHANIS6_23NormalHeightF")!=null){ CHANIS6_23NormalHeightF=conn.rs.getString("CHANIS6_23NormalHeightF");}else{ CHANIS6_23NormalHeightF="";}
    if(conn.rs.getString("CHANIS6_23NormalHeightM")!=null){ CHANIS6_23NormalHeightM=conn.rs.getString("CHANIS6_23NormalHeightM");}else{ CHANIS6_23NormalHeightM="";}
    if(conn.rs.getString("CHANIS6_23NormalHeightT")!=null){ CHANIS6_23NormalHeightT=conn.rs.getString("CHANIS6_23NormalHeightT");}else{ CHANIS6_23NormalHeightT="";}
    if(conn.rs.getString("CHANIS6_23StuntedF")!=null){ CHANIS6_23StuntedF=conn.rs.getString("CHANIS6_23StuntedF");}else{ CHANIS6_23StuntedF="";}
    if(conn.rs.getString("CHANIS6_23StuntedM")!=null){ CHANIS6_23StuntedM=conn.rs.getString("CHANIS6_23StuntedM");}else{ CHANIS6_23StuntedM="";}
    if(conn.rs.getString("CHANIS6_23StuntedT")!=null){ CHANIS6_23StuntedT=conn.rs.getString("CHANIS6_23StuntedT");}else{ CHANIS6_23StuntedT="";}
    if(conn.rs.getString("CHANIS6_23sevStuntedF")!=null){ CHANIS6_23sevStuntedF=conn.rs.getString("CHANIS6_23sevStuntedF");}else{ CHANIS6_23sevStuntedF="";}
    if(conn.rs.getString("CHANIS6_23sevStuntedM")!=null){ CHANIS6_23sevStuntedM=conn.rs.getString("CHANIS6_23sevStuntedM");}else{ CHANIS6_23sevStuntedM="";}
    if(conn.rs.getString("CHANIS6_23sevStuntedT")!=null){ CHANIS6_23sevStuntedT=conn.rs.getString("CHANIS6_23sevStuntedT");}else{ CHANIS6_23sevStuntedT="";}
    if(conn.rs.getString("CHANIS6_23TMeasF")!=null){ CHANIS6_23TMeasF=conn.rs.getString("CHANIS6_23TMeasF");}else{ CHANIS6_23TMeasF="";}
    if(conn.rs.getString("CHANIS6_23TMeasM")!=null){ CHANIS6_23TMeasM=conn.rs.getString("CHANIS6_23TMeasM");}else{ CHANIS6_23TMeasM="";}
    if(conn.rs.getString("CHANIS6_23TMeas")!=null){ CHANIS6_23TMeas=conn.rs.getString("CHANIS6_23TMeas");}else{ CHANIS6_23TMeas="";}  
    if(conn.rs.getString("CHANIS24_59NormalHeightF")!=null){ CHANIS24_59NormalHeightF=conn.rs.getString("CHANIS24_59NormalHeightF");}else{ CHANIS24_59NormalHeightF="";}
    if(conn.rs.getString("CHANIS24_59NormalHeightM")!=null){ CHANIS24_59NormalHeightM=conn.rs.getString("CHANIS24_59NormalHeightM");}else{ CHANIS24_59NormalHeightM="";}
    if(conn.rs.getString("CHANIS24_59NormalHeightT")!=null){ CHANIS24_59NormalHeightT=conn.rs.getString("CHANIS24_59NormalHeightT");}else{ CHANIS24_59NormalHeightT="";}
    if(conn.rs.getString("CHANIS24_59StuntedF")!=null){ CHANIS24_59StuntedF=conn.rs.getString("CHANIS24_59StuntedF");}else{ CHANIS24_59StuntedF="";}
    if(conn.rs.getString("CHANIS24_59StuntedM")!=null){ CHANIS24_59StuntedM=conn.rs.getString("CHANIS24_59StuntedM");}else{ CHANIS24_59StuntedM="";}
    if(conn.rs.getString("CHANIS24_59StuntedT")!=null){ CHANIS24_59StuntedT=conn.rs.getString("CHANIS24_59StuntedT");}else{ CHANIS24_59StuntedT="";}
    if(conn.rs.getString("CHANIS24_59sevStuntedF")!=null){ CHANIS24_59sevStuntedF=conn.rs.getString("CHANIS24_59sevStuntedF");}else{ CHANIS24_59sevStuntedF="";}
    if(conn.rs.getString("CHANIS24_59sevStuntedM")!=null){ CHANIS24_59sevStuntedM=conn.rs.getString("CHANIS24_59sevStuntedM");}else{ CHANIS24_59sevStuntedM="";}
    if(conn.rs.getString("CHANIS24_59sevStuntedT")!=null){ CHANIS24_59sevStuntedT=conn.rs.getString("CHANIS24_59sevStuntedT");}else{ CHANIS24_59sevStuntedT="";}
    if(conn.rs.getString("CHANIS24_59TMeasF")!=null){ CHANIS24_59TMeasF=conn.rs.getString("CHANIS24_59TMeasF");}else{ CHANIS24_59TMeasF="";}
    if(conn.rs.getString("CHANIS24_59TMeasM")!=null){ CHANIS24_59TMeasM=conn.rs.getString("CHANIS24_59TMeasM");}else{ CHANIS24_59TMeasM="";}
    if(conn.rs.getString("CHANIS24_59TMeas")!=null){ CHANIS24_59TMeas=conn.rs.getString("CHANIS24_59TMeas");}else{ CHANIS24_59TMeas="";}
    if(conn.rs.getString("CHANIS0_59NewVisitsF")!=null){ CHANIS0_59NewVisitsF=conn.rs.getString("CHANIS0_59NewVisitsF");}else{ CHANIS0_59NewVisitsF="";}
    if(conn.rs.getString("CHANIS0_59NewVisitsM")!=null){ CHANIS0_59NewVisitsM=conn.rs.getString("CHANIS0_59NewVisitsM");}else{ CHANIS0_59NewVisitsM="";}
    if(conn.rs.getString("CHANIS0_59NewVisitsT")!=null){ CHANIS0_59NewVisitsT=conn.rs.getString("CHANIS0_59NewVisitsT");}else{ CHANIS0_59NewVisitsT="";}
    if(conn.rs.getString("CHANIS0_59KwashiakorF")!=null){ CHANIS0_59KwashiakorF=conn.rs.getString("CHANIS0_59KwashiakorF");}else{ CHANIS0_59KwashiakorF="";}
    if(conn.rs.getString("CHANIS0_59KwashiakorM")!=null){ CHANIS0_59KwashiakorM=conn.rs.getString("CHANIS0_59KwashiakorM");}else{ CHANIS0_59KwashiakorM="";}
    if(conn.rs.getString("CHANIS0_59KwashiakorT")!=null){ CHANIS0_59KwashiakorT=conn.rs.getString("CHANIS0_59KwashiakorT");}else{ CHANIS0_59KwashiakorT="";}
    if(conn.rs.getString("CHANIS0_59MarasmusF")!=null){ CHANIS0_59MarasmusF=conn.rs.getString("CHANIS0_59MarasmusF");}else{ CHANIS0_59MarasmusF="";}
    if(conn.rs.getString("CHANIS0_59MarasmusM")!=null){ CHANIS0_59MarasmusM=conn.rs.getString("CHANIS0_59MarasmusM");}else{ CHANIS0_59MarasmusM="";}
    if(conn.rs.getString("CHANIS0_59MarasmusT")!=null){ CHANIS0_59MarasmusT=conn.rs.getString("CHANIS0_59MarasmusT");}else{ CHANIS0_59MarasmusT="";}
    if(conn.rs.getString("CHANIS0_59FalgrowthF")!=null){ CHANIS0_59FalgrowthF=conn.rs.getString("CHANIS0_59FalgrowthF");}else{ CHANIS0_59FalgrowthF="";}
    if(conn.rs.getString("CHANIS0_59FalgrowthM")!=null){ CHANIS0_59FalgrowthM=conn.rs.getString("CHANIS0_59FalgrowthM");}else{ CHANIS0_59FalgrowthM="";}
    if(conn.rs.getString("CHANIS0_59FalgrowthT")!=null){ CHANIS0_59FalgrowthT=conn.rs.getString("CHANIS0_59FalgrowthT");}else{ CHANIS0_59FalgrowthT="";}
    if(conn.rs.getString("CHANIS0_59F")!=null){ CHANIS0_59F=conn.rs.getString("CHANIS0_59F");}else{ CHANIS0_59F="";}
    if(conn.rs.getString("CHANIS0_59M")!=null){ CHANIS0_59M=conn.rs.getString("CHANIS0_59M");}else{ CHANIS0_59M="";}
    if(conn.rs.getString("CHANIS0_59T")!=null){ CHANIS0_59T=conn.rs.getString("CHANIS0_59T");}else{ CHANIS0_59T="";}
    if(conn.rs.getString("CHANIS0_5EXCLBreastF")!=null){ CHANIS0_5EXCLBreastF=conn.rs.getString("CHANIS0_5EXCLBreastF");}else{ CHANIS0_5EXCLBreastF="";}
    if(conn.rs.getString("CHANIS0_5EXCLBreastM")!=null){ CHANIS0_5EXCLBreastM=conn.rs.getString("CHANIS0_5EXCLBreastM");}else{ CHANIS0_5EXCLBreastM="";}
    if(conn.rs.getString("CHANIS0_5EXCLBreastT")!=null){ CHANIS0_5EXCLBreastT=conn.rs.getString("CHANIS0_5EXCLBreastT");}else{ CHANIS0_5EXCLBreastT="";}
    if(conn.rs.getString("CHANIS12_59DewormedF")!=null){ CHANIS12_59DewormedF=conn.rs.getString("CHANIS12_59DewormedF");}else{ CHANIS12_59DewormedF="";}
    if(conn.rs.getString("CHANIS12_59DewormedM")!=null){ CHANIS12_59DewormedM=conn.rs.getString("CHANIS12_59DewormedM");}else{ CHANIS12_59DewormedM="";}
    if(conn.rs.getString("CHANIS12_59DewormedT")!=null){ CHANIS12_59DewormedT=conn.rs.getString("CHANIS12_59DewormedT");}else{ CHANIS12_59DewormedT="";}
    if(conn.rs.getString("CHANIS6_23MNPsF")!=null){ CHANIS6_23MNPsF=conn.rs.getString("CHANIS6_23MNPsF");}else{ CHANIS6_23MNPsF="";}
    if(conn.rs.getString("CHANIS6_23MNPsM")!=null){ CHANIS6_23MNPsM=conn.rs.getString("CHANIS6_23MNPsM");}else{ CHANIS6_23MNPsM="";}
    if(conn.rs.getString("CHANIS6_23MNPsT")!=null){ CHANIS6_23MNPsT=conn.rs.getString("CHANIS6_23MNPsT");}else{ CHANIS6_23MNPsT="";}
     if(conn.rs.getString("CHANIS0_59DisabilityF")!=null){ CHANIS0_59DisabilityF=conn.rs.getString("CHANIS0_59DisabilityF");}else{ CHANIS0_59DisabilityF="";}
    if(conn.rs.getString("CHANIS0_59DisabilityM")!=null){ CHANIS0_59DisabilityM=conn.rs.getString("CHANIS0_59DisabilityM");}else{ CHANIS0_59DisabilityM="";}
    if(conn.rs.getString("CHANIS0_59DisabilityT")!=null){ CHANIS0_59DisabilityT=conn.rs.getString("CHANIS0_59DisabilityT");}else{ CHANIS0_59DisabilityT="";}
    
    //================================================Cervical cancer Screening CS====================================================================

 if(conn.rs.getString("CCSVVH24")!=null){ CCSVVH24=conn.rs.getString("CCSVVH24");}else{ CCSVVH24="";}
    if(conn.rs.getString("CCSVVH25_49")!=null){ CCSVVH25_49=conn.rs.getString("CCSVVH25_49");}else{ CCSVVH25_49="";}
    if(conn.rs.getString("CCSVVH50")!=null){ CCSVVH50=conn.rs.getString("CCSVVH50");}else{ CCSVVH50="";}
    if(conn.rs.getString("CCSPAPSMEAR24")!=null){ CCSPAPSMEAR24=conn.rs.getString("CCSPAPSMEAR24");}else{ CCSPAPSMEAR24="";}
    if(conn.rs.getString("CCSPAPSMEAR25_49")!=null){ CCSPAPSMEAR25_49=conn.rs.getString("CCSPAPSMEAR25_49");}else{ CCSPAPSMEAR25_49="";}
    if(conn.rs.getString("CCSPAPSMEAR50")!=null){ CCSPAPSMEAR50=conn.rs.getString("CCSPAPSMEAR50");}else{ CCSPAPSMEAR50="";}
    if(conn.rs.getString("CCSHPV24")!=null){ CCSHPV24=conn.rs.getString("CCSHPV24");}else{ CCSHPV24="";}
    if(conn.rs.getString("CCSHPV25_49")!=null){ CCSHPV25_49=conn.rs.getString("CCSHPV25_49");}else{ CCSHPV25_49="";}
     if(conn.rs.getString("CCSHPV50")!=null){ CCSHPV50=conn.rs.getString("CCSHPV50");}else{ CCSHPV50="";}
    if(conn.rs.getString("CCSVIAVILIPOS24")!=null){ CCSVIAVILIPOS24=conn.rs.getString("CCSVIAVILIPOS24");}else{ CCSVIAVILIPOS24="";}
    if(conn.rs.getString("CCSVIAVILIPOS25_49")!=null){ CCSVIAVILIPOS25_49=conn.rs.getString("CCSVIAVILIPOS25_49");}else{ CCSVIAVILIPOS25_49="";}
    if(conn.rs.getString("CCSVIAVILIPOS50")!=null){ CCSVIAVILIPOS50=conn.rs.getString("CCSVIAVILIPOS50");}else{ CCSVIAVILIPOS50="";}
    if(conn.rs.getString("CCSCYTOLPOS24")!=null){ CCSCYTOLPOS24=conn.rs.getString("CCSCYTOLPOS24");}else{ CCSCYTOLPOS24="";}
    if(conn.rs.getString("CCSCYTOLPOS25_49")!=null){ CCSCYTOLPOS25_49=conn.rs.getString("CCSCYTOLPOS25_49");}else{ CCSCYTOLPOS25_49="";}
    if(conn.rs.getString("CCSCYTOLPOS50")!=null){ CCSCYTOLPOS50=conn.rs.getString("CCSCYTOLPOS50");}else{ CCSCYTOLPOS50="";}
    if(conn.rs.getString("CCSHPVPOS24")!=null){ CCSHPVPOS24=conn.rs.getString("CCSHPVPOS24");}else{ CCSHPVPOS24="";}
    if(conn.rs.getString("CCSHPVPOS25_49")!=null){ CCSHPVPOS25_49=conn.rs.getString("CCSHPVPOS25_49");}else{ CCSHPVPOS25_49="";}
    if(conn.rs.getString("CCSHPVPOS50")!=null){ CCSHPVPOS50=conn.rs.getString("CCSHPVPOS50");}else{ CCSHPVPOS50="";}
    
    if(conn.rs.getString("CCSCryotherapy24")!=null){ CCSCryotherapy24=conn.rs.getString("CCSCryotherapy24");}else{ CCSCryotherapy24="";}
    if(conn.rs.getString("CCSCryotherapy25_49")!=null){ CCSCryotherapy25_49=conn.rs.getString("CCSCryotherapy25_49");}else{ CCSCryotherapy25_49="";}
    if(conn.rs.getString("CCSCryotherapy50")!=null){ CCSCryotherapy50=conn.rs.getString("CCSCryotherapy50");}else{ CCSCryotherapy50="";}
    
    

    if(conn.rs.getString("CCSSUSPICIOUSLES24")!=null){ CCSSUSPICIOUSLES24=conn.rs.getString("CCSSUSPICIOUSLES24");}else{ CCSSUSPICIOUSLES24="";}
    if(conn.rs.getString("CCSSUSPICIOUSLES25_49")!=null){ CCSSUSPICIOUSLES25_49=conn.rs.getString("CCSSUSPICIOUSLES25_49");}else{ CCSSUSPICIOUSLES25_49="";}
    if(conn.rs.getString("CCSSUSPICIOUSLES50")!=null){ CCSSUSPICIOUSLES50=conn.rs.getString("CCSSUSPICIOUSLES50");}else{ CCSSUSPICIOUSLES50="";}
    
    
    if(conn.rs.getString("CCSLEEP24")!=null){ CCSLEEP24=conn.rs.getString("CCSLEEP24");}else{ CCSLEEP24="";}
    if(conn.rs.getString("CCSLEEP25_49")!=null){ CCSLEEP25_49=conn.rs.getString("CCSLEEP25_49");}else{ CCSLEEP25_49="";}
    if(conn.rs.getString("CCSLEEP50")!=null){ CCSLEEP50=conn.rs.getString("CCSLEEP50");}else{ CCSLEEP50="";}
    if(conn.rs.getString("CCSHIVPOSSCREENED24")!=null){ CCSHIVPOSSCREENED24=conn.rs.getString("CCSHIVPOSSCREENED24");}else{ CCSHIVPOSSCREENED24="";}
    if(conn.rs.getString("CCSHIVPOSSCREENED25_49")!=null){ CCSHIVPOSSCREENED25_49=conn.rs.getString("CCSHIVPOSSCREENED25_49");}else{ CCSHIVPOSSCREENED25_49="";}
    if(conn.rs.getString("CCSHIVPOSSCREENED50")!=null){ CCSHIVPOSSCREENED50=conn.rs.getString("CCSHIVPOSSCREENED50");}else{ CCSHIVPOSSCREENED50="";}

 //===========================================================PNC===========================================================   
    
    if(conn.rs.getString("PNCBreastExam")!=null){ PNCBreastExam=conn.rs.getString("PNCBreastExam");}else{ PNCBreastExam="";}
    if(conn.rs.getString("PNCCounselled")!=null){ PNCCounselled=conn.rs.getString("PNCCounselled");}else{ PNCCounselled="";}
    if(conn.rs.getString("PNCFistula")!=null){ PNCFistula=conn.rs.getString("PNCFistula");}else{ PNCFistula="";}
    if(conn.rs.getString("PNCExerNegative")!=null){ PNCExerNegative=conn.rs.getString("PNCExerNegative");}else{ PNCExerNegative="";}
    if(conn.rs.getString("PNCExerPositive")!=null){ PNCExerPositive=conn.rs.getString("PNCExerPositive");}else{ PNCExerPositive="";}
    if(conn.rs.getString("PNCCCSsuspect")!=null){ PNCCCSsuspect=conn.rs.getString("PNCCCSsuspect");}else{ PNCCCSsuspect="";}
    if(conn.rs.getString("PNCmotherspostpartum2_3")!=null){ PNCmotherspostpartum2_3=conn.rs.getString("PNCmotherspostpartum2_3");}else{ PNCmotherspostpartum2_3="";}
    if(conn.rs.getString("PNCmotherspostpartum6")!=null){ PNCmotherspostpartum6=conn.rs.getString("PNCmotherspostpartum6");}else{ PNCmotherspostpartum6="";}
     if(conn.rs.getString("PNCinfantspostpartum2_3")!=null){ PNCinfantspostpartum2_3=conn.rs.getString("PNCinfantspostpartum2_3");}else{ PNCinfantspostpartum2_3="";}
    if(conn.rs.getString("PNCinfantspostpartum6")!=null){ PNCinfantspostpartum6=conn.rs.getString("PNCinfantspostpartum6");}else{ PNCinfantspostpartum6="";}
    if(conn.rs.getString("PNCreferralsfromotherHF")!=null){ PNCreferralsfromotherHF=conn.rs.getString("PNCreferralsfromotherHF");}else{ PNCreferralsfromotherHF="";}
    if(conn.rs.getString("PNCreferralsfromotherCU")!=null){ PNCreferralsfromotherCU=conn.rs.getString("PNCreferralsfromotherCU");}else{ PNCreferralsfromotherCU="";}
    if(conn.rs.getString("PNCreferralsTootherHF")!=null){ PNCreferralsTootherHF=conn.rs.getString("PNCreferralsTootherHF");}else{ PNCreferralsTootherHF="";}
    if(conn.rs.getString("PNCreferralsTootherCU")!=null){ PNCreferralsTootherCU=conn.rs.getString("PNCreferralsTootherCU");}else{ PNCreferralsTootherCU="";}
    
    
  //=====================================================RS=======================================================
    
	
 
    if(conn.rs.getString("RsAssessed")!=null){ RsAssessed=conn.rs.getString("RsAssessed");}else{ RsAssessed="";}
    if(conn.rs.getString("Rstreated")!=null){ Rstreated=conn.rs.getString("Rstreated");}else{ Rstreated="";}
    if(conn.rs.getString("RsRehabilitated")!=null){ RsRehabilitated=conn.rs.getString("RsRehabilitated");}else{ RsRehabilitated="";}
    if(conn.rs.getString("Rsreffered")!=null){ Rsreffered=conn.rs.getString("Rsreffered");}else{ Rsreffered="";}
    if(conn.rs.getString("RsIntergrated")!=null){ RsIntergrated=conn.rs.getString("RsIntergrated");}else{ RsIntergrated="";}
   
    
 //=======================================================MSW======================================================  

 
    if(conn.rs.getString("MSWpscounselling")!=null){ MSWpscounselling=conn.rs.getString("MSWpscounselling");}else{ MSWpscounselling="";}
    if(conn.rs.getString("MSWdrugabuse")!=null){ MSWdrugabuse=conn.rs.getString("MSWdrugabuse");}else{ MSWdrugabuse="";}
    if(conn.rs.getString("MSWMental")!=null){ MSWMental=conn.rs.getString("MSWMental");}else{ MSWMental="";}
    if(conn.rs.getString("MSWAdolescent")!=null){ MSWAdolescent=conn.rs.getString("MSWAdolescent");}else{ MSWAdolescent="";}
    if(conn.rs.getString("MSWPsAsses")!=null){ MSWPsAsses=conn.rs.getString("MSWPsAsses");}else{ MSWPsAsses="";}
    if(conn.rs.getString("MSWsocialinv")!=null){ MSWsocialinv=conn.rs.getString("MSWsocialinv");}else{ MSWsocialinv="";}
    if(conn.rs.getString("MSWsocialRehab")!=null){ MSWsocialRehab=conn.rs.getString("MSWsocialRehab");}else{ MSWsocialRehab="";}
    if(conn.rs.getString("MSWoutreach")!=null){ MSWoutreach=conn.rs.getString("MSWoutreach");}else{ MSWoutreach="";}
    if(conn.rs.getString("MSWreferrals")!=null){ MSWreferrals=conn.rs.getString("MSWreferrals");}else{ MSWreferrals="";}
    if(conn.rs.getString("MSWwaivedpatients")!=null){ MSWwaivedpatients=conn.rs.getString("MSWwaivedpatients");}else{ MSWwaivedpatients="";}
      
    
   //===========================================PS===========================================================
    
    if(conn.rs.getString("PsPWDOPD4")!=null){ PsPWDOPD4=conn.rs.getString("PsPWDOPD4");}else{ PsPWDOPD4="";}
    if(conn.rs.getString("PsPWDOPD5_19")!=null){ PsPWDOPD5_19=conn.rs.getString("PsPWDOPD5_19");}else{ PsPWDOPD5_19="";}
    if(conn.rs.getString("PsPWDOPD20")!=null){ PsPWDOPD20=conn.rs.getString("PsPWDOPD20");}else{ PsPWDOPD20="";}
    if(conn.rs.getString("PsPWDinpatient4")!=null){ PsPWDinpatient4=conn.rs.getString("PsPWDinpatient4");}else{ PsPWDinpatient4="";}
    if(conn.rs.getString("PsPWDinpatient5_19")!=null){ PsPWDinpatient5_19=conn.rs.getString("PsPWDinpatient5_19");}else{ PsPWDinpatient5_19="";}
    if(conn.rs.getString("PsPWDinpatient20")!=null){ PsPWDinpatient20=conn.rs.getString("PsPWDinpatient20");}else{ PsPWDinpatient20="";}
    
    if(conn.rs.getString("PsotherOPD4")!=null){ PsotherOPD4=conn.rs.getString("PsotherOPD4");}else{ PsotherOPD4="";}
    if(conn.rs.getString("PsotherOPD5_19")!=null){ PsotherOPD5_19=conn.rs.getString("PsotherOPD5_19");}else{ PsotherOPD5_19="";}
    if(conn.rs.getString("PsotherOPD20")!=null){ PsotherOPD20=conn.rs.getString("PsotherOPD20");}else{ PsotherOPD20="";}
    if(conn.rs.getString("Psotherinpatient4")!=null){ Psotherinpatient4=conn.rs.getString("Psotherinpatient4");}else{ Psotherinpatient4="";}
    if(conn.rs.getString("Psotherinpatient5_19")!=null){ Psotherinpatient5_19=conn.rs.getString("Psotherinpatient5_19");}else{ Psotherinpatient5_19="";}
    if(conn.rs.getString("Psotherinpatient20")!=null){ Psotherinpatient20=conn.rs.getString("Psotherinpatient20");}else{ Psotherinpatient20="";}
    
    
    if(conn.rs.getString("PsTreatments4")!=null){ PsTreatments4=conn.rs.getString("PsTreatments4");}else{ PsTreatments4="";}
    if(conn.rs.getString("PsTreatments5_19")!=null){ PsTreatments5_19=conn.rs.getString("PsTreatments5_19");}else{ PsTreatments5_19="";}
    if(conn.rs.getString("PsTreatments20")!=null){ PsTreatments20=conn.rs.getString("PsTreatments20");}else{ PsTreatments20="";}
    if(conn.rs.getString("PsAssessed4")!=null){ PsAssessed4=conn.rs.getString("PsAssessed4");}else{ PsAssessed4="";}
    if(conn.rs.getString("PsAssessed5_19")!=null){ PsAssessed5_19=conn.rs.getString("PsAssessed5_19");}else{ PsAssessed5_19="";}
    if(conn.rs.getString("PsAssessed20")!=null){ PsAssessed20=conn.rs.getString("PsAssessed20");}else{ PsAssessed20="";}
    if(conn.rs.getString("PsServices4")!=null){ PsServices4=conn.rs.getString("PsServices4");}else{ PsServices4="";}
    if(conn.rs.getString("PsServices5_19")!=null){ PsServices5_19=conn.rs.getString("PsServices5_19");}else{ PsServices5_19="";}
    if(conn.rs.getString("PsServices20")!=null){ PsServices20=conn.rs.getString("PsServices20");}else{ PsServices20="";}
    if(conn.rs.getString("PsANCCounsel5_19")!=null){ PsANCCounsel5_19=conn.rs.getString("PsANCCounsel5_19");}else{ PsANCCounsel5_19="";}
    if(conn.rs.getString("PsANCCounsel20")!=null){ PsANCCounsel20=conn.rs.getString("PsANCCounsel20");}else{ PsANCCounsel20="";}
    if(conn.rs.getString("PsExercise5_19")!=null){ PsExercise5_19=conn.rs.getString("PsExercise5_19");}else{ PsExercise5_19="";}
    if(conn.rs.getString("PsExercise20")!=null){ PsExercise20=conn.rs.getString("PsExercise20");}else{ PsExercise20="";}
    if(conn.rs.getString("PsFIFcollected5_19")!=null){ PsFIFcollected5_19=conn.rs.getString("PsFIFcollected5_19");}else{ PsFIFcollected5_19="";}
    if(conn.rs.getString("PsFIFcollected20")!=null){ PsFIFcollected20=conn.rs.getString("PsFIFcollected20");}else{ PsFIFcollected20="";}
    if(conn.rs.getString("PsFIFwaived5_19")!=null){ PsFIFwaived5_19=conn.rs.getString("PsFIFwaived5_19");}else{ PsFIFwaived5_19="";}
    if(conn.rs.getString("PsFIFwaived20")!=null){ PsFIFwaived20=conn.rs.getString("PsFIFwaived20");}else{ PsFIFwaived20="";}
    if(conn.rs.getString("PsFIFexempted4")!=null){ PsFIFexempted4=conn.rs.getString("PsFIFexempted4");}else{ PsFIFexempted4="";}
    if(conn.rs.getString("PsFIFexempted5_19")!=null){ PsFIFexempted5_19=conn.rs.getString("PsFIFexempted5_19");}else{ PsFIFexempted5_19="";}
    if(conn.rs.getString("PsFIFexempted20")!=null){ PsFIFexempted20=conn.rs.getString("PsFIFexempted20");}else{ PsFIFexempted20="";}
    if(conn.rs.getString("PsDiasbilitymeeting4")!=null){ PsDiasbilitymeeting4=conn.rs.getString("PsDiasbilitymeeting4");}else{ PsDiasbilitymeeting4="";}
    if(conn.rs.getString("PsDiasbilitymeeting5_19")!=null){ PsDiasbilitymeeting5_19=conn.rs.getString("PsDiasbilitymeeting5_19");}else{ PsDiasbilitymeeting5_19="";}
    if(conn.rs.getString("PsDiasbilitymeeting20")!=null){ PsDiasbilitymeeting20=conn.rs.getString("PsDiasbilitymeeting20");}else{ PsDiasbilitymeeting20="";}
   
    
    
    
    
    
    //================================================================================================================================================
    
    
  
  /**   
          
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
  
  

  
  //=================================================DTC=========================================
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
  
  */ //comments end here
  
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
      
           // FamilyPlanninng=pmct=maternity=vct=dtc="";
         
             
            String ancpmct_ext="";//2016
            String maternity_ext="";//2016
            String sgbv_ext="";//2016
            String FamilyPlanninng_ext="";//2016
            String pac_ext="";//2016
            String chanisheight_ext="";//2016
            String chanisweight_ext="";//2016
            String cervicalcancer_ext="";//2016
            String pnc_ext="";//2016
            String rehabilitation_ext="";//2016
            String msw_ext="";//2016
            String physiotherapy_ext=""; //201605
            
            
            
            FamilyPlanninng_tab+="";
          
            validitychecker+="<p id=\"checkValidity\" hidden=\"hidden\">"+validity+"</p>";

          
             FamilyPlanninng_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> D. Family Planning (Number of clients Issued with contraceptives) </b></legend> "+
              "<table frame='box' border='1'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='3' class='form-actions'><b></b></td> <td class=\"form-actions\"> <b>NEW</b></td> <td class=\"form-actions\"> <b>RE-VISITS</b></td> <td class=\"form-actions\"> <b>TOTAL</b></td> </tr>"               
            
            + "<tr> <td >1</td> <td rowspan='3' >Pills</td> <td>Progestin only pills </td> "
            + "<td > <input "+islocked+" type='text'  name='FPProgestinN'  onblur=\"autosave('FPProgestinN');autosum('FPProgestinN@FPProgestinR','FPProgestinT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPProgestinN' value='"+FPProgestinN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPProgestinR'  onblur=\"autosave('FPProgestinR');autosum('FPProgestinN@FPProgestinR','FPProgestinT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPProgestinR' value='"+FPProgestinR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPProgestinT'   id='FPProgestinT' value='"+FPProgestinT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
            + "<tr> <td >2</td>   <td>Combined Oral Contraceptive pills </td> "
            + "<td > <input "+islocked+" type='text'  name='FPCocN'  onblur=\"autosave('FPCocN');autosum('FPCocN@FPCocR','FPCocT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCocN' value='"+FPCocN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPCocR'  onblur=\"autosave('FPCocR');autosum('FPCocN@FPCocR','FPCocT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCocR' value='"+FPCocR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1' type='text'  name='FPCocT'   id='FPCocT' value='"+FPCocT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                      
            + "<tr> <td >3</td>   <td>Emergency contraceptive pill </td> "
            + "<td > <input "+islocked+" type='text'  name='FPEcpN'  onblur=\"autosave('FPEcpN');autosum('FPEcpN@FPEcpR','FPEcpT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPEcpN' value='"+FPEcpN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPEcpR'  onblur=\"autosave('FPEcpR');autosum('FPCocN@FPEcpR','FPEcpT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPEcpR' value='"+FPEcpR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1' type='text'  name='FPEcpT'   id='FPEcpT' value='"+FPEcpT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
//            + "<tr> <td >4</td> <td>  </td> <td>Injectables </td> "
//            + "<td > <input "+islocked+" type='text'  name='FPINJECTABLESN'  onblur=\"autosave('FPINJECTABLESN');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLESN' value='"+FPINJECTABLESN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
//            + "<td > <input "+islocked+" type='text'  name='FPINJECTABLESR'  onblur=\"autosave('FPINJECTABLESR');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLESR' value='"+FPINJECTABLESR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
//            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPINJECTABLEST' readonly onblur=\"autosave('FPINJECTABLEST');autosum('FPINJECTABLESN@FPINJECTABLESR','FPINJECTABLEST');\" id='FPINJECTABLEST' value='"+FPINJECTABLEST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
//                      
            + "<tr> <td >5</td> <td>  </td> <td>Injectables </td> "
            + "<td > <input "+islocked+" type='text'  name='FPINJECTIONSN'  onblur=\"autosave('FPINJECTIONSN');autosum('FPINJECTIONSN@FPINJECTIONSR','FPINJECTIONST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPINJECTIONSN' value='"+FPINJECTIONSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPINJECTIONSR'  onblur=\"autosave('FPINJECTIONSR');autosum('FPINJECTIONSN@FPINJECTIONSR','FPINJECTIONST');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPINJECTIONSR' value='"+FPINJECTIONSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPINJECTIONST'   id='FPINJECTIONST' value='"+FPINJECTIONST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                
                      
             + "<tr> <td >6</td> <td> Injections </td> <td>Insertion </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDN'  onblur=\"autosave('FPIUCDN');autosum('FPIUCDN@FPIUCDR','FPIUCDT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIUCDN' value='"+FPIUCDN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDR'  onblur=\"autosave('FPIUCDR');autosum('FPIUCDN@FPIUCDR','FPIUCDT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIUCDR' value='"+FPIUCDR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPIUCDT'   id='FPIUCDT' value='"+FPIUCDT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
              + "<tr> <td >7</td> <td> I.U.C.D </td> <td>Insertion </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSN'  onblur=\"autosave('FPIMPLANTSN');autosum('FPIMPLANTSN@FPIMPLANTSR','FPIMPLANTST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIMPLANTSN' value='"+FPIMPLANTSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSR'  onblur=\"autosave('FPIMPLANTSR');autosum('FPIMPLANTSN@FPIMPLANTSR','FPIMPLANTST');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPIMPLANTSR' value='"+FPIMPLANTSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPIMPLANTST'   id='FPIMPLANTST' value='"+FPIMPLANTST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                        
           
            + "<tr> <td >8</td> <td> Implants </td> <td>BTL</td> "
            + "<td > <input "+islocked+" type='text'  name='FPBTLN'  onblur=\"autosave('FPBTLN');autosum('FPBTLN@FPBTLR','FPBTLT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPBTLN' value='"+FPBTLN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPBTLR'  onblur=\"autosave('FPBTLR');autosum('FPBTLN@FPBTLR','FPBTLT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPBTLR' value='"+FPBTLR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPBTLT'   id='FPBTLT' value='"+FPBTLT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                        
             + "<tr> <td >9</td> <td> Sterilization </td> <td>Vasectomy</td> "
            + "<td > <input "+islocked+" type='text'  name='FPVasectomyN'  onblur=\"autosave('FPVasectomyN');autosum('FPVasectomyN@FPVasectomyR','FPVasectomyT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPVasectomyN' value='"+FPVasectomyN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPVasectomyR'  onblur=\"autosave('FPVasectomyR');autosum('FPVasectomyN@FPVasectomyR','FPVasectomyT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPVasectomyR' value='"+FPVasectomyR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1'    type='text'  name='FPVasectomyT'   id='FPVasectomyT' value='"+FPVasectomyT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                                 
            
            + "<tr> <td >10</td> <td rowspan='2'> Condoms </td> <td>No.of clients received (Male condoms)</td> "
            + "<td > <input "+islocked+" type='text'  name='FPCONDOMSMN'  onblur=\"autosave('FPCONDOMSMN');autosum('FPCONDOMSMN@FPCONDOMSFN','FPCONDOMST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCONDOMSMN' value='"+FPCONDOMSMN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"               
                         
            + "<tr> <td >11</td>  <td>No.of clients received (Female condoms)</td> "
            + "<td > <input "+islocked+" type='text'  name='FPCONDOMSFN'  onblur=\"autosave('FPCONDOMSFN');autosum('FPCONDOMSMN@FPCONDOMSFN','FPCONDOMST');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPCONDOMSFN' value='"+FPCONDOMSFN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td ><input readonly tabindex='-1' type='hidden'  name='FPCONDOMST'   id='FPCONDOMST' value='"+FPCONDOMST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> </tr>"            
            
            + "<tr> <td >12</td> <td colspan='2'>Natural Family Planning</td> "
            + "<td > <input "+islocked+" type='text'  name='FPNaturalN'  onblur=\"autosave('FPNaturalN');autosum('FPNaturalN@FPNaturalR','FPNaturalT');autosum('FPProgestinN@FPCocN@FPEcpN@FPINJECTIONSN@FPIUCDN@FPIMPLANTSN@FPBTLN@FPVasectomyN@FPCONDOMST@FPNaturalN','FPCLIENTSN');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPNaturalN' value='"+FPNaturalN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPNaturalR'  onblur=\"autosave('FPNaturalR');autosum('FPNaturalN@FPNaturalR','FPNaturalT');autosum('FPProgestinR@FPCocR@FPEcpR@FPINJECTIONSR@FPIUCDR@FPIMPLANTSR@FPBTLR@FPVasectomyR@FPNaturalR','FPCLIENTSR');autosum('FPCLIENTSN@FPCLIENTSR','FPCLIENTST');\" id='FPNaturalR' value='"+FPNaturalR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPNaturalT'   id='FPNaturalT' value='"+FPNaturalT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
            + "<tr> <td >13</td> <td colspan='2'>Total No of Clients</td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPCLIENTSN'   id='FPCLIENTSN' value='"+FPCLIENTSN+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly tabindex='-1' type='text'  name='FPCLIENTSR'   id='FPCLIENTSR' value='"+FPCLIENTSR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPCLIENTST'   id='FPCLIENTST' value='"+FPCLIENTST+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                      
             + "<tr> <td >14</td> <td colspan='2'>Total adolescent clients (10-14YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT10_14N'  onblur=\"autosave('FPADOLESCENT10_14N');autosum('FPADOLESCENT10_14N@FPADOLESCENT10_14R','FPADOLESCENT10_14T');\" id='FPADOLESCENT10_14N' value='"+FPADOLESCENT10_14N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT10_14R'  onblur=\"autosave('FPADOLESCENT10_14R');autosum('FPADOLESCENT10_14N@FPADOLESCENT10_14R','FPADOLESCENT10_14T');\" id='FPADOLESCENT10_14R' value='"+FPADOLESCENT10_14R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly   tabindex='-1'  type='text'  name='FPADOLESCENT10_14T'   id='FPADOLESCENT10_14T' value='"+FPADOLESCENT10_14T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                       
              + "<tr> <td >15</td> <td colspan='2'>Total adolescent clients (15-19YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT15_19N'  onblur=\"autosave('FPADOLESCENT15_19N');autosum('FPADOLESCENT15_19N@FPADOLESCENT15_19R','FPADOLESCENT15_19T');\" id='FPADOLESCENT15_19N' value='"+FPADOLESCENT15_19N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT15_19R'  onblur=\"autosave('FPADOLESCENT15_19R');autosum('FPADOLESCENT15_19N@FPADOLESCENT15_19R','FPADOLESCENT15_19T');\" id='FPADOLESCENT15_19R' value='"+FPADOLESCENT15_19R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPADOLESCENT15_19T'   id='FPADOLESCENT15_19T' value='"+FPADOLESCENT15_19T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
              + "<tr> <td >16</td> <td colspan='2'>Total youth clients (20-24YRS) receiving FP Services</td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT20_24N'  onblur=\"autosave('FPADOLESCENT20_24N');autosum('FPADOLESCENT20_24N@FPADOLESCENT20_24R','FPADOLESCENT20_24T');\" id='FPADOLESCENT20_24N' value='"+FPADOLESCENT20_24N+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input "+islocked+" type='text'  name='FPADOLESCENT20_24R'  onblur=\"autosave('FPADOLESCENT20_24R');autosum('FPADOLESCENT20_24N@FPADOLESCENT20_24R','FPADOLESCENT20_24T');\" id='FPADOLESCENT20_24R' value='"+FPADOLESCENT20_24R+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > <input readonly  tabindex='-1'   type='text'  name='FPADOLESCENT20_24T'   id='FPADOLESCENT20_24T' value='"+FPADOLESCENT20_24T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
            
             + "<tr> <td >10</td> <td rowspan='2'> Removals </td> <td>I.U.C.D</td> "
            + "<td > <input "+islocked+" type='text'  name='FPIUCDRemoval'  onblur=\"autosave('FPIUCDRemoval');\" id='FPIUCDRemoval' value='"+FPIUCDRemoval+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"               
                         
            + "<tr> <td >11</td>  <td>Implants</td> "
            + "<td > <input "+islocked+" type='text'  name='FPIMPLANTSRemoval'  onblur=\"autosave('FPIMPLANTSRemoval');\" id='FPIMPLANTSRemoval' value='"+FPIMPLANTSRemoval+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td > </td> "
            + "<td > </td> </tr>"            
                      
                      
            + " </table> </fieldset> ";
                 FamilyPlanninng_tab+=FamilyPlanninng_ext;
             // PMCTA_1stVisit_ANC=PMCTA_ReVisit_ANC=PMCTANCClientsT=PMCTHB7=PMCTIPT1=PMCTIPT2=PMCTANCClients4=PMCTITN=MATNormalDelivery=MATCSection="";
                 
           
           
           ancpmct_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;'> A: ANC/PMTCT </b> </legend>"
              + " <table border='1' frame='box' cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
              + "<tr><td colspan='3' class=\"form-actions\"><b> </b></td>"            
              + "<td class=\"form-actions\"> <b>TOTAL </b></td>"
              + "</tr>"
                   
              + "<tr> <td>1</td> <td rowspan='2'> No. of ANC Clients </td> <td>New</td><td> <input type='text' "+islocked+" name='PMCTA_1stVisit_ANC' id='PMCTA_1stVisit_ANC' value='"+PMCTA_1stVisit_ANC+"' autocomplete='off'  onblur=\"autosave('PMCTA_1stVisit_ANC');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>2</td>  <td>Re-Visit</td><td> <input type='text' "+islocked+" name='PMCTA_ReVisit_ANC' id='PMCTA_ReVisit_ANC' value='"+PMCTA_ReVisit_ANC+"' autocomplete='off'  onblur=\"autosave('PMCTA_ReVisit_ANC');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>3</td> <td colspan='2'> No of clients with IPT (1st Dose ) </td><td> <input type='text' "+islocked+" name='PMCTIPT1' id='PMCTIPT1' value='"+PMCTIPT1+"' autocomplete='off'  onblur=\"autosave('PMCTIPT1');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>4</td> <td colspan='2'> No of clients with IPT (2nd Dose ) </td><td> <input type='text' "+islocked+" name='PMCTIPT2' id='PMCTIPT2' value='"+PMCTIPT2+"' autocomplete='off'  onblur=\"autosave('PMCTIPT2');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>5</td> <td colspan='2'> No of clients with  with Hb < 11 g/dl </td><td> <input type='text' "+islocked+" name='PMCTHB11' id='PMCTHB11' value='"+PMCTHB11+"' autocomplete='off'  onblur=\"autosave('PMCTHB11');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>6</td> <td colspan='2'> No of clients completed 4th antenatal care  visit </td><td> <input type='text' "+islocked+" name='PMCTANCClients4' id='PMCTANCClients4' value='"+PMCTANCClients4+"' autocomplete='off'  onblur=\"autosave('PMCTANCClients4');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>7</td> <td colspan='2'> No.LLITNs distributed to under 1 year </td><td> <input type='text' "+islocked+" name='PMCTITN1' id='PMCTITN1' value='"+PMCTITN1+"' autocomplete='off'  onblur=\"autosave('PMCTITN1');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>8</td> <td colspan='2'> No of ITNs distributed to ANC Clients  </td><td> <input type='text' "+islocked+" name='PMCTITN' id='PMCTITN' value='"+PMCTITN+"' autocomplete='off'  onblur=\"autosave('PMCTITN');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>9</td> <td rowspan='2'> No. of  Clients </td> <td>Tested for Syphilis</td><td> <input type='text' "+islocked+" name='PMTCTSYPHILISTES' id='PMTCTSYPHILISTES' value='"+PMTCTSYPHILISTES+"' autocomplete='off'  onblur=\"autosave('PMTCTSYPHILISTES');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>10</td> <td>Positive (+ve)</td><td> <input type='text' "+islocked+" name='PMTCTSYPHILISPOS' id='PMTCTSYPHILISPOS' value='"+PMTCTSYPHILISPOS+"' autocomplete='off'  onblur=\"autosave('PMTCTSYPHILISPOS');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>11</td> <td colspan='2'>No. of mothers counselled on infant feeding options  </td><td> <input type='text' "+islocked+" name='PMTCTCOUNSELLEDFEED' id='PMTCTCOUNSELLEDFEED' value='"+PMTCTCOUNSELLEDFEED+"' autocomplete='off'  onblur=\"autosave('PMTCTCOUNSELLEDFEED');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>12</td> <td colspan='2'>Total women done breast examination  </td><td> <input type='text' "+islocked+" name='PMTCTBREAST' id='PMTCTBREAST' value='"+PMTCTBREAST+"' autocomplete='off'  onblur=\"autosave('PMTCTBREAST');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>13</td> <td colspan='2'>Total women given exercise  </td><td> <input type='text' "+islocked+" name='PMTCTEXERCISE' id='PMTCTEXERCISE' value='"+PMTCTEXERCISE+"' autocomplete='off'  onblur=\"autosave('PMTCTEXERCISE');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>14</td> <td colspan='2'>No. of adolescents (10-14 years) presenting with pregnancy  </td><td> <input type='text' "+islocked+" name='PMTCTPREG10_14' id='PMTCTPREG10_14' value='"+PMTCTPREG10_14+"' autocomplete='off'  onblur=\"autosave('PMTCTPREG10_14');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>15</td> <td colspan='2'>No. of adolescents (15-19 years) presenting with pregnancy  </td><td> <input type='text' "+islocked+" name='PMTCTPREG15_19' id='PMTCTPREG15_19' value='"+PMTCTPREG15_19+"' autocomplete='off'  onblur=\"autosave('PMTCTPREG15_19');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>16</td> <td colspan='2'>No. of clients issued with Iron  </td><td> <input type='text' "+islocked+" name='PMTCTIRON' id='PMTCTIRON' value='"+PMTCTIRON+"' autocomplete='off'  onblur=\"autosave('PMTCTIRON');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>17</td> <td colspan='2'>No. of clients issued with Folic  </td><td> <input type='text' "+islocked+" name='PMTCTFOLIC' id='PMTCTFOLIC' value='"+PMTCTFOLIC+"' autocomplete='off'  onblur=\"autosave('PMTCTFOLIC');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "<tr> <td>18</td> <td colspan='2'>No. of clients issued with Combined Ferrous Folate  </td><td> <input type='text' "+islocked+" name='PMTCTFERROUS' id='PMTCTFERROUS' value='"+PMTCTFERROUS+"' autocomplete='off'  onblur=\"autosave('PMTCTFERROUS');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td></tr> "
              + "</table> </fieldset>";
      
                  ancpmct_tab+=ancpmct_ext;
                  
                  
         //add maternity tab here  
         
        maternity_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> B. Maternity and Delivery </b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='3' class='form-actions'><b></b></td><td  colspan='2' class=\"form-actions\"> <b>Total  </b></td></tr>"               
            + "<tr><td>1.</td><td colspan='2' >Normal Deliveries </td> <td  colspan='2'><input type='text' "+islocked+" name='MATNormalDelivery' id='MATNormalDelivery' value='"+MATNormalDelivery+"' autocomplete='off' onblur=\"autosave('MATNormalDelivery');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>2.</td><td colspan='2' >Caesarian Section </td> <td  colspan='2'><input type='text' "+islocked+" name='MATCSection' id='MATCSection' value='"+MATCSection+"' autocomplete='off' onblur=\"autosave('MATCSection');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>3.</td><td colspan='2' >Breech Delivery </td> <td  colspan='2'><input type='text' "+islocked+" name='MATBreech' id='MATBreech' value='"+MATBreech+"' autocomplete='off' onblur=\"autosave('MATBreech');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>4.</td><td colspan='2' >Assisted Vaginal Delivery  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATAssistedVag' id='MATAssistedVag' value='"+MATAssistedVag+"' autocomplete='off' onblur=\"autosave('MATAssistedVag');autosum('MATNormalDelivery@MATCSection@MATBreech@MATAssistedVag','MATDeliveryT');\" maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>5.</td><td colspan='2' ><b>Total Deliveries </b> </td> <td  colspan='2'><input type='text' tabindex='-1'  readonly='true' name='MATDeliveryT' id='MATDeliveryT'  value='"+MATDeliveryT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            
                + "<tr><td>6.</td><td colspan='2' >Live Births  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATLiveBirth' id='MATLiveBirth' onblur=\"autosave('MATLiveBirth');\" value='"+MATLiveBirth+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>7.</td><td colspan='2' >Fresh still Birth  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATFreshStillBirth' id='MATFreshStillBirth' onblur=\"autosave('MATFreshStillBirth');\" value='"+MATFreshStillBirth+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>8.</td><td colspan='2' >Macerated Still Births  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATMeceratedStillBirth' id='MATMeceratedStillBirth' onblur=\"autosave('MATMeceratedStillBirth');\" value='"+MATMeceratedStillBirth+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>9.</td><td colspan='2' >Birth with Deformities  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATDeformities' id='MATDeformities' onblur=\"autosave('MATDeformities');\" value='"+MATDeformities+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>10.</td><td colspan='2' >No. with Low APGAR Score  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATLowAPGAR' id='MATLowAPGAR' onblur=\"autosave('MATLowAPGAR');\" value='"+MATLowAPGAR+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>11.</td><td colspan='2' >No. of Low birth weight Babies (below 2500 grams)  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATWeight2500' id='MATWeight2500' onblur=\"autosave('MATWeight2500');\" value='"+MATWeight2500+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>12.</td><td colspan='2' >No. of babies given tetracycline at birth  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATTetracycline' id='MATTetracycline' onblur=\"autosave('MATTetracycline');\" value='"+MATTetracycline+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>13.</td><td colspan='2' >Pre-Term babies  </td> <td  colspan='2'><input type='text'  "+islocked+" name='MATPreTerm' id='MATPreTerm' onblur=\"autosave('MATPreTerm');\" value='"+MATPreTerm+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>14.</td><td colspan='2' >No. of babies discharged alive  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATDischargealive' id='MATDischargealive' onblur=\"autosave('MATDischargealive');\" value='"+MATDischargealive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>15.</td><td colspan='2' >No. of Infants initiatied on breastfeeding within 1 hour after birth  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATbreastfeeding1' id='MATbreastfeeding1' onblur=\"autosave('MATbreastfeeding1');\" value='"+MATbreastfeeding1+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>16.</td><td colspan='2' >Total Deliveries from HIV +ve women  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATDeliveriesPos' id='MATDeliveriesPos' onblur=\"autosave('MATDeliveriesPos');\" value='"+MATDeliveriesPos+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>17.</td><td colspan='2' >Neonatal Deaths  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATNeoNatalD' id='MATNeoNatalD' onblur=\"autosave('MATNeoNatalD');\" value='"+MATNeoNatalD+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>18.</td><td colspan='2' >No. of adolescents (10-19YRS) Maternal Deaths  </td> <td  colspan='2'><input "+islocked+" type='text'  name='MATMaternalD10_19' id='MATMaternalD10_19' onblur=\"autosave('MATMaternalD10_19');\" value='"+MATMaternalD10_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>19.</td><td colspan='2' >Maternal Deaths  </td> <td  colspan='2'><input type='text' "+islocked+" name='MATMaternalD' id='MATMaternalD' onblur=\"autosave('MATMaternalD');\" value='"+MATMaternalD+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"
            + "<tr><td>20.</td><td colspan='2' >Maternal deaths audited  </td> <td  colspan='2'><input type='text' "+islocked+"  name='MATMaternalDAudited' id='MATMaternalDAudited' onblur=\"autosave('MATMaternalDAudited');\" value='"+MATMaternalDAudited+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td></tr>"            
            + "<tr> <td colspan='3' class='form-actions'><b>Maternal complications</b></td> <td   class=\"form-actions\"> <b>Alive  </b></td> <td   class=\"form-actions\"> <b>Deaths  </b></td> </tr>"  
            + "<tr><td>21.</td><td colspan='2' >A.P.H (Anter Partum Haemorrhage)  </td> <td ><input "+islocked+" type='text'  name='MATAPHAlive' id='MATAPHAlive' onblur=\"autosave('MATAPHAlive');\" value='"+MATAPHAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATAPHDead' id='MATAPHDead' onblur=\"autosave('MATAPHDead');\" value='"+MATAPHDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>22.</td><td colspan='2' >P.P.H. (Post Partum Haemorrhage)  </td> <td ><input "+islocked+" type='text'  name='MATPPHAlive' id='MATPPHAlive' onblur=\"autosave('MATPPHAlive');\" value='"+MATPPHAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATPPHDead' id='MATPPHDead' onblur=\"autosave('MATPPHDead');\" value='"+MATPPHDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>23.</td><td colspan='2' >Eclampsia  </td> <td ><input "+islocked+" type='text'  name='MATEclampAlive' id='MATEclampAlive' onblur=\"autosave('MATEclampAlive');\" value='"+MATEclampAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATEclampDead' id='MATEclampDead' onblur=\"autosave('MATEclampDead');\" value='"+MATEclampDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>24.</td><td colspan='2' >Ruptured Uterus  </td> <td ><input "+islocked+" type='text'  name='MATRupUtAlive' id='MATRupUtAlive' onblur=\"autosave('MATRupUtAlive');\" value='"+MATRupUtAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input  "+islocked+" type='text'  name='MATRupUtDead' id='MATRupUtDead' onblur=\"autosave('MATRupUtDead');\" value='"+MATRupUtDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>25.</td><td colspan='2' >Obstructed Labour  </td> <td ><input "+islocked+" type='text'  name='MATObstrLaborAlive' id='MATObstrLaborAlive' onblur=\"autosave('MATObstrLaborAlive');\" value='"+MATObstrLaborAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATObstrLaborDead' id='MATObstrLaborDead' onblur=\"autosave('MATObstrLaborDead');\" value='"+MATObstrLaborDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"
            + "<tr><td>26.</td><td colspan='2' >Sepsis  </td> <td ><input "+islocked+" type='text'  name='MATSepsisAlive' id='MATSepsisAlive' onblur=\"autosave('MATSepsisAlive');\" value='"+MATSepsisAlive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> <td ><input "+islocked+" type='text'  name='MATSepsisDead' id='MATSepsisDead' onblur=\"autosave('MATSepsisDead');\" value='"+MATSepsisDead+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"             
            + "<tr> <td colspan='2' class='form-actions'><b></b></td> <td  class='form-actions'><b></b></td>  <td colspan='2'   class=\"form-actions\"> <b>Total  </b></td> </tr>"        
            + "<tr> <td >27</td> <td   rowspan='4'>No. of Referrals</td>  <td  >From Other Health Facility</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFFromOtherFacility' id='MATREFFromOtherFacility' onblur=\"autosave('MATREFFromOtherFacility');\" value='"+MATREFFromOtherFacility+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td >28</td>   <td  >From Community Unit</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFFromCU' id='MATREFFromCU' onblur=\"autosave('MATREFFromCU');\" value='"+MATREFFromCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td >29</td>   <td  >To Other Health Facility</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFToOtherFacility' id='MATREFToOtherFacility' onblur=\"autosave('MATREFToOtherFacility');\" value='"+MATREFToOtherFacility+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + "<tr> <td >30</td>   <td  >To Community Unit</td> <td  colspan='2'><input "+islocked+" type='text'  name='MATREFToCU' id='MATREFToCU' onblur=\"autosave('MATREFToCU');\" value='"+MATREFToCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"        
            + " </table> </fieldset> ";          
                
     maternity_tab+=maternity_ext;
               
      
     
     sgbv_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'> C. Sexual and Gender Based Violence (SGBV) </b></legend> "+
                "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='2' class='form-actions'><b></b></td> <td class=\"form-actions\"> <b>0-9 yrs</b></td> <td class=\"form-actions\"> <b>10-17 yrs</b></td> <td class=\"form-actions\"> <b>18-49 yrs</b></td> <td class='form-actions'> <b>50 yrs</b></td><td class='form-actions'> <b>Total</b> </td> </tr>"               
                  + "<tr> <td >1</td> <td > No. of Rape Cases presenting within 72 hrs </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_0_9' id='SGBVRape72_0_9' onblur=\"autosave('SGBVRape72_0_9');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_10_17' id='SGBVRape72_10_17' onblur=\"autosave('SGBVRape72_10_17');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVRape72_18_49' id='SGBVRape72_18_49' onblur=\"autosave('SGBVRape72_18_49');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVRape72_50' id='SGBVRape72_50' onblur=\"autosave('SGBVRape72_50');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" value='"+SGBVRape72_50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVRape72T' tabindex='-1' readonly onblur=\"autosave('SGBVRape72_50');autosum('SGBVRape72_0_9@SGBVRape72_10_17@SGBVRape72_18_49@SGBVRape72_50','SGBVRape72T');\" id='SGBVRape72T' value='"+SGBVRape72T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                  + "<tr> <td >2</td> <td >No. of Rape Cases initiating PEP </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP0_9' id='SGBVinitPEP0_9' onblur=\"autosave('SGBVinitPEP0_9');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP10_17@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP10_17' id='SGBVinitPEP10_17' onblur=\"autosave('SGBVinitPEP10_17');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVinitPEP18_49' id='SGBVinitPEP18_49' onblur=\"autosave('SGBVinitPEP18_49');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVinitPEP50' id='SGBVinitPEP50' onblur=\"autosave('SGBVinitPEP50');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP18_49@SGBVinitPEP50','SGBVinitPEPT');\" value='"+SGBVinitPEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVinitPEPT' tabindex='-1' readonly onblur=\"autosave('SGBVinitPEP50');autosum('SGBVinitPEP0_9@SGBVinitPEP10_17@SGBVinitPEP10_17@SGBVinitPEP50','SGBVinitPEPT');\" id='SGBVinitPEPT' value='"+SGBVinitPEPT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                  + "<tr> <td >3</td> <td >No. Completed PEP </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP0_9' id='SGBVcompPEP0_9' onblur=\"autosave('SGBVcompPEP0_9');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP10_17@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP10_17' id='SGBVcompPEP10_17' onblur=\"autosave('SGBVcompPEP10_17');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVcompPEP18_49' id='SGBVcompPEP18_49' onblur=\"autosave('SGBVcompPEP18_49');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVcompPEP50' id='SGBVcompPEP50' onblur=\"autosave('SGBVcompPEP50');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP18_49@SGBVcompPEP50','SGBVcompPEPT');\" value='"+SGBVcompPEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVcompPEPT' tabindex='-1' readonly onblur=\"autosave('SGBVcompPEP50');autosum('SGBVcompPEP0_9@SGBVcompPEP10_17@SGBVcompPEP10_17@SGBVcompPEP50','SGBVcompPEPT');\" id='SGBVcompPEPT' value='"+SGBVcompPEPT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
           
                  + "<tr> <td >4</td> <td >No. of Survivors Pregrant 4weeks after Exposure </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant0_9' id='SGBVPregnant0_9' onblur=\"autosave('SGBVPregnant0_9');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant10_17@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant10_17' id='SGBVPregnant10_17' onblur=\"autosave('SGBVPregnant10_17');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVPregnant18_49' id='SGBVPregnant18_49' onblur=\"autosave('SGBVPregnant18_49');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVPregnant50' id='SGBVPregnant50' onblur=\"autosave('SGBVPregnant50');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant18_49@SGBVPregnant50','SGBVPregnantT');\" value='"+SGBVPregnant50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVPregnantT' tabindex='-1' readonly onblur=\"autosave('SGBVPregnant50');autosum('SGBVPregnant0_9@SGBVPregnant10_17@SGBVPregnant10_17@SGBVPregnant50','SGBVPregnantT');\" id='SGBVPregnantT' value='"+SGBVPregnantT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                  + "<tr> <td >5</td> <td > No. of survivors seroconverting 3months after exposure </td>"
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting0_9' id='SGBVseroconverting0_9' onblur=\"autosave('SGBVseroconverting0_9');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting10_17@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting10_17' id='SGBVseroconverting10_17' onblur=\"autosave('SGBVseroconverting10_17');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVseroconverting18_49' id='SGBVseroconverting18_49' onblur=\"autosave('SGBVseroconverting18_49');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVseroconverting50' id='SGBVseroconverting50' onblur=\"autosave('SGBVseroconverting50');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting18_49@SGBVseroconverting50','SGBVseroconvertingT');\" value='"+SGBVseroconverting50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVseroconvertingT' tabindex='-1' readonly onblur=\"autosave('SGBVseroconverting50');autosum('SGBVseroconverting0_9@SGBVseroconverting10_17@SGBVseroconverting10_17@SGBVseroconverting50','SGBVseroconvertingT');\" id='SGBVseroconvertingT' value='"+SGBVseroconvertingT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
             
                  + " <tr> <td >6</td> <td > Total Survivors seen </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors0_9' id='SGBVsurvivors0_9' onblur=\"autosave('SGBVsurvivors0_9');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors10_17@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors0_9+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'>  </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors10_17' id='SGBVsurvivors10_17' onblur=\"autosave('SGBVsurvivors10_17');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors10_17+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
                  + " <td > <input "+islocked+" type='text'  name='SGBVsurvivors18_49' id='SGBVsurvivors18_49' onblur=\"autosave('SGBVsurvivors18_49');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors18_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td>"
                  + " <td >  <input "+islocked+" type='text'  name='SGBVsurvivors50' id='SGBVsurvivors50' onblur=\"autosave('SGBVsurvivors50');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors18_49@SGBVsurvivors50','SGBVsurvivorsT');\" value='"+SGBVsurvivors50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "               
                  + " <td >  <input "+islocked+" type='text'  name='SGBVsurvivorsT' tabindex='-1' readonly onblur=\"autosave('SGBVsurvivors50');autosum('SGBVsurvivors0_9@SGBVsurvivors10_17@SGBVsurvivors10_17@SGBVsurvivors50','SGBVsurvivorsT');\" id='SGBVsurvivorsT' value='"+SGBVsurvivorsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"               
                  + " </table> </fieldset> ";  
     
     sgbv_tab+=sgbv_ext;
   
     
     
     
     
     //PAC
     
     pac_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>E. Post Abortal Care (PAC) Services </b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + "<tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"               
            
            + "<tr> <td >1</td>   <td  >Adolescent (10-19 years) Accessing PAC Services</td> <td  ><input "+islocked+" type='text' onblur=\"autosave('PAC10_19');\"  name='PAC10_19' id='PAC10_19' value='"+PAC10_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"           
            + "<tr> <td >2</td>   <td  >Total Receiving PAC Services</td> <td  ><input "+islocked+" type='text' onblur=\"autosave('PACT');\"  name='PACT' id='PACT' value='"+PACT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'></td> </tr>"           
          + " </table> </fieldset> "; 
    pac_tab+=pac_ext; 
    
    chanisweight_tab+=""; 
    
    chanisweight_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>F. Child Health and Nutrition Information System(CHANIS)</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b>Weight for Age</b></td><td   class=\"form-actions\"> <b>F</b></td><td   class=\"form-actions\"> <b>M  </b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"  
           
            + " <tr> <td >1</td><td rowspan='6' > 0-< 6 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalweightF');autosum('CHANIS0_5NormalweightF@CHANIS0_5NormalweightM','CHANIS0_5NormalweightT');\"  name='CHANIS0_5NormalweightF' id='CHANIS0_5NormalweightF' value='"+CHANIS0_5NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalweightM');autosum('CHANIS0_5NormalweightF@CHANIS0_5NormalweightM','CHANIS0_5NormalweightT');\"  name='CHANIS0_5NormalweightM' id='CHANIS0_5NormalweightM' value='"+CHANIS0_5NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5NormalweightT');autosum('CHANIS0_5NormalweightF@CHANIS0_5NormalweightM','CHANIS0_5NormalweightT');\"  name='CHANIS0_5NormalweightT' id='CHANIS0_5NormalweightT' value='"+CHANIS0_5NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >2</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5UnderweightF');autosum('CHANIS0_5UnderweightF@CHANIS0_5UnderweightM','CHANIS0_5UnderweightT');\"  name='CHANIS0_5UnderweightF' id='CHANIS0_5UnderweightF' value='"+CHANIS0_5UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5UnderweightM');autosum('CHANIS0_5UnderweightF@CHANIS0_5UnderweightM','CHANIS0_5UnderweightT');\"  name='CHANIS0_5UnderweightM' id='CHANIS0_5UnderweightM' value='"+CHANIS0_5UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5UnderweightT');autosum('CHANIS0_5UnderweightF@CHANIS0_5UnderweightM','CHANIS0_5UnderweightT');\"  name='CHANIS0_5UnderweightT' id='CHANIS0_5UnderweightT' value='"+CHANIS0_5UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >3</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevUnderweightF');autosum('CHANIS0_5sevUnderweightF@CHANIS0_5sevUnderweightM','CHANIS0_5sevUnderweightT');\"  name='CHANIS0_5sevUnderweightF' id='CHANIS0_5sevUnderweightF' value='"+CHANIS0_5sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevUnderweightM');autosum('CHANIS0_5sevUnderweightF@CHANIS0_5sevUnderweightM','CHANIS0_5sevUnderweightT');\"  name='CHANIS0_5sevUnderweightM' id='CHANIS0_5sevUnderweightM' value='"+CHANIS0_5sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5sevUnderweightT');autosum('CHANIS0_5sevUnderweightF@CHANIS0_5sevUnderweightM','CHANIS0_5sevUnderweightT');\"  name='CHANIS0_5sevUnderweightT' id='CHANIS0_5sevUnderweightT' value='"+CHANIS0_5sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >4</td><td>Overweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5OverweightF');autosum('CHANIS0_5OverweightF@CHANIS0_5OverweightM','CHANIS0_5OverweightT');\"  name='CHANIS0_5OverweightF' id='CHANIS0_5OverweightF' value='"+CHANIS0_5OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5OverweightM');autosum('CHANIS0_5OverweightF@CHANIS0_5OverweightM','CHANIS0_5OverweightT');\"  name='CHANIS0_5OverweightM' id='CHANIS0_5OverweightM' value='"+CHANIS0_5OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5OverweightT');autosum('CHANIS0_5OverweightF@CHANIS0_5OverweightM','CHANIS0_5OverweightT');\"  name='CHANIS0_5OverweightT' id='CHANIS0_5OverweightT' value='"+CHANIS0_5OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >5</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5ObeseF');autosum('CHANIS0_5ObeseF@CHANIS0_5ObeseM','CHANIS0_5ObeseT');\"  name='CHANIS0_5ObeseF' id='CHANIS0_5ObeseF' value='"+CHANIS0_5ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5ObeseM');autosum('CHANIS0_5ObeseF@CHANIS0_5ObeseM','CHANIS0_5ObeseT');\"  name='CHANIS0_5ObeseM' id='CHANIS0_5ObeseM' value='"+CHANIS0_5ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5ObeseT');autosum('CHANIS0_5ObeseF@CHANIS0_5ObeseM','CHANIS0_5ObeseT');\"  name='CHANIS0_5ObeseT' id='CHANIS0_5ObeseT' value='"+CHANIS0_5ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >6</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TWF');autosum('CHANIS0_5TWF@CHANIS0_5TWM','CHANIS0_5TW');\"  name='CHANIS0_5TWF' id='CHANIS0_5TWF' value='"+CHANIS0_5TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TWM');autosum('CHANIS0_5TWF@CHANIS0_5TWM','CHANIS0_5TW');\"  name='CHANIS0_5TWM' id='CHANIS0_5TWM' value='"+CHANIS0_5TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5TW');autosum('CHANIS0_5TWF@CHANIS0_5TWM','CHANIS0_5TW');\"  name='CHANIS0_5TW' id='CHANIS0_5TW' value='"+CHANIS0_5TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
            
                  
                  
            + " <tr> <td >7</td><td rowspan='6' > 6-23 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalweightF');autosum('CHANIS6_23NormalweightF@CHANIS6_23NormalweightM','CHANIS6_23NormalweightT');\"  name='CHANIS6_23NormalweightF' id='CHANIS6_23NormalweightF' value='"+CHANIS6_23NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalweightM');autosum('CHANIS6_23NormalweightF@CHANIS6_23NormalweightM','CHANIS6_23NormalweightT');\"  name='CHANIS6_23NormalweightM' id='CHANIS6_23NormalweightM' value='"+CHANIS6_23NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23NormalweightT');autosum('CHANIS6_23NormalweightF@CHANIS6_23NormalweightM','CHANIS6_23NormalweightT');\"  name='CHANIS6_23NormalweightT' id='CHANIS6_23NormalweightT' value='"+CHANIS6_23NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >8</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23UnderweightF');autosum('CHANIS6_23UnderweightF@CHANIS6_23UnderweightM','CHANIS6_23UnderweightT');\"  name='CHANIS6_23UnderweightF' id='CHANIS6_23UnderweightF' value='"+CHANIS6_23UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23UnderweightM');autosum('CHANIS6_23UnderweightF@CHANIS6_23UnderweightM','CHANIS6_23UnderweightT');\"  name='CHANIS6_23UnderweightM' id='CHANIS6_23UnderweightM' value='"+CHANIS6_23UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23UnderweightT');autosum('CHANIS6_23UnderweightF@CHANIS6_23UnderweightM','CHANIS6_23UnderweightT');\"  name='CHANIS6_23UnderweightT' id='CHANIS6_23UnderweightT' value='"+CHANIS6_23UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >9</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevUnderweightF');autosum('CHANIS6_23sevUnderweightF@CHANIS6_23sevUnderweightM','CHANIS6_23sevUnderweightT');\"  name='CHANIS6_23sevUnderweightF' id='CHANIS6_23sevUnderweightF' value='"+CHANIS6_23sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevUnderweightM');autosum('CHANIS6_23sevUnderweightF@CHANIS6_23sevUnderweightM','CHANIS6_23sevUnderweightT');\"  name='CHANIS6_23sevUnderweightM' id='CHANIS6_23sevUnderweightM' value='"+CHANIS6_23sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23sevUnderweightT');autosum('CHANIS6_23sevUnderweightF@CHANIS6_23sevUnderweightM','CHANIS6_23sevUnderweightT');\"  name='CHANIS6_23sevUnderweightT' id='CHANIS6_23sevUnderweightT' value='"+CHANIS6_23sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >10</td><td>Overweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23OverweightF');autosum('CHANIS6_23OverweightF@CHANIS6_23OverweightM','CHANIS6_23OverweightT');\"  name='CHANIS6_23OverweightF' id='CHANIS6_23OverweightF' value='"+CHANIS6_23OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23OverweightM');autosum('CHANIS6_23OverweightF@CHANIS6_23OverweightM','CHANIS6_23OverweightT');\"  name='CHANIS6_23OverweightM' id='CHANIS6_23OverweightM' value='"+CHANIS6_23OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23OverweightT');autosum('CHANIS6_23OverweightF@CHANIS6_23OverweightM','CHANIS6_23OverweightT');\"  name='CHANIS6_23OverweightT' id='CHANIS6_23OverweightT' value='"+CHANIS6_23OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >11</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23ObeseF');autosum('CHANIS6_23ObeseF@CHANIS6_23ObeseM','CHANIS6_23ObeseT');\"  name='CHANIS6_23ObeseF' id='CHANIS6_23ObeseF' value='"+CHANIS6_23ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23ObeseM');autosum('CHANIS6_23ObeseF@CHANIS6_23ObeseM','CHANIS6_23ObeseT');\"  name='CHANIS6_23ObeseM' id='CHANIS6_23ObeseM' value='"+CHANIS6_23ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23ObeseT');autosum('CHANIS6_23ObeseF@CHANIS6_23ObeseM','CHANIS6_23ObeseT');\"  name='CHANIS6_23ObeseT' id='CHANIS6_23ObeseT' value='"+CHANIS6_23ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >12</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TWF');autosum('CHANIS6_23TWF@CHANIS6_23TWM','CHANIS6_23TW');\"  name='CHANIS6_23TWF' id='CHANIS6_23TWF' value='"+CHANIS6_23TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TWM');autosum('CHANIS6_23TWF@CHANIS6_23TWM','CHANIS6_23TW');\"  name='CHANIS6_23TWM' id='CHANIS6_23TWM' value='"+CHANIS6_23TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23TW');autosum('CHANIS6_23TWF@CHANIS6_23TWM','CHANIS6_23TW');\"  name='CHANIS6_23TW' id='CHANIS6_23TW' value='"+CHANIS6_23TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
                   
            + " <tr> <td >13</td><td rowspan='6' > 24-59 months </td> <td>Normal Weight for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalweightF');\"  name='CHANIS24_59NormalweightF' id='CHANIS24_59NormalweightF' value='"+CHANIS24_59NormalweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalweightM');autosum('CHANIS24_59NormalweightF@CHANIS24_59NormalweightM','CHANIS24_59NormalweightT');\"  name='CHANIS24_59NormalweightM' id='CHANIS24_59NormalweightM' value='"+CHANIS24_59NormalweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59NormalweightT');autosum('CHANIS24_59NormalweightF@CHANIS24_59NormalweightM','CHANIS24_59NormalweightT');\"  name='CHANIS24_59NormalweightT' id='CHANIS24_59NormalweightT' value='"+CHANIS24_59NormalweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >14</td><td>Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59UnderweightF');autosum('CHANIS24_59UnderweightF@CHANIS24_59UnderweightM','CHANIS24_59UnderweightT');\"  name='CHANIS24_59UnderweightF' id='CHANIS24_59UnderweightF' value='"+CHANIS24_59UnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59UnderweightM');autosum('CHANIS24_59UnderweightF@CHANIS24_59UnderweightM','CHANIS24_59UnderweightT');\"  name='CHANIS24_59UnderweightM' id='CHANIS24_59UnderweightM' value='"+CHANIS24_59UnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59UnderweightT');autosum('CHANIS24_59UnderweightF@CHANIS24_59UnderweightM','CHANIS24_59UnderweightT');\"  name='CHANIS24_59UnderweightT' id='CHANIS24_59UnderweightT' value='"+CHANIS24_59UnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >15</td><td>Severe Underweight </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevUnderweightF');autosum('CHANIS24_59sevUnderweightF@CHANIS24_59sevUnderweightM','CHANIS24_59sevUnderweightT');\"  name='CHANIS24_59sevUnderweightF' id='CHANIS24_59sevUnderweightF' value='"+CHANIS24_59sevUnderweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevUnderweightM');autosum('CHANIS24_59sevUnderweightF@CHANIS24_59sevUnderweightM','CHANIS24_59sevUnderweightT');\"  name='CHANIS24_59sevUnderweightM' id='CHANIS24_59sevUnderweightM' value='"+CHANIS24_59sevUnderweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59sevUnderweightT');autosum('CHANIS24_59sevUnderweightF@CHANIS24_59sevUnderweightM','CHANIS24_59sevUnderweightT');\"  name='CHANIS24_59sevUnderweightT' id='CHANIS24_59sevUnderweightT' value='"+CHANIS24_59sevUnderweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >16</td><td>Overweight </td>"
            + " <td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59OverweightF');autosum('CHANIS24_59OverweightF@CHANIS24_59OverweightM','CHANIS24_59OverweightT');\"  name='CHANIS24_59OverweightF' id='CHANIS24_59OverweightF' value='"+CHANIS24_59OverweightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + " <td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59OverweightM');autosum('CHANIS24_59OverweightF@CHANIS24_59OverweightM','CHANIS24_59OverweightT');\"  name='CHANIS24_59OverweightM' id='CHANIS24_59OverweightM' value='"+CHANIS24_59OverweightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + " <td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59OverweightT');autosum('CHANIS24_59OverweightF@CHANIS24_59OverweightM','CHANIS24_59OverweightT');\"  name='CHANIS24_59OverweightT' id='CHANIS24_59OverweightT' value='"+CHANIS24_59OverweightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >17</td><td>Obese </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59ObeseF');autosum('CHANIS24_59ObeseF@CHANIS24_59ObeseM','CHANIS24_59ObeseT');\"  name='CHANIS24_59ObeseF' id='CHANIS24_59ObeseF' value='"+CHANIS24_59ObeseF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59ObeseM');autosum('CHANIS24_59ObeseF@CHANIS24_59ObeseM','CHANIS24_59ObeseT');\"  name='CHANIS24_59ObeseM' id='CHANIS24_59ObeseM' value='"+CHANIS24_59ObeseM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59ObeseT');autosum('CHANIS24_59ObeseF@CHANIS24_59ObeseM','CHANIS24_59ObeseT');\"  name='CHANIS24_59ObeseT' id='CHANIS24_59ObeseT' value='"+CHANIS24_59ObeseT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >18</td><td>Total Weighed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TWF');autosum('CHANIS24_59TWF@CHANIS24_59TWM','CHANIS24_59TW');\"  name='CHANIS24_59TWF' id='CHANIS24_59TWF' value='"+CHANIS24_59TWF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TWM');autosum('CHANIS24_59TWF@CHANIS24_59TWM','CHANIS24_59TW');\"  name='CHANIS24_59TWM' id='CHANIS24_59TWM' value='"+CHANIS24_59TWM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59TW');autosum('CHANIS24_59TWF@CHANIS24_59TWM','CHANIS24_59TW');\"  name='CHANIS24_59TW' id='CHANIS24_59TW' value='"+CHANIS24_59TW+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                  
                  
            
             + " <tr> <td >19</td><td rowspan='6' > MUAC 6-59 months </td> <td>Normal(Green) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACNormalF');autosum('CHANISMUACNormalF@CHANISMUACNormalM','CHANISMUACNormalT');\"  name='CHANISMUACNormalF' id='CHANISMUACNormalF' value='"+CHANISMUACNormalF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACNormalM');autosum('CHANISMUACNormalF@CHANISMUACNormalM','CHANISMUACNormalT');\"  name='CHANISMUACNormalM' id='CHANISMUACNormalM' value='"+CHANISMUACNormalM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACNormalT');autosum('CHANISMUACNormalF@CHANISMUACNormalM','CHANISMUACNormalT');\"  name='CHANISMUACNormalT' id='CHANISMUACNormalT' value='"+CHANISMUACNormalT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >20</td><td> Moderate (Yellow) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACModerateF');autosum('CHANISMUACModerateF@CHANISMUACModerateM','CHANISMUACModerateT');\"  name='CHANISMUACModerateF' id='CHANISMUACModerateF' value='"+CHANISMUACModerateF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACModerateM');autosum('CHANISMUACModerateF@CHANISMUACModerateM','CHANISMUACModerateT');\"  name='CHANISMUACModerateM' id='CHANISMUACModerateM' value='"+CHANISMUACModerateM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACModerateT');autosum('CHANISMUACModerateF@CHANISMUACModerateM','CHANISMUACModerateT');\"  name='CHANISMUACModerateT' id='CHANISMUACModerateT' value='"+CHANISMUACModerateT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >21</td><td> Severe (Red) </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACSevereF');autosum('CHANISMUACSevereF@CHANISMUACSevereM','CHANISMUACSevereT');\"  name='CHANISMUACSevereF' id='CHANISMUACSevereF' value='"+CHANISMUACSevereF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACSevereM');autosum('CHANISMUACSevereF@CHANISMUACSevereM','CHANISMUACSevereT');\"  name='CHANISMUACSevereM' id='CHANISMUACSevereM' value='"+CHANISMUACSevereM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACSevereT');autosum('CHANISMUACSevereF@CHANISMUACSevereM','CHANISMUACSevereT');\"  name='CHANISMUACSevereT' id='CHANISMUACSevereT' value='"+CHANISMUACSevereT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
           
                  
            + " <tr> <td > 22</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACMeasuredF');autosum('CHANISMUACMeasuredF@CHANISMUACMeasuredM','CHANISMUACMeasured');\"  name='CHANISMUACMeasuredF' id='CHANISMUACMeasuredF' value='"+CHANISMUACMeasuredF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANISMUACMeasuredM');autosum('CHANISMUACMeasuredF@CHANISMUACMeasuredM','CHANISMUACMeasured');\"  name='CHANISMUACMeasuredM' id='CHANISMUACMeasuredM' value='"+CHANISMUACMeasuredM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANISMUACMeasuredT');autosum('CHANISMUACMeasuredF@CHANISMUACMeasuredM','CHANISMUACMeasuredT');\"  name='CHANISMUACMeasuredT' id='CHANISMUACMeasuredT' value='"+CHANISMUACMeasuredT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>"       
            + " </table> </fieldset> ";   
          
       
    chanisweight_tab+=chanisweight_ext;
    
    
    
    
    chanisheight_tab+="";
    
    
    chanisheight_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>F. Child Health and Nutrition Information System(CHANIS)</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b>Height for Age</b></td><td   class='form-actions'> <b>F</b></td><td   class=\"form-actions\"> <b>M  </b></td><td   class=\"form-actions\"> <b>Total  </b></td></tr>"  
           
            + "<tr> <td >23</td><td rowspan='4' style=' vertical-align:top;' > 0-< 6 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalHeightF');autosum('CHANIS0_5NormalHeightF@CHANIS0_5NormalHeightM','CHANIS0_5NormalHeightT');\"  name='CHANIS0_5NormalHeightF' id='CHANIS0_5NormalHeightF' value='"+CHANIS0_5NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5NormalHeightM');autosum('CHANIS0_5NormalHeightF@CHANIS0_5NormalHeightM','CHANIS0_5NormalHeightT');\"  name='CHANIS0_5NormalHeightM' id='CHANIS0_5NormalHeightM' value='"+CHANIS0_5NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5NormalHeightT');autosum('CHANIS0_5NormalHeightF@CHANIS0_5NormalHeightM','CHANIS0_5NormalHeightT');\"  name='CHANIS0_5NormalHeightT' id='CHANIS0_5NormalHeightT' value='"+CHANIS0_5NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >24</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5StuntedF');autosum('CHANIS0_5StuntedF@CHANIS0_5StuntedM','CHANIS0_5StuntedT');\"  name='CHANIS0_5StuntedF' id='CHANIS0_5StuntedF' value='"+CHANIS0_5StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5StuntedM');autosum('CHANIS0_5StuntedF@CHANIS0_5StuntedM','CHANIS0_5StuntedT');\"  name='CHANIS0_5StuntedM' id='CHANIS0_5StuntedM' value='"+CHANIS0_5StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5StuntedT');autosum('CHANIS0_5StuntedF@CHANIS0_5StuntedM','CHANIS0_5StuntedT');\"  name='CHANIS0_5StuntedT' id='CHANIS0_5StuntedT' value='"+CHANIS0_5StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >25</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevStuntedF');autosum('CHANIS0_5sevStuntedF@CHANIS0_5sevStuntedM','CHANIS0_5sevStuntedT');\"  name='CHANIS0_5sevStuntedF' id='CHANIS0_5sevStuntedF' value='"+CHANIS0_5sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5sevStuntedM');autosum('CHANIS0_5sevStuntedF@CHANIS0_5sevStuntedM','CHANIS0_5sevStuntedT');\"  name='CHANIS0_5sevStuntedM' id='CHANIS0_5sevStuntedM' value='"+CHANIS0_5sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5sevStuntedT');autosum('CHANIS0_5sevStuntedF@CHANIS0_5sevStuntedM','CHANIS0_5sevStuntedT');\"  name='CHANIS0_5sevStuntedT' id='CHANIS0_5sevStuntedT' value='"+CHANIS0_5sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >26</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TMeasF');autosum('CHANIS0_5TMeasF@CHANIS0_5TMeasM','CHANIS0_5TMeas');\"  name='CHANIS0_5TMeasF' id='CHANIS0_5TMeasF' value='"+CHANIS0_5TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5TMeasM');autosum('CHANIS0_5TMeasF@CHANIS0_5TMeasM','CHANIS0_5TMeas');\"  name='CHANIS0_5TMeasM' id='CHANIS0_5TMeasM' value='"+CHANIS0_5TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5TMeas');autosum('CHANIS0_5TMeasF@CHANIS0_5TMeasM','CHANIS0_5TMeas');\"  name='CHANIS0_5TMeas' id='CHANIS0_5TMeas' value='"+CHANIS0_5TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
            + "<tr> <td >27</td><td rowspan='4' style=' vertical-align:top;' > 6-23 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalHeightF');autosum('CHANIS6_23NormalHeightF@CHANIS6_23NormalHeightM','CHANIS6_23NormalHeightT');\"  name='CHANIS6_23NormalHeightF' id='CHANIS6_23NormalHeightF' value='"+CHANIS6_23NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23NormalHeightM');autosum('CHANIS6_23NormalHeightF@CHANIS6_23NormalHeightM','CHANIS6_23NormalHeightT');\"  name='CHANIS6_23NormalHeightM' id='CHANIS6_23NormalHeightM' value='"+CHANIS6_23NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23NormalHeightT');autosum('CHANIS6_23NormalHeightF@CHANIS6_23NormalHeightM','CHANIS6_23NormalHeightT');\"  name='CHANIS6_23NormalHeightT' id='CHANIS6_23NormalHeightT' value='"+CHANIS6_23NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >28</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23StuntedF');autosum('CHANIS6_23StuntedF@CHANIS6_23StuntedM','CHANIS6_23StuntedT');\"  name='CHANIS6_23StuntedF' id='CHANIS6_23StuntedF' value='"+CHANIS6_23StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23StuntedM');autosum('CHANIS6_23StuntedF@CHANIS6_23StuntedM','CHANIS6_23StuntedT');\"  name='CHANIS6_23StuntedM' id='CHANIS6_23StuntedM' value='"+CHANIS6_23StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23StuntedT');autosum('CHANIS6_23StuntedF@CHANIS6_23StuntedM','CHANIS6_23StuntedT');\"  name='CHANIS6_23StuntedT' id='CHANIS6_23StuntedT' value='"+CHANIS6_23StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >29</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevStuntedF');autosum('CHANIS6_23sevStuntedF@CHANIS6_23sevStuntedM','CHANIS6_23sevStuntedT');\"  name='CHANIS6_23sevStuntedF' id='CHANIS6_23sevStuntedF' value='"+CHANIS6_23sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23sevStuntedM');autosum('CHANIS6_23sevStuntedF@CHANIS6_23sevStuntedM','CHANIS6_23sevStuntedT');\"  name='CHANIS6_23sevStuntedM' id='CHANIS6_23sevStuntedM' value='"+CHANIS6_23sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23sevStuntedT');autosum('CHANIS6_23sevStuntedF@CHANIS6_23sevStuntedM','CHANIS6_23sevStuntedT');\"  name='CHANIS6_23sevStuntedT' id='CHANIS6_23sevStuntedT' value='"+CHANIS6_23sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >30</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TMeasF');autosum('CHANIS6_23TMeasF@CHANIS6_23TMeasM','CHANIS6_23TMeas');\"  name='CHANIS6_23TMeasF' id='CHANIS6_23TMeasF' value='"+CHANIS6_23TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23TMeasM');autosum('CHANIS6_23TMeasF@CHANIS6_23TMeasM','CHANIS6_23TMeas');\"  name='CHANIS6_23TMeasM' id='CHANIS6_23TMeasM' value='"+CHANIS6_23TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23TMeas');autosum('CHANIS6_23TMeasF@CHANIS6_23TMeasM','CHANIS6_23TMeas');\"  name='CHANIS6_23TMeas' id='CHANIS6_23TMeas' value='"+CHANIS6_23TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
              + "<tr> <td >31</td><td rowspan='4' style=' vertical-align:top;' > 24-59 months </td> <td>Normal Height for Age </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalHeightF');autosum('CHANIS24_59NormalHeightF@CHANIS24_59NormalHeightM','CHANIS24_59NormalHeightT');\"  name='CHANIS24_59NormalHeightF' id='CHANIS24_59NormalHeightF' value='"+CHANIS24_59NormalHeightF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59NormalHeightM');autosum('CHANIS24_59NormalHeightF@CHANIS24_59NormalHeightM','CHANIS24_59NormalHeightT');\"  name='CHANIS24_59NormalHeightM' id='CHANIS24_59NormalHeightM' value='"+CHANIS24_59NormalHeightM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59NormalHeightT');autosum('CHANIS24_59NormalHeightF@CHANIS24_59NormalHeightM','CHANIS24_59NormalHeightT');\"  name='CHANIS24_59NormalHeightT' id='CHANIS24_59NormalHeightT' value='"+CHANIS24_59NormalHeightT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          
            + " <tr> <td >32</td><td>Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59StuntedF');autosum('CHANIS24_59StuntedF@CHANIS24_59StuntedM','CHANIS24_59StuntedT');\"  name='CHANIS24_59StuntedF' id='CHANIS24_59StuntedF' value='"+CHANIS24_59StuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59StuntedM');autosum('CHANIS24_59StuntedF@CHANIS24_59StuntedM','CHANIS24_59StuntedT');\"  name='CHANIS24_59StuntedM' id='CHANIS24_59StuntedM' value='"+CHANIS24_59StuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59StuntedT');autosum('CHANIS24_59StuntedF@CHANIS24_59StuntedM','CHANIS24_59StuntedT');\"  name='CHANIS24_59StuntedT' id='CHANIS24_59StuntedT' value='"+CHANIS24_59StuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >33</td><td>Severely Stunted </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevStuntedF');autosum('CHANIS24_59sevStuntedF@CHANIS24_59sevStuntedM','CHANIS24_59sevStuntedT');\"  name='CHANIS24_59sevStuntedF' id='CHANIS24_59sevStuntedF' value='"+CHANIS24_59sevStuntedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59sevStuntedM');autosum('CHANIS24_59sevStuntedF@CHANIS24_59sevStuntedM','CHANIS24_59sevStuntedT');\"  name='CHANIS24_59sevStuntedM' id='CHANIS24_59sevStuntedM' value='"+CHANIS24_59sevStuntedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59sevStuntedT');autosum('CHANIS24_59sevStuntedF@CHANIS24_59sevStuntedM','CHANIS24_59sevStuntedT');\"  name='CHANIS24_59sevStuntedT' id='CHANIS24_59sevStuntedT' value='"+CHANIS24_59sevStuntedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >34</td><td>Total Measured </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TMeasF');autosum('CHANIS24_59TMeasF@CHANIS24_59TMeasM','CHANIS24_59TMeas');\"  name='CHANIS24_59TMeasF' id='CHANIS24_59TMeasF' value='"+CHANIS24_59TMeasF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS24_59TMeasM');autosum('CHANIS24_59TMeasF@CHANIS24_59TMeasM','CHANIS24_59TMeas');\"  name='CHANIS24_59TMeasM' id='CHANIS24_59TMeasM' value='"+CHANIS24_59TMeasM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS24_59TMeas');autosum('CHANIS24_59TMeasF@CHANIS24_59TMeasM','CHANIS24_59TMeas');\"  name='CHANIS24_59TMeas' id='CHANIS24_59TMeas' value='"+CHANIS24_59TMeas+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
            +"<tr><td colspan='3' class='form-actions'><b>OTHER</b></td><td class='form-actions'><b>F</b></td><td class='form-actions'><b>M</b></td><td class='form-actions'><b>TOTAL</b></td></tr>"          
            
                  
            + " <tr> <td >35</td><td rowspan='5' > 0-59 months </td> <td> New Visits </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59NewVisitsF');autosum('CHANIS0_59NewVisitsF@CHANIS0_59NewVisitsM','CHANIS0_59NewVisitsT');\"  name='CHANIS0_59NewVisitsF' id='CHANIS0_59NewVisitsF' value='"+CHANIS0_59NewVisitsF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59NewVisitsM');autosum('CHANIS0_59NewVisitsF@CHANIS0_59NewVisitsM','CHANIS0_59NewVisitsT');\"  name='CHANIS0_59NewVisitsM' id='CHANIS0_59NewVisitsM' value='"+CHANIS0_59NewVisitsM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59NewVisitsT');autosum('CHANIS0_59NewVisitsF@CHANIS0_59NewVisitsM','CHANIS0_59NewVisitsT');\"  name='CHANIS0_59NewVisitsT' id='CHANIS0_59NewVisitsT' value='"+CHANIS0_59NewVisitsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                  
            + " <tr> <td >36</td><td>Kwashiakor </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59KwashiakorF');autosum('CHANIS0_59KwashiakorF@CHANIS0_59KwashiakorM','CHANIS0_59KwashiakorT');\"  name='CHANIS0_59KwashiakorF' id='CHANIS0_59KwashiakorF' value='"+CHANIS0_59KwashiakorF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59KwashiakorM');autosum('CHANIS0_59KwashiakorF@CHANIS0_59KwashiakorM','CHANIS0_59KwashiakorT');\"  name='CHANIS0_59KwashiakorM' id='CHANIS0_59KwashiakorM' value='"+CHANIS0_59KwashiakorM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59KwashiakorT');autosum('CHANIS0_59KwashiakorF@CHANIS0_59KwashiakorM','CHANIS0_59KwashiakorT');\"  name='CHANIS0_59KwashiakorT' id='CHANIS0_59KwashiakorT' value='"+CHANIS0_59KwashiakorT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                  
            + " <tr> <td >37</td><td>Marasmus</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59MarasmusF');autosum('CHANIS0_59MarasmusF@CHANIS0_59MarasmusM','CHANIS0_59MarasmusT');\"  name='CHANIS0_59MarasmusF' id='CHANIS0_59MarasmusF' value='"+CHANIS0_59MarasmusF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59MarasmusM');autosum('CHANIS0_59MarasmusF@CHANIS0_59MarasmusM','CHANIS0_59MarasmusT');\"  name='CHANIS0_59MarasmusM' id='CHANIS0_59MarasmusM' value='"+CHANIS0_59MarasmusM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59MarasmusT');autosum('CHANIS0_59MarasmusF@CHANIS0_59MarasmusM','CHANIS0_59MarasmusT');\"  name='CHANIS0_59MarasmusT' id='CHANIS0_59MarasmusT' value='"+CHANIS0_59MarasmusT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >38</td><td>Faltering growth </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59FalgrowthF');autosum('CHANIS0_59FalgrowthF@CHANIS0_59FalgrowthM','CHANIS0_59FalgrowthT');\"  name='CHANIS0_59FalgrowthF' id='CHANIS0_59FalgrowthF' value='"+CHANIS0_59FalgrowthF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59FalgrowthM');autosum('CHANIS0_59FalgrowthF@CHANIS0_59FalgrowthM','CHANIS0_59FalgrowthT');\"  name='CHANIS0_59FalgrowthM' id='CHANIS0_59FalgrowthM' value='"+CHANIS0_59FalgrowthM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59FalgrowthT');autosum('CHANIS0_59FalgrowthF@CHANIS0_59FalgrowthM','CHANIS0_59FalgrowthT');\"  name='CHANIS0_59FalgrowthT' id='CHANIS0_59FalgrowthT' value='"+CHANIS0_59FalgrowthT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                  
            + " <tr> <td >39</td><td>Total </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59F');autosum('CHANIS0_59F@CHANIS0_59M','CHANIS0_59T');\"  name='CHANIS0_59F' id='CHANIS0_59F' value='"+CHANIS0_59F+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59M');autosum('CHANIS0_59F@CHANIS0_59M','CHANIS0_59T');\"  name='CHANIS0_59M' id='CHANIS0_59M' value='"+CHANIS0_59M+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59T');autosum('CHANIS0_59F@CHANIS0_59M','CHANIS0_59T');\"  name='CHANIS0_59T' id='CHANIS0_59T' value='"+CHANIS0_59T+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
           + " <tr> <td >40</td><td>0 <-6 months </td><td>Exclusive breast feeding </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5EXCLBreastF');autosum('CHANIS0_5EXCLBreastF@CHANIS0_5EXCLBreastM','CHANIS0_5EXCLBreastT');\"  name='CHANIS0_5EXCLBreastF' id='CHANIS0_5EXCLBreastF' value='"+CHANIS0_5EXCLBreastF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_5EXCLBreastM');autosum('CHANIS0_5EXCLBreastF@CHANIS0_5EXCLBreastM','CHANIS0_5EXCLBreastT');\"  name='CHANIS0_5EXCLBreastM' id='CHANIS0_5EXCLBreastM' value='"+CHANIS0_5EXCLBreastM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_5EXCLBreastT');autosum('CHANIS0_5EXCLBreastF@CHANIS0_5EXCLBreastM','CHANIS0_5EXCLBreastT');\"  name='CHANIS0_5EXCLBreastT' id='CHANIS0_5EXCLBreastT' value='"+CHANIS0_5EXCLBreastT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                   
            + " <tr> <td >41</td><td>12-59 months </td><td>Dewormed </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS12_59DewormedF');autosum('CHANIS12_59DewormedF@CHANIS12_59DewormedM','CHANIS12_59DewormedT');\"  name='CHANIS12_59DewormedF' id='CHANIS12_59DewormedF' value='"+CHANIS12_59DewormedF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS12_59DewormedM');autosum('CHANIS12_59DewormedF@CHANIS12_59DewormedM','CHANIS12_59DewormedT');\"  name='CHANIS12_59DewormedM' id='CHANIS12_59DewormedM' value='"+CHANIS12_59DewormedM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS12_59DewormedT');autosum('CHANIS12_59DewormedF@CHANIS12_59DewormedM','CHANIS12_59DewormedT');\"  name='CHANIS12_59DewormedT' id='CHANIS12_59DewormedT' value='"+CHANIS12_59DewormedT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
          + " <tr> <td >42</td><td>6-23 months </td><td>MNPs Supplementation </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23MNPsF');autosum('CHANIS6_23MNPsF@CHANIS6_23MNPsM','CHANIS6_23MNPsT');\"  name='CHANIS6_23MNPsF' id='CHANIS6_23MNPsF' value='"+CHANIS6_23MNPsF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS6_23MNPsM');autosum('CHANIS6_23MNPsF@CHANIS6_23MNPsM','CHANIS6_23MNPsT');\"  name='CHANIS6_23MNPsM' id='CHANIS6_23MNPsM' value='"+CHANIS6_23MNPsM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS6_23MNPsT');autosum('CHANIS6_23MNPsF@CHANIS6_23MNPsM','CHANIS6_23MNPsT');\"  name='CHANIS6_23MNPsT' id='CHANIS6_23MNPsT' value='"+CHANIS6_23MNPsT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                    
           + " <tr> <td >43</td><td>0-59 months </td><td>Any Disability </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59DisabilityF');autosum('CHANIS0_59DisabilityF@CHANIS0_59DisabilityM','CHANIS0_59DisabilityT');\"  name='CHANIS0_59DisabilityF' id='CHANIS0_59DisabilityF' value='"+CHANIS0_59DisabilityF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CHANIS0_59DisabilityM');autosum('CHANIS0_59DisabilityF@CHANIS0_59DisabilityM','CHANIS0_59DisabilityT');\"  name='CHANIS0_59DisabilityM' id='CHANIS0_59DisabilityM' value='"+CHANIS0_59DisabilityM+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input readonly tabindex='-1' type='text' onblur=\"autosave('CHANIS0_59DisabilityT');autosum('CHANIS0_59DisabilityF@CHANIS0_59DisabilityM','CHANIS0_59DisabilityT');\"  name='CHANIS0_59DisabilityT' id='CHANIS0_59DisabilityT' value='"+CHANIS0_59DisabilityT+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                     
          + " </table> </fieldset> ";   
          
    
     chanisheight_tab+=chanisheight_ext;
    
  
     cervicalcancer_tab+="";
     cervicalcancer_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>G. Cervical cancer screening</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class='form-actions'> <b> < 25yrs</b></td><td   class=\"form-actions\"> <b>25-49yrs </b></td><td   class=\"form-actions\"> <b>50+yrs </b></td></tr>"  
             
            + " <tr> <td >1</td><td>No. of Client receiving VIA /VILI /HPV VILI / HPV </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH24');\"  name='CCSVVH24' id='CCSVVH24' value='"+CCSVVH24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH25_49');\"  name='CCSVVH25_49' id='CCSVVH25_49' value='"+CCSVVH25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVVH50');\"  name='CCSVVH50' id='CCSVVH50' value='"+CCSVVH50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                    + " <tr> <td >2</td><td>No.Screened for Pap smear </td>"
            + "<td><input readonly tabindex='-1' "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR24');\"  name='CCSPAPSMEAR24' id='CCSPAPSMEAR24' value='"+CCSPAPSMEAR24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR25_49');\"  name='CCSPAPSMEAR25_49' id='CCSPAPSMEAR25_49' value='"+CCSPAPSMEAR25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSPAPSMEAR50');\"  name='CCSPAPSMEAR50' id='CCSPAPSMEAR50' value='"+CCSPAPSMEAR50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
                    + " <tr> <td >3</td><td>No.Screened for HPV test </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV24');\"  name='CCSHPV24' id='CCSHPV24' value='"+CCSHPV24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV25_49');\"  name='CCSHPV25_49' id='CCSHPV25_49' value='"+CCSHPV25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPV50');\"  name='CCSHPV50' id='CCSHPV50' value='"+CCSHPV50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
            + " <tr> <td >4</td><td>Number of clients with Positive VIA/VILI result  </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS24');\"  name='CCSVIAVILIPOS24' id='CCSVIAVILIPOS24' value='"+CCSVIAVILIPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS25_49');\"  name='CCSVIAVILIPOS25_49' id='CCSVIAVILIPOS25_49' value='"+CCSVIAVILIPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSVIAVILIPOS50');\"  name='CCSVIAVILIPOS50' id='CCSVIAVILIPOS50' value='"+CCSVIAVILIPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
                    
            + " <tr> <td >5</td><td>Number of clients with Positive Cytology </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS24');\"  name='CCSCYTOLPOS24' id='CCSCYTOLPOS24' value='"+CCSCYTOLPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS25_49');\"  name='CCSCYTOLPOS25_49' id='CCSCYTOLPOS25_49' value='"+CCSCYTOLPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCYTOLPOS50');\"  name='CCSCYTOLPOS50' id='CCSCYTOLPOS50' value='"+CCSCYTOLPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                
            + " <tr> <td >6</td><td>Number of clients with Positive HPV result</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS24');\"  name='CCSHPVPOS24' id='CCSHPVPOS24' value='"+CCSHPVPOS24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS25_49');\"  name='CCSHPVPOS25_49' id='CCSHPVPOS25_49' value='"+CCSHPVPOS25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHPVPOS50');\"  name='CCSHPVPOS50' id='CCSHPVPOS50' value='"+CCSHPVPOS50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                       
            + " <tr> <td >7</td><td>Number of clients with suspicious cancer lesions</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES24');\"  name='CCSSUSPICIOUSLES24' id='CCSSUSPICIOUSLES24' value='"+CCSSUSPICIOUSLES24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES25_49');\"  name='CCSSUSPICIOUSLES25_49' id='CCSSUSPICIOUSLES25_49' value='"+CCSSUSPICIOUSLES25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSSUSPICIOUSLES50');\"  name='CCSSUSPICIOUSLES50' id='CCSSUSPICIOUSLES50' value='"+CCSSUSPICIOUSLES50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                       
             + " <tr> <td >8</td><td>Number of clients treated using Cryotherapy</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy24');\"  name='CCSCryotherapy24' id='CCSCryotherapy24' value='"+CCSCryotherapy24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy25_49');\"  name='CCSCryotherapy25_49' id='CCSCryotherapy25_49' value='"+CCSCryotherapy25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSCryotherapy50');\"  name='CCSCryotherapy50' id='CCSCryotherapy50' value='"+CCSCryotherapy50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
             + " <tr> <td >9</td><td>Number of clients treated using LEEP</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP24');\"  name='CCSLEEP24' id='CCSLEEP24' value='"+CCSLEEP24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP25_49');\"  name='CCSLEEP25_49' id='CCSLEEP25_49' value='"+CCSLEEP25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSLEEP50');\"  name='CCSLEEP50' id='CCSLEEP50' value='"+CCSLEEP50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
            + " <tr> <td >10</td><td>Number of HIV positive clients screened</td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED24');\"  name='CCSHIVPOSSCREENED24' id='CCSHIVPOSSCREENED24' value='"+CCSHIVPOSSCREENED24+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td>"
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED25_49');\"  name='CCSHIVPOSSCREENED25_49' id='CCSHIVPOSSCREENED25_49' value='"+CCSHIVPOSSCREENED25_49+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
            + "<td><input "+islocked+" type='text' onblur=\"autosave('CCSHIVPOSSCREENED50');\"  name='CCSHIVPOSSCREENED50' id='CCSHIVPOSSCREENED50' value='"+CCSHIVPOSSCREENED50+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
                   
                    
          + " </table> </fieldset> ";   
          
              
     
     cervicalcancer_tab+=cervicalcancer_ext;
     
  
     
      pnc_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>H. Post Natal Care (PNC)</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='3' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
            
             + " <tr> <td >1</td><td colspan='2'>Breast Exam</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCBreastExam');\"  name='PNCBreastExam' id='PNCBreastExam' value='"+PNCBreastExam+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >2</td><td colspan='2'>No of Women counselled</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCCounselled');\"  name='PNCCounselled' id='PNCCounselled' value='"+PNCCounselled+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
            + " <tr> <td >3</td><td colspan='2'>Number of Cases of Fistula</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCFistula');\"  name='PNCFistula' id='PNCFistula' value='"+PNCFistula+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >4</td><td rowspan='2' >PNC Given Exercise</td><td >Negative</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCExerNegative');\"  name='PNCExerNegative' id='PNCExerNegative' value='"+PNCExerNegative+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >5</td><td >Positive</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCExerPositive');\"  name='PNCExerPositive' id='PNCExerPositive' value='"+PNCExerPositive+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
              + " <tr> <td >6</td><td  >Cervical cancer screening </td><td >Suspect Cancer</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCCCSsuspect');\"  name='PNCCCSsuspect' id='PNCCCSsuspect' value='"+PNCCCSsuspect+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
             
             + " <tr> <td >7</td><td rowspan='2' >No. of Mothers received Postpartum Care</td><td >within 2-3 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCmotherspostpartum2_3');\"  name='PNCmotherspostpartum2_3' id='PNCmotherspostpartum2_3' value='"+PNCmotherspostpartum2_3+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >8</td><td > 6 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCmotherspostpartum6');\"  name='PNCmotherspostpartum6' id='PNCmotherspostpartum6' value='"+PNCmotherspostpartum6+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >9</td><td rowspan='2' >No. of Infants received Postpartum Care </td><td >within 2-3 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCinfantspostpartum2_3');\"  name='PNCinfantspostpartum2_3' id='PNCinfantspostpartum2_3' value='"+PNCinfantspostpartum2_3+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >10</td><td > 6 days</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCinfantspostpartum6');\"  name='PNCinfantspostpartum6' id='PNCinfantspostpartum6' value='"+PNCinfantspostpartum6+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
            
             + " <tr> <td >11</td><td rowspan='4' >No. of Referrals </td><td > From Other Health Facility</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsfromotherHF');\"  name='PNCreferralsfromotherHF' id='PNCreferralsfromotherHF' value='"+PNCreferralsfromotherHF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
               
             + " <tr> <td >12</td><td > From Community Unit</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsfromotherCU');\"  name='PNCreferralsfromotherCU' id='PNCreferralsfromotherCU' value='"+PNCreferralsfromotherCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >13</td><td > To Other Health Facility</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsTootherHF');\"  name='PNCreferralsTootherHF' id='PNCreferralsTootherHF' value='"+PNCreferralsTootherHF+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >14</td><td > To Community Unit</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PNCreferralsTootherCU');\"  name='PNCreferralsTootherCU' id='PNCreferralsTootherCU' value='"+PNCreferralsTootherCU+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
             
          + " </table> </fieldset> ";   
          
      pnc_tab+=pnc_ext;   
     
     
      
      
      
      
      rehabilitation_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>I. Rehabilitation Services</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
           
            + " <tr> <td >1</td><td > Number Assessed</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsAssessed');\"  name='RsAssessed' id='RsAssessed' value='"+RsAssessed+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
           
                   + " <tr> <td >2</td><td > Number Assessed</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Rstreated');\"  name='Rstreated' id='Rstreated' value='"+Rstreated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
              
                    + " <tr> <td >3</td><td >Number Rehabilitated</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsRehabilitated');\"  name='RsRehabilitated' id='RsRehabilitated' value='"+RsRehabilitated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
             + " <tr> <td >4</td><td >Number Referred for further Interventions</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Rsreffered');\"  name='Rsreffered' id='Rsreffered' value='"+Rsreffered+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
             + " <tr> <td >5</td><td >Number Integrated to Communities</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('RsIntergrated');\"  name='RsIntergrated' id='RsIntergrated' value='"+RsIntergrated+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
                      
          + " </table> </fieldset> "; 
      
      rehabilitation_tab+=rehabilitation_ext;
      
      
     msw_tab+="";
     
     msw_ext= "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>J. Medical Social Work</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
            + " <tr> <td colspan='2' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b>Total </b></td></tr>"  
            
            + " <tr> <td >1</td><td > Psycho-Social Counselling</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWpscounselling');\"  name='MSWpscounselling' id='MSWpscounselling' value='"+MSWpscounselling+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
           
            + " <tr> <td >2</td><td > Alcohol and Drug Abuse</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWdrugabuse');\"  name='MSWdrugabuse' id='MSWdrugabuse' value='"+MSWdrugabuse+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
            + " <tr> <td >3</td><td > Mental Illness</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWMental');\"  name='MSWMental' id='MSWMental' value='"+MSWMental+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >4</td><td > Adolescent Issues</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWAdolescent');\"  name='MSWAdolescent' id='MSWAdolescent' value='"+MSWAdolescent+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >5</td><td > Psycho-Social Assessments (psycho, social and economic)</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWPsAsses');\"  name='MSWPsAsses' id='MSWPsAsses' value='"+MSWPsAsses+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >6</td><td > Social investigations  (Home visits / Follow ups)</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWsocialinv');\"  name='MSWsocialinv' id='MSWsocialinv' value='"+MSWsocialinv+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >7</td><td >Social Rehabilitation</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWsocialRehab');\"  name='MSWsocialRehab' id='MSWsocialRehab' value='"+MSWsocialRehab+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >8</td><td >Referrals</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWoutreach');\"  name='MSWoutreach' id='MSWoutreach' value='"+MSWoutreach+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
            
             + " <tr> <td >9</td><td >Outreach Services / Health Talks</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWreferrals');\"  name='MSWreferrals' id='MSWreferrals' value='"+MSWreferrals+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
             + " <tr> <td >10</td><td >Number of waived patients</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('MSWwaivedpatients');\"  name='MSWwaivedpatients' id='MSWwaivedpatients' value='"+MSWwaivedpatients+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> </tr>" 
          
          + " </table> </fieldset> "; 
     
     msw_tab+=msw_ext;
      
     
   physiotherapy_ext = "<fieldset class='formatter'><legend class='formatter'><b style='text-align:center;font-family:cambria;'>K. Physiotherapy Service</b></legend> "+
             "<table border='1' frame='box'  cellpadding='2px' style='border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;font-family:cambria;'>"
             + " <tr> <td colspan='3' class='form-actions'><b></b></td><td   class=\"form-actions\"> <b> < 5yrs </b></td><td   class=\"form-actions\"> <b>5-19yrs </b></td><td   class=\"form-actions\"> <b>20 yrs +</b></td></tr>"  
            
             + " <tr> <td >1</td><td rowspan='2' > Number of PWDs identified and receiving physiotherapy </td><td>OPD</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD4');\"  name='PsPWDOPD4' id='PsPWDOPD4' value='"+PsPWDOPD4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD5_19');\"  name='PsPWDOPD5_19' id='PsPWDOPD5_19' value='"+PsPWDOPD5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDOPD20');\"  name='PsPWDOPD20' id='PsPWDOPD20' value='"+PsPWDOPD20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
             
               + " <tr> <td >2</td><td>In-Patient</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient4');\"  name='PsPWDinpatient4' id='PsPWDinpatient4' value='"+PsPWDinpatient4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient5_19');\"  name='PsPWDinpatient5_19' id='PsPWDinpatient5_19' value='"+PsPWDinpatient5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsPWDinpatient20');\"  name='PsPWDinpatient20' id='PsPWDinpatient20' value='"+PsPWDinpatient20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
               
             + " <tr> <td >3</td><td rowspan='2' > Number of other clients/patients receiving physiotherapy </td><td>OPD</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD4');\"  name='PsotherOPD4' id='PsotherOPD4' value='"+PsotherOPD4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD5_19');\"  name='PsotherOPD5_19' id='PsotherOPD5_19' value='"+PsotherOPD5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsotherOPD20');\"  name='PsotherOPD20' id='PsotherOPD20' value='"+PsotherOPD20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>" 
             
            + " <tr> <td >4</td><td>In-Patient</td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient4');\"  name='Psotherinpatient4' id='Psotherinpatient4' value='"+Psotherinpatient4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient5_19');\"  name='Psotherinpatient5_19' id='Psotherinpatient5_19' value='"+Psotherinpatient5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('Psotherinpatient20');\"  name='Psotherinpatient20' id='Psotherinpatient20' value='"+Psotherinpatient20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >5</td><td colspan='2'>Total Number of treatments </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments4');\"  name='PsTreatments4' id='PsTreatments4' value='"+PsTreatments4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments5_19');\"  name='PsTreatments5_19' id='PsTreatments5_19' value='"+PsTreatments5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsTreatments20');\"  name='PsTreatments20' id='PsTreatments20' value='"+PsTreatments20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >6</td><td colspan='2'>PWDs assessed for registration </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed4');\"  name='PsAssessed4' id='PsAssessed4' value='"+PsAssessed4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed5_19');\"  name='PsAssessed5_19' id='PsAssessed5_19' value='"+PsAssessed5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsAssessed20');\"  name='PsAssessed20' id='PsAssessed20' value='"+PsAssessed20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >7</td><td colspan='2'>Number of clients receiving out/in reach services </td>"
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices4');\"  name='PsServices4' id='PsServices4' value='"+PsServices4+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices5_19');\"  name='PsServices5_19' id='PsServices5_19' value='"+PsServices5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsServices20');\"  name='PsServices20' id='PsServices20' value='"+PsServices20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >8</td><td colspan='2'>Number of pregnant women attending ANC receiving counselling </td>"
             + "<td class='form-controls' > </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsANCCounsel5_19');\"  name='PsANCCounsel5_19' id='PsANCCounsel5_19' value='"+PsANCCounsel5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsANCCounsel20');\"  name='PsANCCounsel20' id='PsANCCounsel20' value='"+PsANCCounsel20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
            + " <tr> <td >9</td><td colspan='2'>WRA receiving prenatal/postnatal exercises </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsExercise5_19');\"  name='PsExercise5_19' id='PsExercise5_19' value='"+PsExercise5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsExercise20');\"  name='PsExercise20' id='PsExercise20' value='"+PsExercise20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >10</td><td colspan='2'>Amount of FIF collected </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFcollected5_19');\"  name='PsFIFcollected5_19' id='PsFIFcollected5_19' value='"+PsFIFcollected5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFcollected20');\"  name='PsFIFcollected20' id='PsFIFcollected20' value='"+PsFIFcollected20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >11</td><td colspan='2'>Amount of FIF waived </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFwaived5_19');\"  name='PsFIFwaived5_19' id='PsFIFwaived5_19' value='"+PsFIFwaived5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFwaived20');\"  name='PsFIFwaived20' id='PsFIFwaived20' value='"+PsFIFwaived20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >12</td><td colspan='2'>Amount of FIF exempted </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFexempted5_19');\"  name='PsFIFexempted5_19' id='PsFIFexempted5_19' value='"+PsFIFexempted5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsFIFexempted20');\"  name='PsFIFexempted20' id='PsFIFexempted20' value='"+PsFIFexempted20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
            + " <tr> <td >13</td><td colspan='2'>Number of Disability committee meetings </td>"
             + "<td class='form-controls'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsDiasbilitymeeting5_19');\"  name='PsDiasbilitymeeting5_19' id='PsDiasbilitymeeting5_19' value='"+PsDiasbilitymeeting5_19+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "<td><input "+islocked+" type='text' onblur=\"autosave('PsDiasbilitymeeting20');\"  name='PsDiasbilitymeeting20' id='PsDiasbilitymeeting20' value='"+PsDiasbilitymeeting20+"' autocomplete='off'  maxlength='10' onkeypress='return numbers(event)' style='width: 80px;'> </td> "
             + "</tr>"  
               
             + " </table> </fieldset> ";
       
   physiotherapy_tab+=physiotherapy_ext;
     
    //vct
     vct+="";   
     vct+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> H: VCT </b></legend>"+
             "<table border='1' frame=\"box\" cellpadding=\"2px\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
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
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_CF\" id=\"VCTClient_Couns_CF\" value=\""+VCTClient_Couns_CF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Couns_CF');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_CM\" id=\"VCTClient_Couns_CM\" value=\""+VCTClient_Couns_CM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Couns_CM');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_AF\" id=\"VCTClient_Couns_AF\" value=\""+VCTClient_Couns_AF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Couns_AF');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Couns_AM\" id=\"VCTClient_Couns_AM\" value=\""+VCTClient_Couns_AM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Couns_AM');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" readonly  tabindex='-1' name=\"VCTClient_Couns_TOT\" id=\"VCTClient_Couns_TOT\" value=\""+VCTClient_Couns_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Couns_TOT');vctconstotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>Tested</td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_CF\" id=\"VCTClient_Tested_CF\" value=\""+VCTClient_Tested_CF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Tested_CF');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_CM\" id=\"VCTClient_Tested_CM\" value=\""+VCTClient_Tested_CM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Tested_CM'); vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_AF\" id=\"VCTClient_Tested_AF\" value=\""+VCTClient_Tested_AF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Tested_AF');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_Tested_AM\" id=\"VCTClient_Tested_AM\" value=\""+VCTClient_Tested_AM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Tested_AM');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" readonly  tabindex='-1' name=\"VCTClient_Tested_TOT\" id=\"VCTClient_Tested_TOT\" value=\""+VCTClient_Tested_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_Tested_TOT');vcttestedtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
                  + "<tr>"
              + ""
                    + "<td>HIV+</td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_CF\" id=\"VCTClient_HIV_CF\" value=\""+VCTClient_HIV_CF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_HIV_CF');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_CM\" id=\"VCTClient_HIV_CM\" value=\""+VCTClient_HIV_CM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_HIV_CM');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_AF\" id=\"VCTClient_HIV_AF\" value=\""+VCTClient_HIV_AF+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_HIV_AF');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_AM\" id=\"VCTClient_HIV_AM\" value=\""+VCTClient_HIV_AM+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_HIV_AM');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                   + "<td ><input type=\"text\" name=\"VCTClient_HIV_TOT\" tabindex='-1' readonly id=\"VCTClient_HIV_TOT\" value=\""+VCTClient_HIV_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTClient_HIV_TOT');vcthivtotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
            + "<tr>"
              + "<td rowspan=\"4\">2.</td><td rowspan=\"4\">No of couples</td>"
                      + "<td>Counselled</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Couns_TOT\" id=\"VCTPartner_Couns_TOT\" value=\""+VCTPartner_Couns_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTPartner_Couns_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Tested</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Tested_TOT\" id=\"VCTPartner_Tested_TOT\" value=\""+VCTPartner_Tested_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTPartner_Tested_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>Both HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_HIV_TOT\" id=\"VCTPartner_HIV_TOT\" value=\""+VCTPartner_HIV_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTPartner_HIV_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
            + "<tr>"
              + ""
                      + "<td>with discordant HIV+</td>"
                      + "<td colspan=\"4\">  </td>"
                      + "<td ><input type=\"text\" name=\"VCTPartner_Disc_TOT\" id=\"VCTPartner_Disc_TOT\" value=\""+VCTPartner_Disc_TOT+"\" autocomplete=\"off\"  onblur=\"autosave('VCTPartner_Disc_TOT');\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
                  
              + "</tr>"
             
             +"</table></fieldset>"
              
              
              + ""
              + "";
           VCT_TAB+=vct;
           
           
 
           
           // dtc 
           
       dtc+="";    
           
        dtc+= "<fieldset class=\"formatter\"><legend class=\"formatter\"><p id=\"demo\" hidden=\"true\"></p><b style=\"text-align:center;\"> I: DTC </b></legend>"+
             "<table border='1' frame=\"box\" cellpadding=\"2px\"  style=\"border-color: #e5e5e5;margin-bottom: 3px; margin-left:150px; width:800px;\">"
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
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_CF\" id=\"DTCA_Couns_Out_CF\" value=\""+DTCA_Couns_Out_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_Out_CF');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_CM\" id=\"DTCA_Couns_Out_CM\" value=\""+DTCA_Couns_Out_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_Out_CM');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_AF\" id=\"DTCA_Couns_Out_AF\" value=\""+DTCA_Couns_Out_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_Out_AF');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_AM\" id=\"DTCA_Couns_Out_AM\" value=\""+DTCA_Couns_Out_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_Out_AM');counsouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_Out_Tot\" id=\"DTCA_Couns_Out_Tot\" value=\""+DTCA_Couns_Out_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_Out_Tot');counsouttotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + ""
              + "<td>Inpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_CF\" id=\"DTCA_Couns_In_CF\" value=\""+DTCA_Couns_In_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_In_CF');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_CM\" id=\"DTCA_Couns_In_CM\" value=\""+DTCA_Couns_In_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_In_CM');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_AF\" id=\"DTCA_Couns_In_AF\" value=\""+DTCA_Couns_In_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_In_AF');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_AM\" id=\"DTCA_Couns_In_AM\" value=\""+DTCA_Couns_In_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_In_AM');counsintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCA_Couns_In_Tot\" id=\"DTCA_Couns_In_Tot\" value=\""+DTCA_Couns_In_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCA_Couns_In_Tot');counsintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">2.</td><td rowspan=\"2\" >No. tested</td><td>Outpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_CF\" id=\"DTCB_Test_Out_CF\" value=\""+DTCB_Test_Out_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_Out_CF');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_CM\" id=\"DTCB_Test_Out_CM\" value=\""+DTCB_Test_Out_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_Out_CM');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_AF\" id=\"DTCB_Test_Out_AF\" value=\""+DTCB_Test_Out_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_Out_AF');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_AM\" id=\"DTCB_Test_Out_AM\" value=\""+DTCB_Test_Out_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_Out_AM');testedouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_Out_Tot\" id=\"DTCB_Test_Out_Tot\" value=\""+DTCB_Test_Out_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_Out_Tot');testedouttotal(); tabindex='-1'  \" readonly  maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_CF\" id=\"DTCB_Test_In_CF\" value=\""+DTCB_Test_In_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_In_CF');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_CM\" id=\"DTCB_Test_In_CM\" value=\""+DTCB_Test_In_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_In_CM');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_AF\" id=\"DTCB_Test_In_AF\" value=\""+DTCB_Test_In_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_In_AF');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_AM\" id=\"DTCB_Test_In_AM\" value=\""+DTCB_Test_In_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_In_AM');testedintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCB_Test_In_Tot\" id=\"DTCB_Test_In_Tot\" value=\""+DTCB_Test_In_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCB_Test_In_Tot');testedintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"             
              + "<td rowspan=\"2\">3.</td><td rowspan=\"2\" >No. HIV+</td><td>Outpatient</td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_CF\" id=\"DTCC_HIV_Out_CF\" value=\""+DTCC_HIV_Out_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_Out_CF');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_CM\" id=\"DTCC_HIV_Out_CM\" value=\""+DTCC_HIV_Out_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_Out_CM');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_AF\" id=\"DTCC_HIV_Out_AF\" value=\""+DTCC_HIV_Out_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_Out_AF');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_AM\" id=\"DTCC_HIV_Out_AM\" value=\""+DTCC_HIV_Out_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_Out_AM');hivouttotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_Out_Tot\" id=\"DTCC_HIV_Out_Tot\" value=\""+DTCC_HIV_Out_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_Out_Tot');hivouttotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"  
              + "<tr>"
              + "<td>Inpatient</td>"            
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_CF\" id=\"DTCC_HIV_In_CF\" value=\""+DTCC_HIV_In_CF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_In_CF');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_CM\" id=\"DTCC_HIV_In_CM\" value=\""+DTCC_HIV_In_CM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_In_CM');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_AF\" id=\"DTCC_HIV_In_AF\" value=\""+DTCC_HIV_In_AF+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_In_AF');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_AM\" id=\"DTCC_HIV_In_AM\" value=\""+DTCC_HIV_In_AM+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_In_AM');hivintotal();\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "<td ><input type=\"text\" name=\"DTCC_HIV_In_Tot\" id=\"DTCC_HIV_In_Tot\" value=\""+DTCC_HIV_In_Tot+"\" autocomplete=\"off\"  onblur=\"autosave('DTCC_HIV_In_Tot');hivintotal();\" tabindex='-1' readonly maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>" 
            +"</table></fieldset>"; 
           
           
           DTC_TAB+=dtc;
           

           
           
           
           
           
           
         
         
            ancpmct_tab+="</div></div></div>";
            maternity_tab+="</div></div></div>";
            sgbv_tab+="</div></div></div>";
            FamilyPlanninng_tab+="</div></div></div>";
            pac_tab+="</div></div></div>";
            chanisweight_tab+="</div></div></div>";
            chanisheight_tab+="</div></div></div>";
            cervicalcancer_tab+="</div></div></div>";
            pnc_tab+="</div></div></div>";
            rehabilitation_tab+="</div></div></div>";
            msw_tab+="</div></div></div>";
            physiotherapy_tab+="</div></div></div>";
            
            
           VCT_TAB+="</div></div></div>";
           DTC_TAB+="</div></div></div>";
           data+=validitychecker+" "+ ul+" "+ancpmctpane+""+matpane+""+sgbvpane+""+fppane+""+pacpane+""+chanisweight_pane+""+chanisheight_pane+""+cervical_pane+""+pnc_pane+rs_pane+""+msw_pane+""+ps_pane+validatepane+" "+mainpane;
    //add the headers based on if the header is active
          
             if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
          data+=ancpmct_tab;
          }
          
          if(session.getAttribute("forms_holder").toString().contains(",Maternity,")){
          data+=maternity_tab;
          }
          
        if(1==newtablesactive){
          data+=sgbv_tab;
          }
        
        if(session.getAttribute("forms_holder").toString().contains(",FP,")){
          data+=FamilyPlanninng_tab;
          }
        
        if(1==newtablesactive){
          data+=pac_tab;
          }
        
        if(1==newtablesactive){
          data+=chanisweight_tab;
          }
        if(1==newtablesactive){
          data+=chanisheight_tab;
          }
         if(1==newtablesactive){
          data+=cervicalcancer_tab;
          } 
         
          if(1==newtablesactive){
          data+=pnc_tab;
          }
          
          if(1==newtablesactive){
          data+=rehabilitation_tab;
          }
          
          if(1==newtablesactive){
          data+=msw_tab;
          }
          if(1==newtablesactive){
          data+=physiotherapy_tab;
          }
          
//          if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
//          data+=VCT_TAB+""+DTC_TAB;
//          }
         
       
          data+="<div class='form-actions'>" +
"                              <button type='submit' class='btn blue'>Run Validation</button>" +
"                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;   "+ enterdby+"" +
"                           </div>";
           data+="  </div> ";
           }else{
               data="<font color=\"red\" size=\"6px;\" ><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support module MOH 711A.</font>";
               }
           
           
              String unvalidatedLink="";int counter=0;
     if(invalidFP>0 || invalidHTC>0||invalidMAT>0||invalidPMTCT>0){
     String getUnvalidated="SELECT moh711_new.SubPartnerID,subpartnera.SubPartnerNom FROM moh711_new JOIN subpartnera ON moh711_new.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND moh711_new.Mois='"+month+"' AND moh711_new.Annee='"+year+"' AND moh711_new.isValidated='0'";
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
     * FPCocRparam request servlet request
     * FPCocRparam response servlet response
     * FPCocRthrows ServletException if a servlet-specific error occurs
     * FPCocRthrows IOException if an I/O error occurs
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
     * FPCocRparam request servlet request
     * FPCocRparam response servlet response
     * FPCocRthrows ServletException if a servlet-specific error occurs
     * FPCocRthrows IOException if an I/O error occurs
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
     * FPCocRreturn a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

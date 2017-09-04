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
 * @author GNyabuto
 */
public class loadnew731 extends HttpServlet {

HttpSession session;
String data,id;
String facilityId,year,month;
String HIV_TPS,PMTCT,HIV_TB,VMMC,PEP,MAT;

String s1_HIV_testing,s1_HIV_postive_results,s1_HIV_postive_3_months_ago_linked_to_care,s1_pre_exposure_prophylaxis;

String s2_antenatal_and_delivery_contacts,s2_maternal_HIV_testing,s2_HIV_postive_results,s2_maternal_HAART,
        s2_retention_to_PMTCT_ARVs,s2_syphillis_screening,s2_family_planning_at_postnatal,s2_male_partner_HIV_status,
        s2_adolscents_10_19_testing_and_results,s2_infant_HIV_exposure_status_At_penta_1,s2_infant_prophylaxis,
        s2_infant_testing,s2_programme_outcommes,s2_retention_of_baby_mother_pairs,s2_infant_feeding_for_HIVP_mothers;

String s3_enrollment_in_care,s3_current_on_pre_ART,s3_starting_ART,s3_currently_on_ART,s3_retention_on_ART,
        s3_on_CTX_Dapsone,s3_TB_screening_and_presumed_TB,s3_starting_IPT,s3_nutrition_and_HIV,s3_HIV_in_TB_clinic,
        s3_community_dispensing_of_ARVs,s3_family_planning_CaCx_screen_in_HIV_CCC;

String s4_number_circumcised,s4_type_of_circumcision,s4_circumcision_adverse_events;

String s5_post_exposure_prophylaxis;

String s6_methadone_assisted_therapy;

String HV0101,HV0102,HV0103,HV0104,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116,HV0117,HV0118,HV0119,HV0120,HV0121,HV0122,HV0123,HV0124,HV0125,HV0126,
        HV0127,HV0128,HV0129,HV0130,HV0131,HV0132,HV0133,HV0134,HV0135,HV0136,HV0137,HV0138,HV0139,HV0140;

String HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
        HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0222,HV0223,HV0224,HV0225,HV0226,HV0227,
        HV0228,HV0229,HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,
        HV0242,HV0243,HV0244,HV0245,HV0246,HV0247,HV0248,HV0249,HV0250,HV0251,HV0252,HV0253,HV0254,HV0255,
        HV0256,HV0257,HV0258;

String HV03001,HV03002,HV03003,HV03004,HV03005,HV03006,HV03007,HV03008,HV03009,HV03010,HV03011,HV03012,HV03013,HV03014,
        HV03015,HV03016,HV03017,HV03018,HV03019,HV03020,HV03021,HV03022,HV03023,HV03024,HV03025,HV03026,HV03027,HV03028,
        HV03029,HV03030,HV03031,HV03032,HV03033,HV03034,HV03035,HV03036,HV03037,HV03038,HV03039,HV03045,HV03046,HV03047,
        HV03048,HV03049,HV03050,HV03051,HV03052,HV03053,HV03054,HV03055,HV03056,HV03057,HV03058,HV03059,HV03060,HV03061,
        HV03062,HV03063,HV03064,HV03065,HV03066,HV03067,HV03068,HV03069,HV03070,HV03071,HV03072,HV03073,HV03074,HV03075,
        HV03076,HV03077,HV03078,HV03079,HV03080,HV03081,HV03082,HV03083,HV03084,HV03085,HV03086,HV03087,HV03088,HV03089;

String HV0401,HV0402,HV0403,HV0404,HV0405,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,
        HV0415,HV0416,HV0417;

String HV0501,HV0502,HV0503,HV0504,HV0505,HV0506;

String HV0601,HV0602,HV0603,HV0604,HV0605;

//String HV03040_1,HV03041_1,HV03042_1,HV03043_1,HV03044_1;

String HV03040,HV03041,HV03042,HV03043,HV03044;
 
String isValidated,validity;

int markActive;

String classType="";

int expectedPMTCT,expectedCARE,expectedPEP,expectedHTC,expectedVMMC,expectedMAT;

int validPMTCT,invalidPMTCT,totalPMTCT;

int validCARE,invalidCARE,totalCARE;

int validPEP,invalidPEP,totalPEP;

int validHTC,invalidHTC,totalHTC;

int validVMMC,invalidVMMC,totalVMMC;

int validMAT,invalidMAT,totalMAT;

String enterdby,tabs,subcountyid;

String invalidPMTCTTXT,invalidCARETXT,invalidPEPTXT,invalidHTCTXT,invalidVMMCTXT,invalidMATTXT;

String unvalidatedFacilities=""; 

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");

        String lock = null;
int isLocked=0;
        HIV_TPS=PMTCT=HIV_TB=VMMC=PEP=MAT="";
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
           markActive=0; 
           classType="";
           expectedHTC=expectedPMTCT=expectedCARE=expectedPEP=expectedVMMC=expectedMAT=0;
     validPMTCT=invalidPMTCT=totalPMTCT=0;
     validCARE=invalidCARE=totalCARE=0;
     validPEP=invalidPEP=totalPEP=0;
     validHTC=invalidHTC=totalHTC=0;
     validVMMC=invalidVMMC=totalVMMC=0;
     validMAT=invalidMAT=totalMAT=0;
     
enterdby=tabs="";
subcountyid="";
unvalidatedFacilities="";

//clear cached current tableid session
session.removeAttribute("table_id");

     if(session.getAttribute("forms_holder")!=null && !session.getAttribute("forms_holder").toString().equals(",")){ 
         if(session.getAttribute("forms_holder").toString().contains(",PMTCT,") || session.getAttribute("forms_holder").toString().contains(",ART,") || 
   session.getAttribute("forms_holder").toString().contains(",PEP,") ||  session.getAttribute("forms_holder").toString().contains(",HTC,") ){
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
         
    data="";
    tabs=" <div class=\"tabbable tabbable-custom boxless\" style=\"margin-left:5%; margin-right:5%\">" +
"                     <ul class=\"nav nav-tabs\">"; 
    
     markActive=0;
     if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
        if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li style=\"min-width:10%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_1\" data-toggle=\"tab\"><b>1. HTS.</b></a></li>";
    markActive++; 
    }
    
    if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
        markActive++;
          if(markActive==0){classType="class=\"active\"";}else{classType="";}
        
         tabs+=" <li  style=\"min-width:10%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_2\" data-toggle=\"tab\"><b>2. PMTCT </b></a></li>";
         }
    if(session.getAttribute("forms_holder").toString().contains(",ART,")){
        if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li style=\"min-width:15%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_3\" data-toggle=\"tab\"><b>3. HIV and TB treatment.</b></a></li>";
    markActive++; 
    }
    if(1==1){
         if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li style=\"min-width:10%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_4\" data-toggle=\"tab\"><b>4. VMMC.</b></a></li>";
     markActive++;
    }
    if(session.getAttribute("forms_holder").toString().contains(",PEP,")){
         if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li style=\"min-width:10%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_5\" data-toggle=\"tab\"><b>5. PEP.</b></a></li>";
     markActive++;
    }
    if(1==1){
         if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li style=\"min-width:10%\" "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_6\" data-toggle=\"tab\"><b>6. Methadone Assisted Therapy.</b></a></li>";
     markActive++;
    }

         tabs+="<li style=\"margin-left:20px;\" id=\"isValidated\"></li>" +
                "</ul>" +
                "</div>" +
                "<div class=\"tab-content\" id=\"data\">";
    
    markActive=0;
    classType="";
//          id="2015_1_14498";
    
   
   String getExpectedForms="SELECT SUM(PMTCT),SUM(ART),SUM(PEP),SUM(HTC),SUM(VMMC) FROM subpartnera WHERE subpartnera.DistrictID='"+subcountyid+"'" ;
   conn.rs1=conn.st1.executeQuery(getExpectedForms);
   if(conn.rs1.next()==true){
//       System.out.println("pmtct : "+conn.rs1.getString(1)+"  care : "+conn.rs1.getInt(2)+" pep : "+conn.rs1.getInt(3));
           expectedPMTCT=conn.rs1.getInt(1);
           expectedCARE=conn.rs1.getInt(2);
           expectedPEP=conn.rs1.getInt(3);
           expectedHTC=conn.rs1.getInt(4);
           expectedVMMC=conn.rs1.getInt(5);
           expectedMAT=conn.rs1.getInt(5); //Know the right column to read from
   }
    validPMTCT=invalidPMTCT=totalPMTCT=0;
     validCARE=invalidCARE=totalCARE=0;
     validPEP=invalidPEP=totalPEP=0;
     validHTC=invalidHTC=totalHTC=0;
     validVMMC=invalidVMMC=totalVMMC=0;
     validMAT=invalidMAT=totalMAT=0;
     
    String getEntered="SELECT moh731_new.isValidated,SUM(subpartnera.PMTCT),SUM(subpartnera.ART),SUM(subpartnera.PEP),SUM(subpartnera.HTC),SUM(subpartnera.VMMC)"
            + " FROM subpartnera JOIN moh731_new ON subpartnera.SubPartnerID=moh731_new.SubPartnerID WHERE "
            + "moh731_new.Mois='"+month+"' AND moh731_new.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY moh731_new.isValidated";
    conn.rs1=conn.st1.executeQuery(getEntered);
    while(conn.rs1.next()){
        System.out.println("isvalidated : "+conn.rs1.getInt(1)+"  num : "+conn.rs1.getInt(2));
   if(conn.rs1.getInt(1)==1){
    validPMTCT=conn.rs1.getInt(2);
    validCARE=conn.rs1.getInt(3);
    validPEP=conn.rs1.getInt(4);
    validHTC=conn.rs1.getInt(5);
    validVMMC=conn.rs1.getInt(6);
    validMAT=conn.rs1.getInt(6); //Know the right column to read from
   }
   if(conn.rs1.getInt(1)==0){
      invalidPMTCT=conn.rs1.getInt(2);
      invalidCARE=conn.rs1.getInt(3);
      invalidPEP=conn.rs1.getInt(4);
      invalidHTC=conn.rs1.getInt(5);
      invalidVMMC=conn.rs1.getInt(6);
      invalidMAT=conn.rs1.getInt(6); // Know the right column to read from
      
   }
    }
    totalPMTCT=validPMTCT+invalidPMTCT;
    totalCARE=validCARE+invalidCARE;
    totalPEP=validPEP+invalidPEP;
    totalHTC=validHTC+invalidHTC;
    totalVMMC=validVMMC+invalidVMMC;
    totalMAT=validMAT+invalidMAT;
// invalidPMTCTTXT="Unvalidated Form(s) : "+invalidPMTCT;
// invalidCARETXT="Unvalidated Form(s) : "+invalidCARE;
// invalidPEPTXT="Unvalidated Form(s) : "+invalidPEP;
  
  invalidPMTCTTXT=" Unvalidated Form(s) : 0";
 invalidCARETXT=" Unvalidated Form(s) : 0";
 invalidPEPTXT=" Unvalidated Form(s) : 0";
 invalidHTCTXT=" Unvalidated Form(s) : 0";
 invalidVMMCTXT=" Unvalidated Form(s) : 0";
 invalidMATTXT=" Unvalidated Form(s) : 0";
 
    if(invalidPMTCT>0)
    {
   invalidPMTCTTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidPMTCT+"</span></button>";
    }
   
    if(invalidCARE>0)
    {
  invalidCARETXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidCARE+"</span></button>";       
    }
    
    if(invalidPEP>0)
    {
  invalidPEPTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidPEP+"</span></button>";       
    }
    if(invalidHTC>0)
    {
  invalidHTCTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidHTC+"</span></button>";       
    }
    if(invalidVMMC>0)
    {
  invalidVMMCTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidVMMC+"</span></button>";       
    }
    if(invalidMAT>0)
    {
  invalidMATTXT="<button type=\"button\" class=\"btn btn-primary btn-lg\" data-toggle=\"modal\" style=\"width:auto; height:auto;\" data-target=\"#unvalidatedModal\"> Unvalidated Form(s) : <span class=\"badge badge-important\">"+invalidMAT+"</span></button>";       
    }
    int tabcount=1;
     markActive=0;
     String myclass="";
     if(session.getAttribute("forms_holder").toString().contains(",HTC,"))
        {
            if(markActive==0){classType="active";}else{classType="";}
            
        HIV_TPS="<div class=\"tab-pane "+classType+" \" id=\"tab_1\"><div class=\"portlet box blue\">" +
                              "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"> <b>1. HIV Counselling and Testing</b> <b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalHTC+" out of "+expectedHTC+" &nbsp; Validated Form(s) : "+validHTC+"  &nbsp; "+invalidHTCTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
   markActive++;
    tabcount++;
         }
     
        if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
                if(markActive==0){classType="active";}else{classType="";}
       
        PMTCT="<div class=\"tab-pane "+classType+" \"   id=\"tab_2\"><div class=\"portlet box blue\">" +
                              "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>2. Prevention of Mother-to-Child Transmission.</b><b style=\"color:yellow; font-family:cambria;  margin-left:10%; font-size:16px;\"> Record Counter: "+totalPMTCT+" out of "+expectedPMTCT+" &nbsp; Validated Form(s) : "+validPMTCT+"  &nbsp; "+invalidPMTCTTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++; 
        tabcount++;
        }
         if(session.getAttribute("forms_holder").toString().contains(",ART,")){
        if(markActive==0){classType="active";}else{classType="";}
        HIV_TB="<div class=\"tab-pane "+classType+" \" id=\"tab_3\"><div class=\"portlet box blue\">" +
                              "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>3. Care and Treatment.</b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalCARE+" out of "+expectedCARE+" &nbsp; Validated Form(s) : "+validCARE+"  &nbsp; "+invalidCARETXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
//         session.getAttribute("forms_holder").toString().contains(",VMMC,")
         if(1==1){
              if(markActive==0){classType="active";}else{classType="";}
        VMMC+="<div class=\"tab-pane "+classType+" \" id=\"tab_4\"><div class=\"portlet box blue\">" +
                             "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>4. VMMC. </b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalPEP+" out of "+expectedPEP+" &nbsp; Validated Form(s) : "+validPEP+"  &nbsp; "+invalidPEPTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
         if(session.getAttribute("forms_holder").toString().contains(",PEP,")){
              if(markActive==0){classType="active";}else{classType="";}
        PEP="<div class=\"tab-pane "+classType+" \" id=\"tab_5\"><div class=\"portlet box blue\">" +
                             "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>5. Post-Exposure Prophylaxis. </b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalPEP+" out of "+expectedPEP+" &nbsp; Validated Form(s) : "+validPEP+"  &nbsp; "+invalidPEPTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
    //session.getAttribute("forms_holder").toString().contains(",MAT,")
         if(1==1){
              if(markActive==0){classType="active";}else{classType="";}
        MAT+="<div class=\"tab-pane "+classType+" \" id=\"tab_6\"><div class=\"portlet box blue\">" +
                             "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>6. Methadone Assisted Therapy. </b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalPEP+" out of "+expectedPEP+" &nbsp; Validated Form(s) : "+validPEP+"  &nbsp; "+invalidPEPTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
         
        
  isValidated="";validity="";          
HV0101=HV0102=HV0104=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=
    HV0115=HV0116=HV0117=HV0118=HV0119=HV0120=HV0121=HV0122=HV0123=HV0124=HV0125=HV0126=
    HV0127=HV0128=HV0129=HV0130=HV0131=HV0132=HV0133=HV0134=HV0135=HV0136=HV0137=HV0138=HV0139=
    HV0140="";
 
    HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
    HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0222=HV0223=HV0224=HV0225=HV0226=
    HV0227=HV0228=HV0229=HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=
    HV0240=HV0241=HV0242=HV0243=HV0244=HV0245=HV0246=HV0247=HV0248=HV0249=HV0250=HV0251=HV0252=
    HV0253=HV0254=HV0255=HV0256=HV0257=HV0258="";

    HV03001=HV03002=HV03003=HV03004=HV03005=HV03006=HV03007=HV03008=HV03009=HV03010=HV03011=HV03012=HV03013=HV03014=
    HV03015=HV03016=HV03017=HV03018=HV03019=HV03020=HV03021=HV03022=HV03023=HV03024=HV03025=HV03026=HV03027=HV03028=
    HV03029=HV03030=HV03031=HV03032=HV03033=HV03034=HV03035=HV03036=HV03037=HV03038=HV03039=HV03045=HV03046=HV03047=
    HV03048=HV03049=HV03050=HV03051=HV03052=HV03053=HV03054=HV03055=HV03056=HV03057=HV03058=HV03059=HV03060=HV03061=
    HV03062=HV03063=HV03064=HV03065=HV03066=HV03067=HV03068=HV03069=HV03070=HV03071=HV03072=HV03073=HV03074=HV03075=
    HV03076=HV03077=HV03078=HV03079=HV03080=HV03081=HV03082=HV03083=HV03084=HV03085=HV03086=HV03087=HV03088=HV03089="";

    HV0401=HV0402=HV0403=HV0404=HV0405=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=
    HV0415=HV0416=HV0417="";
    
    HV0501=HV0502=HV0503=HV0504=HV0505=HV0506="";
    
    HV0601=HV0602=HV0603=HV0604=HV0605="";
 
//    HV03040_1=HV03041_1=HV03042_1=HV03043_1=HV03044_1="0";
    
    HV03040=HV03041=HV03042=HV03043=HV03044="";
 
 
   
String tempmonth=month;
int pepfaryear=Integer.parseInt(year);
if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
else {pepfaryear--;}
 String yearmonth="";
yearmonth=pepfaryear+""+tempmonth;

//____We need to get the cumulatives up to the maximum reported yearmonth   as long as its less than the current month and year
//eg if its 2016 jan, get the max reported yearmonth.from 2014 12 backwards. Also ensure that the value of accumulatives for that max month is not Zero
//this is the default maxyearmonth
String maxyearmonth="201503";
 
 
String getmaxmonthandyear="select Max(yearmonth) as yearmo from moh731_new where yearmonth < '"+yearmonth+"' and (HV03040 !='0' && HV03040 is not null) and SubPartnerID='"+facilityId+"'";
  
            System.out.println("__"+getmaxmonthandyear);

conn.rs2=conn.st2.executeQuery(getmaxmonthandyear);

if(conn.rs2.next()){
    
    if(conn.rs2.getString(1)!=null){
maxyearmonth=conn.rs2.getString(1);
    }

}
int yrmonth=Integer.parseInt(maxyearmonth);
//if(yrmonth>201503 && yrmonth<=201504){
 System.out.println("Max Year Month on loading is____"+maxyearmonth);
//After getting the Maximum valid YearMonth,(By Valid I mean a month that was reported with a cumulative greater than zero) select the cumulatives for male, female and totals
//
//    String gatmaxs="select HV03040 , HV03041 ,HV03042,HV03043,HV03044  from moh731_new where yearmonth='"+maxyearmonth+"' and SubPartnerID='"+facilityId+"'";
//             System.out.println("max query: "+gatmaxs);
//      conn.rs=conn.st.executeQuery(gatmaxs);
//    if(conn.rs.next()==true){
//    
//HV03040_1=conn.rs.getString("HV03040");
//if(HV03040_1==null){HV03040_1="0"; }
//
//HV03041_1=conn.rs.getString("HV03041");
//if(HV03041_1==null){HV03041_1="0"; }
//
//HV03042_1=conn.rs.getString("HV03042");
//if(HV03042_1==null){HV03042_1="0"; }
// 
//HV03043_1=conn.rs.getString("HV03043");
//if(HV03043_1==null){HV03043_1="0"; }
//
//HV03044_1=conn.rs.getString("HV03044");
//if(HV03044_1==null){HV03044_1="0"; }
//
//        System.out.println("baseline exist: ");
//}
//    else{
////        Get baselines
//        String baselines="SELECT HV03040 , HV03041 ,HV03042,HV03043,HV03044 FROM moh731_baseline_new WHERE SubPartnerID='"+facilityId+"'";
//       conn.rs=conn.st.executeQuery(baselines);
//    if(conn.rs.next()==true){
//    
//HV03040_1=conn.rs.getString(1);
//HV03041_1=conn.rs.getString(2);
//HV03042_1=conn.rs.getString(3);
//HV03043_1=conn.rs.getString(4);
//HV03044_1=conn.rs.getString(5);
//}
//        System.out.println("getting baselines");
//    }    
//             System.out.println("after loading baselines");
////}
// HV03040=HV03040_1;HV03041=HV03041_1;HV03042=HV03042_1;HV03043=HV03043_1;HV03044=HV03044_1;
     
          String checker="SELECT * FROM moh731_new WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
         session.setAttribute("facili_marked",true);    
              //get the name of the person who entered the form 
        
        String enterer="select * from user where userid='"+conn.rs.getString("user_id") +"'";
        
        conn.rs1=conn.st1.executeQuery(enterer);
        //add details of person who entered
        if(conn.rs1.next()){
        enterdby="<font color='green'>Data 1st entered by:   <b> "+conn.rs1.getString("fname")+" "+conn.rs1.getString("mname")+" "+conn.rs1.getString("lname")+"</b>  on  <b>"+conn.rs.getString("timestamp") +"</b></font>";
        }
        
		
		//now check if form was updated and if its one month after data entry
        
        if(conn.rs.getString("updatedOn")!=null){
        //get difference in months between entered date and updated date
        String compdate="SELECT TIMESTAMPDIFF(MONTH,'"+conn.rs.getString("timestamp") +"','"+conn.rs.getString("updatedOn") +"')";
        conn.rs2=conn.st2.executeQuery(compdate);
        if (conn.rs2.next()){
            //now get the details of the person who updated the form
        //if the difference is greater than or equal to one, 
        
            
            if(conn.rs2.getInt(1)>=1){
        String updater="select * from user where userid='"+conn.rs.getString("updatedBy") +"'";
        
        conn.rs1=conn.st1.executeQuery(updater);
        //add details of person who entered
        if(conn.rs1.next()){
            enterdby += "<span style='margin-left:30%;'><font color='red'>   Updated  by:   <b> " + conn.rs1.getString("fname") + " " + conn.rs1.getString("mname") + " " + conn.rs1.getString("lname") + "</b>  on  <b>" + conn.rs.getString("updatedOn") + "</b></font></span>";
                            }
        } //end of if month >=1 
        }//end of date comparison if 
        
        }//end of if updated !=null
              
              
//      set table_id session VERY IMPORTANT
      session.setAttribute("table_id", id);
      
      System.out.println("Data already exist loading............................");

  if(conn.rs.getString("HV0101")!=null){HV0101=conn.rs.getString("HV0101");}
  if(conn.rs.getString("HV0102")!=null){HV0102=conn.rs.getString("HV0102");}
  if(conn.rs.getString("HV0103")!=null){HV0103=conn.rs.getString("HV0103");}
  if(conn.rs.getString("HV0104")!=null){HV0104=conn.rs.getString("HV0104");}
  if(conn.rs.getString("HV0105")!=null){HV0105=conn.rs.getString("HV0105");}
  if(conn.rs.getString("HV0106")!=null){HV0106=conn.rs.getString("HV0106");}
  if(conn.rs.getString("HV0107")!=null){HV0107=conn.rs.getString("HV0107");}
  if(conn.rs.getString("HV0108")!=null){HV0108=conn.rs.getString("HV0108");}
  if(conn.rs.getString("HV0109")!=null){HV0109=conn.rs.getString("HV0109");}
  if(conn.rs.getString("HV0110")!=null){HV0110=conn.rs.getString("HV0110");}
  if(conn.rs.getString("HV0111")!=null){HV0111=conn.rs.getString("HV0111");}
  if(conn.rs.getString("HV0112")!=null){HV0112=conn.rs.getString("HV0112");}
  if(conn.rs.getString("HV0113")!=null){HV0113=conn.rs.getString("HV0113");}
  if(conn.rs.getString("HV0114")!=null){HV0114=conn.rs.getString("HV0114");}
  if(conn.rs.getString("HV0115")!=null){HV0115=conn.rs.getString("HV0115");}
  if(conn.rs.getString("HV0116")!=null){HV0116=conn.rs.getString("HV0116");}
  if(conn.rs.getString("HV0117")!=null){HV0117=conn.rs.getString("HV0117");}
  if(conn.rs.getString("HV0118")!=null){HV0118=conn.rs.getString("HV0118");}
  if(conn.rs.getString("HV0119")!=null){HV0119=conn.rs.getString("HV0119");}
  if(conn.rs.getString("HV0120")!=null){HV0120=conn.rs.getString("HV0120");}
  if(conn.rs.getString("HV0121")!=null){HV0121=conn.rs.getString("HV0121");}
  if(conn.rs.getString("HV0122")!=null){HV0122=conn.rs.getString("HV0122");}
  if(conn.rs.getString("HV0123")!=null){HV0123=conn.rs.getString("HV0123");}
  if(conn.rs.getString("HV0124")!=null){HV0124=conn.rs.getString("HV0124");}
  if(conn.rs.getString("HV0125")!=null){HV0125=conn.rs.getString("HV0125");}
  if(conn.rs.getString("HV0126")!=null){HV0126=conn.rs.getString("HV0126");}
  if(conn.rs.getString("HV0127")!=null){HV0127=conn.rs.getString("HV0127");}
  if(conn.rs.getString("HV0128")!=null){HV0128=conn.rs.getString("HV0128");}
  if(conn.rs.getString("HV0129")!=null){HV0129=conn.rs.getString("HV0129");}
  if(conn.rs.getString("HV0130")!=null){HV0130=conn.rs.getString("HV0130");}
  if(conn.rs.getString("HV0131")!=null){HV0131=conn.rs.getString("HV0131");}
  if(conn.rs.getString("HV0132")!=null){HV0132=conn.rs.getString("HV0132");}
  if(conn.rs.getString("HV0133")!=null){HV0133=conn.rs.getString("HV0133");}
  if(conn.rs.getString("HV0134")!=null){HV0134=conn.rs.getString("HV0134");}
  if(conn.rs.getString("HV0135")!=null){HV0135=conn.rs.getString("HV0135");}
  if(conn.rs.getString("HV0136")!=null){HV0136=conn.rs.getString("HV0136");}
  if(conn.rs.getString("HV0137")!=null){HV0137=conn.rs.getString("HV0137");}
  if(conn.rs.getString("HV0138")!=null){HV0138=conn.rs.getString("HV0138");}
  if(conn.rs.getString("HV0139")!=null){HV0139=conn.rs.getString("HV0139");}
  if(conn.rs.getString("HV0140")!=null){HV0140=conn.rs.getString("HV0140");}
 
if(conn.rs.getString("HV0201")!=null){HV0201=conn.rs.getString("HV0201");}
if(conn.rs.getString("HV0202")!=null){HV0202=conn.rs.getString("HV0202");}
if(conn.rs.getString("HV0203")!=null){HV0203=conn.rs.getString("HV0203");}
if(conn.rs.getString("HV0204")!=null){HV0204=conn.rs.getString("HV0204");}
if(conn.rs.getString("HV0205")!=null){HV0205=conn.rs.getString("HV0205");}
if(conn.rs.getString("HV0206")!=null){HV0206=conn.rs.getString("HV0206");}
if(conn.rs.getString("HV0207")!=null){HV0207=conn.rs.getString("HV0207");}
if(conn.rs.getString("HV0208")!=null){HV0208=conn.rs.getString("HV0208");}
if(conn.rs.getString("HV0209")!=null){HV0209=conn.rs.getString("HV0209");}
if(conn.rs.getString("HV0210")!=null){HV0210=conn.rs.getString("HV0210");}
if(conn.rs.getString("HV0211")!=null){HV0211=conn.rs.getString("HV0211");}
if(conn.rs.getString("HV0212")!=null){HV0212=conn.rs.getString("HV0212");}
if(conn.rs.getString("HV0213")!=null){HV0213=conn.rs.getString("HV0213");}
if(conn.rs.getString("HV0214")!=null){HV0214=conn.rs.getString("HV0214");}
if(conn.rs.getString("HV0215")!=null){HV0215=conn.rs.getString("HV0215");}
if(conn.rs.getString("HV0216")!=null){HV0216=conn.rs.getString("HV0216");}
if(conn.rs.getString("HV0217")!=null){HV0217=conn.rs.getString("HV0217");}
if(conn.rs.getString("HV0218")!=null){HV0218=conn.rs.getString("HV0218");}
if(conn.rs.getString("HV0219")!=null){HV0219=conn.rs.getString("HV0219");}
if(conn.rs.getString("HV0220")!=null){HV0220=conn.rs.getString("HV0220");}
if(conn.rs.getString("HV0221")!=null){HV0221=conn.rs.getString("HV0221");}
if(conn.rs.getString("HV0222")!=null){HV0222=conn.rs.getString("HV0222");}
if(conn.rs.getString("HV0223")!=null){HV0223=conn.rs.getString("HV0223");}
if(conn.rs.getString("HV0224")!=null){HV0224=conn.rs.getString("HV0224");}
if(conn.rs.getString("HV0225")!=null){HV0225=conn.rs.getString("HV0225");}
if(conn.rs.getString("HV0226")!=null){HV0226=conn.rs.getString("HV0226");}
if(conn.rs.getString("HV0227")!=null){HV0227=conn.rs.getString("HV0227");}
if(conn.rs.getString("HV0228")!=null){HV0228=conn.rs.getString("HV0228");}
if(conn.rs.getString("HV0229")!=null){HV0229=conn.rs.getString("HV0229");}
if(conn.rs.getString("HV0230")!=null){HV0230=conn.rs.getString("HV0230");}
if(conn.rs.getString("HV0231")!=null){HV0231=conn.rs.getString("HV0231");}
if(conn.rs.getString("HV0232")!=null){HV0232=conn.rs.getString("HV0232");}
if(conn.rs.getString("HV0233")!=null){HV0233=conn.rs.getString("HV0233");}
if(conn.rs.getString("HV0234")!=null){HV0234=conn.rs.getString("HV0234");}
if(conn.rs.getString("HV0235")!=null){HV0235=conn.rs.getString("HV0235");}
if(conn.rs.getString("HV0236")!=null){HV0236=conn.rs.getString("HV0236");}
if(conn.rs.getString("HV0237")!=null){HV0237=conn.rs.getString("HV0237");}
if(conn.rs.getString("HV0238")!=null){HV0238=conn.rs.getString("HV0238");}
if(conn.rs.getString("HV0239")!=null){HV0239=conn.rs.getString("HV0239");}
if(conn.rs.getString("HV0240")!=null){HV0240=conn.rs.getString("HV0240");}
if(conn.rs.getString("HV0241")!=null){HV0241=conn.rs.getString("HV0241");}
if(conn.rs.getString("HV0242")!=null){HV0242=conn.rs.getString("HV0242");}
if(conn.rs.getString("HV0243")!=null){HV0243=conn.rs.getString("HV0243");}
if(conn.rs.getString("HV0244")!=null){HV0244=conn.rs.getString("HV0244");}
if(conn.rs.getString("HV0245")!=null){HV0245=conn.rs.getString("HV0245");}
if(conn.rs.getString("HV0246")!=null){HV0246=conn.rs.getString("HV0246");}
if(conn.rs.getString("HV0247")!=null){HV0247=conn.rs.getString("HV0247");}
if(conn.rs.getString("HV0248")!=null){HV0248=conn.rs.getString("HV0248");}
if(conn.rs.getString("HV0249")!=null){HV0249=conn.rs.getString("HV0249");}
if(conn.rs.getString("HV0250")!=null){HV0250=conn.rs.getString("HV0250");}
if(conn.rs.getString("HV0251")!=null){HV0251=conn.rs.getString("HV0251");}
if(conn.rs.getString("HV0252")!=null){HV0252=conn.rs.getString("HV0252");}
if(conn.rs.getString("HV0253")!=null){HV0253=conn.rs.getString("HV0253");}
if(conn.rs.getString("HV0254")!=null){HV0254=conn.rs.getString("HV0254");}
if(conn.rs.getString("HV0255")!=null){HV0255=conn.rs.getString("HV0255");}
if(conn.rs.getString("HV0256")!=null){HV0256=conn.rs.getString("HV0256");}
if(conn.rs.getString("HV0257")!=null){HV0257=conn.rs.getString("HV0257");}
if(conn.rs.getString("HV0258")!=null){HV0258=conn.rs.getString("HV0258");}

if(conn.rs.getString("HV03001")!=null){HV03001=conn.rs.getString("HV03001");}
if(conn.rs.getString("HV03002")!=null){HV03002=conn.rs.getString("HV03002");}
if(conn.rs.getString("HV03003")!=null){HV03003=conn.rs.getString("HV03003");}
if(conn.rs.getString("HV03004")!=null){HV03004=conn.rs.getString("HV03004");}
if(conn.rs.getString("HV03005")!=null){HV03005=conn.rs.getString("HV03005");}
if(conn.rs.getString("HV03006")!=null){HV03006=conn.rs.getString("HV03006");}
if(conn.rs.getString("HV03007")!=null){HV03007=conn.rs.getString("HV03007");}
if(conn.rs.getString("HV03008")!=null){HV03008=conn.rs.getString("HV03008");}
if(conn.rs.getString("HV03009")!=null){HV03009=conn.rs.getString("HV03009");}
if(conn.rs.getString("HV03010")!=null){HV03010=conn.rs.getString("HV03010");}
if(conn.rs.getString("HV03011")!=null){HV03011=conn.rs.getString("HV03011");}
if(conn.rs.getString("HV03012")!=null){HV03012=conn.rs.getString("HV03012");}
if(conn.rs.getString("HV03013")!=null){HV03013=conn.rs.getString("HV03013");}
if(conn.rs.getString("HV03014")!=null){HV03014=conn.rs.getString("HV03014");}
if(conn.rs.getString("HV03015")!=null){HV03015=conn.rs.getString("HV03015");}
if(conn.rs.getString("HV03016")!=null){HV03016=conn.rs.getString("HV03016");}
if(conn.rs.getString("HV03017")!=null){HV03017=conn.rs.getString("HV03017");}
if(conn.rs.getString("HV03018")!=null){HV03018=conn.rs.getString("HV03018");}
if(conn.rs.getString("HV03019")!=null){HV03019=conn.rs.getString("HV03019");}
if(conn.rs.getString("HV03020")!=null){HV03020=conn.rs.getString("HV03020");}
if(conn.rs.getString("HV03021")!=null){HV03021=conn.rs.getString("HV03021");}
if(conn.rs.getString("HV03022")!=null){HV03022=conn.rs.getString("HV03022");}
if(conn.rs.getString("HV03023")!=null){HV03023=conn.rs.getString("HV03023");}
if(conn.rs.getString("HV03024")!=null){HV03024=conn.rs.getString("HV03024");}
if(conn.rs.getString("HV03025")!=null){HV03025=conn.rs.getString("HV03025");}
if(conn.rs.getString("HV03026")!=null){HV03026=conn.rs.getString("HV03026");}
if(conn.rs.getString("HV03027")!=null){HV03027=conn.rs.getString("HV03027");}
if(conn.rs.getString("HV03028")!=null){HV03028=conn.rs.getString("HV03028");}
if(conn.rs.getString("HV03029")!=null){HV03029=conn.rs.getString("HV03029");}
if(conn.rs.getString("HV03030")!=null){HV03030=conn.rs.getString("HV03030");}
if(conn.rs.getString("HV03031")!=null){HV03031=conn.rs.getString("HV03031");}
if(conn.rs.getString("HV03032")!=null){HV03032=conn.rs.getString("HV03032");}
if(conn.rs.getString("HV03033")!=null){HV03033=conn.rs.getString("HV03033");}
if(conn.rs.getString("HV03034")!=null){HV03034=conn.rs.getString("HV03034");}
if(conn.rs.getString("HV03035")!=null){HV03035=conn.rs.getString("HV03035");}
if(conn.rs.getString("HV03036")!=null){HV03036=conn.rs.getString("HV03036");}
if(conn.rs.getString("HV03037")!=null){HV03037=conn.rs.getString("HV03037");}
if(conn.rs.getString("HV03038")!=null){HV03038=conn.rs.getString("HV03038");}
if(conn.rs.getString("HV03039")!=null){HV03039=conn.rs.getString("HV03039");}
if(conn.rs.getString("HV03040")!=null){HV03040=conn.rs.getString("HV03040");}
if(conn.rs.getString("HV03041")!=null){HV03041=conn.rs.getString("HV03041");}
if(conn.rs.getString("HV03042")!=null){HV03042=conn.rs.getString("HV03042");}
if(conn.rs.getString("HV03043")!=null){HV03043=conn.rs.getString("HV03043");}
if(conn.rs.getString("HV03044")!=null){HV03044=conn.rs.getString("HV03044");}
if(conn.rs.getString("HV03045")!=null){HV03045=conn.rs.getString("HV03045");}
if(conn.rs.getString("HV03046")!=null){HV03046=conn.rs.getString("HV03046");}
if(conn.rs.getString("HV03047")!=null){HV03047=conn.rs.getString("HV03047");}
if(conn.rs.getString("HV03048")!=null){HV03048=conn.rs.getString("HV03048");}
if(conn.rs.getString("HV03049")!=null){HV03049=conn.rs.getString("HV03049");}
if(conn.rs.getString("HV03050")!=null){HV03050=conn.rs.getString("HV03050");}
if(conn.rs.getString("HV03051")!=null){HV03051=conn.rs.getString("HV03051");}
if(conn.rs.getString("HV03052")!=null){HV03052=conn.rs.getString("HV03052");}
if(conn.rs.getString("HV03053")!=null){HV03053=conn.rs.getString("HV03053");}
if(conn.rs.getString("HV03054")!=null){HV03054=conn.rs.getString("HV03054");}
if(conn.rs.getString("HV03055")!=null){HV03055=conn.rs.getString("HV03055");}
if(conn.rs.getString("HV03056")!=null){HV03056=conn.rs.getString("HV03056");}
if(conn.rs.getString("HV03057")!=null){HV03057=conn.rs.getString("HV03057");}
if(conn.rs.getString("HV03058")!=null){HV03058=conn.rs.getString("HV03058");}
if(conn.rs.getString("HV03059")!=null){HV03059=conn.rs.getString("HV03059");}
if(conn.rs.getString("HV03060")!=null){HV03060=conn.rs.getString("HV03060");}
if(conn.rs.getString("HV03061")!=null){HV03061=conn.rs.getString("HV03061");}
if(conn.rs.getString("HV03062")!=null){HV03062=conn.rs.getString("HV03062");}
if(conn.rs.getString("HV03063")!=null){HV03063=conn.rs.getString("HV03063");}
if(conn.rs.getString("HV03064")!=null){HV03064=conn.rs.getString("HV03064");}
if(conn.rs.getString("HV03065")!=null){HV03065=conn.rs.getString("HV03065");}
if(conn.rs.getString("HV03066")!=null){HV03066=conn.rs.getString("HV03066");}
if(conn.rs.getString("HV03067")!=null){HV03067=conn.rs.getString("HV03067");}
if(conn.rs.getString("HV03068")!=null){HV03068=conn.rs.getString("HV03068");}
if(conn.rs.getString("HV03069")!=null){HV03069=conn.rs.getString("HV03069");}

if(conn.rs.getString("HV03070")!=null){HV03070=conn.rs.getString("HV03070");}
if(conn.rs.getString("HV03071")!=null){HV03071=conn.rs.getString("HV03071");}
if(conn.rs.getString("HV03072")!=null){HV03072=conn.rs.getString("HV03072");}
if(conn.rs.getString("HV03073")!=null){HV03073=conn.rs.getString("HV03073");}
if(conn.rs.getString("HV03074")!=null){HV03074=conn.rs.getString("HV03074");}
if(conn.rs.getString("HV03075")!=null){HV03075=conn.rs.getString("HV03075");}
if(conn.rs.getString("HV03076")!=null){HV03076=conn.rs.getString("HV03076");}
if(conn.rs.getString("HV03077")!=null){HV03077=conn.rs.getString("HV03077");}
if(conn.rs.getString("HV03078")!=null){HV03078=conn.rs.getString("HV03078");}
if(conn.rs.getString("HV03079")!=null){HV03079=conn.rs.getString("HV03079");}
if(conn.rs.getString("HV03080")!=null){HV03080=conn.rs.getString("HV03080");}
if(conn.rs.getString("HV03081")!=null){HV03081=conn.rs.getString("HV03081");}
if(conn.rs.getString("HV03082")!=null){HV03082=conn.rs.getString("HV03082");}
if(conn.rs.getString("HV03083")!=null){HV03083=conn.rs.getString("HV03083");}
if(conn.rs.getString("HV03084")!=null){HV03084=conn.rs.getString("HV03084");}
if(conn.rs.getString("HV03085")!=null){HV03085=conn.rs.getString("HV03085");}
if(conn.rs.getString("HV03086")!=null){HV03086=conn.rs.getString("HV03086");}
if(conn.rs.getString("HV03087")!=null){HV03087=conn.rs.getString("HV03087");}
if(conn.rs.getString("HV03088")!=null){HV03088=conn.rs.getString("HV03088");}
if(conn.rs.getString("HV03089")!=null){HV03089=conn.rs.getString("HV03089");}

if(conn.rs.getString("HV0401")!=null){HV0401=conn.rs.getString("HV0401");}
if(conn.rs.getString("HV0402")!=null){HV0402=conn.rs.getString("HV0402");}
if(conn.rs.getString("HV0403")!=null){HV0403=conn.rs.getString("HV0403");}
if(conn.rs.getString("HV0404")!=null){HV0404=conn.rs.getString("HV0404");}
if(conn.rs.getString("HV0405")!=null){HV0405=conn.rs.getString("HV0405");}
if(conn.rs.getString("HV0406")!=null){HV0406=conn.rs.getString("HV0406");}
if(conn.rs.getString("HV0407")!=null){HV0407=conn.rs.getString("HV0407");}
if(conn.rs.getString("HV0408")!=null){HV0408=conn.rs.getString("HV0408");}
if(conn.rs.getString("HV0409")!=null){HV0409=conn.rs.getString("HV0409");}
if(conn.rs.getString("HV0410")!=null){HV0410=conn.rs.getString("HV0410");}
if(conn.rs.getString("HV0411")!=null){HV0411=conn.rs.getString("HV0411");}
if(conn.rs.getString("HV0412")!=null){HV0412=conn.rs.getString("HV0412");}
if(conn.rs.getString("HV0413")!=null){HV0413=conn.rs.getString("HV0413");}
if(conn.rs.getString("HV0414")!=null){HV0414=conn.rs.getString("HV0414");}
if(conn.rs.getString("HV0415")!=null){HV0415=conn.rs.getString("HV0415");}
if(conn.rs.getString("HV0416")!=null){HV0416=conn.rs.getString("HV0416");}
if(conn.rs.getString("HV0417")!=null){HV0417=conn.rs.getString("HV0417");}

if(conn.rs.getString("HV0501")!=null){HV0501=conn.rs.getString("HV0501");}
if(conn.rs.getString("HV0502")!=null){HV0502=conn.rs.getString("HV0502");}
if(conn.rs.getString("HV0503")!=null){HV0503=conn.rs.getString("HV0503");}
if(conn.rs.getString("HV0504")!=null){HV0504=conn.rs.getString("HV0504");}
if(conn.rs.getString("HV0505")!=null){HV0505=conn.rs.getString("HV0505");}
if(conn.rs.getString("HV0506")!=null){HV0506=conn.rs.getString("HV0506");}


if(conn.rs.getString("HV0601")!=null){HV0601=conn.rs.getString("HV0601");}
if(conn.rs.getString("HV0602")!=null){HV0602=conn.rs.getString("HV0602");}
if(conn.rs.getString("HV0603")!=null){HV0603=conn.rs.getString("HV0603");}
if(conn.rs.getString("HV0604")!=null){HV0604=conn.rs.getString("HV0604");}
if(conn.rs.getString("HV0605")!=null){HV0605=conn.rs.getString("HV0605");}
 
isLocked=conn.rs.getInt("isLocked");
//isLocked=1;
if(isLocked==1){lock="disabled";}

//if(HV03040.equals("")){HV03040=HV03040_1;}
//if(HV03041.equals("")){HV03041=HV03041_1;}
//if(HV03042.equals("")){HV03042=HV03042_1;}
//if(HV03043.equals("")){HV03043=HV03043_1;}
//if(HV03044.equals("")){HV03044=HV03044_1;}
//System.out.println("HVO3040 : "+HV03040+" HV03040_1 : "+HV03040_1);
//  Check if the form is validated============
isValidated=conn.rs.getString("isValidated");

          }
System.out.println("Validity checker : "+isValidated);
      if(isValidated.equals("0")){
  validity="<font color=\"red\"><b>Form Not Validated.<img style=\"margin-left:10px;\" src=\"images/notValidated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;
}
      else if(isValidated.equals("1")){
   validity="<font color=\"green\"><b>Form Validated.<img style=\"margin-left:10px;\" src=\"images/validated.jpg\" width=\"20px\" height=\"20px\"></b></font>"  ;  
}
      else{
        validity="<font color=\"blue\"><b>New entry form.</b></font>"  ;          
              }
      
      session.setAttribute("isValidated", validity);
      
      System.out.println("read from session : "+session.getAttribute("isValidated").toString());
   //####################HIV COUNSELLING AND TESTING#######################################
//              ###################################################################
             String checkValidity="<p hidden=\"true\" id=\"checkValidity\">"+validity+"</p>"; 
       s1_HIV_postive_3_months_ago_linked_to_care=s1_HIV_postive_results=s1_HIV_testing=s1_pre_exposure_prophylaxis="";
             
      s1_HIV_testing="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.1 HIV Testing.</b></legend>"
              + "<table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Tested 1-9</td><td style=\"padding-right:40px;\" >HV01-01</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0101\" id=\"HV0101\" value=\""+HV0101+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0101');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Tested 10-14</td><td style=\"padding-right:40px;\" > (M) HV01-02</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0102\" id=\"HV0102\" value=\""+HV0102+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0102');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-03</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"3\" name=\"HV0103\" id=\"HV0103\" value=\""+HV0103+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0103');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Tested 15-19</td><td style=\"padding-right:40px;\" > (M) HV01-04</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0104\" id=\"HV0104\"  value=\""+HV0104+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0104');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-05</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0105\" id=\"HV0105\"  value=\""+HV0105+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0105');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Tested 20-24</td><td style=\"padding-right:40px;\" > (M) HV01-06</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"6\" name=\"HV0106\" id=\"HV0106\" value=\""+HV0106+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0106');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-07</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"7\" name=\"HV0107\" id=\"HV0107\" value=\""+HV0107+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0107');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Tested 25+</td><td style=\"padding-right:40px;\" > (M) HV01-08</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"8\" name=\"HV0108\" id=\"HV0108\" value=\""+HV0108+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0108');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-09</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"9\" name=\"HV0109\" id=\"HV0109\" value=\""+HV0109+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0109');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0110();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"\" style=\"padding-right:40px;\"> Tested Total (Sum HV01-01 to HV01-09)</td><td></td><td></td><td style=\"padding-right:40px;\" >HV01-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0110\" id=\"HV0110\" value=\""+HV0110+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br>"
              + "<hr>"
              + "<table style=\"margin-left:100px;\"><tr><td colspan=\"3\"></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested Facility</td><td style=\"padding:0 55px 0 70px;\" >HV01-11</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"11\" name=\"HV0111\" id=\"HV0111\" value=\""+HV0111+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0111');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested Community</td><td style=\"padding:0 55px 0 70px;\" >HV01-12</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"12\" name=\"HV0112\" id=\"HV0112\" value=\""+HV0112+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0112');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested New</td><td style=\"padding:0 55px 0 70px;\" >HV01-13</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"13\" name=\"HV0113\" id=\"HV0113\"  value=\""+HV0113+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0113');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested Repeated</td><td style=\"padding:0 55px 0 70px;\" >HV01-14</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"14\" name=\"HV0114\" id=\"HV0114\" value=\""+HV0114+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0114');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested Couples</td><td style=\"padding:0 55px 0 70px;\" >HV01-15</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"15\" name=\"HV0115\" id=\"HV0115\" value=\""+HV0115+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0115');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"\" style=\"padding:0 55px 0 70px;\">Tested KeyPop</td><td style=\"padding:0 55px 0 70px;\" >HV01-16</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"16\" name=\"HV0116\" id=\"HV0116\" value=\""+HV0116+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0116');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\" ></td>"
              + "</tr>"
              + "</table>"
              + "<br>"
              + "</fieldset>";
      
//      HIV Postive results
      s1_HIV_postive_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.2 HIV Postive Results.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive 1-9</td><td style=\"padding-right:40px;\" >HV01-17</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"17\" name=\"HV0117\" id=\"HV0117\" value=\""+HV0117+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0117');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive 10-14</td><td style=\"padding-right:40px;\" > (M) HV01-18</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"18\" name=\"HV0118\" id=\"HV0118\" value=\""+HV0118+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0118');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-19</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"19\" name=\"HV0119\" id=\"HV0119\" value=\""+HV0119+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0119');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive 15-19</td><td style=\"padding-right:40px;\" > (M) HV01-20</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"20\" name=\"HV0120\" id=\"HV0120\"  value=\""+HV0120+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0120');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-21</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"21\" name=\"HV0121\" id=\"HV0121\"  value=\""+HV0121+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0121');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive 20-24</td><td style=\"padding-right:40px;\" > (M) HV01-22</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"22\" name=\"HV0122\" id=\"HV0122\" value=\""+HV0122+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0122');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-23</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"23\" name=\"HV0123\" id=\"HV0123\" value=\""+HV0123+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0123');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive 25+</td><td style=\"padding-right:40px;\" > (M) HV01-24</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"24\" name=\"HV0124\" id=\"HV0124\" value=\""+HV0124+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0124');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding-right:40px;\" > (F) HV01-25</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"25\" name=\"HV0125\" id=\"HV0125\" value=\""+HV0125+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0125');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0126();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td colspan=\"\" style=\"padding-right:40px;\"> Postive Total (Sum HV01-17 to HV01-25)</td><td></td><td></td><td style=\"padding-right:40px;\" >HV01-26</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0126\" id=\"HV0126\" value=\""+HV0126+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\"  maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              +"<br>"
              + "<hr>"
              + "<table style=\"margin-left:100px;\"><tr><td colspan=\"3\"></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Negative Total</td><td style=\"padding:0 70px 0 55px;\" >HV01-27</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"27\" name=\"HV0127\" id=\"HV0127\" value=\""+HV0127+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0127');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Discordant</td><td style=\"padding:0 70px 0 55px;\" >HV01-28</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"28\" name=\"HV0128\" id=\"HV0128\" value=\""+HV0128+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0128');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive KeyPop</td><td style=\"padding:0 70px 0 55px;\" >HV01-29</td><td style=\"padding:0 70px 0 55px;\"><input type=\"text\" tabindex=\"29\" name=\"HV0129\" id=\"HV0129\"  value=\""+HV0129+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0129');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
      
      
       s1_HIV_postive_3_months_ago_linked_to_care="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.3 HIV Postive 3 months ago linked to Care</b></legend>"
               + "<table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked 1-9</td><td style=\"padding-right:40px;\" >HV01-30</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"30\" name=\"HV0130\" id=\"HV0130\" value=\""+HV0130+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0130');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0135();\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked 10-14</td><td style=\"padding-right:40px;\" >HV01-31</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"31\" name=\"HV0131\" id=\"HV0131\" value=\""+HV0131+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0131');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0135();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked 15-19</td><td style=\"padding-right:40px;\" >HV01-32</td><td style=\"padding-right:40px;\"><input type=\"text\"tabindex=\"32\"   name=\"HV0132\" id=\"HV0132\" value=\""+HV0132+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0132');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0135();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked 20-24</td><td style=\"padding-right:40px;\" >HV01-33</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"33\" name=\"HV0133\" id=\"HV0133\" value=\""+HV0133+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0133');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0135();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked 25+</td><td style=\"padding-right:40px;\" >HV01-34</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"34\" name=\"HV0134\" id=\"HV0134\" value=\""+HV0134+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0134');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\" sumHV0135();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Linked Total</td><td style=\"padding-right:40px;\" >HV01-35</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0135\" id=\"HV0135\" value=\""+HV0135+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total tested postive (3 months ago)</td><td style=\"padding-right:40px;\" >HV01-36</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"36\" name=\"HV0136\" id=\"HV0136\" value=\""+HV0136+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0136');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "</table></td>"
              + "<br></fieldset>";
       
        s1_pre_exposure_prophylaxis="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.4 Pre Exposure Prophylaxis.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Prep Eligible New</td><td style=\"padding-right:40px;\" >HV01-37</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"37\" name=\"HV0137\" id=\"HV0137\" value=\""+HV0137+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0137');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prep Start New</td><td style=\"padding-right:40px;\" >HV01-38</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"38\" name=\"HV0138\" id=\"HV0138\" value=\""+HV0138+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0138');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prep Current</td><td style=\"padding-right:40px;\" >HV01-39</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"39\" name=\"HV0139\"  id=\"HV0139\" value=\""+HV0139+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0139');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prep Stopped</td><td style=\"padding-right:40px;\" >HV01-40</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"40\" name=\"HV0140\" id=\"HV0140\" value=\""+HV0140+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0140');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
      
             HIV_TPS+=s1_HIV_testing+""+s1_HIV_postive_results+""+s1_HIV_postive_3_months_ago_linked_to_care+""+s1_pre_exposure_prophylaxis; 
//              System.out.println("Loading new form for data entry.......................");

//    PMTCT################################################################
//               ##########################################################
 

        s2_antenatal_and_delivery_contacts=s2_maternal_HIV_testing=s2_HIV_postive_results=s2_maternal_HAART=
        s2_retention_to_PMTCT_ARVs=s2_syphillis_screening=s2_family_planning_at_postnatal=s2_male_partner_HIV_status=
        s2_adolscents_10_19_testing_and_results=s2_infant_HIV_exposure_status_At_penta_1=s2_infant_prophylaxis=
        s2_infant_testing=s2_programme_outcommes=s2_retention_of_baby_mother_pairs=s2_infant_feeding_for_HIVP_mothers="";        
           
  //    ###############################PREVENTION OF MOTHER TO CHILD TRANSMISSION OF HIV (PMTCT) #################################
//               ##########################################################         
    
s2_antenatal_and_delivery_contacts="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"padding:0 70px 0 70px;\">2.1 Antenatal and Delivery Contacts.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 70px 0 70px;\">1<sup>st</sup> ANC Visits</td><td style=\"padding:0 70px 0 70px;\" >HV02-01</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0201\" id=\"HV0201\" value=\""+HV0201+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0201');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 70px 0 70px;\">Delivery from HIV+ Mothers</td><td style=\"padding:0 70px 0 70px;\" >HV02-02</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0202\" id=\"HV0202\" value=\""+HV0202+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0202');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_maternal_HIV_testing="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.2 Maternal HIV Testing.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Known Postive at 1<sup>st</sup> ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-03</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"3\" name=\"HV0203\" id=\"HV0203\" value=\""+HV0203+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0203');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0207();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial test at ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-04</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0204\" id=\"HV0204\" value=\""+HV0204+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0204');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0207();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial test at L&D</td><td style=\"padding:0 55px 0 70px;\" >HV02-05</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0205\" id=\"HV0205\" value=\""+HV0205+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0205');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0207();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial test at PNC<=6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-06</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"6\" name=\"HV0206\" id=\"HV0206\" value=\""+HV0206+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0206');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0207();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Known HIV status Total (HV02-03 to 06)</td><td style=\"padding:0 55px 0 70px;\" >HV02-07</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" name=\"HV0207\" id=\"HV0207\" value=\""+HV0207+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Retesting PNC<=6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-08</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"8\" name=\"HV0208\" id=\"HV0208\" value=\""+HV0208+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0208');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Tested PNC >6wks to 6 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-09</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"9\" name=\"HV0209\" id=\"HV0209\" value=\""+HV0209+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0209');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_HIV_postive_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.3 HIV Postive Results.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Known Postive at 1<sup>st</sup> ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-10</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"10\" name=\"HV0210\" id=\"HV0210\" value=\""+HV0210+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0210');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0214();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive Results ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-11</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"11\" name=\"HV0211\" id=\"HV0211\" value=\""+HV0211+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0211');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0214();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive Results L&D</td><td style=\"padding:0 55px 0 70px;\" >HV02-12</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"12\" name=\"HV0212\" id=\"HV0212\" value=\""+HV0212+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0212');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0214();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive Results PNC<=6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-13</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"13\" name=\"HV0213\" id=\"HV0213\" value=\""+HV0213+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0213');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0214();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Total Postive (Sum HV02-10 to 13)</td><td style=\"padding:0 55px 0 70px;\" >HV02-14</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" name=\"HV0214\" id=\"HV0214\" value=\""+HV0214+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive PNC >6wks to 6 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-15</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"15\" name=\"HV0215\" id=\"HV0215\" value=\""+HV0215+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0215');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_maternal_HAART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.4 Maternal HAART.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">On HAART at 1<sup>st</sup> ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-16</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"16\" name=\"HV0216\" id=\"HV0216\" value=\""+HV0216+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0216');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0220();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Start HAART ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-17</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"17\" name=\"HV0217\" id=\"HV0217\" value=\""+HV0217+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0217');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0220();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Start HAART L&D</td><td style=\"padding:0 55px 0 70px;\" >HV02-18</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"18\" name=\"HV0218\" id=\"HV0218\" value=\""+HV0218+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0218');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0220();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Start HAART PNC<=6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-19</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"19\" name=\"HV0219\" id=\"HV0219\" value=\""+HV0219+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0219');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0220();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Maternal HAART Total (HV02-16 to 19)</td><td style=\"padding:0 55px 0 70px;\" >HV02-20</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" name=\"HV0220\" id=\"HV0220\" value=\""+HV0220+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Start HAART PNC >6wks to 6 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-21</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"21\" name=\"HV0221\" id=\"HV0221\" value=\""+HV0221+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0221');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_retention_to_PMTCT_ARVs="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.5 Retention to PMTCT ARVs (Cohort).</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">On Maternal HAART 12 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-22</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"22\" name=\"HV0222\" id=\"HV0222\" value=\""+HV0222+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0222');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Net Cohorts 12 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-23</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"23\" name=\"HV0223\" id=\"HV0223\" value=\""+HV0223+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0223');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_syphillis_screening="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.6 Syphillis Screening.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Syphillis Screened ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-24</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"24\" name=\"HV0224\" id=\"HV0224\" value=\""+HV0224+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0224');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Syphillis Screened Postive</td><td style=\"padding:0 55px 0 70px;\" >HV02-25</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"25\" name=\"HV0225\" id=\"HV0225\" value=\""+HV0225+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0225');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Syphillis Treated</td><td style=\"padding:0 55px 0 70px;\" >HV02-26</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"26\" name=\"HV0226\" id=\"HV0226\" value=\""+HV0226+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0226');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_family_planning_at_postnatal="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.7 Family Planning at Postnatal.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">HIV+ On Modern FP at 6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-27</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"27\" name=\"HV0227\" id=\"HV0227\" value=\""+HV0227+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0227');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">HIV+ PNC Visits at 6wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-28</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"28\" name=\"HV0228\" id=\"HV0228\" value=\""+HV0228+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0228');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_male_partner_HIV_status="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.8 Male Partner HIV Status.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Known Postive Status 1<sup>st</sup> Contact</td><td style=\"padding:0 55px 0 70px;\" >HV02-29</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"29\" name=\"HV0229\" id=\"HV0229\" value=\""+HV0229+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0229');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0232();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial Test at ANC Male</td><td style=\"padding:0 55px 0 70px;\" >HV02-30</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"30\" name=\"HV0230\" id=\"HV0230\" value=\""+HV0230+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0230');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0232();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial Test at PNC Male</td><td style=\"padding:0 55px 0 70px;\" >HV02-31</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"31\" name=\"HV0231\" id=\"HV0231\" value=\""+HV0231+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0231');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0232();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Total Know Status Male(HV02-29 to 31)</td><td style=\"padding:0 55px 0 70px;\" >HV02-32</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" name=\"HV0232\" id=\"HV0232\" value=\""+HV0232+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_adolscents_10_19_testing_and_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.9 Adolescents (10-19 yrs) Testing and results.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">1<sup>st</sup> ANC KP adolescents (10-19)</td><td style=\"padding:0 55px 0 70px;\" >HV02-33</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"33\" name=\"HV0233\" id=\"HV0233\" value=\""+HV0233+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0233');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Postive results adolescents</td><td style=\"padding:0 55px 0 70px;\" >HV02-34</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"34\" name=\"HV0234\" id=\"HV0234\" value=\""+HV0234+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0234');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Started HAART adolescents Total</td><td style=\"padding:0 55px 0 70px;\" >HV02-35</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"35\" name=\"HV0235\" id=\"HV0235\" value=\""+HV0235+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0235');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_infant_HIV_exposure_status_At_penta_1="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.10 Infant HIV Exposure Status at Penta 1.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Known Postive at Penta 1</td><td style=\"padding:0 55px 0 70px;\" >HV02-36</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"36\" name=\"HV0236\" id=\"HV0236\" value=\""+HV0236+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0236');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Total given Penta 1</td><td style=\"padding:0 55px 0 70px;\" >HV02-37</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"37\" name=\"HV0237\" id=\"HV0237\" value=\""+HV0237+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0237');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_infant_prophylaxis="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.11 Infant Prophylaxis.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Infant ARV Prophyl ANC</td><td style=\"padding:0 55px 0 70px;\" >HV02-38</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"38\" name=\"HV0238\" id=\"HV0238\" value=\""+HV0238+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0238');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0241();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Infant ARV Prophyl L&D</td><td style=\"padding:0 55px 0 70px;\" >HV02-39</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"39\" name=\"HV0239\" id=\"HV0239\" value=\""+HV0239+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0239');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0241();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Infant ARV Prophyl <8 wks PNC</td><td style=\"padding:0 55px 0 70px;\" >HV02-40</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"40\" name=\"HV0240\" id=\"HV0240\" value=\""+HV0240+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0240');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0241();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Total ARV Prophylaxis (HV02-38 to 40) </td><td style=\"padding:0 55px 0 70px;\" >HV02-41</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" name=\"HV0241\" id=\"HV0241\" value=\""+HV0241+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">HEI CTX/DDS start <2 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-42</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"42\" name=\"HV0242\" id=\"HV0242\" value=\""+HV0242+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0242');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_infant_testing="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.12 Infant Testing (PCR sample collected).</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial PCR < 8 wks</td><td style=\"padding:0 55px 0 70px;\" >HV02-43</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"43\" name=\"HV0243\" id=\"HV0243\" value=\""+HV0243+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0243');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial PCR >= 8wks - 12 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-44</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"44\" name=\"HV0244\" id=\"HV0244\" value=\""+HV0244+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0244');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Initial PCR  Test </td><td style=\"padding:0 55px 0 70px;\" >HV02-45</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"45\" name=\"HV0245\" id=\"HV0245\" value=\""+HV0245+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0245');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_programme_outcommes="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.13 Programme Outcomes.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Infected 24 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-46</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"46\" name=\"HV0246\" id=\"HV0246\" value=\""+HV0246+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0246');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Uninfected 24 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-47</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"47\" name=\"HV0247\" id=\"HV0247\" value=\""+HV0247+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0247');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Unknown outcome 24 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-48</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"48\" name=\"HV0248\" id=\"HV0248\" value=\""+HV0248+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0248');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Net cohort HEI 24 months </td><td style=\"padding:0 55px 0 70px;\" >HV02-49</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"49\" name=\"HV0249\" id=\"HV0249\" value=\""+HV0249+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0249');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_retention_of_baby_mother_pairs="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.14 Retention of Baby-Mother Pairs.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Infected 24 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-50</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"50\" name=\"HV0250\" id=\"HV0250\" value=\""+HV0250+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0250');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Uninfected 24 months</td><td style=\"padding:0 55px 0 70px;\" >HV02-51</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"51\" name=\"HV0251\" id=\"HV0251\" value=\""+HV0251+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0251');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";

s2_infant_feeding_for_HIVP_mothers="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.15 Infant feeding for HIV+ Mothers.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">EBF (at 6 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-52</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"52\" name=\"HV0252\" id=\"HV0252\" value=\""+HV0252+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0252');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">ERF (at 6 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-53</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"53\" name=\"HV0253\" id=\"HV0253\" value=\""+HV0253+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0253');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">MF (at 6 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-54</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"54\" name=\"HV0254\" id=\"HV0254\" value=\""+HV0254+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0254');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">BF (at 12 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-55</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"55\" name=\"HV0255\" id=\"HV0255\" value=\""+HV0255+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0255');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Not BF (at 12 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-56</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"56\" name=\"HV0256\" id=\"HV0256\" value=\""+HV0256+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0256');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">BF (at 18 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-57</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"57\" name=\"HV0257\" id=\"HV0257\" value=\""+HV0257+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0257');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 55px 0 70px;\">Not BF (at 18 months)</td><td style=\"padding:0 55px 0 70px;\" >HV02-58</td><td style=\"padding:0 70px 0 70px;\"><input type=\"text\" tabindex=\"58\" name=\"HV0258\" id=\"HV0258\" value=\""+HV0258+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0258');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";


        PMTCT+=s2_antenatal_and_delivery_contacts+""+s2_maternal_HIV_testing+""+s2_HIV_postive_results+""+s2_maternal_HAART+""+
        s2_retention_to_PMTCT_ARVs+""+s2_syphillis_screening+""+s2_family_planning_at_postnatal+""+s2_male_partner_HIV_status+""+
        s2_adolscents_10_19_testing_and_results+""+s2_infant_HIV_exposure_status_At_penta_1+""+s2_infant_prophylaxis+""+
        s2_infant_testing+""+s2_programme_outcommes+""+s2_retention_of_baby_mother_pairs+""+s2_infant_feeding_for_HIVP_mothers;    

         
          
        s3_enrollment_in_care=s3_current_on_pre_ART=s3_starting_ART=s3_currently_on_ART=s3_retention_on_ART=s3_on_CTX_Dapsone=
        s3_TB_screening_and_presumed_TB=s3_starting_IPT=s3_nutrition_and_HIV=s3_HIV_in_TB_clinic=s3_community_dispensing_of_ARVs=
        s3_family_planning_CaCx_screen_in_HIV_CCC="";
        
       s3_enrollment_in_care="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.1 Enrollment in Care.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-001</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"1\" name=\"HV03001\" id=\"HV03001\" value=\""+HV03001+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03001');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-002</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"2\" name=\"HV03002\" id=\"HV03002\" value=\""+HV03002+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03002');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-003</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"3\" name=\"HV03003\" id=\"HV03003\" value=\""+HV03003+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03003');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-004</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"4\" name=\"HV03004\" id=\"HV03004\" value=\""+HV03004+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03004');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-005</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"5\" name=\"HV03005\" id=\"HV03005\" value=\""+HV03005+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03005');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-006</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"6\" name=\"HV03006\" id=\"HV03006\" value=\""+HV03006+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03006');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-007</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"7\" name=\"HV03007\" id=\"HV03007\" value=\""+HV03007+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03007');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-008</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"8\" name=\"HV03008\" id=\"HV03008\" value=\""+HV03008+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03008');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-009</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"9\" name=\"HV03009\" id=\"HV03009\" value=\""+HV03009+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03009');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-010</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"10\" name=\"HV03010\" id=\"HV03010\" value=\""+HV03010+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03010');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03011();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled Total (HV03-001 to HV03010)</td>"
                + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
               + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-011</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03011\" id=\"HV03011\" value=\""+HV03011+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Enrolled in Care KeyPop</td>"
            + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
               + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-012</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"12\" name=\"HV03012\" id=\"HV03012\" value=\""+HV03012+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03012');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       s3_current_on_pre_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.2 Current on Pre ART.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">In Pre ART 0-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-013</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"13\" name=\"HV03013\" id=\"HV03013\" value=\""+HV03013+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03013');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03015();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">In Pre ART 15+</td><td style=\"padding:0 30px 0 35px;\" >HV03-014</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"14\" name=\"HV03014\" id=\"HV03014\" value=\""+HV03014+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03014');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03015();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">In Pre ART Total (HV03-013 to HV03-014)</td><td style=\"padding:0 30px 0 35px;\" >HV03-015</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03015\" id=\"HV03015\" value=\""+HV03015+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       s3_starting_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.3 Starting ART.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-016</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"16\" name=\"HV03016\" id=\"HV03016\" value=\""+HV03016+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03016');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-017</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"17\" name=\"HV03017\" id=\"HV03017\" value=\""+HV03017+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03017');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-018</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"18\" name=\"HV03018\" id=\"HV03018\" value=\""+HV03018+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03018');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-019</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"19\" name=\"HV03019\" id=\"HV03019\" value=\""+HV03019+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03019');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-020</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"20\" name=\"HV03020\" id=\"HV03020\" value=\""+HV03020+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03020');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-021</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"21\" name=\"HV03021\" id=\"HV03021\" value=\""+HV03021+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03021');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-022</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"22\" name=\"HV03022\" id=\"HV03022\" value=\""+HV03022+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03022');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-023</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"23\" name=\"HV03023\" id=\"HV03023\" value=\""+HV03023+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03023');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-024</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"24\" name=\"HV03024\" id=\"HV03024\" value=\""+HV03024+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03024');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-025</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"25\" name=\"HV03025\" id=\"HV03025\" value=\""+HV03025+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03025');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03026();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART Total (HV03-016 to HV03025)</td>"
              + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-026</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03026\" id=\"HV03026\" value=\""+HV03026+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start ART KeyPop</td>"
              + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
               + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-027</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"27\" name=\"HV03027\" id=\"HV03027\" value=\""+HV03027+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03027');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       s3_currently_on_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.4 Currently on ART [All].</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-028</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"28\" name=\"HV03028\" id=\"HV03028\" value=\""+HV03028+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03028');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-029</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"29\" name=\"HV03029\" id=\"HV03029\" value=\""+HV03029+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03029');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-030</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"30\" name=\"HV03030\" id=\"HV03030\" value=\""+HV03030+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03030');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-031</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"31\" name=\"HV03031\" id=\"HV03031\" value=\""+HV03031+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03031');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-032</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"32\" name=\"HV03032\" id=\"HV03032\" value=\""+HV03032+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03032');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-033</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"33\" name=\"HV03033\" id=\"HV03033\" value=\""+HV03033+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03033');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-034</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"34\" name=\"HV03034\" id=\"HV03034\" value=\""+HV03034+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03034');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-035</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"35\" name=\"HV03035\" id=\"HV03035\" value=\""+HV03035+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03035');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-036</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"36\" name=\"HV03036\" id=\"HV03036\" value=\""+HV03036+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03036');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-037</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"37\" name=\"HV03037\" id=\"HV03037\" value=\""+HV03037+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03037');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03038();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART Total (HV03-028 to HV03037)</td>"
              + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-038</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03038\" id=\"HV03038\" value=\""+HV03038+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART KeyPop</td>"
              + "<td style=\"padding:0 30px 0 35px;\"></td><td style=\"padding:0 30px 0 35px;\"></td>"
               + "<td style=\"padding:0 30px 0 35px;\" > &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;HV03-039</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"39\" name=\"HV03039\" id=\"HV03039\" value=\""+HV03039+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03039');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
              
       s3_retention_on_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.5 Retention ART.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On ART 12 Months</td><td style=\"padding:0 30px 0 35px;\" >HV03-040</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"40\" name=\"HV03040\" id=\"HV03040\" value=\""+HV03040+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03040');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Net Cohort 12 months</td><td style=\"padding:0 30px 0 35px;\" >HV03-041</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"41\" name=\"HV03041\" id=\"HV03041\" value=\""+HV03041+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03041');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Viral load <1000 12 months</td><td style=\"padding:0 30px 0 35px;\" >HV03-042</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"42\" name=\"HV03042\" id=\"HV03042\" value=\""+HV03042+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03042');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Viral load result 12 months</td><td style=\"padding:0 30px 0 35px;\" >HV03-043</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"43\" name=\"HV03043\" id=\"HV03043\" value=\""+HV03043+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03043');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       
       s3_on_CTX_Dapsone="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.6 On CTX/Dapsone.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-044</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"44\" name=\"HV03044\" id=\"HV03044\" value=\""+HV03044+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03044');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-045</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"45\" name=\"HV03045\" id=\"HV03045\" value=\""+HV03045+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03045');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-046</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"46\" name=\"HV03046\" id=\"HV03046\" value=\""+HV03046+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03046');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-047</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"47\" name=\"HV03047\" id=\"HV03047\" value=\""+HV03047+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03047');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-048</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"48\" name=\"HV03048\" id=\"HV03048\" value=\""+HV03048+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03048');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-049</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"49\" name=\"HV03049\" id=\"HV03049\" value=\""+HV03049+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03049');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03050();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On CTX/DDS Total (sum HV03-044 to HV03-049)</td><td style=\"padding:0 30px 0 35px;\" >HV03-050</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03050\" id=\"HV03050\" value=\""+HV03050+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       
       s3_TB_screening_and_presumed_TB="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.7 TB Screening & Presumed TB.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-051</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"51\" name=\"HV03051\" id=\"HV03051\" value=\""+HV03051+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03051');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-052</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"52\" name=\"HV03052\" id=\"HV03052\" value=\""+HV03052+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03052');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-053</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"53\" name=\"HV03053\" id=\"HV03053\" value=\""+HV03053+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03053');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-054</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"54\" name=\"HV03054\" id=\"HV03054\" value=\""+HV03054+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03054');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-055</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"55\" name=\"HV03055\" id=\"HV03055\" value=\""+HV03055+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03055');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-056</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"56\" name=\"HV03056\" id=\"HV03056\" value=\""+HV03056+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03056');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03057();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screened for TB Total (sum HV03-051 to HV03-056)</td><td style=\"padding:0 30px 0 35px;\" >HV03-057</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03057\" id=\"HV03057\" value=\""+HV03057+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Presumed TB Total </td><td style=\"padding:0 30px 0 35px;\" >HV03-058</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"58\" name=\"HV03058\" id=\"HV03058\" value=\""+HV03058+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03058');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       
       s3_starting_IPT="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.8 Starting IPT.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT <1</td><td style=\"padding:0 30px 0 35px;\" >HV03-059</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"59\" name=\"HV03059\" id=\"HV03059\" value=\""+HV03059+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03059');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT 1-9</td><td style=\"padding:0 30px 0 35px;\" >HV03-060</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"60\" name=\"HV03060\" id=\"HV03060\" value=\""+HV03060+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03060');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV03-061</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"61\" name=\"HV03061\" id=\"HV03061\" value=\""+HV03061+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03061');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV03-062</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"62\" name=\"HV03062\" id=\"HV03062\" value=\""+HV03062+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03062');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT 20-24</td><td style=\"padding:0 30px 0 35px;\" >HV03-063</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"63\" name=\"HV03063\" id=\"HV03063\" value=\""+HV03063+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03063');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT 25+</td><td style=\"padding:0 30px 0 35px;\" >HV03-064</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"64\" name=\"HV03064\" id=\"HV03064\" value=\""+HV03064+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03064');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03065();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Start IPT Total (sum HV03-059 to HV03-064)</td><td style=\"padding:0 30px 0 35px;\" >HV03-065</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\"  name=\"HV03065\" id=\"HV03065\" value=\""+HV03065+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Completed IPT 12 months </td><td style=\"padding:0 30px 0 35px;\" >HV03-066</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"66\" name=\"HV03066\" id=\"HV03066\" value=\""+HV03066+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03066');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       s3_nutrition_and_HIV="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.9 Nutrition and HIV.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Nutrition Assess <15</td><td style=\"padding:0 30px 0 35px;\" >HV03-067</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"67\" name=\"HV03067\" id=\"HV03067\" value=\""+HV03067+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03067');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03069();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Nutrition Assess 15+</td><td style=\"padding:0 30px 0 35px;\" >HV03-068</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"68\" name=\"HV03068\" id=\"HV03068\" value=\""+HV03068+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03068');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03069();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Nutrition Assess Total (HV03-067+HV03-068)</td><td style=\"padding:0 30px 0 35px;\" >HV03-069</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03069\" id=\"HV03069\" value=\""+HV03069+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Malnourished <15</td><td style=\"padding:0 30px 0 35px;\" >HV03-070</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"70\" name=\"HV03070\" id=\"HV03070\" value=\""+HV03070+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03070');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03072();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Malnourished 15+</td><td style=\"padding:0 30px 0 35px;\" >HV03-071</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"71\" name=\"HV03071\" id=\"HV03071\" value=\""+HV03071+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03071');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03072();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Malnourished Total (HV03-070+HV03-071)</td><td style=\"padding:0 30px 0 35px;\" >HV03-072</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03072\" id=\"HV03072\" value=\""+HV03072+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">FBP Provided <15</td><td style=\"padding:0 30px 0 35px;\" >HV03-73</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"73\" name=\"HV03073\" id=\"HV03073\" value=\""+HV03073+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03073');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03075();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">FBP Provided 15+</td><td style=\"padding:0 30px 0 35px;\" >HV03-74</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"74\" name=\"HV03074\" id=\"HV03074\" value=\""+HV03074+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03074');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03075();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">FBP Provided Total (HV03-073+HV03-074)</td><td style=\"padding:0 30px 0 35px;\" >HV03-75</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03075\" id=\"HV03075\" value=\""+HV03075+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
       s3_HIV_in_TB_clinic="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.10 HIV TB Clinic.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Cases New <15</td><td style=\"padding:0 30px 0 35px;\" >HV03-076</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"76\" name=\"HV03076\" id=\"HV03076\" value=\""+HV03076+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03076');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Cases KP</td><td style=\"padding:0 30px 0 35px;\" >HV03-077</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"77\" name=\"HV03077\" id=\"HV03077\" value=\""+HV03077+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03077');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03079();sumHV03081();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Cases Tested HIV</td><td style=\"padding:0 30px 0 35px;\" >HV03-078</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"78\" name=\"HV03078\" id=\"HV03078\" value=\""+HV03078+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03078');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03079();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Known Status (HV03-077 +078)</td><td style=\"padding:0 30px 0 35px;\" >HV03-079</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03079\" id=\"HV03079\" value=\""+HV03079+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB New HIV Postive </td><td style=\"padding:0 30px 0 35px;\" >HV03-080</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"80\" name=\"HV03080\" id=\"HV03080\" value=\""+HV03080+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03080');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03081();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Total HIV Postive (HV03-077 + 080)</td><td style=\"padding:0 30px 0 35px;\" >HV03-081</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03081\" id=\"HV03081\" value=\""+HV03081+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB already on HAART </td><td style=\"padding:0 30px 0 35px;\" >HV03-82</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"82\" name=\"HV03082\" id=\"HV03082\" value=\""+HV03082+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03082');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03084();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Start HAART</td><td style=\"padding:0 30px 0 35px;\" >HV03-83</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"83\" name=\"HV03083\" id=\"HV03083\" value=\""+HV03083+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03083');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV03084();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">TB Total on HAART (HV03-082+083)</td><td style=\"padding:0 30px 0 35px;\" >HV03-084</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV03084\" id=\"HV03084\" value=\""+HV03084+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
        s3_community_dispensing_of_ARVs="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.11 Community Dispensing of ARVs.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Community ART current </td><td style=\"padding:0 30px 0 35px;\" >(M)HV03-085</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"85\" name=\"HV03085\" id=\"HV03085\" value=\""+HV03085+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03085');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "<td style=\"padding:0 30px 0 35px;\" >(F) HV03-086</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"86\" name=\"HV03086\" id=\"HV03086\" value=\""+HV03086+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03086');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
       
        s3_family_planning_CaCx_screen_in_HIV_CCC="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.12 Family Planning & CaCx screen in HIV CCC.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Screen CaCx New F18+</td><td style=\"padding:0 30px 0 35px;\" >HV03-087</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"87\" name=\"HV03087\" id=\"HV03087\" value=\""+HV03087+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03087');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Clinical visits F18+</td><td style=\"padding:0 30px 0 35px;\" >HV03-088</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"88\" name=\"HV03088\" id=\"HV03088\" value=\""+HV03088+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03088');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">On modern FP F15+</td><td style=\"padding:0 30px 0 35px;\" >HV03-089</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"89\" name=\"HV03089\" id=\"HV03089\" value=\""+HV03089+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV03089');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"postiveResults();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
        
        HIV_TB+=s3_enrollment_in_care+""+s3_current_on_pre_ART+""+s3_starting_ART+""+s3_currently_on_ART+""+s3_retention_on_ART+""+s3_on_CTX_Dapsone+""+
        s3_TB_screening_and_presumed_TB+""+s3_starting_IPT+""+s3_nutrition_and_HIV+""+s3_HIV_in_TB_clinic+""+s3_community_dispensing_of_ARVs+""+
        s3_family_planning_CaCx_screen_in_HIV_CCC;
        
// ***************************************************************************       
//        START OF VMMC**************************************************
//************************************************************************
        s4_number_circumcised=s4_type_of_circumcision=s4_circumcision_adverse_events="";
       
     s4_number_circumcised = "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.1 Number circumcised.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised <1</td><td style=\"padding:0 30px 0 35px;\" >HV04-01</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0401\" id=\"HV0401\" value=\""+HV0401+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0401');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised 1-9 yrs</td><td style=\"padding:0 30px 0 35px;\" >HV04-02</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0402\" id=\"HV0402\" value=\""+HV0402+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0402');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised 10-14</td><td style=\"padding:0 30px 0 35px;\" >HV04-03</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"3\" name=\"HV0403\" id=\"HV0403\" value=\""+HV0403+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0403');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised 15-19</td><td style=\"padding:0 30px 0 35px;\" >HV04-04</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0404\" id=\"HV0404\" value=\""+HV0404+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0404');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised 20-25</td><td style=\"padding:0 30px 0 35px;\" >HV04-05</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0405\" id=\"HV0405\" value=\""+HV0405+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0405');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised 25+</td><td style=\"padding:0 30px 0 35px;\" >HV04-06</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"6\" name=\"HV0406\" id=\"HV0406\" value=\""+HV0406+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0406');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0407();\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised Total (Sum HV04-01 to 06)</td><td style=\"padding:0 30px 0 35px;\" >HV04-07</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV0407\" id=\"HV0407\" value=\""+HV0407+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised HIV+</td><td style=\"padding:0 30px 0 35px;\" >HV04-08</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"8\" name=\"HV0408\" id=\"HV0408\" value=\""+HV0408+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0408');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised HIV-</td><td style=\"padding:0 30px 0 35px;\" >HV04-09</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"9\" name=\"HV0409\" id=\"HV0409\" value=\""+HV0409+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0409');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Circumcised HIV NK</td><td style=\"padding:0 30px 0 35px;\" >HV04-10</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"10\" name=\"HV0410\" id=\"HV0410\" value=\""+HV0410+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0410');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
     
     
     s4_type_of_circumcision = "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.2 Type of circumcision.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Surgical</td><td style=\"padding:0 30px 0 35px;\" >HV04-11</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"11\" name=\"HV0411\" id=\"HV0411\" value=\""+HV0411+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0411');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Devices</td><td style=\"padding:0 30px 0 35px;\" >HV04-12</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"12\" name=\"HV0412\" id=\"HV0412\" value=\""+HV0412+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0412');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
     
     
     s4_circumcision_adverse_events = "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.3 Circumcision Adverse Events.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">AE During Moderate</td><td style=\"padding:0 30px 0 35px;\" >HV04-13</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"13\" name=\"HV0413\" id=\"HV0413\" value=\""+HV0413+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0413');\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">AE During Severe</td><td style=\"padding:0 30px 0 35px;\" >HV04-14</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"14\" name=\"HV0414\" id=\"HV0414\" value=\""+HV0414+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0414');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">AE Post Moderate</td><td style=\"padding:0 30px 0 35px;\" >HV04-15</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"15\" name=\"HV0415\" id=\"HV0415\" value=\""+HV0415+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0415');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">AE Post Severe</td><td style=\"padding:0 30px 0 35px;\" >HV04-16</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"16\" name=\"HV0416\" id=\"HV0416\" value=\""+HV0416+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0416');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Follow up visits <=14 days</td><td style=\"padding:0 30px 0 35px;\" >HV04-17</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"17\" name=\"HV0417\" id=\"HV0417\" value=\""+HV0417+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0417');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
        
        
        
        
        VMMC+=s4_number_circumcised+""+s4_type_of_circumcision+""+s4_circumcision_adverse_events;
        
        
         
        s5_post_exposure_prophylaxis="";
        
        s5_post_exposure_prophylaxis = "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">5 Post Exposure Prophylaxis.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Exposed Occupational</td><td style=\"padding:0 30px 0 35px;\" >HV05-01</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0501\" id=\"HV0501\" value=\""+HV0501+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0501');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0503();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Exposed Other</td><td style=\"padding:0 30px 0 35px;\" >HV05-02</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0502\" id=\"HV0502\" value=\""+HV0502+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0502');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0503();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">Exposed Total (HV05-01+02)</td><td style=\"padding:0 30px 0 35px;\" >HV05-03</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\"  name=\"HV0503\" id=\"HV0503\" value=\""+HV0503+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">PEP Occupational</td><td style=\"padding:0 30px 0 35px;\" >HV05-04</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0504\" id=\"HV0504\" value=\""+HV0504+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0504');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0506();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">PEP Other</td><td style=\"padding:0 30px 0 35px;\" >HV05-05</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0505\" id=\"HV0505\" value=\""+HV0505+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0505');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" oninput=\"sumHV0506();\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">PEP Total (HV05-04 & 05)</td><td style=\"padding:0 30px 0 35px;\" >HV05-06</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" name=\"HV0506\" id=\"HV0506\" value=\""+HV0506+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;background-color:#DDDDDD;\"  readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
        
        PEP+=s5_post_exposure_prophylaxis;
        
        s6_methadone_assisted_therapy="";
        
        s6_methadone_assisted_therapy= "<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">6 Methadone Assisted Therapy.</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">KeyPop on MAT</td><td style=\"padding:0 30px 0 35px;\" >HV06-01</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0601\" id=\"HV0601\" value=\""+HV0601+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0601');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">MAT clients known HIV+</td><td style=\"padding:0 30px 0 35px;\" >HV06-02</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0602\" id=\"HV0602\" value=\""+HV0602+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0602');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">MAT clients Tested HIV</td><td style=\"padding:0 30px 0 35px;\" >HV06-03</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"3\" name=\"HV0603\" id=\"HV0603\" value=\""+HV0603+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0603');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">MAT client New HIV+</td><td style=\"padding:0 30px 0 35px;\" >HV06-04</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0604\" id=\"HV0604\" value=\""+HV0604+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0604');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding:0 30px 0 35px;\">HIV+ MAT Clients on ART</td><td style=\"padding:0 30px 0 35px;\" >HV06-05</td><td style=\"padding:0 35px 0 35px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0605\" id=\"HV0605\" value=\""+HV0605+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0605');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
        
        MAT+=s6_methadone_assisted_therapy;
       
               
          
          
//          }
          
//       ###################################################################   
//       ###################################################################   
//       ###################################################################   
//       ###################################################################   
//        ###################################################################  
//        ###################################################################  
//        ###################################################################  
//        ###################################################################  
//        ###################################################################  
//          
//         ################################################################### 
//          
//          ###################################################################
      
           String validationText="",buttonColor="", titleText="";
             switch (isLocked) {
                 case 0:
                     validationText="Run Validation";
                     buttonColor="blue";
                     titleText="";
                     break;
                 case 1:
                     validationText="<b>Data Locked !</b>";
                     buttonColor="red";
                     titleText="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style=\"color:red;\">Sorry: Data editing has been locked for this health facility, year and month.</b>";
                     break;
                 default:
                     break;
             }
        
        HIV_TPS+="</div></div></div>";
        PMTCT+="</div></div></div>";
        HIV_TB+="</div></div></div>";
        VMMC+="</div></div></div>";
        PEP+="</div></div></div>";
      
        data+=""+enterdby+""+titleText+"<br><br><br>"+tabs;
             System.out.println("____"+HIV_TPS);
        if(session.getAttribute("forms_holder").toString().contains("HTC,")){data+=HIV_TPS;}
        if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){data+=PMTCT;}
        if(session.getAttribute("forms_holder").toString().contains(",ART,")){data+=HIV_TB;}
        if(session.getAttribute("forms_holder").toString().contains(",PEP,")){data+=PEP;}
        if(1==1){data+=VMMC;}//update this field with the right sessions
        if(1==1){data+=MAT;}//update this field with the right sessions
//        if(session.getAttribute("forms_holder").toString().contains(",Blood_Safety,")){data+=Blood;}
     
        
//        String getForms="SELECT COUNT(id),isValidated FROM moh731_new WHERE year='"+year+"' && month='"+month+"' GROUP BY isValidated";
//        conn.rs1=conn.st1.executeQuery(getForms);
//        while(conn.rs1.next()){
//            System.out.println("cont : "+conn.rs1.getString(1)+"   numberv is "+conn.rs.getString(2));
//        }
        
        
//        data=HIV_TPS+""+PMTCT+""+CT+""+VMMC+""+PEP+""+Blood+""+checkValidity;
        data+=checkValidity;
        
   
//        data+="<input type=\"submit\" class=\"btn blue\" style=\"margin-left:40%;\" "+lock+" value=\""+validationText+"\">";
        data+="<p hidden=\"true\">"+titleText+"</p>";
        
         data+="<div class=\"form-actions\">\n" +
"                              <button type=\"submit\" style=\"margin-left: 30%;\" class=\"btn "+buttonColor+"\" "+lock+" >"+validationText+"</button>" +
"                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; " +
"                           </div>";
           data+="  </div> ";
           
           
    data+=" </div>";
     isLocked=0;
    if(isLocked==1){
            data="<font color=\"red\" size=\"6px;\" style=\"margin-left: 20%;\"><b>Sorry :</b> </font><font color=\"black\" size=\"5px;\"> This data has been locked by the Administrator.</font>";
    }
    
     }
     
     else{
         data="<font color=\"red\" size=\"6px;\" style=\"margin-left: 30%;\"><b>sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support module MOH731.</font>";
     }
     }
    else{
         data="<font color=\"red\" size=\"6px;\" style=\"margin-left: 30%;\"><b>Sorry :</b> </font><font color=\"black\" size=\"5px;\"> Facility Does not Support Module  MOH731.</font>";
     }
   
     String unvalidatedLink="";int counter=0;
     if(invalidPMTCT>0 || invalidCARE>0 || invalidPEP>0){
     String getUnvalidated="SELECT moh731_new.SubPartnerID,subpartnera.SubPartnerNom FROM moh731_new JOIN subpartnera ON moh731_new.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND moh731_new.Mois='"+month+"' AND moh731_new.Annee='"+year+"' AND moh731_new.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=loadnew731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     }
    }
     
    data+="<p hidden=\"true\" id=\"invalidatedData\">"+unvalidatedLink+"</p>"; 
     
     if(conn.st!=null){conn.st.close();}
     if(conn.st1!=null){conn.st1.close();}
     if(conn.st2!=null){conn.st2.close();}
     
     if(conn.rs!=null){conn.rs.close();}
     if(conn.rs1!=null){conn.rs1.close();}
     if(conn.rs2!=null){conn.rs2.close();}
     if(conn.conn!=null){conn.conn.close();}
     System.out.println("ended here");
            out.println(data);
            
        } finally {
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
          Logger.getLogger(loadnew731.class.getName()).log(Level.SEVERE, null, ex);
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
          Logger.getLogger(loadnew731.class.getName()).log(Level.SEVERE, null, ex);
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

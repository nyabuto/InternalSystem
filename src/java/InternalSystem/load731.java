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
public class load731 extends HttpServlet {
HttpSession session;
String data,id;
String facilityId,year,month;
String HIV_CT,PMTCT,CT,VMMC,PEP,Blood;

String testing,receiving_results,receiving_postive_results;
String testing_for_HIV,HIV_Postive_results,partner_involvement,
        maternal_prophylaxis,assessment_ART,infant_testing,
        confirmed_infant,infant_feeding,infant_ARV;
String on_CP,enrolled_care,currently_care,starting_ART,revisit_ART,
        current_ART,cumulative_ART,cumulative_ARTPrevious,survival_ART,screening,pwp,HIV_care;
String number_circumcised,hiv_status,adverse_events;
String type_exposure,provided_p;
String blood_safety;
String HV0101,HV0102,HV0103,HV0105,HV0106,HV0107,HV0108,HV0109,HV0110,HV0111,HV0112,HV0113,HV0114,
        HV0115,HV0116;
String HV0201,HV0202,HV0203,HV0204,HV0205,HV0206,HV0207,HV0208,HV0209,HV0210,HV0211,HV0212,HV0213,
HV0214,HV0215,HV0216,HV0217,HV0218,HV0219,HV0220,HV0221,HV0224,HV0225,HV0226,HV0227,HV0228,HV0229,
        HV0230,HV0231,HV0232,HV0233,HV0234,HV0235,HV0236,HV0237,HV0238,HV0239,HV0240,HV0241,HV0242,
        HV0243,HV0244;
String HV0301,HV0302,HV0303,HV0304,HV0305,HV0306,HV0307,HV0308,HV0309,HV0310,HV0311,HV0312,HV0313,HV0314,
        HV0315,HV0316,HV0317,HV0318,HV0319,HV0320,HV0321,HV0322,HV0323,HV0324,HV0325,HV0326,HV0327,HV0328,
        HV0329,HV0330,HV0331,HV0332,HV0333,HV0334,HV0335,HV0336,HV0337,HV0338,HV0339,HV0340,HV0341,
        HV0342,HV0343,HV0344,HV0345,HV0346,HV0347,HV0348,HV0349,HV0350,HV0351,HV0352,HV0353,
        HV0354,HV0355,HV0904,HV0905,HV0370,HV0371,HV0372,HV0373;
String HV0401,HV0402,HV0403,HV0406,HV0407,HV0408,HV0409,HV0410,HV0411,HV0412,HV0413,HV0414,HV0415;
String HV0501,HV0502,HV0503,HV0504,HV0505,HV0506,HV0507,HV0508,HV0509,HV0510,HV0511,HV0512,HV0513,HV0514;
String HV0601,HV0602,HV0605,isValidated,validity;
int markActive;
String classType="";
String HV0340_1,HV0341_1,HV0342_1,HV0343_1,HV0344_1;
int expectedPMTCT,expectedCARE,expectedPEP,expectedHTC;
int validPMTCT,invalidPMTCT,totalPMTCT;
int validCARE,invalidCARE,totalCARE;
int validPEP,invalidPEP,totalPEP;
int validHTC,invalidHTC,totalHTC;

String enterdby,tabs,subcountyid;
String invalidPMTCTTXT,invalidCARETXT,invalidPEPTXT,invalidHTCTXT;
String unvalidatedFacilities="";
int found;
String found_db;   //0 for master and 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       String lock = null;
int isLocked=0;
found_db="none";
        HIV_CT="";
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
           markActive=0; 
           classType="";
           expectedHTC=expectedPMTCT=expectedCARE=expectedPEP=0;
     validPMTCT=invalidPMTCT=totalPMTCT=0;
     validCARE=invalidCARE=totalCARE=0;
     validPEP=invalidPEP=totalPEP=0;
     validHTC=invalidHTC=totalHTC=0;
     
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
    tabs=" <div class=\"tabbable tabbable-custom boxless\">" +
"                     <ul class=\"nav nav-tabs\">"; 
    
     markActive=0;
     if(session.getAttribute("forms_holder").toString().contains(",HTC,")){
        if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_1\" data-toggle=\"tab\"><b>1. HIV Counselling and Testing.</b></a></li>";
    markActive++; 
    }
    
    if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){
        markActive++;
          if(markActive==0){classType="class=\"active\"";}else{classType="";}
        
         tabs+=" <li  "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_2\" data-toggle=\"tab\"><b>2. Prevention of Mother-to-Child Transmission.</b></a></li>";
         }
    if(session.getAttribute("forms_holder").toString().contains(",ART,")){
        if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_3\" data-toggle=\"tab\"><b>3. Care and Treatment.</b></a></li>";
    markActive++; 
    }
    if(session.getAttribute("forms_holder").toString().contains(",PEP,")){
         if(markActive==0){classType="class=\"active\"";}else{classType="";}
         tabs+="<li "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_4\" data-toggle=\"tab\"><b>4. Post-Exposure Prophylaxis.</b></a></li>";
     markActive++;
    }
//    if(session.getAttribute("forms_holder").toString().contains(",Blood_Safety,")){
//         if(markActive==0){classType="class=\"active\"";}else{classType="";}
//         data+="<li "+classType+"><a class=\"advance_form_with_chosen_element\" href=\"#tab_4\" data-toggle=\"tab\"><b>4. Blood Safety.</b></a></li>";
//       }
         tabs+="<li style=\"margin-left:20px;\" id=\"isValidated\"></li>" +
                "</ul>" +
                "</div>" +
                "<div class=\"tab-content\" id=\"data\">";
    
    markActive=0;
    classType="";
//          id="2015_1_14498";
    
   
   String getExpectedForms="SELECT SUM(PMTCT),SUM(ART),SUM(PEP),SUM(HTC) FROM subpartnera WHERE subpartnera.DistrictID='"+subcountyid+"'" ;
   conn.rs1=conn.st1.executeQuery(getExpectedForms);
   if(conn.rs1.next()==true){
//       System.out.println("pmtct : "+conn.rs1.getString(1)+"  care : "+conn.rs1.getInt(2)+" pep : "+conn.rs1.getInt(3));
           expectedPMTCT=conn.rs1.getInt(1);
           expectedCARE=conn.rs1.getInt(2);
           expectedPEP=conn.rs1.getInt(3);
           expectedHTC=conn.rs1.getInt(4);
   }
    validPMTCT=invalidPMTCT=totalPMTCT=0;
     validCARE=invalidCARE=totalCARE=0;
     validPEP=invalidPEP=totalPEP=0;
     validHTC=invalidHTC=totalHTC=0;
     
    String getEntered="SELECT moh731.isValidated,SUM(subpartnera.PMTCT),SUM(subpartnera.ART),SUM(subpartnera.PEP),SUM(subpartnera.HTC)"
            + " FROM subpartnera JOIN moh731 ON subpartnera.SubPartnerID=moh731.SubPartnerID WHERE "
            + "moh731.Mois='"+month+"' AND moh731.Annee='"+year+"' AND subpartnera.DistrictID='"+subcountyid+"' GROUP BY moh731.isValidated";
    conn.rs1=conn.st1.executeQuery(getEntered);
    while(conn.rs1.next()){
        System.out.println("isvalidated : "+conn.rs1.getInt(1)+"  num : "+conn.rs1.getInt(2));
   if(conn.rs1.getInt(1)==1){
    validPMTCT=conn.rs1.getInt(2);
    validCARE=conn.rs1.getInt(3);
    validPEP=conn.rs1.getInt(4);
    validHTC=conn.rs1.getInt(5);
   }
   if(conn.rs1.getInt(1)==0){
      invalidPMTCT=conn.rs1.getInt(2);
      invalidCARE=conn.rs1.getInt(3);
      invalidPEP=conn.rs1.getInt(4);
      invalidHTC=conn.rs1.getInt(5);
      
   }
    }
    totalPMTCT=validPMTCT+invalidPMTCT;
    totalCARE=validCARE+invalidCARE;
    totalPEP=validPEP+invalidPEP;
    totalHTC=validHTC+invalidHTC;
// invalidPMTCTTXT="Unvalidated Form(s) : "+invalidPMTCT;
// invalidCARETXT="Unvalidated Form(s) : "+invalidCARE;
// invalidPEPTXT="Unvalidated Form(s) : "+invalidPEP;
  
  invalidPMTCTTXT=" Unvalidated Form(s) : 0";
 invalidCARETXT=" Unvalidated Form(s) : 0";
 invalidPEPTXT=" Unvalidated Form(s) : 0";
 invalidHTCTXT=" Unvalidated Form(s) : 0";
 
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
    int tabcount=1;
     markActive=0;
     String myclass="";
     if(session.getAttribute("forms_holder").toString().contains(",HTC,"))
        {
            if(markActive==0){classType="active";}else{classType="";}
            
        HIV_CT="<div class=\"tab-pane "+classType+" \" id=\"tab_1\"><div class=\"portlet box blue\">" +
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
        CT="<div class=\"tab-pane "+classType+" \" id=\"tab_3\"><div class=\"portlet box blue\">" +
                              "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>3. Care and Treatment.</b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalCARE+" out of "+expectedCARE+" &nbsp; Validated Form(s) : "+validCARE+"  &nbsp; "+invalidCARETXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
//        VMMC="<div class=\"tab-pane \" id=\"tab_4\"><div class=\"portlet box blue\">" +
//                              "<div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:40%;\">4. Voluntary Medical Male Circumcision</h4>" +
//                              "</div><div class=\"portlet-body form\">";
         if(session.getAttribute("forms_holder").toString().contains(",PEP,")){
              if(markActive==0){classType="active";}else{classType="";}
        PEP="<div class=\"tab-pane "+classType+" \" id=\"tab_4\"><div class=\"portlet box blue\">" +
                             "<br><div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:0%;\"><b>4. Post-Exposure Prophylaxis. </b><b style=\"color:yellow; font-family:cambria;  margin-left:25%; font-size:16px;\"> Record Counter: "+totalPEP+" out of "+expectedPEP+" &nbsp; Validated Form(s) : "+validPEP+"  &nbsp; "+invalidPEPTXT+"</b></h4>" +
                              "<br></div><div class=\"portlet-body form\">";
         markActive++;
          tabcount++;
         }
//             if(session.getAttribute("forms_holder").toString().contains(",Blood_Safety,")){
//         if(markActive==0){classType="active";}else{classType="";}
//        Blood="<div class=\"tab-pane "+classType+" \" id=\"tab_4\"><div class=\"portlet box blue\">" +
//                              "<div class=\"portlet-autocomplete=\"off\" title\"><h4 style=\"margin-left:40%;\">"+tabcount+". Blood Safety.</h4>" +
//                              "</div><div class=\"portlet-body form\">";
//             }
             
             
        
  isValidated="";validity="";          
 HV0101=HV0102=HV0103=HV0105=HV0106=HV0107=HV0108=HV0109=HV0110=HV0111=HV0112=HV0113=HV0114=
        HV0115=HV0116="";
HV0201=HV0202=HV0203=HV0204=HV0205=HV0206=HV0207=HV0208=HV0209=HV0210=HV0211=HV0212=HV0213=
HV0214=HV0215=HV0216=HV0217=HV0218=HV0219=HV0220=HV0221=HV0224=HV0225=HV0226=HV0227=HV0228=HV0229=
        HV0230=HV0231=HV0232=HV0233=HV0234=HV0235=HV0236=HV0237=HV0238=HV0239=HV0240=HV0241=HV0242=
        HV0243=HV0244="";
HV0301=HV0302=HV0303=HV0304=HV0305=HV0306=HV0307=HV0308=HV0309=HV0310=HV0311=HV0312=HV0313=HV0314=
        HV0315=HV0316=HV0317=HV0318=HV0319=HV0320=HV0321=HV0322=HV0323=HV0324=HV0325=HV0326=HV0327=HV0328=
        HV0329=HV0330=HV0331=HV0332=HV0333=HV0334=HV0335=HV0336=HV0337=HV0338=HV0339=HV0340=HV0341=
        HV0342=HV0343=HV0344=HV0345=HV0346=HV0347=HV0348=HV0349=HV0350=HV0351=HV0352=HV0353=
        HV0354=HV0355=HV0904=HV0905=HV0370=HV0371=HV0372=HV0373="";
HV0401=HV0402=HV0403=HV0406=HV0407=HV0408=HV0409=HV0410=HV0411=HV0412=HV0413=HV0414=HV0415="";
 HV0501=HV0502=HV0503=HV0504=HV0505=HV0506=HV0507=HV0508=HV0509=HV0510=HV0511=HV0512=HV0513=HV0514="";
 HV0601=HV0602=HV0605="";
 HV0340_1=HV0341_1=HV0342_1=HV0343_1="0";
 HV0344_1="0";
 
 
   
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
 
 
String getmaxmonthandyear="select Max(yearmonth) as yearmo from moh731 where yearmonth < '"+yearmonth+"' and (HV0340 !='0' && HV0340 is not null) and SubPartnerID='"+facilityId+"'";
  
            System.out.println("__"+getmaxmonthandyear);

conn.rs2=conn.st2.executeQuery(getmaxmonthandyear);

if(conn.rs2.next()){
    
    if(conn.rs2.getString(1)!=null){
maxyearmonth=conn.rs2.getString(1);
    }

}
int yrmonth=Integer.parseInt(maxyearmonth);
//if(yrmonth>201503 && yrmonth<=201504){
 System.out.println("Max Year Month____"+maxyearmonth);
//After getting the Maximum valid YearMonth,(By Valid I mean a month that was reported with a cumulative greater than zero) select the cumulatives for male, female and totals

    String gatmaxs="select HV0340 , HV0341 ,HV0342,HV0343,HV0344  from moh731 where yearmonth='"+maxyearmonth+"' and SubPartnerID='"+facilityId+"'";
    
      conn.rs=conn.st.executeQuery(gatmaxs);
    if(conn.rs.next()==true){
    
HV0340_1=conn.rs.getString("HV0340");
if(HV0340_1==null){HV0340_1="0"; }

HV0341_1=conn.rs.getString("HV0341");
if(HV0341_1==null){HV0341_1="0"; }

HV0342_1=conn.rs.getString("HV0342");
if(HV0342_1==null){HV0342_1="0"; }
 
HV0343_1=conn.rs.getString("HV0343");
if(HV0343_1==null){HV0343_1="0"; }

HV0344_1=conn.rs.getString("HV0344");
if(HV0344_1==null){HV0344_1="0"; }
}
    else{
//        Get baselines
        String baselines="SELECT HV0340 , HV0341 ,HV0342,HV0343,HV0344 FROM moh731_baseline WHERE SubPartnerID='"+facilityId+"'";
       conn.rs=conn.st.executeQuery(baselines);
    if(conn.rs.next()==true){
    
HV0340_1=conn.rs.getString(1);
HV0341_1=conn.rs.getString(2);
HV0342_1=conn.rs.getString(3);
HV0343_1=conn.rs.getString(4);
HV0344_1=conn.rs.getString(5);
}
 
    }    

    found=0;
//}
 HV0340=HV0340_1;HV0341=HV0341_1;HV0342=HV0342_1;HV0343=HV0343_1;HV0344=HV0344_1;
     
           String checker="SELECT * FROM moh731 WHERE id=?";
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          if(conn.rs.next()==true){
          found++;   
          found_db="master";
          }
        session.setAttribute("found_db", found_db);
        session.setAttribute("found", found);
          if(found==1){
              
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
              
              
              
              System.out.println("Data already exist loading............................");
              
//      set table_id session VERY IMPORTANT
      session.setAttribute("table_id", id);
      
      
  if(conn.rs.getString("HV0101")!=null){HV0101=conn.rs.getString("HV0101");}
  if(conn.rs.getString("HV0102")!=null){HV0102=conn.rs.getString("HV0102");}
  if(conn.rs.getString("HV0103")!=null){HV0103=conn.rs.getString("HV0103");}
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

if(conn.rs.getString("HV0301")!=null){HV0301=conn.rs.getString("HV0301");}
if(conn.rs.getString("HV0302")!=null){HV0302=conn.rs.getString("HV0302");}
if(conn.rs.getString("HV0303")!=null){HV0303=conn.rs.getString("HV0303");}
if(conn.rs.getString("HV0304")!=null){HV0304=conn.rs.getString("HV0304");}
if(conn.rs.getString("HV0305")!=null){HV0305=conn.rs.getString("HV0305");}
if(conn.rs.getString("HV0306")!=null){HV0306=conn.rs.getString("HV0306");}
if(conn.rs.getString("HV0307")!=null){HV0307=conn.rs.getString("HV0307");}
if(conn.rs.getString("HV0308")!=null){HV0308=conn.rs.getString("HV0308");}
if(conn.rs.getString("HV0309")!=null){HV0309=conn.rs.getString("HV0309");}
if(conn.rs.getString("HV0310")!=null){HV0310=conn.rs.getString("HV0310");}
if(conn.rs.getString("HV0311")!=null){HV0311=conn.rs.getString("HV0311");}
if(conn.rs.getString("HV0312")!=null){HV0312=conn.rs.getString("HV0312");}
if(conn.rs.getString("HV0313")!=null){HV0313=conn.rs.getString("HV0313");}
if(conn.rs.getString("HV0314")!=null){HV0314=conn.rs.getString("HV0314");}
if(conn.rs.getString("HV0315")!=null){HV0315=conn.rs.getString("HV0315");}
if(conn.rs.getString("HV0316")!=null){HV0316=conn.rs.getString("HV0316");}
if(conn.rs.getString("HV0317")!=null){HV0317=conn.rs.getString("HV0317");}
if(conn.rs.getString("HV0318")!=null){HV0318=conn.rs.getString("HV0318");}
if(conn.rs.getString("HV0319")!=null){HV0319=conn.rs.getString("HV0319");}
if(conn.rs.getString("HV0320")!=null){HV0320=conn.rs.getString("HV0320");}
if(conn.rs.getString("HV0321")!=null){HV0321=conn.rs.getString("HV0321");}
if(conn.rs.getString("HV0322")!=null){HV0322=conn.rs.getString("HV0322");}
if(conn.rs.getString("HV0323")!=null){HV0323=conn.rs.getString("HV0323");}
if(conn.rs.getString("HV0324")!=null){HV0324=conn.rs.getString("HV0324");}
if(conn.rs.getString("HV0325")!=null){HV0325=conn.rs.getString("HV0325");}
if(conn.rs.getString("HV0326")!=null){HV0326=conn.rs.getString("HV0326");}
if(conn.rs.getString("HV0327")!=null){HV0327=conn.rs.getString("HV0327");}
if(conn.rs.getString("HV0328")!=null){HV0328=conn.rs.getString("HV0328");}
if(conn.rs.getString("HV0329")!=null){HV0329=conn.rs.getString("HV0329");}
if(conn.rs.getString("HV0330")!=null){HV0330=conn.rs.getString("HV0330");}
if(conn.rs.getString("HV0331")!=null){HV0331=conn.rs.getString("HV0331");}
if(conn.rs.getString("HV0332")!=null){HV0332=conn.rs.getString("HV0332");}
if(conn.rs.getString("HV0333")!=null){HV0333=conn.rs.getString("HV0333");}
if(conn.rs.getString("HV0334")!=null){HV0334=conn.rs.getString("HV0334");}
if(conn.rs.getString("HV0335")!=null){HV0335=conn.rs.getString("HV0335");}
if(conn.rs.getString("HV0336")!=null){HV0336=conn.rs.getString("HV0336");}
if(conn.rs.getString("HV0337")!=null){HV0337=conn.rs.getString("HV0337");}
if(conn.rs.getString("HV0338")!=null){HV0338=conn.rs.getString("HV0338");}
if(conn.rs.getString("HV0339")!=null){HV0339=conn.rs.getString("HV0339");}
if(conn.rs.getString("HV0340")!=null){HV0340=conn.rs.getString("HV0340");}
if(conn.rs.getString("HV0341")!=null){HV0341=conn.rs.getString("HV0341");}
if(conn.rs.getString("HV0342")!=null){HV0342=conn.rs.getString("HV0342");}
if(conn.rs.getString("HV0343")!=null){HV0343=conn.rs.getString("HV0343");}
if(conn.rs.getString("HV0344")!=null){HV0344=conn.rs.getString("HV0344");}
if(conn.rs.getString("HV0345")!=null){HV0345=conn.rs.getString("HV0345");}
if(conn.rs.getString("HV0346")!=null){HV0346=conn.rs.getString("HV0346");}
if(conn.rs.getString("HV0347")!=null){HV0347=conn.rs.getString("HV0347");}
if(conn.rs.getString("HV0348")!=null){HV0348=conn.rs.getString("HV0348");}
if(conn.rs.getString("HV0349")!=null){HV0349=conn.rs.getString("HV0349");}
if(conn.rs.getString("HV0350")!=null){HV0350=conn.rs.getString("HV0350");}
if(conn.rs.getString("HV0351")!=null){HV0351=conn.rs.getString("HV0351");}
if(conn.rs.getString("HV0352")!=null){HV0352=conn.rs.getString("HV0352");}
if(conn.rs.getString("HV0353")!=null){HV0353=conn.rs.getString("HV0353");}
if(conn.rs.getString("HV0354")!=null){HV0354=conn.rs.getString("HV0354");}
if(conn.rs.getString("HV0355")!=null){HV0355=conn.rs.getString("HV0355");}
if(conn.rs.getString("HV0904")!=null){HV0904=conn.rs.getString("HV0904");}
if(conn.rs.getString("HV0905")!=null){HV0905=conn.rs.getString("HV0905");}
if(conn.rs.getString("HV0370")!=null){HV0370=conn.rs.getString("HV0370");}
if(conn.rs.getString("HV0371")!=null){HV0371=conn.rs.getString("HV0371");}
if(conn.rs.getString("HV0372")!=null){HV0372=conn.rs.getString("HV0372");}
if(conn.rs.getString("HV0373")!=null){HV0373=conn.rs.getString("HV0373");}

if(conn.rs.getString("HV0401")!=null){HV0401=conn.rs.getString("HV0401");}
if(conn.rs.getString("HV0402")!=null){HV0402=conn.rs.getString("HV0402");}
if(conn.rs.getString("HV0403")!=null){HV0403=conn.rs.getString("HV0403");}
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

if(conn.rs.getString("HV0501")!=null){HV0501=conn.rs.getString("HV0501");}
if(conn.rs.getString("HV0502")!=null){HV0502=conn.rs.getString("HV0502");}
if(conn.rs.getString("HV0503")!=null){HV0503=conn.rs.getString("HV0503");}
if(conn.rs.getString("HV0504")!=null){HV0504=conn.rs.getString("HV0504");}
if(conn.rs.getString("HV0505")!=null){HV0505=conn.rs.getString("HV0505");}
if(conn.rs.getString("HV0506")!=null){HV0506=conn.rs.getString("HV0506");}
if(conn.rs.getString("HV0507")!=null){HV0507=conn.rs.getString("HV0507");}
if(conn.rs.getString("HV0508")!=null){HV0508=conn.rs.getString("HV0508");}
if(conn.rs.getString("HV0509")!=null){HV0509=conn.rs.getString("HV0509");}
if(conn.rs.getString("HV0510")!=null){HV0510=conn.rs.getString("HV0510");}
if(conn.rs.getString("HV0511")!=null){HV0511=conn.rs.getString("HV0511");}
if(conn.rs.getString("HV0512")!=null){HV0512=conn.rs.getString("HV0512");}
if(conn.rs.getString("HV0513")!=null){HV0513=conn.rs.getString("HV0513");}
if(conn.rs.getString("HV0514")!=null){HV0514=conn.rs.getString("HV0514");}

if(conn.rs.getString("HV0601")!=null){HV0601=conn.rs.getString("HV0601");}
if(conn.rs.getString("HV0602")!=null){HV0602=conn.rs.getString("HV0602");}
if(conn.rs.getString("HV0605")!=null){HV0605=conn.rs.getString("HV0605");}
 
isLocked=conn.rs.getInt("isLocked");
//isLocked=1;
if(isLocked==1){lock="disabled";}

if(HV0340.equals("")){HV0340=HV0340_1;}
if(HV0341.equals("")){HV0341=HV0341_1;}
if(HV0342.equals("")){HV0342=HV0342_1;}
if(HV0343.equals("")){HV0343=HV0343_1;}
if(HV0344.equals("")){HV0344=HV0344_1;}
System.out.println("HVO340 : "+HV0340+" HV0340_1 : "+HV0340_1);
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
       testing=receiving_results=receiving_postive_results="";    
          HIV_CT+="";
      testing="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.1 Testing.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">First</td><td style=\"padding-right:40px;\" >HV01-01</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"1\" name=\"HV0101\" id=\"HV0101\" value=\""+HV0101+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0101'); testingTotal();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Repeat</td><td style=\"padding-right:40px;\" >HV01-02</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"2\" name=\"HV0102\" id=\"HV0102\" value=\""+HV0102+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0102'); testingTotal();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (HV0-01 plus HV01-02)</td><td style=\"padding-right:40px;\" >HV01-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0103\" id=\"HV0103\"  value=\""+HV0103+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0103');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Couples</td><td style=\"padding-right:40px;\" >HV01-05</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"3\" name=\"HV0105\" id=\"HV0105\" value=\""+HV0105+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0105');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Static [Facility]</td><td style=\"padding-right:40px;\" >HV01-06</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"4\" name=\"HV0106\" id=\"HV0106\" value=\""+HV0106+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0106');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Outreach</td><td style=\"padding-right:40px;\" >HV01-07</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"5\" name=\"HV0107\" id=\"HV0107\" value=\""+HV0107+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0107');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + "</table>"
              + "<br></fieldset>";
      
       receiving_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.2 Receiving results - </b>(<i>Couples only</i>)</legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Cocordant couples</td><td style=\"padding-right:40px;\" >HV01-08</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"6\" name=\"HV0108\" id=\"HV0108\" value=\""+HV0108+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0108');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV01-09</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"7\" name=\"HV0109\" id=\"HV0109\" value=\""+HV0109+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0109');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "</table></td>"
              + "<br></fieldset>";
       
        receiving_postive_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">1.3 Receiving postive results.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Males - Below 15 years</td><td style=\"padding-right:40px;\" >HV01-10</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"8\" name=\"HV0110\" id=\"HV0110\" value=\""+HV0110+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0110'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females - Below 15 years.</td><td style=\"padding-right:40px;\" >HV01-11</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"9\" name=\"HV0111\" id=\"HV0111\" value=\""+HV0111+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0111'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 15-24 years </td><td style=\"padding-right:40px;\" >HV01-12</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"10\" name=\"HV0112\"  id=\"HV0112\" value=\""+HV0112+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0112'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 15-24 years </td><td style=\"padding-right:40px;\" >HV01-13</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"11\" name=\"HV0113\" id=\"HV0113\" value=\""+HV0113+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0113'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 25 years and older</td><td style=\"padding-right:40px;\" >HV01-14</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"12\" name=\"HV0114\" id=\"HV0114\" value=\""+HV0114+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0114'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 25 years and older</td><td style=\"padding-right:40px;\" >HV01-15</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"13\" name=\"HV0115\" id=\"HV0115\" value=\""+HV0115+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0115'); postiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total receiving postive results <br>(Sum HV01-10 to HV01-15)</td><td style=\"padding-right:40px;\" >HV01-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0116\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0116');\" id=\"HV0116\" value=\""+HV0116+"\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
      
             HIV_CT+=testing+""+receiving_results+""+receiving_postive_results+""; 
//              System.out.println("Loading new form for data entry.......................");

//    PMTCT################################################################
//               ##########################################################
       
        testing_for_HIV=HIV_Postive_results=partner_involvement=
        maternal_prophylaxis=assessment_ART=infant_testing=
        confirmed_infant=infant_feeding=infant_ARV="";
           
          testing_for_HIV="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.1 Testing for HIV.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-01</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"14\" name=\"HV0201\"  id=\"HV0201\" value=\""+HV0201+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0201'); testingForHIV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-02</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"15\" name=\"HV0202\" id=\"HV0202\" value=\""+HV0202+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0202'); testingForHIV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-03</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"16\" name=\"HV0203\" id=\"HV0203\" value=\""+HV0203+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0203'); testingForHIV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-01 to HV02-03) </td><td style=\"padding-right:40px;\" >HV02-04</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0204\" id=\"HV0204\" value=\""+HV0204+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0204');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
             + "</table>"
              + "<br></fieldset>";
          
       HIV_Postive_results="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.2 HIV Postive Results.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Known Postive status(at entry into ANC)</td><td style=\"padding-right:40px;\" >HV02-05</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"17\" name=\"HV0205\" id=\"HV0205\" value=\""+HV0205+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0205'); HIVPostiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-06</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"18\" name=\"HV0206\" id=\"HV0206\" value=\""+HV0206+"\" maxLength=\"4\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0206'); HIVPostiveResults();\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-07</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"19\" name=\"HV0207\" id=\"HV0207\" value=\""+HV0207+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0207');  HIVPostiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-08</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"20\" name=\"HV0208\" id=\"HV0208\" value=\""+HV0208+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0208'); HIVPostiveResults();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-05 to HV02-08) </td><td style=\"padding-right:40px;\" >HV02-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0209\" id=\"HV0209\" value=\""+HV0209+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0209');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total with known status <br> (Sum HV02-04 to HV02-05) </td><td style=\"padding-right:40px;\" >HV02-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0210\" id=\"HV0210\" value=\""+HV0210+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0210');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";   
          
          partner_involvement="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.3 Partner Involvement.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Male partners tested (ANC/L&D)</td><td style=\"padding-right:40px;\" >HV02-11</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"20\" name=\"HV0211\" id=\"HV0211\" value=\""+HV0211+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0211');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV02-12</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"21\" name=\"HV0212\" id=\"HV0212\" value=\""+HV0212+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0212');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
          
          
        
           maternal_prophylaxis="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.4 Maternal Prophylaxis .</b>(<i>at first contact only</i>)</legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - NVP only </td><td style=\"padding-right:40px;\" >HV02-13</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"22\" name=\"HV0213\" id=\"HV0213\" value=\""+HV0213+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0213'); MaternalProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - (AZT + SdNVP) </td><td style=\"padding-right:40px;\" >HV02-14</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"23\" name=\"HV0214\" id=\"HV0214\" value=\""+HV0214+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0214'); MaternalProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis -Interrupted HAART </td><td style=\"padding-right:40px;\" >HV02-15</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"24\" name=\"HV0215\" id=\"HV0215\" value=\""+HV0215+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0215'); MaternalProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HAART (ART) </td><td style=\"padding-right:40px;\" >HV02-16</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"25\" name=\"HV0216\" id=\"HV0216\" value=\""+HV0216+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0216'); MaternalProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">total PMTCT prophylaxis (Sum HV02-13 to HV02-16) </td><td style=\"padding-right:40px;\" >HV02-17</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0217\" id=\"HV0217\" value=\""+HV0217+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0217');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
           
          assessment_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.5 Assessment for ART Eligibility in MCH .</b>(<i>at diagnosis</i>)</legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - WHO staging done </td><td style=\"padding-right:40px;\" >HV02-18</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"26\" name=\"HV0218\" id=\"HV0218\" value=\""+HV0218+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0218'); ARTEligibility();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - CD4 </td><td style=\"padding-right:40px;\" >HV02-19</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"27\" name=\"HV0219\" id=\"HV0219\" value=\""+HV0219+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0219'); ARTEligibility();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility in ANC (Sum HV02-18 to HV02-19) </td><td style=\"padding-right:40px;\" >HV02-20</td><td style=\"padding-right:40px;\"><input type=\"text\"  name=\"HV0220\" id=\"HV0220\" value=\""+HV0220+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0220');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Started on ART during ANC </td><td style=\"padding-right:40px;\" >HV02-21</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"28\" name=\"HV0221\" id=\"HV0221\" value=\""+HV0221+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0221');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
           
           infant_testing="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.6 Infant Testing</b> (<i>Initial tests only</i>)</legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (within 2 months) </td><td style=\"padding-right:40px;\" >HV02-24</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"29\" name=\"HV0224\" id=\"HV0224\" value=\""+HV0224+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0224'); infantTesting();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (From 3 to 8 months) </td><td style=\"padding-right:40px;\" >HV02-25</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"30\" name=\"HV0225\" id=\"HV0225\" value=\""+HV0225+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0225'); infantTesting();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Serology antibody test (from 9 to 12 months)  </td><td style=\"padding-right:40px;\" >HV02-26</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"31\" name=\"HV0226\" id=\"HV0226\" value=\""+HV0226+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0226'); infantTesting();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (from 9 to 12 months) </td><td style=\"padding-right:40px;\" >HV02-27</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0227\" id=\"HV0227\" tabindex=\"32\" value=\""+HV0227+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0227');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total HEI Tested by 12 months (Sum HV02-24 to HV02-26) </td><td style=\"padding-right:40px;\" >HV02-28</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0228\" id=\"HV0228\" value=\""+HV0228+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0228');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
                   
          confirmed_infant="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.7 Confirmed Infant Test Results</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (within 2 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-29</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"33\" name=\"HV00229\" id=\"HV0229\" value=\""+HV0229+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0229');  confirmedInfants();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (From 3 to 8 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-30</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"34\" name=\"HV00230\" id=\"HV0230\" value=\""+HV0230+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0230'); confirmedInfants();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive -  (from 9 to 12 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-31</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"35\" name=\"HV0231\" id=\"HV0231\" value=\""+HV0231+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0231'); confirmedInfants();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Confirmed Postive (Sum HV02-29 to HV02-31) </td><td style=\"padding-right:40px;\" >HV02-32</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0232\" id=\"HV0232\" value=\""+HV0232+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0232');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
              
          infant_feeding="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.8 Infant feeding.</b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">EBF (at 6 months)</td><td style=\"padding-right:40px;\" >HV02-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0233\" id=\"HV0233\" tabindex=\"36\" value=\""+HV0233+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0233'); exposedAgedSix();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">ERF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-34</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"37\" name=\"HV0234\" id=\"HV0234\" value=\""+HV0234+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0234'); exposedAgedSix();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">MF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-35</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"38\" name=\"HV0235\" id=\"HV0235\" value=\""+HV0235+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0235'); exposedAgedSix();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 6 months. </td><td style=\"padding-right:40px;\" >HV02-36</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0236\" id=\"HV0236\" value=\""+HV0236+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0236');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
             + "<tr>"
              + "<td style=\"padding-right:40px;\">BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-37</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"39\" name=\"HV0237\" id=\"HV0237\" value=\""+HV0237+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0237'); exposedAgedTwelve();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-38</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"40\" name=\"HV0238\" id=\"HV0238\" value=\""+HV0238+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0238'); exposedAgedTwelve();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not known </td><td style=\"padding-right:40px;\" >HV02-39</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"40\" name=\"HV0239\" id=\"HV0239\" value=\""+HV0239+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0239'); exposedAgedTwelve();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 12 months (Sum HV02-37 to HV02-39) </td><td style=\"padding-right:40px;\" >HV02-40</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0240\" id=\"HV0240\" value=\""+HV0240+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0240');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
          
           infant_ARV="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">2.9 Infant ARV Prophlaxis</b> (<i>at first contact only</i>)</legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Issued in ANC </td><td style=\"padding-right:40px;\" >HV02-41</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"40\" name=\"HV0241\" id=\"HV0241\" value=\""+HV0241+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0241'); infantARV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and Delivery </td><td style=\"padding-right:40px;\" >HV02-42</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"41\" name=\"HV0242\" id=\"HV0242\" value=\""+HV0242+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0242');  infantARV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PNC(<72hrs) </td><td style=\"padding-right:40px;\" >HV02-43</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"42\" name=\"HV0243\" id=\"HV0243\" value=\""+HV0243+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0243'); infantARV();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Infants Issued Prophylaxis (Sum HV02-41 to HV02-43) </td><td style=\"padding-right:40px;\" >HV02-44</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0244\" id=\"HV0244\" value=\""+HV0244+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0244');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
           
  //    ###############################CARE AND TREATMENT #################################
//               ##########################################################         
           
          PMTCT+=testing_for_HIV+""+HIV_Postive_results+""+partner_involvement+""+
        maternal_prophylaxis+""+assessment_ART+""+infant_testing+""+
        confirmed_infant+""+infant_feeding+""+infant_ARV;
          
         on_CP=enrolled_care=currently_care=starting_ART=revisit_ART=
        current_ART=cumulative_ART=cumulative_ARTPrevious=survival_ART=screening=pwp=HIV_care="";
         
         on_CP="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.1 On Cotrimoxazole Prophylaxis</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed Infant (within 2 months) </td><td style=\"padding-right:40px;\" >HV03-01</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"43\" name=\"HV0301\" id=\"HV0301\" value=\""+HV0301+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0301');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed infant (Eligible for CTX at 2 months) </td><td style=\"padding-right:40px;\" >HV03-02</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"44\" name=\"HV0302\" id=\"HV0302\" value=\""+HV0302+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0302');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - below 15 years</td><td style=\"padding-right:40px;\" >HV03-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"45\" name=\"HV0303\" id=\"HV0303\" value=\""+HV0303+"\" maxLength=\"4\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0303'); totalCTX();\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"46\" name=\"HV0304\" id=\"HV0304\" value=\""+HV0304+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0304'); totalCTX();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & older </td><td style=\"padding-right:40px;\" >HV03-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"47\" name=\"HV0305\" id=\"HV0305\" value=\""+HV0305+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0305'); totalCTX();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"48\" name=\"HV0306\" id=\"HV0306\" value=\""+HV0306+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0306'); totalCTX();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & olderTotal On CTX (Sum HV03-03 to HV03-06) </td><td style=\"padding-right:40px;\" >HV03-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0307\" id=\"HV0307\" value=\""+HV0307+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0307');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
         enrolled_care="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.2 Enrolled in Care</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-08</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"49\" name=\"HV0308\" id=\"HV0308\" value=\""+HV0308+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0308');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-09(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"50\" name=\"HV0309\" id=\"HV0309\" value=\""+HV0309+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0309'); enrolledCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-10(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"51\" name=\"HV0310\" id=\"HV0310\" value=\""+HV0310+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0310'); enrolledCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-11(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"52\" name=\"HV0311\" id=\"HV0311\" value=\""+HV0311+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0311'); enrolledCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-12(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"53\" name=\"HV0312\" id=\"HV0312\" value=\""+HV0312+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0312'); enrolledCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" id=\"HV0313\" value=\""+HV0313+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0313');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
         currently_care="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.3 Currently in Care</b>(<i>from the tally sheet-this month only and from last 2 months</i>)</legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-14</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"54\" name=\"HV0314\" id=\"HV0314\" value=\""+HV0314+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0314');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-15(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"55\" name=\"HV0315\" id=\"HV0315\" value=\""+HV0315+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0315'); currentCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-16(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"56\" name=\"HV0316\" id=\"HV0316\" value=\""+HV0316+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0316'); currentCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-17(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"57\" name=\"HV0317\" id=\"HV0317\" value=\""+HV0317+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0317'); currentCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-18(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"58\" name=\"HV0318\" id=\"HV0318\" value=\""+HV0318+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0318'); currentCare();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-19</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0319\" id=\"HV0319\" value=\""+HV0319+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0319');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
         starting_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.4 Starting ART</b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-20</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"59\" name=\"HV0320\" id=\"HV0320\" value=\""+HV0320+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0320'); startingART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-21(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"60\" name=\"HV0321\" id=\"HV0321\" value=\""+HV0321+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0321');  startingART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-22(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"61\" name=\"HV0322\" id=\"HV0322\" value=\""+HV0322+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0322'); startingART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-23(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"62\" name=\"HV0323\" id=\"HV0323\" value=\""+HV0323+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0323');  startingART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-24(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"63\" name=\"HV0324\" id=\"HV0324\" value=\""+HV0324+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0324'); startingART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Total (Sum HV03-21 to HV03-24) </td><td style=\"padding-right:40px;\" >HV03-25</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0325\" maxLength=\"6\" id=\"HV0325\" value=\""+HV0325+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0325');\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - Pregnant </td><td style=\"padding-right:40px;\" >HV03-26</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"64\" name=\"HV0326\" id=\"HV0326\" value=\""+HV0326+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0326');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  "
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - TB Patient </td><td style=\"padding-right:40px;\" >HV03-27</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"65\" name=\"HV0327\" id=\"HV0327\" value=\""+HV0327+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0327');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> "
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
         
         revisit_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.5 Revisits on ART</b>(<i>from the tally sheet-this month only and from last 2 months</i>)</legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-28</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"66\" name=\"HV0328\" id=\"HV0328\" value=\""+HV0328+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0328'); revisitART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-29(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"67\" name=\"HV0329\" id=\"HV0329\" value=\""+HV0329+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0329'); revisitART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-30(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"68\" name=\"HV0330\" id=\"HV0330\" value=\""+HV0330+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0330');  revisitART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-31(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"69\" name=\"HV0331\" id=\"HV0331\" value=\""+HV0331+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0331'); revisitART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-32(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"70\" name=\"HV0332\" id=\"HV0332\" value=\""+HV0332+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0332'); revisitART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Total (Sum HV03-29 to HV03-32) </td><td style=\"padding-right:40px;\" >HV03-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0333\" id=\"HV0333\" value=\""+HV0333+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0333');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
          current_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.6 Currently on ART [All]</b>(<i>Add 3.4 and 3.5 e.g HV03-34=HV03-20+HV03-28</i>)</legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-34</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0334\" id=\"HV0334\" value=\""+HV0334+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0334');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-35(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0335\" id=\"HV0335\" value=\""+HV0335+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0335'); currentART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>  <td style=\"padding-right:40px;\" >HV03-36(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0336\" id=\"HV0336\" value=\""+HV0336+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0336'); currentART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-37(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0337\" id=\"HV0337\" value=\""+HV0337+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0337'); currentART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td> <td style=\"padding-right:40px;\" >HV03-38(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0338\" id=\"HV0338\" value=\""+HV0338+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0338'); currentART();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-39</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0339\" id=\"HV0339\" value=\""+HV0339+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0339');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
          
         cumulative_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.7 Cumullative ever on ART </b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-40(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0340\"  tabindex=\"-1\" id=\"HV0340\" value=\""+HV0340+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0340'); cumulativeART();\" maxLength=\"5\" onkeypress=\"return numbers(event)\"  readonly=\"true\" style=\"width: 80px; background-color:#DDDDDD;\"></td>  <td style=\"padding-right:40px;\" >HV03-41(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"-1\" name=\"HV0341\" id=\"HV0341\" value=\""+HV0341+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0341'); cumulativeART();\" maxLength=\"5\" onkeypress=\"return numbers(event)\"  readonly=\"true\" style=\"width: 80px; background-color:#DDDDDD;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-42(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0342\"  tabindex=\"-1\" id=\"HV0342\" value=\""+HV0342+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0342'); cumulativeART();\" maxLength=\"5\" onkeypress=\"return numbers(event)\"  readonly=\"true\" style=\"width: 80px; background-color:#DDDDDD;\"></td> <td style=\"padding-right:40px;\" >HV03-43(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"-1\" name=\"HV0343\" id=\"HV0343\" value=\""+HV0343+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0343');  cumulativeART();\" maxLength=\"5\" onkeypress=\"return numbers(event)\"  readonly=\"true\" style=\"width: 80px; background-color:#DDDDDD;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Total (Sum HV03-40 to HV03-43) </td><td style=\"padding-right:40px;\" >HV03-44</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"-1\" name=\"HV0344\" id=\"HV0344\" value=\""+HV0344+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0344');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
         
          cumulative_ARTPrevious="<input type=\"hidden\" name=\"HV0340_1\" id=\"HV0340_1\" value=\""+HV0340_1+"\"> <input type=\"hidden\" name=\"HV0341_1\" id=\"HV0341_1\" value=\""+HV0341_1+"\">"
              + "<input type=\"hidden\" name=\"HV0342_1\" id=\"HV0342_1\" value=\""+HV0342_1+"\" ><input type=\"hidden\" name=\"HV0343_1\" id=\"HV0343_1\" value=\""+HV0343_1+"\">"
              + "<input type=\"hidden\" name=\"HV0344_1\" id=\"HV0344_1\" value=\""+HV0344_1+"\">"
              + "";
          
          
         survival_ART="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.8 Survival and Retention on ART at 12 months </b></legend><table style=\"margin-left:200px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">ART Net cohort at 12 months </td><td style=\"padding-right:40px;\" >HV03-45</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"75\" name=\"HV0345\" id=\"HV0345\" value=\""+HV0345+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0345');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On Original 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-46</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"76\" name=\"HV0346\" id=\"HV0346\" value=\""+HV0346+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0346'); therapyTwelveMonths();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On alternative 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-47</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"77\" name=\"HV0347\" id=\"HV0347\" value=\""+HV0347+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0347'); therapyTwelveMonths();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On 2nd Line (or higher) at 12 months </td><td style=\"padding-right:40px;\" >HV03-48</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"78\" name=\"HV0348\" id=\"HV0348\" value=\""+HV0348+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0348'); therapyTwelveMonths();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On therapy at 12 months (Sum HV03-46 to HV03-48) </td><td style=\"padding-right:40px;\" >HV03-49</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0349\" id=\"HV0349\" value=\""+HV0349+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0349');\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
         
            screening="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.9 Screening </b></legend><table style=\"margin-left:100px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-50(M)</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"79\" name=\"HV0350\" id=\"HV0350\" value=\""+HV0350+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0350'); screening();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-51(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"80\" name=\"HV0351\" id=\"HV0351\" value=\""+HV0351+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0351');screening();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-52(M)</td><td style=\"padding-right:40px;\"><input type=\"text\"  tabindex=\"81\" name=\"HV0352\" id=\"HV0352\" value=\""+HV0352+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0352'); screening();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-53(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"82\" name=\"HV0353\" id=\"HV0353\" value=\""+HV0353+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0353');screening();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-54</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0354\" id=\"HV0354\" value=\""+HV0354+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0354');\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for cervical cancer (F 18+) </td><td style=\"padding-right:40px;\" >HV03-55</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"83\" name=\"HV0355\" id=\"HV0355\" value=\""+HV0355+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0355');\" maxLength=\"6\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
            
            pwp="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.10 Prevention with Postive </b></legend><table style=\"margin-left:200px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Modern Contraceptive methods </td><td style=\"padding-right:40px;\" >HV09-04</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"83\" name=\"HV0904\" id=\"HV0904\" value=\""+HV0904+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0904');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Provided with Condoms </td><td style=\"padding-right:40px;\" >HV09-05</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"84\" name=\"HV0905\" id=\"HV0905\" value=\""+HV0905+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0905');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
            
         HIV_care="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">3.11 HIV Care Visits </b></legend><table style=\"margin-left:200px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Females (18+) </td><td style=\"padding-right:40px;\" >HV03-70</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"85\" name=\"HV0370\" id=\"HV0370\" value=\""+HV0370+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0370');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Scheduled </td><td style=\"padding-right:40px;\" >HV03-71</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"86\" name=\"HV0371\" id=\"HV0371\" value=\""+HV0371+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0371'); HIVCareVisits();\" maxLength=\"4\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unscheduled </td><td style=\"padding-right:40px;\" >HV03-72</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"87\" name=\"HV0372\" id=\"HV0372\" value=\""+HV0372+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0372'); HIVCareVisits();\" maxLength=\"4\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Visits (Sum HV03-71 to HV03-72) </td><td style=\"padding-right:40px;\" >HV03-73</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0373\" id=\"HV0373\" value=\""+HV0373+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0373');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";   
            
         CT+=on_CP+""+enrolled_care+""+currently_care+""+starting_ART+""+revisit_ART+""+
        current_ART+""+cumulative_ART+""+cumulative_ARTPrevious+""+survival_ART+""+screening+""+pwp+""+HIV_care;
         
        number_circumcised=hiv_status=adverse_events;
        
        number_circumcised="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.1  Number Circumcised </b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">0 - 14 </td><td style=\"padding-right:40px;\" >HV04-01</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"88\" name=\"HV0401\" id=\"HV0401\" value=\""+HV0401+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0401'); numberCircumcised();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">14 - 24 </td><td style=\"padding-right:40px;\" >HV04-02</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"89\" name=\"HV0402\" id=\"HV0402\" value=\""+HV0402+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0402'); numberCircumcised();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">25+ </td><td style=\"padding-right:40px;\" >HV04-03</td><td style=\"padding-right:40px;\"><input type=\"text\"   tabindex=\"90\" name=\"HV0403\" id=\"HV0403\" value=\""+HV0403+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0403'); numberCircumcised();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total (Sum HV04-01 to HV04-02) </td><td style=\"padding-right:40px;\" >HV04-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0406\" id=\"HV0406\" value=\""+HV0406+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0406');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
        
        hiv_status="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.2  HIV Status (at circumcision) </b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive </td><td style=\"padding-right:40px;\" >HV04-07</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"91\" name=\"HV0407\" id=\"HV0407\" value=\""+HV0407+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0407');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Negative </td><td style=\"padding-right:40px;\" >HV04-08</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"92\" name=\"HV0408\" id=\"HV0408\" value=\""+HV0408+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0408');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unknown </td><td style=\"padding-right:40px;\" >HV04-09</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"93\" name=\"HV0409\" id=\"HV0409\" value=\""+HV0409+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0409');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
        
        adverse_events="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">4.3  Adverse events (Circumcision) </b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) moderate </td><td style=\"padding-right:40px;\" >HV04-10</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"94\" name=\"HV0410\" id=\"HV0410\" value=\""+HV0410+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0410'); totalAEDuring();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-11</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"95\" name=\"HV0411\" id=\"HV0411\" value=\""+HV0411+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0411'); totalAEDuring();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) moderate  </td><td style=\"padding-right:40px;\" >HV04-12</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"96\" name=\"HV0412\" id=\"HV0412\" value=\""+HV0412+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0412'); totalAEDuring();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-13</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"97\" name=\"HV0413\" id=\"HV0413\" value=\""+HV0413+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0413'); totalAEDuring();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE During (Sum HV04-10  & HV04-11) </td><td style=\"padding-right:40px;\" >HV04-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0414\" id=\"HV0414\" value=\""+HV0414+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0414');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE Post (Sum HV04-12  & HV04-13) </td><td style=\"padding-right:40px;\" >HV04-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0415\" id=\"HV0415\" value=\""+HV0415+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0415');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
        
        VMMC+=number_circumcised+""+hiv_status+""+adverse_events;
         
        type_exposure=provided_p="";
       
        type_exposure="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">5.1 Type of exposure </b></legend><table style=\"margin-left:200px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-01(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"98\" name=\"HV0501\" id=\"HV0501\" value=\""+HV0501+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0501');typeExposure();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-02(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"99\" name=\"HV0502\" id=\"HV0502\" value=\""+HV0502+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0502'); typeExposure();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"100\" name=\"HV0503\" id=\"HV0503\" value=\""+HV0503+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0503');typeExposure();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"101\" name=\"HV0504\" id=\"HV0504\" value=\""+HV0504+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0504'); typeExposure();\" maxLength=\"4\"  onkeypress=\"return numbers(event)\"   style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"102\" name=\"HV0505\" id=\"HV0505\" value=\""+HV0505+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0505');typeExposure();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"103\" name=\"HV0506\" id=\"HV0506\" value=\""+HV0506+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0506'); typeExposure();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total  </td><td style=\"padding-right:40px;\" >HV05-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0507\" id=\"HV0507\" value=\""+HV0507+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0507');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px;  background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
                
           provided_p ="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\">5.2 Provided with Prophylaxis </b></legend><table style=\"margin-left:200px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-08(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"104\" name=\"HV0508\" id=\"HV0508\" value=\""+HV0508+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0508'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-09(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"105\" name=\"HV0509\" id=\"HV0509\" value=\""+HV0509+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0509'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-10(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"106\" name=\"HV0510\" id=\"HV0510\" value=\""+HV0510+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0510'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-11(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"107\" name=\"HV0511\" id=\"HV0511\" value=\""+HV0511+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0511'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-12(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"108\" name=\"HV0512\" id=\"HV0512\" value=\""+HV0512+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0512'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-13(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"109\" name=\"HV0513\" id=\"HV0513\" value=\""+HV0513+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0513'); ProvidedProphylaxis();\" maxLength=\"4\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total PEP  </td><td style=\"padding-right:40px;\" >HV05-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0514\" id=\"HV0514\" value=\""+HV0514+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0514');\" maxLength=\"6\" onkeypress=\"return numbers(event)\" style=\"width: 80px; background-color:#DDDDDD;\" readonly=\"true\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>";
           
           
                
        PEP+=type_exposure+""+provided_p;
        
        blood_safety="";
        
         blood_safety="<fieldset class=\"formatter\"><legend class=\"formatter\"><b style=\"text-align:center;\"> Blood Safety </b></legend><table style=\"margin-left:250px;\"><tr><td colspan=\"3\"><br> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Donated Blood Units </td><td style=\"padding-right:40px;\" >HV06-01</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"110\" name=\"HV0601\" id=\"HV0601\" value=\""+HV0601+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0601');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood Units screened for TTIs </td><td style=\"padding-right:40px;\" >HV06-02</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"111\" name=\"HV0602\" id=\"HV0602\" value=\""+HV0602+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0602');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood units reactive to HIVH </td><td style=\"padding-right:40px;\" >HV06-05</td><td style=\"padding-right:40px;\"><input type=\"text\" tabindex=\"112\" name=\"HV0605\" id=\"HV0605\" value=\""+HV0605+"\"  data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" title=\"\" onblur=\"autosave('HV0605');\" maxLength=\"4\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "<br></fieldset>"; 
        
        Blood+=blood_safety;       
          
          
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
        if(isLocked==0){validationText="Run Validation";buttonColor="blue"; titleText="";}
        else if(isLocked==1){validationText="<b>Data Locked !</b>";buttonColor="red";titleText="&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style=\"color:red;\">Sorry: Data editing has been locked for this health facility, year and month.</b>";}
        else{}
        
        HIV_CT+="</div></div></div>";
        PMTCT+="</div></div></div>";
        CT+="</div></div></div>";
        VMMC+="</div></div></div>";
        PEP+="</div></div></div>";
//        Blood+="</div></div></div>";
      
        data+=""+enterdby+""+titleText+"<br><br><br>"+tabs;
             System.out.println("____"+HIV_CT);
        if(session.getAttribute("forms_holder").toString().contains("HTC,")){data+=HIV_CT;}
        if(session.getAttribute("forms_holder").toString().contains(",PMTCT,")){data+=PMTCT;}
        if(session.getAttribute("forms_holder").toString().contains(",ART,")){data+=CT;}
        if(session.getAttribute("forms_holder").toString().contains(",PEP,")){data+=PEP;}
//        if(session.getAttribute("forms_holder").toString().contains(",Blood_Safety,")){data+=Blood;}
     
        
//        String getForms="SELECT COUNT(id),isValidated FROM moh731 WHERE year='"+year+"' && month='"+month+"' GROUP BY isValidated";
//        conn.rs1=conn.st1.executeQuery(getForms);
//        while(conn.rs1.next()){
//            System.out.println("cont : "+conn.rs1.getString(1)+"   numberv is "+conn.rs.getString(2));
//        }
        
        
//        data=HIV_CT+""+PMTCT+""+CT+""+VMMC+""+PEP+""+Blood+""+checkValidity;
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
     String getUnvalidated="SELECT moh731.SubPartnerID,subpartnera.SubPartnerNom FROM moh731 JOIN subpartnera ON moh731.SubPartnerID=subpartnera.SubPartnerID WHERE subpartnera.DistrictID='"+subcountyid+"' AND moh731.Mois='"+month+"' AND moh731.Annee='"+year+"' AND moh731.isValidated='0'";
     conn.rs=conn.st.executeQuery(getUnvalidated);
     while(conn.rs.next()){
         counter++;
//     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
     unvalidatedLink+="<a href=\"changeFacilitySession?facilityID="+conn.rs.getString(1)+"&&src=Form731.jsp\">"+counter+". "+conn.rs.getString(2)+"</a><br><br>" ;   
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
        Logger.getLogger(load731.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load731.class.getName()).log(Level.SEVERE, null, ex);
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

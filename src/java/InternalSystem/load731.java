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
        current_ART,cumulative_ART,survival_ART,screening,pwp,HIV_care;
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
String HV0601,HV0602,HV0605;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session=request.getSession();
            
          
           data="";
           if(request.getParameter("facilityId")!=null){
           if(request.getParameter("facilityId")!=null){
            facilityId= request.getParameter("facilityId");  
           }
           if(request.getParameter("year")!=null){
            year=request.getParameter("year");
           }
           if(request.getParameter("month")!=null){
             month=request.getParameter("month");
           }
           }
       else   if(session.getAttribute("facilityId")!=null){
           if(request.getParameter("facilityId")==null){
            facilityId=  session.getAttribute("facilityId").toString();
           }
           if(request.getParameter("year")==null){
           year=  session.getAttribute("year").toString();
           }
           if(request.getParameter("month")==null){
           month=session.getAttribute("month").toString();
           }
          }
       else{
          facilityId=year=month=""; 
       }
          id=year+"_"+month+"_"+facilityId;
           
          id="2015_1_14498";
        HIV_CT="<div class=\"tab-pane active\" id=\"tab_1\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4>HIV Counselling and Testing</h4>" +
                              "</div><div class=\"portlet-body form\">";
        
        PMTCT="<div class=\"tab-pane \" id=\"tab_2\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4>Form Sample</h4>" +
                              "</div><div class=\"portlet-body form\">";
        CT="<div class=\"tab-pane \" id=\"tab_3\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4>Form Sample</h4>" +
                              "</div><div class=\"portlet-body form\">";
        VMMC="<div class=\"tab-pane \" id=\"tab_4\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4>Form Sample</h4>" +
                              "</div><div class=\"portlet-body form\">";
        PEP="<div class=\"tab-pane \" id=\"tab_5\"><div class=\"portlet box blue\">" +
                             "<div class=\"portlet-title\"><h4>Form Sample</h4>" +
                              "</div><div class=\"portlet-body form\">";
        Blood="<div class=\"tab-pane \" id=\"tab_6\"><div class=\"portlet box blue\">" +
                              "<div class=\"portlet-title\"><h4>Form Sample</h4>" +
                              "</div><div class=\"portlet-body form\">";
        
           
          String checker="SELECT * FROM moh731 WHERE id=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, id);
          conn.rs=conn.pst.executeQuery();
          
          if(conn.rs.next()==true){
              System.out.println("Data already exist loading............................");
 
 HV0101=conn.rs.getString("HV0101");
 HV0102=conn.rs.getString("HV0102");
 HV0103=conn.rs.getString("HV0103");
 HV0105=conn.rs.getString("HV0105");
 HV0106=conn.rs.getString("HV0106");
 HV0107=conn.rs.getString("HV0107");
 HV0108=conn.rs.getString("HV0108");
 HV0109=conn.rs.getString("HV0109");
 HV0110=conn.rs.getString("HV0110");
 HV0111=conn.rs.getString("HV0111");
 HV0112=conn.rs.getString("HV0112");
 HV0113=conn.rs.getString("HV0113");
 HV0114=conn.rs.getString("HV0114");
 HV0115=conn.rs.getString("HV0115");
 HV0116=conn.rs.getString("HV0116");
 
HV0201=conn.rs.getString("HV0201");
HV0202=conn.rs.getString("HV0202");
HV0203=conn.rs.getString("HV0203");
HV0204=conn.rs.getString("HV0204");
HV0205=conn.rs.getString("HV0205");
HV0206=conn.rs.getString("HV0206");
HV0207=conn.rs.getString("HV0207");
HV0208=conn.rs.getString("HV0208");
HV0209=conn.rs.getString("HV0209");
HV0210=conn.rs.getString("HV0210");
HV0211=conn.rs.getString("HV0211");
HV0212=conn.rs.getString("HV0212");
HV0213=conn.rs.getString("HV0213");
HV0214=conn.rs.getString("HV0214");
HV0215=conn.rs.getString("HV0215");
HV0216=conn.rs.getString("HV0216");
HV0217=conn.rs.getString("HV0217");
HV0218=conn.rs.getString("HV0218");
HV0219=conn.rs.getString("HV0219");
HV0220=conn.rs.getString("HV0220");
HV0221=conn.rs.getString("HV0221");
HV0224=conn.rs.getString("HV0224");
HV0225=conn.rs.getString("HV0225");
HV0226=conn.rs.getString("HV0226");
HV0227=conn.rs.getString("HV0227");
HV0228=conn.rs.getString("HV0228");
HV0229=conn.rs.getString("HV0229");
HV0230=conn.rs.getString("HV0230");
HV0231=conn.rs.getString("HV0231");
HV0232=conn.rs.getString("HV0232");
HV0233=conn.rs.getString("HV0233");
HV0234=conn.rs.getString("HV0234");
HV0235=conn.rs.getString("HV0235");
HV0236=conn.rs.getString("HV0236");
HV0237=conn.rs.getString("HV0237");
HV0238=conn.rs.getString("HV0238");
HV0239=conn.rs.getString("HV0239");
HV0240=conn.rs.getString("HV0240");
HV0241=conn.rs.getString("HV0241");
HV0242=conn.rs.getString("HV0242");
HV0243=conn.rs.getString("HV0243");
HV0244=conn.rs.getString("HV0244");

HV0301=conn.rs.getString("HV0301");
HV0302=conn.rs.getString("HV0302");
HV0303=conn.rs.getString("HV0303");
HV0304=conn.rs.getString("HV0304");
HV0305=conn.rs.getString("HV0305");
HV0306=conn.rs.getString("HV0306");
HV0307=conn.rs.getString("HV0307");
HV0308=conn.rs.getString("HV0308");
HV0309=conn.rs.getString("HV0309");
HV0310=conn.rs.getString("HV0310");
HV0311=conn.rs.getString("HV0311");
HV0312=conn.rs.getString("HV0312");
HV0313=conn.rs.getString("HV0313");
HV0314=conn.rs.getString("HV0314");
HV0315=conn.rs.getString("HV0315");
HV0316=conn.rs.getString("HV0316");
HV0317=conn.rs.getString("HV0317");
HV0318=conn.rs.getString("HV0318");
HV0319=conn.rs.getString("HV0319");
HV0320=conn.rs.getString("HV0320");
HV0321=conn.rs.getString("HV0321");
HV0322=conn.rs.getString("HV0322");
HV0323=conn.rs.getString("HV0323");
HV0324=conn.rs.getString("HV0324");
HV0325=conn.rs.getString("HV0325");
HV0326=conn.rs.getString("HV0326");
HV0327=conn.rs.getString("HV0327");
HV0328=conn.rs.getString("HV0328");
HV0329=conn.rs.getString("HV0329");
HV0330=conn.rs.getString("HV0330");
HV0331=conn.rs.getString("HV0331");
HV0332=conn.rs.getString("HV0332");
HV0333=conn.rs.getString("HV0333");
HV0334=conn.rs.getString("HV0334");
HV0335=conn.rs.getString("HV0335");
HV0336=conn.rs.getString("HV0336");
HV0337=conn.rs.getString("HV0337");
HV0338=conn.rs.getString("HV0338");
HV0339=conn.rs.getString("HV0339");
HV0340=conn.rs.getString("HV0340");
HV0341=conn.rs.getString("HV0341");
HV0342=conn.rs.getString("HV0342");
HV0343=conn.rs.getString("HV0343");
HV0344=conn.rs.getString("HV0344");
HV0345=conn.rs.getString("HV0345");
HV0346=conn.rs.getString("HV0346");
HV0347=conn.rs.getString("HV0347");
HV0348=conn.rs.getString("HV0348");
HV0349=conn.rs.getString("HV0349");
HV0350=conn.rs.getString("HV0350");
HV0351=conn.rs.getString("HV0351");
HV0352=conn.rs.getString("HV0352");
HV0353=conn.rs.getString("HV0353");
HV0354=conn.rs.getString("HV0354");
HV0355=conn.rs.getString("HV0355");
HV0904=conn.rs.getString("HV0904");
HV0905=conn.rs.getString("HV0905");
HV0370=conn.rs.getString("HV0370");
HV0371=conn.rs.getString("HV0371");
HV0372=conn.rs.getString("HV0372");
HV0373=conn.rs.getString("HV0373");

HV0401=conn.rs.getString("HV0401");
HV0402=conn.rs.getString("HV0402");
HV0403=conn.rs.getString("HV0403");
HV0406=conn.rs.getString("HV0406");
HV0407=conn.rs.getString("HV0407");
HV0408=conn.rs.getString("HV0408");
HV0409=conn.rs.getString("HV0409");
HV0410=conn.rs.getString("HV0410");
HV0411=conn.rs.getString("HV0411");
HV0412=conn.rs.getString("HV0412");
HV0413=conn.rs.getString("HV0413");
HV0414=conn.rs.getString("HV0414");
HV0415=conn.rs.getString("HV0415");

HV0501=conn.rs.getString("HV0501");
HV0502=conn.rs.getString("HV0502");
HV0503=conn.rs.getString("HV0503");
HV0504=conn.rs.getString("HV0504");
HV0505=conn.rs.getString("HV0505");
HV0506=conn.rs.getString("HV0506");
HV0507=conn.rs.getString("HV0507");
HV0508=conn.rs.getString("HV0508");
HV0509=conn.rs.getString("HV0509");
HV0510=conn.rs.getString("HV0510");
HV0511=conn.rs.getString("HV0511");
HV0512=conn.rs.getString("HV0512");
HV0513=conn.rs.getString("HV0513");
HV0514=conn.rs.getString("HV0514");

HV0601=conn.rs.getString("HV0601");
HV0602=conn.rs.getString("HV0602");
HV0605=conn.rs.getString("HV0605");

         
   //####################HIV COUNSELLING AND TESTING#######################################
//              ###################################################################
              
       testing=receiving_results=receiving_postive_results="";    
          HIV_CT+="";
      testing="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">1.1 Testing.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">First</td><td style=\"padding-right:40px;\" >HV01-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0101\" id=\"HV0101\" value=\""+HV0101+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Repeat</td><td style=\"padding-right:40px;\" >HV01-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0102\" id=\"HV0102\" value=\""+HV0102+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (HVO0-01 plus HV01-02)</td><td style=\"padding-right:40px;\" >HV01-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0103\" id=\"HV0103\"  value=\""+HV0103+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Couples</td><td style=\"padding-right:40px;\" >HV01-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0105\" id=\"HV0105\" value=\""+HV0105+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Staic [Facility]</td><td style=\"padding-right:40px;\" >HV01-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0106\" id=\"HV0106\" value=\""+HV0106+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Outreach</td><td style=\"padding-right:40px;\" >HV01-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0107\" id=\"HV0107\" value=\""+HV0107+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + ""
              + "";
      
       receiving_results="<tr><td colspan=\"3\"><b style=\"text-align:center;\">1.2 Receiving results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Cocordant couples</td><td style=\"padding-right:40px;\" >HV01-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0108\" id=\"HV0108\" value=\""+HV0108+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV01-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0109\" id=\"HV0109\" value=\""+HV0109+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "</table></td>"
              + "";
       
        receiving_postive_results="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">1.3 Receiving postive results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Males - Below 15 years</td><td style=\"padding-right:40px;\" >HV01-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0110\" id=\"HV0110\" value=\""+HV0110+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females - Below 15 years.</td><td style=\"padding-right:40px;\" >HV01-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0111\" id=\"HV0111\" value=\""+HV0111+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 15-24 years </td><td style=\"padding-right:40px;\" >HV01-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0112\" id=\"HV0112\" value=\""+HV0112+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 15-24 years </td><td style=\"padding-right:40px;\" >HV01-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0113\" id=\"HV0113\" value=\""+HV0113+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 25 years and older</td><td style=\"padding-right:40px;\" >HV01-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0114\" id=\"HV0114\" value=\""+HV0114+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 25 years and older</td><td style=\"padding-right:40px;\" >HV01-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0115\" id=\"HV0115\" value=\""+HV0115+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total receiving postive results <br>(Sum HV01-10 to HV01-15)</td><td style=\"padding-right:40px;\" >HV01-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0116\" id=\"HV0116\" value=\""+HV0116+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
      
             HIV_CT+=testing+""+receiving_results+""+receiving_postive_results+""; 
//              System.out.println("Loading new form for data entry.......................");

//    PMTCT################################################################
//               ##########################################################
       
        testing_for_HIV=HIV_Postive_results=partner_involvement=
        maternal_prophylaxis=assessment_ART=infant_testing=
        confirmed_infant=infant_feeding=infant_ARV="";
           
          testing_for_HIV="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.1 Testing for HIV.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0201\" id=\"HV0201\" value=\""+HV0201+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0202\" id=\"HV0202\" value=\""+HV0202+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0203\" id=\"HV0203\" value=\""+HV0203+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-01 to HV02-03) </td><td style=\"padding-right:40px;\" >HV02-04</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0204\" id=\"HV0204\" value=\""+HV0204+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
             + "</table>"
              + "";
          
       HIV_Postive_results="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.2 HIV Postive Results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Known Postive status(at entry into ANC)</td><td style=\"padding-right:40px;\" >HV02-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0205\" id=\"HV0205\" value=\""+HV0205+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0206\" id=\"HV0206\" value=\""+HV0206+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0207\" id=\"HV0207\" value=\""+HV0207+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0208\" id=\"HV0208\" value=\""+HV0208+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-05 to HV02-08) </td><td style=\"padding-right:40px;\" >HV02-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0209\" id=\"HV0209\" value=\""+HV0209+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total with known status <br> (Sum HV02-04 to HV02-05) </td><td style=\"padding-right:40px;\" >HV02-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0210\" id=\"HV0210\" value=\""+HV0210+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";   
          
          partner_involvement="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.3 Partner Involvement.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Male partners tested (ANC/L&D)</td><td style=\"padding-right:40px;\" >HV02-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0211\" id=\"HV0211\" value=\""+HV0211+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV02-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0212\" id=\"HV0212\" value=\""+HV0212+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
          
        
           maternal_prophylaxis="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.4 Maternal Prophylaxis .</b>(<i>at first contact only</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - NVP only </td><td style=\"padding-right:40px;\" >HV02-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0213\" id=\"HV0213\" value=\""+HV0213+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - (AZT + SdNVP) </td><td style=\"padding-right:40px;\" >HV02-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0214\" id=\"HV0214\" value=\""+HV0214+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis -Interrupted HAART </td><td style=\"padding-right:40px;\" >HV02-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0215\" id=\"HV0215\" value=\""+HV0215+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HAART (ART) </td><td style=\"padding-right:40px;\" >HV02-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0216\" id=\"HV0216\" value=\""+HV0216+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">total PMTCT prophylaxis (Sum HV02-13 to HV02-16) </td><td style=\"padding-right:40px;\" >HV02-17</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0217\" id=\"HV0217\" value=\""+HV0217+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
          assessment_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.5 Assessment for ART Eligibility in MCH .</b>(<i>at diagnosis</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - WHO staging done </td><td style=\"padding-right:40px;\" >HV02-18</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0218\" id=\"HV0218\" value=\""+HV0218+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - CD4 </td><td style=\"padding-right:40px;\" >HV02-19</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0219\" id=\"HV0219\" value=\""+HV0219+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility in ANC (Sum HV02-18 to HV02-19) </td><td style=\"padding-right:40px;\" >HV02-20</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0220\" id=\"HV0220\" value=\""+HV0220+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Started on ART during ANC </td><td style=\"padding-right:40px;\" >HV02-21</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0221\" id=\"HV0221\" value=\""+HV0221+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
           infant_testing="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.6 Infant Testing</b> (<i>Initial tests only</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (within 2 months) </td><td style=\"padding-right:40px;\" >HV02-24</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0224\" id=\"HV0224\" value=\""+HV0224+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (From 3 to 8 months) </td><td style=\"padding-right:40px;\" >HV02-25</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0225\" id=\"HV0225\" value=\""+HV0225+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Serology antibody test (from 9 to 12 months)  </td><td style=\"padding-right:40px;\" >HV02-26</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0226\" id=\"HV0226\" value=\""+HV0226+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (from 9 to 12 months) </td><td style=\"padding-right:40px;\" >HV02-27</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0227\" id=\"HV0227\" value=\""+HV0227+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total HEI Tested by 12 months (Sum HV02-24 to HV02-26) </td><td style=\"padding-right:40px;\" >HV02-28</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0228\" id=\"HV0228\" value=\""+HV0228+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
                   
          confirmed_infant="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.7 Confirmed Infant Test Results</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (within 2 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-29</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV00229\" id=\"HV0229\" value=\""+HV0229+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (From 3 to 8 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-30</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV00230\" id=\"HV0230\" value=\""+HV0230+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive -  (from 9 to 12 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-31</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0231\" id=\"HV0231\" value=\""+HV0231+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Confirmed Postive (Sum HV02-29 to HV02-31) </td><td style=\"padding-right:40px;\" >HV02-32</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0232\" id=\"HV0232\" value=\""+HV0232+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
              
          infant_feeding="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.9 Infant feeding.</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">EBF (at 6 months)</td><td style=\"padding-right:40px;\" >HV02-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0233\" id=\"HV0233\" value=\""+HV0233+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">ERF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-34</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0234\" id=\"HV0234\" value=\""+HV0234+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">MF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-35</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0235\" id=\"HV0235\" value=\""+HV0235+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 6 months. </td><td style=\"padding-right:40px;\" >HV02-36</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0236\" id=\"HV0236\" value=\""+HV0236+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
             + "<tr>"
              + "<td style=\"padding-right:40px;\">BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-67</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0237\" id=\"HV0237\" value=\""+HV0237+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-38</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0238\" id=\"HV0238\" value=\""+HV0238+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not known </td><td style=\"padding-right:40px;\" >HV02-39</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0239\" id=\"HV0239\" value=\""+HV0239+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 12 months (Sum HV02-37 to HV02-39) </td><td style=\"padding-right:40px;\" >HV02-40</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0240\" id=\"HV0240\" value=\""+HV0240+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
           infant_ARV="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.9 Infant ARV Prophlaxis</b> (<i>at first contact only</i>) </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Issued in ANC </td><td style=\"padding-right:40px;\" >HV02-41</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0241\" id=\"HV0241\" value=\""+HV0241+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and Delivery </td><td style=\"padding-right:40px;\" >HV02-42</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0242\" id=\"HV0242\" value=\""+HV0242+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PNC(<72hrs) </td><td style=\"padding-right:40px;\" >HV02-43</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0243\" id=\"HV0243\" value=\""+HV0243+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Infants Issued Prophylaxis (Sum HV02-41 to HV02-43) </td><td style=\"padding-right:40px;\" >HV02-32</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0244\" id=\"HV0244\" value=\""+HV0244+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
  //    ###############################CARE AND TREATMENT #################################
//               ##########################################################         
           
          PMTCT+=testing_for_HIV+""+HIV_Postive_results+""+partner_involvement+""+
        maternal_prophylaxis+""+assessment_ART+""+infant_testing+""+
        confirmed_infant+""+infant_feeding+""+infant_ARV;
          
         on_CP=enrolled_care=currently_care=starting_ART=revisit_ART=
        current_ART=cumulative_ART=survival_ART=screening=pwp=HIV_care="";
         
         on_CP="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.1 On Cotrimoxazole Prophylaxis</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed Infant (within 2 months) </td><td style=\"padding-right:40px;\" >HV03-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0301\" id=\"HV0301\" value=\""+HV0301+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed infant (Eligible for CTX at 2 months) </td><td style=\"padding-right:40px;\" >HV03-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0302\" id=\"HV0302\" value=\""+HV0302+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - below 15 years</td><td style=\"padding-right:40px;\" >HV03-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0303\" id=\"HV0303\" value=\""+HV0303+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0304\" id=\"HV0304\" value=\""+HV0304+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & older </td><td style=\"padding-right:40px;\" >HV03-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0305\" id=\"HV0305\" value=\""+HV0305+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0306\" id=\"HV0306\" value=\""+HV0306+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & olderTotal On CTX (Sum HV03-03 to HV03-06) </td><td style=\"padding-right:40px;\" >HV03-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0307\" id=\"HV0307\" value=\""+HV0307+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         enrolled_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.2 Enrolled in Care</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0308\" id=\"HV0308\" value=\""+HV0308+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-09(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0309\" id=\"HV0309\" value=\""+HV0309+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-10(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0310\" id=\"HV0310\" value=\""+HV0310+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-11(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0311\" id=\"HV0311\" value=\""+HV0311+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-12(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0312\" id=\"HV0312\" value=\""+HV0312+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" id=\"HV0313\" value=\""+HV0313+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         currently_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.3 Currently in Care</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0314\" id=\"HV0314\" value=\""+HV0314+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-15(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0315\" id=\"HV0315\" value=\""+HV0315+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-16(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0316\" id=\"HV0316\" value=\""+HV0316+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-17(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0317\" id=\"HV0317\" value=\""+HV0317+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-18(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0318\" id=\"HV0318\" value=\""+HV0318+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-19</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" id=\"HV0313\" value=\""+HV0313+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         starting_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.4 Starting ART</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-20</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0320\" id=\"HV0320\" value=\""+HV0320+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-21(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0321\" id=\"HV0321\" value=\""+HV0321+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-22(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0322\" id=\"HV0322\" value=\""+HV0322+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-23(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0323\" id=\"HV0323\" value=\""+HV0323+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-24(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0324\" id=\"HV0324\" value=\""+HV0324+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Total (Sum HV03-21 to HV03-24) </td><td style=\"padding-right:40px;\" >HV03-25</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" maxlength=\"10\" id=\"HV0313\" value=\""+HV0313+"\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - Pregnant </td><td style=\"padding-right:40px;\" >HV03-26</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0326\" id=\"HV0326\" value=\""+HV0326+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  "
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - TB Patient </td><td style=\"padding-right:40px;\" >HV03-27</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0327\" id=\"HV0327\" value=\""+HV0327+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> "
              + "</tr>"
              + "</table>"
              + "";
         
         
         revisit_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.5 Revisits on ART</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-28</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0328\" id=\"HV0328\" value=\""+HV0328+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-29(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0329\" id=\"HV0329\" value=\""+HV0329+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-30(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0330\" id=\"HV0330\" value=\""+HV0330+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-31(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0331\" id=\"HV0331\" value=\""+HV0331+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-32(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0332\" id=\"HV0332\" value=\""+HV0332+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Total (Sum HV03-29 to HV03-32) </td><td style=\"padding-right:40px;\" >HV03-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0333\" id=\"HV0333\" value=\""+HV0333+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
          current_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.6 Currently on ART [All]</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-34</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0334\" id=\"HV0334\" value=\""+HV0334+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-35(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0335\" id=\"HV0335\" value=\""+HV0335+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-36(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0336\" id=\"HV0336\" value=\""+HV0336+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-37(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0337\" id=\"HV0337\" value=\""+HV0337+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-38(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0338\" id=\"HV0338\" value=\""+HV0338+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-39</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0339\" id=\"HV0339\" value=\""+HV0339+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
         cumulative_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.7 Cumullative ever on ART </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-40(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0340\" id=\"HV0340\" value=\""+HV0340+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-41(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0341\" id=\"HV0341\" value=\""+HV0341+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-42(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0342\" id=\"HV0342\" value=\""+HV0342+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-43(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0343\" id=\"HV0343\" value=\""+HV0343+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-44</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0344\" id=\"HV0344\" value=\""+HV0344+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
          
         survival_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.8 Survival and Retention on ART at 12 months </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">ART Net cohort at 12 months </td><td style=\"padding-right:40px;\" >HV03-45</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0345\" id=\"HV0345\" value=\""+HV0345+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On Original 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-46</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0346\" id=\"HV0346\" value=\""+HV0346+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On alternative 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-47</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0347\" id=\"HV0347\" value=\""+HV0347+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On 2nd Line (or higher) at 12 months </td><td style=\"padding-right:40px;\" >HV03-48</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0348\" id=\"HV0348\" value=\""+HV0348+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On therapy at 12 months (Sum HV03-46 to HV03-48) </td><td style=\"padding-right:40px;\" >HV03-49</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0349\" id=\"HV0349\" value=\""+HV0349+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
         
            screening="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.9 Screening </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-50(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0350\" id=\"HV0350\" value=\""+HV0350+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-51(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0351\" id=\"HV0351\" value=\""+HV0351+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-52(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0352\" id=\"HV0352\" value=\""+HV0352+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-53(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0353\" id=\"HV0353\" value=\""+HV0353+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-54</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0354\" id=\"HV0354\" value=\""+HV0354+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
            pwp="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.10 Prevention with Postive </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Modern Contraceptive methods </td><td style=\"padding-right:40px;\" >HV09-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0904\" id=\"HV0904\" value=\""+HV0904+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Provided with Condoms </td><td style=\"padding-right:40px;\" >HV09-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0905\" id=\"HV0905\" value=\""+HV0905+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
         HIV_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.11 HIV Care Visits </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Females (18+) </td><td style=\"padding-right:40px;\" >HV03-70</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0370\" id=\"HV0370\" value=\""+HV0370+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Scheduled </td><td style=\"padding-right:40px;\" >HV03-71</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0371\" id=\"HV0371\" value=\""+HV0371+"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unscheduled </td><td style=\"padding-right:40px;\" >HV03-72</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0372\" id=\"HV0372\" value=\""+HV0372+"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Visits (Sum HV03-71 to HV03-72) </td><td style=\"padding-right:40px;\" >HV03-73</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0373\" id=\"HV0373\" value=\""+HV0373+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";   
            
         CT+=on_CP+""+enrolled_care+""+currently_care+""+starting_ART+""+revisit_ART+""+
        current_ART+""+cumulative_ART+""+survival_ART+""+screening+""+pwp+""+HIV_care;
         
        number_circumcised=hiv_status=adverse_events;
        
        number_circumcised="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.1  Nuber Circumcised </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">0 - 14 </td><td style=\"padding-right:40px;\" >HV04-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0401\" id=\"HV0401\" value=\""+HV0401+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">14 - 24 </td><td style=\"padding-right:40px;\" >HV04-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0402\" id=\"HV0402\" value=\""+HV0402+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">25+ </td><td style=\"padding-right:40px;\" >HV04-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0403\" id=\"HV0403\" value=\""+HV0403+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total (Sum HV04-01 to HV04-02) </td><td style=\"padding-right:40px;\" >HV04-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0406\" id=\"HV0406\" value=\""+HV0406+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        hiv_status="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.2  HIV Status (at circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive </td><td style=\"padding-right:40px;\" >HV04-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0407\" id=\"HV0407\" value=\""+HV0407+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Negative </td><td style=\"padding-right:40px;\" >HV04-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0408\" id=\"HV0408\" value=\""+HV0408+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unknown </td><td style=\"padding-right:40px;\" >HV04-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0409\" id=\"HV0409\" value=\""+HV0409+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        adverse_events="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.3  Adverse events (Circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) moderate </td><td style=\"padding-right:40px;\" >HV04-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0410\" id=\"HV0410\" value=\""+HV0410+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0411\" id=\"HV0411\" value=\""+HV0411+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) moderate  </td><td style=\"padding-right:40px;\" >HV04-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0412\" id=\"HV0412\" value=\""+HV0412+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0413\" id=\"HV0413\" value=\""+HV0413+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE During (Sum HV04-10  & HV04-11) </td><td style=\"padding-right:40px;\" >HV04-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0414\" id=\"HV0414\" value=\""+HV0414+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE Post (Sum HV04-12  & HV04-13) </td><td style=\"padding-right:40px;\" >HV04-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0415\" id=\"HV0415\" value=\""+HV0415+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
        
        VMMC+=number_circumcised+""+hiv_status+""+adverse_events;
         
        type_exposure=provided_p="";
       
        type_exposure="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">5.1 Type of exposure </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-01(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0501\" id=\"HV0501\" value=\""+HV0501+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-02(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0502\" id=\"HV0502\" value=\""+HV0502+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0503\" id=\"HV0503\" value=\""+HV0503+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0504\" id=\"HV0504\" value=\""+HV0504+"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0505\" id=\"HV0505\" value=\""+HV0505+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0506\" id=\"HV0506\" value=\""+HV0506+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total  </td><td style=\"padding-right:40px;\" >HV05-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0507\" id=\"HV0507\" value=\""+HV0507+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
                
           provided_p ="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">5.2 Provided with Prophylaxis </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-08(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0508\" id=\"HV0508\" value=\""+HV0508+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-09(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0509\" id=\"HV0509\" value=\""+HV0509+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-10(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0510\" id=\"HV0510\" value=\""+HV0510+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-11(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0511\" id=\"HV0511\" value=\""+HV0511+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-12(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0512\" id=\"HV0512\" value=\""+HV0512+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-13(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0513\" id=\"HV0513\" value=\""+HV0513+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total PEP  </td><td style=\"padding-right:40px;\" >HV05-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0514\" id=\"HV0514\" value=\""+HV0514+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
           
                
        PEP+=type_exposure+""+provided_p;
        
        blood_safety="";
        
         blood_safety="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\"> Blood Safety </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Donated Blood Units </td><td style=\"padding-right:40px;\" >HV06-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0601\" id=\"HV0601\" value=\""+HV0601+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood Units screened for TTIs </td><td style=\"padding-right:40px;\" >HV06-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0602\" id=\"HV0602\" value=\""+HV0602+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood units reactive to HIVH </td><td style=\"padding-right:40px;\" >HV06-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0605\" id=\"HV0605\" value=\""+HV0605+"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        Blood+=blood_safety;       
          
          
          }
          else{
//####################HIV COUNSELLING AND TESTING#######################################
//              ###################################################################
              
       testing=receiving_results=receiving_postive_results="";    
          HIV_CT+="";
      testing="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">1.1 Testing.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">First</td><td style=\"padding-right:40px;\" >HV01-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0101\" id=\"HV0101\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Repeat</td><td style=\"padding-right:40px;\" >HV01-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0102\" id=\"HV0102\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (HVO0-01 plus HV01-02)</td><td style=\"padding-right:40px;\" >HV01-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0103\" id=\"HV0103\"  value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Couples</td><td style=\"padding-right:40px;\" >HV01-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0105\" id=\"HV0105\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Staic [Facility]</td><td style=\"padding-right:40px;\" >HV01-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0106\" id=\"HV0106\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Outreach</td><td style=\"padding-right:40px;\" >HV01-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0107\" id=\"HV0107\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + ""
              + "";
      
       receiving_results="<tr><td colspan=\"3\"><b style=\"text-align:center;\">1.2 Receiving results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Cocordant couples</td><td style=\"padding-right:40px;\" >HV01-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0108\" id=\"HV0108\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV01-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0109\" id=\"HV0109\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "</table></td>"
              + "";
       
        receiving_postive_results="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">1.3 Receiving postive results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Males - Below 15 years</td><td style=\"padding-right:40px;\" >HV01-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0110\" id=\"HV0110\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females - Below 15 years.</td><td style=\"padding-right:40px;\" >HV01-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0111\" id=\"HV0111\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 15-24 years </td><td style=\"padding-right:40px;\" >HV01-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0112\" id=\"HV0112\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 15-24 years </td><td style=\"padding-right:40px;\" >HV01-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0113\" id=\"HV0113\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 25 years and older</td><td style=\"padding-right:40px;\" >HV01-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0114\" id=\"HV0114\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 25 years and older</td><td style=\"padding-right:40px;\" >HV01-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0115\" id=\"HV0115\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total receiving postive results <br>(Sum HV01-10 to HV01-15)</td><td style=\"padding-right:40px;\" >HV01-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0116\" id=\"HV0116\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
      
             HIV_CT+=testing+""+receiving_results+""+receiving_postive_results+""; 
//              System.out.println("Loading new form for data entry.......................");

//    PMTCT################################################################
//               ##########################################################
       
        testing_for_HIV=HIV_Postive_results=partner_involvement=
        maternal_prophylaxis=assessment_ART=infant_testing=
        confirmed_infant=infant_feeding=infant_ARV="";
           
          testing_for_HIV="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.1 Testing for HIV.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0201\" id=\"HV0201\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0202\" id=\"HV0202\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0203\" id=\"HV0203\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-01 to HV02-03) </td><td style=\"padding-right:40px;\" >HV02-04</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0204\" id=\"HV0204\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
             + "</table>"
              + "";
          
       HIV_Postive_results="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.2 HIV Postive Results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Known Postive status(at entry into ANC)</td><td style=\"padding-right:40px;\" >HV02-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0205\" id=\"HV0205\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Antenatal</td><td style=\"padding-right:40px;\" >HV02-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0206\" id=\"HV0206\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and delivery.</td><td style=\"padding-right:40px;\" >HV02-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0207\" id=\"HV0207\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postnatal (within 72 hrs) </td><td style=\"padding-right:40px;\" >HV02-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0208\" id=\"HV0208\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (PMTCT)<br> (Sum HV02-05 to HV02-08) </td><td style=\"padding-right:40px;\" >HV02-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0209\" id=\"HV0209\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total with known status <br> (Sum HV02-04 to HV02-05) </td><td style=\"padding-right:40px;\" >HV02-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0210\" id=\"HV0210\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";   
          
          partner_involvement="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.3 Partner Involvement.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Male partners tested (ANC/L&D)</td><td style=\"padding-right:40px;\" >HV02-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0211\" id=\"HV0211\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV02-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0212\" id=\"HV0212\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
          
        
           maternal_prophylaxis="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.4 Maternal Prophylaxis .</b>(<i>at first contact only</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - NVP only </td><td style=\"padding-right:40px;\" >HV02-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0213\" id=\"HV0213\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis - (AZT + SdNVP) </td><td style=\"padding-right:40px;\" >HV02-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0214\" id=\"HV0214\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Prophylaxis -Interrupted HAART </td><td style=\"padding-right:40px;\" >HV02-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0215\" id=\"HV0215\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HAART (ART) </td><td style=\"padding-right:40px;\" >HV02-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0216\" id=\"HV0216\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">total PMTCT prophylaxis (Sum HV02-13 to HV02-16) </td><td style=\"padding-right:40px;\" >HV02-17</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0217\" id=\"HV0217\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
          assessment_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.5 Assessment for ART Eligibility in MCH .</b>(<i>at diagnosis</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - WHO staging done </td><td style=\"padding-right:40px;\" >HV02-18</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0218\" id=\"HV0218\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility at 1st ANC - CD4 </td><td style=\"padding-right:40px;\" >HV02-19</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0219\" id=\"HV0219\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Assessed for eligibility in ANC (Sum HV02-18 to HV02-19) </td><td style=\"padding-right:40px;\" >HV02-20</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0220\" id=\"HV0220\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Started on ART during ANC </td><td style=\"padding-right:40px;\" >HV02-21</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0221\" id=\"HV0221\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
           infant_testing="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.6 Infant Testing</b> (<i>Initial tests only</i>)</td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (within 2 months) </td><td style=\"padding-right:40px;\" >HV02-24</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0224\" id=\"HV0224\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (From 3 to 8 months) </td><td style=\"padding-right:40px;\" >HV02-25</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0225\" id=\"HV0225\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Serology antibody test (from 9 to 12 months)  </td><td style=\"padding-right:40px;\" >HV02-26</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0226\" id=\"HV0226\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PCR (from 9 to 12 months) </td><td style=\"padding-right:40px;\" >HV02-27</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0227\" id=\"HV0227\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total HEI Tested by 12 months (Sum HV02-24 to HV02-26) </td><td style=\"padding-right:40px;\" >HV02-28</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0228\" id=\"HV0228\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
                   
          confirmed_infant="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.7 Confirmed Infant Test Results</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (within 2 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-29</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV00229\" id=\"HV0229\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive - (From 3 to 8 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-30</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV00230\" id=\"HV0230\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive -  (from 9 to 12 months) -PCR </td><td style=\"padding-right:40px;\" >HV02-31</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0231\" id=\"HV0231\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Confirmed Postive (Sum HV02-29 to HV02-31) </td><td style=\"padding-right:40px;\" >HV02-32</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0232\" id=\"HV0232\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
              
          infant_feeding="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.9 Infant feeding.</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">EBF (at 6 months)</td><td style=\"padding-right:40px;\" >HV02-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0233\" id=\"HV0233\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">ERF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-34</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0234\" id=\"HV0234\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">MF (at 6 months) </td><td style=\"padding-right:40px;\" >HV02-35</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0235\" id=\"HV0235\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 6 months. </td><td style=\"padding-right:40px;\" >HV02-36</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0236\" id=\"HV0236\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
             + "<tr>"
              + "<td style=\"padding-right:40px;\">BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-67</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0237\" id=\"HV0237\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not BF (12 months) </td><td style=\"padding-right:40px;\" >HV02-38</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0238\" id=\"HV0238\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Not known </td><td style=\"padding-right:40px;\" >HV02-39</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0239\" id=\"HV0239\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total exposed aged 12 months (Sum HV02-37 to HV02-39) </td><td style=\"padding-right:40px;\" >HV02-40</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0240\" id=\"HV0240\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
           infant_ARV="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">2.9 Infant ARV Prophlaxis</b> (<i>at first contact only</i>) </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Issued in ANC </td><td style=\"padding-right:40px;\" >HV02-41</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0241\" id=\"HV0241\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Labour and Delivery </td><td style=\"padding-right:40px;\" >HV02-42</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0242\" id=\"HV0242\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">PNC(<72hrs) </td><td style=\"padding-right:40px;\" >HV02-43</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0243\" id=\"HV0243\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Infants Issued Prophylaxis (Sum HV02-41 to HV02-43) </td><td style=\"padding-right:40px;\" >HV02-32</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0244\" id=\"HV0244\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
  //    ###############################CARE AND TREATMENT #################################
//               ##########################################################         
           
          PMTCT+=testing_for_HIV+""+HIV_Postive_results+""+partner_involvement+""+
        maternal_prophylaxis+""+assessment_ART+""+infant_testing+""+
        confirmed_infant+""+infant_feeding+""+infant_ARV;
          
         on_CP=enrolled_care=currently_care=starting_ART=revisit_ART=
        current_ART=cumulative_ART=survival_ART=screening=pwp=HIV_care="";
         
         on_CP="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.1 On Cotrimoxazole Prophylaxis</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed Infant (within 2 months) </td><td style=\"padding-right:40px;\" >HV03-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0301\" id=\"HV0301\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">HIV Exposed infant (Eligible for CTX at 2 months) </td><td style=\"padding-right:40px;\" >HV03-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0302\" id=\"HV0302\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - below 15 years</td><td style=\"padding-right:40px;\" >HV03-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0303\" id=\"HV0303\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0304\" id=\"HV0304\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & older </td><td style=\"padding-right:40px;\" >HV03-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0305\" id=\"HV0305\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0306\" id=\"HV0306\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On CTX - 15 years & olderTotal On CTX (Sum HV03-03 to HV03-06) </td><td style=\"padding-right:40px;\" >HV03-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0307\" id=\"HV0307\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         enrolled_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.2 Enrolled in Care</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0308\" id=\"HV0308\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-09(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0309\" id=\"HV0309\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-10(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0310\" id=\"HV0310\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-11(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0311\" id=\"HV0311\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-12(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0312\" id=\"HV0312\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Enrolled in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" id=\"HV0313\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         currently_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.3 Currently in Care</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0314\" id=\"HV0314\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-15(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0315\" id=\"HV0315\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-16(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0316\" id=\"HV0316\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-17(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0317\" id=\"HV0317\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-18(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0318\" id=\"HV0318\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently in care - Total (Sum HV03-09 to HV03-12) </td><td style=\"padding-right:40px;\" >HV03-19</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" id=\"HV0313\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
         starting_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.4 Starting ART</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-20</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0320\" id=\"HV0320\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-21(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0321\" id=\"HV0321\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-22(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0322\" id=\"HV0322\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-23(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0323\" id=\"HV0323\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-24(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0324\" id=\"HV0324\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting ART - Total (Sum HV03-21 to HV03-24) </td><td style=\"padding-right:40px;\" >HV03-25</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0313\" maxlength=\"10\" id=\"HV0313\" value=\"\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - Pregnant </td><td style=\"padding-right:40px;\" >HV03-26</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0326\" id=\"HV0326\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  "
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Starting - TB Patient </td><td style=\"padding-right:40px;\" >HV03-27</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0327\" id=\"HV0327\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> "
              + "</tr>"
              + "</table>"
              + "";
         
         
         revisit_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.5 Revisits on ART</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-28</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0328\" id=\"HV0328\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-29(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0329\" id=\"HV0329\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-30(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0330\" id=\"HV0330\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-31(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0331\" id=\"HV0331\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-32(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0332\" id=\"HV0332\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Revisits on ART - Total (Sum HV03-29 to HV03-32) </td><td style=\"padding-right:40px;\" >HV03-33</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0333\" id=\"HV0333\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
         
          current_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.6 Currently on ART [All]</b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 1 year </td><td style=\"padding-right:40px;\" >HV03-34</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0334\" id=\"HV0334\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-35(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0335\" id=\"HV0335\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-36(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0336\" id=\"HV0336\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-37(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0337\" id=\"HV0337\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-38(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0338\" id=\"HV0338\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Currently on ART - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-39</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0339\" id=\"HV0339\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
          
         cumulative_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.7 Cumullative ever on ART </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-40(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0340\" id=\"HV0340\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-41(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0341\" id=\"HV0341\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-42(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0342\" id=\"HV0342\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-43(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0343\" id=\"HV0343\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Ever on ART - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-44</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0344\" id=\"HV0344\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
          
         survival_ART="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.8 Survival and Retention on ART at 12 months </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">ART Net cohort at 12 months </td><td style=\"padding-right:40px;\" >HV03-45</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0345\" id=\"HV0345\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">On Original 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-46</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0346\" id=\"HV0346\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On alternative 1st Line at 12 months </td><td style=\"padding-right:40px;\" >HV03-47</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0347\" id=\"HV0347\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On 2nd Line (or higher) at 12 months </td><td style=\"padding-right:40px;\" >HV03-48</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0348\" id=\"HV0348\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">On therapy at 12 months (Sum HV03-46 to HV03-48) </td><td style=\"padding-right:40px;\" >HV03-49</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0349\" id=\"HV0349\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
         
            screening="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.9 Screening </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Below 15 year </td><td style=\"padding-right:40px;\" >HV03-50(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0350\" id=\"HV0350\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV03-51(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0351\" id=\"HV0351\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - 15 years and older </td><td style=\"padding-right:40px;\" >HV03-52(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0352\" id=\"HV0352\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV03-53(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0353\" id=\"HV0353\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Screened for TB - Total (Sum HV03-35 to HV03-38) </td><td style=\"padding-right:40px;\" >HV03-54</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0354\" id=\"HV0354\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
            pwp="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.10 Prevention with Postive </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Modern Contraceptive methods </td><td style=\"padding-right:40px;\" >HV09-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0904\" id=\"HV0904\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Provided with Condoms </td><td style=\"padding-right:40px;\" >HV09-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0905\" id=\"HV0905\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
            
         HIV_care="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">3.11 HIV Care Visits </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Females (18+) </td><td style=\"padding-right:40px;\" >HV03-70</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0370\" id=\"HV0370\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Scheduled </td><td style=\"padding-right:40px;\" >HV03-71</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0371\" id=\"HV0371\" value=\"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unscheduled </td><td style=\"padding-right:40px;\" >HV03-72</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0372\" id=\"HV0372\" value=\"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Visits (Sum HV03-71 to HV03-72) </td><td style=\"padding-right:40px;\" >HV03-73</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0373\" id=\"HV0373\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";   
            
         CT+=on_CP+""+enrolled_care+""+currently_care+""+starting_ART+""+revisit_ART+""+
        current_ART+""+cumulative_ART+""+survival_ART+""+screening+""+pwp+""+HIV_care;
         
        number_circumcised=hiv_status=adverse_events;
        
        number_circumcised="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.1  Nuber Circumcised </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">0 - 14 </td><td style=\"padding-right:40px;\" >HV04-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0401\" id=\"HV0401\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">14 - 24 </td><td style=\"padding-right:40px;\" >HV04-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0402\" id=\"HV00402\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">25+ </td><td style=\"padding-right:40px;\" >HV04-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV00403\" id=\"HV00403\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total (Sum HV04-01 to HV04-02) </td><td style=\"padding-right:40px;\" >HV04-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0406\" id=\"HV0406\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        hiv_status="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.2  HIV Status (at circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Postive </td><td style=\"padding-right:40px;\" >HV04-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0407\" id=\"HV0407\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Negative </td><td style=\"padding-right:40px;\" >HV04-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0408\" id=\"HV00408\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Unknown </td><td style=\"padding-right:40px;\" >HV04-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0409\" id=\"HV0409\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        adverse_events="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">4.3  Adverse events (Circumcision) </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) moderate </td><td style=\"padding-right:40px;\" >HV04-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0410\" id=\"HV0410\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">During AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0411\" id=\"HV00411\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) moderate  </td><td style=\"padding-right:40px;\" >HV04-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0412\" id=\"HV0412\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Post AE(s) severe  </td><td style=\"padding-right:40px;\" >HV04-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0413\" id=\"HV0413\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE During (Sum HV04-10  & HV04-11) </td><td style=\"padding-right:40px;\" >HV04-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0414\" id=\"HV00414\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total AE Post (Sum HV04-12  & HV04-13) </td><td style=\"padding-right:40px;\" >HV04-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0415\" id=\"HV0415\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
        
        VMMC+=number_circumcised+""+hiv_status+""+adverse_events;
         
        type_exposure=provided_p="";
       
        type_exposure="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">5.1 Type of exposure </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-01(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0501\" id=\"HV0501\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-02(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0502\" id=\"HV0502\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-03(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0503\" id=\"HV0503\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-04(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0504\" id=\"HV0504\" value=\"\" maxlength=\"10\"  onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-05(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0505\" id=\"HV0505\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-06(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0506\" id=\"HV0506\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total  </td><td style=\"padding-right:40px;\" >HV05-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0507\" id=\"HV0507\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
                
           provided_p ="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\">5.2 Provided with Prophylaxis </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Occupational </td><td style=\"padding-right:40px;\" >HV05-08(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0508\" id=\"HV0508\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-09(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0509\" id=\"HV0509\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Sexual assault </td><td style=\"padding-right:40px;\" >HV05-10(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0510\" id=\"HV0510\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>  <td style=\"padding-right:40px;\" >HV05-11(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0511\" id=\"HV0511\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Other reasons </td><td style=\"padding-right:40px;\" >HV05-12(M)</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0512\" id=\"HV0512\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td> <td style=\"padding-right:40px;\" >HV05-13(F)</td>  <td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0513\" id=\"HV0513\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\"  style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total PEP  </td><td style=\"padding-right:40px;\" >HV05-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0514\" id=\"HV0514\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + "";
           
           
                
        PEP+=type_exposure+""+provided_p;
        
        blood_safety="";
        
         blood_safety="<table><tr><td colspan=\"3\"><b style=\"text-align:center;\"> Blood Safety </b> </td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Donated Blood Units </td><td style=\"padding-right:40px;\" >HV06-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0407\" id=\"HV0407\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood Units screened for TTIs </td><td style=\"padding-right:40px;\" >HV06-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0408\" id=\"HV00408\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Blood units reactive to HIVH </td><td style=\"padding-right:40px;\" >HV06-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0409\" id=\"HV0409\" value=\"\" maxlength=\"10\" onkeypress=\"return numbers(event)\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "</table>"
              + ""; 
        
        Blood+=blood_safety;
          }
          
          
          HIV_CT+="</div></div></div>";
        PMTCT+="</div></div></div>";
        CT+="</div></div></div>";
        VMMC+="</div></div></div>";
        PEP+="</div></div></div>";
        Blood+="</div></div></div>";
        
        data=HIV_CT+""+PMTCT+""+CT+""+VMMC+""+PEP+""+Blood;
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

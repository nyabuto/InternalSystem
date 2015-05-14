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
String data;
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
        HV0354,HV0355,HV0904,HV0906,HV0370,HV0371,HV0372,HV0373;
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
          
          String checker="SELECT * FROM moh731 WHERE SubPartnerID=? && Mois=? && Annee=?" ;
          conn.pst=conn.conn.prepareStatement(checker);
          conn.pst.setString(1, facilityId);
          conn.pst.setString(2, month);
          conn.pst.setString(3, year);
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
HV0906=conn.rs.getString("HV0906");
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

          }
          else{
       testing=receiving_results=receiving_postive_results="";    
          HIV_CT="<table>";
      testing="<tr><td colspan=\"3\"><b style=\"text-align:center;\">1.1 Testing.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">First</td><td style=\"padding-right:40px;\" >HV01-01</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0101\" id=\"HV0101\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Repeat</td><td style=\"padding-right:40px;\" >HV01-02</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0102\" id=\"HV0102\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Total Tested (HVO0-01 plus HV01-02)</td><td style=\"padding-right:40px;\" >HV01-03</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0103\" id=\"HV0103\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Couples</td><td style=\"padding-right:40px;\" >HV01-05</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0105\" id=\"HV0105\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Staic [Facility]</td><td style=\"padding-right:40px;\" >HV01-06</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0106\" id=\"HV0106\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Outreach</td><td style=\"padding-right:40px;\" >HV01-07</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0107\" id=\"HV0107\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
              
              + ""
              + "";
      
       receiving_results="<tr><td colspan=\"3\"><b style=\"text-align:center;\">1.2 Receiving results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Cocordant couples</td><td style=\"padding-right:40px;\" >HV01-08</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0108\" id=\"HV0108\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Discordant couples</td><td style=\"padding-right:40px;\" >HV01-09</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0109\" id=\"HV0109\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
             + ""
              + "";
       
        receiving_postive_results="<tr><td colspan=\"3\"><b style=\"text-align:center;\">1.3 Receiving postive results.</b></td></tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Males - Below 15 years</td><td style=\"padding-right:40px;\" >HV01-10</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0110\" id=\"HV0110\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females - Below 15 years.</td><td style=\"padding-right:40px;\" >HV01-11</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0111\" id=\"HV0111\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 15-24 years </td><td style=\"padding-right:40px;\" >HV01-12</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0112\" id=\"HV0112\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 15-24 years </td><td style=\"padding-right:40px;\" >HV01-13</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0113\" id=\"HV0113\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Males 25 years and older</td><td style=\"padding-right:40px;\" >HV01-14</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0114\" id=\"HV0114\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
               + "<tr>"
              + "<td style=\"padding-right:40px;\">Females 25 years and older</td><td style=\"padding-right:40px;\" >HV01-15</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0115\" id=\"HV0115\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + "<tr>"
              + "<td style=\"padding-right:40px;\">Total receiving postive results (Sum HV01-10 to HV01-15)</td><td style=\"padding-right:40px;\" >HV01-16</td><td style=\"padding-right:40px;\"><input type=\"text\" name=\"HV0116\" id=\"HV0116\" value=\"\" style=\"width: 80px;\"></td>"
              + "</tr>"
              + ""
              + "";
      
             HIV_CT+=testing+"</table>"; 
              System.out.println("Loading new form for data entry.......................");
          }
            out.println(HIV_CT);
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

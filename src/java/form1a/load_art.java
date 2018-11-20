/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
public class load_art extends HttpServlet {
HttpSession session;
String output,lock,enterdby,validity,Header;
String newART,currentART;
String year,month,yearmonth,isLocked;
String database_name="fas_art";
String columns[] = {"m_uk","f_uk","m_1","f_1","m_4","f_4","m_9","f_9","m_14","f_14","m_19","f_19","m_24","f_24","m_29","f_29","m_34","f_34","m_39","f_39","m_44","f_44","m_49","f_49","m_50","f_50"};
String indicator_ids[][] = {{"121","122","123","124"},{"1"}};
String indicators[][] = {{"Starting ART","Breastfeeding at initiation of ART","Currently on ART (All)","TB screening & presumed TB"},{"Am done"}};
String main_indicator[]={"HIV Treatment & TB screening","Testing"};
String main_indic_rowspan[] = {"4","1"};
int indic_pos=0,main_indic_pos=0,max_length=5;
            String facil = "";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
       try {
           dbConn conn = new dbConn();
           session = request.getSession();
           //   tableid="2018_10_331";
        lock="";

            validity="<b><font color='#4b8df8'>New Entry</font></b>";

//            if (session.getAttribute("year") != null) {
//                year = session.getAttribute("year").toString();
//            }
//            if (session.getAttribute("monthid") != null) {
//                month = session.getAttribute("monthid").toString();
//            }
//
//            if (session.getAttribute("facilityid") != null) {
//                facil = session.getAttribute("facilityid").toString();
//            }
          year="2018";
          month="10";
          facil = "383";
            
            
            
            
int ARTdone=0;
int ARTundone=0;
int ARTvalid=0;
int facilssupporting=0;
String distid="";

if(session.getAttribute("subcountyid")!=null){
distid=session.getAttribute("subcountyid").toString();
}
     yearmonth="";
        String tempmonth=month;
        int pepfaryear=Integer.parseInt(year);
        if(Integer.parseInt(month)<10){ tempmonth="0"+month; }
        else {pepfaryear--;}


        yearmonth=pepfaryear+""+tempmonth;

    String locked_DATA = "SELECT id FROM locked_data WHERE yearmonth=? AND art=?";
    conn.pst = conn.conn.prepareStatement(locked_DATA);
    conn.pst.setString(1, yearmonth);
    conn.pst.setInt(2, 1);
    conn.rs = conn.pst.executeQuery();
    if(conn.rs.next()){
      isLocked= "1";
      lock="disabled";
    }
    
 
         enterdby="";  
         output = "<table  border=\"1px;\">";
          output+="<tr>"
                 + "<td rowspan=\"2\">Main Indicator</td>"
                  + "<td rowspan=\"2\">Indicator</td>"
                  + "<td colspan=\"2\">Unknown</td>"
                  + "<td colspan=\"2\"><1</td>"
                  + "<td colspan=\"2\">1-4</td>"
                  + "<td colspan=\"2\">5-9</td>"
                  + "<td colspan=\"2\">10-14</td>"
                 + "<td colspan=\"2\">15-19</td>"
                  + "<td colspan=\"2\">20-24</td>"
                  + "<td colspan=\"2\">25-29</td>"
                  + "<td colspan=\"2\">30-34</td>"
                  + "<td colspan=\"2\">35-39</td>"
                  + "<td colspan=\"2\">40-44</td>"
                  + "<td colspan=\"2\">45-49</td>"
                  + "<td colspan=\"2\">50+</td>"
                  + "<td>Total</td>"
                 + "</tr>"; 
          output+="<tr><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>Total</td>"
                  + "</tr>"; 
          
          
          main_indic_pos = 0;
          
         for(String main_indic:main_indicator){
          indic_pos=0;   
             System.out.println("main indicator pos = "+main_indic_pos);
         for(String indic_id:indicator_ids[main_indic_pos]){
             String tableid = yearmonth+"_"+indic_id;
         String get_data = "SELECT * FROM fas_art WHERE id=?";
         conn.pst = conn.conn.prepareStatement(get_data);
         conn.pst.setString(1, tableid);
         conn.rs = conn.pst.executeQuery();
         if(conn.rs.next()){ // indicator data already exist
         output+="<tr>";
         if(indic_pos==0){
         output+= "<td rowspan=\""+main_indic_rowspan[main_indic_pos]+"\">"+main_indic+"</td>";
         }
         output+="<td>"+indicators[main_indic_pos][indic_pos]+"</td>";
          for(String column_name:columns){
          output+="<td><input type=\"text\"  name=\""+column_name+"_"+indic_id+"\" id=\""+column_name+"_"+indic_id+"\" value=\""+conn.rs.getString(column_name)+"\" onblur=\"indicate_changed('"+column_name+"_"+indic_id+"');\"  oninput=\"art"+indic_pos+"(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\""+max_length+"\" onkeypress=\"return numbers(event)\" ></td>";
                  }
          output+="<td><p id=\""+indic_id+"\">"+indic_id+"</p></td></tr>";   
         }
         else{ // new indicator
         output+="<tr>";
         if(indic_pos==0){
         output+= "<td rowspan=\""+main_indic_rowspan[main_indic_pos]+"\">"+main_indic+"</td>";
         }
         output+="<td>"+indicators[main_indic_pos][indic_pos]+"</td>";
          for(String column_name:columns){
          output+="<td><input type=\"text\" name=\""+column_name+"_"+indic_id+"\" id=\""+column_name+"_"+indic_id+"\" value=\"\" onblur=\"indicate_changed('"+column_name+"_"+indic_id+"');\"  oninput=\"art"+indic_pos+"(); \" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\""+max_length+"\" onkeypress=\"return numbers(event)\" ></td>";
                  }
          output+="<td><p id=\""+indic_id+"\">"+indic_id+""+indic_id+"</p></td></tr>";  
         }
         
         
         indic_pos++;
                 }
         main_indic_pos++;
       }
        
        out.println(output);
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
        Logger.getLogger(load_art.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_art.class.getName()).log(Level.SEVERE, null, ex);
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

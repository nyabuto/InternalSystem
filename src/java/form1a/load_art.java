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
int indic_pos=0,main_indic_pos=0,max_length=5,indic_counter=0,totals,locked_form,locked_indicator=0,columns_pos;
String tableid = "",submit_button_name="",value="";
String facil = "";
String finer_age_sex_state[] = {"",",2,3,4,5,6,7,8,10,12,14,16,18,20,22,24,","","","",""}; // disable male for breastfeeding
String table_name="fas_art",save_data_url="save_data",load_data_url="load_art",form_name="fas_art";
String section_name="1.10 HIV & TB SCREENING",section_code="10";

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

            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }


//          year="2018";
//          month="10";
//          facil = "383";

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
      locked_form=1;
    }
    else{
         isLocked= "0";
         lock="";  
         locked_form=0;
    }


         enterdby="";
         output = ""+
                  " <fieldset class='formatter' style=\"margin:20px; color:black;\"><legend class='formatter'><b style='text-align:center;'> "+section_name+"</b></legend>" +
                 " <form action=\"#\" id=\""+form_name+"\" method=\"post\" class=\"form-horizontal\">"+
                  " <table  border=\"1px;\">";

          output+="<tr style=\"font-weight:bold; background:#a9c7e4;\">"
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
          output+="<tr style=\"font-weight:bold; background:#a9c7e4;\"><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>M</td><td>F</td><td>M</td><td>F</td>"
                  + "<td>Total</td>"
                  + "</tr>";


          main_indic_pos = 0;
          indic_counter = 0;

         for(String main_indic:main_indicator){
          indic_pos=0;
             System.out.println("main indicator pos = "+main_indic_pos);
         for(String indic_id:indicator_ids[main_indic_pos]){
             totals=columns_pos=0;
             indic_counter++;
             tableid = yearmonth+"_"+facil+"_"+indic_id;
         String get_data = "SELECT * FROM fas_art WHERE id=?";
         conn.pst = conn.conn.prepareStatement(get_data);
         conn.pst.setString(1, tableid);
         conn.rs = conn.pst.executeQuery();


         if(conn.rs.next()){ // indicator data already exist
             if(conn.rs.getString("is_locked")!=null){
           if(conn.rs.getString("is_locked").equals("1")){
//                isLocked= "1";
//                lock="disabled";
                locked_indicator=1;
           } 
           else{
//                isLocked= "0";
//                lock="";
                locked_indicator=0;
           }
             }
             else{
//               isLocked= "0";
//                lock=""; 
                locked_indicator=0;
             }
             
         submit_button_name = "Update ART";
         output+="<tr>";
         if(indic_pos==0){
         output+= "<td rowspan=\""+main_indic_rowspan[main_indic_pos]+"\">"+main_indic+"</td>";
         }
         output+="<td>"+indicators[main_indic_pos][indic_pos]+"<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
          for(String column_name:columns){
              
              if(conn.rs.getString(column_name)!=null){
                  value = conn.rs.getString(column_name);
                  if(isNumeric(value)){
                  totals+=Integer.parseInt(value);
                          }
              }
              else{value="";}
             if(finer_age_sex_state[indic_counter-1].contains(","+columns_pos+",")){
              lock="disabled"; 
              isLocked="1";  
             }
             else{
               if(locked_form==0 && locked_indicator==0){
                  lock=""; 
                  isLocked="0";  
               }
                else if(locked_indicator==1){
                  lock="disabled"; 
                  isLocked="1"; 
               }
           }
           
             //
          output+="<td><input type=\"text\"  name=\""+column_name+"_"+indic_id+"\" id=\""+column_name+"_"+indic_id+"\" value=\""+value+"\" onblur=\"indicate_changed('"+column_name+"_"+indic_id+"'); section_changed('"+section_code+"');\"  oninput=\"sum_indicators('"+indic_counter+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\""+max_length+"\" onkeypress=\"return numbers(event)\" ></td>";
           
          columns_pos++;
          }
          System.out.println("locked form:"+locked_form+" locked indic"+locked_indicator);
          output+="<td><p id=\""+indic_id+"\" style=\"padding-left:10px; padding-right:10px; font-weight:bold;\">"+totals+"</p></td></tr>";
         }

         
         
         
         
         
         else{ // new indicator
             locked_indicator=0;
             if(locked_form==0){
              isLocked= "0";
              lock="";     
             }
          submit_button_name = "Save ART";
         output+="<tr>";
         if(indic_pos==0){
         output+= "<td rowspan=\""+main_indic_rowspan[main_indic_pos]+"\">"+main_indic+"</td>";
         }
         output+="<td>"+indicators[main_indic_pos][indic_pos]+"<input type=\"hidden\" id=\"indic_pos_"+indic_counter+"\" name=\"indic_pos_"+indic_counter+"\" value=\""+indic_id+"\"></td>";
          for(String column_name:columns){
              
           if(finer_age_sex_state[indic_counter-1].contains(","+columns_pos+",")){
              lock="disabled"; 
              isLocked="1";
             }
           else{
               if(locked_form==0 && locked_indicator==0){
                  lock=""; 
                  isLocked="0";  
               }
               else{
                  lock="disabled"; 
                  isLocked="1"; 
               }
           }
          // System.out.println("locked form:"+locked_form+" locked indic"+locked_indicator);
          output+="<td><input type=\"text\" name=\""+column_name+"_"+indic_id+"\" id=\""+column_name+"_"+indic_id+"\" value=\"\" onblur=\"indicate_changed('"+column_name+"_"+indic_id+"');  section_changed('"+section_code+"');\" oninput=\"sum_indicators('"+indic_counter+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\""+max_length+"\" onkeypress=\"return numbers(event)\" ></td>";
          columns_pos++;
          }
          output+="<td><p id=\""+indic_id+"\" style=\"padding-left:10px; padding-right:10px; font-weight:bold;\"></p></td></tr>";
         }

         indic_pos++;
                 }
         main_indic_pos++;
       }
         output+="</table>";
         output+="<input type=\"hidden\" name=\"num_indicators\" id=\"num_indicators\" value=\""+indic_counter+"\">";
         output+="<input type=\"hidden\" name=\"table_name\" id=\"table_name\" value=\""+table_name+"\">";

//         output+="<p id=\"submit_name\">"+submit_button_name+"</p>";
         output+="<div class='form-actions' style=\"text-align:right;\"><button type='button' class='btn blue' onclick=\"save_data('"+form_name+"','"+save_data_url+"','"+load_data_url+"','"+section_code+"')\" name='validate' id='validate' style=\"font-weight:700; font-size:20px; width:20%;\">"+submit_button_name+"</button></div>"
                 + "</form>"
                 + " </fieldset>";


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

       public boolean isNumeric(String s) {  
        return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}
   private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
}

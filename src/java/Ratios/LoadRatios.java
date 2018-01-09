/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ratios;

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
public class LoadRatios extends HttpServlet {
HttpSession session;
String output,headers,data,status_label,Update_btn,hidden_values,lock;
String id,indicator,county_id,county_name,f_1,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50;
String m_1,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50,is_active,start_yearmonth,end_yearmonth;
int position;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
            session = request.getSession();
            position = 0;
            hidden_values=data=lock="";
            
        headers = "<thead>"
        + "<tr border=\"1px;\">"
        + "<th class=\"title\">No.</th>"
        + "<th class=\"title\">Indicator</th>"
        + "<th class=\"title\">County</th>"
        + "<th class=\"title\">F<br><1 Yr</th>"
        + "<th class=\"title\">F<br>1-9 Yrs</th>"
        + "<th class=\"title\">F<br>10-14 Yrs</th>"
        + "<th class=\"title\">F<br>15-19 Yrs</th>"
        + "<th class=\"title\">F<br>20-24 Yrs</th>"
        + "<th class=\"title\">F<br>25-29 Yrs</th>"
        + "<th class=\"title\">F<br>30-34 Yrs</th>"
        + "<th class=\"title\">F<br>35-39 Yrs</th>"
        + "<th class=\"title\">F<br>40-49 Yrs</th>"
        + "<th class=\"title\">F<br>50+ Yrs</th>"
                
        + "<th class=\"title\"> </th>"
                
        + "<th class=\"title\">M<br><1 Yr</th>"
        + "<th class=\"title\">M<br>1-9 Yrs</th>"
        + "<th class=\"title\">M<br>10-14 Yrs</th>"
        + "<th class=\"title\">M<br>15-19 Yrs</th>"
        + "<th class=\"title\">M<br>20-24 Yrs</th>"
        + "<th class=\"title\">M<br>25-29 Yrs</th>"
        + "<th class=\"title\">M<br>30-34 Yrs</th>"
        + "<th class=\"title\">M<br>35-39 Yrs</th>"
        + "<th class=\"title\">M<br>40-49 Yrs</th>"
        + "<th class=\"title\">M<br>50+ Yrs</th>"
        + "<th class=\"title\">Status</th>"
        + "<th class=\"title\">Action</th>"
        + "</tr>"
        + "</thead><tdata>"; 
            
            
            
           String getratios = "SELECT id,indicator,county_id,county_name,f_1,f_9,f_14,f_19,f_24,f_29,f_34,f_39,f_49,f_50,"
                   + "m_1,m_9,m_14,m_19,m_24,m_29,m_34,m_39,m_49,m_50,is_active,start_yearmonth,end_yearmonth "
                   + "FROM ratios";
           conn.rs = conn.st.executeQuery(getratios);
           while(conn.rs.next()){
           id=indicator=county_id=county_name=f_1=f_9=f_14=f_19=f_24=f_29=f_34=f_39=f_49=f_50;
           m_1=m_9=m_14=m_19=m_24=m_29=m_34=m_39=m_49=m_50=is_active=start_yearmonth=end_yearmonth;        
           position++;
                id= conn.rs.getString("id");
                indicator= conn.rs.getString("indicator");
                county_id= conn.rs.getString("county_id");
                county_name= conn.rs.getString("county_name");
                f_1= conn.rs.getString("f_1");
                f_9= conn.rs.getString("f_9");
                f_14= conn.rs.getString("f_14");
                f_19= conn.rs.getString("f_19");
                f_24= conn.rs.getString("f_24");
                f_29= conn.rs.getString("f_29");
                f_34= conn.rs.getString("f_34");
                f_39= conn.rs.getString("f_39");
                f_49= conn.rs.getString("f_49");
                f_50= conn.rs.getString("f_50");
                m_1= conn.rs.getString("m_1");
                m_9= conn.rs.getString("m_9");
                m_14= conn.rs.getString("m_14");
                m_19= conn.rs.getString("m_19");
                m_24= conn.rs.getString("m_24");
                m_29= conn.rs.getString("m_29");
                m_34= conn.rs.getString("m_34");
                m_39= conn.rs.getString("m_39");
                m_49= conn.rs.getString("m_49");
                m_50= conn.rs.getString("m_50");
                is_active= conn.rs.getString("is_active");
                start_yearmonth= conn.rs.getString("start_yearmonth");
                end_yearmonth= conn.rs.getString("end_yearmonth"); 
               
             if(is_active.equals("1")){
                 status_label="Active";
                 Update_btn="<input type='submit' class='btn blue' value='Save Ratio' onclick=\"edit_ratio("+position+");\" name='edit_ratio' id='edit_ratio'/>";
                 lock="";
             }
             else{
                status_label="Inactive"; 
                Update_btn="";
                lock="disabled";
             }
             
             data = data+ "<tr border=\"1px;\">"
                        + "<td class=\"title\">"+position+"</td>"
                        + "<td class=\"title\">"+indicator+"</td>"
                        + "<td class=\"title\">"+county_name+"</td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"f_1_"+position+"\" id=\"f_1_"+position+"\" value=\""+f_1+"\" onblur=\"changed_cell('f_1_"+position+"');\"  class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"f_9_"+position+"\" id=\"f_9_"+position+"\" value=\""+f_9+"\" onblur=\"changed_cell('f_9_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"f_14_"+position+"\" id=\"f_14_"+position+"\" value=\""+f_14+"\" onblur=\"changed_cell('f_14_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"f_19_"+position+"\" id=\"f_19_"+position+"\" value=\""+f_19+"\" onblur=\"changed_cell('f_19_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"f_24_"+position+"\" id=\"f_24_"+position+"\" value=\""+f_24+"\" onblur=\"changed_cell('f_24_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"f_29_"+position+"\" id=\"f_29_"+position+"\" value=\""+f_29+"\" onblur=\"changed_cell('f_29_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"f_34_"+position+"\" id=\"f_34_"+position+"\" value=\""+f_34+"\" onblur=\"changed_cell('f_34_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"f_39_"+position+"\" id=\"f_39_"+position+"\" value=\""+f_39+"\" onblur=\"changed_cell('f_39_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"f_49_"+position+"\" id=\"f_49_"+position+"\" value=\""+f_49+"\" onblur=\"changed_cell('f_49_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\" ></td>"       
                     + "<td class=\"title\"><input type=\"text\" name=\"f_50_"+position+"\" id=\"f_50_"+position+"\" value=\""+f_50+"\" onblur=\"changed_cell('f_50_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                      
                     + "<td class=\"title\"> </td>"
                     
                     + "<td class=\"title\"><input type=\"text\" name=\"m_1_"+position+"\" id=\"m_1_"+position+"\" value=\""+m_1+"\" onblur=\"changed_cell('m_1_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"m_9_"+position+"\" id=\"m_9_"+position+"\" value=\""+m_9+"\" onblur=\"changed_cell('m_9_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"m_14_"+position+"\" id=\"m_14_"+position+"\" value=\""+m_14+"\" onblur=\"changed_cell('m_14_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"
                     + "<td class=\"title\"><input type=\"text\" name=\"m_19_"+position+"\" id=\"m_19_"+position+"\" value=\""+m_19+"\" onblur=\"changed_cell('m_19_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"m_24_"+position+"\" id=\"m_24_"+position+"\" value=\""+m_24+"\" onblur=\"changed_cell('m_24_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"m_29_"+position+"\" id=\"m_29_"+position+"\" value=\""+m_29+"\" onblur=\"changed_cell('m_29_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"m_34_"+position+"\" id=\"m_34_"+position+"\" value=\""+m_34+"\" onblur=\"changed_cell('m_34_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"m_39_"+position+"\" id=\"m_39_"+position+"\" value=\""+m_39+"\" onblur=\"changed_cell('m_39_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\"></td>"        
                     + "<td class=\"title\"><input type=\"text\" name=\"m_49_"+position+"\" id=\"m_49_"+position+"\" value=\""+m_49+"\" onblur=\"changed_cell('m_49_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\" ></td>"       
                     + "<td class=\"title\"><input type=\"text\" name=\"m_50_"+position+"\" id=\"m_50_"+position+"\" value=\""+m_50+"\" onblur=\"changed_cell('m_50_"+position+"');\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" maxLength=\"8\" onkeypress=\"return numbers(event)\">"
                     + "<input type=\"hidden\" name=\"ratio_id_"+position+"\" id=\"ratio_id_"+position+"\" value=\""+id+"\" class=\"data-cell\" data-toggle=\"tooltip\"  "+lock+"  data-placement=\"right\" autocomplete=\"off\" onkeypress=\"return numbers(event)\"></td>"
                    + "<td class=\"title\">"+status_label+"</td>"
                    + "<td class=\"title\">"+Update_btn+"</td>"
                    + "</tr>"
                    + "";  
                        
            }
           data+="</tdata>";
            output=headers+""+data;
           
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
        Logger.getLogger(LoadRatios.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(LoadRatios.class.getName()).log(Level.SEVERE, null, ex);
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

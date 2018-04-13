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
public class validate_newVmmc extends HttpServlet {
HttpSession session;
String titles [] ={"site_type","v1_60d","v1_4y","v1_9y","v1_14y","v1_19y","v1_24y","v1_29y","v1_34y","v1_39y","v1_49y","v1_50y","v1_total","v2_dc_m_60d","v2_dc_m_4y","v2_dc_m_9y","v2_dc_m_14y","v2_dc_m_19y","v2_dc_m_24y","v2_dc_m_29y","v2_dc_m_34y","v2_dc_m_39y","v2_dc_m_49y","v2_dc_m_50y","v2_dc_m_total","v2_dc_s_60d","v2_dc_s_4y","v2_dc_s_9y","v2_dc_s_14y","v2_dc_s_19y","v2_dc_s_24y","v2_dc_s_29y","v2_dc_s_34y","v2_dc_s_39y","v2_dc_s_49y","v2_dc_s_50y","v2_dc_s_total","v2_dc_t_60d","v2_dc_t_4y","v2_dc_t_9y","v2_dc_t_14y","v2_dc_t_19y","v2_dc_t_24y","v2_dc_t_29y","v2_dc_t_34y","v2_dc_t_39y","v2_dc_t_49y","v2_dc_t_50y","v2_dc_t_total","v2_pc_m_60d","v2_pc_m_4y","v2_pc_m_9y","v2_pc_m_14y","v2_pc_m_19y","v2_pc_m_24y","v2_pc_m_29y","v2_pc_m_34y","v2_pc_m_39y","v2_pc_m_49y","v2_pc_m_50y","v2_pc_m_total","v2_pc_s_60d","v2_pc_s_4y","v2_pc_s_9y","v2_pc_s_14y","v2_pc_s_19y","v2_pc_s_24y","v2_pc_s_29y","v2_pc_s_34y","v2_pc_s_39y","v2_pc_s_49y","v2_pc_s_50y","v2_pc_s_total","v2_pc_t_60d","v2_pc_t_4y","v2_pc_t_9y","v2_pc_t_14y","v2_pc_t_19y","v2_pc_t_24y","v2_pc_t_29y","v2_pc_t_34y","v2_pc_t_39y","v2_pc_t_49y","v2_pc_t_50y","v2_pc_t_total","v2_60d_total","v2_4y_total","v2_9y_total","v2_14y_total","v2_19y_total","v2_24y_total","v2_29y_total","v2_34y_total","v2_39y_total","v2_49y_total","v2_50y_total","v2_total","v3_tp_60d","v3_tp_4y","v3_tp_9y","v3_tp_14y","v3_tp_19y","v3_tp_24y","v3_tp_29y","v3_tp_34y","v3_tp_39y","v3_tp_49y","v3_tp_50y","v3_tp_total","v3_srp_60d","v3_srp_4y","v3_srp_9y","v3_srp_14y","v3_srp_19y","v3_srp_24y","v3_srp_29y","v3_srp_34y","v3_srp_39y","v3_srp_49y","v3_srp_50y","v3_srp_total","v3_tn_60d","v3_tn_4y","v3_tn_9y","v3_tn_14y","v3_tn_19y","v3_tn_24y","v3_tn_29y","v3_tn_34y","v3_tn_39y","v3_tn_49y","v3_tn_50y","v3_tn_total","v3_nt_60d","v3_nt_4y","v3_nt_9y","v3_nt_14y","v3_nt_19y","v3_nt_24y","v3_nt_29y","v3_nt_34y","v3_nt_39y","v3_nt_49y","v3_nt_50y","v3_nt_total","v3_us_60d","v3_us_4y","v3_us_9y","v3_us_14y","v3_us_19y","v3_us_24y","v3_us_29y","v3_us_34y","v3_us_39y","v3_us_49y","v3_us_50y","v3_us_total","v3_srn_60d","v3_srn_4y","v3_srn_9y","v3_srn_14y","v3_srn_19y","v3_srn_24y","v3_srn_29y","v3_srn_34y","v3_srn_39y","v3_srn_49y","v3_srn_50y","v3_srn_total","v3_t_60d","v3_t_4y","v3_t_9y","v3_t_14y","v3_t_19y","v3_t_24y","v3_t_29y","v3_t_34y","v3_t_39y","v3_t_49y","v3_t_50y","v3_t_total","v4_s_vmmc_60d","v4_s_vmmc_4y","v4_s_vmmc_9y","v4_s_vmmc_14y","v4_s_vmmc_19y","v4_s_vmmc_24y","v4_s_vmmc_29y","v4_s_vmmc_34y","v4_s_vmmc_39y","v4_s_vmmc_49y","v4_s_vmmc_50y","v4_s_vmmc_total","v4_db_vmmc_60d","v4_db_vmmc_4y","v4_db_vmmc_9y","v4_db_vmmc_14y","v4_db_vmmc_19y","v4_db_vmmc_24y","v4_db_vmmc_29y","v4_db_vmmc_34y","v4_db_vmmc_39y","v4_db_vmmc_49y","v4_db_vmmc_50y","v4_db_vmmc_total","v4_t_vmmc_60d","v4_t_vmmc_4y","v4_t_vmmc_9y","v4_t_vmmc_14y","v4_t_vmmc_19y","v4_t_vmmc_24y","v4_t_vmmc_29y","v4_t_vmmc_34y","v4_t_vmmc_39y","v4_t_vmmc_49y","v4_t_vmmc_50y","v4_t_vmmc_total","v5_followup_60d","v5_followup_4y","v5_followup_9y","v5_followup_14y","v5_followup_19y","v5_followup_24y","v5_followup_29y","v5_followup_34y","v5_followup_39y","v5_followup_49y","v5_followup_50y","v5_followup_total","v6_nofollowup_60d","v6_nofollowup_4y","v6_nofollowup_9y","v6_nofollowup_14y","v6_nofollowup_19y","v6_nofollowup_24y","v6_nofollowup_29y","v6_nofollowup_34y","v6_nofollowup_39y","v6_nofollowup_49y","v6_nofollowup_50y","v6_nofollowup_total"};
String values_query="",query="";
String label,value,year,month,tableid,facil,yearmonth;
String userid,site_type;
protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session = request.getSession();
           dbConn conn = new dbConn();
           
//             site_type = request.getParameter("site_type"); 
          if(session.getAttribute("site_type")!=null){
            site_type=session.getAttribute("site_type").toString();
           }
        else{
             site_type = "1"; 
          }
          //unset site type session
          session.removeAttribute("site_type");
          
           values_query="REPLACE INTO vmmc_new SET ";
           
            if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            
            userid="unknown";

            if(session.getAttribute("userid")!=null){        
                userid=session.getAttribute("userid").toString();
            }
            tableid = year + "_" + month + "_" + facil;
           
            if(Integer.parseInt(month)>=10){
                yearmonth=(Integer.parseInt(year)-1)+""+month;
            }
            else{
              yearmonth=Integer.parseInt(year)+"0"+month;  
            }
            System.out.println("Yearmonth : "+yearmonth);
           
           values_query+="tableid='"+tableid+"',SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"',site_type='"+site_type+"',user_id='"+userid+"',isValidated=1,isLocked=0,yearmonth='"+yearmonth+"',";
           
           
        for(String title:titles){
         if(request.getParameter(title)!=null){
             
             if(!request.getParameter(title).equals("")){
              label = title;
              value = request.getParameter(title);
             }
             else{
               label = title;
              value = "0";  
             }
             
             values_query+=label+"='"+value+"',";
         }  
       }    
       query  = removeLast(values_query);
       System.out.println("query: "+query); 
       conn.st.executeUpdate(query);
       
     response.sendRedirect("loadVmmc.jsp");
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
        Logger.getLogger(validate_newVmmc.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(validate_newVmmc.class.getName()).log(Level.SEVERE, null, ex);
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

    public String removeLast(String str) {
    if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == ',') {
        str = str.substring(0, str.length() - 1);
    }
    return str;
}
}

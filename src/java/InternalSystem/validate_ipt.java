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
public class validate_ipt extends HttpServlet {
    HttpSession session;
    String titles[] = {"m1_A_New_ART","m1_A_Prev_ART","m4_A_New_ART","m4_A_Prev_ART","m9_A_New_ART","m9_A_Prev_ART","m14_A_New_ART","m14_A_Prev_ART","m19_A_New_ART","m19_A_Prev_ART","m24_A_New_ART","m24_A_Prev_ART","m29_A_New_ART","m29_A_Prev_ART","m34_A_New_ART","m34_A_Prev_ART","m39_A_New_ART","m39_A_Prev_ART","m49_A_New_ART","m49_A_Prev_ART","m50_A_New_ART","m50_A_Prev_ART","tm_A_New_ART","tm_A_Prev_ART","f1_A_New_ART","f1_A_Prev_ART","f4_A_New_ART","f4_A_Prev_ART","f9_A_New_ART","f9_A_Prev_ART","f14_A_New_ART","f14_A_Prev_ART","f19_A_New_ART","f19_A_Prev_ART","f24_A_New_ART","f24_A_Prev_ART","f29_A_New_ART","f29_A_Prev_ART","f34_A_New_ART","f34_A_Prev_ART","f39_A_New_ART","f39_A_Prev_ART","f49_A_New_ART","f49_A_Prev_ART","f50_A_New_ART","f50_A_Prev_ART","tf_A_New_ART","tf_A_Prev_ART","GT_A_New_ART","GT_A_Prev_ART","m1_B_New_ART","m1_B_Prev_ART","m4_B_New_ART","m4_B_Prev_ART","m9_B_New_ART","m9_B_Prev_ART","m14_B_New_ART","m14_B_Prev_ART","m19_B_New_ART","m19_B_Prev_ART","m24_B_New_ART","m24_B_Prev_ART","m29_B_New_ART","m29_B_Prev_ART","m34_B_New_ART","m34_B_Prev_ART","m39_B_New_ART","m39_B_Prev_ART","m49_B_New_ART","m49_B_Prev_ART","m50_B_New_ART","m50_B_Prev_ART","tm_B_New_ART","tm_B_Prev_ART","f1_B_New_ART","f1_B_Prev_ART","f4_B_New_ART","f4_B_Prev_ART","f9_B_New_ART","f9_B_Prev_ART","f14_B_New_ART","f14_B_Prev_ART","f19_B_New_ART","f19_B_Prev_ART","f24_B_New_ART","f24_B_Prev_ART","f29_B_New_ART","f29_B_Prev_ART","f34_B_New_ART","f34_B_Prev_ART","f39_B_New_ART","f39_B_Prev_ART","f49_B_New_ART","f49_B_Prev_ART","f50_B_New_ART","f50_B_Prev_ART","tf_B_New_ART","tf_B_Prev_ART","GT_B_New_ART","GT_B_Prev_ART","m1_C_New_ART","m1_C_Prev_ART","m4_C_New_ART","m4_C_Prev_ART","m9_C_New_ART","m9_C_Prev_ART","m14_C_New_ART","m14_C_Prev_ART","m19_C_New_ART","m19_C_Prev_ART","m24_C_New_ART","m24_C_Prev_ART","m29_C_New_ART","m29_C_Prev_ART","m34_C_New_ART","m34_C_Prev_ART","m39_C_New_ART","m39_C_Prev_ART","m49_C_New_ART","m49_C_Prev_ART","m50_C_New_ART","m50_C_Prev_ART","tm_C_New_ART","tm_C_Prev_ART","f1_C_New_ART","f1_C_Prev_ART","f4_C_New_ART","f4_C_Prev_ART","f9_C_New_ART","f9_C_Prev_ART","f14_C_New_ART","f14_C_Prev_ART","f19_C_New_ART","f19_C_Prev_ART","f24_C_New_ART","f24_C_Prev_ART","f29_C_New_ART","f29_C_Prev_ART","f34_C_New_ART","f34_C_Prev_ART","f39_C_New_ART","f39_C_Prev_ART","f49_C_New_ART","f49_C_Prev_ART","f50_C_New_ART","f50_C_Prev_ART","tf_C_New_ART","tf_C_Prev_ART","GT_C_New_ART","GT_C_Prev_ART","m1_D_New_ART","m1_D_Prev_ART","m4_D_New_ART","m4_D_Prev_ART","m9_D_New_ART","m9_D_Prev_ART","m14_D_New_ART","m14_D_Prev_ART","m19_D_New_ART","m19_D_Prev_ART","m24_D_New_ART","m24_D_Prev_ART","m29_D_New_ART","m29_D_Prev_ART","m34_D_New_ART","m34_D_Prev_ART","m39_D_New_ART","m39_D_Prev_ART","m49_D_New_ART","m49_D_Prev_ART","m50_D_New_ART","m50_D_Prev_ART","tm_D_New_ART","tm_D_Prev_ART","f1_D_New_ART","f1_D_Prev_ART","f4_D_New_ART","f4_D_Prev_ART","f9_D_New_ART","f9_D_Prev_ART","f14_D_New_ART","f14_D_Prev_ART","f19_D_New_ART","f19_D_Prev_ART","f24_D_New_ART","f24_D_Prev_ART","f29_D_New_ART","f29_D_Prev_ART","f34_D_New_ART","f34_D_Prev_ART","f39_D_New_ART","f39_D_Prev_ART","f49_D_New_ART","f49_D_Prev_ART","f50_D_New_ART","f50_D_Prev_ART","tf_D_New_ART","tf_D_Prev_ART","GT_D_New_ART","GT_D_Prev_ART","m1_E_New_ART","m1_E_Prev_ART","m4_E_New_ART","m4_E_Prev_ART","m9_E_New_ART","m9_E_Prev_ART","m14_E_New_ART","m14_E_Prev_ART","m19_E_New_ART","m19_E_Prev_ART","m24_E_New_ART","m24_E_Prev_ART","m29_E_New_ART","m29_E_Prev_ART","m34_E_New_ART","m34_E_Prev_ART","m39_E_New_ART","m39_E_Prev_ART","m49_E_New_ART","m49_E_Prev_ART","m50_E_New_ART","m50_E_Prev_ART","tm_E_New_ART","tm_E_Prev_ART","f1_E_New_ART","f1_E_Prev_ART","f4_E_New_ART","f4_E_Prev_ART","f9_E_New_ART","f9_E_Prev_ART","f14_E_New_ART","f14_E_Prev_ART","f19_E_New_ART","f19_E_Prev_ART","f24_E_New_ART","f24_E_Prev_ART","f29_E_New_ART","f29_E_Prev_ART","f34_E_New_ART","f34_E_Prev_ART","f39_E_New_ART","f39_E_Prev_ART","f49_E_New_ART","f49_E_Prev_ART","f50_E_New_ART","f50_E_Prev_ART","tf_E_New_ART","tf_E_Prev_ART","GT_E_New_ART","GT_E_Prev_ART","m1_F_New_ART","m1_F_Prev_ART","m4_F_New_ART","m4_F_Prev_ART","m9_F_New_ART","m9_F_Prev_ART","m14_F_New_ART","m14_F_Prev_ART","m19_F_New_ART","m19_F_Prev_ART","m24_F_New_ART","m24_F_Prev_ART","m29_F_New_ART","m29_F_Prev_ART","m34_F_New_ART","m34_F_Prev_ART","m39_F_New_ART","m39_F_Prev_ART","m49_F_New_ART","m49_F_Prev_ART","m50_F_New_ART","m50_F_Prev_ART","tm_F_New_ART","tm_F_Prev_ART","f1_F_New_ART","f1_F_Prev_ART","f4_F_New_ART","f4_F_Prev_ART","f9_F_New_ART","f9_F_Prev_ART","f14_F_New_ART","f14_F_Prev_ART","f19_F_New_ART","f19_F_Prev_ART","f24_F_New_ART","f24_F_Prev_ART","f29_F_New_ART","f29_F_Prev_ART","f34_F_New_ART","f34_F_Prev_ART","f39_F_New_ART","f39_F_Prev_ART","f49_F_New_ART","f49_F_Prev_ART","f50_F_New_ART","f50_F_Prev_ART","tf_F_New_ART","tf_F_Prev_ART","GT_F_New_ART","GT_F_Prev_ART"};
    String values_query="",query="";
    String label,value,year,month,tableid,facil,yearmonth;
    String userid;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          session = request.getSession();
           dbConn conn = new dbConn();

          //unset site type session
          session.removeAttribute("site_type");
          
           values_query="REPLACE INTO ipt SET ";
           
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
              yearmonth=Integer.parseInt(year)+""+month;  
            }
            System.out.println("Yearmonth : "+yearmonth);
           
           values_query+="tableid='"+tableid+"',SubPartnerID='"+facil+"',Annee='"+year+"',Mois='"+month+"',user_id='"+userid+"',isValidated=1,isLocked=0,yearmonth='"+yearmonth+"',";
           
           
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
       
     response.sendRedirect("IPT.jsp");
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
            Logger.getLogger(validate_ipt.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(validate_ipt.class.getName()).log(Level.SEVERE, null, ex);
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

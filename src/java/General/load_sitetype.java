/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package General;

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
public class load_sitetype extends HttpServlet {
HttpSession session;
String output,tableid,year,month,facil;
String chosen_type_id;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
           session = request.getSession();
           dbConn conn = new dbConn();
           year=month=facil="";
           if (session.getAttribute("year") != null) {
                year = session.getAttribute("year").toString();
            }
            if (session.getAttribute("monthid") != null) {
                month = session.getAttribute("monthid").toString();
            }

            if (session.getAttribute("facilityid") != null) {
                facil = session.getAttribute("facilityid").toString();
            }
            tableid = year + "_" + month + "_" + facil;
            
           output=chosen_type_id="";
           output="<option value=\"\">Choose Option</option>";
           if(session.getAttribute("site_type")!=null){
            chosen_type_id=session.getAttribute("site_type").toString();
           }
           else{
               String gettype="SELECT site_type FROM vmmc_new WHERE tableid='"+tableid+"'";
               conn.rs=conn.st.executeQuery(gettype);
               if(conn.rs.next()){
                chosen_type_id=conn.rs.getString(1);
               }
           }
          String gettypes="SELECT id,name FROM site_types ORDER BY id ASC";
          conn.rs = conn.st.executeQuery(gettypes);
          while(conn.rs.next()){
              if(conn.rs.getString("id").equals(chosen_type_id)){
                  output+="<option value=\""+conn.rs.getString("id")+"\" selected>"+conn.rs.getString("name")+"</option>";
              }
              else{
                  output+="<option value=\""+conn.rs.getString("id")+"\">"+conn.rs.getString("name")+"</option>";
              }
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
        Logger.getLogger(load_sitetype.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_sitetype.class.getName()).log(Level.SEVERE, null, ex);
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

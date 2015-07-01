/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reports;

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
public class loadFacils extends HttpServlet {
HttpSession session;
String facilQuery,counties,current_districts;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
              dbConn conn=new dbConn();
              session=request.getSession();
              
            facilQuery="";current_districts="";
            
         if(request.getParameter("districts").contains(",")){
             String countids []=request.getParameter("districts").split(",");
             
             for(String ids: countids){
             facilQuery+=" || DistrictID="+ids;   
                 
             }
             System.out.println("before remove : "+facilQuery);
             
             facilQuery=facilQuery.substring(3);
             
             System.out.println("after remove : "+facilQuery);
         }
         else{
             facilQuery="DistrictID="+request.getParameter("districts");
         }
         
         System.out.println("final : "+facilQuery);
         
        String facilities="Select SubPartnerId,SubPartnerNom from subpartnera where "+facilQuery+"";
      System.out.println("query : "+facilities);
          conn.rs=conn.st.executeQuery(facilities);
      while(conn.rs.next()){

current_districts=current_districts+"<option value=\""+conn.rs.getString(1)+"\">"+conn.rs.getString(2)+"</option>";
 }
        System.out.println(current_districts);
            out.println(current_districts);
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
        Logger.getLogger(loadFacils.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(loadFacils.class.getName()).log(Level.SEVERE, null, ex);
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

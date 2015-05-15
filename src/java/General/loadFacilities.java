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
 * @author Geofrey Nyabuto
 */
public class loadFacilities extends HttpServlet {

    HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        session=request.getSession();
        
        String facilityonsession="";
        
        if(session.getAttribute("facilityid")!=null){
        facilityonsession=session.getAttribute("facilityid").toString();
        
        }
        
        
        PrintWriter out = response.getWriter();
        try {
            dbConn conn = new dbConn();
           
            String facils="<option value=''>Select Site</option>";
            
            String getfacils="select SubPartnerId,SubPartnerNom from subpartnera";
            
            conn.rs=conn.st.executeQuery(getfacils);
           
            while(conn.rs.next()){
                //if the current facility on loop is same as the facility on session, then make it selected
                if(facilityonsession.equals(conn.rs.getString(1))){
                
            facils+="<option selected value='"+conn.rs.getString(1) +"'> "+conn.rs.getString(2)+" </option>";
                }
                else{
                         facils+="<option value='"+conn.rs.getString(1) +"'> "+conn.rs.getString(2)+" </option>";
                }
                
            
                                 }
            
             
            out.println(facils);
        } catch (SQLException ex) {
            Logger.getLogger(loadFacilities.class.getName()).log(Level.SEVERE, null, ex);
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
        processRequest(request, response);
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
        processRequest(request, response);
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

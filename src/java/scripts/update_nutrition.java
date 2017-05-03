/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

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

/**
 *
 * @author EKaunda
 */
public class update_nutrition extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           dbConn conn= new dbConn();
           
           
           String spttype="select Facility,MflCode,Nutrition,Lab,Gender,Prep from masterlist_changes ";
            conn.rs=conn.st.executeQuery(spttype);
            while(conn.rs.next()){ 
                String mflcode=conn.rs.getString("MflCode");
                
                String facility=conn.rs.getString("Facility");
                String nutrition=conn.rs.getString("Nutrition");
                String Lab=conn.rs.getString("Lab");
                String Gender=conn.rs.getString("Gender");
                String prep=conn.rs.getString("Prep");
                
        
            
            String updatesupport="update subpartnera set SubPartnerNom=?,Nutrition=?,Lab=?,Gender=?,Prep=? where CentreSanteId=? ";
            
            conn.pst=conn.conn.prepareStatement(updatesupport);
            conn.pst.setString(1, facility);
            conn.pst.setString(2, nutrition);
            conn.pst.setString(3, Lab);
            conn.pst.setString(4, Gender);
            conn.pst.setString(5, prep);
            conn.pst.setString(6, mflcode);            
            conn.pst.executeUpdate();
            
           
            
           
            
            }
            
            out.println("</html>");
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
            Logger.getLogger(update_supporttype.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(update_supporttype.class.getName()).log(Level.SEVERE, null, ex);
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

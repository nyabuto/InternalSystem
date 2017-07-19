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
 * @author Emmanuel E
 */
public class highvolumescript extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
            dbConn conn = new dbConn();
            
            String getsubpartner="select MoHCode as mflcode,longitude as longitude,Latitude   from latlong ";
            
            conn.rs=conn.st.executeQuery(getsubpartner);
            while(conn.rs.next()){
                
                System.out.println(" "+conn.rs.getString(1));
             
            //update art
            String updatecode="update subpartnera2014 set latitude=? , longitude=? where CentreSanteId=?";                
            //conn.st2.executeUpdate(updatecode);
            
            
        conn.pst=conn.conn.prepareStatement(updatecode);
        conn.pst.setString(1, conn.rs.getString("Latitude"));
        conn.pst.setString(2, conn.rs.getString("longitude"));
        conn.pst.setString(3, conn.rs.getString("mflcode"));     
    
        conn.pst.executeUpdate();
               
            
            //"+conn.rs.getString("Ward")+"
            //"+conn.rs.getString("constituency")+"
            //"+conn.rs.getString("mflcode")+"
            
            }//end of outer loop
            
            out.println("script completed");
            
            
            
            
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
            Logger.getLogger(highvolumescript.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(highvolumescript.class.getName()).log(Level.SEVERE, null, ex);
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

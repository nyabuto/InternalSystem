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
            
            String getsubpartner="select SubPartnerID,CentreSanteId as mflcode from subpartnera2014 ";
            
            conn.rs=conn.st.executeQuery(getsubpartner);
            while(conn.rs.next()){
                
                System.out.println(" "+conn.rs.getString(1));
                //
                String getart="select ART_highvolume as art from hv where ART_highvolume='"+conn.rs.getString("mflcode")+"' ";
                String gethtc="select HTC_highvolume as htc  from hv where HTC_highvolume='"+conn.rs.getString("mflcode")+"' ";
                String getpmtct="select  PMTCT_highvolume as pmtct from hv where PMTCT_highvolume='"+conn.rs.getString("mflcode")+"' ";
                //ART
            conn.rs1=conn.st1.executeQuery(getart);
            if(conn.rs1.next()){
            //update art
            String updatecode="update subpartnera2014 set ART_highvolume='1' where CentreSanteId='"+conn.rs.getString("mflcode")+"'";                
            conn.st2.executeUpdate(updatecode);
            }
            //HTC 
             conn.rs1=conn.st1.executeQuery(gethtc);
            if(conn.rs1.next()){
            //update art
            String updatecode="update subpartnera2014 set HTC_highvolume='1' where CentreSanteId='"+conn.rs.getString("mflcode")+"'";                
            conn.st2.executeUpdate(updatecode);
                               }
            
            //PMTCT
             conn.rs1=conn.st1.executeQuery(getpmtct);
            if(conn.rs1.next()){
            //update art
            String updatecode="update subpartnera2014 set PMTCT_highvolume='1' where CentreSanteId='"+conn.rs.getString("mflcode")+"'";                
            conn.st2.executeUpdate(updatecode);
                               }
            
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

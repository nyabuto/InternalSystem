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
public class updateuserid extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
          dbConn conn = new dbConn();
          
          String getdata="select * from imis.hc_participants where concat(id,timestamp) not in (select concat(id,timestamp) from internal_system.hc_participants) and imis.hc_participants.id  in ( select id from internal_system.hc_participants) ";
            
          conn.rs=conn.st.executeQuery(getdata);
          int count=0;
          while(conn.rs.next()){
          count++;
          
              String currentid=conn.rs.getString("id");
              String updatedid="n"+(new Integer(currentid)+28);
              
              String update="update imis.hc_participants set id='"+updatedid+"' where id='"+currentid+"'";
              String update2="update imis.hc_services set participantid='"+updatedid+"' where participantid='"+currentid+"'";
              
              //conn.st1.executeUpdate(update);
              //conn.st1.executeUpdate(update2);
              
              System.out.println("line "+count);
              System.out.println(update);
              System.out.println(update2+"\n\n");
              
          }
          
          
          
          
            out.println("</html>");
        } catch (SQLException ex) {
            Logger.getLogger(updateuserid.class.getName()).log(Level.SEVERE, null, ex);
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

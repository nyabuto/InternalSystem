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
public class updatePmtct_othercolumns extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
            dbConn conn= new dbConn();
            
          String getcode=" Select " +
"	 id," +
"	 ROUND(0.05*numerator) as linked_art, " +
"	 0 as not_linked_art," +
"	 0 as unknown_link, " +
"	 ROUND(0.83*numerator) as not_breastfeeding," +
"	 0 as breastfeeding," +
"	 0 as breastfeeding_unknown," +
"	 0 as care_no_test," +
"	 Round(0.08*numerator) as ltfu," +
"	 Round(0.02*numerator) as died," +
"	 Round(0.02*numerator) as transferred_out from pmtct_fo  where linked_art is null ";  
         
          
          conn.rs=conn.st.executeQuery(getcode);
          while(conn.rs.next()){
         
              
              String updatecode=" update pmtct_fo set linked_art='"+conn.rs.getString("linked_art")+"', not_linked_art='"+conn.rs.getString("not_linked_art")+"', unknown_link='"+conn.rs.getString("unknown_link")+"' , not_breastfeeding='"+conn.rs.getString("not_breastfeeding")+"',breastfeeding='"+conn.rs.getString("breastfeeding")+"',breastfeeding_unknown='"+conn.rs.getString("breastfeeding_unknown")+"',care_no_test='"+conn.rs.getString("care_no_test")+"',ltfu='"+conn.rs.getString("ltfu")+"',died='"+conn.rs.getString("died")+"',transferred_out='"+conn.rs.getString("transferred_out")+"' where id='"+conn.rs.getString("id")+"' ";
              System.out.println(updatecode);
              conn.st4.executeUpdate(updatecode);
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
            Logger.getLogger(updatePmtct_othercolumns.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(updatePmtct_othercolumns.class.getName()).log(Level.SEVERE, null, ex);
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

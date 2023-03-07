/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HCA;

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
 * @author Emmanuel Kaunda
 */
public class correctbirthmonth extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
       
            dbConn conn = new dbConn();
            
            
            String qry=" SELECT \n" +
"    result_id,\n" +
"    indicator_id,\n" +
"    reportingyearmonth,\n" +
"    SUBSTRING(DATE_SUB(DATE(CONCAT(reportingyearmonth, '01')),\n" +
"            INTERVAL 2 YEAR),\n" +
"        1,\n" +
"        4) AS bym\n" +
"FROM\n" +
"    hei.results\n" +
"WHERE\n" +
"    indicator_id >= 16 ";
            
            try {
                conn.rs=conn.st.executeQuery(qry);
                
                while(conn.rs.next())
                {
                
                
                
                //result_id
                    //bym
                    
                    String result_id=conn.rs.getString("result_id");
                    String bym=conn.rs.getString("bym");
                    
                    String upd="update hei.results set birth_year='"+bym+"' where result_id='"+result_id+"'";          
                    System.out.println(""+upd);
                conn.st0.executeUpdate(upd);
                
                
                }
            } catch (SQLException ex) {
                Logger.getLogger(correctbirthmonth.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
            
            out.println("</html>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GapAnalysis;

import database.dbConn;
import database.dbConnWeb;
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
 * @author GNyabuto
 */
public class DownloadGaps extends HttpServlet {
String[] columns =  {"rule","gap","program_area","county","sub_county","facility","year","month","ward","latitude","longitude"};
String query,value,id;
int downloaded,existing;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          dbConn conn = new dbConn();
          dbConnWeb webconn = new dbConnWeb();
          downloaded=existing=0; 
          
        String Get_all_gaps = "SELECT * FROM gaps WHERE status=1 AND downloaded=0";
        webconn.rs = conn.st.executeQuery(Get_all_gaps);
        while(webconn.rs.next()){
            query = "REPLACE INTO gaps SET ";
            
            for (String label : columns){
               value = webconn.rs.getString(label);
               query+=label+"='"+value+"',"; 
               
               }
            id = webconn.rs.getString("id");
            
            // insert into the local db
            int rec = conn.st.executeUpdate(query);
            if(rec>0){
            String updatedWeb = "UPDATE gaps SET downloaded=1 WHERE id='"+id+"'";
            webconn.st.executeUpdate(updatedWeb);
            downloaded++;
            }
            }
          
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
        Logger.getLogger(DownloadGaps.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(DownloadGaps.class.getName()).log(Level.SEVERE, null, ex);
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

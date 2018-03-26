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
import java.util.Arrays;
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
public class DownloadGaps extends HttpServlet {
String[] columns =  {"id","rule","gap","program_area","county","sub_county","facility","year","month","ward","latitude","longitude","explanation","status","downloaded","timestamp"};
String query,value,id;
int downloaded,existing;
HttpSession session;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
            
          dbConn conn = new dbConn();
          dbConnWeb webconn = new dbConnWeb();
          downloaded=existing=0; 
            System.out.println(Arrays.toString(webconn.dbsetup));
        String Get_all_gaps = "SELECT * FROM gaps WHERE status=1 AND downloaded=0";
//        String Get_all_gaps = "SELECT * FROM gaps";
        webconn.rs = webconn.st.executeQuery(Get_all_gaps);
        
//    webconn.rs.last();
//    int size = webconn.rs.getRow();
//    webconn.rs.beforeFirst();

//    get all approved gaps
        while(webconn.rs.next()){
            query = "REPLACE INTO gaps SET ";
            System.out.println("id : "+webconn.rs.getInt(1));
            
            for (String label : columns){
                
               value = webconn.rs.getString(label);
               
               query+=label+"='"+value+"',"; 
               
               }
            
            id = webconn.rs.getString("id");
            
            // remove the last character. Usually it is a comma.
            query=removeLast(query,1); 
            // insert into the local db
            
            System.out.println("query:"+query);
            int rec = conn.st.executeUpdate(query);
            
            // update the server to show that the record has already been downloaded.
            if(rec>0){
            String updatedWeb = "UPDATE gaps SET downloaded=1 WHERE id='"+id+"'";
            webconn.st1.executeUpdate(updatedWeb);
            downloaded++;
            }
            else{
             existing++;   
            }
            }
        
   session.setAttribute("gaps_downloaded", "<b>"+downloaded+"</b> resolved gaps have been downloaded to the local server.");
    response.sendRedirect("DownloadGaps.jsp");
        
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
    
    public String removeLast(String str, int num) {
    if (str != null && str.length() > 0) {
        str = str.substring(0, str.length() - num);
    }
    return str;
    }
}

/*
 * To change this template, choose Tools | Templates
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
 * @author Elkant
 */
public class loadForms extends HttpServlet {

   HttpSession session=null;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session=request.getSession();
        try {
    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();
    
    dbConn conn= new dbConn();
   String forms="<option value=''> Select Form</option>"; 
    String getForms="select * from forms";
   
    conn.rs=conn.st.executeQuery(getForms);
    
    
    while(conn.rs.next()){
     if(session.getAttribute("forms_holder")!=null){ if(session.getAttribute("forms_holder").toString().contains(conn.rs.getString("form"))  || conn.rs.getString("form").equals("MOH 711A")|| conn.rs.getString("form").equals("MOH 731")){     
     forms+="<option value='"+conn.rs.getString("nextpage") +"'>"+conn.rs.getString("form")+"</option>";   
     } }
                         }
    
    try {
        
        out.println(forms);
    } finally {    
          conn.conn.close();
        out.close();
        
    }
}       catch (SQLException ex) {
            Logger.getLogger(loadForms.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

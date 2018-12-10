/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datim;

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
 * @author GNyabuto
 */
public class load_indicators extends HttpServlet {
HttpSession session;
String output="",category_where;
int items=0;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            session = request.getSession();
           dbConn conn = new dbConn();
           
           String categories = request.getParameter("service_areas");
//           String categories = "Prevention_Treatment";
           
           String cats[] = categories.split("_");
           
           items=0;
           category_where = " (";
           for(String cat:cats){
               if(cat!=null && !cat.equals("")){
                 category_where+="category='"+cat+"' OR "; 
                 items++;
               }
           }
           
           if(items>0){
            category_where = removeLast(category_where, 3);
           }
           else{
               category_where+=" 1=1 ";
           }
           
           category_where+=")";
           
           output="";
           String get_indicators = "SELECT indicator_name,indicator_code,stored_procedure,frequency FROM datim_output WHERE is_active=1 AND "+category_where+" AND (stored_procedure!='' AND stored_procedure IS NOT NULL) ORDER BY order_no";
           conn.rs = conn.st.executeQuery(get_indicators);
           while(conn.rs.next()){
             output+="<option value=\""+conn.rs.getString(2)+"\">"+conn.rs.getString(1)+" ["+conn.rs.getString(2)+"]</option>";  
           }
           
            System.out.println(get_indicators);
            out.println(output);
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
        Logger.getLogger(load_indicators.class.getName()).log(Level.SEVERE, null, ex);
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
        Logger.getLogger(load_indicators.class.getName()).log(Level.SEVERE, null, ex);
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

 private static String removeLast(String str, int num) {
    return str.substring(0, str.length() - num);
}
}

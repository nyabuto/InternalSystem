/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scripts;

import database.dbConn;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author EKaunda
 */
public class mergePNSIndex extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
          
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

public void insertIndex(){}
public void pullPNSIndex(){

    
     String id ="";
 String mflcode ="";
 String supporttype ="";
 String p_u_f ="";
 String p_u_m ="";
 String p_1_f ="";
 String p_1_m ="";
 String p_9_f ="";
 String p_9_m ="";
 String p_14_f ="";
 String p_14_m ="";
 String p_19_f ="";
 String p_19_m ="";
 String p_24_f ="";
 String p_24_m ="";
 String p_29_f ="";
 String p_29_m ="";
 String p_34_f ="";
 String p_34_m ="";
 String p_39_f ="";
 String p_39_m ="";
 String p_49_f ="";
 String p_49_m ="";
 String p_50_f ="";
 String p_50_m ="";
 String n_u_f ="";
 String n_u_m ="";
 String n_1_f ="";
 String n_1_m ="";
 String n_9_f ="";
 String n_9_m ="";
 String n_14_f ="";
 String n_14_m ="";
 String n_19_f ="";
 String n_19_m ="";
 String n_24_f ="";
 String n_24_m ="";
 String n_29_f ="";
 String n_29_m ="";
 String n_34_f ="";
 String n_34_m ="";
 String n_39_f ="";
 String n_39_m ="";
 String n_49_f ="";
 String n_49_m ="";
 String n_50_f ="";
 String n_50_m ="";
 String subtotal ="";
 String yearmonth ="";

    
    dbConn conn = new dbConn();
    
    String getIndex="";
    
    HashMap<String,String> hm = new HashMap<String,String>();
    

}
}

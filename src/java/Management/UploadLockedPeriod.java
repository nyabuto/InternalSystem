/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Management;

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
 * @author Administrator
 */
public class UploadLockedPeriod extends HttpServlet {

 
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            
         
            
            String id="";
            String periods="";
            String form="";
            
            if(request.getParameter("id")!=null){ id=request.getParameter("id"); }
            
            if(request.getParameter("periods")!=null){ periods=request.getParameter("periods"); }
            if(request.getParameter("fname")!=null){ form=request.getParameter("fname"); }
            
              String updatedata=" update fas_allowed_excel_uploads set periods='"+periods+"' where id='"+id+"'"; 
              
              dbConn conn = new dbConn();
              
              conn.st.executeUpdate(updatedata);
           
            
            /* TODO output your page here. You may use following sample code. */
           out.println("<font color='green'>Period <b>"+periods+"</b> Updated Successfully for form <b>"+form+"</b></font>");
            
        } catch (SQLException ex) {
            Logger.getLogger(UploadLockedPeriod.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
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

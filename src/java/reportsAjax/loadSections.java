/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportsAjax;

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
 * @author Elkant
 */
public class loadSections extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            response.setContentType("text/html;charset=UTF-8");
            
            String form=request.getParameter("form");
            
            System.out.println("  FFFForm ni_"+ form+"_");
            
            if(form.equalsIgnoreCase("MOH 711 (New)"))
            {
            
            form="moh711new";
                
                
            }
            else if(form.equals("MOH 711A")){
            
            form="moh711";
            
            }
            else if(form.equals("MOH 731")){
              form="vw_moh731";
               System.out.println(" imediteeeect");
          }
            else  if(form.equalsIgnoreCase("vmmc")){
              form="vmmc_new";
          }
            else {            
            
            form=form.replace(" ", "").toLowerCase();
            }
            
          
           
            
            String selectforms="select sectionid as sectionid, section as section from pivottable where active='1' and form='"+form+"' group by sectionid";
            
             System.out.println("*_________________________________________________________"+selectforms);
            
            String table="";
            
            dbConn conn= new dbConn();
            conn.rs=conn.st.executeQuery(selectforms);
            
            while(conn.rs.next()){
             
               
                
              table+="<option value='"+conn.rs.getString(1)+"'>"+conn.rs.getString(2).toUpperCase()+"</option>";  
              
                
                
                                 }
            
             if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            PrintWriter out = response.getWriter();
            try {
                /* TODO output your page here. You may use following sample code. */
                out.println(table);
                
            } finally {
                out.close();
            }
        }   catch (SQLException ex) {
            Logger.getLogger(loadReportedForms.class.getName()).log(Level.SEVERE, null, ex);
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

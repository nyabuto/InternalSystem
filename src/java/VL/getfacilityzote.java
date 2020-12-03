/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VL;

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
public class getfacilityzote extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    try {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        /* TODO output your page here. You may use following sample code. */
        response.setContentType("text/html;charset=UTF-8");
        
        
        String districts="Select Subpartnernom as `Facility Name`, Centresanteid as `mflcode` from subpartnera where active=1  group by `mflcode`";
        
        dbConn conn=new dbConn();
        
        conn.rs=conn.st.executeQuery(districts);
        
        String current_districts="";
        
        while(conn.rs.next()){
            
            
            current_districts=current_districts+"<option value=\""+conn.rs.getString(2)+"\">"+conn.rs.getString(1)+"</option>";
            
            
            
            
            
        }
        
        
        
        
        
        
        
        
        try {
            out.println(current_districts);
            
        } finally {
            if(conn.conn!=null){ conn.conn.close();}
            if(conn.rs!=null){ conn.rs.close();}
            if(conn.st!=null){ conn.st.close();}
            out.close();
        }
        
        
    }   catch (SQLException ex) {
            Logger.getLogger(getfacilityzote.class.getName()).log(Level.SEVERE, null, ex);
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

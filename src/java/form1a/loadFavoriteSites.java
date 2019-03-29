/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form1a;

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
 * @author EKaunda
 */
public class loadFavoriteSites extends HttpServlet {
    HttpSession session=null;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        session=request.getSession();
        
       String userid="";
       
       if(session.getAttribute("userid")!=null)
       {
              userid=session.getAttribute("userid").toString();
       }
        
       dbConn conn= new dbConn();
       
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            String getfavs="select id, clustername,county,subcounty,facility from f1a_clusters where user='"+userid+"'";
             String data="<option value='.'>Bookmark Name</option>";
            try {
                conn.rs=conn.st.executeQuery(getfavs);
               
                
                while(conn.rs.next()){
                
                data+="<option data-county='"+conn.rs.getString("county")+"' data-subcounty='"+conn.rs.getString("subcounty")+"' data-facil='"+conn.rs.getString("facility")+"'   value=''>"+conn.rs.getString("clustername")+"</option>";
                
                                     }
                
            } catch (SQLException ex) {
                Logger.getLogger(loadFavoriteSites.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            if(data.equals("<option value='.'>Bookmark Name</option>"))
            {
            
            data="";
               
            }
            
            out.println(data);
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

}

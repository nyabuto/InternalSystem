/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppprev;

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
public class getFacilitator extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           String groupid="";
           
           if(request.getParameter("group")!=null){
           
           groupid=request.getParameter("group");
           }
          
           String wherestring="";
           
           
           if(!groupid.equals("")){
           wherestring=" where 1=1 ";
           wherestring+=" and groups_id='"+groupid+"' ";
           }
           
           
            dbConn conn = new dbConn();
            
            String data="<option value=''>Select Facilitator</option>";
            String qry="select * from hc_facilitator "+wherestring;
            
            conn.rs= conn.st.executeQuery(qry);
            while(conn.rs.next()){
                String phone="";
                
                if(conn.rs.getString("phone")!=null){phone=conn.rs.getString("phone");}
                
                
                
            String facilitatorname=conn.rs.getString(2)+" "+conn.rs.getString(3)+" "+conn.rs.getString(4);
            
            if(!phone.equals("")){facilitatorname+=" ["+phone+"]";}
            
            data+="<option data-phoneno='"+phone+"' value='"+conn.rs.getString(1)+"'>"+facilitatorname+"</option>";
            
            }
            
           
            
            
            out.println(data);
             if(conn.rs!=null){conn.rs.close();}
            if(conn.st!=null){conn.st.close();}
            
        } catch (SQLException ex) {
            Logger.getLogger(getPartner.class.getName()).log(Level.SEVERE, null, ex);
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HPT;

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
public class updateHptValues extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           
            dbConn conn = new dbConn();            
            
            String Msg="";
                
            String updatepart="";
            //Update CCC mnumber
             if(request.getParameter("rev_is_hpt_controlled")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String vr=request.getParameter("rev_is_hpt_controlled"); 
              
              updatepart+=", rev_is_hpt_controlled='"+vr+"'"; 
              String qry=" update hpt_audit_list set status='HPT Controlled Status Updated' "+updatepart+" where id='"+system_id+"' ;";
                System.out.println("Queeeery niii___"+qry);
                
                if(!vr.equals("0")){
            conn.st.executeUpdate(qry);
            Msg="<b>Hypertension Constrolled status successfully!!</b>";
                }
                else {
                 Msg="<b>Controlled status value "+vr+" NOT updated !!</b>";
                }
            
            }
           
       // update Treatment Status
        if(request.getParameter("is_on_hpt_treat")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String vr=request.getParameter("is_on_hpt_treat"); 
              
              updatepart+=", is_on_hpt_treat='"+vr+"'"; 
              String qry=" update hpt_audit_list set status='Treatment Updated' "+updatepart+" where id='"+system_id+"' ;";
                System.out.println("Queeeery niii___"+qry);
                
                if(!vr.equals("0")){
            conn.st.executeUpdate(qry);
            Msg="<b>Hypertension Treatment status successfully!!</b>";
                }
                else 
                {
                 Msg="<b>Treatment status value "+vr+" NOT updated !!</b>";
                }
            
            }
        
        //Reason not on Treatment updated
        
        if(request.getParameter("reason_not_on_treat")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String vr=request.getParameter("reason_not_on_treat"); 
              
              updatepart+=", reason_not_on_treat='"+vr+"'"; 
              String qry=" update hpt_audit_list set status='Reason not on Treatment Updated' "+updatepart+" where id='"+system_id+"' ;";
                System.out.println("Queeeery niii___"+qry);
                
                if(!vr.equals("0")){
            conn.st.executeUpdate(qry);
            Msg="<b>Reason on Treatment status successfully!!</b>";
                }
                else 
                {
                 Msg="<b>Reason not on Treatment status value "+vr+" NOT updated !!</b>";
                }
            
            }
        
         if(request.getParameter("reason_not_on_treat")!=null && request.getParameter("is_on_hpt_treat")!=null && request.getParameter("rev_is_hpt_controlled")!=null  )
            {
          
              String system_id=request.getParameter("sid");
              String vr=request.getParameter("rev_is_hpt_controlled"); 
              String vr1=request.getParameter("is_on_hpt_treat"); 
              String vr2=request.getParameter("reason_not_on_treat"); 
              
              updatepart+=", rev_is_hpt_controlled='"+vr+"' , is_on_hpt_treat='"+vr1+"' , reason_not_on_treat='"+vr2+"' "; 
              String qry=" update hpt_audit_list set status='All Details Updated' "+updatepart+" where id='"+system_id+"' ;";
                System.out.println("Queeeery niii___"+qry);
                
                if(!vr.equals("0")){
            conn.st.executeUpdate(qry);
            Msg="<b>All Details Updated successfully!!</b>";
                }
                else 
                {
                 Msg="<b>Reason not on Treatment status value "+vr+" NOT updated !!</b>";
                }
            
            }
        
     
                     
             out.print(Msg);
             
             
             
         if(conn.rs!=null){conn.rs.close();}  
         if(conn.st!=null){conn.st.close();}  
        
         if(conn.pst1!=null){conn.pst1.close();}  
         if(conn.conn!=null){conn.conn.close();}  
             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(updateHptValues.class.getName()).log(Level.SEVERE, null, ex);
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

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
 * @author Administrator
 */
public class updateNewRecords extends HttpServlet {

   
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
             if(request.getParameter("ccc")!=null)
            {
          
              String system_id=request.getParameter("sid");
              String ccc=request.getParameter("ccc"); 
              
              updatepart+=", new_cccno='"+ccc+"'"; 
              String qry=" update vl_for_cleaning set status=1 "+updatepart+" where system_id='"+system_id+"' ";
                System.out.println(""+qry);
            conn.st.executeUpdate(qry);
            Msg=" CCC Number "+ccc+" updated successfully!!";
            
            }
            
             //Update PMTCT
             if(request.getParameter("pmtct")!=null)
            {
                
                String system_id=request.getParameter("sid");
                String pmtct=request.getParameter("pmtct");
                
            updatepart+=",new_pmtct='"+pmtct+"'";  
            String qry=" update vl_for_cleaning set status=1 "+updatepart+" where system_id='"+system_id+"' ";
             System.out.println(""+qry);
             conn.st.executeUpdate(qry);
               Msg=" PMTCT type "+pmtct+" updated successfully!!";
            
            }
            
            
             out.print(Msg);
             
             
             
         if(conn.rs!=null){conn.rs.close();}  
         if(conn.st!=null){conn.st.close();}  
        
         if(conn.pst1!=null){conn.pst1.close();}  
         if(conn.conn!=null){conn.conn.close();}  
             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(updateNewRecords.class.getName()).log(Level.SEVERE, null, ex);
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
